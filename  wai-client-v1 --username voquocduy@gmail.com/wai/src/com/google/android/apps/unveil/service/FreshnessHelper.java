package com.google.android.apps.unveil.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

class FreshnessHelper
{
  private static final String LAST_SEEN_TIMESTAMP_KEY = "lastSeen";
  private static final UnveilLogger logger = new UnveilLogger();
  private final SharedPreferences preferences;

  public FreshnessHelper(SharedPreferences paramSharedPreferences)
  {
    this.preferences = paramSharedPreferences;
  }

  private void setLatestSeenTimestamp(long paramLong)
  {
    this.preferences.edit().putLong("lastSeen", paramLong).commit();
  }

  public List<ImageLoader.Image> getFreshImages(List<ImageLoader.Image> paramList)
  {
    LinkedList localLinkedList = new LinkedList();
    long l = getLatestSeenTimestamp();
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(l);
    localUnveilLogger.i("Checking for images newer than %d", arrayOfObject);
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ImageLoader.Image localImage = (ImageLoader.Image)localIterator.next();
      if (localImage.dateTaken > l)
        localLinkedList.add(localImage);
    }
    return localLinkedList;
  }

  public long getLatestSeenTimestamp()
  {
    long l1 = System.currentTimeMillis();
    long l2 = this.preferences.getLong("lastSeen", l1);
    if (l2 == l1)
      setLatestSeenTimestamp(l1);
    return l2;
  }

  public void setFreshnessTimestamp(long paramLong)
  {
    setLatestSeenTimestamp(paramLong);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.FreshnessHelper
 * JD-Core Version:    0.6.2
 */