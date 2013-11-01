package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.NinePatch;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;

class RotatingStateListDrawableBackground extends Drawable
{
  private final RotatingViewHelper helper;
  private final NinePatch normal;
  private final NinePatch pressed;
  private final NinePatch selected;
  int[] states;

  RotatingStateListDrawableBackground(RotatingViewHelper paramRotatingViewHelper, Context paramContext, int paramInt1, int paramInt2, int paramInt3)
  {
    this.helper = paramRotatingViewHelper;
    Resources localResources = paramContext.getResources();
    Bitmap localBitmap1 = BitmapFactory.decodeResource(localResources, paramInt1);
    this.normal = new NinePatch(localBitmap1, localBitmap1.getNinePatchChunk(), null);
    Bitmap localBitmap2 = BitmapFactory.decodeResource(localResources, paramInt2);
    this.pressed = new NinePatch(localBitmap2, localBitmap2.getNinePatchChunk(), null);
    Bitmap localBitmap3 = BitmapFactory.decodeResource(localResources, paramInt3);
    this.selected = new NinePatch(localBitmap3, localBitmap3.getNinePatchChunk(), null);
  }

  private boolean hasState(int paramInt)
  {
    for (int i = 0; i < this.states.length; i++)
      if (this.states[i] == paramInt)
        return true;
    return false;
  }

  private boolean hasStateSet(int[] paramArrayOfInt)
  {
    if (this.states == null)
      return false;
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfInt.length)
        break label33;
      if (!hasState(paramArrayOfInt[i]))
        break;
    }
    label33: return true;
  }

  public void draw(Canvas paramCanvas)
  {
    paramCanvas.save();
    paramCanvas.concat(this.helper.getMatrix());
    Rect localRect = new Rect(this.helper.getRotatingPaddingLeft(), this.helper.getRotatingPaddingTop(), this.helper.getRotatedWidth() - this.helper.getRotatingPaddingRight(), this.helper.getRotatedHeight() - this.helper.getRotatingPaddingBottom());
    if (hasStateSet(ViewStateSetExposer.pressedEnabledStateSet()))
      this.pressed.draw(paramCanvas, localRect);
    while (true)
    {
      paramCanvas.restore();
      return;
      if (hasStateSet(ViewStateSetExposer.exposedFocusedStateSet()))
        this.selected.draw(paramCanvas, localRect);
      else
        this.normal.draw(paramCanvas, localRect);
    }
  }

  public int getOpacity()
  {
    return -1;
  }

  public boolean isStateful()
  {
    return true;
  }

  public void setAlpha(int paramInt)
  {
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
  }

  public boolean setState(int[] paramArrayOfInt)
  {
    this.states = paramArrayOfInt;
    invalidateSelf();
    return true;
  }

  private static class ViewStateSetExposer extends View
  {
    private ViewStateSetExposer()
    {
      super();
    }

    public static int[] exposedFocusedStateSet()
    {
      return FOCUSED_STATE_SET;
    }

    public static int[] pressedEnabledStateSet()
    {
      return PRESSED_ENABLED_STATE_SET;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingStateListDrawableBackground
 * JD-Core Version:    0.6.2
 */