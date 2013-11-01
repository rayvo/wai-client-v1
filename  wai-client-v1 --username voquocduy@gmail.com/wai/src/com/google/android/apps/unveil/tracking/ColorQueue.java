package com.google.android.apps.unveil.tracking;

import java.util.Iterator;
import java.util.LinkedList;

class ColorQueue
{
  private final int backupColor;
  private final LinkedList<Integer> unusedColors;

  ColorQueue(int[] paramArrayOfInt, int paramInt)
  {
    this.backupColor = paramInt;
    this.unusedColors = new LinkedList();
    int i = paramArrayOfInt.length;
    for (int j = 0; j < i; j++)
    {
      Integer localInteger = Integer.valueOf(paramArrayOfInt[j]);
      this.unusedColors.add(localInteger);
    }
  }

  int getColor()
  {
    if (this.unusedColors.size() > 0)
      return ((Integer)this.unusedColors.poll()).intValue();
    return this.backupColor;
  }

  int getColor(int paramInt)
  {
    Iterator localIterator = this.unusedColors.iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      if (paramInt == localInteger.intValue())
      {
        this.unusedColors.remove(localInteger);
        return paramInt;
      }
    }
    return getColor();
  }

  void returnColor(int paramInt)
  {
    if (paramInt != this.backupColor)
      this.unusedColors.add(Integer.valueOf(paramInt));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.ColorQueue
 * JD-Core Version:    0.6.2
 */