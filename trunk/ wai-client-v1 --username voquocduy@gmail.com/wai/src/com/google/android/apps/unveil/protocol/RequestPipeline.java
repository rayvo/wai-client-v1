package com.google.android.apps.unveil.protocol;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Pair;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.BandwidthEstimator;
import com.google.android.apps.unveil.env.TonePlayer;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.network.ContinuousConnector;
import com.google.android.apps.unveil.network.ContinuousNetworkParams;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue.ActiveFrame;
import com.google.android.apps.unveil.protocol.nonstop.ContinuousTracer;
import com.google.android.apps.unveil.protocol.nonstop.DispatchingEventListener;
import com.google.android.apps.unveil.protocol.nonstop.EventListener;
import com.google.android.apps.unveil.protocol.nonstop.FrameEncoder;
import com.google.android.apps.unveil.protocol.nonstop.FrameEncoder.EncodedFrame;
import com.google.android.apps.unveil.protocol.nonstop.PerFrameTracer;
import com.google.android.apps.unveil.protocol.nonstop.RequestIds;
import com.google.android.apps.unveil.protocol.nonstop.RequestPreprocessor;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.sensors.LocationCache;
import com.google.android.apps.unveil.sensors.UnveilLocationProvider;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.tracking.AnnotationTracker;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.GogglesProtos.GogglesRequest;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.TracingProtos.ProcessorStatus.Type;
import com.google.goggles.TracingProtos.TraceRequest;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

public class RequestPipeline extends FrameProcessor
{
  private static final long INITIAL_UPLOAD_DELAY = 1000L;
  private static final float LOCATION_CACHE_EVICTION_METERS = 100.0F;
  private static final int MAX_HISTORY_SIZE = 12;
  private static final int NUM_THROWAWAY_FRAMES = 10;
  private static final float PADDING = 0.2F;
  private static final boolean PLAY_TONES;
  private static final UnveilLogger logger = new UnveilLogger("RequestPipeline", "");
  private float accumulatedDelta;
  private final ActiveFrameQueue activeFrames = new ActiveFrameQueue(12);
  protected final AnnotationTracker annotationTracker;
  protected final UnveilContext application;
  private final BandwidthEstimator bandwidthEstimator;
  protected ContinuousConnectionManager connectionManager;
  private final FrameEncoder frameEncoder = new FrameEncoder();
  private int lastNumResults;
  private long lastRequestDuration = -1L;
  private long lastRequestEstimatedUploadDurationMillis;
  private long lastResponseDuration = -1L;
  private final DispatchingEventListener listeners = new DispatchingEventListener();
  protected final LocationCache locationCache;
  private AtomicInteger nextPushSequenceNumber;
  private int numDroppedResults;
  private int numRequestsSent = 0;
  private int numResponsesReceived = 0;
  private final ContinuousNetworkParams params;
  private final QueryListener pullQueryListener;
  private final PushStrategy pushStrategy;
  private final Iterable<? extends RequestPreprocessor> requestPreprocessors;
  protected boolean running;
  protected UnveilSensorProvider sensorProvider;
  private boolean traceDataPending;
  private final ContinuousTracer tracingHelper;

  public RequestPipeline(UnveilContext paramUnveilContext, ContinuousNetworkParams paramContinuousNetworkParams, PushStrategy paramPushStrategy, AnnotationTracker paramAnnotationTracker, TraceTracker paramTraceTracker, Iterable<? extends RequestPreprocessor> paramIterable)
  {
    this.application = paramUnveilContext;
    this.params = paramContinuousNetworkParams;
    this.annotationTracker = paramAnnotationTracker;
    this.requestPreprocessors = paramIterable;
    this.listeners.addEventListener(paramAnnotationTracker);
    this.pushStrategy = paramPushStrategy;
    this.pullQueryListener = new PullQueryListener(null);
    this.tracingHelper = new ContinuousTracer(paramTraceTracker);
    this.connectionManager = createNewConnectionManager();
    this.bandwidthEstimator = new BandwidthEstimator();
    this.running = false;
    this.locationCache = this.application.getLocationProvider().getLocationCache(100.0F);
    this.sensorProvider = this.application.getSensorProvider();
    reset(false);
  }

