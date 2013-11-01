package com.google.zxing.client.result;

import com.google.zxing.Result;

final class EmailDoCoMoResultParser extends AbstractDoCoMoResultParser
{
  private static final char[] ATEXT_SYMBOLS = { 64, 46, 33, 35, 36, 37, 38, 39, 42, 43, 45, 47, 61, 63, 94, 95, 96, 123, 124, 125, 126 };

  private static boolean isAtextSymbol(char paramChar)
  {
    for (int i = 0; i < ATEXT_SYMBOLS.length; i++)
      if (paramChar == ATEXT_SYMBOLS[i])
        return true;
    return false;
  }

  static boolean isBasicallyValidEmailAddress(String paramString)
  {
    boolean bool;
    if (paramString == null)
      bool = false;
    while (true)
    {
      return bool;
      bool = false;
      for (int i = 0; i < paramString.length(); i++)
      {
        char c = paramString.charAt(i);
        if (((c < 'a') || (c > 'z')) && ((c < 'A') || (c > 'Z')) && ((c < '0') || (c > '9')) && (!isAtextSymbol(c)))
          return false;
        if (c == '@')
        {
          if (bool)
            return false;
          bool = true;
        }
      }
    }
  }

  public static EmailAddressParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if ((str1 == null) || (!str1.startsWith("MATMSG:")));
    String str2;
    do
    {
      String[] arrayOfString;
      do
      {
        return null;
        arrayOfString = matchDoCoMoPrefixedField("TO:", str1, true);
      }
      while (arrayOfString == null);
      str2 = arrayOfString[0];
    }
    while (!isBasicallyValidEmailAddress(str2));
    return new EmailAddressParsedResult(str2, matchSingleDoCoMoPrefixedField("SUB:", str1, false), matchSingleDoCoMoPrefixedField("BODY:", str1, false), "mailto:" + str2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.EmailDoCoMoResultParser
 * JD-Core Version:    0.6.2
 */