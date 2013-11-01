package com.google.android.apps.unveil.protocol;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.GogglesConfigProtos.GogglesConfig;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;

public class PuggleQueryBuilder extends QueryBuilder
{
  private static final UnveilLogger logger = new UnveilLogger();
  private String text;

  public PuggleQueryBuilder()
  {
    setSource(GogglesProtos.GogglesRequest.Source.PUGGLE);
  }

  public static PuggleQueryBuilder from(Cursor paramCursor)
  {
    PuggleQueryBuilder localPuggleQueryBuilder = new PuggleQueryBuilder();
    try
    {
      localPuggleQueryBuilder.setValuesFromCursor(paramCursor);
      return localPuggleQueryBuilder;
    }
    catch (QueryBuilder.QueryBuilderParseException localQueryBuilderParseException)
    {
      logger.e(localQueryBuilderParseException, "Could not parse query from the given cursor.", new Object[0]);
    }
    return null;
  }

  public PuggleQueryBuilder addText(String paramString)
  {
    this.text = paramString;
    return this;
  }

  public GogglesProtos.GogglesRequest.Builder buildGogglesRequestBuilder(UnveilContext paramUnveilContext)
  {
    GogglesProtos.GogglesRequest.Builder localBuilder = super.buildGogglesRequestBuilder(paramUnveilContext);
    GogglesConfigProtos.GogglesConfig.newBuilder();
    if (!TextUtils.isEmpty(this.text))
      localBuilder.setText(this.text);
    return localBuilder;
  }

  public String getText()
  {
    return this.text;
  }

  protected void setValuesFromCursor(Cursor paramCursor)
    throws QueryBuilder.QueryBuilderParseException
  {
    super.setValuesFromCursor(paramCursor);
    int i = paramCursor.getColumnIndex("text");
    if (i != -1)
      this.text = paramCursor.getString(i);
  }

  public ContentValues toContentValues()
  {
    ContentValues localContentValues = super.toContentValues();
    if (TextUtils.isEmpty(this.text))
      localContentValues.put("text", this.text);
    return localContentValues;
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PuggleQueryBuilder [");
    if (this.text != null)
    {
      localStringBuilder.append("text=");
      localStringBuilder.append(this.text);
      localStringBuilder.append(", ");
    }
    localStringBuilder.append(super.toString());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.PuggleQueryBuilder
 * JD-Core Version:    0.6.2
 */