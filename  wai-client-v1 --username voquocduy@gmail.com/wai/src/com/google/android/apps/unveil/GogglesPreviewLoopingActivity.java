package com.google.android.apps.unveil;

import android.content.Intent;
import android.graphics.Matrix;
import android.os.Bundle;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.gl.GLCameraPreview;
import com.google.android.apps.unveil.nonstop.PreviewLooper;
import com.google.android.apps.unveil.sensors.UnveilLocationProvider;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;

public abstract class GogglesPreviewLoopingActivity extends PreviewLoopingActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  protected UnveilContext application;
  private UnveilLocationProvider locationProvider;
  protected UnveilSensorProvider sensorProvider;

  protected void disableSensors()
  {
    this.locationProvider.off();
    this.sensorProvider.off();
  }

  protected void enableSensors()
  {
    this.locationProvider.on();
    this.sensorProvider.on();
  }

  public UnveilSettings getSettings()
  {
    Intent localIntent = getIntent();
    if (localIntent.hasExtra("UnveilSettings"))
      return (UnveilSettings)localIntent.getExtras().get("UnveilSettings");
    return null;
  }

  protected void initializeActivity(int paramInt)
  {
    logger.v("initializeActivity", new Object[0]);
    this.application.setContinuous(true);
    UnveilSettings localUnveilSettings = getSettings();
    if (localUnveilSettings != null)
      this.application.setSettings(localUnveilSettings);
    super.initializeActivity(paramInt);
    this.locationProvider = this.application.getLocationProvider();
    this.sensorProvider = this.application.getSensorProvider();
  }

  protected void maybeCreateLooper(Matrix paramMatrix)
  {
    if (this.previewLooper != null);
    do
    {
      return;
      super.maybeCreateLooper(paramMatrix);
    }
    while ((this.glCameraPreview == null) || (!this.application.getSettings().useGLES2Overlay));
    this.previewLooper.addPreviewProcessor(this.glCameraPreview.getFrameLoader(), 0);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilContext)getApplicationContext());
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
        label24: disableSensors();
        super.pauseLooping();
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
        label24: enableSensors();
        super.resumeLooping();
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.GogglesPreviewLoopingActivity
 * JD-Core Version:    0.6.2
 */