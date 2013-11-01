package com.google.android.apps.unveil.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.env.UnveilLogger;

public class LaunchReceiver extends BroadcastReceiver
{
  private static final UnveilLogger logger = new UnveilLogger();

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramIntent.getData();
    localUnveilLogger.d("Broadcast received: %s", arrayOfObject);
    if (!Settings.getBoolean(paramContext, R.string.background_goggle_key))
    {
      logger.d("Background Goggling disabled. Bailing out.", new Object[0]);
      return;
    }
    if ("android.net.conn.CONNECTIVITY_CHANGE".equals(paramIntent.getAction()))
    {
      if (paramIntent.getBooleanExtra("noConnectivity", false))
      {
        logger.d("No connectivity, don't schedule polling for search from camera", new Object[0]);
        return;
      }
      logger.d("Connectivity changed, schedule polling for search from camera", new Object[0]);
    }
    paramContext.startService(PictureRequestService.makePollIntent(paramContext));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.LaunchReceiver
 * JD-Core Version:    0.6.2
 */