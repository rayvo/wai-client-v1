package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.text.TextUtils;
import android.text.TextUtils.TruncateAt;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.UrlGroupProtos.Url.Purpose;

public class ResultItemViewFactory
{
  private static final boolean ALLOW_QUICK_ACTIONS;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Context context;
  private final ResultViewUtils resultViewUtils;

  public ResultItemViewFactory(Context paramContext)
  {
    this.context = paramContext;
    this.resultViewUtils = new ResultViewUtils();
  }

  private void addQuickActions(ResultItem paramResultItem, View paramView)
  {
  }

  private void bindViewForOneShot(View paramView, ResultItem paramResultItem, int paramInt)
  {
    populateThumbnail(paramResultItem, paramView);
    if (!TextUtils.isEmpty(paramResultItem.getDirectUrl()))
      ((ImageView)paramView.findViewById(R.id.direct_link)).setVisibility(0);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.result_bar);
    if ((paramResultItem.isAdvertisement()) || (paramResultItem.isUserGenerated()))
      localImageView.setBackgroundResource(R.drawable.result_bar_yellow);
    while (!paramResultItem.hasAnnotationResult())
    {
      setLabels(paramResultItem, paramView);
      return;
      localImageView.setBackgroundResource(paramInt);
    }
    if (this.resultViewUtils.isTranslationResult(paramResultItem))
    {
      hideResultViews(paramView);
      this.resultViewUtils.setTranslationLabels(paramResultItem, paramView);
      ((TextView)paramView.findViewById(R.id.translation)).setMaxLines(2);
      ((TextView)paramView.findViewById(R.id.translation)).setEllipsize(TextUtils.TruncateAt.END);
      ((TextView)paramView.findViewById(R.id.text)).setMaxLines(2);
      ((TextView)paramView.findViewById(R.id.text)).setEllipsize(TextUtils.TruncateAt.END);
    }
    while (true)
    {
      addQuickActions(paramResultItem, paramView);
      return;
      if (this.resultViewUtils.isBookResult(paramResultItem))
      {
        hideResultViews(paramView);
        this.resultViewUtils.setBookLabels(paramResultItem, paramView);
      }
      else if (this.resultViewUtils.isProductResult(paramResultItem))
      {
        hideResultViews(paramView);
        this.resultViewUtils.setProductLabels(paramResultItem, paramView);
      }
      else
      {
        setLabels(paramResultItem, paramView);
      }
    }
  }

  public static int getIconResourceForPurpose(UrlGroupProtos.Url.Purpose paramPurpose)
  {
    switch (2.$SwitchMap$com$google$goggles$UrlGroupProtos$Url$Purpose[paramPurpose.ordinal()])
    {
    default:
      return R.drawable.action_search;
    case 1:
      return R.drawable.action_map;
    case 2:
      return R.drawable.action_shop;
    case 3:
      return R.drawable.action_book;
    case 4:
      return R.drawable.action_translate;
    case 5:
      return R.drawable.action_call;
    case 6:
      return R.drawable.action_email;
    case 7:
      return R.drawable.action_add;
    case 8:
      return R.drawable.action_open;
    case 9:
      return R.drawable.action_puzzle;
    case 10:
    }
    return R.drawable.action_shop;
  }

  private static void hideResultViews(View paramView)
  {
    paramView.findViewById(R.id.translation_content).setVisibility(8);
    paramView.findViewById(R.id.book_content).setVisibility(8);
    paramView.findViewById(R.id.product_content).setVisibility(8);
    paramView.findViewById(R.id.result_content).setVisibility(8);
  }

  private void populateThumbnail(ResultItem paramResultItem, View paramView)
  {
    ThumbnailProvider localThumbnailProvider = ((UnveilApplication)this.context.getApplicationContext()).getThumbnailCache();
    final ImageView localImageView = (ImageView)paramView.findViewById(R.id.result_thumbnail);
    localImageView.setVisibility(0);
    String str = paramResultItem.getThumbnailUrl();
    if (TextUtils.isEmpty(str))
    {
      logger.e("Thumbnail URL is null/empty", new Object[0]);
      return;
    }
    localThumbnailProvider.fetch(str, new ThumbnailProvider.ThumbnailListener()
    {
      public void onThumbnailReady(Picture paramAnonymousPicture)
      {
        if (paramAnonymousPicture == null)
          return;
        ResultItemViewFactory.this.populateThumbnailView(localImageView, paramAnonymousPicture);
      }
    }
    , ThumbnailProvider.SizeSpec.FIFE_ORIGINAL);
  }

  private void populateThumbnailView(ImageView paramImageView, Picture paramPicture)
  {
    paramPicture.populateWithBitmap(paramImageView);
    paramImageView.startAnimation(AnimationUtils.loadAnimation(this.context, 17432576));
  }

  public void bindViewForContinuousHistory(View paramView, ResultItem paramResultItem)
  {
    bindViewForOneShot(paramView, paramResultItem, 0);
  }

  public void bindViewForContinuousTimeline(View paramView, ResultItem paramResultItem)
  {
    bindViewForOneShot(paramView, paramResultItem, 0);
    paramView.findViewById(R.id.result_thumbnail).setVisibility(8);
    paramView.findViewById(R.id.result_bar).setVisibility(8);
  }

  public View createContributedView(ContributedResultItem paramContributedResultItem, String paramString)
  {
    View localView = View.inflate(this.context, R.layout.contributed_item, null);
    localView.setVisibility(0);
    TextView localTextView1 = (TextView)localView.findViewById(R.id.contribution_title);
    TextView localTextView2 = (TextView)localView.findViewById(R.id.contribution_description);
    localTextView1.setText(paramContributedResultItem.getTitle());
    localTextView2.setText(paramContributedResultItem.getUserContribution().getDescription());
    ((UnveilContext)this.context.getApplicationContext()).getThumbnailCache().fetch(paramString, ThumbnailProvider.makeImageViewThumbnailListener((ImageView)localView.findViewById(R.id.result_thumbnail), paramString), ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
    return localView;
  }

  public View createViewForContinuousHistory(ResultItem paramResultItem)
  {
    View localView = createViewForOneShot(paramResultItem, 0);
    localView.findViewById(R.id.result_thumbnail).setVisibility(0);
    localView.findViewById(R.id.result_bar).setVisibility(8);
    return localView;
  }

  public View createViewForContinuousTimeline(ResultItem paramResultItem)
  {
    View localView = createViewForOneShot(paramResultItem, 0);
    localView.findViewById(R.id.result_thumbnail).setVisibility(8);
    localView.findViewById(R.id.result_bar).setVisibility(8);
    localView.findViewById(R.id.timeline_padding).setVisibility(0);
    return localView;
  }

  public View createViewForOneShot(ResultItem paramResultItem, int paramInt)
  {
    if (paramResultItem.isWithdrawn())
      return View.inflate(this.context, R.layout.withdrawn_result_item, null);
    View localView = View.inflate(this.context, R.layout.result_item, null);
    bindViewForOneShot(localView, paramResultItem, paramInt);
    return localView;
  }

  public void setHighlighted(View paramView, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      paramView.findViewById(R.id.content).setBackgroundResource(R.drawable.selected_result);
      return;
    }
    paramView.findViewById(R.id.content).setBackgroundResource(R.color.result_item_background);
  }

  public void setLabels(ResultItem paramResultItem, View paramView)
  {
    hideResultViews(paramView);
    paramView.findViewById(R.id.result_content).setVisibility(0);
    ((TextView)paramView.findViewById(R.id.result_title)).setText(paramResultItem.getTitle());
    ((TextView)paramView.findViewById(R.id.result_subtitle)).setText(paramResultItem.getSubtitle());
    ((TextView)paramView.findViewById(R.id.result_type)).setText(paramResultItem.getType());
    ((TextView)paramView.findViewById(R.id.result_subtype)).setText(paramResultItem.getSubtype());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ResultItemViewFactory
 * JD-Core Version:    0.6.2
 */