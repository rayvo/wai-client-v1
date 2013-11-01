package com.google.android.apps.unveil.ui.resultdrawer;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.result.MultipleResultView;
import com.google.android.apps.unveil.ui.result.MultipleResultView.ResultTouchListener;
import com.google.android.apps.unveil.ui.result.ResultItemViewFactory;
import com.google.android.apps.unveil.ui.result.ResultSelectionView.ResultSelectionListener;
import com.google.android.apps.unveil.ui.result.ResultStackView;
import com.google.android.apps.unveil.ui.result.swipe.SwipeableResultsView;
import com.google.android.apps.unveil.ui.result.swipe.SwipeableResultsView.OnResultClickListener;
import java.util.ArrayList;
import java.util.List;

public class SimilarImagesInsideOfDrawer extends InsideOfDrawer
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<? extends ResultModel> contributions;
  private final String imageUrl;
  private boolean initialized;
  private View insideDrawerLayout;
  private List<ResultItem> productCategories;
  private SwipeableResultsView puggleResultsView;
  private SwipeableResultsView similarImageResultsView;
  private List<ResultItem> similarImages;

  public SimilarImagesInsideOfDrawer(Context paramContext, List<ContributedResultItem> paramList, String paramString)
  {
    super(paramContext);
    this.contributions = paramList;
    this.imageUrl = paramString;
    setBackgroundColor(-16777216);
  }

  private MultipleResultView.ResultTouchListener<ResultModel> makeResultTouchListener(final ResultSelectionView.ResultSelectionListener paramResultSelectionListener)
  {
    return new MultipleResultView.ResultTouchListener()
    {
      public boolean onResultTouch(MultipleResultView<ResultModel> paramAnonymousMultipleResultView, MotionEvent paramAnonymousMotionEvent, ResultModel paramAnonymousResultModel)
      {
        if ((paramAnonymousMotionEvent.getAction() == 1) && (paramAnonymousResultModel != null))
          paramResultSelectionListener.onResultSelected(paramAnonymousResultModel);
        return true;
      }
    };
  }

  public int getAboveTheFoldHeight()
  {
    if (!this.initialized)
      return 1;
    return this.insideDrawerLayout.getHeight();
  }

  public int getNumVisiblePuggleCategories()
  {
    return this.puggleResultsView.getNumVisibleResults();
  }

  public int getNumVisibleSimilarImages()
  {
    return this.similarImageResultsView.getNumVisibleResults();
  }

  public void init(List<ResultItem> paramList1, SwipeableResultsView.OnResultClickListener paramOnResultClickListener1, List<ResultItem> paramList2, SwipeableResultsView.OnResultClickListener paramOnResultClickListener2, View.OnClickListener paramOnClickListener, ResultSelectionView.ResultSelectionListener paramResultSelectionListener)
  {
    this.initialized = true;
    this.similarImages = new ArrayList(paramList1);
    this.productCategories = new ArrayList(paramList2);
    this.insideDrawerLayout = View.inflate(getContext(), R.layout.similar_images_inside_of_drawer, null);
    add(this.insideDrawerLayout);
    if (this.contributions != null)
    {
      ResultStackView localResultStackView = new ResultStackView(getContext(), new ResultItemViewFactory(getContext()), this.imageUrl);
      localResultStackView.setResults(this.contributions);
      localResultStackView.setResultTouchListener(makeResultTouchListener(paramResultSelectionListener));
      LinearLayout localLinearLayout = (LinearLayout)this.insideDrawerLayout.findViewById(R.id.result_stack_container);
      localLinearLayout.addView(localResultStackView, 0);
      localLinearLayout.findViewById(R.id.contribution_divider).setVisibility(0);
    }
    if (this.productCategories.isEmpty())
    {
      findViewById(R.id.categories).setVisibility(8);
      findViewById(R.id.category_matches).setVisibility(8);
      if (!this.similarImages.isEmpty())
        break label325;
      findViewById(R.id.similar_images).setVisibility(8);
      findViewById(R.id.similar_images_matches).setVisibility(8);
    }
    while (true)
    {
      if ((this.productCategories.isEmpty()) && (this.similarImages.isEmpty()))
        findViewById(R.id.no_matches_text).setVisibility(8);
      ((Button)this.insideDrawerLayout.findViewById(R.id.send_to_google)).setOnClickListener(paramOnClickListener);
      return;
      this.puggleResultsView = ((SwipeableResultsView)findViewById(R.id.categories));
      this.puggleResultsView.setResults(this.productCategories);
      this.puggleResultsView.setOnResultClickListener(paramOnResultClickListener2);
      this.puggleResultsView.setVisibility(0);
      findViewById(R.id.category_matches).setVisibility(0);
      break;
      label325: this.similarImageResultsView = ((SwipeableResultsView)findViewById(R.id.similar_images));
      this.similarImageResultsView.setResults(this.similarImages);
      this.similarImageResultsView.setOnResultClickListener(paramOnResultClickListener1);
      this.similarImageResultsView.setVisibility(0);
      findViewById(R.id.similar_images_matches).setVisibility(0);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.resultdrawer.SimilarImagesInsideOfDrawer
 * JD-Core Version:    0.6.2
 */