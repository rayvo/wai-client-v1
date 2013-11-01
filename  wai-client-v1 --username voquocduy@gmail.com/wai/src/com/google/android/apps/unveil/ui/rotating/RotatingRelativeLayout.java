package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.ui.RotationUtils;

public class RotatingRelativeLayout extends RelativeLayout
  implements RotatingWidget
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final RotatingViewHelper helper = new RotatingViewHelper(paramContext, paramAttributeSet, this);
  private int originalGravity = 0;
  private ViewGroup.LayoutParams originalLayoutParams;

  public RotatingRelativeLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingLayouts, 0, 0);
    this.originalGravity = RotationUtils.parseGravity(localTypedArray.getString(0));
    localTypedArray.recycle();
    if ((this.originalLayoutParams == null) && (getLayoutParams() != null))
      this.originalLayoutParams = RotationUtils.cloneLayoutParams(getLayoutParams());
  }

  public void drawRotated(Canvas paramCanvas)
  {
  }

  public ViewGroup.LayoutParams getOriginalLayoutParams()
  {
    return this.helper.getOriginalLayoutParams();
  }

  public void rotateWidget(int paramInt)
  {
    this.helper.rotateWidget(paramInt);
    int i = RotationUtils.getRotatedGravity(paramInt, this.originalGravity);
    if (i >= 0)
      setGravity(i);
  }

  public void setLayoutParams(ViewGroup.LayoutParams paramLayoutParams)
  {
    super.setLayoutParams(paramLayoutParams);
    this.helper.setLayoutParams(paramLayoutParams);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingRelativeLayout
 * JD-Core Version:    0.6.2
 */