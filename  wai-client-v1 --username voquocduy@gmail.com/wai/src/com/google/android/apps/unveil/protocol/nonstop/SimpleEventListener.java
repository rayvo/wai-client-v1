package com.google.android.apps.unveil.protocol.nonstop;

import android.util.Pair;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.List;

public class SimpleEventListener
  implements EventListener
{
  public void onHighQualityRequest(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onNetworkError(int paramInt)
  {
  }

  public void onNewFrame(TimestampedFrame paramTimestampedFrame)
  {
  }

  public void onNewRequest(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
  }

  public void onPullReceived()
  {
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onPushSent(TimestampedFrame.Metadata paramMetadata, byte[] paramArrayOfByte)
  {
  }

  public void onSessionReset()
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.SimpleEventListener
 * JD-Core Version:    0.6.2
 */