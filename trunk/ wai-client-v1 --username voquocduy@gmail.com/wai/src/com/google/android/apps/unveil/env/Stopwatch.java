package com.google.android.apps.unveil.env;

import android.os.SystemClock;

public class Stopwatch
{
  private boolean isRunning;
  private final String name;
  private long startTime;
  private long totalTime;

  public Stopwatch()
  {
    this.name = null;
  }

  public Stopwatch(String paramString)
  {
    this.name = paramString;
  }

  public long getElapsedMilliseconds()
  {
    if (this.isRunning)
    {
      long l = SystemClock.uptimeMillis();
      this.totalTime += l - this.startTime;
      this.startTime = l;
    }
    return this.totalTime;
  }

  public float getElapsedSeconds()
  {
    return (float)getElapsedMilliseconds() / 1000.0F;
  }

  public boolean isRunning()
  {
    return this.isRunning;
  }

  public void reset()
  {
    this.startTime = SystemClock.uptimeMillis();
    this.totalTime = 0L;
  }

  public void start()
  {
    if (this.isRunning)
      return;
    this.startTime = SystemClock.uptimeMillis();
    this.isRunning = true;
  }

  public void startFromZero()
  {
    this.totalTime = 0L;
    this.startTime = SystemClock.uptimeMillis();
    this.isRunning = true;
  }

  public void stop()
  {
    if (!this.isRunning)
      return;
    long l = SystemClock.uptimeMillis();
    this.totalTime += l - this.startTime;
    this.isRunning = false;
  }

  public String toString()
  {
    if (this.name != null)
      return "[" + this.name + ": " + this.totalTime + "ms]";
    return this.totalTime + "ms";
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Stopwatch
 * JD-Core Version:    0.6.2
 */