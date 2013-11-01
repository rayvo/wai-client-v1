package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Clock;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.goggles.GogglesConfigProtos.GogglesConfig.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.GogglesReplayProtos.GogglesReplayRequest.Builder;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import com.google.protobuf.GeneratedMessageLite;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class QueryManager
{
  public static final int NO_STATUS_CODE = -1;
  private static final UnveilLogger logger = new UnveilLogger();
  private final AbstractConnector connector;
  private final List<GogglesResponsesListener> gogglesResponsesListeners;
  private final QueryManagerParams params;
  private final Provider<Boolean> searchHistorySettingProvider;
  private final TraceTracker traceTracker;
  private final Provider<Viewport> viewportProvider;

  public QueryManager(UnveilContext paramUnveilContext, QueryManagerParams paramQueryManagerParams, TracingCookieFetcher paramTracingCookieFetcher)
  {
    this(paramUnveilContext.getTraceTracker(), paramQueryManagerParams, Collections.singletonList(new GogglesResponsesListener()
    {
      public void onGogglesResponse(GogglesProtos.GogglesResponse paramAnonymousGogglesResponse)
      {
        QueryManager.this.getClickTracker().setTrackingId(IdExtractor.extractTrackingId(paramAnonymousGogglesResponse));
      }
    }), paramUnveilContext.getConnector(), new Provider()
    {
      public Viewport get()
      {
        return QueryManager.this.getViewport();
      }
    }
    , new Provider()
    {
      public Boolean get()
      {
        return Boolean.valueOf(QueryManager.this.isSearchHistoryEnabled());
      }
    });
  }

  public QueryManager(TraceTracker paramTraceTracker, QueryManagerParams paramQueryManagerParams, List<GogglesResponsesListener> paramList, AbstractConnector paramAbstractConnector, Provider<Viewport> paramProvider, Provider<Boolean> paramProvider1)
  {
    this.traceTracker = paramTraceTracker;
    this.params = paramQueryManagerParams;
    this.gogglesResponsesListeners = paramList;
    this.connector = paramAbstractConnector;
    this.viewportProvider = paramProvider;
    this.searchHistorySettingProvider = paramProvider1;
  }

  private GogglesProtos.GogglesRequest.Builder attachGogglesConfig(QueryBuilder paramQueryBuilder, GogglesProtos.GogglesRequest.Builder paramBuilder)
  {
    GogglesConfigProtos.GogglesConfig.Builder localBuilder = this.params.toGogglesConfig();
    if ((paramQueryBuilder.getQueryType().equals(QueryResponseFactory.QueryType.LOCAL_BARCODE)) || (paramQueryBuilder.getSource() == GogglesProtos.GogglesRequest.Source.PUGGLE));
    for (boolean bool = false; ; bool = ((Boolean)this.searchHistorySettingProvider.get()).booleanValue())
    {
      localBuilder.setCanStoreToSearchHistory(bool);
      if (paramQueryBuilder.getSource() == GogglesProtos.GogglesRequest.Source.PUGGLE)
        localBuilder.setMaxResults(100);
      if (paramQueryBuilder.getCanLogImage())
        localBuilder.setCanLogImage(true);
      paramBuilder.setGogglesConfig(localBuilder.build());
      return paramBuilder;
    }
  }

  private void handleNetworkError(QueryBuilder paramQueryBuilder, GogglesProtos.GogglesRequest.Builder paramBuilder, QueryListener paramQueryListener, int paramInt, boolean paramBoolean)
  {
    if (this.connector.isNetworkAvailable())
    {
      logger.e("Network error.", new Object[0]);
      if (paramInt > 0)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        localUnveilLogger.e("Retrying query. Retries remaining: %d", arrayOfObject);
      }
    }
    else
    {
      switch (6.$SwitchMap$com$google$android$apps$unveil$protocol$QueryResponseFactory$QueryType[paramQueryBuilder.getQueryType().ordinal()])
      {
      default:
        sendQueryRecursive(paramQueryBuilder, paramBuilder, paramQueryListener, paramBoolean, paramInt - 1);
        return;
        logger.e("Network is unavailable, no need to reset the session or automatically retry.", new Object[0]);
        paramQueryListener.onNetworkError(-1);
        return;
      case 1:
      }
      replayQueryRecursive(paramQueryBuilder, paramQueryListener, paramInt - 1);
      return;
    }
    paramQueryListener.onNetworkError(-1);
  }

  private void handleReplayNetworkError(QueryBuilder paramQueryBuilder, QueryListener paramQueryListener, int paramInt)
  {
    handleNetworkError(paramQueryBuilder, null, paramQueryListener, paramInt, false);
  }

  private void replayQueryRecursive(final QueryBuilder paramQueryBuilder, final QueryListener paramQueryListener, final int paramInt)
  {
    GogglesConfigProtos.GogglesConfig.Builder localBuilder = this.params.toGogglesConfig();
    localBuilder.setCanStoreToSearchHistory(false);
    GogglesReplayProtos.GogglesReplayRequest.Builder localBuilder1 = paramQueryBuilder.buildReplayRequestBuilder();
    localBuilder1.setGogglesConfig(localBuilder);
    AbstractConnector.ResponseHandler local5 = new AbstractConnector.ResponseHandler()
    {
      public void onNetworkError()
      {
        QueryManager.this.handleReplayNetworkError(paramQueryBuilder, paramQueryListener, paramInt);
      }

      public void onResponse(UnveilResponse<GogglesReplayProtos.GogglesReplayResponse> paramAnonymousUnveilResponse)
      {
        GogglesReplayProtos.GogglesReplayResponse localGogglesReplayResponse = (GogglesReplayProtos.GogglesReplayResponse)paramAnonymousUnveilResponse.getProtocolBuffer();
        String str = IdExtractor.extractMomentId(localGogglesReplayResponse.getGogglesResponse());
        QueryManager.this.notifyAllResponsesListeners(localGogglesReplayResponse.getGogglesResponse());
        paramQueryListener.onQueryResponse(QueryResponseFactory.interpretReplayResponse(localGogglesReplayResponse, paramQueryBuilder.getQueryType(), paramQueryBuilder.getSource(), str, (Viewport)QueryManager.this.viewportProvider.get(), paramAnonymousUnveilResponse.getResponseReceivedTimestamp()));
      }

      public void onServerErrorCode(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 403)
        {
          paramQueryListener.onAuthenticationError();
          return;
        }
        if (paramInt > 0)
        {
          QueryManager.this.replayQueryRecursive(paramQueryBuilder, paramQueryListener, -1 + paramInt);
          return;
        }
        paramQueryListener.onNetworkError(paramAnonymousInt);
      }
    };
    this.connector.sendRequest(localBuilder1.build(), GogglesReplayProtos.GogglesReplayResponse.class, local5, this.traceTracker.getTracingCookieForCurrentAction());
  }

  private int sendQueryRecursive(final QueryBuilder paramQueryBuilder, GogglesProtos.GogglesRequest.Builder paramBuilder, final QueryListener paramQueryListener, final boolean paramBoolean, final int paramInt)
  {
    final int i = this.traceTracker.getCurrentActionNumber();
    final GogglesProtos.GogglesRequest localGogglesRequest = paramBuilder.build();
    AbstractConnector.ResponseHandler local4 = new AbstractConnector.ResponseHandler()
    {
      public void onHeavyProcessing()
      {
        super.onHeavyProcessing();
        paramQueryListener.onHeavyProcessingStart();
      }

      public void onNetworkError()
      {
        QueryManager.this.handleNetworkError(paramQueryBuilder, paramInt, paramQueryListener, paramBoolean, this.val$ignoreResults);
      }

      public void onResponse(UnveilResponse<GogglesProtos.GogglesResponse> paramAnonymousUnveilResponse)
      {
        QueryManager.logger.v("ResponseHandler.onResponse", new Object[0]);
        QueryManager.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.REQUEST_TO_RESPONSE, i);
        QueryManager.logger.v("Notifying listener of heavyProcessingEnd.", new Object[0]);
        paramQueryListener.onHeavyProcessingEnd();
        GogglesProtos.GogglesResponse localGogglesResponse = (GogglesProtos.GogglesResponse)paramAnonymousUnveilResponse.getProtocolBuffer();
        String str1 = IdExtractor.extractMomentId(localGogglesResponse);
        String str2 = IdExtractor.extractTrackingId(localGogglesResponse);
        QueryManager.this.traceTracker.setTrackingId(str2, i);
        QueryManager.this.notifyAllResponsesListeners(localGogglesResponse);
        UnveilLogger localUnveilLogger = QueryManager.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramAnonymousUnveilResponse.getResponseSize());
        localUnveilLogger.d("Response size: %d", arrayOfObject);
        SingleShotQueryResponse localSingleShotQueryResponse = QueryResponseFactory.interpretResponse(localGogglesResponse, paramQueryBuilder.getQueryType(), paramQueryBuilder.getSource(), str1, paramAnonymousUnveilResponse.getResponseReceivedTimestamp(), new SingleShotQueryResponse.RequestMetrics(paramQueryBuilder.imageSize, localGogglesRequest.getSerializedSize(), paramAnonymousUnveilResponse.getResponseReceivedTimestamp() - this.val$requestSentTimestamp), str2);
        QueryManager.logger.v("Notifying listener of QueryResponse: %s", new Object[] { localSingleShotQueryResponse });
        paramQueryListener.onQueryResponse(localSingleShotQueryResponse);
      }

      public void onServerErrorCode(int paramAnonymousInt)
      {
        if (paramAnonymousInt == 403)
        {
          paramQueryListener.onAuthenticationError();
          return;
        }
        UnveilLogger localUnveilLogger1 = QueryManager.logger;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paramAnonymousInt);
        localUnveilLogger1.e("Query send failed due to server error %d", arrayOfObject1);
        if (paramBoolean > 0)
        {
          UnveilLogger localUnveilLogger2 = QueryManager.logger;
          Object[] arrayOfObject2 = new Object[1];
          arrayOfObject2[0] = Integer.valueOf(paramBoolean);
          localUnveilLogger2.e("Retrying query. Retries remaining: %d", arrayOfObject2);
          QueryManager.this.sendQueryRecursive(paramQueryBuilder, paramInt, paramQueryListener, this.val$ignoreResults, -1 + paramBoolean);
          return;
        }
        QueryManager.logger.e("No retries left, this is now a network error.", new Object[0]);
        paramQueryListener.onNetworkError(paramAnonymousInt);
      }
    };
    String str = this.traceTracker.getTracingCookieForCurrentAction();
    if (paramBoolean)
    {
      this.traceTracker.addPoint(TracingProtos.PointVariable.Type.HISTORY);
      GogglesProtos.GogglesRequest.Source localSource = paramQueryBuilder.getSource();
      if ((localSource != GogglesProtos.GogglesRequest.Source.FELIX_TEXT) && (localSource != GogglesProtos.GogglesRequest.Source.TRANSLATE))
        break label136;
      this.connector.sendRequest(localGogglesRequest, GogglesProtos.GogglesResponse.class, "/goggles/a/single_shot_text_mode", local4, str);
    }
    while (true)
    {
      this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.REQUEST_TO_RESPONSE);
      return paramQueryBuilder.getSequenceNumber();
      this.traceTracker.addPoint(TracingProtos.PointVariable.Type.PUSH);
      break;
      label136: this.connector.sendRequest(localGogglesRequest, GogglesProtos.GogglesResponse.class, local4, str);
    }
  }

  protected void notifyAllResponsesListeners(GogglesProtos.GogglesResponse paramGogglesResponse)
  {
    Iterator localIterator = this.gogglesResponsesListeners.iterator();
    while (localIterator.hasNext())
      ((GogglesResponsesListener)localIterator.next()).onGogglesResponse(paramGogglesResponse);
  }

  public void replayQuery(QueryBuilder paramQueryBuilder, QueryListener paramQueryListener)
  {
    paramQueryBuilder.setAsReplayType();
    replayQueryRecursive(paramQueryBuilder, paramQueryListener, this.params.maxRetries);
  }

  public int sendQuery(UnveilContext paramUnveilContext, QueryBuilder paramQueryBuilder, QueryListener paramQueryListener, boolean paramBoolean)
  {
    return sendQuery(paramQueryBuilder, paramQueryBuilder.buildGogglesRequestBuilder(paramUnveilContext), paramQueryListener, paramBoolean);
  }

  public int sendQuery(QueryBuilder paramQueryBuilder, GogglesProtos.GogglesRequest.Builder paramBuilder, QueryListener paramQueryListener, boolean paramBoolean)
  {
    return sendQueryRecursive(paramQueryBuilder, attachGogglesConfig(paramQueryBuilder, paramBuilder), paramQueryListener, paramBoolean, this.params.maxRetries);
  }

  public static abstract interface GogglesResponsesListener
  {
    public abstract void onGogglesResponse(GogglesProtos.GogglesResponse paramGogglesResponse);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryManager
 * JD-Core Version:    0.6.2
 */