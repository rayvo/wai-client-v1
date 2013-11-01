package com.google.zxing.qrcode.decoder;

final class DataBlock
{
  private final byte[] codewords;
  private final int numDataCodewords;

  private DataBlock(int paramInt, byte[] paramArrayOfByte)
  {
    this.numDataCodewords = paramInt;
    this.codewords = paramArrayOfByte;
  }

  static DataBlock[] getDataBlocks(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if (paramArrayOfByte.length != paramVersion.getTotalCodewords())
      throw new IllegalArgumentException();
    Version.ECBlocks localECBlocks = paramVersion.getECBlocksForLevel(paramErrorCorrectionLevel);
    int i = 0;
    Version.ECB[] arrayOfECB = localECBlocks.getECBlocks();
    for (int j = 0; j < arrayOfECB.length; j++)
      i += arrayOfECB[j].getCount();
    DataBlock[] arrayOfDataBlock = new DataBlock[i];
    int k = 0;
    for (int m = 0; m < arrayOfECB.length; m++)
    {
      Version.ECB localECB = arrayOfECB[m];
      int i19 = 0;
      while (i19 < localECB.getCount())
      {
        int i20 = localECB.getDataCodewords();
        int i21 = i20 + localECBlocks.getECCodewordsPerBlock();
        int i22 = k + 1;
        DataBlock localDataBlock = new DataBlock(i20, new byte[i21]);
        arrayOfDataBlock[k] = localDataBlock;
        i19++;
        k = i22;
      }
    }
    int n = arrayOfDataBlock[0].codewords.length;
    int i1 = -1 + arrayOfDataBlock.length;
    int i2;
    int i3;
    int i4;
    int i5;
    if ((i1 < 0) || (arrayOfDataBlock[i1].codewords.length == n))
    {
      i2 = i1 + 1;
      i3 = n - localECBlocks.getECCodewordsPerBlock();
      i4 = 0;
      i5 = 0;
    }
    while (true)
    {
      if (i5 >= i3)
        break label292;
      int i16 = 0;
      int i17 = i4;
      while (true)
        if (i16 < k)
        {
          byte[] arrayOfByte3 = arrayOfDataBlock[i16].codewords;
          int i18 = i17 + 1;
          arrayOfByte3[i5] = paramArrayOfByte[i17];
          i16++;
          i17 = i18;
          continue;
          i1--;
          break;
        }
      i5++;
      i4 = i17;
    }
    label292: int i6 = i2;
    int i15;
    for (int i7 = i4; i6 < k; i7 = i15)
    {
      byte[] arrayOfByte2 = arrayOfDataBlock[i6].codewords;
      i15 = i7 + 1;
      arrayOfByte2[i3] = paramArrayOfByte[i7];
      i6++;
    }
    int i8 = arrayOfDataBlock[0].codewords.length;
    int i9 = i3;
    int i12;
    for (int i10 = i7; i9 < i8; i10 = i12)
    {
      int i11 = 0;
      i12 = i10;
      if (i11 < k)
      {
        if (i11 < i2);
        for (int i13 = i9; ; i13 = i9 + 1)
        {
          byte[] arrayOfByte1 = arrayOfDataBlock[i11].codewords;
          int i14 = i12 + 1;
          arrayOfByte1[i13] = paramArrayOfByte[i12];
          i11++;
          i12 = i14;
          break;
        }
      }
      i9++;
    }
    return arrayOfDataBlock;
  }

  byte[] getCodewords()
  {
    return this.codewords;
  }

  int getNumDataCodewords()
  {
    return this.numDataCodewords;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.DataBlock
 * JD-Core Version:    0.6.2
 */