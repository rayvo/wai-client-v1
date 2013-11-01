package com.google.android.apps.unveil.tracking;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.graphics.Typeface;
import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import javax.microedition.khronos.opengles.GL10;

class ObjectTracker
{
  private static final boolean DRAW_TEXT = false;
  private static final int MAX_DEBUG_HISTORY_SIZE = 30;
  private static final int MAX_FRAME_HISTORY_SIZE = 200;
  private static ObjectTracker instance;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Vector<PointF> debugHistory;
  private final float downsampleFactor;
  private final int frameHeight;
  private final int frameWidth;
  private FrameChange lastFeatures;
  private long lastTimestamp;
  private int nativeObjectTracker;
  private final LinkedList<TimestampedDeltas> timestampedDeltas;
  private final Map<String, TrackedObject> trackedObjects;

  static
  {
    ResourceUtils.loadNativeLibrary("clientvision");
  }

  private ObjectTracker(int paramInt1, int paramInt2, int paramInt3)
  {
    this.frameWidth = paramInt1;
    this.frameHeight = paramInt2;
    this.downsampleFactor = paramInt3;
    this.timestampedDeltas = new LinkedList();
    this.trackedObjects = new HashMap();
    this.debugHistory = new Vector(30);
    initNative(paramInt1, paramInt2);
  }

  private RectF downscaleRect(RectF paramRectF)
  {
    return new RectF(paramRectF.left / this.downsampleFactor, paramRectF.top / this.downsampleFactor, paramRectF.right / this.downsampleFactor, paramRectF.bottom / this.downsampleFactor);
  }

  private void drawFeaturesDebug(Canvas paramCanvas)
  {
    Paint localPaint = new Paint();
    if (this.lastFeatures == null);
    while (true)
    {
      return;
      float f1 = this.lastFeatures.minScore;
      float f2 = this.lastFeatures.maxScore;
      Iterator localIterator = this.lastFeatures.pointDeltas.iterator();
      while (localIterator.hasNext())
      {
        PointChange localPointChange = (PointChange)localIterator.next();
        if (localPointChange.wasFound)
        {
          int i = floatToChar((localPointChange.featureA.score - f1) / (f2 - f1));
          localPaint.setColor(floatToChar(1.0F - (localPointChange.featureA.score - f1) / (f2 - f1)) | (0xFF000000 | i << 16));
          float[] arrayOfFloat2 = new float[4];
          arrayOfFloat2[0] = localPointChange.featureA.x;
          arrayOfFloat2[1] = localPointChange.featureA.y;
          arrayOfFloat2[2] = localPointChange.featureB.x;
          arrayOfFloat2[3] = localPointChange.featureB.y;
          paramCanvas.drawRect(arrayOfFloat2[2] - 3.0F, arrayOfFloat2[3] - 3.0F, 3.0F + arrayOfFloat2[2], 3.0F + arrayOfFloat2[3], localPaint);
          localPaint.setColor(-16711681);
          paramCanvas.drawLine(arrayOfFloat2[2], arrayOfFloat2[3], arrayOfFloat2[0], arrayOfFloat2[1], localPaint);
        }
        else
        {
          localPaint.setColor(-256);
          float[] arrayOfFloat1 = new float[2];
          arrayOfFloat1[0] = localPointChange.featureA.x;
          arrayOfFloat1[1] = localPointChange.featureA.y;
          paramCanvas.drawCircle(arrayOfFloat1[0], arrayOfFloat1[1], 5.0F, localPaint);
        }
      }
    }
  }

  private void drawHistoryDebug(Canvas paramCanvas)
  {
    drawHistoryPoint(paramCanvas, this.frameWidth * this.downsampleFactor / 2.0F, this.frameHeight * this.downsampleFactor / 2.0F);
  }

  private void drawHistoryPoint(Canvas paramCanvas, float paramFloat1, float paramFloat2)
  {
    Paint localPaint = new Paint();
    localPaint.setAntiAlias(false);
    localPaint.setTypeface(Typeface.SERIF);
    localPaint.setColor(-65536);
    localPaint.setStrokeWidth(2.0F);
    localPaint.setColor(-16711936);
    paramCanvas.drawCircle(paramFloat1, paramFloat2, 3.0F, localPaint);
    localPaint.setColor(-65536);
    synchronized (this.debugHistory)
    {
      int i = this.debugHistory.size();
      float f1 = paramFloat1;
      float f2 = paramFloat2;
      for (int j = 0; j < i; j++)
      {
        PointF localPointF = (PointF)this.debugHistory.get(-1 + (i - j));
        float f3 = f1 + localPointF.x;
        float f4 = f2 + localPointF.y;
        paramCanvas.drawLine(f1, f2, f3, f4, localPaint);
        f1 = f3;
        f2 = f4;
      }
      return;
    }
  }

