package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.PixelUtils;

public class ThumbnailGridView extends ViewGroup
{
  public static final int DEFAULT_COLUMN_WIDTH_DP = 76;
  private ThumbnailAdapter adapter;
  private boolean childrenDirty;
  private int columnCount;
  private final int columnWidth;
  private int horizontalSpacing;
  private int maxColumnCount;
  private int rawRowHeight;
  private int verticalSpacing;

  public ThumbnailGridView(Context paramContext)
  {
    super(paramContext);
    this.columnWidth = PixelUtils.dipToPix(76.0F, paramContext);
  }

  public ThumbnailGridView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ThumbnailGridView, 0, 0);
    this.columnWidth = localTypedArray.getDimensionPixelOffset(2, 76);
    this.horizontalSpacing = localTypedArray.getDimensionPixelOffset(0, 0);
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
    int i = getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(0, 0), 0, localLayoutParams.height);
    paramView.measure(getChildMeasureSpec(View.MeasureSpec.makeMeasureSpec(this.columnWidth, 1073741824), 0, localLayoutParams.width), i);
  }

  private void populateChildren()
  {
    int i = this.adapter.getCount();
    int j = (-1 + (i + this.columnCount)) / this.columnCount;
    View localView1 = this.adapter.getView(0, null, this);
    measureChild(localView1);
    int k = localView1.getMeasuredWidth();
    int m = localView1.getMeasuredHeight();
    int n = (getMeasuredWidth() - getPaddingLeft() - getPaddingRight() - k * this.columnCount) / (1 + this.columnCount);
    int i1 = 0;
    while (i1 < j)
    {
      LinearLayout localLinearLayout = new LinearLayout(getContext());
      localLinearLayout.setOrientation(0);
      localLinearLayout.setPadding(getPaddingLeft(), 0, getPaddingRight(), 0);
      int i2 = 0;
      int i3;
      if (i2 < this.columnCount)
      {
        i3 = i2 + i1 * this.columnCount;
        if (i3 < i);
      }
      else
      {
        addView(localLinearLayout);
        i1++;
        continue;
      }
      View localView2 = this.adapter.getView(i3, null, this);
      measureChild(localView2);
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(k, m);
      if (i2 == 0);
      for (int i4 = n; ; i4 = 0)
      {
        localLayoutParams.setMargins(i4, 0, n, 0);
        localLinearLayout.addView(localView2, localLayoutParams);
        i2++;
        break;
      }
    }
  }

  private void removeAllViewsRecursively()
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
      ((LinearLayout)getChildAt(j)).removeAllViews();
    removeAllViews();
  }

  public ThumbnailAdapter getAdapter()
  {
    return this.adapter;
  }

  public int getColumnCount()
  {
    return this.columnCount;
  }

  public int getRowHeight()
  {
    return this.rawRowHeight + this.verticalSpacing;
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
        LinearLayout localLinearLayout = (LinearLayout)getChildAt(j);
        int k = j * (this.rawRowHeight + this.verticalSpacing);
        int m = k + (this.rawRowHeight + this.verticalSpacing);
        localLinearLayout.layout(0, k, paramInt3 - paramInt1, m);
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
      k = this.columnWidth + getPaddingLeft() + getPaddingRight();
    if (j == 0)
      m = 0;
    if ((this.adapter != null) && (this.adapter.getCount() > 0))
    {
      View localView = this.adapter.getView(0, null, this);
      measureChild(localView);
      int n = localView.getMeasuredWidth();
      int i1 = localView.getMeasuredHeight();
      this.columnCount = ((k - getPaddingLeft() - getPaddingRight() + this.horizontalSpacing) / (n + this.horizontalSpacing));
      this.columnCount = Math.min(this.columnCount, this.adapter.getCount());
      if (this.maxColumnCount > 0)
        this.columnCount = Math.min(this.maxColumnCount, this.adapter.getCount());
      int i2 = getPaddingTop() + getPaddingBottom();
      int i3 = this.adapter.getCount();
      int i4 = 0;
      while (i4 < i3)
      {
        i2 += i1;
        if (i4 + this.columnCount < i3)
          i2 += this.verticalSpacing;
        i4 += this.columnCount;
      }
      if (i2 > m)
        m = i2;
    }
    setMeasuredDimension(k, m);
  }

  public void setAdapter(ThumbnailAdapter paramThumbnailAdapter)
  {
    this.adapter = paramThumbnailAdapter;
    this.childrenDirty = true;
    requestLayout();
  }

  public void setMaxColumnCount(int paramInt)
  {
    this.maxColumnCount = paramInt;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ThumbnailGridView
 * JD-Core Version:    0.6.2
 */