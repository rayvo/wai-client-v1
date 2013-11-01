package com.google.android.apps.unveil.network;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesResponse;
import com.google.goggles.GogglesProtos.ImageRotation;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpMessage;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;

public class HttpProtoResponseHandler<ResponseType extends GeneratedMessageLite>
  implements ResponseHandler<UnveilResponse<ResponseType>>
{
  public static final String X_DEBUG_HEADER = "X-Goggles-Debug";
  private static ExtensionRegistryLite extensionRegistry = createExtensionRegistry();
  private static final UnveilLogger logger = new UnveilLogger();
  private final Class<ResponseType> responseClass;

  public HttpProtoResponseHandler(Class<ResponseType> paramClass)
  {
    this.responseClass = paramClass;
  }

  private static ExtensionRegistryLite createExtensionRegistry()
  {
    ExtensionRegistryLite localExtensionRegistryLite = ExtensionRegistryLite.newInstance();
    localExtensionRegistryLite.add(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse);
    localExtensionRegistryLite.add(AnnotationResultProtos.AnnotationResult.annotationResult);
    localExtensionRegistryLite.add(GogglesProtos.ImageRotation.imageRotation);
    return localExtensionRegistryLite;
  }

  private static String getDebugHeaderValue(HttpResponse paramHttpResponse)
  {
    Header[] arrayOfHeader = paramHttpResponse.getHeaders("X-Goggles-Debug");
    int i = arrayOfHeader.length;
    if (i == 0)
      return "";
    if (i == 1)
      return arrayOfHeader[0].getValue();
    StringBuilder localStringBuilder = new StringBuilder();
    int j = arrayOfHeader.length;
    for (int k = 0; k < j; k++)
    {
      Header localHeader = arrayOfHeader[k];
      localStringBuilder.append(localHeader.getValue() + "; ");
    }
    return localStringBuilder.toString();
  }

  private static int getTruncatedResponseSize(HttpEntity paramHttpEntity)
  {
    long l = paramHttpEntity.getContentLength();
    if (l >= 0L)
      return (int)l;
    return -1;
  }

  private static boolean isGzipped(HttpMessage paramHttpMessage)
  {
    Header localHeader = paramHttpMessage.getFirstHeader("Content-Encoding");
    return (localHeader != null) && (localHeader.getValue().equalsIgnoreCase("gzip"));
  }

  private static void releaseHttpEntity(HttpEntity paramHttpEntity)
  {
    if (paramHttpEntity == null)
      return;
    try
    {
      paramHttpEntity.consumeContent();
      return;
    }
    catch (IOException localIOException)
    {
      logger.w(localIOException, "Error releasing HTTP entity: ", new Object[0]);
    }
  }

  public Runnable asRunnable(final HttpResponse paramHttpResponse, final AbstractConnector.ResponseHandler<ResponseType> paramResponseHandler)
  {
    return new Runnable()
    {
      public void run()
      {
        try
        {
          UnveilResponse localUnveilResponse = HttpProtoResponseHandler.this.handleResponse(paramHttpResponse);
          paramResponseHandler.onResponse(localUnveilResponse);
          return;
        }
        catch (HttpResponseException localHttpResponseException)
        {
          UnveilLogger localUnveilLogger = HttpProtoResponseHandler.logger;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(localHttpResponseException.getStatusCode());
          arrayOfObject[1] = localHttpResponseException.getMessage();
          localUnveilLogger.w("Server error: %d, %s", arrayOfObject);
          paramResponseHandler.onServerErrorCode(localHttpResponseException.getStatusCode());
          return;
        }
        catch (IOException localIOException)
        {
          paramResponseHandler.onNetworkError();
        }
      }
    };
  }

  public UnveilResponse<ResponseType> handleResponse(HttpResponse paramHttpResponse)
    throws HttpResponseException, IOException
  {
    long l = System.currentTimeMillis();
    if (paramHttpResponse == null)
      throw new IOException("Null response.");
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    int i = paramHttpResponse.getStatusLine().getStatusCode();
    if (i != 200)
    {
      releaseHttpEntity(localHttpEntity);
      String str = getDebugHeaderValue(paramHttpResponse);
      if (!TextUtils.isEmpty(str))
      {
        logger.e("Frontend Exception: %s", new Object[] { str });
        throw new HttpResponseException(i, str);
      }
      throw new HttpResponseException(i, "Error code returned from remote host.");
    }
    if ((localHttpEntity == null) || (localHttpEntity.getContentLength() == 0L))
    {
      logger.v("There was no response content, but the server returned success.", new Object[0]);
      releaseHttpEntity(localHttpEntity);
      return UnveilResponse.get(l);
    }
    boolean bool = isGzipped(paramHttpResponse);
    if (!bool)
      logger.w("Server response wasn't gzipped.", new Object[0]);
    int j = getTruncatedResponseSize(localHttpEntity);
    InputStream localInputStream = null;
    try
    {
      localInputStream = localHttpEntity.getContent();
      Class localClass = this.responseClass;
      Object localObject2;
      if (bool)
        localObject2 = new GZIPInputStream(localInputStream);
      while (true)
      {
        GeneratedMessageLite localGeneratedMessageLite = ProtoBuilder.build(localClass, (InputStream)localObject2, extensionRegistry);
        releaseHttpEntity(localHttpEntity);
        if (localInputStream != null);
        try
        {
          localInputStream.close();
          return UnveilResponse.get(localGeneratedMessageLite, j, l);
          localObject2 = localInputStream;
        }
        catch (IOException localIOException2)
        {
          while (true)
            logger.e(localIOException2, "Error releasing input streams: ", new Object[0]);
        }
      }
    }
    finally
    {
      releaseHttpEntity(localHttpEntity);
      if (localInputStream == null);
    }
    try
    {
      localInputStream.close();
      throw localObject1;
    }
    catch (IOException localIOException1)
    {
      while (true)
        logger.e(localIOException1, "Error releasing input streams: ", new Object[0]);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.HttpProtoResponseHandler
 * JD-Core Version:    0.6.2
 */