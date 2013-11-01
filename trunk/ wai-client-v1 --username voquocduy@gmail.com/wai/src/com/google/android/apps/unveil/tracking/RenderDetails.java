package com.google.android.apps.unveil.tracking;

import android.graphics.RectF;
import android.os.SystemClock;
import com.google.android.apps.unveil.env.Size;

public class RenderDetails
{
  public final TrackedAnnotation annotation;
  protected long creationTime;
  protected long phaseStartTime;

  public RenderDetails(TrackedAnnotation paramTrackedAnnotation)
  {
    this.annotation = paramTrackedAnnotation;
    this.creationTime = SystemClock.elapsedRealtime();
  }

  protected void animate(long paramLong)
  {
  }

  public long getCreationTime()
  {
    return this.creationTime;
  }

  public RectF getRenderPosition(Size paramSize)
  {
    return this.annotation.getLastKnownPositionInFrame();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.RenderDetails
 * JD-Core Version:    0.6.2
 */