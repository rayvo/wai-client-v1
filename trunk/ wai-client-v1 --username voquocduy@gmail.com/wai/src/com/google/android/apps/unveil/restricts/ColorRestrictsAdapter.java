package com.google.android.apps.unveil.restricts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.RestrictsProtos.ColorEnum.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ColorRestrictsAdapter extends BaseAdapter
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<ColorMap> colors;
  private final Context context;

  public ColorRestrictsAdapter(Context paramContext, List<RestrictsProtos.ColorEnum.Color> paramList)
  {
    this.context = paramContext;
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localHashSet.add(ColorMap.getColorMap((RestrictsProtos.ColorEnum.Color)localIterator.next()));
    this.colors = new ArrayList(localHashSet);
    Collections.sort(this.colors);
  }

  public int getCount()
  {
    return this.colors.size();
  }

  public RestrictsProtos.ColorEnum.Color getItem(int paramInt)
  {
    return RestrictsProtos.ColorEnum.Color.valueOf(((ColorMap)this.colors.get(paramInt)).toString());
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null);
    View localView;
    for (FrameLayout localFrameLayout = (FrameLayout)paramView; ; localFrameLayout = (FrameLayout)LayoutInflater.from(this.context).inflate(R.layout.color_box, null))
    {
      localView = localFrameLayout.findViewById(R.id.color_patch);
      if (this.colors.get(paramInt) != ColorMap.GOLD)
        break;
      localView.setBackgroundResource(R.drawable.gold);
      return localFrameLayout;
    }
    if (this.colors.get(paramInt) == ColorMap.SILVER)
    {
      localView.setBackgroundResource(R.drawable.silver);
      return localFrameLayout;
    }
    localView.setBackgroundColor(((ColorMap)this.colors.get(paramInt)).colorValue);
    return localFrameLayout;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.ColorRestrictsAdapter
 * JD-Core Version:    0.6.2
 */