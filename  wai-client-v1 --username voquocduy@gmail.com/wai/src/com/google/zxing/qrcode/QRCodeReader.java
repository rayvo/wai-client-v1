package com.google.zxing.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.ChecksumException;
import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.qrcode.decoder.Decoder;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.google.zxing.qrcode.detector.Detector;
import java.util.Hashtable;

public class QRCodeReader
  implements Reader
{
  private static final ResultPoint[] NO_POINTS = new ResultPoint[0];
  private final Decoder decoder = new Decoder();

  private static BitMatrix extractPureBits(BitMatrix paramBitMatrix)
    throws NotFoundException
  {
    int i = paramBitMatrix.getHeight();
    int j = paramBitMatrix.getWidth();
    int k = Math.min(i, j);
    for (int m = 0; (m < k) && (!paramBitMatrix.get(m, m)); m++);
    if (m == k)
      throw NotFoundException.getNotFoundInstance();
    for (int n = m; (n < k) && (paramBitMatrix.get(n, n)); n++);
    if (n == k)
      throw NotFoundException.getNotFoundInstance();
    int i1 = n - m;
    for (int i2 = j - 1; (i2 >= 0) && (!paramBitMatrix.get(i2, m)); i2--);
    if (i2 < 0)
      throw NotFoundException.getNotFoundInstance();
    int i3 = i2 + 1;
    if ((i3 - m) % i1 != 0)
      throw NotFoundException.getNotFoundInstance();
    int i4 = (i3 - m) / i1;
    int i5 = m + (i1 >> 1);
    int i6 = i5 + i1 * (i4 - 1);
    if ((i6 >= j) || (i6 >= i))
      throw NotFoundException.getNotFoundInstance();
    BitMatrix localBitMatrix = new BitMatrix(i4);
    for (int i7 = 0; i7 < i4; i7++)
    {
      int i8 = i5 + i7 * i1;
      for (int i9 = 0; i9 < i4; i9++)
        if (paramBitMatrix.get(i5 + i9 * i1, i8))
          localBitMatrix.set(i9, i7);
    }
    return localBitMatrix;
  }

  public Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException
  {
    return decode(paramBinaryBitmap, null);
  }

  public Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException
  {
    DecoderResult localDecoderResult;
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.PURE_BARCODE)))
    {
      BitMatrix localBitMatrix = extractPureBits(paramBinaryBitmap.getBlackMatrix());
      localDecoderResult = this.decoder.decode(localBitMatrix, paramHashtable);
    }
    DetectorResult localDetectorResult;
    for (ResultPoint[] arrayOfResultPoint = NO_POINTS; ; arrayOfResultPoint = localDetectorResult.getPoints())
    {
      Result localResult = new Result(localDecoderResult.getText(), localDecoderResult.getRawBytes(), arrayOfResultPoint, BarcodeFormat.QR_CODE);
      if (localDecoderResult.getByteSegments() != null)
        localResult.putMetadata(ResultMetadataType.BYTE_SEGMENTS, localDecoderResult.getByteSegments());
      if (localDecoderResult.getECLevel() != null)
        localResult.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, localDecoderResult.getECLevel().toString());
      return localResult;
      localDetectorResult = new Detector(paramBinaryBitmap.getBlackMatrix()).detect(paramHashtable);
      localDecoderResult = this.decoder.decode(localDetectorResult.getBits(), paramHashtable);
    }
  }

  protected Decoder getDecoder()
  {
    return this.decoder;
  }

  public void reset()
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.QRCodeReader
 * JD-Core Version:    0.6.2
 */