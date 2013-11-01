package com.google.android.apps.unveil.env.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MediaStoreMerger
{
  private static final String ORDER_BY = "corrected_date_taken desc";
  private static final String[] PROJECTION = arrayOfString;
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<MediaRowCursor> cursors = new ArrayList();

  static
  {
    String[] arrayOfString = new String[8];
    arrayOfString[0] = "_id";
    arrayOfString[1] = "_display_name";
    arrayOfString[2] = MediaStoreUtils.CORRECTED_DATE_TAKEN_EXPR;
    arrayOfString[3] = "_data";
    arrayOfString[4] = "description";
    arrayOfString[5] = "orientation";
    arrayOfString[6] = "latitude";
    arrayOfString[7] = "longitude";
  }

  public static MediaStoreMerger fromUris(ContentResolver paramContentResolver, List<Uri> paramList)
    throws InvalidUriException
  {
    MediaStoreMerger localMediaStoreMerger = new MediaStoreMerger();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Uri localUri = (Uri)localIterator.next();
      Cursor localCursor = paramContentResolver.query(localUri, PROJECTION, null, null, "corrected_date_taken desc");
      if (localCursor != null)
      {
        MediaRowCursor localMediaRowCursor = new MediaRowCursor(localUri, localCursor, null);
        localMediaRowCursor.moveToFirst();
        localMediaStoreMerger.cursors.add(localMediaRowCursor);
      }
    }
    return localMediaStoreMerger;
  }

  public void close()
  {
    Iterator localIterator = this.cursors.iterator();
    while (localIterator.hasNext())
      ((MediaRowCursor)localIterator.next()).cursor.close();
  }

  public MediaRow nextMediaRow()
    throws InvalidUriException
  {
    Object localObject = null;
    Iterator localIterator = this.cursors.iterator();
    while (localIterator.hasNext())
    {
      MediaRowCursor localMediaRowCursor = (MediaRowCursor)localIterator.next();
      MediaRow localMediaRow2 = localMediaRowCursor.get();
      if (localMediaRow2 != null)
        if (localObject == null)
          localObject = localMediaRowCursor;
        else if (localObject.get().dateTaken < localMediaRow2.dateTaken)
          localObject = localMediaRowCursor;
    }
    MediaRow localMediaRow1 = null;
    if (localObject != null)
    {
      localMediaRow1 = localObject.get();
      localObject.moveToNext();
    }
    return localMediaRow1;
  }

  public static class MediaRow
  {
    public final long dateTaken;
    public final String description;
    public final String displayName;
    private long fileLength = 0L;
    public final long id;
    public final double latitude;
    public final double longitude;
    public final int orientation;
    public final String path;
    public final Uri storageUri;
    private Uri uri = null;

    private MediaRow(Uri paramUri, long paramLong1, String paramString1, String paramString2, long paramLong2, String paramString3, int paramInt, double paramDouble1, double paramDouble2)
    {
      this.storageUri = paramUri;
      this.id = paramLong1;
      this.displayName = paramString1;
      this.path = paramString2;
      this.dateTaken = paramLong2;
      this.description = paramString3;
      this.orientation = paramInt;
      this.latitude = paramDouble1;
      this.longitude = paramDouble2;
    }

    public static MediaRow fromCursor(Cursor paramCursor, Uri paramUri)
      throws InvalidUriException
    {
      try
      {
        long l = paramCursor.getLong(paramCursor.getColumnIndexOrThrow("_id"));
        String str1 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("_display_name"));
        String str2 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("_data"));
        String str3 = paramCursor.getString(paramCursor.getColumnIndexOrThrow("description"));
        int i = paramCursor.getInt(paramCursor.getColumnIndexOrThrow("orientation"));
        double d1 = paramCursor.getDouble(paramCursor.getColumnIndexOrThrow("latitude"));
        double d2 = paramCursor.getDouble(paramCursor.getColumnIndexOrThrow("longitude"));
        MediaRow localMediaRow = new MediaRow(paramUri, l, str1, str2, paramCursor.getLong(paramCursor.getColumnIndexOrThrow("corrected_date_taken")), str3, i, d1, d2);
        return localMediaRow;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw new InvalidUriException(localIllegalArgumentException);
      }
    }

    public long getFileLength()
    {
      if (this.fileLength == 0L)
        this.fileLength = new File(this.path).length();
      return this.fileLength;
    }

    public Uri getUri()
    {
      if (this.uri == null)
        this.uri = ContentUris.withAppendedId(this.storageUri, this.id);
      return this.uri;
    }
  }

  private static class MediaRowCursor
  {
    private final Cursor cursor;
    private MediaStoreMerger.MediaRow row;
    private final Uri storageUri;

    private MediaRowCursor(Uri paramUri, Cursor paramCursor)
    {
      this.storageUri = paramUri;
      this.cursor = paramCursor;
    }

    private MediaStoreMerger.MediaRow readUntilValid(boolean paramBoolean)
      throws InvalidUriException
    {
      while (paramBoolean)
      {
        MediaStoreMerger.MediaRow localMediaRow = MediaStoreMerger.MediaRow.fromCursor(this.cursor, this.storageUri);
        if ((localMediaRow.path != null) && (localMediaRow.path.length() > 0) && (localMediaRow.getFileLength() > 0L))
          return localMediaRow;
        UnveilLogger localUnveilLogger = MediaStoreMerger.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localMediaRow.getUri();
        localUnveilLogger.w("skipping invalid MediaStore row=%s", arrayOfObject);
        this.cursor.moveToNext();
        paramBoolean = this.cursor.moveToNext();
      }
      return null;
    }

    public MediaStoreMerger.MediaRow get()
    {
      return this.row;
    }

    public boolean moveToFirst()
      throws InvalidUriException
    {
      this.row = readUntilValid(this.cursor.moveToFirst());
      return this.row != null;
    }

    public boolean moveToNext()
      throws InvalidUriException
    {
      this.row = readUntilValid(this.cursor.moveToNext());
      return this.row != null;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.media.MediaStoreMerger
 * JD-Core Version:    0.6.2
 */