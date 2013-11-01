package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.PixelUtils;
import java.util.Locale;

public class SwitchButtonDrawable extends Drawable
{
  private final Paint backgroundPaint;
  private final int horizontalPadding;
  private final int interStringSpace;
  private final int minWidth;
  private final String offLabel;
  private final Paint offPaint;
  private final boolean on;
  private final String onLabel;
  private final Paint onPaint;
  private final Paint textPaint;
  private final int verticalPadding;

  public SwitchButtonDrawable(String paramString1, String paramString2, boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, Context paramContext)
  {
    this.onLabel = paramString1;
    this.offLabel = paramString2;
    this.on = paramBoolean;
    this.textPaint = new Paint();
    this.textPaint.setTextSize(PixelUtils.dipToPix(16.0F, paramContext));
    this.textPaint.setColor(Color.rgb(195, 195, 195));
    this.textPaint.setAntiAlias(true);
    this.onPaint = newFillPaint(paramInt1);
    this.offPaint = newFillPaint(paramInt2);
    this.backgroundPaint = newFillPaint(paramInt3);
    this.verticalPadding = PixelUtils.dipToPix(2.0F, paramContext);
    this.horizontalPadding = PixelUtils.dipToPix(14.0F, paramContext);
    this.minWidth = PixelUtils.dipToPix(80.0F, paramContext);
    this.interStringSpace = PixelUtils.dipToPix(20.0F, paramContext);
  }

  public static Drawable makeOnOffDrawable(Context paramContext)
  {
    Locale localLocale = paramContext.getResources().getConfiguration().locale;
    StateListDrawable localStateListDrawable = new StateListDrawable();
    String str1 = paramContext.getString(R.string.on).toUpperCase(localLocale);
    String str2 = paramContext.getString(R.string.off).toUpperCase(localLocale);
    int i = Color.rgb(33, 33, 33);
    int j = Color.rgb(7, 129, 170);
    int k = Color.rgb(74, 74, 74);
    int m = Color.rgb(51, 181, 229);
    localStateListDrawable.addState(new int[] { 16842912, 16842919 }, new SwitchButtonDrawable(str1, str2, true, m, k, i, paramContext));
    localStateListDrawable.addState(new int[] { 16842912 }, new SwitchButtonDrawable(str1, str2, true, j, k, i, paramContext));
    localStateListDrawable.addState(new int[] { 16842919 }, new SwitchButtonDrawable(str1, str2, false, j, m, i, paramContext));
    localStateListDrawable.addState(StateSet.WILD_CARD, new SwitchButtonDrawable(str1, str2, false, j, k, i, paramContext));
    return localStateListDrawable;
  }

  private static Paint newFillPaint(int paramInt)
  {
    Paint localPaint = new Paint();
    localPaint.setColor(paramInt);
    localPaint.setAntiAlias(true);
    return localPaint;
  }

  private Path rightHalfPath()
  {
    double d1 = 0.5D;
    Rect localRect = getBounds();
    Path localPath = new Path();
    localPath.moveTo(localRect.right, localRect.top);
    localPath.lineTo(localRect.right, localRect.bottom);
    double d2 = localRect.right;
    double d3;
    double d4;
    if (this.on)
    {
      d3 = 0.4D;
      localPath.lineTo((int)(d3 * d2), localRect.bottom);
      d4 = localRect.right;
      if (!this.on)
        break label128;
    }
    while (true)
    {
      localPath.lineTo((int)(d4 * d1), localRect.top);
      localPath.close();
      return localPath;
      d3 = d1;
      break;
      label128: d1 = 0.6000000238418579D;
    }
  }

  public void draw(Canvas paramCanvas)
  {
    Rect localRect = getBounds();
    Path localPath = rightHalfPath();
    if (this.on)
    {
      paramCanvas.drawRect(localRect, this.backgroundPaint);
      paramCanvas.drawPath(localPath, this.onPaint);
      paramCanvas.drawText(this.onLabel, localRect.right - this.textPaint.measureText(this.onLabel) - this.horizontalPadding, localRect.top - this.textPaint.ascent() + this.verticalPadding, this.textPaint);
      return;
    }
    paramCanvas.drawRect(localRect, this.offPaint);
    paramCanvas.drawPath(localPath, this.backgroundPaint);
    paramCanvas.drawText(this.offLabel, localRect.left + this.horizontalPadding, localRect.top - this.textPaint.ascent() + this.verticalPadding, this.textPaint);
  }

  public int getIntrinsicHeight()
  {
    return (int)(this.textPaint.descent() - this.textPaint.ascent() + 2 * this.verticalPadding);
  }

  public int getIntrinsicWidth()
  {
    int i = (int)(this.textPaint.measureText(this.onLabel) + this.textPaint.measureText(this.offLabel) + 2 * this.horizontalPadding + this.interStringSpace);
    return Math.max(this.minWidth, i);
  }

  public int getOpacity()
  {
    return -1;
  }

  public void setAlpha(int paramInt)
  {
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.SwitchButtonDrawable
 * JD-Core Version:    0.6.2
 */