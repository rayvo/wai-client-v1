package com.google.android.apps.unveil.history;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.ui.history.ItemModels;

public class ItemListAdapter extends BaseAdapter
  implements AbsListView.OnScrollListener
{
  protected static final int FOOTER_TYPE = 1;
  private static final int NO_RESULTS_TYPE = 4;
  private static final int SAVED_QUERY_TYPE = 2;
  private static final int SEARCH_HISTORY_ITEM_TYPE = 0;
  private static final int SFC_TYPE = 5;
  private static final int UPSELL_TYPE = 3;
  private static final int VIEW_TYPE_COUNT = 6;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Context context;
  private final ItemProvider itemProvider;
  private final ThumbnailProvider thumbnailCache;

  public ItemListAdapter(UnveilContext paramUnveilContext, Context paramContext, ItemProvider paramItemProvider)
  {
    this.context = paramContext;
    this.itemProvider = paramItemProvider;
    this.thumbnailCache = paramUnveilContext.getThumbnailCache();
  }

  private void updateThumbnail(View paramView)
  {
    ItemModel localItemModel = (ItemModel)paramView.getTag();
    if (localItemModel == null)
      return;
    ItemModels.updateThumbnail(localItemModel, (ImageView)paramView.findViewById(R.id.history_thumbnail), this.thumbnailCache);
  }

  public int getCount()
  {
    return this.itemProvider.getCount();
  }

  public Object getItem(int paramInt)
  {
    if (paramInt == this.itemProvider.getCount())
      return null;
    return this.itemProvider.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public int getItemViewType(int paramInt)
  {
    ItemModel localItemModel = this.itemProvider.get(paramInt);
    if ((localItemModel instanceof SearchHistoryItem))
      return 0;
    if ((localItemModel instanceof SavedQuery))
      return 2;
    if ((localItemModel instanceof SearchHistoryUpsell))
      return 3;
    if ((localItemModel instanceof NoResultsItem))
      return 4;
    if ((localItemModel instanceof SfCItem))
      return 5;
    throw new IllegalStateException("Item at position " + paramInt + " is of unexpected type.");
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView == null)
      paramView = ((LayoutInflater)this.context.getSystemService("layout_inflater")).inflate(R.layout.history_item, null);
    ItemModel localItemModel = this.itemProvider.get(paramInt);
    ItemModels.presentAsListItem(localItemModel, paramView);
    paramView.setTag(localItemModel);
    updateThumbnail(paramView);
    return paramView;
  }

  public int getViewTypeCount()
  {
    return 6;
  }

  public void onScroll(AbsListView paramAbsListView, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onScrollStateChanged(AbsListView paramAbsListView, int paramInt)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.ItemListAdapter
 * JD-Core Version:    0.6.2
 */