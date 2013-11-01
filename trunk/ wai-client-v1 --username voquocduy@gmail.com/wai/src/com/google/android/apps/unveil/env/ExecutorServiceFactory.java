package com.google.android.apps.unveil.env;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorServiceFactory
{
  private static final UnveilLogger logger = new UnveilLogger();

  private static ExecutorService newFifoLoggingExecutor(int paramInt1, int paramInt2)
  {
    return newLoggingExecutor(paramInt1, paramInt2, new LinkedBlockingQueue());
  }

  private static ExecutorService newLifoLoggingExecutor(int paramInt1, int paramInt2)
  {
    return newLoggingExecutor(paramInt1, paramInt2, new LIFOBlockingQueue());
  }

  private static ExecutorService newLoggingExecutor(int paramInt1, int paramInt2, BlockingQueue<Runnable> paramBlockingQueue)
  {
    return new ThreadPoolExecutor(paramInt1, paramInt1, paramInt2, TimeUnit.SECONDS, paramBlockingQueue)
    {
      protected void afterExecute(Runnable paramAnonymousRunnable, Throwable paramAnonymousThrowable)
      {
        super.afterExecute(paramAnonymousRunnable, paramAnonymousThrowable);
        if ((paramAnonymousThrowable == null) && ((paramAnonymousRunnable instanceof Future)));
        try
        {
          ((Future)paramAnonymousRunnable).get();
          if (paramAnonymousThrowable != null)
            ExecutorServiceFactory.logger.e(paramAnonymousThrowable, "Uncaught executor service background exception: %s", new Object[] { paramAnonymousThrowable });
          return;
        }
        catch (ExecutionException localExecutionException)
        {
          while (true)
            paramAnonymousThrowable = localExecutionException.getCause();
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            Thread.currentThread().interrupt();
        }
      }
    };
  }

  public static ExecutorService newLoggingExecutor(Order paramOrder, int paramInt1, int paramInt2)
  {
    switch (2.$SwitchMap$com$google$android$apps$unveil$env$ExecutorServiceFactory$Order[paramOrder.ordinal()])
    {
    default:
      throw new AssertionError();
    case 1:
      return newFifoLoggingExecutor(paramInt1, paramInt2);
    case 2:
    }
    return newLifoLoggingExecutor(paramInt1, paramInt2);
  }

  static enum Order
  {
    static
    {
      Order[] arrayOfOrder = new Order[2];
      arrayOfOrder[0] = FIFO;
      arrayOfOrder[1] = LIFO;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.ExecutorServiceFactory
 * JD-Core Version:    0.6.2
 */