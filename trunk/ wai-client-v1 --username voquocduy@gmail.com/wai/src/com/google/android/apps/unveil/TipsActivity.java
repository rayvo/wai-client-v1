package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.goggles.NativeClientLoggingProtos;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.List;

public class TipsActivity extends StateRestorationActivity
{
  private boolean isFirstRunFlow()
  {
    UnveilApplication localUnveilApplication = (UnveilApplication)getApplication();
    return (localUnveilApplication.isFirstRun()) || (localUnveilApplication.isUpgrade());
  }

  protected void onCreate(Bundle paramBundle)
  {
    int i = 8;
    super.onCreate(paramBundle);
    setContentView(R.layout.tips);
    UnveilApplication localUnveilApplication = (UnveilApplication)getApplication();
    if (!ConstantConfiguration.PUGGLE_LANGUAGES.contains(localUnveilApplication.getLanguage()))
    {
      findViewById(R.id.puggle_layout).setVisibility(i);
      findViewById(R.id.puggle_divider).setVisibility(i);
    }
    findViewById(R.id.lets_start).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TIPS_LETS_START_CLICK_VALUE);
        TipsActivity.this.setResult(-1);
        TipsActivity.this.finish();
      }
    });
    boolean bool = isFirstRunFlow();
    if (bool);
    while (true)
    {
      findViewById(R.id.puggle_link).setVisibility(i);
      findViewById(R.id.translation_link).setVisibility(i);
      findViewById(R.id.search_from_camera_link).setVisibility(i);
      if (!bool)
      {
        findViewById(R.id.puggle_layout).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TIPS_PUGGLE_CLICK_VALUE);
            TipsActivity.this.startActivity(UnveilApplication.makeCaptureActivityIntent(TipsActivity.this));
          }
        });
        findViewById(R.id.translation_layout).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TIPS_TRANSLATE_CLICK_VALUE);
            TipsActivity.this.startActivity(UnveilApplication.makeCaptureActivityIntent(TipsActivity.this));
          }
        });
        findViewById(R.id.background_layout).setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            ClickTracker.logClick(paramAnonymousView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.TIPS_SEARCH_FROM_CAMERA_CLICK_VALUE);
            TipsActivity.this.startActivity(new Intent(TipsActivity.this, SfCFirstRunActivity.class));
          }
        });
      }
      return;
      i = 0;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.TipsActivity
 * JD-Core Version:    0.6.2
 */