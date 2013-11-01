package com.google.zxing.common;

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.util.Vector;

public final class DecoderResult
{
  private final Vector byteSegments;
  private final ErrorCorrectionLevel ecLevel;
  private final byte[] rawBytes;
  private final String text;

  public DecoderResult(byte[] paramArrayOfByte, String paramString, Vector paramVector, ErrorCorrectionLevel paramErrorCorrectionLevel)
  {
    if ((paramArrayOfByte == null) && (paramString == null))
      throw new IllegalArgumentException();
    this.rawBytes = paramArrayOfByte;
    this.text = paramString;
    this.byteSegments = paramVector;
    this.ecLevel = paramErrorCorrectionLevel;
  }

  public Vector getByteSegments()
  {
    return this.byteSegments;
  }

  public ErrorCorrectionLevel getECLevel()
  {
    return this.ecLevel;
  }

  public byte[] getRawBytes()
  {
    return this.rawBytes;
  }

  public String getText()
  {
    return this.text;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.DecoderResult
 * JD-Core Version:    0.6.2
 */