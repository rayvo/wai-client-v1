package com.google.android.apps.unveil.history;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.BoundingBoxProtos.BoundingBox.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import java.util.zip.DataFormatException;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchHistoryItemFactory
{
  private static final UnveilLogger logger = new UnveilLogger();

  public static SearchHistoryItem fromJsonObject(JSONObject paramJSONObject)
    throws JSONException, DataFormatException
  {
    if (paramJSONObject == null)
      throw new IllegalArgumentException("Null JSON object.");
    boolean bool1 = paramJSONObject.has("note");
    boolean bool2 = paramJSONObject.has("location");
    Double localDouble1 = null;
    Double localDouble2 = null;
    String str = null;
    if (bool2)
    {
      JSONObject localJSONObject = (JSONObject)paramJSONObject.get("location");
      boolean bool3 = localJSONObject.has("description");
      str = null;
      if (bool3)
        str = localJSONObject.getString("description");
      boolean bool4 = localJSONObject.has("lat");
      localDouble1 = null;
      localDouble2 = null;
      if (bool4)
      {
        boolean bool5 = localJSONObject.has("lng");
        localDouble1 = null;
        localDouble2 = null;
        if (bool5)
        {
          localDouble1 = Double.valueOf(localJSONObject.getDouble("lat"));
          localDouble2 = Double.valueOf(localJSONObject.getDouble("lng"));
        }
      }
    }
    if (representsPuggleItem(paramJSONObject))
      return toPuggleItem(paramJSONObject, bool1, str, localDouble1, localDouble2);
    return toItem(paramJSONObject, bool1, str, localDouble1, localDouble2);
  }

  static GogglesProtos.GogglesRequest.Source getSource(String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return GogglesProtos.GogglesRequest.Source.LIVE;
    try
    {
      GogglesProtos.GogglesRequest.Source localSource = GogglesProtos.GogglesRequest.Source.valueOf(paramString);
      return localSource;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    return GogglesProtos.GogglesRequest.Source.UNKNOWN;
  }

  private static boolean representsPuggleItem(JSONObject paramJSONObject)
    throws JSONException
  {
    boolean bool1 = paramJSONObject.has("querySource");
    boolean bool2 = false;
    if (bool1)
    {
      GogglesProtos.GogglesRequest.Source localSource1 = getSource(paramJSONObject.getString("querySource"));
      GogglesProtos.GogglesRequest.Source localSource2 = GogglesProtos.GogglesRequest.Source.PUGGLE;
      bool2 = false;
      if (localSource1 == localSource2)
        bool2 = true;
    }
    return bool2;
  }

  private static SearchHistoryItem toItem(JSONObject paramJSONObject, boolean paramBoolean, String paramString, Double paramDouble1, Double paramDouble2)
    throws JSONException, DataFormatException
  {
    String str1 = paramJSONObject.getString("momentId");
    String str2 = paramJSONObject.getString("bestTitle");
    String str3 = paramJSONObject.getString("url");
    long l = paramJSONObject.getLong("creationTime");
    String str4 = paramJSONObject.getString("imageUrl");
    if (paramBoolean);
    SearchHistoryItem localSearchHistoryItem;
    for (String str5 = paramJSONObject.getString("note"); ; str5 = null)
    {
      localSearchHistoryItem = new SearchHistoryItem(str1, str2, str3, l, str4, str5, paramJSONObject.getBoolean("isShared"), paramJSONObject.getBoolean("isSearchable"), paramString, paramDouble1, paramDouble2);
      if ((!localSearchHistoryItem.getMomentId().equals("")) && (!localSearchHistoryItem.getMomentUrl().equals("")) && (!localSearchHistoryItem.getThumbnailUrl().equals("")))
        break;
      throw new DataFormatException("JSON missing required fields: " + paramJSONObject);
    }
    return localSearchHistoryItem;
  }

  private static SearchHistoryPuggleItem toPuggleItem(JSONObject paramJSONObject, boolean paramBoolean, String paramString, Double paramDouble1, Double paramDouble2)
    throws JSONException
  {
    boolean bool1 = paramJSONObject.has("initialSegmentation");
    BoundingBoxProtos.BoundingBox.Builder localBuilder = BoundingBoxProtos.BoundingBox.newBuilder();
    if (bool1)
    {
      JSONObject localJSONObject = (JSONObject)paramJSONObject.get("initialSegmentation");
      localBuilder.setX(localJSONObject.getInt("x"));
      localBuilder.setY(localJSONObject.getInt("y"));
      localBuilder.setWidth(localJSONObject.getInt("width"));
      localBuilder.setHeight(localJSONObject.getInt("height"));
    }
    String str1 = paramJSONObject.getString("momentId");
    String str2 = paramJSONObject.getString("bestTitle");
    String str3 = paramJSONObject.getString("url");
    long l = paramJSONObject.getLong("creationTime");
    String str4 = paramJSONObject.getString("imageUrl");
    String str5;
    boolean bool2;
    boolean bool3;
    String str6;
    if (paramBoolean)
    {
      str5 = paramJSONObject.getString("note");
      bool2 = paramJSONObject.getBoolean("isShared");
      bool3 = paramJSONObject.getBoolean("isSearchable");
      if (!paramJSONObject.has("textRestrict"))
        break label216;
      str6 = paramJSONObject.getString("textRestrict");
      label166: if (!bool1)
        break label223;
    }
    label216: label223: for (BoundingBoxProtos.BoundingBox localBoundingBox = localBuilder.build(); ; localBoundingBox = null)
    {
      return new SearchHistoryPuggleItem(str1, str2, str3, l, str4, str5, bool2, bool3, paramString, paramDouble1, paramDouble2, str6, localBoundingBox);
      str5 = null;
      break;
      str6 = "";
      break label166;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryItemFactory
 * JD-Core Version:    0.6.2
 */