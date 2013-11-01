package com.google.android.apps.unveil;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.actionbarsherlock.app.ActionBar;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SavedQuery;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryPipeline.EventListener;
import com.google.android.apps.unveil.protocol.QueryPipeline.NoPendingQueryException;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.ui.ProgressView;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;

public class RunQueryActivity extends AuthenticatedActivity
{
  public static final String EXTRA_RECIPE = "recipe";
  private static final UnveilLogger logger = new UnveilLogger();
  private ProgressDialog progressDialog;
  private ProgressView progressView;
  private Long savedQueryId;

  private Class<? extends MomentActivity> getHandlingActivity(QueryResponse paramQueryResponse)
  {
    if (paramQueryResponse.hasEyeCandyResults())
      return EpicFailActivity.class;
    if (paramQueryResponse.hasPuggleResults())
      return PuggleResultsActivity.class;
    return ResultsActivity.class;
  }

  public static Intent makeUpsellIntent(Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, RunQueryActivity.class);
    localIntent.putExtra("recipe", Recipe.RERUN);
    return localIntent;
  }

  private void prepareForSavedQuery(QueryPipeline paramQueryPipeline, SavedQuery paramSavedQuery)
  {
    paramQueryPipeline.setProcessedPicture(paramSavedQuery.getCachedQueryImage());
    QueryBuilder localQueryBuilder = paramSavedQuery.getQueryParameters();
    if (localQueryBuilder.getSource() != GogglesProtos.GogglesRequest.Source.PUGGLE)
      localQueryBuilder.setSource(GogglesProtos.GogglesRequest.Source.SAVED_FOR_LATER);
    localQueryBuilder.addMsSinceEpoch(Long.valueOf(System.currentTimeMillis()));
    paramQueryPipeline.setPendingQuery(localQueryBuilder);
  }

  private void prepareProcessed(QueryPipeline paramQueryPipeline)
    throws QueryPipeline.NoPendingQueryException
  {
    paramQueryPipeline.getPendingQuery().addImageData(paramQueryPipeline.getPicture().getJpegData(), paramQueryPipeline.getPicture().getSize());
  }

  private void prepareQuery(QueryPipeline paramQueryPipeline, Intent paramIntent)
    throws QueryPipeline.NoPendingQueryException
  {
    switch (3.$SwitchMap$com$google$android$apps$unveil$RunQueryActivity$Recipe[Recipe.classify(paramIntent).ordinal()])
    {
    default:
      throw new AssertionError();
    case 2:
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramQueryPipeline.getPendingQuery();
      localUnveilLogger.i("Rerunning query: %s", arrayOfObject);
      return;
    case 3:
      this.savedQueryId = Long.valueOf(paramIntent.getLongExtra("saved_query_id", -1L));
      prepareForSavedQuery(paramQueryPipeline, this.application.getSavedQueryProvider().get(this.savedQueryId.longValue()));
      return;
    case 4:
      prepareRaw(paramQueryPipeline);
      return;
    case 1:
    }
    prepareProcessed(paramQueryPipeline);
  }

  private void prepareRaw(QueryPipeline paramQueryPipeline)
  {
    paramQueryPipeline.startRotatePhotoTask(paramQueryPipeline.getPicture());
  }

  private void setupViews()
  {
    Intent localIntent = getIntent();
    switch (3.$SwitchMap$com$google$android$apps$unveil$RunQueryActivity$Recipe[Recipe.classify(localIntent).ordinal()])
    {
    default:
      this.progressView = new ProgressView(this.application);
      this.application.getQueryPipeline().getPicture();
      this.progressView.showMessageArea(shouldShowMessageArea());
      this.progressView.setRotatingWidgetsLocked(true);
      this.progressView.setCancelClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          RunQueryActivity.this.application.getQueryPipeline().cancel();
          RunQueryActivity.this.finish();
        }
      });
      setContentView(this.progressView);
      return;
    case 1:
    }
    this.progressDialog = ProgressDialog.show(this, "", getString(R.string.loading), true, true, new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        RunQueryActivity.this.finish();
      }
    });
  }

  private boolean shouldShowMessageArea()
  {
    return Recipe.classify(getIntent()) != Recipe.RERUN;
  }

  private void startResultsActivity(QueryResponse paramQueryResponse)
  {
    this.progressView = null;
    Intent localIntent = new Intent(this, getHandlingActivity(paramQueryResponse));
    localIntent.setAction("com.google.android.apps.unveil.show_results");
    localIntent.setFlags(65536);
    localIntent.putExtra("response", paramQueryResponse);
    startActivity(localIntent);
    if (this.progressDialog != null)
      this.progressDialog.dismiss();
    finish();
  }

  void doSearch()
  {
    QueryPipeline localQueryPipeline = this.application.getQueryPipeline();
    Intent localIntent = getIntent();
    try
    {
      prepareQuery(localQueryPipeline, localIntent);
      localQueryPipeline.start(localIntent, null, new PreparedPictureQueryListener(localQueryPipeline, null));
      return;
    }
    catch (QueryPipeline.NoPendingQueryException localNoPendingQueryException)
    {
      logger.e("No pending query available. Toasting and finishing.", new Object[0]);
      Toast.makeText(this, getText(R.string.load_query_problem), 1).show();
      finish();
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setTheme(R.style.SherlockFullscreen);
    this.application = ((UnveilApplication)getApplication());
    StateRestorationActivity.restoreState(paramBundle, this.application, this);
    ActionBar localActionBar = getSupportActionBar();
    if (localActionBar != null)
      localActionBar.hide();
  }

  public void onDestroy()
  {
    super.onDestroy();
    if ((isFinishing()) && (this.progressDialog != null))
      this.progressDialog.dismiss();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
  }

  public void onStart()
  {
    super.onStart();
    setupViews();
    doSearch();
  }

  private class PreparedPictureQueryListener extends QueryPipeline.EventListener
  {
    private final QueryPipeline queryPipeline;

    private PreparedPictureQueryListener(QueryPipeline arg2)
    {
      Object localObject;
      this.queryPipeline = localObject;
    }

    public void onAuthenticationError()
    {
      RunQueryActivity.this.invalidateAuthToken();
    }

    public void onHeavyProcessingEnd()
    {
      if ((!RunQueryActivity.this.isFinishing()) && (RunQueryActivity.this.progressView != null))
        RunQueryActivity.this.progressView.startAnimation();
    }

    public void onHeavyProcessingStart()
    {
      if ((!RunQueryActivity.this.isFinishing()) && (RunQueryActivity.this.progressView != null))
        RunQueryActivity.this.progressView.stopAnimation();
    }

    public void onImageCropped(Picture paramPicture)
    {
    }

    public void onImageFailed()
    {
      Toast.makeText(RunQueryActivity.this.application, RunQueryActivity.this.application.getText(R.string.process_image_problem), 1).show();
    }

    public void onImageReady(Picture paramPicture, QueryBuilder paramQueryBuilder)
    {
      if (RunQueryActivity.this.progressView != null)
        RunQueryActivity.this.progressView.setBackgroundPicture(paramPicture);
    }

    public void onNetworkError(int paramInt)
    {
      RunQueryActivity localRunQueryActivity = RunQueryActivity.this;
      String str = RunQueryActivity.this.getString(R.string.connection_problem_status_code);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      Toast.makeText(localRunQueryActivity, String.format(str, arrayOfObject), 1).show();
      if (RunQueryActivity.this.progressDialog != null)
      {
        RunQueryActivity.this.progressDialog.dismiss();
        RunQueryActivity.this.finish();
      }
    }

    public void onQueryResponse(QueryResponse paramQueryResponse)
    {
      if (RunQueryActivity.this.savedQueryId != null)
        RunQueryActivity.this.application.getSavedQueryProvider().onQueryExecuted(RunQueryActivity.this.savedQueryId.longValue());
      RunQueryActivity.this.startResultsActivity(paramQueryResponse);
    }
  }

  public static enum Recipe
  {
    static
    {
      RAW_IMAGE = new Recipe("RAW_IMAGE", 1);
      PROCESSED_IMAGE = new Recipe("PROCESSED_IMAGE", 2);
      SAVE_FOR_LATER = new Recipe("SAVE_FOR_LATER", 3);
      Recipe[] arrayOfRecipe = new Recipe[4];
      arrayOfRecipe[0] = RERUN;
      arrayOfRecipe[1] = RAW_IMAGE;
      arrayOfRecipe[2] = PROCESSED_IMAGE;
      arrayOfRecipe[3] = SAVE_FOR_LATER;
    }

    public static Recipe classify(Intent paramIntent)
    {
      return (Recipe)paramIntent.getSerializableExtra("recipe");
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.RunQueryActivity
 * JD-Core Version:    0.6.2
 */