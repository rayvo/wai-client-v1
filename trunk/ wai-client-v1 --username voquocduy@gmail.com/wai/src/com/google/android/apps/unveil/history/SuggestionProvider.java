package com.google.android.apps.unveil.history;

import android.content.Context;
import android.content.SearchRecentSuggestionsProvider;
import com.google.android.apps.unveil.R.string;

public class SuggestionProvider extends SearchRecentSuggestionsProvider
{
  public static final int MODE = 1;

  public static String getAuthority(Context paramContext)
  {
    return paramContext.getString(R.string.suggestion_provider_authority);
  }

  public boolean onCreate()
  {
    setupSuggestions(getAuthority(getContext()), 1);
    return super.onCreate();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SuggestionProvider
 * JD-Core Version:    0.6.2
 */