package com.google.android.apps.unveil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.apps.unveil.env.UnveilLogger;

public class LauncherReceiver extends BroadcastReceiver
{
  private final UnveilLogger logger = new UnveilLogger();

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    this.logger.v("onReceive", new Object[0]);
    UnveilApplication localUnveilApplication = (UnveilApplication)paramContext.getApplicationContext();
    if (localUnveilApplication == null)
    {
      this.logger.e("Could not obtain application context.", new Object[0]);
      return;
    }
    localUnveilApplication.setSettings((UnveilSettings)paramIntent.getExtras().get("UnveilSettings"));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.LauncherReceiver
 * JD-Core Version:    0.6.2
 */