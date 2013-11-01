package com.google.android.apps.unveil.auth;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

public class AuthToken
  implements Parcelable
{
  public static final Parcelable.Creator<AuthToken> CREATOR = new Parcelable.Creator()
  {
    public AuthToken createFromParcel(Parcel paramAnonymousParcel)
    {
      String str1 = paramAnonymousParcel.readString();
      String str2 = paramAnonymousParcel.readString();
      return new AuthToken(AuthToken.AuthTokenType.valueOf(str1), str2);
    }

    public AuthToken[] newArray(int paramAnonymousInt)
    {
      return new AuthToken[paramAnonymousInt];
    }
  };
  private static final String GPLUS_AUTH_TOKEN_TYPE = "oauth2:https://www.googleapis.com/auth/emeraldsea.stream.read https://www.googleapis.com/auth/emeraldsea.stream.write https://www.googleapis.com/auth/emeraldsea.circles.read https://www.googleapis.com/auth/emeraldsea.circles.write https://www.googleapis.com/auth/picasa https://www.googleapis.com/auth/userinfo.email";
  private static final String SID_AUTH_TOKEN_TYPE = "SID";
  private static final String WEBUPDATES_AUTH_TOKEN_TYPE = "webupdates";
  public final String authTokenValue;
  public final AuthTokenType tokenType;

  public AuthToken(AuthTokenType paramAuthTokenType, String paramString)
  {
    this.tokenType = paramAuthTokenType;
    this.authTokenValue = paramString;
  }

  public int describeContents()
  {
    return 0;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append(" { type=" + this.tokenType);
    localStringBuilder.append(" authTokenValue=" + this.authTokenValue + " }");
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.tokenType.name());
    paramParcel.writeString(this.authTokenValue);
  }

  public static enum AuthTokenType
  {
    private final String authTokenType;

    static
    {
      GPLUS = new AuthTokenType("GPLUS", 1, "oauth2:https://www.googleapis.com/auth/emeraldsea.stream.read https://www.googleapis.com/auth/emeraldsea.stream.write https://www.googleapis.com/auth/emeraldsea.circles.read https://www.googleapis.com/auth/emeraldsea.circles.write https://www.googleapis.com/auth/picasa https://www.googleapis.com/auth/userinfo.email");
      WEBUPDATES = new AuthTokenType("WEBUPDATES", 2, "webupdates");
      AuthTokenType[] arrayOfAuthTokenType = new AuthTokenType[3];
      arrayOfAuthTokenType[0] = SID;
      arrayOfAuthTokenType[1] = GPLUS;
      arrayOfAuthTokenType[2] = WEBUPDATES;
    }

    private AuthTokenType(String paramString)
    {
      this.authTokenType = paramString;
    }

    public String getAuthTokenType()
    {
      return this.authTokenType;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.auth.AuthToken
 * JD-Core Version:    0.6.2
 */