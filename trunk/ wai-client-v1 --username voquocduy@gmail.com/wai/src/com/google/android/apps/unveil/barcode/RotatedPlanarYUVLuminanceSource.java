package com.google.android.apps.unveil.barcode;

import com.google.zxing.LuminanceSource;

final class RotatedPlanarYUVLuminanceSource extends LuminanceSource
{
  private final byte[] croppedCopySpace;
  private final int dataWidth;
  private final int left;
  private final int top;
  private final byte[] yuvData;

  public RotatedPlanarYUVLuminanceSource(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, byte[] paramArrayOfByte2)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 > paramInt2) || (paramInt4 + paramInt6 > paramInt1))
      throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    this.yuvData = paramArrayOfByte1;
    this.dataWidth = paramInt1;
    this.left = paramInt3;
    this.top = paramInt4;
    this.croppedCopySpace = paramArrayOfByte2;
  }

  public byte[] getData()
  {
    return this.yuvData;
  }

  public int getDataWidth()
  {
    return this.dataWidth;
  }

  public int getLeft()
  {
    return this.left;
  }

  public byte[] getMatrix()
  {
    byte[] arrayOfByte = this.croppedCopySpace;
    int i = this.left * this.dataWidth + (-1 + this.dataWidth - this.top);
    nativeCopy(this.yuvData, arrayOfByte, i, this.dataWidth, getWidth(), getHeight());
    return arrayOfByte;
  }

  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt < 0) || (paramInt >= getHeight()))
      throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
    int i = getWidth();
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i))
      paramArrayOfByte = new byte[i];
    int j = this.left * this.dataWidth + (-1 + this.dataWidth - (paramInt + this.top));
    byte[] arrayOfByte = this.yuvData;
    int k = this.dataWidth;
    nativeCopy(arrayOfByte, paramArrayOfByte, j, k, i, 1);
    return paramArrayOfByte;
  }

  public int getTop()
  {
    return this.top;
  }

  public boolean isCropSupported()
  {
    return true;
  }

  native void nativeCopy(byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int paramInt1, int paramInt2, int paramInt3, int paramInt4);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.RotatedPlanarYUVLuminanceSource
 * JD-Core Version:    0.6.2
 */