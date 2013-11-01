package com.google.android.apps.unveil.ui.result.swipe;

import android.content.Context;
import android.util.FloatMath;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ResultItem;

public class InsideSwipeableResultsView extends AdapterView<SwipeableResultsAdapter>
{
  private static final UnveilLogger logger = new UnveilLogger();
  private SwipeableResultsAdapter adapter;
  private final int childWidth;
  private final int horizontalSpacing;
  private final int numColumnsFullyVisible;
  private int numRows;
  private SwipeableResultsView.OnResultClickListener resultListener;
  private View selectedView;
  private final int verticalSpacing;

  private InsideSwipeableResultsView(Context paramContext, final int paramInt1, final int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    super(paramContext);
    this.childWidth = paramInt1;
    this.horizontalSpacing = paramInt2;
    this.verticalSpacing = paramInt3;
    this.numColumnsFullyVisible = paramInt4;
    this.numRows = paramInt5;
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        switch (paramAnonymousMotionEvent.getAction())
        {
        case 2:
        case 3:
        default:
          return false;
        case 0:
          return true;
        case 1:
        }
        int i = (int)((paramAnonymousMotionEvent.getX() - InsideSwipeableResultsView.this.getPaddingLeft()) / (paramInt1 + paramInt2));
        InsideSwipeableResultsView.this.resultListener.onResultClicked((ResultItem)InsideSwipeableResultsView.this.adapter.getItem(i), i);
        return true;
      }
    });
  }

  private void layoutChildren(int paramInt)
  {
    int i = getChildCount() / this.numRows;
    for (int j = 0; ; j++)
    {
      int k;
      int m;
      if (j < i)
      {
        k = getPaddingLeft() + j * (this.childWidth + this.horizontalSpacing);
        m = k + this.childWidth;
      }
      for (int n = 0; n < this.numRows; n++)
      {
        int i1 = n + j * this.numRows;
        if (i1 >= getChildCount())
          return;
        ViewGroup localViewGroup = (ViewGroup)getChildAt(i1);
        int i2 = getPaddingBottom() + n * (paramInt + this.verticalSpacing);
        localViewGroup.layout(k, i2, m, i2 + paramInt);
      }
    }
  }

  public static Builder newBuilder()
  {
    return new Builder();
  }

  private void populateChildren()
  {
    int i = this.adapter.getCount();
    for (int j = 0; j < i; j++)
    {
      View localView = this.adapter.getView(j, null, this);
      ViewGroup.LayoutParams localLayoutParams = localView.getLayoutParams();
      if (localLayoutParams == null)
        localLayoutParams = new ViewGroup.LayoutParams(-2, -2);
      addViewInLayout(localView, -1, localLayoutParams, true);
      localView.measure(0x40000000 | getWidth(), 0);
    }
  }

  public SwipeableResultsAdapter getAdapter()
  {
    return this.adapter;
  }

  public View getSelectedView()
  {
    return this.selectedView;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.adapter == null)
      return;
    if (getChildCount() == 0)
      populateChildren();
    layoutChildren((paramInt4 - paramInt2 - this.verticalSpacing * (-1 + this.numRows)) / this.numRows);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt2);
    int k = View.MeasureSpec.getSize(paramInt1);
    int m = View.MeasureSpec.getSize(paramInt2);
    if (i == 0)
      k = 0;
    if (j == 0)
      m = 0;
    int n;
    if ((this.adapter != null) && (this.adapter.getCount() > 0))
    {
      n = (int)FloatMath.ceil(this.adapter.getCount() / this.numColumnsFullyVisible);
      if (n >= this.numRows)
        break label214;
    }
    while (true)
    {
      this.numRows = n;
      int i1 = (int)Math.ceil(this.adapter.getCount() / this.numRows);
      k = i1 * this.childWidth + (i1 - 1) * this.horizontalSpacing + getPaddingLeft() + getPaddingRight();
      View localView = this.adapter.getView(0, null, this);
      localView.measure(View.MeasureSpec.makeMeasureSpec(this.childWidth, 1073741824), 0);
      m = this.numRows * localView.getMeasuredHeight() + (-1 + this.numRows) * this.verticalSpacing + getPaddingTop() + getPaddingBottom();
      setMeasuredDimension(k, m);
      return;
      label214: n = this.numRows;
    }
  }

  public void setAdapter(SwipeableResultsAdapter paramSwipeableResultsAdapter)
  {
    this.adapter = paramSwipeableResultsAdapter;
    removeAllViewsInLayout();
    requestLayout();
  }

  public void setOnResultClickListener(SwipeableResultsView.OnResultClickListener paramOnResultClickListener)
  {
    this.resultListener = paramOnResultClickListener;
  }

  public void setSelection(int paramInt)
  {
    this.selectedView = getChildAt(paramInt);
  }

  public static class Builder
  {
    private int childWidth;
    private Context context;
    private int horizontalSpacing;
    private int numColumnsFullyVisible;
    private int numRows;
    private int verticalSpacing;

    public InsideSwipeableResultsView build()
    {
      if (this.context == null)
        throw new IllegalArgumentException("context cannot be null");
      if (this.childWidth <= 0)
        throw new IllegalArgumentException("childWidth must be greater than 0");
      if (this.horizontalSpacing < 0)
        throw new IllegalArgumentException("horizontalSpacing must be at least 0");
      if (this.verticalSpacing < 0)
        throw new IllegalArgumentException("verticalSpacing must be at least 0");
      if (this.numColumnsFullyVisible <= 0)
        throw new IllegalArgumentException("numColumnsFullyVisible must be greater than 0");
      if (this.numRows <= 0)
        throw new IllegalArgumentException("numRows must be greater than 0");
      return new InsideSwipeableResultsView(this.context, this.childWidth, this.horizontalSpacing, this.verticalSpacing, this.numColumnsFullyVisible, this.numRows, null);
    }

    public Builder setChildWidth(int paramInt)
    {
      this.childWidth = paramInt;
      return this;
    }

    public Builder setContext(Context paramContext)
    {
      this.context = paramContext;
      return this;
    }

    public Builder setHorizontalSpacing(int paramInt)
    {
      this.horizontalSpacing = paramInt;
      return this;
    }

    public Builder setNumColumnsFullyVisible(int paramInt)
    {
      this.numColumnsFullyVisible = paramInt;
      return this;
    }

    public Builder setNumRows(int paramInt)
    {
      this.numRows = paramInt;
      return this;
    }

    public Builder setVerticalSpacing(int paramInt)
    {
      this.verticalSpacing = paramInt;
      return this;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.swipe.InsideSwipeableResultsView
 * JD-Core Version:    0.6.2
 */