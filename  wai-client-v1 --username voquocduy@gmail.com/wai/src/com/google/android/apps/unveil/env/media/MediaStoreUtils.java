package com.google.android.apps.unveil.env.media;

import android.net.Uri;

public class MediaStoreUtils
{
  public static final String CORRECTED_DATE_TAKEN = "corrected_date_taken";
  public static final String CORRECTED_DATE_TAKEN_EXPR = String.format("case when (datetaken >= %1$d and datetaken < %2$d) then datetaken * 1000 when (datetaken >= %3$d and datetaken < %4$d) then datetaken when (datetaken >= %5$d and datetaken < %6$d) then datetaken / 1000 else 0 end AS %7$s", arrayOfObject);
  public static final long MAX_ALLOWABLE_UTC_MSEC = 1892160000000L;
  public static final long MAX_ALLOWABLE_UTC_SEC = 1892160000L;
  public static final long MAX_ALLOWABLE_UTC_USEC = 1892160000000000L;
  public static final long MAX_ALLOWABLE_YEAR = 2030L;
  public static final long MIN_ALLOWABLE_UTC_MSEC = 157680000000L;
  public static final long MIN_ALLOWABLE_UTC_SEC = 157680000L;
  public static final long MIN_ALLOWABLE_UTC_USEC = 157680000000000L;
  public static final long MIN_ALLOWABLE_YEAR = 1975L;

  static
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = Long.valueOf(157680000L);
    arrayOfObject[1] = Long.valueOf(1892160000L);
    arrayOfObject[2] = Long.valueOf(157680000000L);
    arrayOfObject[3] = Long.valueOf(1892160000000L);
    arrayOfObject[4] = Long.valueOf(157680000000000L);
    arrayOfObject[5] = Long.valueOf(1892160000000000L);
    arrayOfObject[6] = "corrected_date_taken";
  }

  public static boolean isMediaStoreUri(Uri paramUri)
  {
    return ("content".equals(paramUri.getScheme())) && ("media".equals(paramUri.getAuthority()));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.media.MediaStoreUtils
 * JD-Core Version:    0.6.2
 */