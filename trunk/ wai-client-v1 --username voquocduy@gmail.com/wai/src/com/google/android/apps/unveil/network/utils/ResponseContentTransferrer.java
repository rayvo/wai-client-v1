package com.google.android.apps.unveil.network.utils;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.RequestFailedException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.message.BasicHttpResponse;

public final class ResponseContentTransferrer
{
  private static final int DEFAULT_OUTPUT_BUFFER_SIZE_BYTES = 4096;
  private static final UnveilLogger logger = new UnveilLogger();
  private final int outputBufferSize = 4096;

  private void transferEntity(HttpResponse paramHttpResponse, BasicHttpResponse paramBasicHttpResponse)
    throws RequestFailedException
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity == null)
      return;
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(this.outputBufferSize);
    try
    {
      localHttpEntity.writeTo(localByteArrayOutputStream);
      localByteArrayOutputStream.flush();
      paramBasicHttpResponse.setEntity(new ByteArrayEntity(localByteArrayOutputStream.toByteArray()));
      return;
    }
    catch (IOException localIOException)
    {
      throw new RequestFailedException(localIOException);
    }
  }

  private void transferHeaders(HttpResponse paramHttpResponse, BasicHttpResponse paramBasicHttpResponse)
  {
    Header[] arrayOfHeader = paramHttpResponse.getAllHeaders();
    int i = arrayOfHeader.length;
    for (int j = 0; j < i; j++)
      paramBasicHttpResponse.addHeader(arrayOfHeader[j]);
  }

  public HttpResponse consumeAndRelease(HttpResponse paramHttpResponse)
    throws RequestFailedException
  {
    BasicHttpResponse localBasicHttpResponse = new BasicHttpResponse(paramHttpResponse.getStatusLine());
    transferHeaders(paramHttpResponse, localBasicHttpResponse);
    transferEntity(paramHttpResponse, localBasicHttpResponse);
    try
    {
      if (paramHttpResponse.getEntity() != null)
        paramHttpResponse.getEntity().consumeContent();
      return localBasicHttpResponse;
    }
    catch (IOException localIOException)
    {
      logger.e("It's not possible to keep connection alive", new Object[0]);
    }
    return localBasicHttpResponse;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.utils.ResponseContentTransferrer
 * JD-Core Version:    0.6.2
 */