package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.TouchDelegate;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingImageView extends ImageView
{
  private static final int DEFAULT_TOUCH_PADDING_DIP;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Matrix identityMatrix = new Matrix();
  private int orientationDegrees;
  private final Matrix rotationMatrix = new Matrix();
  private final int touchPadding;

  public RotatingImageView(Context paramContext)
  {
    super(paramContext);
    this.touchPadding = ((int)(0.0F * getResources().getDisplayMetrics().density));
    init();
  }

  public RotatingImageView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingImageView, 0, 0);
    this.touchPadding = localTypedArray.getDimensionPixelSize(0, (int)(0.0F * getResources().getDisplayMetrics().density));
    localTypedArray.recycle();
    init();
  }

  private void addTouchDelegate()
  {
    post(new Runnable()
    {
      public void run()
      {
        Rect localRect = new Rect();
        RotatingImageView.this.getHitRect(localRect);
        localRect.top -= RotatingImageView.this.touchPadding;
        localRect.bottom += RotatingImageView.this.touchPadding;
        localRect.left -= RotatingImageView.this.touchPadding;
        localRect.right += RotatingImageView.this.touchPadding;
        TouchDelegate localTouchDelegate = new TouchDelegate(localRect, RotatingImageView.this);
        ((View)RotatingImageView.this.getParent()).setTouchDelegate(localTouchDelegate);
      }
    });
  }

  private void setRotation(int paramInt)
  {
    this.orientationDegrees = paramInt;
    this.rotationMatrix.reset();
    this.rotationMatrix.preRotate(this.orientationDegrees, getWidth() / 2, getHeight() / 2);
    Drawable localDrawable = getDrawable();
    if (localDrawable != null)
    {
      int i = localDrawable.getIntrinsicWidth();
      int j = localDrawable.getIntrinsicHeight();
      int k = (getWidth() - i) / 2;
      int m = (getHeight() - j) / 2;
      this.rotationMatrix.preTranslate(k, m);
    }
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.rotationMatrix;
    localUnveilLogger.d("Rotating a RIV with matrix: %s", arrayOfObject);
    setImageMatrix(this.rotationMatrix);
  }

  protected void init()
  {
    setScaleType(ImageView.ScaleType.MATRIX);
    setImageMatrix(new Matrix());
    if (this.touchPadding > 0)
      addTouchDelegate();
  }

  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    setRotation(0);
  }

  public void rotateTo(final int paramInt)
  {
    if ((getWidth() == 0) || (getHeight() == 0) || (paramInt == this.orientationDegrees))
      return;
    if (getVisibility() != 0)
    {
      setRotation(paramInt);
      return;
    }
    int i = this.orientationDegrees - paramInt;
    if (i < -180)
      i += 360;
    while (true)
    {
      RotateAnimation localRotateAnimation = new RotateAnimation(i, 0.0F, 1, 0.5F, 1, 0.5F);
      localRotateAnimation.setDuration(400L);
      localRotateAnimation.setInterpolator(new DecelerateInterpolator());
      localRotateAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          RotatingImageView.this.setRotation(paramInt);
        }
      });
      startAnimation(localRotateAnimation);
      return;
      if (i > 180)
        i -= 360;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingImageView
 * JD-Core Version:    0.6.2
 */