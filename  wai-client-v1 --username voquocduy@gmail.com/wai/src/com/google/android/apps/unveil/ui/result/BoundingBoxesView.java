package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AbsoluteLayout;
import android.widget.AbsoluteLayout.LayoutParams;
import com.google.android.apps.unveil.R.anim;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class BoundingBoxesView extends AbsoluteLayout
  implements MultipleResultView<ResultModel>
{
  private static final int NONE = -1;
  private static final float RESOURCE_LINE_OFFSET = 0.375F;
  private static final UnveilLogger logger = new UnveilLogger();
  private final int RESOURCE_HEIGHT;
  private final int RESOURCE_WIDTH;
  private final Set<ResultModel> animatedResults = new HashSet();
  private final QueryImageBackground background;
  private int highlightedResult = -1;
  private final Paint outsideOfHolePaint;
  private List<ResultModel> resultItems;
  private final List<BoxedResult> results = Collections.synchronizedList(new LinkedList());
  private MultipleResultView.ResultTouchListener<ResultModel> touchListener;

  public BoundingBoxesView(Context paramContext, QueryImageBackground paramQueryImageBackground)
  {
    super(paramContext);
    this.background = paramQueryImageBackground;
    Drawable localDrawable = getContext().getResources().getDrawable(R.drawable.bbox_green);
    this.RESOURCE_WIDTH = localDrawable.getIntrinsicWidth();
    this.RESOURCE_HEIGHT = localDrawable.getIntrinsicHeight();
    setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
    setWillNotDraw(false);
    this.outsideOfHolePaint = new Paint();
    this.outsideOfHolePaint.setColor(getResources().getColor(R.color.bounding_box_dimming));
  }

  private Rect adjustForBoundingBoxDrawable(RectF paramRectF)
  {
    Rect localRect = GeometryUtils.round(paramRectF);
    logger.d("Adjusting for bb drawable.", new Object[0]);
    int i = localRect.width();
    int j = localRect.height();
    int k = (int)(i + 0.375F * this.RESOURCE_WIDTH);
    int m = (int)(j + 0.375F * this.RESOURCE_HEIGHT);
    int n = Math.max(this.RESOURCE_WIDTH, k);
    int i1 = Math.max(this.RESOURCE_HEIGHT, m);
    if ((n != i) || (i1 != j))
    {
      logger.d("Returning upscaled rect.", new Object[0]);
      return GeometryUtils.scale(localRect, i1, n);
    }
    logger.d("No upscaling needed.", new Object[0]);
    return new Rect(localRect);
  }

  private BoundingBoxView createBoundingBox(ResultSelectionView.BoundedResult paramBoundedResult, int paramInt)
  {
    ResultModel localResultModel = paramBoundedResult.result;
    Rect localRect = adjustForBoundingBoxDrawable(paramBoundedResult.bounds);
    BoundingBoxView localBoundingBoxView = new BoundingBoxView(getContext(), localResultModel.isAdvertisement(), localResultModel.isUserGenerated(), paramInt);
    localBoundingBoxView.setLayoutParams(new AbsoluteLayout.LayoutParams(localRect.right - localRect.left, localRect.bottom - localRect.top, localRect.left, localRect.top));
    localBoundingBoxView.setVisibility(0);
    if (!this.animatedResults.contains(localResultModel))
    {
      this.animatedResults.add(localResultModel);
      localBoundingBoxView.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.box_entrance));
    }
    return localBoundingBoxView;
  }

  private void drawHoleAround(Canvas paramCanvas, BoundingBoxView paramBoundingBoxView)
  {
    paramCanvas.drawRect(0.0F, 0.0F, getRight(), paramBoundingBoxView.getTop(), this.outsideOfHolePaint);
    paramCanvas.drawRect(0.0F, paramBoundingBoxView.getTop(), paramBoundingBoxView.getLeft(), paramBoundingBoxView.getBottom(), this.outsideOfHolePaint);
    paramCanvas.drawRect(paramBoundingBoxView.getRight(), paramBoundingBoxView.getTop(), getRight(), paramBoundingBoxView.getBottom(), this.outsideOfHolePaint);
    paramCanvas.drawRect(0.0F, paramBoundingBoxView.getBottom(), getRight(), getBottom(), this.outsideOfHolePaint);
  }

  private void fadeAllExcept(BoundingBoxView paramBoundingBoxView)
  {
    setFadeForAllExcept(paramBoundingBoxView, true);
  }

  private BoundingBoxView getBoxAt(int paramInt)
  {
    List localList = this.results;
    if (paramInt >= 0);
    try
    {
      if (paramInt >= this.results.size())
        return null;
      BoundingBoxView localBoundingBoxView = ((BoxedResult)this.results.get(paramInt)).boundingBoxView;
      return localBoundingBoxView;
    }
    finally
    {
    }
  }

  private BoundingBoxView getBoxOf(ResultModel paramResultModel)
  {
    synchronized (this.results)
    {
      Iterator localIterator = this.results.iterator();
      while (localIterator.hasNext())
      {
        BoxedResult localBoxedResult = (BoxedResult)localIterator.next();
        if (localBoxedResult.result.equals(paramResultModel))
        {
          BoundingBoxView localBoundingBoxView = localBoxedResult.boundingBoxView;
          return localBoundingBoxView;
        }
      }
      return null;
    }
  }

  private void mergeInNewResults(List<ResultSelectionView.BoundedResult> paramList)
  {
    synchronized (this.results)
    {
      removeNoLongerVisibleResults(ResultSelectionView.BoundedResult.extractResultItems(paramList));
      int i = 0;
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ResultSelectionView.BoundedResult localBoundedResult = (ResultSelectionView.BoundedResult)localIterator.next();
        if (localBoundedResult.bounds != null)
          if (isDisplaying(localBoundedResult.result))
          {
            Rect localRect2 = adjustForBoundingBoxDrawable(localBoundedResult.bounds);
            getBoxOf(localBoundedResult.result).layout(localRect2.left, localRect2.top, localRect2.right, localRect2.bottom);
            if ((localBoundedResult.result instanceof ResultItem))
              i++;
          }
          else
          {
            BoundingBoxView localBoundingBoxView = createBoundingBox(localBoundedResult, i);
            Rect localRect1 = adjustForBoundingBoxDrawable(localBoundedResult.bounds);
            localBoundingBoxView.layout(localRect1.left, localRect1.top, localRect1.right, localRect1.bottom);
            this.results.add(new BoxedResult(localBoundedResult.result, localBoundingBoxView));
          }
      }
    }
  }

  private void remove(ResultModel paramResultModel)
  {
    List localList = this.results;
    int i = -1;
    for (int j = 0; ; j++)
      try
      {
        if (j < this.results.size())
        {
          if (((BoxedResult)this.results.get(j)).result.equals(paramResultModel))
            i = j;
        }
        else
        {
          if (i != -1)
            this.results.remove(i);
          return;
        }
      }
      finally
      {
      }
  }

  private void removeNoLongerVisibleResults(List<ResultModel> paramList)
  {
    synchronized (this.results)
    {
      Iterator localIterator = BoxedResult.extractResultItems(this.results).iterator();
      while (localIterator.hasNext())
      {
        ResultModel localResultModel = (ResultModel)localIterator.next();
        if (!paramList.contains(localResultModel))
          remove(localResultModel);
      }
    }
  }

  private void setFadeForAllExcept(BoundingBoxView paramBoundingBoxView, boolean paramBoolean)
  {
    Iterator localIterator = this.results.iterator();
    while (localIterator.hasNext())
    {
      BoundingBoxView localBoundingBoxView = ((BoxedResult)localIterator.next()).boundingBoxView;
      if ((localBoundingBoxView != paramBoundingBoxView) && (localBoundingBoxView != null))
        localBoundingBoxView.setFaded(paramBoolean);
    }
  }

  private void unfadeAllexcept(BoundingBoxView paramBoundingBoxView)
  {
    setFadeForAllExcept(paramBoundingBoxView, false);
  }

  private void updateBoundingBoxes()
  {
    removeAllViews();
    clearDisappearingChildren();
    Iterator localIterator = this.results.iterator();
    while (localIterator.hasNext())
      addView(((BoxedResult)localIterator.next()).boundingBoxView);
    if (getChildCount() != this.results.size())
      throw new IllegalStateException("More children than results.");
    measure(0, 0);
  }

  public void clear()
  {
    synchronized (this.results)
    {
      Iterator localIterator = this.results.iterator();
      if (localIterator.hasNext())
        removeView(((BoxedResult)localIterator.next()).boundingBoxView);
    }
    this.results.clear();
    invalidate();
  }

  public void clearHighlights()
  {
    if (this.highlightedResult != -1)
    {
      unHighlightResult(this.highlightedResult);
      this.highlightedResult = -1;
    }
  }

  public ResultModel getResult(int paramInt)
  {
    return ((BoxedResult)this.results.get(paramInt)).result;
  }

  public void highlightResult(int paramInt)
  {
    BoundingBoxView localBoundingBoxView = getBoxAt(paramInt);
    if ((localBoundingBoxView == null) || (localBoundingBoxView.isHighlited()))
      return;
    localBoundingBoxView.setHighlighted(true);
    bringChildToFront(localBoundingBoxView);
    fadeAllExcept(localBoundingBoxView);
    this.highlightedResult = paramInt;
    invalidate();
  }

  public boolean isDisplaying(ResultModel paramResultModel)
  {
    Iterator localIterator = this.results.iterator();
    while (localIterator.hasNext())
      if (((BoxedResult)localIterator.next()).result.equals(paramResultModel))
        return true;
    return false;
  }

  public int numResults()
  {
    return this.results.size();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    if (this.highlightedResult != -1)
      drawHoleAround(paramCanvas, getBoxAt(this.highlightedResult));
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    logger.i("onLayout", new Object[0]);
    mergeInNewResults(this.background.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4, getContext(), this.resultItems));
    updateBoundingBoxes();
  }

  public void removeResult(ResultModel paramResultModel)
  {
    remove(paramResultModel);
    removeView(getBoxOf(paramResultModel));
  }

  public ResultModel resultItemAtPoint(int paramInt1, int paramInt2)
  {
    int i = 2147483647;
    ResultModel localResultModel = null;
    Iterator localIterator = this.results.iterator();
    while (localIterator.hasNext())
    {
      BoxedResult localBoxedResult = (BoxedResult)localIterator.next();
      BoundingBoxView localBoundingBoxView = localBoxedResult.boundingBoxView;
      if (localBoundingBoxView != null)
      {
        int j = localBoundingBoxView.getWidth() * localBoundingBoxView.getHeight();
        if ((paramInt1 >= localBoundingBoxView.getLeft()) && (paramInt1 <= localBoundingBoxView.getRight()) && (paramInt2 >= localBoundingBoxView.getTop()) && (paramInt2 <= localBoundingBoxView.getBottom()) && (j < i))
        {
          i = j;
          localResultModel = localBoxedResult.result;
        }
      }
    }
    return localResultModel;
  }

  public void setResultTouchListener(MultipleResultView.ResultTouchListener<ResultModel> paramResultTouchListener)
  {
    this.touchListener = paramResultTouchListener;
    setOnTouchListener(new BoundingBoxTouchListener(null));
  }

  public void setResults(List<ResultModel> paramList)
  {
    this.resultItems = paramList;
  }

  public void unHighlightResult(int paramInt)
  {
    BoundingBoxView localBoundingBoxView = getBoxAt(paramInt);
    if (localBoundingBoxView == null)
      return;
    localBoundingBoxView.setHighlighted(false);
    unfadeAllexcept(localBoundingBoxView);
    invalidate();
  }

  public void updateColors(List<ResultModel> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ResultModel localResultModel = (ResultModel)localIterator.next();
      BoundingBoxView localBoundingBoxView = getBoxOf(localResultModel);
      if (localBoundingBoxView != null)
        localBoundingBoxView.updateIndex(paramList.indexOf(localResultModel));
    }
  }

  private class BoundingBoxTouchListener
    implements View.OnTouchListener
  {
    private BoundingBoxTouchListener()
    {
    }

    public boolean onTouch(View paramView, MotionEvent paramMotionEvent)
    {
      ResultModel localResultModel = BoundingBoxesView.this.resultItemAtPoint((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
      return BoundingBoxesView.this.touchListener.onResultTouch(BoundingBoxesView.this, paramMotionEvent, localResultModel);
    }
  }

  protected static class BoxedResult
  {
    final BoundingBoxView boundingBoxView;
    final ResultModel result;

    static
    {
      if (!BoundingBoxesView.class.desiredAssertionStatus());
      for (boolean bool = true; ; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }

    public BoxedResult(ResultModel paramResultModel, BoundingBoxView paramBoundingBoxView)
    {
      assert (paramResultModel != null);
      this.result = paramResultModel;
      this.boundingBoxView = paramBoundingBoxView;
    }

    public static List<ResultModel> extractResultItems(List<BoxedResult> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localArrayList.add(((BoxedResult)localIterator.next()).result);
      return localArrayList;
    }

    public String toString()
    {
      return "BoxedResult [result=" + this.result + ", boundingBoxView=" + this.boundingBoxView + "]";
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.BoundingBoxesView
 * JD-Core Version:    0.6.2
 */