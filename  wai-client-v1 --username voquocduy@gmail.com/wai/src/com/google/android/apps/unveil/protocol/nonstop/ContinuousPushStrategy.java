package com.google.android.apps.unveil.protocol.nonstop;

import android.os.SystemClock;
import com.google.android.apps.unveil.env.Clock;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.ContinuousNetworkParams;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.protocol.RequestPipeline.PushStrategy;

public class ContinuousPushStrategy
  implements RequestPipeline.PushStrategy
{
  private static final boolean FORCE_ALL_FRAMES_ACCEPTABLE = true;
  private static final long MAX_UPLOAD_DELAY = 2000L;
  private static final float MAX_UPLOAD_MOVEMENT = 5.0F;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Clock clock;
  private long lastPushTimeMs;
  private final ContinuousNetworkParams params;

  public ContinuousPushStrategy(Clock paramClock, ContinuousNetworkParams paramContinuousNetworkParams)
  {
    this.clock = paramClock;
    this.params = paramContinuousNetworkParams;
  }

  protected boolean isFrameAcceptable(TimestampedFrame paramTimestampedFrame)
  {
    return (paramTimestampedFrame.getOpticalFlowDelta() < 5.0F) && (!paramTimestampedFrame.isBlurred());
  }

  protected boolean isLastPushRecent(long paramLong)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(this.clock.now() - paramLong);
    localUnveilLogger.d("is last push recent? %d ago", arrayOfObject);
    return this.clock.now() - paramLong < this.params.interPushDelayMs;
  }

  protected boolean mustPush(long paramLong)
  {
    long l = SystemClock.uptimeMillis() - paramLong;
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(l);
    localUnveilLogger.d("Must push? timeSinceLastRequest=%d", arrayOfObject);
    return l > 2000L;
  }

  public void onPush(TimestampedFrame paramTimestampedFrame)
  {
    logger.d("Updating lastPushTimeMs", new Object[0]);
    this.lastPushTimeMs = this.clock.now();
  }

  public void onReset()
  {
    logger.d("Resetting lastPushTimeMs", new Object[0]);
    this.lastPushTimeMs = 0L;
  }

  public boolean shouldPush(TimestampedFrame paramTimestampedFrame)
  {
    return !isLastPushRecent(this.lastPushTimeMs);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.ContinuousPushStrategy
 * JD-Core Version:    0.6.2
 */