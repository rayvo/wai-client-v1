package com.google.android.apps.unveil.env;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LastTextRestrictsProvider
{
  protected static final int MAX_STORED_TEXT_RESTRICTS = 20;
  private static final String WHERE_CLAUSE = "text_restrict = ?";
  private static final UnveilLogger logger = new UnveilLogger();
  private final ContentResolver contentProvider;
  private final Uri defaultUri;

  public LastTextRestrictsProvider(ContentResolver paramContentResolver, Context paramContext)
  {
    this.contentProvider = paramContentResolver;
    this.defaultUri = UnveilContentProvider.LastTextRestricts.getContentUri(paramContext);
  }

  private List<String> getTextRestricts()
  {
    Cursor localCursor = this.contentProvider.query(this.defaultUri, new String[] { "text_restrict" }, null, null, null);
    if (localCursor.getCount() == 0)
    {
      localCursor.close();
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList(localCursor.getCount());
    localCursor.moveToFirst();
    while (!localCursor.isAfterLast())
    {
      localArrayList.add(localCursor.getString(localCursor.getColumnIndex("text_restrict")));
      localCursor.moveToNext();
    }
    localCursor.close();
    return localArrayList;
  }

  public Cursor getCursor()
  {
    return this.contentProvider.query(this.defaultUri, new String[] { "_id", "text_restrict" }, null, null, null);
  }

  public void putTextRestrict(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return;
    List localList = getTextRestricts();
    if (localList.contains(paramString))
    {
      ContentValues localContentValues1 = new ContentValues(1);
      localContentValues1.put("created", Long.valueOf(System.currentTimeMillis()));
      this.contentProvider.update(this.defaultUri, localContentValues1, "text_restrict = ?", new String[] { paramString });
      return;
    }
    if (localList.size() == 20)
    {
      ContentResolver localContentResolver = this.contentProvider;
      Uri localUri = this.defaultUri;
      String[] arrayOfString = new String[1];
      arrayOfString[0] = ((String)localList.get(-1 + localList.size()));
      localContentResolver.delete(localUri, "text_restrict = ?", arrayOfString);
    }
    ContentValues localContentValues2 = new ContentValues(1);
    localContentValues2.put("text_restrict", paramString);
    this.contentProvider.insert(this.defaultUri, localContentValues2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.LastTextRestrictsProvider
 * JD-Core Version:    0.6.2
 */