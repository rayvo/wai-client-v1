package com.google.android.apps.unveil.protocol.nonstop;

import com.google.android.apps.unveil.env.UnveilLogger;

public class RequestIds
{
  private static final String DELIMITER = ":";
  private static final UnveilLogger logger = new UnveilLogger();

  private RequestIds()
  {
    throw new UnsupportedOperationException();
  }

  public static long decodeBaseRequestId(String paramString)
  {
    String[] arrayOfString = paramString.split(":");
    if (arrayOfString.length == 1)
      logger.e("RequestId missing request type sub-identifier: %s", new Object[] { paramString });
    return Long.valueOf(arrayOfString[0]).longValue();
  }

  public static String encodeFullRequestId(long paramLong, RequestType paramRequestType)
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = Long.valueOf(paramLong);
    arrayOfObject[1] = ":";
    arrayOfObject[2] = paramRequestType.identifier;
    return String.format("%012d%s%s", arrayOfObject);
  }

  public static enum RequestType
  {
    public final String identifier;

    static
    {
      CLIENT_ANNOTATION = new RequestType("CLIENT_ANNOTATION", 1, "c");
      RequestType[] arrayOfRequestType = new RequestType[2];
      arrayOfRequestType[0] = IMAGE;
      arrayOfRequestType[1] = CLIENT_ANNOTATION;
    }

    private RequestType(String paramString)
    {
      this.identifier = paramString;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.RequestIds
 * JD-Core Version:    0.6.2
 */