package com.x.google.common.lang;

public abstract interface ThreadFactory
{
  public abstract Thread createThread(String paramString, Runnable paramRunnable);

  public abstract void debug();

  public abstract int getCreatedCount();

  public abstract int getRunningCount();
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.common.lang.ThreadFactory
 * JD-Core Version:    0.6.2
 */