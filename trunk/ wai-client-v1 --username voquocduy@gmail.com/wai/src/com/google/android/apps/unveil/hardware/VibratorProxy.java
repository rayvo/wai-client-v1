package com.google.android.apps.unveil.hardware;

import android.content.Context;
import android.os.Vibrator;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class VibratorProxy
{
  public static final int SHORT_VIBRATE_MS = 100;
  private static final UnveilLogger logger = new UnveilLogger();
  private Vibrator vibrator;

  public VibratorProxy(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.vibrator = ((Vibrator)paramContext.getSystemService("vibrator"));
    Method[] arrayOfMethod = Vibrator.class.getDeclaredMethods();
    int i = arrayOfMethod.length;
    int j = 0;
    while (j < i)
    {
      Method localMethod = arrayOfMethod[j];
      if (localMethod.getName().equals("hasVibrator"));
      try
      {
        if (!((Boolean)localMethod.invoke(this.vibrator, new Object[0])).booleanValue())
          this.vibrator = null;
        j++;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        while (true)
          logger.e(localIllegalArgumentException, "Error creating vibrator proxy!", new Object[0]);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        while (true)
          logger.e(localIllegalAccessException, "Error creating vibrator proxy!", new Object[0]);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        while (true)
          logger.e(localInvocationTargetException, "Error creating vibrator proxy!", new Object[0]);
      }
    }
  }

  public boolean hasVibrator()
  {
    return this.vibrator != null;
  }

  public void vibrate(long paramLong)
  {
    if (hasVibrator())
      this.vibrator.vibrate(paramLong);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.hardware.VibratorProxy
 * JD-Core Version:    0.6.2
 */