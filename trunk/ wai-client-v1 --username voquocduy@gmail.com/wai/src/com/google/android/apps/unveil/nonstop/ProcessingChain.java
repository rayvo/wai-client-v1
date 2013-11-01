package com.google.android.apps.unveil.nonstop;

import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ProcessingChain
{
  protected static final UnveilLogger logger = new UnveilLogger("ProcessingChain", "");
  private volatile boolean isProcessing;
  private final ProcessingChain nextChain;
  private final List<FrameProcessor> previewProcessors;
  private final Object readyLock = new Object();
  private volatile boolean stopNow;
  private final Stopwatch stopwatch;

  public ProcessingChain(ProcessingChain paramProcessingChain)
  {
    this.nextChain = paramProcessingChain;
    this.isProcessing = false;
    this.stopwatch = new Stopwatch();
    this.previewProcessors = new ArrayList();
    this.stopwatch.start();
  }

  public void addProcessor(FrameProcessor paramFrameProcessor)
  {
    this.previewProcessors.add(paramFrameProcessor);
  }

  public void blockUntilReadyForFrame()
  {
    while (this.isProcessing)
      waitForNotification();
  }

  public List<FrameProcessor> getProcessors()
  {
    return Collections.unmodifiableList(this.previewProcessors);
  }

  protected boolean isReady()
  {
    return !this.isProcessing;
  }

  protected void pollRunnables(Collection<Runnable> paramCollection)
  {
    Iterator localIterator = this.previewProcessors.iterator();
    while (localIterator.hasNext())
      ((FrameProcessor)localIterator.next()).pollRunnables(paramCollection);
  }

  protected void processFrame(TimestampedFrame paramTimestampedFrame)
  {
    this.stopwatch.reset();
    Iterator localIterator = this.previewProcessors.iterator();
    FrameProcessor localFrameProcessor;
    if (localIterator.hasNext())
    {
      localFrameProcessor = (FrameProcessor)localIterator.next();
      if (!this.stopNow);
    }
    else
    {
      if ((!this.stopNow) && (this.nextChain != null) && (this.nextChain.isReady()))
        break label85;
      paramTimestampedFrame.removeReference();
    }
    while (true)
    {
      setReady(true);
      return;
      localFrameProcessor.processFrame(paramTimestampedFrame);
      break;
      label85: this.nextChain.sendFrame(paramTimestampedFrame);
    }
  }

  protected void sendFrame(TimestampedFrame paramTimestampedFrame)
  {
    this.isProcessing = true;
    processFrame(paramTimestampedFrame);
  }

  protected void setReady(boolean paramBoolean)
  {
    if (!paramBoolean);
    for (boolean bool = true; ; bool = false)
    {
      this.isProcessing = bool;
      if (paramBoolean)
        synchronized (this.readyLock)
        {
          this.readyLock.notifyAll();
          return;
        }
      return;
    }
  }

  protected void shutdown()
  {
    this.stopNow = true;
    while (this.isProcessing)
      waitForNotification();
  }

  protected void start()
  {
    this.stopNow = false;
  }

  protected void stopSoon()
  {
    this.stopNow = true;
  }

  protected void waitForNotification()
  {
    synchronized (this.readyLock)
    {
      try
      {
        this.readyLock.wait();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        while (true)
          logger.e(localInterruptedException, "Exception!", new Object[0]);
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.ProcessingChain
 * JD-Core Version:    0.6.2
 */