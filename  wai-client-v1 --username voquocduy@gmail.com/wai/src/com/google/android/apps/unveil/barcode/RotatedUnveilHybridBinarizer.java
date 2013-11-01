package com.google.android.apps.unveil.barcode;

import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

final class RotatedUnveilHybridBinarizer extends Binarizer
{
  private final byte[] data;
  private final int dataWidth;
  private final int height;
  private final int left;
  private final BitMatrix matrix;
  private final int top;
  private final int width;

  public RotatedUnveilHybridBinarizer(LuminanceSource paramLuminanceSource, int[] paramArrayOfInt, byte[] paramArrayOfByte)
  {
    super(paramLuminanceSource);
    RotatedPlanarYUVLuminanceSource localRotatedPlanarYUVLuminanceSource = (RotatedPlanarYUVLuminanceSource)paramLuminanceSource;
    this.data = localRotatedPlanarYUVLuminanceSource.getData();
    this.dataWidth = localRotatedPlanarYUVLuminanceSource.getDataWidth();
    this.width = localRotatedPlanarYUVLuminanceSource.getWidth();
    this.height = localRotatedPlanarYUVLuminanceSource.getHeight();
    this.left = localRotatedPlanarYUVLuminanceSource.getLeft();
    this.top = localRotatedPlanarYUVLuminanceSource.getTop();
    this.matrix = new BitMatrix(this.height, this.width, paramArrayOfInt);
  }

  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    throw new UnsupportedOperationException("Not supported.");
  }

  public BitMatrix getBlackMatrix()
  {
    throw new UnsupportedOperationException("This method is only used for 2D barcode scanning, which we don't need, as this class is only used for 1D.");
  }

  public BitArray getBlackRow(int paramInt, BitArray paramBitArray)
  {
    if ((paramBitArray == null) || (BitArray.size < this.width))
      paramBitArray = new BitArray(this.width);
    int i = this.left * this.dataWidth + (-1 + this.dataWidth - (paramInt + this.top));
    UnveilHybridBinarizer.nativeBinarizeRow(this.width, this.data, i, this.dataWidth, BitArray.bits);
    return paramBitArray;
  }

  public BitArray getBlackRowLocal(int paramInt, BitArray paramBitArray)
  {
    return this.matrix.getColumn(-1 + this.height - paramInt, paramBitArray);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.RotatedUnveilHybridBinarizer
 * JD-Core Version:    0.6.2
 */