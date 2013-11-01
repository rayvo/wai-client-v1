package com.google.android.apps.unveil.env;

import android.os.Handler;
import java.util.concurrent.Executor;

public class HandlerToExecutorAdapter
  implements Executor
{
  private final Handler handler;

  public HandlerToExecutorAdapter(Handler paramHandler)
  {
    this.handler = paramHandler;
  }

  public void execute(Runnable paramRunnable)
  {
    this.handler.post(paramRunnable);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.HandlerToExecutorAdapter
 * JD-Core Version:    0.6.2
 */