package com.google.zxing;

import java.util.Hashtable;

public final class Result
{
  private final BarcodeFormat format;
  private final byte[] rawBytes;
  private Hashtable resultMetadata;
  private ResultPoint[] resultPoints;
  private final String text;

  public Result(String paramString, byte[] paramArrayOfByte, ResultPoint[] paramArrayOfResultPoint, BarcodeFormat paramBarcodeFormat)
  {
    if ((paramString == null) && (paramArrayOfByte == null))
      throw new IllegalArgumentException("Text and bytes are null");
    this.text = paramString;
    this.rawBytes = paramArrayOfByte;
    this.resultPoints = paramArrayOfResultPoint;
    this.format = paramBarcodeFormat;
    this.resultMetadata = null;
  }

  public void addResultPoints(ResultPoint[] paramArrayOfResultPoint)
  {
    if (this.resultPoints == null)
      this.resultPoints = paramArrayOfResultPoint;
    while ((paramArrayOfResultPoint == null) || (paramArrayOfResultPoint.length <= 0))
      return;
    ResultPoint[] arrayOfResultPoint = new ResultPoint[this.resultPoints.length + paramArrayOfResultPoint.length];
    System.arraycopy(this.resultPoints, 0, arrayOfResultPoint, 0, this.resultPoints.length);
    System.arraycopy(paramArrayOfResultPoint, 0, arrayOfResultPoint, this.resultPoints.length, paramArrayOfResultPoint.length);
    this.resultPoints = arrayOfResultPoint;
  }

  public BarcodeFormat getBarcodeFormat()
  {
    return this.format;
  }

  public byte[] getRawBytes()
  {
    return this.rawBytes;
  }

  public Hashtable getResultMetadata()
  {
    return this.resultMetadata;
  }

  public ResultPoint[] getResultPoints()
  {
    return this.resultPoints;
  }

  public String getText()
  {
    return this.text;
  }

  public void putMetadata(ResultMetadataType paramResultMetadataType, Object paramObject)
  {
    if (this.resultMetadata == null)
      this.resultMetadata = new Hashtable(3);
    this.resultMetadata.put(paramResultMetadataType, paramObject);
  }

  public String toString()
  {
    if (this.text == null)
      return "[" + this.rawBytes.length + " bytes]";
    return this.text;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.Result
 * JD-Core Version:    0.6.2
 */