package com.google.android.apps.unveil.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Random;

public class BackoffHandler
{
  private static final String FAILURE_COUNT_KEY = "failure_count";
  private static final String LAST_FAILURE_TIME_KEY = "last_failure_time";
  private final double backoffFactor;
  private final double fuzzRatio;
  private final long initialInterval;
  private final long maxInterval;
  private final SharedPreferences preferences;
  private final RandomnessSource randomnessSource;

  public BackoffHandler(SharedPreferences paramSharedPreferences, long paramLong1, double paramDouble1, long paramLong2, double paramDouble2)
  {
    this(paramSharedPreferences, paramLong1, paramDouble1, paramLong2, paramDouble2, new SystemRandomnessSource(new Random()));
  }

  BackoffHandler(SharedPreferences paramSharedPreferences, long paramLong1, double paramDouble1, long paramLong2, double paramDouble2, RandomnessSource paramRandomnessSource)
  {
    this.preferences = paramSharedPreferences;
    this.initialInterval = paramLong1;
    this.backoffFactor = paramDouble1;
    this.maxInterval = paramLong2;
    this.fuzzRatio = paramDouble2;
    this.randomnessSource = paramRandomnessSource;
  }

  private int getFailureCount()
  {
    return this.preferences.getInt("failure_count", 0);
  }

  private long getFuzzyAdjustment(long paramLong)
  {
    return ()((this.randomnessSource.nextDouble() * (2.0D * this.fuzzRatio) - this.fuzzRatio) * paramLong);
  }

  private long getLastFailTime()
  {
    return this.preferences.getLong("last_failure_time", -9223372036854775808L);
  }

  private void setFailureCount(int paramInt)
  {
    this.preferences.edit().putInt("failure_count", paramInt).commit();
  }

  private void setLastFailTime(long paramLong)
  {
    this.preferences.edit().putLong("last_failure_time", paramLong).commit();
  }

  public boolean canQuery(long paramLong)
  {
    return paramLong > getEarliestAllowedQueryTime();
  }

  public long getEarliestAllowedQueryTime()
  {
    int i = getFailureCount();
    if (i == 0)
      return -9223372036854775808L;
    long l = Math.min(()(this.initialInterval * Math.pow(this.backoffFactor, i - 1)), this.maxInterval);
    return l + getLastFailTime() + getFuzzyAdjustment(l);
  }

  public QueryExecutor.PostExecutionHook makeHook()
  {
    return new QueryExecutor.PostExecutionHook()
    {
      public void onQueryFailed(BackgroundQuery paramAnonymousBackgroundQuery, int paramAnonymousInt)
      {
        if (paramAnonymousInt != 200)
          BackoffHandler.this.onQueryFailed(System.currentTimeMillis());
      }

      public void onQuerySucceeded(BackgroundQuery paramAnonymousBackgroundQuery)
      {
        BackoffHandler.this.reset();
      }
    };
  }

  void onQueryFailed(long paramLong)
  {
    setFailureCount(1 + getFailureCount());
    setLastFailTime(paramLong);
  }

  void reset()
  {
    setFailureCount(0);
  }

  public static abstract interface RandomnessSource
  {
    public abstract double nextDouble();
  }

  static class SystemRandomnessSource
    implements BackoffHandler.RandomnessSource
  {
    private final Random random;

    public SystemRandomnessSource(Random paramRandom)
    {
      this.random = paramRandom;
    }

    public double nextDouble()
    {
      return this.random.nextDouble();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.BackoffHandler
 * JD-Core Version:    0.6.2
 */