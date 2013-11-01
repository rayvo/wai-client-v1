package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler;
import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler.Factory;
import com.google.android.apps.unveil.network.ContinuousConnector;
import com.google.android.apps.unveil.network.ContinuousConnector.PushLogger;
import com.google.android.apps.unveil.network.ContinuousNetworkParams;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.OtherThreadResponseHandler;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.goggles.GogglesProtos.GogglesRequest;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.TracingProtos.TraceRequest;
import com.google.goggles.TracingProtos.TraceResponse;
import com.google.protobuf.GeneratedMessageLite;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import org.apache.http.HttpHost;

public class ContinuousConnectionManager
{
  public static final int NO_STATUS_CODE = -1;
  private static final String REQUEST_URL_TEMPLATE = "/goggles/a/nonstop_goggles_proto?cssid=%s";
  private static final UnveilLogger logger = new UnveilLogger();
  private final UnveilContext application;
  private final Object disconnectLock = new Object();
  private boolean disconnected;
  private final TracingCookieFetcher fetcher;
  private final Executor mainThread;
  private final QueryListener pullListener;
  private final ContinuousConnector queryConnector;
  private final String requestUrl;
  private final Session session;

  public ContinuousConnectionManager(UnveilContext paramUnveilContext, QueryListener paramQueryListener, TracingCookieFetcher paramTracingCookieFetcher, Session paramSession, ContinuousConnector.PushLogger paramPushLogger, Executor paramExecutor, ContinuousNetworkParams paramContinuousNetworkParams)
  {
    this.application = paramUnveilContext;
    this.fetcher = paramTracingCookieFetcher;
    this.session = paramSession;
    this.pullListener = paramQueryListener;
    this.mainThread = paramExecutor;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramSession.getSessionId();
    this.requestUrl = String.format("/goggles/a/nonstop_goggles_proto?cssid=%s", arrayOfObject);
    this.queryConnector = ContinuousConnector.makeDefault(paramUnveilContext, new HttpHost(paramUnveilContext.getFrontendUrl().getHost()), getSessionId(), new ContinuousPullResponseHandlerFactory(null), createPullRequest(), paramPushLogger, createRequestFactory(), this.requestUrl);
  }

  private byte[] createPullRequest()
  {
    return new ContinuousQueryBuilder().setWantResults(true).setSession(this.session).buildGogglesRequestBuilder(this.application).build().toByteArray();
  }

  private DefaultHttpRequestFactory createRequestFactory()
  {
    ArrayList localArrayList = new ArrayList(2);
    localArrayList.add(this.fetcher.getHeaderProvider());
    AuthState localAuthState = this.application.getAuthState();
    if (localAuthState.isAuthenticated(AuthToken.AuthTokenType.SID))
      localArrayList.add(DefaultHttpRequestFactory.newStaticAuthenticationProvider(localAuthState.getAuthToken(AuthToken.AuthTokenType.SID)));
    localArrayList.add(DefaultHttpRequestFactory.newInstallationIdProvider(this.application));
    return DefaultHttpRequestFactory.newAnonymousRequestFactory(this.application, localArrayList);
  }

  private AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> rerouteToMainThread(AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramResponseHandler)
  {
    return new OtherThreadResponseHandler(this.mainThread, paramResponseHandler);
  }

  public boolean canPush()
  {
    return this.queryConnector.canSerialPush();
  }

  public void disconnect()
  {
    try
    {
      synchronized (this.disconnectLock)
      {
        if (this.disconnected)
          logger.e("disconnect() called multiple times!", new Object[0]);
        this.disconnected = true;
        if (this.queryConnector != null)
          this.queryConnector.disconnect();
        return;
      }
    }
    finally
    {
    }
  }

  public ContinuousConnector getConnector()
  {
    return this.queryConnector;
  }

  public Session getSession()
  {
    return this.session;
  }

  public String getSessionId()
  {
    return this.session.getSessionId();
  }

