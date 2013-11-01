package com.google.zxing.client.result;

import com.google.zxing.Result;

final class VEventResultParser extends ResultParser
{
  public static CalendarParsedResult parse(Result paramResult)
  {
    String str1 = paramResult.getText();
    if (str1 == null)
      return null;
    if (str1.indexOf("BEGIN:VEVENT") < 0)
      return null;
    String str2 = VCardResultParser.matchSingleVCardPrefixedField("SUMMARY", str1, true);
    String str3 = VCardResultParser.matchSingleVCardPrefixedField("DTSTART", str1, true);
    String str4 = VCardResultParser.matchSingleVCardPrefixedField("DTEND", str1, true);
    String str5 = VCardResultParser.matchSingleVCardPrefixedField("LOCATION", str1, true);
    String str6 = VCardResultParser.matchSingleVCardPrefixedField("DESCRIPTION", str1, true);
    try
    {
      CalendarParsedResult localCalendarParsedResult = new CalendarParsedResult(str2, str3, str4, str5, null, str6);
      return localCalendarParsedResult;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
    }
    return null;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.VEventResultParser
 * JD-Core Version:    0.6.2
 */