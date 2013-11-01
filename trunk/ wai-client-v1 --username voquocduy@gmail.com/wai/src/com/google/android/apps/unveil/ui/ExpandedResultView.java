package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.text.Html;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.unveil.AttributionActivity;
import com.google.android.apps.unveil.ExpandedResultsActivity;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.RunQueryActivity;
import com.google.android.apps.unveil.RunQueryActivity.Recipe;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.PuggleResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter;
import com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter.PuggleResultClickListener;
import com.google.android.apps.unveil.ui.result.ResultItemViewFactory;
import com.google.android.apps.unveil.ui.result.ResultViewUtils;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.AnnotationResultProtos.AnnotationResult.ContributionInformation;
import com.google.goggles.AnnotationResultProtos.AnnotationResult.PersonInformation;
import com.google.goggles.FactsProtos.Fact;
import com.google.goggles.FactsProtos.Facts;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.MoreLikeThisActionProtos.MoreLikeThisAction;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.ShareActionProtos.ShareAction;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import com.google.goggles.UrlGroupProtos.Url;
import com.google.goggles.UrlGroupProtos.UrlGroup;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExpandedResultView extends FrameLayout
{
  private static final UnveilLogger logger = new UnveilLogger();
  private MomentInteractionListener listener;
  private ResultModel resultModel;
  private final View resultView;
  private final ResultViewUtils resultViewUtils;
  private final WebView webView;

  public ExpandedResultView(final Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.resultView = LayoutInflater.from(paramContext).inflate(R.layout.expanded_result_view, null);
    this.resultViewUtils = new ResultViewUtils();
    addView(this.resultView);
    setVisibility(8);
    this.webView = ((WebView)findViewById(R.id.web_view));
    this.webView.getSettings().setJavaScriptEnabled(true);
    final ImageView localImageView = (ImageView)findViewById(R.id.web_view_progress_indicator);
    final AnimationDrawable localAnimationDrawable = (AnimationDrawable)localImageView.getBackground();
    ((UnveilContext)paramContext.getApplicationContext()).setUserAgent(this.webView.getSettings().getUserAgentString());
    this.webView.setWebViewClient(new WebViewClient()
    {
      public void onPageFinished(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        localAnimationDrawable.stop();
        localImageView.setVisibility(8);
      }

      public boolean shouldOverrideUrlLoading(WebView paramAnonymousWebView, String paramAnonymousString)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(paramAnonymousString));
        paramContext.startActivity(localIntent);
        return true;
      }
    });
  }

  private void addButtonsForResult(ResultItem paramResultItem, LinearLayout paramLinearLayout)
  {
    AnnotationResultProtos.AnnotationResult localAnnotationResult = paramResultItem.getAnnotationResult();
    int i = 0;
    Iterator localIterator = localAnnotationResult.getUrlGroupsList().iterator();
    while (localIterator.hasNext())
    {
      UrlGroupProtos.UrlGroup localUrlGroup = (UrlGroupProtos.UrlGroup)localIterator.next();
      for (int j = 0; j < localUrlGroup.getUrlList().size(); j++)
      {
        paramLinearLayout.addView(createActionButton(localUrlGroup.getUrl(j), paramResultItem, i));
        i++;
      }
    }
  }

  private void addContributorInformation(final AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramPersonInformation.getDisplayName();
    arrayOfObject[1] = paramPersonInformation.getProfileUrl();
    arrayOfObject[2] = paramPersonInformation.getProfilePhotoUrl();
    localUnveilLogger.i("Adding contributionInfo: %s, %s, %s", arrayOfObject);
    configureProfilePicture(paramPersonInformation);
    TextView localTextView = (TextView)findViewById(R.id.profile_name);
    localTextView.setText(paramPersonInformation.getDisplayName());
    if (TextUtils.isEmpty(paramPersonInformation.getProfileUrl()))
      return;
    localTextView.setTextColor(localTextView.getContext().getResources().getColor(R.color.link));
    findViewById(R.id.contributor_information).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Uri localUri = Uri.parse(paramPersonInformation.getProfileUrl());
        Intent localIntent = new Intent().setAction("android.intent.action.VIEW");
        paramAnonymousView.getContext().startActivity(localIntent.setData(localUri));
      }
    });
  }

  private void configureFactUi(AnnotationResultProtos.AnnotationResult paramAnnotationResult, TextView paramTextView)
  {
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramAnnotationResult.getFacts().getPrimaryCount());
    localUnveilLogger1.i("Primary count: %d", arrayOfObject1);
    LinearLayout localLinearLayout = (LinearLayout)this.resultView.findViewById(R.id.fact_layout);
    localLinearLayout.removeAllViews();
    ArrayList localArrayList = new ArrayList();
    localArrayList.addAll(paramAnnotationResult.getFacts().getPrimaryList());
    localArrayList.addAll(paramAnnotationResult.getFacts().getSecondaryList());
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
    {
      FactsProtos.Fact localFact = (FactsProtos.Fact)localIterator.next();
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localFact.getAttribute();
      localUnveilLogger2.i("Fact: %s", arrayOfObject2);
      View localView = LayoutInflater.from(getContext()).inflate(R.layout.expanded_result_fact, null);
      ((TextView)localView.findViewById(R.id.key)).setText(localFact.getAttribute());
      ((TextView)localView.findViewById(R.id.value)).setText(localFact.getValue());
      localLinearLayout.addView(localView);
    }
  }

  private void configureLargeImage(ResultModel paramResultModel)
  {
    this.resultView.findViewById(R.id.thumbnail_view).setVisibility(8);
    this.resultView.findViewById(R.id.large_image_view).setVisibility(0);
    ImageView localImageView = (ImageView)this.resultView.findViewById(R.id.large_image_view);
    populateFromUrl(localImageView, paramResultModel.getImageUrl());
    localImageView.setOnClickListener(newAttributionClickListener(paramResultModel));
  }

  private void configureMoreLikeThisLink(final ResultItem paramResultItem)
  {
    if (!paramResultItem.getAnnotationResult().hasMoreLikeThisAction())
      return;
    TextView localTextView = (TextView)findViewById(R.id.more_like_this_label);
    localTextView.setVisibility(0);
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UnveilApplication localUnveilApplication = (UnveilApplication)ExpandedResultView.this.getContext().getApplicationContext();
        localUnveilApplication.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.MORE_LIKE_THIS_FROM_EXPANDED_RESULTS);
        localUnveilApplication.getTraceTracker().addPoint(TracingProtos.PointVariable.Type.PUGGLE);
        localUnveilApplication.getTraceTracker().beginInterval(TracingProtos.SpanVariable.Type.START_TO_RENDERED);
        ExpandedResultView.this.getThumbnailProvider().fetch(paramResultItem.getThumbnailUrl(), new ThumbnailProvider.ThumbnailListener()
        {
          public void onThumbnailReady(Picture paramAnonymous2Picture)
          {
            if (paramAnonymous2Picture == null)
            {
              Toast.makeText(ExpandedResultView.this.getContext(), ExpandedResultView.this.getContext().getString(R.string.load_image_failed), 0).show();
              return;
            }
            ExpandedResultView.this.runMoreLikeThisQuery(paramAnonymous2Picture, ExpandedResultView.7.this.val$resultItem);
          }
        }
        , PuggleResultAdapter.PUGGLE_THUMB_SIZE_SPEC);
      }
    });
  }

  private void configureProfilePicture(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
  {
    ImageView localImageView = (ImageView)findViewById(R.id.profile_picture);
    ThumbnailProvider localThumbnailProvider = getThumbnailProvider();
    if (!TextUtils.isEmpty(paramPersonInformation.getProfilePhotoUrl()))
      localThumbnailProvider.fetch(paramPersonInformation.getProfilePhotoUrl(), ThumbnailProvider.makeImageViewThumbnailListener(localImageView, paramPersonInformation.getProfilePhotoUrl()), ThumbnailProvider.SizeSpec.FIFE_ORIGINAL);
  }

  private void configureRichResultInfo(ResultItem paramResultItem, TextView paramTextView)
  {
    if (this.resultViewUtils.isBookResult(paramResultItem))
    {
      paramTextView.setVisibility(8);
      localView2 = findViewById(R.id.book_content);
      this.resultViewUtils.setBookLabels(paramResultItem, localView2);
    }
    while (!this.resultViewUtils.isProductResult(paramResultItem))
    {
      View localView2;
      return;
    }
    paramTextView.setVisibility(8);
    View localView1 = findViewById(R.id.product_content);
    this.resultViewUtils.setProductLabels(paramResultItem, localView1);
  }

  private void configureShareLink(final ResultItem paramResultItem)
  {
    if (!paramResultItem.getAnnotationResult().hasShareAction())
      return;
    TextView localTextView = (TextView)findViewById(R.id.share_label);
    localTextView.setVisibility(0);
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((UnveilApplication)ExpandedResultView.this.getContext().getApplicationContext()).getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SHARE_RESULT);
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.setType("text/plain");
        localIntent.putExtra("android.intent.extra.TEXT", paramResultItem.getAnnotationResult().getShareAction().getText());
        ExpandedResultView.this.getContext().startActivity(Intent.createChooser(localIntent, ExpandedResultView.this.getContext().getString(R.string.share_label)));
      }
    });
  }

  private void configureSimilarResults(ResultItem paramResultItem)
  {
    if (!(paramResultItem instanceof PuggleResultItem));
    List localList;
    do
    {
      return;
      localList = ((PuggleResultItem)paramResultItem).getRelatedItems();
    }
    while (localList.isEmpty());
    ResultClickListener localResultClickListener = new ResultClickListener((PuggleResultItem)paramResultItem);
    NonScrollingListView localNonScrollingListView = (NonScrollingListView)findViewById(R.id.similar_results);
    localNonScrollingListView.setAdapter(new PuggleResultAdapter(localList, getThumbnailProvider(), localResultClickListener));
    localNonScrollingListView.setVisibility(0);
    findViewById(R.id.similar_results_label).setVisibility(0);
  }

  private void configureThumbnail(ResultModel paramResultModel)
  {
    this.resultView.findViewById(R.id.large_image_view).setVisibility(8);
    this.resultView.findViewById(R.id.thumbnail_view).setVisibility(0);
    populateFromUrl((ImageView)this.resultView.findViewById(R.id.thumbnail_view), paramResultModel.getThumbnailUrl());
  }

  private void configureTranslationUi(ResultItem paramResultItem, TextView paramTextView)
  {
    if (this.resultViewUtils.isTranslationResult(paramResultItem))
    {
      paramTextView.setVisibility(8);
      View localView = findViewById(R.id.translation_content);
      this.resultViewUtils.setTranslationLabels(paramResultItem, localView);
      ((ImageView)localView.findViewById(R.id.translate_arrow)).setImageResource(R.drawable.translate_arrow_dark);
    }
  }

  private void configureUiForContributedResultItem(String paramString1, String paramString2, String paramString3, String paramString4, AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
  {
    ((TextView)findViewById(R.id.result_title)).setText(paramString2);
    TextView localTextView1 = (TextView)findViewById(R.id.result_type);
    if (!TextUtils.isEmpty(paramString3))
      localTextView1.setText(paramString3);
    while (true)
    {
      if (!TextUtils.isEmpty(paramString4))
      {
        TextView localTextView2 = (TextView)findViewById(R.id.result_description);
        localTextView2.setText(paramString4);
        localTextView2.setVisibility(0);
      }
      prepareWithdrawButton(paramString1, this.resultView);
      addContributorInformation(paramPersonInformation);
      return;
      localTextView1.setVisibility(8);
    }
  }

  private View createActionButton(UrlGroupProtos.Url paramUrl, ResultItem paramResultItem, int paramInt)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramUrl.getDescription();
    arrayOfObject[1] = paramUrl.getUrl();
    localUnveilLogger.i("URL: %s : %s", arrayOfObject);
    View localView1 = LayoutInflater.from(getContext()).inflate(R.layout.expanded_result_action, null);
    View localView2 = localView1.findViewById(R.id.action_layout);
    TextView localTextView = (TextView)localView1.findViewById(R.id.label);
    if (TextUtils.isEmpty(paramUrl.getDescription()))
      localTextView.setText(paramUrl.getUrl());
    while (true)
    {
      ((ImageView)localView1.findViewById(R.id.action_icon)).setImageResource(ResultItemViewFactory.getIconResourceForPurpose(paramUrl.getPurpose()));
      ActionClickListener.applyTo(localView2, paramUrl.getUrl(), paramResultItem, paramInt, ActionClickListener.ActionContext.EXPANDED_RESULT_SCREEN);
      return localView1;
      localTextView.setText(Html.fromHtml(paramUrl.getDescription()));
    }
  }

  private ThumbnailProvider getThumbnailProvider()
  {
    return ((UnveilContext)getContext().getApplicationContext()).getThumbnailCache();
  }

  private static View.OnClickListener newAttributionClickListener(ResultModel paramResultModel)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Context localContext = paramAnonymousView.getContext();
        Intent localIntent = new Intent(localContext, AttributionActivity.class);
        localIntent.putExtra("result", this.val$resultModel);
        localContext.startActivity(localIntent);
      }
    };
  }

  private void populateFromUrl(ImageView paramImageView, String paramString)
  {
    paramImageView.setImageBitmap(null);
    paramImageView.setTag(null);
    if (paramString != null)
      getThumbnailProvider().fetch(paramString, ThumbnailProvider.makeImageViewThumbnailListener(paramImageView, paramString), ThumbnailProvider.SizeSpec.FIFE_ORIGINAL);
  }

  private void prepareFlagButton(final AnnotationResultProtos.AnnotationResult.ContributionInformation paramContributionInformation, View paramView)
  {
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.flag_withdraw_button);
    localImageView.setBackgroundResource(R.drawable.flag_button);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ExpandedResultView.this.listener.flagForAbuse(paramContributionInformation.getReportAbuseUrl());
      }
    });
    localImageView.setVisibility(0);
  }

  private void prepareWithdrawButton(final String paramString, View paramView)
  {
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.flag_withdraw_button);
    localImageView.setBackgroundResource(R.drawable.withdraw_button);
    localImageView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ExpandedResultView.this.listener.withdrawSubmission(paramString);
      }
    });
    localImageView.setVisibility(0);
  }

  private void runMoreLikeThisQuery(Picture paramPicture, ResultItem paramResultItem)
  {
    QueryPipeline localQueryPipeline = ((UnveilContext)getContext().getApplicationContext()).getQueryPipeline();
    localQueryPipeline.startNewQuery();
    PuggleQueryBuilder localPuggleQueryBuilder = new PuggleQueryBuilder();
    localPuggleQueryBuilder.addRestricts(paramResultItem.getAnnotationResult().getMoreLikeThisAction().getRestricts()).addImageUrl(paramResultItem.getImageUrl()).addDocid(paramResultItem.getAnnotationResult().getMoreLikeThisAction().getDocid());
    localQueryPipeline.setPendingQuery(localPuggleQueryBuilder);
    localQueryPipeline.setProcessedPicture(paramPicture);
    Intent localIntent = new Intent(getContext(), RunQueryActivity.class);
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.PROCESSED_IMAGE);
    getContext().startActivity(localIntent);
  }

  public ResultModel getResultModel()
  {
    return this.resultModel;
  }

  public void loadWebPage()
  {
    String str = this.resultModel.getWebSearchUrl();
    ImageView localImageView = (ImageView)findViewById(R.id.web_view_progress_indicator);
    if (TextUtils.isEmpty(str))
    {
      localImageView.setVisibility(8);
      return;
    }
    localImageView.setVisibility(0);
    final AnimationDrawable localAnimationDrawable = (AnimationDrawable)localImageView.getBackground();
    this.webView.loadUrl(str);
    post(new Runnable()
    {
      public void run()
      {
        localAnimationDrawable.start();
        ((ScrollView)ExpandedResultView.this.resultView.findViewById(R.id.scroll_view)).scrollTo(0, 0);
      }
    });
  }

  public void setMomentInteractionListener(MomentInteractionListener paramMomentInteractionListener)
  {
    this.listener = paramMomentInteractionListener;
  }

  public void setResultModel(ResultModel paramResultModel)
  {
    this.resultModel = paramResultModel;
    this.webView.clearView();
    if ((paramResultModel instanceof ResultItem))
      if (paramResultModel.hasImageUrl())
      {
        configureLargeImage(paramResultModel);
        localResultItem = (ResultItem)paramResultModel;
        ((TextView)findViewById(R.id.result_type)).setText(localResultItem.getType());
        localTextView1 = (TextView)findViewById(R.id.result_title);
        localTextView1.setText(localResultItem.getTitle());
        if (localResultItem.hasAnnotationResult())
          break label90;
      }
    label90: 
    while (!(paramResultModel instanceof ContributedResultItem))
    {
      ResultItem localResultItem;
      TextView localTextView1;
      while (true)
      {
        return;
        configureThumbnail(paramResultModel);
      }
      AnnotationResultProtos.AnnotationResult localAnnotationResult = localResultItem.getAnnotationResult();
      if ((localAnnotationResult.hasContributionInfo()) && (localAnnotationResult.getContributionInfo().hasContributor()))
      {
        if (localAnnotationResult.getContributionInfo().getUserIsOwner())
        {
          configureUiForContributedResultItem(localResultItem.getAnnotationResult().getContributionInfo().getMomentId(), localResultItem.getTitle(), localResultItem.getType(), localResultItem.getAnnotationResult().getContributionInfo().getUserSubmittedDescription(), localResultItem.getAnnotationResult().getContributionInfo().getContributor());
          return;
        }
        addContributorInformation(localAnnotationResult.getContributionInfo().getContributor());
      }
      while (true)
      {
        if ((localAnnotationResult.hasContributionInfo()) && (!TextUtils.isEmpty(localAnnotationResult.getContributionInfo().getUserSubmittedDescription())))
        {
          TextView localTextView2 = (TextView)findViewById(R.id.result_description);
          localTextView2.setText(localAnnotationResult.getContributionInfo().getUserSubmittedDescription());
          localTextView2.setVisibility(0);
        }
        configureRichResultInfo(localResultItem, localTextView1);
        configureFactUi(localAnnotationResult, localTextView1);
        configureTranslationUi(localResultItem, localTextView1);
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(localAnnotationResult.getUrlGroupsCount());
        localUnveilLogger.i("URL groups: %d", arrayOfObject);
        LinearLayout localLinearLayout = (LinearLayout)this.resultView.findViewById(R.id.button_layout);
        localLinearLayout.removeAllViews();
        addButtonsForResult(localResultItem, localLinearLayout);
        if (localAnnotationResult.hasContributionInfo())
          prepareFlagButton(localAnnotationResult.getContributionInfo(), this.resultView);
        configureShareLink(localResultItem);
        configureMoreLikeThisLink(localResultItem);
        configureSimilarResults(localResultItem);
        return;
        findViewById(R.id.contributor_information).setVisibility(8);
      }
    }
    ContributedResultItem localContributedResultItem = (ContributedResultItem)paramResultModel;
    configureUiForContributedResultItem(localContributedResultItem.getMomentId(), localContributedResultItem.getUserContribution().getTitle(), localContributedResultItem.getUserContribution().getType(), localContributedResultItem.getUserContribution().getDescription(), localContributedResultItem.getUserContribution().getContributor());
  }

  protected void showExpandedResults(ResultModel paramResultModel, List<PuggleResultItem> paramList)
  {
    Intent localIntent = new Intent(getContext(), ExpandedResultsActivity.class);
    localIntent.putExtra("results", new ArrayList(paramList));
    localIntent.putExtra("open_to", paramResultModel);
    getContext().startActivity(localIntent);
  }

  public static abstract interface MomentInteractionListener
  {
    public abstract void flagForAbuse(String paramString);

    public abstract void withdrawSubmission(String paramString);
  }

  private class ResultClickListener
    implements PuggleResultAdapter.PuggleResultClickListener
  {
    final List<PuggleResultItem> similarItems;

    public ResultClickListener(PuggleResultItem arg2)
    {
      Object localObject;
      this.similarItems = localObject.getRelatedItems();
    }

    public boolean onPuggleResultClick(int paramInt)
    {
      ExpandedResultView.this.showExpandedResults((ResultModel)this.similarItems.get(paramInt), this.similarItems);
      ((UnveilApplication)ExpandedResultView.this.getContext().getApplicationContext()).getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SIMILAR_ITEM_FROM_EXPANDED_RESULTS);
      return true;
    }

    public boolean onPuggleResultLongClick(Picture paramPicture, ResultItem paramResultItem)
    {
      return false;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ExpandedResultView
 * JD-Core Version:    0.6.2
 */