package com.google.android.apps.unveil.network;

import com.google.protobuf.GeneratedMessageLite;
import java.util.concurrent.Executor;

public final class OtherThreadResponseHandler<ResponseType extends GeneratedMessageLite> extends AbstractConnector.ResponseHandler<ResponseType>
{
  private final AbstractConnector.ResponseHandler<ResponseType> inner;
  private final Executor otherThreadHandler;

  public OtherThreadResponseHandler(Executor paramExecutor, AbstractConnector.ResponseHandler<ResponseType> paramResponseHandler)
  {
    this.otherThreadHandler = paramExecutor;
    this.inner = paramResponseHandler;
  }

  public void onNetworkError()
  {
    this.otherThreadHandler.execute(new Runnable()
    {
      public void run()
      {
        OtherThreadResponseHandler.this.inner.onNetworkError();
      }
    });
  }

  public void onResponse(final UnveilResponse<ResponseType> paramUnveilResponse)
  {
    this.otherThreadHandler.execute(new Runnable()
    {
      public void run()
      {
        OtherThreadResponseHandler.this.inner.onResponse(paramUnveilResponse);
      }
    });
  }

  public void onServerErrorCode(final int paramInt)
  {
    this.otherThreadHandler.execute(new Runnable()
    {
      public void run()
      {
        OtherThreadResponseHandler.this.inner.onServerErrorCode(paramInt);
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.OtherThreadResponseHandler
 * JD-Core Version:    0.6.2
 */