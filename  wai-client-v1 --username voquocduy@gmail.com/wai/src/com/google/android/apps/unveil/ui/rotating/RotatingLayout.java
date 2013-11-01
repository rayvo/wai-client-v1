package com.google.android.apps.unveil.ui.rotating;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.OrientationEventListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.google.android.apps.unveil.R.styleable;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.ui.RotationUtils;

public class RotatingLayout extends RelativeLayout
{
  public static final int ROTATION_LANDSCAPE_TURNED_CLOCKWISE = 1;
  public static final int ROTATION_LANDSCAPE_TURNED_COUNTER_CLOCKWISE = 3;
  public static final int ROTATION_PORTRAIT = 0;
  public static final int ROTATION_PORTRAIT_UPSIDE_DOWN = 2;
  private static final UnveilLogger logger = new UnveilLogger();
  private Context context;
  private int currentRotation = -1;
  public boolean locked = false;
  private OrientationEventListener orientationListener;
  private final int originalGravity;
  int rotation = 0;

  public RotatingLayout(Context paramContext)
  {
    super(paramContext);
    this.originalGravity = 0;
    commonInit(paramContext);
  }

  public RotatingLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.originalGravity = RotationUtils.parseGravity(paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.RotatingLayouts, 0, 0).getString(0));
    commonInit(paramContext);
  }

  private int adjustRotationForDevice(int paramInt)
  {
    return ((UnveilApplication)this.context.getApplicationContext()).getViewport().deviceRotationToCameraRotation(paramInt + 270) % 360;
  }

  private void commonInit(Context paramContext)
  {
    this.orientationListener = new OrientationEventListener(paramContext)
    {
      public void onOrientationChanged(int paramAnonymousInt)
      {
        if (paramAnonymousInt != -1)
          RotatingLayout.this.setRotation(RotatingLayout.this.adjustRotationForDevice(paramAnonymousInt));
      }
    };
    this.context = paramContext;
  }

  private void rotateChild(RotatingWidget paramRotatingWidget, int paramInt)
  {
    rotateChildLayoutParams(paramRotatingWidget, paramInt);
    paramRotatingWidget.rotateWidget(paramInt);
    if ((paramRotatingWidget instanceof ViewGroup))
    {
      ViewGroup localViewGroup = (ViewGroup)paramRotatingWidget;
      for (int i = 0; i < localViewGroup.getChildCount(); i++)
        rotateChild((RotatingWidget)localViewGroup.getChildAt(i), paramInt);
    }
  }

  private void rotateChildLayoutParams(RotatingWidget paramRotatingWidget, int paramInt)
  {
    if (paramRotatingWidget == null);
    ViewGroup.MarginLayoutParams localMarginLayoutParams1;
    do
    {
      return;
      localMarginLayoutParams1 = (ViewGroup.MarginLayoutParams)paramRotatingWidget.getOriginalLayoutParams();
    }
    while (localMarginLayoutParams1 == null);
    int[] arrayOfInt = new int[4];
    arrayOfInt[0] = localMarginLayoutParams1.topMargin;
    arrayOfInt[1] = localMarginLayoutParams1.rightMargin;
    arrayOfInt[2] = localMarginLayoutParams1.bottomMargin;
    arrayOfInt[3] = localMarginLayoutParams1.leftMargin;
    View localView = (View)paramRotatingWidget;
    ViewGroup.LayoutParams localLayoutParams1;
    int i;
    if ((paramInt == 1) || (paramInt == 3))
    {
      localLayoutParams1 = localView.getLayoutParams();
      if (localLayoutParams1.width == localMarginLayoutParams1.width)
      {
        int j = localLayoutParams1.height;
        int k = localMarginLayoutParams1.height;
        i = 0;
        if (j == k);
      }
      else
      {
        i = 1;
      }
      localLayoutParams1.width = localMarginLayoutParams1.width;
    }
    ViewGroup.LayoutParams localLayoutParams2;
    for (localLayoutParams1.height = localMarginLayoutParams1.height; ; localLayoutParams2.height = localMarginLayoutParams1.width)
    {
      if ((localView.getLayoutParams() instanceof ViewGroup.MarginLayoutParams))
      {
        ViewGroup.MarginLayoutParams localMarginLayoutParams2 = (ViewGroup.MarginLayoutParams)localView.getLayoutParams();
        localMarginLayoutParams2.topMargin = arrayOfInt[((3 - paramInt) % 4)];
        localMarginLayoutParams2.leftMargin = arrayOfInt[((4 - paramInt) % 4)];
        localMarginLayoutParams2.bottomMargin = arrayOfInt[((5 - paramInt) % 4)];
        localMarginLayoutParams2.rightMargin = arrayOfInt[((6 - paramInt) % 4)];
        i = 1;
      }
      if ((localView.getLayoutParams() instanceof LinearLayout.LayoutParams))
      {
        LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramRotatingWidget.getOriginalLayoutParams();
        ((LinearLayout.LayoutParams)localView.getLayoutParams()).gravity = RotationUtils.getRotatedGravity(paramInt, localLayoutParams.gravity);
        i = 1;
      }
      if (i == 0)
        break;
      localView.requestLayout();
      return;
      localLayoutParams2 = localView.getLayoutParams();
      if (localLayoutParams2.width == localMarginLayoutParams1.height)
      {
        int m = localLayoutParams2.height;
        int n = localMarginLayoutParams1.width;
        i = 0;
        if (m == n);
      }
      else
      {
        i = 1;
      }
      localLayoutParams2.width = localMarginLayoutParams1.height;
    }
  }

  private void rotateChildren(int paramInt)
  {
    for (int i = 0; i < getChildCount(); i++)
      rotateChild((RotatingWidget)getChildAt(i), paramInt);
    this.currentRotation = paramInt;
  }

  public void draw(Canvas paramCanvas)
  {
    Drawable localDrawable = getBackground();
    if (localDrawable != null)
    {
      localDrawable.setBounds(0, 0, getWidth(), getHeight());
      localDrawable.draw(paramCanvas);
    }
    super.draw(paramCanvas);
  }

  public void initializeChildren()
  {
    int i = ((UnveilApplication)this.context.getApplicationContext()).getSensorProvider().getRoundedDeviceOrientation();
    if (i == -1)
      i = 0;
    setRotation(adjustRotationForDevice(i));
  }

  public void initializeRotation()
  {
    setRotation(adjustRotationForDevice(this.context.getResources().getConfiguration().orientation));
  }

  public void onWindowVisibilityChanged(int paramInt)
  {
    if (paramInt == 0)
    {
      this.orientationListener.enable();
      return;
    }
    this.orientationListener.disable();
  }

  int quantizeRotation(int paramInt)
  {
    if (this.locked)
      return 3;
    return (paramInt + 45) % 360 / 90;
  }

  public void rotateWidget(int paramInt)
  {
    int i = RotationUtils.getRotatedGravity(paramInt, this.originalGravity);
    if (i >= 0)
      setGravity(i);
    while (true)
    {
      requestLayout();
      return;
      if (this.originalGravity == 1)
      {
        if (paramInt % 2 == 0)
          setGravity(16);
        else
          setGravity(1);
      }
      else if (this.originalGravity == 16)
        if (paramInt % 2 == 0)
          setGravity(1);
        else
          setGravity(16);
    }
  }

  public void setLocked(boolean paramBoolean)
  {
    this.locked = paramBoolean;
  }

  public void setRotation(int paramInt)
  {
    int i = quantizeRotation(paramInt);
    this.rotation = i;
    if (i != this.currentRotation)
    {
      rotateWidget(i);
      rotateChildren(i);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingLayout
 * JD-Core Version:    0.6.2
 */