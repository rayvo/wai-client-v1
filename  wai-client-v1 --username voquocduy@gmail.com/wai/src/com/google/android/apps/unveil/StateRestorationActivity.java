package com.google.android.apps.unveil;

import android.content.Context;
import android.os.Bundle;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.protocol.QueryPipeline;

public abstract class StateRestorationActivity extends SherlockActivity
{
  private static final String BUNDLE_AUTHSTATE = "StateRestorationActivity.AuthState";
  private static final String BUNDLE_PICTURE_DATA = "StateRestorationActivity.Picture";
  private static final String BUNDLE_PICTURE_ORIENTATION = "StateRestorationActivity.Picture.Orientation";
  private static final String BUNDLE_VIEWPORT = "StateRestorationActivity.Viewport";
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;

  public static void restoreState(Bundle paramBundle, UnveilContext paramUnveilContext, Context paramContext)
  {
    if (paramBundle == null);
    AuthState localAuthState;
    do
    {
      return;
      if (paramBundle.containsKey("StateRestorationActivity.Picture"))
      {
        byte[] arrayOfByte = paramBundle.getByteArray("StateRestorationActivity.Picture");
        if (arrayOfByte != null)
        {
          Picture localPicture = PictureFactory.createJpeg(arrayOfByte, paramBundle.getInt("StateRestorationActivity.Picture.Orientation"));
          paramUnveilContext.getQueryPipeline().setProcessedPicture(localPicture);
        }
      }
      paramUnveilContext.setViewport((Viewport)paramBundle.getParcelable("StateRestorationActivity.Viewport"));
      localAuthState = (AuthState)paramBundle.getParcelable("StateRestorationActivity.AuthState");
    }
    while (!paramUnveilContext.getAuthState().isOlderThan(localAuthState));
    paramUnveilContext.restoreAuthState(localAuthState);
  }

  public static void saveState(Bundle paramBundle, UnveilContext paramUnveilContext)
  {
    QueryPipeline localQueryPipeline = paramUnveilContext.getQueryPipeline();
    if ((localQueryPipeline != null) && (localQueryPipeline.isPictureReady()))
    {
      paramBundle.putByteArray("StateRestorationActivity.Picture", localQueryPipeline.getPicture().getJpegData());
      paramBundle.putInt("StateRestorationActivity.Picture.Orientation", localQueryPipeline.getPicture().getOrientation());
    }
    paramBundle.putParcelable("StateRestorationActivity.Viewport", paramUnveilContext.getViewport());
    paramBundle.putParcelable("StateRestorationActivity.AuthState", paramUnveilContext.getAuthState());
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilContext)getApplication());
    restoreState(paramBundle, this.application, this);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == 16908332)
    {
      onBackPressed();
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    saveState(paramBundle, this.application);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.StateRestorationActivity
 * JD-Core Version:    0.6.2
 */