  private ContinuousConnectionManager createNewConnectionManager()
  {
    this.nextPushSequenceNumber = new AtomicInteger();
    Session localSession = new Session();
    this.application.getClickTracker().setSessionId(localSession.getSessionId());
    TracingCookieFetcher localTracingCookieFetcher = new TracingCookieFetcher(this.application.getConnector());
    localTracingCookieFetcher.replenish();
    return new ContinuousConnectionManager(this.application, this.pullQueryListener, localTracingCookieFetcher, localSession, this.tracingHelper.getPushLogger(), createResponseExecutor(), this.params);
  }

  private Executor createResponseExecutor()
  {
    return new Executor()
    {
      public void execute(Runnable paramAnonymousRunnable)
      {
        RequestPipeline.this.runOnUiThreadBeforeNextFrame(paramAnonymousRunnable);
      }
    };
  }

  private FrameEncoder.EncodedFrame encode(TimestampedFrame paramTimestampedFrame)
  {
    int i = paramTimestampedFrame.getFrameNum();
    this.tracingHelper.onBeginEncoding(i);
    FrameEncoder.EncodedFrame localEncodedFrame = this.frameEncoder.encode(paramTimestampedFrame);
    this.tracingHelper.onEndEncoding(i);
    this.listeners.onNewRequest(paramTimestampedFrame.getMetadata());
    return localEncodedFrame;
  }

  private void maybeSendTraceData(int paramInt)
  {
    if (!this.traceDataPending)
    {
      TracingProtos.TraceRequest localTraceRequest = this.tracingHelper.getTraceRequest(paramInt);
      this.traceDataPending = this.connectionManager.maybeSendTraceData(new TraceDataListener(null), localTraceRequest);
    }
  }

  private void populateBasicQuery(ContinuousQueryBuilder paramContinuousQueryBuilder, long paramLong)
  {
    int i = this.sensorProvider.getRoundedDeviceOrientation();
    int j = this.application.getViewport().deviceRotationToCameraRotation(i);
    if (i != -1)
      paramContinuousQueryBuilder.addOrientationRelativeToDevice(i);
    if (this.params.source == null)
      throw new AssertionError("Continuous params don't have a source.");
    paramContinuousQueryBuilder.setSource(this.params.source);
    paramContinuousQueryBuilder.addOrientationRelativeToCamera(j);
    paramContinuousQueryBuilder.addLocation(this.locationCache.getLocation(), this.locationCache.getEncryptedLocation());
    int k = this.nextPushSequenceNumber.getAndIncrement();
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(paramLong);
    arrayOfObject[1] = Integer.valueOf(k);
    localUnveilLogger.v("Sending request requestIdBase=%d; sequenceNumber=%d", arrayOfObject);
    paramContinuousQueryBuilder.addRequestIdBase(Long.valueOf(paramLong)).setSession(this.connectionManager.getSession());
    paramContinuousQueryBuilder.addPushSequenceNumber(Integer.valueOf(k));
  }

  private void processResponse(QueryResponse paramQueryResponse)
  {
    ArrayList localArrayList = new ArrayList();
    this.lastNumResults = paramQueryResponse.getResults().size();
    synchronized (this.activeFrames)
    {
      Iterator localIterator = paramQueryResponse.getResults().iterator();
      if (localIterator.hasNext())
      {
        ResultItem localResultItem = (ResultItem)localIterator.next();
        localArrayList.add(Pair.create(localResultItem, processResult(localResultItem, paramQueryResponse.getResponseReceivedTimestamp())));
      }
    }
    this.listeners.onNewResults(localArrayList);
  }

  private ActiveFrameQueue.ActiveFrame processResult(ResultItem paramResultItem, long paramLong)
  {
    logger.v("Result: %s", new Object[] { paramResultItem });
    AnnotationResultProtos.AnnotationResult localAnnotationResult = paramResultItem.getAnnotationResult();
    if ((!localAnnotationResult.hasBoundingBoxRequestId()) || (!localAnnotationResult.hasBoundingBox()))
    {
      logger.v("No bounding box, aborting.", new Object[0]);
      return null;
    }
    long l = RequestIds.decodeBaseRequestId(localAnnotationResult.getBoundingBoxRequestId());
    synchronized (this.activeFrames)
    {
      ActiveFrameQueue.ActiveFrame localActiveFrame = this.activeFrames.getByTimestamp(l);
      if (localActiveFrame != null)
      {
        if (localActiveFrame.getMetadata().addResult(paramResultItem))
        {
          int i = localActiveFrame.getFrameNum();
          this.tracingHelper.onResult(i, paramLong);
        }
        return localActiveFrame;
      }
    }
    logger.w("Dropping result!", new Object[0]);
    this.numDroppedResults = (1 + this.numDroppedResults);
    return null;
  }

