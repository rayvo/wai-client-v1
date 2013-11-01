package com.google.zxing.client.result;

import com.google.zxing.Result;

final class URIResultParser extends ResultParser
{
  static boolean isBasicallyValidURI(String paramString)
  {
    if ((paramString == null) || (paramString.indexOf(' ') >= 0) || (paramString.indexOf('\n') >= 0));
    int j;
    do
    {
      int i;
      do
      {
        do
        {
          return false;
          i = paramString.indexOf('.');
        }
        while (i >= -2 + paramString.length());
        j = paramString.indexOf(':');
      }
      while ((i < 0) && (j < 0));
      if (j < 0)
        break;
      if ((i < 0) || (i > j))
        for (int k = 0; ; k++)
        {
          if (k >= j)
            break label170;
          int m = paramString.charAt(k);
          if (((m < 97) || (m > 122)) && ((m < 65) || (m > 90)))
            break;
        }
    }
    while (j >= -2 + paramString.length());
    for (int n = j + 1; ; n++)
    {
      if (n >= j + 3)
        break label170;
      int i1 = paramString.charAt(n);
      if ((i1 < 48) || (i1 > 57))
        break;
    }
    label170: return true;
  }

  public static URIParsedResult parse(Result paramResult)
  {
    String str = paramResult.getText();
    if ((str != null) && (str.startsWith("URL:")))
      str = str.substring(4);
    if (!isBasicallyValidURI(str))
      return null;
    return new URIParsedResult(str, null);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.URIResultParser
 * JD-Core Version:    0.6.2
 */