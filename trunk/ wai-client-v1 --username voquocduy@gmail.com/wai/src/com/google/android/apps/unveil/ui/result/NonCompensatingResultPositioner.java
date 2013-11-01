package com.google.android.apps.unveil.ui.result;

import android.graphics.RectF;
import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.android.apps.unveil.results.ResultModel;

public class NonCompensatingResultPositioner extends ResultPositioner
{
  private static final UnveilLogger logger = new UnveilLogger();

  protected RectF computeBounds(ResultModel paramResultModel, int paramInt1, int paramInt2, int paramInt3, Viewport paramViewport, int paramInt4, int paramInt5)
  {
    if ((paramResultModel == null) || (paramResultModel.getBoundingBox() == null))
      return null;
    RectF localRectF1 = new RectF(paramResultModel.getBoundingBox());
    if (paramResultModel.getQueryType() == QueryResponseFactory.QueryType.LOCAL_BARCODE)
    {
      Size localSize = paramViewport.getRotatedPreviewSize(paramViewport.getLatestBarcodeQueryRotation());
      if (localSize == null)
      {
        logger.w("Failed to compute rotated bounds because Viewport's previewSize was not initialized.", new Object[0]);
        return localRectF1;
      }
      RectF localRectF2 = new RectF(paramViewport.rotateBarcodeBox(paramResultModel.getBoundingBox(), paramViewport.getLatestBarcodeQueryRotation()));
      if (Math.abs(paramInt1 % 180) == 0)
      {
        GeometryUtils.translate(localRectF2, paramInt2, paramInt3, localSize.width, localSize.height);
        return localRectF2;
      }
      GeometryUtils.translate(localRectF2, paramInt2, paramInt3, localSize.height, localSize.width);
      return localRectF2;
    }
    GeometryUtils.translate(localRectF1, paramInt2, paramInt3, paramInt4, paramInt5);
    return localRectF1;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.NonCompensatingResultPositioner
 * JD-Core Version:    0.6.2
 */