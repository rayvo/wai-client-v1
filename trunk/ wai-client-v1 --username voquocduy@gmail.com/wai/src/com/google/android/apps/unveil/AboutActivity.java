package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.apps.unveil.env.InfoProvider;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.feedback.FeedbackUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AboutActivity extends StateRestorationActivity
{
  private static final int LEGAL_CRAP_DIALOG = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  UnveilContext application;

  private String getLegalDisclaimers()
  {
    BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(getResources().openRawResource(R.raw.legal_notices)));
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      while (localBufferedReader.ready())
        localStringBuilder.append(localBufferedReader.readLine() + "\n");
    }
    catch (IOException localIOException)
    {
      return "";
    }
    return localStringBuilder.toString();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.about);
    this.application = ((UnveilContext)getApplication());
    UnveilApplication.configureWindowFormat(getWindow());
    ((TextView)findViewById(R.id.about_version)).setText(getString(R.string.version_label) + " " + InfoProvider.getClientVersion(this));
    ((Button)findViewById(R.id.about_help_button)).setOnClickListener(new GoToLinkListener(R.string.help_link));
    ((Button)findViewById(R.id.about_tos_button)).setOnClickListener(new GoToLinkListener(R.string.tos_link));
    ((Button)findViewById(R.id.about_privacy_button)).setOnClickListener(new GoToLinkListener(R.string.privacy_link));
    ((Button)findViewById(R.id.about_video_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AboutActivity.this.startActivity(new Intent(AboutActivity.this, TipsActivity.class));
      }
    });
    ((Button)findViewById(R.id.send_feedback_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        FeedbackUtils.onFeedbackOptionsItemSelected(AboutActivity.this, null);
      }
    });
    ((Button)findViewById(R.id.legal_notices_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        AboutActivity.this.showDialog(1);
      }
    });
    ((Button)findViewById(R.id.content_policy_button)).setOnClickListener(new GoToLinkListener(getString(R.string.content_policy_url)));
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 1:
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setTitle(R.string.legal_notices);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(R.string.ok, null);
    localBuilder.setMessage(getLegalDisclaimers());
    return localBuilder.create();
  }

  private class GoToLinkListener
    implements View.OnClickListener
  {
    private final String link;

    public GoToLinkListener(int arg2)
    {
      int i;
      this.link = AboutActivity.this.getString(i);
    }

    public GoToLinkListener(String arg2)
    {
      Object localObject;
      this.link = localObject;
    }

    public void onClick(View paramView)
    {
      Intent localIntent = new Intent("android.intent.action.VIEW");
      localIntent.setData(Uri.parse(this.link));
      AboutActivity.this.startActivity(localIntent);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.AboutActivity
 * JD-Core Version:    0.6.2
 */