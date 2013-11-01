package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public class ISBNResultParser extends ResultParser
{
  public static ISBNParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if (!BarcodeFormat.EAN_13.equals(localBarcodeFormat));
    String str;
    do
    {
      return null;
      str = paramResult.getText();
    }
    while ((str == null) || (str.length() != 13) || ((!str.startsWith("978")) && (!str.startsWith("979"))));
    return new ISBNParsedResult(str);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ISBNResultParser
 * JD-Core Version:    0.6.2
 */