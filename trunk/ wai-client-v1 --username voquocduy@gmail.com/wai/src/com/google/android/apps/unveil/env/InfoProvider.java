package com.google.android.apps.unveil.env;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION;
import com.google.goggles.DeviceInfoProtos.DeviceInfo;
import com.google.goggles.DeviceInfoProtos.DeviceInfo.Builder;

public class InfoProvider
{
  private static String clientVersion;
  private static DeviceInfoProtos.DeviceInfo deviceInfo;

  public static String getClientVersion(Context paramContext)
  {
    try
    {
      String str1 = clientVersion;
      if (str1 == null);
      try
      {
        clientVersion = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionName;
        String str2 = clientVersion;
        return str2;
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        throw new RuntimeException(localNameNotFoundException);
      }
    }
    finally
    {
    }
  }

  public static DeviceInfoProtos.DeviceInfo getDeviceInfo()
  {
    try
    {
      if (deviceInfo == null)
        deviceInfo = DeviceInfoProtos.DeviceInfo.newBuilder().setPlatform("Android").setBuildVersion(Build.VERSION.RELEASE).setModel(Build.MODEL).build();
      DeviceInfoProtos.DeviceInfo localDeviceInfo = deviceInfo;
      return localDeviceInfo;
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.InfoProvider
 * JD-Core Version:    0.6.2
 */