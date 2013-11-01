package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.Hashtable;
import java.util.Vector;

public final class MultiFormatUPCEANReader extends OneDReader
{
  private int formats = 0;
  private final int[] resultBuffer = new int[16];

  public MultiFormatUPCEANReader(Hashtable paramHashtable)
  {
    if (paramHashtable == null);
    for (Vector localVector = null; ; localVector = (Vector)paramHashtable.get(DecodeHintType.POSSIBLE_FORMATS))
    {
      if (localVector != null)
      {
        if (localVector.contains(BarcodeFormat.EAN_13))
          this.formats = (0x1 | this.formats);
        if (localVector.contains(BarcodeFormat.UPC_A))
          this.formats = (0x2 | this.formats);
        if (localVector.contains(BarcodeFormat.EAN_8))
          this.formats = (0x4 | this.formats);
        if (localVector.contains(BarcodeFormat.UPC_E))
          this.formats = (0x8 | this.formats);
      }
      if (this.formats == 0)
        this.formats = 13;
      return;
    }
  }

  public static String convertUPCEtoUPCA(String paramString)
  {
    char[] arrayOfChar = new char[6];
    paramString.getChars(1, 7, arrayOfChar, 0);
    StringBuffer localStringBuffer = new StringBuffer(12);
    localStringBuffer.append(paramString.charAt(0));
    char c = arrayOfChar[5];
    switch (c)
    {
    default:
      localStringBuffer.append(arrayOfChar, 0, 5);
      localStringBuffer.append("0000");
      localStringBuffer.append(c);
    case '0':
    case '1':
    case '2':
    case '3':
    case '4':
    }
    while (true)
    {
      localStringBuffer.append(paramString.charAt(7));
      return localStringBuffer.toString();
      localStringBuffer.append(arrayOfChar, 0, 2);
      localStringBuffer.append(c);
      localStringBuffer.append("0000");
      localStringBuffer.append(arrayOfChar, 2, 3);
      continue;
      localStringBuffer.append(arrayOfChar, 0, 3);
      localStringBuffer.append("00000");
      localStringBuffer.append(arrayOfChar, 3, 2);
      continue;
      localStringBuffer.append(arrayOfChar, 0, 4);
      localStringBuffer.append("00000");
      localStringBuffer.append(arrayOfChar[4]);
    }
  }

  private Result createResult(BarcodeFormat paramBarcodeFormat, int paramInt1, int paramInt2)
  {
    StringBuffer localStringBuffer = new StringBuffer(paramInt1);
    for (int i = 0; i < paramInt1; i++)
      localStringBuffer.append(this.resultBuffer[(i + 3)]);
    String str = localStringBuffer.toString();
    ResultPoint[] arrayOfResultPoint = new ResultPoint[2];
    arrayOfResultPoint[0] = new ResultPoint(this.resultBuffer[1], paramInt2);
    arrayOfResultPoint[1] = new ResultPoint(this.resultBuffer[2], paramInt2);
    Result localResult = new Result(str, null, arrayOfResultPoint, paramBarcodeFormat);
    if (this.resultBuffer[1] > this.resultBuffer[2])
      localResult.putMetadata(ResultMetadataType.ORIENTATION, new Integer(180));
    return localResult;
  }

  private native boolean nativeDecodeRow(boolean[] paramArrayOfBoolean, int paramInt, boolean paramBoolean, int[] paramArrayOfInt);

  public Result decodeRow(int paramInt, BitArray paramBitArray, Hashtable paramHashtable)
  {
    return decodeRowBidirect(paramInt, paramBitArray, false);
  }

  public Result decodeRowBidirect(int paramInt, BitArray paramBitArray, boolean paramBoolean)
  {
    if (nativeDecodeRow(BitArray.bits, this.formats, paramBoolean, this.resultBuffer))
    {
      if (this.resultBuffer[0] == 1)
        return createResult(BarcodeFormat.EAN_13, 13, paramInt);
      if (this.resultBuffer[0] == 2)
        return createResult(BarcodeFormat.UPC_A, 12, paramInt);
      if (this.resultBuffer[0] == 4)
        return createResult(BarcodeFormat.EAN_8, 8, paramInt);
      if (this.resultBuffer[0] == 8)
        return createResult(BarcodeFormat.UPC_E, 8, paramInt);
    }
    return null;
  }

  public void reset()
  {
  }

  public boolean supportBidirect()
  {
    return true;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.oned.MultiFormatUPCEANReader
 * JD-Core Version:    0.6.2
 */