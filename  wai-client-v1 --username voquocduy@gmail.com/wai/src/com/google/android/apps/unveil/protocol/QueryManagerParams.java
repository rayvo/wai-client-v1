package com.google.android.apps.unveil.protocol;

import android.content.res.Configuration;
import android.text.TextUtils;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.ExtendedGogglesConfigProtos.ExtendedGogglesConfig;
import com.google.goggles.ExtendedGogglesConfigProtos.ExtendedGogglesConfig.Builder;
import com.google.goggles.GogglesConfigProtos.GogglesConfig;
import com.google.goggles.GogglesConfigProtos.GogglesConfig.Builder;
import java.util.Locale;

public class QueryManagerParams
{
  private static final UnveilLogger logger = new UnveilLogger();
  public boolean canStoreToSearchHistory = true;
  private final Provider<Configuration> configurationProvider;
  public boolean disableOcr = false;
  public boolean disableSimilarImages = false;
  public int maxResults = 3;
  public int maxRetries = 1;
  public boolean pollImmediatelyIfSessionConfigured = true;
  public boolean wantAnnotationResults = true;

  public QueryManagerParams(Provider<Configuration> paramProvider)
  {
    this.configurationProvider = paramProvider;
  }

  public GogglesConfigProtos.GogglesConfig.Builder toGogglesConfig()
  {
    ExtendedGogglesConfigProtos.ExtendedGogglesConfig.Builder localBuilder = ExtendedGogglesConfigProtos.ExtendedGogglesConfig.newBuilder();
    localBuilder.setWantAnnotationResults(this.wantAnnotationResults);
    if (!this.disableSimilarImages);
    for (boolean bool = true; ; bool = false)
    {
      localBuilder.setWantEyeCandyResults(bool);
      GogglesConfigProtos.GogglesConfig.Builder localBuilder1 = GogglesConfigProtos.GogglesConfig.newBuilder();
      localBuilder1.setMaxResults(this.maxResults);
      Configuration localConfiguration = (Configuration)this.configurationProvider.get();
      String str1 = localConfiguration.locale.getLanguage();
      if (!TextUtils.isEmpty(str1))
      {
        logger.d("Setting config language to %s", new Object[] { str1 });
        localBuilder1.setLanguagePref(str1);
      }
      String str2 = localConfiguration.locale.getCountry();
      if (!TextUtils.isEmpty(str2))
        localBuilder1.setCountry(str2);
      localBuilder1.setCanStoreToSearchHistory(this.canStoreToSearchHistory);
      localBuilder1.setExtension(ExtendedGogglesConfigProtos.ExtendedGogglesConfig.extendedGogglesConfig, localBuilder.build());
      return localBuilder1;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryManagerParams
 * JD-Core Version:    0.6.2
 */