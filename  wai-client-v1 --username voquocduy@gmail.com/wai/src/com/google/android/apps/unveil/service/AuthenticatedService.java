package com.google.android.apps.unveil.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.SHFirstRunActivity;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.auth.Authenticator.AuthenticationCallback;
import com.google.android.apps.unveil.auth.Authenticator.ErrorType;
import com.google.android.apps.unveil.env.UnveilLogger;

public abstract class AuthenticatedService extends Service
{
  private static final UnveilLogger logger = new UnveilLogger();
  protected UnveilContext application;
  protected Authenticator authenticator;

  private void checkAuthentication(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.application.getAuthState().getAccount();
      localUnveilLogger.i("Allowing authenticated operation for account %s", arrayOfObject);
      onAuthSuccess(paramIntent, paramInt1, paramInt2);
      return;
    }
    if (this.application.userWantsHistory())
    {
      this.authenticator.getCredentials(makeAuthCallback(paramIntent, paramInt1, paramInt2));
      return;
    }
    onNoAuth(paramIntent, paramInt1, paramInt2);
  }

  private Authenticator.AuthenticationCallback makeAuthCallback()
  {
    return new ServiceAuthenticationCallback(null, 0, 0);
  }

  private Authenticator.AuthenticationCallback makeAuthCallback(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return new ServiceAuthenticationCallback(paramIntent, paramInt1, paramInt2);
  }

  protected Authenticator getAuthenticator()
  {
    return this.application.getAuthenticator();
  }

  abstract int getStartMode(Intent paramIntent, int paramInt1, int paramInt2);

  protected UnveilContext getUnveilContext()
  {
    return (UnveilContext)getApplication();
  }

  protected void invalidateAuthToken()
  {
    this.authenticator.invalidateAuthToken(makeAuthCallback());
  }

  protected abstract void onAuthFailure(Intent paramIntent1, int paramInt1, int paramInt2, Intent paramIntent2);

  protected abstract void onAuthSuccess(Intent paramIntent, int paramInt1, int paramInt2);

  public IBinder onBind(Intent paramIntent)
  {
    return null;
  }

  public void onCreate()
  {
    super.onCreate();
    this.application = getUnveilContext();
    this.authenticator = getAuthenticator();
  }

  protected void onMultipleAccounts(Intent paramIntent1, int paramInt1, int paramInt2, Intent paramIntent2)
  {
    onAuthFailure(paramIntent1, paramInt1, paramInt2, paramIntent2);
  }

  protected abstract void onNoAuth(Intent paramIntent, int paramInt1, int paramInt2);

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    checkAuthentication(paramIntent, paramInt1, paramInt2);
    return getStartMode(paramIntent, paramInt1, paramInt2);
  }

  private class ServiceAuthenticationCallback
    implements Authenticator.AuthenticationCallback
  {
    private final int flags;
    private final Intent intent;
    private final int startId;

    public ServiceAuthenticationCallback(Intent paramInt1, int paramInt2, int arg4)
    {
      this.intent = paramInt1;
      this.flags = paramInt2;
      int i;
      this.startId = i;
    }

    public void onAuthSuccess()
    {
      if (this.intent != null)
        AuthenticatedService.this.onAuthSuccess(this.intent, this.flags, this.startId);
    }

    public void onAuthTokenInvalidated()
    {
      if (Settings.getBoolean(AuthenticatedService.this, R.string.background_goggle_key))
        AuthenticatedService.this.authenticator.getCredentials(this);
    }

    public void onAuthenticationError(Authenticator.ErrorType paramErrorType)
    {
      AuthenticatedService.logger.w("AuthenticatedService filed to authenticate: %s", new Object[] { paramErrorType });
      AuthenticatedService.this.onAuthFailure(this.intent, this.flags, this.startId, null);
    }

    public void onMultipleAccounts()
    {
      AuthenticatedService.this.onMultipleAccounts(this.intent, this.flags, this.startId, new Intent(AuthenticatedService.this, SHFirstRunActivity.class));
    }

    public void onSignIn(Intent paramIntent)
    {
      AuthenticatedService.this.onAuthFailure(this.intent, this.flags, this.startId, paramIntent);
    }

    public void onSignInCanceled()
    {
      AuthenticatedService.this.onAuthFailure(this.intent, this.flags, this.startId, null);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.AuthenticatedService
 * JD-Core Version:    0.6.2
 */