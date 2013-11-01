package com.google.android.apps.unveil.barcode;

import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.zxing.Binarizer;
import com.google.zxing.LuminanceSource;
import com.google.zxing.common.BitArray;
import com.google.zxing.common.BitMatrix;

final class UnveilHybridBinarizer extends Binarizer
{
  private static final int MINIMUM_DIMENSION = 40;
  private final byte[] data;
  private final int dataWidth;
  private final int[] globalBitMatrixBits;
  private final byte[] globalBlackPoints;
  private final int height;
  private final int left;
  private BitMatrix matrix;
  private final int top;
  private final int width;

  static
  {
    ResourceUtils.loadNativeLibrary("zxing");
  }

  public UnveilHybridBinarizer(LuminanceSource paramLuminanceSource, int[] paramArrayOfInt, byte[] paramArrayOfByte)
  {
    super(paramLuminanceSource);
    PlanarYUVLuminanceSource localPlanarYUVLuminanceSource = (PlanarYUVLuminanceSource)paramLuminanceSource;
    this.data = localPlanarYUVLuminanceSource.getData();
    this.dataWidth = localPlanarYUVLuminanceSource.getDataWidth();
    this.width = localPlanarYUVLuminanceSource.getWidth();
    this.height = localPlanarYUVLuminanceSource.getHeight();
    this.left = localPlanarYUVLuminanceSource.getLeft();
    this.top = localPlanarYUVLuminanceSource.getTop();
    this.globalBitMatrixBits = paramArrayOfInt;
    this.globalBlackPoints = paramArrayOfByte;
  }

  private void binarizeEntireImage()
  {
    if (this.matrix == null)
    {
      this.matrix = new BitMatrix(this.width, this.height, this.globalBitMatrixBits);
      if ((this.width >= 40) && (this.height >= 40))
      {
        byte[] arrayOfByte = getLuminanceSource().getMatrix();
        nativeBinarizeEntireImage(this.width, this.height, arrayOfByte, this.globalBlackPoints, this.matrix.bits);
      }
    }
    else
    {
      return;
    }
    this.matrix.clear();
  }

  static native void nativeBinarizeEntireImage(int paramInt1, int paramInt2, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, int[] paramArrayOfInt);

  static native void nativeBinarizeRow(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3, boolean[] paramArrayOfBoolean);

  public Binarizer createBinarizer(LuminanceSource paramLuminanceSource)
  {
    throw new RuntimeException("Not supported.");
  }

  public BitMatrix getBlackMatrix()
  {
    binarizeEntireImage();
    return this.matrix;
  }

  public BitArray getBlackRow(int paramInt, BitArray paramBitArray)
  {
    if ((paramBitArray == null) || (BitArray.size < this.width))
      paramBitArray = new BitArray(this.width);
    int i = (paramInt + this.top) * this.dataWidth + this.left;
    nativeBinarizeRow(this.width, this.data, i, 1, BitArray.bits);
    return paramBitArray;
  }

  public BitArray getBlackRowLocal(int paramInt, BitArray paramBitArray)
  {
    if (this.matrix == null)
      binarizeEntireImage();
    return this.matrix.getRow(paramInt, paramBitArray);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.UnveilHybridBinarizer
 * JD-Core Version:    0.6.2
 */