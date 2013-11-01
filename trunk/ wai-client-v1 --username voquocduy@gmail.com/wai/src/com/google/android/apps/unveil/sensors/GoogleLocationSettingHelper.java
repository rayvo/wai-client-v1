package com.google.android.apps.unveil.sensors;

import android.content.ActivityNotFoundException;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class GoogleLocationSettingHelper
{
  private static final String ACTION_SET_USE_LOCATION_FOR_SERVICES = "com.google.android.gsf.action.SET_USE_LOCATION_FOR_SERVICES";
  public static final String EXTRA_DISABLE_USE_LOCATION_FOR_SERVICES = "disable";
  private static final String GOOGLE_SETTINGS_AUTHORITY = "com.google.settings";
  private static final Uri GOOGLE_SETTINGS_CONTENT_URI = Uri.parse("content://com.google.settings/partner");
  private static final String NAME = "name";
  private static final String TAG = "GoogleLocationSettingHelper";
  private static final String USE_LOCATION_FOR_SERVICES = "use_location_for_services";
  public static final int USE_LOCATION_FOR_SERVICES_NOT_SET = 2;
  public static final int USE_LOCATION_FOR_SERVICES_OFF = 0;
  public static final int USE_LOCATION_FOR_SERVICES_ON = 1;
  private static final String VALUE = "value";

  private static Intent getSetUseLocationIntent()
  {
    return new Intent("com.google.android.gsf.action.SET_USE_LOCATION_FOR_SERVICES");
  }

  public static int getUseLocationForServices(Context paramContext)
  {
    ContentResolver localContentResolver = paramContext.getContentResolver();
    Cursor localCursor = null;
    Object localObject2;
    try
    {
      localCursor = localContentResolver.query(GOOGLE_SETTINGS_CONTENT_URI, new String[] { "value" }, "name=?", new String[] { "use_location_for_services" }, null);
      localObject2 = null;
      if (localCursor != null)
      {
        boolean bool = localCursor.moveToNext();
        localObject2 = null;
        if (bool)
        {
          String str = localCursor.getString(0);
          localObject2 = str;
        }
      }
      if (localCursor != null)
        localCursor.close();
      if (localObject2 == null)
        return 2;
    }
    catch (RuntimeException localRuntimeException)
    {
      while (true)
      {
        Log.w("GoogleLocationSettingHelper", "Failed to get 'Use My Location' setting", localRuntimeException);
        localObject2 = null;
        if (localCursor != null)
        {
          localCursor.close();
          localObject2 = null;
        }
      }
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
    try
    {
      int i = Integer.parseInt(localObject2);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return 2;
  }

  public static boolean isAvailable(Context paramContext)
  {
    return paramContext.getPackageManager().resolveActivity(getSetUseLocationIntent(), 65536) != null;
  }

  public static void setUseLocationForServices(Context paramContext, boolean paramBoolean)
  {
    Intent localIntent = getSetUseLocationIntent();
    localIntent.setFlags(268435456);
    boolean bool;
    if (!paramBoolean)
      bool = true;
    while (true)
    {
      localIntent.putExtra("disable", bool);
      try
      {
        paramContext.startActivity(localIntent);
        return;
        bool = false;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Log.e("GoogleLocationSettingHelper", "Problem while starting GSF location activity");
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.GoogleLocationSettingHelper
 * JD-Core Version:    0.6.2
 */