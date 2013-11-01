package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.actionbarsherlock.app.ActionBar;
import com.google.android.apps.unveil.env.Check;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.puggle.ParcelableQuery;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.restricts.QueryRestricts;
import com.google.android.apps.unveil.restricts.RestrictType;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.BackOfCardView;
import com.google.android.apps.unveil.ui.result.QueryImageBackground;
import com.google.android.apps.unveil.ui.result.swipe.SwipeableResultsView.OnResultClickListener;
import com.google.android.apps.unveil.ui.resultdrawer.InsideOfDrawer;
import com.google.android.apps.unveil.ui.resultdrawer.ResultDrawer;
import com.google.android.apps.unveil.ui.resultdrawer.SimilarImagesInsideOfDrawer;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Category.Domain;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EpicFailActivity extends MomentActivity
{
  private static final String BUNDLE_LAST_QUERY = "EpicFailActivity.lastQuery";
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<ContributedResultItem> contributions = new ArrayList();
  private ParcelableQuery lastQuery;
  private ResultDrawer resultDrawer;

  private InsideOfDrawer makeInsideOfDrawer(List<ResultItem> paramList1, List<ContributedResultItem> paramList, String paramString, List<ResultItem> paramList2)
  {
    final SimilarImagesInsideOfDrawer localSimilarImagesInsideOfDrawer = new SimilarImagesInsideOfDrawer(this, paramList, paramString);
    localSimilarImagesInsideOfDrawer.init(paramList1, new SwipeableResultsView.OnResultClickListener()
    {
      public boolean onResultClicked(ResultItem paramAnonymousResultItem, int paramAnonymousInt)
      {
        EpicFailActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.EYECANDY_RESULT_CLICK, paramAnonymousInt, localSimilarImagesInsideOfDrawer.getNumVisibleSimilarImages());
        EpicFailActivity.this.startAttributionActivity(paramAnonymousResultItem);
        return true;
      }
    }
    , paramList2, new SwipeableResultsView.OnResultClickListener()
    {
      public boolean onResultClicked(ResultItem paramAnonymousResultItem, int paramAnonymousInt)
      {
        EpicFailActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_CATEGORY_CLICK_IN_FAIL_PAGE, paramAnonymousInt, localSimilarImagesInsideOfDrawer.getNumVisiblePuggleCategories());
        EpicFailActivity.this.runPuggleQuery(paramAnonymousResultItem);
        return true;
      }
    }
    , makeSendToGoogleListener(), this);
    return localSimilarImagesInsideOfDrawer;
  }

  private void runPuggleQuery(ResultItem paramResultItem)
  {
    Check.checkTrue("Couldn't run a Puggle Query: result was not a product: " + paramResultItem, paramResultItem.isProduct());
    Iterator localIterator = paramResultItem.getAnnotationResult().getProductInfo().getCategoriesList().iterator();
    RestrictsProtos.Category localCategory;
    do
    {
      boolean bool = localIterator.hasNext();
      localObject = null;
      if (!bool)
        break;
      localCategory = (RestrictsProtos.Category)localIterator.next();
    }
    while (localCategory.getDomain() != RestrictsProtos.Category.Domain.PRETTY_NAME);
    Object localObject = localCategory;
    Check.checkNotNull(localObject);
    this.queryPipeline.startNewQuery();
    this.queryPipeline.setProcessedPicture(getLastQuery().getPicture());
    this.application.getTraceTracker().addPoint(TracingProtos.PointVariable.Type.PUGGLE);
    this.application.getTraceTracker().beginInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
    QueryRestricts localQueryRestricts = new QueryRestricts();
    localQueryRestricts.setRestrict(RestrictType.PRETTY_NAME_CATEGORY, localObject);
    this.queryPipeline.setPendingQuery(new PuggleQueryBuilder().addRestricts(localQueryRestricts.buildRestricts()));
    Intent localIntent = new Intent(this, RunQueryActivity.class);
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.PROCESSED_IMAGE);
    startActivity(localIntent);
  }

  private void separateResultModels(List<ResultModel> paramList, List<ResultItem> paramList1, List<ContributedResultItem> paramList2)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ResultModel localResultModel = (ResultModel)localIterator.next();
      if ((localResultModel instanceof ResultItem))
        paramList1.add((ResultItem)localResultModel);
      else
        paramList2.add((ContributedResultItem)localResultModel);
    }
  }

  private void setBackground(ImageView paramImageView)
  {
    getQueryImageBackground().getBackground(this, this.application.getViewport()).populateWithBitmap(paramImageView);
  }

  private void startAttributionActivity(ResultItem paramResultItem)
  {
    Intent localIntent = new Intent(this, AttributionActivity.class);
    localIntent.putExtra("result", paramResultItem);
    localIntent.putExtra("last_response", getLastResponse());
    startActivity(localIntent);
  }

  protected void displayResults(Picture paramPicture, QueryResponse paramQueryResponse)
  {
    if (this.resultDrawer != null)
      return;
    ArrayList localArrayList = new ArrayList();
    separateResultModels(paramQueryResponse.getAllResults(), localArrayList, this.contributions);
    this.resultDrawer = new ResultDrawer(this, makeInsideOfDrawer(paramQueryResponse.getEyeCandyResults(), this.contributions, paramQueryResponse.getQueryImageUrl(), paramQueryResponse.getPuggleResults()));
    this.resultDrawer.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
    this.resultDrawer.post(new Runnable()
    {
      public void run()
      {
        ((FrameLayout.LayoutParams)EpicFailActivity.this.resultDrawer.getLayoutParams()).setMargins(0, EpicFailActivity.this.getSupportActionBar().getHeight(), 0, 0);
        EpicFailActivity.this.resultDrawer.setActionBarHeight(EpicFailActivity.this.getSupportActionBar().getHeight());
        EpicFailActivity.this.resultDrawer.invalidate();
      }
    });
    setBackground((ImageView)findViewById(R.id.result_failure_image));
    ((FrameLayout)this.frameLayout.findViewById(R.id.animation_container)).addView(this.resultDrawer);
    Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_overshoot);
    this.resultDrawer.startAnimation(localAnimation);
    this.application.getTraceTracker().endInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
  }

  protected ParcelableQuery getLastQuery()
  {
    return this.lastQuery;
  }

  protected String getMomentId()
  {
    return getLastResponse().getMomentId();
  }

  protected List<? extends ResultModel> getResultsToExpand()
  {
    return this.contributions;
  }

  protected boolean handleBackButtonPress()
  {
    if (isShowingBackOfCard())
    {
      handleHideBackOfCard();
      return true;
    }
    if ((this.resultDrawer != null) && (this.resultDrawer.canTransitionToSemi()))
    {
      this.resultDrawer.toggleExpansion();
      return true;
    }
    return false;
  }

  protected void handleHideBackOfCard()
  {
    doFlipViews(this.backOfCardView, findViewById(R.id.animation_container));
  }

  protected void handleShowBackOfCard()
  {
    doFlipViews(findViewById(R.id.animation_container), this.backOfCardView);
  }

  protected boolean hasExpectedResponses(QueryResponse paramQueryResponse)
  {
    return paramQueryResponse.hasEyeCandyResults();
  }

  public void onActionShowResults(Intent paramIntent)
  {
    if (paramIntent.hasExtra("search_history_item"))
      updateDetails((SearchHistoryItem)paramIntent.getSerializableExtra("search_history_item"));
    while (true)
    {
      setLastQuery(new ParcelableQuery("", this.queryPipeline.getPicture(), null));
      displayResponse(getLastResponse());
      return;
      asyncLoadQueryDetails();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    logger.v("onConfigurationChanged", new Object[0]);
    if (this.frameLayout == null)
      return;
    setBackground((ImageView)findViewById(R.id.result_failure_image));
    this.frameLayout.requestFocus();
    displayResponse(getLastResponse());
    this.resultDrawer.onConfigChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle != null)
      setLastQuery((ParcelableQuery)paramBundle.getParcelable("EpicFailActivity.lastQuery"));
    this.frameLayout = ((FrameLayout)getLayoutInflater().inflate(R.layout.result_failure, null));
    setContentView(this.frameLayout);
    createBackOfCardView();
  }

  public void onResultSelected(ResultModel paramResultModel)
  {
    showExpandedResults(paramResultModel);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (getLastQuery() != null)
      paramBundle.putParcelable("EpicFailActivity.lastQuery", getLastQuery());
  }

  protected void onStart()
  {
    super.onStart();
    this.backOfCardView.setThumbnail(this.application.getQueryPipeline().getPicture());
    if (getLastResponse() != null)
    {
      asyncLoadQueryDetails();
      displayResponse(getLastResponse());
    }
    Intent localIntent;
    do
    {
      return;
      localIntent = getIntent();
    }
    while (!"com.google.android.apps.unveil.show_results".equals(localIntent.getAction()));
    handleActionShowResults(localIntent);
  }

  protected void setLastQuery(ParcelableQuery paramParcelableQuery)
  {
    this.lastQuery = paramParcelableQuery;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.EpicFailActivity
 * JD-Core Version:    0.6.2
 */