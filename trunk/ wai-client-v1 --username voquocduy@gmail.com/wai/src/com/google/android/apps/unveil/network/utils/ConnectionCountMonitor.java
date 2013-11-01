package com.google.android.apps.unveil.network.utils;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.HttpClientFactory.MonitoringTSCCManager;
import org.apache.http.HttpHost;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.conn.tsccm.RouteSpecificPool;
import org.apache.http.params.HttpParams;

public class ConnectionCountMonitor
{
  private static final UnveilLogger logger = new UnveilLogger(ConnectionCountMonitor.class.getSimpleName(), null);

  public static void checkConnectionCounts(HttpClientFactory.MonitoringTSCCManager paramMonitoringTSCCManager, RouteSpecificPool paramRouteSpecificPool, HttpParams paramHttpParams)
  {
    checkRouteConnectionCounts(paramRouteSpecificPool);
    checkTotalConnectionCount(paramMonitoringTSCCManager, ConnManagerParams.getMaxTotalConnections(paramHttpParams));
  }

  private static void checkRouteConnectionCounts(RouteSpecificPool paramRouteSpecificPool)
  {
    int i = paramRouteSpecificPool.getEntryCount();
    int j = paramRouteSpecificPool.getMaxEntries();
    String str = paramRouteSpecificPool.getRoute().getTargetHost().getHostName();
    if (i == j)
    {
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = str;
      arrayOfObject2[1] = Integer.valueOf(i);
      localUnveilLogger2.e("Connections to %s exhausted: per route max=%d", arrayOfObject2);
      return;
    }
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = str;
    arrayOfObject1[1] = Integer.valueOf(i);
    arrayOfObject1[2] = Integer.valueOf(j);
    localUnveilLogger1.v("Connections to %s: %d/%d", arrayOfObject1);
  }

  private static void checkTotalConnectionCount(HttpClientFactory.MonitoringTSCCManager paramMonitoringTSCCManager, int paramInt)
  {
    int i = paramMonitoringTSCCManager.getConnectionsInPool();
    if (i == paramInt)
    {
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(i);
      localUnveilLogger2.e("Connections exhausted: max=%d", arrayOfObject2);
      return;
    }
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(i);
    arrayOfObject1[1] = Integer.valueOf(paramInt);
    localUnveilLogger1.v("Total connections: %d/%d", arrayOfObject1);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.utils.ConnectionCountMonitor
 * JD-Core Version:    0.6.2
 */