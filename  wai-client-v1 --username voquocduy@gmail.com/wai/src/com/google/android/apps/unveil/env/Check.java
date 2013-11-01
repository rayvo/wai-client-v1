package com.google.android.apps.unveil.env;

public class Check
{
  public static final boolean ENABLED;
  private static final UnveilLogger logger = new UnveilLogger();

  public static void checkEqual(double paramDouble1, double paramDouble2)
  {
    checkEqual("Not equal", paramDouble1, paramDouble2);
  }

  public static void checkEqual(long paramLong1, long paramLong2)
  {
    checkEqual("Not equal", paramLong1, paramLong2);
  }

  public static void checkEqual(Object paramObject1, Object paramObject2)
  {
    checkEqual("Not equal", paramObject1, paramObject2);
  }

  public static void checkEqual(String paramString, double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 != paramDouble2)
      fail(paramString + " Expected:" + paramDouble1 + " Actual:" + paramDouble2);
  }

  public static void checkEqual(String paramString, long paramLong1, long paramLong2)
  {
    if (paramLong1 != paramLong2)
      fail(paramString + " Expected:" + paramLong1 + " Actual:" + paramLong2);
  }

  public static void checkEqual(String paramString, Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) || (paramObject2 == null))
      if ((paramObject1 != null) || (paramObject2 != null));
    while (paramObject1.equals(paramObject2))
    {
      return;
      fail(paramString + " Expected:" + paramObject1 + " Actual:" + paramObject2);
    }
    fail(paramString + " Expected:" + paramObject1 + " Actual:" + paramObject2);
  }

  public static void checkEqual(String paramString, boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1 != paramBoolean2)
      fail(paramString + " Expected:" + paramBoolean1 + " Actual:" + paramBoolean2);
  }

  public static void checkEqual(boolean paramBoolean1, boolean paramBoolean2)
  {
    checkEqual("Not equal", paramBoolean1, paramBoolean2);
  }

  public static void checkFalse(String paramString, boolean paramBoolean)
  {
    if (paramBoolean)
      fail(paramString);
  }

  public static void checkFalse(boolean paramBoolean)
  {
  }

  public static void checkGreater(double paramDouble1, double paramDouble2)
  {
    checkGreater("Not greater than", paramDouble1, paramDouble2);
  }

  public static void checkGreater(String paramString, double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 <= paramDouble2)
      fail(paramString + " Expected:" + paramDouble1 + " Actual:" + paramDouble2);
  }

  public static void checkGreaterOrEqual(double paramDouble1, double paramDouble2)
  {
    checkGreaterOrEqual("Not greater than or equal to", paramDouble1, paramDouble2);
  }

  public static void checkGreaterOrEqual(long paramLong1, long paramLong2)
  {
    checkGreaterOrEqual("Not greater than or equal to", paramLong1, paramLong2);
  }

  public static void checkGreaterOrEqual(String paramString, double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 < paramDouble2)
      fail(paramString + " Expected:" + paramDouble1 + " Actual:" + paramDouble2);
  }

  public static void checkGreaterOrEqual(String paramString, long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2)
      fail(paramString + " Expected:" + paramLong1 + " Actual:" + paramLong2);
  }

  public static void checkLess(double paramDouble1, double paramDouble2)
  {
    checkLess("Not less than", paramDouble1, paramDouble2);
  }

  public static void checkLess(String paramString, double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 >= paramDouble2)
      fail(paramString + " Expected:" + paramDouble1 + " Actual:" + paramDouble2);
  }

  public static void checkLessOrEqual(double paramDouble1, double paramDouble2)
  {
    checkLessOrEqual("Not less than or equal to", paramDouble1, paramDouble2);
  }

  public static void checkLessOrEqual(long paramLong1, long paramLong2)
  {
    checkLessOrEqual("Not less than or equal to", paramLong1, paramLong2);
  }

  public static void checkLessOrEqual(String paramString, double paramDouble1, double paramDouble2)
  {
    if (paramDouble1 > paramDouble2)
      fail(paramString + " Expected:" + paramDouble1 + " Actual:" + paramDouble2);
  }

  public static void checkLessOrEqual(String paramString, long paramLong1, long paramLong2)
  {
    if (paramLong1 > paramLong2)
      fail(paramString + " Expected:" + paramLong1 + " Actual:" + paramLong2);
  }

  public static void checkNotNull(Object paramObject)
  {
    if (paramObject == null)
      fail("Unexpected null argument");
  }

  public static void checkSame(Object paramObject1, Object paramObject2)
  {
    checkSame("Not same", paramObject1, paramObject2);
  }

  public static void checkSame(String paramString, Object paramObject1, Object paramObject2)
  {
    if (paramObject1 != paramObject2)
      fail(paramString + " Expected:" + paramObject1 + " Actual:" + paramObject2);
  }

  public static void checkTrue(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean)
      fail(paramString);
  }

  public static void checkTrue(boolean paramBoolean)
  {
  }

  private static void fail(String paramString)
  {
    logger.e(paramString, new Object[0]);
  }

  public static class FailedException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;

    FailedException(String paramString)
    {
      super();
    }
  }

  public static class ThreadCheck
  {
    private final Thread thread = Thread.currentThread();

    public void check()
    {
      if (Thread.currentThread() != this.thread)
        Check.fail("Expected thread: " + this.thread + " but was: " + Thread.currentThread());
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Check
 * JD-Core Version:    0.6.2
 */