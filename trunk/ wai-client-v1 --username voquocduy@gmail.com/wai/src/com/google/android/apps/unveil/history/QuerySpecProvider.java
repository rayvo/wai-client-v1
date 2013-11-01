package com.google.android.apps.unveil.history;

import android.content.res.Resources;
import com.google.android.apps.unveil.R.plurals;
import com.google.android.apps.unveil.R.string;
import java.util.Collections;
import java.util.List;

class QuerySpecProvider extends ItemProvider.AsyncItemProvider
{
  private static final NoResultsItem NO_HISTORY_YET_ITEM = new NoResultsItem(R.string.no_history_yet);
  private static final NoResultsItem NO_RESULTS_ITEM = new NoResultsItem(R.string.no_results);
  private static final NoResultsItem NO_UGC_YET_ITEM = new NoResultsItem(R.string.no_ugc_yet);
  private final SearchHistoryProvider historyProvider;
  private final SearchHistoryQuery.QuerySpec querySpec;
  private final SearchListener searchListener;
  private final SearchHistoryProvider.QueryToken token;

  public QuerySpecProvider(String paramString, SearchHistoryProvider paramSearchHistoryProvider, SearchHistoryQuery.QuerySpec paramQuerySpec, SearchListener paramSearchListener)
  {
    super(paramString);
    this.historyProvider = paramSearchHistoryProvider;
    this.querySpec = paramQuerySpec;
    this.searchListener = paramSearchListener;
    this.token = SearchHistoryProvider.makeQuery(paramQuerySpec, new CallbacksQueryListener(null));
  }

  private void dispenseResults(SearchHistoryProvider.QueryToken paramQueryToken)
  {
    notifySearchListener(paramQueryToken);
    onSuccess();
  }

  private List<? extends ItemModel> getCurrentItems()
  {
    if ((!this.token.hasMore()) && (this.token.getApproximateTotalCount() == 0))
    {
      if (!this.querySpec.isQueryForAllItems())
        return Collections.singletonList(NO_RESULTS_ITEM);
      if ((this.querySpec instanceof SearchHistoryQuery.SearchableItemsQuery))
        return Collections.singletonList(NO_UGC_YET_ITEM);
      return Collections.singletonList(NO_HISTORY_YET_ITEM);
    }
    return this.token.getResults();
  }

  private String getStateDependentTitle(Resources paramResources)
  {
    if (this.token.hasCommunicatedWithServer())
      return getTitleForToken(paramResources, this.querySpec.toPresentationString(), this.token.getApproximateTotalCount());
    return paramResources.getString(R.string.loading);
  }

  private static String getTitleForToken(Resources paramResources, String paramString, int paramInt)
  {
    int i = R.plurals.results_for_query;
    if (paramInt == 1)
      return paramResources.getQuantityString(i, paramInt, new Object[] { paramString });
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    arrayOfObject[1] = paramString;
    return paramResources.getQuantityString(i, paramInt, arrayOfObject);
  }

  private void notifySearchListener(SearchHistoryProvider.QueryToken paramQueryToken)
  {
    if ((!paramQueryToken.hasMore()) && (paramQueryToken.getApproximateTotalCount() == 0))
    {
      if (!this.querySpec.isQueryForAllItems())
      {
        this.searchListener.onSearchResultsNoResults(NO_RESULTS_ITEM.messageId);
        return;
      }
      this.searchListener.onSearchResultsNoResults(NO_HISTORY_YET_ITEM.messageId);
      return;
    }
    this.searchListener.onSearchCompleted(this.querySpec, paramQueryToken.getApproximateTotalCount());
  }

  private void runQueryOnBackground()
  {
    onQuerying();
    this.historyProvider.query(this.token);
  }

  public ItemModel get(int paramInt)
  {
    return (ItemModel)getCurrentItems().get(paramInt);
  }

  public int getCount()
  {
    return getCurrentItems().size();
  }

  public String getTitle(Resources paramResources)
  {
    if ((this.querySpec instanceof SearchHistoryQuery.StringQuery))
      return getStateDependentTitle(paramResources);
    return super.getTitle(paramResources);
  }

  public boolean hasMore()
  {
    return this.token.hasMore();
  }

  public void loadMore()
  {
    this.historyProvider.query(this.token);
  }

  public void startLoading()
  {
    runQueryOnBackground();
  }

  private final class CallbacksQueryListener
    implements SearchHistoryProvider.QueryListener
  {
    private CallbacksQueryListener()
    {
    }

    public void onError()
    {
      QuerySpecProvider.this.onError();
    }

    public void onResults(SearchHistoryProvider.QueryToken paramQueryToken, int paramInt)
    {
      QuerySpecProvider.this.dispenseResults(paramQueryToken);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.QuerySpecProvider
 * JD-Core Version:    0.6.2
 */