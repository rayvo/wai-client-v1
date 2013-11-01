package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;

public class ContinuousResultItemView extends ViewGroup
{
  private final View.OnClickListener clickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      if (ContinuousResultItemView.this.listener != null)
        ContinuousResultItemView.this.listener.onResultClicked((ResultItem)paramAnonymousView.getTag());
    }
  };
  private ResultClickListener listener;
  private int maxChildHeight = 0;
  private final ResultItemViewFactory viewFactory;

  public ContinuousResultItemView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.viewFactory = new ResultItemViewFactory(paramContext);
  }

  private int getItemWidth()
  {
    if (getChildCount() == 0)
      return 0;
    return getWidth() / getChildCount();
  }

  private void onViewUpdated(View paramView)
  {
    paramView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(getItemWidth()), 1073741824), View.MeasureSpec.makeMeasureSpec(0, 0));
    if (paramView.getMeasuredHeight() > this.maxChildHeight)
    {
      this.maxChildHeight = paramView.getMeasuredHeight();
      measure(View.MeasureSpec.makeMeasureSpec(getItemWidth(), 1073741824), 0);
    }
  }

  public void addResult(ResultItem paramResultItem)
  {
    View localView = this.viewFactory.createViewForContinuousTimeline(paramResultItem);
    localView.setOnClickListener(this.clickListener);
    localView.setTag(paramResultItem);
    onViewUpdated(localView);
    addView(localView, 0);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = paramInt1;
    for (int k = 0; k < i; k++)
    {
      View localView = getChildAt(k);
      int m = localView.getMeasuredWidth();
      localView.layout(j, paramInt2, j + m, paramInt4);
      j += m;
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    if (i == 0)
    {
      setMeasuredDimension(0, 0);
      return;
    }
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      View localView = getChildAt(k);
      localView.measure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(paramInt1), 1073741824), View.MeasureSpec.makeMeasureSpec(this.maxChildHeight, 1073741824));
      j += localView.getMeasuredWidth();
    }
    int m = View.MeasureSpec.getMode(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt1);
    if (m != 0)
      j = Math.max(n, j);
    setMeasuredDimension(j, this.maxChildHeight);
  }

  public void scroll(int paramInt)
  {
    scrollTo(paramInt, 0);
  }

  public void setListener(ResultClickListener paramResultClickListener)
  {
    this.listener = paramResultClickListener;
  }

  public void updateResult(final ResultItem paramResultItem)
  {
    int i = getChildCount();
    for (int j = 0; ; j++)
    {
      final View localView = null;
      if (j < i)
      {
        if (((ResultItem)getChildAt(j).getTag()).getAnnotationResult().getResultId().equals(paramResultItem.getAnnotationResult().getResultId()))
          localView = getChildAt(j);
      }
      else
      {
        if (localView != null)
        {
          localView.setTag(paramResultItem);
          post(new Runnable()
          {
            public void run()
            {
              ContinuousResultItemView.this.viewFactory.bindViewForContinuousTimeline(localView, paramResultItem);
              ContinuousResultItemView.this.onViewUpdated(localView);
            }
          });
        }
        return;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ContinuousResultItemView
 * JD-Core Version:    0.6.2
 */