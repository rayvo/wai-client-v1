package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitMatrix;
import java.util.Hashtable;

public class FinderPatternFinder
{
  private final BitMatrix image;

  public FinderPatternFinder(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  public static native boolean nativeFind(int paramInt1, int paramInt2, int[] paramArrayOfInt, boolean paramBoolean, float[] paramArrayOfFloat);

  FinderPatternInfo fastFind(Hashtable paramHashtable)
    throws NotFoundException
  {
    if ((paramHashtable != null) && (paramHashtable.containsKey(DecodeHintType.TRY_HARDER)));
    float[] arrayOfFloat;
    for (boolean bool = true; ; bool = false)
    {
      int i = this.image.getHeight();
      int j = this.image.getWidth();
      int[] arrayOfInt = this.image.bits;
      arrayOfFloat = new float[12];
      if (nativeFind(j, i, arrayOfInt, bool, arrayOfFloat))
        break;
      throw NotFoundException.getNotFoundInstance();
    }
    FinderPattern[] arrayOfFinderPattern = new FinderPattern[3];
    for (int k = 0; k < 3; k++)
      arrayOfFinderPattern[k] = new FinderPattern(arrayOfFloat[(k << 2)], arrayOfFloat[(1 + (k << 2))], arrayOfFloat[(2 + (k << 2))]);
    ResultPoint.orderBestPatterns(arrayOfFinderPattern);
    return new FinderPatternInfo(arrayOfFinderPattern);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.FinderPatternFinder
 * JD-Core Version:    0.6.2
 */