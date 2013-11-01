package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View.MeasureSpec;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;

public class PuggleCellResult extends PuggleResultLayout
{
  private static final UnveilLogger logger = new UnveilLogger();
  private LinearLayout layout;

  public PuggleCellResult(Context paramContext)
  {
    super(paramContext);
  }

  protected void init(Context paramContext)
  {
    this.layout = ((LinearLayout)LayoutInflater.from(paramContext).inflate(R.layout.puggle_cell_result, null));
    addView(this.layout);
    this.imageView = ((ImageView)this.layout.findViewById(R.id.puggle_product_image));
    this.brandView = ((TextView)this.layout.findViewById(R.id.puggle_product_brand));
    this.priceView = ((TextView)this.layout.findViewById(R.id.puggle_product_price));
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    int i = View.MeasureSpec.getMode(paramInt1);
    View.MeasureSpec.getMode(paramInt2);
    int j = View.MeasureSpec.getSize(paramInt1);
    View.MeasureSpec.getSize(paramInt2);
    if (i == 0)
      j = 0;
    this.imageView.setLayoutParams(new RelativeLayout.LayoutParams(j, j));
    this.imageView.measure(paramInt1, paramInt1);
    this.priceView.measure(paramInt1, paramInt2);
    int k = j + this.priceView.getMeasuredHeight();
    this.layout.measure(paramInt1, View.MeasureSpec.makeMeasureSpec(k, 1073741824));
    setMeasuredDimension(j, k);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.PuggleCellResult
 * JD-Core Version:    0.6.2
 */