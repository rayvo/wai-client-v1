package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class Code39Reader extends OneDReader
{
  private static final char[] ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".toCharArray();
  static final String ALPHABET_STRING = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%";
  private static final int ASTERISK_ENCODING = CHARACTER_ENCODINGS[39];
  static final int[] CHARACTER_ENCODINGS = { 52, 289, 97, 352, 49, 304, 112, 37, 292, 100, 265, 73, 328, 25, 280, 88, 13, 268, 76, 28, 259, 67, 322, 19, 274, 82, 7, 262, 70, 22, 385, 193, 448, 145, 400, 208, 133, 388, 196, 148, 168, 162, 138, 42 };
  private final boolean extendedMode;
  private final boolean usingCheckDigit;

  public Code39Reader()
  {
    this.usingCheckDigit = false;
    this.extendedMode = false;
  }

  public Code39Reader(boolean paramBoolean)
  {
    this.usingCheckDigit = paramBoolean;
    this.extendedMode = false;
  }

  public Code39Reader(boolean paramBoolean1, boolean paramBoolean2)
  {
    this.usingCheckDigit = paramBoolean1;
    this.extendedMode = paramBoolean2;
  }

  private static String decodeExtended(StringBuffer paramStringBuffer)
    throws FormatException
  {
    int i = paramStringBuffer.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    int j = 0;
    if (j < i)
    {
      char c1 = paramStringBuffer.charAt(j);
      int k;
      char c2;
      if ((c1 == '+') || (c1 == '$') || (c1 == '%') || (c1 == '/'))
      {
        k = paramStringBuffer.charAt(j + 1);
        c2 = '\000';
        switch (c1)
        {
        default:
          label112: localStringBuffer.append(c2);
          j++;
        case '+':
        case '$':
        case '%':
        case '/':
        }
      }
      while (true)
      {
        j++;
        break;
        if ((k >= 65) && (k <= 90))
        {
          c2 = (char)(k + 32);
          break label112;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 90))
        {
          c2 = (char)(k - 64);
          break label112;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 69))
        {
          c2 = (char)(k - 38);
          break label112;
        }
        if ((k >= 70) && (k <= 87))
        {
          c2 = (char)(k - 11);
          break label112;
        }
        throw FormatException.getFormatInstance();
        if ((k >= 65) && (k <= 79))
        {
          c2 = (char)(k - 32);
          break label112;
        }
        if (k == 90)
        {
          c2 = ':';
          break label112;
        }
        throw FormatException.getFormatInstance();
        localStringBuffer.append(c1);
      }
    }
    return localStringBuffer.toString();
  }

  private static int[] findAsteriskPattern(BitArray paramBitArray)
    throws NotFoundException
  {
    int i = paramBitArray.getSize();
    int k;
    int[] arrayOfInt;
    int m;
    int i1;
    int i2;
    for (int j = 0; ; j++)
      if ((j >= i) || (paramBitArray.get(j)))
      {
        k = 0;
        arrayOfInt = new int[9];
        m = j;
        n = 0;
        i1 = arrayOfInt.length;
        for (i2 = j; ; i2++)
        {
          if (i2 >= i)
            break label228;
          if ((n ^ paramBitArray.get(i2)) == 0)
            break;
          arrayOfInt[k] = (1 + arrayOfInt[k]);
        }
      }
    if (k == i1 - 1)
    {
      if ((toNarrowWidePattern(arrayOfInt) == ASTERISK_ENCODING) && (paramBitArray.isRange(Math.max(0, m - (i2 - m) / 2), m, false)))
        return new int[] { m, i2 };
      m += arrayOfInt[0] + arrayOfInt[1];
      for (int i3 = 2; i3 < i1; i3++)
        arrayOfInt[(i3 - 2)] = arrayOfInt[i3];
      arrayOfInt[(i1 - 2)] = 0;
      arrayOfInt[(i1 - 1)] = 0;
      k--;
      label200: arrayOfInt[k] = 1;
      if (n != 0)
        break label222;
    }
    label222: for (int n = 1; ; n = 0)
    {
      break;
      k++;
      break label200;
    }
    label228: throw NotFoundException.getNotFoundInstance();
  }

  private static char patternToChar(int paramInt)
    throws NotFoundException
  {
    for (int i = 0; i < CHARACTER_ENCODINGS.length; i++)
      if (CHARACTER_ENCODINGS[i] == paramInt)
        return ALPHABET[i];
    throw NotFoundException.getNotFoundInstance();
  }

  private static int toNarrowWidePattern(int[] paramArrayOfInt)
  {
    int i = paramArrayOfInt.length;
    int j = 0;
    int n;
    do
    {
      int k = 2147483647;
      for (int m = 0; m < i; m++)
      {
        int i7 = paramArrayOfInt[m];
        if ((i7 < k) && (i7 > j))
          k = i7;
      }
      j = k;
      n = 0;
      int i1 = 0;
      int i2 = 0;
      for (int i3 = 0; i3 < i; i3++)
      {
        int i6 = paramArrayOfInt[i3];
        if (paramArrayOfInt[i3] > j)
        {
          i2 |= 1 << i - 1 - i3;
          n++;
          i1 += i6;
        }
      }
      if (n == 3)
        for (int i4 = 0; ; i4++)
          if ((i4 < i) && (n > 0))
          {
            int i5 = paramArrayOfInt[i4];
            if (paramArrayOfInt[i4] > j)
            {
              n--;
              if (i5 << 1 >= i1)
                i2 = -1;
            }
          }
          else
          {
            return i2;
          }
    }
    while (n > 3);
    return -1;
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    int[] arrayOfInt1 = findAsteriskPattern(paramBitArray);
    int i = arrayOfInt1[1];
    int j = paramBitArray.getSize();
    while ((i < j) && (!paramBitArray.get(i)))
      i++;
    StringBuffer localStringBuffer = new StringBuffer(20);
    int[] arrayOfInt2 = new int[9];
    char c;
    int m;
    do
    {
      recordPattern(paramBitArray, i, arrayOfInt2);
      int k = toNarrowWidePattern(arrayOfInt2);
      if (k < 0)
        throw NotFoundException.getNotFoundInstance();
      c = patternToChar(k);
      localStringBuffer.append(c);
      m = i;
      for (int n = 0; n < arrayOfInt2.length; n++)
        i += arrayOfInt2[n];
      while ((i < j) && (!paramBitArray.get(i)))
        i++;
    }
    while (c != '*');
    localStringBuffer.deleteCharAt(-1 + localStringBuffer.length());
    int i1 = 0;
    for (int i2 = 0; i2 < arrayOfInt2.length; i2++)
      i1 += arrayOfInt2[i2];
    int i3 = i - m - i1;
    if ((i != j) && (i3 / 2 < i1))
      throw NotFoundException.getNotFoundInstance();
    if (this.usingCheckDigit)
    {
      int i4 = -1 + localStringBuffer.length();
      int i5 = 0;
      for (int i6 = 0; i6 < i4; i6++)
        i5 += "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ-. *$/+%".indexOf(localStringBuffer.charAt(i6));
      if (localStringBuffer.charAt(i4) != ALPHABET[(i5 % 43)])
        throw ChecksumException.getChecksumInstance();
      localStringBuffer.deleteCharAt(i4);
    }
    if (localStringBuffer.length() == 0)
      throw NotFoundException.getNotFoundInstance();
    if (this.extendedMode);
    for (String str = decodeExtended(localStringBuffer); ; str = localStringBuffer.toString())
    {
      float f1 = arrayOfInt1[0];
      float f2 = m + i1;
      ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
      ResultPoint localResultPoint1 = new ResultPoint(f1, paramInt);
      arrayOfResultPoint[0] = localResultPoint1;
      ResultPoint localResultPoint2 = new ResultPoint(f2, paramInt);
      arrayOfResultPoint[1] = localResultPoint2;
      Result localResult = new Result(str, null, arrayOfResultPoint, BarcodeFormat.CODE_39);
      return localResult;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.Code39Reader
 * JD-Core Version:    0.6.2
 */