  private void pushImage(TimestampedFrame paramTimestampedFrame)
  {
    logger.i("Making request", new Object[0]);
    paramTimestampedFrame.getFrameNum();
    this.tracingHelper.onMakingRequest(this.activeFrames.getLastFrameNum(), paramTimestampedFrame);
    this.pushStrategy.onPush(paramTimestampedFrame);
    paramTimestampedFrame.getMetadata().onPickedForQuery();
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.connectionManager.getSessionId();
    localUnveilLogger.i("Making request on session %s", arrayOfObject);
    FrameEncoder.EncodedFrame localEncodedFrame = encode(paramTimestampedFrame);
    ContinuousQueryBuilder localContinuousQueryBuilder = makeQuery(localEncodedFrame);
    ActiveFrameQueue.ActiveFrame localActiveFrame = this.activeFrames.maybeAdd(paramTimestampedFrame, localEncodedFrame.jpegData);
    byte[] arrayOfByte = localContinuousQueryBuilder.buildGogglesRequestBuilder(this.application).build().toByteArray();
    PushQueryListener localPushQueryListener = new PushQueryListener(localActiveFrame);
    localPushQueryListener.onSending(arrayOfByte.length);
    this.connectionManager.getConnector().serialPush(arrayOfByte, this.connectionManager.newPushHandler(localPushQueryListener), paramTimestampedFrame.getFrameNum());
    TonePlayer.logTone(8, 100, 90, false);
  }

  private boolean shouldRequest(TimestampedFrame paramTimestampedFrame)
  {
    if (paramTimestampedFrame.getFrameNum() < 10);
    while ((!this.connectionManager.canPush()) || (!this.pushStrategy.shouldPush(paramTimestampedFrame)))
      return false;
    return true;
  }

