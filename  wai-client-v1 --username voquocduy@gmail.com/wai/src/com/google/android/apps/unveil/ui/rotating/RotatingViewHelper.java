package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.ui.RotationUtils;

public class RotatingViewHelper
{
  private static final UnveilLogger logger = new UnveilLogger();
  private DisplayMetrics cachedMetrics;
  private final Context context;
  int marginBottom = 0;
  int marginLeft = 0;
  int marginRight = 0;
  int marginTop = 0;
  private final Matrix matrix = new Matrix();
  ViewGroup.LayoutParams originalLayoutParams;
  int paddingBottom = 0;
  int paddingLeft = 0;
  int paddingRight = 0;
  int paddingTop = 0;
  private int rotation;
  private final View view;
  private final RotatingWidget widget;

  public RotatingViewHelper(Context paramContext, AttributeSet paramAttributeSet, RotatingWidget paramRotatingWidget)
  {
    this.widget = paramRotatingWidget;
    this.context = paramContext;
    this.view = ((View)paramRotatingWidget);
    if ((this.originalLayoutParams == null) && (this.view.getLayoutParams() != null))
      this.originalLayoutParams = RotationUtils.cloneLayoutParams(this.view.getLayoutParams());
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingTextView, 0, 0);
    this.paddingLeft = localTypedArray.getDimensionPixelOffset(0, 0);
    this.paddingRight = localTypedArray.getDimensionPixelOffset(1, 0);
    this.paddingTop = localTypedArray.getDimensionPixelOffset(2, 0);
    this.paddingBottom = localTypedArray.getDimensionPixelOffset(3, 0);
    localTypedArray.recycle();
    this.rotation = 270;
  }

  private void reconfigurePadding(int paramInt)
  {
    switch (paramInt)
    {
    default:
      return;
    case 0:
      this.view.setPadding(this.paddingTop, this.paddingRight, this.paddingBottom, this.paddingLeft);
      return;
    case 1:
      this.view.setPadding(this.paddingRight, this.paddingBottom, this.paddingLeft, this.paddingTop);
      return;
    case 2:
      this.view.setPadding(this.paddingBottom, this.paddingLeft, this.paddingTop, this.paddingRight);
      return;
    case 3:
    }
    this.view.setPadding(this.paddingLeft, this.paddingTop, this.paddingRight, this.paddingBottom);
  }

  public Context getContext()
  {
    return this.context;
  }

  public Matrix getMatrix()
  {
    return this.matrix;
  }

  public DisplayMetrics getMetrics()
  {
    if (this.cachedMetrics == null)
    {
      this.cachedMetrics = new DisplayMetrics();
      ((WindowManager)this.context.getSystemService("window")).getDefaultDisplay().getMetrics(this.cachedMetrics);
    }
    return this.cachedMetrics;
  }

  public ViewGroup.LayoutParams getOriginalLayoutParams()
  {
    return this.originalLayoutParams;
  }

  public int getRotatedHeight()
  {
    if (this.rotation % 180 != 0)
      return this.view.getWidth();
    return this.view.getHeight();
  }

  public int getRotatedWidth()
  {
    if (this.rotation % 180 != 0)
      return this.view.getHeight();
    return this.view.getWidth();
  }

  public int getRotatingMarginBottom()
  {
    return this.marginBottom;
  }

  public int getRotatingMarginLeft()
  {
    return this.marginLeft;
  }

  public int getRotatingMarginRight()
  {
    return this.marginRight;
  }

  public int getRotatingMarginTop()
  {
    return this.marginTop;
  }

  public int getRotatingPaddingBottom()
  {
    return this.paddingBottom;
  }

  public int getRotatingPaddingLeft()
  {
    return this.paddingLeft;
  }

  public int getRotatingPaddingRight()
  {
    return this.paddingRight;
  }

  public int getRotatingPaddingTop()
  {
    return this.paddingTop;
  }

  public int getRotation()
  {
    return this.rotation;
  }

  public void onDraw(Canvas paramCanvas)
  {
    if (this.rotation != 0)
    {
      paramCanvas.save();
      paramCanvas.concat(this.matrix);
    }
    paramCanvas.translate(this.paddingLeft, this.paddingTop);
    this.widget.drawRotated(paramCanvas);
    if (this.rotation != 0)
      paramCanvas.restore();
  }

  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    RotationUtils.transformMatrixForRotation(this.rotation, paramInt1, paramInt2, paramInt3, paramInt4, this.matrix);
  }

  public void rotateWidget(int paramInt)
  {
    this.rotation = (90 * (3 - paramInt) % 360);
    if (this.originalLayoutParams != null)
      RotationUtils.setViewDimensionsForRotation(this.view, paramInt, this.originalLayoutParams);
    reconfigurePadding(paramInt);
    this.view.requestLayout();
  }

  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    if (this.originalLayoutParams == null)
      this.originalLayoutParams = RotationUtils.cloneLayoutParams(paramLayoutParams);
  }

  public void setMargin(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.marginLeft = paramInt1;
    this.marginTop = paramInt2;
    this.marginRight = paramInt3;
    this.marginBottom = paramInt4;
    this.view.requestLayout();
  }

  public void setPadding(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    this.paddingLeft = paramInt1;
    this.paddingTop = paramInt2;
    this.paddingRight = paramInt3;
    this.paddingBottom = paramInt4;
    this.view.requestLayout();
  }

  public void setRotatingPaddingBottom(int paramInt)
  {
    this.paddingBottom = paramInt;
    this.view.requestLayout();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingViewHelper
 * JD-Core Version:    0.6.2
 */