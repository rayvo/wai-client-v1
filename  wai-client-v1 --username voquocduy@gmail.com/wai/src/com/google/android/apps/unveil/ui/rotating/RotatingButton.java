package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingButton extends RotatingTextView
{
  private static final int ICON_TO_TEXT_MARGIN = 5;
  private static final int MARGIN = 15;
  private static final int TEXT_SIZE_DIP = 19;
  private static final UnveilLogger logger = new UnveilLogger();

  public RotatingButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setClickable(true);
    setFocusable(true);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingButton, 0, 0);
    setBackgroundDrawable(new RotatingStateListDrawableBackground(getHelper(), paramContext, localTypedArray.getResourceId(3, R.drawable.btn_default_normal), localTypedArray.getResourceId(4, R.drawable.btn_default_pressed), localTypedArray.getResourceId(5, R.drawable.btn_default_selected)));
    setTextSize(dipToPix(19));
    getHelper().setMargin(dipToPix(15), dipToPix(15), dipToPix(15), dipToPix(15));
    setIconToTextMargin(dipToPix(5));
    setLeftDrawable(localTypedArray.getDrawable(2));
    localTypedArray.recycle();
  }

  private int dipToPix(int paramInt)
  {
    return (int)(paramInt * getHelper().getMetrics().scaledDensity);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingButton
 * JD-Core Version:    0.6.2
 */