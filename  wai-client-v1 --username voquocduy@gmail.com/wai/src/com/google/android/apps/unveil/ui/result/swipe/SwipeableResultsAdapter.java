package com.google.android.apps.unveil.ui.result.swipe;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.PuggleResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.ArrayList;
import java.util.List;

public class SwipeableResultsAdapter extends BaseAdapter
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final int childWidth;
  private final List<ResultItem> results;
  private final ThumbnailProvider thumbnailCache;

  public SwipeableResultsAdapter(Context paramContext, List<ResultItem> paramList, int paramInt)
  {
    this.results = new ArrayList(paramList);
    this.childWidth = paramInt;
    this.thumbnailCache = ((UnveilApplication)paramContext.getApplicationContext()).getThumbnailCache();
  }

  public int getCount()
  {
    return this.results.size();
  }

  public Object getItem(int paramInt)
  {
    return this.results.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    ResultItem localResultItem = (ResultItem)this.results.get(paramInt);
    if (paramView != null);
    for (final SwipeableCell localSwipeableCell = (SwipeableCell)paramView; ; localSwipeableCell = new SwipeableCell(paramViewGroup.getContext(), this.childWidth))
    {
      String str = localResultItem.getThumbnailUrl();
      if (str != null)
        this.thumbnailCache.fetch(str, new ThumbnailProvider.ThumbnailListener()
        {
          public void onThumbnailReady(Picture paramAnonymousPicture)
          {
            localSwipeableCell.setImage(paramAnonymousPicture);
            SwipeableResultsAdapter.this.notifyDataSetChanged();
          }
        }
        , ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
      if ((localResultItem instanceof PuggleResultItem))
        localSwipeableCell.setName(((PuggleResultItem)localResultItem).getDisplayablePrettyNameString());
      return localSwipeableCell;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.swipe.SwipeableResultsAdapter
 * JD-Core Version:    0.6.2
 */