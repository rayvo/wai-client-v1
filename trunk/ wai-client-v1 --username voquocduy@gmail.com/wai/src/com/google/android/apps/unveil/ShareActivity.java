package com.google.android.apps.unveil;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.env.media.InvalidUriException;
import com.google.android.apps.unveil.env.media.MediaStoreMergerAdapter;
import com.google.android.apps.unveil.env.media.MediaStoreUtils;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryPipeline;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;

public class ShareActivity extends AuthenticatedActivity
{
  private static final int DIALOG_AUTH_PROGRESS = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  private Picture rawImage;

  private Picture readDirectly(Uri paramUri)
  {
    return ImageUtils.getPicture(getContentResolver(), paramUri, 0);
  }

  private void readImage()
  {
    Uri localUri = (Uri)getIntent().getExtras().get("android.intent.extra.STREAM");
    this.rawImage = null;
    if (MediaStoreUtils.isMediaStoreUri(localUri));
    try
    {
      ImageLoader.Image localImage = MediaStoreMergerAdapter.getImage(getContentResolver(), localUri);
      this.rawImage = ImageUtils.getPicture(getContentResolver(), localImage);
      if (this.rawImage == null)
      {
        this.rawImage = readDirectly(localUri);
        if (this.rawImage == null)
        {
          Toast.makeText(this, R.string.share_error, 1).show();
          logger.e("Unable to read image URI from 'SEND' intent.", new Object[0]);
          finish();
          return;
        }
      }
    }
    catch (InvalidUriException localInvalidUriException)
    {
      while (true)
        logger.w("Image URI is not from the MediaStore: %s", new Object[] { localUri });
      QueryPipeline localQueryPipeline = this.application.getQueryPipeline();
      localQueryPipeline.startNewQuery();
      localQueryPipeline.setProcessedPicture(this.rawImage);
      QueryBuilder localQueryBuilder = new QueryBuilder().addImageData(this.rawImage.getJpegData(), this.rawImage.getSize());
      localQueryBuilder.setSource(GogglesProtos.GogglesRequest.Source.SHARED);
      localQueryBuilder.setQueryType(QueryResponseFactory.QueryType.SHARE);
      localQueryPipeline.setPendingQuery(localQueryBuilder);
      startQueryActivity();
    }
  }

  private void startQueryActivity()
  {
    Intent localIntent = new Intent(this, RunQueryActivity.class);
    localIntent.setFlags(65536);
    localIntent.putExtra("is_shared_query", true);
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.RAW_IMAGE);
    startActivity(localIntent);
    finish();
  }

  public void onAuthCanceled()
  {
    logger.i("onAuthCanceled", new Object[0]);
    readImage();
  }

  public void onAuthFailure()
  {
    logger.w("onAuthFailure", new Object[0]);
    readImage();
  }

  public void onAuthSuccess()
  {
    dismissDialog(1);
    readImage();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    if (paramInt != 1)
      return null;
    ProgressDialog localProgressDialog = new ProgressDialog(this);
    localProgressDialog.setMessage(getString(R.string.auth));
    return localProgressDialog;
  }

  protected void onStart()
  {
    super.onStart();
    this.application.getViewport().updateNaturalOrientation(Viewport.computeNaturalOrientation(this));
    if (!"android.intent.action.SEND".equals(getIntent().getAction()))
    {
      logger.e("Received unknown intent.", new Object[0]);
      finish();
      return;
    }
    if ((this.application.userWantsHistory()) && (!this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID)))
    {
      fetchAuthToken();
      showDialog(1);
      return;
    }
    readImage();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ShareActivity
 * JD-Core Version:    0.6.2
 */