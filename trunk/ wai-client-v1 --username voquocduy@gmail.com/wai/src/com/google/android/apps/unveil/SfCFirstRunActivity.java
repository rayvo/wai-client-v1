package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.TextView;
import com.actionbarsherlock.app.ActionBar;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.service.BackgroundGogglesPreferencesActivity.MobileConnectionOption;
import com.google.android.apps.unveil.service.PictureRequestService;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class SfCFirstRunActivity extends StateRestorationActivity
{
  private UnveilApplication application;

  private void configLearnMoreLink(TextView paramTextView)
  {
    SpannableString localSpannableString = new SpannableString(paramTextView.getText());
    localSpannableString.setSpan(new ClickableSpan()
    {
      public void onClick(View paramAnonymousView)
      {
        SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_LEARN_MORE);
        Intent localIntent = new Intent("android.intent.action.VIEW");
        localIntent.setData(Uri.parse(SfCFirstRunActivity.this.getString(R.string.search_from_camera_learn_more_link)));
        SfCFirstRunActivity.this.startActivity(localIntent);
      }
    }
    , 0, localSpannableString.length(), 33);
    paramTextView.setMovementMethod(LinkMovementMethod.getInstance());
    paramTextView.setText(localSpannableString);
  }

  private boolean isFirstRunFlow()
  {
    UnveilApplication localUnveilApplication = (UnveilApplication)getApplication();
    return (localUnveilApplication.isFirstRun()) || (localUnveilApplication.isUpgrade());
  }

  private void restoreUi()
  {
    CheckBox localCheckBox = (CheckBox)findViewById(R.id.checkbox);
    RadioButton localRadioButton1 = (RadioButton)findViewById(R.id.radio_wifi_mobile);
    RadioButton localRadioButton2 = (RadioButton)findViewById(R.id.radio_wifi_only);
    localCheckBox.setChecked(Settings.getBoolean(this, R.string.background_goggle_key));
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    String str = BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_ONLY.getEntryValue(this);
    if (localSharedPreferences.getString(getString(R.string.background_goggle_mobile_connection_key), BackgroundGogglesPreferencesActivity.MobileConnectionOption.DEFAULT.getEntryValue(this)).equals(str))
    {
      localRadioButton2.setChecked(true);
      return;
    }
    localRadioButton1.setChecked(true);
  }

  private void saveConfig()
  {
    boolean bool = ((CheckBox)findViewById(R.id.checkbox)).isChecked();
    Settings.putBoolean(this, R.string.background_goggle_key, bool);
    if (bool)
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
      if (((RadioButton)findViewById(R.id.radio_wifi_only)).isChecked())
        localSharedPreferences.edit().putString(getString(R.string.background_goggle_mobile_connection_key), BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_ONLY.getEntryValue(this)).commit();
      while (true)
      {
        PictureRequestService.onEnable(this);
        return;
        localSharedPreferences.edit().putString(getString(R.string.background_goggle_mobile_connection_key), BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_AND_MOBILE_NETWORKS.getEntryValue(this)).commit();
      }
    }
    PictureRequestService.onDisable(this);
  }

  private void startSHPromotionActivityAndFinish()
  {
    startActivity(new Intent(this, SHFirstRunActivity.class));
    finish();
  }

  private void updateUi()
  {
    CheckBox localCheckBox = (CheckBox)findViewById(R.id.checkbox);
    View localView1 = findViewById(R.id.radio_wifi_mobile);
    View localView2 = findViewById(R.id.radio_wifi_only);
    boolean bool = localCheckBox.isChecked();
    View localView3 = findViewById(R.id.radio_shade);
    if (bool);
    for (int i = 8; ; i = 0)
    {
      localView3.setVisibility(i);
      localView1.setEnabled(bool);
      localView1.setFocusable(bool);
      localView2.setEnabled(bool);
      localView2.setFocusable(bool);
      findViewById(R.id.radio_group_title).setFocusable(bool);
      findViewById(R.id.warning).setFocusable(bool);
      return;
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    getSupportActionBar().setHomeButtonEnabled(false);
    if ((isFirstRunFlow()) && (Settings.getBoolean(this, R.string.background_goggle_key, false)))
    {
      startSHPromotionActivityAndFinish();
      return;
    }
    setContentView(R.layout.sfc_first_run);
    this.application = ((UnveilApplication)getApplication());
    Button localButton = (Button)findViewById(R.id.next);
    if ((!this.application.isFirstRun()) && (!this.application.isUpgrade()))
      localButton.setText(getString(R.string.ok));
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SfCFirstRunActivity.this.saveConfig();
        SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_NEXT);
        if (SfCFirstRunActivity.this.isFirstRunFlow())
        {
          SfCFirstRunActivity.this.startSHPromotionActivityAndFinish();
          return;
        }
        SfCFirstRunActivity.this.finish();
      }
    });
    final CheckBox localCheckBox = (CheckBox)findViewById(R.id.checkbox);
    localCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
    {
      public void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        if (paramAnonymousBoolean)
          SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_ON);
        while (true)
        {
          SfCFirstRunActivity.this.updateUi();
          return;
          SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_OFF);
        }
      }
    });
    RadioButton localRadioButton1 = (RadioButton)findViewById(R.id.radio_wifi_mobile);
    RadioButton localRadioButton2 = (RadioButton)findViewById(R.id.radio_wifi_only);
    localRadioButton1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_MOBILE_WIFI);
      }
    });
    localRadioButton2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        SfCFirstRunActivity.this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_WIFI_ONLY);
      }
    });
    findViewById(R.id.search_from_camera_label).setOnClickListener(new View.OnClickListener()
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
    configLearnMoreLink((TextView)findViewById(R.id.learn_more));
    restoreUi();
  }

  protected void onResume()
  {
    super.onResume();
    updateUi();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.SfCFirstRunActivity
 * JD-Core Version:    0.6.2
 */