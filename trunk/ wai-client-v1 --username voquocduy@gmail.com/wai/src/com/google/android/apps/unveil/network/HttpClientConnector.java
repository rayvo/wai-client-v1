package com.google.android.apps.unveil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.Providers;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.HttpResponseHandler;
import com.google.android.apps.unveil.network.utils.ResponseContentTransferrer;
import com.google.protobuf.GeneratedMessageLite;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;

public class HttpClientConnector extends AbstractConnector
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final HttpClient httpClient;
  private final ResponseContentTransferrer responseConsumer = new ResponseContentTransferrer();

  public HttpClientConnector(HttpClient paramHttpClient, ConnectivityManager paramConnectivityManager, Provider<URL> paramProvider, DefaultHttpRequestFactory paramDefaultHttpRequestFactory)
  {
    super(connectionManagerConnectivityProvider(paramConnectivityManager), paramProvider, paramDefaultHttpRequestFactory);
    this.httpClient = paramHttpClient;
  }

  public static HttpClientConnector newHttpClientConnector(Context paramContext, String paramString1, Provider<String> paramProvider, String paramString2)
  {
    try
    {
      URL localURL = new URL(paramString1);
      return new HttpClientConnector(HttpClientFactory.make(paramContext), (ConnectivityManager)paramContext.getSystemService("connectivity"), Providers.staticProvider(localURL), DefaultHttpRequestFactory.newAnonymousRequestFactory(paramProvider, paramString2));
    }
    catch (MalformedURLException localMalformedURLException)
    {
      throw new RuntimeException(localMalformedURLException);
    }
  }

  public <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> blockingRequest(Class<ResponseType> paramClass, HttpPost paramHttpPost)
    throws AbstractConnector.ConnectorException
  {
    try
    {
      UnveilResponse localUnveilResponse = new HttpProtoResponseHandler(paramClass).handleResponse(this.httpClient.execute(paramHttpPost));
      return localUnveilResponse;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      throw new AbstractConnector.ConnectorException(localClientProtocolException);
    }
    catch (IOException localIOException)
    {
      throw new AbstractConnector.ConnectorException(localIOException);
    }
  }

  public FetchResponse blockingRequest(HttpUriRequest paramHttpUriRequest)
    throws AbstractConnector.ConnectorException
  {
    FetchResponse.HttpResponseHandler localHttpResponseHandler = new FetchResponse.HttpResponseHandler(this.httpClient);
    try
    {
      HttpResponse localHttpResponse = this.httpClient.execute(paramHttpUriRequest);
      FetchResponse localFetchResponse = localHttpResponseHandler.handleResponse(this.responseConsumer.consumeAndRelease(localHttpResponse));
      return localFetchResponse;
    }
    catch (ClientProtocolException localClientProtocolException)
    {
      throw new AbstractConnector.ConnectorException(localClientProtocolException);
    }
    catch (IOException localIOException)
    {
      throw new AbstractConnector.ConnectorException(localIOException);
    }
    catch (RequestFailedException localRequestFailedException)
    {
      throw new AbstractConnector.ConnectorException(localRequestFailedException);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.HttpClientConnector
 * JD-Core Version:    0.6.2
 */