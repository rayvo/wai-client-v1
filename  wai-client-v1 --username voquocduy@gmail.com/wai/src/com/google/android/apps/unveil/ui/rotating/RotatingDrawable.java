package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingDrawable extends View
  implements RotatingWidget
{
  private static final UnveilLogger logger = new UnveilLogger(RotatingDrawable.class);
  private Drawable drawable;
  private final RotatingViewHelper helper = new RotatingViewHelper(paramContext, paramAttributeSet, this);

  public RotatingDrawable(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingDrawable, 0, 0);
    this.drawable = localTypedArray.getDrawable(0);
    localTypedArray.recycle();
  }

  public void drawRotated(Canvas paramCanvas)
  {
    Rect localRect = new Rect(0, 0, getRotatedWidth(), getRotatedHeight() - this.helper.getRotatingPaddingTop());
    this.drawable.setBounds(localRect);
    this.drawable.draw(paramCanvas);
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

  protected int getViewRotation()
  {
    return this.helper.getRotation();
  }

  protected void onDraw(Canvas paramCanvas)
  {
    this.helper.onDraw(paramCanvas);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.helper.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getSize(paramInt1);
    int j = View.MeasureSpec.getMode(paramInt1);
    int k = View.MeasureSpec.getSize(paramInt2);
    int m = View.MeasureSpec.getMode(paramInt2);
    int n;
    int i1;
    if (this.helper.getRotation() % 180 != 0)
    {
      n = this.drawable.getIntrinsicHeight() + this.helper.getRotatingPaddingTop();
      i1 = this.drawable.getIntrinsicWidth();
      if (j != 1073741824)
        break label114;
      label70: if (m != 1073741824)
        break label120;
    }
    while (true)
    {
      setMeasuredDimension(i, k);
      return;
      n = this.drawable.getIntrinsicWidth();
      i1 = this.drawable.getIntrinsicHeight() + this.helper.getRotatingPaddingTop();
      break;
      label114: i = n;
      break label70;
      label120: k = i1;
    }
  }

  public void rotateWidget(int paramInt)
  {
    this.helper.rotateWidget(paramInt);
  }

  public void setDrawable(int paramInt)
  {
    this.drawable = this.helper.getContext().getResources().getDrawable(paramInt);
  }

  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setLayoutParams(paramLayoutParams);
    this.helper.setLayoutParams(paramLayoutParams);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingDrawable
 * JD-Core Version:    0.6.2
 */