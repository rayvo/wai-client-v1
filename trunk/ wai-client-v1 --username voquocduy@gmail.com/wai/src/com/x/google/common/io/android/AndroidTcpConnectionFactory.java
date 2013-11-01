package com.x.google.common.io.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;

public class AndroidTcpConnectionFactory extends J2SeTcpConnectionFactory
{
  private ConnectivityManager cm;

  public AndroidTcpConnectionFactory(Context paramContext)
  {
    this.cm = ((ConnectivityManager)paramContext.getSystemService("connectivity"));
  }

  public int isNetworkAvailable()
  {
    NetworkInfo.State localState = this.cm.getActiveNetworkInfo().getState();
    if (localState == NetworkInfo.State.CONNECTED)
      return 1;
    if (localState == NetworkInfo.State.CONNECTING)
      return 2;
    return 0;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.common.io.android.AndroidTcpConnectionFactory
 * JD-Core Version:    0.6.2
 */