package com.google.android.apps.unveil.protocol;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Handler;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.ImageSaver;
import com.google.android.apps.unveil.env.ImageUtils.RotatePhotoTask;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.DeleteListener;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutionException;

public class QueryPipeline
{
  private static final int QUEUED_IMAGE_REQUEST_DELAY_MS = 1000;
  private static final UnveilLogger logger = new UnveilLogger();
  private final UnveilContext application;
  private MakeRequestTask currentMakeRequestTask;
  private PipelinedQueryListener currentQueryListener;
  private SaveQueryImageTask currentSaveQueryImageTask;
  private final ImageSaver imageSaver;
  private QueryBuilder pendingQuery;
  private ImageUtils.RotatePhotoTask photoTask;
  private final PosterityRequestsHandler posterityRequestsHandler;
  private final QueryManager queryManager;
  private Picture queryPicture;
  private volatile boolean queuedImageRequest;
  private volatile boolean readyToSendImageRequest = true;
  private volatile boolean sentBarcodeRequest;
  private final TraceTracker traceTracker;

  public QueryPipeline(UnveilContext paramUnveilContext, ImageSaver paramImageSaver)
  {
    this.application = paramUnveilContext;
    this.traceTracker = paramUnveilContext.getTraceTracker();
    this.queryManager = paramUnveilContext.getQueryManager();
    this.imageSaver = paramImageSaver;
    this.posterityRequestsHandler = new PosterityRequestsHandler(null);
  }

