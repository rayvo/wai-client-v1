package com.google.android.apps.unveil.sensors.proxies.camera;

import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.view.SurfaceHolder;
import com.google.android.apps.unveil.env.PlatformVersionUtils.TextureView;
import java.io.IOException;

public abstract interface CameraProxy
{
  public abstract void addCallbackBuffer(byte[] paramArrayOfByte);

  public abstract void autoFocus(Camera.AutoFocusCallback paramAutoFocusCallback);

  public abstract void cancelAutoFocus();

  public abstract ParametersProxy getParameters();

  public abstract void release();

  public abstract void setDisplayOrientation(int paramInt);

  public abstract void setOneShotPreviewCallback(Camera.PreviewCallback paramPreviewCallback);

  public abstract void setParameters(ParametersProxy paramParametersProxy);

  public abstract void setPreviewCallback(Camera.PreviewCallback paramPreviewCallback);

  public abstract void setPreviewCallbackWithBuffer(Camera.PreviewCallback paramPreviewCallback);

  public abstract void setPreviewDisplay(SurfaceHolder paramSurfaceHolder)
    throws IOException;

  public abstract void setPreviewTexture(PlatformVersionUtils.TextureView paramTextureView)
    throws IOException;

  public abstract void setZoomChangeListener(Camera.OnZoomChangeListener paramOnZoomChangeListener);

  public abstract void startPreview();

  public abstract void startSmoothZoom(int paramInt);

  public abstract void stopPreview();

  public abstract void takePicture(Camera.ShutterCallback paramShutterCallback, Camera.PictureCallback paramPictureCallback1, Camera.PictureCallback paramPictureCallback2);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.CameraProxy
 * JD-Core Version:    0.6.2
 */