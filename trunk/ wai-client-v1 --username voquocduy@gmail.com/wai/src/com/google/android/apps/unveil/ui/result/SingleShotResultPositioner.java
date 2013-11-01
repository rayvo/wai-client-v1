package com.google.android.apps.unveil.ui.result;

import android.graphics.RectF;
import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.android.apps.unveil.results.ResultModel;

public class SingleShotResultPositioner extends ResultPositioner
{
  private static final UnveilLogger logger = new UnveilLogger();

  private void rotateToPictureCoordinates(RectF paramRectF, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramRectF == null)
      return;
    float f1 = paramRectF.left;
    float f2 = paramRectF.top;
    float f3 = paramRectF.right;
    float f4 = paramRectF.bottom;
    switch (paramInt1)
    {
    case 0:
    default:
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt1);
      localUnveilLogger.e("Viewport reported unexpected rotation: %d", arrayOfObject);
      return;
    case -270:
    case 90:
      paramRectF.left = (paramInt2 - f4);
      paramRectF.top = f1;
      paramRectF.right = (paramInt2 - f2);
      paramRectF.bottom = f3;
      return;
    case -540:
    case -180:
    case 180:
    case 540:
      paramRectF.left = (paramInt2 - f3);
      paramRectF.top = (paramInt3 - f4);
      paramRectF.right = (paramInt2 - f1);
      paramRectF.bottom = (paramInt3 - f2);
      return;
    case -90:
    case 270:
    }
    paramRectF.left = f2;
    paramRectF.top = (paramInt3 - f3);
    paramRectF.right = f4;
    paramRectF.bottom = (paramInt3 - f1);
  }

  protected RectF computeBounds(ResultModel paramResultModel, int paramInt1, int paramInt2, int paramInt3, Viewport paramViewport, int paramInt4, int paramInt5)
  {
    if ((paramResultModel == null) || (paramResultModel.getBoundingBox() == null))
      return null;
    int i;
    int j;
    if (paramInt1 % 180 == 0)
    {
      i = paramInt4;
      if (paramInt1 % 180 != 0)
        break label95;
      j = paramInt5;
    }
    while (true)
      if (paramResultModel.getQueryType() == QueryResponseFactory.QueryType.LOCAL_BARCODE)
      {
        Size localSize = paramViewport.getRotatedPreviewSize(paramViewport.getLatestBarcodeQueryRotation());
        if (localSize == null)
        {
          logger.w("Failed to compute rotated bounds because Viewport's previewSize was not initialized.", new Object[0]);
          return new RectF(paramResultModel.getBoundingBox());
          i = paramInt5;
          break;
          label95: j = paramInt4;
          continue;
        }
        RectF localRectF2 = new RectF(paramViewport.rotateBarcodeBox(paramResultModel.getBoundingBox(), paramViewport.getLatestBarcodeQueryRotation()));
        if (Math.abs(paramInt1 % 180) == 0)
        {
          rotateToPictureCoordinates(localRectF2, paramInt1, localSize.width, localSize.height);
          GeometryUtils.translate(localRectF2, paramInt2, paramInt3, localSize.width, localSize.height);
          return localRectF2;
        }
        rotateToPictureCoordinates(localRectF2, paramInt1, localSize.height, localSize.width);
        GeometryUtils.translate(localRectF2, paramInt2, paramInt3, localSize.height, localSize.width);
        return localRectF2;
      }
    RectF localRectF1 = new RectF(paramResultModel.getBoundingBox());
    rotateToPictureCoordinates(localRectF1, paramInt1, i, j);
    GeometryUtils.translate(localRectF1, paramInt2, paramInt3, i, j);
    return localRectF1;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.SingleShotResultPositioner
 * JD-Core Version:    0.6.2
 */