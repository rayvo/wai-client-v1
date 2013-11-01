package com.google.android.apps.unveil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import com.actionbarsherlock.app.SherlockActivity;

public class LocationFirstRunActivity extends SherlockActivity
{
  private UnveilApplication application;

  private void configLearnMoreLink(TextView paramTextView)
  {
    SpannableString localSpannableString = new SpannableString(paramTextView.getText());
    localSpannableString.setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(LocationFirstRunActivity.this.getString(R.string.location_learn_more_link)));
        LocationFirstRunActivity.this.startActivity(localIntent);
      }
    }
    , 0, localSpannableString.length(), 33);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    paramTextView.setText(localSpannableString);
  }

  private void startSearchHistoryActivity()
  {
    if ((this.application.isUpgrade()) || (this.application.isFirstRun()))
    {
      this.application.setPreviousVersion(this.application.getVersionCode());
      startActivity(UnveilApplication.makeSearchHistoryActivityIntent(this));
    }
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    setContentView(R.layout.location_first_run);
    ((Button)findViewById(R.id.next)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LocationFirstRunActivity.this.startSearchHistoryActivity();
      }
    });
    configLearnMoreLink((TextView)findViewById(R.id.learn_more));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.LocationFirstRunActivity
 * JD-Core Version:    0.6.2
 */