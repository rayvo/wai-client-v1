package com.google.zxing.common.reedsolomon;

final class GF256Poly
{
  private final int[] coefficients;
  private final GF256 field;

  GF256Poly(GF256 paramGF256, int[] paramArrayOfInt)
  {
    if ((paramArrayOfInt == null) || (paramArrayOfInt.length == 0))
      throw new IllegalArgumentException();
    this.field = paramGF256;
    int i = paramArrayOfInt.length;
    if ((i > 1) && (paramArrayOfInt[0] == 0))
    {
      for (int j = 1; (j < i) && (paramArrayOfInt[j] == 0); j++);
      if (j == i)
      {
        this.coefficients = paramGF256.getZero().coefficients;
        return;
      }
      this.coefficients = new int[i - j];
      System.arraycopy(paramArrayOfInt, j, this.coefficients, 0, this.coefficients.length);
      return;
    }
    this.coefficients = paramArrayOfInt;
  }

  GF256Poly addOrSubtract(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if (isZero())
      return paramGF256Poly;
    if (paramGF256Poly.isZero())
      return this;
    Object localObject1 = this.coefficients;
    Object localObject2 = paramGF256Poly.coefficients;
    if (localObject1.length > localObject2.length)
    {
      Object localObject3 = localObject1;
      localObject1 = localObject2;
      localObject2 = localObject3;
    }
    int[] arrayOfInt = new int[localObject2.length];
    int i = localObject2.length - localObject1.length;
    System.arraycopy(localObject2, 0, arrayOfInt, 0, i);
    for (int j = i; j < localObject2.length; j++)
      arrayOfInt[j] = GF256.addOrSubtract(localObject1[(j - i)], localObject2[j]);
    return new GF256Poly(this.field, arrayOfInt);
  }

  GF256Poly[] divide(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if (paramGF256Poly.isZero())
      throw new IllegalArgumentException("Divide by 0");
    GF256Poly localGF256Poly1 = this.field.getZero();
    GF256Poly localGF256Poly2 = this;
    int i = paramGF256Poly.getCoefficient(paramGF256Poly.getDegree());
    int j = this.field.inverse(i);
    while ((localGF256Poly2.getDegree() >= paramGF256Poly.getDegree()) && (!localGF256Poly2.isZero()))
    {
      int k = localGF256Poly2.getDegree() - paramGF256Poly.getDegree();
      int m = this.field.multiply(localGF256Poly2.getCoefficient(localGF256Poly2.getDegree()), j);
      GF256Poly localGF256Poly3 = paramGF256Poly.multiplyByMonomial(k, m);
      localGF256Poly1 = localGF256Poly1.addOrSubtract(this.field.buildMonomial(k, m));
      localGF256Poly2 = localGF256Poly2.addOrSubtract(localGF256Poly3);
    }
    return new GF256Poly[] { localGF256Poly1, localGF256Poly2 };
  }

  int evaluateAt(int paramInt)
  {
    int j;
    if (paramInt == 0)
      j = getCoefficient(0);
    while (true)
    {
      return j;
      int i = this.coefficients.length;
      if (paramInt == 1)
      {
        j = 0;
        for (int m = 0; m < i; m++)
          j = GF256.addOrSubtract(j, this.coefficients[m]);
      }
      else
      {
        j = this.coefficients[0];
        for (int k = 1; k < i; k++)
          j = GF256.addOrSubtract(this.field.multiply(paramInt, j), this.coefficients[k]);
      }
    }
  }

  int getCoefficient(int paramInt)
  {
    return this.coefficients[(-1 + this.coefficients.length - paramInt)];
  }

  int[] getCoefficients()
  {
    return this.coefficients;
  }

  int getDegree()
  {
    return -1 + this.coefficients.length;
  }

  boolean isZero()
  {
    int i = this.coefficients[0];
    boolean bool = false;
    if (i == 0)
      bool = true;
    return bool;
  }

  GF256Poly multiply(int paramInt)
  {
    if (paramInt == 0)
      this = this.field.getZero();
    while (paramInt == 1)
      return this;
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt);
    return new GF256Poly(this.field, arrayOfInt);
  }

  GF256Poly multiply(GF256Poly paramGF256Poly)
  {
    if (!this.field.equals(paramGF256Poly.field))
      throw new IllegalArgumentException("GF256Polys do not have same GF256 field");
    if ((isZero()) || (paramGF256Poly.isZero()))
      return this.field.getZero();
    int[] arrayOfInt1 = this.coefficients;
    int i = arrayOfInt1.length;
    int[] arrayOfInt2 = paramGF256Poly.coefficients;
    int j = arrayOfInt2.length;
    int[] arrayOfInt3 = new int[-1 + (i + j)];
    for (int k = 0; k < i; k++)
    {
      int m = arrayOfInt1[k];
      for (int n = 0; n < j; n++)
        arrayOfInt3[(k + n)] = GF256.addOrSubtract(arrayOfInt3[(k + n)], this.field.multiply(m, arrayOfInt2[n]));
    }
    return new GF256Poly(this.field, arrayOfInt3);
  }

  GF256Poly multiplyByMonomial(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      throw new IllegalArgumentException();
    if (paramInt2 == 0)
      return this.field.getZero();
    int i = this.coefficients.length;
    int[] arrayOfInt = new int[i + paramInt1];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = this.field.multiply(this.coefficients[j], paramInt2);
    return new GF256Poly(this.field, arrayOfInt);
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(8 * getDegree());
    int i = getDegree();
    if (i >= 0)
    {
      int j = getCoefficient(i);
      label48: int k;
      if (j != 0)
      {
        if (j >= 0)
          break label101;
        localStringBuffer.append(" - ");
        j = -j;
        if ((i == 0) || (j != 1))
        {
          k = this.field.log(j);
          if (k != 0)
            break label118;
          localStringBuffer.append('1');
        }
        label79: if (i != 0)
        {
          if (i != 1)
            break label151;
          localStringBuffer.append('x');
        }
      }
      while (true)
      {
        i--;
        break;
        label101: if (localStringBuffer.length() <= 0)
          break label48;
        localStringBuffer.append(" + ");
        break label48;
        label118: if (k == 1)
        {
          localStringBuffer.append('a');
          break label79;
        }
        localStringBuffer.append("a^");
        localStringBuffer.append(k);
        break label79;
        label151: localStringBuffer.append("x^");
        localStringBuffer.append(i);
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.reedsolomon.GF256Poly
 * JD-Core Version:    0.6.2
 */