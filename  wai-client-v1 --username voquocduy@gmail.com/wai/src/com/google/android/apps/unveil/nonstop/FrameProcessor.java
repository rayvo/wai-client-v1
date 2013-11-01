package com.google.android.apps.unveil.nonstop;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.SystemClock;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.NumberUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.TracingProtos.ProcessorStatus;
import com.google.goggles.TracingProtos.ProcessorStatus.Builder;
import com.google.goggles.TracingProtos.ProcessorStatus.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;

public abstract class FrameProcessor
{
  public static final int DUTY_CYCLE_ALL = 1;
  public static final int DUTY_CYCLE_NONE = 2147483647;
  private boolean debugActive;
  private int dutyCycleCounter = 0;
  private int dutyCyclePeriod = 1;
  private Matrix frameToCanvas;
  private boolean initialized;
  private boolean isFrameDiscarded;
  private boolean isProcessingEnabled = true;
  private long lastFrameTime;
  private final UnveilLogger logger = new UnveilLogger();
  private Size previewFrameSize;
  private final ArrayList<Runnable> queuedRunnables = new ArrayList();
  private int rotation;
  private final TimeStats timeStats = new TimeStats();
  private Size viewSize;

  protected void discardFrame()
  {
    this.isFrameDiscarded = true;
  }

  protected final void drawDebug(Canvas paramCanvas)
  {
    onDrawDebug(paramCanvas);
  }

  public Vector<String> getDebugText()
  {
    return new Vector();
  }

  protected int getDutyCycle()
  {
    return this.dutyCyclePeriod;
  }

  protected Matrix getFrameToCanvasMatrix()
  {
    return this.frameToCanvas;
  }

  protected String getHeaderText()
  {
    return getName();
  }

  protected long getLastFrameTime()
  {
    return this.lastFrameTime;
  }

  protected String getName()
  {
    String str = getClass().getSimpleName();
    if (str.length() > 0)
      return str;
    return "<anonymous>";
  }

  protected Size getPreviewFrameSize()
  {
    return this.previewFrameSize;
  }

  protected TracingProtos.ProcessorStatus.Type getProcessorType()
  {
    return null;
  }

  protected int getRotation()
  {
    return this.rotation;
  }

  public TracingProtos.ProcessorStatus getStatus()
  {
    TracingProtos.ProcessorStatus.Type localType = getProcessorType();
    if (localType == null)
      return null;
    TracingProtos.ProcessorStatus.Builder localBuilder = TracingProtos.ProcessorStatus.newBuilder().setType(localType).setNumFrames(this.timeStats.numFrames).setCumulativeProcessTimeMs((int)this.timeStats.totalProcessTime);
    if (this.dutyCyclePeriod > 1)
      localBuilder.setDutyPeriod(this.dutyCyclePeriod);
    return localBuilder.build();
  }

  public float getTimePerFrame()
  {
    return this.timeStats.timePerFrame();
  }

  public String getTimeStats()
  {
    return this.timeStats.toString();
  }

  public Size getViewSize()
  {
    return this.viewSize;
  }

  public final void init(Size paramSize1, Size paramSize2, int paramInt, Matrix paramMatrix)
  {
    try
    {
      this.initialized = true;
      this.previewFrameSize = paramSize1;
      this.viewSize = paramSize2;
      this.rotation = paramInt;
      if (paramMatrix != null);
      for (this.frameToCanvas = paramMatrix; ; this.frameToCanvas = ImageUtils.getTransformationMatrix(paramSize1, paramSize2, paramInt))
      {
        this.lastFrameTime = -1L;
        onInit(paramSize1);
        return;
        this.logger.w("Null frameToCanvas Matrix, debug drawing may not line up!", new Object[0]);
      }
    }
    finally
    {
    }
  }

  public boolean isDebugActive()
  {
    return this.debugActive;
  }

  protected final boolean isInitialized()
  {
    return this.initialized;
  }

