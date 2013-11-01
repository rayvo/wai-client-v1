package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;
import android.text.TextUtils;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.SessionlessRequests;
import com.google.android.apps.unveil.service.BackgroundGogglesPreferencesActivity;
import java.net.MalformedURLException;
import java.net.URL;

public class PreferencesActivity extends PreferenceActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private Preference backgroundGogglesDetailedSettingsPreference;

  private void configureBackgroundGogglesPreferenceItem()
  {
    if (Settings.getBoolean(this, R.string.background_goggle_key));
    for (int i = R.string.enabled; ; i = R.string.disabled)
    {
      this.backgroundGogglesDetailedSettingsPreference.setSummary(i);
      return;
    }
  }

  private void configureHistoryPreferenceItem()
  {
    Preference localPreference = findPreference(getString(R.string.user_wants_history_key));
    String str;
    if (this.application.userWantsHistory())
    {
      str = this.application.getAuthState().getAccount();
      if (TextUtils.isEmpty(str))
        localPreference.setSummary(getResources().getString(R.string.enabled));
    }
    while (true)
    {
      localPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener()
      {
        public boolean onPreferenceClick(Preference paramAnonymousPreference)
        {
          Intent localIntent = new Intent(PreferencesActivity.this, SHFirstRunActivity.class);
          PreferencesActivity.this.startActivity(localIntent);
          return true;
        }
      });
      return;
      localPreference.setSummary(String.format(getResources().getString(R.string.enabled_for_account), new Object[] { str }));
      continue;
      localPreference.setSummary(R.string.disabled);
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilContext)getApplicationContext());
    StateRestorationActivity.restoreState(paramBundle, this.application, this);
    addPreferencesFromResource(R.xml.goggles_preferences);
    setContentView(R.layout.goggles_preferences);
    getPreferenceScreen().removePreference(findPreference(getString(R.string.debug_group_key)));
    this.backgroundGogglesDetailedSettingsPreference = findPreference(getString(R.string.background_goggle_detailed_settings_placeholder_key));
    this.backgroundGogglesDetailedSettingsPreference.setIntent(new Intent(this, BackgroundGogglesPreferencesActivity.class));
  }

  protected void onResume()
  {
    super.onResume();
    configureHistoryPreferenceItem();
    configureBackgroundGogglesPreferenceItem();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
  }

  protected void onStop()
  {
    super.onStop();
    SessionlessRequests.sendLogs(this.application);
  }

  private class FrontendPreferenceChangeListener
    implements Preference.OnPreferenceChangeListener
  {
    private FrontendPreferenceChangeListener()
    {
    }

    private String getCompleteDomain(String paramString1, String paramString2)
    {
      if ((paramString2.equals(PreferencesActivity.this.getString(R.string.custom_frontend_domain_key))) && (!paramString1.contains(".")))
        paramString1 = paramString1 + ".visualsearch.sandbox.google.com";
      return paramString1;
    }

    public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
    {
      if (TextUtils.isEmpty((String)paramObject))
        return false;
      String str1 = paramPreference.getKey();
      String str2 = getCompleteDomain((String)paramObject, str1);
      if (str1.equals(PreferencesActivity.this.getString(R.string.frontend_domain_key)))
      {
        PreferencesActivity.logger.d("User chose a frontend from the list, clearing custom frontend.", new Object[0]);
        PreferencesActivity.this.getPreferenceManager().getSharedPreferences().edit().remove(PreferencesActivity.this.getString(R.string.custom_frontend_domain_key)).commit();
      }
      try
      {
        URL localURL = new URL("http", str2, "");
        PreferencesActivity.this.application.setFrontendUrl(localURL);
        return true;
      }
      catch (MalformedURLException localMalformedURLException)
      {
        PreferencesActivity.logger.e(localMalformedURLException, "Could not parse a URL out of %s; not committing.", new Object[] { paramObject });
      }
      return false;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.PreferencesActivity
 * JD-Core Version:    0.6.2
 */