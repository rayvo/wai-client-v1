package com.google.zxing.qrcode.detector;

import com.google.zxing.DecodeHintType;
import com.google.zxing.FormatException;
import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPoint;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.common.GridSampler;
import com.google.zxing.common.PerspectiveTransform;
import com.google.zxing.qrcode.decoder.Version;
import java.util.Hashtable;

public class Detector
{
  private final BitMatrix image;
  private ResultPointCallback resultPointCallback;

  public Detector(BitMatrix paramBitMatrix)
  {
    this.image = paramBitMatrix;
  }

  private float calculateModuleSizeOneWay(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2)
  {
    float f1 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint1.getX(), (int)paramResultPoint1.getY(), (int)paramResultPoint2.getX(), (int)paramResultPoint2.getY());
    float f2 = sizeOfBlackWhiteBlackRunBothWays((int)paramResultPoint2.getX(), (int)paramResultPoint2.getY(), (int)paramResultPoint1.getX(), (int)paramResultPoint1.getY());
    if (Float.isNaN(f1))
      return f2 / 7.0F;
    if (Float.isNaN(f2))
      return f1 / 7.0F;
    return (f1 + f2) / 14.0F;
  }

  protected static int computeDimension(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, float paramFloat)
    throws NotFoundException
  {
    int i = 7 + (round(ResultPoint.distance(paramResultPoint1, paramResultPoint2) / paramFloat) + round(ResultPoint.distance(paramResultPoint1, paramResultPoint3) / paramFloat) >> 1);
    switch (i & 0x3)
    {
    case 1:
    default:
      return i;
    case 0:
      return i + 1;
    case 2:
      return i - 1;
    case 3:
    }
    throw NotFoundException.getNotFoundInstance();
  }

  private static int round(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  private static BitMatrix sampleGrid(BitMatrix paramBitMatrix, PerspectiveTransform paramPerspectiveTransform, int paramInt)
    throws NotFoundException
  {
    return GridSampler.getInstance().sampleGrid(paramBitMatrix, paramInt, paramPerspectiveTransform);
  }

  private float sizeOfBlackWhiteBlackRun(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = this.image.getWidth();
    int j = this.image.getHeight();
    int k;
    int m;
    int n;
    int i1;
    int i2;
    label93: int i3;
    label101: int i4;
    int i5;
    int i6;
    if (Math.abs(paramInt4 - paramInt2) > Math.abs(paramInt3 - paramInt1))
    {
      k = 1;
      if (k != 0)
      {
        int i13 = paramInt1;
        paramInt1 = paramInt2;
        paramInt2 = i13;
        int i14 = paramInt3;
        paramInt3 = paramInt4;
        paramInt4 = i14;
      }
      m = Math.abs(paramInt3 - paramInt1);
      n = Math.abs(paramInt4 - paramInt2);
      i1 = -m >> 1;
      if (paramInt2 >= paramInt4)
        break label230;
      i2 = 1;
      if (paramInt1 >= paramInt3)
        break label236;
      i3 = 1;
      i4 = 0;
      i5 = paramInt1;
      i6 = paramInt2;
    }
    while (true)
    {
      if (i5 != paramInt3)
      {
        int i9;
        label125: int i10;
        if (k != 0)
        {
          i9 = i6;
          if (k == 0)
            break label249;
          i10 = i5;
          label134: if (i4 != 1)
            break label256;
          if ((i9 >= 0) && (i9 < i) && (i10 >= 0) && (i10 < j) && (this.image.get(i9, i10)))
            i4++;
        }
        while (true)
        {
          if (i4 != 3)
            break label300;
          int i11 = i5 - paramInt1;
          int i12 = i6 - paramInt2;
          if (i3 < 0)
            i11++;
          return (float)Math.sqrt(i11 * i11 + i12 * i12);
          k = 0;
          break;
          label230: i2 = -1;
          break label93;
          label236: i3 = -1;
          break label101;
          i9 = i5;
          break label125;
          label249: i10 = i6;
          break label134;
          label256: if ((i9 < 0) || (i9 >= i) || (i10 < 0) || (i10 >= j) || (!this.image.get(i9, i10)))
            i4++;
        }
        label300: i1 += n;
        if (i1 <= 0)
          break label361;
        if (i6 != paramInt4);
      }
      else
      {
        int i7 = paramInt3 - paramInt1;
        int i8 = paramInt4 - paramInt2;
        return (float)Math.sqrt(i7 * i7 + i8 * i8);
      }
      i6 += i2;
      i1 -= m;
      label361: i5 += i3;
    }
  }

  private float sizeOfBlackWhiteBlackRunBothWays(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f1 = sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, paramInt3, paramInt4);
    float f2 = 1.0F;
    int i = paramInt1 - (paramInt3 - paramInt1);
    int j;
    float f3;
    if (i < 0)
    {
      f2 = (0 - paramInt1) / (i - paramInt1);
      i = 0;
      j = (int)(paramInt2 - f2 * (paramInt4 - paramInt2));
      f3 = 1.0F;
      if (j >= 0)
        break label144;
      f3 = (0 - paramInt2) / (j - paramInt2);
      j = 0;
    }
    while (true)
    {
      return f1 + sizeOfBlackWhiteBlackRun(paramInt1, paramInt2, (int)(paramInt1 + f3 * (i - paramInt1)), j);
      if (i <= this.image.getWidth())
        break;
      f2 = (this.image.getWidth() - paramInt1) / (i - paramInt1);
      i = this.image.getWidth();
      break;
      label144: if (j > this.image.getHeight())
      {
        f3 = (this.image.getHeight() - paramInt2) / (j - paramInt2);
        j = this.image.getHeight();
      }
    }
  }

  protected float calculateModuleSize(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3)
  {
    return (calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint2) + calculateModuleSizeOneWay(paramResultPoint1, paramResultPoint3)) / 2.0F;
  }

  public PerspectiveTransform createTransform(ResultPoint paramResultPoint1, ResultPoint paramResultPoint2, ResultPoint paramResultPoint3, ResultPoint paramResultPoint4, int paramInt)
  {
    float f1 = paramInt - 3.5F;
    float f2;
    float f3;
    float f4;
    if (paramResultPoint4 != null)
    {
      f2 = paramResultPoint4.getX();
      f3 = paramResultPoint4.getY();
      f4 = f1 - 3.0F;
    }
    for (float f5 = f4; ; f5 = f1)
    {
      return PerspectiveTransform.quadrilateralToQuadrilateral(3.5F, 3.5F, f1, 3.5F, f5, f4, 3.5F, f1, paramResultPoint1.getX(), paramResultPoint1.getY(), paramResultPoint2.getX(), paramResultPoint2.getY(), f2, f3, paramResultPoint3.getX(), paramResultPoint3.getY());
      f2 = paramResultPoint2.getX() - paramResultPoint1.getX() + paramResultPoint3.getX();
      f3 = paramResultPoint2.getY() - paramResultPoint1.getY() + paramResultPoint3.getY();
      f4 = f1;
    }
  }

  public DetectorResult detect()
    throws NotFoundException, FormatException
  {
    return detect(null);
  }

  public DetectorResult detect(Hashtable paramHashtable)
    throws NotFoundException, FormatException
  {
    if (paramHashtable == null);
    for (ResultPointCallback localResultPointCallback = null; ; localResultPointCallback = (ResultPointCallback)paramHashtable.get(DecodeHintType.NEED_RESULT_POINT_CALLBACK))
    {
      this.resultPointCallback = localResultPointCallback;
      return processFinderPatternInfo(new FinderPatternFinder(this.image).fastFind(paramHashtable));
    }
  }

  protected AlignmentPattern findAlignmentInRegion(float paramFloat1, int paramInt1, int paramInt2, float paramFloat2)
    throws NotFoundException
  {
    int i = (int)(paramFloat2 * paramFloat1);
    int j = Math.max(0, paramInt1 - i);
    int k = Math.min(-1 + this.image.getWidth(), paramInt1 + i);
    if (k - j < paramFloat1 * 3.0F)
      throw NotFoundException.getNotFoundInstance();
    int m = Math.max(0, paramInt2 - i);
    int n = Math.min(-1 + this.image.getHeight(), paramInt2 + i);
    if (n - m < paramFloat1 * 3.0F)
      throw NotFoundException.getNotFoundInstance();
    return new AlignmentPatternFinder(this.image, j, m, k - j, n - m, paramFloat1, this.resultPointCallback).find();
  }

  protected BitMatrix getImage()
  {
    return this.image;
  }

  protected ResultPointCallback getResultPointCallback()
  {
    return this.resultPointCallback;
  }

  protected DetectorResult processFinderPatternInfo(FinderPatternInfo paramFinderPatternInfo)
    throws NotFoundException, FormatException
  {
    FinderPattern localFinderPattern1 = paramFinderPatternInfo.getTopLeft();
    FinderPattern localFinderPattern2 = paramFinderPatternInfo.getTopRight();
    FinderPattern localFinderPattern3 = paramFinderPatternInfo.getBottomLeft();
    ResultPoint localResultPoint = new ResultPoint(localFinderPattern2.getX() - localFinderPattern1.getX() + localFinderPattern3.getX(), localFinderPattern2.getY() - localFinderPattern1.getY() + localFinderPattern3.getY());
    float f1 = calculateModuleSize(localFinderPattern1, localFinderPattern2, localFinderPattern3);
    if (f1 < 1.0F)
      throw NotFoundException.getNotFoundInstance();
    int i = computeDimension(localFinderPattern1, localFinderPattern2, localFinderPattern3, f1);
    Version localVersion = Version.getProvisionalVersionForDimension(i);
    int j = -7 + localVersion.getDimensionForVersion();
    int k = localVersion.getAlignmentPatternCenters().length;
    Object localObject = null;
    int m;
    int n;
    int i1;
    float f3;
    if (k > 0)
    {
      float f2 = 1.0F - 3.0F / j;
      m = (int)(localFinderPattern1.getX() + f2 * (localResultPoint.getX() - localFinderPattern1.getX()));
      n = (int)(localFinderPattern1.getY() + f2 * (localResultPoint.getY() - localFinderPattern1.getY()));
      i1 = 4;
      localObject = null;
      if (i1 <= 16)
        f3 = i1;
    }
    while (true)
    {
      try
      {
        AlignmentPattern localAlignmentPattern = findAlignmentInRegion(f1, m, n, f3);
        localObject = localAlignmentPattern;
        if (localObject != null)
          localResultPoint = new ResultPoint(localObject.getX() + localResultPoint.getX() - m, localObject.getY() + localResultPoint.getY() - n);
        PerspectiveTransform localPerspectiveTransform = createTransform(localFinderPattern1, localFinderPattern2, localFinderPattern3, localObject, i);
        BitMatrix localBitMatrix = sampleGrid(this.image, localPerspectiveTransform, i);
        if (localObject != null)
          break label335;
        arrayOfResultPoint = new ResultPoint[4];
        arrayOfResultPoint[0] = localFinderPattern3;
        arrayOfResultPoint[1] = localFinderPattern1;
        arrayOfResultPoint[2] = localFinderPattern2;
        arrayOfResultPoint[3] = localResultPoint;
        return new DetectorResult(localBitMatrix, arrayOfResultPoint);
      }
      catch (NotFoundException localNotFoundException)
      {
        i1 <<= 1;
      }
      break;
      label335: ResultPoint[] arrayOfResultPoint = new ResultPoint[5];
      arrayOfResultPoint[0] = localFinderPattern3;
      arrayOfResultPoint[1] = localFinderPattern1;
      arrayOfResultPoint[2] = localFinderPattern2;
      arrayOfResultPoint[3] = localResultPoint;
      arrayOfResultPoint[4] = localObject;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.Detector
 * JD-Core Version:    0.6.2
 */