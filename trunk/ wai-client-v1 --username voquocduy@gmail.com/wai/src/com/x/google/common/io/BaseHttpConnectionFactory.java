package com.x.google.common.io;

public abstract class BaseHttpConnectionFactory extends BaseConnectionFactory
  implements HttpConnectionFactory
{
  protected static final String NETWORK_AVAILABLE_PREF_NAME = "HttpWorks";

  protected BaseHttpConnectionFactory()
  {
    super("HttpWorks");
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.common.io.BaseHttpConnectionFactory
 * JD-Core Version:    0.6.2
 */