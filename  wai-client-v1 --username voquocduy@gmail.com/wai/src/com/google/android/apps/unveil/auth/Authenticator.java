package com.google.android.apps.unveil.auth;

import android.content.Intent;

public abstract interface Authenticator
{
  public static final String GOOGLE_SESSION_COOKIE = "SID";

  public abstract void clearAuthToken();

  public abstract String[] getAccounts();

  public abstract AuthState getAuthState();

  public abstract void getCredentials(AuthenticationCallback paramAuthenticationCallback);

  public abstract void invalidateAuthToken(AuthenticationCallback paramAuthenticationCallback);

  public abstract void onSignInResult(AuthenticationCallback paramAuthenticationCallback, int paramInt, Intent paramIntent);

  public abstract void setPreferredHistoryAccount(String paramString);

  public static abstract interface AuthenticationCallback
  {
    public abstract void onAuthSuccess();

    public abstract void onAuthTokenInvalidated();

    public abstract void onAuthenticationError(Authenticator.ErrorType paramErrorType);

    public abstract void onMultipleAccounts();

    public abstract void onSignIn(Intent paramIntent);

    public abstract void onSignInCanceled();
  }

  public static enum ErrorType
  {
    static
    {
      GOOGLE_LOGIN_SERVICE_AUTHENTICATOR_ERROR = new ErrorType("GOOGLE_LOGIN_SERVICE_AUTHENTICATOR_ERROR", 1);
      GOOGLE_LOGIN_SERVICE_AUTHENTICATION_SERVER_ERROR = new ErrorType("GOOGLE_LOGIN_SERVICE_AUTHENTICATION_SERVER_ERROR", 2);
      ErrorType[] arrayOfErrorType = new ErrorType[3];
      arrayOfErrorType[0] = UNKNOWN;
      arrayOfErrorType[1] = GOOGLE_LOGIN_SERVICE_AUTHENTICATOR_ERROR;
      arrayOfErrorType[2] = GOOGLE_LOGIN_SERVICE_AUTHENTICATION_SERVER_ERROR;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.auth.Authenticator
 * JD-Core Version:    0.6.2
 */