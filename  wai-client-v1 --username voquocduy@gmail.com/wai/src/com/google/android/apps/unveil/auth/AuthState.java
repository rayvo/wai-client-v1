package com.google.android.apps.unveil.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.webkit.CookieManager;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AuthState
  implements Parcelable
{
  public static final Parcelable.Creator<AuthState> CREATOR = new ParcelCreator(null);
  protected String account;
  protected String authCookieUrl;
  protected long lastModifiedTimestamp = System.currentTimeMillis();
  private final UnveilLogger logger = new UnveilLogger();
  protected String prodCookieUrl;
  protected Map<AuthToken.AuthTokenType, AuthToken> tokenCache = new HashMap();

  public AuthState()
  {
    this.authCookieUrl = "";
    this.prodCookieUrl = "";
  }

  public AuthState(URL paramURL, String paramString)
  {
    setUrls(paramURL, paramString);
  }

  private void setAuthCookie(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return;
      str = "SID=" + paramString;
      CookieManager.getInstance().setCookie(this.authCookieUrl, str);
    }
    while (this.authCookieUrl.equals(this.prodCookieUrl));
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.prodCookieUrl;
    localUnveilLogger.d("Setting a second cookie for %s", arrayOfObject);
    CookieManager.getInstance().setCookie(this.prodCookieUrl, str);
  }

  private void updateTimestamp()
  {
    this.lastModifiedTimestamp = System.currentTimeMillis();
  }

  public void clearAuthToken(AuthToken.AuthTokenType paramAuthTokenType)
  {
    this.tokenCache.remove(paramAuthTokenType);
    if (paramAuthTokenType == AuthToken.AuthTokenType.SID)
      CookieManager.getInstance().removeAllCookie();
  }

  public int describeContents()
  {
    return 0;
  }

  public String getAccount()
  {
    return this.account;
  }

  public String getAuthToken(AuthToken.AuthTokenType paramAuthTokenType)
  {
    AuthToken localAuthToken = (AuthToken)this.tokenCache.get(paramAuthTokenType);
    if (localAuthToken == null)
      return null;
    return localAuthToken.authTokenValue;
  }

  public long getLastUpdateTimestamp()
  {
    return this.lastModifiedTimestamp;
  }

  public boolean isAuthenticated(AuthToken.AuthTokenType paramAuthTokenType)
  {
    return this.tokenCache.containsKey(paramAuthTokenType);
  }

  public boolean isOlderThan(AuthState paramAuthState)
  {
    return this.lastModifiedTimestamp < paramAuthState.lastModifiedTimestamp;
  }

  public void setAccount(String paramString)
  {
    this.account = paramString;
    updateTimestamp();
  }

  public void setAuthToken(AuthToken.AuthTokenType paramAuthTokenType, String paramString)
  {
    this.tokenCache.put(paramAuthTokenType, new AuthToken(paramAuthTokenType, paramString));
    if (paramAuthTokenType == AuthToken.AuthTokenType.SID)
      setAuthCookie(paramString);
    updateTimestamp();
  }

  public void setUrls(URL paramURL, String paramString)
  {
    this.prodCookieUrl = paramString;
    Object localObject = paramString;
    try
    {
      String str = new URL(paramURL.getProtocol(), paramURL.getHost(), paramURL.getPort(), "").toString();
      localObject = str;
      label36: this.authCookieUrl = ((String)localObject);
      return;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      break label36;
    }
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.authCookieUrl);
    paramParcel.writeString(this.prodCookieUrl);
    paramParcel.writeString(this.account);
    paramParcel.writeLong(this.lastModifiedTimestamp);
    paramParcel.writeTypedList(new ArrayList(this.tokenCache.values()));
  }

  private static class ParcelCreator
    implements Parcelable.Creator<AuthState>
  {
    public AuthState createFromParcel(Parcel paramParcel)
    {
      AuthState localAuthState = new AuthState();
      localAuthState.authCookieUrl = paramParcel.readString();
      localAuthState.prodCookieUrl = paramParcel.readString();
      localAuthState.account = paramParcel.readString();
      localAuthState.lastModifiedTimestamp = paramParcel.readLong();
      ArrayList localArrayList = new ArrayList();
      paramParcel.readTypedList(localArrayList, AuthToken.CREATOR);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        AuthToken localAuthToken = (AuthToken)localIterator.next();
        localAuthState.tokenCache.put(localAuthToken.tokenType, localAuthToken);
      }
      return localAuthState;
    }

    public AuthState[] newArray(int paramInt)
    {
      return new AuthState[paramInt];
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.auth.AuthState
 * JD-Core Version:    0.6.2
 */