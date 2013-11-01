package com.google.android.apps.unveil.history;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilContentProvider.SfCResults;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class SfCItemProvider extends ItemProvider
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Context context;
  private final ArrayList<String> ids;

  public SfCItemProvider(String paramString, List<String> paramList, Context paramContext)
  {
    super(paramString);
    this.ids = new ArrayList(paramList);
    this.context = paramContext;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if (SfCItem.from(paramContext, str) == null)
      {
        logger.d("Ignore invalid search from camera result with id %d", new Object[] { str });
        this.ids.remove(str);
      }
    }
  }

  public static void deleteAllExceptFor(Context paramContext, List<String> paramList)
  {
    paramContext.getContentResolver().delete(UnveilContentProvider.SfCResults.getContentUri(paramContext), "_id NOT IN " + generateIdWhereClause(paramList), null);
  }

  private static String generateIdWhereClause(List<String> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append('(');
    localStringBuilder.append(TextUtils.join(",", paramList));
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }

  public static List<String> getLocalSfCResultsOnly(Context paramContext, List<String> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = paramContext.getContentResolver().query(UnveilContentProvider.SfCResults.getContentUri(paramContext), new String[] { "_id" }, "_id IN " + generateIdWhereClause(paramList) + " AND " + "moment_id" + " IS NOT NULL", null, null);
    if (localCursor != null);
    try
    {
      if (localCursor.moveToFirst())
      {
        boolean bool;
        do
        {
          localArrayList.add(String.valueOf(localCursor.getLong(localCursor.getColumnIndex("_id"))));
          bool = localCursor.moveToNext();
        }
        while (bool);
      }
      return localArrayList;
    }
    finally
    {
      if (localCursor != null)
        localCursor.close();
    }
  }

  public ItemModel get(int paramInt)
  {
    String str = (String)this.ids.get(-1 + getCount() - paramInt);
    return SfCItem.from(this.context, str);
  }

  public int getCount()
  {
    return this.ids.size();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SfCItemProvider
 * JD-Core Version:    0.6.2
 */