package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.TagListener;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.ui.history.ItemModels;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class TagActivity extends StateRestorationActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilApplication context;

  private String getType()
  {
    Spinner localSpinner = (Spinner)findViewById(R.id.type_selector);
    if (localSpinner.getSelectedItemPosition() == 0)
      return "";
    if (localSpinner.getSelectedItemPosition() == -1 + localSpinner.getCount())
      return ((EditText)findViewById(R.id.other_type)).getText().toString();
    return (String)localSpinner.getSelectedItem();
  }

  private void sendSubmission(final SearchHistoryItem paramSearchHistoryItem, Rect paramRect, final String paramString1, final String paramString2, final String paramString3, final ProgressDialog paramProgressDialog)
  {
    this.context.getSearchHistoryProvider().setUserSubmission(paramSearchHistoryItem.getMomentId(), paramString1, paramString2, paramString3, paramRect, new SearchHistoryProvider.TagListener()
    {
      public void onError()
      {
        paramProgressDialog.dismiss();
        Toast.makeText(TagActivity.this, R.string.sending_tags_failed, 1).show();
      }

      public void onResult()
      {
        paramProgressDialog.dismiss();
        TagActivity.this.showThankYouDialog(paramSearchHistoryItem, paramString1, paramString2, paramString3);
      }
    });
  }

  private void showEnterNameDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.enter_a_name);
    localBuilder.setIcon(17301543);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(R.string.ok, null);
    localBuilder.show();
  }

  private void showPrivacyDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.learn_more_title);
    localBuilder.setMessage(R.string.learn_more_content);
    localBuilder.setIcon(17301659);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(R.string.ok, null);
    localBuilder.show();
  }

  private void showThankYouDialog(SearchHistoryItem paramSearchHistoryItem, String paramString1, String paramString2, String paramString3)
  {
    Dialog localDialog = new Dialog(this);
    localDialog.requestWindowFeature(1);
    localDialog.setContentView(R.layout.tag_activity_thank_you_dialog);
    ItemModels.updateThumbnail(paramSearchHistoryItem, (ImageView)localDialog.findViewById(R.id.thumbnail), this.context.getThumbnailCache());
    ((TextView)localDialog.findViewById(R.id.name)).setText(paramString1);
    ((TextView)localDialog.findViewById(R.id.type)).setText(paramString3);
    ((TextView)localDialog.findViewById(R.id.description)).setText(paramString2);
    ((Button)localDialog.findViewById(R.id.tagging_dialog_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TagActivity.this.setResult(-1);
        TagActivity.this.finish();
      }
    });
    localDialog.show();
  }

  public void onBackPressed()
  {
    ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DESCRIBE_BACK);
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.tag);
    final SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)getIntent().getSerializableExtra("search_history_item");
    final Rect localRect = (Rect)getIntent().getExtras().get("tagging_rect");
    Button localButton = (Button)findViewById(R.id.submit_button);
    this.context = ((UnveilApplication)getApplicationContext());
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DESCRIBE_SUBMIT);
        String str1 = ((EditText)TagActivity.this.findViewById(R.id.name)).getText().toString();
        String str2 = ((EditText)TagActivity.this.findViewById(R.id.description)).getText().toString();
        String str3 = TagActivity.this.getType();
        if (TextUtils.isEmpty(str1.trim()))
        {
          TagActivity.this.findViewById(R.id.name).requestFocus();
          TagActivity.this.showEnterNameDialog();
          return;
        }
        ProgressDialog localProgressDialog = ProgressDialog.show(TagActivity.this, "", TagActivity.this.getString(R.string.saving), true, false);
        TagActivity.this.sendSubmission(localSearchHistoryItem, localRect, str1, str2, str3, localProgressDialog);
      }
    });
    ((Button)findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DESCRIBE_CANCEL);
        TagActivity.this.setResult(1);
        TagActivity.this.finish();
      }
    });
    TextView localTextView = (TextView)findViewById(R.id.privacy_notice);
    localTextView.setText(Html.fromHtml(getResources().getString(R.string.submission_legal_text) + " <font color='#6666ff'><u>" + getResources().getString(R.string.learn_more) + "</u></font>"));
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        TagActivity.this.showPrivacyDialog();
      }
    });
    final View localView1 = findViewById(R.id.other_type);
    final View localView2 = findViewById(R.id.other_type_label);
    ((Spinner)findViewById(R.id.type_selector)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
    {
      public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        if (paramAnonymousInt == -1 + paramAnonymousAdapterView.getCount())
        {
          localView1.setVisibility(0);
          localView2.setVisibility(0);
          localView1.requestFocus();
        }
        while (true)
        {
          ((InputMethodManager)TagActivity.this.getSystemService("input_method")).hideSoftInputFromWindow(paramAnonymousView.getWindowToken(), 0);
          return;
          localView1.setVisibility(8);
          localView2.setVisibility(8);
        }
      }

      public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
      {
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.TagActivity
 * JD-Core Version:    0.6.2
 */