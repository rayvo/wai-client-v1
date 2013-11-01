package com.google.android.apps.unveil.service;

public final class FreshnessHook
  implements QueryExecutor.PostExecutionHook
{
  private final FreshnessHelper freshnessHelper;

  public FreshnessHook(FreshnessHelper paramFreshnessHelper)
  {
    this.freshnessHelper = paramFreshnessHelper;
  }

  public void onQueryFailed(BackgroundQuery paramBackgroundQuery, int paramInt)
  {
  }

  public void onQuerySucceeded(BackgroundQuery paramBackgroundQuery)
  {
    this.freshnessHelper.setFreshnessTimestamp(paramBackgroundQuery.timestamp);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.FreshnessHook
 * JD-Core Version:    0.6.2
 */