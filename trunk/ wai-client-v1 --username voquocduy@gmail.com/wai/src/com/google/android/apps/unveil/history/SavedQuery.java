package com.google.android.apps.unveil.history;

import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryBuilder;

public class SavedQuery extends ItemModel
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final long id;
  private final QueryBuilder queryParameters;
  private final long timestamp;

  public SavedQuery(QueryBuilder paramQueryBuilder, long paramLong1, long paramLong2)
  {
    this.queryParameters = paramQueryBuilder;
    this.id = paramLong1;
    this.timestamp = paramLong2;
  }

  public Picture getCachedQueryImage()
  {
    return this.queryParameters.toPicture();
  }

  public long getId()
  {
    return this.id;
  }

  public QueryBuilder getQueryParameters()
  {
    return this.queryParameters;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SavedQuery
 * JD-Core Version:    0.6.2
 */