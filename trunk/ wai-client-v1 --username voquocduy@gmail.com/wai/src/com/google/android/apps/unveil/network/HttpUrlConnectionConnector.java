package com.google.android.apps.unveil.network;

import android.net.ConnectivityManager;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.StreamLoader;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.HttpResponseHandler;
import com.google.protobuf.GeneratedMessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public class HttpUrlConnectionConnector extends AbstractConnector
{
  private static final UnveilLogger logger = new UnveilLogger();

  public HttpUrlConnectionConnector(ConnectivityManager paramConnectivityManager, Provider<URL> paramProvider, DefaultHttpRequestFactory paramDefaultHttpRequestFactory)
  {
    super(connectionManagerConnectivityProvider(paramConnectivityManager), paramProvider, paramDefaultHttpRequestFactory);
    if (CookieHandler.getDefault() == null)
      CookieHandler.setDefault(new CookieManager());
  }

  private static HttpResponse asHttpResponse(HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    BasicHttpResponse localBasicHttpResponse = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), paramHttpURLConnection.getResponseCode(), paramHttpURLConnection.getResponseMessage()));
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramHttpURLConnection.getURL();
    arrayOfObject[1] = Integer.valueOf(paramHttpURLConnection.getResponseCode());
    arrayOfObject[2] = paramHttpURLConnection.getResponseMessage();
    localUnveilLogger.d("%s %d %s", arrayOfObject);
    Iterator localIterator = paramHttpURLConnection.getHeaderFields().entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localEntry.getKey() != null)
        localBasicHttpResponse.setHeader((String)localEntry.getKey(), (String)((List)localEntry.getValue()).get(0));
    }
    if (paramHttpURLConnection.getResponseCode() == 200);
    for (InputStream localInputStream = paramHttpURLConnection.getInputStream(); ; localInputStream = paramHttpURLConnection.getErrorStream())
    {
      localBasicHttpResponse.setEntity(new ByteArrayEntity(StreamLoader.getByteArrayForStream(localInputStream)));
      return localBasicHttpResponse;
    }
  }

  private static void configureConnectionForRequest(HttpUriRequest paramHttpUriRequest, HttpURLConnection paramHttpURLConnection)
    throws ProtocolException, IOException
  {
    paramHttpURLConnection.setRequestMethod(paramHttpUriRequest.getMethod());
    paramHttpURLConnection.setDoInput(true);
    paramHttpURLConnection.setDoOutput(paramHttpUriRequest instanceof HttpEntityEnclosingRequest);
    copyRequestHeaders(paramHttpUriRequest, paramHttpURLConnection);
  }

  private static void copyRequestHeaders(HttpUriRequest paramHttpUriRequest, HttpURLConnection paramHttpURLConnection)
  {
    Header[] arrayOfHeader = paramHttpUriRequest.getAllHeaders();
    int i = arrayOfHeader.length;
    int j = 0;
    if (j < i)
    {
      Header localHeader = arrayOfHeader[j];
      if (!"accept-encoding".equalsIgnoreCase(localHeader.getName()))
        paramHttpURLConnection.addRequestProperty(localHeader.getName(), localHeader.getValue());
      while (true)
      {
        j++;
        break;
        logger.i("Ignoring accept-encoding header to allow URLConnection to manage compression", new Object[0]);
      }
    }
  }

  private static <T> T execute(HttpUriRequest paramHttpUriRequest, ResponseHandler<T> paramResponseHandler)
    throws MalformedURLException, IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramHttpUriRequest.getURI().toURL().openConnection();
    configureConnectionForRequest(paramHttpUriRequest, localHttpURLConnection);
    maybePostContent(paramHttpUriRequest, localHttpURLConnection);
    HttpResponse localHttpResponse = asHttpResponse(localHttpURLConnection);
    localHttpURLConnection.disconnect();
    return paramResponseHandler.handleResponse(localHttpResponse);
  }

  private static Provider<Map<String, String>> getCookieProvider(HttpUriRequest paramHttpUriRequest)
  {
    return new Provider()
    {
      public Map<String, String> get()
      {
        HashMap localHashMap = new HashMap();
        try
        {
          Iterator localIterator = CookieHandler.getDefault().get(this.val$request.getURI(), Collections.emptyMap()).entrySet().iterator();
          while (localIterator.hasNext())
          {
            Map.Entry localEntry = (Map.Entry)localIterator.next();
            if (!((List)localEntry.getValue()).isEmpty())
              localHashMap.put(localEntry.getKey(), ((List)localEntry.getValue()).get(0));
          }
        }
        catch (IOException localIOException)
        {
          throw new RuntimeException(localIOException);
        }
        return localHashMap;
      }
    };
  }

  private static void maybePostContent(HttpUriRequest paramHttpUriRequest, HttpURLConnection paramHttpURLConnection)
    throws IOException
  {
    if ((paramHttpUriRequest instanceof HttpEntityEnclosingRequest))
    {
      InputStream localInputStream = ((HttpEntityEnclosingRequest)paramHttpUriRequest).getEntity().getContent();
      OutputStream localOutputStream = paramHttpURLConnection.getOutputStream();
      StreamLoader.copy(localInputStream, localOutputStream);
      localOutputStream.flush();
    }
  }

  public <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> blockingRequest(Class<ResponseType> paramClass, HttpPost paramHttpPost)
    throws AbstractConnector.ConnectorException
  {
    try
    {
      UnveilResponse localUnveilResponse = (UnveilResponse)execute(paramHttpPost, new HttpProtoResponseHandler(paramClass));
      return localUnveilResponse;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      throw new AbstractConnector.ConnectorException(localMalformedURLException);
    }
    catch (IOException localIOException)
    {
      throw new AbstractConnector.ConnectorException(localIOException);
    }
  }

  public FetchResponse blockingRequest(HttpUriRequest paramHttpUriRequest)
    throws AbstractConnector.ConnectorException
  {
    try
    {
      FetchResponse localFetchResponse = (FetchResponse)execute(paramHttpUriRequest, new FetchResponse.HttpResponseHandler(getCookieProvider(paramHttpUriRequest)));
      return localFetchResponse;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      throw new AbstractConnector.ConnectorException(localIllegalStateException);
    }
    catch (IOException localIOException)
    {
      throw new AbstractConnector.ConnectorException(localIOException);
    }
  }

  public HttpResponse execute(HttpUriRequest paramHttpUriRequest)
    throws MalformedURLException, IOException
  {
    HttpURLConnection localHttpURLConnection = (HttpURLConnection)paramHttpUriRequest.getURI().toURL().openConnection();
    configureConnectionForRequest(paramHttpUriRequest, localHttpURLConnection);
    maybePostContent(paramHttpUriRequest, localHttpURLConnection);
    return asHttpResponse(localHttpURLConnection);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.HttpUrlConnectionConnector
 * JD-Core Version:    0.6.2
 */