package com.x.google.common;

public class GenericClock
  implements Clock
{
  public long currentTimeMillis()
  {
    return System.currentTimeMillis();
  }

  public long relativeTimeMillis()
  {
    return System.currentTimeMillis();
  }

  public long uptimeMillis()
  {
    return -9223372036854775808L;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.common.GenericClock
 * JD-Core Version:    0.6.2
 */