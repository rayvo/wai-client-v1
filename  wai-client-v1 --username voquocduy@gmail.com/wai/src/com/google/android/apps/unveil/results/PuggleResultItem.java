package com.google.android.apps.unveil.results;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.CanonicalImageProtos.CanonicalImage;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.ProductInformationProtos.ProductInformation.OfferInformation;
import com.google.goggles.ProductInformationProtos.ProductInformation.PriceInformation;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Category.Domain;
import com.google.goggles.ResultProtos.Result;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class PuggleResultItem extends ResultItem
  implements Serializable
{
  private static final UnveilLogger logger = new UnveilLogger();
  public double price;
  private List<PuggleResultItem> relatedItems = new ArrayList();

  public PuggleResultItem(ResultProtos.Result paramResult, QueryResponseFactory.QueryType paramQueryType, int paramInt)
  {
    super(paramResult, paramQueryType, paramInt);
    ProductInformationProtos.ProductInformation localProductInformation = getAnnotationResult().getProductInfo();
    if (localProductInformation.getPricesCount() > 0)
      this.price = localProductInformation.getPrices(0).getLowPrice();
  }

  public String getBrand()
  {
    if (this.annotation.hasProductInfo())
      return this.annotation.getProductInfo().getBrand();
    return "";
  }

  public String getClusterId()
  {
    if ((this.annotation.hasProductInfo()) && (this.annotation.getProductInfo().hasClusterId()))
      return this.annotation.getProductInfo().getClusterId();
    return "";
  }

  public String getDisplayablePrettyNameString()
  {
    if ((!this.annotation.hasProductInfo()) || (this.annotation.getProductInfo().getCategoriesCount() == 0))
      return "";
    Iterator localIterator = this.annotation.getProductInfo().getCategoriesList().iterator();
    while (localIterator.hasNext())
    {
      RestrictsProtos.Category localCategory = (RestrictsProtos.Category)localIterator.next();
      if (localCategory.getDomain() == RestrictsProtos.Category.Domain.PRETTY_NAME)
        return localCategory.getDisplayName();
    }
    return "";
  }

  public String getImageUrl()
  {
    return this.annotation.getCanonicalImage().getImageUrl();
  }

  public String getPrice()
  {
    if ((this.annotation.hasProductInfo()) && (this.annotation.getProductInfo().getPricesCount() > 0))
      return this.annotation.getProductInfo().getPrices(0).getPriceSummary();
    return "";
  }

  public String getReferrerUrl()
  {
    return this.annotation.getCanonicalImage().getReferrerUrl();
  }

  public List<PuggleResultItem> getRelatedItems()
  {
    return this.relatedItems;
  }

  public int getRelatedItemsCount()
  {
    return this.relatedItems.size();
  }

  public boolean isCatalog()
  {
    return (this.annotation.hasProductInfo()) && (this.annotation.getProductInfo().getOffersCount() > 1);
  }

  public boolean isOffer()
  {
    return (this.annotation.hasProductInfo()) && (this.annotation.getProductInfo().getOffersCount() == 1);
  }

  public void setRelatedItems(List<PuggleResultItem> paramList)
  {
    this.relatedItems = new ArrayList(paramList);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PuggleResultItem [");
    localStringBuilder.append(super.toString());
    localStringBuilder.append(", referrerUrl=");
    localStringBuilder.append(getReferrerUrl());
    ProductInformationProtos.ProductInformation localProductInformation = getAnnotationResult().getProductInfo();
    localStringBuilder.append(", productInfo=[");
    localStringBuilder.append("cid=");
    localStringBuilder.append(localProductInformation.getCid());
    localStringBuilder.append(", brand=");
    localStringBuilder.append(localProductInformation.getBrand());
    localStringBuilder.append(", prices=[");
    Iterator localIterator1 = localProductInformation.getPricesList().iterator();
    while (localIterator1.hasNext())
    {
      ProductInformationProtos.ProductInformation.PriceInformation localPriceInformation = (ProductInformationProtos.ProductInformation.PriceInformation)localIterator1.next();
      localStringBuilder.append(localPriceInformation.getPriceSummary());
      localStringBuilder.append("(");
      localStringBuilder.append(localPriceInformation.getLowPrice());
      localStringBuilder.append(",");
      localStringBuilder.append(localPriceInformation.getHighPrice());
      localStringBuilder.append("), ");
    }
    localStringBuilder.append("], clusterId=");
    localStringBuilder.append(localProductInformation.getClusterId());
    localStringBuilder.append(", numReviews=");
    localStringBuilder.append(localProductInformation.getNumReviews());
    localStringBuilder.append(", starRating=");
    localStringBuilder.append(localProductInformation.getStarRating());
    localStringBuilder.append(", categories=[");
    Iterator localIterator2 = localProductInformation.getCategoriesList().iterator();
    while (localIterator2.hasNext())
    {
      RestrictsProtos.Category localCategory = (RestrictsProtos.Category)localIterator2.next();
      localStringBuilder.append(localCategory.getDomain().name());
      localStringBuilder.append(":");
      localStringBuilder.append(localCategory.getName());
      localStringBuilder.append(":");
      localStringBuilder.append(localCategory.getDomainSpecificId());
      localStringBuilder.append(", ");
    }
    localStringBuilder.append("], ");
    localStringBuilder.append(localProductInformation.getOffersCount());
    localStringBuilder.append(" offers");
    localStringBuilder.append("=[");
    Iterator localIterator3 = localProductInformation.getOffersList().iterator();
    while (localIterator3.hasNext())
    {
      ProductInformationProtos.ProductInformation.OfferInformation localOfferInformation = (ProductInformationProtos.ProductInformation.OfferInformation)localIterator3.next();
      localStringBuilder.append("[");
      localStringBuilder.append("merchant=");
      localStringBuilder.append(localOfferInformation.getMerchant());
      localStringBuilder.append(", id=");
      localStringBuilder.append(localOfferInformation.getId());
      localStringBuilder.append(", price=");
      localStringBuilder.append(localOfferInformation.getPrice().getPriceSummary());
      localStringBuilder.append("(");
      localStringBuilder.append(localOfferInformation.getPrice().getLowPrice());
      localStringBuilder.append(",");
      localStringBuilder.append(localOfferInformation.getPrice().getHighPrice());
      localStringBuilder.append("), url=");
      localStringBuilder.append(localOfferInformation.getUrl());
      localStringBuilder.append("]");
    }
    return localStringBuilder.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.PuggleResultItem
 * JD-Core Version:    0.6.2
 */