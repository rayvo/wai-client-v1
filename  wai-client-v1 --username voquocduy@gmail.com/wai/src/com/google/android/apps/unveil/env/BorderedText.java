package com.google.android.apps.unveil.env;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Rect;

public class BorderedText
{
  private static final float SIZE_TO_WIDTH_RATIO = 0.2F;
  private final Paint exteriorPaint;
  private final Paint interiorPaint = new Paint();
  private final float textSize;

  public BorderedText(float paramFloat)
  {
    this(-1, -16777216, paramFloat, Paint.Align.LEFT);
  }

  public BorderedText(float paramFloat, Paint.Align paramAlign)
  {
    this(-1, -16777216, paramFloat, paramAlign);
  }

  public BorderedText(int paramInt1, int paramInt2, float paramFloat, Paint.Align paramAlign)
  {
    this.interiorPaint.setTextSize(paramFloat);
    this.interiorPaint.setColor(paramInt1);
    this.interiorPaint.setStyle(Paint.Style.FILL);
    this.interiorPaint.setStrokeWidth(paramFloat * 0.2F);
    this.interiorPaint.setAntiAlias(false);
    this.interiorPaint.setTextAlign(paramAlign);
    this.exteriorPaint = new Paint();
    this.exteriorPaint.setTextSize(paramFloat);
    this.exteriorPaint.setColor(paramInt2);
    this.exteriorPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    this.exteriorPaint.setStrokeWidth(paramFloat * 0.2F);
    this.exteriorPaint.setAntiAlias(false);
    this.exteriorPaint.setTextAlign(paramAlign);
    this.textSize = paramFloat;
  }

  public void drawText(Canvas paramCanvas, float paramFloat1, float paramFloat2, String paramString)
  {
    paramCanvas.drawText(paramString, paramFloat1, paramFloat2, this.exteriorPaint);
    paramCanvas.drawText(paramString, paramFloat1, paramFloat2, this.interiorPaint);
  }

  public void getTextBounds(String paramString, int paramInt1, int paramInt2, Rect paramRect)
  {
    this.interiorPaint.getTextBounds(paramString, paramInt1, paramInt2, paramRect);
  }

  public float getTextSize()
  {
    return this.textSize;
  }

  public void setAlpha(int paramInt)
  {
    this.interiorPaint.setAlpha(paramInt);
    this.exteriorPaint.setAlpha(paramInt);
  }

  public void setExteriorColor(int paramInt)
  {
    this.exteriorPaint.setColor(paramInt);
  }

  public void setInteriorColor(int paramInt)
  {
    this.interiorPaint.setColor(paramInt);
  }

  public void setTextAlign(Paint.Align paramAlign)
  {
    this.interiorPaint.setTextAlign(paramAlign);
    this.exteriorPaint.setTextAlign(paramAlign);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.BorderedText
 * JD-Core Version:    0.6.2
 */