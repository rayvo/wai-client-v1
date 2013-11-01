package com.google.android.apps.unveil.sensors.proxies.camera;

import android.hardware.Camera.Parameters;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.HashMap;
import java.util.List;

public class FakeParameters
  implements ParametersProxy
{
  private static final String DEFAULT_PICTURE_SIZES = "2592x1944,2048x1536,1600x1200,1024x768,512x384";
  private static final String DEFAULT_PREVIEW_SIZES = "1280x720,800x480,720x480,640x480,576x432,480x320,384x288,352x288,320x240,240x160,176x144";
  private final UnveilLogger logger = new UnveilLogger();
  private final HashMap<String, String> parameterMap = new HashMap();

  protected FakeParameters()
  {
    this.parameterMap.put("antibanding", "auto");
    this.parameterMap.put("antibanding-values", "off,50hz,60hz,auto");
    this.parameterMap.put("effect", "none");
    this.parameterMap.put("effect-values", "none,mono,negative,solarize,sepia,posterize,whiteboard,blackboard,aqua");
    this.parameterMap.put("flash-mode", "off");
    this.parameterMap.put("flash-mode-values", "off,auto,on");
    this.parameterMap.put("focus-mode", "auto");
    this.parameterMap.put("focus-mode-values", "auto,infinity");
    this.parameterMap.put("jpeg-quality", "100");
    this.parameterMap.put("jpeg-thumbnail-height", "384");
    this.parameterMap.put("jpeg-thumbnail-quality", "90");
    this.parameterMap.put("jpeg-thumbnail-width", "512");
    this.parameterMap.put("max-zoom", "5");
    this.parameterMap.put("picture-format", "jpeg");
    this.parameterMap.put("picture-format-values", "jpeg");
    this.parameterMap.put("picture-size", "2048x1536");
    this.parameterMap.put("picture-size-values", "2592x1944,2048x1536,1600x1200,1024x768,512x384");
    this.parameterMap.put("preview-format", "yuv420sp");
    this.parameterMap.put("preview-format-values", "yuv420sp");
    this.parameterMap.put("preview-frame-rate", "15");
    this.parameterMap.put("preview-size", "720x480");
    this.parameterMap.put("preview-size-values", "1280x720,800x480,720x480,640x480,576x432,480x320,384x288,352x288,320x240,240x160,176x144");
    this.parameterMap.put("whitebalance", "auto");
    this.parameterMap.put("whitebalance-values", "auto,incandescent,fluorescent,daylight,cloudy-daylight");
    this.parameterMap.put("zoom", "0");
    this.parameterMap.put("zoom-supported", "true");
  }

  protected FakeParameters(FakeParameters paramFakeParameters)
  {
    this.parameterMap.putAll(paramFakeParameters.parameterMap);
  }

  public String flatten()
  {
    this.logger.e("flatten() is unimplemented!", new Object[0]);
    return null;
  }

  public String get(String paramString)
  {
    return (String)this.parameterMap.get(paramString);
  }

  public Camera.Parameters getActualParameters()
  {
    this.logger.e("getActualParameters() is unimplemented!", new Object[0]);
    return null;
  }

  public int getInt(String paramString)
  {
    return Integer.parseInt((String)this.parameterMap.get(paramString));
  }

  public int getMaxZoom()
  {
    this.logger.e("getMaxZoom() is unimplemented!", new Object[0]);
    return 0;
  }

  public int getPictureFormat()
  {
    this.logger.e("getPictureFormat() is unimplemented!", new Object[0]);
    return 0;
  }

  public Size getPictureSize()
  {
    return Size.parseFromString((String)this.parameterMap.get("picture-size"));
  }

  public int getPreviewFormat()
  {
    this.logger.e("getPreviewFormat() is unimplemented!", new Object[0]);
    return 0;
  }

  public int getPreviewFrameRate()
  {
    return Integer.parseInt((String)this.parameterMap.get("preview-frame-rate"));
  }

  public Size getPreviewSize()
  {
    return Size.parseFromString((String)this.parameterMap.get("preview-size"));
  }

  public List<Size> getSupportedPictureSizes()
  {
    return Size.sizeStringToList((String)this.parameterMap.get("picture-size-values"));
  }

  public List<Size> getSupportedPreviewSizes()
  {
    return Size.sizeStringToList((String)this.parameterMap.get("preview-size-values"));
  }

  public boolean isSmoothZoomSupported()
  {
    return false;
  }

  public void remove(String paramString)
  {
    this.parameterMap.remove(paramString);
  }

  public void set(String paramString, int paramInt)
  {
    this.parameterMap.put(paramString, "" + paramInt);
  }

  public void set(String paramString1, String paramString2)
  {
    this.parameterMap.put(paramString1, paramString2);
  }

  public void setPictureFormat(int paramInt)
  {
    this.logger.e("setPictureFormat() is unimplemented!", new Object[0]);
  }

  public void setPictureSize(int paramInt1, int paramInt2)
  {
    this.parameterMap.put("picture-size", new Size(paramInt1, paramInt2).toString());
  }

  public void setPreviewFormat(int paramInt)
  {
    this.logger.e("setPreviewFormat() is unimplemented!", new Object[0]);
  }

  public void setPreviewFrameRate(int paramInt)
  {
    this.parameterMap.put("preview-frame-rate", "" + paramInt);
  }

  public void setPreviewSize(int paramInt1, int paramInt2)
  {
    this.parameterMap.put("preview-size", paramInt1 + "x" + paramInt2);
  }

  public void unflatten(String paramString)
  {
    this.logger.e("unflatten() is unimplemented!", new Object[0]);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.FakeParameters
 * JD-Core Version:    0.6.2
 */