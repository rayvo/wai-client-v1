package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.List;

public class ThumbnailAdapter extends BaseAdapter
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Thumbnail[] cells;
  private final Context context;
  private final ThumbnailClickListener listener;
  private final ThumbnailProvider thumbnailCache;

  public ThumbnailAdapter(Context paramContext, UnveilContext paramUnveilContext, ThumbnailClickListener paramThumbnailClickListener)
  {
    this.context = paramContext;
    this.listener = paramThumbnailClickListener;
    this.thumbnailCache = paramUnveilContext.getThumbnailCache();
  }

  public int getCount()
  {
    if (this.cells == null)
      return 0;
    return this.cells.length;
  }

  public Object getItem(int paramInt)
  {
    if (this.cells == null)
      return null;
    return this.cells[paramInt];
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if ((this.cells == null) || (paramInt >= this.cells.length))
      return null;
    return this.cells[paramInt];
  }

  public void loadImages(List<? extends ResultModel> paramList)
  {
    this.cells = new Thumbnail[paramList.size()];
    for (int i = 0; i < this.cells.length; i++)
      this.cells[i] = new Thumbnail(this.context, (ResultModel)paramList.get(i));
    for (int j = -1 + paramList.size(); j > -1; j--)
    {
      final Thumbnail localThumbnail = this.cells[j];
      final int k = j;
      if (this.listener != null)
        localThumbnail.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ThumbnailAdapter.this.listener.onThumbnailClick(localThumbnail, k);
          }
        });
      String str = ((ResultModel)paramList.get(j)).getThumbnailUrl();
      this.thumbnailCache.fetch(str, localThumbnail.getListener(), ThumbnailProvider.SizeSpec.ORIGINAL);
    }
  }

  public static abstract interface Factory
  {
    public abstract ThumbnailAdapter get();
  }

  public static abstract interface ThumbnailClickListener
  {
    public abstract void onThumbnailClick(Thumbnail paramThumbnail, int paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ThumbnailAdapter
 * JD-Core Version:    0.6.2
 */