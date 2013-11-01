package com.google.android.apps.unveil;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

public class ActivationReceiver extends BroadcastReceiver
{
  private static final String ACTIVATE_KEY = "activate";
  private static final String GOGGLES_PACKAGE_NAME = UnveilApplication.class.getPackage().getName();
  private static String[] classes = arrayOfString;

  static
  {
    String[] arrayOfString = new String[2];
    arrayOfString[0] = CaptureActivity.class.getName();
    arrayOfString[1] = ShareActivity.class.getName();
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    boolean bool = paramIntent.getExtras().getBoolean("activate");
    String[] arrayOfString = classes;
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str = arrayOfString[j];
      ComponentName localComponentName = new ComponentName(GOGGLES_PACKAGE_NAME, str);
      if (bool);
      for (int k = 0; ; k = 2)
      {
        localPackageManager.setComponentEnabledSetting(localComponentName, k, 1);
        j++;
        break;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ActivationReceiver
 * JD-Core Version:    0.6.2
 */