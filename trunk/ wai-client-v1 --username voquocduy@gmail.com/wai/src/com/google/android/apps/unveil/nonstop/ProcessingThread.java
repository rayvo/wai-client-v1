package com.google.android.apps.unveil.nonstop;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class ProcessingThread extends ProcessingChain
  implements Runnable
{
  private static final int STOP_CODE = -1;
  private Handler processingHandler;
  private final String threadName;
  private final int threadPriority;

  public ProcessingThread(String paramString, int paramInt, ProcessingChain paramProcessingChain)
  {
    super(paramProcessingChain);
    this.threadName = paramString;
    this.threadPriority = paramInt;
  }

  public void run()
  {
    Looper.prepare();
    this.processingHandler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what == -1)
        {
          Looper.myLooper().quit();
          return;
        }
        TimestampedFrame localTimestampedFrame = (TimestampedFrame)paramAnonymousMessage.obj;
        ProcessingThread.this.processFrame(localTimestampedFrame);
      }
    };
    setReady(true);
    Looper.loop();
    this.processingHandler = null;
    setReady(true);
  }

  protected void sendFrame(TimestampedFrame paramTimestampedFrame)
  {
    setReady(false);
    Message localMessage = new Message();
    localMessage.obj = paramTimestampedFrame;
    this.processingHandler.sendMessage(localMessage);
  }

  protected void shutdown()
  {
    if (this.processingHandler != null)
      this.processingHandler.sendEmptyMessage(-1);
    super.shutdown();
  }

  protected void start()
  {
    super.start();
    if (this.processingHandler == null)
    {
      Thread localThread = new Thread(this);
      localThread.setName(this.threadName);
      localThread.setPriority(this.threadPriority);
      localThread.start();
      while (this.processingHandler == null)
        waitForNotification();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.ProcessingThread
 * JD-Core Version:    0.6.2
 */