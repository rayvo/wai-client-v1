package com.google.android.apps.unveil.ui.result.swipe;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Display;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.List;

public class SwipeableResultsView extends HorizontalScrollView
{
  private int childWidth;
  private final InsideSwipeableResultsView insideView;
  private int numColumnsFullyVisible;
  private final int numRows;

  public SwipeableResultsView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.SwipableResultsView, 0, 0);
    int i = localTypedArray.getDimensionPixelOffset(2, 200);
    int j = localTypedArray.getInteger(3, 3);
    this.numRows = localTypedArray.getInteger(4, 1);
    int k = localTypedArray.getDimensionPixelOffset(0, 3);
    int m = localTypedArray.getDimensionPixelOffset(1, 3);
    localTypedArray.recycle();
    computeChildWidthAndNumColumnsFullyVisible(i, j, k, getScreenWidth());
    this.insideView = InsideSwipeableResultsView.newBuilder().setContext(paramContext).setChildWidth(this.childWidth).setHorizontalSpacing(k).setVerticalSpacing(m).setNumColumnsFullyVisible(this.numColumnsFullyVisible).setNumRows(this.numRows).build();
    addView(this.insideView);
  }

  private int getScreenWidth()
  {
    return ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay().getWidth();
  }

  void computeChildWidthAndNumColumnsFullyVisible(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = (int)((paramInt4 - paramInt2 * paramInt3) / (0.5D + paramInt2));
    if (i <= paramInt1)
    {
      this.childWidth = i;
      this.numColumnsFullyVisible = paramInt2;
      return;
    }
    this.numColumnsFullyVisible = ((int)Math.ceil((paramInt4 - 0.5D * paramInt1) / (paramInt1 + paramInt3)));
    this.childWidth = ((int)((paramInt4 - paramInt3 * this.numColumnsFullyVisible) / (0.5D + this.numColumnsFullyVisible)));
  }

  public int getChildWidth()
  {
    return this.childWidth;
  }

  public int getNumVisibleResults()
  {
    return this.numRows * this.numColumnsFullyVisible;
  }

  public void setOnResultClickListener(OnResultClickListener paramOnResultClickListener)
  {
    this.insideView.setOnResultClickListener(paramOnResultClickListener);
  }

  public void setResults(List<ResultItem> paramList)
  {
    this.insideView.setAdapter(new SwipeableResultsAdapter(getContext(), paramList, this.childWidth));
  }

  public static abstract interface OnResultClickListener
  {
    public abstract boolean onResultClicked(ResultItem paramResultItem, int paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.swipe.SwipeableResultsView
 * JD-Core Version:    0.6.2
 */