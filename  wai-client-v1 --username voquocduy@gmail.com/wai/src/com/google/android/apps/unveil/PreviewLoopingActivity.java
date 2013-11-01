package com.google.android.apps.unveil;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.gl.GLCameraPreview;
import com.google.android.apps.unveil.nonstop.DebugView;
import com.google.android.apps.unveil.nonstop.DebugView.RenderCallback;
import com.google.android.apps.unveil.nonstop.PreviewLooper;
import com.google.android.apps.unveil.nonstop.StatusFrameProcessor;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.sensors.CameraManager.Listener;
import com.google.android.apps.unveil.ui.CameraWrappingLayout;
import com.google.android.apps.unveil.ui.CameraWrappingLayout.CameraLayoutHandler;
import com.google.android.apps.unveil.ui.GlOverlay;
import com.google.android.apps.unveil.ui.GlOverlay.RenderCallback;

public abstract class PreviewLoopingActivity extends Activity
  implements CameraManager.Listener, CameraWrappingLayout.CameraLayoutHandler
{
  private static final int LOOPER_FPS = 30;
  private static final UnveilLogger logger = new UnveilLogger();
  protected CameraManager cameraManager;
  private DebugView debugView;
  protected GLCameraPreview glCameraPreview;
  private GlOverlay glDebugView;
  private boolean isActivityRunning;
  private boolean needsStartCameraPreview;
  private boolean onResumePending = false;
  private Size previewDisplayedSize;
  protected PreviewLooper previewLooper;
  private CameraWrappingLayout wrappingLayout;

  private void onResumeInternal()
  {
    logger.i("onResumeInternal()", new Object[0]);
    this.cameraManager.acquireCamera();
    if (this.glDebugView != null)
      this.glDebugView.onResume();
    onLooperRestart();
    this.isActivityRunning = true;
    this.wrappingLayout.requestLayout();
  }

  protected abstract void addFrameProcessors(PreviewLooper paramPreviewLooper);

  public void addGlRenderCallback(GlOverlay.RenderCallback paramRenderCallback)
  {
    if (this.glDebugView != null)
      this.glDebugView.addRenderCallback(paramRenderCallback);
  }

  public void addRenderCallback(DebugView.RenderCallback paramRenderCallback)
  {
    this.debugView.addCallback(paramRenderCallback);
  }

  protected void addViewAsCameraOverlay(View paramView, int paramInt)
  {
    this.wrappingLayout.addView(paramView, paramInt);
    this.wrappingLayout.requestLayout();
  }

  public int getRotationAngleBetweenCameraAndDisplay()
  {
    switch (getRequestedOrientation())
    {
    default:
      throw new RuntimeException("Unhandled orientation: " + getRequestedOrientation());
    case 0:
      return 0;
    case 1:
    }
    return 90;
  }

  protected void initializeActivity(int paramInt)
  {
    logger.v("initializeActivity", new Object[0]);
    requestWindowFeature(1);
    getWindow().setFlags(1024, 1024);
    setContentView(paramInt);
    this.debugView = ((DebugView)findViewById(R.id.nonstop_debug_view));
    this.debugView.setVisibility(8);
    this.cameraManager = ((CameraManager)findViewById(R.id.camera_preview));
    this.cameraManager.registerListener(this);
    this.glCameraPreview = ((GLCameraPreview)findViewById(R.id.gl_camera_preview_overlay));
    this.wrappingLayout = ((CameraWrappingLayout)findViewById(R.id.camera_wrapper_layout));
    this.wrappingLayout.setCameraManager(this.cameraManager);
    this.wrappingLayout.setCameraLayoutHandler(this);
    this.wrappingLayout.setRotation(getRotationAngleBetweenCameraAndDisplay());
    this.wrappingLayout.setMirrored(this.cameraManager.getMirrored());
    this.glDebugView = ((GlOverlay)findViewById(R.id.gl_overlay));
    if (this.glDebugView != null)
      this.glDebugView.setZOrderMediaOverlay(true);
    this.needsStartCameraPreview = true;
  }

  protected boolean isLoopingPaused()
  {
    try
    {
      if (this.previewLooper != null)
      {
        boolean bool2 = this.previewLooper.isRunning();
        if (bool2);
      }
      else
      {
        bool1 = true;
        return bool1;
      }
      boolean bool1 = false;
    }
    finally
    {
    }
  }

  protected void maybeCreateLooper(Matrix paramMatrix)
  {
    if (this.previewLooper != null)
      return;
    logger.i("Initializing PreviewLooper.", new Object[0]);
    this.previewLooper = new PreviewLooper(this.cameraManager, getRotationAngleBetweenCameraAndDisplay(), 30.0F, 2, paramMatrix);
    this.previewLooper.addPreviewProcessor(new StatusFrameProcessor(this.cameraManager, null, this.debugView, this.glDebugView), 0);
    addFrameProcessors(this.previewLooper);
    this.debugView.setCallback(this.previewLooper);
  }

  public void onCameraAcquisitionError()
  {
    logger.e("Failed to acquire camera.", new Object[0]);
  }

  public void onCameraConnected()
  {
    logger.i("Camera connected, requesting final layout before starting preview.", new Object[0]);
    this.wrappingLayout.requestLayout();
  }

  public void onCameraFlashControlError()
  {
    logger.e("Failed to apply camera flash setting.", new Object[0]);
  }

  public void onCameraLayoutComplete()
  {
    logger.i("Camera is measured successfully", new Object[0]);
  }

  public void onCameraLayoutFinished(Size paramSize, Matrix paramMatrix)
  {
    if (!this.isActivityRunning);
    do
    {
      return;
      this.previewDisplayedSize = paramSize;
    }
    while (!this.needsStartCameraPreview);
    this.cameraManager.startPreview();
    maybeCreateLooper(paramMatrix);
    resumeLooping();
    this.needsStartCameraPreview = false;
  }

  public void onCameraPreviewSizeChanged()
  {
    try
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.cameraManager.getPreviewSize();
      localUnveilLogger.i("Camera preview size changed to %s", arrayOfObject);
      this.wrappingLayout.requestLayout();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onCameraQualityError()
  {
    logger.e("Failed to apply camera quality settings.", new Object[0]);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
  }

  protected void onDestroy()
  {
    logger.i("onDestroy", new Object[0]);
    if (this.previewLooper != null)
    {
      logger.i("Shutting down preview frame processing.", new Object[0]);
      this.previewLooper.shutdown();
    }
    super.onDestroy();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    case 24:
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onKeyUp(int paramInt, KeyEvent paramKeyEvent)
  {
    switch (paramInt)
    {
    case 24:
    }
    return super.onKeyUp(paramInt, paramKeyEvent);
  }

  protected void onLooperRestart()
  {
  }

  protected void onPause()
  {
    this.isActivityRunning = false;
    if (this.glDebugView != null)
      this.glDebugView.onPause();
    pauseLooping();
    if (this.cameraManager.isPreviewing())
      this.needsStartCameraPreview = true;
    this.cameraManager.forceReleaseCamera();
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    if (!hasWindowFocus())
    {
      logger.i("onResume. onResumePending=true", new Object[0]);
      this.onResumePending = true;
      return;
    }
    logger.i("onResume. onResumePending=false", new Object[0]);
    onResumeInternal();
    this.onResumePending = false;
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Boolean.valueOf(this.onResumePending);
    localUnveilLogger.i("onWindowFocusChanged.hasFocus=%B, .onResumePending=%B", arrayOfObject);
    if ((paramBoolean) && (this.onResumePending))
    {
      onResumeInternal();
      this.onResumePending = false;
    }
  }

  protected void pauseLooping()
  {
    try
    {
      if (this.previewLooper != null)
      {
        boolean bool = this.previewLooper.isRunning();
        if (bool)
          break label24;
      }
      while (true)
      {
        return;
        label24: logger.v("Attempting to pause looping.", new Object[0]);
        this.previewLooper.pauseLoop();
      }
    }
    finally
    {
    }
  }

  protected void resumeLooping()
  {
    try
    {
      if (this.previewLooper != null)
      {
        boolean bool = this.previewLooper.isRunning();
        if (!bool)
          break label24;
      }
      while (true)
      {
        return;
        label24: onLooperRestart();
        logger.v("Attempting to resume looping.", new Object[0]);
        this.previewLooper.startLoop(this.previewDisplayedSize);
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.PreviewLoopingActivity
 * JD-Core Version:    0.6.2
 */