  protected void onDrawDebug(Canvas paramCanvas)
  {
  }

  protected void onInit(Size paramSize)
  {
  }

  protected void onPause()
  {
  }

  protected abstract void onProcessFrame(TimestampedFrame paramTimestampedFrame);

  protected void onShutdown()
  {
  }

  protected void onStart()
  {
  }

  protected final void pause()
  {
    try
    {
      this.queuedRunnables.clear();
      onPause();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected final void pollRunnables(Collection<Runnable> paramCollection)
  {
    synchronized (this.queuedRunnables)
    {
      if (this.queuedRunnables.size() > 0)
      {
        paramCollection.addAll(this.queuedRunnables);
        this.queuedRunnables.clear();
      }
      return;
    }
  }

  protected final void processFrame(TimestampedFrame paramTimestampedFrame)
  {
    try
    {
      if (this.dutyCycleCounter == 0)
      {
        this.isFrameDiscarded = false;
        if (this.isProcessingEnabled)
        {
          long l1 = SystemClock.currentThreadTimeMillis();
          onProcessFrame(paramTimestampedFrame);
          long l2 = SystemClock.currentThreadTimeMillis() - l1;
          if (!this.isFrameDiscarded)
            this.timeStats.addFrame(l2);
        }
        this.lastFrameTime = paramTimestampedFrame.getTimestamp();
      }
      this.dutyCycleCounter = ((1 + this.dutyCycleCounter) % this.dutyCyclePeriod);
      return;
    }
    finally
    {
    }
  }

  protected final void runOnUiThreadBeforeNextFrame(Runnable paramRunnable)
  {
    synchronized (this.queuedRunnables)
    {
      this.queuedRunnables.add(paramRunnable);
      return;
    }
  }

  protected void setDebugActive(boolean paramBoolean)
  {
    this.debugActive = paramBoolean;
  }

  public void setDutyCycle(int paramInt)
  {
    this.dutyCyclePeriod = paramInt;
  }

  public void setProcessingEnabled(boolean paramBoolean)
  {
    try
    {
      this.isProcessingEnabled = paramBoolean;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected final void shutdown()
  {
    try
    {
      this.initialized = false;
      onShutdown();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected final void start()
  {
    try
    {
      if (this.queuedRunnables.size() > 0)
      {
        this.logger.e("Left over queued runnables from last time!", new Object[0]);
        this.queuedRunnables.clear();
      }
      onStart();
      return;
    }
    finally
    {
    }
  }

  class TimeStats
  {
    private int numFrames = 0;
    private float runningAverageProcessTimePerFrame = 0.0F;
    private long totalProcessTime = 0L;

    TimeStats()
    {
    }

    private void addFrame(long paramLong)
    {
      this.numFrames = (1 + this.numFrames);
      this.totalProcessTime = (paramLong + this.totalProcessTime);
      float f = (float)this.totalProcessTime / this.numFrames;
      if (this.numFrames == 1)
      {
        this.runningAverageProcessTimePerFrame = f;
        return;
      }
      this.runningAverageProcessTimePerFrame = weightedAverage(this.runningAverageProcessTimePerFrame, (float)paramLong, 0.95F);
    }

    private float weightedAverage(float paramFloat1, float paramFloat2, float paramFloat3)
    {
      return paramFloat3 * paramFloat1 + paramFloat2 * (1.0F - paramFloat3);
    }

    public float timePerFrame()
    {
      if (this.numFrames <= 0)
        return 0.0F;
      return (float)this.totalProcessTime / this.numFrames;
    }

    public String toString()
    {
      if (FrameProcessor.this.isProcessingEnabled)
        return "[" + this.numFrames + " frames] [" + this.totalProcessTime + "ms total] [" + NumberUtils.format(this.runningAverageProcessTimePerFrame, 2) + "]";
      return "[DISABLED]";
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.FrameProcessor
 * JD-Core Version:    0.6.2
 */