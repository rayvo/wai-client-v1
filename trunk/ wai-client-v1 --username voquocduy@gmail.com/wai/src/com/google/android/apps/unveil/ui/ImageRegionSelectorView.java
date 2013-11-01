package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;

public class ImageRegionSelectorView extends ImageView
{
  private Size imageSize;
  final UnveilLogger logger = new UnveilLogger();
  private AsymmetricRegionSelectorView regionSelector;

  public ImageRegionSelectorView(Context paramContext)
  {
    super(paramContext);
  }

  public ImageRegionSelectorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public Size getImageSize()
  {
    return this.imageSize;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    Rect localRect = GeometryUtils.fitBox(new Size(getWidth(), getHeight()), this.imageSize);
    if (this.regionSelector != null)
    {
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt3 - paramInt1);
      arrayOfObject[1] = Integer.valueOf(paramInt4 - paramInt2);
      localUnveilLogger.d("Setting layout to %dx%d", arrayOfObject);
      this.regionSelector.setMaxExtents(new Rect(paramInt1 + localRect.left, paramInt2, paramInt3 - (getWidth() - localRect.right), paramInt4));
    }
  }

  public void setAsymmetricRegionSelectorView(AsymmetricRegionSelectorView paramAsymmetricRegionSelectorView)
  {
    this.regionSelector = paramAsymmetricRegionSelectorView;
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    super.setImageBitmap(paramBitmap);
    this.imageSize = new Size(paramBitmap.getWidth(), paramBitmap.getHeight());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ImageRegionSelectorView
 * JD-Core Version:    0.6.2
 */