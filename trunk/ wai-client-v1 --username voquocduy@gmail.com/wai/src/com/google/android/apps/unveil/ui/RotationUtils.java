package com.google.android.apps.unveil.ui;

import android.graphics.Matrix;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotationUtils
{
  private static final UnveilLogger logger = new UnveilLogger();

  public static ViewGroup.LayoutParams cloneLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramLayoutParams instanceof LinearLayout.LayoutParams))
    {
      LinearLayout.LayoutParams localLayoutParams1 = (LinearLayout.LayoutParams)paramLayoutParams;
      LinearLayout.LayoutParams localLayoutParams2 = new LinearLayout.LayoutParams(localLayoutParams1);
      localLayoutParams2.weight = localLayoutParams1.weight;
      localLayoutParams2.gravity = localLayoutParams1.gravity;
      return localLayoutParams2;
    }
    if ((paramLayoutParams instanceof ViewGroup.MarginLayoutParams))
      return new ViewGroup.MarginLayoutParams((ViewGroup.MarginLayoutParams)paramLayoutParams);
    return new ViewGroup.LayoutParams(paramLayoutParams);
  }

  public static int getRotatedGravity(int paramInt1, int paramInt2)
  {
    int[] arrayOfInt1 = { 48, 5, 80, 3 };
    int[] arrayOfInt2 = { 112, 7, 112, 7 };
    int i = 0;
    for (int j = 0; j < arrayOfInt1.length; j++)
      if ((paramInt2 & arrayOfInt2[j]) == arrayOfInt1[j])
        i |= arrayOfInt1[((3 + (j - paramInt1)) % arrayOfInt1.length)];
    if ((paramInt2 & 0x7) == 1)
      if (paramInt1 % 2 == 1)
        i |= 1;
    while ((paramInt2 & 0x70) != 16)
    {
      return i;
      return i | 0x10;
    }
    if (paramInt1 % 2 == 1)
      return i | 0x10;
    return i | 0x1;
  }

  public static int parseGravity(String paramString)
  {
    int i = 0;
    if (paramString == null);
    boolean bool;
    do
    {
      while (true)
      {
        return i;
        if (paramString.indexOf('|') < 0)
          break;
        i = 0;
        String[] arrayOfString = paramString.split("\\|");
        int j = arrayOfString.length;
        for (int k = 0; k < j; k++)
          i |= parseGravity(arrayOfString[k]);
      }
      if (paramString.equals("top"))
        return 48;
      if (paramString.equals("right"))
        return 5;
      if (paramString.equals("left"))
        return 3;
      if (paramString.equals("bottom"))
        return 80;
      if (paramString.equals("center_vertical"))
        return 16;
      bool = paramString.equals("center_horizontal");
      i = 0;
    }
    while (!bool);
    return 1;
  }

  public static void setViewDimensionsForRotation(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if (paramInt % 2 == 0)
    {
      paramView.getLayoutParams().width = paramLayoutParams.height;
      paramView.getLayoutParams().height = paramLayoutParams.width;
      return;
    }
    paramView.getLayoutParams().height = paramLayoutParams.height;
    paramView.getLayoutParams().width = paramLayoutParams.width;
  }

  public static void transformMatrixForRotation(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Matrix paramMatrix)
  {
    int i = (paramInt5 - paramInt3) / 2;
    int j = (paramInt4 - paramInt2) / 2;
    paramMatrix.reset();
    switch (paramInt1)
    {
    default:
      return;
    case 0:
    case 180:
      paramMatrix.postRotate(paramInt1, j, i);
      return;
    case 90:
      paramMatrix.postRotate(paramInt1, j, j);
      return;
    case 270:
    }
    paramMatrix.postRotate(paramInt1, i, i);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.RotationUtils
 * JD-Core Version:    0.6.2
 */