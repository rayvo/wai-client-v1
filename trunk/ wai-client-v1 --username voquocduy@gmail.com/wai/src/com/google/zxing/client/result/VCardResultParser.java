package com.google.zxing.client.result;

import com.google.zxing.Result;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

final class VCardResultParser extends ResultParser
{
  private static String decodeQuotedPrintable(String paramString1, String paramString2)
  {
    int i = paramString1.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    int j = 0;
    if (j < i)
    {
      char c1 = paramString1.charAt(j);
      switch (c1)
      {
      default:
        maybeAppendFragment(localByteArrayOutputStream, paramString2, localStringBuffer);
        localStringBuffer.append(c1);
      case '\n':
      case '\r':
      case '=':
      }
    }
    while (true)
    {
      j++;
      break;
      if (j >= i - 2)
        continue;
      char c2 = paramString1.charAt(j + 1);
      if ((c2 == '\r') || (c2 == '\n'))
        continue;
      char c3 = paramString1.charAt(j + 2);
      try
      {
        localByteArrayOutputStream.write(16 * toHexValue(c2) + toHexValue(c3));
        label157: j += 2;
        continue;
        maybeAppendFragment(localByteArrayOutputStream, paramString2, localStringBuffer);
        return localStringBuffer.toString();
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        break label157;
      }
    }
  }

  private static String formatAddress(String paramString)
  {
    if (paramString == null)
      return null;
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    int j = 0;
    if (j < i)
    {
      char c = paramString.charAt(j);
      if (c == ';')
        localStringBuffer.append(' ');
      while (true)
      {
        j++;
        break;
        localStringBuffer.append(c);
      }
    }
    return localStringBuffer.toString().trim();
  }

  private static void formatNames(String[] paramArrayOfString)
  {
    if (paramArrayOfString != null)
      for (int i = 0; i < paramArrayOfString.length; i++)
      {
        String str = paramArrayOfString[i];
        String[] arrayOfString = new String[5];
        int j = 0;
        int k = 0;
        while (true)
        {
          int m = str.indexOf(';', j);
          if (m <= 0)
            break;
          arrayOfString[k] = str.substring(j, m);
          k++;
          j = m + 1;
        }
        arrayOfString[k] = str.substring(j);
        StringBuffer localStringBuffer = new StringBuffer(100);
        maybeAppendComponent(arrayOfString, 3, localStringBuffer);
        maybeAppendComponent(arrayOfString, 1, localStringBuffer);
        maybeAppendComponent(arrayOfString, 2, localStringBuffer);
        maybeAppendComponent(arrayOfString, 0, localStringBuffer);
        maybeAppendComponent(arrayOfString, 4, localStringBuffer);
        paramArrayOfString[i] = localStringBuffer.toString().trim();
      }
  }

  private static boolean isLikeVCardDate(String paramString)
  {
    if (paramString == null);
    while ((isStringOfDigits(paramString, 8)) || ((paramString.length() == 10) && (paramString.charAt(4) == '-') && (paramString.charAt(7) == '-') && (isSubstringOfDigits(paramString, 0, 4)) && (isSubstringOfDigits(paramString, 5, 2)) && (isSubstringOfDigits(paramString, 8, 2))))
      return true;
    return false;
  }

  static String matchSingleVCardPrefixedField(String paramString1, String paramString2, boolean paramBoolean)
  {
    String[] arrayOfString = matchVCardPrefixedField(paramString1, paramString2, paramBoolean);
    if (arrayOfString == null)
      return null;
    return arrayOfString[0];
  }

