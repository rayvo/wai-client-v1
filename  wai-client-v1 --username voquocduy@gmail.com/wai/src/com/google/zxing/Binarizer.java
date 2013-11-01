package com.google.zxing;

import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

public abstract class Binarizer
{
  private final LuminanceSource source;

  protected Binarizer(LuminanceSource paramLuminanceSource)
  {
    if (paramLuminanceSource == null)
      throw new IllegalArgumentException("Source must be non-null.");
    this.source = paramLuminanceSource;
  }

  public abstract Binarizer createBinarizer(LuminanceSource paramLuminanceSource);

  public abstract BitMatrix getBlackMatrix()
    throws NotFoundException;

  public abstract BitArray getBlackRow(int paramInt, BitArray paramBitArray)
    throws NotFoundException;

  public BitArray getBlackRowLocal(int paramInt, BitArray paramBitArray)
    throws NotFoundException
  {
    throw NotFoundException.getNotFoundInstance();
  }

  public LuminanceSource getLuminanceSource()
  {
    return this.source;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.Binarizer
 * JD-Core Version:    0.6.2
 */