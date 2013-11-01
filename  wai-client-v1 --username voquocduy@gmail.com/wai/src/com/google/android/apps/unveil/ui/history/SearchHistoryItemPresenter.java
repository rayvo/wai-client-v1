package com.google.android.apps.unveil.ui.history;

import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnCreateContextMenuListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.NoteActivity;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.DeleteListener;
import com.google.android.apps.unveil.history.SearchHistoryProvider.UpdateListener;
import com.google.android.apps.unveil.ui.ShareDialog;
import com.google.android.apps.unveil.ui.WithdrawalDialogHelper;
import com.google.android.apps.unveil.ui.WithdrawalDialogHelper.WithdrawalDialogListener;

class SearchHistoryItemPresenter extends ItemModels.Presenter<SearchHistoryItem>
{
  private static final int CONTEXT_MENU_DELETE = 5;
  private static final int CONTEXT_MENU_NOTE = 3;
  private static final int CONTEXT_MENU_SHARE = 2;
  private static final int CONTEXT_MENU_VIEW = 1;
  private static final int CONTEXT_MENU_WITHDRAW = 4;
  private static final View.OnClickListener HISTORY_ITEM_CLICK_LISTENER = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SearchHistoryItemPresenter.replayHistoryItem(paramAnonymousView);
    }
  };

  private static void confirmAndMaybeDelete(View paramView)
  {
    final Context localContext = paramView.getContext();
    new AlertDialog.Builder(localContext).setIcon(17301543).setTitle(R.string.delete_query).setMessage(R.string.confirm_deletion).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        SearchHistoryItemPresenter.deleteMoment((SearchHistoryItem)this.val$v.getTag(), localContext);
      }
    }).setNegativeButton(R.string.no, null).show();
  }

  private static void confirmAndMaybeWithdrawHistoryItem(View paramView)
  {
    SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)paramView.getTag();
    Context localContext = paramView.getContext();
    BaseApplication localBaseApplication = (BaseApplication)localContext.getApplicationContext();
    WithdrawalDialogHelper.showWithdrawalDialog(localSearchHistoryItem.getMomentId(), localContext, localBaseApplication, new WithdrawalDialogHelper.WithdrawalDialogListener()
    {
      public void withdrawalSuccessful()
      {
      }
    });
  }

  private static void deleteMoment(SearchHistoryItem paramSearchHistoryItem, final Context paramContext)
  {
    UnveilContext localUnveilContext = (UnveilContext)paramContext.getApplicationContext();
    ProgressDialog localProgressDialog = ProgressDialog.show(paramContext, "", paramContext.getString(R.string.deleting), true, false);
    localUnveilContext.getSearchHistoryProvider().delete(paramSearchHistoryItem.getMomentId(), new SearchHistoryProvider.DeleteListener()
    {
      public void onError()
      {
        this.val$progressDialog.dismiss();
        Toast.makeText(paramContext, R.string.delete_failed, 1).show();
      }

      public void onResult()
      {
        this.val$progressDialog.dismiss();
      }
    });
  }

  private static final View.OnCreateContextMenuListener makeContextMenuListener(boolean paramBoolean)
  {
    return new View.OnCreateContextMenuListener()
    {
      public void onCreateContextMenu(ContextMenu paramAnonymousContextMenu, final View paramAnonymousView, ContextMenu.ContextMenuInfo paramAnonymousContextMenuInfo)
      {
        final SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)paramAnonymousView.getTag();
        UnveilContext localUnveilContext = (UnveilContext)paramAnonymousView.getContext().getApplicationContext();
        paramAnonymousContextMenu.setHeaderTitle(localSearchHistoryItem.getTitle());
        paramAnonymousContextMenu.add(0, 1, 1, R.string.view).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
          {
            SearchHistoryItemPresenter.replayHistoryItem(paramAnonymousView);
            return true;
          }
        });
        paramAnonymousContextMenu.add(0, 2, 2, R.string.share).setOnMenuItemClickListener(new SearchHistoryItemPresenter.ShareMenuItemClickListener(localUnveilContext.getSearchHistoryProvider(), paramAnonymousView, localSearchHistoryItem, null));
        paramAnonymousContextMenu.add(0, 3, 3, R.string.edit_note).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
          {
            Intent localIntent = new Intent(paramAnonymousView.getContext(), NoteActivity.class);
            localIntent.putExtra("search_history_item", localSearchHistoryItem);
            paramAnonymousView.getContext().startActivity(localIntent);
            return true;
          }
        });
        if (this.val$isSearchable)
          paramAnonymousContextMenu.add(0, 4, 4, R.string.withdraw_submission_menu_label).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
          {
            public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
            {
              SearchHistoryItemPresenter.confirmAndMaybeWithdrawHistoryItem(paramAnonymousView);
              return true;
            }
          });
        paramAnonymousContextMenu.add(0, 5, 5, R.string.delete).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener()
        {
          public boolean onMenuItemClick(MenuItem paramAnonymous2MenuItem)
          {
            SearchHistoryItemPresenter.confirmAndMaybeDelete(paramAnonymousView);
            return true;
          }
        });
      }
    };
  }

  private static void replayHistoryItem(View paramView)
  {
    ItemModels.replay((SearchHistoryItem)paramView.getTag(), paramView.getContext());
  }

  void presentAsListItem(SearchHistoryItem paramSearchHistoryItem, View paramView)
  {
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.title);
    TextView localTextView2;
    if (TextUtils.isEmpty(paramSearchHistoryItem.getTitle()))
    {
      localTextView1.setVisibility(8);
      ((TextView)paramView.findViewById(R.id.relative_time)).setText(ItemModels.getRelativeTimeString(paramView.getContext(), paramSearchHistoryItem.getTimestamp()));
      ((TextView)paramView.findViewById(R.id.location)).setText(paramSearchHistoryItem.getLocation());
      localTextView2 = (TextView)paramView.findViewById(R.id.notes);
      if (TextUtils.isEmpty(paramSearchHistoryItem.getNote()))
        break label145;
      localTextView2.setVisibility(0);
      localTextView2.setText(paramSearchHistoryItem.getNote());
    }
    while (true)
    {
      paramView.setTag(paramSearchHistoryItem);
      paramView.setOnClickListener(HISTORY_ITEM_CLICK_LISTENER);
      paramView.setOnCreateContextMenuListener(makeContextMenuListener(paramSearchHistoryItem.isSearchable()));
      return;
      localTextView1.setVisibility(0);
      localTextView1.setText(paramSearchHistoryItem.getTitle());
      break;
      label145: localTextView2.setVisibility(8);
    }
  }

  void updateThumbnail(SearchHistoryItem paramSearchHistoryItem, ImageView paramImageView, ThumbnailProvider paramThumbnailProvider)
  {
    String str = paramSearchHistoryItem.getThumbnailUrl();
    if (paramImageView != null)
    {
      paramImageView.setImageResource(R.drawable.no_thumbnail);
      paramImageView.setTag(null);
      paramThumbnailProvider.fetch(str, ThumbnailProvider.makeImageViewThumbnailListener(paramImageView, str), ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
    }
  }

  private static final class ShareMenuItemClickListener
    implements MenuItem.OnMenuItemClickListener
  {
    private final SearchHistoryItem historyItem;
    private final View listItemView;
    private final SearchHistoryProvider searchHistoryProvider;

    private ShareMenuItemClickListener(SearchHistoryProvider paramSearchHistoryProvider, View paramView, SearchHistoryItem paramSearchHistoryItem)
    {
      this.searchHistoryProvider = paramSearchHistoryProvider;
      this.listItemView = paramView;
      this.historyItem = paramSearchHistoryItem;
    }

    private Picture getThumbnail()
    {
      Object localObject = this.listItemView.findViewById(R.id.history_thumbnail).getTag();
      if ((localObject instanceof Picture))
        return (Picture)localObject;
      return null;
    }

    private void shareItem(final ShareDialog paramShareDialog, final Picture paramPicture)
    {
      this.searchHistoryProvider.share(this.historyItem.getMomentId(), new SearchHistoryProvider.UpdateListener()
      {
        public void onError()
        {
          paramShareDialog.dismiss();
          new AlertDialog.Builder(SearchHistoryItemPresenter.ShareMenuItemClickListener.this.listItemView.getContext()).setIcon(17301543).setMessage(R.string.network_error).setPositiveButton(R.string.ok, null).show();
        }

        public void onResult(SearchHistoryItem paramAnonymousSearchHistoryItem)
        {
          if (paramShareDialog.isShowing())
            paramShareDialog.populate(paramAnonymousSearchHistoryItem, paramPicture);
        }
      });
    }

    public boolean onMenuItemClick(MenuItem paramMenuItem)
    {
      ShareDialog localShareDialog = new ShareDialog(this.listItemView.getContext());
      Picture localPicture = getThumbnail();
      localShareDialog.populate(this.historyItem, localPicture);
      localShareDialog.show();
      if (!this.historyItem.isShared())
        shareItem(localShareDialog, localPicture);
      return true;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.SearchHistoryItemPresenter
 * JD-Core Version:    0.6.2
 */