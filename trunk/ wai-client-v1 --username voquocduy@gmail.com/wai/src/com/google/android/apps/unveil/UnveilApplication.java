package com.google.android.apps.unveil;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilContentProvider.Queries;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.DatabaseBackedSavedQueryProvider;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.service.PictureRequestService;

public class UnveilApplication extends BaseApplication
  implements UnveilContext
{
  private final UnveilLogger logger = new UnveilLogger();
  private SavedQueryProvider pendingQueryProvider;
  private int previousVersion = 0;

  private final void loadVersionCodes()
  {
    try
    {
      this.versionCode = getPackageManager().getPackageInfo(getApplicationInfo().packageName, 0).versionCode;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      try
      {
        while (true)
        {
          this.previousVersion = Integer.parseInt(this.preferences.getString(getString(R.string.previous_version_key), "0"));
          return;
          localNameNotFoundException = localNameNotFoundException;
          this.logger.e("Unable to retrieve version code from manifest", new Object[0]);
        }
      }
      catch (NumberFormatException localNumberFormatException)
      {
        this.logger.e("Invalid previous_version value in preferences", new Object[0]);
      }
    }
  }

  public static final Intent makeCaptureActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, CaptureActivity.class).addFlags(67108864);
  }

  public static final Intent makeContinuousActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, ContinuousActivity.class).addFlags(67108864);
  }

  public static final Intent makeSearchHistoryActivityIntent(Context paramContext)
  {
    return new Intent(paramContext, SearchHistoryActivity.class).addFlags(67108864);
  }

  protected UnveilSettings createSettings(SharedPreferences paramSharedPreferences)
  {
    return new UnveilSettings(this, paramSharedPreferences);
  }

  protected String getDefaultFrontendUrlString()
  {
    return getString(R.string.frontend_url);
  }

  public SavedQueryProvider getSavedQueryProvider()
  {
    if (this.pendingQueryProvider == null)
      this.pendingQueryProvider = new DatabaseBackedSavedQueryProvider(getApplicationContext().getContentResolver().acquireContentProviderClient(UnveilContentProvider.Queries.getContentUri(this)), this);
    return this.pendingQueryProvider;
  }

  public boolean isContinuous()
  {
    return this.preferences.getBoolean(getString(R.string.last_choice_is_continuous_mode_key), false);
  }

  public final boolean isFirstRun()
  {
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(this.previousVersion);
    localUnveilLogger.v("shouldShowTutorialActivity? previousVersion is %d", arrayOfObject);
    return this.previousVersion == 0;
  }

  public boolean isUpgrade()
  {
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.previousVersion);
    arrayOfObject[1] = Integer.valueOf(this.versionCode);
    localUnveilLogger.v("shouldShowUpgradeChangelog? previousVersion is %d  and versionCode is %d", arrayOfObject);
    return (this.previousVersion != 0) && (this.previousVersion < this.versionCode);
  }

  public void onCreate()
  {
    super.onCreate();
    loadVersionCodes();
    if (Settings.getBoolean(this, R.string.background_goggle_key))
      startService(PictureRequestService.makePollIntent(this));
    if (TextUtils.isEmpty(getInstallationId()))
      resetInstallationId();
  }

  void overrideQueryManager(QueryManager paramQueryManager)
  {
    this.queryManager = paramQueryManager;
  }

  void overrideQueryPipeline(QueryPipeline paramQueryPipeline)
  {
    this.queryPipeline = paramQueryPipeline;
  }

  public void setContinuous(boolean paramBoolean)
  {
    this.preferences.edit().putBoolean(getString(R.string.last_choice_is_continuous_mode_key), paramBoolean).commit();
  }

  public final void setPreviousVersion(int paramInt)
  {
    this.previousVersion = paramInt;
    String str1 = this.preferences.getString(getString(R.string.previous_version_key), "0");
    String str2 = String.valueOf(paramInt);
    if (!str1.equals(str2))
      getClickTracker().logInstall(str1, str2);
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.putString(getString(R.string.previous_version_key), Integer.toString(paramInt));
    localEditor.commit();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.UnveilApplication
 * JD-Core Version:    0.6.2
 */