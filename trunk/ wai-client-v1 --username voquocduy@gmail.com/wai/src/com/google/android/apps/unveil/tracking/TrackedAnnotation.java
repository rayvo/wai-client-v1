package com.google.android.apps.unveil.tracking;

import android.graphics.RectF;
import android.os.SystemClock;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.nonstop.RequestIds;
import com.google.android.apps.unveil.results.BasicAnnotation;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.BoundingBoxProtos.BoundingBox.Builder;
import com.google.goggles.ExtendedGogglesProtos.TrackedResult;
import com.google.goggles.ExtendedGogglesProtos.TrackedResult.Builder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Vector;

public class TrackedAnnotation extends BasicAnnotation
{
  private static final int MAX_POSITION_HISTORY = 100;
  private static final UnveilLogger logger = new UnveilLogger();
  private final LinkedList<TimestampedPosition> positions = new LinkedList();
  private long previewFrameTimestamp;
  private ObjectTracker.TrackedObject trackedObject;

  public TrackedAnnotation(ResultItem paramResultItem)
  {
    super(paramResultItem);
  }

  protected Vector<String> getDebugText()
  {
    Vector localVector = super.getDebugText();
    localVector.add("Last reqId: " + this.previewFrameTimestamp);
    return localVector;
  }

  public RectF getLastKnownPositionInFrame()
  {
    try
    {
      if (this.trackedObject != null)
      {
        boolean bool = this.positions.isEmpty();
        if (!bool)
          break label27;
      }
      label27: RectF localRectF;
      for (Object localObject2 = null; ; localObject2 = localRectF)
      {
        return localObject2;
        localRectF = new RectF(((TimestampedPosition)this.positions.getFirst()).position);
      }
    }
    finally
    {
    }
  }

  protected RectF getPositionInFrameAtTime(long paramLong)
  {
    synchronized (this.positions)
    {
      Iterator localIterator = this.positions.iterator();
      while (localIterator.hasNext())
      {
        TimestampedPosition localTimestampedPosition = (TimestampedPosition)localIterator.next();
        if (localTimestampedPosition.timestamp <= paramLong)
        {
          RectF localRectF = new RectF(localTimestampedPosition.position);
          return localRectF;
        }
      }
      return null;
    }
  }

  public long getPreviewFrameTimestamp()
  {
    return this.previewFrameTimestamp;
  }

  public ObjectTracker.TrackedObject getTrackedObject()
  {
    try
    {
      ObjectTracker.TrackedObject localTrackedObject = this.trackedObject;
      return localTrackedObject;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void markPositionAtTime(long paramLong)
  {
    if (this.trackedObject == null)
      return;
    synchronized (this.positions)
    {
      this.positions.addFirst(new TimestampedPosition(paramLong, this.trackedObject.getTrackedPositionInPreviewFrame()));
      if (this.positions.size() > 100)
        this.positions.removeLast();
    }
  }

  public void setTrackedObject(ObjectTracker.TrackedObject paramTrackedObject)
  {
    try
    {
      this.trackedObject = paramTrackedObject;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public ExtendedGogglesProtos.TrackedResult toTrackedResult(long paramLong)
  {
    ExtendedGogglesProtos.TrackedResult.Builder localBuilder = ExtendedGogglesProtos.TrackedResult.newBuilder();
    localBuilder.setResultId(getResultId());
    if (this.trackedObject != null)
    {
      RectF localRectF = getPositionInFrameAtTime(paramLong);
      if (localRectF != null)
      {
        BoundingBoxProtos.BoundingBox.Builder localBuilder1 = BoundingBoxProtos.BoundingBox.newBuilder();
        localBuilder1.setX((int)localRectF.left);
        localBuilder1.setY((int)localRectF.top);
        localBuilder1.setWidth((int)localRectF.width());
        localBuilder1.setHeight((int)localRectF.height());
        localBuilder.setBoundingBox(localBuilder1);
      }
    }
    return localBuilder.build();
  }

  public void updateResultItem(ResultItem paramResultItem)
  {
    AnnotationResultProtos.AnnotationResult localAnnotationResult = paramResultItem.getAnnotationResult();
    long l1 = SystemClock.uptimeMillis();
    long l2 = 0L;
    if (localAnnotationResult.hasBoundingBoxRequestId())
    {
      if (!localAnnotationResult.hasBoundingBox())
        logger.e("AnnotationResult has previewFrameTimestamp but no boundingBox!", new Object[0]);
      l2 = RequestIds.decodeBaseRequestId(localAnnotationResult.getBoundingBoxRequestId());
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[3];
      arrayOfObject3[0] = localAnnotationResult.getResultId();
      arrayOfObject3[1] = localAnnotationResult.getBoundingBoxRequestId();
      arrayOfObject3[2] = Float.valueOf((float)(l1 - l2) / 1000.0F);
      localUnveilLogger3.v("AnnotationResult [%s] has timestamp [%s] which is %2.2fs old.", arrayOfObject3);
    }
    while (l2 < this.previewFrameTimestamp)
    {
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[5];
      arrayOfObject2[0] = getResultId();
      arrayOfObject2[1] = Long.valueOf(this.previewFrameTimestamp);
      arrayOfObject2[2] = Long.valueOf(l2);
      arrayOfObject2[3] = Float.valueOf((float)(this.previewFrameTimestamp - l2) / 1000.0F);
      arrayOfObject2[4] = Float.valueOf((float)(l1 - l2) / 1000.0F);
      localUnveilLogger2.e("previewFrameTimestamp regression for AnnotationResult [%s], aborting update! %d to %d (%2.2fs older than most recent, %2.2fs older than now)", arrayOfObject2);
      return;
      if (localAnnotationResult.hasBoundingBox())
        logger.e("AnnotationResult has boundingBox but no previewFrameTimestamp!", new Object[0]);
    }
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Double.valueOf(System.currentTimeMillis() / 1000.0D);
    arrayOfObject1[1] = paramResultItem.toString();
    localUnveilLogger1.v("Updating with new ResultItem @ %f: %s", arrayOfObject1);
    super.updateResultItem(paramResultItem);
    this.previewFrameTimestamp = l2;
  }

  private class TimestampedPosition
  {
    RectF position;
    long timestamp;

    public TimestampedPosition(long arg2, RectF arg4)
    {
      this.timestamp = ???;
      Object localObject;
      this.position = localObject;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.TrackedAnnotation
 * JD-Core Version:    0.6.2
 */