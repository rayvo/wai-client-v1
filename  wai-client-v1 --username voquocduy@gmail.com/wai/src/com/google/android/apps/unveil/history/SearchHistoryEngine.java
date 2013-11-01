package com.google.android.apps.unveil.history;

import android.graphics.Rect;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Clock;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory.ContentType;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory.HeaderProvider;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.Handler;
import com.google.protobuf.ByteString;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SearchHistoryEngine
{
  public static final String SEARCH_PATH = "/goggles/a/api/json/moments/";
  private static final UnveilLogger logger = new UnveilLogger();
  private final UnveilContext application;
  private final AbstractConnector connector;
  private final DefaultHttpRequestFactory.HeaderProvider manualAuthenticationProvider;
  private final DefaultHttpRequestFactory requestFactory;
  private final XsrfTokenManager xsrfTokenManager;

  public SearchHistoryEngine(UnveilContext paramUnveilContext)
  {
    this.application = paramUnveilContext;
    this.connector = paramUnveilContext.getConnector();
    this.requestFactory = DefaultHttpRequestFactory.newAuthenticatedRequestFactory(paramUnveilContext);
    this.manualAuthenticationProvider = DefaultHttpRequestFactory.newAuthenticationProvider(paramUnveilContext);
    this.xsrfTokenManager = new XsrfTokenManager(this.requestFactory, Clock.SYSTEM_CLOCK, this.connector, paramUnveilContext.getFrontendUrl());
  }

  private URL convertUrlToHttps(URL paramURL)
  {
    try
    {
      URL localURL = new URL("https", paramURL.getHost(), paramURL.getPort(), paramURL.getFile());
      return localURL;
    }
    catch (MalformedURLException localMalformedURLException)
    {
    }
    return paramURL;
  }

  private static String encodeUserSubmission(String paramString1, String paramString2, String paramString3, Rect paramRect)
  {
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = URLEncoder.encode(paramString1);
    arrayOfObject[1] = URLEncoder.encode(paramString2);
    arrayOfObject[2] = URLEncoder.encode(paramString3);
    arrayOfObject[3] = Integer.valueOf(paramRect.left);
    arrayOfObject[4] = Integer.valueOf(paramRect.top);
    arrayOfObject[5] = Integer.valueOf(paramRect.width());
    arrayOfObject[6] = Integer.valueOf(paramRect.height());
    return String.format("_method=put&userSubmittedTitle=%s&userSubmittedDescription=%s&userSubmittedType=%s&userSubmittedRegion=%d,%d,%d,%d&isSearchable=true", arrayOfObject);
  }

  private String getMomentUrl(String paramString)
  {
    return getRootUrl(false) + paramString;
  }

  private String getRootUrl(boolean paramBoolean)
  {
    URL localURL = this.application.getFrontendUrl();
    if (localURL == null)
      return null;
    if (paramBoolean)
      return convertUrlToHttps(localURL) + "/goggles/a/api/json/moments/";
    return localURL + "/goggles/a/api/json/moments/";
  }

  private HttpPost newPost(String paramString1, String paramString2)
  {
    return this.requestFactory.newPostRequest(paramString1, DefaultHttpRequestFactory.newStringEntity(paramString2), DefaultHttpRequestFactory.ContentType.URL_ENCODED);
  }

  public void delete(String paramString, final EventListener paramEventListener)
  {
    ResponseHandler local6 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        paramEventListener.onDeleteResponse();
      }
    };
    String str = getMomentUrl(paramString);
    this.xsrfTokenManager.executeSideEffectRequest(newPost(str, "_method=DELETE"), local6);
  }

  public void invalidateXsrfToken()
  {
    this.xsrfTokenManager.invalidateToken();
  }

  public void lookup(String paramString, final EventListener paramEventListener)
  {
    ResponseHandler local2 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        try
        {
          paramEventListener.onLookupResponse(SearchHistoryItemFactory.fromJsonObject(new JSONObject(paramAnonymousString)));
          return;
        }
        catch (JSONException localJSONException)
        {
          SearchHistoryEngine.logger.e(localJSONException, "Failed to parse JSON: %s", new Object[] { paramAnonymousString });
          paramEventListener.onError();
          return;
        }
        catch (DataFormatException localDataFormatException)
        {
          SearchHistoryEngine.logger.e(localDataFormatException, "Failed to lookup SearchHistoryItem: %s", new Object[] { paramAnonymousString });
          paramEventListener.onError();
        }
      }
    };
    String str = getMomentUrl(paramString);
    this.connector.sendRequest(this.requestFactory.newGetRequest(str), local2);
  }

  public void reportAbuse(String paramString1, String paramString2, String paramString3, boolean paramBoolean, final EventListener paramEventListener)
  {
    ResponseHandler local7 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        paramEventListener.onAbuseResponse();
      }
    };
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramString1;
    arrayOfObject[1] = URLEncoder.encode(paramString2);
    arrayOfObject[2] = URLEncoder.encode(paramString3);
    HttpPost localHttpPost = newPost(String.format("%s?abuseCategory=%s&comment=%s", arrayOfObject), "");
    if (paramBoolean)
    {
      this.manualAuthenticationProvider.attachHeader(localHttpPost);
      this.xsrfTokenManager.executeSideEffectRequest(localHttpPost, local7);
      return;
    }
    this.connector.sendRequest(localHttpPost, local7);
  }

  public void search(SearchHistoryQuery.QuerySpec paramQuerySpec, String paramString, int paramInt, final EventListener paramEventListener)
  {
    ResponseHandler local1 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        JSONObject localJSONObject;
        ArrayList localArrayList;
        try
        {
          localJSONObject = new JSONObject(paramAnonymousString);
          JSONArray localJSONArray = localJSONObject.getJSONArray("gogglesQueries");
          localArrayList = new ArrayList();
          int i = 0;
          while (true)
          {
            int j = localJSONArray.length();
            if (i < j)
              try
              {
                localArrayList.add(SearchHistoryItemFactory.fromJsonObject(localJSONArray.getJSONObject(i)));
                i++;
              }
              catch (DataFormatException localDataFormatException)
              {
                while (true)
                  SearchHistoryEngine.logger.e("Skipping problematic SearchHistoryItem: %s", new Object[] { localDataFormatException });
              }
          }
        }
        catch (JSONException localJSONException)
        {
          SearchHistoryEngine.logger.e(localJSONException, "Failed to parse JSON: %s", new Object[] { paramAnonymousString });
          paramEventListener.onError();
          return;
        }
        paramEventListener.onSearchResponse((SearchHistoryItem[])localArrayList.toArray(new SearchHistoryItem[0]), localJSONObject.getInt("numMatched"), localJSONObject.getString("continuationToken"));
      }
    };
    StringBuilder localStringBuilder = new StringBuilder(getRootUrl(paramQuerySpec.prefersHttps()));
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramQuerySpec.queryString();
    arrayOfObject[1] = paramString;
    arrayOfObject[2] = Integer.valueOf(paramInt);
    localStringBuilder.append(String.format("?%s&continuationToken=%s&num=%d", arrayOfObject));
    String str = localStringBuilder.toString();
    logger.i("SearchHistoryEngine.search: url=%s", new Object[] { str });
    this.connector.sendRequest(this.requestFactory.newGetRequest(str), local1);
  }

  public void setUserSubmission(String paramString1, String paramString2, String paramString3, String paramString4, Rect paramRect, final EventListener paramEventListener)
  {
    ResponseHandler local3 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        paramEventListener.onUserSubmissionResponse();
      }
    };
    String str1 = getMomentUrl(paramString1);
    String str2 = encodeUserSubmission(paramString2, paramString3, paramString4, paramRect);
    logger.i("Sending user submission to %s", new Object[] { str1 });
    logger.i("Submission body: %s", new Object[] { str2 });
    this.xsrfTokenManager.executeSideEffectRequest(newPost(str1, str2), local3);
  }

  public void update(String paramString1, String paramString2, String paramString3, final EventListener paramEventListener)
  {
    ResponseHandler local5 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        try
        {
          paramEventListener.onUpdateResponse(SearchHistoryItemFactory.fromJsonObject(new JSONObject(paramAnonymousString)));
          return;
        }
        catch (JSONException localJSONException)
        {
          SearchHistoryEngine.logger.e(localJSONException, "Failed to parse JSON: %s", new Object[] { paramAnonymousString });
          paramEventListener.onError();
          return;
        }
        catch (DataFormatException localDataFormatException)
        {
          SearchHistoryEngine.logger.e(localDataFormatException, "Failed to create updated SearchHistoryItem: %s", new Object[] { paramAnonymousString });
          paramEventListener.onError();
        }
      }
    };
    String str1 = getMomentUrl(paramString1);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = URLEncoder.encode(paramString2);
    arrayOfObject[1] = URLEncoder.encode(paramString3);
    String str2 = String.format("_method=PUT&%s=%s", arrayOfObject);
    this.xsrfTokenManager.executeSideEffectRequest(newPost(str1, str2), local5);
  }

  public void withdrawUserSubmission(String paramString, final EventListener paramEventListener)
  {
    ResponseHandler local4 = new ResponseHandler(paramEventListener, paramEventListener)
    {
      public void onValidResponse(String paramAnonymousString)
      {
        paramEventListener.onUserSubmissionResponse();
      }
    };
    String str = getMomentUrl(paramString);
    logger.i("Sending user submission to %s", new Object[] { str });
    logger.i("Submission body: %s", new Object[] { "_method=put&isSearchable=false" });
    this.xsrfTokenManager.executeSideEffectRequest(newPost(str, "_method=put&isSearchable=false"), local4);
  }

  public static abstract class EventListener
  {
    public void onAbuseResponse()
    {
      throw new UnsupportedOperationException();
    }

    public void onDeleteResponse()
    {
      throw new UnsupportedOperationException();
    }

    public abstract void onError();

    public void onLookupResponse(SearchHistoryItem paramSearchHistoryItem)
    {
      throw new UnsupportedOperationException();
    }

    public void onSearchResponse(SearchHistoryItem[] paramArrayOfSearchHistoryItem, int paramInt, String paramString)
    {
      throw new UnsupportedOperationException();
    }

    public void onUpdateResponse(SearchHistoryItem paramSearchHistoryItem)
    {
      throw new UnsupportedOperationException();
    }

    public void onUserSubmissionResponse()
    {
      throw new UnsupportedOperationException();
    }
  }

  private abstract class ResponseHandler
    implements FetchResponse.Handler
  {
    private final SearchHistoryEngine.EventListener listener;

    public ResponseHandler(SearchHistoryEngine.EventListener arg2)
    {
      Object localObject;
      this.listener = localObject;
    }

    public final void onResponseReceived(FetchResponse paramFetchResponse)
    {
      if (!FetchResponse.isValid(paramFetchResponse))
      {
        this.listener.onError();
        return;
      }
      SearchHistoryEngine.this.xsrfTokenManager.handleGetResponse(paramFetchResponse);
      onValidResponse(ByteString.copyFrom(paramFetchResponse.getData()).toStringUtf8());
    }

    protected abstract void onValidResponse(String paramString);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryEngine
 * JD-Core Version:    0.6.2
 */