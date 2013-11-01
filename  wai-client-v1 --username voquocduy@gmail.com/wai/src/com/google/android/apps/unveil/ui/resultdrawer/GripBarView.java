package com.google.android.apps.unveil.ui.resultdrawer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.R.drawable;

public class GripBarView extends ImageView
{
  public static final float CLICKABLE_HEIGHT_IN = 0.3937F;
  private boolean pressed;

  public GripBarView(Context paramContext)
  {
    super(paramContext);
    setLayoutParams(new LinearLayout.LayoutParams(-1, getDrawableForState(false).getIntrinsicHeight()));
    updateDrawable();
  }

  private Drawable getDrawableForState(boolean paramBoolean)
  {
    if (paramBoolean)
      return getResources().getDrawable(R.drawable.drawer_handle_pressed);
    return getResources().getDrawable(R.drawable.drawer_handle);
  }

  private void updateDrawable()
  {
    setBackgroundDrawable(getDrawableForState(this.pressed));
  }

  public int getDrawableHeight()
  {
    return getBackground().getIntrinsicHeight();
  }

  public void setPressed(boolean paramBoolean)
  {
    if (this.pressed == paramBoolean)
      return;
    this.pressed = paramBoolean;
    updateDrawable();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.resultdrawer.GripBarView
 * JD-Core Version:    0.6.2
 */