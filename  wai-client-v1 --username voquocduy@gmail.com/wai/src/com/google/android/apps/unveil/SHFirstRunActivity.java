package com.google.android.apps.unveil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryEngine;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class SHFirstRunActivity extends AuthenticatedActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private String[] accounts;
  private final View.OnClickListener confirmClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SHFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_NEXT);
      boolean bool = ((CheckBox)SHFirstRunActivity.this.findViewById(R.id.checkbox)).isChecked();
      if (!bool)
      {
        SHFirstRunActivity.this.application.setUserWantsHistory(false);
        SHFirstRunActivity.this.application.getAuthState().clearAuthToken(AuthToken.AuthTokenType.SID);
        SHFirstRunActivity.this.finishedWithHistorySetting();
        return;
      }
      if (SHFirstRunActivity.this.accounts.length <= 1)
      {
        if (SHFirstRunActivity.this.application.userWantsHistory() != bool)
        {
          SHFirstRunActivity.this.saveHistorySetting();
          return;
        }
        SHFirstRunActivity.this.finishedWithHistorySetting();
        return;
      }
      String str1 = SHFirstRunActivity.this.application.getAuthState().getAccount();
      String str2 = SHFirstRunActivity.this.getCheckedAccount();
      if (str2 == null)
      {
        SHFirstRunActivity.logger.e("Several accounts exist but no one selected! Fallback to disable history.", new Object[0]);
        SHFirstRunActivity.this.application.setUserWantsHistory(false);
        SHFirstRunActivity.this.application.getAuthState().clearAuthToken(AuthToken.AuthTokenType.SID);
        SHFirstRunActivity.this.finishedWithHistorySetting();
        return;
      }
      if ((str2 == str1) || ((str1 != null) && (str2.equals(str1))))
      {
        if (SHFirstRunActivity.this.application.userWantsHistory() != bool)
        {
          SHFirstRunActivity.this.saveHistorySetting();
          return;
        }
        SHFirstRunActivity.this.finishedWithHistorySetting();
        return;
      }
      SHFirstRunActivity.this.authenticator.setPreferredHistoryAccount(str2);
      SHFirstRunActivity.this.application.getSearchHistoryProvider().getEngine().invalidateXsrfToken();
      SHFirstRunActivity.this.invalidateAuthToken();
      SHFirstRunActivity.this.saveHistorySetting();
    }
  };
  private RadioGroup radioGroup;

  private void configLearnMoreLink(TextView paramTextView)
  {
    SpannableString localSpannableString = new SpannableString(paramTextView.getText());
    localSpannableString.setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        SHFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_LEARN_MORE);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(SHFirstRunActivity.this.getString(R.string.search_history_learn_more_link)));
        SHFirstRunActivity.this.startActivity(localIntent);
      }
    }
    , 0, localSpannableString.length(), 33);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    paramTextView.setText(localSpannableString);
  }

  private void finishedWithHistorySetting()
  {
    if (isUpselling())
      respondToUpsellIntent();
    while (true)
    {
      finish();
      return;
      if ((this.application.isUpgrade()) || (this.application.isFirstRun()))
        startActivity(new Intent(this, LocationFirstRunActivity.class));
    }
  }

  private String getCheckedAccount()
  {
    int i = this.radioGroup.getChildCount();
    for (int j = 0; j < i; j++)
    {
      RadioButton localRadioButton = (RadioButton)this.radioGroup.getChildAt(j);
      if (localRadioButton.isChecked())
        return (String)localRadioButton.getTag();
    }
    return null;
  }

  private boolean isUpselling()
  {
    if (getIntent().getAction() != null)
      return getIntent().getAction().equals("com.google.android.apps.unveil.moment_upsell");
    return false;
  }

  private void respondToUpsellIntent()
  {
    if (this.application.isSearchHistoryEnabled());
    for (int i = -1; ; i = 0)
    {
      setResult(i);
      return;
    }
  }

  private void restoreUi()
  {
    ((CheckBox)findViewById(R.id.checkbox)).setChecked(this.application.userWantsHistory());
    String str = this.application.getAuthState().getAccount();
    int i = this.radioGroup.getChildCount();
    this.radioGroup.clearCheck();
    if (!TextUtils.isEmpty(str))
      for (int j = 0; j < i; j++)
      {
        RadioButton localRadioButton = (RadioButton)this.radioGroup.getChildAt(j);
        if (str.equals(localRadioButton.getTag()))
          localRadioButton.setChecked(true);
      }
    if ((this.radioGroup.getCheckedRadioButtonId() == -1) && (i > 0))
      ((RadioButton)this.radioGroup.getChildAt(0)).setChecked(true);
  }

  private void saveHistorySetting()
  {
    boolean bool = ((CheckBox)findViewById(R.id.checkbox)).isChecked();
    this.application.setUserWantsHistory(bool);
    if (!this.authenticator.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
    {
      fetchAuthToken();
      return;
    }
    finishedWithHistorySetting();
  }

  private void updateUiEnabled()
  {
    CheckBox localCheckBox = (CheckBox)findViewById(R.id.checkbox);
    TextView localTextView = (TextView)findViewById(R.id.radio_group_title);
    View localView = findViewById(R.id.radio_shade);
    int i = this.radioGroup.getChildCount();
    boolean bool = localCheckBox.isChecked();
    localTextView.setEnabled(bool);
    localTextView.setFocusable(bool);
    this.radioGroup.setEnabled(bool);
    this.radioGroup.setFocusable(bool);
    if (bool);
    for (int j = 8; ; j = 0)
    {
      localView.setVisibility(j);
      for (int k = 0; k < i; k++)
      {
        RadioButton localRadioButton = (RadioButton)this.radioGroup.getChildAt(k);
        localRadioButton.setEnabled(bool);
        localRadioButton.setFocusable(bool);
      }
    }
    if ((bool) && (this.radioGroup.getCheckedRadioButtonId() == -1))
      findViewById(R.id.next).setEnabled(false);
    while (true)
    {
      this.radioGroup.setVisibility(0);
      localTextView.setVisibility(0);
      if (this.radioGroup.getChildCount() <= 0)
      {
        findViewById(R.id.next).setEnabled(true);
        this.radioGroup.setVisibility(8);
        localTextView.setVisibility(8);
        localView.setVisibility(8);
      }
      return;
      findViewById(R.id.next).setEnabled(true);
    }
  }

  public void onAuthCanceled()
  {
    this.application.setUserWantsHistory(false);
    getAuthenticator().setPreferredHistoryAccount(null);
  }

  public void onAuthFailure()
  {
    onAuthCanceled();
  }

  public void onAuthSuccess()
  {
    saveHistorySetting();
  }

  public void onAuthTokenInvalidated()
  {
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    if ((this.application.userWantsHistory()) && ((this.application.isUpgrade()) || (this.application.isFirstRun())))
    {
      finishedWithHistorySetting();
      return;
    }
    setContentView(R.layout.sh_first_run);
    this.radioGroup = ((RadioGroup)findViewById(R.id.radio_group));
    this.radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
    {
      public void onCheckedChanged(RadioGroup paramAnonymousRadioGroup, int paramAnonymousInt)
      {
        SHFirstRunActivity.this.updateUiEnabled();
      }
    });
    Button localButton = (Button)findViewById(R.id.next);
    if ((!this.application.isFirstRun()) && (!this.application.isUpgrade()))
      localButton.setText(getString(R.string.ok));
    localButton.setOnClickListener(this.confirmClickListener);
    final CheckBox localCheckBox = (CheckBox)findViewById(R.id.checkbox);
    findViewById(R.id.search_history_label).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        CheckBox localCheckBox = localCheckBox;
        if (!localCheckBox.isChecked());
        for (boolean bool = true; ; bool = false)
        {
          localCheckBox.setChecked(bool);
          return;
        }
      }
    });
    localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
          SHFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_ON);
        while (true)
        {
          SHFirstRunActivity.this.updateUiEnabled();
          return;
          SHFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_OFF);
        }
      }
    });
    configLearnMoreLink((TextView)findViewById(R.id.learn_more));
  }

  protected void onStart()
  {
    super.onStart();
    this.accounts = getAccounts();
    this.radioGroup.removeAllViews();
    for (String str : this.accounts)
    {
      RadioButton localRadioButton = new RadioButton(getApplicationContext());
      localRadioButton.setText(str);
      localRadioButton.setTag(str);
      localRadioButton.setTextColor(R.drawable.text_color);
      this.radioGroup.addView(localRadioButton);
    }
    updateUiEnabled();
    restoreUi();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.SHFirstRunActivity
 * JD-Core Version:    0.6.2
 */