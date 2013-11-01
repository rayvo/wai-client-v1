package com.google.android.apps.unveil.env;

import android.os.Handler;
import android.os.Message;

public class Timeout
{
  public static final int MSG_TIMEOUT = 1;
  private final Handler handler;
  private final int milliseconds;

  public Timeout(TimeoutCallback paramTimeoutCallback, int paramInt)
  {
    this(paramTimeoutCallback, paramInt, true);
  }

  public Timeout(final TimeoutCallback paramTimeoutCallback, int paramInt, boolean paramBoolean)
  {
    this.handler = new Handler()
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        if (paramAnonymousMessage.what == 1)
          paramTimeoutCallback.onTimeout();
      }
    };
    this.milliseconds = paramInt;
    if (paramBoolean)
      reset();
  }

  public boolean isRunning()
  {
    return this.handler.hasMessages(1);
  }

  public void reset()
  {
    this.handler.removeMessages(1);
    this.handler.sendMessageDelayed(this.handler.obtainMessage(1), this.milliseconds);
  }

  public void stop()
  {
    this.handler.removeMessages(1);
  }

  public static abstract interface TimeoutCallback
  {
    public abstract void onTimeout();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Timeout
 * JD-Core Version:    0.6.2
 */