package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import com.google.android.apps.unveil.R.anim;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RegionSelectorView extends View
{
  public static final int GRABBY_PADDING_DIP = 8;
  public static final float HYSTERESIS_DIP = 40.0F;
  public static final int MIN_HEIGHT_DIP = 30;
  public static final int MIN_RECT_MARGIN = 4;
  public static final int MIN_WIDTH_DIP = 30;
  public static final int OUTLINE_COLOR = -3551489;
  public static final int RESIZE_BOTTOM_EDGE = 16;
  public static final int RESIZE_LEFT_EDGE = 2;
  public static final int RESIZE_NONE = 1;
  public static final int RESIZE_RIGHT_EDGE = 4;
  public static final int RESIZE_TOP_EDGE = 8;
  public static final int START_PADDING = 50;
  public static final int STROKE_WIDTH = 2;
  private static final UnveilLogger logger = new UnveilLogger();
  private boolean alreadyCalledMarginCallback = false;
  private Rect animationTargetRect;
  private int callbackMargin;
  protected ResizeState currentResizeState = new ResizeState();
  private Rect defaultAnimationTargetRect;
  protected float density;
  private DragEventsCallback dragEventsCallback;
  private RequestAutoFocusCallback focusCallback;
  private final Paint noFocusPaint = new Paint();
  protected int offsetBottom = 0;
  protected int offsetLeft = 0;
  protected int offsetRight = 0;
  protected int offsetTop = 0;
  private final Paint outlinePaint = new Paint();
  protected Rect region;
  private Drawable resizeDrawable;

  public RegionSelectorView(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public RegionSelectorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  private void checkForMarginCallback()
  {
    if ((this.dragEventsCallback != null) && (!this.alreadyCalledMarginCallback) && (this.region.left <= this.callbackMargin))
    {
      this.dragEventsCallback.onReachedMargin();
      this.alreadyCalledMarginCallback = true;
    }
  }

  protected static Rect computeDefaultRect(boolean paramBoolean, Rect paramRect)
  {
    Rect localRect = new Rect(paramRect);
    if (-100 + (paramRect.bottom - paramRect.top) < 0)
      return localRect;
    if (paramBoolean)
    {
      localRect.top = (50 + localRect.top);
      localRect.bottom = (-50 + localRect.bottom);
      return localRect;
    }
    localRect.left = (50 + localRect.left);
    localRect.right = (-50 + localRect.right);
    return localRect;
  }

  private void handleTouchDownAt(float paramFloat1, float paramFloat2)
  {
    updateResizeState(paramFloat1, paramFloat2);
    int i = (int)paramFloat1;
    int j = (int)paramFloat2;
    if (this.currentResizeState.resizeTop)
      this.offsetTop = (this.region.top - j);
    if (this.currentResizeState.resizeBottom)
      this.offsetBottom = (this.region.bottom - j);
    if (this.currentResizeState.resizeLeft)
      this.offsetLeft = (this.region.left - i);
    if (this.currentResizeState.resizeRight)
      this.offsetRight = (this.region.right - i);
    handleTouchAt(paramFloat1, paramFloat2);
  }

  private void init(Context paramContext)
  {
    this.density = paramContext.getResources().getDisplayMetrics().density;
    this.resizeDrawable = getResources().getDrawable(R.drawable.crop_handle);
    this.noFocusPaint.setARGB(125, 50, 50, 50);
    this.outlinePaint.setStrokeWidth(2.0F);
    this.outlinePaint.setStyle(Paint.Style.STROKE);
    this.outlinePaint.setAntiAlias(true);
    this.outlinePaint.setColor(-3551489);
  }

  private boolean isVisible()
  {
    return getVisibility() == 0;
  }

  private void maybeRequestAutoFocus()
  {
    if (this.focusCallback == null)
      return;
    logger.v("Requesting auto focus.", new Object[0]);
    this.focusCallback.onRequestAutoFocus();
  }

  public boolean containsRect(Rect paramRect)
  {
    return (this.region == null) || (!isVisible()) || (this.region.contains(paramRect));
  }

  public Rect getRegion()
  {
    if (this.region == null)
      return null;
    return new Rect(this.region);
  }

  protected boolean handleTouchAt(float paramFloat1, float paramFloat2)
  {
    updateResizeState(paramFloat1, paramFloat2);
    int i = (int)paramFloat1;
    int j = (int)paramFloat2;
    Rect localRect = new Rect(this.region);
    if (this.currentResizeState.resizeLeft)
    {
      int i5 = i - this.region.left + this.offsetLeft;
      localRect.left = (i5 + localRect.left);
      localRect.right -= i5;
    }
    if (this.currentResizeState.resizeRight)
    {
      int i4 = i - this.region.right + this.offsetRight;
      localRect.right = (i4 + localRect.right);
      localRect.left -= i4;
    }
    if (this.currentResizeState.resizeTop)
    {
      int i3 = j - this.region.top + this.offsetTop;
      localRect.top = (i3 + localRect.top);
      localRect.bottom -= i3;
    }
    if (this.currentResizeState.resizeBottom)
    {
      int i2 = j - this.region.bottom + this.offsetBottom;
      localRect.bottom = (i2 + localRect.bottom);
      localRect.top -= i2;
    }
    int k = getWidth();
    int m = getHeight();
    if (localRect.top < 4)
    {
      localRect.top = 4;
      localRect.bottom = (m - 4);
    }
    if (localRect.left < 4)
    {
      localRect.left = 4;
      localRect.right = (k - 4);
    }
    int n = (int)(30.0F * this.density);
    if (localRect.height() < n)
    {
      localRect.top = ((m - n) / 2);
      localRect.bottom = (n + localRect.top);
    }
    int i1 = (int)(30.0F * this.density);
    if (localRect.width() < i1)
    {
      localRect.left = ((k - i1) / 2);
      localRect.right = (i1 + localRect.left);
    }
    this.region = localRect;
    return true;
  }

  public void hide(boolean paramBoolean)
  {
    if (paramBoolean)
      setVisibility(4);
    while (!isVisible())
      return;
    Animation localAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.roi_alpha_out);
    localAnimation.setAnimationListener(new Animation.AnimationListener()
    {
      public void onAnimationEnd(Animation paramAnonymousAnimation)
      {
        RegionSelectorView.this.setVisibility(4);
        if (RegionSelectorView.this.dragEventsCallback != null)
          RegionSelectorView.this.dragEventsCallback.onAnimationEvent(false, false);
      }

      public void onAnimationRepeat(Animation paramAnonymousAnimation)
      {
      }

      public void onAnimationStart(Animation paramAnonymousAnimation)
      {
        if (RegionSelectorView.this.dragEventsCallback != null)
          RegionSelectorView.this.dragEventsCallback.onAnimationEvent(true, false);
      }
    });
    startAnimation(localAnimation);
  }

  public boolean isLandscape(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    return paramInt3 - paramInt1 > paramInt4 - paramInt2;
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    paramCanvas.drawRect(0.0F, 0.0F, getWidth(), this.region.top, this.noFocusPaint);
    paramCanvas.drawRect(0.0F, this.region.bottom, getWidth(), getHeight(), this.noFocusPaint);
    paramCanvas.drawRect(0.0F, this.region.top, this.region.left, this.region.bottom, this.noFocusPaint);
    paramCanvas.drawRect(this.region.right, this.region.top, getWidth(), this.region.bottom, this.noFocusPaint);
    paramCanvas.drawRect(this.region, this.outlinePaint);
    int i = this.region.left;
    int j = this.region.right;
    int k = this.region.top;
    int m = this.region.bottom;
    int n = this.resizeDrawable.getIntrinsicWidth() / 2;
    int i1 = this.resizeDrawable.getIntrinsicHeight() / 2;
    this.resizeDrawable.setBounds(i - n, k - i1, i + n, k + i1);
    this.resizeDrawable.draw(paramCanvas);
    this.resizeDrawable.setBounds(j - n, m - n, j + n, m + i1);
    this.resizeDrawable.draw(paramCanvas);
    this.resizeDrawable.setBounds(j - n, k - i1, j + n, k + i1);
    this.resizeDrawable.draw(paramCanvas);
    this.resizeDrawable.setBounds(i - n, m - n, i + n, m + i1);
    this.resizeDrawable.draw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if ((this.region == null) || (paramBoolean))
    {
      if ((this.animationTargetRect == null) || (paramBoolean))
      {
        int i = (paramInt3 - paramInt1) / 2;
        int j = (paramInt4 - paramInt2) / 2;
        int k = Math.min(i - 50, j - 50);
        int m = i - k;
        int n = j - k;
        this.animationTargetRect = new Rect(m, n, i * 2 - m, j * 2 - n);
        this.defaultAnimationTargetRect = computeDefaultRect(isLandscape(paramInt1, paramInt2, paramInt3, paramInt4), this.animationTargetRect);
      }
      this.region = new Rect(paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }

  protected boolean onSetAlpha(int paramInt)
  {
    if (paramInt == 255)
      return false;
    if (this.animationTargetRect != null)
    {
      int i = getWidth();
      int j = getHeight();
      this.region.left = (paramInt * this.animationTargetRect.left / 255);
      this.region.top = (paramInt * this.animationTargetRect.top / 255);
      this.region.right = (i - paramInt * (i - this.animationTargetRect.right) / 255);
      this.region.bottom = (j - paramInt * (j - this.animationTargetRect.bottom) / 255);
      invalidate();
    }
    return true;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    float f1 = paramMotionEvent.getX();
    float f2 = paramMotionEvent.getY();
    switch (paramMotionEvent.getAction())
    {
    default:
      return false;
    case 0:
      handleTouchDownAt(f1, f2);
      if (this.currentResizeState.isNotEmpty())
        maybeRequestAutoFocus();
      break;
    case 2:
    case 1:
    case 3:
    }
    while (true)
    {
      return true;
      if (handleTouchAt(f1, f2))
      {
        invalidate();
        checkForMarginCallback();
        continue;
        this.animationTargetRect.set(this.region);
        this.currentResizeState.clear();
        if ((this.dragEventsCallback != null) && (this.alreadyCalledMarginCallback))
        {
          this.alreadyCalledMarginCallback = false;
          this.dragEventsCallback.onDragEnd();
        }
      }
    }
  }

  public void setDragEventsCallback(DragEventsCallback paramDragEventsCallback, int paramInt)
  {
    this.dragEventsCallback = paramDragEventsCallback;
    this.callbackMargin = paramInt;
  }

  void setRegion(Rect paramRect)
  {
    this.region = paramRect;
  }

  public void setRememberLastRegion(boolean paramBoolean)
  {
    if (!paramBoolean)
      this.animationTargetRect = new Rect(this.defaultAnimationTargetRect);
  }

  public void setRequestAutoFocusCallback(RequestAutoFocusCallback paramRequestAutoFocusCallback)
  {
    this.focusCallback = paramRequestAutoFocusCallback;
  }

  public void show()
  {
    if (!isVisible())
    {
      Animation localAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.roi_alpha_in);
      localAnimation.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          if (RegionSelectorView.this.dragEventsCallback != null)
            RegionSelectorView.this.dragEventsCallback.onAnimationEvent(false, true);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
          if (RegionSelectorView.this.dragEventsCallback != null)
            RegionSelectorView.this.dragEventsCallback.onAnimationEvent(true, true);
        }
      });
      startAnimation(localAnimation);
      setVisibility(0);
    }
  }

  protected void updateOffsetAndResizeState(boolean paramBoolean1, boolean paramBoolean2, float paramFloat1, float paramFloat2, int paramInt)
  {
    int i = (int)paramFloat1;
    int j = (int)paramFloat2;
    if (paramBoolean1)
    {
      if ((Math.abs(this.region.left - paramFloat1) < paramInt) && (!this.currentResizeState.resizeLeft))
      {
        this.currentResizeState.resizeLeft = true;
        this.offsetLeft = (this.region.left - i);
      }
      if ((Math.abs(this.region.right - paramFloat1) < paramInt) && (!this.currentResizeState.resizeRight))
      {
        this.currentResizeState.resizeRight = true;
        this.offsetRight = (this.region.right - i);
      }
      if ((this.currentResizeState.resizeLeft) && (this.currentResizeState.resizeRight))
      {
        if (Math.abs(this.region.left - paramFloat1) <= Math.abs(this.region.right - paramFloat1))
          break label374;
        this.currentResizeState.resizeLeft = false;
        this.offsetRight = (this.region.right - i);
      }
    }
    while (true)
    {
      if (paramBoolean2)
      {
        if ((Math.abs(this.region.top - paramFloat2) < paramInt) && (!this.currentResizeState.resizeTop))
        {
          this.currentResizeState.resizeTop = true;
          this.offsetTop = (this.region.top - j);
        }
        if ((Math.abs(this.region.bottom - paramFloat2) < paramInt) && (!this.currentResizeState.resizeBottom))
        {
          this.currentResizeState.resizeBottom = true;
          this.offsetBottom = (this.region.bottom - j);
        }
        if ((this.currentResizeState.resizeBottom) && (this.currentResizeState.resizeTop))
        {
          if (Math.abs(this.region.top - paramFloat2) <= Math.abs(this.region.bottom - paramFloat2))
            break;
          this.currentResizeState.resizeTop = false;
          this.offsetBottom = (this.region.bottom - j);
        }
      }
      return;
      label374: this.currentResizeState.resizeRight = false;
      this.offsetLeft = (this.region.left - i);
    }
    this.currentResizeState.resizeBottom = false;
    this.offsetTop = (this.region.top - j);
  }

  protected void updateResizeState(float paramFloat1, float paramFloat2)
  {
    int i = (int)(40.0F * this.density);
    boolean bool1;
    if ((paramFloat2 > this.region.top - i) && (paramFloat2 < i + this.region.bottom))
    {
      bool1 = true;
      if ((paramFloat1 <= this.region.left - i) || (paramFloat1 >= i + this.region.right))
        break label145;
    }
    label145: for (boolean bool2 = true; ; bool2 = false)
    {
      updateOffsetAndResizeState(bool1, bool2, paramFloat1, paramFloat2, i);
      if ((bool1) && (bool2) && (!this.currentResizeState.isNotEmpty()))
      {
        this.currentResizeState.resizeTop = true;
        this.currentResizeState.resizeBottom = true;
        this.currentResizeState.resizeLeft = true;
        this.currentResizeState.resizeRight = true;
      }
      return;
      bool1 = false;
      break;
    }
  }

  public static abstract interface DragEventsCallback
  {
    public abstract void onAnimationEvent(boolean paramBoolean1, boolean paramBoolean2);

    public abstract void onDragEnd();

    public abstract void onReachedMargin();
  }

  public static abstract interface RequestAutoFocusCallback
  {
    public abstract void onRequestAutoFocus();
  }

  protected static class ResizeState
  {
    public boolean resizeBottom = false;
    public boolean resizeLeft = false;
    public boolean resizeRight = false;
    public boolean resizeTop = false;

    public void clear()
    {
      this.resizeLeft = false;
      this.resizeTop = false;
      this.resizeRight = false;
      this.resizeBottom = false;
    }

    public boolean isNotEmpty()
    {
      return (this.resizeLeft) || (this.resizeRight) || (this.resizeTop) || (this.resizeBottom);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.RegionSelectorView
 * JD-Core Version:    0.6.2
 */