package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.history.SavedQuery;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

class SavedQueryPresenter extends ItemModels.Presenter<SavedQuery>
{
  private static final View.OnClickListener CLICK_LISTENER = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SavedQueryPresenter.sendSavedQuery(paramAnonymousView);
    }
  };
  private static final int CONTEXT_MENU_DELETE = 2;
  private static final int CONTEXT_MENU_RUN_QUERY = 1;

  private static final View.OnCreateContextMenuListener newContextMenuListener(SavedQuery paramSavedQuery)
  {
    return new View.OnCreateContextMenuListener()
    {
      public void onCreateContextMenu(ContextMenu paramAnonymousContextMenu, final View paramAnonymousView, ContextMenu.ContextMenuInfo paramAnonymousContextMenuInfo)
      {
        final UnveilApplication localUnveilApplication = (UnveilApplication)paramAnonymousView.getContext().getApplicationContext();
        paramAnonymousContextMenu.setHeaderTitle(R.string.pending_query);
        paramAnonymousContextMenu.add(0, 1, 1, R.string.run_query).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
          {
            SavedQueryPresenter.sendSavedQuery(paramAnonymousView);
            return true;
          }
        });
        paramAnonymousContextMenu.add(0, 2, 2, R.string.delete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
          {
            localUnveilApplication.getSavedQueryProvider().delete(SavedQueryPresenter.2.this.val$savedQuery.getId());
            return true;
          }
        });
      }
    };
  }

  public static void sendSavedQuery(View paramView)
  {
    Context localContext = paramView.getContext();
    BaseApplication localBaseApplication = (BaseApplication)localContext.getApplicationContext();
    localBaseApplication.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.EXECUTE_SAVED_QUERY);
    localContext.startActivity(ItemModels.toIntent((SavedQuery)paramView.getTag(), localContext, localBaseApplication));
  }

  public void presentAsListItem(SavedQuery paramSavedQuery, View paramView)
  {
    paramView.setTag(paramSavedQuery);
    paramView.setBackgroundResource(R.color.saved_query_background);
    ((TextView)paramView.findViewById(R.id.title)).setVisibility(8);
    ((TextView)paramView.findViewById(R.id.pending_query)).setVisibility(0);
    ((TextView)paramView.findViewById(R.id.relative_time)).setText(ItemModels.getRelativeTimeString(paramView.getContext(), paramSavedQuery.getTimestamp()));
    ((TextView)paramView.findViewById(R.id.location)).setVisibility(8);
    ((TextView)paramView.findViewById(R.id.notes)).setVisibility(8);
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.history_thumbnail);
    paramSavedQuery.getCachedQueryImage().populateWithBitmap(localImageView);
    paramView.setOnClickListener(CLICK_LISTENER);
    paramView.setOnCreateContextMenuListener(newContextMenuListener(paramSavedQuery));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.SavedQueryPresenter
 * JD-Core Version:    0.6.2
 */