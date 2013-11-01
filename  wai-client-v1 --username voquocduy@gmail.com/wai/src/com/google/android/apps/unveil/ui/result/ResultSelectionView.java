package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.resultdrawer.ResultDrawer;
import com.google.android.apps.unveil.ui.resultdrawer.ResultsInsideOfDrawer;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ResultSelectionView extends FrameLayout
  implements MultipleResultView.ResultTouchListener<ResultModel>
{
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private QueryImageBackground background;
  private ImageView backgroundView;
  private MultipleResultView<ResultModel> boundingBoxes;
  private final List<ResultModel> displayedResults;
  private boolean initialized = false;
  private ResultDrawer resultDrawer;
  private ResultSelectionListener resultSelectionListener;
  private MultipleResultView<ResultModel> resultStack;
  private volatile int selectedResultIndex = -1;

  public ResultSelectionView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.displayedResults = new LinkedList();
  }

  public ResultSelectionView(Context paramContext, Picture paramPicture, ResultSelectionListener paramResultSelectionListener, UnveilContext paramUnveilContext, ResultsInsideOfDrawer paramResultsInsideOfDrawer, QueryImageBackground paramQueryImageBackground, List<ResultModel> paramList)
  {
    super(paramContext);
    setFocusableInTouchMode(true);
    requestFocus();
    this.displayedResults = new LinkedList();
    setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    init(paramContext, paramResultSelectionListener, paramUnveilContext, paramResultsInsideOfDrawer, paramQueryImageBackground, paramList);
  }

  private void clearHighlights()
  {
    this.boundingBoxes.clearHighlights();
    this.resultStack.clearHighlights();
  }

  private void highlightResult(int paramInt)
  {
    try
    {
      if (this.selectedResultIndex >= 0)
      {
        int i = this.selectedResultIndex;
        int j = this.displayedResults.size();
        if (i < j)
          break label34;
      }
      while (true)
      {
        return;
        label34: this.resultStack.highlightResult(paramInt);
        this.boundingBoxes.highlightResult(paramInt);
      }
    }
    finally
    {
    }
  }

  private int indexOf(ResultModel paramResultModel)
  {
    for (int i = 0; i < this.displayedResults.size(); i++)
      if (((ResultModel)this.displayedResults.get(i)).equals(paramResultModel))
        return i;
    return -1;
  }

  private void setSelectedResultIndex(int paramInt)
  {
    this.selectedResultIndex = paramInt;
    updateHighlights();
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (this.displayedResults == null)
      return false;
    int i = this.selectedResultIndex;
    boolean bool = false;
    if (i >= 0)
    {
      int j = this.selectedResultIndex;
      int k = this.displayedResults.size();
      bool = false;
      if (j < k)
      {
        ResultModel localResultModel = (ResultModel)this.displayedResults.get(this.selectedResultIndex);
        bool = false;
        if (localResultModel != null)
          bool = localResultModel.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
      }
    }
    if (!bool)
    {
      paramAccessibilityEvent.setItemCount(this.displayedResults.size());
      paramAccessibilityEvent.setCurrentItemIndex(this.selectedResultIndex);
    }
    return bool;
  }

  public void forceShowResultList()
  {
    this.resultDrawer.setVisibility(0);
  }

  public ResultDrawer getResultDrawer()
  {
    return this.resultDrawer;
  }

  public void init(Context paramContext, ResultSelectionListener paramResultSelectionListener, UnveilContext paramUnveilContext, ResultsInsideOfDrawer paramResultsInsideOfDrawer, QueryImageBackground paramQueryImageBackground, List<ResultModel> paramList)
  {
    if (this.initialized)
      logger.e("init() called multiple times!", new Object[0]);
    this.initialized = true;
    this.resultSelectionListener = paramResultSelectionListener;
    this.application = paramUnveilContext;
    this.backgroundView = new ImageView(paramContext);
    this.backgroundView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    addView(this.backgroundView);
    this.displayedResults.addAll(paramList);
    this.background = paramQueryImageBackground;
    BoundingBoxesView localBoundingBoxesView = new BoundingBoxesView(paramContext, paramQueryImageBackground);
    localBoundingBoxesView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    localBoundingBoxesView.setResults(paramList);
    addView(localBoundingBoxesView);
    this.boundingBoxes = localBoundingBoxesView;
    localBoundingBoxesView.setResultTouchListener(this);
    this.resultDrawer = new ResultDrawer(paramContext, paramResultsInsideOfDrawer);
    this.resultDrawer.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
    addView(this.resultDrawer);
    this.resultStack = paramResultsInsideOfDrawer.getResultStack();
    this.resultStack.setResults(paramList);
    this.resultStack.setResultTouchListener(this);
    requestLayout();
  }

  public void onConfigChanged(Configuration paramConfiguration, Context paramContext)
  {
    this.background.getBackground(paramContext, this.application.getViewport()).populateWithBitmap(this.backgroundView);
    this.resultDrawer.onConfigChanged(paramConfiguration);
    invalidate();
    requestLayout();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getAction() == 0);
    switch (paramInt)
    {
    case 21:
    case 22:
    default:
      return false;
    case 20:
      if (this.selectedResultIndex < 0);
      for (this.selectedResultIndex = 0; ; this.selectedResultIndex = Math.min(1 + this.selectedResultIndex, -1 + this.resultStack.numResults()))
      {
        updateHighlights();
        sendAccessibilityEvent(4);
        return true;
      }
    case 19:
      if (this.selectedResultIndex < 0);
      for (this.selectedResultIndex = (-1 + this.resultStack.numResults()); ; this.selectedResultIndex = Math.max(-1 + this.selectedResultIndex, 0))
      {
        updateHighlights();
        sendAccessibilityEvent(4);
        return true;
      }
    case 23:
    }
    if (this.selectedResultIndex >= 0)
    {
      this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE, this.selectedResultIndex, 3);
      this.resultSelectionListener.onResultSelected((ResultModel)this.displayedResults.get(this.selectedResultIndex));
    }
    return true;
  }

  public boolean onResultTouch(MultipleResultView<ResultModel> paramMultipleResultView, MotionEvent paramMotionEvent, ResultModel paramResultModel)
  {
    int i = indexOf(paramResultModel);
    setSelectedResultIndex(i);
    if ((paramMotionEvent.getAction() != 1) || (paramResultModel == null) || (((paramResultModel instanceof ResultItem)) && (((ResultItem)paramResultModel).isWithdrawn())))
      return true;
    this.resultSelectionListener.onResultSelected(paramResultModel);
    int j;
    if (paramMultipleResultView == this.boundingBoxes)
    {
      j = 1;
      if (j == 0)
        break label109;
    }
    label109: for (NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.BOUNDING_BOX_CLICK_ON_RESULTS_PAGE; ; localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST)
    {
      if (!(paramResultModel instanceof ResultItem))
        break label117;
      ResultItem localResultItem = (ResultItem)paramResultModel;
      ClickTracker.logResultClick(getContext(), localCLICK_TARGET, localResultItem, i);
      return true;
      j = 0;
      break;
    }
    label117: this.application.getClickTracker().logClick(localCLICK_TARGET, i, 3);
    return true;
  }

  public void resetResultSelection()
  {
    clearHighlights();
    this.selectedResultIndex = -1;
  }

  public void updateHighlights()
  {
    clearHighlights();
    if (this.selectedResultIndex != -1)
      highlightResult(this.selectedResultIndex);
  }

  public static class BoundedResult
  {
    final RectF bounds;
    final ResultModel result;

    static
    {
      if (!ResultSelectionView.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    public BoundedResult(ResultModel paramResultModel, RectF paramRectF)
    {
      assert (paramResultModel != null);
      this.result = paramResultModel;
      this.bounds = paramRectF;
    }

    public static List<ResultModel> extractResultItems(List<BoundedResult> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((BoundedResult)localIterator.next()).result);
      return localArrayList;
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
    {
      if (this.result == null)
        return false;
      return this.result.dispatchPopulateAccessibilityEvent(paramAccessibilityEvent);
    }

    public String toString()
    {
      return "[" + this.bounds.toString() + ", " + this.result.toString() + "]";
    }
  }

  public static abstract interface ResultSelectionListener
  {
    public abstract void onResultSelected(ResultModel paramResultModel);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ResultSelectionView
 * JD-Core Version:    0.6.2
 */