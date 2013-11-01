package com.google.android.apps.unveil.network;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.text.TextUtils;
import com.google.android.apps.unveil.env.HoneycombAsyncTask;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.Handler;
import com.google.android.apps.unveil.network.utils.Stats;
import com.google.android.apps.unveil.network.utils.Stats.Tags;
import com.google.goggles.ContainerProtos.ContainerResponse;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse;
import com.google.goggles.NativeClientLoggingProtos.NativeClientLogEventResponse;
import com.google.goggles.TracingCookieProtos.TracingCookieResponse;
import com.google.goggles.TracingProtos.TraceResponse;
import com.google.protobuf.GeneratedMessageLite;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;

public abstract class AbstractConnector
{
  private static final String FETCH_URL = "/goggles/a/fetch_search_history_entry_proto";
  private static final String GOGGLES_RESPONSE_URL = "/goggles/a/singleshot_search_proto";
  private static final String NATIVE_CLICK_URL = "/goggles/a/native_client_log_proto";
  public static final int NO_STATUS_CODE = -1;
  private static final String REQUEST_URL = "/goggles/container_proto";
  public static final String TEXT_MODE_BASE_PATH = "/goggles/a/single_shot_text_mode";
  private static final String TRACE_COOKIE_URL = "/goggles/a/tracing_cookie_proto";
  private static final String TRACE_URL = "/goggles/a/trace_proto";
  private static final UnveilLogger logger = new UnveilLogger();
  private final Provider<Boolean> connectivityProvider;
  private final Provider<URL> frontendUrlProvider;
  private final DefaultHttpRequestFactory requestFactory;
  private final ExecutorService threadPool;
  private final Handler uiThreadHandler;

  public AbstractConnector(Provider<Boolean> paramProvider, Provider<URL> paramProvider1, DefaultHttpRequestFactory paramDefaultHttpRequestFactory)
  {
    this.frontendUrlProvider = paramProvider1;
    this.connectivityProvider = paramProvider;
    this.requestFactory = paramDefaultHttpRequestFactory;
    this.uiThreadHandler = new Handler();
    this.threadPool = Executors.newCachedThreadPool();
  }

  public static Provider<Boolean> connectionManagerConnectivityProvider(ConnectivityManager paramConnectivityManager)
  {
    return new Provider()
    {
      public Boolean get()
      {
        NetworkInfo localNetworkInfo = this.val$connectivityManager.getActiveNetworkInfo();
        if ((localNetworkInfo != null) && (localNetworkInfo.isConnected()));
        for (boolean bool = true; ; bool = false)
          return Boolean.valueOf(bool);
      }
    };
  }

  private HoneycombAsyncTask<FetchResponse> createFetchRequestTask(final HttpUriRequest paramHttpUriRequest, final FetchResponse.Handler paramHandler)
  {
    return new HoneycombAsyncTask()
    {
      protected FetchResponse doInBackground()
      {
        try
        {
          FetchResponse localFetchResponse = AbstractConnector.this.blockingRequest(paramHttpUriRequest);
          return localFetchResponse;
        }
        catch (AbstractConnector.ConnectorException localConnectorException)
        {
        }
        return null;
      }

      protected void onPostExecute(FetchResponse paramAnonymousFetchResponse)
      {
        paramHandler.onResponseReceived(paramAnonymousFetchResponse);
      }
    };
  }

  private <ProtoResponseType extends GeneratedMessageLite> HoneycombAsyncTask<UnveilResponse<ProtoResponseType>> createProtobufRequestTask(final GeneratedMessageLite paramGeneratedMessageLite, final Class<ProtoResponseType> paramClass, final String paramString1, final String paramString2, final ResponseHandler<ProtoResponseType> paramResponseHandler)
  {
    return new HoneycombAsyncTask()
    {
      protected UnveilResponse<ProtoResponseType> doInBackground()
      {
        try
        {
          Stats.setThreadTag(AbstractConnector.getTagForClass(paramClass));
          UnveilResponse localUnveilResponse = AbstractConnector.this.blockingRequest(paramClass, AbstractConnector.this.prepareRequest(paramString1, new ByteArrayEntity(paramGeneratedMessageLite.toByteArray()), paramString2));
          return localUnveilResponse;
        }
        catch (AbstractConnector.ConnectorException localConnectorException)
        {
          return UnveilResponse.get(localConnectorException);
        }
      }

      protected void onPostExecute(UnveilResponse<ProtoResponseType> paramAnonymousUnveilResponse)
      {
        if (paramAnonymousUnveilResponse.getException() == null)
        {
          paramResponseHandler.onResponse(paramAnonymousUnveilResponse);
          return;
        }
        AbstractConnector.this.handleConnectorException(paramResponseHandler, paramAnonymousUnveilResponse.getException());
      }
    };
  }

  private <ResponseType extends GeneratedMessageLite> void doRequestAndHandleResponse(GeneratedMessageLite paramGeneratedMessageLite, Class<ResponseType> paramClass, String paramString, ResponseHandler<ResponseType> paramResponseHandler)
  {
    try
    {
      paramResponseHandler.onResponse(blockingRequest(paramClass, prepareRequest(getUrlForResponseClass(paramClass), new ByteArrayEntity(paramGeneratedMessageLite.toByteArray()), paramString)));
      return;
    }
    catch (ConnectorException localConnectorException)
    {
      handleConnectorException(paramResponseHandler, localConnectorException);
    }
  }

