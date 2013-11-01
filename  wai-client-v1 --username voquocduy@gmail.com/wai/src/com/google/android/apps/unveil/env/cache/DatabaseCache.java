package com.google.android.apps.unveil.env.cache;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;

public class DatabaseCache extends AbstractCache<String, Picture>
{
  private static final int CREATED_DATE_INDEX;
  private static final int DATA_INDEX;
  static final String[] PROJECTION = { "data" };
  static final String[] SIMPLE_PROJECTION;
  private static final UnveilLogger logger = new UnveilLogger();
  private final ContentResolver contentProvider;
  private final Uri contentUri;
  private final int inDiskPicturesAtMost;
  private final PictureFactory pictureFactory;
  private final float reclaimRatio;
  private volatile int totalCount = 0;

  static
  {
    SIMPLE_PROJECTION = new String[] { "created" };
  }

  private DatabaseCache(ContentResolver paramContentResolver, Uri paramUri, int paramInt, float paramFloat, PictureFactory paramPictureFactory)
  {
    this.contentProvider = paramContentResolver;
    this.contentUri = paramUri;
    this.inDiskPicturesAtMost = paramInt;
    this.reclaimRatio = paramFloat;
    this.pictureFactory = paramPictureFactory;
    this.totalCount = getCount(paramContentResolver, paramUri, SIMPLE_PROJECTION);
  }

  private static int getCount(ContentResolver paramContentResolver, Uri paramUri, String[] paramArrayOfString)
  {
    try
    {
      Cursor localCursor = paramContentResolver.query(paramUri, paramArrayOfString, null, null, null);
      if (localCursor != null)
      {
        int i = localCursor.getCount();
        localCursor.close();
        return i;
      }
    }
    catch (SQLiteException localSQLiteException)
    {
      throw new DatabaseUnavailableException(null);
    }
    throw new DatabaseUnavailableException(null);
  }

  public static AbstractCache<String, Picture> makeDefault(ContentResolver paramContentResolver, Uri paramUri, int paramInt, float paramFloat, PictureFactory paramPictureFactory)
  {
    if (paramContentResolver == null)
      return new NoOpCache(null);
    try
    {
      DatabaseCache localDatabaseCache = new DatabaseCache(paramContentResolver, paramUri, paramInt, paramFloat, paramPictureFactory);
      return localDatabaseCache;
    }
    catch (DatabaseUnavailableException localDatabaseUnavailableException)
    {
    }
    return new NoOpCache(null);
  }

  private int reclaimDiskStorage()
  {
    int i = (int)(this.inDiskPicturesAtMost * (1.0F - this.reclaimRatio));
    Cursor localCursor = this.contentProvider.query(this.contentUri, SIMPLE_PROJECTION, null, null, "created DESC");
    if (localCursor == null)
      return 0;
    if (localCursor.move(i))
    {
      long l = localCursor.getLong(0);
      localCursor.close();
      return this.contentProvider.delete(this.contentUri, "created<=" + l, null);
    }
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(i);
    localUnveilLogger.w("Failed to move cursor to the %sth oldest query. Cannot delete.", arrayOfObject);
    localCursor.close();
    return 0;
  }

  public Picture get(String paramString)
  {
    Cursor localCursor = this.contentProvider.query(this.contentUri, PROJECTION, "key=?", new String[] { paramString }, null);
    if (localCursor != null)
    {
      if (localCursor.moveToFirst())
      {
        byte[] arrayOfByte = localCursor.getBlob(0);
        if (arrayOfByte != null)
        {
          Picture localPicture = this.pictureFactory.toPicture(arrayOfByte);
          localCursor.close();
          return localPicture;
        }
      }
      localCursor.close();
    }
    logger.d("Can not find thumbnail with key '%s' in local cache.", new Object[] { paramString });
    return null;
  }

  public void put(String paramString, Picture paramPicture)
  {
    try
    {
      if (this.totalCount >= this.inDiskPicturesAtMost)
      {
        int i = reclaimDiskStorage();
        if (i <= 0)
          logger.d("No space to save new thumbnail in disk.", new Object[0]);
        this.totalCount -= i;
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        localUnveilLogger.d("Deleted %d thumbnails from disk.", arrayOfObject);
      }
      ContentValues localContentValues = new ContentValues();
      localContentValues.put("key", paramString);
      localContentValues.put("data", paramPicture.getJpegData());
      this.contentProvider.insert(this.contentUri, localContentValues);
      this.totalCount = (1 + this.totalCount);
      return;
    }
    finally
    {
    }
  }

  private static class DatabaseUnavailableException extends RuntimeException
  {
  }

  private static class NoOpCache extends AbstractCache<String, Picture>
  {
    public Picture get(String paramString)
    {
      return null;
    }

    public void put(String paramString, Picture paramPicture)
    {
    }
  }

  public static abstract interface PictureFactory
  {
    public abstract Picture toPicture(byte[] paramArrayOfByte);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.cache.DatabaseCache
 * JD-Core Version:    0.6.2
 */