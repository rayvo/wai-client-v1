package com.google.android.apps.unveil.history;

public abstract interface SearchListener
{
  public abstract void onSearchCompleted(SearchHistoryQuery.QuerySpec paramQuerySpec, int paramInt);

  public abstract void onSearchResultsNoResults(int paramInt);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchListener
 * JD-Core Version:    0.6.2
 */