package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.FloatMath;
import android.view.View.MeasureSpec;
import android.widget.ListView;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class PuggleResultsView extends ListView
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final int idealNumCols;
  private final int maxColWidth;
  private PuggleResultAdapter puggleResultAdapter;

  public PuggleResultsView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.PuggleResultsView, 0, 0);
    this.idealNumCols = localTypedArray.getInteger(0, 3);
    this.maxColWidth = localTypedArray.getDimensionPixelOffset(1, 200);
    localTypedArray.recycle();
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    View.MeasureSpec.getMode(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt1);
    if (this.puggleResultAdapter == null)
      return;
    int j = (int)((i - getDividerHeight() * (1 + this.idealNumCols)) / this.idealNumCols);
    int m;
    int k;
    if (j <= this.maxColWidth)
    {
      m = j;
      k = this.idealNumCols;
    }
    while (true)
    {
      this.puggleResultAdapter.setNumCols(k);
      this.puggleResultAdapter.setColWidth(m);
      this.puggleResultAdapter.setHorizontalSpacing(getDividerHeight());
      return;
      k = (int)FloatMath.ceil((i - getDividerHeight()) / (this.maxColWidth + getDividerHeight()));
      m = (int)((i - getDividerHeight() * (k + 1)) / k);
    }
  }

  public void setAdapter(PuggleResultAdapter paramPuggleResultAdapter)
  {
    super.setAdapter(paramPuggleResultAdapter);
    this.puggleResultAdapter = paramPuggleResultAdapter;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.PuggleResultsView
 * JD-Core Version:    0.6.2
 */