package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingLinearLayout extends LinearLayout
  implements RotatingWidget
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Drawable backgroundDrawable;
  private final RotatingViewHelper helper = new RotatingViewHelper(paramContext, paramAttributeSet, this);
  boolean orderReversed = false;
  private int originalOrientation = 0;

  public RotatingLinearLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingLayouts, 0, 0);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingLinearLayout, 0, 0);
    if (localTypedArray.getString(0).equals("vertical"));
    for (this.originalOrientation = 1; ; this.originalOrientation = 0)
    {
      Drawable localDrawable = localTypedArray.getDrawable(1);
      if (localDrawable != null)
      {
        this.backgroundDrawable = new RotatingBackground(localDrawable);
        setBackgroundDrawable(this.backgroundDrawable);
      }
      localTypedArray.recycle();
      return;
    }
  }

  private void reverseChildren()
  {
    for (int i = 1; i < getChildCount(); i++)
    {
      View localView = getChildAt(i);
      removeViewAt(i);
      addView(localView, 0);
    }
    boolean bool1 = this.orderReversed;
    boolean bool2 = false;
    if (!bool1)
      bool2 = true;
    this.orderReversed = bool2;
  }

  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = getBackground();
    if (localDrawable != null)
    {
      localDrawable.setBounds(0, 0, getWidth(), getHeight());
      localDrawable.draw(paramCanvas);
    }
    super.draw(paramCanvas);
  }

  public void drawRotated(Canvas paramCanvas)
  {
  }

  public ViewGroup.LayoutParams getOriginalLayoutParams()
  {
    return this.helper.getOriginalLayoutParams();
  }

  public int getRotatedHeight()
  {
    return this.helper.getRotatedHeight();
  }

  public int getRotatedWidth()
  {
    return this.helper.getRotatedWidth();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.helper.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  public void rotateWidget(int paramInt)
  {
    this.helper.rotateWidget(paramInt);
    if (paramInt % 2 == 0)
      if (this.originalOrientation == 0)
      {
        setOrientation(1);
        if (this.originalOrientation != 0)
          break label101;
        if (((paramInt != 0) && (paramInt != 1)) || (this.orderReversed))
          break label77;
        reverseChildren();
      }
    while (true)
    {
      requestLayout();
      return;
      setOrientation(0);
      break;
      setOrientation(this.originalOrientation);
      break;
      label77: if (((paramInt == 2) || (paramInt == 3)) && (this.orderReversed))
      {
        reverseChildren();
        continue;
        label101: if (((paramInt == 1) || (paramInt == 2)) && (!this.orderReversed))
          reverseChildren();
        else if (((paramInt == 0) || (paramInt == 3)) && (this.orderReversed))
          reverseChildren();
      }
    }
  }

  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setLayoutParams(paramLayoutParams);
    this.helper.setLayoutParams(paramLayoutParams);
  }

  public void setRotatingPaddingBottom(int paramInt)
  {
    this.helper.setRotatingPaddingBottom(paramInt);
  }

  public void startAnimation()
  {
    Drawable localDrawable = getBackground();
    if ((localDrawable != null) && ((localDrawable instanceof AnimationDrawable)))
      ((AnimationDrawable)localDrawable).start();
  }

  private class RotatingBackground extends Drawable
  {
    private final Drawable backingDrawable;

    public RotatingBackground(Drawable arg2)
    {
      Object localObject;
      this.backingDrawable = localObject;
    }

    public void draw(Canvas paramCanvas)
    {
      paramCanvas.save();
      paramCanvas.concat(RotatingLinearLayout.this.helper.getMatrix());
      this.backingDrawable.setBounds(new Rect(0, 0, RotatingLinearLayout.this.getRotatedWidth(), RotatingLinearLayout.this.getRotatedHeight()));
      this.backingDrawable.draw(paramCanvas);
      paramCanvas.restore();
    }

    public int getOpacity()
    {
      return this.backingDrawable.getOpacity();
    }

    public boolean isStateful()
    {
      return this.backingDrawable.isStateful();
    }

    public void setAlpha(int paramInt)
    {
      this.backingDrawable.setAlpha(paramInt);
    }

    public void setColorFilter(ColorFilter paramColorFilter)
    {
      this.backingDrawable.setColorFilter(paramColorFilter);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingLinearLayout
 * JD-Core Version:    0.6.2
 */