package com.google.android.apps.unveil.protocol;

import java.security.SecureRandom;

public class Session
{
  private static final SecureRandom RANDOM = new SecureRandom();
  private final String sessionId;

  public Session()
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(RANDOM.nextLong());
    this.sessionId = String.format("%016X", arrayOfObject);
  }

  protected String getSessionId()
  {
    return this.sessionId;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Session [sessionId=").append(this.sessionId).append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.Session
 * JD-Core Version:    0.6.2
 */