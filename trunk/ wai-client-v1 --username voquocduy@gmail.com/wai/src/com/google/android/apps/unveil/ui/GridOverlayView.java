package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class GridOverlayView extends View
{
  private static final int ALPHA = 65;
  private static final int MAX_HORIZ_BOXES = 4;
  private final Paint paint = new Paint();

  public GridOverlayView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public GridOverlayView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  private int gcd(int paramInt1, int paramInt2)
  {
    if ((paramInt1 <= 0) || (paramInt2 <= 0))
      return 1;
    if (paramInt1 < paramInt2)
    {
      int j = paramInt1;
      paramInt1 = paramInt2;
      paramInt2 = j;
    }
    while (paramInt2 != 0)
    {
      int i = paramInt2;
      paramInt2 = paramInt1 % i;
      paramInt1 = i;
    }
    return paramInt1;
  }

  private void init()
  {
    this.paint.setAlpha(65);
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    int i = gcd(getWidth(), getHeight());
    int j = getHeight() / i;
    int k = getWidth() / i;
    if ((j <= 2) || (k <= 2))
    {
      j *= 2;
      k *= 2;
    }
    if (j > 4)
    {
      j = 4;
      k = Math.round(4.0F * getWidth() / getHeight());
    }
    int m = j - 1;
    int n = k - 1;
    int i1 = getHeight() / (m + 1);
    int i2 = getWidth() / (n + 1);
    for (int i3 = 1; i3 <= n; i3++)
      paramCanvas.drawLine(i3 * i2, 0.0F, i3 * i2, getHeight(), this.paint);
    for (int i4 = 1; i4 <= m; i4++)
      paramCanvas.drawLine(0.0F, i4 * i1, getWidth(), i4 * i1, this.paint);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.GridOverlayView
 * JD-Core Version:    0.6.2
 */