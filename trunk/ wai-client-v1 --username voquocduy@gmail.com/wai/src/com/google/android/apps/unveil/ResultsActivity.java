package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Toast;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.Handler;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryListener;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryPipeline.PosterityRequestListener;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.service.PictureRequestService;
import com.google.android.apps.unveil.ui.BackOfCardView;
import com.google.android.apps.unveil.ui.result.ResultItemViewFactory;
import com.google.android.apps.unveil.ui.result.ResultSelectionView;
import com.google.android.apps.unveil.ui.result.ResultStackView;
import com.google.android.apps.unveil.ui.resultdrawer.ResultDrawer;
import com.google.android.apps.unveil.ui.resultdrawer.ResultsInsideOfDrawer;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.util.ArrayList;
import java.util.List;

public class ResultsActivity extends MomentActivity
  implements QueryPipeline.PosterityRequestListener
{
  private static final String BUNDLE_CURRENT_IMAGE_ID = "ResultsActivity.current_image_id";
  private static final int DIALOG_FATAL_AUTH_ERROR = 2;
  private static final int DIALOG_REPLAY_PROGRESS = 3;
  private static final int DIALOG_RETRY = 1;
  private static final long NO_IMAGE_ID = -1L;
  private static final UnveilLogger logger = new UnveilLogger();
  private long currentImageId = -1L;
  private FrameLayout frontOfCard;
  private boolean isShowingActivity = false;
  private int lastStatusCode = -1;
  private String posterityMomentId;
  private boolean posterityQueryPending;
  private String replayId;
  private ResultSelectionView resultSelectionView;
  private Animation.AnimationListener slideOutAnimationListener;

  private void handleIntent()
  {
    Intent localIntent = getIntent();
    if ("com.google.android.apps.unveil.replay".equals(localIntent.getAction()))
    {
      String str = localIntent.getStringExtra("replay_id");
      if ((this.replayId == null) || (!this.replayId.equals(str)))
      {
        this.replayId = str;
        sendReplay(this.replayId);
      }
    }
    do
    {
      return;
      if ("com.google.android.apps.unveil.show_results".equals(localIntent.getAction()))
      {
        handleActionShowResults(localIntent);
        return;
      }
    }
    while (!"com.google.android.apps.unveil.load_sfc_results".equals(localIntent.getAction()));
    if (localIntent.hasExtra("log_notification_count"))
    {
      int i = localIntent.getExtras().getInt("log_notification_count");
      this.application.getClickTracker().logNotificationClick(i);
    }
    if (localIntent.hasExtra("seen_result_ids"))
      PictureRequestService.notifyOfSeenItems(this, localIntent);
    handleActionLoadSfCResults(localIntent);
  }

  private AlertDialog.Builder makeAuthErrorDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(R.string.auth_failed);
    localBuilder.setIcon(17301543);
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(getString(R.string.close), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        ResultsActivity.this.finish();
      }
    });
    return localBuilder;
  }

  private ProgressDialog makeReplayDialog()
  {
    ProgressDialog localProgressDialog = new ProgressDialog(this);
    localProgressDialog.setTitle("");
    localProgressDialog.setMessage(getString(R.string.loading));
    localProgressDialog.setIndeterminate(true);
    localProgressDialog.setCancelable(true);
    localProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        ResultsActivity.this.finish();
      }
    });
    return localProgressDialog;
  }

  private AlertDialog.Builder makeRetryDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    if (this.lastStatusCode != -1)
    {
      String str = getString(R.string.connection_problem_status_code);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.lastStatusCode);
      localBuilder.setTitle(String.format(str, arrayOfObject));
    }
    while (true)
    {
      localBuilder.setIcon(17301543);
      localBuilder.setPositiveButton(R.string.try_again, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ResultsActivity.this.retryReplay();
        }
      });
      localBuilder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          ResultsActivity.this.dismissDialog(1);
          ResultsActivity.this.finish();
        }
      });
      localBuilder.setOnCancelListener(new DialogInterface.OnCancelListener()
      {
        public void onCancel(DialogInterface paramAnonymousDialogInterface)
        {
          ResultsActivity.this.dismissDialog(1);
          ResultsActivity.this.finish();
        }
      });
      return localBuilder;
      localBuilder.setTitle(R.string.connection_problem);
    }
  }

  private void prepareForPosterityRequest()
  {
    logger.v("lastResponse was a LOCAL_BARCODE, setting up QueryPipeline for posterity.", new Object[0]);
    this.posterityQueryPending = true;
    this.queryPipeline.setPosterityListener(this);
  }

  private void prepareUI()
  {
    if (this.slideOutAnimationListener == null)
      this.slideOutAnimationListener = new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          ResultsActivity.this.hideFullscreenViews();
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      };
  }

  private void replayQueryBitmapFailed()
  {
    logger.e("Failed to load the replay query bitmap.", new Object[0]);
    setLastResponse(null);
    this.lastStatusCode = -1;
    showDialog(1);
  }

  private void retryReplay()
  {
    if (this.replayId != null)
    {
      sendReplay(this.replayId);
      return;
    }
    logger.w("Trying to retry a request but we don't have a replayId.", new Object[0]);
  }

  private void showResult(ResultModel paramResultModel)
  {
    this.resultSelectionView.resetResultSelection();
    String str = paramResultModel.getDirectUrl();
    if (!TextUtils.isEmpty(str))
    {
      Intent localIntent = new Intent().setAction("android.intent.action.VIEW");
      try
      {
        startActivity(localIntent.setData(Uri.parse(str)));
        return;
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        Toast.makeText(this, R.string.no_activity_for_uri, 1).show();
      }
    }
    showExpandedResults(paramResultModel);
  }

  protected void createResultSelectionView(Picture paramPicture, GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata, String paramString)
  {
    if (this.resultSelectionView == null)
    {
      this.resultSelectionView = new ResultSelectionView(this, paramPicture, this, this.application, makeInsideOfDrawer(paramUserContributionMetadata, paramString), getQueryImageBackground(), getLastResponse().getAllResults());
      this.resultSelectionView.onConfigChanged(getResources().getConfiguration(), this);
      this.frontOfCard.addView(this.resultSelectionView);
    }
  }

  protected void displayResults(Picture paramPicture, QueryResponse paramQueryResponse)
  {
    if (this.resultSelectionView != null)
    {
      logger.d("Ignoring results arriving after the local barcode", new Object[0]);
      return;
    }
    createBackOfCardView();
    this.backOfCardView.setThumbnail(paramPicture);
    createResultSelectionView(paramPicture, paramQueryResponse.getUserContribution(), paramQueryResponse.getQueryImageUrl());
    this.application.getTraceTracker().endInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
    Animation localAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up_overshoot);
    this.resultSelectionView.getResultDrawer().startAnimation(localAnimation);
  }

  protected String getMomentId()
  {
    if (this.posterityQueryPending)
      return null;
    if (this.posterityMomentId == null)
      return getLastResponse().getMomentId();
    return this.posterityMomentId;
  }

  protected List<? extends ResultModel> getResultsToExpand()
  {
    return new ArrayList(ResultModel.excludeDirectUrlResults(getLastResponse().getAllResults()));
  }

  protected boolean handleBackButtonPress()
  {
    if (isShowingBackOfCard())
    {
      handleHideBackOfCard();
      return true;
    }
    if ((this.resultSelectionView != null) && (this.resultSelectionView.getResultDrawer().canTransitionToSemi()))
    {
      this.resultSelectionView.getResultDrawer().toggleExpansion();
      return true;
    }
    return false;
  }

  protected void handleHideBackOfCard()
  {
    doFlipViews(this.backOfCardView, this.frontOfCard);
  }

  protected void handleShowBackOfCard()
  {
    doFlipViews(this.frontOfCard, this.backOfCardView);
  }

  protected boolean hasExpectedResponses(QueryResponse paramQueryResponse)
  {
    return !paramQueryResponse.getResults().isEmpty();
  }

  protected void hideFullscreenViews()
  {
    if (this.backOfCardView != null)
      this.backOfCardView.setVisibility(8);
  }

  protected void loadReplayQueryImage(final QueryResponse paramQueryResponse)
  {
    FetchResponse.Handler local8 = new FetchResponse.Handler()
    {
      public void onResponseReceived(FetchResponse paramAnonymousFetchResponse)
      {
        ResultsActivity.this.removeDialog(3);
        if (!FetchResponse.isValid(paramAnonymousFetchResponse))
        {
          ResultsActivity.this.replayQueryBitmapFailed();
          return;
        }
        ResultsActivity.this.prepareUI();
        Picture localPicture = PictureFactory.createJpeg(paramAnonymousFetchResponse.getData(), paramQueryResponse.getRotation());
        ResultsActivity.this.queryPipeline.setProcessedPicture(localPicture);
        if (ResultsActivity.this.getLastResponse().hasResults())
        {
          ResultsActivity.this.displayResponse(ResultsActivity.this.getLastResponse());
          return;
        }
        ResultsActivity localResultsActivity = ResultsActivity.this;
        if (ResultsActivity.this.getLastResponse().hasEyeCandyResults());
        for (Object localObject = EpicFailActivity.class; ; localObject = PuggleResultsActivity.class)
        {
          Intent localIntent = new Intent(localResultsActivity, (Class)localObject);
          localIntent.setAction("com.google.android.apps.unveil.show_results");
          localIntent.putExtra("response", ResultsActivity.this.getLastResponse());
          if (ResultsActivity.this.searchHistoryDetails != null)
            localIntent.putExtra("search_history_item", ResultsActivity.this.searchHistoryDetails);
          localIntent.setFlags(65536);
          ResultsActivity.this.startActivity(localIntent);
          ResultsActivity.this.finish();
          return;
        }
      }
    };
    String str = ThumbnailProvider.SizeSpec.FIFE_ORIGINAL.getSizedUrl(this, paramQueryResponse.getQueryImageUrl());
    DefaultHttpRequestFactory localDefaultHttpRequestFactory = DefaultHttpRequestFactory.newAuthenticatedRequestFactory(this.application);
    this.application.getConnector().sendRequest(localDefaultHttpRequestFactory.newGetRequest(str), local8);
  }

  protected ResultsInsideOfDrawer makeInsideOfDrawer(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata, String paramString)
  {
    return new ResultsInsideOfDrawer(this, new ResultStackView(this, new ResultItemViewFactory(this), paramString), makeSendToGoogleListener());
  }

  protected void onActionShowResults(Intent paramIntent)
  {
    if (getLastResponse().getQueryType() == QueryResponseFactory.QueryType.LOCAL_BARCODE)
      prepareForPosterityRequest();
    while (true)
    {
      logger.v("displayResult", new Object[0]);
      this.currentImageId = this.queryPipeline.getPicture().getId();
      prepareUI();
      displayResponse(getLastResponse());
      return;
      asyncLoadQueryDetails();
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    logger.v("onConfigurationChanged", new Object[0]);
    if (this.frameLayout == null);
    while (this.resultSelectionView == null)
      return;
    this.resultSelectionView.onConfigChanged(paramConfiguration, this);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.frontOfCard = new FrameLayout(this, null);
    this.frontOfCard.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    this.frameLayout.addView(this.frontOfCard);
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    if (!this.isShowingActivity)
      return null;
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 1:
      return makeRetryDialog().create();
    case 2:
      return makeAuthErrorDialog().create();
    case 3:
    }
    return makeReplayDialog();
  }

  public void onPosterityResponse(QueryResponse paramQueryResponse)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramQueryResponse.getMomentId();
    localUnveilLogger.v("Got a posterity response with momentId %s", arrayOfObject);
    this.posterityMomentId = paramQueryResponse.getMomentId();
    this.posterityQueryPending = false;
    asyncLoadQueryDetails();
  }

  public void onResultSelected(ResultModel paramResultModel)
  {
    showResult(paramResultModel);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    logger.v("onSaveInstanceState", new Object[0]);
    paramBundle.putLong("ResultsActivity.current_image_id", this.currentImageId);
  }

  protected void onStart()
  {
    super.onStart();
    this.isShowingActivity = true;
    if (getLastResponse() != null)
    {
      if (getLastResponse().getQueryType() == QueryResponseFactory.QueryType.LOCAL_BARCODE)
        prepareForPosterityRequest();
      while (true)
      {
        prepareUI();
        if (this.resultSelectionView == null)
          displayResponse(getLastResponse());
        return;
        asyncLoadQueryDetails();
      }
    }
    handleIntent();
  }

  protected void onStop()
  {
    super.onStop();
    logger.v("onStop", new Object[0]);
    this.isShowingActivity = false;
  }

  protected void restoreInstanceState(Bundle paramBundle)
  {
    super.restoreInstanceState(paramBundle);
    this.currentImageId = paramBundle.getLong("ResultsActivity.current_image_id", -1L);
    if (this.queryPipeline.getPicture() != null)
      this.currentImageId = this.queryPipeline.getPicture().getId();
  }

  void sendReplay(String paramString)
  {
    logger.d("Replaying query %s", new Object[] { paramString });
    showDialog(3);
    QueryBuilder localQueryBuilder = new QueryBuilder();
    localQueryBuilder.addQueryId(paramString);
    localQueryBuilder.setQueryType(QueryResponseFactory.QueryType.REPLAY);
    this.application.getQueryManager().replayQuery(localQueryBuilder, new QueryListener()
    {
      public void onAuthenticationError()
      {
        ResultsActivity.this.removeDialog(3);
        ResultsActivity.logger.e("replay auth error", new Object[0]);
        ResultsActivity.this.showDialog(2);
      }

      public void onHeavyProcessingEnd()
      {
      }

      public void onHeavyProcessingStart()
      {
      }

      public void onNetworkError(int paramAnonymousInt)
      {
        ResultsActivity.access$202(ResultsActivity.this, paramAnonymousInt);
        ResultsActivity.this.removeDialog(3);
        ResultsActivity.logger.e("replay network error", new Object[0]);
        ResultsActivity.this.showDialog(1);
      }

      public void onQueryResponse(QueryResponse paramAnonymousQueryResponse)
      {
        ResultsActivity.this.setLastResponse(paramAnonymousQueryResponse);
        ResultsActivity.logger.i("Got replay response, loading image.", new Object[0]);
        ResultsActivity.this.loadReplayQueryImage(paramAnonymousQueryResponse);
        ResultsActivity.this.asyncLoadQueryDetails();
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ResultsActivity
 * JD-Core Version:    0.6.2
 */