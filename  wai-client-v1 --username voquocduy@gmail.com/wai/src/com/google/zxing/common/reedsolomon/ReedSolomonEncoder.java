package com.google.zxing.common.reedsolomon;

import java.util.Vector;

public final class ReedSolomonEncoder
{
  private final Vector cachedGenerators;
  private final GF256 field;

  public ReedSolomonEncoder(GF256 paramGF256)
  {
    if (!GF256.QR_CODE_FIELD.equals(paramGF256))
      throw new IllegalArgumentException("Only QR Code is supported at this time");
    this.field = paramGF256;
    this.cachedGenerators = new Vector();
    this.cachedGenerators.addElement(new GF256Poly(paramGF256, new int[] { 1 }));
  }

  private GF256Poly buildGenerator(int paramInt)
  {
    if (paramInt >= this.cachedGenerators.size())
    {
      Object localObject = (GF256Poly)this.cachedGenerators.elementAt(-1 + this.cachedGenerators.size());
      for (int i = this.cachedGenerators.size(); i <= paramInt; i++)
      {
        GF256 localGF256 = this.field;
        int[] arrayOfInt = new int[2];
        arrayOfInt[0] = 1;
        arrayOfInt[1] = this.field.exp(i - 1);
        GF256Poly localGF256Poly = ((GF256Poly)localObject).multiply(new GF256Poly(localGF256, arrayOfInt));
        this.cachedGenerators.addElement(localGF256Poly);
        localObject = localGF256Poly;
      }
    }
    return (GF256Poly)this.cachedGenerators.elementAt(paramInt);
  }

  public void encode(int[] paramArrayOfInt, int paramInt)
  {
    if (paramInt == 0)
      throw new IllegalArgumentException("No error correction bytes");
    int i = paramArrayOfInt.length - paramInt;
    if (i <= 0)
      throw new IllegalArgumentException("No data bytes provided");
    GF256Poly localGF256Poly = buildGenerator(paramInt);
    int[] arrayOfInt1 = new int[i];
    System.arraycopy(paramArrayOfInt, 0, arrayOfInt1, 0, i);
    int[] arrayOfInt2 = new GF256Poly(this.field, arrayOfInt1).multiplyByMonomial(paramInt, 1).divide(localGF256Poly)[1].getCoefficients();
    int j = paramInt - arrayOfInt2.length;
    for (int k = 0; k < j; k++)
      paramArrayOfInt[(i + k)] = 0;
    System.arraycopy(arrayOfInt2, 0, paramArrayOfInt, i + j, arrayOfInt2.length);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.ReedSolomonEncoder
 * JD-Core Version:    0.6.2
 */