package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.plurals;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.AnnotationResultProtos.AnnotationResult.TextInformation;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.ProductInformationProtos.ProductInformation.PriceInformation;
import java.util.Locale;

public class ResultViewUtils
{
  static void setRating(ViewGroup paramViewGroup, float paramFloat)
  {
    ViewGroup localViewGroup = (ViewGroup)paramViewGroup.findViewById(R.id.rating_container);
    int i = 0;
    if (i < 5)
    {
      ImageView localImageView = (ImageView)localViewGroup.getChildAt(i);
      int j = R.drawable.star_empty;
      if (paramFloat >= 1.0F)
        j = R.drawable.star_full;
      while (true)
      {
        localImageView.setImageDrawable(localViewGroup.getContext().getResources().getDrawable(j));
        paramFloat -= 1.0F;
        i++;
        break;
        if (paramFloat >= 0.5F)
          j = R.drawable.star_half;
      }
    }
  }

  public boolean isBookResult(ResultItem paramResultItem)
  {
    if (!paramResultItem.getAnnotationResult().hasProductInfo());
    ProductInformationProtos.ProductInformation localProductInformation;
    do
    {
      return false;
      localProductInformation = paramResultItem.getAnnotationResult().getProductInfo();
    }
    while ((!localProductInformation.hasAuthor()) || (!localProductInformation.hasPublisher()));
    return true;
  }

  public boolean isProductResult(ResultItem paramResultItem)
  {
    if (!paramResultItem.getAnnotationResult().hasProductInfo());
    ProductInformationProtos.ProductInformation localProductInformation;
    do
    {
      return false;
      localProductInformation = paramResultItem.getAnnotationResult().getProductInfo();
    }
    while ((localProductInformation.getNumReviews() <= 0) && (localProductInformation.getStarRating() <= 0.0F) && (localProductInformation.getPricesCount() <= 0));
    return true;
  }

  public boolean isTranslationResult(ResultItem paramResultItem)
  {
    return (paramResultItem.getAnnotationResult().hasTextInfo()) && (paramResultItem.getAnnotationResult().hasLanguage());
  }

  public void setBookLabels(ResultItem paramResultItem, View paramView)
  {
    paramView.findViewById(R.id.book_content).setVisibility(0);
    ProductInformationProtos.ProductInformation localProductInformation = paramResultItem.getAnnotationResult().getProductInfo();
    Context localContext = paramView.getContext();
    TextView localTextView1 = (TextView)paramView.findViewById(R.id.book_title_and_author);
    String str1 = localContext.getString(R.string.title_author_template);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = paramResultItem.getTitle();
    arrayOfObject1[1] = localProductInformation.getAuthor();
    localTextView1.setText(String.format(str1, arrayOfObject1));
    setReviews((LinearLayout)paramView.findViewById(R.id.book_rating_container), localProductInformation, R.id.book_reviews);
    TextView localTextView2 = (TextView)paramView.findViewById(R.id.book_publisher);
    String str2 = localContext.getString(R.string.publisher_year_template);
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = localProductInformation.getPublisher();
    arrayOfObject2[1] = localProductInformation.getPublicationYear();
    localTextView2.setText(String.format(str2, arrayOfObject2));
    setPrices((TextView)paramView.findViewById(R.id.book_price), localProductInformation);
  }

  void setPrices(TextView paramTextView, ProductInformationProtos.ProductInformation paramProductInformation)
  {
    if (paramProductInformation.getPricesCount() > 0)
    {
      paramTextView.setText(paramProductInformation.getPrices(0).getPriceSummary());
      return;
    }
    paramTextView.setVisibility(8);
  }

  public void setProductLabels(ResultItem paramResultItem, View paramView)
  {
    paramView.findViewById(R.id.product_content).setVisibility(0);
    ProductInformationProtos.ProductInformation localProductInformation = paramResultItem.getAnnotationResult().getProductInfo();
    ((TextView)paramView.findViewById(R.id.product_title)).setText(paramResultItem.getTitle());
    setReviews((LinearLayout)paramView.findViewById(R.id.product_rating_container), localProductInformation, R.id.product_reviews);
    setPrices((TextView)paramView.findViewById(R.id.product_price), localProductInformation);
  }

  public void setReviews(LinearLayout paramLinearLayout, ProductInformationProtos.ProductInformation paramProductInformation, int paramInt)
  {
    if ((paramProductInformation.getNumReviews() > 0) || (paramProductInformation.getStarRating() > 0.0F))
    {
      paramLinearLayout.setVisibility(0);
      setRating(paramLinearLayout, paramProductInformation.getStarRating());
      TextView localTextView = (TextView)paramLinearLayout.findViewById(paramInt);
      Resources localResources = paramLinearLayout.getContext().getResources();
      int i = R.plurals.review_count_template;
      int j = paramProductInformation.getNumReviews();
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramProductInformation.getNumReviews());
      localTextView.setText(localResources.getQuantityString(i, j, arrayOfObject));
      return;
    }
    paramLinearLayout.setVisibility(8);
  }

  public void setTranslationLabels(ResultItem paramResultItem, View paramView)
  {
    Locale localLocale = new Locale(paramResultItem.getAnnotationResult().getLanguage());
    paramView.findViewById(R.id.translation_content).setVisibility(0);
    ((TextView)paramView.findViewById(R.id.source_language)).setText(localLocale.getDisplayLanguage());
    ((TextView)paramView.findViewById(R.id.destination_language)).setText(Locale.getDefault().getDisplayLanguage());
    ((TextView)paramView.findViewById(R.id.text)).setText(paramResultItem.getTitle());
    ((TextView)paramView.findViewById(R.id.translation)).setText(paramResultItem.getAnnotationResult().getTextInfo().getTranslatedTitle());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.ResultViewUtils
 * JD-Core Version:    0.6.2
 */