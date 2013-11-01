package com.google.android.apps.unveil.network.utils;

import android.net.TrafficStats;
import android.os.Build.VERSION;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.lang.reflect.Method;

public class Stats
{
  private static final UnveilLogger logger = new UnveilLogger();
  private static Method setThreadTag;

  private Stats()
  {
    throw new UnsupportedOperationException();
  }

  private static Method getSetThreadTagMethod()
    throws SecurityException, NoSuchMethodException
  {
    if (setThreadTag == null)
    {
      Class[] arrayOfClass = new Class[1];
      arrayOfClass[0] = Integer.TYPE;
      setThreadTag = TrafficStats.class.getMethod("setThreadStatsTag", arrayOfClass);
    }
    return setThreadTag;
  }

  private static boolean isTaggingSupported()
  {
    return Build.VERSION.SDK_INT >= 14;
  }

  public static void setThreadTag(Tags paramTags)
  {
    try
    {
      boolean bool = isTaggingSupported();
      if (!bool);
      while (true)
      {
        return;
        try
        {
          Method localMethod = getSetThreadTagMethod();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.valueOf(paramTags.id);
          localMethod.invoke(null, arrayOfObject);
        }
        catch (Exception localException)
        {
          logger.e(localException, "Unexpected reflection failure", new Object[0]);
        }
      }
    }
    finally
    {
    }
  }

  public static enum Tags
  {
    private static final int RESERVED_TAG_MAX = -1;
    private static final int RESERVED_TAG_MIN = -256;
    private final int id;

    static
    {
      CONTINUOUS_PULL = new Tags("CONTINUOUS_PULL", 1, 257);
      THUMBNAIL_FETCH = new Tags("THUMBNAIL_FETCH", 2, 513);
      CONTAINER = new Tags("CONTAINER", 3, 4096);
      TRACING_COOKIE = new Tags("TRACING_COOKIE", 4, 4097);
      SINGLE_SHOT = new Tags("SINGLE_SHOT", 5, 4098);
      REPLAY = new Tags("REPLAY", 6, 4099);
      CLICK_TRACK = new Tags("CLICK_TRACK", 7, 4100);
      TRACE = new Tags("TRACE", 8, 4101);
      Tags[] arrayOfTags = new Tags[9];
      arrayOfTags[0] = CONTINUOUS_PUSH;
      arrayOfTags[1] = CONTINUOUS_PULL;
      arrayOfTags[2] = THUMBNAIL_FETCH;
      arrayOfTags[3] = CONTAINER;
      arrayOfTags[4] = TRACING_COOKIE;
      arrayOfTags[5] = SINGLE_SHOT;
      arrayOfTags[6] = REPLAY;
      arrayOfTags[7] = CLICK_TRACK;
      arrayOfTags[8] = TRACE;
    }

    private Tags(int paramInt)
    {
      if (isReserved(paramInt))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        throw new IllegalArgumentException(String.format("%d is a reserved id, cannot apply to thread", arrayOfObject));
      }
      this.id = paramInt;
    }

    private static boolean isReserved(int paramInt)
    {
      return (paramInt >= -256) && (paramInt <= -1);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.utils.Stats
 * JD-Core Version:    0.6.2
 */