  public void addEventListener(EventListener paramEventListener)
  {
    try
    {
      this.listeners.addEventListener(paramEventListener);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addPerFrameTracer(PerFrameTracer paramPerFrameTracer)
  {
    this.tracingHelper.addPerFrameTracer(paramPerFrameTracer);
  }

  public Vector<String> getDebugText()
  {
    Vector localVector;
    try
    {
      localVector = new Vector();
      localVector.add("Session: " + this.connectionManager.getSessionId());
      localVector.add("FE: " + this.application.getFrontendUrl().toString());
      localVector.add("Tx|Rx times and counts: " + this.lastRequestDuration + " | " + this.lastResponseDuration + "       " + this.numRequestsSent + " | " + this.numResponsesReceived);
      localVector.add("Num results: " + this.lastNumResults);
      localVector.add("Num results dropped: " + this.numDroppedResults);
      localVector.add("Accum move amount: " + this.accumulatedDelta);
      localVector.addAll(this.bandwidthEstimator.getDebugText());
      Iterator localIterator = this.requestPreprocessors.iterator();
      while (localIterator.hasNext())
        ((RequestPreprocessor)localIterator.next()).addDebugText(localVector);
    }
    finally
    {
    }
    return localVector;
  }

  protected TracingProtos.ProcessorStatus.Type getProcessorType()
  {
    return TracingProtos.ProcessorStatus.Type.REQUEST_PIPELINE;
  }

  public String getSessionId()
  {
    return this.connectionManager.getSessionId();
  }

  protected ContinuousQueryBuilder makeQuery(FrameEncoder.EncodedFrame paramEncodedFrame)
  {
    ContinuousQueryBuilder localContinuousQueryBuilder = new ContinuousQueryBuilder();
    populateQueryBuilder(localContinuousQueryBuilder, paramEncodedFrame);
    Iterator localIterator = this.requestPreprocessors.iterator();
    while (localIterator.hasNext())
      ((RequestPreprocessor)localIterator.next()).preprocess(localContinuousQueryBuilder);
    return localContinuousQueryBuilder;
  }

  protected void onDrawDebug(Canvas paramCanvas)
  {
    this.activeFrames.drawDebug(paramCanvas);
  }

  protected void onPause()
  {
    try
    {
      pausePipeline();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    try
    {
      boolean bool = this.running;
      if (!bool);
      while (true)
      {
        return;
        int i = 1;
        this.listeners.onNewFrame(paramTimestampedFrame);
        this.tracingHelper.onProcessFrame(paramTimestampedFrame.getFrameNum());
        if (shouldRequest(paramTimestampedFrame))
        {
          long l = System.currentTimeMillis();
          this.tracingHelper.onWillRequest(paramTimestampedFrame.getFrameNum());
          pushImage(paramTimestampedFrame);
          this.lastRequestDuration = (System.currentTimeMillis() - l);
          this.numRequestsSent = (1 + this.numRequestsSent);
          i = 0;
        }
        if (i != 0)
          discardFrame();
      }
    }
    finally
    {
    }
  }

  protected void onShutdown()
  {
    try
    {
      this.running = false;
      this.connectionManager.disconnect();
      this.application.getClickTracker().setSessionId("");
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void onStart()
  {
    try
    {
      startPipeline();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void outOfBandPush(TimestampedFrame paramTimestampedFrame, ContinuousQueryBuilder paramContinuousQueryBuilder)
  {
    ActiveFrameQueue.ActiveFrame localActiveFrame = this.activeFrames.maybeAdd(paramTimestampedFrame, null);
    populateBasicQuery(paramContinuousQueryBuilder, paramTimestampedFrame.getTimestamp());
    byte[] arrayOfByte = paramContinuousQueryBuilder.buildGogglesRequestBuilder(this.application).build().toByteArray();
    PushQueryListener localPushQueryListener = new PushQueryListener(localActiveFrame);
    localPushQueryListener.onSending(arrayOfByte.length);
    this.tracingHelper.onClientAnnotationPush(paramTimestampedFrame.getFrameNum());
    this.connectionManager.getConnector().outOfBandPush(arrayOfByte, this.connectionManager.newPushHandler(localPushQueryListener), paramTimestampedFrame.getFrameNum());
    TonePlayer.logTone(8, 100, 90, false);
  }

  public void pausePipeline()
  {
    try
    {
      this.running = false;
      this.connectionManager.pause();
      Iterator localIterator = this.requestPreprocessors.iterator();
      while (localIterator.hasNext())
        ((RequestPreprocessor)localIterator.next()).onPause();
    }
    finally
    {
    }
    maybeSendTraceData(1);
  }

  protected void populateQueryBuilder(ContinuousQueryBuilder paramContinuousQueryBuilder, FrameEncoder.EncodedFrame paramEncodedFrame)
  {
    populateBasicQuery(paramContinuousQueryBuilder, paramEncodedFrame.timestamp);
    this.annotationTracker.addTrackingDataToQuery(paramContinuousQueryBuilder, paramEncodedFrame.timestamp);
    paramContinuousQueryBuilder.addJpegQuality(paramEncodedFrame.quality).addImageData(paramEncodedFrame.jpegData, paramEncodedFrame.size, false);
  }

  public void reset()
  {
    try
    {
      reset(true);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void reset(boolean paramBoolean)
  {
    try
    {
      this.bandwidthEstimator.reset();
      this.activeFrames.clear();
      this.lastRequestEstimatedUploadDurationMillis = 0L;
      this.application.getTraceTracker();
      this.tracingHelper.reset();
      if (paramBoolean)
      {
        this.connectionManager.disconnect();
        this.connectionManager = createNewConnectionManager();
      }
      this.traceDataPending = false;
      this.accumulatedDelta = 0.0F;
      this.numDroppedResults = 0;
      this.lastNumResults = 0;
      this.pushStrategy.onReset();
      this.listeners.onSessionReset();
      Iterator localIterator = this.requestPreprocessors.iterator();
      while (localIterator.hasNext())
        ((RequestPreprocessor)localIterator.next()).onReset();
    }
    finally
    {
    }
  }

  public void startPipeline()
  {
    this.running = true;
    this.activeFrames.clear();
    Iterator localIterator = this.requestPreprocessors.iterator();
    while (localIterator.hasNext())
      ((RequestPreprocessor)localIterator.next()).onStart();
  }

  private abstract class ContinuousRequestQueryListener extends QueryListener
  {
    private ContinuousRequestQueryListener()
    {
    }

    public void onAuthenticationError()
    {
      RequestPipeline.logger.e("Authentication error!", new Object[0]);
      RequestPipeline.this.reset();
    }

    public void onNetworkError(int paramInt)
    {
      UnveilLogger localUnveilLogger = RequestPipeline.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.e("Network error (%d)!", arrayOfObject);
      RequestPipeline.this.listeners.onNetworkError(paramInt);
      RequestPipeline.this.reset();
    }
  }

  private class PullQueryListener extends RequestPipeline.ContinuousRequestQueryListener
  {
    private PullQueryListener()
    {
      super(null);
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      TonePlayer.logTone(3, 120, 90, false);
      if ((paramQueryResponse != null) && (RequestPipeline.this.running))
      {
        long l = System.currentTimeMillis();
        RequestPipeline.this.processResponse(paramQueryResponse);
        RequestPipeline.access$1002(RequestPipeline.this, System.currentTimeMillis() - l);
        RequestPipeline.access$1104(RequestPipeline.this);
      }
      RequestPipeline.this.listeners.onPullReceived();
    }
  }

  private class PushQueryListener extends RequestPipeline.ContinuousRequestQueryListener
  {
    private final ActiveFrameQueue.ActiveFrame queryFrame;

    public PushQueryListener(ActiveFrameQueue.ActiveFrame arg2)
    {
      super(null);
      Object localObject;
      this.queryFrame = localObject;
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      if (!RequestPipeline.this.running)
        return;
      RequestPipeline.this.listeners.onPushReceived(this.queryFrame.metadata);
      RequestPipeline.this.bandwidthEstimator.requestIn(SystemClock.uptimeMillis());
      this.queryFrame.getMetadata().onQuerySent();
      int i = this.queryFrame.getFrameNum();
      UnveilLogger localUnveilLogger = RequestPipeline.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(i);
      localUnveilLogger.i("Push response for frame %d", arrayOfObject);
      RequestPipeline.this.tracingHelper.onQueryRespnse(RequestPipeline.this.activeFrames.getActionNumbers(), i);
      TonePlayer.logTone(1, 100, 90, false);
      RequestPipeline.this.maybeSendTraceData(300);
    }

    public void onSending(int paramInt)
    {
      RequestPipeline.this.bandwidthEstimator.requestOut(SystemClock.uptimeMillis(), paramInt);
      float f = RequestPipeline.this.bandwidthEstimator.getThroughputBps();
      if (f > 0.0F)
        RequestPipeline.access$402(RequestPipeline.this, ()(1000.0F * (1.2F * (paramInt / f))));
      while (true)
      {
        RequestPipeline.this.listeners.onPushSent(this.queryFrame.metadata, this.queryFrame.cachedJpeg);
        UnveilLogger localUnveilLogger = RequestPipeline.logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Long.valueOf(RequestPipeline.this.lastRequestEstimatedUploadDurationMillis);
        arrayOfObject[1] = Integer.valueOf(paramInt);
        localUnveilLogger.d("Estimating that it will take %d ms to upload this %d byte request.", arrayOfObject);
        this.queryFrame.getMetadata().onQueryStartSending();
        int i = this.queryFrame.getFrameNum();
        RequestPipeline.this.tracingHelper.onSending(i);
        return;
        RequestPipeline.access$402(RequestPipeline.this, 1000L);
      }
    }
  }

  public static abstract interface PushStrategy
  {
    public abstract void onPush(TimestampedFrame paramTimestampedFrame);

    public abstract void onReset();

    public abstract boolean shouldPush(TimestampedFrame paramTimestampedFrame);
  }

  private class TraceDataListener extends RequestPipeline.ContinuousRequestQueryListener
  {
    private TraceDataListener()
    {
      super(null);
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      synchronized (RequestPipeline.this)
      {
        RequestPipeline.access$1202(RequestPipeline.this, false);
        return;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.RequestPipeline
 * JD-Core Version:    0.6.2
 */