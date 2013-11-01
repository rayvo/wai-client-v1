package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.CameraManager;

public class CameraWrappingLayout extends FrameLayout
{
  Alignment alignment = Alignment.TOP;
  private CameraLayoutHandler cameraLayoutHandler;
  private CameraManager cameraManager;
  private Matrix frameToCanvas;
  private final UnveilLogger logger = new UnveilLogger();
  private boolean mirrored = false;
  private int rotation;
  private ScaleType scaleType = ScaleType.FIT;

  public CameraWrappingLayout(Context paramContext)
  {
    super(paramContext);
  }

  public CameraWrappingLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public CameraWrappingLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  static Size getDisplayedSize(Size paramSize1, Size paramSize2, int paramInt, ScaleType paramScaleType)
  {
    if (paramScaleType == ScaleType.FILL)
      return paramSize1;
    float f;
    int i;
    if ((paramInt == 90) || (paramInt == 270))
    {
      f = paramSize2.height / paramSize2.width;
      if (f <= paramSize1.width / paramSize1.height)
        break label97;
      i = paramSize1.width;
    }
    for (int j = (int)(paramSize1.width / f); ; j = paramSize1.height)
    {
      return new Size(i, j);
      f = paramSize2.width / paramSize2.height;
      break;
      label97: i = (int)(f * paramSize1.height);
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    this.cameraManager.onConfigurationChanged(paramConfiguration);
  }

  public void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    UnveilLogger localUnveilLogger1 = this.logger;
    Object[] arrayOfObject1 = new Object[5];
    arrayOfObject1[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject1[1] = Integer.valueOf(paramInt1);
    arrayOfObject1[2] = Integer.valueOf(paramInt2);
    arrayOfObject1[3] = Integer.valueOf(paramInt3);
    arrayOfObject1[4] = Integer.valueOf(paramInt4);
    localUnveilLogger1.i("onLayout: %b, %d, %d, %d, %d", arrayOfObject1);
    if ((!paramBoolean) && (this.cameraManager.isPreviewing()))
      this.logger.i("Already previewing or no layout needed, but recursing anyway.", new Object[0]);
    Size localSize1 = new Size(paramInt3 - paramInt1, paramInt4 - paramInt2);
    this.logger.i("Full view size: %s", new Object[] { localSize1 });
    Size localSize3;
    label441: 
    do
    {
      Size localSize2;
      synchronized (this.cameraManager)
      {
        this.cameraManager.setFullScreenDisplaySize(localSize1);
        if ((this.rotation == 90) || (this.rotation == 270))
        {
          localSize2 = this.cameraManager.getOptimalPreviewSize(localSize1.height, localSize1.width);
          if (localSize2 == null)
          {
            this.logger.w("Preview size was null!", new Object[0]);
            super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
          }
        }
        else
        {
          localSize2 = this.cameraManager.getOptimalPreviewSize(localSize1.width, localSize1.height);
        }
      }
      localSize3 = getDisplayedSize(localSize1, localSize2, this.rotation, this.scaleType);
      int i = (localSize1.width - localSize3.width) / 2;
      int j = (localSize1.height - localSize3.height) / 2;
      this.frameToCanvas = ImageUtils.getTransformationMatrix(localSize2, localSize3, this.rotation);
      if (this.mirrored)
      {
        Matrix localMatrix = new Matrix();
        localMatrix.postScale(-1.0F, 1.0F);
        localMatrix.postTranslate(localSize2.width, 0.0F);
        this.frameToCanvas.preConcat(localMatrix);
      }
      int k;
      View localView;
      switch (1.$SwitchMap$com$google$android$apps$unveil$ui$CameraWrappingLayout$Alignment[this.alignment.ordinal()])
      {
      default:
        UnveilLogger localUnveilLogger3 = this.logger;
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = this.alignment.name();
        localUnveilLogger3.e("Error: unsupported alignment %s", arrayOfObject3);
      case 3:
        this.frameToCanvas.postTranslate(i, j);
        k = 0;
        if (k < getChildCount())
        {
          localView = getChildAt(k);
          switch (1.$SwitchMap$com$google$android$apps$unveil$ui$CameraWrappingLayout$Alignment[this.alignment.ordinal()])
          {
          default:
            UnveilLogger localUnveilLogger2 = this.logger;
            Object[] arrayOfObject2 = new Object[1];
            arrayOfObject2[0] = this.alignment.name();
            localUnveilLogger2.e("Error: unsupported alignment %s", arrayOfObject2);
          case 3:
            localView.layout(i, j, i + localSize3.width, j + localSize3.height);
          case 1:
          case 2:
          }
        }
        break;
      case 1:
      case 2:
        while (true)
        {
          k++;
          break label441;
          this.frameToCanvas.postTranslate(i, 0.0F);
          break;
          this.frameToCanvas.postTranslate(i, j * 2);
          break;
          localView.layout(i, 0, i + localSize3.width, localSize3.height);
          continue;
          localView.layout(i, j * 2, i + localSize3.width, j * 2 + localSize3.height);
        }
      }
    }
    while (this.cameraLayoutHandler == null);
    this.cameraLayoutHandler.onCameraLayoutFinished(localSize3, this.frameToCanvas);
  }

  public void setAlignment(Alignment paramAlignment)
  {
    this.alignment = paramAlignment;
  }

  public void setCameraLayoutHandler(CameraLayoutHandler paramCameraLayoutHandler)
  {
    this.cameraLayoutHandler = paramCameraLayoutHandler;
  }

  public void setCameraManager(CameraManager paramCameraManager)
  {
    this.cameraManager = paramCameraManager;
  }

  public void setMirrored(boolean paramBoolean)
  {
    this.mirrored = paramBoolean;
  }

  public void setRotation(int paramInt)
  {
    this.rotation = paramInt;
  }

  public void setScaleType(ScaleType paramScaleType)
  {
    this.scaleType = paramScaleType;
  }

  public static enum Alignment
  {
    static
    {
      CENTER = new Alignment("CENTER", 1);
      BOTTOM = new Alignment("BOTTOM", 2);
      Alignment[] arrayOfAlignment = new Alignment[3];
      arrayOfAlignment[0] = TOP;
      arrayOfAlignment[1] = CENTER;
      arrayOfAlignment[2] = BOTTOM;
    }
  }

  public static abstract interface CameraLayoutHandler
  {
    public abstract void onCameraLayoutFinished(Size paramSize, Matrix paramMatrix);
  }

  public static enum ScaleType
  {
    static
    {
      FILL = new ScaleType("FILL", 1);
      ScaleType[] arrayOfScaleType = new ScaleType[2];
      arrayOfScaleType[0] = FIT;
      arrayOfScaleType[1] = FILL;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.CameraWrappingLayout
 * JD-Core Version:    0.6.2
 */