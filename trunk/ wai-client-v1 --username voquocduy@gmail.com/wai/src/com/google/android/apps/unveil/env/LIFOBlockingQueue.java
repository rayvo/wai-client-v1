package com.google.android.apps.unveil.env;

public class LIFOBlockingQueue<E> extends LinkedBlockingDeque<E>
{
  public boolean offer(E paramE)
  {
    return super.offerFirst(paramE);
  }

  public E remove()
  {
    return super.removeFirst();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.LIFOBlockingQueue
 * JD-Core Version:    0.6.2
 */