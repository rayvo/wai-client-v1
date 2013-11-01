package com.google.android.apps.unveil.env;

import android.util.FloatMath;

public abstract class NumberUtils
{
  public static final String format(float paramFloat, int paramInt)
  {
    if (paramInt == 0)
      return "" + (int)paramFloat;
    int i = 10;
    for (int j = 1; j < paramInt; j++)
      i *= 10;
    int k = (int)(paramFloat * i);
    return k / i + "." + Math.abs(k) % i;
  }

  public static final float getMeanIndex(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = 0;
    for (int k = 0; k < paramArrayOfInt.length; k++)
    {
      j += k * paramArrayOfInt[k];
      i += paramArrayOfInt[k];
    }
    return j / i;
  }

  public static final int getMedianIndex(int[] paramArrayOfInt)
  {
    int i = 0;
    int j = paramArrayOfInt.length;
    for (int k = 0; k < j; k++)
      i += paramArrayOfInt[k];
    int m = i / 2;
    int n = 0;
    for (int i1 = 0; i1 < paramArrayOfInt.length; i1++)
    {
      n += paramArrayOfInt[i1];
      if (n >= m)
        return i1;
    }
    return -1;
  }

  public static final float getNormalizedStdDev(int[] paramArrayOfInt)
  {
    int i = 0;
    long l = 0L;
    int j = paramArrayOfInt.length;
    for (int k = 0; k < j; k++)
    {
      int m = paramArrayOfInt[k];
      i += m;
      l += m * m;
    }
    float f = i / paramArrayOfInt.length;
    return FloatMath.sqrt(((float)l - f * i) / paramArrayOfInt.length / (i * i));
  }

  public static float normalizeDegrees(float paramFloat)
  {
    float f = paramFloat - 360 * (int)(paramFloat / 360.0F);
    if (f > 180.0F)
      f -= 360.0F;
    while (f > -180.0F)
      return f;
    return f + 360.0F;
  }

  public static float radiansToNormalizedDegrees(float paramFloat)
  {
    return normalizeDegrees((float)Math.toDegrees(paramFloat));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.NumberUtils
 * JD-Core Version:    0.6.2
 */