  private native void drawNative(int paramInt1, int paramInt2, int paramInt3);

  private static int floatToChar(float paramFloat)
  {
    return Math.max(0, Math.min((int)(255.99899F * paramFloat), 255));
  }

  private native void forgetNative(String paramString);

  private PointF getAccumulatedDelta(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3)
  {
    try
    {
      RectF localRectF = getCurrentPosition(paramLong, new RectF(paramFloat1 - paramFloat3, paramFloat2 - paramFloat3, paramFloat1 + paramFloat3, paramFloat2 + paramFloat3));
      PointF localPointF = new PointF(localRectF.centerX() - paramFloat1, localRectF.centerY() - paramFloat2);
      return localPointF;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private native float getCurrentCorrelation(String paramString);

  private RectF getCurrentPosition(long paramLong, RectF paramRectF)
  {
    try
    {
      RectF localRectF1 = downscaleRect(paramRectF);
      float[] arrayOfFloat = new float[4];
      getCurrentPositionNative(paramLong, localRectF1.left, localRectF1.top, localRectF1.right, localRectF1.bottom, arrayOfFloat);
      RectF localRectF2 = upscaleRect(new RectF(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]));
      return localRectF2;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private native void getCurrentPositionNative(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float[] paramArrayOfFloat);

  private native float[] getFeaturesNative(boolean paramBoolean);

  private native byte[] getFeaturesPacked(float paramFloat);

  public static ObjectTracker getInstance(int paramInt1, int paramInt2, int paramInt3)
  {
    try
    {
      if (instance == null)
      {
        instance = new ObjectTracker(paramInt1, paramInt2, paramInt3);
        ObjectTracker localObjectTracker = instance;
        return localObjectTracker;
      }
      throw new RuntimeException("Tried to create a new objectracker before releasing the old one!");
    }
    finally
    {
    }
  }

  private native void getTrackedPositionNative(String paramString, float[] paramArrayOfFloat);

  private native void initNative(int paramInt1, int paramInt2);

  private native boolean isObjectVisible(String paramString);

  private native void nextFrameNative(byte[] paramArrayOfByte, long paramLong, float[] paramArrayOfFloat);

  private native void registerAppearanceInFrameNative(String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, byte[] paramArrayOfByte);

  private native void releaseMemoryNative();

  private native void setPreviousPositionNative(String paramString, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, long paramLong);

  private void updateDebugHistory()
  {
    this.lastFeatures = new FrameChange(getFeaturesNative(false));
    PointF localPointF = getAccumulatedDelta(this.lastTimestamp, this.frameWidth / 2, this.frameHeight / 2, 100.0F);
    synchronized (this.debugHistory)
    {
      this.debugHistory.add(localPointF);
      if (this.debugHistory.size() > 30)
        this.debugHistory.remove(0);
    }
  }

  private RectF upscaleRect(RectF paramRectF)
  {
    return new RectF(paramRectF.left * this.downsampleFactor, paramRectF.top * this.downsampleFactor, paramRectF.right * this.downsampleFactor, paramRectF.bottom * this.downsampleFactor);
  }

  protected void drawDebug(Canvas paramCanvas, Matrix paramMatrix)
  {
    try
    {
      paramCanvas.save();
      paramCanvas.setMatrix(paramMatrix);
      drawHistoryDebug(paramCanvas);
      drawFeaturesDebug(paramCanvas);
      paramCanvas.restore();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void drawOverlay(GL10 paramGL10, Size paramSize, int paramInt)
  {
    try
    {
      drawNative(paramSize.width, paramSize.height, paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Vector<String> getDebugText()
  {
    Vector localVector = new Vector();
    if (this.lastFeatures != null)
    {
      localVector.add("Num features " + this.lastFeatures.pointDeltas.size());
      localVector.add("Min score: " + this.lastFeatures.minScore);
      localVector.add("Max score: " + this.lastFeatures.maxScore);
    }
    return localVector;
  }

  protected void nextFrame(byte[] paramArrayOfByte, long paramLong, float[] paramArrayOfFloat, boolean paramBoolean)
  {
    try
    {
      nextFrameNative(paramArrayOfByte, paramLong, paramArrayOfFloat);
      this.timestampedDeltas.add(new TimestampedDeltas(paramLong, getFeaturesPacked(this.downsampleFactor)));
      while (this.timestampedDeltas.size() > 200)
        this.timestampedDeltas.removeFirst();
    }
    finally
    {
    }
    Iterator localIterator = this.trackedObjects.values().iterator();
    while (localIterator.hasNext())
      ((TrackedObject)localIterator.next()).updateTrackedPosition();
    if (paramBoolean)
      updateDebugHistory();
    this.lastTimestamp = paramLong;
  }

  public List<byte[]> pollAccumulatedFlowData(long paramLong)
  {
    ArrayList localArrayList;
    try
    {
      localArrayList = new ArrayList();
      while (this.timestampedDeltas.size() > 0)
      {
        TimestampedDeltas localTimestampedDeltas = (TimestampedDeltas)this.timestampedDeltas.peekFirst();
        if (localTimestampedDeltas.timestamp > paramLong)
          break;
        localArrayList.add(localTimestampedDeltas.deltas);
        this.timestampedDeltas.removeFirst();
      }
    }
    finally
    {
    }
    return localArrayList;
  }

  protected void release()
  {
    try
    {
      releaseMemoryNative();
      try
      {
        instance = null;
        return;
      }
      finally
      {
      }
    }
    finally
    {
    }
  }

  public TrackedObject trackObject(RectF paramRectF, long paramLong, byte[] paramArrayOfByte)
  {
    try
    {
      TrackedObject localTrackedObject = new TrackedObject(paramRectF, paramLong, paramArrayOfByte);
      return localTrackedObject;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public TrackedObject trackObject(RectF paramRectF, byte[] paramArrayOfByte)
  {
    try
    {
      TrackedObject localTrackedObject = new TrackedObject(paramRectF, this.lastTimestamp, paramArrayOfByte);
      return localTrackedObject;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static class Feature
  {
    public final float score;
    public final int type;
    public final float x;
    public final float y;

    public Feature(float paramFloat1, float paramFloat2)
    {
      this.x = paramFloat1;
      this.y = paramFloat2;
      this.score = 0.0F;
      this.type = -1;
    }

    public Feature(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt)
    {
      this.x = paramFloat1;
      this.y = paramFloat2;
      this.score = paramFloat3;
      this.type = paramInt;
    }

    Feature delta(Feature paramFeature)
    {
      return new Feature(this.x - paramFeature.x, this.y - paramFeature.y);
    }
  }

  public class FrameChange
  {
    public static final int FEATURE_STEP = 7;
    private final float maxScore;
    private final float minScore;
    public final Vector<ObjectTracker.PointChange> pointDeltas;

    public FrameChange(float[] arg2)
    {
      float f1 = 100.0F;
      float f2 = -100.0F;
      Object localObject;
      this.pointDeltas = new Vector(localObject.length / 7);
      float f3 = 0.0F;
      float f4 = 0.0F;
      int i = 0;
      if (i < localObject.length)
      {
        float f5 = localObject[(i + 0)] * ObjectTracker.this.downsampleFactor;
        float f6 = localObject[(i + 1)] * ObjectTracker.this.downsampleFactor;
        if (localObject[(i + 2)] > 0.0F);
        for (boolean bool = true; ; bool = false)
        {
          float f7 = localObject[(i + 3)] * ObjectTracker.this.downsampleFactor;
          float f8 = localObject[(i + 4)] * ObjectTracker.this.downsampleFactor;
          float f9 = localObject[(i + 5)];
          int j = (int)localObject[(i + 6)];
          f1 = Math.min(f1, f9);
          f2 = Math.max(f2, f9);
          ObjectTracker.PointChange localPointChange = new ObjectTracker.PointChange(f5, f6, f7, f8, f9, j, bool);
          ObjectTracker.Feature localFeature = localPointChange.getDelta();
          f3 += localFeature.x;
          f4 += localFeature.y;
          this.pointDeltas.add(localPointChange);
          i += 7;
          break;
        }
      }
      this.minScore = f1;
      this.maxScore = f2;
    }
  }

  public static class PointChange
  {
    public final ObjectTracker.Feature featureA;
    public final ObjectTracker.Feature featureB;
    ObjectTracker.Feature pointDelta;
    private final boolean wasFound;

    public PointChange(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, int paramInt, boolean paramBoolean)
    {
      this.wasFound = paramBoolean;
      this.featureA = new ObjectTracker.Feature(paramFloat1, paramFloat2, paramFloat5, paramInt);
      this.featureB = new ObjectTracker.Feature(paramFloat3, paramFloat4);
    }

    public ObjectTracker.Feature getDelta()
    {
      if (this.pointDelta == null)
        this.pointDelta = this.featureB.delta(this.featureA);
      return this.pointDelta;
    }
  }

  private class TimestampedDeltas
  {
    final byte[] deltas;
    final long timestamp;

    public TimestampedDeltas(long arg2, byte[] arg4)
    {
      this.timestamp = ???;
      Object localObject;
      this.deltas = localObject;
    }
  }

  class TrackedObject
  {
    private final String id = Integer.toString(hashCode());
    private boolean isDead = false;
    private long lastExternalPositionTime;
    private RectF lastTrackedPosition;
    private boolean visibleInLastFrame;

    TrackedObject(RectF paramLong, long arg3, byte[] arg5)
    {
      this.lastExternalPositionTime = ???;
      try
      {
        byte[] arrayOfByte1;
        registerAppearance(paramLong, arrayOfByte1);
        setExternalPosition(paramLong, ???);
        ObjectTracker.this.trackedObjects.put(this.id, this);
        return;
      }
      finally
      {
      }
    }

    private void checkValidObject()
    {
      if (this.isDead)
        throw new RuntimeException("TrackedObject already removed from tracking!");
      if (ObjectTracker.this != ObjectTracker.instance)
        throw new RuntimeException("TrackedObject created with another ObjectTracker!");
    }

    private void updateTrackedPosition()
    {
      try
      {
        checkValidObject();
        float[] arrayOfFloat = new float[4];
        ObjectTracker.this.getTrackedPositionNative(this.id, arrayOfFloat);
        this.lastTrackedPosition = new RectF(arrayOfFloat[0], arrayOfFloat[1], arrayOfFloat[2], arrayOfFloat[3]);
        this.visibleInLastFrame = ObjectTracker.this.isObjectVisible(this.id);
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public float getCurrentCorrelation()
    {
      checkValidObject();
      return ObjectTracker.this.getCurrentCorrelation(this.id);
    }

    long getLastExternalPositionTime()
    {
      try
      {
        long l = this.lastExternalPositionTime;
        return l;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    RectF getTrackedPositionInPreviewFrame()
    {
      try
      {
        checkValidObject();
        RectF localRectF1 = this.lastTrackedPosition;
        if (localRectF1 == null);
        RectF localRectF2;
        for (Object localObject2 = null; ; localObject2 = localRectF2)
        {
          return localObject2;
          localRectF2 = ObjectTracker.this.upscaleRect(this.lastTrackedPosition);
        }
      }
      finally
      {
      }
    }

    void registerAppearance(RectF paramRectF, byte[] paramArrayOfByte)
    {
      checkValidObject();
      RectF localRectF = ObjectTracker.this.downscaleRect(paramRectF);
      synchronized (ObjectTracker.this)
      {
        ObjectTracker.this.registerAppearanceInFrameNative(this.id, localRectF.left, localRectF.top, localRectF.right, localRectF.bottom, paramArrayOfByte);
        return;
      }
    }

    void setExternalPosition(RectF paramRectF, long paramLong)
    {
      try
      {
        checkValidObject();
        synchronized (ObjectTracker.this)
        {
          if (this.lastExternalPositionTime > paramLong)
          {
            ObjectTracker.logger.w("Tried to use older position time!", new Object[0]);
            return;
          }
          RectF localRectF = ObjectTracker.this.downscaleRect(paramRectF);
          this.lastExternalPositionTime = paramLong;
          ObjectTracker.this.setPreviousPositionNative(this.id, localRectF.left, localRectF.top, localRectF.right, localRectF.bottom, this.lastExternalPositionTime);
          updateTrackedPosition();
        }
      }
      finally
      {
      }
    }

    void stopTracking()
    {
      checkValidObject();
      synchronized (ObjectTracker.this)
      {
        this.isDead = true;
        ObjectTracker.this.forgetNative(this.id);
        ObjectTracker.this.trackedObjects.remove(this.id);
        return;
      }
    }

    boolean visibleInLastPreviewFrame()
    {
      try
      {
        boolean bool = this.visibleInLastFrame;
        return bool;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.ObjectTracker
 * JD-Core Version:    0.6.2
 */