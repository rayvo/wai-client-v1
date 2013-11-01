package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Gallery;
import android.widget.TextView;
import com.google.android.apps.unveil.env.Timeout;
import com.google.android.apps.unveil.env.Timeout.TimeoutCallback;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.HorizontalImageScrollingView;
import com.google.android.apps.unveil.ui.HorizontalImageScrollingView.OnImageChangeListener;
import com.google.android.apps.unveil.ui.Thumbnail;
import com.google.android.apps.unveil.ui.ThumbnailAdapter;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.ArrayList;
import java.util.List;

public class AttributionActivity extends StateRestorationActivity
{
  private static final int CONTROL_TIMEOUT_MS = 4000;
  public static final String EXTRA_LAST_RESPONSE = "last_response";
  public static final String EXTRA_RESULT_TO_SHOW = "result";
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext applicationContext;
  private View attributionControls;
  private HorizontalImageScrollingView attributionImages;
  private Timeout controlsTimeout;
  private String imageUrl;
  private String pageUrl;
  private List<ResultModel> results;
  private ResultModel userClicked;

  private boolean controlsHaveVisibilty(int paramInt)
  {
    return (this.attributionControls != null) && (this.attributionControls.getVisibility() == paramInt) && (this.attributionControls.getAnimation() == null);
  }

  private void goToUserSelection()
  {
    for (int i = 0; ; i++)
      if ((i >= this.results.size()) || (this.userClicked.equals(this.results.get(i))))
      {
        if (i == this.results.size())
          i = 0;
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        localUnveilLogger.d("Scrolling to user selection: %d", arrayOfObject);
        if (i == 0)
          this.applicationContext.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SHOW_FULL_SIMILAR_IMAGE, i);
        this.attributionImages.scrollTo(i);
        return;
      }
  }

  private void toggleControlVisibility()
  {
    if (this.attributionControls.getVisibility() == 0)
    {
      Animation localAnimation2 = AnimationUtils.loadAnimation(this, R.anim.attribution_fade_out);
      localAnimation2.setAnimationListener(new Animation.AnimationListener()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          AttributionActivity.this.attributionControls.setVisibility(8);
        }

        public void onAnimationRepeat(Animation paramAnonymousAnimation)
        {
        }

        public void onAnimationStart(Animation paramAnonymousAnimation)
        {
        }
      });
      this.attributionControls.startAnimation(localAnimation2);
      return;
    }
    Animation localAnimation1 = AnimationUtils.loadAnimation(this, R.anim.attribution_fade_in);
    this.attributionControls.setVisibility(0);
    this.attributionControls.startAnimation(localAnimation1);
    this.controlsTimeout.reset();
  }

  private void updateControls()
  {
    TextView localTextView = (TextView)this.attributionControls.findViewById(R.id.page_url);
    if (TextUtils.isEmpty(this.pageUrl))
    {
      findViewById(R.id.divider).setVisibility(4);
      localTextView.setVisibility(4);
      return;
    }
    localTextView.setText(getString(R.string.from_colon) + " " + this.pageUrl);
    findViewById(R.id.divider).setVisibility(0);
    localTextView.setVisibility(0);
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(getLayoutInflater().inflate(R.layout.attribution, null));
    this.attributionControls = findViewById(R.id.attribution_controls);
    this.applicationContext = ((UnveilContext)getApplication());
    Intent localIntent = getIntent();
    this.userClicked = ((ResultModel)localIntent.getSerializableExtra("result"));
    QueryResponse localQueryResponse = (QueryResponse)localIntent.getSerializableExtra("last_response");
    if (localQueryResponse == null)
    {
      this.results = new ArrayList();
      this.results.add(this.userClicked);
    }
    while (true)
    {
      this.attributionImages = ((HorizontalImageScrollingView)findViewById(R.id.attribution_image));
      this.attributionImages.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (AttributionActivity.this.controlsHaveVisibilty(0))
            AttributionActivity.this.toggleControlVisibility();
        }
      });
      final Gallery localGallery = (Gallery)findViewById(R.id.gallery);
      this.attributionImages.setOnImageChangeListener(new HorizontalImageScrollingView.OnImageChangeListener()
      {
        public void onImageChanged(ResultModel paramAnonymousResultModel)
        {
          AttributionActivity.access$202(AttributionActivity.this, paramAnonymousResultModel.getImageUrl());
          AttributionActivity.access$302(AttributionActivity.this, paramAnonymousResultModel.getReferrerUrl());
          AttributionActivity.this.updateControls();
          int i = AttributionActivity.this.results.indexOf(paramAnonymousResultModel);
          if (localGallery.getSelectedItemPosition() != i)
          {
            AttributionActivity.this.applicationContext.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SHOW_FULL_SIMILAR_IMAGE, i);
            localGallery.setSelection(i, true);
          }
        }
      });
      this.attributionImages.setImages(this.results);
      ((TextView)findViewById(R.id.back_to_results)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          AttributionActivity.this.finish();
        }
      });
      ((TextView)findViewById(R.id.image_url)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          Intent localIntent = new Intent("android.intent.action.VIEW");
          localIntent.setData(Uri.parse(AttributionActivity.this.imageUrl));
          AttributionActivity.this.startActivity(localIntent);
        }
      });
      final ThumbnailAdapter localThumbnailAdapter = new ThumbnailAdapter(this, this.applicationContext, null);
      localThumbnailAdapter.loadImages(this.results);
      localGallery.setAdapter(localThumbnailAdapter);
      localGallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          ResultModel localResultModel = ((Thumbnail)localThumbnailAdapter.getItem(paramAnonymousInt)).getResult();
          AttributionActivity.access$202(AttributionActivity.this, localResultModel.getImageUrl());
          AttributionActivity.access$302(AttributionActivity.this, localResultModel.getReferrerUrl());
          AttributionActivity.this.attributionImages.scrollTo(paramAnonymousInt);
        }
      });
      this.controlsTimeout = new Timeout(new Timeout.TimeoutCallback()
      {
        public void onTimeout()
        {
          if (AttributionActivity.this.controlsHaveVisibilty(0))
            AttributionActivity.this.toggleControlVisibility();
        }
      }
      , 4000);
      goToUserSelection();
      if (this.results.size() == 1)
        localGallery.setVisibility(8);
      return;
      this.results = new ArrayList(localQueryResponse.getEyeCandyResults());
    }
  }

  protected void onDestroy()
  {
    this.attributionImages.recycle();
    super.onDestroy();
  }

  protected void onStart()
  {
    super.onStart();
    this.attributionControls.setVisibility(0);
    this.controlsTimeout.reset();
  }

  public void onUserInteraction()
  {
    if (controlsHaveVisibilty(8))
      toggleControlVisibility();
    this.controlsTimeout.reset();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.AttributionActivity
 * JD-Core Version:    0.6.2
 */