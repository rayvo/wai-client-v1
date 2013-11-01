package com.google.zxing.common;

import java.util.Arrays;

public final class BitMatrix
{
  public final int[] bits;
  public final int height;
  public final int rowSize;
  public final int width;

  public BitMatrix(int paramInt)
  {
    this(paramInt, paramInt);
  }

  public BitMatrix(int paramInt1, int paramInt2)
  {
    if ((paramInt1 < 1) || (paramInt2 < 1))
      throw new IllegalArgumentException("Both dimensions must be greater than 0");
    this.width = paramInt1;
    this.height = paramInt2;
    this.rowSize = (paramInt1 + 31 >> 5);
    this.bits = new int[paramInt2 * this.rowSize];
  }

  public BitMatrix(int paramInt1, int paramInt2, int[] paramArrayOfInt)
  {
    if ((paramInt1 < 1) || (paramInt2 < 1))
      throw new IllegalArgumentException("Both dimensions must be greater than 0");
    this.width = paramInt1;
    this.height = paramInt2;
    this.rowSize = (paramInt1 + 31 >> 5);
    if (paramArrayOfInt.length < paramInt2 * this.rowSize)
      throw new IllegalArgumentException("Bits buffer too small");
    this.bits = paramArrayOfInt;
  }

  public void clear()
  {
    Arrays.fill(this.bits, 0);
  }

  public void flip(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[i] ^= 1 << (paramInt1 & 0x1F);
  }

  public boolean get(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    return (0x1 & this.bits[i] >>> (paramInt1 & 0x1F)) != 0;
  }

  public BitArray getColumn(int paramInt, BitArray paramBitArray)
  {
    if ((paramBitArray == null) || (BitArray.size < this.height))
      paramBitArray = new BitArray(this.height);
    nativeGetColumn(this.bits, this.width, this.height, paramInt, BitArray.bits);
    return paramBitArray;
  }

  public int getDimension()
  {
    if (this.width != this.height)
      throw new RuntimeException("Can't call getDimension() on a non-square matrix");
    return this.width;
  }

  public int getHeight()
  {
    return this.height;
  }

  public BitArray getRow(int paramInt, BitArray paramBitArray)
  {
    if ((paramBitArray == null) || (BitArray.size < this.width))
      paramBitArray = new BitArray(this.width);
    nativeGetRow(this.bits, this.width, this.height, paramInt, BitArray.bits);
    return paramBitArray;
  }

  public int getWidth()
  {
    return this.width;
  }

  native void nativeGetColumn(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, boolean[] paramArrayOfBoolean);

  native void nativeGetRow(int[] paramArrayOfInt, int paramInt1, int paramInt2, int paramInt3, boolean[] paramArrayOfBoolean);

  public void set(int paramInt1, int paramInt2)
  {
    int i = paramInt2 * this.rowSize + (paramInt1 >> 5);
    int[] arrayOfInt = this.bits;
    arrayOfInt[i] |= 1 << (paramInt1 & 0x1F);
  }

  public void setRegion(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((paramInt2 < 0) || (paramInt1 < 0))
      throw new IllegalArgumentException("Left and top must be nonnegative");
    if ((paramInt4 < 1) || (paramInt3 < 1))
      throw new IllegalArgumentException("Height and width must be at least 1");
    int i = paramInt1 + paramInt3;
    int j = paramInt2 + paramInt4;
    if ((j > this.height) || (i > this.width))
      throw new IllegalArgumentException("The region must fit inside the matrix");
    for (int k = paramInt2; k < j; k++)
    {
      int m = k * this.rowSize;
      for (int n = paramInt1; n < i; n++)
      {
        int[] arrayOfInt = this.bits;
        int i1 = m + (n >> 5);
        arrayOfInt[i1] |= 1 << (n & 0x1F);
      }
    }
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(this.height * (1 + this.width));
    for (int i = 0; i < this.height; i++)
    {
      int j = 0;
      if (j < this.width)
      {
        if (get(j, i));
        for (String str = "X "; ; str = "  ")
        {
          localStringBuffer.append(str);
          j++;
          break;
        }
      }
      localStringBuffer.append('\n');
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitMatrix
 * JD-Core Version:    0.6.2
 */