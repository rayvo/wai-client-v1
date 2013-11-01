package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.results.ResultModel;

public class Thumbnail extends FrameLayout
{
  public static final int HEIGHT_DP = 76;
  public static final int WIDTH_DP = 76;
  private final ResultModel result;
  ThumbnailProvider.ThumbnailListener thumbnailListener = new ThumbnailProvider.ThumbnailListener()
  {
    public void onThumbnailReady(Picture paramAnonymousPicture)
    {
      Thumbnail.this.result.getThumbnailUrl();
      ((UnveilApplication)Thumbnail.this.getContext().getApplicationContext());
      Thumbnail.this.setPicture(paramAnonymousPicture);
      Animation localAnimation = AnimationUtils.loadAnimation(Thumbnail.this.getContext(), 17432576);
      Thumbnail.this.setVisibility(0);
      Thumbnail.this.startAnimation(localAnimation);
    }
  };
  private final View view;

  public Thumbnail(Context paramContext, ResultModel paramResultModel)
  {
    super(paramContext);
    this.result = paramResultModel;
    setLayoutParams(new Gallery.LayoutParams(PixelUtils.dipToPix(76.0F, paramContext), PixelUtils.dipToPix(76.0F, paramContext)));
    this.view = LayoutInflater.from(paramContext).inflate(R.layout.thumbnail, null);
    addView(this.view);
  }

  public ThumbnailProvider.ThumbnailListener getListener()
  {
    return this.thumbnailListener;
  }

  public ResultModel getResult()
  {
    return this.result;
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.view.setOnClickListener(paramOnClickListener);
    this.view.setFocusable(true);
  }

  public void setPicture(Picture paramPicture)
  {
    paramPicture.populateWithBitmap((ImageView)this.view.findViewById(R.id.image));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.Thumbnail
 * JD-Core Version:    0.6.2
 */