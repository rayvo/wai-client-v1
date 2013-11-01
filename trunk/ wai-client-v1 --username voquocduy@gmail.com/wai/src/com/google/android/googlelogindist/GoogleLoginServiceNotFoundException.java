package com.google.android.googlelogindist;

import android.util.AndroidException;

public class GoogleLoginServiceNotFoundException extends AndroidException
{
  private int mErrorCode;

  public GoogleLoginServiceNotFoundException(int paramInt)
  {
    super(GoogleLoginServiceConstants.getErrorCodeMessage(paramInt));
    this.mErrorCode = paramInt;
  }

  int getErrorCode()
  {
    return this.mErrorCode;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.googlelogindist.GoogleLoginServiceNotFoundException
 * JD-Core Version:    0.6.2
 */