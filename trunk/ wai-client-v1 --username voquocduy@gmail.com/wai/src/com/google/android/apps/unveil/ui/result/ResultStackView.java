package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.ArrayList;
import java.util.List;

public class ResultStackView extends LinearLayout
  implements MultipleResultView<ResultModel>
{
  private static final int[] RESULT_BAR_COLORS = arrayOfInt;
  private final ResultItemViewFactory factory;
  private final String imageUrl;
  private final UnveilLogger logger = new UnveilLogger();
  private final List<ResultModel> results = new ArrayList();
  private MultipleResultView.ResultTouchListener<ResultModel> touchListener;

  static
  {
    int[] arrayOfInt = new int[3];
    arrayOfInt[0] = R.drawable.result_bar_green;
    arrayOfInt[1] = R.drawable.result_bar_blue;
    arrayOfInt[2] = R.drawable.result_bar_red;
  }

  public ResultStackView(Context paramContext, ResultItemViewFactory paramResultItemViewFactory, String paramString)
  {
    super(paramContext);
    this.factory = paramResultItemViewFactory;
    this.imageUrl = paramString;
  }

  private View createContributedResultItemView(ContributedResultItem paramContributedResultItem)
  {
    View localView = this.factory.createContributedView(paramContributedResultItem, this.imageUrl);
    localView.setTag(paramContributedResultItem);
    return localView;
  }

  private View createResultListItemView(ResultItem paramResultItem, int paramInt)
  {
    View localView = this.factory.createViewForOneShot(paramResultItem, RESULT_BAR_COLORS[(paramInt % RESULT_BAR_COLORS.length)]);
    localView.setTag(paramResultItem);
    return localView;
  }

  private View getResultItemView(int paramInt)
  {
    return getChildAt(paramInt);
  }

  private static boolean isPermutation(List<ResultItem> paramList1, List<ResultItem> paramList2)
  {
    if (paramList1.size() != paramList2.size())
      return false;
    ArrayList localArrayList = new ArrayList(paramList2);
    for (int i = 0; i < paramList1.size(); i++)
      for (int j = 0; ; j++)
      {
        int k = localArrayList.size();
        int m = 0;
        if (j < k)
        {
          if (((ResultItem)paramList1.get(i)).equals(localArrayList.get(j)))
          {
            localArrayList.remove(j);
            m = 1;
          }
        }
        else
        {
          if (m != 0)
            break;
          return false;
        }
      }
    return true;
  }

  private void updateDisplay(List<ResultModel> paramList)
  {
    int i = 0;
    int j = 0;
    if (j < paramList.size())
    {
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = ((ResultModel)paramList.get(j)).getTitle();
      localUnveilLogger.i("Add %s", arrayOfObject);
      if ((paramList.get(j) instanceof ResultItem))
      {
        addView(createResultListItemView((ResultItem)paramList.get(j), i), j);
        i++;
      }
      while (true)
      {
        j++;
        break;
        addView(createContributedResultItemView((ContributedResultItem)paramList.get(j)));
      }
    }
    invalidate();
  }

  public void clear()
  {
    post(new Runnable()
    {
      public void run()
      {
        ResultStackView.this.results.clear();
        ResultStackView.this.requestLayout();
      }
    });
  }

  public void clearHighlights()
  {
    for (int i = 0; i < numResults(); i++)
    {
      View localView = getResultItemView(i);
      if (localView != null)
        this.factory.setHighlighted(localView, false);
    }
    invalidate();
  }

  public ResultModel getResult(int paramInt)
  {
    return (ResultModel)this.results.get(paramInt);
  }

  public void highlightResult(int paramInt)
  {
    View localView = getResultItemView(paramInt);
    if (localView == null)
      return;
    this.factory.setHighlighted(localView, true);
    invalidate();
  }

  public boolean isDisplaying(ResultModel paramResultModel)
  {
    return this.results.contains(paramResultModel);
  }

  public int numResults()
  {
    return this.results.size();
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.touchListener != null)
    {
      switch (paramMotionEvent.getAction())
      {
      default:
        ResultModel localResultModel = resultItemAtPoint((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
        this.touchListener.onResultTouch(this, paramMotionEvent, localResultModel);
        return true;
      case 3:
      }
      this.touchListener.onResultTouch(this, paramMotionEvent, null);
    }
    return super.onTouchEvent(paramMotionEvent);
  }

  public void removeResult(ResultModel paramResultModel)
  {
    this.results.remove(paramResultModel);
  }

  public ResultModel resultItemAtPoint(int paramInt1, int paramInt2)
  {
    for (int i = 0; i < getChildCount(); i++)
    {
      View localView = getChildAt(i);
      if ((localView.getTag() != null) && (paramInt2 >= localView.getTop()) && (paramInt2 <= localView.getBottom()))
        return (ResultModel)localView.getTag();
    }
    return null;
  }

  public void setResultTouchListener(MultipleResultView.ResultTouchListener<ResultModel> paramResultTouchListener)
  {
    this.touchListener = paramResultTouchListener;
  }

  public void setResults(List<ResultModel> paramList)
  {
    if (this.results.equals(paramList))
      return;
    this.results.clear();
    this.results.addAll(paramList);
    updateDisplay(this.results);
  }

  public void updateColors(List<ResultModel> paramList)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ResultStackView
 * JD-Core Version:    0.6.2
 */