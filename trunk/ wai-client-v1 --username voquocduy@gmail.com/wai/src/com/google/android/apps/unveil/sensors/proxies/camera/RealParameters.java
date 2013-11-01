package com.google.android.apps.unveil.sensors.proxies.camera;

import android.hardware.Camera.Parameters;
import android.hardware.Camera.Size;
import com.google.android.apps.unveil.env.Size;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RealParameters
  implements ParametersProxy
{
  private final Camera.Parameters actualParameters;

  public RealParameters(Camera.Parameters paramParameters)
  {
    this.actualParameters = paramParameters;
  }

  private List<Size> toSizeList(List<Camera.Size> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Camera.Size localSize = (Camera.Size)localIterator.next();
      localArrayList.add(new Size(localSize.width, localSize.height));
    }
    return localArrayList;
  }

  public String flatten()
  {
    return this.actualParameters.flatten();
  }

  public String get(String paramString)
  {
    return this.actualParameters.get(paramString);
  }

  public Camera.Parameters getActualParameters()
  {
    return this.actualParameters;
  }

  public int getInt(String paramString)
  {
    return this.actualParameters.getInt(paramString);
  }

  public int getMaxZoom()
  {
    return this.actualParameters.getMaxZoom();
  }

  public int getPictureFormat()
  {
    return this.actualParameters.getPictureFormat();
  }

  public Size getPictureSize()
  {
    return new Size(this.actualParameters.getPictureSize());
  }

  public int getPreviewFormat()
  {
    return this.actualParameters.getPreviewFormat();
  }

  public int getPreviewFrameRate()
  {
    return this.actualParameters.getPreviewFrameRate();
  }

  public Size getPreviewSize()
  {
    return new Size(this.actualParameters.getPreviewSize());
  }

  public List<Size> getSupportedPictureSizes()
  {
    return toSizeList(this.actualParameters.getSupportedPictureSizes());
  }

  public List<Size> getSupportedPreviewSizes()
  {
    return toSizeList(this.actualParameters.getSupportedPreviewSizes());
  }

  public boolean isSmoothZoomSupported()
  {
    return this.actualParameters.isSmoothZoomSupported();
  }

  public void remove(String paramString)
  {
    this.actualParameters.remove(paramString);
  }

  public void set(String paramString, int paramInt)
  {
    this.actualParameters.set(paramString, paramInt);
  }

  public void set(String paramString1, String paramString2)
  {
    this.actualParameters.set(paramString1, paramString2);
  }

  public void setPictureFormat(int paramInt)
  {
    this.actualParameters.setPictureFormat(paramInt);
  }

  public void setPictureSize(int paramInt1, int paramInt2)
  {
    this.actualParameters.setPictureSize(paramInt1, paramInt2);
  }

  public void setPreviewFormat(int paramInt)
  {
    this.actualParameters.setPreviewFormat(paramInt);
  }

  public void setPreviewFrameRate(int paramInt)
  {
    this.actualParameters.setPreviewFrameRate(paramInt);
  }

  public void setPreviewSize(int paramInt1, int paramInt2)
  {
    this.actualParameters.setPreviewSize(paramInt1, paramInt2);
  }

  public void unflatten(String paramString)
  {
    this.actualParameters.unflatten(paramString);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.RealParameters
 * JD-Core Version:    0.6.2
 */