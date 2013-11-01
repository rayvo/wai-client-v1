package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.barcode.Barcode;
import com.google.android.apps.unveil.barcode.BarcodeRecognizer.Mode;
import com.google.android.apps.unveil.barcode.BarcodeScanner;
import com.google.android.apps.unveil.barcode.LocalBarcodeListener;
import com.google.android.apps.unveil.barcode.LocalBarcodeListener.Callback;
import com.google.android.apps.unveil.env.Clock;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.Timeout;
import com.google.android.apps.unveil.env.Timeout.TimeoutCallback;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.network.ContinuousNetworkParams;
import com.google.android.apps.unveil.network.RsaEncrypter;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.ImageBlurProcessor;
import com.google.android.apps.unveil.nonstop.NetworkStatusMonitor;
import com.google.android.apps.unveil.nonstop.NetworkStatusMonitor.NetworkStatusListener;
import com.google.android.apps.unveil.nonstop.PreviewLooper;
import com.google.android.apps.unveil.nonstop.SceneDetector;
import com.google.android.apps.unveil.nonstop.SceneDetector.OnEventListener;
import com.google.android.apps.unveil.nonstop.SpeechEngine;
import com.google.android.apps.unveil.nonstop.SpeechEngine.Slot;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.nonstop.VisionGyroProcessor;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.ContinuousQueryBuilder;
import com.google.android.apps.unveil.protocol.LocalTracingCookieFetcher;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.RequestPipeline;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.protocol.TracingBarcodeRecognitionObserver;
import com.google.android.apps.unveil.protocol.nonstop.ContinuousPushStrategy;
import com.google.android.apps.unveil.protocol.nonstop.EventListener;
import com.google.android.apps.unveil.protocol.nonstop.PerFrameTracer;
import com.google.android.apps.unveil.protocol.nonstop.SimpleEventListener;
import com.google.android.apps.unveil.results.BasicAnnotation;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.sensors.CameraManager.Listener;
import com.google.android.apps.unveil.sensors.UnveilOrientationSensor;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.tracking.AnnotationTracker;
import com.google.android.apps.unveil.tracking.AnnotationTracker.AnnotationEventListener;
import com.google.android.apps.unveil.tracking.ContinuousAnnotationRenderer;
import com.google.android.apps.unveil.tracking.TrackedAnnotation;
import com.google.android.apps.unveil.ui.CameraWrappingLayout;
import com.google.android.apps.unveil.ui.ContinuousStateTextView;
import com.google.android.apps.unveil.ui.NetworkIndicatorView;
import com.google.android.apps.unveil.ui.result.ContinuousFullResultView;
import com.google.android.apps.unveil.ui.result.ContinuousResultItemView;
import com.google.android.apps.unveil.ui.result.ResultClickListener;
import com.google.android.apps.unveil.ui.result.TimelineView;
import com.google.android.apps.unveil.ui.result.TimelineView.EventListener;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ContinuousActivity extends AuthenticatedPreviewLoopingActivity
  implements LocalBarcodeListener.Callback, CameraManager.Listener
{
  public static final boolean ALLOW_SERVER_POSITION_UPDATES = true;
  private static final int CAMERA_PREVIEW_STOP_DELAY_MILLIS = 50;
  private static final int DIALOG_CAMERA_CONNECTION_FAILED = 2;
  private static final int DIALOG_UPGRADE_REQUIRED = 1;
  private static final int INTERACTION_TIMEOUT_MS = 180000;
  private static final long INTER_PUSH_DELAY_MS = 750L;
  private static final int MESSAGE_STOP_CAMERA_PREVIEW = 1;
  private static final int MINIMUM_PROCESSOR_STATUS_TRACE_PERIOD_MS = 2000;
  private static final int OVERLAY_LAYER = 2;
  public static final boolean REMOVE_MISSING_RESULTS = false;
  private static final int SCENE_TIMEOUT_MS = 60000;
  private static final int SEARCHING_TIMEOUT_MILLIS = 15000;
  private static final float SWIPE_THRESHOLD_FRACTION = 0.5F;
  private static final int TIMEOUT_AFTER_TIMEOUT_MS = 15000;
  public static final boolean TRACK_REPEATED_RESULTS = true;
  private static final boolean USE_SURFACE_VIEW;
  private static final UnveilLogger logger = new UnveilLogger();
  private ContinuousStateTextView activityIndicator;
  private ContinuousAnnotationRenderer annotationRenderer;
  private AnnotationTracker annotationTracker;
  private BarcodeScanner barcodeScanner;
  private Timeout deepSleepTimeout;
  private ContinuousFullResultView fullResultView;
  private GestureDetector gestureDetector;
  private VisionGyroProcessor gyroProcessor;
  private long historyUpdatedAt;
  private Timeout initialSearchTimeout;
  private Timeout interactionTimeout;
  private LocalBarcodeListener localBarcodeListener;
  private Handler mainThreadHandler;
  private NetworkStatusMonitor networkStatusMonitor;
  private ImageView pausePreview;
  EventListener pinger = new SimpleEventListener()
  {
    public void onPushSent(TimestampedFrame.Metadata paramAnonymousMetadata, byte[] paramAnonymousArrayOfByte)
    {
      ContinuousActivity.this.speechEngine.say(SpeechEngine.Slot.SONAR_PING, "ping");
    }
  };
  private TimestampedFrame recentFrame;
  private RequestPipeline requestPipeline;
  private ContinuousResultItemView resultItemView;
  private View resultPanel;
  private SceneDetector sceneDetector;
  private Timeout sceneTimeout;
  private View searchFailureView;
  private SpeechEngine speechEngine;
  private TimelineView timelineView;
  private TraceTracker traceTracker;
  private final ResultClickListener viewFullDetailsListener = new ViewFullResultDetailsListener(null);

  private void addAnnotationToTimeLine(BasicAnnotation paramBasicAnnotation)
  {
    this.fullResultView.addAnnotation(paramBasicAnnotation);
    this.resultItemView.addResult(paramBasicAnnotation.getResult());
    this.timelineView.addResult(paramBasicAnnotation, this.annotationRenderer.getColor(paramBasicAnnotation));
    if (this.searchFailureView.getVisibility() == 0)
      this.searchFailureView.setVisibility(4);
    if (this.resultPanel.getVisibility() != 0)
      this.resultPanel.setVisibility(0);
    if (paramBasicAnnotation.getResult().hasRealTtsDescription())
    {
      this.speechEngine.say(SpeechEngine.Slot.RESULT, paramBasicAnnotation.getResult().getTtsDescription());
      return;
    }
    int i = this.timelineView.getChildCount();
    int j = R.plurals.continuous_n_results_found;
    Resources localResources = getResources();
    if (i == 1)
    {
      this.speechEngine.say(SpeechEngine.Slot.RESULT, localResources.getQuantityString(j, i));
      return;
    }
    SpeechEngine localSpeechEngine = this.speechEngine;
    SpeechEngine.Slot localSlot = SpeechEngine.Slot.RESULT;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    localSpeechEngine.say(localSlot, localResources.getQuantityString(j, i, arrayOfObject));
  }

  private PerFrameTracer createProcessorTracer()
  {
    return new PerFrameTracer()
    {
      long lastStatusReportTimestamp;

      public void onProcessFrame(int paramAnonymousInt, TraceTracker paramAnonymousTraceTracker)
      {
        if (System.currentTimeMillis() - this.lastStatusReportTimestamp < 2000L)
          return;
        Iterator localIterator = ContinuousActivity.this.previewLooper.getAllProcessors().iterator();
        while (localIterator.hasNext())
          paramAnonymousTraceTracker.addProcessorStatus(paramAnonymousInt, ((FrameProcessor)localIterator.next()).getStatus());
        this.lastStatusReportTimestamp = System.currentTimeMillis();
      }
    };
  }

  private void displayAllResults()
  {
    ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CONTINUOUS_VIEW_ALL_RESULTS);
    pauseLooping();
    this.fullResultView.setVisibility(0);
  }

  private void handlePause()
  {
    try
    {
      Matrix localMatrix = new Matrix();
      Object localObject2;
      ImageView localImageView;
      Object localObject3;
      if (this.recentFrame == null)
      {
        localObject2 = null;
        if (localObject2 != null)
        {
          if (CameraManager.isFrontFacingCamera(this))
            localMatrix.preScale(-1.0F, 1.0F);
          localMatrix.preScale(this.cameraManager.getWidth() / ((Picture)localObject2).getSize().height, this.cameraManager.getHeight() / ((Picture)localObject2).getSize().width);
          localMatrix.postRotate(90.0F, this.cameraManager.getWidth() / 2, this.cameraManager.getWidth() / 2);
        }
        this.pausePreview.setImageMatrix(localMatrix);
        localImageView = this.pausePreview;
        localObject3 = null;
        if (localObject2 != null)
          break label237;
      }
      while (true)
      {
        localImageView.setImageBitmap((Bitmap)localObject3);
        this.pausePreview.setTag(localObject2);
        this.pausePreview.setVisibility(0);
        if (!this.mainThreadHandler.hasMessages(1))
          this.mainThreadHandler.sendMessageDelayed(this.mainThreadHandler.obtainMessage(1), 50L);
        pauseLooping();
        this.deepSleepTimeout.reset();
        this.initialSearchTimeout.stop();
        this.activityIndicator.pause();
        return;
        localObject2 = PictureFactory.createBitmap(this.recentFrame.getRawData(), this.recentFrame.getWidth(), this.recentFrame.getHeight(), 0);
        break;
        label237: Bitmap localBitmap = ((Picture)localObject2).peekBitmap();
        localObject3 = localBitmap;
      }
    }
    finally
    {
    }
  }

  private void handleResume()
  {
    try
    {
      this.pausePreview.setImageBitmap(null);
      this.pausePreview.setTag(null);
      this.pausePreview.setVisibility(8);
      if (this.recentFrame != null)
      {
        this.recentFrame.removeReference();
        this.recentFrame = null;
      }
      this.mainThreadHandler.removeMessages(1);
      this.cameraManager.startPreview();
      resumeLooping();
      this.timelineView.resetScroll();
      if (hasConnection())
      {
        logger.d("We have a connection", new Object[0]);
        this.activityIndicator.resume();
      }
      while (true)
      {
        this.initialSearchTimeout.reset();
        this.barcodeScanner.reset();
        return;
        logger.d("No connection", new Object[0]);
        this.activityIndicator.noConnection();
      }
    }
    finally
    {
    }
  }

  private boolean hasConnection()
  {
    NetworkInfo localNetworkInfo = ((ConnectivityManager)getSystemService("connectivity")).getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnectedOrConnecting());
  }

  private void intializeFrameProcessors()
  {
    View local7 = new View(getApplicationContext())
    {
      protected void onDraw(Canvas paramAnonymousCanvas)
      {
        if (ContinuousActivity.this.annotationRenderer != null)
          ContinuousActivity.this.annotationRenderer.render(paramAnonymousCanvas);
      }
    };
    addViewAsCameraOverlay(local7, 2);
    this.annotationRenderer = new ContinuousAnnotationRenderer(local7, this.timelineView, 5);
    this.gyroProcessor = new VisionGyroProcessor(null);
    this.annotationTracker = new AnnotationTracker(this.annotationRenderer, false, true, true, this.gyroProcessor);
    addGlRenderCallback(this.annotationTracker);
    this.annotationTracker.setListener(new AnnotationTracker.AnnotationEventListener()
    {
      public void onNewResultAppeared(BasicAnnotation paramAnonymousBasicAnnotation, boolean paramAnonymousBoolean)
      {
        ContinuousActivity.this.initialSearchTimeout.stop();
        if (ContinuousActivity.this.searchFailureView.getVisibility() == 0)
          ContinuousActivity.this.searchFailureView.setVisibility(8);
        ContinuousActivity.this.addAnnotationToTimeLine(paramAnonymousBasicAnnotation);
      }

      public void onResultUpdated(BasicAnnotation paramAnonymousBasicAnnotation, boolean paramAnonymousBoolean)
      {
        ContinuousActivity.this.resultItemView.updateResult(paramAnonymousBasicAnnotation.getResult());
        ContinuousActivity.this.timelineView.updateResult(paramAnonymousBasicAnnotation, ContinuousActivity.this.annotationRenderer.getColor(paramAnonymousBasicAnnotation));
      }

      public void onTrackingStarted(TrackedAnnotation paramAnonymousTrackedAnnotation)
      {
      }

      public void onTrackingStopped(TrackedAnnotation paramAnonymousTrackedAnnotation)
      {
        ContinuousActivity.this.timelineView.updateResult(paramAnonymousTrackedAnnotation, ContinuousActivity.this.annotationRenderer.getColor(paramAnonymousTrackedAnnotation));
      }
    });
    ContinuousNetworkParams localContinuousNetworkParams = new ContinuousNetworkParams();
    localContinuousNetworkParams.interPushDelayMs = 750L;
    localContinuousNetworkParams.source = GogglesProtos.GogglesRequest.Source.LIVE;
    this.requestPipeline = new RequestPipeline(this.application, localContinuousNetworkParams, new ContinuousPushStrategy(Clock.SYSTEM_CLOCK, localContinuousNetworkParams), this.annotationTracker, this.traceTracker, Collections.emptyList());
    this.historyUpdatedAt = this.application.getAuthState().getLastUpdateTimestamp();
    this.sceneDetector = new SceneDetector(new SceneDetector.OnEventListener()
    {
      public void onResult(ResultItem paramAnonymousResultItem)
      {
      }

      public void onSceneChanged()
      {
        ContinuousActivity.this.sceneTimeout.reset();
      }

      public void onScenePushed()
      {
      }
    });
    this.requestPipeline.addEventListener(this.sceneDetector);
    this.networkStatusMonitor = new NetworkStatusMonitor(new NetworkStatusMonitor.NetworkStatusListener()
    {
      public void onNetworkError(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 420)
        {
          ContinuousActivity.this.showDialog(1);
          ContinuousActivity.this.handlePause();
        }
        do
        {
          return;
          ContinuousActivity.this.initialSearchTimeout.stop();
          ContinuousActivity.this.speechEngine.say(SpeechEngine.Slot.STATUS, ContinuousActivity.this.getString(R.string.continuous_network_error));
        }
        while (ContinuousActivity.this.hasConnection());
        ContinuousActivity.this.activityIndicator.noConnection();
      }

      public void onNetworkRecovered()
      {
        ContinuousActivity.this.initialSearchTimeout.reset();
        ContinuousActivity.this.speechEngine.say(SpeechEngine.Slot.STATUS, ContinuousActivity.this.getString(R.string.indicate_goggles_is_running));
        ContinuousActivity.this.activityIndicator.resume();
      }
    });
    this.requestPipeline.addEventListener(this.networkStatusMonitor);
    NetworkIndicatorView localNetworkIndicatorView = (NetworkIndicatorView)findViewById(R.id.network_indicator_view);
    this.requestPipeline.addEventListener(localNetworkIndicatorView);
    this.requestPipeline.addEventListener(this.pinger);
    this.requestPipeline.addPerFrameTracer(createProcessorTracer());
  }

  private void onUiInteraction()
  {
    if (isLoopingPaused())
    {
      wakeup();
      return;
    }
    this.interactionTimeout.reset();
    this.sceneTimeout.reset();
  }

  private void sendToFrontend(TimestampedFrame paramTimestampedFrame, Barcode paramBarcode)
  {
    ContinuousQueryBuilder localContinuousQueryBuilder = new ContinuousQueryBuilder();
    localContinuousQueryBuilder.addLocalBarcode(paramBarcode).addMsSinceEpoch(Long.valueOf(System.currentTimeMillis())).setSource(GogglesProtos.GogglesRequest.Source.LIVE);
    this.requestPipeline.outOfBandPush(paramTimestampedFrame, localContinuousQueryBuilder);
  }

  protected void addFrameProcessors(PreviewLooper paramPreviewLooper)
  {
    paramPreviewLooper.addPreviewProcessor(this.gyroProcessor, 0);
    paramPreviewLooper.addPreviewProcessor(this.annotationTracker, 0);
    paramPreviewLooper.addPreviewProcessor(new FrameProcessor()
    {
      protected void onProcessFrame(TimestampedFrame paramAnonymousTimestampedFrame)
      {
        if (ContinuousActivity.this.recentFrame != null)
          ContinuousActivity.this.recentFrame.removeReference();
        ContinuousActivity.access$1402(ContinuousActivity.this, paramAnonymousTimestampedFrame);
        paramAnonymousTimestampedFrame.addReference();
      }
    }
    , 0);
    paramPreviewLooper.addPreviewProcessor(new ImageBlurProcessor(this.cameraManager), 1);
    if (this.application.getSettings().useLocalBarcode)
      paramPreviewLooper.addPreviewProcessor(this.barcodeScanner, 1);
    paramPreviewLooper.addPreviewProcessor(this.requestPipeline, 1);
  }

  public void addResultCallback(EventListener paramEventListener)
  {
    this.requestPipeline.addEventListener(paramEventListener);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
    {
      if (this.fullResultView.getVisibility() == 0)
      {
        this.fullResultView.setVisibility(4);
        return true;
      }
      ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RETURN_TO_HOME_SCREEN_BACK_BUTTON);
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    onUiInteraction();
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  public boolean dispatchTrackballEvent(MotionEvent paramMotionEvent)
  {
    onUiInteraction();
    return super.dispatchTrackballEvent(paramMotionEvent);
  }

  public void onBarcodeRecognized(TimestampedFrame paramTimestampedFrame, Barcode paramBarcode)
  {
    this.application.getTraceTracker().addProcessorStatus(paramTimestampedFrame.getFrameNum(), this.barcodeScanner.getStatus());
    sendToFrontend(paramTimestampedFrame, paramBarcode);
  }

  public void onCameraAcquisitionError()
  {
    showDialog(2);
  }

  public void onCameraFlashControlError()
  {
  }

  public void onCameraLayoutFinished(Size paramSize, Matrix paramMatrix)
  {
    this.annotationRenderer.setFrameToCanvas(paramMatrix);
    super.onCameraLayoutFinished(paramSize, paramMatrix);
  }

  public void onCameraPreviewSizeChanged()
  {
  }

  public void onCameraQualityError()
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.application.getViewport().updateNaturalOrientation(Viewport.computeNaturalOrientation(this));
    ((CameraWrappingLayout)findViewById(R.id.camera_wrapper_layout)).onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    logger.v("onCreate", new Object[0]);
    super.onCreate(paramBundle);
    if (Settings.getBoolean(this, R.string.show_continuous_first_run_key, true))
    {
      startActivity(new Intent(this, ContinuousFirstRunActivity.class));
      finish();
    }
    initializeActivity(R.layout.continuous_capture);
    this.cameraManager.registerListener(this);
    ((UnveilOrientationSensor)this.sensorProvider.getOrientationSensor()).setForceLandscape(true);
    RsaEncrypter.prefetchEncryptor(0);
    this.speechEngine = new SpeechEngine(this, R.string.indicate_continuous_tts_is_active, R.string.continuous_tts_settings_key);
    StateRestorationActivity.restoreState(paramBundle, this.application, this);
    UnveilApplication.configureWindowFormat(getWindow());
    this.localBarcodeListener = new LocalBarcodeListener(this);
    this.traceTracker = new TraceTracker(this.application.getNetworkInfoProvider(), LocalTracingCookieFetcher.NO_OP);
    this.barcodeScanner = new BarcodeScanner(this.localBarcodeListener, null, null, BarcodeRecognizer.Mode.MUTLISTAGE, new TracingBarcodeRecognitionObserver(this.traceTracker), true);
    this.fullResultView = ((ContinuousFullResultView)findViewById(R.id.full_result_view));
    this.fullResultView.setListener(this.viewFullDetailsListener);
    this.resultPanel = findViewById(R.id.result_panel);
    this.gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener()
    {
      public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        float f = (paramAnonymousMotionEvent2.getRawX() - paramAnonymousMotionEvent1.getRawX()) / ContinuousActivity.this.cameraManager.getWidth();
        if (f < -0.5F)
        {
          ContinuousActivity.this.timelineView.slideLensRight();
          return true;
        }
        if (f > 0.5F)
        {
          ContinuousActivity.this.timelineView.slideLensLeft();
          return true;
        }
        return false;
      }
    });
    this.timelineView = ((TimelineView)findViewById(R.id.timeline_view));
    this.timelineView.setListener(new TimelineView.EventListener()
    {
      public void onResultClicked(ResultItem paramAnonymousResultItem)
      {
        ContinuousActivity.this.viewFullDetailsListener.onResultClicked(paramAnonymousResultItem);
      }

      public void onScrollChanged()
      {
      }

      public void onScrolling(float paramAnonymousFloat)
      {
        ContinuousActivity.this.resultItemView.scroll((int)(paramAnonymousFloat * ContinuousActivity.this.resultItemView.getWidth()));
        ContinuousActivity.this.annotationRenderer.requestRender();
      }
    });
    this.timelineView.setSpeechEngine(this.speechEngine);
    this.resultItemView = ((ContinuousResultItemView)findViewById(R.id.result_item_view));
    this.resultItemView.setListener(this.viewFullDetailsListener);
    this.activityIndicator = ((ContinuousStateTextView)findViewById(R.id.activity_indicator));
    if (!hasConnection())
      this.activityIndicator.noConnection();
    this.searchFailureView = findViewById(R.id.search_failure_indicator);
    this.pausePreview = ((ImageView)findViewById(R.id.pause_preview));
    this.mainThreadHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        ContinuousActivity.this.cameraManager.stopPreview();
      }
    };
    intializeFrameProcessors();
  }

  protected Dialog onCreateDialog(int paramInt, Bundle paramBundle)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setIcon(17301543);
    switch (paramInt)
    {
    default:
    case 1:
    case 2:
    }
    while (true)
    {
      return localBuilder.create();
      localBuilder.setMessage(R.string.client_too_old);
      localBuilder.setCancelable(false);
      localBuilder.setPositiveButton(R.string.go_to_market, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse(ContinuousActivity.this.getString(R.string.market_link)));
          ContinuousActivity.this.startActivity(localIntent);
          ContinuousActivity.this.dismissDialog(1);
          ContinuousActivity.this.finish();
        }
      });
      localBuilder.setNegativeButton(R.string.close, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ContinuousActivity.this.dismissDialog(1);
          ContinuousActivity.this.finish();
        }
      });
      continue;
      localBuilder.setTitle(R.string.camera_error_title);
      localBuilder.setMessage(R.string.camera_connection_error_message);
      localBuilder.setCancelable(false);
      localBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ContinuousActivity.this.dismissDialog(2);
          ContinuousActivity.this.finish();
        }
      });
    }
  }

  protected void onLooperRestart()
  {
    wakeup();
    this.interactionTimeout.reset();
    this.sceneTimeout.reset();
    this.annotationTracker.clearTrackedAnnotations();
    long l = this.application.getAuthState().getLastUpdateTimestamp();
    if (l > this.historyUpdatedAt)
    {
      this.historyUpdatedAt = l;
      this.requestPipeline.reset();
    }
  }

  protected void onPause()
  {
    super.onPause();
    this.interactionTimeout.stop();
    this.speechEngine.shutdown();
  }

  public void onResume()
  {
    super.onResume();
    this.speechEngine.startup(this);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
  }

  protected void onStart()
  {
    super.onStart();
    this.application.getViewport().updateNaturalOrientation(Viewport.computeNaturalOrientation(this));
    this.application.setContinuous(true);
    if (this.application.userWantsHistory())
    {
      logger.i("User history is enabled.", new Object[0]);
      if (!this.application.isSearchHistoryEnabled())
        fetchAuthToken();
    }
    while (true)
    {
      Timeout.TimeoutCallback local13 = new Timeout.TimeoutCallback()
      {
        public void onTimeout()
        {
          ContinuousActivity.this.handlePause();
          ClickTracker.logClick(ContinuousActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CONTINUOUS_AUTOMATED_PAUSE);
        }
      };
      this.interactionTimeout = new Timeout(local13, 180000);
      this.sceneTimeout = new Timeout(local13, 60000);
      this.deepSleepTimeout = new Timeout(new Timeout.TimeoutCallback()
      {
        public void onTimeout()
        {
          ContinuousActivity.this.sleep();
        }
      }
      , 15000);
      this.deepSleepTimeout.stop();
      this.initialSearchTimeout = new Timeout(new Timeout.TimeoutCallback()
      {
        public void onTimeout()
        {
          if (((ContinuousActivity.this.resultPanel.getHeight() == 0) || (ContinuousActivity.this.resultPanel.getVisibility() != 0)) && (ContinuousActivity.this.hasConnection()))
          {
            ContinuousActivity.this.searchFailureView.setVisibility(0);
            ContinuousActivity.this.speechEngine.say(SpeechEngine.Slot.STATUS, ContinuousActivity.this.getString(R.string.continuous_no_result_found));
          }
        }
      }
      , 15000);
      return;
      logger.i("User history is disabled.", new Object[0]);
    }
  }

  protected void onStop()
  {
    super.onStop();
    this.interactionTimeout.stop();
    this.sceneTimeout.stop();
    this.deepSleepTimeout.stop();
    if (this.recentFrame != null)
    {
      this.recentFrame.removeReference();
      this.recentFrame = null;
    }
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.gestureDetector.onTouchEvent(paramMotionEvent))
      return true;
    if (paramMotionEvent.getAction() == 1)
    {
      if (!isLoopingPaused())
      {
        handlePause();
        this.deepSleepTimeout.stop();
        return true;
      }
      handleResume();
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  protected void pauseLooping()
  {
    this.speechEngine.say(SpeechEngine.Slot.SONAR_PING, null);
    this.speechEngine.say(SpeechEngine.Slot.STATUS, getString(R.string.indicate_goggles_is_paused));
    super.pauseLooping();
  }

  protected void resumeLooping()
  {
    this.speechEngine.say(SpeechEngine.Slot.STATUS, getString(R.string.indicate_goggles_is_running));
    super.resumeLooping();
  }

  public void sleep()
  {
    findViewById(R.id.camera_wrapper_layout).setVisibility(4);
    findViewById(R.id.network_indicator_view).setVisibility(4);
    this.resultPanel.setVisibility(4);
    this.requestPipeline.reset();
    this.annotationTracker.clearSessionAndTracked();
  }

  public void wakeup()
  {
    this.deepSleepTimeout.stop();
    findViewById(R.id.camera_wrapper_layout).setVisibility(0);
    if (Settings.getBoolean(this, R.string.network_indicator_in_continuous_key))
      findViewById(R.id.network_indicator_view).setVisibility(0);
    this.resultPanel.setVisibility(0);
  }

  private final class ViewFullResultDetailsListener
    implements ResultClickListener
  {
    private ViewFullResultDetailsListener()
    {
    }

    public void onResultClicked(ResultItem paramResultItem)
    {
      String str = paramResultItem.getDirectUrl();
      if (TextUtils.isEmpty(str))
      {
        ContinuousActivity.this.pauseLooping();
        Intent localIntent2 = new Intent(ContinuousActivity.this, ExpandedResultsActivity.class);
        localIntent2.putExtra("open_to", paramResultItem);
        localIntent2.putExtra("results", new ArrayList(ResultModel.excludeDirectUrlResults(ContinuousActivity.this.timelineView.getResults())));
        ContinuousActivity.this.startActivity(localIntent2);
        return;
      }
      Uri localUri = Uri.parse(str);
      Intent localIntent1 = new Intent().setAction("android.intent.action.VIEW");
      ContinuousActivity.this.startActivity(localIntent1.setData(localUri));
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ContinuousActivity
 * JD-Core Version:    0.6.2
 */