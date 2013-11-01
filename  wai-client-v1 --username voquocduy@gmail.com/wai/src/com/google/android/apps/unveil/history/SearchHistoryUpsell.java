package com.google.android.apps.unveil.history;

import android.app.Activity;

public class SearchHistoryUpsell extends ItemModel
{
  public static final int REQUEST_CODE = 1024;
  private final Activity activity;

  public SearchHistoryUpsell(Activity paramActivity)
  {
    this.activity = paramActivity;
  }

  public Activity getActivity()
  {
    return this.activity;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryUpsell
 * JD-Core Version:    0.6.2
 */