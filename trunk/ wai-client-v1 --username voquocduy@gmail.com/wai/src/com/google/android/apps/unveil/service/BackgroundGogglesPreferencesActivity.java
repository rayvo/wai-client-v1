package com.google.android.apps.unveil.service;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.R.xml;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.StateRestorationActivity;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;

public class BackgroundGogglesPreferencesActivity extends PreferenceActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private DependencyTogglingBackgroundGogglingListener backgroundGogglesChangeListener;
  private CheckBoxPreference backgroundGogglesPreference;
  private UnveilContext context;
  private ListPreference mobileConnectionPreference;
  private CheckBoxPreference roamingPreference;
  private CheckBoxPreference silentPreference;

  private void configureDependentPreferences(boolean paramBoolean)
  {
    this.mobileConnectionPreference.setEnabled(paramBoolean);
    this.mobileConnectionPreference.setSummary(this.mobileConnectionPreference.getEntry());
    if (paramBoolean)
    {
      boolean bool = this.mobileConnectionPreference.getValue().equals(MobileConnectionOption.WIFI_AND_MOBILE_NETWORKS.getEntryValue(this));
      this.roamingPreference.setEnabled(bool);
    }
    while (true)
    {
      this.silentPreference.setEnabled(paramBoolean);
      return;
      this.roamingPreference.setEnabled(false);
    }
  }

  public static boolean silenceNotifications(Context paramContext)
  {
    return PreferenceManager.getDefaultSharedPreferences(paramContext).getBoolean(paramContext.getString(R.string.background_goggle_silent_key), false);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.context = ((UnveilContext)getApplicationContext());
    StateRestorationActivity.restoreState(paramBundle, this.context, this);
    addPreferencesFromResource(R.xml.background_goggles_preferences);
    setContentView(R.layout.goggles_preferences);
    this.backgroundGogglesPreference = ((CheckBoxPreference)findPreference(getString(R.string.background_goggle_key)));
    this.backgroundGogglesChangeListener = new DependencyTogglingBackgroundGogglingListener(null);
    this.backgroundGogglesPreference.setOnPreferenceChangeListener(this.backgroundGogglesChangeListener);
    this.mobileConnectionPreference = ((ListPreference)findPreference(getString(R.string.background_goggle_mobile_connection_key)));
    this.mobileConnectionPreference.setEntries(MobileConnectionOption.getEntries(this));
    this.mobileConnectionPreference.setEntryValues(MobileConnectionOption.getEntryValues(this));
    this.roamingPreference = ((CheckBoxPreference)findPreference(getString(R.string.background_goggle_roaming_key)));
    this.mobileConnectionPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener()
    {
      public boolean onPreferenceChange(Preference paramAnonymousPreference, Object paramAnonymousObject)
      {
        Context localContext = paramAnonymousPreference.getContext();
        if (((String)paramAnonymousObject).equals(BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_ONLY.getEntryValue(localContext)))
        {
          BackgroundGogglesPreferencesActivity.this.mobileConnectionPreference.setSummary(BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_ONLY.getEntry(localContext));
          BackgroundGogglesPreferencesActivity.this.roamingPreference.setEnabled(false);
          return true;
        }
        BackgroundGogglesPreferencesActivity.this.mobileConnectionPreference.setSummary(BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_AND_MOBILE_NETWORKS.getEntry(localContext));
        BackgroundGogglesPreferencesActivity.this.roamingPreference.setEnabled(true);
        return true;
      }
    });
    this.silentPreference = ((CheckBoxPreference)findPreference(getString(R.string.background_goggle_silent_key)));
  }

  protected void onResume()
  {
    super.onResume();
    if (Settings.getBoolean(this, R.string.background_goggle_key))
      setResult(-1);
    configureDependentPreferences(Settings.getBoolean(this, R.string.background_goggle_key));
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.context);
  }

  private final class DependencyTogglingBackgroundGogglingListener
    implements Preference.OnPreferenceChangeListener
  {
    private DependencyTogglingBackgroundGogglingListener()
    {
    }

    public boolean onPreferenceChange(Preference paramPreference, Object paramObject)
    {
      boolean bool = ((Boolean)paramObject).booleanValue();
      BackgroundGogglesPreferencesActivity.this.configureDependentPreferences(bool);
      if (bool)
      {
        PictureRequestService.onEnable(paramPreference.getContext());
        BackgroundGogglesPreferencesActivity.this.setResult(-1);
      }
      while (true)
      {
        return true;
        PictureRequestService.onDisable(paramPreference.getContext());
        BackgroundGogglesPreferencesActivity.this.setResult(0);
      }
    }
  }

  public static enum MobileConnectionOption
  {
    public static final MobileConnectionOption DEFAULT = WIFI_AND_MOBILE_NETWORKS;
    private final int entryId;
    private final int entryValueId;

    static
    {
      WIFI_AND_MOBILE_NETWORKS = new MobileConnectionOption("WIFI_AND_MOBILE_NETWORKS", 1, R.string.wifi_and_mobile_networks_option_title, R.string.wifi_and_mobile_networks_option_value);
      MobileConnectionOption[] arrayOfMobileConnectionOption = new MobileConnectionOption[2];
      arrayOfMobileConnectionOption[0] = WIFI_ONLY;
      arrayOfMobileConnectionOption[1] = WIFI_AND_MOBILE_NETWORKS;
    }

    private MobileConnectionOption(int paramInt1, int paramInt2)
    {
      this.entryId = paramInt1;
      this.entryValueId = paramInt2;
    }

    public static String[] getEntries(Context paramContext)
    {
      MobileConnectionOption[] arrayOfMobileConnectionOption = values();
      String[] arrayOfString = new String[arrayOfMobileConnectionOption.length];
      for (int i = 0; i < arrayOfMobileConnectionOption.length; i++)
        arrayOfString[i] = arrayOfMobileConnectionOption[i].getEntry(paramContext);
      return arrayOfString;
    }

    public static String[] getEntryValues(Context paramContext)
    {
      MobileConnectionOption[] arrayOfMobileConnectionOption = values();
      String[] arrayOfString = new String[arrayOfMobileConnectionOption.length];
      for (int i = 0; i < arrayOfMobileConnectionOption.length; i++)
        arrayOfString[i] = arrayOfMobileConnectionOption[i].getEntryValue(paramContext);
      return arrayOfString;
    }

    public String getEntry(Context paramContext)
    {
      return paramContext.getString(this.entryId);
    }

    public String getEntryValue(Context paramContext)
    {
      return paramContext.getString(this.entryValueId);
    }
  }

  public static final class MobileConnectionSettings
  {
    public final boolean roamingAllowed;
    public final boolean wifiOnly;

    private MobileConnectionSettings(boolean paramBoolean1, boolean paramBoolean2)
    {
      this.wifiOnly = paramBoolean1;
      this.roamingAllowed = paramBoolean2;
    }

    public static MobileConnectionSettings get(Context paramContext)
    {
      SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
      String str = BackgroundGogglesPreferencesActivity.MobileConnectionOption.WIFI_ONLY.getEntryValue(paramContext);
      return new MobileConnectionSettings(localSharedPreferences.getString(paramContext.getString(R.string.background_goggle_mobile_connection_key), BackgroundGogglesPreferencesActivity.MobileConnectionOption.DEFAULT.getEntryValue(paramContext)).equals(str), localSharedPreferences.getBoolean(paramContext.getString(R.string.background_goggle_roaming_key), false));
    }

    public boolean isBackgroundGogglingAllowed(NetworkInfo paramNetworkInfo)
    {
      int i = 1;
      if ((paramNetworkInfo == null) || (!paramNetworkInfo.isConnected()))
        i = 0;
      while ((paramNetworkInfo.getType() == i) || ((!this.wifiOnly) && ((!paramNetworkInfo.isRoaming()) || (this.roamingAllowed))))
        return i;
      return false;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.BackgroundGogglesPreferencesActivity
 * JD-Core Version:    0.6.2
 */