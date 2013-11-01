package com.google.zxing.common.reedsolomon;

public final class ReedSolomonDecoder
{
  private final GF256 field;

  public ReedSolomonDecoder(GF256 paramGF256)
  {
    this.field = paramGF256;
  }

  private int[] findErrorLocations(GF256Poly paramGF256Poly)
    throws ReedSolomonException
  {
    int i = paramGF256Poly.getDegree();
    int[] arrayOfInt;
    if (i == 1)
    {
      arrayOfInt = new int[1];
      arrayOfInt[0] = paramGF256Poly.getCoefficient(1);
    }
    int j;
    do
    {
      return arrayOfInt;
      arrayOfInt = new int[i];
      j = 0;
      for (int k = 1; (k < 256) && (j < i); k++)
        if (paramGF256Poly.evaluateAt(k) == 0)
        {
          arrayOfInt[j] = this.field.inverse(k);
          j++;
        }
    }
    while (j == i);
    throw new ReedSolomonException("Error locator degree does not match number of roots");
  }

  private int[] findErrorMagnitudes(GF256Poly paramGF256Poly, int[] paramArrayOfInt, boolean paramBoolean)
  {
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
    {
      int k = this.field.inverse(paramArrayOfInt[j]);
      int m = 1;
      int n = 0;
      if (n < i)
      {
        int i1;
        if (j != n)
        {
          i1 = this.field.multiply(paramArrayOfInt[n], k);
          if ((i1 & 0x1) != 0)
            break label100;
        }
        label100: for (int i2 = i1 | 0x1; ; i2 = i1 & 0xFFFFFFFE)
        {
          m = this.field.multiply(m, i2);
          n++;
          break;
        }
      }
      arrayOfInt[j] = this.field.multiply(paramGF256Poly.evaluateAt(k), this.field.inverse(m));
      if (paramBoolean)
        arrayOfInt[j] = this.field.multiply(arrayOfInt[j], k);
    }
    return arrayOfInt;
  }

  private GF256Poly[] runEuclideanAlgorithm(GF256Poly paramGF256Poly1, GF256Poly paramGF256Poly2, int paramInt)
    throws ReedSolomonException
  {
    if (paramGF256Poly1.getDegree() < paramGF256Poly2.getDegree())
    {
      GF256Poly localGF256Poly4 = paramGF256Poly1;
      paramGF256Poly1 = paramGF256Poly2;
      paramGF256Poly2 = localGF256Poly4;
    }
    Object localObject1 = paramGF256Poly1;
    Object localObject2 = paramGF256Poly2;
    Object localObject3 = this.field.getOne();
    GF256Poly localGF256Poly1 = this.field.getZero();
    Object localObject4 = this.field.getZero();
    Object localObject7;
    GF256Poly localGF256Poly3;
    for (GF256Poly localGF256Poly2 = this.field.getOne(); ((GF256Poly)localObject2).getDegree() >= paramInt / 2; localGF256Poly2 = localGF256Poly3.multiply((GF256Poly)localObject4).addOrSubtract(localObject7))
    {
      Object localObject5 = localObject1;
      Object localObject6 = localObject3;
      localObject7 = localObject4;
      localObject1 = localObject2;
      localObject3 = localGF256Poly1;
      localObject4 = localGF256Poly2;
      if (((GF256Poly)localObject1).isZero())
        throw new ReedSolomonException("r_{i-1} was zero");
      localObject2 = localObject5;
      localGF256Poly3 = this.field.getZero();
      int k = ((GF256Poly)localObject1).getCoefficient(((GF256Poly)localObject1).getDegree());
      int m = this.field.inverse(k);
      while ((((GF256Poly)localObject2).getDegree() >= ((GF256Poly)localObject1).getDegree()) && (!((GF256Poly)localObject2).isZero()))
      {
        int n = ((GF256Poly)localObject2).getDegree() - ((GF256Poly)localObject1).getDegree();
        int i1 = this.field.multiply(((GF256Poly)localObject2).getCoefficient(((GF256Poly)localObject2).getDegree()), m);
        localGF256Poly3 = localGF256Poly3.addOrSubtract(this.field.buildMonomial(n, i1));
        localObject2 = ((GF256Poly)localObject2).addOrSubtract(((GF256Poly)localObject1).multiplyByMonomial(n, i1));
      }
      localGF256Poly1 = localGF256Poly3.multiply((GF256Poly)localObject3).addOrSubtract(localObject6);
    }
    int i = localGF256Poly2.getCoefficient(0);
    if (i == 0)
      throw new ReedSolomonException("sigmaTilde(0) was zero");
    int j = this.field.inverse(i);
    return new GF256Poly[] { localGF256Poly2.multiply(j), ((GF256Poly)localObject2).multiply(j) };
  }

  public void decode(int[] paramArrayOfInt, int paramInt)
    throws ReedSolomonException
  {
    GF256Poly localGF256Poly1 = new GF256Poly(this.field, paramArrayOfInt);
    int[] arrayOfInt1 = new int[paramInt];
    boolean bool = this.field.equals(GF256.DATA_MATRIX_FIELD);
    int i = 1;
    int j = 0;
    if (j < paramInt)
    {
      GF256 localGF256 = this.field;
      if (bool);
      for (int n = j + 1; ; n = j)
      {
        int i1 = localGF256Poly1.evaluateAt(localGF256.exp(n));
        arrayOfInt1[(-1 + arrayOfInt1.length - j)] = i1;
        if (i1 != 0)
          i = 0;
        j++;
        break;
      }
    }
    if (i != 0);
    while (true)
    {
      return;
      GF256Poly localGF256Poly2 = new GF256Poly(this.field, arrayOfInt1);
      GF256Poly[] arrayOfGF256Poly = runEuclideanAlgorithm(this.field.buildMonomial(paramInt, 1), localGF256Poly2, paramInt);
      GF256Poly localGF256Poly3 = arrayOfGF256Poly[0];
      GF256Poly localGF256Poly4 = arrayOfGF256Poly[1];
      int[] arrayOfInt2 = findErrorLocations(localGF256Poly3);
      int[] arrayOfInt3 = findErrorMagnitudes(localGF256Poly4, arrayOfInt2, bool);
      for (int k = 0; k < arrayOfInt2.length; k++)
      {
        int m = -1 + paramArrayOfInt.length - this.field.log(arrayOfInt2[k]);
        if (m < 0)
          throw new ReedSolomonException("Bad error location");
        paramArrayOfInt[m] = GF256.addOrSubtract(paramArrayOfInt[m], arrayOfInt3[k]);
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.ReedSolomonDecoder
 * JD-Core Version:    0.6.2
 */