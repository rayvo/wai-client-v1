package com.google.android.apps.unveil.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.NoteActivity;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.ui.history.ItemModels;

public class BackOfCardView extends FrameLayout
{
  private static final View.OnClickListener NOTE_LISTENER = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)paramAnonymousView.getTag();
      Intent localIntent = new Intent(paramAnonymousView.getContext(), NoteActivity.class);
      localIntent.putExtra("search_history_item", localSearchHistoryItem);
      ((Activity)paramAnonymousView.getContext()).startActivityForResult(localIntent, 2);
    }
  };
  private static final View.OnClickListener SHARE_LISTENER;
  private static final UnveilLogger logger = new UnveilLogger();
  private final View noteView;
  private final View shareView;

  static
  {
    SHARE_LISTENER = new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)paramAnonymousView.getTag();
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(localSearchHistoryItem.getMomentUrl()));
        paramAnonymousView.getContext().startActivity(localIntent);
      }
    };
  }

  public BackOfCardView(Context paramContext)
  {
    super(paramContext);
    FrameLayout localFrameLayout = (FrameLayout)LayoutInflater.from(paramContext).inflate(R.layout.back_of_card, null);
    addView(localFrameLayout);
    this.noteView = localFrameLayout.findViewById(R.id.note);
    this.shareView = localFrameLayout.findViewById(R.id.share_status);
    this.noteView.setOnClickListener(NOTE_LISTENER);
    this.shareView.setOnClickListener(SHARE_LISTENER);
  }

  public void setThumbnail(Picture paramPicture)
  {
    if (paramPicture == null)
      return;
    paramPicture.populateWithBitmap((ImageView)findViewById(R.id.thumbnail));
  }

  public void updateWidgets(SearchHistoryItem paramSearchHistoryItem)
  {
    if (paramSearchHistoryItem == null)
      throw new IllegalArgumentException("Updating back-of-card but search history item is null");
    View localView1 = getRootView();
    TextView localTextView1 = (TextView)localView1.findViewById(R.id.location);
    TextView localTextView2 = (TextView)localView1.findViewById(R.id.note_text);
    ((TextView)localView1.findViewById(R.id.time)).setText(ItemModels.getRelativeTimeString(localView1.getContext(), paramSearchHistoryItem.getTimestamp()));
    localTextView1.setText(paramSearchHistoryItem.getLocation());
    String str = paramSearchHistoryItem.getNote();
    View localView2;
    if (TextUtils.isEmpty(str))
    {
      localTextView2.setText(R.string.tap_to_add_note);
      localView2 = localView1.findViewById(R.id.share_layout);
      if (!paramSearchHistoryItem.isShared())
        break label149;
    }
    label149: for (int i = 0; ; i = 8)
    {
      localView2.setVisibility(i);
      this.noteView.setTag(paramSearchHistoryItem);
      this.shareView.setTag(paramSearchHistoryItem);
      return;
      localTextView2.setText(str);
      break;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.BackOfCardView
 * JD-Core Version:    0.6.2
 */