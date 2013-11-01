package com.google.android.apps.unveil.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.text.style.URLSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.ui.history.ItemModels;

public class ShareDialog extends Dialog
{
  public ShareDialog(Context paramContext)
  {
    super(paramContext);
    View localView = LayoutInflater.from(paramContext).inflate(R.layout.share_dialog, null);
    setTitle(R.string.share_your_results);
    setContentView(localView);
    ((Button)findViewById(R.id.cancel)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ShareDialog.this.dismiss();
      }
    });
  }

  private View.OnClickListener makeShareOkListener(final SearchHistoryItem paramSearchHistoryItem)
  {
    return new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent();
        localIntent.setAction("android.intent.action.SEND");
        localIntent.setType("text/plain");
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = ShareDialog.this.getContext().getString(R.string.sharing_preface);
        arrayOfObject[1] = paramSearchHistoryItem.getMomentUrl();
        localIntent.putExtra("android.intent.extra.TEXT", String.format("%s %s", arrayOfObject));
        paramAnonymousView.getContext().startActivity(Intent.createChooser(localIntent, paramAnonymousView.getContext().getString(R.string.share_label)));
        ShareDialog.this.dismiss();
      }
    };
  }

  public void populate(SearchHistoryItem paramSearchHistoryItem, Picture paramPicture)
  {
    if (paramPicture != null)
      paramPicture.populateWithBitmap((ImageView)findViewById(R.id.thumbnail));
    ((TextView)findViewById(R.id.title)).setText(paramSearchHistoryItem.getTitle());
    ((TextView)findViewById(R.id.time)).setText(ItemModels.getRelativeTimeString(getContext(), paramSearchHistoryItem.getTimestamp()));
    populateShareDialogUrl(paramSearchHistoryItem);
    ((TextView)findViewById(R.id.location)).setText(paramSearchHistoryItem.getLocation());
    ((Button)findViewById(R.id.share)).setOnClickListener(makeShareOkListener(paramSearchHistoryItem));
  }

  void populateShareDialogUrl(SearchHistoryItem paramSearchHistoryItem)
  {
    TextView localTextView1 = (TextView)findViewById(R.id.moment_url);
    TextView localTextView2 = (TextView)findViewById(R.id.url_loading);
    if (paramSearchHistoryItem.isShared())
    {
      SpannableStringBuilder localSpannableStringBuilder = new SpannableStringBuilder(paramSearchHistoryItem.getMomentUrl());
      localSpannableStringBuilder.setSpan(new URLSpan(paramSearchHistoryItem.getMomentUrl()), 0, localSpannableStringBuilder.length(), 33);
      localTextView1.setText(localSpannableStringBuilder);
      localTextView1.setMovementMethod(LinkMovementMethod.getInstance());
      localTextView2.setVisibility(8);
      localTextView1.setVisibility(0);
      return;
    }
    localTextView1.setText(paramSearchHistoryItem.getMomentUrl());
    localTextView2.setText(localTextView1.getContext().getString(R.string.loading_url));
    localTextView2.setVisibility(0);
    localTextView1.setVisibility(8);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ShareDialog
 * JD-Core Version:    0.6.2
 */