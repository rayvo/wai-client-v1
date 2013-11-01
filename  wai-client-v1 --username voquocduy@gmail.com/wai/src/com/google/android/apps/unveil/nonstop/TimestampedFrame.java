package com.google.android.apps.unveil.nonstop;

import android.os.SystemClock;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.ImageUtils.ImageStatistics;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class TimestampedFrame
{
  public static final int NOT_SET = -1;
  private static final UnveilLogger logger = new UnveilLogger("TimestampedFrame", "");
  private final BufferSink bufferSink;
  private int[] cachedBucketDist;
  private DownsampledImage cachedDownsampledImage;
  private Boolean cachedIsBlurred;
  private int[] cachedSignature;
  private final int frameNum;
  private final int height;
  private ImageUtils.ImageStatistics imageStatistics;
  private final Metadata metadata;
  private int opticalFlowDelta;
  private byte[] rawFrameData;
  private int referenceCount;
  private final int rotationAngle;
  private final Size size;
  private Boolean takenWhileFocusing;
  private final long timestamp;
  private final int width;

  public TimestampedFrame(byte[] paramArrayOfByte, Size paramSize, int paramInt1, long paramLong, int paramInt2, BufferSink paramBufferSink)
  {
    this.rawFrameData = paramArrayOfByte;
    this.frameNum = paramInt1;
    this.timestamp = paramLong;
    this.size = paramSize;
    this.width = paramSize.width;
    this.height = paramSize.height;
    this.rotationAngle = paramInt2;
    this.bufferSink = paramBufferSink;
    this.referenceCount = 0;
    this.metadata = new Metadata(paramLong);
  }

  private byte[] clearRawData()
  {
    try
    {
      byte[] arrayOfByte = getRawData();
      this.rawFrameData = null;
      if (this.cachedDownsampledImage != null)
      {
        this.cachedDownsampledImage.removeReference();
        this.cachedDownsampledImage = null;
      }
      return arrayOfByte;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void createDownsampledImage()
  {
    this.cachedDownsampledImage = DownsampledImage.downsample(getRawData(), this.width, this.height, this.timestamp);
    this.cachedDownsampledImage.addReference();
  }

  public static int diffSignature(int[] paramArrayOfInt1, int[] paramArrayOfInt2)
  {
    return ImageUtils.diffSignatureNative(paramArrayOfInt1, paramArrayOfInt2);
  }

  private void generateBucketDistributionAndSignature()
  {
    try
    {
      if (this.cachedBucketDist == null)
        this.cachedBucketDist = ImageUtils.getBucketDistributionNative(getDownsampledImage().getBytes(), getDownsampledWidth(), getDownsampledHeight());
      if (this.cachedSignature == null)
        this.cachedSignature = ImageUtils.computeSignatureNative(getDownsampledImage().getBytes(), getDownsampledWidth(), getDownsampledHeight(), null);
      return;
    }
    finally
    {
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

  protected boolean allThreadsDone()
  {
    try
    {
      int i = this.referenceCount;
      if (i == 0)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public int diffSignature(int[] paramArrayOfInt)
  {
    try
    {
      int i = diffSignature(paramArrayOfInt, getSignature());
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected int[] getBucketDistribution()
  {
    try
    {
      generateBucketDistributionAndSignature();
      int[] arrayOfInt = this.cachedBucketDist;
      return arrayOfInt;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getDownsampleFactor()
  {
    return DownsampledImage.getDownsampleFactor(this.width, this.height);
  }

  public int getDownsampledHeight()
  {
    return DownsampledImage.getDownsampledHeight(this.height, DownsampledImage.getDownsampleFactor(this.width, this.height));
  }

  public DownsampledImage getDownsampledImage()
  {
    try
    {
      if ((!hasRawData()) && (this.cachedDownsampledImage == null))
        throw new IllegalStateException("Frame data for frame " + this.frameNum + " is no longer available.");
    }
    finally
    {
    }
    if (this.cachedDownsampledImage == null)
      createDownsampledImage();
    DownsampledImage localDownsampledImage = this.cachedDownsampledImage;
    return localDownsampledImage;
  }

  public int getDownsampledWidth()
  {
    return DownsampledImage.getDownsampledWidth(this.width, DownsampledImage.getDownsampleFactor(this.width, this.height));
  }

  public int getFrameNum()
  {
    return this.frameNum;
  }

  public int getHeight()
  {
    return this.height;
  }

  public ImageUtils.ImageStatistics getImageStatistics()
  {
    try
    {
      if (this.imageStatistics == null)
        this.imageStatistics = ImageUtils.computeImageStatistics(this.width, this.height, getRawData());
      ImageUtils.ImageStatistics localImageStatistics = this.imageStatistics;
      return localImageStatistics;
    }
    finally
    {
    }
  }

  public Metadata getMetadata()
  {
    return this.metadata;
  }

  public int getOpticalFlowDelta()
  {
    try
    {
      int i = this.opticalFlowDelta;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte[] getRawData()
  {
    try
    {
      if (!hasRawData())
        throw new RuntimeException("Frame data for frame " + this.frameNum + " is no longer available.");
    }
    finally
    {
    }
    byte[] arrayOfByte = this.rawFrameData;
    return arrayOfByte;
  }

  public int getRotationAngle()
  {
    return this.rotationAngle;
  }

  public int[] getSignature()
  {
    try
    {
      generateBucketDistributionAndSignature();
      int[] arrayOfInt = this.cachedSignature;
      return arrayOfInt;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Size getSize()
  {
    return this.size;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public int getWidth()
  {
    return this.width;
  }

  protected boolean hasRawData()
  {
    try
    {
      byte[] arrayOfByte = this.rawFrameData;
      if (arrayOfByte != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean isBlurred()
  {
    try
    {
      if (this.cachedIsBlurred == null)
        this.cachedIsBlurred = Boolean.valueOf(ImageUtils.isBlurredNative(getRawData(), this.width, this.height));
      boolean bool = this.cachedIsBlurred.booleanValue();
      return bool;
    }
    finally
    {
    }
  }

  public int recommendedQuality()
  {
    return ImageUtils.getJpegQualityRecommendation(this.width, this.height);
  }

  public void removeReference()
  {
    try
    {
      this.referenceCount = (-1 + this.referenceCount);
      if (this.referenceCount == 0)
      {
        arrayOfByte = clearRawData();
        if (this.bufferSink != null)
          this.bufferSink.returnBuffer(arrayOfByte);
      }
      while (this.referenceCount >= 0)
      {
        byte[] arrayOfByte;
        return;
      }
      throw new AssertionError("Negative reference count.");
    }
    finally
    {
    }
  }

  public void setOpticalFlowDelta(int paramInt)
  {
    try
    {
      this.opticalFlowDelta = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setTakenWhileFocusing(boolean paramBoolean)
  {
    this.takenWhileFocusing = Boolean.valueOf(paramBoolean);
  }

  public boolean takenWhileFocusing()
  {
    if (this.takenWhileFocusing == null)
      return false;
    return this.takenWhileFocusing.booleanValue();
  }

  protected static abstract interface BufferSink
  {
    public abstract void returnBuffer(byte[] paramArrayOfByte);
  }

  public static class Metadata
  {
    private long jpegCreatedMillis;
    private long pickedForQueryMillis;
    private long querySentMillis;
    private long queryStartMillis;
    private long responseReceivedMillis;
    public final Map<String, ResultItem> results = new HashMap();
    private final long timestamp;

    public Metadata(long paramLong)
    {
      this.timestamp = paramLong;
      this.pickedForQueryMillis = -1L;
      this.jpegCreatedMillis = -1L;
      this.queryStartMillis = -1L;
      this.querySentMillis = -1L;
      this.responseReceivedMillis = -1L;
    }

    private long getPickedForQueryTime()
    {
      return this.pickedForQueryMillis - this.timestamp;
    }

    private long getServerResponseTime()
    {
      return this.responseReceivedMillis - this.timestamp;
    }

    private long getUploadFinishTime()
    {
      return this.querySentMillis - this.timestamp;
    }

    private long getUploadStartTime()
    {
      return this.queryStartMillis - this.timestamp;
    }

    public boolean addResult(ResultItem paramResultItem)
    {
      boolean bool = this.results.isEmpty();
      this.results.put(paramResultItem.getAnnotationResult().getResultId(), paramResultItem);
      if (this.responseReceivedMillis < 0L)
        this.responseReceivedMillis = SystemClock.uptimeMillis();
      return bool;
    }

    public Vector<String> getDebugText()
    {
      Vector localVector = new Vector();
      if (this.querySentMillis != -1L)
        localVector.add("up: " + getUploadFinishTime() + "ms");
      if (this.responseReceivedMillis != -1L)
      {
        localVector.add("s+r: " + getServerResponseTime() + "ms");
        localVector.add("#res: " + this.results.size());
      }
      return localVector;
    }

    public long getEncodeFinishedTime()
    {
      return this.jpegCreatedMillis - this.timestamp;
    }

    public long getQuerySentMillis()
    {
      return this.querySentMillis;
    }

    public long getTimestamp()
    {
      return this.timestamp;
    }

    public String getTimingString()
    {
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = Long.valueOf(getPickedForQueryTime());
      arrayOfObject1[1] = Long.valueOf(getEncodeFinishedTime());
      arrayOfObject1[2] = Long.valueOf(getUploadStartTime());
      String str = String.format("%4d\t%4d\t%4d", arrayOfObject1);
      if (this.querySentMillis != -1L)
      {
        StringBuilder localStringBuilder2 = new StringBuilder().append(str);
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Long.valueOf(getUploadFinishTime());
        str = String.format("\t%4d", arrayOfObject3);
      }
      if (this.responseReceivedMillis != -1L)
      {
        StringBuilder localStringBuilder1 = new StringBuilder().append(str);
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = Long.valueOf(getServerResponseTime());
        str = String.format("\t%4d", arrayOfObject2);
      }
      return str;
    }

    public void onCreatedCachedJpeg()
    {
      this.jpegCreatedMillis = SystemClock.uptimeMillis();
    }

    public void onPickedForQuery()
    {
      if (this.pickedForQueryMillis != -1L)
      {
        TimestampedFrame.logger.w("onPickedForQuery called multiple times.", new Object[0]);
        return;
      }
      this.pickedForQueryMillis = SystemClock.uptimeMillis();
    }

    public void onQuerySent()
    {
      if (this.querySentMillis != -1L)
      {
        TimestampedFrame.logger.w("querySent called multiple times.", new Object[0]);
        return;
      }
      this.querySentMillis = SystemClock.uptimeMillis();
    }

    public void onQueryStartSending()
    {
      if (this.queryStartMillis != -1L)
      {
        TimestampedFrame.logger.w("queryStart called multiple times.", new Object[0]);
        return;
      }
      this.queryStartMillis = SystemClock.uptimeMillis();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.TimestampedFrame
 * JD-Core Version:    0.6.2
 */