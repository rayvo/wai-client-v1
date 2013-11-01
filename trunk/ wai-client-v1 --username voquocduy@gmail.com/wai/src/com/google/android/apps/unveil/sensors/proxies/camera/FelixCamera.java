package com.google.android.apps.unveil.sensors.proxies.camera;

import android.content.res.Resources;
import android.hardware.Camera;
import android.os.Handler;
import java.util.Map;

public class FelixCamera extends RealCamera
{
  private static FelixCamera globalCamera;
  private boolean skipNextRelease = false;

  protected FelixCamera(Camera paramCamera)
  {
    super(paramCamera);
  }

  public static FelixCamera open(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    if (globalCamera != null)
      return globalCamera;
    Camera localCamera = Camera.open();
    if (localCamera == null)
      localCamera = Camera.open(0);
    globalCamera = new FelixCamera(localCamera);
    return globalCamera;
  }

  public static void skipNextRelease()
  {
    if (globalCamera != null)
      globalCamera.skipNextRelease = true;
  }

  public void release()
  {
    if (this.skipNextRelease)
    {
      this.skipNextRelease = false;
      return;
    }
    super.release();
    globalCamera = null;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.FelixCamera
 * JD-Core Version:    0.6.2
 */