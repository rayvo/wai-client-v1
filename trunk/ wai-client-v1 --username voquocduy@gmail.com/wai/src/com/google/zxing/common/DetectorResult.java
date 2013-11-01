package com.google.zxing.common;

import com.google.zxing.ResultPoint;

public final class DetectorResult
{
  private final BitMatrix bits;
  private final ResultPoint[] points;

  public DetectorResult(BitMatrix paramBitMatrix, ResultPoint[] paramArrayOfResultPoint)
  {
    this.bits = paramBitMatrix;
    this.points = paramArrayOfResultPoint;
  }

  public BitMatrix getBits()
  {
    return this.bits;
  }

  public ResultPoint[] getPoints()
  {
    return this.points;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.DetectorResult
 * JD-Core Version:    0.6.2
 */