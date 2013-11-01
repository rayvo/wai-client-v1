package com.google.android.apps.unveil.service;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import com.google.android.apps.unveil.ConstantConfiguration;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilContentProvider.SfCResults;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryListener;
import com.google.android.apps.unveil.protocol.QueryResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class BackgroundQueryListener extends QueryListener
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Context context;
  private final Uri imageUri;
  private final NotificationHelper notifier;
  private final int orientation;
  private final Bitmap queryImage;
  private final PictureRequestService.TokenInvalidator tokenInvalidator;

  public BackgroundQueryListener(Context paramContext, NotificationHelper paramNotificationHelper, Uri paramUri, Bitmap paramBitmap, int paramInt, PictureRequestService.TokenInvalidator paramTokenInvalidator)
  {
    this.context = paramContext;
    this.tokenInvalidator = paramTokenInvalidator;
    this.imageUri = paramUri;
    this.queryImage = paramBitmap;
    this.notifier = paramNotificationHelper;
    this.orientation = paramInt;
  }

  private String saveResult(QueryResponse paramQueryResponse)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramQueryResponse);
      localObjectOutputStream.close();
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("query_response", localByteArrayOutputStream.toByteArray());
      localContentValues.put("query_image", this.imageUri.toString());
      localContentValues.put("orientation", Integer.valueOf(this.orientation));
      return (String)this.context.getContentResolver().insert(UnveilContentProvider.SfCResults.getContentUri(this.context), localContentValues).getPathSegments().get(1);
    }
    catch (IOException localIOException)
    {
      logger.e("Failed to save search by camera query response.", new Object[0]);
    }
    return null;
  }

  public void onAuthenticationError()
  {
    logger.e("Auth error", new Object[0]);
    this.tokenInvalidator.invalidateAuthToken();
  }

  public void onHeavyProcessingEnd()
  {
  }

  public void onHeavyProcessingStart()
  {
  }

  public void onNetworkError(int paramInt)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    localUnveilLogger.e("Network error %d; ignoring.", arrayOfObject);
  }

  public void onQueryResponse(QueryResponse paramQueryResponse)
  {
    if (paramQueryResponse.hasEyeCandyResults())
      logger.w("Got an eye candy response, ignoring.", new Object[0]);
    Bitmap localBitmap;
    String str;
    do
    {
      return;
      float f = Math.min(PixelUtils.dipToPix(ConstantConfiguration.NOTIFICATION_THUMBNAIL_MAX_SIZE_DP.width, this.context) / this.queryImage.getWidth(), PixelUtils.dipToPix(ConstantConfiguration.NOTIFICATION_THUMBNAIL_MAX_SIZE_DP.height, this.context) / this.queryImage.getHeight());
      localBitmap = Bitmap.createScaledBitmap(this.queryImage, (int)(f * this.queryImage.getWidth()), (int)(f * this.queryImage.getHeight()), true);
      str = saveResult(paramQueryResponse);
    }
    while (str == null);
    logger.d("Saved search by camera result with id %s", new Object[] { str });
    this.notifier.handleQueryResponse(this.context, str, paramQueryResponse, localBitmap);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.BackgroundQueryListener
 * JD-Core Version:    0.6.2
 */