package com.google.zxing.client.result;

public final class SMSParsedResult extends ParsedResult
{
  private final String body;
  private final String[] numbers;
  private final String subject;
  private final String[] vias;

  public SMSParsedResult(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(ParsedResultType.SMS);
    this.numbers = new String[] { paramString1 };
    this.vias = new String[] { paramString2 };
    this.subject = paramString3;
    this.body = paramString4;
  }

  public SMSParsedResult(String[] paramArrayOfString1, String[] paramArrayOfString2, String paramString1, String paramString2)
  {
    super(ParsedResultType.SMS);
    this.numbers = paramArrayOfString1;
    this.vias = paramArrayOfString2;
    this.subject = paramString1;
    this.body = paramString2;
  }

  public String getBody()
  {
    return this.body;
  }

  public String getDisplayResult()
  {
    StringBuffer localStringBuffer = new StringBuffer(100);
    maybeAppend(this.numbers, localStringBuffer);
    maybeAppend(this.subject, localStringBuffer);
    maybeAppend(this.body, localStringBuffer);
    return localStringBuffer.toString();
  }

  public String[] getNumbers()
  {
    return this.numbers;
  }

  public String getSMSURI()
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("sms:");
    int i = 1;
    int j = 0;
    if (j < this.numbers.length)
    {
      if (i != 0)
        i = 0;
      while (true)
      {
        localStringBuffer.append(this.numbers[j]);
        if (this.vias[j] != null)
        {
          localStringBuffer.append(";via=");
          localStringBuffer.append(this.vias[j]);
        }
        j++;
        break;
        localStringBuffer.append(',');
      }
    }
    int k;
    if (this.body != null)
    {
      k = 1;
      if (this.subject == null)
        break label195;
    }
    label195: for (int m = 1; ; m = 0)
    {
      if ((k != 0) || (m != 0))
      {
        localStringBuffer.append('?');
        if (k != 0)
        {
          localStringBuffer.append("body=");
          localStringBuffer.append(this.body);
        }
        if (m != 0)
        {
          if (k != 0)
            localStringBuffer.append('&');
          localStringBuffer.append("subject=");
          localStringBuffer.append(this.subject);
        }
      }
      return localStringBuffer.toString();
      k = 0;
      break;
    }
  }

  public String getSubject()
  {
    return this.subject;
  }

  public String[] getVias()
  {
    return this.vias;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.client.result.SMSParsedResult
 * JD-Core Version:    0.6.2
 */