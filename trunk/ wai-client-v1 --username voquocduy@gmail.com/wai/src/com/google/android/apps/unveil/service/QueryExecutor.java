package com.google.android.apps.unveil.service;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.protocol.QueryListener;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.WrappingQueryListener;
import java.util.Iterator;
import java.util.List;

public abstract class QueryExecutor
{
  private final BackgroundQueryFactory backgroundQueryFactory;
  private final UnveilContext context;
  private DelegatingPostExecutionHook hooks;
  private final QueryManager queryManager;

  public QueryExecutor(UnveilContext paramUnveilContext, QueryManager paramQueryManager, BackgroundQueryFactory paramBackgroundQueryFactory)
  {
    this.context = paramUnveilContext;
    this.queryManager = paramQueryManager;
    this.backgroundQueryFactory = paramBackgroundQueryFactory;
  }

  public abstract void execute(List<ImageLoader.Image> paramList);

  protected QueryListener getWrappedBackgroundQueryListener(BackgroundQuery paramBackgroundQuery)
  {
    QueryListener localQueryListener = paramBackgroundQuery.queryListener;
    return new WrappingQueryListener(new HookCallingQueryListener(this.hooks, paramBackgroundQuery), localQueryListener);
  }

  protected void makeQuery(ImageLoader.Image paramImage, OnBackgroundQueryReadyListener paramOnBackgroundQueryReadyListener)
  {
    this.backgroundQueryFactory.make(paramImage, paramOnBackgroundQueryReadyListener);
  }

  protected final void send(BackgroundQuery paramBackgroundQuery, QueryListener paramQueryListener)
  {
    this.queryManager.sendQuery(this.context, paramBackgroundQuery.queryParams, paramQueryListener, false);
  }

  protected void setPostExecutionHooks(List<? extends PostExecutionHook> paramList)
  {
    this.hooks = new DelegatingPostExecutionHook(paramList);
  }

  static abstract interface BackgroundQueryFactory
  {
    public abstract void make(ImageLoader.Image paramImage, QueryExecutor.OnBackgroundQueryReadyListener paramOnBackgroundQueryReadyListener);
  }

  private static class DelegatingPostExecutionHook
    implements QueryExecutor.PostExecutionHook
  {
    private final List<? extends QueryExecutor.PostExecutionHook> hooks;

    public DelegatingPostExecutionHook(List<? extends QueryExecutor.PostExecutionHook> paramList)
    {
      this.hooks = paramList;
    }

    public void onQueryFailed(BackgroundQuery paramBackgroundQuery, int paramInt)
    {
      Iterator localIterator = this.hooks.iterator();
      while (localIterator.hasNext())
        ((QueryExecutor.PostExecutionHook)localIterator.next()).onQueryFailed(paramBackgroundQuery, paramInt);
    }

    public void onQuerySucceeded(BackgroundQuery paramBackgroundQuery)
    {
      Iterator localIterator = this.hooks.iterator();
      while (localIterator.hasNext())
        ((QueryExecutor.PostExecutionHook)localIterator.next()).onQuerySucceeded(paramBackgroundQuery);
    }
  }

  private static final class HookCallingQueryListener extends QueryListener
  {
    private final QueryExecutor.PostExecutionHook hook;
    private final BackgroundQuery query;

    public HookCallingQueryListener(QueryExecutor.PostExecutionHook paramPostExecutionHook, BackgroundQuery paramBackgroundQuery)
    {
      this.hook = paramPostExecutionHook;
      this.query = paramBackgroundQuery;
    }

    public void onAuthenticationError()
    {
      this.hook.onQueryFailed(this.query, -1);
    }

    public void onNetworkError(int paramInt)
    {
      this.hook.onQueryFailed(this.query, paramInt);
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      this.hook.onQuerySucceeded(this.query);
    }
  }

  protected static abstract interface OnBackgroundQueryReadyListener
  {
    public abstract void onBackgroundQueryReady(BackgroundQuery paramBackgroundQuery);
  }

  static abstract interface PostExecutionHook
  {
    public abstract void onQueryFailed(BackgroundQuery paramBackgroundQuery, int paramInt);

    public abstract void onQuerySucceeded(BackgroundQuery paramBackgroundQuery);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.QueryExecutor
 * JD-Core Version:    0.6.2
 */