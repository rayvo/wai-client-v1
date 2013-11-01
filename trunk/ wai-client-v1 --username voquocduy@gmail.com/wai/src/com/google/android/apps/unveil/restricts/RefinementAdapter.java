package com.google.android.apps.unveil.restricts;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import java.util.List;

public class RefinementAdapter extends BaseAdapter
{
  private final SuggestionsListener clickListener;
  private final QueryRestricts selectedRestricts;
  private final QueryRestricts suggestedRestricts;

  public RefinementAdapter(QueryRestricts paramQueryRestricts1, QueryRestricts paramQueryRestricts2, SuggestionsListener paramSuggestionsListener)
  {
    this.suggestedRestricts = paramQueryRestricts1;
    this.selectedRestricts = paramQueryRestricts2;
    this.clickListener = paramSuggestionsListener;
  }

  private String getCategoryLabel(Context paramContext)
  {
    if (this.selectedRestricts.getActiveRestrictTypes().contains(RestrictType.PRODUCT_CATEGORY));
    for (RestrictType localRestrictType = RestrictType.PRODUCT_CATEGORY; ; localRestrictType = RestrictType.PRETTY_NAME_CATEGORY)
    {
      int i = R.string.restricts_type_customized_category;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = QueryRestricts.getDisplayableString(this.selectedRestricts.getValues(localRestrictType).get(0), paramContext);
      return paramContext.getString(i, arrayOfObject);
    }
  }

  private RelativeLayout getColorView(View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) || (!(paramView instanceof RelativeLayout)) || (paramView.findViewById(R.id.color_patch_frame) == null));
    for (RelativeLayout localRelativeLayout = (RelativeLayout)LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.suggestion_list_type_color, null); ; localRelativeLayout = (RelativeLayout)paramView)
    {
      ((TextView)localRelativeLayout.findViewById(R.id.suggestion_type)).setText(RestrictType.COLOR.toString());
      View localView = localRelativeLayout.findViewById(R.id.color_patch_frame);
      ColorMap localColorMap = ColorMap.valueOf(this.selectedRestricts.getValues(RestrictType.COLOR).get(0).toString());
      localRelativeLayout.findViewById(R.id.color_patch).setBackgroundColor(localColorMap.colorValue);
      localView.setVisibility(0);
      return localRelativeLayout;
    }
  }

  private RestrictType getItemRestrictType(int paramInt)
  {
    if (isSelectedSuggestion(paramInt))
      return (RestrictType)this.selectedRestricts.getActiveRestrictTypes().get(paramInt);
    return (RestrictType)this.suggestedRestricts.getActiveRestrictTypes().get(paramInt - this.selectedRestricts.getActiveRestrictTypes().size());
  }

  private View getSelectedSuggestionView(final RestrictType paramRestrictType, View paramView, ViewGroup paramViewGroup)
  {
    if (paramRestrictType == RestrictType.COLOR);
    for (RelativeLayout localRelativeLayout = getColorView(paramView, paramViewGroup); ; localRelativeLayout = getTextView(paramRestrictType, paramView, paramViewGroup))
    {
      ((TextView)localRelativeLayout.findViewById(R.id.suggestion_type)).setText(paramRestrictType.getName(localRelativeLayout.getContext()));
      ((CheckBox)localRelativeLayout.findViewById(R.id.checked)).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
      {
        public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
        {
          RefinementAdapter.this.selectedRestricts.remove(paramRestrictType);
          RefinementAdapter.this.clickListener.onRestrictClick(RefinementAdapter.this.selectedRestricts.buildRestricts());
        }
      });
      return localRelativeLayout;
    }
  }

  private View getSuggestionTypeView(RestrictType paramRestrictType, View paramView, ViewGroup paramViewGroup)
  {
    TextView localTextView;
    if ((paramView == null) || (!(paramView instanceof TextView)))
    {
      localTextView = (TextView)LayoutInflater.from(paramViewGroup.getContext()).inflate(17367043, null);
      localTextView.setTextColor(-16777216);
    }
    while (paramRestrictType == RestrictType.PRODUCT_CATEGORY)
    {
      localTextView.setText(getCategoryLabel(localTextView.getContext()));
      return localTextView;
      localTextView = (TextView)paramView;
    }
    localTextView.setText(paramRestrictType.getName(localTextView.getContext()));
    return localTextView;
  }

  private RelativeLayout getTextView(RestrictType paramRestrictType, View paramView, ViewGroup paramViewGroup)
  {
    if ((paramView == null) || (!(paramView instanceof RelativeLayout)) || (paramView.findViewById(R.id.selected_suggestion) == null));
    for (RelativeLayout localRelativeLayout = (RelativeLayout)LayoutInflater.from(paramViewGroup.getContext()).inflate(R.layout.suggestion_list_type_item, null); ; localRelativeLayout = (RelativeLayout)paramView)
    {
      ((TextView)localRelativeLayout.findViewById(R.id.selected_suggestion)).setText(QueryRestricts.getDisplayableString(this.selectedRestricts.getValues(paramRestrictType).get(0), localRelativeLayout.getContext()));
      return localRelativeLayout;
    }
  }

  public int getCount()
  {
    return this.selectedRestricts.getActiveRestrictTypes().size() + this.suggestedRestricts.getActiveRestrictTypes().size();
  }

  public RestrictType getItem(int paramInt)
  {
    return getItemRestrictType(paramInt);
  }

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    if (isSelectedSuggestion(paramInt))
      return getSelectedSuggestionView(getItemRestrictType(paramInt), paramView, paramViewGroup);
    return getSuggestionTypeView(getItemRestrictType(paramInt), paramView, paramViewGroup);
  }

  public boolean isSelectedSuggestion(int paramInt)
  {
    return paramInt < this.selectedRestricts.getActiveRestrictTypes().size();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.RefinementAdapter
 * JD-Core Version:    0.6.2
 */