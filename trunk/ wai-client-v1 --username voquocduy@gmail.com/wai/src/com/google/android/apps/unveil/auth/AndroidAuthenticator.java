package com.google.android.apps.unveil.auth;

import java.io.IOException;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

import com.actionbarsherlock.R;
import com.google.android.apps.unveil.env.UnveilLogger;

public class AndroidAuthenticator
  implements Authenticator
{
  public static final String GOOGLE_ACCOUNT = "com.google";
  private static final UnveilLogger logger = new UnveilLogger();
  protected final AccountManagerBridge accountManagerBridge;
  private final AuthState authState;
  private final String historyUserKey;
  private final SharedPreferences preferences;

  public AndroidAuthenticator(Context paramContext, AccountManagerBridge paramAccountManagerBridge, AuthState paramAuthState)
  {
    this.preferences = PreferenceManager.getDefaultSharedPreferences(paramContext);
    this.historyUserKey = paramContext.getString(R.string.history_user_key);
    this.accountManagerBridge = paramAccountManagerBridge;
    this.authState = paramAuthState;
  }

  public AndroidAuthenticator(Context paramContext, AuthState paramAuthState)
  {
    this(paramContext, new AndroidAccountManager(AccountManager.get(paramContext)), paramAuthState);
  }

  private Account getPreferredHistoryAccount()
  {
    String str = this.preferences.getString(this.historyUserKey, null);
    for (Account localAccount : this.accountManagerBridge.getAccounts())
      if (localAccount.name.equals(str))
        return localAccount;
    return null;
  }

  private AccountManagerCallback<Bundle> makeCallback(final Authenticator.AuthenticationCallback paramAuthenticationCallback)
  {
    return new AccountManagerCallback()
    {
      private void handleResult(Bundle paramAnonymousBundle)
      {
        String str1 = paramAnonymousBundle.getString("authAccount");
        String str2 = paramAnonymousBundle.getString("authtoken");
        Intent localIntent = (Intent)paramAnonymousBundle.getParcelable("intent");
        AndroidAuthenticator.logger.d("Handling bundle: %s", new Object[] { paramAnonymousBundle });
        if ((str1 != null) && (str2 != null))
        {
          AndroidAuthenticator.this.authState.setAccount(str1);
          AndroidAuthenticator.this.authState.setAuthToken(AuthToken.AuthTokenType.SID, str2);
          paramAuthenticationCallback.onAuthSuccess();
          return;
        }
        if (localIntent != null)
        {
          localIntent.setFlags(0xEFFFFFFF & localIntent.getFlags());
          paramAuthenticationCallback.onSignIn(localIntent);
          return;
        }
        if (str1 != null)
        {
          AndroidAuthenticator.this.getAuthState().setAccount(str1);
          AndroidAuthenticator.this.getAuthState().clearAuthToken(AuthToken.AuthTokenType.SID);
          AndroidAuthenticator.this.getAuthToken(AndroidAuthenticator.this.mapToSystemAccount(str1), this);
          return;
        }
        paramAuthenticationCallback.onAuthenticationError(Authenticator.ErrorType.UNKNOWN);
      }

      public void run(AccountManagerFuture<Bundle> paramAnonymousAccountManagerFuture)
      {
        try
        {
          handleResult((Bundle)paramAnonymousAccountManagerFuture.getResult());
          return;
        }
        catch (OperationCanceledException localOperationCanceledException)
        {
          AndroidAuthenticator.logger.i("User canceled", new Object[0]);
          paramAuthenticationCallback.onSignInCanceled();
          return;
        }
        catch (AuthenticatorException localAuthenticatorException)
        {
          AndroidAuthenticator.logger.e(localAuthenticatorException, "Authenticator error while fetching auth token.", new Object[0]);
          paramAuthenticationCallback.onAuthenticationError(Authenticator.ErrorType.GOOGLE_LOGIN_SERVICE_AUTHENTICATOR_ERROR);
          return;
        }
        catch (IOException localIOException)
        {
          AndroidAuthenticator.logger.e(localIOException, "Error fetching auth token.", new Object[0]);
          paramAuthenticationCallback.onAuthenticationError(Authenticator.ErrorType.GOOGLE_LOGIN_SERVICE_AUTHENTICATION_SERVER_ERROR);
        }
      }
    };
  }

  private Account mapToSystemAccount(String paramString)
  {
    Account localAccount;
    if (paramString == null)
    {
      localAccount = null;
      return localAccount;
    }
    Account[] arrayOfAccount = this.accountManagerBridge.getAccounts();
    if ((arrayOfAccount != null) && (arrayOfAccount.length > 0))
    {
      int i = arrayOfAccount.length;
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label65;
        localAccount = arrayOfAccount[j];
        if (localAccount.name.equals(paramString))
          break;
      }
    }
    label65: return null;
  }

  AccountManagerFuture<Bundle> addAccount(AccountManagerCallback<Bundle> paramAccountManagerCallback)
  {
    return this.accountManagerBridge.addAccount(paramAccountManagerCallback);
  }

  public void clearAuthToken()
  {
    getAuthState().clearAuthToken(AuthToken.AuthTokenType.SID);
  }

  public String[] getAccounts()
  {
    Account[] arrayOfAccount = this.accountManagerBridge.getAccounts();
    String[] arrayOfString;
    int i;
    if ((arrayOfAccount != null) && (arrayOfAccount.length > 0))
    {
      arrayOfString = new String[arrayOfAccount.length];
      i = 0;
    }
    while (i < arrayOfAccount.length)
    {
      arrayOfString[i] = arrayOfAccount[i].name;
      i++;
      continue;
      arrayOfString = new String[0];
    }
    return arrayOfString;
  }

  public AuthState getAuthState()
  {
    return this.authState;
  }

  void getAuthToken(Account paramAccount, AccountManagerCallback<Bundle> paramAccountManagerCallback)
  {
    this.accountManagerBridge.getAuthToken(paramAccount, true, paramAccountManagerCallback);
  }

  public void getCredentials(Authenticator.AuthenticationCallback paramAuthenticationCallback)
  {
    String[] arrayOfString = getAccounts();
    switch (arrayOfString.length)
    {
    default:
      Account localAccount2 = getPreferredHistoryAccount();
      if (localAccount2 != null)
      {
        getAuthToken(localAccount2, makeCallback(paramAuthenticationCallback));
        return;
      }
      break;
    case 0:
      addAccount(makeCallback(paramAuthenticationCallback));
      return;
    case 1:
      Account localAccount1 = mapToSystemAccount(arrayOfString[0]);
      if (localAccount1 == null)
      {
        addAccount(makeCallback(paramAuthenticationCallback));
        return;
      }
      getAuthToken(localAccount1, makeCallback(paramAuthenticationCallback));
      return;
    }
    paramAuthenticationCallback.onMultipleAccounts();
  }

  public void invalidateAuthToken(Authenticator.AuthenticationCallback paramAuthenticationCallback)
  {
    this.accountManagerBridge.invalidateAuthToken(getAuthState().getAuthToken(AuthToken.AuthTokenType.SID));
    getAuthState().clearAuthToken(AuthToken.AuthTokenType.SID);
    paramAuthenticationCallback.onAuthTokenInvalidated();
  }

  public void onSignInResult(Authenticator.AuthenticationCallback paramAuthenticationCallback, int paramInt, Intent paramIntent)
  {
    if (paramInt == -1)
    {
      if (paramIntent != null)
      {
        String str1 = paramIntent.getStringExtra("authAccount");
        String str2 = paramIntent.getStringExtra("authtoken");
        if ((str1 != null) && (str2 != null))
        {
          getAuthState().setAccount(str1);
          getAuthState().setAuthToken(AuthToken.AuthTokenType.SID, str2);
          paramAuthenticationCallback.onAuthSuccess();
          return;
        }
        getCredentials(paramAuthenticationCallback);
        return;
      }
      getCredentials(paramAuthenticationCallback);
      return;
    }
    paramAuthenticationCallback.onSignInCanceled();
  }

  public void setPreferredHistoryAccount(String paramString)
  {
    SharedPreferences.Editor localEditor = this.preferences.edit();
    localEditor.putString(this.historyUserKey, paramString);
    localEditor.commit();
    this.authState.setAccount(paramString);
  }

  public static abstract interface AccountManagerBridge
  {
    public abstract AccountManagerFuture<Bundle> addAccount(AccountManagerCallback<Bundle> paramAccountManagerCallback);

    public abstract Account[] getAccounts();

    public abstract void getAuthToken(Account paramAccount, boolean paramBoolean, AccountManagerCallback<Bundle> paramAccountManagerCallback);

    public abstract void invalidateAuthToken(String paramString);
  }

  private static class AndroidAccountManager
    implements AndroidAuthenticator.AccountManagerBridge
  {
    private final AccountManager accountManager;

    public AndroidAccountManager(AccountManager paramAccountManager)
    {
      this.accountManager = paramAccountManager;
    }

    public AccountManagerFuture<Bundle> addAccount(AccountManagerCallback<Bundle> paramAccountManagerCallback)
    {
      return this.accountManager.addAccount("com.google", "SID", null, null, null, paramAccountManagerCallback, null);
    }

    public Account[] getAccounts()
    {
      return this.accountManager.getAccountsByType("com.google");
    }

    public void getAuthToken(Account paramAccount, boolean paramBoolean, AccountManagerCallback<Bundle> paramAccountManagerCallback)
    {
      this.accountManager.getAuthToken(paramAccount, "SID", paramBoolean, paramAccountManagerCallback, null);
    }

    public void invalidateAuthToken(String paramString)
    {
      this.accountManager.invalidateAuthToken("com.google", paramString);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.auth.AndroidAuthenticator
 * JD-Core Version:    0.6.2
 */