package com.google.android.apps.unveil.protocol;

public class LocalTracingCookieFetcher extends TracingCookieFetcher
{
  public static final LocalTracingCookieFetcher NO_OP = new LocalTracingCookieFetcher("");
  private final String cookie;

  public LocalTracingCookieFetcher(String paramString)
  {
    super(null);
    this.cookie = paramString;
  }

  public String getFreshCookie()
  {
    return this.cookie;
  }

  public void replenish()
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.LocalTracingCookieFetcher
 * JD-Core Version:    0.6.2
 */