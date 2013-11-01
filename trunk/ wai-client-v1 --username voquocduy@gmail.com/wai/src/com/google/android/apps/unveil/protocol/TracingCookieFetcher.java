package com.google.android.apps.unveil.protocol;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory.HeaderProvider;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.goggles.TracingCookieProtos.TracingCookieRequest;
import com.google.goggles.TracingCookieProtos.TracingCookieRequest.Builder;
import com.google.goggles.TracingCookieProtos.TracingCookieResponse;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.DiscardPolicy;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpRequest;

public class TracingCookieFetcher
{
  private static final int MAX_COOKIES_PER_REQUEST = 30;
  private static final int REPLENISH_THRESHOLD_COOKIES = 9;
  private static final Class<TracingCookieProtos.TracingCookieResponse> RESPONSE_CLASS = TracingCookieProtos.TracingCookieResponse.class;
  private static final UnveilLogger logger = new UnveilLogger();
  private final AbstractConnector connector;
  private final Queue<String> cookies = new LinkedList();
  private final ExecutorService executor;
  private final int replenishThreshold;
  private final TracingCookieProtos.TracingCookieRequest request;
  private final AbstractConnector.ResponseHandler<TracingCookieProtos.TracingCookieResponse> responseHandler;

  public TracingCookieFetcher(AbstractConnector paramAbstractConnector)
  {
    this.connector = paramAbstractConnector;
    this.replenishThreshold = 9;
    this.request = makeRequest(30);
    this.responseHandler = makeHandler();
    this.executor = createExecutor();
  }

  private ExecutorService createExecutor()
  {
    return new ThreadPoolExecutor(1, 1, 5L, TimeUnit.SECONDS, new ArrayBlockingQueue(1), new ThreadPoolExecutor.DiscardPolicy());
  }

  private AbstractConnector.ResponseHandler<TracingCookieProtos.TracingCookieResponse> makeHandler()
  {
    return new AbstractConnector.ResponseHandler()
    {
      public void onNetworkError()
      {
        TracingCookieFetcher.logger.e("TracingCookieRequest network error", new Object[0]);
      }

      public void onResponse(UnveilResponse<TracingCookieProtos.TracingCookieResponse> paramAnonymousUnveilResponse)
      {
        TracingCookieProtos.TracingCookieResponse localTracingCookieResponse = (TracingCookieProtos.TracingCookieResponse)paramAnonymousUnveilResponse.getProtocolBuffer();
        TracingCookieFetcher.this.addAll(localTracingCookieResponse.getTracingCookiesList());
      }

      public void onServerErrorCode(int paramAnonymousInt)
      {
        UnveilLogger localUnveilLogger = TracingCookieFetcher.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(paramAnonymousInt);
        localUnveilLogger.e("TracingCookieRequest server error: %d", arrayOfObject);
      }
    };
  }

  private static TracingCookieProtos.TracingCookieRequest makeRequest(int paramInt)
  {
    TracingCookieProtos.TracingCookieRequest.Builder localBuilder = TracingCookieProtos.TracingCookieRequest.newBuilder();
    localBuilder.setMaxCookies(paramInt);
    return localBuilder.build();
  }

  protected void addAll(List<String> paramList)
  {
    logger.v("Got tracing cookies: " + paramList, new Object[0]);
    this.cookies.addAll(paramList);
  }

  public String getFreshCookie()
  {
    String str = (String)this.cookies.poll();
    if (this.cookies.size() < this.replenishThreshold)
      replenish();
    if (str == null)
      logger.w("Failed to provide a fresh tracing cookie.", new Object[0]);
    return str;
  }

  public DefaultHttpRequestFactory.HeaderProvider getHeaderProvider()
  {
    return new TracingCookieHeaderProvider(this);
  }

  public void replenish()
  {
    this.executor.execute(this.connector.requestRunnable(this.request, RESPONSE_CLASS, "", this.responseHandler));
  }

  private static final class TracingCookieHeaderProvider
    implements DefaultHttpRequestFactory.HeaderProvider
  {
    private final TracingCookieFetcher cookieFetcher;

    public TracingCookieHeaderProvider(TracingCookieFetcher paramTracingCookieFetcher)
    {
      this.cookieFetcher = paramTracingCookieFetcher;
    }

    public void attachHeader(HttpRequest paramHttpRequest)
    {
      String str = this.cookieFetcher.getFreshCookie();
      if (!TextUtils.isEmpty(str))
        paramHttpRequest.addHeader("Cookie", str);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.TracingCookieFetcher
 * JD-Core Version:    0.6.2
 */