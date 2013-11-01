package com.google.android.apps.unveil.env;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;

public class PixelUtils
{
  public static int dipToPix(float paramFloat, Context paramContext)
  {
    return (int)(0.5F + paramFloat * getDensity(paramContext));
  }

  private static float getDensity(Context paramContext)
  {
    return paramContext.getResources().getDisplayMetrics().density;
  }

  public static int inchToPix(float paramFloat, Context paramContext)
  {
    return (int)(paramFloat * (160.0F * getDensity(paramContext)));
  }

  public static int pixToDip(float paramFloat, Context paramContext)
  {
    return (int)(0.5F + paramFloat / getDensity(paramContext));
  }

  public static float pixToInch(float paramFloat, Context paramContext)
  {
    return paramFloat / (160.0F * getDensity(paramContext));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.PixelUtils
 * JD-Core Version:    0.6.2
 */