package com.google.android.apps.unveil.nonstop;

import android.util.Pair;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue.ActiveFrame;
import com.google.android.apps.unveil.protocol.nonstop.SimpleEventListener;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.Iterator;
import java.util.List;

public class SceneDetector extends SimpleEventListener
{
  private static final int SIMILAR_THRESHOLD = 50;
  private static final UnveilLogger logger = new UnveilLogger();
  private int[] lastSceneSignature;
  private long lastSceneStartMillis;
  private final OnEventListener listener;

  public SceneDetector(OnEventListener paramOnEventListener)
  {
    if (paramOnEventListener == null)
      throw new IllegalArgumentException("Must provide a listener");
    this.listener = paramOnEventListener;
  }

  private boolean belongsToCurrentScene(TimestampedFrame.Metadata paramMetadata)
  {
    return paramMetadata.getTimestamp() >= this.lastSceneStartMillis;
  }

  private void onNewScene(TimestampedFrame paramTimestampedFrame)
  {
    this.lastSceneSignature = paramTimestampedFrame.getSignature();
    this.lastSceneStartMillis = paramTimestampedFrame.getTimestamp();
    this.listener.onSceneChanged();
  }

  public void onNewFrame(TimestampedFrame paramTimestampedFrame)
  {
    if (this.lastSceneSignature == null)
      onNewScene(paramTimestampedFrame);
    int[] arrayOfInt;
    do
    {
      return;
      arrayOfInt = paramTimestampedFrame.getSignature();
    }
    while (TimestampedFrame.diffSignature(this.lastSceneSignature, arrayOfInt) <= 50);
    onNewScene(paramTimestampedFrame);
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      if (localPair.second != null)
        this.listener.onResult((ResultItem)localPair.first);
    }
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
    if (belongsToCurrentScene(paramMetadata))
      this.listener.onScenePushed();
  }

  public static final class DispatchingListener
    implements SceneDetector.OnEventListener
  {
    private final List<SceneDetector.OnEventListener> delegates;

    public DispatchingListener(List<SceneDetector.OnEventListener> paramList)
    {
      this.delegates = paramList;
    }

    public void onResult(ResultItem paramResultItem)
    {
      Iterator localIterator = this.delegates.iterator();
      while (localIterator.hasNext())
        ((SceneDetector.OnEventListener)localIterator.next()).onResult(paramResultItem);
    }

    public void onSceneChanged()
    {
      Iterator localIterator = this.delegates.iterator();
      while (localIterator.hasNext())
        ((SceneDetector.OnEventListener)localIterator.next()).onSceneChanged();
    }

    public void onScenePushed()
    {
      Iterator localIterator = this.delegates.iterator();
      while (localIterator.hasNext())
        ((SceneDetector.OnEventListener)localIterator.next()).onScenePushed();
    }
  }

  public static abstract interface OnEventListener
  {
    public abstract void onResult(ResultItem paramResultItem);

    public abstract void onSceneChanged();

    public abstract void onScenePushed();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.SceneDetector
 * JD-Core Version:    0.6.2
 */