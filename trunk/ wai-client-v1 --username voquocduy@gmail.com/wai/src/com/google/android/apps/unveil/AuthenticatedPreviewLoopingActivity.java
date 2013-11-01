package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.auth.Authenticator.AuthenticationCallback;
import com.google.android.apps.unveil.env.UnveilLogger;

public abstract class AuthenticatedPreviewLoopingActivity extends GogglesPreviewLoopingActivity
  implements AuthenticatableActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Authenticator.AuthenticationCallback authCallback = new ActivityAuthenticationCallback(this);
  protected Authenticator authenticator;

  public void clearAuthToken()
  {
    this.authenticator.clearAuthToken();
  }

  public void fetchAuthToken()
  {
    this.authenticator.getCredentials(getCallback());
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
    logger.d("Auth token invalidated.", new Object[0]);
    this.authenticator.invalidateAuthToken(getCallback());
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
    onAuthFailure();
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
    this.authenticator = this.application.getAuthenticator();
  }

  public void showToast(int paramInt)
  {
    Toast.makeText(this, getText(paramInt), 0).show();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.AuthenticatedPreviewLoopingActivity
 * JD-Core Version:    0.6.2
 */