package com.google.protobuf;

import java.io.UnsupportedEncodingException;

public class Internal
{
  public static ByteString bytesDefaultValue(String paramString)
  {
    try
    {
      ByteString localByteString = ByteString.copyFrom(paramString.getBytes("ISO-8859-1"));
      return localByteString;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new IllegalStateException("Java VM does not support a standard character set.", localUnsupportedEncodingException);
    }
  }

  public static boolean isValidUtf8(ByteString paramByteString)
  {
    int i = paramByteString.size();
    int j = 0;
    int m;
    label58: int n;
    int i1;
    while (j < i)
    {
      int k = j + 1;
      m = 0xFF & paramByteString.byteAt(j);
      if (m < 128)
      {
        j = k;
      }
      else
      {
        if ((m < 194) || (m > 244));
        do
        {
          int i2;
          do
          {
            do
              return false;
            while (k >= i);
            j = k + 1;
            n = 0xFF & paramByteString.byteAt(k);
            if ((n < 128) || (n > 191))
              return false;
            if (m <= 223)
              break;
            if (j >= i)
              return false;
            i1 = j + 1;
            i2 = 0xFF & paramByteString.byteAt(j);
          }
          while ((i2 < 128) || (i2 > 191));
          if (m > 239)
            break label198;
        }
        while (((m == 224) && (n < 160)) || ((m == 237) && (n > 159)));
      }
    }
    while (true)
    {
      j = i1;
      break;
      label198: if (i1 >= i)
        break label58;
      int i3 = i1 + 1;
      int i4 = 0xFF & paramByteString.byteAt(i1);
      if ((i4 < 128) || (i4 > 191))
        return false;
      if (((m == 240) && (n < 144)) || ((m == 244) && (n > 143)))
      {
        return false;
        return true;
      }
      i1 = i3;
    }
  }

  public static String stringDefaultValue(String paramString)
  {
    try
    {
      String str = new String(paramString.getBytes("ISO-8859-1"), "UTF-8");
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new IllegalStateException("Java VM does not support a standard character set.", localUnsupportedEncodingException);
    }
  }

  public static abstract interface EnumLite
  {
    public abstract int getNumber();
  }

  public static abstract interface EnumLiteMap<T extends Internal.EnumLite>
  {
    public abstract T findValueByNumber(int paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.Internal
 * JD-Core Version:    0.6.2
 */