package com.actionbarsherlock.internal.widget;

import android.view.View;

final class IcsView
{
  public static int getMeasuredStateInt(View paramView)
  {
    return 0xFF000000 & paramView.getMeasuredWidth() | 0xFFFFFF00 & paramView.getMeasuredHeight() >> 16;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.internal.widget.IcsView
 * JD-Core Version:    0.6.2
 */