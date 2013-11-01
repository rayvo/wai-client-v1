package com.google.android.apps.unveil.env.media;

import android.content.ContentResolver;
import android.net.Uri;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MediaStoreMergerAdapter
  implements ImageLoader.ImageSource
{
  private final MediaStoreMergerFactory mediaStoreMergerFactory;

  public MediaStoreMergerAdapter(MediaStoreMergerFactory paramMediaStoreMergerFactory)
  {
    this.mediaStoreMergerFactory = paramMediaStoreMergerFactory;
  }

  public static ImageLoader.Image getImage(ContentResolver paramContentResolver, Uri paramUri)
    throws InvalidUriException
  {
    MediaStoreMerger localMediaStoreMerger = MediaStoreMerger.fromUris(paramContentResolver, Collections.singletonList(paramUri));
    try
    {
      MediaStoreMerger.MediaRow localMediaRow = localMediaStoreMerger.nextMediaRow();
      if (localMediaRow == null)
        return null;
      ImageLoader.Image localImage = new ImageLoader.Image(localMediaRow.storageUri, localMediaRow.description, localMediaRow.dateTaken, localMediaRow.orientation, localMediaRow.latitude, localMediaRow.longitude, localMediaRow.path);
      return localImage;
    }
    finally
    {
      localMediaStoreMerger.close();
    }
  }

  public List<ImageLoader.Image> getImages()
  {
    MediaStoreMerger localMediaStoreMerger = this.mediaStoreMergerFactory.makeMediaStoreMerger();
    LinkedList localLinkedList;
    try
    {
      localLinkedList = new LinkedList();
      try
      {
        while (true)
        {
          MediaStoreMerger.MediaRow localMediaRow = localMediaStoreMerger.nextMediaRow();
          if (localMediaRow == null)
            break;
          localLinkedList.add(new ImageLoader.Image(localMediaRow.getUri(), localMediaRow.description, localMediaRow.dateTaken, localMediaRow.orientation, localMediaRow.latitude, localMediaRow.longitude, localMediaRow.path));
        }
      }
      catch (InvalidUriException localInvalidUriException)
      {
        throw new AssertionError(localInvalidUriException);
      }
    }
    finally
    {
      localMediaStoreMerger.close();
    }
    localMediaStoreMerger.close();
    return localLinkedList;
  }

  public static abstract interface MediaStoreMergerFactory
  {
    public abstract MediaStoreMerger makeMediaStoreMerger();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.media.MediaStoreMergerAdapter
 * JD-Core Version:    0.6.2
 */