package com.google.android.apps.unveil.results;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.CanonicalImageProtos.CanonicalImage;
import com.google.goggles.ResultProtos.Result;
import java.io.Serializable;
import java.util.List;

public class ResultItem extends ResultModel
  implements Serializable
{
  public static final String LOCAL_BARCODE_RESULT_ID_PREFIX = "local_barcode";
  public static final int UNKNOWN_SEQUENCE_NUMBER = -1;
  private static final UnveilLogger logger = new UnveilLogger();
  static final long serialVersionUID = 1L;
  protected final AnnotationResultProtos.AnnotationResult annotation;
  private final BoundingBoxProtos.BoundingBox boundingBox;
  private transient Rect boundingBoxRect;
  private final String directUrl;
  private final QueryResponseFactory.QueryType queryType;
  private final int resultPosition;
  private final String subtitle;
  private final String subtype;
  private final String thumbnailUrl;
  private final String title;
  private final String ttsDescription;
  private final String type;
  private final String webSearchUrl;

  public ResultItem(ResultProtos.Result paramResult, QueryResponseFactory.QueryType paramQueryType, int paramInt)
  {
    AnnotationResultProtos.AnnotationResult localAnnotationResult = (AnnotationResultProtos.AnnotationResult)paramResult.getExtension(AnnotationResultProtos.AnnotationResult.annotationResult);
    String str;
    if (localAnnotationResult.hasBoundingBox())
    {
      this.boundingBox = localAnnotationResult.getBoundingBox();
      this.boundingBoxRect = toRect(this.boundingBox);
      boolean bool1 = localAnnotationResult.hasCanonicalImage();
      str = null;
      if (bool1)
      {
        CanonicalImageProtos.CanonicalImage localCanonicalImage = localAnnotationResult.getCanonicalImage();
        boolean bool2 = localCanonicalImage.hasThumbnailUrl();
        str = null;
        if (bool2)
          str = localCanonicalImage.getThumbnailUrl();
      }
      this.title = localAnnotationResult.getTitle();
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.title;
      localUnveilLogger.d("Got result %s", arrayOfObject);
      this.subtitle = localAnnotationResult.getSubtitle();
      this.type = localAnnotationResult.getType();
      this.subtype = localAnnotationResult.getSubtype();
      if ((!TextUtils.isEmpty(localAnnotationResult.getTtsDescription())) || (!"Banknote".equals(this.type)))
        break label256;
    }
    label256: for (this.ttsDescription = this.title; ; this.ttsDescription = localAnnotationResult.getTtsDescription())
    {
      this.directUrl = localAnnotationResult.getDirectUrl();
      this.webSearchUrl = localAnnotationResult.getWebsearchUrl();
      this.thumbnailUrl = str;
      this.queryType = paramQueryType;
      this.annotation = ((AnnotationResultProtos.AnnotationResult)paramResult.getExtension(AnnotationResultProtos.AnnotationResult.annotationResult));
      this.resultPosition = paramInt;
      if (this.annotation != null)
        return;
      throw new IllegalArgumentException("No annotation result found.");
      this.boundingBox = null;
      break;
    }
  }

  private ResultItem(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, BoundingBoxProtos.BoundingBox paramBoundingBox, String paramString6, String paramString7, AnnotationResultProtos.AnnotationResult paramAnnotationResult, String paramString8, QueryResponseFactory.QueryType paramQueryType, int paramInt)
  {
    this.title = paramString1;
    this.subtitle = paramString2;
    this.type = paramString3;
    this.subtype = paramString4;
    this.ttsDescription = paramString5;
    this.boundingBox = paramBoundingBox;
    this.directUrl = paramString6;
    this.webSearchUrl = paramString7;
    this.thumbnailUrl = paramString8;
    this.annotation = paramAnnotationResult;
    this.queryType = paramQueryType;
    this.resultPosition = paramInt;
    if (this.annotation == null)
      throw new IllegalArgumentException("No annotation result found.");
  }

  private static Rect toRect(BoundingBoxProtos.BoundingBox paramBoundingBox)
  {
    return new Rect(paramBoundingBox.getX(), paramBoundingBox.getY(), paramBoundingBox.getX() + paramBoundingBox.getWidth(), paramBoundingBox.getY() + paramBoundingBox.getHeight());
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    paramAccessibilityEvent.getText().add(getTtsDescription());
    return true;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    ResultItem localResultItem;
    do
    {
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localResultItem = (ResultItem)paramObject;
        if (isAdvertisement() != localResultItem.isAdvertisement())
          return false;
        if (this.subtitle == null)
        {
          if (localResultItem.subtitle != null)
            return false;
        }
        else if (!this.subtitle.equals(localResultItem.subtitle))
          return false;
        if (this.subtype == null)
        {
          if (localResultItem.subtype != null)
            return false;
        }
        else if (!this.subtype.equals(localResultItem.subtype))
          return false;
        if (this.ttsDescription == null)
        {
          if (localResultItem.ttsDescription != null)
            return false;
        }
        else if (!this.ttsDescription.equals(localResultItem.ttsDescription))
          return false;
        if (this.thumbnailUrl == null)
        {
          if (localResultItem.thumbnailUrl != null)
            return false;
        }
        else if (!this.thumbnailUrl.equals(localResultItem.thumbnailUrl))
          return false;
        if (this.title == null)
        {
          if (localResultItem.title != null)
            return false;
        }
        else if (!this.title.equals(localResultItem.title))
          return false;
        if (this.type != null)
          break;
      }
      while (localResultItem.type == null);
      return false;
    }
    while (this.type.equals(localResultItem.type));
    return false;
  }

  public AnnotationResultProtos.AnnotationResult getAnnotationResult()
  {
    return this.annotation;
  }

  public Rect getBoundingBox()
  {
    try
    {
      if ((this.boundingBoxRect == null) && (this.boundingBox != null))
        this.boundingBoxRect = toRect(this.boundingBox);
      Rect localRect = this.boundingBoxRect;
      return localRect;
    }
    finally
    {
    }
  }

  public String getDirectUrl()
  {
    return this.directUrl;
  }

  public String getImageUrl()
  {
    if (!hasAnnotationResult())
      return "";
    return getAnnotationResult().getCanonicalImage().getImageUrl();
  }

  public String getKeyFromHashCode()
  {
    return String.valueOf(hashCode());
  }

  public QueryResponseFactory.QueryType getQueryType()
  {
    return this.queryType;
  }

  public String getReferrerUrl()
  {
    if (!hasAnnotationResult())
      return "";
    return getAnnotationResult().getCanonicalImage().getReferrerUrl();
  }

  public int getResultPosition()
  {
    return this.resultPosition;
  }

  public String getSubtitle()
  {
    if (TextUtils.isEmpty(this.subtitle))
      return this.subtitle;
    return " - " + this.subtitle;
  }

  public String getSubtype()
  {
    if (TextUtils.isEmpty(this.subtype))
      return this.subtype;
    return " - " + this.subtype;
  }

  public String getThumbnailUrl()
  {
    return this.thumbnailUrl;
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getTtsDescription()
  {
    if (hasRealTtsDescription())
      return this.ttsDescription;
    StringBuilder localStringBuilder = new StringBuilder();
    if (!TextUtils.isEmpty(this.type))
    {
      localStringBuilder.append(this.type);
      localStringBuilder.append(". ");
    }
    if (!TextUtils.isEmpty(this.subtype))
    {
      localStringBuilder.append(this.subtype);
      localStringBuilder.append(". ");
    }
    if (!TextUtils.isEmpty(this.title))
    {
      localStringBuilder.append(this.title);
      localStringBuilder.append(". ");
    }
    if (!TextUtils.isEmpty(this.subtitle))
    {
      localStringBuilder.append(this.subtitle);
      localStringBuilder.append(". ");
    }
    return localStringBuilder.toString();
  }

  public String getType()
  {
    return this.type;
  }

  public String getWebSearchUrl()
  {
    return this.webSearchUrl;
  }

  public boolean hasAnnotationResult()
  {
    return this.annotation != null;
  }

  public boolean hasImageUrl()
  {
    if (!hasAnnotationResult())
      return false;
    return getAnnotationResult().getCanonicalImage().hasImageUrl();
  }

  public boolean hasRealTtsDescription()
  {
    return !TextUtils.isEmpty(this.ttsDescription);
  }

  public int hashCode()
  {
    int i;
    int k;
    label26: int n;
    label44: int i2;
    label64: int i4;
    label84: int i6;
    label104: int i8;
    label124: int i9;
    int i10;
    if (this.subtitle == null)
    {
      i = 0;
      int j = 31 * (i + 31);
      if (this.subtype != null)
        break label165;
      k = 0;
      int m = 31 * (j + k);
      if (this.thumbnailUrl != null)
        break label176;
      n = 0;
      int i1 = 31 * (m + n);
      if (this.title != null)
        break label188;
      i2 = 0;
      int i3 = 31 * (i1 + i2);
      if (this.type != null)
        break label200;
      i4 = 0;
      int i5 = 31 * (i3 + i4);
      if (this.ttsDescription != null)
        break label212;
      i6 = 0;
      int i7 = 31 * (i5 + i6);
      if (this.directUrl != null)
        break label224;
      i8 = 0;
      i9 = 31 * (i7 + i8);
      String str = this.webSearchUrl;
      i10 = 0;
      if (str != null)
        break label236;
    }
    while (true)
    {
      return i9 + i10;
      i = this.subtitle.hashCode();
      break;
      label165: k = this.subtype.hashCode();
      break label26;
      label176: n = this.thumbnailUrl.hashCode();
      break label44;
      label188: i2 = this.title.hashCode();
      break label64;
      label200: i4 = this.type.hashCode();
      break label84;
      label212: i6 = this.ttsDescription.hashCode();
      break label104;
      label224: i8 = this.directUrl.hashCode();
      break label124;
      label236: i10 = this.webSearchUrl.hashCode();
    }
  }

  public boolean isAdvertisement()
  {
    return this.annotation.getIsAd();
  }

  public boolean isPlace()
  {
    return this.annotation.hasPlaceInfo();
  }

  public boolean isProduct()
  {
    return this.annotation.hasProductInfo();
  }

  public boolean isUserGenerated()
  {
    return this.annotation.hasContributionInfo();
  }

  public boolean isWithdrawn()
  {
    return this.annotation.getIsWithdrawn();
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("ResultItem [directUrl=");
    localStringBuilder.append(this.directUrl);
    localStringBuilder.append(", webSearchUrl=");
    localStringBuilder.append(this.webSearchUrl);
    localStringBuilder.append(", title=");
    localStringBuilder.append(this.title);
    localStringBuilder.append(", subtitle=");
    localStringBuilder.append(this.subtitle);
    localStringBuilder.append(", type=");
    localStringBuilder.append(this.type);
    localStringBuilder.append(", subtype=");
    localStringBuilder.append(this.subtype);
    localStringBuilder.append(", ttsDescription=");
    localStringBuilder.append(this.ttsDescription);
    localStringBuilder.append(", thumbnailUrl=");
    localStringBuilder.append(this.thumbnailUrl);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.ResultItem
 * JD-Core Version:    0.6.2
 */