package com.google.android.apps.unveil.ui;

import android.os.Handler;
import android.os.Message;
import android.view.animation.Animation.AnimationListener;
import com.google.android.apps.unveil.env.UnveilLogger;

public abstract class DirectedAlphaAnimationHelper
{
  private static final long FRAME_DELAY_MS = 16L;
  private static final int MSG_ANIMATE = 999;
  private static final UnveilLogger logger = new UnveilLogger();
  private boolean animating;
  private int animationDuration;
  private float animationFrom;
  private long animationStartTime;
  private float animationTo;
  private Animation.AnimationListener currentListener;
  private final Handler handler = new AnimationHandler(null);

  public abstract void clearAnimation();

  public void doAnimation()
  {
    long l;
    if (this.animating)
    {
      l = System.currentTimeMillis() - this.animationStartTime;
      if (l + 16L > this.animationDuration)
      {
        handleAlpha(this.animationTo);
        this.currentListener.onAnimationEnd(null);
        clearAnimation();
        this.animating = false;
      }
    }
    else
    {
      return;
    }
    handleAlpha((float)l / this.animationDuration * (this.animationTo - this.animationFrom) + this.animationFrom);
    this.handler.sendMessageDelayed(this.handler.obtainMessage(999), 16L);
  }

  public abstract void handleAlpha(float paramFloat);

  public boolean isAnimating()
  {
    return this.animating;
  }

  public final void startAnimation(float paramFloat1, float paramFloat2, Animation.AnimationListener paramAnimationListener, int paramInt)
  {
    this.animating = true;
    this.animationStartTime = System.currentTimeMillis();
    this.animationDuration = paramInt;
    this.animationFrom = paramFloat1;
    this.animationTo = paramFloat2;
    this.currentListener = paramAnimationListener;
    this.handler.removeMessages(999);
    this.handler.dispatchMessage(this.handler.obtainMessage(999));
    paramAnimationListener.onAnimationStart(null);
  }

  private class AnimationHandler extends Handler
  {
    private AnimationHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default:
        return;
      case 999:
      }
      DirectedAlphaAnimationHelper.this.doAnimation();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.DirectedAlphaAnimationHelper
 * JD-Core Version:    0.6.2
 */