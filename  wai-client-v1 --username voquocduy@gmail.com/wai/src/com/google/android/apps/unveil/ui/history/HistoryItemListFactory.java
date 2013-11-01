package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import com.google.android.apps.unveil.UnveilContext;

public class HistoryItemListFactory
{
  public ListView makeList(Context paramContext, UnveilContext paramUnveilContext)
  {
    ListView localListView = new ListView(paramContext);
    localListView.setFocusable(true);
    localListView.setCacheColorHint(0);
    localListView.setFocusableInTouchMode(true);
    localListView.setSelector(17301602);
    localListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        paramAnonymousView.performClick();
      }
    });
    return localListView;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.HistoryItemListFactory
 * JD-Core Version:    0.6.2
 */