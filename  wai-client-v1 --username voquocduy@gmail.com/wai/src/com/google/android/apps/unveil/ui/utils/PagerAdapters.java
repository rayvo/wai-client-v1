package com.google.android.apps.unveil.ui.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

public final class PagerAdapters
{
  private PagerAdapters()
  {
    throw new UnsupportedOperationException("Utility class PagerAdapters should not be instantiated.");
  }

  public static void attach(ViewPager paramViewPager, View paramView)
  {
    paramViewPager.addView(paramView);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.utils.PagerAdapters
 * JD-Core Version:    0.6.2
 */