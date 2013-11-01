package com.google.android.apps.unveil;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.barcode.Barcode;
import com.google.android.apps.unveil.barcode.BarcodeRecognizer.Mode;
import com.google.android.apps.unveil.barcode.BarcodeScanner;
import com.google.android.apps.unveil.barcode.LocalBarcodeListener;
import com.google.android.apps.unveil.barcode.LocalBarcodeListener.Callback;
import com.google.android.apps.unveil.env.AccessibilityUtils;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.TonePlayer;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.intents.Intents;
import com.google.android.apps.unveil.intents.Intents.IntentRecipe;
import com.google.android.apps.unveil.intents.ZxingScan;
import com.google.android.apps.unveil.network.RsaEncrypter;
import com.google.android.apps.unveil.nonstop.DebugView;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.ImageBlurProcessor;
import com.google.android.apps.unveil.nonstop.PreviewLooper;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryPipeline.EventListener;
import com.google.android.apps.unveil.protocol.QueryPipeline.NoPendingQueryException;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.protocol.TracingBarcodeRecognitionObserver;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.sensors.CameraManager.FocusCallback;
import com.google.android.apps.unveil.sensors.CameraManager.Listener;
import com.google.android.apps.unveil.sensors.CameraManager.PictureCallback;
import com.google.android.apps.unveil.sensors.CameraManager.PictureQuality;
import com.google.android.apps.unveil.sensors.UnveilLocationProvider;
import com.google.android.apps.unveil.sensors.UnveilOrientationSensor;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.ui.CameraWrappingLayout;
import com.google.android.apps.unveil.ui.CameraWrappingLayout.Alignment;
import com.google.android.apps.unveil.ui.CameraWrappingLayout.CameraLayoutHandler;
import com.google.android.apps.unveil.ui.ProgressView;
import com.google.android.apps.unveil.ui.RegionSelectorView;
import com.google.android.apps.unveil.ui.RegionSelectorView.DragEventsCallback;
import com.google.android.apps.unveil.ui.RegionSelectorView.RequestAutoFocusCallback;
import com.google.android.apps.unveil.ui.rotating.RotatingAlertDialog.Builder;
import com.google.android.apps.unveil.ui.rotating.RotatingDialog;
import com.google.android.apps.unveil.ui.rotating.RotatingDialog.OnCancelListener;
import com.google.android.apps.unveil.ui.rotating.RotatingDialog.OnClickListener;
import com.google.android.apps.unveil.ui.rotating.RotatingDialogController;
import com.google.android.apps.unveil.ui.rotating.RotatingImageView;
import com.google.android.apps.unveil.ui.rotating.RotatingToast;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Selector;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class CaptureActivity extends AuthenticatedActivity
  implements CameraManager.Listener, RegionSelectorView.DragEventsCallback, CameraManager.PictureCallback, CameraManager.FocusCallback, CameraWrappingLayout.CameraLayoutHandler, LocalBarcodeListener.Callback
{
  private static final int BARCODE_VIBRATION_DURATION_MS = 40;
  private static final int LAUNCH_STATE_NO_ACTION = 3;
  private static final int LAUNCH_STATE_RESULTS = 1;
  private static final int MAX_AUTH_RETRIES = 5;
  private static final float PREVIEW_LOOPER_FPS = 4.0F;
  private static final int REQUEST_CODE_LOAD_IMAGE = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  private AccessibilityUtils accessibilityUtils;
  private AudioManager audioManager;
  private boolean authenticated;
  private BarcodeScanner barcodeScanner;
  private OrientationListener buttonOrientationListener;
  private CameraManager cameraManager;
  private View cameraOverlay;
  private LinearLayout captureButtons;
  private RotatingImageView cropButton;
  private final DialogBoxManager dialogBoxManager = new DialogBoxManager();
  private boolean enableFlashOnResume;
  private RotatingImageView flashButton;
  private boolean havePhoto;
  private ImageBlurProcessor imageBlurProcessor;
  private final IntentHelper intentHelper = new IntentHelper(null);
  private Boolean ipv6SupportAvailable;
  private boolean isLastResponseHandled;
  private Picture lastPreviewFrameWhenTakingPicture;
  private Picture lastPreviewFrameWhenTakingPreview;
  private int lastStatusCode = -1;
  private int launchState;
  private LocalBarcodeListener localBarcodeListener;
  private DebugView looperDebugView;
  private int numberOfAuthRetries;
  private boolean onResumePending = false;
  private Size previewDisplayedSize;
  private PreviewLooper previewLooper;
  private FrameLayout progressFrame;
  private ProgressView progressView;
  private RotatingImageView puggleModeButton;
  private QueryPipeline queryPipeline;
  private QueryEventListener queryPipelineListener = new QueryEventListener(null);
  private RegionOfInterestHelper roiHelper;
  private SensorManager sensorManager;
  private FrameLayout shutterButton;
  private final StateMachine stateMachine = new StateMachine(null);
  private TraceTracker traceTracker;
  private CameraWrappingLayout wrappingLayout;

  private boolean canUsePreviewFrame()
  {
    if (this.roiHelper.isRoiVisible());
    while (!this.application.getSettings().usePreviewFrame)
      return false;
    Size localSize = this.cameraManager.getPreviewSize();
    if ((localSize.width < CameraManager.PictureQuality.NORMAL_QUALITY.desiredWidth) || (localSize.height < CameraManager.PictureQuality.NORMAL_QUALITY.desiredHeight))
    {
      logger.i("Preview frame of %s is too small for recognition", new Object[] { localSize });
      return false;
    }
    return true;
  }

  private boolean captureButtonsVisible()
  {
    return this.captureButtons.getVisibility() == 0;
  }

  private RelativeLayout getCaptureLayout()
  {
    return (RelativeLayout)findViewById(R.id.capture_layout);
  }

  private void handleQueryResponse(QueryResponse paramQueryResponse)
  {
    if (this.isLastResponseHandled)
    {
      logger.d("Results activity is being started, ignore duplicated start request.", new Object[0]);
      return;
    }
    this.isLastResponseHandled = true;
    startResultsActivity(paramQueryResponse);
  }

  private void hideInterstitial()
  {
    this.progressView.stopAnimation();
    this.progressView.setBackgroundPicture(null);
    this.progressFrame.setVisibility(8);
    slideInCaptureButtons();
    setCropButtonHighlight(this.roiHelper.isRoiVisible());
    this.cameraOverlay.setVisibility(4);
  }

  private void initializePreviewLooper()
  {
    logger.i("initializePreviewLooper", new Object[0]);
    if (this.previewLooper == null)
    {
      this.previewLooper = new PreviewLooper(this.cameraManager, 0, 4.0F, 2);
      this.looperDebugView.setCallback(this.previewLooper);
      this.previewLooper.addPreviewProcessor(new FrameProcessor()
      {
        private final Vector<String> debugText = new Vector();

        public Vector<String> getDebugText()
        {
          this.debugText.clear();
          this.debugText.add("FE: " + CaptureActivity.this.application.getFrontendUrl().toString());
          this.debugText.add("Capture state: " + CaptureActivity.StateMachine.access$5300(CaptureActivity.this.stateMachine, CaptureActivity.StateMachine.access$5200(CaptureActivity.this.stateMachine)));
          this.debugText.add("Camera state: " + CaptureActivity.this.cameraManager.getStateName());
          return this.debugText;
        }

        public void onProcessFrame(TimestampedFrame paramAnonymousTimestampedFrame)
        {
          CaptureActivity.this.onProcessFrame(paramAnonymousTimestampedFrame);
          if (CaptureActivity.this.looperDebugView.getVisibility() == 0)
            CaptureActivity.this.looperDebugView.invalidate();
        }
      }
      , 0);
      if (!PreviewLooper.supportNonstopFrameProcessing(this))
        break label137;
      this.imageBlurProcessor = new ImageBlurProcessor(this.cameraManager);
      this.previewLooper.addPreviewProcessor(this.imageBlurProcessor, 1);
      this.previewLooper.addPreviewProcessor(this.barcodeScanner, 1);
    }
    while (true)
    {
      logger.i("Starting preview frame processing.", new Object[0]);
      this.previewLooper.startLoop(this.previewDisplayedSize);
      return;
      label137: logger.i("Nonstop frame processing is not enabled.", new Object[0]);
    }
  }

  private boolean isIpv6SupportAvailable()
  {
    if (this.ipv6SupportAvailable != null)
      return this.ipv6SupportAvailable.booleanValue();
    try
    {
      Selector.open().wakeup();
      this.ipv6SupportAvailable = Boolean.valueOf(true);
      return true;
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localIOException.getMessage();
        localUnveilLogger.e("Unexpected IO Exception in IPv6 Check; %s", arrayOfObject);
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      this.ipv6SupportAvailable = Boolean.valueOf(false);
    }
    return false;
  }

  private void maybeTriggerFocus(CameraManager.FocusCallback paramFocusCallback, boolean paramBoolean)
  {
    if (((paramBoolean) || (this.barcodeScanner.getBarcode() == null)) && ((this.imageBlurProcessor == null) || (this.imageBlurProcessor.isLastFrameBlurred())))
      this.cameraManager.focus(paramFocusCallback);
    while (paramFocusCallback == null)
      return;
    paramFocusCallback.onFocus(true);
  }

  private void pausePreviewFrameProcessing()
  {
    if (this.previewLooper != null)
    {
      this.previewLooper.pauseLoop();
      return;
    }
    logger.w("Tried to stop null PreviewLooper", new Object[0]);
  }

  private void safeResendQuery()
  {
    try
    {
      this.queryPipeline.resend(this.queryPipelineListener);
      return;
    }
    catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
    {
      logger.e("No pending query when trying to resend a query.", new Object[0]);
      showToast(R.string.load_query_problem);
    }
  }

  private void setCropButtonHighlight(boolean paramBoolean)
  {
    findViewById(R.id.crop_button_wrapper).setSelected(paramBoolean);
  }

  private void setShoppingButtonHighlight(boolean paramBoolean)
  {
    if (this.puggleModeButton != null)
      if (!paramBoolean)
        break label39;
    label39: for (int i = R.drawable.ic_shopping_pressed; ; i = R.drawable.ic_shopping_active)
    {
      this.puggleModeButton.setImageDrawable(getResources().getDrawable(i));
      this.puggleModeButton.setSelected(paramBoolean);
      return;
    }
  }

  private void setShutterButtonHighlight(boolean paramBoolean)
  {
    this.shutterButton.setSelected(paramBoolean);
  }

  private void showFailedToSaveForLaterToast()
  {
    Toast.makeText(this, R.string.save_for_later_failure, 1).show();
  }

  private void showInterstitial(Picture paramPicture)
  {
    slideOutCaptureButtons();
    setShutterButtonHighlight(false);
    if (this.roiHelper.isRoiVisible())
    {
      this.roiHelper.setPreviousQueryUsedRoi(true);
      this.roiHelper.hideRoi(true);
    }
    while (true)
    {
      this.cameraOverlay.setVisibility(0);
      this.progressView.setRotatingWidgetsLocked(false);
      this.progressView.showMessageArea(true);
      this.progressView.hideSaveForLaterButton();
      this.progressFrame.setVisibility(0);
      this.progressView.setBackgroundPicture(paramPicture);
      return;
      this.roiHelper.setPreviousQueryUsedRoi(false);
    }
  }

  private void slideInCaptureButtons()
  {
    Animation localAnimation = AnimationUtils.makeInAnimation(this, false);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        if (!CaptureActivity.this.buttonOrientationListener.getFirstButtonAnimationStarted())
        {
          CaptureActivity.this.buttonOrientationListener.setFirstButtonAnimationStarted(true);
          CaptureActivity.this.buttonOrientationListener.updateToLastOrientation();
        }
        CaptureActivity.this.captureButtons.setVisibility(0);
      }
    });
    this.captureButtons.startAnimation(localAnimation);
  }

  private void slideOutCaptureButtons()
  {
    Animation localAnimation = AnimationUtils.makeOutAnimation(this, true);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        CaptureActivity.this.captureButtons.setVisibility(4);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
      }
    });
    this.captureButtons.startAnimation(localAnimation);
  }

  private void startPreviewFrameProcessing()
  {
    this.previewLooper.startLoop(this.previewDisplayedSize);
  }

  private void startResultsActivity(QueryResponse paramQueryResponse)
  {
    logger.d("Starting results activity...", new Object[0]);
    if (paramQueryResponse.hasEyeCandyResults());
    for (Object localObject = EpicFailActivity.class; ; localObject = ResultsActivity.class)
    {
      Intent localIntent = new Intent(this, (Class)localObject);
      localIntent.setAction("com.google.android.apps.unveil.show_results");
      localIntent.setFlags(65536);
      localIntent.putExtra("response", paramQueryResponse);
      startActivity(localIntent);
      return;
    }
  }

  private void updateFlashButtonHighlight()
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.cameraManager.getExpectedFlashMode();
    localUnveilLogger.v("Flash mode is now %s", arrayOfObject);
    boolean bool = this.cameraManager.getExpectedFlashMode().equals("torch");
    findViewById(R.id.flash_button_wrapper).setSelected(bool);
    RotatingImageView localRotatingImageView = this.flashButton;
    Resources localResources = getResources();
    if (bool);
    for (int i = R.string.disable_torch; ; i = R.string.enable_torch)
    {
      localRotatingImageView.setContentDescription(localResources.getString(i));
      return;
    }
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    logger.v("onActivityResult", new Object[0]);
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    if ((paramInt1 == 1) && (paramInt2 == -1))
    {
      logger.v("Got a saved image URI", new Object[0]);
      Uri localUri = paramIntent.getData();
      logger.v("URI: %s", new Object[] { localUri });
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.SEND");
      localIntent.setClass(this, ShareActivity.class);
      localIntent.putExtra("android.intent.extra.STREAM", localUri);
      startActivity(localIntent);
    }
  }

  public void onAnimationEvent(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
      setCropButtonHighlight(paramBoolean2);
  }

  public void onAuthCanceled()
  {
    logger.i("CaptureActivity.onAuthCanceled()", new Object[0]);
    this.authenticated = false;
    this.application.setUserWantsHistory(false);
  }

  public void onAuthFailure()
  {
    logger.i("CaptureActivity.onAuthFailure()", new Object[0]);
    this.authenticated = false;
  }

  public void onAuthSuccess()
  {
    logger.i("CaptureActivity.onAuthSuccess()", new Object[0]);
    if (this.numberOfAuthRetries <= 5)
    {
      this.authenticated = true;
      this.numberOfAuthRetries = 0;
      logger.v("Authorization successful.", new Object[0]);
      if ((this.launchState == 1) && (this.havePhoto))
        safeResendQuery();
      return;
    }
    this.dialogBoxManager.showRotatingDialog(2);
  }

  public void onBarcodeRecognized(TimestampedFrame paramTimestampedFrame, final Barcode paramBarcode)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        CaptureActivity.StateMachine.access$7000(CaptureActivity.this.stateMachine, this.val$picture, paramBarcode);
      }
    });
  }

  public void onCameraAcquisitionError()
  {
    logger.e("Failed to acquire camera.", new Object[0]);
    this.dialogBoxManager.showRotatingDialog(4);
  }

  public void onCameraConnected()
  {
    this.wrappingLayout.requestLayout();
  }

  public void onCameraFlashControlError()
  {
    logger.e("Failed to apply camera flash setting.", new Object[0]);
  }

  public void onCameraLayoutComplete()
  {
  }

  public void onCameraLayoutFinished(Size paramSize, Matrix paramMatrix)
  {
    this.previewDisplayedSize = paramSize;
    this.stateMachine.handleCameraLayoutFinished();
  }

  public void onCameraPreviewSizeChanged()
  {
    this.stateMachine.handleCameraPreviewSizeChangedEvent();
  }

  public void onCameraQualityError()
  {
    logger.e("Failed to apply camera quality settings.", new Object[0]);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.application.getViewport().updateNaturalOrientation(Viewport.computeNaturalOrientation(this));
    ((CameraWrappingLayout)findViewById(R.id.camera_wrapper_layout)).onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    logger.v("onCreate", new Object[0]);
    this.application = ((UnveilApplication)getApplication());
    this.traceTracker = this.application.getTraceTracker();
    this.queryPipeline = this.application.getQueryPipeline();
    this.audioManager = ((AudioManager)getSystemService("audio"));
    this.accessibilityUtils = new AccessibilityUtils(this, R.string.continuous_tts_settings_key);
    UnveilLocationProvider localUnveilLocationProvider = this.application.getLocationProvider();
    UnveilSensorProvider localUnveilSensorProvider = this.application.getSensorProvider();
    ((UnveilOrientationSensor)localUnveilSensorProvider.getOrientationSensor()).setForceLandscape(false);
    setContentView(R.layout.capture);
    this.cameraManager = ((CameraManager)findViewById(R.id.camera_preview));
    this.cameraManager.registerListener(this);
    this.wrappingLayout = ((CameraWrappingLayout)findViewById(R.id.camera_wrapper_layout));
    this.wrappingLayout.setCameraManager(this.cameraManager);
    this.wrappingLayout.setCameraLayoutHandler(this);
    this.wrappingLayout.setAlignment(CameraWrappingLayout.Alignment.CENTER);
    this.wrappingLayout.setRotation(0);
    this.cropButton = ((RotatingImageView)findViewById(R.id.crop_button));
    this.flashButton = ((RotatingImageView)findViewById(R.id.flash_button));
    this.shutterButton = ((FrameLayout)findViewById(R.id.shutter_button_layout));
    RegionSelectorView localRegionSelectorView = (RegionSelectorView)findViewById(R.id.region_selector);
    this.cameraOverlay = findViewById(R.id.camera_overlay);
    this.localBarcodeListener = new LocalBarcodeListener(this);
    this.roiHelper = new RegionOfInterestHelper(localRegionSelectorView, this.cameraManager);
    localRegionSelectorView.setDragEventsCallback(this, this.roiHelper.getRoiMarginPx(this, Build.MODEL));
    this.captureButtons = ((LinearLayout)findViewById(R.id.capture_buttons));
    this.looperDebugView = ((DebugView)findViewById(R.id.nonstop_debug_view));
    this.looperDebugView.setVisibility(8);
    RotatingImageView[] arrayOfRotatingImageView = new RotatingImageView[3];
    arrayOfRotatingImageView[0] = ((RotatingImageView)findViewById(R.id.shutter_button_dark));
    arrayOfRotatingImageView[1] = this.cropButton;
    arrayOfRotatingImageView[2] = this.flashButton;
    LinkedList localLinkedList = new LinkedList(Arrays.asList(arrayOfRotatingImageView));
    this.buttonOrientationListener = new OrientationListener(this, this.application.getViewport(), localLinkedList);
    this.sensorManager = new SensorManager(localUnveilLocationProvider, localUnveilSensorProvider, this.buttonOrientationListener, this.application.getViewport());
    this.barcodeScanner = new BarcodeScanner(this.localBarcodeListener, this.application.getViewport(), localRegionSelectorView, BarcodeRecognizer.Mode.SINGLE_STAGE, new TracingBarcodeRecognitionObserver(this.traceTracker), false);
    this.progressFrame = ((FrameLayout)findViewById(R.id.progress_frame));
    this.progressView = new ProgressView(this);
    this.progressView.setCancelClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
      }
    });
    this.progressFrame.addView(this.progressView);
    this.progressFrame.setVisibility(8);
    this.cameraManager.setVisibility(0);
    this.stateMachine.configureButton(this.cropButton, 16);
    this.cropButton.setTag(localRegionSelectorView);
    this.stateMachine.configureButton(this.shutterButton, 1);
    this.stateMachine.configureButton(this.flashButton, 32);
    localRegionSelectorView.setRequestAutoFocusCallback(new RegionSelectorView.RequestAutoFocusCallback()
    {
      public void onRequestAutoFocus()
      {
        CaptureActivity.logger.i("region selector requesting focus", new Object[0]);
        CaptureActivity.this.maybeTriggerFocus(null, true);
      }
    });
    RsaEncrypter.prefetchEncryptor(5);
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
      localActionBar.hide();
  }

  protected RotatingDialog onCreateRotatingDialog(int paramInt)
  {
    return this.stateMachine.handleOnCreateRotatingDialogEvent(paramInt);
  }

  protected void onDestroy()
  {
    logger.i("Shutting down preview looper.", new Object[0]);
    if (this.previewLooper != null)
      this.previewLooper.shutdown();
    super.onDestroy();
  }

  public void onDragEnd()
  {
    slideInCaptureButtons();
  }

  public void onFlashModeChanged()
  {
    updateFlashButtonHighlight();
  }

  public void onFocus(boolean paramBoolean)
  {
    this.stateMachine.handleFocusEvent(paramBoolean);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    boolean bool = true;
    if (paramKeyEvent.getRepeatCount() != 0)
    {
      bool = super.onKeyDown(paramInt, paramKeyEvent);
      return bool;
    }
    switch (paramInt)
    {
    default:
    case 80:
    case 23:
    case 27:
    case 4:
      while (true)
      {
        return super.onKeyDown(paramInt, paramKeyEvent);
        this.stateMachine.handleKeyDownEvent(2);
        return bool;
        this.stateMachine.handleKeyDownEvent(8);
        return bool;
        this.stateMachine.handleKeyDownEvent(4);
        return bool;
        if ((this.dialogBoxManager.handleBackButton()) || (this.stateMachine.handleBackButtonEvent()))
          break;
        ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RETURN_TO_HOME_SCREEN_BACK_BUTTON);
      }
    case 24:
      this.stateMachine.handleKeyDownEvent(4);
      return bool;
    case 25:
    }
    this.stateMachine.handleKeyDownEvent(4);
    return bool;
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getRepeatCount() != 0)
      return super.onKeyUp(paramInt, paramKeyEvent);
    switch (paramInt)
    {
    default:
      return super.onKeyUp(paramInt, paramKeyEvent);
    case 80:
      this.stateMachine.handleKeyUpEvent(2);
      return true;
    case 23:
      this.stateMachine.handleKeyUpEvent(8);
      return true;
    case 27:
      this.stateMachine.handleKeyUpEvent(4);
      return true;
    case 24:
      this.stateMachine.handleKeyUpEvent(4);
      return true;
    case 25:
    }
    this.stateMachine.handleKeyUpEvent(4);
    return true;
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
  }

  protected void onPause()
  {
    this.stateMachine.handlePauseEvent();
    super.onPause();
  }

  public void onPictureTaken(Picture paramPicture)
  {
    this.stateMachine.handlePictureTakenEvent(paramPicture);
  }

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    this.stateMachine.handleProcessFrameEvent(paramTimestampedFrame);
  }

  public void onReachedMargin()
  {
    slideOutCaptureButtons();
  }

  protected void onResume()
  {
    super.onResume();
    if (!hasWindowFocus())
    {
      logger.i("onResume onResumePending=true", new Object[0]);
      this.onResumePending = true;
      return;
    }
    logger.i("onResume() onResumePending=false", new Object[0]);
    this.stateMachine.handleResumeEvent();
    this.onResumePending = false;
  }

  protected void onStart()
  {
    super.onStart();
    this.application.getViewport().updateNaturalOrientation(Viewport.computeNaturalOrientation(this));
    this.application.setContinuous(false);
    this.stateMachine.handleStartEvent();
  }

  protected void onStop()
  {
    this.stateMachine.handleStopEvent();
    super.onStop();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.stateMachine.handleTouchEvent(paramMotionEvent))
      return false;
    return super.onTouchEvent(paramMotionEvent);
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Boolean.valueOf(this.onResumePending);
    localUnveilLogger.i("onWindowFocusChanged() hasFocus=%B, onResumePending=%B", arrayOfObject);
    if ((paramBoolean) && (this.onResumePending))
    {
      this.stateMachine.handleResumeEvent();
      this.onResumePending = false;
    }
  }

  public void sendBarcodeOnlyRequest(Barcode paramBarcode, Context paramContext)
  {
    SensorManager localSensorManager = this.sensorManager;
    if (this.roiHelper.isRoiVisible());
    for (Rect localRect = this.roiHelper.getRawRegionOfInterest(); ; localRect = null)
    {
      localSensorManager.onLocalBarcodeRequest(localRect);
      logger.v("Populating barcode-only query request", new Object[0]);
      QueryBuilder localQueryBuilder = new QueryBuilder();
      localQueryBuilder.setSource(GogglesProtos.GogglesRequest.Source.LIVE);
      this.sensorManager.populateLocalBarcode(localQueryBuilder);
      localQueryBuilder.addLocalBarcode(paramBarcode).addMsSinceEpoch(Long.valueOf(System.currentTimeMillis())).addJpegQuality(this.cameraManager.getPictureQuality().recompressJpegQuality);
      if (this.roiHelper.previousQueryUsedRoi())
        localQueryBuilder.addCropRect(this.roiHelper.getRawRegionOfInterest(), this.roiHelper.getRegionSelectorWidth(), this.roiHelper.getRegionSelectorHeight());
      this.queryPipeline.sendLocalBarcodeQuery(localQueryBuilder, this.queryPipelineListener);
      return;
    }
  }

  public void showToast(int paramInt)
  {
    RotatingToast.makeText(this, (RelativeLayout)findViewById(R.id.capture_layout), this.application.getText(paramInt), 0).show();
  }

  private class DialogBoxManager
  {
    public static final int DIALOG_AUTH_RETRY = 2;
    public static final int DIALOG_CAMERA_CONNECTION_ERROR = 4;
    public static final int DIALOG_CLIENT_LACKS_IPV6_SUPPORT = 6;
    public static final int DIALOG_CLIENT_UPGRADE_REQUIRED = 3;
    public static final int DIALOG_NETWORK_ERROR = 7;
    private final RotatingDialogController rotatingDialogController = new RotatingDialogController();
    private final HashMap<Integer, RotatingDialog> rotatingDialogs = new HashMap();

    public DialogBoxManager()
    {
    }

    private void removeRotatingDialog(int paramInt)
    {
      dismissDialog(paramInt);
      this.rotatingDialogs.remove(Integer.valueOf(paramInt));
    }

    private RotatingDialog showAuthRetryDialog(RotatingAlertDialog.Builder paramBuilder)
    {
      paramBuilder.setTitle(R.string.connection_problem);
      paramBuilder.setIcon(17301543);
      paramBuilder.setPositiveButton(R.string.try_again, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          CaptureActivity.access$6702(CaptureActivity.this, 0);
          if (CaptureActivity.this.application.userWantsHistory())
          {
            CaptureActivity.this.fetchAuthToken();
            return;
          }
          CaptureActivity.logger.e("Dropping auth token; history disabled.", new Object[0]);
        }
      });
      paramBuilder.setNegativeButton(R.string.cancel, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          CaptureActivity.DialogBoxManager.this.dismissDialog(2);
          CaptureActivity.this.finish();
        }
      });
      paramBuilder.setOnCancelListener(new RotatingDialog.OnCancelListener()
      {
        public void onCancel(RotatingDialog paramAnonymousRotatingDialog)
        {
          CaptureActivity.DialogBoxManager.this.dismissDialog(2);
          CaptureActivity.this.finish();
        }
      });
      return paramBuilder.create();
    }

    private RotatingDialog showCameraConnectionErrorDialog(RotatingAlertDialog.Builder paramBuilder)
    {
      paramBuilder.setTitle(R.string.camera_error_title);
      paramBuilder.setIcon(17301543);
      paramBuilder.setMessage(R.string.camera_connection_error_message);
      paramBuilder.setCancelable(false);
      paramBuilder.setNeutralButton(R.string.ok, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          CaptureActivity.DialogBoxManager.this.dismissDialog(4);
          CaptureActivity.this.finish();
        }
      });
      return paramBuilder.create();
    }

    private RotatingDialog showClientLacksIpv6SupportDialog(RotatingAlertDialog.Builder paramBuilder)
    {
      paramBuilder.setTitle(R.string.unsupported_system_title);
      paramBuilder.setMessage(R.string.unsupported_system_explanation);
      paramBuilder.setPositiveButton(R.string.more_information, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          Intent localIntent = new Intent().setAction("android.intent.action.VIEW");
          CaptureActivity.this.startActivity(localIntent.setData(Uri.parse(CaptureActivity.this.getString(R.string.ipv6_help_url))));
        }
      });
      paramBuilder.setNegativeButton(R.string.exit_goggles, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          CaptureActivity.this.finish();
        }
      });
      paramBuilder.setOnCancelListener(new RotatingDialog.OnCancelListener()
      {
        public void onCancel(RotatingDialog paramAnonymousRotatingDialog)
        {
          CaptureActivity.this.finish();
        }
      });
      return paramBuilder.create();
    }

    private RotatingDialog showClientUpgradeRequiredDialog(RotatingAlertDialog.Builder paramBuilder)
    {
      paramBuilder.setMessage(R.string.client_too_old);
      paramBuilder.setIcon(17301543);
      paramBuilder.setCancelable(false);
      paramBuilder.setPositiveButton(R.string.go_to_market, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse(CaptureActivity.this.getString(R.string.market_link)));
          CaptureActivity.this.startActivity(localIntent);
          CaptureActivity.DialogBoxManager.this.dismissDialog(3);
          CaptureActivity.this.finish();
        }
      });
      paramBuilder.setNegativeButton(R.string.close, new RotatingDialog.OnClickListener()
      {
        public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
        {
          CaptureActivity.DialogBoxManager.this.dismissDialog(3);
          CaptureActivity.this.finish();
        }
      });
      return paramBuilder.create();
    }

    private RotatingDialog showNetworkErrorDialog(RotatingAlertDialog.Builder paramBuilder)
    {
      if (CaptureActivity.this.lastStatusCode != -1)
      {
        String str = CaptureActivity.this.getString(R.string.connection_problem_status_code);
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(CaptureActivity.this.lastStatusCode);
        paramBuilder.setTitle(String.format(str, arrayOfObject));
      }
      while (true)
      {
        paramBuilder.setIcon(17301543);
        paramBuilder.setNeutralButton(R.string.try_again, new RotatingDialog.OnClickListener()
        {
          public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
          {
            CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RETRY_FROM_SEARCH_ERROR);
            CaptureActivity.this.safeResendQuery();
          }
        });
        paramBuilder.setNegativeButton(R.string.save_for_later, new RotatingDialog.OnClickListener()
        {
          public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
          {
            CaptureActivity.DialogBoxManager.this.removeRotatingDialog(7);
            CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SAVE_FOR_LATER_FROM_SEARCH_ERROR);
            try
            {
              CaptureActivity.this.application.getSavedQueryProvider().save(CaptureActivity.this.queryPipeline.getPendingQuery());
              CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
              return;
            }
            catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
            {
              while (true)
                CaptureActivity.this.showFailedToSaveForLaterToast();
            }
          }
        });
        paramBuilder.setPositiveButton(R.string.cancel, new RotatingDialog.OnClickListener()
        {
          public void onClick(RotatingDialog paramAnonymousRotatingDialog, int paramAnonymousInt)
          {
            CaptureActivity.DialogBoxManager.this.removeRotatingDialog(7);
            CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CANCEL_FROM_SEARCH_ERROR);
            CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
          }
        });
        paramBuilder.setOnCancelListener(new RotatingDialog.OnCancelListener()
        {
          public void onCancel(RotatingDialog paramAnonymousRotatingDialog)
          {
            CaptureActivity.DialogBoxManager.this.removeRotatingDialog(7);
            CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CANCEL_FROM_SEARCH_ERROR);
            CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
          }
        });
        paramBuilder.setVertical(true);
        return paramBuilder.create();
        paramBuilder.setTitle(R.string.connection_problem);
      }
    }

    public RotatingDialog createDialog(int paramInt)
    {
      RotatingAlertDialog.Builder localBuilder = new RotatingAlertDialog.Builder(CaptureActivity.this, CaptureActivity.this.getCaptureLayout());
      switch (paramInt)
      {
      case 5:
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        localUnveilLogger.e("Bogus dialog id in createDialog: %d", arrayOfObject);
        return null;
      case 2:
        return showAuthRetryDialog(localBuilder);
      case 3:
        return showClientUpgradeRequiredDialog(localBuilder);
      case 4:
        return showCameraConnectionErrorDialog(localBuilder);
      case 6:
        return showClientLacksIpv6SupportDialog(localBuilder);
      case 7:
      }
      return showNetworkErrorDialog(localBuilder);
    }

    public void dismissDialog(int paramInt)
    {
      RotatingDialog localRotatingDialog = (RotatingDialog)this.rotatingDialogs.get(Integer.valueOf(paramInt));
      if (localRotatingDialog != null)
        this.rotatingDialogController.dismiss(localRotatingDialog);
    }

    public boolean handleBackButton()
    {
      return this.rotatingDialogController.handleBackButton();
    }

    public void showRotatingDialog(int paramInt)
    {
      boolean bool = this.rotatingDialogs.containsKey(Integer.valueOf(paramInt));
      RotatingDialog localRotatingDialog = null;
      if (bool)
        localRotatingDialog = (RotatingDialog)this.rotatingDialogs.get(Integer.valueOf(paramInt));
      if (localRotatingDialog == null)
      {
        localRotatingDialog = CaptureActivity.this.onCreateRotatingDialog(paramInt);
        this.rotatingDialogs.put(Integer.valueOf(paramInt), localRotatingDialog);
      }
      this.rotatingDialogController.show(localRotatingDialog);
    }
  }

  private final class IntentHelper
  {
    private Intents.IntentRecipe recipe = Intents.NONE;

    private IntentHelper()
    {
    }

    private void setRecipe(Intents.IntentRecipe paramIntentRecipe)
    {
      this.recipe = paramIntentRecipe;
    }

    public Intents.IntentRecipe getRecipe()
    {
      return this.recipe;
    }

    public void handleLocalBarcode(Barcode paramBarcode)
    {
      if (!(getRecipe() instanceof ZxingScan))
      {
        CaptureActivity.logger.e("Asked to handle a barcode when not given a barcode scanning intent.", new Object[0]);
        return;
      }
      ZxingScan localZxingScan = (ZxingScan)getRecipe();
      switch (CaptureActivity.7.$SwitchMap$com$google$android$apps$unveil$intents$ZxingScan$HandleStrategy[localZxingScan.getHandleStrategy().ordinal()])
      {
      default:
        return;
      case 1:
        CaptureActivity.this.setResult(-1, localZxingScan.getResponse(paramBarcode));
        CaptureActivity.this.finish();
        return;
      case 2:
      }
      CaptureActivity.this.startActivity(localZxingScan.getIntentToFire(paramBarcode));
    }

    public void onResume()
    {
      setRecipe(Intents.classify(CaptureActivity.this.getIntent()));
      boolean bool = getRecipe().showCaptureButtons();
      View[] arrayOfView = new View[2];
      arrayOfView[0] = CaptureActivity.this.findViewById(R.id.shutter_button_layout);
      arrayOfView[1] = CaptureActivity.this.findViewById(R.id.crop_button);
      Iterator localIterator = new LinkedList(Arrays.asList(arrayOfView)).iterator();
      if (localIterator.hasNext())
      {
        View localView = (View)localIterator.next();
        if (bool);
        for (int i = 0; ; i = 8)
        {
          localView.setVisibility(i);
          break;
        }
      }
    }

    public boolean shouldShowOptionsMenu()
    {
      return getRecipe().showOptionsMenu();
    }
  }

  private static class OrientationListener extends OrientationEventListener
  {
    private final CaptureActivity context;
    private boolean firstButtonAnimationStarted;
    private int lastOrientation = -1;
    private final Collection<RotatingImageView> rotatingWidgets;
    private final Viewport viewport;

    public OrientationListener(CaptureActivity paramCaptureActivity, Viewport paramViewport, Collection<RotatingImageView> paramCollection)
    {
      super();
      this.viewport = paramViewport;
      this.rotatingWidgets = paramCollection;
      this.context = paramCaptureActivity;
    }

    private void updateOrientation(int paramInt, boolean paramBoolean)
    {
      if (!this.context.captureButtonsVisible());
      label113: 
      while (true)
      {
        return;
        int i = UnveilSensorProvider.roundOrientation(paramInt);
        if (i != -1)
        {
          if (paramBoolean);
          for (int j = this.viewport.deviceRotationToCameraRotation(i); ; j = i)
          {
            if ((j == this.lastOrientation) && (this.lastOrientation != -1))
              break label113;
            this.lastOrientation = j;
            int k = (360 - j) % 360;
            Iterator localIterator = this.rotatingWidgets.iterator();
            while (localIterator.hasNext())
              ((RotatingImageView)localIterator.next()).rotateTo(k);
            break;
          }
        }
      }
    }

    public boolean getFirstButtonAnimationStarted()
    {
      return this.firstButtonAnimationStarted;
    }

    public void invalidate()
    {
      this.lastOrientation = -1;
      updateToLastOrientation();
    }

    public void onOrientationChanged(int paramInt)
    {
      updateOrientation(paramInt, true);
    }

    public void setFirstButtonAnimationStarted(boolean paramBoolean)
    {
      this.firstButtonAnimationStarted = paramBoolean;
    }

    public void updateToLastOrientation()
    {
      updateOrientation(this.lastOrientation, false);
    }
  }

  private class QueryEventListener extends QueryPipeline.EventListener
  {
    private QueryResponse localBarcodeResponse;

    private QueryEventListener()
    {
    }

    public void onAuthenticationError()
    {
      CaptureActivity.logger.i("Query listener auth error, invalidating token and aborting processing.", new Object[0]);
      CaptureActivity.this.invalidateAuthToken();
      CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
    }

    public void onHeavyProcessingEnd()
    {
      CaptureActivity.this.progressView.startAnimation();
    }

    public void onHeavyProcessingStart()
    {
      CaptureActivity.this.progressView.stopAnimation();
    }

    public void onImageCropped(Picture paramPicture)
    {
      CaptureActivity.logger.time("Image is cropped for interstitial", new Object[0]);
      if (paramPicture != null)
        CaptureActivity.this.showInterstitial(paramPicture);
      CaptureActivity.logger.time("Edge bitmap is being created now", new Object[0]);
    }

    public void onImageFailed()
    {
      RelativeLayout localRelativeLayout = (RelativeLayout)CaptureActivity.this.findViewById(R.id.capture_layout);
      RotatingToast.makeText(CaptureActivity.this, localRelativeLayout, CaptureActivity.this.application.getText(R.string.process_image_problem), 1).show();
      CaptureActivity.this.stateMachine.handleAbortProcessingEvent();
    }

    public void onImageReady(Picture paramPicture, final QueryBuilder paramQueryBuilder)
    {
      CaptureActivity.logger.w("onImageReady", new Object[0]);
      if (this.localBarcodeResponse != null)
      {
        CaptureActivity.this.handleQueryResponse(this.localBarcodeResponse);
        return;
      }
      CaptureActivity.this.progressView.displaySaveForLaterButton(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CaptureActivity.this.dialogBoxManager.dismissDialog(7);
          CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TAP_SAVE_FOR_LATER);
          if (CaptureActivity.this.application.getSavedQueryProvider().save(paramQueryBuilder) == null)
          {
            CaptureActivity.logger.e("Failed to save query to provider.", new Object[0]);
            CaptureActivity.this.showFailedToSaveForLaterToast();
          }
        }
      });
    }

    public void onNetworkError(int paramInt)
    {
      CaptureActivity.access$402(CaptureActivity.this, paramInt);
      if (paramInt == 420)
      {
        CaptureActivity.this.dialogBoxManager.showRotatingDialog(3);
        return;
      }
      CaptureActivity.this.dialogBoxManager.showRotatingDialog(7);
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      if (CaptureActivity.this.queryPipeline.getPicture() == null)
      {
        CaptureActivity.logger.i("Refusing to start ResultsActivity because we don't have a picture yet.", new Object[0]);
        this.localBarcodeResponse = paramQueryResponse;
        return;
      }
      CaptureActivity.this.handleQueryResponse(paramQueryResponse);
    }
  }

  private static class RegionOfInterestHelper
  {
    private final CameraManager cameraManager;
    private boolean previousQueryUsedRoi;
    private final RegionSelectorView regionSelector;

    public RegionOfInterestHelper(RegionSelectorView paramRegionSelectorView, CameraManager paramCameraManager)
    {
      this.regionSelector = paramRegionSelectorView;
      this.cameraManager = paramCameraManager;
    }

    public Rect getRawRegionOfInterest()
    {
      return this.regionSelector.getRegion();
    }

    public int getRegionSelectorHeight()
    {
      return this.regionSelector.getHeight();
    }

    public int getRegionSelectorWidth()
    {
      return this.regionSelector.getWidth();
    }

    public int getRoiMarginPx(CaptureActivity paramCaptureActivity, String paramString)
    {
      int i = 80;
      if ("Droid".equals(paramString))
        i = 60;
      while (("Nexus S".equals(paramString)) || ("Galaxy Nexus".equals(paramString)))
        return i;
      return (int)(110.0F * paramCaptureActivity.getResources().getDisplayMetrics().density);
    }

    public void hideRoi(boolean paramBoolean)
    {
      this.regionSelector.hide(paramBoolean);
    }

    public boolean isRoiVisible()
    {
      return this.regionSelector.getVisibility() == 0;
    }

    public boolean previousQueryUsedRoi()
    {
      return this.previousQueryUsedRoi;
    }

    public void rememberLastRegion(boolean paramBoolean)
    {
      this.regionSelector.setRememberLastRegion(paramBoolean);
    }

    public void setCropArea(Picture paramPicture, Rect paramRect)
    {
      if (paramRect == null)
        return;
      paramPicture.setCropArea(ImageUtils.adjustCropForUnexpectedPictureSize(this.cameraManager.transformToPictureCoordinates(paramRect), this.cameraManager.getPictureSize(), paramPicture.getSize()));
    }

    public void setPreviousQueryUsedRoi(boolean paramBoolean)
    {
      this.previousQueryUsedRoi = paramBoolean;
    }

    public void showRoi()
    {
      this.regionSelector.show();
    }
  }

  private static class SensorManager
  {
    private final CaptureActivity.OrientationListener buttonOrientationListener;
    private final UnveilLocationProvider locationProvider;
    private final UnveilSensorProvider sensorProvider;
    private final Viewport viewport;

    public SensorManager(UnveilLocationProvider paramUnveilLocationProvider, UnveilSensorProvider paramUnveilSensorProvider, CaptureActivity.OrientationListener paramOrientationListener, Viewport paramViewport)
    {
      this.locationProvider = paramUnveilLocationProvider;
      this.sensorProvider = paramUnveilSensorProvider;
      this.buttonOrientationListener = paramOrientationListener;
      this.viewport = paramViewport;
    }

    private void populateOrientations(QueryBuilder paramQueryBuilder)
    {
      int i = this.sensorProvider.getRoundedDeviceOrientation();
      int j = this.viewport.deviceRotationToCameraRotation(i);
      if (i != -1)
        paramQueryBuilder.addOrientationRelativeToDevice(i);
      paramQueryBuilder.addOrientationRelativeToCamera(j);
    }

    public void disableSensors()
    {
      this.buttonOrientationListener.disable();
      this.locationProvider.off();
      this.sensorProvider.off();
    }

    public void enableSensors()
    {
      this.buttonOrientationListener.enable();
      this.locationProvider.on();
      this.sensorProvider.on();
    }

    public void onLocalBarcodeRequest(Rect paramRect)
    {
      int i = this.sensorProvider.getRoundedDeviceOrientation();
      if (i == -1)
        i = 0;
      this.viewport.setLatestBarcodeQueryRotation(this.viewport.deviceRotationToCameraRotation(i));
      this.viewport.setLatestBarcodeQueryCrop(paramRect);
    }

    public void populate(QueryBuilder paramQueryBuilder)
    {
      populateOrientations(paramQueryBuilder);
      paramQueryBuilder.addLocation(UnveilSensorProvider.toLocationProto(this.locationProvider.getLocation()));
    }

    public void populateLocalBarcode(QueryBuilder paramQueryBuilder)
    {
      populateOrientations(paramQueryBuilder);
    }
  }

  private class StateMachine
  {
    public static final int CAMERA_KEY = 4;
    public static final int CENTER_KEY = 8;
    private static final int CROP_BUTTON = 16;
    private static final int FLASH_BUTTON = 32;
    private static final int FOCUS_BUTTONS_MASK = 15;
    public static final int FOCUS_KEY = 2;
    public static final int SHUTTER_BUTTON = 1;
    private static final int STATE_FOCUS_ONLY = 1;
    private static final int STATE_HOLDING_FOCUS_BUTTONS = 6;
    private static final int STATE_NORMAL = 0;
    private static final int STATE_PAUSED = 7;
    private static final int STATE_PIC_AFTER_FOCUS = 3;
    private static final int STATE_PROCESSING_RESULT = 9;
    private static final int STATE_QUERY_FOCUS = 2;
    private static final int STATE_STOPPED = 8;
    private static final int STATE_TAKING_PICTURE = 4;
    private static final int STATE_TAKING_PREVIEW = 5;
    private int buttonsDown = 0;
    private int state = 8;

    private StateMachine()
    {
    }

    private boolean anyButtonsDown()
    {
      return this.buttonsDown != 0;
    }

    private boolean anyFocusButtonsDown()
    {
      return (0xF & this.buttonsDown) != 0;
    }

    private void handleCameraPreviewSizeChangedEvent()
    {
      CaptureActivity.logger.i("handleCameraPreviewSizeChangedEvent", new Object[0]);
      RotatingImageView localRotatingImageView;
      int i;
      switch (this.state)
      {
      default:
        CaptureActivity.this.application.getViewport().setPreviewSize(CaptureActivity.this.cameraManager.getPreviewSize());
        CaptureActivity.this.initializePreviewLooper();
        localRotatingImageView = CaptureActivity.this.flashButton;
        boolean bool = CaptureActivity.this.cameraManager.isFlashSupported();
        i = 0;
        if (!bool)
          break;
      case 7:
      case 8:
      }
      while (true)
      {
        localRotatingImageView.setVisibility(i);
        return;
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignore cameraPreviewSizeChangedEvent in state %s", arrayOfObject);
        return;
        i = 4;
      }
    }

    private void handleCropButtonClickEvent()
    {
      CaptureActivity.logger.i("handleCropButtonClickEvent", new Object[0]);
      switch (this.state)
      {
      case 3:
      case 4:
      case 5:
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignoring press on crop button in state %s", arrayOfObject);
      case 0:
      case 1:
      case 2:
      case 6:
      }
      do
        return;
      while (anyButtonsDown());
      CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.ROI_TOGGLE);
      if (CaptureActivity.this.roiHelper.isRoiVisible())
      {
        CaptureActivity.logger.v("Hiding region of interest selector.", new Object[0]);
        CaptureActivity.this.roiHelper.hideRoi(false);
        return;
      }
      CaptureActivity.this.roiHelper.showRoi();
      CaptureActivity.logger.i("crop button click requesting focus", new Object[0]);
      CaptureActivity.this.maybeTriggerFocus(null, false);
    }

    private void handleFocusEvent(boolean paramBoolean)
    {
      UnveilLogger localUnveilLogger1 = CaptureActivity.logger;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Boolean.valueOf(paramBoolean);
      localUnveilLogger1.i("handleFocusEvent(%b)", arrayOfObject1);
      CaptureActivity.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.FOCUS);
      switch (this.state)
      {
      default:
        UnveilLogger localUnveilLogger3 = CaptureActivity.logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = stateName(this.state);
        localUnveilLogger3.i("Ignore focus event in state %s", arrayOfObject3);
        return;
      case 1:
        CaptureActivity.logger.time("Touch-to-focus complete", new Object[0]);
        if (!anyFocusButtonsDown())
        {
          setState(0);
          return;
        }
        setState(6);
        return;
      case 2:
        CaptureActivity.logger.time("Query focus complete", new Object[0]);
        if (!anyFocusButtonsDown())
        {
          setState(0);
          return;
        }
        setState(6);
        return;
      case 3:
        takePictureSoon();
        return;
      case 0:
      case 4:
      case 5:
      case 6:
      }
      UnveilLogger localUnveilLogger2 = CaptureActivity.logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = stateName(this.state);
      localUnveilLogger2.e("Unexpected focus transition from state %s", arrayOfObject2);
    }

    private void handleKeyDownEvent(int paramInt)
    {
      UnveilLogger localUnveilLogger1 = CaptureActivity.logger;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramInt);
      localUnveilLogger1.i("handleKeyDownEvent(%d)", arrayOfObject1);
      if ((!anyFocusButtonsDown()) && ((paramInt & 0xF) != 0))
      {
        CaptureActivity.logger.i("Disabling sensors", new Object[0]);
        CaptureActivity.this.sensorManager.disableSensors();
      }
      setButtonDown(paramInt);
      switch (this.state)
      {
      default:
        return;
      case 0:
        switch (paramInt)
        {
        default:
          return;
        case 1:
          CaptureActivity.this.setShutterButtonHighlight(true);
        case 4:
        case 8:
          CaptureActivity.logger.resetTime("Touch down", new Object[0]);
          CaptureActivity.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.FOCUS);
          setState(2);
          CaptureActivity.logger.i("keydown -> some focus button requesting focus", new Object[0]);
          CaptureActivity.this.maybeTriggerFocus(CaptureActivity.this, false);
          return;
        case 2:
          setState(1);
          CaptureActivity.logger.i("keydown -> focus key requesting focus", new Object[0]);
          CaptureActivity.this.cameraManager.focus(CaptureActivity.this);
          return;
        case 16:
          CaptureActivity.this.setCropButtonHighlight(true);
          return;
        case 32:
        }
        CaptureActivity.this.updateFlashButtonHighlight();
        return;
      case 1:
      case 2:
      case 6:
        switch (paramInt)
        {
        default:
          UnveilLogger localUnveilLogger3 = CaptureActivity.logger;
          Object[] arrayOfObject3 = new Object[1];
          arrayOfObject3[0] = stateName(this.state);
          localUnveilLogger3.i("Ignore key down because in state %s", arrayOfObject3);
          return;
        case 16:
          CaptureActivity.this.setCropButtonHighlight(true);
          return;
        case 32:
          CaptureActivity.this.updateFlashButtonHighlight();
          return;
        case 1:
          CaptureActivity.this.setShutterButtonHighlight(true);
          return;
        case 4:
        }
        if ((this.state == 1) || (this.state == 2))
        {
          setState(3);
          CaptureActivity.logger.i("Will take a picture after focus completes.", new Object[0]);
          return;
        }
        takePictureSoon();
        return;
      case 3:
      case 4:
      case 5:
      case 7:
      case 8:
      case 9:
      }
      UnveilLogger localUnveilLogger2 = CaptureActivity.logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = stateName(this.state);
      localUnveilLogger2.i("Ignore key down from state %s", arrayOfObject2);
    }

    private void handleKeyUpEvent(int paramInt)
    {
      UnveilLogger localUnveilLogger1 = CaptureActivity.logger;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramInt);
      localUnveilLogger1.i("handleKeyUpEvent(%d)", arrayOfObject1);
      setButtonUp(paramInt);
      int i;
      if ((paramInt & 0xF) != 0)
      {
        i = 1;
        if ((i == 0) || (!anyFocusButtonsDown()))
          break label71;
        CaptureActivity.logger.i("Ignoring event because other buttons are still down.", new Object[0]);
      }
      label71: 
      do
      {
        return;
        i = 0;
        break;
        if (i != 0)
        {
          CaptureActivity.logger.i("Enabling sensors", new Object[0]);
          CaptureActivity.this.sensorManager.enableSensors();
        }
        switch (this.state)
        {
        case 3:
        case 4:
        case 5:
        default:
          UnveilLogger localUnveilLogger2 = CaptureActivity.logger;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = stateName(this.state);
          localUnveilLogger2.i("Ignore key up from state %s", arrayOfObject2);
          return;
        case 6:
          if (!anyFocusButtonsDown())
            setState(0);
          break;
        case 1:
        case 2:
        }
      }
      while ((anyFocusButtonsDown()) || ((paramInt != 4) && (paramInt != 8)));
      CaptureActivity.logger.resetTime("Dpad center or camera pressed", new Object[0]);
      handleTakePictureEvent();
    }

    private void handleLocalBarcodeNotificationEvent(Picture paramPicture, Barcode paramBarcode)
    {
      CaptureActivity.logger.i("handleLocalBarcodeNotificationEvent", new Object[0]);
      switch (this.state)
      {
      default:
        return;
      case 1:
        CaptureActivity.logger.v("Found barcode while focusing.", new Object[0]);
      case 0:
        if ((CaptureActivity.this.intentHelper.getRecipe() instanceof ZxingScan))
        {
          CaptureActivity.this.intentHelper.handleLocalBarcode(paramBarcode);
          resetBarcodeState();
          return;
        }
        CaptureActivity.logger.i("Sending a local barcode request", new Object[0]);
        CaptureActivity.this.sendBarcodeOnlyRequest(paramBarcode, CaptureActivity.this);
        CaptureActivity.this.traceTracker.addPoint(TracingProtos.PointVariable.Type.BARCODE);
        CaptureActivity.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
        CaptureActivity.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.ACQUIRE_TO_IMAGE);
        ((Vibrator)CaptureActivity.this.getSystemService("vibrator")).vibrate(40L);
        if (CaptureActivity.this.roiHelper.isRoiVisible())
        {
          paramPicture.recycle();
          handleTakePictureEvent();
          return;
        }
        CaptureActivity.this.pausePreviewFrameProcessing();
        setState(5);
        CaptureActivity.this.cameraManager.takePictureFromFrame(paramPicture, CaptureActivity.this);
        return;
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 8:
      case 9:
      }
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = stateName(this.state);
      localUnveilLogger.w("Detected a local barcode in state %s instead of STATE_NORMAL, clearing barcode.", arrayOfObject);
      resetBarcodeState();
    }

    private RotatingDialog handleOnCreateRotatingDialogEvent(int paramInt)
    {
      CaptureActivity.logger.i("handleOnCreateRotatingDialogEvent", new Object[0]);
      switch (this.state)
      {
      case 7:
      case 8:
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignore create rotating dialog transistion from state %s", arrayOfObject);
        return null;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 9:
      }
      return CaptureActivity.this.dialogBoxManager.createDialog(paramInt);
    }

    private void handlePauseEvent()
    {
      CaptureActivity.logger.i("handlePauseEvent", new Object[0]);
      CaptureActivity.this.progressView.stopAnimation();
      switch (this.state)
      {
      case 8:
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignoring pause event in state %s", arrayOfObject);
        return;
      case 0:
      case 1:
      case 2:
      case 3:
      case 4:
      case 5:
      case 6:
      case 7:
      case 9:
      }
      setState(7);
      CaptureActivity.this.sensorManager.disableSensors();
      CaptureActivity.this.buttonOrientationListener.setFirstButtonAnimationStarted(false);
      CaptureActivity.this.pausePreviewFrameProcessing();
      CaptureActivity.this.cameraManager.forceReleaseCamera();
    }

    private void handlePictureTakenEvent(Picture paramPicture)
    {
      CaptureActivity.logger.i("handlePictureTakenEvent", new Object[0]);
      boolean bool1 = CaptureActivity.this.cameraManager.getExpectedFlashMode().equals("torch");
      switch (this.state)
      {
      default:
      case 5:
      case 4:
      case 6:
      case 0:
      case 1:
      case 2:
      case 3:
      }
      while (true)
      {
        UnveilLogger localUnveilLogger2 = CaptureActivity.logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = stateName(this.state);
        localUnveilLogger2.i("Ignore picture taken event in state %s", arrayOfObject2);
        return;
        CaptureActivity.this.slideOutCaptureButtons();
        if (CaptureActivity.this.lastPreviewFrameWhenTakingPreview != null)
          CaptureActivity.this.lastPreviewFrameWhenTakingPreview.recycle();
        CaptureActivity.access$4102(CaptureActivity.this, paramPicture);
        if (bool1)
          toggleFlashState();
        CaptureActivity.access$2302(CaptureActivity.this, bool1);
        CaptureActivity.this.setShutterButtonHighlight(false);
        CaptureActivity.logger.time("Picture taken", new Object[0]);
        if (CaptureActivity.this.application.getViewport().getNaturalOrientation(CaptureActivity.this) == 2)
          paramPicture.setOrientation(-90 + paramPicture.getOrientation());
        CaptureActivity.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.ACQUIRE_TO_IMAGE);
        if (CaptureActivity.this.stateMachine.state == 4)
        {
          if (!CaptureActivity.this.accessibilityUtils.isAccessibilityEnabled())
            CaptureActivity.this.audioManager.setStreamMute(1, false);
          boolean bool2 = CaptureActivity.this.roiHelper.previousQueryUsedRoi();
          Rect localRect = null;
          if (bool2)
            localRect = CaptureActivity.this.roiHelper.getRawRegionOfInterest();
          CaptureActivity.this.roiHelper.setCropArea(paramPicture, localRect);
          CaptureActivity.this.queryPipeline.startRotatePhotoTask(paramPicture);
          CaptureActivity.this.pausePreviewFrameProcessing();
          CaptureActivity.access$3102(CaptureActivity.this, true);
          if ((!CaptureActivity.this.authenticated) && (CaptureActivity.this.application.userWantsHistory()))
            CaptureActivity.logger.e("User wants history but has no auth. Querying anyway.", new Object[0]);
          QueryBuilder localQueryBuilder = new QueryBuilder();
          localQueryBuilder.setSource(GogglesProtos.GogglesRequest.Source.LIVE);
          CaptureActivity.this.sensorManager.populate(localQueryBuilder);
          localQueryBuilder.addMsSinceEpoch(Long.valueOf(System.currentTimeMillis())).addLocalBarcode(CaptureActivity.this.barcodeScanner.getBarcode()).addJpegQuality(CaptureActivity.this.cameraManager.getPictureQuality().recompressJpegQuality);
          if (CaptureActivity.this.roiHelper.previousQueryUsedRoi())
            localQueryBuilder.addCropRect(CaptureActivity.this.roiHelper.getRawRegionOfInterest(), CaptureActivity.this.roiHelper.getRegionSelectorWidth(), CaptureActivity.this.roiHelper.getRegionSelectorHeight());
          CaptureActivity.this.queryPipeline.setPendingQuery(localQueryBuilder);
          CaptureActivity.access$402(CaptureActivity.this, -1);
          if (CaptureActivity.this.stateMachine.state != 5)
            break label576;
          CaptureActivity.this.queryPipeline.start(CaptureActivity.this.getIntent(), paramPicture, CaptureActivity.this.queryPipelineListener);
        }
        while (true)
        {
          setState(9);
          return;
          CaptureActivity.this.roiHelper.setPreviousQueryUsedRoi(false);
          break;
          label576: CaptureActivity.this.queryPipeline.start(CaptureActivity.this.getIntent(), null, CaptureActivity.this.queryPipelineListener);
        }
        UnveilLogger localUnveilLogger1 = CaptureActivity.logger;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = stateName(this.state);
        localUnveilLogger1.e("Unexpected picture taken transition from state %s", arrayOfObject1);
      }
    }

    private void handleProcessFrameEvent(TimestampedFrame paramTimestampedFrame)
    {
      switch (this.state)
      {
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignore process frame event in state %s", arrayOfObject);
      case 0:
      case 1:
      case 2:
      case 3:
      case 6:
        return;
      case 4:
        CaptureActivity.this.pausePreviewFrameProcessing();
        takePictureSilently();
        if (CaptureActivity.this.lastPreviewFrameWhenTakingPicture != null)
          CaptureActivity.this.lastPreviewFrameWhenTakingPicture.recycle();
        CaptureActivity.access$4002(CaptureActivity.this, PictureFactory.createBitmap(paramTimestampedFrame.getRawData(), paramTimestampedFrame.getWidth(), paramTimestampedFrame.getHeight(), 0));
        if (CaptureActivity.this.roiHelper.isRoiVisible())
          CaptureActivity.this.roiHelper.setCropArea(CaptureActivity.this.lastPreviewFrameWhenTakingPicture, CaptureActivity.this.roiHelper.getRawRegionOfInterest());
        CaptureActivity.this.showInterstitial(CaptureActivity.this.lastPreviewFrameWhenTakingPicture);
        CaptureActivity.logger.time("Interstitial is shown, maybe waiting for edge bitmap.", new Object[0]);
        return;
      case 5:
      }
      if (CaptureActivity.this.accessibilityUtils.isAccessibilityEnabled())
        TonePlayer.logTone(14, 300, 60, true);
      CaptureActivity.this.pausePreviewFrameProcessing();
      CaptureActivity.this.cameraManager.takePictureFromFrame(paramTimestampedFrame, CaptureActivity.this);
    }

    private void handleResumeEvent()
    {
      CaptureActivity.logger.i("handleResumeEvent", new Object[0]);
      switch (this.state)
      {
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.e("Unexpected resume while in state %s", arrayOfObject);
      case 0:
      case 7:
      }
      resetBarcodeState();
      CaptureActivity.access$1402(CaptureActivity.this, false);
      setState(0);
      if (!CaptureActivity.this.isIpv6SupportAvailable())
      {
        CaptureActivity.this.dialogBoxManager.showRotatingDialog(6);
        return;
      }
      CaptureActivity.this.sensorManager.enableSensors();
      setAllButtonsUp();
      CaptureActivity.this.setShutterButtonHighlight(false);
      CaptureActivity.this.setCropButtonHighlight(false);
      CaptureActivity.access$1902(CaptureActivity.this, new CaptureActivity.QueryEventListener(CaptureActivity.this, null));
      CaptureActivity.this.application.getQueryPipeline().setProcessedPicture(null);
      CaptureActivity.this.queryPipeline.startNewQuery();
      CaptureActivity.this.traceTracker.addPoint(TracingProtos.PointVariable.Type.CAMERA_PREVIEW_START);
      CaptureActivity.this.cameraManager.acquireCamera();
      CaptureActivity.this.hideInterstitial();
      if (CaptureActivity.this.enableFlashOnResume)
        toggleFlashState();
      CaptureActivity.this.intentHelper.onResume();
      CaptureActivity.this.wrappingLayout.requestLayout();
      CaptureActivity.this.cropButton.setVisibility(0);
      CaptureActivity.this.setShoppingButtonHighlight(false);
      CaptureActivity.this.roiHelper.hideRoi(false);
      CaptureActivity.this.roiHelper.rememberLastRegion(true);
      try
      {
        CaptureActivity.this.dialogBoxManager.dismissDialog(7);
        return;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
      }
    }

    private void handleShutterButtonClickEvent()
    {
      CaptureActivity.logger.i("handleShutterButtonClickEvent", new Object[0]);
      switch (this.state)
      {
      case 3:
      case 4:
      case 5:
      default:
        CaptureActivity.this.setShutterButtonHighlight(false);
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignoring press on shutter button in state %s", arrayOfObject);
        return;
      case 0:
      case 1:
      case 2:
      case 6:
      }
      if (!anyButtonsDown())
      {
        CaptureActivity.this.traceTracker.addPoint(TracingProtos.PointVariable.Type.USER);
        CaptureActivity.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
        CaptureActivity.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.ACQUIRE_TO_IMAGE);
        handleTakePictureEvent();
        return;
      }
      CaptureActivity.this.setShutterButtonHighlight(false);
    }

    private void handleStartEvent()
    {
      CaptureActivity.logger.i("handleStartEvent", new Object[0]);
      if (this.state != 8)
      {
        UnveilLogger localUnveilLogger2 = CaptureActivity.logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = stateName(this.state);
        localUnveilLogger2.e("Unexpected start event in state %s", arrayOfObject2);
      }
      CaptureActivity.access$3102(CaptureActivity.this, false);
      setState(0);
      setAllButtonsUp();
      CaptureActivity.this.slideInCaptureButtons();
      CaptureActivity.access$3302(CaptureActivity.this, CaptureActivity.this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID));
      UnveilLogger localUnveilLogger1 = CaptureActivity.logger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Boolean.valueOf(CaptureActivity.this.authenticated);
      arrayOfObject1[1] = Boolean.valueOf(CaptureActivity.this.application.userWantsHistory());
      localUnveilLogger1.i("onStart, authenticated = %b and userWantsHistory = %b", arrayOfObject1);
      CaptureActivity.access$3402(CaptureActivity.this, 3);
      if (CaptureActivity.this.application.userWantsHistory())
      {
        if (!CaptureActivity.this.authenticated)
          CaptureActivity.this.fetchAuthToken();
        return;
      }
      CaptureActivity.access$3302(CaptureActivity.this, false);
    }

    private void handleStopEvent()
    {
      CaptureActivity.logger.i("handleStopEvent", new Object[0]);
      if (CaptureActivity.this.lastPreviewFrameWhenTakingPicture != null)
      {
        CaptureActivity.this.lastPreviewFrameWhenTakingPicture.recycle();
        CaptureActivity.access$4002(CaptureActivity.this, null);
      }
      if (CaptureActivity.this.lastPreviewFrameWhenTakingPreview != null)
      {
        CaptureActivity.this.lastPreviewFrameWhenTakingPreview.recycle();
        CaptureActivity.access$4102(CaptureActivity.this, null);
      }
      CaptureActivity.this.progressView.recycle();
      setState(8);
    }

    private void handleTakePictureEvent()
    {
      CaptureActivity.logger.i("handleTakePictureEvent", new Object[0]);
      CaptureActivity.this.barcodeScanner.disable();
      switch (this.state)
      {
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Ignore take picture request from state %s", arrayOfObject);
        return;
      case 0:
        CaptureActivity.this.startPreviewFrameProcessing();
        setState(3);
        CaptureActivity.logger.i("take picture event requesting focus", new Object[0]);
        CaptureActivity.this.maybeTriggerFocus(CaptureActivity.this, false);
        return;
      case 1:
      case 2:
      }
      setState(3);
    }

    private boolean handleTouchEvent(MotionEvent paramMotionEvent)
    {
      if (paramMotionEvent.getAction() == 0)
        CaptureActivity.logger.i("handleTouchEvent(ACTION_DOWN)", new Object[0]);
      switch (this.state)
      {
      default:
        return false;
      case 0:
      }
      CaptureActivity.logger.v("Camera preview click--triggering focus", new Object[0]);
      setState(1);
      CaptureActivity.this.cameraManager.focus(CaptureActivity.this);
      return false;
    }

    private void handleTouchOutEvent(int paramInt)
    {
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.i("handleTouchOutEvent(%d)", arrayOfObject);
      switch (this.state)
      {
      default:
        switch (paramInt)
        {
        default:
          return;
        case 1:
        case 16:
        case 32:
        }
        break;
      case 2:
      }
      switch (paramInt)
      {
      default:
        return;
      case 1:
        CaptureActivity.this.setShutterButtonHighlight(false);
        setState(6);
        return;
      case 16:
        CaptureActivity.this.setCropButtonHighlight(false);
        return;
      case 32:
      }
      CaptureActivity.this.updateFlashButtonHighlight();
      return;
      CaptureActivity.this.setShutterButtonHighlight(false);
      return;
      CaptureActivity.this.setCropButtonHighlight(false);
      return;
      CaptureActivity.this.updateFlashButtonHighlight();
    }

    private void resetBarcodeState()
    {
      CaptureActivity.this.localBarcodeListener.clearLastScannedBarcodes();
      CaptureActivity.this.barcodeScanner.reset();
    }

    private void setState(int paramInt)
    {
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = stateName(paramInt);
      localUnveilLogger.i("Enter state %s", arrayOfObject);
      this.state = paramInt;
    }

    private String stateName(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return "UNKNOWN STATE " + paramInt;
      case 0:
        return "STATE_NORMAL";
      case 2:
        return "STATE_QUERY_FOCUS";
      case 1:
        return "STATE_FOCUS_ONLY";
      case 3:
        return "STATE_PIC_AFTER_FOCUS";
      case 4:
        return "STATE_TAKING_PICTURE";
      case 5:
        return "STATE_TAKING_PREVIEW";
      case 6:
        return "STATE_HOLDING_FOCUS_BUTTONS";
      case 7:
        return "STATE_PAUSED";
      case 8:
        return "STATE_STOPPED";
      case 9:
      }
      return "STATE_PROCESSING_RESULT";
    }

    private void takePictureSilently()
    {
      CaptureActivity.logger.time("Begin taking picture.", new Object[0]);
      if (!CaptureActivity.this.accessibilityUtils.isAccessibilityEnabled())
        CaptureActivity.this.audioManager.setStreamMute(1, true);
      CaptureActivity.this.cameraManager.takePicture(null, CaptureActivity.this);
      CaptureActivity.logger.time("cameraManager.takePicture() returned.", new Object[0]);
    }

    private void takePictureSoon()
    {
      if (CaptureActivity.this.roiHelper.isRoiVisible())
      {
        CaptureActivity.logger.i("Use high quality", new Object[0]);
        CaptureActivity.this.cameraManager.requestPictureQuality(CameraManager.PictureQuality.HIGH_QUALITY);
      }
      while (true)
      {
        CaptureActivity.logger.time("Taking picture", new Object[0]);
        if (!CaptureActivity.this.canUsePreviewFrame())
          break;
        setState(5);
        return;
        CaptureActivity.logger.i("Use normal quality", new Object[0]);
        CaptureActivity.this.cameraManager.requestPictureQuality(CameraManager.PictureQuality.NORMAL_QUALITY);
      }
      CaptureActivity.logger.i("Take snapshot", new Object[0]);
      if (!PreviewLooper.supportNonstopFrameProcessing(CaptureActivity.this))
        CaptureActivity.this.startPreviewFrameProcessing();
      setState(4);
    }

    private void toggleFlashState()
    {
      int i;
      CameraManager localCameraManager;
      if (!CaptureActivity.this.cameraManager.getExpectedFlashMode().equals("torch"))
      {
        i = 1;
        localCameraManager = CaptureActivity.this.cameraManager;
        if (i == 0)
          break label55;
      }
      label55: for (String str = "torch"; ; str = "off")
      {
        localCameraManager.setFlashMode(str);
        CaptureActivity.this.updateFlashButtonHighlight();
        return;
        i = 0;
        break;
      }
    }

    public void configureButton(View paramView, final int paramInt)
    {
      paramView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          CaptureActivity.this.stateMachine.handleButtonClickEvent(paramInt);
        }
      });
      paramView.setOnTouchListener(new View.OnTouchListener()
      {
        public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
        {
          if ((paramInt == 16) && (((RegionSelectorView)paramAnonymousView.getTag()).getVisibility() == 0));
          do
          {
            return false;
            switch (paramAnonymousMotionEvent.getAction())
            {
            default:
              return false;
            case 0:
              CaptureActivity.this.stateMachine.handleKeyDownEvent(paramInt);
              return false;
            case 1:
              CaptureActivity.this.stateMachine.handleKeyUpEvent(paramInt);
              return false;
            case 2:
            }
          }
          while ((paramAnonymousMotionEvent.getX() >= 0.0F) && (paramAnonymousMotionEvent.getY() >= 0.0F) && (paramAnonymousMotionEvent.getX() < paramAnonymousView.getWidth()) && (paramAnonymousMotionEvent.getY() < paramAnonymousView.getHeight()));
          CaptureActivity.this.stateMachine.handleTouchOutEvent(paramInt);
          return false;
        }
      });
    }

    public void handleAbortProcessingEvent()
    {
      CaptureActivity.logger.i("handleAbortProcessingEvent", new Object[0]);
      switch (this.state)
      {
      default:
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.i("Unexpected abort processing transition from state %s", arrayOfObject);
        return;
      case 9:
      }
      CaptureActivity.this.queryPipeline.cancel();
      CaptureActivity.this.queryPipeline.startNewQuery();
      CaptureActivity.this.traceTracker.addPoint(TracingProtos.PointVariable.Type.CAMERA_PREVIEW_START);
      CaptureActivity.this.sensorManager.enableSensors();
      CaptureActivity.this.cameraManager.acquireCamera();
      CaptureActivity.this.cameraManager.startPreview();
      CaptureActivity.this.hideInterstitial();
      CaptureActivity.this.startPreviewFrameProcessing();
      CaptureActivity.this.application.getQueryPipeline().setProcessedPicture(null);
      resetBarcodeState();
      CaptureActivity.access$1902(CaptureActivity.this, new CaptureActivity.QueryEventListener(CaptureActivity.this, null));
      CaptureActivity.access$1402(CaptureActivity.this, false);
      setState(0);
    }

    public boolean handleBackButtonEvent()
    {
      CaptureActivity.logger.i("handlebackButtonEvent", new Object[0]);
      boolean bool;
      if (CaptureActivity.this.progressFrame.getVisibility() != 0)
      {
        int i = CaptureActivity.this.cameraOverlay.getVisibility();
        bool = false;
        if (i != 0);
      }
      else
      {
        handleAbortProcessingEvent();
        bool = true;
      }
      return bool;
    }

    public void handleButtonClickEvent(int paramInt)
    {
      switch (paramInt)
      {
      default:
        return;
      case 16:
        handleCropButtonClickEvent();
        return;
      case 32:
        handleFlashButtonClickEvent();
        return;
      case 1:
      }
      handleShutterButtonClickEvent();
    }

    public void handleCameraLayoutFinished()
    {
      CaptureActivity.logger.i("handleAbortProcessingEvent", new Object[0]);
      if ((this.state == 7) || (this.state == 8))
      {
        UnveilLogger localUnveilLogger = CaptureActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = stateName(this.state);
        localUnveilLogger.w("Not starting camera preview in state %s", arrayOfObject);
        return;
      }
      CaptureActivity.this.cameraManager.startPreview();
      CaptureActivity.this.initializePreviewLooper();
    }

    public void handleFlashButtonClickEvent()
    {
      CaptureActivity.logger.i("handleFlashButtonClickEvent", new Object[0]);
      CaptureActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.FLASH_TOGGLE);
      toggleFlashState();
    }

    public void setAllButtonsUp()
    {
      this.buttonsDown = 0;
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.buttonsDown);
      localUnveilLogger.i("buttonsDown <- %d", arrayOfObject);
    }

    public void setButtonDown(int paramInt)
    {
      this.buttonsDown = (paramInt | this.buttonsDown);
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.buttonsDown);
      localUnveilLogger.i("buttonsDown <- %d", arrayOfObject);
    }

    public void setButtonUp(int paramInt)
    {
      this.buttonsDown &= (paramInt ^ 0xFFFFFFFF);
      UnveilLogger localUnveilLogger = CaptureActivity.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.buttonsDown);
      localUnveilLogger.i("buttonsDown <- %d", arrayOfObject);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.CaptureActivity
 * JD-Core Version:    0.6.2
 */