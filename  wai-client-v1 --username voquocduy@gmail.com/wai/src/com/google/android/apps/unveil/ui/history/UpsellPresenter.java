package com.google.android.apps.unveil.ui.history;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.SHFirstRunActivity;
import com.google.android.apps.unveil.history.SearchHistoryUpsell;

class UpsellPresenter extends ItemModels.Presenter<SearchHistoryUpsell>
{
  public void presentAsListItem(SearchHistoryUpsell paramSearchHistoryUpsell, View paramView)
  {
    final Activity localActivity = paramSearchHistoryUpsell.getActivity();
    LinearLayout localLinearLayout = (LinearLayout)paramView;
    localLinearLayout.removeAllViews();
    localLinearLayout.addView(LayoutInflater.from(localActivity).inflate(R.layout.search_history_upsell, null));
    View.OnClickListener local1 = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(localActivity, SHFirstRunActivity.class);
        localIntent.setAction("com.google.android.apps.unveil.moment_upsell");
        localActivity.startActivityForResult(localIntent, 1024);
      }
    };
    Button localButton = (Button)localLinearLayout.findViewById(R.id.upsell_button);
    localButton.setOnClickListener(local1);
    localLinearLayout.setOnClickListener(local1);
    localButton.setFocusable(false);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.UpsellPresenter
 * JD-Core Version:    0.6.2
 */