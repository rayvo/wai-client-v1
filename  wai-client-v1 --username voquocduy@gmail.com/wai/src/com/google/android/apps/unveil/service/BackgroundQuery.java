package com.google.android.apps.unveil.service;

import android.graphics.Bitmap;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryListener;

public class BackgroundQuery
{
  public final Bitmap queryImage;
  public final QueryListener queryListener;
  public final QueryBuilder queryParams;
  public final long timestamp;

  protected BackgroundQuery(QueryBuilder paramQueryBuilder, Bitmap paramBitmap, long paramLong, QueryListener paramQueryListener)
  {
    this.queryParams = paramQueryBuilder;
    this.queryImage = paramBitmap;
    this.timestamp = paramLong;
    this.queryListener = paramQueryListener;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.BackgroundQuery
 * JD-Core Version:    0.6.2
 */