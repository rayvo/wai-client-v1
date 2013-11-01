package com.google.android.apps.unveil.protocol.nonstop;

import android.graphics.Canvas;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ActiveFrameQueue
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<ActiveFrame> activeFrames = new ArrayList();
  private final int maxSize;

  public ActiveFrameQueue(int paramInt)
  {
    this.maxSize = paramInt;
  }

  private ActiveFrame getByFrameNum(int paramInt)
  {
    try
    {
      Iterator localIterator = this.activeFrames.iterator();
      ActiveFrame localActiveFrame;
      int i;
      do
      {
        if (!localIterator.hasNext())
          break;
        localActiveFrame = (ActiveFrame)localIterator.next();
        i = localActiveFrame.frameNum;
      }
      while (i != paramInt);
      while (true)
      {
        return localActiveFrame;
        localActiveFrame = null;
      }
    }
    finally
    {
    }
  }

  private void trimToSize(int paramInt)
  {
    if (paramInt < 0)
      try
      {
        throw new IllegalArgumentException();
      }
      finally
      {
      }
    while (this.activeFrames.size() > paramInt)
    {
      ActiveFrame localActiveFrame = (ActiveFrame)this.activeFrames.remove(0);
      localActiveFrame.downsampledImage.removeReference();
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localActiveFrame.getMetadata().getTimingString();
      localUnveilLogger.v("Removing active frame: %s", arrayOfObject);
    }
  }

  public void clear()
  {
    try
    {
      trimToSize(0);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void drawDebug(Canvas paramCanvas)
  {
    try
    {
      float f = paramCanvas.getWidth() / this.maxSize;
      int i = this.activeFrames.size();
      for (int j = 0; j < i; j++)
        ((ActiveFrame)this.activeFrames.get(j)).draw(paramCanvas, j + this.maxSize - i, f);
      return;
    }
    finally
    {
    }
  }

  public List<Integer> getActionNumbers()
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList(this.activeFrames.size());
      Iterator localIterator = this.activeFrames.iterator();
      while (localIterator.hasNext())
        localArrayList.add(Integer.valueOf(((ActiveFrame)localIterator.next()).getFrameNum()));
    }
    finally
    {
    }
    return localArrayList;
  }

  public ActiveFrame getByTimestamp(long paramLong)
  {
    try
    {
      Iterator localIterator = this.activeFrames.iterator();
      ActiveFrame localActiveFrame;
      long l;
      do
      {
        if (!localIterator.hasNext())
          break;
        localActiveFrame = (ActiveFrame)localIterator.next();
        l = localActiveFrame.getTimestamp();
      }
      while (l != paramLong);
      while (true)
      {
        return localActiveFrame;
        localActiveFrame = null;
      }
    }
    finally
    {
    }
  }

  public List<ActiveFrame> getFrames()
  {
    try
    {
      List localList = Collections.unmodifiableList(this.activeFrames);
      return localList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Integer getLastFrameNum()
  {
    try
    {
      boolean bool = this.activeFrames.isEmpty();
      if (bool);
      Integer localInteger;
      for (Object localObject2 = null; ; localObject2 = localInteger)
      {
        return localObject2;
        localInteger = Integer.valueOf(((ActiveFrame)this.activeFrames.get(-1 + this.activeFrames.size())).getFrameNum());
      }
    }
    finally
    {
    }
  }

  public ActiveFrame maybeAdd(TimestampedFrame paramTimestampedFrame, byte[] paramArrayOfByte)
  {
    try
    {
      ActiveFrame localActiveFrame1 = getByFrameNum(paramTimestampedFrame.getFrameNum());
      Object localObject2 = localActiveFrame1;
      if (localObject2 != null);
      while (true)
      {
        return localObject2;
        ActiveFrame localActiveFrame2 = new ActiveFrame(paramTimestampedFrame, paramArrayOfByte, null);
        this.activeFrames.add(localActiveFrame2);
        trimToSize(this.maxSize);
        localObject2 = localActiveFrame2;
      }
    }
    finally
    {
    }
  }

  public static class ActiveFrame
  {
    public final byte[] cachedJpeg;
    private final DebugDrawer debugDrawer;
    public final DownsampledImage downsampledImage;
    public final int frameNum;
    public final TimestampedFrame.Metadata metadata;

    private ActiveFrame(TimestampedFrame paramTimestampedFrame, byte[] paramArrayOfByte)
    {
      this.frameNum = paramTimestampedFrame.getFrameNum();
      this.metadata = paramTimestampedFrame.getMetadata();
      this.downsampledImage = paramTimestampedFrame.getDownsampledImage();
      this.downsampledImage.addReference();
      this.cachedJpeg = paramArrayOfByte;
      this.debugDrawer = createDebugDrawer(paramTimestampedFrame, paramArrayOfByte);
    }

    private static DebugDrawer createDebugDrawer(TimestampedFrame paramTimestampedFrame, byte[] paramArrayOfByte)
    {
      return DebugDrawer.NO_OP;
    }

    public void draw(Canvas paramCanvas, int paramInt, float paramFloat)
    {
      this.debugDrawer.draw(paramCanvas, paramInt, paramFloat);
    }

    public DownsampledImage getDownsampledImage()
    {
      return this.downsampledImage;
    }

    public int getFrameNum()
    {
      return this.frameNum;
    }

    public TimestampedFrame.Metadata getMetadata()
    {
      return this.metadata;
    }

    public long getTimestamp()
    {
      return this.metadata.getTimestamp();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue
 * JD-Core Version:    0.6.2
 */