package com.google.android.apps.unveil.protocol.nonstop;

import android.util.Pair;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.List;

public abstract interface EventListener
{
  public abstract void onHighQualityRequest(TimestampedFrame.Metadata paramMetadata);

  public abstract void onNetworkError(int paramInt);

  public abstract void onNewFrame(TimestampedFrame paramTimestampedFrame);

  public abstract void onNewRequest(TimestampedFrame.Metadata paramMetadata);

  public abstract void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList);

  public abstract void onPullReceived();

  public abstract void onPushReceived(TimestampedFrame.Metadata paramMetadata);

  public abstract void onPushSent(TimestampedFrame.Metadata paramMetadata, byte[] paramArrayOfByte);

  public abstract void onSessionReset();
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.EventListener
 * JD-Core Version:    0.6.2
 */