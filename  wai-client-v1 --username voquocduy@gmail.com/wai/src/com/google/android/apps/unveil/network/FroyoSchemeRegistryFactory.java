package com.google.android.apps.unveil.network;

import android.content.Context;
import android.net.SSLCertificateSocketFactory;
import android.net.SSLSessionCache;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;

public class FroyoSchemeRegistryFactory
{
  static SchemeRegistry get(Context paramContext)
  {
    try
    {
      localSSLSessionCache = new SSLSessionCache(paramContext);
      SchemeRegistry localSchemeRegistry = new SchemeRegistry();
      localSchemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
      localSchemeRegistry.register(new Scheme("https", SSLCertificateSocketFactory.getHttpSocketFactory(30000, localSSLSessionCache), 443));
      return localSchemeRegistry;
    }
    catch (NullPointerException localNullPointerException)
    {
      while (true)
        SSLSessionCache localSSLSessionCache = null;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.FroyoSchemeRegistryFactory
 * JD-Core Version:    0.6.2
 */