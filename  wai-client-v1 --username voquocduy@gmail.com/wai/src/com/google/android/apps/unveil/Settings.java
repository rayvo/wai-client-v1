package com.google.android.apps.unveil;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.preference.PreferenceManager;

public class Settings
{
  public static boolean getBoolean(Context paramContext, int paramInt)
  {
    return getBoolean(paramContext, paramInt, false);
  }

  public static boolean getBoolean(Context paramContext, int paramInt, boolean paramBoolean)
  {
    return getPreferences(paramContext).getBoolean(r(paramContext).getString(paramInt), paramBoolean);
  }

  public static SharedPreferences getPreferences(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext);
  }

  public static void putBoolean(Context paramContext, int paramInt, boolean paramBoolean)
  {
    getPreferences(paramContext).edit().putBoolean(r(paramContext).getString(paramInt), paramBoolean).commit();
  }

  public static void putInt(Context paramContext, int paramInt1, int paramInt2)
  {
    getPreferences(paramContext).edit().putInt(r(paramContext).getString(paramInt1), paramInt2).commit();
  }

  private static Resources r(Context paramContext)
  {
    return paramContext.getResources();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.Settings
 * JD-Core Version:    0.6.2
 */