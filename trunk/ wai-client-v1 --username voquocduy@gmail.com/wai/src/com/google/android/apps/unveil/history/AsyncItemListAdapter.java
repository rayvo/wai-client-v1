package com.google.android.apps.unveil.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.ui.SearchHistoryListFooterView;
import com.google.android.apps.unveil.ui.SearchHistoryListFooterView.OnClickListener;

public class AsyncItemListAdapter extends ItemListAdapter
  implements AbsListView.OnScrollListener, ItemProvider.AsyncItemProvider.Callback
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final SearchHistoryListFooterView footerView;
  private final ItemProvider.AsyncItemProvider itemProvider;

  public AsyncItemListAdapter(UnveilContext paramUnveilContext, Context paramContext, ItemProvider.AsyncItemProvider paramAsyncItemProvider)
  {
    super(paramUnveilContext, paramContext, paramAsyncItemProvider);
    this.itemProvider = paramAsyncItemProvider;
    this.footerView = ((SearchHistoryListFooterView)((LayoutInflater)paramContext.getSystemService("layout_inflater")).inflate(R.layout.search_history_list_footer, null));
    this.footerView.setOnClickListener(makeFooterViewClickListener());
  }

  private SearchHistoryListFooterView.OnClickListener makeFooterViewClickListener()
  {
    return new SearchHistoryListFooterView.OnClickListener()
    {
      public void onClick()
      {
        AsyncItemListAdapter.this.footerView.bind(0);
        AsyncItemListAdapter.this.footerView.invalidate();
        AsyncItemListAdapter.this.itemProvider.loadMore();
      }
    };
  }

  private boolean shouldShowFooterView()
  {
    return this.itemProvider.hasMore();
  }

  public int getCount()
  {
    int i = 0 + this.itemProvider.getCount();
    if (shouldShowFooterView())
      i++;
    return i;
  }

  public int getItemViewType(int paramInt)
  {
    if (paramInt < this.itemProvider.getCount())
      return super.getItemViewType(paramInt);
    return 1;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramInt == this.itemProvider.getCount())
      return this.footerView;
    return super.getView(paramInt, paramView, paramViewGroup);
  }

  public void onError()
  {
    this.footerView.bind(1);
  }

  public void onQuerying()
  {
    this.footerView.bind(0);
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((paramInt1 + paramInt2 == paramInt3) && (this.itemProvider.hasMore()))
    {
      this.footerView.bind(0);
      this.itemProvider.loadMore();
    }
    super.onScroll(paramAbsListView, paramInt1, paramInt2, paramInt3);
  }

  public void onSuccess()
  {
    notifyDataSetChanged();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.AsyncItemListAdapter
 * JD-Core Version:    0.6.2
 */