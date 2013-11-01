package com.google.android.apps.unveil.history;

import android.graphics.Rect;
import com.google.android.apps.unveil.env.AbstractProvider;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SearchHistoryProvider extends AbstractProvider
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final SearchHistoryEngine engine;

  public SearchHistoryProvider(SearchHistoryEngine paramSearchHistoryEngine)
  {
    this.engine = paramSearchHistoryEngine;
  }

  public static QueryToken makeQuery(SearchHistoryQuery.QuerySpec paramQuerySpec, QueryListener paramQueryListener)
  {
    return new QueryToken(paramQuerySpec, paramQueryListener);
  }

  private void update(String paramString1, String paramString2, String paramString3, final UpdateListener paramUpdateListener)
  {
    this.engine.update(paramString1, paramString2, paramString3, new SearchHistoryEngine.EventListener()
    {
      public void onError()
      {
        paramUpdateListener.onError();
      }

      public void onUpdateResponse(SearchHistoryItem paramAnonymousSearchHistoryItem)
      {
        paramUpdateListener.onResult(paramAnonymousSearchHistoryItem);
        SearchHistoryProvider.this.notifyListenersOfChange();
      }
    });
  }

  public void delete(String paramString, final DeleteListener paramDeleteListener)
  {
    this.engine.delete(paramString, new SearchHistoryEngine.EventListener()
    {
      public void onDeleteResponse()
      {
        paramDeleteListener.onResult();
        SearchHistoryProvider.this.notifyListenersOfChange();
      }

      public void onError()
      {
        paramDeleteListener.onError();
      }
    });
  }

  public SearchHistoryEngine getEngine()
  {
    return this.engine;
  }

  public void lookup(String paramString, final LookupListener paramLookupListener)
  {
    this.engine.lookup(paramString, new SearchHistoryEngine.EventListener()
    {
      public void onError()
      {
        paramLookupListener.onError();
      }

      public void onLookupResponse(SearchHistoryItem paramAnonymousSearchHistoryItem)
      {
        paramLookupListener.onResult(paramAnonymousSearchHistoryItem);
      }
    });
  }

  public void query(final QueryToken paramQueryToken)
  {
    try
    {
      if (!paramQueryToken.hasMore())
        return;
      int i = paramQueryToken.results.size();
      if (paramQueryToken.hasRequestedMore)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        localUnveilLogger.d("Ignore duplicated retrieve request for search history item @%d", arrayOfObject);
        return;
      }
    }
    finally
    {
    }
    QueryToken.access$002(paramQueryToken, true);
    paramQueryToken.lastContinuationToken = paramQueryToken.nextContinuationToken;
    paramQueryToken.nextContinuationToken = "";
    this.engine.search(paramQueryToken.getQuerySpec(), paramQueryToken.lastContinuationToken, paramQueryToken.getBatchSize(), new SearchHistoryEngine.EventListener()
    {
      public void onError()
      {
        synchronized (paramQueryToken)
        {
          SearchHistoryProvider.QueryToken.access$002(paramQueryToken, false);
          if (SearchHistoryProvider.QueryToken.access$100(paramQueryToken))
            return;
          paramQueryToken.listener.onError();
          return;
        }
      }

      public void onSearchResponse(SearchHistoryItem[] paramAnonymousArrayOfSearchHistoryItem, int paramAnonymousInt, String paramAnonymousString)
      {
        synchronized (paramQueryToken)
        {
          if (SearchHistoryProvider.QueryToken.access$100(paramQueryToken))
            return;
          int i = paramQueryToken.results.size();
          SearchHistoryProvider.QueryToken.access$002(paramQueryToken, false);
          paramQueryToken.nextContinuationToken = paramAnonymousString;
          int j = paramAnonymousArrayOfSearchHistoryItem.length;
          for (int k = 0; k < j; k++)
          {
            SearchHistoryItem localSearchHistoryItem = paramAnonymousArrayOfSearchHistoryItem[k];
            paramQueryToken.results.add(localSearchHistoryItem);
          }
          if (paramAnonymousArrayOfSearchHistoryItem.length == 0)
          {
            SearchHistoryProvider.QueryToken.access$202(paramQueryToken, paramQueryToken.results.size());
            paramQueryToken.listener.onResults(paramQueryToken, i);
            return;
          }
          SearchHistoryProvider.QueryToken.access$202(paramQueryToken, paramAnonymousInt);
        }
      }
    });
  }

  public void reportAbuse(String paramString1, String paramString2, String paramString3, boolean paramBoolean, final AbuseListener paramAbuseListener)
  {
    this.engine.reportAbuse(paramString1, paramString2, paramString3, paramBoolean, new SearchHistoryEngine.EventListener()
    {
      public void onAbuseResponse()
      {
        paramAbuseListener.onResult();
        SearchHistoryProvider.this.notifyListenersOfChange();
      }

      public void onError()
      {
        paramAbuseListener.onError();
      }
    });
  }

  public void setUserSubmission(String paramString1, String paramString2, String paramString3, String paramString4, Rect paramRect, final TagListener paramTagListener)
  {
    this.engine.setUserSubmission(paramString1, paramString2, paramString3, paramString4, paramRect, new SearchHistoryEngine.EventListener()
    {
      public void onError()
      {
        paramTagListener.onError();
      }

      public void onUserSubmissionResponse()
      {
        paramTagListener.onResult();
        SearchHistoryProvider.this.notifyListenersOfChange();
      }
    });
  }

  public void share(String paramString, UpdateListener paramUpdateListener)
  {
    update(paramString, "isShared", "true", paramUpdateListener);
  }

  public void updateNote(String paramString1, String paramString2, UpdateListener paramUpdateListener)
  {
    update(paramString1, "note", paramString2, paramUpdateListener);
  }

  public void withdrawUserSubmission(String paramString, final TagListener paramTagListener)
  {
    this.engine.withdrawUserSubmission(paramString, new SearchHistoryEngine.EventListener()
    {
      public void onError()
      {
        paramTagListener.onError();
      }

      public void onUserSubmissionResponse()
      {
        paramTagListener.onResult();
        SearchHistoryProvider.this.notifyListenersOfChange();
      }
    });
  }

  public static abstract interface AbuseListener
  {
    public abstract void onError();

    public abstract void onResult();
  }

  public static abstract interface DeleteListener
  {
    public abstract void onError();

    public abstract void onResult();
  }

  public static abstract interface LookupListener
  {
    public abstract void onError();

    public abstract void onResult(SearchHistoryItem paramSearchHistoryItem);
  }

  public static abstract interface QueryListener
  {
    public abstract void onError();

    public abstract void onResults(SearchHistoryProvider.QueryToken paramQueryToken, int paramInt);
  }

  public static class QueryToken
  {
    static final int DEFAULT_BATCH_COUNT = 10;
    private int approximateTotalCount;
    private int batchSize = 10;
    private boolean hasRequestedMore;
    public String lastContinuationToken = null;
    protected SearchHistoryProvider.QueryListener listener;
    public String nextContinuationToken = "";
    private final SearchHistoryQuery.QuerySpec querySpec;
    private boolean recycled;
    protected final ArrayList<SearchHistoryItem> results;

    QueryToken(SearchHistoryQuery.QuerySpec paramQuerySpec, SearchHistoryProvider.QueryListener paramQueryListener)
    {
      SearchHistoryProvider.logger.i("Creating a new queryToken.", new Object[0]);
      this.results = new ArrayList();
      this.querySpec = paramQuerySpec;
      this.hasRequestedMore = false;
      this.approximateTotalCount = -1;
      this.listener = paramQueryListener;
      this.recycled = false;
    }

    public int getApproximateTotalCount()
    {
      if (hasCommunicatedWithServer())
        return this.approximateTotalCount;
      return 0;
    }

    public int getBatchSize()
    {
      return this.batchSize;
    }

    public SearchHistoryQuery.QuerySpec getQuerySpec()
    {
      return this.querySpec;
    }

    public List<SearchHistoryItem> getResults()
    {
      try
      {
        List localList = Collections.unmodifiableList(this.results);
        return localList;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public boolean hasCommunicatedWithServer()
    {
      return this.approximateTotalCount != -1;
    }

    public boolean hasMore()
    {
      try
      {
        if (hasCommunicatedWithServer())
        {
          if (this.lastContinuationToken != null)
          {
            boolean bool2 = this.lastContinuationToken.equals(this.nextContinuationToken);
            if (bool2);
          }
        }
        else
        {
          bool1 = true;
          return bool1;
        }
        boolean bool1 = false;
      }
      finally
      {
      }
    }

    public boolean isQueryForAllItems()
    {
      try
      {
        boolean bool = this.querySpec.isQueryForAllItems();
        return bool;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void recycle()
    {
      try
      {
        this.recycled = true;
        this.results.clear();
        this.listener = null;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void setBatchSize(int paramInt)
    {
      this.batchSize = paramInt;
    }

    public String toString()
    {
      try
      {
        String str = "queryString(" + this.querySpec.queryString() + "), " + "hasRequestedMore(" + this.hasRequestedMore + "), " + "totalCount(" + this.approximateTotalCount + "), " + "recycled(" + this.recycled + ")";
        return str;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }

  public static abstract interface TagListener
  {
    public abstract void onError();

    public abstract void onResult();
  }

  public static abstract interface UpdateListener
  {
    public abstract void onError();

    public abstract void onResult(SearchHistoryItem paramSearchHistoryItem);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryProvider
 * JD-Core Version:    0.6.2
 */