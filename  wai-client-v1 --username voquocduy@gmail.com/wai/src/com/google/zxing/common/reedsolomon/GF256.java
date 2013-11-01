package com.google.zxing.common.reedsolomon;

public final class GF256
{
  public static final GF256 DATA_MATRIX_FIELD = new GF256(301);
  public static final GF256 QR_CODE_FIELD = new GF256(285);
  private final int[] expTable = new int[256];
  private final int[] logTable = new int[256];
  private final GF256Poly one;
  private final GF256Poly zero;

  private GF256(int paramInt)
  {
    int i = 1;
    for (int j = 0; j < 256; j++)
    {
      this.expTable[j] = i;
      i <<= 1;
      if (i >= 256)
        i ^= paramInt;
    }
    for (int k = 0; k < 255; k++)
      this.logTable[this.expTable[k]] = k;
    this.zero = new GF256Poly(this, new int[] { 0 });
    this.one = new GF256Poly(this, new int[] { 1 });
  }

  static int addOrSubtract(int paramInt1, int paramInt2)
  {
    return paramInt1 ^ paramInt2;
  }

  GF256Poly buildMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.zero;
    int[] arrayOfInt = new int[paramInt1 + 1];
    arrayOfInt[0] = paramInt2;
    return new GF256Poly(this, arrayOfInt);
  }

  int exp(int paramInt)
  {
    return this.expTable[paramInt];
  }

  GF256Poly getOne()
  {
    return this.one;
  }

  GF256Poly getZero()
  {
    return this.zero;
  }

  int inverse(int paramInt)
  {
    if (paramInt == 0)
      throw new ArithmeticException();
    return this.expTable[(255 - this.logTable[paramInt])];
  }

  int log(int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException();
    return this.logTable[paramInt];
  }

  int multiply(int paramInt1, int paramInt2)
  {
    if ((paramInt1 == 0) || (paramInt2 == 0))
      return 0;
    int i = this.logTable[paramInt1] + this.logTable[paramInt2];
    return this.expTable[((i & 0xFF) + (i >>> 8))];
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.GF256
 * JD-Core Version:    0.6.2
 */