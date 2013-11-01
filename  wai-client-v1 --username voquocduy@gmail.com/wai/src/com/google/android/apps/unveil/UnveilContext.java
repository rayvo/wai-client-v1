package com.google.android.apps.unveil;

import android.location.Location;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.LatLngEncrypter;
import com.google.android.apps.unveil.network.NetworkInfoProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.sensors.UnveilLocationProvider;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import java.net.URL;
import java.util.Map;
import org.apache.http.client.HttpClient;

public abstract interface UnveilContext
{
  public abstract AuthState getAuthState();

  public abstract Authenticator getAuthenticator();

  public abstract Map<String, String> getCameraParams();

  public abstract String getCameraType();

  public abstract ClickTracker getClickTracker();

  public abstract AbstractConnector getConnector();

  public abstract String getCountry();

  public abstract URL getFrontendUrl();

  public abstract Provider<String> getFullUserAgent();

  public abstract String getGroundtruthDirectory();

  public abstract HttpClient getHttpClient();

  public abstract String getInstallationId();

  public abstract String getLanguage();

  public abstract LatLngEncrypter getLatLngEncrypter();

  public abstract UnveilLocationProvider getLocationProvider();

  public abstract Location getMockLocation();

  public abstract NetworkInfoProvider getNetworkInfoProvider();

  public abstract QueryManager getQueryManager();

  public abstract QueryPipeline getQueryPipeline();

  public abstract SearchHistoryProvider getSearchHistoryProvider();

  public abstract UnveilSensorProvider getSensorProvider();

  public abstract UnveilSettings getSettings();

  public abstract ThumbnailProvider getThumbnailCache();

  public abstract TraceTracker getTraceTracker();

  public abstract boolean getUseGroundtruth();

  public abstract int getVersionCode();

  public abstract Viewport getViewport();

  public abstract boolean isContinuous();

  public abstract boolean isFirstRun();

  public abstract boolean isNetworkAvailable();

  public abstract boolean isSearchHistoryEnabled();

  public abstract boolean isUpgrade();

  public abstract void restoreAuthState(AuthState paramAuthState);

  public abstract void setContinuous(boolean paramBoolean);

  public abstract void setFrontendUrl(URL paramURL);

  public abstract void setMockLocation(double paramDouble1, double paramDouble2);

  public abstract void setPreviousVersion(int paramInt);

  public abstract void setSettings(UnveilSettings paramUnveilSettings);

  public abstract void setUserAgent(String paramString);

  public abstract void setUserWantsHistory(boolean paramBoolean);

  public abstract void setViewport(Viewport paramViewport);

  public abstract boolean userWantsHistory();
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.UnveilContext
 * JD-Core Version:    0.6.2
 */