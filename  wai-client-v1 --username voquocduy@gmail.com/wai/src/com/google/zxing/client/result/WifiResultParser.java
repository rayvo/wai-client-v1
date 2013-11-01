package com.google.zxing.client.result;

import com.google.zxing.Result;

final class WifiResultParser extends ResultParser
{
  public static WifiParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if ((str1 == null) || (!str1.startsWith("WIFI:")))
      return null;
    String str2 = matchSinglePrefixedField("S:", str1, ';', false);
    String str3 = matchSinglePrefixedField("P:", str1, ';', false);
    return new WifiParsedResult(matchSinglePrefixedField("T:", str1, ';', false), str2, str3);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.WifiResultParser
 * JD-Core Version:    0.6.2
 */