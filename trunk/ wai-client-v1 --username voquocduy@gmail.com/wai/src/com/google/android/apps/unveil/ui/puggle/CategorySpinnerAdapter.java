package com.google.android.apps.unveil.ui.puggle;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.goggles.RestrictsProtos.Category;
import java.util.List;

public class CategorySpinnerAdapter extends BaseAdapter
{
  List<RestrictsProtos.Category> categories;

  public CategorySpinnerAdapter(List<RestrictsProtos.Category> paramList)
  {
    this.categories = paramList;
  }

  public int getCount()
  {
    return this.categories.size();
  }

  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null);
    for (View localView = paramView; ; localView = View.inflate(paramViewGroup.getContext(), 17367043, null))
    {
      TextView localTextView = (TextView)localView;
      localTextView.setText(((RestrictsProtos.Category)this.categories.get(paramInt)).getDisplayName());
      return localTextView;
    }
  }

  public Object getItem(int paramInt)
  {
    return this.categories.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (paramView != null);
    for (View localView = paramView; ; localView = View.inflate(paramViewGroup.getContext(), 17367048, null))
    {
      TextView localTextView = (TextView)localView;
      localTextView.setText(((RestrictsProtos.Category)this.categories.get(paramInt)).getDisplayName());
      return localTextView;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.CategorySpinnerAdapter
 * JD-Core Version:    0.6.2
 */