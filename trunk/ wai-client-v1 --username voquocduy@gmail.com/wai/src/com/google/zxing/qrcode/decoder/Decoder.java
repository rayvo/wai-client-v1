package com.google.zxing.qrcode.decoder;

import com.google.zxing.ChecksumException;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.reedsolomon.GF256;
import com.google.zxing.common.reedsolomon.ReedSolomonDecoder;
import com.google.zxing.common.reedsolomon.ReedSolomonException;
import java.util.Hashtable;

public final class Decoder
{
  private final ReedSolomonDecoder rsDecoder = new ReedSolomonDecoder(GF256.QR_CODE_FIELD);

  private void correctErrors(byte[] paramArrayOfByte, int paramInt)
    throws ChecksumException
  {
    int i = paramArrayOfByte.length;
    int[] arrayOfInt = new int[i];
    for (int j = 0; j < i; j++)
      arrayOfInt[j] = (0xFF & paramArrayOfByte[j]);
    int k = paramArrayOfByte.length - paramInt;
    try
    {
      this.rsDecoder.decode(arrayOfInt, k);
      for (int m = 0; m < paramInt; m++)
        paramArrayOfByte[m] = ((byte)arrayOfInt[m]);
    }
    catch (ReedSolomonException localReedSolomonException)
    {
      throw ChecksumException.getChecksumInstance();
    }
  }

  public DecoderResult decode(BitMatrix paramBitMatrix)
    throws ChecksumException, FormatException
  {
    return decode(paramBitMatrix, null);
  }

  public DecoderResult decode(BitMatrix paramBitMatrix, Hashtable paramHashtable)
    throws FormatException, ChecksumException
  {
    BitMatrixParser localBitMatrixParser = new BitMatrixParser(paramBitMatrix);
    Version localVersion = localBitMatrixParser.readVersion();
    ErrorCorrectionLevel localErrorCorrectionLevel = localBitMatrixParser.readFormatInformation().getErrorCorrectionLevel();
    DataBlock[] arrayOfDataBlock = DataBlock.getDataBlocks(localBitMatrixParser.readCodewords(), localVersion, localErrorCorrectionLevel);
    int i = 0;
    for (int j = 0; j < arrayOfDataBlock.length; j++)
      i += arrayOfDataBlock[j].getNumDataCodewords();
    byte[] arrayOfByte1 = new byte[i];
    int k = 0;
    int m = 0;
    while (m < arrayOfDataBlock.length)
    {
      DataBlock localDataBlock = arrayOfDataBlock[m];
      byte[] arrayOfByte2 = localDataBlock.getCodewords();
      int n = localDataBlock.getNumDataCodewords();
      correctErrors(arrayOfByte2, n);
      int i1 = 0;
      int i3;
      for (int i2 = k; i1 < n; i2 = i3)
      {
        i3 = i2 + 1;
        arrayOfByte1[i2] = arrayOfByte2[i1];
        i1++;
      }
      m++;
      k = i2;
    }
    return DecodedBitStreamParser.decode(arrayOfByte1, localVersion, localErrorCorrectionLevel, paramHashtable);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean)
    throws ChecksumException, FormatException, NotFoundException
  {
    return decode(paramArrayOfBoolean, null);
  }

  public DecoderResult decode(boolean[][] paramArrayOfBoolean, Hashtable paramHashtable)
    throws ChecksumException, FormatException
  {
    int i = paramArrayOfBoolean.length;
    BitMatrix localBitMatrix = new BitMatrix(i);
    for (int j = 0; j < i; j++)
      for (int k = 0; k < i; k++)
        if (paramArrayOfBoolean[j][k] != 0)
          localBitMatrix.set(k, j);
    return decode(localBitMatrix, paramHashtable);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.Decoder
 * JD-Core Version:    0.6.2
 */