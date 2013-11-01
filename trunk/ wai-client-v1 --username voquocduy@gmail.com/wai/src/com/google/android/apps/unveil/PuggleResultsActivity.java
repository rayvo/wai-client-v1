package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.apps.unveil.env.Check;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.puggle.ParcelableQuery;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryPipeline.NoPendingQueryException;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.restricts.QueryRefiner;
import com.google.android.apps.unveil.restricts.QueryRestricts;
import com.google.android.apps.unveil.restricts.RestrictType;
import com.google.android.apps.unveil.restricts.SuggestionsListener;
import com.google.android.apps.unveil.results.PuggleResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.results.utils.Sorter;
import com.google.android.apps.unveil.results.utils.Sorter.SortedResultsCallback;
import com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter;
import com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter.PuggleResultClickListener;
import com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter.ViewType;
import com.google.android.apps.unveil.ui.puggle.PuggleResultsView;
import com.google.android.apps.unveil.ui.puggle.QuerySummaryView;
import com.google.android.apps.unveil.ui.puggle.QuerySummaryView.CategorySelectedListener;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.MoreLikeThisActionProtos.MoreLikeThisAction;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Restricts;
import com.google.goggles.RestrictsProtos.Restricts.Builder;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PuggleResultsActivity extends MomentActivity
{
  private static final String BUNDLE_LAST_QUERY = "PuggleResultsActivity.lastQuery";
  private static final String BUNDLE_SORTER_STATE = "PuggleResultsActivity.sorter_state";
  private static final UnveilLogger logger = new UnveilLogger();
  private ParcelableQuery lastQuery;
  private PrettyNameClickListener prettyNameClickListener;
  private PuggleResultAdapter puggleResultAdapter;
  private ArrayList<PuggleResultItem> puggleResults;
  private PuggleResultsView puggleResultsView;
  private QuerySummaryView querySummaryView;
  private ResultClickListener resultClickListener;
  private Sorter sorter;

  private void refine()
  {
    this.application.getTraceTracker().addPoint(TracingProtos.PointVariable.Type.PUGGLE);
    this.application.getTraceTracker().beginInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
    Intent localIntent = new Intent(this, RunQueryActivity.class);
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.PROCESSED_IMAGE);
    startActivity(localIntent);
  }

  private void setViewTypeDisplay(PuggleResultAdapter.ViewType paramViewType)
  {
    TextView localTextView = (TextView)findViewById(R.id.view_toolbar_button);
    if (paramViewType == PuggleResultAdapter.ViewType.GRID)
    {
      this.puggleResultAdapter.setViewType(PuggleResultAdapter.ViewType.GRID);
      localTextView.setText(R.string.view_grid);
      Settings.putBoolean(this.application, R.string.should_show_results_as_grid_key, true);
      return;
    }
    this.puggleResultAdapter.setViewType(PuggleResultAdapter.ViewType.LIST);
    localTextView.setText(R.string.view_list);
    Settings.putBoolean(this.application, R.string.should_show_results_as_grid_key, false);
  }

  private void setupPuggleResults(List<ResultItem> paramList)
  {
    this.puggleResults = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ResultItem localResultItem = (ResultItem)localIterator.next();
      this.puggleResults.add((PuggleResultItem)localResultItem);
    }
  }

  private void setupPuggleResultsView()
  {
    if (this.puggleResultAdapter == null)
      this.puggleResultAdapter = new PuggleResultAdapter(this.sorter.sort(this.puggleResults), this.application.getThumbnailCache(), this.resultClickListener);
    this.puggleResultsView.setAdapter(this.puggleResultAdapter);
  }

  private void setupSort()
  {
    this.sorter.setOnSortedResults(new Sorter.SortedResultsCallback()
    {
      public void onSortedResults(String paramAnonymousString, List<PuggleResultItem> paramAnonymousList)
      {
        if (TextUtils.equals(paramAnonymousString, PuggleResultsActivity.this.getString(R.string.sort_price_ascending)))
          PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_SORT_BY_LOW_PRICE);
        while (true)
        {
          PuggleResultsActivity.this.puggleResultAdapter.setResults(paramAnonymousList);
          return;
          if (TextUtils.equals(paramAnonymousString, PuggleResultsActivity.this.getString(R.string.sort_price_descending)))
            PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_SORT_BY_HIGH_PRICE);
          else
            PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_SORT_BY_RELEVANCE);
        }
      }
    });
    ((TextView)findViewById(R.id.sort_toolbar_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        PuggleResultsActivity.this.sorter.show(PuggleResultsActivity.this.puggleResults);
      }
    });
  }

  private void setupViewType()
  {
    if (Settings.getBoolean(this.application, R.string.should_show_results_as_grid_key, true));
    for (PuggleResultAdapter.ViewType localViewType = PuggleResultAdapter.ViewType.GRID; ; localViewType = PuggleResultAdapter.ViewType.LIST)
    {
      setViewTypeDisplay(localViewType);
      ((TextView)findViewById(R.id.view_toolbar_button)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          PuggleResultsActivity.this.showViewTypeDialog(PuggleResultsActivity.this);
        }
      });
      return;
    }
  }

  protected void displayQuerySummary()
  {
    this.querySummaryView.setCategoryClickListener(this.prettyNameClickListener);
    this.querySummaryView.setQuery(getLastQuery(), getLastResponse());
  }

  protected void displayResults(Picture paramPicture, QueryResponse paramQueryResponse)
  {
    if (this.puggleResults == null)
      setupPuggleResults(paramQueryResponse.getPuggleResults());
    if (!this.puggleResults.isEmpty())
      setupPuggleResultsView();
    setupViewType();
    displaySuggestedRestricts(paramQueryResponse.getSuggestedRestricts());
    this.application.getTraceTracker().endInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
  }

  public void displaySelectedRestricts(RestrictsProtos.Restricts paramRestricts)
  {
    TextView localTextView = (TextView)findViewById(R.id.restricts);
    if (!new QueryRestricts(paramRestricts).isEmpty())
    {
      localTextView.setVisibility(0);
      localTextView.setText(QueryRestricts.getSummaryString(paramRestricts, this));
    }
  }

  public void displaySuggestedRestricts(RestrictsProtos.Restricts paramRestricts)
  {
    TextView localTextView = (TextView)findViewById(R.id.filter_toolbar_button);
    final QueryRefiner localQueryRefiner = new QueryRefiner(this, paramRestricts, getLastQuery().getRestricts(), getLastResponse().getPuggleResults());
    localQueryRefiner.setOnClickListener(new SuggestionsListener()
    {
      public boolean onClearClick()
      {
        PuggleResultsActivity.this.refineQueryWithRestricts(RestrictsProtos.Restricts.newBuilder().build());
        localQueryRefiner.close();
        PuggleResultsActivity.this.finish();
        return false;
      }

      public boolean onRestrictClick(RestrictsProtos.Restricts paramAnonymousRestricts)
      {
        PuggleResultsActivity.this.refineQueryWithRestricts(paramAnonymousRestricts);
        localQueryRefiner.close();
        PuggleResultsActivity.this.finish();
        return false;
      }
    });
    if (localQueryRefiner.isEmpty())
    {
      localTextView.setEnabled(false);
      return;
    }
    localTextView.setVisibility(0);
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        localQueryRefiner.show();
      }
    });
  }

  protected ParcelableQuery getLastQuery()
  {
    return this.lastQuery;
  }

  protected String getMomentId()
  {
    return null;
  }

  protected List<? extends ResultModel> getResultsToExpand()
  {
    return this.puggleResultAdapter.getResults();
  }

  protected boolean handleBackButtonPress()
  {
    return false;
  }

  protected void handleHideBackOfCard()
  {
  }

  protected void handleShowBackOfCard()
  {
  }

  protected boolean hasExpectedResponses(QueryResponse paramQueryResponse)
  {
    return paramQueryResponse.hasPuggleResults();
  }

  protected void onActionShowResults(Intent paramIntent)
  {
    if (paramIntent.hasExtra("search_history_item"))
      setLastQuery(new ParcelableQuery(((SearchHistoryItem)paramIntent.getSerializableExtra("search_history_item")).getTitle(), this.queryPipeline.getPicture(), null));
    while (true)
    {
      setupViews();
      return;
      try
      {
        setLastQuery(new ParcelableQuery(((PuggleQueryBuilder)this.queryPipeline.getPendingQuery()).getText(), this.queryPipeline.getPicture(), this.queryPipeline.getPendingQuery().getRestricts()));
      }
      catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
      {
        Toast.makeText(this, R.string.error_missing_query, 0).show();
        finish();
        localNoPendingQueryException.printStackTrace();
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    String str = null;
    if (paramBundle != null)
    {
      setLastQuery((ParcelableQuery)paramBundle.getParcelable("PuggleResultsActivity.lastQuery"));
      str = paramBundle.getString("PuggleResultsActivity.sorter_state");
    }
    this.sorter = Sorter.newSorter(this, str);
    setContentView(R.layout.puggle_results_page);
    this.puggleResultsView = ((PuggleResultsView)findViewById(R.id.puggle_results_view));
    this.querySummaryView = new QuerySummaryView(this);
    this.puggleResultsView.addHeaderView(this.querySummaryView);
    this.resultClickListener = new ResultClickListener(null);
    this.prettyNameClickListener = new PrettyNameClickListener(null);
    setupSort();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    paramMenu.findItem(R.id.share).setVisible(false);
    paramMenu.findItem(R.id.note).setVisible(false);
    return true;
  }

  public void onResultSelected(ResultModel paramResultModel)
  {
    showExpandedResults(paramResultModel);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (getLastQuery() != null)
    {
      paramBundle.putParcelable("PuggleResultsActivity.lastQuery", getLastQuery());
      paramBundle.putString("PuggleResultsActivity.sorter_state" + getMomentId(), this.sorter.getDisplayString());
    }
  }

  protected void onStart()
  {
    super.onStart();
    if ((getLastQuery() != null) && (getLastResponse() != null))
      setupViews();
    Intent localIntent;
    do
    {
      return;
      localIntent = getIntent();
    }
    while (!"com.google.android.apps.unveil.show_results".equals(localIntent.getAction()));
    handleActionShowResults(localIntent);
  }

  void refineQuery(Picture paramPicture, ResultItem paramResultItem)
  {
    this.queryPipeline.startNewQuery();
    this.queryPipeline.setPendingQuery(getLastQuery().toPuggleQueryBuilder().addRestricts(paramResultItem.getAnnotationResult().getMoreLikeThisAction().getRestricts()).addImageUrl(paramResultItem.getImageUrl()).addDocid(paramResultItem.getAnnotationResult().getMoreLikeThisAction().getDocid()));
    this.queryPipeline.setProcessedPicture(paramPicture);
    refine();
  }

  void refineQueryWithRestricts(RestrictsProtos.Restricts paramRestricts)
  {
    this.queryPipeline.startNewQuery();
    this.queryPipeline.setPendingQuery(getLastQuery().toPuggleQueryBuilder().addRestricts(paramRestricts));
    this.queryPipeline.setProcessedPicture(getLastQuery().getPicture());
    refine();
  }

  void refineQueryWithText(String paramString)
  {
    this.queryPipeline.startNewQuery();
    this.queryPipeline.setPendingQuery(getLastQuery().toPuggleQueryBuilder().addText(paramString));
    this.queryPipeline.setProcessedPicture(getLastQuery().getPicture());
    refine();
  }

  protected void setLastQuery(ParcelableQuery paramParcelableQuery)
  {
    this.lastQuery = paramParcelableQuery;
  }

  public void setupViews()
  {
    asyncLoadQueryDetails();
    displayQuerySummary();
    displaySelectedRestricts(this.lastQuery.getRestricts());
    displayResponse(getLastResponse());
  }

  public void showViewTypeDialog(Context paramContext)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(R.string.view_title_label);
    localBuilder.setItems(PuggleResultAdapter.ViewType.displayStrings(paramContext), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        PuggleResultsActivity.this.setViewTypeDisplay(PuggleResultAdapter.ViewType.get(paramAnonymousInt));
        ClickTracker localClickTracker = PuggleResultsActivity.this.application.getClickTracker();
        if (PuggleResultAdapter.ViewType.get(paramAnonymousInt) == PuggleResultAdapter.ViewType.LIST);
        for (NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_CLICK_GRID_VIEW; ; localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_CLICK_LIST_VIEW)
        {
          localClickTracker.logClick(localCLICK_TARGET);
          return;
        }
      }
    });
    localBuilder.show();
  }

  private class PrettyNameClickListener
    implements QuerySummaryView.CategorySelectedListener
  {
    private PrettyNameClickListener()
    {
    }

    public boolean onCategorySelected(RestrictsProtos.Category paramCategory)
    {
      QueryRestricts localQueryRestricts = new QueryRestricts();
      localQueryRestricts.setRestrict(RestrictType.PRETTY_NAME_CATEGORY, paramCategory);
      PuggleResultsActivity.this.refineQueryWithRestricts(localQueryRestricts.buildRestricts());
      PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_CATEGORY_CLICK_IN_RESULTS_PAGE);
      return true;
    }
  }

  private class ResultClickListener
    implements PuggleResultAdapter.PuggleResultClickListener
  {
    private ResultClickListener()
    {
    }

    public boolean onPuggleResultClick(int paramInt)
    {
      PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_RESULT_CLICK_IN_RESULT_LIST, paramInt, PuggleResultsActivity.this.puggleResultAdapter.getCount());
      PuggleResultsActivity.this.onResultSelected((ResultModel)PuggleResultsActivity.this.puggleResultAdapter.getResults().get(paramInt));
      return true;
    }

    public boolean onPuggleResultLongClick(Picture paramPicture, ResultItem paramResultItem)
    {
      Check.checkNotNull(paramPicture);
      PuggleResultsActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.MORE_LIKE_THIS_LONG_PRESS);
      PuggleResultsActivity.this.refineQuery(paramPicture, paramResultItem);
      return true;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.PuggleResultsActivity
 * JD-Core Version:    0.6.2
 */