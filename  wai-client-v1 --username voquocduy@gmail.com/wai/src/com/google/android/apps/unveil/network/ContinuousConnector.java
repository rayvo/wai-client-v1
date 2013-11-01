package com.google.android.apps.unveil.network;

import android.content.Context;
import android.net.ConnectivityManager;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Providers;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.utils.ResponseContentTransferrer;
import com.google.android.apps.unveil.network.utils.Stats;
import com.google.android.apps.unveil.network.utils.Stats.Tags;
import com.google.goggles.GogglesProtos.GogglesResponse;
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;

public class ContinuousConnector
{
  private static final String REQUEST_URL_TEMPLATE = "/goggles/a/%s?cssid=%s";
  static final int SIMULTANEOUS_PULLS = 2;
  private static final boolean USE_HTTP_URL_CONNECTION;
  private static final UnveilLogger logger = new UnveilLogger();
  private final ResponseContentTransferrer contentTransferrer = new ResponseContentTransferrer();
  private final HttpHost host;
  private final DefaultHttpRequestFactory httpRequestFactory;
  private boolean isDisconnected;
  private final Network network;
  private final byte[] pullRequestEntity;
  private final AbstractConnector.ResponseHandler.Factory<GogglesProtos.GogglesResponse> pullResponseHandlerFactory;
  private final List<PullingRunnable> pullRunnables = new ArrayList(2);
  private boolean pulling;
  private final PushLogger pushLogger;
  private boolean pushOutstanding = false;
  private final String requestUrl;
  private final Executor threadPool;

  ContinuousConnector(Network paramNetwork, HttpHost paramHttpHost, String paramString1, AbstractConnector.ResponseHandler.Factory<GogglesProtos.GogglesResponse> paramFactory, byte[] paramArrayOfByte, PushLogger paramPushLogger, Executor paramExecutor, DefaultHttpRequestFactory paramDefaultHttpRequestFactory, String paramString2)
  {
    this.pullRequestEntity = paramArrayOfByte;
    this.pullResponseHandlerFactory = paramFactory;
    this.host = paramHttpHost;
    this.network = paramNetwork;
    this.pushLogger = paramPushLogger;
    this.threadPool = paramExecutor;
    this.httpRequestFactory = paramDefaultHttpRequestFactory;
    this.requestUrl = paramString2;
  }

  static Network connectorNetwork(HttpUrlConnectionConnector paramHttpUrlConnectionConnector)
  {
    return new Network()
    {
      public HttpResponse execute(HttpHost paramAnonymousHttpHost, HttpRequest paramAnonymousHttpRequest)
        throws ClientProtocolException, IOException
      {
        if ((paramAnonymousHttpRequest instanceof HttpRequestBase))
        {
          HttpRequestBase localHttpRequestBase = (HttpRequestBase)paramAnonymousHttpRequest;
          localHttpRequestBase.setURI(URI.create(paramAnonymousHttpHost.toURI().concat(localHttpRequestBase.getURI().toString())));
          return this.val$connector.execute(localHttpRequestBase);
        }
        throw new AssertionError();
      }
    };
  }

  private boolean doPush(byte[] paramArrayOfByte, AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramResponseHandler, int paramInt, boolean paramBoolean)
  {
    try
    {
      if (this.isDisconnected)
      {
        logger.i("Asked to push(), but we are disconnected.", new Object[0]);
        return false;
      }
      if (paramBoolean)
        this.pushOutstanding = true;
      this.threadPool.execute(new PushRunnable(paramArrayOfByte, paramResponseHandler, paramInt, paramBoolean));
      startPullingIfNotPulling();
      return true;
    }
    finally
    {
    }
  }

  static Network httpClientNetwork(HttpClient paramHttpClient)
  {
    return new Network()
    {
      public HttpResponse execute(HttpHost paramAnonymousHttpHost, HttpRequest paramAnonymousHttpRequest)
        throws ClientProtocolException, IOException
      {
        return this.val$client.execute(paramAnonymousHttpHost, paramAnonymousHttpRequest);
      }
    };
  }

  private boolean isSerialPushOustanding()
  {
    return this.pushOutstanding;
  }

  public static ContinuousConnector makeDefault(UnveilContext paramUnveilContext, HttpHost paramHttpHost, String paramString1, AbstractConnector.ResponseHandler.Factory<GogglesProtos.GogglesResponse> paramFactory, byte[] paramArrayOfByte, PushLogger paramPushLogger, DefaultHttpRequestFactory paramDefaultHttpRequestFactory, String paramString2)
  {
    ExecutorService localExecutorService = Executors.newCachedThreadPool();
    new HttpUrlConnectionConnector((ConnectivityManager)((Context)paramUnveilContext).getSystemService("connectivity"), Providers.staticProvider(paramUnveilContext.getFrontendUrl()), paramDefaultHttpRequestFactory);
    return new ContinuousConnector(httpClientNetwork(paramUnveilContext.getHttpClient()), paramHttpHost, paramString1, paramFactory, paramArrayOfByte, paramPushLogger, localExecutorService, paramDefaultHttpRequestFactory, paramString2);
  }

