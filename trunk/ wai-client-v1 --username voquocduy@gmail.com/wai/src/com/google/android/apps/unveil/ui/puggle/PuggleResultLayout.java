package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class PuggleResultLayout extends FrameLayout
{
  protected TextView brandView;
  protected ImageView imageView;
  protected TextView priceView;

  public PuggleResultLayout(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  protected abstract void init(Context paramContext);

  public void setBrand(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.brandView.setVisibility(8);
      return;
    }
    this.brandView.setText(paramString);
  }

  public void setImageBitmap(Bitmap paramBitmap)
  {
    this.imageView.setImageBitmap(paramBitmap);
  }

  public void setPrice(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
    {
      this.priceView.setText("");
      return;
    }
    this.priceView.setText(paramString);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.PuggleResultLayout
 * JD-Core Version:    0.6.2
 */