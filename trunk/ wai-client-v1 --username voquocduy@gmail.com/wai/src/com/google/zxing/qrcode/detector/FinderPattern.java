package com.google.zxing.qrcode.detector;

import com.google.zxing.ResultPoint;

public final class FinderPattern extends ResultPoint
{
  private int count;
  private final float estimatedModuleSize;

  FinderPattern(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    super(paramFloat1, paramFloat2);
    this.estimatedModuleSize = paramFloat3;
    this.count = 1;
  }

  boolean aboutEquals(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    boolean bool1 = Math.abs(paramFloat2 - getY()) < paramFloat1;
    boolean bool2 = false;
    if (!bool1)
    {
      boolean bool3 = Math.abs(paramFloat3 - getX()) < paramFloat1;
      bool2 = false;
      if (!bool3)
      {
        float f = Math.abs(paramFloat1 - this.estimatedModuleSize);
        if (f > 1.0F)
        {
          boolean bool4 = f / this.estimatedModuleSize < 1.0F;
          bool2 = false;
          if (bool4);
        }
        else
        {
          bool2 = true;
        }
      }
    }
    return bool2;
  }

  int getCount()
  {
    return this.count;
  }

  public float getEstimatedModuleSize()
  {
    return this.estimatedModuleSize;
  }

  void incrementCount()
  {
    this.count = (1 + this.count);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPattern
 * JD-Core Version:    0.6.2
 */