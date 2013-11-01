package com.google.android.apps.unveil.protocol.nonstop;

import android.util.Pair;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.results.ResultItem;
import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public final class DispatchingEventListener
  implements EventListener
{
  private final List<WeakReference<EventListener>> listeners = new LinkedList();

  private void trimListeners()
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      if ((EventListener)((WeakReference)localIterator.next()).get() == null)
        localIterator.remove();
  }

  public void addEventListener(EventListener paramEventListener)
  {
    this.listeners.add(new WeakReference(paramEventListener));
  }

  public void onHighQualityRequest(TimestampedFrame.Metadata paramMetadata)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onHighQualityRequest(paramMetadata);
    }
  }

  public void onNetworkError(int paramInt)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onNetworkError(paramInt);
    }
  }

  public void onNewFrame(TimestampedFrame paramTimestampedFrame)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onNewFrame(paramTimestampedFrame);
    }
  }

  public void onNewRequest(TimestampedFrame.Metadata paramMetadata)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onNewRequest(paramMetadata);
    }
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onNewResults(paramList);
    }
  }

  public void onPullReceived()
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onPullReceived();
    }
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onPushReceived(paramMetadata);
    }
  }

  public void onPushSent(TimestampedFrame.Metadata paramMetadata, byte[] paramArrayOfByte)
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onPushSent(paramMetadata, paramArrayOfByte);
    }
  }

  public void onSessionReset()
  {
    trimListeners();
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
    {
      EventListener localEventListener = (EventListener)((WeakReference)localIterator.next()).get();
      if (localEventListener != null)
        localEventListener.onSessionReset();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.DispatchingEventListener
 * JD-Core Version:    0.6.2
 */