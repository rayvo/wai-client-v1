package com.google.android.apps.unveil.nonstop;

import android.util.Pair;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue.ActiveFrame;
import com.google.android.apps.unveil.protocol.nonstop.SimpleEventListener;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.Iterator;
import java.util.List;

public class NetworkStatusMonitor extends SimpleEventListener
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final NetworkStatusListener listener;
  private boolean networkErrorNotified;

  public NetworkStatusMonitor(NetworkStatusListener paramNetworkStatusListener)
  {
    if (paramNetworkStatusListener == null)
      throw new IllegalArgumentException("Must provide a listener");
    this.listener = paramNetworkStatusListener;
  }

  private void onSuccessfulNetworkActivity()
  {
    if (this.networkErrorNotified)
    {
      this.networkErrorNotified = false;
      this.listener.onNetworkRecovered();
    }
  }

  public void onNetworkError(int paramInt)
  {
    if (this.networkErrorNotified)
      return;
    this.networkErrorNotified = true;
    this.listener.onNetworkError(paramInt);
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    onSuccessfulNetworkActivity();
  }

  public void onPullReceived()
  {
    onSuccessfulNetworkActivity();
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
    onSuccessfulNetworkActivity();
  }

  public static final class DispatchingListener
    implements NetworkStatusMonitor.NetworkStatusListener
  {
    private final List<NetworkStatusMonitor.NetworkStatusListener> delegates;

    public DispatchingListener(List<NetworkStatusMonitor.NetworkStatusListener> paramList)
    {
      this.delegates = paramList;
    }

    public void onNetworkError(int paramInt)
    {
      Iterator localIterator = this.delegates.iterator();
      while (localIterator.hasNext())
        ((NetworkStatusMonitor.NetworkStatusListener)localIterator.next()).onNetworkError(paramInt);
    }

    public void onNetworkRecovered()
    {
      Iterator localIterator = this.delegates.iterator();
      while (localIterator.hasNext())
        ((NetworkStatusMonitor.NetworkStatusListener)localIterator.next()).onNetworkRecovered();
    }
  }

  public static abstract interface NetworkStatusListener
  {
    public abstract void onNetworkError(int paramInt);

    public abstract void onNetworkRecovered();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.NetworkStatusMonitor
 * JD-Core Version:    0.6.2
 */