package com.google.android.apps.unveil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.NeighboringCellInfo;
import android.telephony.TelephonyManager;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.NetworkInfoProtos.MobileNetworkInfo;
import com.google.goggles.NetworkInfoProtos.MobileNetworkInfo.Builder;
import com.google.goggles.NetworkInfoProtos.MobileNetworkInfo.Type;
import com.google.goggles.NetworkInfoProtos.NetworkInfo;
import com.google.goggles.NetworkInfoProtos.NetworkInfo.Builder;
import com.google.goggles.NetworkInfoProtos.NetworkInfo.Type;
import java.util.Iterator;
import java.util.List;

public class NetworkInfoProvider
{
  private static final int MAX_RSSI = 31;
  public static final int MAX_SIGNAL_STRENGTH = 100;
  public static final int SIGNAL_STRENGTH_UNAVAILABLE = -1;
  private static final int WIFI_MANAGER_MAX_LEVELS = 45;
  private static final UnveilLogger logger = new UnveilLogger();
  private final ConnectivityManager connectivityManager;
  private final TelephonyManager telephonyManager;
  private final WifiManager wifiManager;

  public NetworkInfoProvider(Context paramContext)
  {
    this.connectivityManager = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
    this.wifiManager = ((WifiManager)paramContext.getSystemService("wifi"));
    this.telephonyManager = ((TelephonyManager)paramContext.getSystemService("phone"));
  }

  private NetworkInfoProtos.MobileNetworkInfo getMobileNetworkInfo()
  {
    NetworkInfoProtos.MobileNetworkInfo.Builder localBuilder = NetworkInfoProtos.MobileNetworkInfo.newBuilder();
    localBuilder.setCarrierName(this.telephonyManager.getNetworkOperatorName());
    switch (this.telephonyManager.getNetworkType())
    {
    default:
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.OTHER);
    case 7:
    case 4:
    case 2:
    case 5:
    case 6:
    case 1:
    case 8:
    case 10:
    case 9:
    case 3:
    }
    while (true)
    {
      return localBuilder.build();
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.ONExRTT);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.CDMA);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.EDGE);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.EVDO_0);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.EVDO_A);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.GPRS);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.HSDPA);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.HSPA);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.HSUPA);
      continue;
      localBuilder.setType(NetworkInfoProtos.MobileNetworkInfo.Type.UTMS);
    }
  }

  public NetworkInfoProtos.NetworkInfo getNetworkInfo()
  {
    NetworkInfoProtos.NetworkInfo.Builder localBuilder = NetworkInfoProtos.NetworkInfo.newBuilder();
    localBuilder.setType(getNetworkType());
    int i = getSignalStrength();
    if (i != -1)
      localBuilder.setSignalStrength(i);
    if (localBuilder.getType() == NetworkInfoProtos.NetworkInfo.Type.MOBILE)
      localBuilder.setMobileNetworkInfo(getMobileNetworkInfo());
    return localBuilder.build();
  }

  public NetworkInfoProtos.NetworkInfo.Type getNetworkType()
  {
    if ((this.connectivityManager == null) || (this.wifiManager == null) || (this.telephonyManager == null))
      return null;
    NetworkInfo localNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
    if (localNetworkInfo == null)
      return NetworkInfoProtos.NetworkInfo.Type.OTHER;
    switch (localNetworkInfo.getType())
    {
    default:
      return NetworkInfoProtos.NetworkInfo.Type.OTHER;
    case 1:
      return NetworkInfoProtos.NetworkInfo.Type.WIFI;
    case 0:
    }
    return NetworkInfoProtos.NetworkInfo.Type.MOBILE;
  }

  public int getSignalStrength()
  {
    int i;
    if (Build.HARDWARE.equals("goldfish"))
    {
      logger.i("Running in emulator, getSignalStrength() returning SIGNAL_STRENGTH_UNAVAILABLE", new Object[0]);
      i = -1;
    }
    List localList;
    do
    {
      do
      {
        WifiInfo localWifiInfo;
        do
        {
          NetworkInfoProtos.NetworkInfo.Type localType;
          do
          {
            return i;
            localType = getNetworkType();
            i = -1;
          }
          while (localType == null);
          if (localType != NetworkInfoProtos.NetworkInfo.Type.WIFI)
            break;
          localWifiInfo = this.wifiManager.getConnectionInfo();
        }
        while (localWifiInfo == null);
        try
        {
          int m = WifiManager.calculateSignalLevel(localWifiInfo.getRssi(), 46);
          return (int)(2.222222F * m);
        }
        catch (ArithmeticException localArithmeticException)
        {
          logger.e("Failed to calculate wifi signal level " + localArithmeticException.toString(), new Object[0]);
          return i;
        }
      }
      while (this.telephonyManager.getPhoneType() != 1);
      localList = this.telephonyManager.getNeighboringCellInfo();
    }
    while (localList == null);
    int j = -1;
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      int k = ((NeighboringCellInfo)localIterator.next()).getRssi();
      if (k != 99)
        j = Math.max(j, k);
    }
    if (j != -1)
      return j * 100 / 31;
    return -1;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.NetworkInfoProvider
 * JD-Core Version:    0.6.2
 */