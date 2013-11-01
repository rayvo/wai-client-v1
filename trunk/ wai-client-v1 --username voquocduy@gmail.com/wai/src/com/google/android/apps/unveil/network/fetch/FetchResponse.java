package com.google.android.apps.unveil.network.fetch;

import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

public class FetchResponse
{
  static final int BUFFER_SIZE = 32768;
  public static final int RESPONSE_TOO_LARGE = -2;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Map<String, String> cookies;
  private final byte[] data;
  private final String reasonPhrase;
  private final int statusCode;

  public FetchResponse(byte[] paramArrayOfByte, int paramInt, Map<String, String> paramMap, String paramString)
  {
    this.data = paramArrayOfByte;
    this.statusCode = paramInt;
    this.reasonPhrase = paramString;
    this.cookies = Collections.unmodifiableMap(paramMap);
  }

  static Map<String, String> extractCookiesFromClient(HttpClient paramHttpClient)
  {
    HashMap localHashMap = new HashMap();
    if ((paramHttpClient instanceof AbstractHttpClient))
    {
      Iterator localIterator = new ArrayList(((DefaultHttpClient)paramHttpClient).getCookieStore().getCookies()).iterator();
      while (localIterator.hasNext())
      {
        Cookie localCookie = (Cookie)localIterator.next();
        localHashMap.put(localCookie.getName(), localCookie.getValue());
      }
    }
    return localHashMap;
  }

  public static boolean isValid(FetchResponse paramFetchResponse)
  {
    if (paramFetchResponse == null)
    {
      logger.e("Null response!", new Object[0]);
      return false;
    }
    if (paramFetchResponse.getStatusCode() != 200)
    {
      logger.e("Invalid response %s", new Object[] { paramFetchResponse });
      return false;
    }
    return true;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    FetchResponse localFetchResponse;
    do
    {
      return true;
      if (paramObject == null)
        return false;
      if (getClass() != paramObject.getClass())
        return false;
      localFetchResponse = (FetchResponse)paramObject;
      if (this.cookies == null)
      {
        if (localFetchResponse.cookies != null)
          return false;
      }
      else if (!this.cookies.equals(localFetchResponse.cookies))
        return false;
      if (!Arrays.equals(this.data, localFetchResponse.data))
        return false;
    }
    while (this.statusCode == localFetchResponse.statusCode);
    return false;
  }

  public String getCookie(String paramString)
  {
    return (String)this.cookies.get(paramString);
  }

  public Map<String, String> getCookies()
  {
    return this.cookies;
  }

  public byte[] getData()
  {
    return this.data;
  }

  public String getReasonPhrase()
  {
    return this.reasonPhrase;
  }

  public int getStatusCode()
  {
    return this.statusCode;
  }

  public int hashCode()
  {
    if (this.cookies == null);
    for (int i = 0; ; i = this.cookies.hashCode())
      return 31 * (31 * (i + 31) + Arrays.hashCode(this.data)) + this.statusCode;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(this.statusCode);
    arrayOfObject[1] = this.reasonPhrase;
    return String.format("FetchResponse [statusCode=%d, reasonPhrase=%s]", arrayOfObject);
  }

  public static abstract interface Handler
  {
    public abstract void onResponseReceived(FetchResponse paramFetchResponse);
  }

  private static class HttpClientCookieProvider
    implements Provider<Map<String, String>>
  {
    private final HttpClient client;

    public HttpClientCookieProvider(HttpClient paramHttpClient)
    {
      this.client = paramHttpClient;
    }

    public Map<String, String> get()
    {
      return FetchResponse.extractCookiesFromClient(this.client);
    }
  }

  public static final class HttpResponseHandler
    implements ResponseHandler<FetchResponse>
  {
    private final Provider<Map<String, String>> cookieProvider;

    public HttpResponseHandler(Provider<Map<String, String>> paramProvider)
    {
      this.cookieProvider = paramProvider;
    }

    public HttpResponseHandler(HttpClient paramHttpClient)
    {
      this.cookieProvider = new FetchResponse.HttpClientCookieProvider(paramHttpClient);
    }

    public static ByteArrayOutputStream bufferResponse(HttpResponse paramHttpResponse, boolean paramBoolean)
      throws IOException
    {
      Object localObject = paramHttpResponse.getEntity().getContent();
      if (paramBoolean)
        localObject = new GZIPInputStream((InputStream)localObject);
      BufferedInputStream localBufferedInputStream = new BufferedInputStream((InputStream)localObject);
      int i = (int)paramHttpResponse.getEntity().getContentLength();
      if (i < 0)
        FetchResponse.logger.w("Unknown length when fetching.", new Object[0]);
      for (ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(32768); ; localByteArrayOutputStream = new ByteArrayOutputStream(i))
      {
        byte[] arrayOfByte = new byte[32768];
        while (true)
        {
          int j = localBufferedInputStream.read(arrayOfByte);
          if (j <= 0)
            break;
          localByteArrayOutputStream.write(arrayOfByte, 0, j);
        }
      }
      localBufferedInputStream.close();
      return localByteArrayOutputStream;
    }

    public FetchResponse handleResponse(HttpResponse paramHttpResponse)
      throws ClientProtocolException, IOException
    {
      Map localMap = (Map)this.cookieProvider.get();
      Header localHeader = paramHttpResponse.getFirstHeader("Content-Encoding");
      boolean bool;
      if ((localHeader != null) && (localHeader.getValue().equalsIgnoreCase("gzip")))
        bool = true;
      try
      {
        while (true)
        {
          ByteArrayOutputStream localByteArrayOutputStream = bufferResponse(paramHttpResponse, bool);
          return new FetchResponse(localByteArrayOutputStream.toByteArray(), paramHttpResponse.getStatusLine().getStatusCode(), localMap, paramHttpResponse.getStatusLine().getReasonPhrase());
          bool = false;
        }
      }
      catch (OutOfMemoryError localOutOfMemoryError)
      {
      }
      return new FetchResponse(null, -2, localMap, "");
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.fetch.FetchResponse
 * JD-Core Version:    0.6.2
 */