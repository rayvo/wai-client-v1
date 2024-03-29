package com.google.zxing.qrcode.detector;

import com.google.zxing.NotFoundException;
import com.google.zxing.ResultPointCallback;
import com.google.zxing.common.BitMatrix;
import java.util.Vector;

final class AlignmentPatternFinder
{
  private final int[] crossCheckStateCount;
  private final int height;
  private final BitMatrix image;
  private final float moduleSize;
  private final Vector possibleCenters;
  private final ResultPointCallback resultPointCallback;
  private final int startX;
  private final int startY;
  private final int width;

  AlignmentPatternFinder(BitMatrix paramBitMatrix, int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat, ResultPointCallback paramResultPointCallback)
  {
    this.image = paramBitMatrix;
    this.possibleCenters = new Vector(5);
    this.startX = paramInt1;
    this.startY = paramInt2;
    this.width = paramInt3;
    this.height = paramInt4;
    this.moduleSize = paramFloat;
    this.crossCheckStateCount = new int[3];
    this.resultPointCallback = paramResultPointCallback;
  }

  private static float centerFromEnd(int[] paramArrayOfInt, int paramInt)
  {
    return paramInt - paramArrayOfInt[2] - paramArrayOfInt[1] / 2.0F;
  }

  private float crossCheckVertical(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    BitMatrix localBitMatrix = this.image;
    int i = localBitMatrix.getHeight();
    int[] arrayOfInt = this.crossCheckStateCount;
    arrayOfInt[0] = 0;
    arrayOfInt[1] = 0;
    arrayOfInt[2] = 0;
    for (int j = paramInt1; (j >= 0) && (localBitMatrix.get(paramInt2, j)) && (arrayOfInt[1] <= paramInt3); j--)
      arrayOfInt[1] = (1 + arrayOfInt[1]);
    if ((j < 0) || (arrayOfInt[1] > paramInt3));
    int k;
    do
    {
      do
      {
        do
        {
          return (0.0F / 0.0F);
          while ((j >= 0) && (!localBitMatrix.get(paramInt2, j)) && (arrayOfInt[0] <= paramInt3))
          {
            arrayOfInt[0] = (1 + arrayOfInt[0]);
            j--;
          }
        }
        while (arrayOfInt[0] > paramInt3);
        for (k = paramInt1 + 1; (k < i) && (localBitMatrix.get(paramInt2, k)) && (arrayOfInt[1] <= paramInt3); k++)
          arrayOfInt[1] = (1 + arrayOfInt[1]);
      }
      while ((k == i) || (arrayOfInt[1] > paramInt3));
      while ((k < i) && (!localBitMatrix.get(paramInt2, k)) && (arrayOfInt[2] <= paramInt3))
      {
        arrayOfInt[2] = (1 + arrayOfInt[2]);
        k++;
      }
    }
    while ((arrayOfInt[2] > paramInt3) || (5 * Math.abs(arrayOfInt[0] + arrayOfInt[1] + arrayOfInt[2] - paramInt4) >= paramInt4 * 2) || (!foundPatternCross(arrayOfInt)));
    return centerFromEnd(arrayOfInt, k);
  }

  private boolean foundPatternCross(int[] paramArrayOfInt)
  {
    float f1 = this.moduleSize;
    float f2 = f1 * 0.5F;
    int i = 1;
    int j = 0;
    if (j < 3)
    {
      if (Math.abs(f1 - paramArrayOfInt[j]) >= f2)
        i = 0;
    }
    else
      if ((Math.abs(3.0F * f1 - paramArrayOfInt[0] - paramArrayOfInt[1] - paramArrayOfInt[2]) >= f2) || (Math.abs(paramArrayOfInt[0] - paramArrayOfInt[2]) >= f2))
        break label111;
    label111: for (int k = 1; ; k = 0)
    {
      boolean bool;
      if (i == 0)
      {
        bool = false;
        if (k == 0);
      }
      else
      {
        bool = true;
      }
      return bool;
      j++;
      break;
    }
  }

  private AlignmentPattern handlePossibleCenter(int[] paramArrayOfInt, int paramInt1, int paramInt2)
  {
    int i = paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2];
    float f1 = centerFromEnd(paramArrayOfInt, paramInt2);
    float f2 = crossCheckVertical(paramInt1, (int)f1, (i << 1) / 3, i);
    if (!Float.isNaN(f2))
    {
      float f3 = (paramArrayOfInt[0] + paramArrayOfInt[1] + paramArrayOfInt[2]) / 3.0F;
      int j = this.possibleCenters.size();
      for (int k = 0; k < j; k++)
        if (((AlignmentPattern)this.possibleCenters.elementAt(k)).aboutEquals(f3, f2, f1))
          return new AlignmentPattern(f1, f2, f3);
      AlignmentPattern localAlignmentPattern = new AlignmentPattern(f1, f2, f3);
      this.possibleCenters.addElement(localAlignmentPattern);
      if (this.resultPointCallback != null)
        this.resultPointCallback.foundPossibleResultPoint(localAlignmentPattern);
    }
    return null;
  }

  AlignmentPattern find()
    throws NotFoundException
  {
    int i = this.startX;
    int j = this.height;
    int k = i + this.width;
    int m = this.startY + (j >> 1);
    int[] arrayOfInt = new int[3];
    label292: for (int n = 0; n < j; n++)
    {
      if ((n & 0x1) == 0);
      int i2;
      int i3;
      for (int i1 = n + 1 >> 1; ; i1 = -(n + 1 >> 1))
      {
        i2 = m + i1;
        arrayOfInt[0] = 0;
        arrayOfInt[1] = 0;
        arrayOfInt[2] = 0;
        for (i3 = i; (i3 < k) && (!this.image.get(i3, i2)); i3++);
      }
      int i4 = 0;
      AlignmentPattern localAlignmentPattern;
      while (true)
        if (i3 < k)
          if (this.image.get(i3, i2))
          {
            if (i4 == 1)
            {
              arrayOfInt[i4] = (1 + arrayOfInt[i4]);
              i3++;
              continue;
            }
            if (i4 == 2)
              if (foundPatternCross(arrayOfInt))
              {
                localAlignmentPattern = handlePossibleCenter(arrayOfInt, i2, i3);
                if (localAlignmentPattern == null)
                  break;
              }
          }
      do
      {
        return localAlignmentPattern;
        arrayOfInt[0] = arrayOfInt[2];
        arrayOfInt[1] = 1;
        arrayOfInt[2] = 0;
        i4 = 1;
        break;
        i4++;
        arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        break;
        if (i4 == 1)
          i4++;
        arrayOfInt[i4] = (1 + arrayOfInt[i4]);
        break;
        if (!foundPatternCross(arrayOfInt))
          break label292;
        localAlignmentPattern = handlePossibleCenter(arrayOfInt, i2, k);
      }
      while (localAlignmentPattern != null);
    }
    if (!this.possibleCenters.isEmpty())
      return (AlignmentPattern)this.possibleCenters.elementAt(0);
    throw NotFoundException.getNotFoundInstance();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.detector.AlignmentPatternFinder
 * JD-Core Version:    0.6.2
 */