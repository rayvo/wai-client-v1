package com.google.android.apps.unveil;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.Toast;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.feedback.FeedbackUtils;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.DeleteListener;
import com.google.android.apps.unveil.history.SearchHistoryProvider.LookupListener;
import com.google.android.apps.unveil.history.SearchHistoryProvider.UpdateListener;
import com.google.android.apps.unveil.history.SfCItem;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryPipeline.NoPendingQueryException;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.android.apps.unveil.protocol.SessionlessRequests;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.restricts.QueryRestricts;
import com.google.android.apps.unveil.restricts.RestrictType;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.BackOfCardView;
import com.google.android.apps.unveil.ui.ShareDialog;
import com.google.android.apps.unveil.ui.result.QueryImageBackground;
import com.google.android.apps.unveil.ui.result.ResultSelectionView.ResultSelectionListener;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Category.Domain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class MomentActivity extends SherlockActivity
  implements ResultSelectionView.ResultSelectionListener
{
  private static final String BUNDLE_LAST_RESPONSE = "MomentActivity.lastResponse";
  private static final String BUNDLE_PENDING_QUERY = "MomentActivity.pendingQuery";
  protected static final int DIALOG_PROCESS_IMAGE_PROBLEM = 100;
  private static final int LOAD_DETAILS_RETRIES = 2;
  protected static final int MAX_RATING_FOR_TAG_REQUEST = 3;
  public static final int REQUEST_CODE_CHANGE_NOTE = 2;
  public static final int REQUEST_CODE_MOMENT_UPSELL = 1;
  public static final int REQUEST_CODE_SHOW_EXPANDED_RESULTS = 4;
  public static final int REQUEST_CODE_TAG = 3;
  public static final int RESULT_CHANGED = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  protected UnveilApplication application;
  protected BackOfCardView backOfCardView;
  private QueryImageBackground background;
  private Dialog deferredDialog;
  private Runnable deferredRunWhenLoaded;
  protected FrameLayout frameLayout;
  private QueryResponse lastResponse;
  protected QueryPipeline queryPipeline;
  protected SearchHistoryItem searchHistoryDetails;
  protected ShareDialog shareDialog;

  private void asyncLoadQueryDetails(Runnable paramRunnable, Dialog paramDialog, final int paramInt)
  {
    logger.v("Loading search history item details.", new Object[0]);
    if (!this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
    {
      logger.e("Fast return from asyncLoadQueryDetails because we're not authenticated.", new Object[0]);
      return;
    }
    final Runnable localRunnable;
    if (paramRunnable == null)
    {
      localRunnable = this.deferredRunWhenLoaded;
      if (paramDialog != null)
        break label102;
    }
    label102: for (final Dialog localDialog = this.deferredDialog; ; localDialog = paramDialog)
    {
      if (!TextUtils.isEmpty(getMomentId()))
        break label108;
      logger.e("Cannot load query details, momentId is null or empty.", new Object[0]);
      this.deferredRunWhenLoaded = localRunnable;
      this.deferredDialog = localDialog;
      return;
      localRunnable = paramRunnable;
      break;
    }
    label108: this.application.getSearchHistoryProvider().lookup(getMomentId(), new SearchHistoryProvider.LookupListener()
    {
      public void onError()
      {
        if (paramInt > 0)
        {
          MomentActivity.this.asyncLoadQueryDetails(localRunnable, localDialog, -1 + paramInt);
          return;
        }
        if ((localDialog != null) && (localDialog.isShowing()))
          localDialog.dismiss();
        UnveilLogger localUnveilLogger = MomentActivity.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = MomentActivity.this.getMomentId();
        localUnveilLogger.e("Failed to look up search history moment %s.", arrayOfObject);
        Toast.makeText(MomentActivity.this, MomentActivity.this.getText(R.string.load_query_problem), 1).show();
      }

      public void onResult(SearchHistoryItem paramAnonymousSearchHistoryItem)
      {
        if (paramAnonymousSearchHistoryItem == null)
        {
          onError();
          return;
        }
        if ((localDialog != null) && (localDialog.isShowing()))
          localDialog.dismiss();
        MomentActivity.this.updateDetails(paramAnonymousSearchHistoryItem);
        if (localRunnable != null)
          localRunnable.run();
        MomentActivity.access$002(MomentActivity.this, null);
        MomentActivity.access$102(MomentActivity.this, null);
      }
    });
  }

  private void deleteThisMoment()
  {
    final ProgressDialog localProgressDialog = ProgressDialog.show(this, "", getString(R.string.deleting), true, false);
    this.application.getSearchHistoryProvider().delete(getLastResponse().getMomentId(), new SearchHistoryProvider.DeleteListener()
    {
      public void onError()
      {
        localProgressDialog.dismiss();
        Toast.makeText(MomentActivity.this, R.string.delete_failed, 1).show();
      }

      public void onResult()
      {
        localProgressDialog.dismiss();
        MomentActivity.this.finish();
      }
    });
  }

  private void handleNoteChangeResult(int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
      updateDetails((SearchHistoryItem)paramIntent.getSerializableExtra("search_history_item"));
  }

  private void handleShowExpandedResultsResult(int paramInt)
  {
    if (paramInt == 1)
      reloadMoment();
  }

  private void handleTagResult(int paramInt)
  {
    if (paramInt == -1)
    {
      reloadMoment();
      return;
    }
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    localUnveilLogger.i("TagImageActivity return result code %s.", arrayOfObject);
  }

  private void handleUpsellResult(int paramInt)
  {
    if (paramInt == -1)
    {
      rerunQuery();
      return;
    }
    logger.i("User elected not to enable search history.", new Object[0]);
  }

  private AlertDialog.Builder makeProcessImageProblemDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.process_image_problem);
    localBuilder.setIcon(17301543);
    localBuilder.setCancelable(false);
    localBuilder.setNeutralButton(R.string.ok, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MomentActivity.this.dismissDialog(100);
        MomentActivity.this.finish();
      }
    });
    return localBuilder;
  }

  private QueryImageBackground makeQueryImageBackground(QueryResponse paramQueryResponse, Picture paramPicture)
  {
    return QueryImageBackground.from(this.application, paramQueryResponse, paramPicture);
  }

  private void reloadMoment()
  {
    Intent localIntent = new Intent(this, ResultsActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.replay");
    localIntent.putExtra("replay_id", getMomentId());
    localIntent.setFlags(67108864);
    startActivity(localIntent);
    finish();
  }

  private void rerunQuery()
  {
    if (getLastResponse().getQueryType() == QueryResponseFactory.QueryType.LOCAL_BARCODE);
    try
    {
      QueryBuilder localQueryBuilder = this.queryPipeline.getPendingQuery();
      Picture localPicture = this.queryPipeline.getPicture();
      this.queryPipeline.startNewQuery();
      this.queryPipeline.setPendingQuery(localQueryBuilder);
      this.queryPipeline.setProcessedPicture(localPicture);
      startActivity(RunQueryActivity.makeUpsellIntent(this));
      finish();
      return;
    }
    catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
    {
      logger.e("NoPendingQuery when trying to rerun a query.", new Object[0]);
      Toast.makeText(this, getText(R.string.load_query_problem), 1).show();
      finish();
    }
  }

  private void showFeedback()
  {
    HashMap localHashMap = new HashMap();
    if (this.searchHistoryDetails != null)
      localHashMap.put("momentId", this.searchHistoryDetails.getMomentId());
    FeedbackUtils.onFeedbackOptionsItemSelected(this, localHashMap);
  }

  private void showUpsellActivity()
  {
    Intent localIntent = new Intent(this, SHFirstRunActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.moment_upsell");
    startActivityForResult(localIntent, 1);
  }

  private boolean triggerPuggleQuery(ResultModel paramResultModel)
  {
    if (!(paramResultModel instanceof ResultItem));
    Object localObject;
    do
    {
      ResultItem localResultItem;
      do
      {
        return false;
        localResultItem = (ResultItem)paramResultModel;
      }
      while ((!localResultItem.hasAnnotationResult()) || (!localResultItem.getAnnotationResult().getIsSimilarProduct()));
      Iterator localIterator = localResultItem.getAnnotationResult().getProductInfo().getCategoriesList().iterator();
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
      localObject = localCategory;
    }
    while (localObject == null);
    QueryRestricts localQueryRestricts = new QueryRestricts();
    localQueryRestricts.setRestrict(RestrictType.PRETTY_NAME_CATEGORY, localObject);
    this.queryPipeline.setPendingQuery(new PuggleQueryBuilder().addRestricts(localQueryRestricts.buildRestricts()));
    Intent localIntent = new Intent(this, RunQueryActivity.class);
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.PROCESSED_IMAGE);
    startActivity(localIntent);
    return true;
  }

  protected void asyncLoadQueryDetails()
  {
    asyncLoadQueryDetails(null, null, 0);
  }

  protected void createBackOfCardView()
  {
    if (this.backOfCardView == null)
    {
      this.backOfCardView = new BackOfCardView(this);
      this.backOfCardView.setVisibility(8);
      this.backOfCardView.post(new Runnable()
      {
        public void run()
        {
          MomentActivity.this.backOfCardView.setPadding(0, MomentActivity.this.getSupportActionBar().getHeight(), 0, 0);
        }
      });
      this.frameLayout.addView(this.backOfCardView);
      ((ImageButton)this.backOfCardView.findViewById(R.id.thumbnail)).setOnClickListener(makeCardFlipClickListener());
    }
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
    {
      this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HARDWARE_BACK_BUTTON);
      if (handleBackButtonPress())
        return true;
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  protected void displayResponse(QueryResponse paramQueryResponse)
  {
    Picture localPicture = this.queryPipeline.getPicture();
    if (localPicture == null)
      logger.w("Picture is null, aborting results selection view building.", new Object[0]);
    GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata localUserContributionMetadata;
    do
    {
      return;
      if (!hasExpectedResponses(paramQueryResponse))
      {
        logger.e("Tried to show a QueryResponse with no results (%s); it's impossible. Force-finishing.", new Object[] { paramQueryResponse });
        Toast.makeText(this, getString(R.string.no_results), 1).show();
        finish();
        return;
      }
      this.background = makeQueryImageBackground(paramQueryResponse, localPicture);
      logger.v("showResults: %s", new Object[] { paramQueryResponse });
      localUserContributionMetadata = paramQueryResponse.getUserContribution();
      displayResults(localPicture, paramQueryResponse);
    }
    while (localUserContributionMetadata == null);
    findViewById(R.id.send_to_google).setVisibility(8);
  }

  protected abstract void displayResults(Picture paramPicture, QueryResponse paramQueryResponse);

  protected void doFlipViews(View paramView1, View paramView2)
  {
    paramView1.setVisibility(8);
    paramView2.setVisibility(0);
    getSherlock().dispatchInvalidateOptionsMenu();
  }

  public QueryResponse getLastResponse()
  {
    return this.lastResponse;
  }

  protected abstract String getMomentId();

  protected QueryImageBackground getQueryImageBackground()
  {
    return this.background;
  }

  protected abstract List<? extends ResultModel> getResultsToExpand();

  protected final void handleActionLoadSfCResults(Intent paramIntent)
  {
    SfCItem localSfCItem = SfCItem.from(this, paramIntent.getStringExtra("sfc_id"));
    if (localSfCItem != null)
    {
      setLastResponse(localSfCItem.getQueryResponse());
      Picture localPicture = localSfCItem.getQueryImage();
      this.queryPipeline.setProcessedPicture(localPicture);
      if (localPicture != null)
        localPicture.setOrientation(0);
    }
    while (getLastResponse() == null)
    {
      logger.e("ResultsActivity started with SHOW_RESULTS intent action but no response.", new Object[0]);
      Toast.makeText(this, getString(R.string.no_results), 1).show();
      finish();
      return;
      logger.e("Failed to load search by camera result.", new Object[0]);
    }
    if (this.queryPipeline.getPicture() == null)
    {
      showDialog(100);
      return;
    }
    onActionShowResults(paramIntent);
  }

  protected final void handleActionShowResults(Intent paramIntent)
  {
    setLastResponse((QueryResponse)paramIntent.getSerializableExtra("response"));
    if (getLastResponse() == null)
    {
      logger.e("ResultsActivity started with SHOW_RESULTS intent action but no response.", new Object[0]);
      Toast.makeText(this, getString(R.string.no_results), 1).show();
      finish();
      return;
    }
    if (this.queryPipeline.getPicture() == null)
    {
      showDialog(100);
      return;
    }
    onActionShowResults(paramIntent);
  }

  protected abstract boolean handleBackButtonPress();

  protected abstract void handleHideBackOfCard();

  protected abstract void handleShowBackOfCard();

  protected abstract boolean hasExpectedResponses(QueryResponse paramQueryResponse);

  protected void hideBackOfCard()
  {
    handleHideBackOfCard();
  }

  protected boolean isShowingBackOfCard()
  {
    return (this.backOfCardView != null) && (this.backOfCardView.getVisibility() == 0);
  }

  protected View.OnClickListener makeCardFlipClickListener()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (MomentActivity.this.application.isSearchHistoryEnabled())
        {
          if (!MomentActivity.this.isShowingBackOfCard())
          {
            MomentActivity.this.showBackOfCard();
            return;
          }
          MomentActivity.this.hideBackOfCard();
          return;
        }
        MomentActivity.this.startUpsellFlow(R.string.upsell_note);
      }
    };
  }

  protected View.OnClickListener makeSendToGoogleListener()
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SUGGEST_A_RESULT_BUTTON_CLICK);
        if (MomentActivity.this.application.isSearchHistoryEnabled())
        {
          MomentActivity.this.showTagging();
          return;
        }
        MomentActivity.this.startUpsellFlow(R.string.upsell_tag);
      }
    };
  }

  protected abstract void onActionShowResults(Intent paramIntent);

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
      return;
    case 2:
      handleNoteChangeResult(paramInt2, paramIntent);
      return;
    case 1:
      handleUpsellResult(paramInt2);
      return;
    case 3:
      handleTagResult(paramInt2);
      return;
    case 4:
    }
    handleShowExpandedResultsResult(paramInt2);
  }

  protected void onCreate(Bundle paramBundle)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    if (paramBundle != null);
    for (String str = ", has bundle"; ; str = "")
    {
      arrayOfObject[0] = str;
      localUnveilLogger.v("onCreate %s", arrayOfObject);
      super.onCreate(paramBundle);
      logger.time("onCreate()", new Object[0]);
      this.application = ((UnveilApplication)getApplication());
      StateRestorationActivity.restoreState(paramBundle, this.application, this);
      UnveilApplication.configureWindowFormat(getWindow());
      this.queryPipeline = this.application.getQueryPipeline();
      if (paramBundle != null)
      {
        setLastResponse((QueryResponse)paramBundle.getParcelable("MomentActivity.lastResponse"));
        Serializable localSerializable = paramBundle.getSerializable("MomentActivity.pendingQuery");
        if (localSerializable != null)
          this.queryPipeline.setPendingQuery((QueryBuilder)localSerializable);
      }
      this.frameLayout = new FrameLayout(this);
      if (paramBundle != null)
        restoreInstanceState(paramBundle);
      setContentView(this.frameLayout);
      setTitle(R.string.results_label);
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setHomeButtonEnabled(true);
      return;
    }
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 100:
    }
    return makeProcessImageProblemDialog().create();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getSherlock().getMenuInflater().inflate(R.menu.moment, paramMenu);
    if (!this.application.isSearchHistoryEnabled())
      paramMenu.findItem(R.id.delete).setVisible(false);
    paramMenu.findItem(R.id.send_feedback).setVisible(false);
    paramMenu.findItem(R.id.debug_info).setVisible(false);
    return true;
  }

  protected void onNewIntent(Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    logger.i("onNewIntent", new Object[0]);
    startActivity(paramIntent);
    finish();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.help)
    {
      startActivity(new Intent(this, AboutActivity.class));
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.share)
    {
      if (this.application.isSearchHistoryEnabled())
      {
        runWhenDetailsLoaded(new Runnable()
        {
          public void run()
          {
            MomentActivity.this.prepareShareDialog();
          }
        });
        return true;
      }
      startUpsellFlow(R.string.upsell_share);
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.send_feedback)
    {
      if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
      {
        showFeedback();
        return true;
      }
      new AlertDialog.Builder(this).setTitle(R.string.untranslated_configure_history).setMessage(R.string.untranslated_feedback_upsell).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MomentActivity.this.showUpsellActivity();
        }
      }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MomentActivity.this.showFeedback();
        }
      }).show();
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.delete)
    {
      new AlertDialog.Builder(this).setIcon(17301543).setTitle(R.string.delete_query).setMessage(R.string.confirm_deletion).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          MomentActivity.this.deleteThisMoment();
        }
      }).setNegativeButton(R.string.no, null).show();
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.debug_info)
    {
      new AlertDialog.Builder(this).setIcon(17301543).setMessage(this.application.getTraceTracker().getLatestActionString()).setPositiveButton(R.string.ok, null).show();
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.note)
    {
      if (this.application.isSearchHistoryEnabled())
      {
        if (!isShowingBackOfCard())
        {
          showBackOfCard();
          return true;
        }
        hideBackOfCard();
        return true;
      }
      startUpsellFlow(R.string.upsell_note);
      return true;
    }
    if (paramMenuItem.getItemId() == 16908332)
    {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    if (this.backOfCardView == null);
    while (this.backOfCardView.getVisibility() != 0)
      return true;
    return false;
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
    if (getLastResponse() != null)
      paramBundle.putSerializable("MomentActivity.lastResponse", getLastResponse());
    if (this.queryPipeline.hasPendingQuery());
    try
    {
      paramBundle.putSerializable("MomentActivity.pendingQuery", this.queryPipeline.getPendingQuery());
      return;
    }
    catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
    {
      logger.w("Failed to save pending query to instance state", new Object[0]);
    }
  }

  protected void onStart()
  {
    logger.v("onStart", new Object[0]);
    super.onStart();
    logger.time("onStart()", new Object[0]);
  }

  protected void onStop()
  {
    logger.v("onStop", new Object[0]);
    super.onStop();
    SessionlessRequests.sendLogs(this.application);
  }

  protected void prepareShareDialog()
  {
    if (this.shareDialog == null)
      this.shareDialog = new ShareDialog(this);
    Picture localPicture = this.application.getQueryPipeline().getPicture();
    if (localPicture == null)
      throw new IllegalStateException("Updating moment dialog but null query image is null.");
    this.shareDialog.populate(this.searchHistoryDetails, localPicture);
    this.shareDialog.show();
    if (!this.searchHistoryDetails.isShared())
      this.application.getSearchHistoryProvider().share(this.searchHistoryDetails.getMomentId(), new SearchHistoryProvider.UpdateListener()
      {
        public void onError()
        {
          MomentActivity.this.shareDialog.dismiss();
          new AlertDialog.Builder(MomentActivity.this).setIcon(17301543).setMessage(R.string.network_error).setPositiveButton(R.string.ok, null).show();
        }

        public void onResult(SearchHistoryItem paramAnonymousSearchHistoryItem)
        {
          MomentActivity.this.updateDetails(paramAnonymousSearchHistoryItem);
        }
      });
  }

  protected void restoreInstanceState(Bundle paramBundle)
  {
  }

  protected void runWhenDetailsLoaded(Runnable paramRunnable)
  {
    if (this.searchHistoryDetails == null)
    {
      ProgressDialog localProgressDialog = ProgressDialog.show(this, null, getString(R.string.loading));
      localProgressDialog.setCancelable(true);
      asyncLoadQueryDetails(paramRunnable, localProgressDialog, 2);
      return;
    }
    updateDetails(this.searchHistoryDetails);
    paramRunnable.run();
  }

  public void setLastResponse(QueryResponse paramQueryResponse)
  {
    this.lastResponse = paramQueryResponse;
  }

  protected void showBackOfCard()
  {
    if (this.searchHistoryDetails != null)
    {
      this.backOfCardView.updateWidgets(this.searchHistoryDetails);
      handleShowBackOfCard();
      return;
    }
    runWhenDetailsLoaded(new Runnable()
    {
      public void run()
      {
        MomentActivity.this.handleShowBackOfCard();
      }
    });
    logger.w("Showing back of the card without search history details", new Object[0]);
  }

  protected void showExpandedResults(ResultModel paramResultModel)
  {
    if (triggerPuggleQuery(paramResultModel))
      return;
    Intent localIntent = new Intent(this, ExpandedResultsActivity.class);
    localIntent.putExtra("results", (ArrayList)getResultsToExpand());
    localIntent.putExtra("open_to", paramResultModel);
    startActivityForResult(localIntent, 4);
  }

  protected void showTagging()
  {
    runWhenDetailsLoaded(new Runnable()
    {
      public void run()
      {
        Intent localIntent = new Intent(MomentActivity.this, TagImageActivity.class);
        localIntent.putExtra("search_history_item", MomentActivity.this.searchHistoryDetails);
        MomentActivity.this.startActivityForResult(localIntent, 3);
      }
    });
  }

  protected void startUpsellFlow(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.search_history_required);
    localBuilder.setCancelable(true);
    localBuilder.setMessage(getString(paramInt));
    localBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MomentActivity.this.showUpsellActivity();
      }
    });
    localBuilder.setNegativeButton(R.string.cancel, null);
    localBuilder.setIcon(17301659);
    localBuilder.create().show();
  }

  protected void updateDetails(SearchHistoryItem paramSearchHistoryItem)
  {
    this.searchHistoryDetails = paramSearchHistoryItem;
    if (this.backOfCardView != null)
      this.backOfCardView.updateWidgets(paramSearchHistoryItem);
    if (this.shareDialog != null)
      this.shareDialog.populate(paramSearchHistoryItem, this.application.getQueryPipeline().getPicture());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.MomentActivity
 * JD-Core Version:    0.6.2
 */