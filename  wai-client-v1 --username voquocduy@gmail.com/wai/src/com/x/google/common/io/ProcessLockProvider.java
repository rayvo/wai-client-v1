package com.x.google.common.io;

public abstract interface ProcessLockProvider
{
  public abstract ProcessLock lock(String paramString);

  public abstract ProcessLock tryLock(String paramString);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.common.io.ProcessLockProvider
 * JD-Core Version:    0.6.2
 */