package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.ui.result.ResultViewUtils;
import com.google.goggles.ProductInformationProtos.ProductInformation;

public class PuggleRowResult extends PuggleResultLayout
{
  private final ResultViewUtils resultViewUtils = new ResultViewUtils();
  protected TextView titleView;

  public PuggleRowResult(Context paramContext)
  {
    super(paramContext);
  }

  protected void init(Context paramContext)
  {
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(paramContext).inflate(R.layout.puggle_row_result, null);
    addView(localLinearLayout);
    this.imageView = ((ImageView)localLinearLayout.findViewById(R.id.puggle_product_image));
    this.titleView = ((TextView)localLinearLayout.findViewById(R.id.puggle_product_title));
    this.priceView = ((TextView)localLinearLayout.findViewById(R.id.puggle_product_price));
    this.brandView = ((TextView)localLinearLayout.findViewById(R.id.puggle_product_brand));
  }

  public void setReviews(ProductInformationProtos.ProductInformation paramProductInformation)
  {
    LinearLayout localLinearLayout = (LinearLayout)findViewById(R.id.product_rating_container);
    this.resultViewUtils.setReviews(localLinearLayout, paramProductInformation, R.id.product_reviews);
    if (localLinearLayout.getVisibility() == 0)
    {
      this.titleView.setMaxLines(2);
      return;
    }
    this.titleView.setMaxLines(3);
  }

  public void setTitle(String paramString)
  {
    this.titleView.setText(paramString);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.PuggleRowResult
 * JD-Core Version:    0.6.2
 */