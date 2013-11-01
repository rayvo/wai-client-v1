package com.google.android.googlelogindist;

import android.content.Intent;

public class GoogleLoginServiceConstants
{
  public static final String ACCOUNTS_KEY = "accounts";
  public static final String AUTHTOKEN_KEY = "authtoken";
  public static final String AUTH_ACCOUNT_KEY = "authAccount";
  public static final int ERROR_CODE_GLS_NOT_FOUND = 0;
  public static final int ERROR_CODE_GLS_VERIFICATION_FAILED = 1;
  public static final String ERROR_CODE_KEY = "errorCode";
  public static final int FLAG_GOOGLE_ACCOUNT = 1;
  public static final int FLAG_HOSTED_ACCOUNT = 2;
  public static final int FLAG_YOUTUBE_ACCOUNT = 4;
  public static final String FULLY_QUALIFIED_SERVICE_NAME = "com.google.android.googleapps.GoogleLoginService";
  public static final boolean PREFER_HOSTED = false;
  public static final String REQUEST_EXTRAS = "callerExtras";
  public static final boolean REQUIRE_GOOGLE = true;
  public static final Intent SERVICE_INTENT = new Intent().setClassName("com.google.android.googleapps", "com.google.android.googleapps.GoogleLoginService");
  public static final String SERVICE_NAME = "GoogleLoginService";
  public static final String SERVICE_PACKAGE_NAME = "com.google.android.googleapps";
  public static final String YOUTUBE_USER_KEY = "YouTubeUser";

  static String getErrorCodeMessage(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return "Unknown error";
    case 0:
      return "The Google login service cannot be found.";
    case 1:
    }
    return "The Google login service cannot be verified.";
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.googlelogindist.GoogleLoginServiceConstants
 * JD-Core Version:    0.6.2
 */