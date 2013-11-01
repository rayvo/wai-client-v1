package com.google.android.apps.unveil.ui.result;

import android.graphics.RectF;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public abstract class ResultPositioner
{
  private static final UnveilLogger logger = new UnveilLogger();

  private final List<ResultSelectionView.BoundedResult> computeBounds(List<ResultModel> paramList, int paramInt1, int paramInt2, int paramInt3, Viewport paramViewport, int paramInt4, int paramInt5)
  {
    LinkedList localLinkedList = new LinkedList();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ResultModel localResultModel = (ResultModel)localIterator.next();
      localLinkedList.add(new ResultSelectionView.BoundedResult(localResultModel, computeBounds(localResultModel, paramInt1, paramInt2, paramInt3, paramViewport, paramInt4, paramInt5)));
    }
    return localLinkedList;
  }

  protected abstract RectF computeBounds(ResultModel paramResultModel, int paramInt1, int paramInt2, int paramInt3, Viewport paramViewport, int paramInt4, int paramInt5);

  final List<ResultSelectionView.BoundedResult> onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, Viewport paramViewport, int paramInt6, int paramInt7, List<ResultModel> paramList)
  {
    if ((paramList == null) || (paramList.isEmpty()))
      return Collections.emptyList();
    return computeBounds(paramList, paramInt5, paramInt3 - paramInt1, paramInt4 - paramInt2, paramViewport, paramInt6, paramInt7);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ResultPositioner
 * JD-Core Version:    0.6.2
 */