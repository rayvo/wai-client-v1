package com.google.android.apps.unveil.env;

import java.util.ArrayList;

public class DownsampledImage
{
  private static final int MAX_QUEUE_SIZE = 2;
  public static final int TARGET_PIXELS = 25680;
  private static final ArrayList<byte[]> downsampledQueue = new ArrayList();
  private static final UnveilLogger logger = new UnveilLogger();
  private final byte[] bytes;
  private int referenceCount;
  private final long timestamp;

  private DownsampledImage(byte[] paramArrayOfByte, long paramLong)
  {
    this.bytes = paramArrayOfByte;
    this.timestamp = paramLong;
  }

  public static DownsampledImage downsample(byte[] paramArrayOfByte, int paramInt1, int paramInt2, long paramLong)
  {
    byte[] arrayOfByte = issueBuffer(paramInt1, paramInt2);
    ImageUtils.downsampleImageNative(paramInt1, paramInt2, paramArrayOfByte, getDownsampleFactor(paramInt1, paramInt2), arrayOfByte);
    return new DownsampledImage(arrayOfByte, paramLong);
  }

  public static int getDownsampleFactor(int paramInt1, int paramInt2)
  {
    int i = 1;
    while (getDownsampledHeight(paramInt2, i) * getDownsampledWidth(paramInt1, i) > 25680)
      i *= 2;
    return i;
  }

  public static int getDownsampledHeight(int paramInt1, int paramInt2)
  {
    return (-1 + (paramInt1 + paramInt2)) / paramInt2;
  }

  public static int getDownsampledWidth(int paramInt1, int paramInt2)
  {
    return (-1 + (paramInt1 + paramInt2)) / paramInt2;
  }

  private static byte[] issueBuffer(int paramInt1, int paramInt2)
  {
    byte[] arrayOfByte = null;
    int i = getDownsampleFactor(paramInt1, paramInt2);
    int j = getDownsampledWidth(paramInt1, i) * getDownsampledHeight(paramInt2, i);
    synchronized (downsampledQueue)
    {
      while ((!downsampledQueue.isEmpty()) && (arrayOfByte == null))
      {
        arrayOfByte = (byte[])downsampledQueue.remove(0);
        if (arrayOfByte.length != j)
          arrayOfByte = null;
      }
      if (arrayOfByte == null)
        arrayOfByte = new byte[j];
      return arrayOfByte;
    }
  }

  private static void reclaimBuffer(byte[] paramArrayOfByte)
  {
    synchronized (downsampledQueue)
    {
      if (downsampledQueue.size() < 2)
        downsampledQueue.add(paramArrayOfByte);
      return;
    }
  }

  public void addReference()
  {
    try
    {
      this.referenceCount = (1 + this.referenceCount);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void finalize()
    throws Throwable
  {
    if (this.referenceCount != 0)
      logger.e("DownsampledImage garbage collected with a non-zero reference count.", new Object[0]);
  }

  public byte[] getBytes()
  {
    return this.bytes;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public void removeReference()
  {
    try
    {
      this.referenceCount = (-1 + this.referenceCount);
      if (this.referenceCount == 0)
        reclaimBuffer(getBytes());
      while (this.referenceCount >= 0)
        return;
      throw new AssertionError("Negative reference count.");
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.DownsampledImage
 * JD-Core Version:    0.6.2
 */