package com.google.android.apps.unveil;

import android.app.Application;
import android.content.SharedPreferences;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.proxies.camera.RealCamera;
import java.io.Serializable;

public class UnveilSettings
  implements Serializable
{
  public static final String SETTINGS_EXTRA = "UnveilSettings";
  private static final UnveilLogger logger = new UnveilLogger();
  private static final long serialVersionUID = 3067856456417963036L;
  public final String cameraProxy;
  public final boolean groundtruth;
  public final Double latitude;
  public final Double longitude;
  public final int previousVersion;
  public final boolean useGLES2Overlay;
  public final boolean useLocalBarcode;
  public final boolean usePreviewFrame;

  public UnveilSettings(int paramInt, String paramString, Double paramDouble1, Double paramDouble2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    logger.v("Creating launcher-based preferences.", new Object[0]);
    this.previousVersion = paramInt;
    this.cameraProxy = paramString;
    this.latitude = paramDouble1;
    this.longitude = paramDouble2;
    this.useGLES2Overlay = paramBoolean1;
    this.useLocalBarcode = paramBoolean2;
    this.usePreviewFrame = paramBoolean3;
    this.groundtruth = paramBoolean4;
  }

  public UnveilSettings(Application paramApplication, SharedPreferences paramSharedPreferences)
  {
    logger.v("Creating default preferences.", new Object[0]);
    try
    {
      int j = Integer.parseInt(paramSharedPreferences.getString(paramApplication.getString(R.string.previous_version_key), "0"));
      i = j;
      this.previousVersion = i;
      this.cameraProxy = RealCamera.class.getSimpleName();
      this.latitude = null;
      this.longitude = null;
      this.useGLES2Overlay = false;
      this.useLocalBarcode = true;
      this.usePreviewFrame = true;
      this.groundtruth = false;
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        logger.e("Invalid previous_version value in preferences.", new Object[0]);
        int i = 0;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.UnveilSettings
 * JD-Core Version:    0.6.2
 */