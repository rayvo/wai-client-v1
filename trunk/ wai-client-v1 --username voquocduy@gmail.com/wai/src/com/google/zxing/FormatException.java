package com.google.zxing;

public final class FormatException extends ReaderException
{
  private static final FormatException instance = new FormatException();

  public static FormatException getFormatInstance()
  {
    return instance;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.FormatException
 * JD-Core Version:    0.6.2
 */