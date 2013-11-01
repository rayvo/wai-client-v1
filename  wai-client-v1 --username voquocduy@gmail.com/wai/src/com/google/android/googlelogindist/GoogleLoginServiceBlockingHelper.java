package com.google.android.googlelogindist;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.googleapps.GoogleLoginCredentialsResult;
import com.google.android.googleapps.IGoogleLoginService;
import com.google.android.googleapps.IGoogleLoginService.Stub;
import com.google.android.googleapps.LoginData;
import com.google.android.googleapps.LoginData.Status;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class GoogleLoginServiceBlockingHelper
{
  private static final String TAG = "GoogleLoginServiceBlockingHelper";
  private final double mBackoffFactor = 2.0D;
  private Condition mBindWaitCondition = this.mGoogleLoginServiceLock.newCondition();
  private final Context mContext;
  private int mDelay = this.mMinDelaySecs;
  private boolean mGlsVerified;
  private volatile IGoogleLoginService mGoogleLoginService = null;
  private Lock mGoogleLoginServiceLock = new ReentrantLock();
  private final int mMaxDelaySecs = 300;
  private final int mMinDelaySecs = 5;
  private ServiceConnection mServiceConnection;
  private Thread mServiceThread = null;

  public GoogleLoginServiceBlockingHelper(Context paramContext)
    throws GoogleLoginServiceNotFoundException
  {
    this.mContext = paramContext;
    if (!GoogleAppsVerifier.isServiceAvailable(paramContext, "com.google.android.googleapps.GoogleLoginService"))
      throw new GoogleLoginServiceNotFoundException(0);
    this.mGoogleLoginServiceLock.lock();
    try
    {
      this.mServiceConnection = new ServiceConnection()
      {
        public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
        {
          try
          {
            GoogleLoginServiceBlockingHelper.this.mGoogleLoginServiceLock.lock();
            GoogleLoginServiceBlockingHelper.access$102(GoogleLoginServiceBlockingHelper.this, GoogleAppsVerifier.isGoogleAppsVerified(GoogleLoginServiceBlockingHelper.this.mContext));
            GoogleLoginServiceBlockingHelper.access$302(GoogleLoginServiceBlockingHelper.this, Thread.currentThread());
            GoogleLoginServiceBlockingHelper.access$402(GoogleLoginServiceBlockingHelper.this, IGoogleLoginService.Stub.asInterface(paramAnonymousIBinder));
            GoogleLoginServiceBlockingHelper.this.mBindWaitCondition.signalAll();
            return;
          }
          finally
          {
            GoogleLoginServiceBlockingHelper.this.mGoogleLoginServiceLock.unlock();
          }
        }

        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
          GoogleLoginServiceBlockingHelper.this.mGoogleLoginServiceLock.lock();
          GoogleLoginServiceBlockingHelper.access$402(GoogleLoginServiceBlockingHelper.this, null);
          GoogleLoginServiceBlockingHelper.this.mGoogleLoginServiceLock.unlock();
        }
      };
      if (!this.mContext.bindService(GoogleLoginServiceConstants.SERVICE_INTENT, this.mServiceConnection, 1))
        throw new GoogleLoginServiceNotFoundException(0);
    }
    finally
    {
      this.mGoogleLoginServiceLock.unlock();
    }
    this.mGoogleLoginServiceLock.unlock();
  }

  private void checkGoogleLoginServiceVerificationLocked()
    throws GoogleLoginServiceNotFoundException
  {
    if ((this.mGoogleLoginService != null) && (!this.mGlsVerified))
      throw new GoogleLoginServiceNotFoundException(1);
  }

  private void delay()
  {
    try
    {
      Thread.sleep(1000L * this.mDelay);
      label12: this.mDelay = ((int)(this.mDelay * this.mBackoffFactor));
      if (this.mDelay > this.mMaxDelaySecs)
        this.mDelay = this.mMaxDelaySecs;
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      break label12;
    }
  }

  public static String getAccount(Context paramContext, boolean paramBoolean)
    throws GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      String str = localGoogleLoginServiceBlockingHelper.getAccount(paramBoolean);
      return str;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  public static String[] getAccounts(Context paramContext)
    throws GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      String[] arrayOfString = localGoogleLoginServiceBlockingHelper.getAccounts();
      return arrayOfString;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  public static long getAndroidId(Context paramContext)
    throws GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      long l = localGoogleLoginServiceBlockingHelper.getAndroidId();
      return l;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  public static String getAuthToken(Context paramContext, String paramString1, String paramString2)
    throws GoogleLoginServiceBlockingHelper.AuthenticationException, GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      String str = localGoogleLoginServiceBlockingHelper.getAuthToken(paramString1, paramString2);
      return str;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  public static String getOneAuthToken(Context paramContext, String paramString1, String paramString2)
    throws GoogleLoginServiceBlockingHelper.AuthenticationException, GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      String str = localGoogleLoginServiceBlockingHelper.getAuthToken(paramString1, paramString2);
      return str;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  public static void invalidateAuthToken(Context paramContext, String paramString)
    throws GoogleLoginServiceNotFoundException
  {
    GoogleLoginServiceBlockingHelper localGoogleLoginServiceBlockingHelper = new GoogleLoginServiceBlockingHelper(paramContext);
    try
    {
      localGoogleLoginServiceBlockingHelper.invalidateAuthToken(paramString);
      return;
    }
    finally
    {
      localGoogleLoginServiceBlockingHelper.close();
    }
  }

  private void resetDelay()
  {
    this.mDelay = this.mMinDelaySecs;
  }

  public void close()
  {
    this.mGoogleLoginServiceLock.lock();
    try
    {
      if (this.mServiceConnection != null)
      {
        this.mContext.unbindService(this.mServiceConnection);
        this.mServiceConnection = null;
        this.mGoogleLoginService = null;
      }
      return;
    }
    finally
    {
      this.mGoogleLoginServiceLock.unlock();
    }
  }

  public String getAccount(boolean paramBoolean)
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        String str = localIGoogleLoginService.getAccount(paramBoolean);
        return str;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public String[] getAccounts()
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        String[] arrayOfString = localIGoogleLoginService.getAccounts();
        return arrayOfString;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public long getAndroidId()
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        long l = localIGoogleLoginService.getAndroidId();
        return l;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public String getAuthToken(String paramString1, String paramString2)
    throws GoogleLoginServiceBlockingHelper.AuthenticationException, GoogleLoginServiceNotFoundException
  {
    resetDelay();
    GoogleLoginCredentialsResult localGoogleLoginCredentialsResult = getCredentials(paramString1, paramString2, true);
    if (localGoogleLoginCredentialsResult.getCredentialsString() == null)
    {
      Intent localIntent = localGoogleLoginCredentialsResult.getCredentialsIntent();
      LoginData.Status localStatus = null;
      if (localIntent != null)
      {
        LoginData localLoginData = (LoginData)localIntent.getParcelableExtra("loginData");
        localStatus = null;
        if (localLoginData != null)
          localStatus = localLoginData.mStatus;
      }
      throw new AuthenticationException("unable to find auth token for account", localStatus);
    }
    return localGoogleLoginCredentialsResult.getCredentialsString();
  }

  public GoogleLoginCredentialsResult getCredentials(String paramString1, String paramString2, boolean paramBoolean)
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        GoogleLoginCredentialsResult localGoogleLoginCredentialsResult = localIGoogleLoginService.blockingGetCredentials(paramString1, paramString2, paramBoolean);
        return localGoogleLoginCredentialsResult;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public IGoogleLoginService getLoginService()
    throws GoogleLoginServiceNotFoundException
  {
    try
    {
      this.mGoogleLoginServiceLock.lock();
      if ((this.mServiceThread != null) && (Thread.currentThread() == this.mServiceThread))
        throw new IllegalStateException("calling GoogleLoginServiceBlockingHelper methods from your main thread can lead to deadlock");
    }
    finally
    {
      this.mGoogleLoginServiceLock.unlock();
    }
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService1 = this.mGoogleLoginService;
      if (localIGoogleLoginService1 != null)
        break;
      try
      {
        this.mBindWaitCondition.await();
      }
      catch (InterruptedException localInterruptedException)
      {
      }
    }
    checkGoogleLoginServiceVerificationLocked();
    IGoogleLoginService localIGoogleLoginService2 = this.mGoogleLoginService;
    this.mGoogleLoginServiceLock.unlock();
    return localIGoogleLoginService2;
  }

  public void invalidateAuthToken(String paramString)
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        localIGoogleLoginService.invalidateAuthToken(paramString);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public String peekCredentials(String paramString1, String paramString2)
    throws GoogleLoginServiceNotFoundException
  {
    resetDelay();
    while (true)
    {
      IGoogleLoginService localIGoogleLoginService = getLoginService();
      try
      {
        String str = localIGoogleLoginService.peekCredentials(paramString1, paramString2);
        return str;
      }
      catch (RemoteException localRemoteException)
      {
        delay();
      }
    }
  }

  public class AuthenticationException extends Exception
  {
    private LoginData.Status mStatus;

    public AuthenticationException()
    {
    }

    public AuthenticationException(LoginData.Status arg2)
    {
      Object localObject;
      this.mStatus = localObject;
    }

    public AuthenticationException(String arg2)
    {
      super();
    }

    public AuthenticationException(String paramStatus, LoginData.Status arg3)
    {
      super();
      Object localObject;
      this.mStatus = localObject;
    }

    public AuthenticationException(String paramThrowable, Throwable arg3)
    {
      super(localThrowable);
    }

    public AuthenticationException(Throwable arg2)
    {
      super();
    }

    public LoginData.Status getStatus()
    {
      return this.mStatus;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.googlelogindist.GoogleLoginServiceBlockingHelper
 * JD-Core Version:    0.6.2
 */