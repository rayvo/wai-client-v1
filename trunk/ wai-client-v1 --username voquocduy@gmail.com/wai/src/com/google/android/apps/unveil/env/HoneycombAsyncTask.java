package com.google.android.apps.unveil.env;

import android.os.Handler;
import java.util.concurrent.Executor;

public abstract class HoneycombAsyncTask<Result>
{
  protected abstract Result doInBackground();

  public void execute(Executor paramExecutor, final Handler paramHandler)
  {
    paramExecutor.execute(new Runnable()
    {
      public void run()
      {
        final Object localObject = HoneycombAsyncTask.this.doInBackground();
        paramHandler.post(new Runnable()
        {
          public void run()
          {
            HoneycombAsyncTask.this.onPostExecute(localObject);
          }
        });
      }
    });
  }

  protected abstract void onPostExecute(Result paramResult);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.HoneycombAsyncTask
 * JD-Core Version:    0.6.2
 */