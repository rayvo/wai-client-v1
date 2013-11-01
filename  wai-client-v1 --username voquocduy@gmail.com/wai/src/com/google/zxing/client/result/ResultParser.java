package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.util.Hashtable;
import java.util.Vector;

public abstract class ResultParser
{
  private static void appendKeyValue(String paramString, int paramInt1, int paramInt2, Hashtable paramHashtable)
  {
    int i = paramString.indexOf('=', paramInt1);
    if (i >= 0)
      paramHashtable.put(paramString.substring(paramInt1, i), urlDecode(paramString.substring(i + 1, paramInt2)));
  }

  private static int findFirstEscape(char[] paramArrayOfChar)
  {
    int i = paramArrayOfChar.length;
    for (int j = 0; j < i; j++)
    {
      int k = paramArrayOfChar[j];
      if ((k == 43) || (k == 37))
        return j;
    }
    return -1;
  }

  protected static boolean isStringOfDigits(String paramString, int paramInt)
  {
    if (paramString == null);
    while (paramInt != paramString.length())
      return false;
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        break label45;
      int j = paramString.charAt(i);
      if ((j < 48) || (j > 57))
        break;
    }
    label45: return true;
  }

  protected static boolean isSubstringOfDigits(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null);
    int i;
    int j;
    do
    {
      return false;
      i = paramString.length();
      j = paramInt1 + paramInt2;
    }
    while (i < j);
    for (int k = paramInt1; ; k++)
    {
      if (k >= j)
        break label60;
      int m = paramString.charAt(k);
      if ((m < 48) || (m > 57))
        break;
    }
    label60: return true;
  }

  static String[] matchPrefixedField(String paramString1, String paramString2, char paramChar, boolean paramBoolean)
  {
    Vector localVector = null;
    int i = 0;
    int j = paramString2.length();
    while (true)
    {
      int k;
      if (i < j)
      {
        k = paramString2.indexOf(paramString1, i);
        if (k >= 0);
      }
      else
      {
        if ((localVector != null) && (!localVector.isEmpty()))
          break;
        return null;
      }
      i = k + paramString1.length();
      int m = i;
      int n = 0;
      while (n == 0)
      {
        int i1 = paramString2.indexOf(paramChar, i);
        if (i1 < 0)
        {
          i = paramString2.length();
          n = 1;
        }
        else if (paramString2.charAt(i1 - 1) == '\\')
        {
          i = i1 + 1;
        }
        else
        {
          if (localVector == null)
            localVector = new Vector(3);
          String str = unescapeBackslash(paramString2.substring(m, i1));
          if (paramBoolean)
            str = str.trim();
          localVector.addElement(str);
          i = i1 + 1;
          n = 1;
        }
      }
    }
    return toStringArray(localVector);
  }

  static String matchSinglePrefixedField(String paramString1, String paramString2, char paramChar, boolean paramBoolean)
  {
    String[] arrayOfString = matchPrefixedField(paramString1, paramString2, paramChar, paramBoolean);
    if (arrayOfString == null)
      return null;
    return arrayOfString[0];
  }

  protected static void maybeAppend(String paramString, StringBuffer paramStringBuffer)
  {
    if (paramString != null)
    {
      paramStringBuffer.append('\n');
      paramStringBuffer.append(paramString);
    }
  }

  protected static void maybeAppend(String[] paramArrayOfString, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfString != null)
      for (int i = 0; i < paramArrayOfString.length; i++)
      {
        paramStringBuffer.append('\n');
        paramStringBuffer.append(paramArrayOfString[i]);
      }
  }

  protected static String[] maybeWrap(String paramString)
  {
    if (paramString == null)
      return null;
    return new String[] { paramString };
  }

  private static int parseHexDigit(char paramChar)
  {
    if (paramChar >= 'a')
    {
      if (paramChar <= 'f')
        return 10 + (paramChar - 'a');
    }
    else if (paramChar >= 'A')
    {
      if (paramChar <= 'F')
        return 10 + (paramChar - 'A');
    }
    else if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    return -1;
  }

  static Hashtable parseNameValuePairs(String paramString)
  {
    int i = paramString.indexOf('?');
    if (i < 0)
      return null;
    Hashtable localHashtable = new Hashtable(3);
    int k;
    for (int j = i + 1; ; j = k + 1)
    {
      k = paramString.indexOf('&', j);
      if (k < 0)
        break;
      appendKeyValue(paramString, j, k, localHashtable);
    }
    appendKeyValue(paramString, j, paramString.length(), localHashtable);
    return localHashtable;
  }

  public static ParsedResult parseResult(Result paramResult)
  {
    URIParsedResult localURIParsedResult1 = BookmarkDoCoMoResultParser.parse(paramResult);
    if (localURIParsedResult1 != null)
      return localURIParsedResult1;
    AddressBookParsedResult localAddressBookParsedResult1 = AddressBookDoCoMoResultParser.parse(paramResult);
    if (localAddressBookParsedResult1 != null)
      return localAddressBookParsedResult1;
    EmailAddressParsedResult localEmailAddressParsedResult1 = EmailDoCoMoResultParser.parse(paramResult);
    if (localEmailAddressParsedResult1 != null)
      return localEmailAddressParsedResult1;
    AddressBookParsedResult localAddressBookParsedResult2 = AddressBookAUResultParser.parse(paramResult);
    if (localAddressBookParsedResult2 != null)
      return localAddressBookParsedResult2;
    AddressBookParsedResult localAddressBookParsedResult3 = VCardResultParser.parse(paramResult);
    if (localAddressBookParsedResult3 != null)
      return localAddressBookParsedResult3;
    AddressBookParsedResult localAddressBookParsedResult4 = BizcardResultParser.parse(paramResult);
    if (localAddressBookParsedResult4 != null)
      return localAddressBookParsedResult4;
    CalendarParsedResult localCalendarParsedResult = VEventResultParser.parse(paramResult);
    if (localCalendarParsedResult != null)
      return localCalendarParsedResult;
    EmailAddressParsedResult localEmailAddressParsedResult2 = EmailAddressResultParser.parse(paramResult);
    if (localEmailAddressParsedResult2 != null)
      return localEmailAddressParsedResult2;
    EmailAddressParsedResult localEmailAddressParsedResult3 = SMTPResultParser.parse(paramResult);
    if (localEmailAddressParsedResult3 != null)
      return localEmailAddressParsedResult3;
    TelParsedResult localTelParsedResult = TelResultParser.parse(paramResult);
    if (localTelParsedResult != null)
      return localTelParsedResult;
    SMSParsedResult localSMSParsedResult1 = SMSMMSResultParser.parse(paramResult);
    if (localSMSParsedResult1 != null)
      return localSMSParsedResult1;
    SMSParsedResult localSMSParsedResult2 = SMSTOMMSTOResultParser.parse(paramResult);
    if (localSMSParsedResult2 != null)
      return localSMSParsedResult2;
    GeoParsedResult localGeoParsedResult = GeoResultParser.parse(paramResult);
    if (localGeoParsedResult != null)
      return localGeoParsedResult;
    WifiParsedResult localWifiParsedResult = WifiResultParser.parse(paramResult);
    if (localWifiParsedResult != null)
      return localWifiParsedResult;
    URIParsedResult localURIParsedResult2 = URLTOResultParser.parse(paramResult);
    if (localURIParsedResult2 != null)
      return localURIParsedResult2;
    URIParsedResult localURIParsedResult3 = URIResultParser.parse(paramResult);
    if (localURIParsedResult3 != null)
      return localURIParsedResult3;
    ISBNParsedResult localISBNParsedResult = ISBNResultParser.parse(paramResult);
    if (localISBNParsedResult != null)
      return localISBNParsedResult;
    ProductParsedResult localProductParsedResult = ProductResultParser.parse(paramResult);
    if (localProductParsedResult != null)
      return localProductParsedResult;
    return new TextParsedResult(paramResult.getText(), null);
  }

  static String[] toStringArray(Vector paramVector)
  {
    int i = paramVector.size();
    String[] arrayOfString = new String[i];
    for (int j = 0; j < i; j++)
      arrayOfString[j] = ((String)paramVector.elementAt(j));
    return arrayOfString;
  }

  protected static String unescapeBackslash(String paramString)
  {
    if (paramString != null)
    {
      int i = paramString.indexOf('\\');
      if (i >= 0)
      {
        int j = paramString.length();
        StringBuffer localStringBuffer = new StringBuffer(j - 1);
        localStringBuffer.append(paramString.toCharArray(), 0, i);
        int k = 0;
        int m = i;
        if (m < j)
        {
          char c = paramString.charAt(m);
          if ((k != 0) || (c != '\\'))
            localStringBuffer.append(c);
          for (k = 0; ; k = 1)
          {
            m++;
            break;
          }
        }
        paramString = localStringBuffer.toString();
      }
    }
    return paramString;
  }

  private static String urlDecode(String paramString)
  {
    if (paramString == null)
      paramString = null;
    char[] arrayOfChar;
    int i;
    do
    {
      return paramString;
      arrayOfChar = paramString.toCharArray();
      i = findFirstEscape(arrayOfChar);
    }
    while (i < 0);
    int j = arrayOfChar.length;
    StringBuffer localStringBuffer = new StringBuffer(j - 2);
    localStringBuffer.append(arrayOfChar, 0, i);
    int k = i;
    if (k < j)
    {
      char c = arrayOfChar[k];
      if (c == '+')
        localStringBuffer.append(' ');
      while (true)
      {
        k++;
        break;
        if (c == '%')
        {
          if (k >= j - 2)
          {
            localStringBuffer.append('%');
          }
          else
          {
            int m = k + 1;
            int n = parseHexDigit(arrayOfChar[m]);
            k = m + 1;
            int i1 = parseHexDigit(arrayOfChar[k]);
            if ((n < 0) || (i1 < 0))
            {
              localStringBuffer.append('%');
              localStringBuffer.append(arrayOfChar[(k - 1)]);
              localStringBuffer.append(arrayOfChar[k]);
            }
            localStringBuffer.append((char)(i1 + (n << 4)));
          }
        }
        else
          localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.ResultParser
 * JD-Core Version:    0.6.2
 */