  private void handleBarcodeResponse(QueryResponse paramQueryResponse)
  {
    if (this.currentMakeRequestTask != null)
    {
      logger.v("currentMakeRequestTask was not null.", new Object[0]);
      this.currentMakeRequestTask.eventListener.onQueryResponse(paramQueryResponse);
    }
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(1000);
    localUnveilLogger.time("Will check for a queued image request in %d ms", arrayOfObject);
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        if (QueryPipeline.this.queuedImageRequest)
        {
          if (QueryPipeline.this.currentMakeRequestTask == null)
          {
            QueryPipeline.logger.e("queuedImageRequest was true, but currentMakeRequestTask was null", new Object[0]);
            QueryPipeline.access$802(QueryPipeline.this, false);
          }
        }
        else
          return;
        QueryPipeline.logger.v("Found a queued image request, sending.", new Object[0]);
        try
        {
          QueryPipeline.this.sendImageRequest(QueryPipeline.this.currentMakeRequestTask.eventListener, true);
          return;
        }
        catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
        {
          QueryPipeline.logger.e(localNoPendingQueryException, "Failed to send a posterity request", new Object[0]);
        }
      }
    }
    , 1000L);
  }

  private void sendImageRequest(EventListener paramEventListener, boolean paramBoolean)
    throws QueryPipeline.NoPendingQueryException
  {
    this.queuedImageRequest = false;
    logger.time("Sending image request.", new Object[0]);
    this.currentQueryListener = new PipelinedQueryListener(paramEventListener, paramBoolean);
    this.queryManager.sendQuery(this.application, getPendingQuery(), this.currentQueryListener, false);
  }

  public final void asyncProcessPicture()
  {
    if (this.photoTask == null)
      logger.e("asyncProcessPicture was called, but photoTask is null", new Object[0]);
    while (!this.photoTask.getStatus().equals(AsyncTask.Status.PENDING))
      return;
    this.photoTask.execute(new Void[0]);
  }

  public void cancel()
  {
    logger.v("Canceling.", new Object[0]);
    if ((this.currentMakeRequestTask != null) && (this.currentMakeRequestTask.getStatus() != AsyncTask.Status.FINISHED))
    {
      logger.v("Canceling the make request task.", new Object[0]);
      this.currentMakeRequestTask.cancel(true);
      this.currentMakeRequestTask = null;
    }
    if ((this.currentSaveQueryImageTask != null) && (this.currentSaveQueryImageTask.getStatus() != AsyncTask.Status.FINISHED))
    {
      logger.v("Canceling the save query image task.", new Object[0]);
      this.currentSaveQueryImageTask.cancel(true);
      this.currentSaveQueryImageTask = null;
    }
    if (this.currentQueryListener != null)
    {
      logger.v("Canceling the query listener.", new Object[0]);
      this.currentQueryListener.cancel();
    }
  }

  public QueryBuilder getPendingQuery()
    throws QueryPipeline.NoPendingQueryException
  {
    if (this.pendingQuery == null)
      throw new NoPendingQueryException();
    return this.pendingQuery;
  }

  public final Picture getPicture()
  {
    if ((this.photoTask == null) && (this.queryPicture == null))
    {
      logger.e("PhotoTask and picture are null", new Object[0]);
      return null;
    }
    if (this.queryPicture != null)
      return this.queryPicture;
    try
    {
      Picture localPicture = (Picture)this.photoTask.get();
      return localPicture;
    }
    catch (InterruptedException localInterruptedException)
    {
      logger.e(localInterruptedException, "PhotoTask interrupted", new Object[0]);
      return null;
    }
    catch (ExecutionException localExecutionException)
    {
      logger.e(localExecutionException, "PhotoTask failed", new Object[0]);
    }
    return null;
  }

  public boolean hasPendingQuery()
  {
    return this.pendingQuery != null;
  }

  public boolean hasSentBarcodeRequest()
  {
    return this.sentBarcodeRequest;
  }

  public final boolean isPictureReady()
  {
    if (this.photoTask != null)
      return AsyncTask.Status.FINISHED.equals(this.photoTask.getStatus());
    return this.queryPicture != null;
  }

  public void resend(EventListener paramEventListener)
    throws QueryPipeline.NoPendingQueryException
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = getPendingQuery();
    localUnveilLogger.v("Resending with pending query: %s", arrayOfObject);
    sendImageRequest(paramEventListener, false);
  }

  public void sendLocalBarcodeQuery(QueryBuilder paramQueryBuilder, final EventListener paramEventListener)
  {
    logger.i("Sending local barcode query.", new Object[0]);
    logger.d(paramQueryBuilder.toString(), new Object[0]);
    if (paramQueryBuilder.getLocalBarcode() == null)
      throw new IllegalArgumentException("Cannot perform local barcode query with a barcodeless QueryBuilder.");
    paramQueryBuilder.setQueryType(QueryResponseFactory.QueryType.LOCAL_BARCODE);
    this.sentBarcodeRequest = true;
    logger.resetTime("Sending local barcode request", new Object[0]);
    this.readyToSendImageRequest = false;
    this.queryManager.sendQuery(this.application, paramQueryBuilder, new QueryListener()
    {
      public void onAuthenticationError()
      {
      }

      public void onHeavyProcessingEnd()
      {
      }

      public void onHeavyProcessingStart()
      {
      }

      public void onNetworkError(int paramAnonymousInt)
      {
        QueryPipeline.logger.e("Network error during local barcode request.", new Object[0]);
        QueryPipeline.access$402(QueryPipeline.this, true);
        QueryPipeline.access$502(QueryPipeline.this, false);
        if (QueryPipeline.this.currentMakeRequestTask != null)
        {
          QueryPipeline.this.currentMakeRequestTask.eventListener.onNetworkError(paramAnonymousInt);
          return;
        }
        QueryPipeline.logger.e("We could not notify a listener of this failure.", new Object[0]);
      }

      public void onQueryResponse(QueryResponse paramAnonymousQueryResponse)
      {
        QueryPipeline.access$402(QueryPipeline.this, true);
        QueryPipeline.logger.time("Barcode Response received by QueryPipeline.", new Object[0]);
        paramEventListener.onQueryResponse(paramAnonymousQueryResponse);
        QueryPipeline.this.handleBarcodeResponse(paramAnonymousQueryResponse);
      }
    }
    , false);
  }

  public void setPendingQuery(QueryBuilder paramQueryBuilder)
  {
    logger.v("Pending query set: %s", new Object[] { paramQueryBuilder });
    this.pendingQuery = paramQueryBuilder;
  }

  public void setPosterityListener(PosterityRequestListener paramPosterityRequestListener)
  {
    this.posterityRequestsHandler.setListener(paramPosterityRequestListener);
  }

  public void setProcessedPicture(Picture paramPicture)
  {
    this.queryPicture = paramPicture;
    this.photoTask = null;
  }

  public void start(Intent paramIntent, Picture paramPicture, EventListener paramEventListener)
  {
    if ((paramPicture != null) && (paramEventListener != null))
      paramEventListener.onImageCropped(paramPicture.getCroppedPicture());
    this.currentMakeRequestTask = new MakeRequestTask(paramIntent, paramEventListener);
    this.currentMakeRequestTask.execute(new Void[0]);
  }

  public void startNewQuery()
  {
    this.pendingQuery = null;
    this.readyToSendImageRequest = true;
    this.sentBarcodeRequest = false;
    this.posterityRequestsHandler.reset();
    logger.resetTime("Starting new query.", new Object[0]);
    this.traceTracker.startNewTraceAction();
    this.application.getClickTracker().clearTrackingId();
    this.application.getClickTracker().setSessionId("");
    if (this.currentMakeRequestTask != null)
    {
      this.currentMakeRequestTask.cancel(true);
      this.currentMakeRequestTask = null;
    }
    SessionlessRequests.sendLogs(this.application);
  }

  public final void startRotatePhotoTask(Picture paramPicture)
  {
    if (paramPicture == this.queryPicture)
      logger.w("Setting same picture twice!", new Object[0]);
    while (true)
    {
      this.queryPicture = null;
      this.photoTask = new ImageUtils.RotatePhotoTask(paramPicture, 408960);
      return;
      if (this.queryPicture != null)
        this.queryPicture.recycle();
    }
  }

  public static abstract class EventListener extends QueryListener
  {
    public abstract void onImageCropped(Picture paramPicture);

    public abstract void onImageFailed();

    public abstract void onImageReady(Picture paramPicture, QueryBuilder paramQueryBuilder);
  }

  private class MakeRequestTask extends AsyncTask<Void, Void, Void>
  {
    volatile QueryPipeline.EventListener eventListener;
    private byte[] imageData;
    private final Intent intent;

    public MakeRequestTask(Intent paramEventListener, QueryPipeline.EventListener arg3)
    {
      this.intent = paramEventListener;
      Object localObject;
      this.eventListener = localObject;
    }

    protected Void doInBackground(Void[] paramArrayOfVoid)
    {
      Thread.currentThread().setName("MakeRequestTask");
      QueryPipeline.logger.time("MakeRequestTask started in background", new Object[0]);
      Picture localPicture = QueryPipeline.this.getPicture();
      QueryPipeline.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.IMAGE_REENCODE);
      if (localPicture == null)
      {
        QueryPipeline.logger.e("Picture is null, aborting query.", new Object[0]);
        this.imageData = null;
        return null;
      }
      this.imageData = localPicture.getJpegData();
      QueryPipeline.logger.time("MakeRequestTask done in background", new Object[0]);
      return null;
    }

    protected void onCancelled()
    {
      QueryPipeline.logger.d("Cancelling request construction.", new Object[0]);
      this.imageData = null;
    }

    // ERROR //
    protected void onPostExecute(Void paramVoid)
    {
      // Byte code:
      //   0: invokestatic 49	com/google/android/apps/unveil/protocol/QueryPipeline:access$000	()Lcom/google/android/apps/unveil/env/UnveilLogger;
      //   3: ldc 113
      //   5: iconst_0
      //   6: anewarray 53	java/lang/Object
      //   9: invokevirtual 59	com/google/android/apps/unveil/env/UnveilLogger:time	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   12: aload_0
      //   13: aload_1
      //   14: invokespecial 115	android/os/AsyncTask:onPostExecute	(Ljava/lang/Object;)V
      //   17: aload_0
      //   18: invokevirtual 119	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:isCancelled	()Z
      //   21: ifne +10 -> 31
      //   24: aload_0
      //   25: getfield 86	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:imageData	[B
      //   28: ifnonnull +18 -> 46
      //   31: aload_0
      //   32: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   35: ifnull +10 -> 45
      //   38: aload_0
      //   39: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   42: invokevirtual 124	com/google/android/apps/unveil/protocol/QueryPipeline$EventListener:onImageFailed	()V
      //   45: return
      //   46: aload_0
      //   47: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   50: invokevirtual 128	com/google/android/apps/unveil/protocol/QueryPipeline:getPendingQuery	()Lcom/google/android/apps/unveil/protocol/QueryBuilder;
      //   53: aload_0
      //   54: getfield 86	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:imageData	[B
      //   57: aload_0
      //   58: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   61: invokevirtual 63	com/google/android/apps/unveil/protocol/QueryPipeline:getPicture	()Lcom/google/android/apps/unveil/env/Picture;
      //   64: invokevirtual 132	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
      //   67: invokevirtual 138	com/google/android/apps/unveil/protocol/QueryBuilder:addImageData	([BLcom/google/android/apps/unveil/env/Size;)Lcom/google/android/apps/unveil/protocol/QueryBuilder;
      //   70: pop
      //   71: aload_0
      //   72: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   75: ifnull +24 -> 99
      //   78: aload_0
      //   79: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   82: aload_0
      //   83: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   86: invokevirtual 63	com/google/android/apps/unveil/protocol/QueryPipeline:getPicture	()Lcom/google/android/apps/unveil/env/Picture;
      //   89: aload_0
      //   90: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   93: invokevirtual 128	com/google/android/apps/unveil/protocol/QueryPipeline:getPendingQuery	()Lcom/google/android/apps/unveil/protocol/QueryBuilder;
      //   96: invokevirtual 142	com/google/android/apps/unveil/protocol/QueryPipeline$EventListener:onImageReady	(Lcom/google/android/apps/unveil/env/Picture;Lcom/google/android/apps/unveil/protocol/QueryBuilder;)V
      //   99: aload_0
      //   100: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   103: new 144	com/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask
      //   106: dup
      //   107: aload_0
      //   108: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   111: aload_0
      //   112: getfield 22	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:intent	Landroid/content/Intent;
      //   115: invokespecial 147	com/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask:<init>	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;Landroid/content/Intent;)V
      //   118: invokestatic 151	com/google/android/apps/unveil/protocol/QueryPipeline:access$1102	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;Lcom/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask;)Lcom/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask;
      //   121: pop
      //   122: aload_0
      //   123: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   126: invokestatic 155	com/google/android/apps/unveil/protocol/QueryPipeline:access$400	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;)Z
      //   129: ifeq +78 -> 207
      //   132: aload_0
      //   133: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   136: aload_0
      //   137: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   140: aload_0
      //   141: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   144: invokevirtual 158	com/google/android/apps/unveil/protocol/QueryPipeline:hasSentBarcodeRequest	()Z
      //   147: invokestatic 162	com/google/android/apps/unveil/protocol/QueryPipeline:access$900	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;Z)V
      //   150: ldc2_w 163
      //   153: invokestatic 168	java/lang/Thread:sleep	(J)V
      //   156: aload_0
      //   157: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   160: ifnull +10 -> 170
      //   163: aload_0
      //   164: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   167: invokevirtual 171	com/google/android/apps/unveil/protocol/QueryPipeline$EventListener:onHeavyProcessingEnd	()V
      //   170: aload_0
      //   171: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   174: invokestatic 175	com/google/android/apps/unveil/protocol/QueryPipeline:access$1100	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;)Lcom/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask;
      //   177: astore 7
      //   179: iconst_1
      //   180: anewarray 176	[B
      //   183: astore 8
      //   185: aload 8
      //   187: iconst_0
      //   188: aload_0
      //   189: getfield 86	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:imageData	[B
      //   192: aastore
      //   193: aload 7
      //   195: aload 8
      //   197: invokevirtual 180	com/google/android/apps/unveil/protocol/QueryPipeline$SaveQueryImageTask:execute	([Ljava/lang/Object;)Landroid/os/AsyncTask;
      //   200: pop
      //   201: aload_0
      //   202: aconst_null
      //   203: putfield 86	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:imageData	[B
      //   206: return
      //   207: aload_0
      //   208: getfield 17	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:this$0	Lcom/google/android/apps/unveil/protocol/QueryPipeline;
      //   211: iconst_1
      //   212: invokestatic 184	com/google/android/apps/unveil/protocol/QueryPipeline:access$802	(Lcom/google/android/apps/unveil/protocol/QueryPipeline;Z)Z
      //   215: pop
      //   216: invokestatic 49	com/google/android/apps/unveil/protocol/QueryPipeline:access$000	()Lcom/google/android/apps/unveil/env/UnveilLogger;
      //   219: ldc 186
      //   221: iconst_0
      //   222: anewarray 53	java/lang/Object
      //   225: invokevirtual 189	com/google/android/apps/unveil/env/UnveilLogger:v	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   228: goto -78 -> 150
      //   231: astore_2
      //   232: invokestatic 49	com/google/android/apps/unveil/protocol/QueryPipeline:access$000	()Lcom/google/android/apps/unveil/env/UnveilLogger;
      //   235: ldc 191
      //   237: iconst_0
      //   238: anewarray 53	java/lang/Object
      //   241: invokevirtual 84	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   244: aload_0
      //   245: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   248: ifnull -203 -> 45
      //   251: aload_0
      //   252: getfield 24	com/google/android/apps/unveil/protocol/QueryPipeline$MakeRequestTask:eventListener	Lcom/google/android/apps/unveil/protocol/QueryPipeline$EventListener;
      //   255: invokevirtual 124	com/google/android/apps/unveil/protocol/QueryPipeline$EventListener:onImageFailed	()V
      //   258: return
      //   259: astore 6
      //   261: goto -105 -> 156
      //
      // Exception table:
      //   from	to	target	type
      //   46	99	231	com/google/android/apps/unveil/protocol/QueryPipeline$NoPendingQueryException
      //   99	150	231	com/google/android/apps/unveil/protocol/QueryPipeline$NoPendingQueryException
      //   207	228	231	com/google/android/apps/unveil/protocol/QueryPipeline$NoPendingQueryException
      //   150	156	259	java/lang/InterruptedException
    }

    protected void onPreExecute()
    {
      QueryPipeline.logger.time("MakeRequestTask pre execute", new Object[0]);
      super.onPreExecute();
      QueryPipeline.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.IMAGE_REENCODE);
      QueryPipeline.this.asyncProcessPicture();
    }
  }

  public static class NoPendingQueryException extends Exception
  {
  }

  private class PipelinedQueryListener extends QueryListener
  {
    private final boolean isPosterity;
    private final QueryPipeline.EventListener listener;
    private boolean queryCanceled;

    public PipelinedQueryListener(QueryPipeline.EventListener paramBoolean, boolean arg3)
    {
      this.listener = paramBoolean;
      boolean bool;
      this.isPosterity = bool;
    }

    public void cancel()
    {
      this.queryCanceled = true;
    }

    public void onAuthenticationError()
    {
      if ((this.listener != null) && (!this.queryCanceled))
        this.listener.onAuthenticationError();
    }

    public void onHeavyProcessingEnd()
    {
      if ((this.listener != null) && (!this.queryCanceled))
        this.listener.onHeavyProcessingEnd();
    }

    public void onHeavyProcessingStart()
    {
      if ((this.listener != null) && (!this.queryCanceled))
        this.listener.onHeavyProcessingStart();
    }

    public void onNetworkError(int paramInt)
    {
      if ((this.listener != null) && (!this.queryCanceled))
        this.listener.onNetworkError(paramInt);
      UnveilLogger localUnveilLogger = QueryPipeline.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.e("sendRequest network error: %d", arrayOfObject);
    }

    public void onQueryResponse(final QueryResponse paramQueryResponse)
    {
      QueryPipeline.logger.v("sendImageRequest.onQueryResponse %s", new Object[] { paramQueryResponse });
      if (this.queryCanceled)
        if ((paramQueryResponse != null) && (paramQueryResponse.getMomentId() != null))
          QueryPipeline.this.application.getSearchHistoryProvider().delete(paramQueryResponse.getMomentId(), new SearchHistoryProvider.DeleteListener()
          {
            public void onError()
            {
              QueryPipeline.logger.w("Error while silently deleting query saved for later.", new Object[0]);
            }

            public void onResult()
            {
              UnveilLogger localUnveilLogger = QueryPipeline.logger;
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = paramQueryResponse.getMomentId();
              localUnveilLogger.i("Moment %s silently deleted.", arrayOfObject);
            }
          });
      do
      {
        return;
        if (this.isPosterity)
        {
          QueryPipeline.logger.v("Posterity response.", new Object[0]);
          QueryPipeline.this.posterityRequestsHandler.onPosterityResponse(paramQueryResponse);
          return;
        }
      }
      while (this.listener == null);
      QueryPipeline.logger.v("Calling listener.onResult", new Object[0]);
      this.listener.onQueryResponse(paramQueryResponse);
    }
  }

  public static abstract interface PosterityRequestListener
  {
    public abstract void onPosterityResponse(QueryResponse paramQueryResponse);
  }

  private static class PosterityRequestsHandler
  {
    private WeakReference<QueryPipeline.PosterityRequestListener> listenerReference;
    private QueryResponse posterityResponse;

    public void onPosterityResponse(QueryResponse paramQueryResponse)
    {
      QueryPipeline.logger.v("onPosterityResponse", new Object[0]);
      this.posterityResponse = paramQueryResponse;
      if (this.listenerReference != null)
      {
        QueryPipeline.PosterityRequestListener localPosterityRequestListener = (QueryPipeline.PosterityRequestListener)this.listenerReference.get();
        if (localPosterityRequestListener != null)
          localPosterityRequestListener.onPosterityResponse(paramQueryResponse);
      }
      else
      {
        return;
      }
      this.listenerReference = null;
    }

    public void reset()
    {
      this.listenerReference = null;
      this.posterityResponse = null;
    }

    public void setListener(QueryPipeline.PosterityRequestListener paramPosterityRequestListener)
    {
      this.listenerReference = new WeakReference(paramPosterityRequestListener);
      if (this.posterityResponse != null)
        paramPosterityRequestListener.onPosterityResponse(this.posterityResponse);
    }
  }

  private class SaveQueryImageTask extends AsyncTask<byte[], Void, Uri>
  {
    private final Intent intent;

    public SaveQueryImageTask(Intent arg2)
    {
      Object localObject;
      this.intent = localObject;
    }

    protected Uri doInBackground(byte[][] paramArrayOfByte)
    {
      Thread.currentThread().setName("SaveQueryImageTask");
      if ((!this.intent.getBooleanExtra("is_shared_query", false)) && (Settings.getBoolean((Context)QueryPipeline.this.application, R.string.save_to_sd_card_key)));
      for (int i = 1; i != 0; i = 0)
        return QueryPipeline.this.imageSaver.saveToGallery(paramArrayOfByte[0]);
      return null;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryPipeline
 * JD-Core Version:    0.6.2
 */