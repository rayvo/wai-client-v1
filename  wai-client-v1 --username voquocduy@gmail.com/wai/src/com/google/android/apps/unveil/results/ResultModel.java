package com.google.android.apps.unveil.results;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ResultModel
  implements Serializable
{
  public static List<? extends ResultModel> excludeDirectUrlResults(List<? extends ResultModel> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ResultModel localResultModel = (ResultModel)localIterator.next();
      if (TextUtils.isEmpty(localResultModel.getDirectUrl()))
        localArrayList.add(localResultModel);
    }
    return localArrayList;
  }

  public abstract boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent);

  public abstract Rect getBoundingBox();

  public String getDirectUrl()
  {
    return "";
  }

  public abstract String getImageUrl();

  public abstract QueryResponseFactory.QueryType getQueryType();

  public String getReferrerUrl()
  {
    return "";
  }

  public abstract String getThumbnailUrl();

  public abstract String getTitle();

  public abstract String getWebSearchUrl();

  public abstract boolean hasImageUrl();

  public boolean isAdvertisement()
  {
    return false;
  }

  public abstract boolean isUserGenerated();
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.ResultModel
 * JD-Core Version:    0.6.2
 */