  public boolean canSerialPush()
  {
    try
    {
      boolean bool1 = this.isDisconnected;
      boolean bool2 = false;
      if (bool1);
      while (true)
      {
        return bool2;
        boolean bool3 = isSerialPushOustanding();
        bool2 = false;
        if (!bool3)
          bool2 = true;
      }
    }
    finally
    {
    }
  }

  public boolean disconnect()
  {
    boolean bool = false;
    try
    {
      if (this.isDisconnected)
        logger.v("Fast return from disconnect() because we're already disconnected.", new Object[0]);
      while (true)
      {
        return bool;
        this.isDisconnected = true;
        stop();
        bool = true;
      }
    }
    finally
    {
    }
  }

  public boolean outOfBandPush(byte[] paramArrayOfByte, AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramResponseHandler, int paramInt)
  {
    return doPush(paramArrayOfByte, paramResponseHandler, paramInt, false);
  }

  public boolean serialPush(byte[] paramArrayOfByte, AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramResponseHandler, int paramInt)
  {
    return doPush(paramArrayOfByte, paramResponseHandler, paramInt, true);
  }

  public void startPullingIfNotPulling()
  {
    try
    {
      if ((!this.pulling) && (!this.isDisconnected))
      {
        this.pulling = true;
        for (int i = 0; i < 2; i++)
        {
          PullingRunnable localPullingRunnable = new PullingRunnable(null);
          this.pullRunnables.add(localPullingRunnable);
          this.threadPool.execute(localPullingRunnable);
        }
      }
      return;
    }
    finally
    {
    }
  }

  public void stop()
  {
    try
    {
      logger.v("stop()", new Object[0]);
      if (!this.pulling)
        break label74;
      this.pulling = false;
      Iterator localIterator = this.pullRunnables.iterator();
      while (localIterator.hasNext())
        ((PullingRunnable)localIterator.next()).stop();
    }
    finally
    {
    }
    this.pullRunnables.clear();
    label74:
  }

  private static abstract interface Network
  {
    public abstract HttpResponse execute(HttpHost paramHttpHost, HttpRequest paramHttpRequest)
      throws ClientProtocolException, IOException;
  }

  private class PullingRunnable extends ContinuousConnector.RequestRunnable
  {
    private final AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> responseHandler = ContinuousConnector.this.pullResponseHandlerFactory.newResponseHandler();
    private boolean stopped;

    private PullingRunnable()
    {
      super(null);
    }