  private static String getBasePath(Class<? extends GeneratedMessageLite> paramClass)
  {
    if (paramClass.equals(ContainerProtos.ContainerResponse.class))
      return "/goggles/container_proto";
    if (paramClass.equals(TracingCookieProtos.TracingCookieResponse.class))
      return "/goggles/a/tracing_cookie_proto";
    if (paramClass.equals(GogglesProtos.GogglesResponse.class))
      return "/goggles/a/singleshot_search_proto";
    if (paramClass.equals(GogglesReplayProtos.GogglesReplayResponse.class))
      return "/goggles/a/fetch_search_history_entry_proto";
    if (paramClass.equals(NativeClientLoggingProtos.NativeClientLogEventResponse.class))
      return "/goggles/a/native_client_log_proto";
    if (paramClass.equals(TracingProtos.TraceResponse.class))
      return "/goggles/a/trace_proto";
    throw new UnsupportedOperationException(paramClass + " not yet supported.");
  }

  private static Stats.Tags getTagForClass(Class<? extends GeneratedMessageLite> paramClass)
  {
    if (paramClass.equals(ContainerProtos.ContainerResponse.class))
      return Stats.Tags.CONTAINER;
    if (paramClass.equals(TracingCookieProtos.TracingCookieResponse.class))
      return Stats.Tags.TRACING_COOKIE;
    if (paramClass.equals(GogglesProtos.GogglesResponse.class))
      return Stats.Tags.SINGLE_SHOT;
    if (paramClass.equals(GogglesReplayProtos.GogglesReplayResponse.class))
      return Stats.Tags.REPLAY;
    if (paramClass.equals(NativeClientLoggingProtos.NativeClientLogEventResponse.class))
      return Stats.Tags.CLICK_TRACK;
    if (paramClass.equals(TracingProtos.TraceResponse.class))
      return Stats.Tags.TRACE;
    throw new UnsupportedOperationException(paramClass + " not yet supported.");
  }

  private String getUrlForBasePath(String paramString)
  {
    return ((URL)this.frontendUrlProvider.get()).toString() + paramString;
  }

  private String getUrlForResponseClass(Class<? extends GeneratedMessageLite> paramClass)
  {
    return getUrlForBasePath(getBasePath(paramClass));
  }

  private <ResponseType extends GeneratedMessageLite> void handleConnectorException(ResponseHandler<ResponseType> paramResponseHandler, ConnectorException paramConnectorException)
  {
    if ((paramConnectorException.getCause() != null) && ((paramConnectorException.getCause() instanceof HttpResponseException)))
    {
      int i = ((HttpResponseException)paramConnectorException.getCause()).getStatusCode();
      if (i != -1)
      {
        paramResponseHandler.onServerErrorCode(i);
        return;
      }
    }
    paramResponseHandler.onNetworkError();
  }

  private HttpPost prepareRequest(String paramString1, HttpEntity paramHttpEntity, String paramString2)
  {
    HttpPost localHttpPost = this.requestFactory.newPostRequest(paramString1, paramHttpEntity, DefaultHttpRequestFactory.ContentType.PROTOBUF);
    if (!TextUtils.isEmpty(paramString2))
      localHttpPost.addHeader("Cookie", paramString2);
    return localHttpPost;
  }

  public abstract <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> blockingRequest(Class<ResponseType> paramClass, HttpPost paramHttpPost)
    throws AbstractConnector.ConnectorException;

  public abstract FetchResponse blockingRequest(HttpUriRequest paramHttpUriRequest)
    throws AbstractConnector.ConnectorException;

  public boolean isNetworkAvailable()
  {
    return ((Boolean)this.connectivityProvider.get()).booleanValue();
  }

  public final <ResponseType extends GeneratedMessageLite> Runnable requestRunnable(final GeneratedMessageLite paramGeneratedMessageLite, final Class<ResponseType> paramClass, final String paramString, final ResponseHandler<ResponseType> paramResponseHandler)
  {
    return new Runnable()
    {
      public void run()
      {
        AbstractConnector.this.doRequestAndHandleResponse(paramGeneratedMessageLite, paramClass, paramString, paramResponseHandler);
      }
    };
  }

  public <ResponseType extends GeneratedMessageLite> void sendRequest(GeneratedMessageLite paramGeneratedMessageLite, Class<ResponseType> paramClass, ResponseHandler<ResponseType> paramResponseHandler, String paramString)
  {
    createProtobufRequestTask(paramGeneratedMessageLite, paramClass, getUrlForResponseClass(paramClass), paramString, paramResponseHandler).execute(this.threadPool, this.uiThreadHandler);
  }

  public <ResponseType extends GeneratedMessageLite> void sendRequest(GeneratedMessageLite paramGeneratedMessageLite, Class<ResponseType> paramClass, String paramString1, ResponseHandler<ResponseType> paramResponseHandler, String paramString2)
  {
    createProtobufRequestTask(paramGeneratedMessageLite, paramClass, getUrlForBasePath(paramString1), paramString2, paramResponseHandler).execute(this.threadPool, this.uiThreadHandler);
  }

  public void sendRequest(HttpUriRequest paramHttpUriRequest, FetchResponse.Handler paramHandler)
  {
    createFetchRequestTask(paramHttpUriRequest, paramHandler).execute(this.threadPool, this.uiThreadHandler);
  }

  public static class ConnectorException extends Exception
  {
    public ConnectorException(Exception paramException)
    {
      super();
    }
  }

  public static abstract class ResponseHandler<ResponseType extends GeneratedMessageLite>
  {
    public void onHeavyProcessing()
    {
    }

    public abstract void onNetworkError();

    public abstract void onResponse(UnveilResponse<ResponseType> paramUnveilResponse);

    public abstract void onServerErrorCode(int paramInt);

    public static abstract interface Factory<ResponseType extends GeneratedMessageLite>
    {
      public abstract AbstractConnector.ResponseHandler<ResponseType> newResponseHandler();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.AbstractConnector
 * JD-Core Version:    0.6.2
 */