package com.google.android.apps.unveil.env;

import android.graphics.RectF;

public class MorphingRect extends RectF
{
  private float bottomDelta;
  private float leftDelta;
  UnveilLogger logger = new UnveilLogger();
  private final float morphDistance;
  private final float morphTime;
  private float rightDelta;
  private final Stopwatch stopwatch = new Stopwatch();
  private float topDelta;

  public MorphingRect(RectF paramRectF, float paramFloat1, float paramFloat2)
  {
    super(paramRectF);
    this.stopwatch.start();
    this.morphDistance = paramFloat1;
    this.morphTime = paramFloat2;
  }

  private float interpolate(float paramFloat1, float paramFloat2)
  {
    float f = Math.abs(paramFloat1);
    if (f <= 0.01F)
      return paramFloat1;
    return paramFloat1 / f * Math.min(paramFloat2 / this.morphTime * this.morphDistance, f);
  }

  public void applyBlended(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.leftDelta = (paramFloat1 + this.leftDelta);
    this.topDelta = (paramFloat2 + this.topDelta);
    this.rightDelta = (paramFloat3 + this.rightDelta);
    this.bottomDelta = (paramFloat4 + this.bottomDelta);
  }

  public void applyInstantly(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    this.left = (paramFloat1 + this.left);
    this.top = (paramFloat2 + this.top);
    this.right = (paramFloat3 + this.right);
    this.bottom = (paramFloat4 + this.bottom);
  }

  public float getArea()
  {
    return width() * height();
  }

  public void morph()
  {
    float f1 = (float)this.stopwatch.getElapsedMilliseconds() / 1000.0F;
    this.stopwatch.reset();
    float f2 = interpolate(this.leftDelta, f1);
    this.leftDelta -= f2;
    this.left = (f2 + this.left);
    float f3 = interpolate(this.topDelta, f1);
    this.topDelta -= f3;
    this.top = (f3 + this.top);
    float f4 = interpolate(this.rightDelta, f1);
    this.rightDelta -= f4;
    this.right = (f4 + this.right);
    float f5 = interpolate(this.bottomDelta, f1);
    this.bottomDelta -= f5;
    this.bottom = (f5 + this.bottom);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.MorphingRect
 * JD-Core Version:    0.6.2
 */