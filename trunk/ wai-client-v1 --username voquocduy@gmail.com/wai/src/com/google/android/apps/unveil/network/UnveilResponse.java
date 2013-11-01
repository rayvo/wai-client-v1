package com.google.android.apps.unveil.network;

import com.google.protobuf.GeneratedMessageLite;

public final class UnveilResponse<ResponseType extends GeneratedMessageLite>
{
  private final AbstractConnector.ConnectorException exception;
  private final ResponseType response;
  private final long responseReceivedTimestamp;
  private final int responseSize;

  private UnveilResponse(ResponseType paramResponseType, int paramInt, AbstractConnector.ConnectorException paramConnectorException, long paramLong)
  {
    this.response = paramResponseType;
    this.responseSize = paramInt;
    this.exception = paramConnectorException;
    this.responseReceivedTimestamp = paramLong;
  }

  public static <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> get(long paramLong)
  {
    return new UnveilResponse(null, 0, null, paramLong);
  }

  public static <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> get(AbstractConnector.ConnectorException paramConnectorException)
  {
    return new UnveilResponse(null, -1, paramConnectorException, -1L);
  }

  public static <ResponseType extends GeneratedMessageLite> UnveilResponse<ResponseType> get(ResponseType paramResponseType, int paramInt, long paramLong)
  {
    return new UnveilResponse(paramResponseType, paramInt, null, paramLong);
  }

  public AbstractConnector.ConnectorException getException()
  {
    return this.exception;
  }

  public ResponseType getProtocolBuffer()
  {
    return this.response;
  }

  public long getResponseReceivedTimestamp()
  {
    return this.responseReceivedTimestamp;
  }

  public int getResponseSize()
  {
    return this.responseSize;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.UnveilResponse
 * JD-Core Version:    0.6.2
 */