  private static String[] matchVCardPrefixedField(String paramString1, String paramString2, boolean paramBoolean)
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
      if ((k > 0) && (paramString2.charAt(k - 1) != '\n'))
      {
        i = k + 1;
      }
      else
      {
        i = k + paramString1.length();
        if ((paramString2.charAt(i) == ':') || (paramString2.charAt(i) == ';'))
        {
          int m = i;
          while (paramString2.charAt(i) != ':')
            i++;
          int n = 0;
          Object localObject = null;
          if (i > m)
          {
            int i4 = m + 1;
            if (i4 <= i)
            {
              String str4;
              String str5;
              if ((paramString2.charAt(i4) == ';') || (paramString2.charAt(i4) == ':'))
              {
                String str3 = paramString2.substring(m + 1, i4);
                int i5 = str3.indexOf('=');
                if (i5 >= 0)
                {
                  str4 = str3.substring(0, i5);
                  str5 = str3.substring(i5 + 1);
                  if (!str4.equalsIgnoreCase("ENCODING"))
                    break label252;
                  if (str5.equalsIgnoreCase("QUOTED-PRINTABLE"))
                    n = 1;
                }
              }
              while (true)
              {
                m = i4;
                i4++;
                break;
                label252: if (str4.equalsIgnoreCase("CHARSET"))
                  localObject = str5;
              }
            }
          }
          int i1 = i + 1;
          int i2 = i1;
          int i3;
          while (true)
          {
            i3 = paramString2.indexOf('\n', i1);
            if (i3 < 0)
              break;
            if ((i3 < -1 + paramString2.length()) && ((paramString2.charAt(i3 + 1) == ' ') || (paramString2.charAt(i3 + 1) == '\t')))
            {
              i1 = i3 + 2;
            }
            else
            {
              if ((n == 0) || ((paramString2.charAt(i3 - 1) != '=') && (paramString2.charAt(i3 - 2) != '=')))
                break;
              i1 = i3 + 1;
            }
          }
          if (i3 < 0)
          {
            i = j;
          }
          else
          {
            if (i3 > i2)
            {
              if (localVector == null)
                localVector = new Vector(1);
              if (paramString2.charAt(i3 - 1) == '\r')
                i3--;
              String str1 = paramString2.substring(i2, i3);
              if (paramBoolean)
                str1 = str1.trim();
              if (n != 0);
              for (String str2 = decodeQuotedPrintable(str1, localObject); ; str2 = stripContinuationCRLF(str1))
              {
                localVector.addElement(str2);
                i = i3 + 1;
                break;
              }
            }
            i = i3 + 1;
          }
        }
      }
    }
    return toStringArray(localVector);
  }

  private static void maybeAppendComponent(String[] paramArrayOfString, int paramInt, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfString[paramInt] != null)
    {
      paramStringBuffer.append(' ');
      paramStringBuffer.append(paramArrayOfString[paramInt]);
    }
  }

  private static void maybeAppendFragment(ByteArrayOutputStream paramByteArrayOutputStream, String paramString, StringBuffer paramStringBuffer)
  {
    byte[] arrayOfByte;
    String str;
    if (paramByteArrayOutputStream.size() > 0)
    {
      arrayOfByte = paramByteArrayOutputStream.toByteArray();
      if (paramString != null)
        break label38;
      str = new String(arrayOfByte);
    }
    while (true)
    {
      paramByteArrayOutputStream.reset();
      paramStringBuffer.append(str);
      return;
      try
      {
        label38: str = new String(arrayOfByte, paramString);
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        str = new String(arrayOfByte);
      }
    }
  }

  public static AddressBookParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if ((str1 == null) || (!str1.startsWith("BEGIN:VCARD")))
      return null;
    String[] arrayOfString1 = matchVCardPrefixedField("FN", str1, true);
    if (arrayOfString1 == null)
    {
      arrayOfString1 = matchVCardPrefixedField("N", str1, true);
      formatNames(arrayOfString1);
    }
    String[] arrayOfString2 = matchVCardPrefixedField("TEL", str1, true);
    String[] arrayOfString3 = matchVCardPrefixedField("EMAIL", str1, true);
    String str2 = matchSingleVCardPrefixedField("NOTE", str1, false);
    String[] arrayOfString4 = matchVCardPrefixedField("ADR", str1, true);
    if (arrayOfString4 != null)
      for (int i = 0; i < arrayOfString4.length; i++)
        arrayOfString4[i] = formatAddress(arrayOfString4[i]);
    String str3 = matchSingleVCardPrefixedField("ORG", str1, true);
    String str4 = matchSingleVCardPrefixedField("BDAY", str1, true);
    if (!isLikeVCardDate(str4))
      str4 = null;
    return new AddressBookParsedResult(arrayOfString1, null, arrayOfString2, arrayOfString3, str2, arrayOfString4, str3, str4, matchSingleVCardPrefixedField("TITLE", str1, true), matchSingleVCardPrefixedField("URL", str1, true));
  }

  private static String stripContinuationCRLF(String paramString)
  {
    int i = paramString.length();
    StringBuffer localStringBuffer = new StringBuffer(i);
    int j = 0;
    int k = 0;
    if (k < i)
    {
      if (j != 0)
        j = 0;
      while (true)
      {
        k++;
        break;
        char c = paramString.charAt(k);
        j = 0;
        switch (c)
        {
        case '\r':
        case '\013':
        case '\f':
        default:
          localStringBuffer.append(c);
          j = 0;
          break;
        case '\n':
          j = 1;
        }
      }
    }
    return localStringBuffer.toString();
  }

  private static int toHexValue(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return 10 + (paramChar - 'A');
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return 10 + (paramChar - 'a');
    throw new IllegalArgumentException();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.VCardResultParser
 * JD-Core Version:    0.6.2
 */