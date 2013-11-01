package com.google.android.apps.unveil.restricts;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class TextRestrictsAdapter extends BaseAdapter
{
  RestrictType suggestionType;
  List<?> suggestionsList;

  public TextRestrictsAdapter(RestrictType paramRestrictType, List<?> paramList)
  {
    this.suggestionType = paramRestrictType;
    this.suggestionsList = new ArrayList(paramList);
  }

  public int getCount()
  {
    return this.suggestionsList.size();
  }

  public Object getItem(int paramInt)
  {
    return this.suggestionsList.get(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    TextView localTextView = (TextView)LayoutInflater.from(paramViewGroup.getContext()).inflate(17367043, null);
    localTextView.setTextColor(-16777216);
    localTextView.setText(QueryRestricts.getDisplayableString(this.suggestionsList.get(paramInt), localTextView.getContext()));
    return localTextView;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.TextRestrictsAdapter
 * JD-Core Version:    0.6.2
 */