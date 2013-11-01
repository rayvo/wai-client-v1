package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.util.FloatMath;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.puggle.PuggleResultItems.PriceComparator;
import com.google.android.apps.unveil.results.PuggleResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PuggleResultAdapter extends BaseAdapter
{
  public static final ThumbnailProvider.SizeSpec PUGGLE_THUMB_SIZE_SPEC = ThumbnailProvider.SizeSpec.makeCustomFifeSpec(100, 100);
  private static final UnveilLogger logger = new UnveilLogger();
  private int colWidth;
  private int horizontalSpacing;
  private final PuggleResultClickListener listener;
  private int numCols;
  private List<PuggleResultItem> results;
  private List<PuggleResultItem> resultsByRelevance;
  private final ThumbnailProvider thumbnailCache;
  private ViewType viewType;

  public PuggleResultAdapter(List<PuggleResultItem> paramList, ThumbnailProvider paramThumbnailProvider, PuggleResultClickListener paramPuggleResultClickListener)
  {
    if (paramList == null);
    for (this.results = new ArrayList(); ; this.results = new ArrayList(paramList))
    {
      this.resultsByRelevance = new ArrayList(this.results);
      this.thumbnailCache = paramThumbnailProvider;
      this.listener = paramPuggleResultClickListener;
      this.viewType = ViewType.LIST;
      return;
    }
  }

  private PuggleResultLayout getCellView(PuggleResultItem paramPuggleResultItem, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView != null) && ((paramView instanceof PuggleCellResult)))
      return (PuggleCellResult)paramView;
    PuggleCellResult localPuggleCellResult = new PuggleCellResult(paramViewGroup.getContext());
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(this.colWidth, -2);
    localLayoutParams.setMargins(0, 0, this.horizontalSpacing, 0);
    localPuggleCellResult.setLayoutParams(localLayoutParams);
    return localPuggleCellResult;
  }

  private PuggleResultLayout getRowView(PuggleResultItem paramPuggleResultItem, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView != null) && ((paramView instanceof PuggleRowResult)));
    for (PuggleRowResult localPuggleRowResult = (PuggleRowResult)paramView; ; localPuggleRowResult = new PuggleRowResult(paramViewGroup.getContext()))
    {
      localPuggleRowResult.setTitle(paramPuggleResultItem.getTitle());
      localPuggleRowResult.setReviews(paramPuggleResultItem.getAnnotationResult().getProductInfo());
      return localPuggleRowResult;
    }
  }

  public int getCount()
  {
    if (this.viewType == ViewType.LIST)
      return this.results.size();
    return (int)FloatMath.ceil(this.results.size() / this.numCols);
  }

  public Object getItem(int paramInt)
  {
    return this.results.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getResultView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    PuggleResultItem localPuggleResultItem = (PuggleResultItem)this.results.get(paramInt);
    if (this.viewType == ViewType.GRID);
    for (PuggleResultLayout localPuggleResultLayout = getCellView(localPuggleResultItem, paramView, paramViewGroup); ; localPuggleResultLayout = getRowView(localPuggleResultItem, paramView, paramViewGroup))
    {
      localPuggleResultLayout.setOnClickListener(new PuggleOnClickListener(this.listener, paramInt));
      localPuggleResultLayout.setOnLongClickListener(new PuggleOnLongClickListener(this.listener, localPuggleResultItem));
      localPuggleResultLayout.setPrice(localPuggleResultItem.getPrice());
      localPuggleResultLayout.setBrand(localPuggleResultItem.getBrand());
      setupImageView(localPuggleResultLayout.imageView, localPuggleResultItem.getThumbnailUrl());
      localPuggleResultLayout.setVisibility(0);
      return localPuggleResultLayout;
    }
  }

  public ArrayList<PuggleResultItem> getResults()
  {
    return new ArrayList(this.results);
  }

  public View getRowOfResultsView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    LinearLayout localLinearLayout;
    if ((paramView instanceof LinearLayout))
      localLinearLayout = (LinearLayout)paramView;
    for (int i = 0; ; i++)
    {
      int j;
      if (i < this.numCols)
      {
        j = i + paramInt * this.numCols;
        if (j >= this.results.size())
          localLinearLayout.removeViews(i, localLinearLayout.getChildCount() - i);
      }
      else
      {
        return localLinearLayout;
        localLinearLayout = new LinearLayout(paramViewGroup.getContext());
        localLinearLayout.setPadding(this.horizontalSpacing, 0, 0, 0);
        localLinearLayout.setBackgroundColor(-1);
        break;
      }
      View localView = getResultView(j, localLinearLayout.getChildAt(i), paramViewGroup);
      if (i >= localLinearLayout.getChildCount())
        localLinearLayout.addView(localView);
    }
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramInt >= getCount())
      return null;
    if (this.viewType == ViewType.GRID)
      return getRowOfResultsView(paramInt, paramView, paramViewGroup);
    return getResultView(paramInt, paramView, paramViewGroup);
  }

  public void setColWidth(int paramInt)
  {
    this.colWidth = paramInt;
    notifyDataSetChanged();
  }

  public void setHorizontalSpacing(int paramInt)
  {
    this.horizontalSpacing = paramInt;
    notifyDataSetChanged();
  }

  public void setNumCols(int paramInt)
  {
    this.numCols = paramInt;
    notifyDataSetChanged();
  }

  public void setResults(List<PuggleResultItem> paramList)
  {
    if (paramList == null);
    for (this.results = new ArrayList(); ; this.results = new ArrayList(paramList))
    {
      this.resultsByRelevance = new ArrayList(this.results);
      notifyDataSetChanged();
      return;
    }
  }

  public void setViewType(ViewType paramViewType)
  {
    this.viewType = paramViewType;
    notifyDataSetChanged();
  }

  public void setupImageView(ImageView paramImageView, String paramString)
  {
    if (paramString == null)
    {
      paramImageView.setImageResource(R.drawable.no_image_found);
      return;
    }
    paramImageView.setImageBitmap(null);
    paramImageView.setTag(paramString);
    this.thumbnailCache.fetch(paramString, ThumbnailProvider.makeImageViewThumbnailListener(paramImageView, paramString), PUGGLE_THUMB_SIZE_SPEC);
  }

  public void sortByPrice(boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList(this.results);
    Collections.sort(localArrayList, new PuggleResultItems.PriceComparator(paramBoolean));
    this.results.clear();
    this.results.addAll(localArrayList);
    notifyDataSetChanged();
  }

  public void sortByRelevance()
  {
    this.results.clear();
    this.results.addAll(this.resultsByRelevance);
    notifyDataSetChanged();
  }

  private static class PuggleOnClickListener
    implements View.OnClickListener
  {
    private final int index;
    private final PuggleResultAdapter.PuggleResultClickListener listener;

    public PuggleOnClickListener(PuggleResultAdapter.PuggleResultClickListener paramPuggleResultClickListener, int paramInt)
    {
      this.index = paramInt;
      this.listener = paramPuggleResultClickListener;
    }

    public void onClick(View paramView)
    {
      this.listener.onPuggleResultClick(this.index);
    }
  }

  public class PuggleOnLongClickListener
    implements View.OnLongClickListener
  {
    private final PuggleResultAdapter.PuggleResultClickListener clickListener;
    private final ResultItem result;
    private final String thumbnailUrl;

    public PuggleOnLongClickListener(PuggleResultAdapter.PuggleResultClickListener paramResultItem, ResultItem arg3)
    {
      Object localObject;
      this.thumbnailUrl = localObject.getThumbnailUrl();
      this.clickListener = paramResultItem;
      this.result = localObject;
    }

    public boolean onLongClick(View paramView)
    {
      PuggleResultAdapter.this.thumbnailCache.fetch(this.thumbnailUrl, new ThumbnailProvider.ThumbnailListener()
      {
        public void onThumbnailReady(Picture paramAnonymousPicture)
        {
          PuggleResultAdapter.PuggleOnLongClickListener.this.clickListener.onPuggleResultLongClick(paramAnonymousPicture, PuggleResultAdapter.PuggleOnLongClickListener.this.result);
        }
      }
      , PuggleResultAdapter.PUGGLE_THUMB_SIZE_SPEC);
      return true;
    }
  }

  public static abstract interface PuggleResultClickListener
  {
    public abstract boolean onPuggleResultClick(int paramInt);

    public abstract boolean onPuggleResultLongClick(Picture paramPicture, ResultItem paramResultItem);
  }

  public static enum ViewType
  {
    int index;
    int resourceId;

    static
    {
      ViewType[] arrayOfViewType = new ViewType[2];
      arrayOfViewType[0] = GRID;
      arrayOfViewType[1] = LIST;
    }

    private ViewType(int paramInt1, int paramInt2)
    {
      this.resourceId = paramInt1;
      this.index = paramInt2;
    }

    public static String[] displayStrings(Context paramContext)
    {
      String[] arrayOfString = new String[2];
      arrayOfString[0] = GRID.resource(paramContext);
      arrayOfString[1] = LIST.resource(paramContext);
      return arrayOfString;
    }

    public static ViewType get(int paramInt)
    {
      return values()[paramInt];
    }

    public String resource(Context paramContext)
    {
      return paramContext.getString(this.resourceId);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.PuggleResultAdapter
 * JD-Core Version:    0.6.2
 */