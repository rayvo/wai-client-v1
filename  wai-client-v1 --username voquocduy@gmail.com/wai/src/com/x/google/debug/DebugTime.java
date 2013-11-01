package com.x.google.debug;

public class DebugTime
{
  private long timestamp;

  public DebugTime()
  {
    reset();
  }

  public void reset()
  {
    this.timestamp = System.currentTimeMillis();
  }

  public String toString()
  {
    long l1 = System.currentTimeMillis();
    long l2 = l1 - this.timestamp;
    this.timestamp = l1;
    return Long.toString(l2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.debug.DebugTime
 * JD-Core Version:    0.6.2
 */