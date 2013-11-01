package com.google.android.apps.unveil.barcode;

import com.google.zxing.LuminanceSource;

final class PlanarYUVLuminanceSource extends LuminanceSource
{
  private final byte[] croppedCopySpace;
  private final int left;
  private final int top;
  private final byte[] yuvData;
  private final int yuvInputHeight;
  private final int yuvInputWidth;

  PlanarYUVLuminanceSource(byte[] paramArrayOfByte1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, byte[] paramArrayOfByte2)
  {
    super(paramInt5, paramInt6);
    if ((paramInt3 + paramInt5 > paramInt1) || (paramInt4 + paramInt6 > paramInt2))
      throw new IllegalArgumentException("Crop rectangle does not fit within image data.");
    this.yuvData = paramArrayOfByte1;
    this.yuvInputWidth = paramInt1;
    this.yuvInputHeight = paramInt2;
    this.left = paramInt3;
    this.top = paramInt4;
    this.croppedCopySpace = paramArrayOfByte2;
  }

  public byte[] getData()
  {
    return this.yuvData;
  }

  public int getDataHeight()
  {
    return this.yuvInputHeight;
  }

  public int getDataWidth()
  {
    return this.yuvInputWidth;
  }

  public int getLeft()
  {
    return this.left;
  }

  public byte[] getMatrix()
  {
    int i = getWidth();
    int j = getHeight();
    byte[] arrayOfByte1;
    if ((i == this.yuvInputWidth) && (j == this.yuvInputHeight))
      arrayOfByte1 = this.yuvData;
    while (true)
    {
      return arrayOfByte1;
      int k = i * j;
      arrayOfByte1 = this.croppedCopySpace;
      int m = this.top * this.yuvInputWidth + this.left;
      if (i == this.yuvInputWidth)
      {
        System.arraycopy(this.yuvData, m, arrayOfByte1, 0, k);
        return arrayOfByte1;
      }
      byte[] arrayOfByte2 = this.yuvData;
      for (int n = 0; n < j; n++)
      {
        System.arraycopy(arrayOfByte2, m, arrayOfByte1, n * i, i);
        m += this.yuvInputWidth;
      }
    }
  }

  public byte[] getRow(int paramInt, byte[] paramArrayOfByte)
  {
    if ((paramInt < 0) || (paramInt >= getHeight()))
      throw new IllegalArgumentException("Requested row is outside the image: " + paramInt);
    int i = getWidth();
    if ((paramArrayOfByte == null) || (paramArrayOfByte.length < i))
      paramArrayOfByte = new byte[i];
    int j = (paramInt + this.top) * this.yuvInputWidth + this.left;
    System.arraycopy(this.yuvData, j, paramArrayOfByte, 0, i);
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
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.PlanarYUVLuminanceSource
 * JD-Core Version:    0.6.2
 */