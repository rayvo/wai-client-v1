package com.google.android.apps.unveil;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import com.google.goggles.NativeClientLoggingProtos;

public class WelcomeActivity extends StateRestorationActivity
{
  private static final int DIALOG_UNSUPPORTED_LANGUAGE = 1;
  private static final String UNSUPPORTED_LANGUAGE_MESSAGE_PARAMETER = "YOUR_LANGUAGE";
  private UnveilApplication application;

  private void configLearnMoreLink(TextView paramTextView)
  {
    SpannableString localSpannableString = new SpannableString(paramTextView.getText());
    localSpannableString.setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        WelcomeActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.WELCOME_LEARN_MORE_VALUE);
        WelcomeActivity.this.startActivity(new Intent(WelcomeActivity.this, TipsActivity.class));
      }
    }
    , 0, localSpannableString.length(), 33);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    paramTextView.setText(localSpannableString);
  }

  private boolean isUserLanguageSupported()
  {
    String str = this.application.getLanguage();
    return ConstantConfiguration.CLIENT_LANGUAGES.contains(str);
  }

  private void startSfCPromotionAndFinish()
  {
    startActivity(new Intent(this, SfCFirstRunActivity.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    setContentView(R.layout.welcome);
    configLearnMoreLink((TextView)findViewById(R.id.learn_more));
    findViewById(R.id.lets_start).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WelcomeActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.WELCOME_NEXT_VALUE);
        WelcomeActivity.this.startSfCPromotionAndFinish();
      }
    });
    if (!isUserLanguageSupported())
      showDialog(1);
    getSupportActionBar().setHomeButtonEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    switch (paramInt)
    {
    default:
      return null;
    case 1:
    }
    localBuilder.setTitle(R.string.unsupported_language_title);
    localBuilder.setIcon(17301543);
    String str = getResources().getString(R.string.current_language_unsupported_message);
    if (str.contains("YOUR_LANGUAGE"))
      str = getResources().getString(R.string.current_language_unsupported_message_english);
    localBuilder.setMessage(str);
    localBuilder.setPositiveButton(R.string.continue_label, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        WelcomeActivity.this.dismissDialog(1);
      }
    });
    localBuilder.setNegativeButton(R.string.close_translated, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        WelcomeActivity.this.dismissDialog(1);
        WelcomeActivity.this.finish();
      }
    });
    return localBuilder.create();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.WelcomeActivity
 * JD-Core Version:    0.6.2
 */