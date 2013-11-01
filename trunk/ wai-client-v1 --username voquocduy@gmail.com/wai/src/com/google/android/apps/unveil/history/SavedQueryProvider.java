package com.google.android.apps.unveil.history;

import com.google.android.apps.unveil.env.AbstractProvider;
import com.google.android.apps.unveil.protocol.QueryBuilder;

public abstract class SavedQueryProvider extends AbstractProvider
{
  protected static SavedQueryCursor newEmptyCursor()
  {
    return new EmptyCursor(null);
  }

  public abstract void delete(long paramLong);

  public abstract SavedQuery get(long paramLong);

  public abstract SavedQueryCursor getQueries();

  public abstract int getQueryCount();

  public abstract void onQueryExecuted(long paramLong);

  public abstract Long save(QueryBuilder paramQueryBuilder);

  private static final class EmptyCursor
    implements SavedQueryProvider.SavedQueryCursor
  {
    public void close()
    {
    }

    public int getCount()
    {
      return 0;
    }

    public boolean hasNext()
    {
      return false;
    }

    public void moveTo(int paramInt)
    {
    }

    public SavedQuery next()
    {
      return null;
    }
  }

  public static abstract interface SavedQueryCursor
  {
    public abstract void close();

    public abstract int getCount();

    public abstract boolean hasNext();

    public abstract void moveTo(int paramInt);

    public abstract SavedQuery next();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SavedQueryProvider
 * JD-Core Version:    0.6.2
 */