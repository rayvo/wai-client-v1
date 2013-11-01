package com.google.android.apps.unveil.ui.result;

import android.view.MotionEvent;
import java.util.List;

public abstract interface MultipleResultView<ResultType>
{
  public abstract void clear();

  public abstract void clearHighlights();

  public abstract ResultType getResult(int paramInt);

  public abstract void highlightResult(int paramInt);

  public abstract boolean isDisplaying(ResultType paramResultType);

  public abstract int numResults();

  public abstract void removeResult(ResultType paramResultType);

  public abstract ResultType resultItemAtPoint(int paramInt1, int paramInt2);

  public abstract void setResultTouchListener(ResultTouchListener<ResultType> paramResultTouchListener);

  public abstract void setResults(List<ResultType> paramList);

  public abstract void updateColors(List<ResultType> paramList);

  public static abstract interface ResultTouchListener<ResultType>
  {
    public abstract boolean onResultTouch(MultipleResultView<ResultType> paramMultipleResultView, MotionEvent paramMotionEvent, ResultType paramResultType);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.MultipleResultView
 * JD-Core Version:    0.6.2
 */