  public boolean maybeSendTraceData(QueryListener paramQueryListener, TracingProtos.TraceRequest paramTraceRequest)
  {
    try
    {
      if (this.disconnected)
        logger.e("maybeSendTraceData() called after disconnect()!", new Object[0]);
      TraceResponseHandler localTraceResponseHandler = new TraceResponseHandler(paramQueryListener, null);
      byte[] arrayOfByte = paramTraceRequest.toByteArray();
      int i = arrayOfByte.length;
      boolean bool = false;
      if (i == 0);
      while (true)
      {
        return bool;
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramTraceRequest.getTraceActionCount());
        arrayOfObject[1] = Integer.valueOf(arrayOfByte.length);
        localUnveilLogger.v("Logging %d trace requests builtRequest size %d", arrayOfObject);
        this.application.getConnector().sendRequest(paramTraceRequest, TracingProtos.TraceResponse.class, localTraceResponseHandler, "");
        bool = true;
      }
    }
    finally
    {
    }
  }

  public AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> newPushHandler(QueryListener paramQueryListener)
  {
    return rerouteToMainThread(new PushResponseHandler(paramQueryListener, null));
  }

  public void pause()
  {
    try
    {
      if (this.disconnected)
        logger.e("pause() called after disconnect()!", new Object[0]);
      this.queryConnector.stop();
      return;
    }
    finally
    {
    }
  }

  private class ContinuousPullResponseHandlerFactory
    implements AbstractConnector.ResponseHandler.Factory<GogglesProtos.GogglesResponse>
  {
    private ContinuousPullResponseHandlerFactory()
    {
    }

    public AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> newResponseHandler()
    {
      return ContinuousConnectionManager.this.rerouteToMainThread(new ContinuousConnectionManager.PullResponseHandler(ContinuousConnectionManager.this, null));
    }
  }

  private abstract class ContinuousResponseHandler<ResponseType extends GeneratedMessageLite> extends AbstractConnector.ResponseHandler<ResponseType>
  {
    protected final QueryListener queryListener;

    private ContinuousResponseHandler(QueryListener arg2)
    {
      Object localObject;
      this.queryListener = localObject;
    }

    public void onNetworkError()
    {
      ContinuousConnectionManager.logger.e("Request network error!", new Object[0]);
      synchronized (ContinuousConnectionManager.this.disconnectLock)
      {
        if (ContinuousConnectionManager.this.disconnected)
        {
          ContinuousConnectionManager.logger.w("Disconnected, aborting callback.", new Object[0]);
          return;
        }
        this.queryListener.onNetworkError(-1);
        return;
      }
    }

    public void onServerErrorCode(int paramInt)
    {
      UnveilLogger localUnveilLogger = ContinuousConnectionManager.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.e("Request error code! %d", arrayOfObject);
      synchronized (ContinuousConnectionManager.this.disconnectLock)
      {
        if (ContinuousConnectionManager.this.disconnected)
        {
          ContinuousConnectionManager.logger.w("Disconnected, aborting callback.", new Object[0]);
          return;
        }
        this.queryListener.onNetworkError(paramInt);
        return;
      }
    }
  }

  private class PullResponseHandler extends ContinuousConnectionManager.ContinuousResponseHandler<GogglesProtos.GogglesResponse>
  {
    private PullResponseHandler()
    {
      super(ContinuousConnectionManager.this.pullListener, null);
    }

    public void onResponse(UnveilResponse<GogglesProtos.GogglesResponse> paramUnveilResponse)
    {
      ContinuousConnectionManager.logger.v("Pull response.", new Object[0]);
      if (paramUnveilResponse == null)
      {
        ContinuousConnectionManager.logger.v("nothing in pull response, ignoring", new Object[0]);
        return;
      }
      GogglesProtos.GogglesResponse localGogglesResponse = (GogglesProtos.GogglesResponse)paramUnveilResponse.getProtocolBuffer();
      UnveilLogger localUnveilLogger1 = ContinuousConnectionManager.logger;
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(paramUnveilResponse.getResponseSize());
      localUnveilLogger1.v("Response size: %d", arrayOfObject1);
      UnveilLogger localUnveilLogger2 = ContinuousConnectionManager.logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(localGogglesResponse.getResultsCount());
      localUnveilLogger2.v("result count: %d", arrayOfObject2);
      ContinuousConnectionManager.this.application.getClickTracker().setTrackingId(IdExtractor.extractTrackingId(localGogglesResponse));
      QueryResponse localQueryResponse = QueryResponseFactory.interpretPullResponse(localGogglesResponse, paramUnveilResponse.getResponseReceivedTimestamp());
      synchronized (ContinuousConnectionManager.this.disconnectLock)
      {
        if (ContinuousConnectionManager.this.disconnected)
        {
          ContinuousConnectionManager.logger.w("Disconnected, aborting callback.", new Object[0]);
          return;
        }
      }
      this.queryListener.onQueryResponse(localQueryResponse);
    }
  }

  private class PushResponseHandler extends ContinuousConnectionManager.ContinuousResponseHandler<GogglesProtos.GogglesResponse>
  {
    private PushResponseHandler(QueryListener arg2)
    {
      super(localQueryListener, null);
    }

    public void onResponse(UnveilResponse<GogglesProtos.GogglesResponse> paramUnveilResponse)
    {
      ContinuousConnectionManager.logger.v("Push response.", new Object[0]);
      synchronized (ContinuousConnectionManager.this.disconnectLock)
      {
        if (ContinuousConnectionManager.this.disconnected)
        {
          ContinuousConnectionManager.logger.w("Disconnected, aborting callback.", new Object[0]);
          return;
        }
        this.queryListener.onQueryResponse(null);
        return;
      }
    }
  }

  private class TraceResponseHandler extends ContinuousConnectionManager.ContinuousResponseHandler<TracingProtos.TraceResponse>
  {
    private TraceResponseHandler(QueryListener arg2)
    {
      super(localQueryListener, null);
    }

    public void onResponse(UnveilResponse<TracingProtos.TraceResponse> paramUnveilResponse)
    {
      ContinuousConnectionManager.logger.v("Trace response.", new Object[0]);
      synchronized (ContinuousConnectionManager.this.disconnectLock)
      {
        if (ContinuousConnectionManager.this.disconnected)
        {
          ContinuousConnectionManager.logger.w("Disconnected, aborting callback.", new Object[0]);
          return;
        }
        this.queryListener.onQueryResponse(null);
        return;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.ContinuousConnectionManager
 * JD-Core Version:    0.6.2
 */