package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URLTOResultParser
{
  public static URIParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if ((str1 == null) || ((!str1.startsWith("urlto:")) && (!str1.startsWith("URLTO:"))));
    int i;
    do
    {
      return null;
      i = str1.indexOf(':', 6);
    }
    while (i < 0);
    String str2 = null;
    if (i <= 6);
    while (true)
    {
      return new URIParsedResult(str1.substring(i + 1), str2);
      str2 = str1.substring(6, i);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URLTOResultParser
 * JD-Core Version:    0.6.2
 */