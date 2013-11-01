package com.google.android.apps.unveil;

import com.google.android.apps.unveil.env.Size;
import java.util.Arrays;
import java.util.List;

public class ConstantConfiguration
{
  public static final boolean ALLOW_ADAPTIVE_JPEG_QUALITY = false;
  public static final boolean ALLOW_PUGGLE_MODE = false;
  public static final List<String> CLIENT_LANGUAGES = Arrays.asList(new String[] { "en", "cs", "de", "es", "fr", "it", "nl", "pl", "pt", "ru", "tr" });
  public static final int CONNECTION_TIMEOUT_MILLIS = 20000;
  public static final String DOMAIN_SUFFIX = ".visualsearch.sandbox.google.com";
  public static final String GOGGLES_URL_PATH = "/goggles/";
  public static final boolean HIGH_FREQUENCY_LOGGING = false;
  public static final boolean IS_RELEASE_BUILD = true;
  public static final boolean LOG_TONES = false;
  public static final int MAX_ANNOTATION_RESULTS = 3;
  public static final int MAX_CONTINUOUS_PREVIEW_PIXELS = 408960;
  public static final int MAX_PICTURE_PIXELS = 408960;
  public static final int MAX_SYMPHO_BYTES = 1048576;
  public static final int MIN_REQUIRED_HEAP_SIZE = 12;
  public static final Size NOTIFICATION_THUMBNAIL_MAX_SIZE_DP = new Size(80, 80);
  public static final int NUM_PUGGLE_RESULTS = 100;
  public static final List<String> PUGGLE_LANGUAGES = Arrays.asList(new String[] { "en" });
  public static final int SOCKET_BUFFER_SIZE_BYTES = 8192;
  public static final int SOCKET_TIMEOUT_MILLIS = 30000;
  public static final boolean TONE_ON_ERRORS = false;
  public static final boolean USE_MARKET_LIBRARIES = true;
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ConstantConfiguration
 * JD-Core Version:    0.6.2
 */