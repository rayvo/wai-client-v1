package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.goggles.TracingProtos.TraceRequest;
import com.google.goggles.TracingProtos.TraceRequest.Builder;
import com.google.goggles.TracingProtos.TraceResponse;

public final class SessionlessRequests
{
  private static final UnveilLogger logger = new UnveilLogger();

  private static void maybeSendTraces(UnveilContext paramUnveilContext)
  {
    if (!paramUnveilContext.getTraceTracker().hasPendingActions())
      return;
    TracingProtos.TraceRequest.Builder localBuilder = TracingProtos.TraceRequest.newBuilder();
    paramUnveilContext.getTraceTracker().populateRequest(localBuilder);
    sendTraceRequest(paramUnveilContext, localBuilder.build(), new AbstractConnector.ResponseHandler()
    {
      public void onNetworkError()
      {
        SessionlessRequests.logger.e("Failed to send logs.", new Object[0]);
      }

      public void onResponse(UnveilResponse<TracingProtos.TraceResponse> paramAnonymousUnveilResponse)
      {
        SessionlessRequests.logger.v("Successfully sent logs.", new Object[0]);
      }

      public void onServerErrorCode(int paramAnonymousInt)
      {
        UnveilLogger localUnveilLogger = SessionlessRequests.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramAnonymousInt);
        localUnveilLogger.e("failed to send logs with status code %d", arrayOfObject);
      }
    });
  }

  public static void sendLogs(UnveilContext paramUnveilContext)
  {
    maybeSendTraces(paramUnveilContext);
  }

  private static void sendTraceRequest(UnveilContext paramUnveilContext, TracingProtos.TraceRequest paramTraceRequest, AbstractConnector.ResponseHandler<TracingProtos.TraceResponse> paramResponseHandler)
  {
    paramUnveilContext.getConnector().sendRequest(paramTraceRequest, TracingProtos.TraceResponse.class, paramResponseHandler, "");
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.SessionlessRequests
 * JD-Core Version:    0.6.2
 */