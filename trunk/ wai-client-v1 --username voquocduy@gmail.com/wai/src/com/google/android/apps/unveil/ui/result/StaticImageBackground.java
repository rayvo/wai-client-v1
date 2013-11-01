package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.sensors.CameraManager;

public class StaticImageBackground extends QueryImageBackground
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Picture currentImageBackground;

  public StaticImageBackground(Picture paramPicture, SingleShotResultPositioner paramSingleShotResultPositioner, Viewport paramViewport)
  {
    super(paramPicture, paramSingleShotResultPositioner, paramViewport);
  }

  public Picture getBackground(Context paramContext, Viewport paramViewport)
  {
    Bitmap localBitmap = getOriginalQueryImage().peekBitmap();
    int i = paramViewport.computeImageRotationDegree(paramContext, getOriginalQueryImage());
    boolean bool = CameraManager.isFrontFacingCamera(paramContext);
    if ((i != 0) || (bool))
    {
      if (localBitmap == null)
      {
        logger.e("Failed to rotate query image. Fallback to original one.", new Object[0]);
        return getOriginalQueryImage();
      }
      Matrix localMatrix = new Matrix();
      if (bool)
        localMatrix.preScale(-1.0F, 1.0F);
      localMatrix.postRotate(i);
      this.currentImageBackground = PictureFactory.createBitmap(Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true), 0);
      return this.currentImageBackground;
    }
    return getOriginalQueryImage();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.StaticImageBackground
 * JD-Core Version:    0.6.2
 */