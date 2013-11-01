package com.google.android.apps.unveil.env;

import android.os.Debug;
import android.util.Log;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public final class UnveilLogger
{
  private static final String DEFAULT_TAG = "goggles";
  private static final Set<String> IGNORED_CLASS_NAMES = new HashSet(3);
  private static long previousTime = 0L;
  private static Stopwatch watch;
  private final String messagePrefix;
  private final String tag;

  static
  {
    IGNORED_CLASS_NAMES.add("dalvik.system.VMStack");
    IGNORED_CLASS_NAMES.add("java.lang.Thread");
    IGNORED_CLASS_NAMES.add(UnveilLogger.class.getCanonicalName());
    watch = new Stopwatch();
  }

  public UnveilLogger()
  {
    this("goggles", null);
  }

  public UnveilLogger(Class<?> paramClass)
  {
    this(paramClass.getSimpleName());
  }

  public UnveilLogger(String paramString)
  {
    this("goggles", paramString);
  }

  public UnveilLogger(String paramString1, String paramString2)
  {
    this.tag = paramString1;
    if (paramString2 == null);
    for (String str = getCallerSimpleName(); ; str = paramString2)
    {
      if (str.length() > 0)
        str = str + ": ";
      this.messagePrefix = str;
      return;
    }
  }

  public static void createHeapDump(String paramString)
  {
    try
    {
      Debug.dumpHprofData(paramString);
      Log.e("goggles", "Out of memory error occurred. Please copy the heap dump file to your computer by running\n\n'adb pull " + paramString + "'\n\n" + "and submit it with your bug report.");
      return;
    }
    catch (IOException localIOException)
    {
      Log.e("goggles", "IOException", localIOException);
    }
  }

  public static void createStackTrace(String paramString)
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    Log.e("goggles", "Requested stack trace '" + paramString + "' on thread " + Thread.currentThread().getName() + ":");
    for (int i = 3; i < arrayOfStackTraceElement.length; i++)
      Log.e("goggles", "        at " + arrayOfStackTraceElement[i].toString());
  }

  private static String getCallerSimpleName()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    int i = arrayOfStackTraceElement.length;
    for (int j = 0; j < i; j++)
    {
      String str = arrayOfStackTraceElement[j].getClassName();
      if (!IGNORED_CLASS_NAMES.contains(str))
      {
        String[] arrayOfString = str.split("\\.");
        return arrayOfString[(-1 + arrayOfString.length)];
      }
    }
    return UnveilLogger.class.getSimpleName();
  }

  private boolean shouldShowDebug()
  {
    return Log.isLoggable(this.tag, 3);
  }

  private String toMessage(String paramString, Object[] paramArrayOfObject)
  {
    StringBuilder localStringBuilder = new StringBuilder().append(this.messagePrefix);
    if (paramArrayOfObject.length > 0)
      paramString = String.format(paramString, paramArrayOfObject);
    return paramString;
  }

  public void d(String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowDebug())
      Log.d(this.tag, toMessage(paramString, paramArrayOfObject));
  }

  public void d(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowDebug())
      Log.d(this.tag, toMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public void e(String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 6))
    {
      Log.e(this.tag, toMessage(paramString, paramArrayOfObject));
      TonePlayer.errorTone();
    }
  }

  public void e(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 6))
    {
      Log.e(this.tag, toMessage(paramString, paramArrayOfObject), paramThrowable);
      TonePlayer.errorTone();
    }
  }

  public void i(String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 4))
      Log.i(this.tag, toMessage(paramString, paramArrayOfObject));
  }

  public void i(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 4))
      Log.i(this.tag, toMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public void resetTime(String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowVerbose())
      Log.v(this.tag, this.messagePrefix + "(TIMER) Resetting log timer: " + String.format(paramString, paramArrayOfObject) + " time: 0");
    watch.reset();
    watch.start();
    previousTime = 0L;
  }

  public boolean shouldShowVerbose()
  {
    return false;
  }

  public void time(String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowVerbose())
    {
      if (!watch.isRunning())
        e("Tried to log a time event without starting the timer!", new Object[0]);
    }
    else
      return;
    long l = watch.getElapsedMilliseconds();
    Log.v(this.tag, this.messagePrefix + "(TIMER) " + String.format(paramString, paramArrayOfObject) + " time: " + l + ", delta: " + (l - previousTime));
    previousTime = l;
  }

  public void v(String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowVerbose())
      Log.v(this.tag, toMessage(paramString, paramArrayOfObject));
  }

  public void v(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (shouldShowVerbose())
      Log.v(this.tag, toMessage(paramString, paramArrayOfObject), paramThrowable);
  }

  public void w(String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 5))
      Log.w(this.tag, toMessage(paramString, paramArrayOfObject));
  }

  public void w(Throwable paramThrowable, String paramString, Object[] paramArrayOfObject)
  {
    if (Log.isLoggable(this.tag, 5))
      Log.w(this.tag, toMessage(paramString, paramArrayOfObject), paramThrowable);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.UnveilLogger
 * JD-Core Version:    0.6.2
 */