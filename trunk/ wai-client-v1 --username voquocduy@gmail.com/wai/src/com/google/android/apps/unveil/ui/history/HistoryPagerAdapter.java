package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.AsyncItemListAdapter;
import com.google.android.apps.unveil.history.ItemListAdapter;
import com.google.android.apps.unveil.history.ItemProvider;
import com.google.android.apps.unveil.history.ItemProvider.AsyncItemProvider;
import com.google.android.apps.unveil.ui.AbstractListPagerAdapter;
import com.google.android.apps.unveil.ui.ViewPagerHeader.TitleProvider;
import java.util.List;

public class HistoryPagerAdapter extends AbstractListPagerAdapter<ItemProvider>
  implements ViewPagerHeader.TitleProvider
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final UnveilContext application;
  private final Context context;
  private final HistoryItemListFactory itemListFactory = new HistoryItemListFactory();

  public HistoryPagerAdapter(Context paramContext, UnveilContext paramUnveilContext, List<? extends ItemProvider> paramList)
  {
    super(paramList);
    this.context = paramContext;
    this.application = paramUnveilContext;
  }

  protected View createPage(ItemProvider paramItemProvider)
  {
    ListView localListView = this.itemListFactory.makeList(this.context, this.application);
    Object localObject;
    if ((paramItemProvider instanceof ItemProvider.AsyncItemProvider))
    {
      ItemProvider.AsyncItemProvider localAsyncItemProvider = (ItemProvider.AsyncItemProvider)paramItemProvider;
      localObject = new AsyncItemListAdapter(this.application, this.context, localAsyncItemProvider);
      localAsyncItemProvider.addCallback((AsyncItemListAdapter)localObject);
      localAsyncItemProvider.startLoading();
    }
    while (true)
    {
      localListView.setOnScrollListener((AbsListView.OnScrollListener)localObject);
      localListView.setAdapter((ListAdapter)localObject);
      return localListView;
      localObject = new ItemListAdapter(this.application, this.context, paramItemProvider);
    }
  }

  public String getTitleOf(Resources paramResources, int paramInt)
  {
    return ((ItemProvider)getItemAt(paramInt)).getTitle(paramResources);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.HistoryPagerAdapter
 * JD-Core Version:    0.6.2
 */