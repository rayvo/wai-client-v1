package com.google.zxing.client.result;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.google.zxing.oned.MultiFormatUPCEANReader;

final class ProductResultParser extends ResultParser
{
  public static ProductParsedResult parse(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    if ((!BarcodeFormat.UPC_A.equals(localBarcodeFormat)) && (!BarcodeFormat.UPC_E.equals(localBarcodeFormat)) && (!BarcodeFormat.EAN_8.equals(localBarcodeFormat)) && (!BarcodeFormat.EAN_13.equals(localBarcodeFormat)));
    String str1;
    do
    {
      return null;
      str1 = paramResult.getText();
    }
    while (str1 == null);
    int i = str1.length();
    for (int j = 0; ; j++)
    {
      if (j >= i)
        break label98;
      int k = str1.charAt(j);
      if ((k < 48) || (k > 57))
        break;
    }
    label98: if (BarcodeFormat.UPC_E.equals(localBarcodeFormat));
    for (String str2 = MultiFormatUPCEANReader.convertUPCEtoUPCA(str1); ; str2 = str1)
      return new ProductParsedResult(str1, str2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ProductResultParser
 * JD-Core Version:    0.6.2
 */