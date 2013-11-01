package com.google.android.apps.unveil.history;

import android.text.TextUtils;
import java.net.URLEncoder;

public class SearchHistoryQuery
{
  public static class AllItemsQuery extends SearchHistoryQuery.QuerySpec
  {
    public AllItemsQuery()
    {
    }

    public AllItemsQuery(boolean paramBoolean)
    {
      super();
    }

    public boolean isQueryForAllItems()
    {
      return true;
    }

    public String queryString()
    {
      return "q=";
    }

    public String toPresentationString()
    {
      return "All Items";
    }
  }

  public static class AllMapsItemsQuery extends SearchHistoryQuery.AllItemsQuery
  {
    public AllMapsItemsQuery()
    {
      super();
    }

    public String queryString()
    {
      return super.queryString() + "&mode=maps";
    }
  }

  public static abstract class QuerySpec
  {
    private final boolean prefersHttps;

    protected QuerySpec()
    {
      this(false);
    }

    protected QuerySpec(boolean paramBoolean)
    {
      this.prefersHttps = paramBoolean;
    }

    public abstract boolean isQueryForAllItems();

    public final boolean prefersHttps()
    {
      return this.prefersHttps;
    }

    public abstract String queryString();

    public abstract String toPresentationString();
  }

  public static class SearchableItemsQuery extends SearchHistoryQuery.AllItemsQuery
  {
    public String queryString()
    {
      return super.queryString() + "&searchable=true";
    }
  }

  public static class StringQuery extends SearchHistoryQuery.QuerySpec
  {
    private final String query;

    public StringQuery(String paramString)
    {
      super();
      this.query = paramString;
    }

    public StringQuery(String paramString, boolean paramBoolean)
    {
      super();
      this.query = paramString;
    }

    public boolean isQueryForAllItems()
    {
      return TextUtils.isEmpty(this.query);
    }

    public String queryString()
    {
      return "q=" + URLEncoder.encode(this.query);
    }

    public String toPresentationString()
    {
      return this.query;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryQuery
 * JD-Core Version:    0.6.2
 */