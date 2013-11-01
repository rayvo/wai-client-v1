package com.google.android.apps.unveil;

import android.content.Intent;
import com.google.android.apps.unveil.auth.Authenticator;
import com.google.android.apps.unveil.auth.Authenticator.AuthenticationCallback;

abstract interface AuthenticatableActivity
{
  public abstract void clearAuthToken();

  public abstract void fetchAuthToken();

  public abstract Authenticator getAuthenticator();

  public abstract Authenticator.AuthenticationCallback getCallback();

  public abstract void invalidateAuthToken();

  public abstract void onAuthCanceled();

  public abstract void onAuthFailure();

  public abstract void onAuthSuccess();

  public abstract void onAuthTokenInvalidated();

  public abstract void showToast(int paramInt);

  public abstract void startActivity(Intent paramIntent);

  public abstract void startActivityForResult(Intent paramIntent, int paramInt);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.AuthenticatableActivity
 * JD-Core Version:    0.6.2
 */