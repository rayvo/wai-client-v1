package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout.Alignment;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup.LayoutParams;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.ui.RotationUtils;

public class RotatingTextView extends View
  implements RotatingWidget
{
  private static final float DEFAULT_TEXT_SIZE_PIXELS_DIP = 13.0F;
  private static final int ICON_TO_TEXT_MARGIN_DIP = 15;
  private static final UnveilLogger logger = new UnveilLogger();
  private Layout.Alignment alignment = Layout.Alignment.ALIGN_CENTER;
  private int gravity;
  private final RotatingViewHelper helper = new RotatingViewHelper(paramContext, paramAttributeSet, this);
  private int iconToTextMargin = (int)(15.0F * this.helper.getMetrics().scaledDensity);
  private StaticLayout layout;
  private Drawable leftDrawable;
  private CharSequence text;
  private final TextPaint textPaint;

  public RotatingTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    int i = (int)(13.0F * getResources().getDisplayMetrics().density);
    TypedArray localTypedArray1 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingTextView, 0, 0);
    this.textPaint = new TextPaint();
    setTextSize(localTypedArray1.getDimensionPixelSize(4, i));
    setTextColor(localTypedArray1.getColor(5, -16777216));
    if (localTypedArray1.getString(6) == null);
    for (this.gravity = 17; ; this.gravity = RotationUtils.parseGravity(localTypedArray1.getString(6)))
    {
      this.leftDrawable = localTypedArray1.getDrawable(7);
      localTypedArray1.recycle();
      TypedArray localTypedArray2 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingButton, 0, 0);
      setText(localTypedArray2.getString(0));
      localTypedArray2.recycle();
      TypedArray localTypedArray3 = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingLinearLayout, 0, 0);
      Drawable localDrawable = localTypedArray3.getDrawable(1);
      if (localDrawable != null)
        setBackgroundDrawable(new RotatingBackground(localDrawable, this.helper));
      localTypedArray3.recycle();
      setClickable(true);
      setFocusable(true);
      this.textPaint.setAntiAlias(true);
      return;
    }
  }

  public void drawRotated(Canvas paramCanvas)
  {
    if (this.layout == null)
      return;
    int i = getRotatedWidth() - this.helper.getRotatingPaddingLeft() - this.helper.getRotatingPaddingRight();
    int j = this.layout.getWidth();
    if (this.leftDrawable != null)
      j = j + this.iconToTextMargin + this.leftDrawable.getIntrinsicWidth();
    Drawable localDrawable = this.leftDrawable;
    int k = 0;
    if (localDrawable != null)
      k = 0 + this.iconToTextMargin + this.leftDrawable.getIntrinsicWidth();
    if ((0x7 & this.gravity) == 1);
    for (int m = (i - j) / 2; ; m = 0)
    {
      paramCanvas.save();
      int n = getRotatedHeight() - this.helper.getRotatingPaddingTop() - this.helper.getRotatingPaddingBottom() - this.helper.getRotatingMarginTop() - this.helper.getRotatingMarginBottom();
      paramCanvas.translate(m + k, this.helper.getRotatingMarginTop() + (n - this.layout.getHeight()) / 2);
      this.layout.draw(paramCanvas);
      paramCanvas.restore();
      if (this.leftDrawable == null)
        break;
      int i1 = this.helper.getRotatingMarginTop() + (n - this.leftDrawable.getIntrinsicHeight()) / 2;
      Rect localRect = new Rect(m, i1, m + this.leftDrawable.getIntrinsicWidth(), i1 + this.leftDrawable.getIntrinsicHeight());
      this.leftDrawable.setBounds(localRect);
      this.leftDrawable.draw(paramCanvas);
      return;
    }
  }

  protected RotatingViewHelper getHelper()
  {
    return this.helper;
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

  protected StaticLayout getStaticLayout()
  {
    return this.layout;
  }

  public CharSequence getText()
  {
    return this.text;
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
    Drawable localDrawable = this.leftDrawable;
    int n = 0;
    int i1 = 0;
    if (localDrawable != null)
    {
      n = this.leftDrawable.getIntrinsicHeight();
      i1 = this.leftDrawable.getIntrinsicWidth() + this.iconToTextMargin;
    }
    float f = StaticLayout.getDesiredWidth(this.text, this.textPaint) + i1;
    int i2 = this.helper.getRotatingMarginLeft() + this.helper.getRotatingMarginRight() + this.helper.getRotatingPaddingLeft() + this.helper.getRotatingPaddingRight();
    int i3 = this.helper.getRotatingMarginTop() + this.helper.getRotatingMarginBottom() + this.helper.getRotatingPaddingTop() + this.helper.getRotatingPaddingBottom();
    int i4;
    int i5;
    if (this.helper.getRotation() % 180 != 0)
    {
      this.layout = new StaticLayout(this.text, this.textPaint, (int)Math.max(0.0F, Math.min(f, k - i1 - i2)), this.alignment, 1.0F, 0.0F, false);
      i4 = i3 + Math.max(n, this.layout.getHeight());
      i5 = i2 + (i1 + this.layout.getWidth());
      if (j != 1073741824)
        break label340;
      label245: if (m != 1073741824)
        break label346;
    }
    while (true)
    {
      setMeasuredDimension(i, k);
      return;
      this.layout = new StaticLayout(this.text, this.textPaint, (int)Math.max(0.0F, Math.min(f, i - i1 - i2)), this.alignment, 1.0F, 0.0F, false);
      i4 = i2 + (i1 + this.layout.getWidth());
      i5 = i3 + Math.max(this.layout.getHeight(), n);
      break;
      label340: i = i4;
      break label245;
      label346: k = i5;
    }
  }

  public void rotateWidget(int paramInt)
  {
    this.helper.rotateWidget(paramInt);
  }

  public void setIconToTextMargin(int paramInt)
  {
    this.iconToTextMargin = paramInt;
  }

  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setLayoutParams(paramLayoutParams);
    this.helper.setLayoutParams(paramLayoutParams);
  }

  public void setLeftDrawable(Drawable paramDrawable)
  {
    this.leftDrawable = paramDrawable;
    requestLayout();
  }

  protected void setStaticLayout(StaticLayout paramStaticLayout)
  {
    this.layout = paramStaticLayout;
  }

  public void setText(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null);
    for (this.text = ""; ; this.text = paramCharSequence)
    {
      setContentDescription(this.text);
      requestLayout();
      return;
    }
  }

  public void setTextAlignment(Layout.Alignment paramAlignment)
  {
    if (paramAlignment != this.alignment)
    {
      this.alignment = paramAlignment;
      requestLayout();
    }
  }

  public void setTextColor(int paramInt)
  {
    if (paramInt != this.textPaint.getColor())
    {
      this.textPaint.setColor(paramInt);
      invalidate();
    }
  }

  public void setTextSize(float paramFloat)
  {
    if (paramFloat != this.textPaint.getTextSize())
    {
      this.textPaint.setTextSize(paramFloat);
      requestLayout();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingTextView
 * JD-Core Version:    0.6.2
 */