package com.google.android.apps.unveil.history;

import android.content.ContentProviderClient;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Handler;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.env.UnveilContentProvider.Queries;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;

public class DatabaseBackedSavedQueryProvider extends SavedQueryProvider
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final ContentProviderClient contentProvider;
  private final Context context;
  private final Handler handler = new Handler();

  public DatabaseBackedSavedQueryProvider(ContentProviderClient paramContentProviderClient, Context paramContext)
  {
    if (paramContentProviderClient == null)
      throw new IllegalArgumentException();
    this.context = paramContext;
    this.contentProvider = paramContentProviderClient;
  }

  private void asyncNotifyListenersOfChange()
  {
    this.handler.post(new Runnable()
    {
      public void run()
      {
        DatabaseBackedSavedQueryProvider.this.notifyListenersOfChange();
      }
    });
  }

  private Uri getContentUri()
  {
    return UnveilContentProvider.Queries.getContentUri(this.context);
  }

  private static SavedQuery getQueryFromCursor(Cursor paramCursor)
  {
    long l = paramCursor.getLong(paramCursor.getColumnIndexOrThrow("_id"));
    byte[] arrayOfByte = paramCursor.getBlob(paramCursor.getColumnIndex("query_params"));
    Object localObject;
    if (arrayOfByte == null)
    {
      String str = paramCursor.getString(paramCursor.getColumnIndexOrThrow("source"));
      if ((!TextUtils.isEmpty(str)) && (str.equals(GogglesProtos.GogglesRequest.Source.PUGGLE.name())))
        localObject = PuggleQueryBuilder.from(paramCursor);
    }
    while (localObject != null)
    {
      return new SavedQuery((QueryBuilder)localObject, l, paramCursor.getLong(paramCursor.getColumnIndexOrThrow("created")));
      localObject = QueryBuilder.from(paramCursor);
      continue;
      localObject = QueryBuilder.parseFrom(arrayOfByte);
    }
    logger.e("Could not parse a QueryBuilder, skipping this row.", new Object[0]);
    return null;
  }

  private boolean performDelete(long paramLong)
  {
    try
    {
      int i = this.contentProvider.delete(Uri.withAppendedPath(getContentUri(), Uri.encode(String.valueOf(paramLong))), null, null);
      switch (i)
      {
      default:
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Long.valueOf(paramLong);
        arrayOfObject[1] = Integer.valueOf(i);
        localUnveilLogger.e("Instead of deleting %d, %d queries were deleted.", arrayOfObject);
        break;
      case 0:
        logger.e("No queries were deleted.", new Object[0]);
        return false;
      case 1:
      }
    }
    catch (RemoteException localRemoteException)
    {
      logger.e(localRemoteException, "Could not delete executed pending query.", new Object[0]);
      return false;
    }
    return true;
  }

  public void delete(long paramLong)
  {
    if (performDelete(paramLong))
      notifyListenersOfChange();
  }

  public SavedQuery get(long paramLong)
  {
    Cursor localCursor = null;
    try
    {
      localCursor = this.contentProvider.query(ContentUris.withAppendedId(getContentUri(), paramLong), UnveilContentProvider.Queries.FULL_PROJECTION, null, null, null);
      boolean bool = localCursor.moveToFirst();
      if (!bool)
      {
        if (localCursor != null)
          localCursor.close();
        localObject2 = null;
        return localObject2;
      }
      SavedQuery localSavedQuery = getQueryFromCursor(localCursor);
      Object localObject2 = localSavedQuery;
      return localObject2;
    }
    catch (RemoteException localRemoteException)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Long.valueOf(paramLong);
      localUnveilLogger.e(localRemoteException, "Failed to get query %d", arrayOfObject);
      return null;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  public SavedQueryProvider.SavedQueryCursor getQueries()
  {
    Cursor localCursor;
    try
    {
      localCursor = this.contentProvider.query(getContentUri(), UnveilContentProvider.Queries.FULL_PROJECTION, null, null, null);
      if (localCursor == null)
        throw new RemoteException();
    }
    catch (RemoteException localRemoteException)
    {
      return newEmptyCursor();
    }
    if (!localCursor.moveToFirst())
    {
      localCursor.close();
      return newEmptyCursor();
    }
    DatabaseSavedQueryCursor localDatabaseSavedQueryCursor = new DatabaseSavedQueryCursor(localCursor);
    return localDatabaseSavedQueryCursor;
  }

  public int getQueryCount()
  {
    Cursor localCursor = null;
    String[] arrayOfString = { "_id" };
    try
    {
      localCursor = this.contentProvider.query(getContentUri(), arrayOfString, null, null, null);
      if (localCursor == null)
        throw new RemoteException();
    }
    catch (RemoteException localRemoteException)
    {
      logger.e(localRemoteException, "Could not compute query count.", new Object[0]);
      if (localCursor != null)
        localCursor.close();
      int i = 0;
      return i;
      int j = localCursor.getCount();
      i = j;
      return i;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  public void onQueryExecuted(long paramLong)
  {
    if (performDelete(paramLong))
    {
      notifyListenersOfChange();
      ((BaseApplication)this.context.getApplicationContext()).getSearchHistoryProvider().notifyListenersOfChange();
    }
  }

  public Long save(QueryBuilder paramQueryBuilder)
  {
    if (!paramQueryBuilder.canGeneratePicture())
      throw new IllegalArgumentException(paramQueryBuilder + " cannot be saved for later because no image data has been provided.");
    paramQueryBuilder.addLocation(null);
    ContentValues localContentValues = paramQueryBuilder.toContentValues();
    Object localObject1 = null;
    try
    {
      Uri localUri = this.contentProvider.insert(getContentUri(), localContentValues);
      asyncNotifyListenersOfChange();
      Cursor localCursor = this.contentProvider.query(localUri, UnveilContentProvider.Queries.FULL_PROJECTION, null, null, null);
      localObject1 = localCursor;
      if (localObject1 == null)
      {
        if (localObject1 != null)
          localObject1.close();
        localLong = null;
        return localLong;
      }
      localObject1.moveToFirst();
      Long localLong = Long.valueOf(localObject1.getLong(localObject1.getColumnIndexOrThrow("_id")));
      localObject1.close();
      return localLong;
    }
    catch (RemoteException localRemoteException)
    {
      logger.e(localRemoteException, "Could not save %s.", new Object[] { paramQueryBuilder });
      return null;
    }
    finally
    {
      if (localObject1 != null)
        localObject1.close();
    }
  }

  private static class DatabaseSavedQueryCursor
    implements SavedQueryProvider.SavedQueryCursor
  {
    private final Cursor databaseCursor;

    public DatabaseSavedQueryCursor(Cursor paramCursor)
    {
      this.databaseCursor = paramCursor;
    }

    public void close()
    {
      this.databaseCursor.close();
    }

    public int getCount()
    {
      return this.databaseCursor.getCount();
    }

    public boolean hasNext()
    {
      return !this.databaseCursor.isAfterLast();
    }

    public void moveTo(int paramInt)
    {
      this.databaseCursor.moveToPosition(paramInt);
    }

    public SavedQuery next()
    {
      SavedQuery localSavedQuery = DatabaseBackedSavedQueryProvider.getQueryFromCursor(this.databaseCursor);
      this.databaseCursor.moveToNext();
      return localSavedQuery;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.DatabaseBackedSavedQueryProvider
 * JD-Core Version:    0.6.2
 */