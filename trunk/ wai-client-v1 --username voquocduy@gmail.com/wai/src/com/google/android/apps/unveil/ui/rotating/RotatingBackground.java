package com.google.android.apps.unveil.ui.rotating;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

class RotatingBackground extends Drawable
{
  private final Drawable backingDrawable;
  private final RotatingViewHelper helper;

  public RotatingBackground(Drawable paramDrawable, RotatingViewHelper paramRotatingViewHelper)
  {
    this.helper = paramRotatingViewHelper;
    this.backingDrawable = paramDrawable;
  }

  public void draw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.concat(this.helper.getMatrix());
    this.backingDrawable.setBounds(new Rect(0, 0, this.helper.getRotatedWidth(), this.helper.getRotatedHeight()));
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

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingBackground
 * JD-Core Version:    0.6.2
 */