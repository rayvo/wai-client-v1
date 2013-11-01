package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;

public final class ITFReader extends OneDReader
{
  private static final int[] DEFAULT_ALLOWED_LENGTHS = { 6, 10, 12, 14, 16, 24, 44 };
  private static final int[] END_PATTERN_REVERSED = { 1, 1, 3 };
  private static final int MAX_AVG_VARIANCE = 107;
  private static final int MAX_INDIVIDUAL_VARIANCE = 204;
  private static final int N = 1;
  static final int[][] PATTERNS = { { 1, 1, 3, 3, 1 }, { 3, 1, 1, 1, 3 }, { 1, 3, 1, 1, 3 }, { 3, 3, 1, 1, 1 }, { 1, 1, 3, 1, 3 }, { 3, 1, 3, 1, 1 }, { 1, 3, 3, 1, 1 }, { 1, 1, 1, 3, 3 }, { 3, 1, 1, 3, 1 }, { 1, 3, 1, 3, 1 } };
  private static final int[] START_PATTERN = { 1, 1, 1, 1 };
  private static final int W = 3;
  private int narrowLineWidth = -1;

  private static int decodeDigit(int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i = 107;
    int j = -1;
    int k = PATTERNS.length;
    for (int m = 0; m < k; m++)
    {
      int n = patternMatchVariance(paramArrayOfInt, PATTERNS[m], 204);
      if (n < i)
      {
        i = n;
        j = m;
      }
    }
    if (j >= 0)
      return j;
    throw NotFoundException.getNotFoundInstance();
  }

  private static void decodeMiddle(BitArray paramBitArray, int paramInt1, int paramInt2, StringBuffer paramStringBuffer)
    throws NotFoundException
  {
    int[] arrayOfInt1 = new int[10];
    int[] arrayOfInt2 = new int[5];
    int[] arrayOfInt3 = new int[5];
    while (paramInt1 < paramInt2)
    {
      recordPattern(paramBitArray, paramInt1, arrayOfInt1);
      for (int i = 0; i < 5; i++)
      {
        int k = i << 1;
        arrayOfInt2[i] = arrayOfInt1[k];
        arrayOfInt3[i] = arrayOfInt1[(k + 1)];
      }
      paramStringBuffer.append((char)(48 + decodeDigit(arrayOfInt2)));
      paramStringBuffer.append((char)(48 + decodeDigit(arrayOfInt3)));
      for (int j = 0; j < arrayOfInt1.length; j++)
        paramInt1 += arrayOfInt1[j];
    }
  }

  private static int[] findGuardPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    int i = paramArrayOfInt.length;
    int[] arrayOfInt = new int[i];
    int j = paramBitArray.getSize();
    int k = 0;
    int m = 0;
    int n = paramInt;
    int i1 = paramInt;
    while (i1 < j)
      if ((k ^ paramBitArray.get(i1)) != 0)
      {
        arrayOfInt[m] = (1 + arrayOfInt[m]);
        i1++;
      }
      else
      {
        if (m == i - 1)
        {
          if (nativePatternMatchVariance(arrayOfInt, paramArrayOfInt, 204) < 107)
            return new int[] { n, i1 };
          n += arrayOfInt[0] + arrayOfInt[1];
          for (int i2 = 2; i2 < i; i2++)
            arrayOfInt[(i2 - 2)] = arrayOfInt[i2];
          arrayOfInt[(i - 2)] = 0;
          arrayOfInt[(i - 1)] = 0;
          m--;
          label157: arrayOfInt[m] = 1;
          if (k != 0)
            break label180;
        }
        label180: for (k = 1; ; k = 0)
        {
          break;
          m++;
          break label157;
        }
      }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int skipWhiteSpace(BitArray paramBitArray)
    throws NotFoundException
  {
    int i = paramBitArray.getSize();
    for (int j = 0; ; j++)
      if ((j >= i) || (paramBitArray.get(j)))
      {
        if (j != i)
          break;
        throw NotFoundException.getNotFoundInstance();
      }
    return j;
  }

  private void validateQuietZone(BitArray paramBitArray, int paramInt)
    throws NotFoundException
  {
    int i = 10 * this.narrowLineWidth;
    for (int j = paramInt - 1; ; j--)
    {
      if ((i <= 0) || (j < 0) || (paramBitArray.get(j)))
      {
        if (i == 0)
          break;
        throw NotFoundException.getNotFoundInstance();
      }
      i--;
    }
  }

  int[] decodeEnd(BitArray paramBitArray)
    throws NotFoundException
  {
    paramBitArray.reverse();
    try
    {
      int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), END_PATTERN_REVERSED);
      validateQuietZone(paramBitArray, arrayOfInt[0]);
      int i = arrayOfInt[0];
      arrayOfInt[0] = (paramBitArray.getSize() - arrayOfInt[1]);
      arrayOfInt[1] = (paramBitArray.getSize() - i);
      return arrayOfInt;
    }
    finally
    {
      paramBitArray.reverse();
    }
  }

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws FormatException, NotFoundException
  {
    int[] arrayOfInt1 = decodeStart(paramBitArray);
    int[] arrayOfInt2 = decodeEnd(paramBitArray);
    StringBuffer localStringBuffer = new StringBuffer(20);
    decodeMiddle(paramBitArray, arrayOfInt1[1], arrayOfInt2[0], localStringBuffer);
    String str = localStringBuffer.toString();
    int[] arrayOfInt3 = null;
    if (paramHashtable != null)
      arrayOfInt3 = (int[])paramHashtable.get(DecodeHintType.ALLOWED_LENGTHS);
    if (arrayOfInt3 == null)
      arrayOfInt3 = DEFAULT_ALLOWED_LENGTHS;
    int i = str.length();
    for (int j = 0; ; j++)
    {
      int k = arrayOfInt3.length;
      int m = 0;
      if (j < k)
      {
        if (i == arrayOfInt3[j])
          m = 1;
      }
      else
      {
        if (m != 0)
          break;
        throw FormatException.getFormatInstance();
      }
    }
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    arrayOfResultPoint[0] = new ResultPoint(arrayOfInt1[0], paramInt);
    arrayOfResultPoint[1] = new ResultPoint(arrayOfInt2[1], paramInt);
    return new Result(str, null, arrayOfResultPoint, BarcodeFormat.ITF);
  }

  int[] decodeStart(BitArray paramBitArray)
    throws NotFoundException
  {
    int[] arrayOfInt = findGuardPattern(paramBitArray, skipWhiteSpace(paramBitArray), START_PATTERN);
    this.narrowLineWidth = (arrayOfInt[1] - arrayOfInt[0] >> 2);
    validateQuietZone(paramBitArray, arrayOfInt[0]);
    return arrayOfInt;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.ITFReader
 * JD-Core Version:    0.6.2
 */