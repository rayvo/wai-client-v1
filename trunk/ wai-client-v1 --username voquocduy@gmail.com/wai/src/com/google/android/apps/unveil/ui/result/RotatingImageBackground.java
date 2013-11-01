package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.Viewport;

public class RotatingImageBackground extends QueryImageBackground
{
  public RotatingImageBackground(Picture paramPicture, NonCompensatingResultPositioner paramNonCompensatingResultPositioner, Viewport paramViewport)
  {
    super(paramPicture, paramNonCompensatingResultPositioner, paramViewport);
  }

  public Picture getBackground(Context paramContext, Viewport paramViewport)
  {
    return getOriginalQueryImage();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.RotatingImageBackground
 * JD-Core Version:    0.6.2
 */