package com.google.zxing.common;

import java.util.Arrays;

public final class BitArray
{
  public static boolean[] bits;
  public static int size;

  public BitArray(int paramInt)
  {
    if (paramInt < 1)
      throw new IllegalArgumentException("size must be at least 1");
    if ((bits == null) || (size != paramInt))
    {
      size = paramInt;
      bits = new boolean[paramInt];
    }
  }

  public static native void nativeReverse(boolean[] paramArrayOfBoolean);

  public final void clear()
  {
    Arrays.fill(bits, false);
  }

  public final void flip(int paramInt)
  {
    boolean[] arrayOfBoolean = bits;
    if (bits[paramInt] == 0);
    for (int i = 1; ; i = 0)
    {
      arrayOfBoolean[paramInt] = i;
      return;
    }
  }

  public final boolean get(int paramInt)
  {
    return bits[paramInt];
  }

  public final int getSize()
  {
    return size;
  }

  public final boolean isRange(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramInt2 < paramInt1)
      throw new IllegalArgumentException();
    if (paramInt2 == paramInt1);
    while (true)
    {
      return true;
      for (int i = paramInt1; i < paramInt2; i++)
        if (bits[i] != paramBoolean)
          return false;
    }
  }

  public final void reverse()
  {
    nativeReverse(bits);
  }

  public final void set(int paramInt)
  {
    bits[paramInt] = true;
  }

  public String toString()
  {
    StringBuffer localStringBuffer = new StringBuffer(size);
    int i = 0;
    if (i < size)
    {
      if ((i & 0x7) == 0)
        localStringBuffer.append(' ');
      if (get(i));
      for (char c = 'X'; ; c = '.')
      {
        localStringBuffer.append(c);
        i++;
        break;
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.BitArray
 * JD-Core Version:    0.6.2
 */