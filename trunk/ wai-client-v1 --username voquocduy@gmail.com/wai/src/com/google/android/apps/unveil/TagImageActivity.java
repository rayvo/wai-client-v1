package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.ui.AsymmetricRegionSelectorView;
import com.google.android.apps.unveil.ui.ImageRegionSelectorView;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class TagImageActivity extends StateRestorationActivity
{
  public static final int RESULT_CODE_SEND_TAG = 1;
  public static final int RESULT_WORKFLOW_CANCELED = 1;
  private static UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private ImageRegionSelectorView imageView;
  private AsymmetricRegionSelectorView regionSelector;

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    switch (paramInt1)
    {
    default:
      super.onActivityResult(paramInt1, paramInt2, paramIntent);
    case 1:
    }
    do
      return;
    while ((paramInt2 != -1) && (paramInt2 != 1));
    setResult(paramInt2);
    finish();
  }

  public void onBackPressed()
  {
    ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TAG_IMAGE_BACK);
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.tag_image);
    this.regionSelector = ((AsymmetricRegionSelectorView)findViewById(R.id.region_selector_view));
    this.imageView = ((ImageRegionSelectorView)findViewById(R.id.image_region_selector));
    this.imageView.setAsymmetricRegionSelectorView(this.regionSelector);
    this.application = ((UnveilContext)getApplication());
    this.application.getQueryPipeline().getPicture().populateWithBitmap(this.imageView);
    ((Button)findViewById(R.id.continue_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TAG_IMAGE_CONTINUE);
        UnveilLogger localUnveilLogger1 = TagImageActivity.logger;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = TagImageActivity.this.imageView.getImageSize();
        localUnveilLogger1.d("Image size is %s", arrayOfObject1);
        UnveilLogger localUnveilLogger2 = TagImageActivity.logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = TagImageActivity.this.regionSelector.getRegionInImageCoordinates(TagImageActivity.this.imageView.getImageSize());
        localUnveilLogger2.d("Region is %s", arrayOfObject2);
        Intent localIntent = new Intent(TagImageActivity.this, TagActivity.class);
        localIntent.putExtra("tagging_rect", TagImageActivity.this.regionSelector.getRegionInImageCoordinates(TagImageActivity.this.imageView.getImageSize()));
        localIntent.putExtras(TagImageActivity.this.getIntent());
        TagImageActivity.this.startActivityForResult(localIntent, 1);
      }
    });
    ((Button)findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TAG_IMAGE_CANCEL);
        TagImageActivity.this.finish();
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.TagImageActivity
 * JD-Core Version:    0.6.2
 */