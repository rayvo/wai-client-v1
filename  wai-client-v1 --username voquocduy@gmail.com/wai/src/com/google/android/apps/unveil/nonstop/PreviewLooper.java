package com.google.android.apps.unveil.nonstop;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.CameraManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class PreviewLooper
  implements Camera.PreviewCallback, TimestampedFrame.BufferSink
{
  private static final long FRAME_SLOP_TIME_MS = 5L;
  private static final int LARGE_TEXT_SIZE = 20;
  private static final int SMALL_TEXT_SIZE = 16;
  private static final int TEXT_BUFFER_SIZE = 4;
  private int activeProcessor = -1;
  private List<FrameProcessor> allPreviewProcessors;
  private final CameraManager cameraManager;
  private boolean delayedRequestPending = false;
  private final Vector<String> emptyLines = new Vector();
  private boolean firstRun;
  private final Stopwatch frameRequestTimer;
  private final Matrix frameToCanvas;
  private final Handler handler;
  private final long interFrameDelayMs;
  private final UnveilLogger logger = new UnveilLogger();
  private int numPreviewFrames;
  private final Paint paint = new Paint();
  private volatile boolean pauseRequested;
  private final Size previewFrameSize;
  private final ProcessingChain[] processingChains;
  private int rotation;
  private volatile boolean running;
  private final Thread uiThread;

  public PreviewLooper(CameraManager paramCameraManager, int paramInt1, float paramFloat, int paramInt2)
  {
    this(paramCameraManager, paramInt1, paramFloat, paramInt2, null);
  }

  public PreviewLooper(CameraManager paramCameraManager, int paramInt1, float paramFloat, int paramInt2, Matrix paramMatrix)
  {
    this.cameraManager = paramCameraManager;
    this.uiThread = Thread.currentThread();
    this.rotation = paramInt1;
    this.frameToCanvas = paramMatrix;
    this.frameRequestTimer = new Stopwatch();
    this.firstRun = true;
    this.running = false;
    this.delayedRequestPending = false;
    this.interFrameDelayMs = (()(1000.0F / paramFloat));
    this.previewFrameSize = paramCameraManager.getPreviewSize();
    this.processingChains = new ProcessingChain[paramInt2];
    this.rotation = paramInt1;
    this.handler = new Handler();
    int i = paramInt2 - 1;
    ProcessingThread localProcessingThread;
    for (Object localObject = null; i > 0; localObject = localProcessingThread)
    {
      int j = Math.max(5 - i, 5);
      localProcessingThread = new ProcessingThread("ProcessingThread " + i, j, (ProcessingChain)localObject);
      this.processingChains[i] = localProcessingThread;
      i--;
    }
    this.processingChains[0] = new ProcessingChain((ProcessingChain)localObject);
  }

  private void initAllProcessors(Size paramSize, Matrix paramMatrix)
  {
    waitUntilNonUiThreadsNotProcessing();
    Iterator localIterator = getAllProcessors().iterator();
    while (localIterator.hasNext())
      ((FrameProcessor)localIterator.next()).init(this.previewFrameSize, paramSize, this.rotation, paramMatrix);
  }

  private void pauseAllProcessors()
  {
    waitUntilNonUiThreadsNotProcessing();
    Iterator localIterator = getAllProcessors().iterator();
    while (localIterator.hasNext())
      ((FrameProcessor)localIterator.next()).pause();
  }

  private boolean pauseIfRequested()
  {
    if (!this.pauseRequested)
      return false;
    this.cameraManager.requestOneFrame(null);
    waitUntilNonUiThreadsNotProcessing();
    runQueuedRunnables();
    pauseAllProcessors();
    this.running = false;
    return true;
  }

  private void requestFrameDelayed()
  {
    while (true)
    {
      try
      {
        if (!this.delayedRequestPending)
        {
          boolean bool = this.running;
          if (bool);
        }
        else
        {
          return;
        }
        this.delayedRequestPending = true;
        long l1 = this.frameRequestTimer.getElapsedMilliseconds();
        long l2 = this.interFrameDelayMs - l1;
        if (l2 > 5L)
        {
          this.handler.postDelayed(new Runnable()
          {
            public void run()
            {
              PreviewLooper.access$002(PreviewLooper.this, false);
              PreviewLooper.this.requestFrameDelayed();
            }
          }
          , l2);
          continue;
        }
      }
      finally
      {
      }
      requestNextFrame();
      this.delayedRequestPending = false;
    }
  }

  private void requestNextFrame()
  {
    this.cameraManager.requestOneFrame(this);
    this.frameRequestTimer.reset();
  }

  private void runQueuedRunnables()
  {
    ArrayList localArrayList = new ArrayList();
    ProcessingChain[] arrayOfProcessingChain = this.processingChains;
    int i = arrayOfProcessingChain.length;
    for (int j = 0; j < i; j++)
      arrayOfProcessingChain[j].pollRunnables(localArrayList);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
      ((Runnable)localIterator.next()).run();
  }

  private void startAllProcessors()
  {
    waitUntilNonUiThreadsNotProcessing();
    Iterator localIterator = getAllProcessors().iterator();
    while (localIterator.hasNext())
      ((FrameProcessor)localIterator.next()).start();
  }

  public static boolean supportNonstopFrameProcessing(Context paramContext)
  {
    return ((ActivityManager)paramContext.getSystemService("activity")).getMemoryClass() >= 12;
  }

  private void waitUntilNonUiThreadsNotProcessing()
  {
    for (int i = 1; i < this.processingChains.length; i++)
      this.processingChains[i].blockUntilReadyForFrame();
  }

  public void addPreviewProcessor(FrameProcessor paramFrameProcessor, int paramInt)
  {
    synchronized (this.processingChains[paramInt])
    {
      this.processingChains[paramInt].addProcessor(paramFrameProcessor);
      this.allPreviewProcessors = null;
      return;
    }
  }

  public boolean changeMode(boolean paramBoolean)
  {
    int i = getAllProcessors().size();
    int j = this.activeProcessor;
    int k;
    int n;
    label70: int i1;
    label73: FrameProcessor localFrameProcessor;
    if (paramBoolean)
    {
      k = 1;
      this.activeProcessor = (k + j);
      int m = i + 2;
      this.activeProcessor = (-1 + (m + (1 + this.activeProcessor)) % m);
      List localList = getAllProcessors();
      if (this.activeProcessor != i)
        break label135;
      n = 1;
      i1 = 0;
      if (i1 >= localList.size())
        break label147;
      localFrameProcessor = (FrameProcessor)localList.get(i1);
      if ((n == 0) && (i1 != this.activeProcessor))
        break label141;
    }
    label135: label141: for (boolean bool = true; ; bool = false)
    {
      localFrameProcessor.setDebugActive(bool);
      i1++;
      break label73;
      k = -1;
      break;
      n = 0;
      break label70;
    }
    label147: return this.activeProcessor >= 0;
  }

  public void drawDebug(Canvas paramCanvas)
  {
    if (this.activeProcessor < 0)
      return;
    List localList = getAllProcessors();
    int i = localList.size();
    int j = 0;
    label23: if (j < localList.size())
      if ((this.activeProcessor != i) && (j != this.activeProcessor))
        break label525;
    label513: label517: label525: for (int i11 = 1; ; i11 = 0)
    {
      if (i11 != 0)
      {
        synchronized ((FrameProcessor)localList.get(j))
        {
          ???.drawDebug(paramCanvas);
        }
        int k;
        int m;
        if (this.activeProcessor == i)
        {
          k = 1;
          m = paramCanvas.getClipBounds().bottom;
        }
        for (int n = 0; ; n++)
        {
          if (n >= localList.size())
            break label517;
          FrameProcessor localFrameProcessor1 = (FrameProcessor)localList.get(n);
          int i1;
          if ((k != 0) || (n == this.activeProcessor))
          {
            i1 = 1;
            label159: if (i1 == 0)
              break label464;
          }
          int i9;
          while (true)
          {
            int i3;
            int i4;
            int i7;
            try
            {
              localVector = localFrameProcessor1.getDebugText();
              int i2 = paramCanvas.getWidth();
              if (k == 0)
                break label473;
              i3 = Math.min(2, localVector.size());
              if (i1 == 0)
                break label494;
              i4 = 1;
              int i5 = 28 + 20 * (i4 + i3);
              m -= i5;
              this.paint.setColor(-16777216);
              this.paint.setAlpha(100);
              int i6 = m;
              Rect localRect = new Rect(0, i6, i2 + 0, i6 + i5);
              paramCanvas.drawRect(localRect, this.paint);
              this.paint.setAlpha(255);
              this.paint.setAntiAlias(true);
              Paint localPaint = this.paint;
              if (i1 == 0)
                break label500;
              i7 = -16711681;
              localPaint.setColor(i7);
              this.paint.setTextSize(20.0F);
              int i8 = i6 + 24;
              paramCanvas.drawText(localFrameProcessor1.getHeaderText(), 0.0F, i8, this.paint);
              if (i1 == 0)
                break label513;
              this.paint.setColor(-1);
              this.paint.setTextSize(16.0F);
              i9 = i8 + 20;
              paramCanvas.drawText(localFrameProcessor1.getTimeStats(), 0.0F, i9, this.paint);
              int i10 = 0;
              if (i10 >= i3)
                break label508;
              i9 += 20;
              paramCanvas.drawText((String)localVector.get(i10), 0.0F, i9, this.paint);
              i10++;
              continue;
              k = 0;
              break;
              i1 = 0;
              break label159;
            }
            finally
            {
            }
            label464: Vector localVector = this.emptyLines;
            continue;
            label473: if (i1 != 0)
            {
              i3 = localVector.size();
            }
            else
            {
              i3 = 0;
              continue;
              label494: i4 = 0;
              continue;
              label500: i7 = -16776961;
            }
          }
          label508: (i9 + 4);
        }
        break;
      }
      j++;
      break label23;
    }
  }

  public List<FrameProcessor> getAllProcessors()
  {
    if (this.allPreviewProcessors == null)
    {
      this.allPreviewProcessors = new ArrayList();
      for (ProcessingChain localProcessingChain : this.processingChains)
        this.allPreviewProcessors.addAll(localProcessingChain.getProcessors());
    }
    return this.allPreviewProcessors;
  }

  public boolean isRunning()
  {
    return this.running;
  }

  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    try
    {
      if (!this.running)
      {
        this.logger.w("Dropping frame due to pause event.", new Object[0]);
        this.cameraManager.requestOneFrame(null);
      }
      while (true)
      {
        return;
        if (!pauseIfRequested())
        {
          runQueuedRunnables();
          this.numPreviewFrames = (1 + this.numPreviewFrames);
          TimestampedFrame localTimestampedFrame = new TimestampedFrame(paramArrayOfByte, this.previewFrameSize, this.numPreviewFrames, SystemClock.uptimeMillis(), this.rotation, this);
          localTimestampedFrame.addReference();
          this.processingChains[0].sendFrame(localTimestampedFrame);
          requestFrameDelayed();
        }
      }
    }
    finally
    {
    }
  }

  public void pauseLoop()
  {
    try
    {
      this.logger.d("pauseLoop", new Object[0]);
      if (!this.running)
        this.logger.w("Pausing a PreviewLooper that was already paused.", new Object[0]);
      while (true)
      {
        return;
        ProcessingChain[] arrayOfProcessingChain = this.processingChains;
        int i = arrayOfProcessingChain.length;
        for (int j = 0; j < i; j++)
          arrayOfProcessingChain[j].stopSoon();
        this.pauseRequested = true;
        if (Thread.currentThread() == this.uiThread)
          pauseIfRequested();
      }
    }
    finally
    {
    }
  }

  public void returnBuffer(byte[] paramArrayOfByte)
  {
    this.cameraManager.addPreviewBuffer(paramArrayOfByte);
  }

  public void shutdown()
  {
    try
    {
      this.logger.i("shutdown", new Object[0]);
      pauseLoop();
      ProcessingChain[] arrayOfProcessingChain = this.processingChains;
      int i = arrayOfProcessingChain.length;
      for (int j = 0; j < i; j++)
        arrayOfProcessingChain[j].shutdown();
      Iterator localIterator = getAllProcessors().iterator();
      while (localIterator.hasNext())
        ((FrameProcessor)localIterator.next()).shutdown();
    }
    finally
    {
    }
  }

  public void startLoop(Size paramSize)
  {
    try
    {
      this.logger.d("startLoop with size %s", new Object[] { paramSize });
      if (this.running)
      {
        this.logger.i("Starting a PreviewLooper that was already started, just resetting preview callback.", new Object[0]);
        this.cameraManager.requestOneFrame(null);
        requestNextFrame();
      }
      while (true)
      {
        return;
        this.running = true;
        this.pauseRequested = false;
        this.frameRequestTimer.start();
        this.delayedRequestPending = false;
        this.numPreviewFrames = 0;
        if (this.firstRun)
          initAllProcessors(paramSize, this.frameToCanvas);
        this.firstRun = false;
        ProcessingChain[] arrayOfProcessingChain = this.processingChains;
        int i = arrayOfProcessingChain.length;
        for (int j = 0; j < i; j++)
          arrayOfProcessingChain[j].start();
        startAllProcessors();
        requestNextFrame();
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.PreviewLooper
 * JD-Core Version:    0.6.2
 */