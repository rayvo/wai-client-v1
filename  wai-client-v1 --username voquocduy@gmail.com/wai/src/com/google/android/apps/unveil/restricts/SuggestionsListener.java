package com.google.android.apps.unveil.restricts;

import com.google.goggles.RestrictsProtos.Restricts;

public abstract interface SuggestionsListener
{
  public abstract boolean onClearClick();

  public abstract boolean onRestrictClick(RestrictsProtos.Restricts paramRestricts);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.SuggestionsListener
 * JD-Core Version:    0.6.2
 */