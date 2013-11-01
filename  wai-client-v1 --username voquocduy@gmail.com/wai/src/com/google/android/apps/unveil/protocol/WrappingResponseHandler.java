package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.network.AbstractConnector.ResponseHandler;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.protobuf.GeneratedMessageLite;

public class WrappingResponseHandler<ResponseType extends GeneratedMessageLite> extends AbstractConnector.ResponseHandler<ResponseType>
{
  protected final AbstractConnector.ResponseHandler<ResponseType> inner;
  protected final AbstractConnector.ResponseHandler<ResponseType> outer;

  public WrappingResponseHandler(AbstractConnector.ResponseHandler<ResponseType> paramResponseHandler1, AbstractConnector.ResponseHandler<ResponseType> paramResponseHandler2)
  {
    this.inner = paramResponseHandler1;
    this.outer = paramResponseHandler2;
  }

  public void onNetworkError()
  {
    this.outer.onNetworkError();
    this.inner.onNetworkError();
  }

  public void onResponse(UnveilResponse<ResponseType> paramUnveilResponse)
  {
    this.outer.onResponse(paramUnveilResponse);
    this.inner.onResponse(paramUnveilResponse);
  }

  public void onServerErrorCode(int paramInt)
  {
    this.outer.onServerErrorCode(paramInt);
    this.inner.onServerErrorCode(paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.WrappingResponseHandler
 * JD-Core Version:    0.6.2
 */