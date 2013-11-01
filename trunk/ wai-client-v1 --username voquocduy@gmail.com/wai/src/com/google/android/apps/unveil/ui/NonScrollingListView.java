package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Adapter;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class NonScrollingListView extends ViewGroup
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Adapter adapter;
  private boolean childrenDirty;
  private int rawRowHeight;
  private int verticalSpacing;

  public NonScrollingListView(Context paramContext)
  {
    super(paramContext);
  }

  public NonScrollingListView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ThumbnailGridView, 0, 0);
    this.verticalSpacing = localTypedArray.getDimensionPixelOffset(1, 0);
    localTypedArray.recycle();
  }

  private void measureChild(View paramView)
  {
    ViewGroup.LayoutParams localLayoutParams = paramView.getLayoutParams();
    if (localLayoutParams == null)
    {
      localLayoutParams = new ViewGroup.LayoutParams(-2, -2);
      paramView.setLayoutParams(localLayoutParams);
    }
    int i = getWidth() - getPaddingLeft() - getPaddingRight();
    int j = getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, localLayoutParams.height);
    paramView.measure(getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(i, 1073741824), 0, i), j);
  }

  private void populateChildren()
  {
    int i = this.adapter.getCount();
    for (int j = 0; j < i; j++)
    {
      View localView = this.adapter.getView(j, null, this);
      measureChild(localView);
      addView(localView);
    }
  }

  private void removeAllViewsRecursively()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
      ((ViewGroup)getChildAt(j)).removeAllViews();
    removeAllViews();
  }

  public Adapter getAdapter()
  {
    return this.adapter;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((this.adapter != null) && ((this.childrenDirty) || (paramBoolean)))
    {
      removeAllViewsRecursively();
      populateChildren();
      this.childrenDirty = false;
    }
    int i = getChildCount();
    if (i == 0);
    while (true)
    {
      return;
      this.rawRowHeight = ((paramInt4 - paramInt2 - this.verticalSpacing * (i - 1)) / i);
      for (int j = 0; j < i; j++)
      {
        View localView = getChildAt(j);
        int k = j * (this.rawRowHeight + this.verticalSpacing);
        int m = k + (this.rawRowHeight + this.verticalSpacing);
        localView.layout(getPaddingLeft(), k, paramInt3 - paramInt1 - getPaddingLeft() - getPaddingRight(), m);
      }
    }
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
    if ((this.adapter != null) && (this.adapter.getCount() > 0))
    {
      View localView = this.adapter.getView(0, null, this);
      measureChild(localView);
      localView.getMeasuredWidth();
      int n = localView.getMeasuredHeight();
      int i1 = this.adapter.getCount();
      int i2 = getPaddingTop() + getPaddingBottom() + i1 * n + (i1 - 1) * this.verticalSpacing;
      if (i2 > m)
        m = i2;
    }
    setMeasuredDimension(k, m);
  }

  public void setAdapter(Adapter paramAdapter)
  {
    this.adapter = paramAdapter;
    this.childrenDirty = true;
    requestLayout();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.NonScrollingListView
 * JD-Core Version:    0.6.2
 */