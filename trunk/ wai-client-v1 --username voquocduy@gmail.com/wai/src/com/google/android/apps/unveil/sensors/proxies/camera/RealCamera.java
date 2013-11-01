package com.google.android.apps.unveil.sensors.proxies.camera;

import android.content.res.Resources;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Build.VERSION;
import android.os.Handler;
import android.view.SurfaceHolder;
import com.google.android.apps.unveil.env.PlatformVersionUtils;
import com.google.android.apps.unveil.env.PlatformVersionUtils.TextureView;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

public class RealCamera
  implements CameraProxy
{
  private Method addBufferMethod = null;
  protected Camera camera;
  private Method setCallbackWithBufferMethod = null;

  protected RealCamera(Camera paramCamera)
  {
    this.camera = paramCamera;
  }

  public static RealCamera open(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    Camera localCamera = Camera.open();
    if (localCamera == null)
    {
      if (Build.VERSION.SDK_INT >= 9)
        localCamera = Camera.open(0);
    }
    else
      return new RealCamera(localCamera);
    return null;
  }

  public void addCallbackBuffer(byte[] paramArrayOfByte)
  {
    Method[] arrayOfMethod;
    int i;
    int j;
    if (this.addBufferMethod == null)
    {
      arrayOfMethod = this.camera.getClass().getDeclaredMethods();
      i = arrayOfMethod.length;
      j = 0;
    }
    while (true)
    {
      if (j < i)
      {
        Method localMethod = arrayOfMethod[j];
        if (!localMethod.getName().equals("addCallbackBuffer"))
          break label81;
        this.addBufferMethod = localMethod;
      }
      try
      {
        this.addBufferMethod.invoke(this.camera, new Object[] { paramArrayOfByte });
        return;
        label81: j++;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
  }

  public void autoFocus(Camera.AutoFocusCallback paramAutoFocusCallback)
  {
    this.camera.autoFocus(paramAutoFocusCallback);
  }

  public void cancelAutoFocus()
  {
    this.camera.cancelAutoFocus();
  }

  public ParametersProxy getParameters()
  {
    return new RealParameters(this.camera.getParameters());
  }

  public void release()
  {
    this.camera.release();
  }

  public void setDisplayOrientation(int paramInt)
  {
    this.camera.setDisplayOrientation(paramInt);
  }

  public void setOneShotPreviewCallback(Camera.PreviewCallback paramPreviewCallback)
  {
    this.camera.setOneShotPreviewCallback(paramPreviewCallback);
  }

  public void setParameters(ParametersProxy paramParametersProxy)
  {
    this.camera.setParameters(paramParametersProxy.getActualParameters());
  }

  public void setPreviewCallback(Camera.PreviewCallback paramPreviewCallback)
  {
    this.camera.setPreviewCallback(paramPreviewCallback);
  }

  public void setPreviewCallbackWithBuffer(Camera.PreviewCallback paramPreviewCallback)
  {
    Method[] arrayOfMethod;
    int i;
    int j;
    if (this.setCallbackWithBufferMethod == null)
    {
      arrayOfMethod = this.camera.getClass().getDeclaredMethods();
      i = arrayOfMethod.length;
      j = 0;
    }
    while (true)
    {
      if (j < i)
      {
        Method localMethod = arrayOfMethod[j];
        if (!localMethod.getName().equals("setPreviewCallbackWithBuffer"))
          break label81;
        this.setCallbackWithBufferMethod = localMethod;
      }
      try
      {
        this.setCallbackWithBufferMethod.invoke(this.camera, new Object[] { paramPreviewCallback });
        return;
        label81: j++;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        localIllegalArgumentException.printStackTrace();
        return;
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        localIllegalAccessException.printStackTrace();
        return;
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        localInvocationTargetException.printStackTrace();
      }
    }
  }

  public void setPreviewDisplay(SurfaceHolder paramSurfaceHolder)
    throws IOException
  {
    this.camera.setPreviewDisplay(paramSurfaceHolder);
  }

  public void setPreviewTexture(PlatformVersionUtils.TextureView paramTextureView)
  {
    PlatformVersionUtils.setPreviewTexture(this.camera, paramTextureView);
  }

  public void setZoomChangeListener(Camera.OnZoomChangeListener paramOnZoomChangeListener)
  {
    this.camera.setZoomChangeListener(paramOnZoomChangeListener);
  }

  public void startPreview()
  {
    this.camera.startPreview();
  }

  public void startSmoothZoom(int paramInt)
  {
    this.camera.startSmoothZoom(paramInt);
  }

  public void stopPreview()
  {
    this.camera.stopPreview();
  }

  public void takePicture(Camera.ShutterCallback paramShutterCallback, Camera.PictureCallback paramPictureCallback1, Camera.PictureCallback paramPictureCallback2)
  {
    this.camera.takePicture(paramShutterCallback, paramPictureCallback1, paramPictureCallback2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.RealCamera
 * JD-Core Version:    0.6.2
 */