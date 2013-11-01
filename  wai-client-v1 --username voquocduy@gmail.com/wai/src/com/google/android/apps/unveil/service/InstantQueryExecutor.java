package com.google.android.apps.unveil.service;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.protocol.QueryListener;
import com.google.android.apps.unveil.protocol.QueryManager;
import java.util.Iterator;
import java.util.List;

public class InstantQueryExecutor extends QueryExecutor
{
  private static final UnveilLogger logger = new UnveilLogger();

  public InstantQueryExecutor(UnveilContext paramUnveilContext, QueryManager paramQueryManager, QueryExecutor.BackgroundQueryFactory paramBackgroundQueryFactory)
  {
    super(paramUnveilContext, paramQueryManager, paramBackgroundQueryFactory);
  }

  public void execute(List<ImageLoader.Image> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      makeQuery((ImageLoader.Image)localIterator.next(), new QueryExecutor.OnBackgroundQueryReadyListener()
      {
        public void onBackgroundQueryReady(BackgroundQuery paramAnonymousBackgroundQuery)
        {
          if (paramAnonymousBackgroundQuery != null)
          {
            QueryListener localQueryListener = InstantQueryExecutor.this.getWrappedBackgroundQueryListener(paramAnonymousBackgroundQuery);
            InstantQueryExecutor.this.send(paramAnonymousBackgroundQuery, localQueryListener);
          }
        }
      });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.InstantQueryExecutor
 * JD-Core Version:    0.6.2
 */