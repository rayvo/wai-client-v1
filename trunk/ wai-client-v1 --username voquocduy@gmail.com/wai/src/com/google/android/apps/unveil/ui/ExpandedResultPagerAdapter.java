package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.view.View;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.List;

public class ExpandedResultPagerAdapter extends AbstractListPagerAdapter<ResultModel>
{
  private final Context context;
  private final ExpandedResultView.MomentInteractionListener listener;

  public ExpandedResultPagerAdapter(Context paramContext, List<ResultModel> paramList, ExpandedResultView.MomentInteractionListener paramMomentInteractionListener)
  {
    super(paramList);
    this.context = paramContext;
    this.listener = paramMomentInteractionListener;
  }

  protected View createPage(ResultModel paramResultModel)
  {
    ExpandedResultView localExpandedResultView = new ExpandedResultView(this.context, null);
    localExpandedResultView.setMomentInteractionListener(this.listener);
    localExpandedResultView.setResultModel(paramResultModel);
    localExpandedResultView.loadWebPage();
    localExpandedResultView.setVisibility(0);
    return localExpandedResultView;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ExpandedResultPagerAdapter
 * JD-Core Version:    0.6.2
 */