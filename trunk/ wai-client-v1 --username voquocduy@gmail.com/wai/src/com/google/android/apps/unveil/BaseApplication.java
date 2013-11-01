package com.google.android.apps.unveil;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.Window;
import android.webkit.CookieSyncManager;
import com.google.android.apps.unveil.auth.AndroidAuthenticator;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.env.ImageSaver;
import com.google.android.apps.unveil.env.InfoProvider;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.StreamLoader;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.UnveilContentProvider.Thumbnails;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.history.SearchHistoryEngine;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.HttpClientConnector;
import com.google.android.apps.unveil.network.HttpClientFactory;
import com.google.android.apps.unveil.network.LatLngEncrypter;
import com.google.android.apps.unveil.network.NetworkInfoProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.android.apps.unveil.protocol.QueryManagerParams;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.protocol.TracingCookieFetcher;
import com.google.android.apps.unveil.sensors.UnveilLocationProvider;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.sensors.proxies.camera.ImageSequenceCamera;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.client.HttpClient;

public abstract class BaseApplication extends Application
  implements UnveilContext
{
  public static final String CAMERA_ARGS_REGEX = "([a-zA-Z0-9]+)(\\[(.*)\\])?";
  protected static Context context;
  private AuthState authState;
  private Authenticator authenticator;
  private Map<String, String> cameraParameters;
  private String cameraType;
  private ClickTracker clickTracker;
  private ConnectivityManager connectivityManager;
  private AbstractConnector connector;
  private Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler;
  private URL frontendUrl;
  private HttpClient httpClient;
  private LatLngEncrypter latLngEncrypter;
  private UnveilLocationProvider locationProvider;
  private final UnveilLogger logger = new UnveilLogger();
  private Location mockLocation;
  private NetworkInfoProvider networkInfoProvider;
  protected SharedPreferences preferences;
  protected QueryManager queryManager;
  protected QueryPipeline queryPipeline;
  private SearchHistoryProvider searchHistoryProvider;
  private UnveilSensorProvider sensorProvider;
  protected UnveilSettings settings;
  private String syntheticUserAgent;
  private ThumbnailProvider thumbnailCache;
  private TraceTracker traceTracker;
  protected int versionCode;
  private Viewport viewport;

  public static final void configureWindowFormat(Window paramWindow)
  {
    if (paramWindow != null)
      paramWindow.setFormat(1);
  }

  private String getBaseUserAgent()
  {
    String str = this.preferences.getString(getString(R.string.user_agent_key), this.syntheticUserAgent);
    if (str == null)
    {
      this.syntheticUserAgent = guessUserAgent();
      str = this.syntheticUserAgent;
    }
    return str;
  }

  private URL getFrontendUrlFromPreferences()
  {
    String str1 = this.preferences.getString(getString(R.string.custom_frontend_domain_key), "");
    if (TextUtils.isEmpty(str1))
      str1 = this.preferences.getString(getString(R.string.frontend_domain_key), "");
    if (!TextUtils.isEmpty(str1))
      try
      {
        if (str1.contains("."));
        String str2;
        for (Object localObject = str1; ; localObject = str2)
        {
          return new URL("http", (String)localObject, "");
          str2 = str1 + ".visualsearch.sandbox.google.com";
        }
      }
      catch (MalformedURLException localMalformedURLException)
      {
        this.logger.e(localMalformedURLException, "Could not parse URL from preferences", new Object[0]);
        return null;
      }
    return null;
  }

  private final String guessUserAgent()
  {
    Locale localLocale = Locale.getDefault();
    StringBuffer localStringBuffer = new StringBuffer();
    String str1 = Build.VERSION.RELEASE;
    if (str1.length() > 0)
    {
      localStringBuffer.append(str1);
      localStringBuffer.append("; ");
      String str2 = localLocale.getLanguage();
      if (str2 == null)
        break label192;
      localStringBuffer.append(str2.toLowerCase());
      String str5 = localLocale.getCountry();
      if (str5 != null)
      {
        localStringBuffer.append("-");
        localStringBuffer.append(str5.toLowerCase());
      }
    }
    while (true)
    {
      if ((Build.VERSION.SDK_INT <= 4) || ("REL".equals(Build.VERSION.CODENAME)))
      {
        String str3 = Build.MODEL;
        if (str3.length() > 0)
        {
          localStringBuffer.append("; ");
          localStringBuffer.append(str3);
        }
      }
      String str4 = Build.ID;
      if (str4.length() > 0)
      {
        localStringBuffer.append(" Build/");
        localStringBuffer.append(str4);
      }
      return String.format(getResources().getText(R.string.web_user_agent).toString(), new Object[] { localStringBuffer });
      localStringBuffer.append("1.0");
      break;
      label192: localStringBuffer.append("en");
    }
  }

  private void initCameraSettings(String paramString)
  {
    Matcher localMatcher = Pattern.compile("([a-zA-Z0-9]+)(\\[(.*)\\])?").matcher(paramString);
    this.logger.v("Trying to match <%s>", new Object[] { paramString });
    if (!localMatcher.matches())
      throw new RuntimeException("Error opening camera proxy. Incorrect class/parameter syntax. cameraProxy string was " + paramString);
    this.cameraType = localMatcher.group(1);
    UnveilLogger localUnveilLogger1 = this.logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = this.cameraType;
    localUnveilLogger1.v("Camera type: %s", arrayOfObject1);
    this.cameraParameters = new HashMap();
    String str = localMatcher.group(3);
    if (TextUtils.isEmpty(str));
    while (true)
    {
      return;
      StringTokenizer localStringTokenizer = new StringTokenizer(str, ",");
      while (localStringTokenizer.hasMoreTokens())
      {
        String[] arrayOfString = localStringTokenizer.nextToken().split("=");
        if (arrayOfString.length == 2)
        {
          UnveilLogger localUnveilLogger2 = this.logger;
          Object[] arrayOfObject2 = new Object[2];
          arrayOfObject2[0] = arrayOfString[0];
          arrayOfObject2[1] = arrayOfString[1];
          localUnveilLogger2.v("Adding parameter '%s' => '%s'", arrayOfObject2);
          this.cameraParameters.put(arrayOfString[0], arrayOfString[1]);
        }
      }
    }
  }

  private HttpClient makeHttpClient()
  {
    return HttpClientFactory.make(this);
  }

  protected UnveilSettings createSettings(SharedPreferences paramSharedPreferences)
  {
    return new UnveilSettings(this, paramSharedPreferences);
  }

  public AuthState getAuthState()
  {
    if (this.authState == null)
      this.authState = new AuthState(getFrontendUrl(), getProdUrl());
    return this.authState;
  }

  public Authenticator getAuthenticator()
  {
    if (this.authenticator == null)
      this.authenticator = new AndroidAuthenticator(this, getAuthState());
    return this.authenticator;
  }

  public Map<String, String> getCameraParams()
  {
    return this.cameraParameters;
  }

  public String getCameraType()
  {
    return this.cameraType;
  }

  public ClickTracker getClickTracker()
  {
    return this.clickTracker;
  }

  public Provider<Configuration> getConfigurationProvider()
  {
    return new Provider()
    {
      public Configuration get()
      {
        return BaseApplication.this.getResources().getConfiguration();
      }
    };
  }

  public AbstractConnector getConnector()
  {
    if (this.connector == null)
      this.connector = new HttpClientConnector(getHttpClient(), (ConnectivityManager)getSystemService("connectivity"), new Provider()
      {
        public URL get()
        {
          return BaseApplication.this.getFrontendUrl();
        }
      }
      , DefaultHttpRequestFactory.newAuthenticatedRequestFactory(this));
    return this.connector;
  }

  public String getCountry()
  {
    return getResources().getConfiguration().locale.getCountry();
  }

  protected abstract String getDefaultFrontendUrlString();

  public URL getFrontendUrl()
  {
    if (this.frontendUrl == null)
    {
      URL localURL = getFrontendUrlFromPreferences();
      if (localURL == null)
        break label54;
      this.frontendUrl = localURL;
    }
    while (true)
    {
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.frontendUrl;
      localUnveilLogger.i("Frontend URL: %s", arrayOfObject);
      return this.frontendUrl;
      try
      {
        label54: this.frontendUrl = new URL(getDefaultFrontendUrlString());
      }
      catch (MalformedURLException localMalformedURLException)
      {
        this.logger.e(localMalformedURLException, "Could not parse URL from resources", new Object[0]);
      }
    }
    return null;
  }

  public Provider<String> getFullUserAgent()
  {
    return new Provider()
    {
      public String get()
      {
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = BaseApplication.this.getBaseUserAgent();
        arrayOfObject[1] = "GoogleGoggles-Android";
        arrayOfObject[2] = InfoProvider.getClientVersion(BaseApplication.this);
        return String.format("%s %s/%s; gzip", arrayOfObject);
      }
    };
  }

  public String getGroundtruthDirectory()
  {
    return (String)this.cameraParameters.get("image_sequence_directory");
  }

  public HttpClient getHttpClient()
  {
    try
    {
      if (this.httpClient == null)
        this.httpClient = makeHttpClient();
      HttpClient localHttpClient = this.httpClient;
      return localHttpClient;
    }
    finally
    {
    }
  }

  public String getInstallationId()
  {
    return this.preferences.getString(getString(R.string.installation_id_key), "");
  }

  public String getLanguage()
  {
    return getResources().getConfiguration().locale.getLanguage();
  }

  public LatLngEncrypter getLatLngEncrypter()
  {
    return this.latLngEncrypter;
  }

  public final UnveilLocationProvider getLocationProvider()
  {
    try
    {
      if (this.locationProvider == null)
        this.locationProvider = new UnveilLocationProvider(this, (LocationManager)getSystemService("location"), this);
      UnveilLocationProvider localUnveilLocationProvider = this.locationProvider;
      return localUnveilLocationProvider;
    }
    finally
    {
    }
  }

  public final Location getMockLocation()
  {
    return this.mockLocation;
  }

  public NetworkInfoProvider getNetworkInfoProvider()
  {
    try
    {
      if (this.networkInfoProvider == null)
        this.networkInfoProvider = new NetworkInfoProvider(this);
      NetworkInfoProvider localNetworkInfoProvider = this.networkInfoProvider;
      return localNetworkInfoProvider;
    }
    finally
    {
    }
  }

  protected String getProdUrl()
  {
    return getString(R.string.prod_cookie_url);
  }

  public final QueryManager getQueryManager()
  {
    try
    {
      if (this.queryManager == null)
      {
        this.logger.d("Creating (SingleShot)QueryManager", new Object[0]);
        this.queryManager = new QueryManager(this, new QueryManagerParams(getConfigurationProvider()), new TracingCookieFetcher(getConnector()));
      }
      QueryManager localQueryManager = this.queryManager;
      return localQueryManager;
    }
    finally
    {
    }
  }

  public QueryPipeline getQueryPipeline()
  {
    if (this.queryPipeline == null)
      this.queryPipeline = new QueryPipeline(this, new ImageSaver(context.getContentResolver()));
    return this.queryPipeline;
  }

  public SearchHistoryProvider getSearchHistoryProvider()
  {
    if (this.searchHistoryProvider == null)
      this.searchHistoryProvider = new SearchHistoryProvider(new SearchHistoryEngine(this));
    return this.searchHistoryProvider;
  }

  public UnveilSensorProvider getSensorProvider()
  {
    try
    {
      if (this.sensorProvider == null)
        this.sensorProvider = new UnveilSensorProvider(this);
      UnveilSensorProvider localUnveilSensorProvider = this.sensorProvider;
      return localUnveilSensorProvider;
    }
    finally
    {
    }
  }

  public UnveilSettings getSettings()
  {
    return this.settings;
  }

  public ThumbnailProvider getThumbnailCache()
  {
    return this.thumbnailCache;
  }

  public TraceTracker getTraceTracker()
  {
    return this.traceTracker;
  }

  public boolean getUseGroundtruth()
  {
    return (this.settings.groundtruth) && (getCameraType().equals(ImageSequenceCamera.class.getSimpleName()));
  }

  public final int getVersionCode()
  {
    return this.versionCode;
  }

  public Viewport getViewport()
  {
    return this.viewport;
  }

  public boolean isNetworkAvailable()
  {
    NetworkInfo localNetworkInfo = this.connectivityManager.getActiveNetworkInfo();
    return (localNetworkInfo != null) && (localNetworkInfo.isConnected());
  }

  public boolean isSearchHistoryEnabled()
  {
    return getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID);
  }

  public void onCreate()
  {
    super.onCreate();
    new AsyncTask()
    {
      protected Void doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        return null;
      }
    };
    this.latLngEncrypter = new LatLngEncrypter(StreamLoader.getByteArrayForStream(getResources().openRawResource(R.raw.pub)));
    this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
    this.traceTracker = new TraceTracker(getNetworkInfoProvider(), new TracingCookieFetcher(getConnector()));
    this.clickTracker = new ClickTracker(this.preferences, getResources(), ClickTracker.newDefaultLogConnector(getConnector()));
    setSettings(createSettings(this.preferences));
    this.viewport = new Viewport(0);
    this.thumbnailCache = ThumbnailProvider.makeDefault(this, getApplicationContext().getContentResolver(), UnveilContentProvider.Thumbnails.getContentUri(this), new Handler());
    CookieSyncManager.createInstance(this);
    this.connectivityManager = ((ConnectivityManager)getSystemService("connectivity"));
    context = getApplicationContext();
  }

  protected void resetInstallationId()
  {
    String str = UUID.randomUUID().toString();
    this.logger.d("Setting installationId to %s", new Object[] { str });
    this.preferences.edit().putString(getString(R.string.installation_id_key), str).commit();
  }

  public void restoreAuthState(AuthState paramAuthState)
  {
    this.authState = paramAuthState;
  }

  public void setFrontendUrl(URL paramURL)
  {
    this.connector = null;
    this.frontendUrl = paramURL;
    getAuthState().setUrls(paramURL, getProdUrl());
  }

  public final void setMockLocation(double paramDouble1, double paramDouble2)
  {
    this.mockLocation = new Location("MockProvider");
    this.mockLocation.setTime(System.currentTimeMillis());
    this.mockLocation.setLatitude(paramDouble1);
    this.mockLocation.setLongitude(paramDouble2);
    this.mockLocation.setAccuracy(1.0F);
  }

  public void setSettings(UnveilSettings paramUnveilSettings)
  {
    if (this.settings != null)
      this.logger.v("Overwriting settings.", new Object[0]);
    this.settings = paramUnveilSettings;
    getAuthState().setUrls(getFrontendUrl(), getProdUrl());
    if ((this.settings.latitude != null) && (this.settings.longitude != null))
      setMockLocation(this.settings.latitude.doubleValue(), this.settings.longitude.doubleValue());
    setPreviousVersion(this.settings.previousVersion);
    initCameraSettings(this.settings.cameraProxy);
  }

  public final void setUserAgent(String paramString)
  {
    if (paramString.compareTo(getBaseUserAgent()) != 0)
    {
      SharedPreferences.Editor localEditor = this.preferences.edit();
      localEditor.putString(getString(R.string.user_agent_key), paramString);
      localEditor.commit();
    }
  }

  public void setUserWantsHistory(boolean paramBoolean)
  {
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    localUnveilLogger.i("Setting userWantsHistory to %b", arrayOfObject);
    Settings.putBoolean(this, R.string.user_wants_history_key, paramBoolean);
    if (!paramBoolean)
      getAuthenticator().clearAuthToken();
    getSearchHistoryProvider().getEngine().invalidateXsrfToken();
  }

  public void setViewport(Viewport paramViewport)
  {
    this.viewport = paramViewport;
  }

  public boolean userWantsHistory()
  {
    boolean bool = this.preferences.getBoolean(getString(R.string.user_wants_history_key), false);
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Boolean.valueOf(bool);
    localUnveilLogger.i("Getting userWantsHistory = %b", arrayOfObject);
    return bool;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.BaseApplication
 * JD-Core Version:    0.6.2
 */