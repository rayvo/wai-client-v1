package com.google.android.apps.unveil.ui.rotating;

import android.graphics.Canvas;
import android.view.ViewGroup.LayoutParams;

public abstract interface RotatingWidget
{
  public abstract void drawRotated(Canvas paramCanvas);

  public abstract ViewGroup.LayoutParams getOriginalLayoutParams();

  public abstract void rotateWidget(int paramInt);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingWidget
 * JD-Core Version:    0.6.2
 */