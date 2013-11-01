package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.results.BasicAnnotation;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.LinkedList;
import java.util.List;

public class ContinuousFullResultView extends ListView
{
  private final AnnotationAdapter adapter = new AnnotationAdapter();
  private final List<BasicAnnotation> annotations = new LinkedList();
  private final Handler handler;
  private ResultClickListener listener;
  private final ResultItemViewFactory viewFactory;

  public ContinuousFullResultView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setAdapter(this.adapter);
    this.handler = new Handler();
    this.viewFactory = new ResultItemViewFactory(paramContext);
  }

  private void notifyAdapter()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        ContinuousFullResultView.this.adapter.notifyDataSetChanged();
      }
    });
  }

  private void onResultClicked(ResultItem paramResultItem, int paramInt)
  {
    ClickTracker.logResultClick(getContext(), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP, paramResultItem, paramInt);
    if (this.listener != null)
      this.listener.onResultClicked(paramResultItem);
  }

  public void addAnnotation(BasicAnnotation paramBasicAnnotation)
  {
    this.annotations.add(0, paramBasicAnnotation);
    notifyAdapter();
  }

  public void clearAnnotations()
  {
    this.annotations.clear();
    notifyAdapter();
  }

  public void setListener(ResultClickListener paramResultClickListener)
  {
    this.listener = paramResultClickListener;
  }

  public class AnnotationAdapter extends BaseAdapter
  {
    public AnnotationAdapter()
    {
    }

    public int getCount()
    {
      if (ContinuousFullResultView.this.annotations == null)
        return 0;
      return ContinuousFullResultView.this.annotations.size();
    }

    public Object getItem(int paramInt)
    {
      return Integer.valueOf(paramInt);
    }

    public long getItemId(int paramInt)
    {
      return paramInt;
    }

    public View getView(final int paramInt, View paramView, ViewGroup paramViewGroup)
    {
      final BasicAnnotation localBasicAnnotation = (BasicAnnotation)ContinuousFullResultView.this.annotations.get(paramInt);
      if (paramView == null)
        paramView = ContinuousFullResultView.this.viewFactory.createViewForContinuousHistory(localBasicAnnotation.getResult());
      while (true)
      {
        paramView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ContinuousFullResultView.this.onResultClicked(localBasicAnnotation.getResult(), paramInt);
          }
        });
        return paramView;
        ContinuousFullResultView.this.viewFactory.bindViewForContinuousHistory(paramView, localBasicAnnotation.getResult());
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ContinuousFullResultView
 * JD-Core Version:    0.6.2
 */