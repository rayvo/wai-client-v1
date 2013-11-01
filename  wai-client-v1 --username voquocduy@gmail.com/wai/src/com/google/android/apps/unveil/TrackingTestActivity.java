package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.view.SurfaceHolder;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.gl.GLCameraPreview;
import com.google.android.apps.unveil.nonstop.ImageBlurProcessor;
import com.google.android.apps.unveil.nonstop.PreviewLooper;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.tracking.TrackingEvaluator;
import com.google.android.apps.unveil.tracking.TrackingEvaluator.EvaluationFinishedCallback;
import com.google.android.apps.unveil.tracking.TrackingTestView;
import com.google.android.apps.unveil.tracking.TrackingTestView.TrackingTestViewProcessor;

public class TrackingTestActivity extends GogglesPreviewLoopingActivity
  implements TrackingEvaluator.EvaluationFinishedCallback
{
  public static final String CPU_KEY = "cpu_key";
  public static final String PERFORMANCE_KEY = "performance_key";
  private static final UnveilLogger logger = new UnveilLogger();
  private TrackingFinishedCallback evaluateTrackingActivity;
  private TrackingEvaluator evaluator;
  private boolean finished;
  private TrackingTestView trackingTestView;

  private void maybeMakeCallback()
  {
    try
    {
      if (this.finished)
      {
        pauseLooping();
        if (this.evaluateTrackingActivity != null)
        {
          this.evaluateTrackingActivity.callback(this.evaluator.getPerformance(), this.evaluator.getTimePerFrame());
          this.evaluateTrackingActivity = null;
        }
        new Thread(new Runnable()
        {
          public void run()
          {
            Intent localIntent = new Intent();
            localIntent.putExtra("performance_key", TrackingTestActivity.this.evaluator.getPerformance());
            localIntent.putExtra("cpu_key", TrackingTestActivity.this.evaluator.getTimePerFrame());
            TrackingTestActivity.this.setResult(-1, localIntent);
            TrackingTestActivity.this.finish();
          }
        }).run();
        this.finished = false;
      }
      return;
    }
    finally
    {
    }
  }

  protected void addFrameProcessors(PreviewLooper paramPreviewLooper)
  {
    logger.v("addFrameProcessors", new Object[0]);
    if (this.application.getUseGroundtruth())
    {
      String str = this.application.getGroundtruthDirectory();
      this.evaluator = new TrackingEvaluator(getResources(), str, null, this);
      paramPreviewLooper.addPreviewProcessor(this.evaluator, 0);
      addRenderCallback(this.evaluator);
      addGlRenderCallback(this.evaluator);
      return;
    }
    this.trackingTestView.getHolder().setFormat(-3);
    TrackingTestView localTrackingTestView = this.trackingTestView;
    localTrackingTestView.getClass();
    TrackingTestView.TrackingTestViewProcessor localTrackingTestViewProcessor = new TrackingTestView.TrackingTestViewProcessor(localTrackingTestView);
    paramPreviewLooper.addPreviewProcessor(localTrackingTestViewProcessor, 0);
    addGlRenderCallback(localTrackingTestViewProcessor);
    this.trackingTestView.setBoundingBoxLayer(this.glCameraPreview.getBoundingBoxLayer());
    paramPreviewLooper.addPreviewProcessor(new ImageBlurProcessor(this.cameraManager), 1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    logger.v("onCreate", new Object[0]);
    super.onCreate(paramBundle);
    this.finished = false;
    initializeActivity(R.layout.tracking_test);
    this.application.setContinuous(true);
    this.trackingTestView = ((TrackingTestView)findViewById(R.id.tracking_view));
    if (this.application.getUseGroundtruth())
      this.trackingTestView.setVisibility(8);
  }

  public void onEvaluationFinished()
  {
    try
    {
      this.finished = true;
      maybeMakeCallback();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void onStop()
  {
    this.cameraManager.forceReleaseCamera();
    pauseLooping();
    super.onStop();
  }

  public void setFinishedCallback(TrackingFinishedCallback paramTrackingFinishedCallback)
  {
    try
    {
      if (this.evaluateTrackingActivity != null)
        logger.w("New callback registered before old one was called!", new Object[0]);
      this.evaluateTrackingActivity = paramTrackingFinishedCallback;
      if (this.finished)
        logger.w("Tracking finished before callback was registered.", new Object[0]);
      maybeMakeCallback();
      return;
    }
    finally
    {
    }
  }

  public static abstract interface TrackingFinishedCallback
  {
    public abstract void callback(float paramFloat1, float paramFloat2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.TrackingTestActivity
 * JD-Core Version:    0.6.2
 */