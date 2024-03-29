package com.google.zxing.client.result;

import com.google.zxing.Result;

final class SMSTOMMSTOResultParser extends ResultParser
{
  public static SMSParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if (str1 == null);
    while ((!str1.startsWith("smsto:")) && (!str1.startsWith("SMSTO:")) && (!str1.startsWith("mmsto:")) && (!str1.startsWith("MMSTO:")))
      return null;
    String str2 = str1.substring(6);
    int i = str2.indexOf(':');
    String str3 = null;
    if (i >= 0)
    {
      str3 = str2.substring(i + 1);
      str2 = str2.substring(0, i);
    }
    return new SMSParsedResult(str2, null, null, str3);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSTOMMSTOResultParser
 * JD-Core Version:    0.6.2
 */