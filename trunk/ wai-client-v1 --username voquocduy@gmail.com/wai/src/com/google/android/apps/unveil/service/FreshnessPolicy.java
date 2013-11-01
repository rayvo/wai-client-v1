package com.google.android.apps.unveil.service;

import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.env.media.ImageLoader.Policy;
import java.util.Collections;
import java.util.List;

public class FreshnessPolicy
  implements ImageLoader.Policy<ImageLoader.Image>
{
  private final FreshnessHelper freshnessHelper;

  public FreshnessPolicy(FreshnessHelper paramFreshnessHelper)
  {
    this.freshnessHelper = paramFreshnessHelper;
  }

  public List<ImageLoader.Image> apply(List<ImageLoader.Image> paramList)
  {
    Collections.sort(paramList);
    return this.freshnessHelper.getFreshImages(paramList);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.FreshnessPolicy
 * JD-Core Version:    0.6.2
 */