package com.google.android.apps.unveil;

import android.app.Activity;
import android.content.Intent;
import com.google.android.apps.unveil.auth.Authenticator.AuthenticationCallback;
import com.google.android.apps.unveil.auth.Authenticator.ErrorType;
import com.google.android.apps.unveil.env.UnveilLogger;

class ActivityAuthenticationCallback
  implements Authenticator.AuthenticationCallback
{
  private final AuthenticatableActivity activity;
  private final UnveilLogger logger = new UnveilLogger();

  protected ActivityAuthenticationCallback(AuthenticatableActivity paramAuthenticatableActivity)
  {
    this.activity = paramAuthenticatableActivity;
  }

  public void onAuthSuccess()
  {
    this.activity.onAuthSuccess();
  }

  public void onAuthTokenInvalidated()
  {
    this.activity.onAuthTokenInvalidated();
  }

  public void onAuthenticationError(Authenticator.ErrorType paramErrorType)
  {
    switch (1.$SwitchMap$com$google$android$apps$unveil$auth$Authenticator$ErrorType[paramErrorType.ordinal()])
    {
    default:
      this.activity.showToast(R.string.disabling_history);
    case 1:
    case 2:
    }
    while (true)
    {
      this.activity.onAuthFailure();
      return;
      this.activity.showToast(R.string.gls_error);
    }
  }

  public void onMultipleAccounts()
  {
    this.activity.startActivity(new Intent((Activity)this.activity, SHFirstRunActivity.class));
  }

  public void onSignIn(Intent paramIntent)
  {
    this.logger.i("onSignIn", new Object[0]);
    this.activity.startActivityForResult(paramIntent, 256);
  }

  public void onSignInCanceled()
  {
    this.activity.showToast(R.string.disabling_history);
    this.activity.onAuthCanceled();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ActivityAuthenticationCallback
 * JD-Core Version:    0.6.2
 */