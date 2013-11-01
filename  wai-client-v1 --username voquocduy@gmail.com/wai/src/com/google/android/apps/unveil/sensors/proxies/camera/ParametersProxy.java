package com.google.android.apps.unveil.sensors.proxies.camera;

import android.hardware.Camera.Parameters;
import com.google.android.apps.unveil.env.Size;
import java.util.List;

public abstract interface ParametersProxy
{
  public static final String FLASH_MODE_KEY = "flash-mode";
  public static final String FLASH_MODE_VALUES_KEY = "flash-mode-values";
  public static final String FOCUS_MODE_KEY = "focus-mode";
  public static final String FOCUS_MODE_VALUES_KEY = "focus-mode-values";
  public static final String JPEG_QUALITY_KEY = "jpeg-quality";
  public static final String PICTURE_SIZE_KEY = "picture-size";
  public static final String PICTURE_SIZE_VALUES_KEY = "picture-size-values";
  public static final String PREVIEW_FRAME_RATE_KEY = "preview-frame-rate";
  public static final String PREVIEW_SIZE_KEY = "preview-size";
  public static final String PREVIEW_SIZE_VALUES_KEY = "preview-size-values";

  public abstract String flatten();

  public abstract String get(String paramString);

  public abstract Camera.Parameters getActualParameters();

  public abstract int getInt(String paramString);

  public abstract int getMaxZoom();

  public abstract int getPictureFormat();

  public abstract Size getPictureSize();

  public abstract int getPreviewFormat();

  public abstract int getPreviewFrameRate();

  public abstract Size getPreviewSize();

  public abstract List<Size> getSupportedPictureSizes();

  public abstract List<Size> getSupportedPreviewSizes();

  public abstract boolean isSmoothZoomSupported();

  public abstract void remove(String paramString);

  public abstract void set(String paramString, int paramInt);

  public abstract void set(String paramString1, String paramString2);

  public abstract void setPictureFormat(int paramInt);

  public abstract void setPictureSize(int paramInt1, int paramInt2);

  public abstract void setPreviewFormat(int paramInt);

  public abstract void setPreviewFrameRate(int paramInt);

  public abstract void setPreviewSize(int paramInt1, int paramInt2);

  public abstract void unflatten(String paramString);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.ParametersProxy
 * JD-Core Version:    0.6.2
 */