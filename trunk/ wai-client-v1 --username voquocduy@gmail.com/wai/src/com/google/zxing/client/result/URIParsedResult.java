package com.google.zxing.client.result;

public final class URIParsedResult extends ParsedResult
{
  private final String title;
  private final String uri;

  public URIParsedResult(String paramString1, String paramString2)
  {
    super(ParsedResultType.URI);
    this.uri = massageURI(paramString1);
    this.title = paramString2;
  }

  private boolean containsUser()
  {
    int i = 1 + this.uri.indexOf(':');
    int j = this.uri.length();
    while ((i < j) && (this.uri.charAt(i) == '/'))
      i++;
    int k = this.uri.indexOf('/', i);
    if (k < 0)
      k = j;
    int m = this.uri.indexOf('@', i);
    return (m >= i) && (m < k);
  }

  private static boolean isColonFollowedByPortNumber(String paramString, int paramInt)
  {
    int i = paramString.indexOf('/', paramInt + 1);
    if (i < 0)
      i = paramString.length();
    if (i <= paramInt + 1)
      return false;
    for (int j = paramInt + 1; ; j++)
    {
      if (j >= i)
        break label63;
      if ((paramString.charAt(j) < '0') || (paramString.charAt(j) > '9'))
        break;
    }
    label63: return true;
  }

  private static String massageURI(String paramString)
  {
    int i = paramString.indexOf(':');
    if (i < 0)
      return "http://" + paramString;
    if (isColonFollowedByPortNumber(paramString, i))
      return "http://" + paramString;
    return paramString.substring(0, i).toLowerCase() + paramString.substring(i);
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(30);
    maybeAppend(this.title, localStringBuffer);
    maybeAppend(this.uri, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String getTitle()
  {
    return this.title;
  }

  public String getURI()
  {
    return this.uri;
  }

  public boolean isPossiblyMaliciousURI()
  {
    return containsUser();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIParsedResult
 * JD-Core Version:    0.6.2
 */