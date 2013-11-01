package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.List;

public abstract class QueryImageBackground
{
  protected final ResultPositioner positioner;
  private final Picture queryImageCopy;
  private final Viewport viewport;

  public QueryImageBackground(Picture paramPicture, ResultPositioner paramResultPositioner, Viewport paramViewport)
  {
    this.positioner = paramResultPositioner;
    this.queryImageCopy = PictureFactory.createJpeg(paramPicture);
    this.viewport = paramViewport;
  }

  public static QueryImageBackground from(UnveilContext paramUnveilContext, QueryResponse paramQueryResponse, Picture paramPicture)
  {
    switch (1.$SwitchMap$com$google$android$apps$unveil$protocol$QueryResponseFactory$QueryType[paramQueryResponse.getQueryType().ordinal()])
    {
    default:
      return new StaticImageBackground(paramPicture, new SingleShotResultPositioner(), paramUnveilContext.getViewport());
    case 1:
    case 2:
    }
    return new RotatingImageBackground(paramPicture, new NonCompensatingResultPositioner(), paramUnveilContext.getViewport());
  }

  public abstract Picture getBackground(Context paramContext, Viewport paramViewport);

  protected final Picture getOriginalQueryImage()
  {
    return this.queryImageCopy;
  }

  final List<ResultSelectionView.BoundedResult> onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, Context paramContext, List<ResultModel> paramList)
  {
    return this.positioner.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4, this.viewport.computeImageRotationDegree(paramContext, this.queryImageCopy), this.viewport, this.queryImageCopy.getSize().width, this.queryImageCopy.getSize().height, paramList);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.QueryImageBackground
 * JD-Core Version:    0.6.2
 */