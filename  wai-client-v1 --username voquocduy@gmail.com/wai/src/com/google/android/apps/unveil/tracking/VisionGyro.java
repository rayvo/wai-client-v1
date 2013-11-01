package com.google.android.apps.unveil.tracking;

import com.google.android.apps.unveil.env.ResourceUtils;

public class VisionGyro
{
  private int nativeVisionGyro;

  static
  {
    ResourceUtils.loadNativeLibrary("clientvision");
  }

  public VisionGyro()
  {
    constructNative();
  }

  protected native void constructNative();

  public void destroy()
  {
    try
    {
      destroyNative();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected native void destroyNative();

  public float[] getLastTransform(boolean paramBoolean)
  {
    try
    {
      float[] arrayOfFloat = new float[9];
      obtainVgTransformNative(arrayOfFloat, paramBoolean);
      return arrayOfFloat;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public float[] nextFrame(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      nextFrameNative(paramArrayOfByte, paramInt1, paramInt2);
      float[] arrayOfFloat = new float[9];
      obtainVgTransformNative(arrayOfFloat, false);
      return arrayOfFloat;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected native void nextFrameNative(byte[] paramArrayOfByte, int paramInt1, int paramInt2);

  protected native void obtainVgTransformNative(float[] paramArrayOfFloat, boolean paramBoolean);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.VisionGyro
 * JD-Core Version:    0.6.2
 */