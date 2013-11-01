package com.google.android.apps.unveil.env;

import java.util.ArrayList;

public class BandwidthEstimator
{
  private static final float BYTES_PER_KB = 1024.0F;
  private static final int UNSENT = -1;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Statistic bytesPerSecond = new Statistic();
  private long requestOutTime;
  private int requestSize;

  public BandwidthEstimator()
  {
    reset();
  }

  public ArrayList<String> getDebugText()
  {
    try
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add("Average throughput: " + this.bytesPerSecond.getMovingAverage() / 1024.0F + "kBps");
      localArrayList.add("Throughput stdDev: " + this.bytesPerSecond.getStandardDeviation() / 1024.0F + "kBps");
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public float getThroughputBps()
  {
    try
    {
      float f = this.bytesPerSecond.getMovingAverage();
      return f;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void requestIn(long paramLong)
  {
    try
    {
      if (this.requestOutTime == -1L)
        logger.w("onResponse() without onSending()!", new Object[0]);
      float f1 = (float)(paramLong - this.requestOutTime) / 1000.0F;
      float f2 = this.requestSize / f1;
      this.bytesPerSecond.addNumber(f2);
      this.requestOutTime = -1L;
      return;
    }
    finally
    {
    }
  }

  public void requestOut(long paramLong, int paramInt)
  {
    try
    {
      if (this.requestOutTime != -1L)
        logger.w("onSending() without onResponse()!", new Object[0]);
      this.requestSize = paramInt;
      this.requestOutTime = paramLong;
      return;
    }
    finally
    {
    }
  }

  public void reset()
  {
    try
    {
      this.bytesPerSecond.reset();
      this.requestOutTime = -1L;
      this.requestSize = -1;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.BandwidthEstimator
 * JD-Core Version:    0.6.2
 */