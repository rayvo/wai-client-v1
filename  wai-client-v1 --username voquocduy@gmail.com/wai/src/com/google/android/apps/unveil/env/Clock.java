package com.google.android.apps.unveil.env;

import android.os.SystemClock;

public abstract class Clock
{
  public static final Clock SYSTEM_CLOCK = new SystemClock(null);

  public abstract long now();

  private static class SystemClock extends Clock
  {
    public long now()
    {
      return SystemClock.uptimeMillis();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Clock
 * JD-Core Version:    0.6.2
 */