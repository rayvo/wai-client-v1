package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewParent;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.PictureFactory.ImageDecodingException;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.Handler;
import com.google.android.apps.unveil.results.ResultModel;
import java.util.List;

public class HorizontalImageScrollingView extends HorizontalScrollView
{
  private static final int SWIPE_THRESHOLD_DISTANCE_DP = 5;
  private static final int SWIPE_THRESHOLD_VELOCITY_DP = 300;
  private static final UnveilLogger logger = new UnveilLogger();
  private int active = 0;
  private View.OnClickListener clickListener;
  private GestureDetector gestureDetector;
  private OnImageChangeListener imageChangeListener;
  private LinearLayout internalWrapper;
  private List<ResultModel> results;
  private int swipeThresholdDistancePixels;
  private int swipeThresholdVelocityPixels;

  public HorizontalImageScrollingView(Context paramContext)
  {
    super(paramContext);
    init();
  }

  public HorizontalImageScrollingView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init();
  }

  public HorizontalImageScrollingView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init();
  }

  private void init()
  {
    Context localContext = getContext();
    this.swipeThresholdDistancePixels = PixelUtils.dipToPix(5.0F, localContext);
    this.swipeThresholdVelocityPixels = PixelUtils.dipToPix(300.0F, localContext);
  }

  private void innerScrollTo(int paramInt, boolean paramBoolean)
  {
    if (paramInt < 0)
    {
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(paramInt);
      localUnveilLogger3.w("Scroll to invalid position %d, fix to 0.", arrayOfObject3);
      paramInt = 0;
    }
    if (paramInt >= this.results.size())
    {
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(paramInt);
      arrayOfObject2[1] = Integer.valueOf(-1 + this.results.size());
      localUnveilLogger2.w("Scroll to invalid position %d, fix to %d", arrayOfObject2);
      paramInt = -1 + this.results.size();
    }
    this.active = paramInt;
    ((PageView)this.internalWrapper.getChildAt(this.active)).loadImage();
    loadNextImage();
    loadPreviousImage();
    int i = getWidth() * this.active;
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(i);
    localUnveilLogger1.d("Scroll to %d", arrayOfObject1);
    if (paramBoolean)
      smoothScrollTo(i, 0);
    while (true)
    {
      if (this.imageChangeListener != null)
        this.imageChangeListener.onImageChanged((ResultModel)this.results.get(this.active));
      return;
      scrollTo(i, 0);
    }
  }

  private void loadNextImage()
  {
    if (this.active < -1 + this.results.size())
      ((PageView)this.internalWrapper.getChildAt(1 + this.active)).loadImage();
  }

  private void loadPreviousImage()
  {
    if (this.active > 0)
      ((PageView)this.internalWrapper.getChildAt(-1 + this.active)).loadImage();
  }

  public void jumpTo(int paramInt)
  {
    innerScrollTo(paramInt, false);
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (paramBoolean)
      scrollTo(this.active);
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (getChildCount() > 0)
      getChildAt(0).measure(View.MeasureSpec.makeMeasureSpec(0, 0), View.MeasureSpec.makeMeasureSpec(getMeasuredHeight(), 1073741824));
  }

  public void recycle()
  {
    int i = this.internalWrapper.getChildCount();
    for (int j = 0; j < i; j++)
      ((PageView)this.internalWrapper.getChildAt(j)).recycle();
  }

  public void scrollTo(int paramInt)
  {
    innerScrollTo(paramInt, true);
  }

  public void setImages(List<ResultModel> paramList)
  {
    this.internalWrapper = new LinearLayout(getContext());
    this.internalWrapper.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
    this.internalWrapper.setOrientation(0);
    addView(this.internalWrapper);
    this.results = paramList;
    for (int i = 0; i < this.results.size(); i++)
    {
      PageView localPageView = new PageView(getContext(), (ResultModel)this.results.get(i));
      localPageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      this.internalWrapper.addView(localPageView);
    }
    setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        int i = HorizontalImageScrollingView.this.getScrollX();
        int j = paramAnonymousView.getWidth();
        if (HorizontalImageScrollingView.this.gestureDetector.onTouchEvent(paramAnonymousMotionEvent))
          return true;
        if ((paramAnonymousMotionEvent.getAction() == 1) || (paramAnonymousMotionEvent.getAction() == 3))
        {
          HorizontalImageScrollingView.access$102(HorizontalImageScrollingView.this, (i + j / 2) / j);
          HorizontalImageScrollingView.this.scrollTo(HorizontalImageScrollingView.this.active);
          return true;
        }
        int k = j * HorizontalImageScrollingView.this.active;
        if (i < k)
          HorizontalImageScrollingView.this.loadPreviousImage();
        while (true)
        {
          return false;
          if (i > k)
            HorizontalImageScrollingView.this.loadNextImage();
        }
      }
    });
    this.gestureDetector = new GestureDetector(getContext(), new ImageScrollingGestureListener(null));
  }

  public void setOnClickListener(View.OnClickListener paramOnClickListener)
  {
    this.clickListener = paramOnClickListener;
  }

  public void setOnImageChangeListener(OnImageChangeListener paramOnImageChangeListener)
  {
    this.imageChangeListener = paramOnImageChangeListener;
  }

  private class ImageScrollingGestureListener extends GestureDetector.SimpleOnGestureListener
  {
    private ImageScrollingGestureListener()
    {
    }

    public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
    {
      int k;
      boolean bool2;
      if ((paramMotionEvent1.getX() - paramMotionEvent2.getX() > HorizontalImageScrollingView.this.swipeThresholdDistancePixels) && (Math.abs(paramFloat1) > HorizontalImageScrollingView.this.swipeThresholdVelocityPixels))
      {
        HorizontalImageScrollingView.this.getWidth();
        HorizontalImageScrollingView localHorizontalImageScrollingView2 = HorizontalImageScrollingView.this;
        if (HorizontalImageScrollingView.this.active < -1 + HorizontalImageScrollingView.this.results.size())
        {
          k = 1 + HorizontalImageScrollingView.this.active;
          HorizontalImageScrollingView.access$102(localHorizontalImageScrollingView2, k);
          HorizontalImageScrollingView.this.scrollTo(HorizontalImageScrollingView.this.active);
          bool2 = true;
        }
      }
      boolean bool3;
      do
      {
        boolean bool1;
        do
        {
          return bool2;
          k = -1 + HorizontalImageScrollingView.this.results.size();
          break;
          bool1 = paramMotionEvent2.getX() - paramMotionEvent1.getX() < HorizontalImageScrollingView.this.swipeThresholdDistancePixels;
          bool2 = false;
        }
        while (!bool1);
        bool3 = Math.abs(paramFloat1) < HorizontalImageScrollingView.this.swipeThresholdVelocityPixels;
        bool2 = false;
      }
      while (!bool3);
      HorizontalImageScrollingView.this.getWidth();
      HorizontalImageScrollingView localHorizontalImageScrollingView1 = HorizontalImageScrollingView.this;
      int i = HorizontalImageScrollingView.this.active;
      int j = 0;
      if (i > 0)
        j = -1 + HorizontalImageScrollingView.this.active;
      HorizontalImageScrollingView.access$102(localHorizontalImageScrollingView1, j);
      HorizontalImageScrollingView.this.scrollTo(HorizontalImageScrollingView.this.active);
      return true;
    }

    public boolean onSingleTapUp(MotionEvent paramMotionEvent)
    {
      HorizontalImageScrollingView.this.scrollTo(HorizontalImageScrollingView.this.active);
      if (HorizontalImageScrollingView.this.clickListener != null)
        HorizontalImageScrollingView.this.clickListener.onClick(HorizontalImageScrollingView.this);
      return true;
    }
  }

  public static abstract interface OnImageChangeListener
  {
    public abstract void onImageChanged(ResultModel paramResultModel);
  }

  private static class PageView extends FrameLayout
  {
    private final UnveilContext application = (UnveilContext)getContext().getApplicationContext();
    private final ImageView image;
    private Picture picture;
    private boolean pictureFetched;
    private final ProgressBar progressBar;
    private final DefaultHttpRequestFactory requestFactory = DefaultHttpRequestFactory.newAnonymousRequestFactory(this.application);
    private final ResultModel result;
    private final ImageView thumbnail;
    private Picture thumbnailPicture;

    public PageView(Context paramContext, ResultModel paramResultModel)
    {
      super();
      this.result = paramResultModel;
      this.image = new ImageView(paramContext);
      this.image.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      this.image.setScaleType(ImageView.ScaleType.FIT_CENTER);
      this.thumbnail = new ImageView(paramContext);
      this.thumbnail.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
      this.thumbnail.setScaleType(ImageView.ScaleType.FIT_CENTER);
      this.progressBar = new ProgressBar(paramContext);
      this.progressBar.setLayoutParams(new FrameLayout.LayoutParams(-2, -2, 17));
      addView(this.image);
      addView(this.thumbnail);
      addView(this.progressBar);
      fetchThumbnail();
      this.image.setVisibility(4);
      this.thumbnail.setVisibility(0);
      this.progressBar.setVisibility(0);
    }

    private void fetchThumbnail()
    {
      final String str = this.result.getThumbnailUrl();
      FetchResponse.Handler local1 = new FetchResponse.Handler()
      {
        public void onResponseReceived(FetchResponse paramAnonymousFetchResponse)
        {
          if (!FetchResponse.isValid(paramAnonymousFetchResponse))
            return;
          byte[] arrayOfByte = paramAnonymousFetchResponse.getData();
          Bitmap localBitmap;
          try
          {
            localBitmap = BitmapFactory.decodeByteArray(arrayOfByte, 0, arrayOfByte.length);
            if (localBitmap == null)
            {
              UnveilLogger localUnveilLogger = HorizontalImageScrollingView.logger;
              Object[] arrayOfObject = new Object[1];
              arrayOfObject[0] = str;
              localUnveilLogger.e("Failed to parse bitmap from %s", arrayOfObject);
              return;
            }
          }
          catch (OutOfMemoryError localOutOfMemoryError)
          {
            HorizontalImageScrollingView.logger.e("Out of memory error when trying to parse thumbnail.", new Object[0]);
            return;
          }
          HorizontalImageScrollingView.PageView.access$1002(HorizontalImageScrollingView.PageView.this, PictureFactory.createBitmap(localBitmap, 0));
          HorizontalImageScrollingView.PageView.this.thumbnail.setImageBitmap(localBitmap);
        }
      };
      this.result.getImageUrl();
      this.application.getConnector().sendRequest(this.requestFactory.newGetRequest(str), local1);
    }

    public void loadImage()
    {
      try
      {
        if (this.pictureFetched)
          return;
        this.pictureFetched = true;
        if (this.picture != null)
        {
          this.picture.populateWithBitmap(this.image);
          this.image.setVisibility(0);
          this.progressBar.setVisibility(4);
          return;
        }
      }
      finally
      {
      }
      FetchResponse.Handler local2 = new FetchResponse.Handler()
      {
        public void onResponseReceived(FetchResponse paramAnonymousFetchResponse)
        {
          HorizontalImageScrollingView.PageView.this.progressBar.setVisibility(4);
          if (!FetchResponse.isValid(paramAnonymousFetchResponse))
          {
            HorizontalImageScrollingView.logger.e("Failed to fetch full-size image", new Object[0]);
            return;
          }
          try
          {
            HorizontalImageScrollingView.PageView.access$1302(HorizontalImageScrollingView.PageView.this, PictureFactory.createBitmapWithDownsampling(paramAnonymousFetchResponse.getData(), 1048576));
            HorizontalImageScrollingView.PageView.this.picture.populateWithBitmap(HorizontalImageScrollingView.PageView.this.image);
            HorizontalImageScrollingView.PageView.this.thumbnail.setVisibility(4);
            HorizontalImageScrollingView.PageView.this.progressBar.setVisibility(4);
            HorizontalImageScrollingView.PageView.this.image.setVisibility(0);
            return;
          }
          catch (PictureFactory.ImageDecodingException localImageDecodingException)
          {
            HorizontalImageScrollingView.logger.e(localImageDecodingException, "Failed to decode image", new Object[0]);
            return;
          }
          catch (OutOfMemoryError localOutOfMemoryError)
          {
            HorizontalImageScrollingView.logger.e(localOutOfMemoryError, "Failed to decode image, out of memory.", new Object[0]);
          }
        }
      };
      String str = this.result.getImageUrl();
      this.application.getConnector().sendRequest(this.requestFactory.newGetRequest(str), local2);
    }

    public void onMeasure(int paramInt1, int paramInt2)
    {
      for (ViewParent localViewParent = getParent(); (!(localViewParent instanceof HorizontalImageScrollingView)) && (localViewParent != null); localViewParent = localViewParent.getParent());
      if (localViewParent == null)
      {
        super.onMeasure(paramInt1, paramInt2);
        return;
      }
      HorizontalImageScrollingView localHorizontalImageScrollingView = (HorizontalImageScrollingView)localViewParent;
      int i = localHorizontalImageScrollingView.getMeasuredWidth();
      int j = localHorizontalImageScrollingView.getMeasuredHeight();
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(j, 1073741824));
    }

    public void recycle()
    {
      this.image.setImageBitmap(null);
      if (this.picture != null)
      {
        this.picture.recycle();
        this.picture = null;
      }
      this.thumbnail.setImageBitmap(null);
      if (this.thumbnailPicture != null)
      {
        this.thumbnailPicture.recycle();
        this.thumbnailPicture = null;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.HorizontalImageScrollingView
 * JD-Core Version:    0.6.2
 */