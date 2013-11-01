package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockActivity;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.auth.Authenticator.AuthenticationCallback;
import com.google.android.apps.unveil.env.UnveilLogger;

public abstract class AuthenticatedActivity extends SherlockActivity
  implements AuthenticatableActivity
{
  public static final int REQUEST_CODE_AUTHTOKEN_INVALIDATED = 257;
  private static final int REQUEST_CODE_BASE = 256;
  public static final int REQUEST_CODE_SIGN_IN = 256;
  UnveilApplication application;
  private final Authenticator.AuthenticationCallback authCallback = new ActivityAuthenticationCallback(this);
  Authenticator authenticator;
  private final UnveilLogger logger = new UnveilLogger();

  public void clearAuthToken()
  {
    this.authenticator.clearAuthToken();
  }

  public void fetchAuthToken()
  {
    this.logger.d("Fetching auth token.", new Object[0]);
    this.authenticator.getCredentials(getCallback());
  }

  public String[] getAccounts()
  {
    return this.authenticator.getAccounts();
  }

  public Authenticator getAuthenticator()
  {
    return this.application.getAuthenticator();
  }

  public final Authenticator.AuthenticationCallback getCallback()
  {
    return this.authCallback;
  }

  public void invalidateAuthToken()
  {
    this.logger.d("Auth token invalidated.", new Object[0]);
    this.authenticator.invalidateAuthToken(getCallback());
  }

  public boolean isInMainThread()
  {
    return Looper.myLooper() == Looper.getMainLooper();
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 256)
    {
      this.authenticator.onSignInResult(getCallback(), paramInt2, paramIntent);
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onAuthCanceled()
  {
  }

  public void onAuthFailure()
  {
  }

  public void onAuthSuccess()
  {
  }

  public void onAuthTokenInvalidated()
  {
    if (this.application.userWantsHistory())
      fetchAuthToken();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    this.authenticator = getAuthenticator();
  }

  public void showToast(int paramInt)
  {
    Toast.makeText(this, getText(paramInt), 0).show();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.AuthenticatedActivity
 * JD-Core Version:    0.6.2
 */