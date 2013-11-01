package com.google.android.apps.unveil.service;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.protocol.QueryListener;
import com.google.android.apps.unveil.protocol.QueryManager;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QueueingQueryExecutor extends QueryExecutor
{
  private static final UnveilLogger logger = new UnveilLogger();
  private boolean isExecuting;
  private final Queue<ImageLoader.Image> pendingQueries;
  private final QueryManager queryManager;

  public QueueingQueryExecutor(UnveilContext paramUnveilContext, QueryExecutor.BackgroundQueryFactory paramBackgroundQueryFactory, QueryManager paramQueryManager, List<? extends QueryExecutor.PostExecutionHook> paramList)
  {
    super(paramUnveilContext, paramQueryManager, paramBackgroundQueryFactory);
    this.queryManager = paramQueryManager;
    this.pendingQueries = new LinkedList();
    LinkedList localLinkedList = new LinkedList(paramList);
    localLinkedList.add(new PollingHook(null));
    setPostExecutionHooks(localLinkedList);
  }

  private void abortQueries()
  {
    try
    {
      logger.i("A background query failed. Aborting execution of the rest of our queries.", new Object[0]);
      this.isExecuting = false;
      this.pendingQueries.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void doExecute(ImageLoader.Image paramImage)
  {
    this.isExecuting = true;
    makeQuery(paramImage, new QueryExecutor.OnBackgroundQueryReadyListener()
    {
      public void onBackgroundQueryReady(BackgroundQuery paramAnonymousBackgroundQuery)
      {
        if (paramAnonymousBackgroundQuery != null)
        {
          QueryListener localQueryListener = QueueingQueryExecutor.this.getWrappedBackgroundQueryListener(paramAnonymousBackgroundQuery);
          QueueingQueryExecutor.this.send(paramAnonymousBackgroundQuery, localQueryListener);
          return;
        }
        QueueingQueryExecutor.this.abortQueries();
      }
    });
  }

  public void execute(List<ImageLoader.Image> paramList)
  {
    try
    {
      if (this.isExecuting)
        logger.i("Ignoring incoming queries because we are still executing the last batch.", new Object[0]);
      while (true)
      {
        return;
        if (!paramList.isEmpty())
        {
          this.pendingQueries.addAll(paramList);
          doExecute((ImageLoader.Image)this.pendingQueries.poll());
        }
      }
    }
    finally
    {
    }
  }

  private class PollingHook
    implements QueryExecutor.PostExecutionHook
  {
    private PollingHook()
    {
    }

    public void onQueryFailed(BackgroundQuery paramBackgroundQuery, int paramInt)
    {
      QueueingQueryExecutor.this.abortQueries();
    }

    public void onQuerySucceeded(BackgroundQuery paramBackgroundQuery)
    {
      QueueingQueryExecutor.logger.i("Background query succeeded; checking for another query to run.", new Object[0]);
      try
      {
        QueueingQueryExecutor.access$202(QueueingQueryExecutor.this, false);
        if (!QueueingQueryExecutor.this.pendingQueries.isEmpty())
          QueueingQueryExecutor.this.doExecute((ImageLoader.Image)QueueingQueryExecutor.this.pendingQueries.poll());
        return;
      }
      finally
      {
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.QueueingQueryExecutor
 * JD-Core Version:    0.6.2
 */