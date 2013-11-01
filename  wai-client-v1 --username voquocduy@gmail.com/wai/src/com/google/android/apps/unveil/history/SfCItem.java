package com.google.android.apps.unveil.history;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.PictureLoadingException;
import com.google.android.apps.unveil.env.UnveilContentProvider.SfCResults;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.results.ResultItem;
import java.io.ByteArrayInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class SfCItem extends ItemModel
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Context context;
  private final String id;
  private final Uri imageUri;
  private final int orientation;
  private Picture queryImage;
  private boolean queryImageLoaded;
  private final QueryResponse queryResponse;

  private SfCItem(Context paramContext, QueryResponse paramQueryResponse, Uri paramUri, int paramInt, String paramString)
  {
    this.context = paramContext;
    this.queryResponse = paramQueryResponse;
    this.imageUri = paramUri;
    this.orientation = paramInt;
    this.id = paramString;
  }

  public static SfCItem from(Context paramContext, String paramString)
  {
    Cursor localCursor = paramContext.getContentResolver().query(Uri.withAppendedPath(UnveilContentProvider.SfCResults.getContentUri(paramContext), paramString), null, null, null, null);
    if (localCursor != null);
    try
    {
      if (localCursor.moveToFirst())
      {
        ObjectInputStream localObjectInputStream = new ObjectInputStream(new ByteArrayInputStream(localCursor.getBlob(localCursor.getColumnIndex("query_response"))));
        QueryResponse localQueryResponse = (QueryResponse)localObjectInputStream.readObject();
        localObjectInputStream.close();
        SfCItem localSfCItem = new SfCItem(paramContext, localQueryResponse, Uri.parse(localCursor.getString(localCursor.getColumnIndex("query_image"))), localCursor.getInt(localCursor.getColumnIndex("orientation")), paramString);
        return localSfCItem;
      }
      localCursor.close();
      logger.d("Failed to load search from camera result with id %s", new Object[] { paramString });
      return null;
    }
    catch (Exception localException)
    {
      while (true)
      {
        logger.e(localException, "Can not load search by camera result", new Object[0]);
        localCursor.close();
      }
    }
    finally
    {
      localCursor.close();
    }
  }

  public String getId()
  {
    return this.id;
  }

  public Picture getQueryImage()
  {
    try
    {
      if (this.queryImage == null)
      {
        boolean bool = this.queryImageLoaded;
        if (bool);
      }
      try
      {
        this.queryImage = PictureFactory.loadAndDownsample(this.context, this.imageUri, this.orientation);
        this.queryImageLoaded = true;
        Picture localPicture = this.queryImage;
        return localPicture;
      }
      catch (PictureLoadingException localPictureLoadingException)
      {
        while (true)
          logger.e("Failed to load query imge for search by camera result", new Object[0]);
      }
    }
    finally
    {
    }
  }

  public QueryResponse getQueryResponse()
  {
    return this.queryResponse;
  }

  public long getTimestamp()
  {
    if (this.queryResponse != null)
      return this.queryResponse.getResponseReceivedTimestamp();
    return 0L;
  }

  public String getTitle()
  {
    if (this.queryResponse != null)
      return ((ResultItem)this.queryResponse.getResults().get(0)).getTitle();
    return "";
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SfCItem
 * JD-Core Version:    0.6.2
 */