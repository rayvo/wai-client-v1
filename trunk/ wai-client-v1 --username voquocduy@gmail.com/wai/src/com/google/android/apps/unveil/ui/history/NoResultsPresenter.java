package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.unveil.history.NoResultsItem;

class NoResultsPresenter extends ItemModels.Presenter<NoResultsItem>
{
  void presentAsListItem(NoResultsItem paramNoResultsItem, View paramView)
  {
    LinearLayout localLinearLayout = (LinearLayout)paramView;
    localLinearLayout.removeAllViews();
    localLinearLayout.setGravity(1);
    TextView localTextView = new TextView(localLinearLayout.getContext());
    localTextView.setText(paramNoResultsItem.getMessageId());
    int i = (int)(13.0F * paramView.getContext().getResources().getDisplayMetrics().density);
    localTextView.setPadding(i, i, i, i);
    localTextView.setTextColor(-3355444);
    localTextView.setTextSize(2, 20.0F);
    localTextView.setTypeface(Typeface.DEFAULT_BOLD);
    localLinearLayout.setFocusable(false);
    localLinearLayout.setClickable(false);
    localLinearLayout.addView(localTextView);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.NoResultsPresenter
 * JD-Core Version:    0.6.2
 */