    // ERROR //
    public void run()
    {
      // Byte code:
      //   0: getstatic 44	com/google/android/apps/unveil/network/utils/Stats$Tags:CONTINUOUS_PULL	Lcom/google/android/apps/unveil/network/utils/Stats$Tags;
      //   3: invokestatic 50	com/google/android/apps/unveil/network/utils/Stats:setThreadTag	(Lcom/google/android/apps/unveil/network/utils/Stats$Tags;)V
      //   6: aload_0
      //   7: aload_0
      //   8: getfield 15	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:this$0	Lcom/google/android/apps/unveil/network/ContinuousConnector;
      //   11: invokestatic 54	com/google/android/apps/unveil/network/ContinuousConnector:access$1000	(Lcom/google/android/apps/unveil/network/ContinuousConnector;)[B
      //   14: aload_0
      //   15: getfield 32	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:responseHandler	Lcom/google/android/apps/unveil/network/AbstractConnector$ResponseHandler;
      //   18: invokevirtual 58	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:doRequest	([BLcom/google/android/apps/unveil/network/AbstractConnector$ResponseHandler;)V
      //   21: aload_0
      //   22: monitorenter
      //   23: aload_0
      //   24: getfield 60	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:stopped	Z
      //   27: ifne +16 -> 43
      //   30: aload_0
      //   31: getfield 15	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:this$0	Lcom/google/android/apps/unveil/network/ContinuousConnector;
      //   34: invokestatic 64	com/google/android/apps/unveil/network/ContinuousConnector:access$500	(Lcom/google/android/apps/unveil/network/ContinuousConnector;)Ljava/util/concurrent/Executor;
      //   37: aload_0
      //   38: invokeinterface 70 2 0
      //   43: aload_0
      //   44: monitorexit
      //   45: return
      //   46: astore_1
      //   47: aload_1
      //   48: invokevirtual 74	com/google/android/apps/unveil/network/RequestFailedException:getCause	()Ljava/lang/Throwable;
      //   51: instanceof 76
      //   54: ifeq +23 -> 77
      //   57: aload_0
      //   58: getfield 60	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:stopped	Z
      //   61: ifeq +16 -> 77
      //   64: invokestatic 80	com/google/android/apps/unveil/network/ContinuousConnector:access$1100	()Lcom/google/android/apps/unveil/env/UnveilLogger;
      //   67: ldc 82
      //   69: iconst_0
      //   70: anewarray 84	java/lang/Object
      //   73: invokevirtual 90	com/google/android/apps/unveil/env/UnveilLogger:i	(Ljava/lang/String;[Ljava/lang/Object;)V
      //   76: return
      //   77: invokestatic 80	com/google/android/apps/unveil/network/ContinuousConnector:access$1100	()Lcom/google/android/apps/unveil/env/UnveilLogger;
      //   80: aload_1
      //   81: ldc 92
      //   83: iconst_0
      //   84: anewarray 84	java/lang/Object
      //   87: invokevirtual 96	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
      //   90: aload_0
      //   91: getfield 32	com/google/android/apps/unveil/network/ContinuousConnector$PullingRunnable:responseHandler	Lcom/google/android/apps/unveil/network/AbstractConnector$ResponseHandler;
      //   94: invokevirtual 101	com/google/android/apps/unveil/network/AbstractConnector$ResponseHandler:onNetworkError	()V
      //   97: return
      //   98: astore_2
      //   99: aload_0
      //   100: monitorexit
      //   101: aload_2
      //   102: athrow
      //
      // Exception table:
      //   from	to	target	type
      //   0	21	46	com/google/android/apps/unveil/network/RequestFailedException
      //   23	43	98	finally
      //   43	45	98	finally
      //   99	101	98	finally
    }

    public void stop()
    {
      try
      {
        this.stopped = true;
        if ((this.request != null) && (!this.request.isAborted()))
          this.request.abort();
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }
  }

  public static abstract interface PushLogger
  {
    public abstract void onReceivePushResponse(int paramInt);

    public abstract void onSendPush(int paramInt);
  }

  private class PushRunnable extends ContinuousConnector.RequestRunnable
  {
    private final byte[] bytes;
    private final AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> connectorResponseHandler;
    private final int frameNum;
    private final boolean serialized;

    public PushRunnable(AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramInt, int paramBoolean, boolean arg4)
    {
      super(null);
      this.bytes = paramInt;
      this.connectorResponseHandler = paramBoolean;
      int i;
      this.frameNum = i;
      boolean bool;
      this.serialized = bool;
    }

    public void run()
    {
      try
      {
        ContinuousConnector.this.pushLogger.onSendPush(this.frameNum);
        Stats.setThreadTag(Stats.Tags.CONTINUOUS_PUSH);
        doRequest(this.bytes, this.connectorResponseHandler);
        ContinuousConnector.this.pushLogger.onReceivePushResponse(this.frameNum);
        return;
      }
      catch (RequestFailedException localRequestFailedException)
      {
        this.connectorResponseHandler.onNetworkError();
        return;
      }
      finally
      {
        if (this.serialized)
          ContinuousConnector.access$802(ContinuousConnector.this, false);
      }
    }
  }

  private abstract class RequestRunnable
    implements Runnable
  {
    protected HttpPost request;

    private RequestRunnable()
    {
    }

    protected void doRequest(byte[] paramArrayOfByte, AbstractConnector.ResponseHandler<GogglesProtos.GogglesResponse> paramResponseHandler)
      throws RequestFailedException
    {
      try
      {
        this.request = ContinuousConnector.this.httpRequestFactory.newPostRequest(ContinuousConnector.this.requestUrl, new ByteArrayEntity(paramArrayOfByte), DefaultHttpRequestFactory.ContentType.PROTOBUF);
        HttpResponse localHttpResponse1 = ContinuousConnector.this.network.execute(ContinuousConnector.this.host, this.request);
        HttpResponse localHttpResponse2 = ContinuousConnector.this.contentTransferrer.consumeAndRelease(localHttpResponse1);
        ContinuousConnector.this.threadPool.execute(new HttpProtoResponseHandler(GogglesProtos.GogglesResponse.class).asRunnable(localHttpResponse2, paramResponseHandler));
        return;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        throw new RequestFailedException(localClientProtocolException);
      }
      catch (IOException localIOException)
      {
        throw new RequestFailedException(localIOException);
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.ContinuousConnector
 * JD-Core Version:    0.6.2
 */