package com.google.android.apps.unveil.protocol;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.res.Resources;
import android.util.Pair;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ClickTrackingPreferenceChangeListener
  implements SharedPreferences.OnSharedPreferenceChangeListener
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Set<String> booleanPreferences;
  private final ClickTracker clickTracker;
  private final String mobileConnectionPreferenceKey;
  private final Map<Pair<String, String>, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET> modificationToClickMap;
  private final String wifiAndMobileNetworksValue;
  private final String wifiOnlyValue;

  public ClickTrackingPreferenceChangeListener(ClickTracker paramClickTracker, Resources paramResources)
  {
    this.clickTracker = paramClickTracker;
    this.mobileConnectionPreferenceKey = paramResources.getString(R.string.background_goggle_mobile_connection_key);
    this.wifiOnlyValue = paramResources.getString(R.string.wifi_only_option_value);
    this.wifiAndMobileNetworksValue = paramResources.getString(R.string.wifi_and_mobile_networks_option_value);
    this.booleanPreferences = generateBooleanPreferencesSet(paramResources);
    this.modificationToClickMap = generateMap(paramResources);
  }

  private NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET getClickTarget(String paramString1, String paramString2)
  {
    return (NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET)this.modificationToClickMap.get(new Pair(paramString1, paramString2));
  }

  private static final Pair<String, String> p(String paramString1, String paramString2)
  {
    return new Pair(paramString1, paramString2);
  }

  private static final Pair<String, String> p(String paramString, boolean paramBoolean)
  {
    return p(paramString, String.valueOf(paramBoolean));
  }

  Set<String> generateBooleanPreferencesSet(Resources paramResources)
  {
    HashSet localHashSet = new HashSet();
    localHashSet.add(paramResources.getString(R.string.background_goggle_key));
    localHashSet.add(paramResources.getString(R.string.user_wants_history_key));
    localHashSet.add(paramResources.getString(R.string.save_to_sd_card_key));
    localHashSet.add(paramResources.getString(R.string.background_goggle_roaming_key));
    return Collections.unmodifiableSet(localHashSet);
  }

  Map<Pair<String, String>, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET> generateMap(Resources paramResources)
  {
    HashMap localHashMap = new HashMap();
    String str1 = paramResources.getString(R.string.background_goggle_key);
    localHashMap.put(p(str1, true), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_ENABLED);
    localHashMap.put(p(str1, false), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_DISABLED);
    String str2 = paramResources.getString(R.string.user_wants_history_key);
    localHashMap.put(p(str2, true), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_ENABLED);
    localHashMap.put(p(str2, false), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_HISTORY_DISABLED);
    String str3 = paramResources.getString(R.string.save_to_sd_card_key);
    localHashMap.put(p(str3, true), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SAVE_TO_GALLERY_ENABLED);
    localHashMap.put(p(str3, false), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SAVE_TO_GALLERY_DISABLED);
    String str4 = paramResources.getString(R.string.background_goggle_roaming_key);
    localHashMap.put(p(str4, true), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_ROAMING_ENABLED);
    localHashMap.put(p(str4, false), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_ROAMING_DISABLED);
    localHashMap.put(p(this.mobileConnectionPreferenceKey, this.wifiAndMobileNetworksValue), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_MOBILE_NETWORKS_ENABLED);
    localHashMap.put(p(this.mobileConnectionPreferenceKey, this.wifiOnlyValue), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_MOBILE_NETWORKS_DISABLED);
    return Collections.unmodifiableMap(localHashMap);
  }

  public void onSharedPreferenceChanged(SharedPreferences paramSharedPreferences, String paramString)
  {
    if (this.booleanPreferences.contains(paramString))
      this.clickTracker.logClick(getClickTarget(paramString, String.valueOf(paramSharedPreferences.getBoolean(paramString, false))));
    while (!paramString.equals(this.mobileConnectionPreferenceKey))
      return;
    String str = paramSharedPreferences.getString(this.mobileConnectionPreferenceKey, this.wifiAndMobileNetworksValue);
    this.clickTracker.logClick(getClickTarget(paramString, str));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.ClickTrackingPreferenceChangeListener
 * JD-Core Version:    0.6.2
 */