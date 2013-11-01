package com.google.android.apps.unveil.history;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ItemProvider
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final String title;

  public ItemProvider(String paramString)
  {
    this.title = paramString;
  }

  public static ItemProvider of(String paramString, SavedQueryProvider paramSavedQueryProvider)
  {
    return new SavedQueryItemProvider(paramString, paramSavedQueryProvider.getQueries());
  }

  public static ItemProvider of(String paramString, SearchHistoryProvider paramSearchHistoryProvider, SearchHistoryQuery.QuerySpec paramQuerySpec, SearchListener paramSearchListener)
  {
    return new QuerySpecProvider(paramString, paramSearchHistoryProvider, paramQuerySpec, paramSearchListener);
  }

  public static ItemProvider of(String paramString, List<? extends ItemModel> paramList)
  {
    return new LocalItemProvider(paramString, paramList);
  }

  public static ItemProvider of(String paramString, List<String> paramList, Context paramContext)
  {
    return new SfCItemProvider(paramString, paramList, paramContext);
  }

  public abstract ItemModel get(int paramInt);

  public abstract int getCount();

  public String getTitle(Resources paramResources)
  {
    return this.title;
  }

  public static abstract class AsyncItemProvider extends ItemProvider
  {
    private final List<Callback> callbacks = new LinkedList();

    public AsyncItemProvider(String paramString)
    {
      super();
    }

    public final void addCallback(Callback paramCallback)
    {
      this.callbacks.add(paramCallback);
    }

    public abstract boolean hasMore();

    public abstract void loadMore();

    protected void onError()
    {
      Iterator localIterator = this.callbacks.iterator();
      while (localIterator.hasNext())
        ((Callback)localIterator.next()).onError();
    }

    protected void onQuerying()
    {
      Iterator localIterator = this.callbacks.iterator();
      while (localIterator.hasNext())
        ((Callback)localIterator.next()).onQuerying();
    }

    protected void onSuccess()
    {
      Iterator localIterator = this.callbacks.iterator();
      while (localIterator.hasNext())
        ((Callback)localIterator.next()).onSuccess();
    }

    public abstract void startLoading();

    static abstract interface Callback
    {
      public abstract void onError();

      public abstract void onQuerying();

      public abstract void onSuccess();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.ItemProvider
 * JD-Core Version:    0.6.2
 */