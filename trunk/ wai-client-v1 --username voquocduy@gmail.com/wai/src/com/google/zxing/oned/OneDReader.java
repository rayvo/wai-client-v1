package com.google.zxing.oned;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Enumeration;
import java.util.Hashtable;

public abstract class OneDReader
  implements Reader
{
  protected static final int INTEGER_MATH_SHIFT = 8;
  protected static final int PATTERN_MATCH_RESULT_SCALE_FACTOR = 256;

  private Result doDecode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable, boolean paramBoolean)
    throws NotFoundException
  {
    int i = paramBinaryBitmap.getWidth();
    int j = paramBinaryBitmap.getHeight();
    BitArray localBitArray = new BitArray(i);
    int k = j >> 1;
    int m;
    int n;
    label55: int i1;
    int i2;
    label75: int i3;
    Hashtable localHashtable;
    int i4;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)))
    {
      m = 1;
      if (m == 0)
        break label257;
      n = 8;
      i1 = Math.max(1, j >> n);
      if (m == 0)
        break label263;
      i2 = j;
      i3 = 1;
      if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.CONFIDENCE_LEVEL)))
        i3 = ((Integer)paramHashtable.get(DecodeHintType.CONFIDENCE_LEVEL)).intValue();
      localHashtable = new Hashtable();
      i4 = 0;
    }
    while (true)
    {
      int i7;
      int i8;
      if (i4 < i2)
      {
        i7 = i4 + 1 >> 1;
        if ((i4 & 0x1) != 0)
          break label270;
        i8 = 1;
        label144: if (i8 == 0)
          break label276;
      }
      int i9;
      Object localObject;
      while (true)
      {
        i9 = k + i1 * i7;
        if ((i9 >= 0) && (i9 < j))
          break label284;
        if (localHashtable.size() <= 0)
          break label421;
        Enumeration localEnumeration = localHashtable.elements();
        int i5 = 0;
        localObject = null;
        while (localEnumeration.hasMoreElements())
        {
          Result localResult1 = (Result)localEnumeration.nextElement();
          int i6 = ((Integer)localResult1.getResultMetadata().get(ResultMetadataType.CONSISTENT_LINES)).intValue();
          if (i6 > i5)
          {
            i5 = i6;
            localObject = localResult1;
          }
        }
        m = 0;
        break;
        label257: n = 5;
        break label55;
        label263: i2 = 24;
        break label75;
        label270: i8 = 0;
        break label144;
        label276: i7 = -i7;
      }
      label284: Result localResult2 = null;
      try
      {
        localBitArray = paramBinaryBitmap.getBlackRow(i9, localBitArray);
        localResult2 = doDecodeRow(i9, localBitArray, localHashtable, paramHashtable, paramBoolean);
        if (localResult2 != null)
        {
          int i11 = ((Integer)localResult2.getResultMetadata().get(ResultMetadataType.CONSISTENT_LINES)).intValue();
          if (i11 >= i3)
            label342: return localResult2;
        }
      }
      catch (NotFoundException localNotFoundException1)
      {
        if (localResult2 != null);
      }
      try
      {
        localBitArray = paramBinaryBitmap.getBlackRowLocal(i9, localBitArray);
        localResult2 = doDecodeRow(i9, localBitArray, localHashtable, paramHashtable, paramBoolean);
        if (localResult2 != null)
        {
          int i10 = ((Integer)localResult2.getResultMetadata().get(ResultMetadataType.CONSISTENT_LINES)).intValue();
          if (i10 >= i3)
            break label342;
        }
        label407: i4++;
        continue;
        if (localObject != null)
          return localObject;
        label421: throw NotFoundException.getNotFoundInstance();
      }
      catch (NotFoundException localNotFoundException2)
      {
        break label407;
      }
    }
  }

  private Result doDecodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable1, Hashtable paramHashtable2, boolean paramBoolean)
  {
    int i = 0;
    while (true)
    {
      if (i < 2)
      {
        if (i != 1)
          break label26;
        if (!paramBoolean);
      }
      else
      {
        return null;
      }
      paramBitArray.reverse();
      label26: if (paramBoolean);
      try
      {
        Result localResult1 = decodeRowBidirect(paramInt, paramBitArray, true);
        break label280;
        localResult1 = decodeRow(paramInt, paramBitArray, paramHashtable2);
        label280: 
        while (localResult1 != null)
        {
          if (i == 1)
          {
            localResult1.putMetadata(ResultMetadataType.ORIENTATION, new Integer(180));
            ResultPoint[] arrayOfResultPoint = localResult1.getResultPoints();
            for (int j = 0; j < arrayOfResultPoint.length; j++)
              arrayOfResultPoint[j] = new ResultPoint(BitArray.size - arrayOfResultPoint[j].getX() - 1.0F, arrayOfResultPoint[j].getY());
          }
          String str = localResult1.getText() + " " + Integer.toString(i);
          if (paramHashtable1.containsKey(str))
          {
            Result localResult3 = (Result)paramHashtable1.get(str);
            localResult3.addResultPoints(localResult1.getResultPoints());
            Hashtable localHashtable = localResult3.getResultMetadata();
            int k = ((Integer)localHashtable.get(ResultMetadataType.CONSISTENT_LINES)).intValue();
            localHashtable.put(ResultMetadataType.CONSISTENT_LINES, new Integer(k + 1));
            return localResult3;
          }
          Result localResult2 = localResult1;
          localResult2.putMetadata(ResultMetadataType.CONSISTENT_LINES, new Integer(1));
          paramHashtable1.put(str, localResult2);
          return localResult2;
        }
        label285: i++;
      }
      catch (ReaderException localReaderException)
      {
        break label285;
      }
    }
  }

  static native int nativePatternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt);

  static native boolean nativeRecordPattern(boolean[] paramArrayOfBoolean, int paramInt, int[] paramArrayOfInt);

  protected static int patternMatchVariance(int[] paramArrayOfInt1, int[] paramArrayOfInt2, int paramInt)
  {
    return nativePatternMatchVariance(paramArrayOfInt1, paramArrayOfInt2, paramInt);
  }

  protected static void recordPattern(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    if (!nativeRecordPattern(BitArray.bits, paramInt, paramArrayOfInt))
      throw NotFoundException.getNotFoundInstance();
  }

  protected static void recordPatternInReverse(BitArray paramBitArray, int paramInt, int[] paramArrayOfInt)
    throws NotFoundException
  {
    boolean[] arrayOfBoolean = BitArray.bits;
    int i = paramArrayOfInt.length;
    int j = arrayOfBoolean[paramInt];
    while ((paramInt > 0) && (i >= 0))
    {
      paramInt--;
      if (arrayOfBoolean[paramInt] != j)
      {
        i--;
        if (j == 0);
        for (j = 1; ; j = 0)
          break;
      }
    }
    if (i >= 0)
      throw NotFoundException.getNotFoundInstance();
    recordPattern(paramBitArray, paramInt + 1, paramArrayOfInt);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    boolean bool = supportBidirect();
    try
    {
      Result localResult2 = doDecode(paramBinaryBitmap, paramHashtable, bool);
      localResult1 = localResult2;
      return localResult1;
    }
    catch (NotFoundException localNotFoundException)
    {
      Result localResult1;
      if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)));
      for (int i = 1; ; i = 0)
      {
        if ((i == 0) || (!paramBinaryBitmap.isRotateSupported()))
          break label207;
        BinaryBitmap localBinaryBitmap = paramBinaryBitmap.rotateCounterClockwise();
        localResult1 = doDecode(localBinaryBitmap, paramHashtable, bool);
        Hashtable localHashtable = localResult1.getResultMetadata();
        int j = 270;
        if ((localHashtable != null) && (localHashtable.containsKey(ResultMetadataType.ORIENTATION)))
          j = (j + ((Integer)localHashtable.get(ResultMetadataType.ORIENTATION)).intValue()) % 360;
        localResult1.putMetadata(ResultMetadataType.ORIENTATION, new Integer(j));
        ResultPoint[] arrayOfResultPoint = localResult1.getResultPoints();
        int k = localBinaryBitmap.getHeight();
        for (int m = 0; m < arrayOfResultPoint.length; m++)
          arrayOfResultPoint[m] = new ResultPoint(k - arrayOfResultPoint[m].getY() - 1.0F, arrayOfResultPoint[m].getX());
      }
      label207: throw localNotFoundException;
    }
  }

  public abstract Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException;

  public Result decodeRowBidirect(int paramInt, BitArray paramBitArray, boolean paramBoolean)
  {
    throw new UnsupportedOperationException("Method not supported");
  }

  public void reset()
  {
  }

  public boolean supportBidirect()
  {
    return false;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.OneDReader
 * JD-Core Version:    0.6.2
 */