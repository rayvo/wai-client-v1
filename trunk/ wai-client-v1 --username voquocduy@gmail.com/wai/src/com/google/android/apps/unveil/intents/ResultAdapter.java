package com.google.android.apps.unveil.intents;

import com.google.android.apps.unveil.results.ResultItem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class ResultAdapter
{
  static final int CURRENT_VERSION = 1;
  static final int ONE = 1;
  public static final String RESULTS_KEY = "results";
  public static final String URL_KEY = "url";

  public static ResultAdapter newResultAdapter(int paramInt)
  {
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException("Version unknown: " + paramInt);
    case 1:
    }
    return new VersionOneResultAdapter();
  }

  public abstract String asJson(List<ResultItem> paramList)
    throws JSONException;

  static final class VersionOneResultAdapter extends ResultAdapter
  {
    static JSONArray toJsonArray(List<ResultItem> paramList)
    {
      JSONArray localJSONArray = new JSONArray();
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
        localJSONArray.put(new JSONObject(toMap((ResultItem)localIterator.next())));
      return localJSONArray;
    }

    static Map<String, String> toMap(ResultItem paramResultItem)
    {
      HashMap localHashMap = new HashMap();
      localHashMap.put("url", paramResultItem.getDirectUrl());
      return localHashMap;
    }

    public String asJson(List<ResultItem> paramList)
      throws JSONException
    {
      JSONObject localJSONObject = new JSONObject();
      localJSONObject.put("results", toJsonArray(paramList));
      return localJSONObject.toString();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.intents.ResultAdapter
 * JD-Core Version:    0.6.2
 */