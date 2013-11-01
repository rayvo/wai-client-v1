package com.google.android.apps.unveil.network;

import android.content.Context;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.utils.ConnectionCountMonitor;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.BasicPoolEntry;
import org.apache.http.impl.conn.tsccm.ConnPoolByRoute;
import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpClientFactory
{
  protected static final int MAX_CONNECTIONS_PER_ROUTE = 0;
  private static final int MAX_TOTAL_CONNECTIONS = 30;
  static final boolean MONITOR_CONNECTIONS = false;
  public static final int SSL_HANDSHAKE_TIMEOUT_MS = 30000;
  private static final UnveilLogger logger = new UnveilLogger();

  private static int getMaxConnectionsPerRoute()
  {
    return 2 + (2 + (3 + (0 + 2)));
  }

  public static HttpClient make(Context paramContext)
  {
    HttpParams localHttpParams = makeHttpParams();
    return new DefaultHttpClient(makeClientConnectionManager(paramContext, localHttpParams), localHttpParams);
  }

  private static ThreadSafeClientConnManager makeClientConnectionManager(Context paramContext, HttpParams paramHttpParams)
  {
    return new ThreadSafeClientConnManager(paramHttpParams, FroyoSchemeRegistryFactory.get(paramContext));
  }

  private static HttpParams makeHttpParams()
  {
    BasicHttpParams localBasicHttpParams = new BasicHttpParams();
    HttpProtocolParams.setVersion(localBasicHttpParams, HttpVersion.HTTP_1_1);
    HttpProtocolParams.setContentCharset(localBasicHttpParams, "ISO-8859-1");
    HttpProtocolParams.setUseExpectContinue(localBasicHttpParams, false);
    HttpConnectionParams.setConnectionTimeout(localBasicHttpParams, 20000);
    HttpConnectionParams.setSoTimeout(localBasicHttpParams, 30000);
    HttpConnectionParams.setSocketBufferSize(localBasicHttpParams, 8192);
    ConnManagerParams.setMaxTotalConnections(localBasicHttpParams, 30);
    ConnManagerParams.setMaxConnectionsPerRoute(localBasicHttpParams, new ConnPerRoute()
    {
      public int getMaxForRoute(HttpRoute paramAnonymousHttpRoute)
      {
        return HttpClientFactory.MAX_CONNECTIONS_PER_ROUTE;
      }
    });
    return localBasicHttpParams;
  }

  public static final class MonitoringConnPool extends ConnPoolByRoute
  {
    private final HttpClientFactory.MonitoringTSCCManager connMan;
    private final HttpParams params;

    public MonitoringConnPool(ClientConnectionOperator paramClientConnectionOperator, HttpParams paramHttpParams, HttpClientFactory.MonitoringTSCCManager paramMonitoringTSCCManager)
    {
      super(paramHttpParams);
      this.params = paramHttpParams;
      this.connMan = paramMonitoringTSCCManager;
    }

    protected BasicPoolEntry createEntry(RouteSpecificPool paramRouteSpecificPool, ClientConnectionOperator paramClientConnectionOperator)
    {
      BasicPoolEntry localBasicPoolEntry = super.createEntry(paramRouteSpecificPool, paramClientConnectionOperator);
      ConnectionCountMonitor.checkConnectionCounts(this.connMan, paramRouteSpecificPool, this.params);
      return localBasicPoolEntry;
    }
  }

  public static final class MonitoringTSCCManager extends ThreadSafeClientConnManager
  {
    public MonitoringTSCCManager(HttpParams paramHttpParams, SchemeRegistry paramSchemeRegistry)
    {
      super(paramSchemeRegistry);
    }

    protected HttpClientFactory.MonitoringConnPool createConnectionPool(HttpParams paramHttpParams)
    {
      return new HttpClientFactory.MonitoringConnPool(this.connOperator, paramHttpParams, this);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.HttpClientFactory
 * JD-Core Version:    0.6.2
 */