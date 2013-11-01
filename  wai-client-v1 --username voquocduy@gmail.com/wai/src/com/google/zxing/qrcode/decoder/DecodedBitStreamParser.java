package com.google.zxing.qrcode.decoder;

import com.google.zxing.FormatException;
import com.google.zxing.common.BitSource;
import com.google.zxing.common.CharacterSetECI;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.StringUtils;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

final class DecodedBitStreamParser
{
  private static final char[] ALPHANUMERIC_CHARS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 32, 36, 37, 42, 43, 45, 46, 47, 58 };
  private static final int GB2312_SUBSET = 1;

  static DecoderResult decode(byte[] paramArrayOfByte, Version paramVersion, ErrorCorrectionLevel paramErrorCorrectionLevel, Hashtable paramHashtable)
    throws FormatException
  {
    BitSource localBitSource = new BitSource(paramArrayOfByte);
    StringBuffer localStringBuffer = new StringBuffer(50);
    CharacterSetECI localCharacterSetECI = null;
    boolean bool1 = false;
    Vector localVector = new Vector(1);
    while (true)
    {
      Mode localMode;
      try
      {
        if (localBitSource.available() < 4)
        {
          localMode = Mode.TERMINATOR;
          if (!localMode.equals(Mode.TERMINATOR))
          {
            if (localMode.equals(Mode.FNC1_FIRST_POSITION))
              break label372;
            if (!localMode.equals(Mode.FNC1_SECOND_POSITION))
              continue;
            break label372;
          }
          boolean bool2 = localMode.equals(Mode.TERMINATOR);
          if (!bool2)
            continue;
          String str = localStringBuffer.toString();
          if (localVector.isEmpty())
            localVector = null;
          return new DecoderResult(paramArrayOfByte, str, localVector, paramErrorCorrectionLevel);
        }
        localMode = Mode.forBits(localBitSource.readBits(4));
        continue;
        if (localMode.equals(Mode.STRUCTURED_APPEND))
        {
          localBitSource.readBits(16);
          continue;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        throw FormatException.getFormatInstance();
      }
      if (localMode.equals(Mode.ECI))
      {
        localCharacterSetECI = CharacterSetECI.getCharacterSetECIByValue(parseECIValue(localBitSource));
        if (localCharacterSetECI == null)
          throw FormatException.getFormatInstance();
      }
      else if (localMode.equals(Mode.HANZI))
      {
        int j = localBitSource.readBits(4);
        int k = localBitSource.readBits(localMode.getCharacterCountBits(paramVersion));
        if (j == 1)
          decodeHanziSegment(localBitSource, localStringBuffer, k);
      }
      else
      {
        int i = localBitSource.readBits(localMode.getCharacterCountBits(paramVersion));
        if (localMode.equals(Mode.NUMERIC))
        {
          decodeNumericSegment(localBitSource, localStringBuffer, i);
        }
        else if (localMode.equals(Mode.ALPHANUMERIC))
        {
          decodeAlphanumericSegment(localBitSource, localStringBuffer, i, bool1);
        }
        else if (localMode.equals(Mode.BYTE))
        {
          decodeByteSegment(localBitSource, localStringBuffer, i, localCharacterSetECI, localVector, paramHashtable);
        }
        else if (localMode.equals(Mode.KANJI))
        {
          decodeKanjiSegment(localBitSource, localStringBuffer, i);
        }
        else
        {
          throw FormatException.getFormatInstance();
          label372: bool1 = true;
        }
      }
    }
  }

  private static void decodeAlphanumericSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt, boolean paramBoolean)
    throws FormatException
  {
    int i = paramStringBuffer.length();
    while (paramInt > 1)
    {
      int k = paramBitSource.readBits(11);
      paramStringBuffer.append(toAlphaNumericChar(k / 45));
      paramStringBuffer.append(toAlphaNumericChar(k % 45));
      paramInt -= 2;
    }
    if (paramInt == 1)
      paramStringBuffer.append(toAlphaNumericChar(paramBitSource.readBits(6)));
    if (paramBoolean)
    {
      int j = i;
      if (j < paramStringBuffer.length())
      {
        if (paramStringBuffer.charAt(j) == '%')
        {
          if ((j >= -1 + paramStringBuffer.length()) || (paramStringBuffer.charAt(j + 1) != '%'))
            break label137;
          paramStringBuffer.deleteCharAt(j + 1);
        }
        while (true)
        {
          j++;
          break;
          label137: paramStringBuffer.setCharAt(j, '\035');
        }
      }
    }
  }

  private static void decodeByteSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt, CharacterSetECI paramCharacterSetECI, Vector paramVector, Hashtable paramHashtable)
    throws FormatException
  {
    byte[] arrayOfByte = new byte[paramInt];
    if (paramInt << 3 > paramBitSource.available())
      throw FormatException.getFormatInstance();
    for (int i = 0; i < paramInt; i++)
      arrayOfByte[i] = ((byte)paramBitSource.readBits(8));
    String str;
    if (paramCharacterSetECI == null)
      str = StringUtils.guessEncoding(arrayOfByte, paramHashtable);
    try
    {
      while (true)
      {
        paramStringBuffer.append(new String(arrayOfByte, str));
        paramVector.addElement(arrayOfByte);
        return;
        str = paramCharacterSetECI.getEncodingName();
      }
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw FormatException.getFormatInstance();
  }

  private static void decodeHanziSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt)
    throws FormatException
  {
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    if (paramInt > 0)
    {
      int j = paramBitSource.readBits(13);
      int k = j / 96 << 8 | j % 96;
      if (k < 959);
      for (int m = k + 41377; ; m = k + 42657)
      {
        arrayOfByte[i] = ((byte)(0xFF & m >> 8));
        arrayOfByte[(i + 1)] = ((byte)(m & 0xFF));
        i += 2;
        paramInt--;
        break;
      }
    }
    try
    {
      paramStringBuffer.append(new String(arrayOfByte, "GB2312"));
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw FormatException.getFormatInstance();
  }

  private static void decodeKanjiSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt)
    throws FormatException
  {
    byte[] arrayOfByte = new byte[paramInt * 2];
    int i = 0;
    if (paramInt > 0)
    {
      int j = paramBitSource.readBits(13);
      int k = j / 192 << 8 | j % 192;
      if (k < 7936);
      for (int m = k + 33088; ; m = k + 49472)
      {
        arrayOfByte[i] = ((byte)(m >> 8));
        arrayOfByte[(i + 1)] = ((byte)m);
        i += 2;
        paramInt--;
        break;
      }
    }
    try
    {
      paramStringBuffer.append(new String(arrayOfByte, "SJIS"));
      return;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw FormatException.getFormatInstance();
  }

  private static void decodeNumericSegment(BitSource paramBitSource, StringBuffer paramStringBuffer, int paramInt)
    throws FormatException
  {
    while (paramInt >= 3)
    {
      int k = paramBitSource.readBits(10);
      if (k >= 1000)
        throw FormatException.getFormatInstance();
      paramStringBuffer.append(toAlphaNumericChar(k / 100));
      paramStringBuffer.append(toAlphaNumericChar(k / 10 % 10));
      paramStringBuffer.append(toAlphaNumericChar(k % 10));
      paramInt -= 3;
    }
    if (paramInt == 2)
    {
      j = paramBitSource.readBits(7);
      if (j >= 100)
        throw FormatException.getFormatInstance();
      paramStringBuffer.append(toAlphaNumericChar(j / 10));
      paramStringBuffer.append(toAlphaNumericChar(j % 10));
    }
    while (paramInt != 1)
    {
      int j;
      return;
    }
    int i = paramBitSource.readBits(4);
    if (i >= 10)
      throw FormatException.getFormatInstance();
    paramStringBuffer.append(toAlphaNumericChar(i));
  }

  private static int parseECIValue(BitSource paramBitSource)
  {
    int i = paramBitSource.readBits(8);
    if ((i & 0x80) == 0)
      return i & 0x7F;
    if ((i & 0xC0) == 128)
      return paramBitSource.readBits(8) | (i & 0x3F) << 8;
    if ((i & 0xE0) == 192)
      return paramBitSource.readBits(16) | (i & 0x1F) << 16;
    throw new IllegalArgumentException("Bad ECI bits starting with byte " + i);
  }

  private static char toAlphaNumericChar(int paramInt)
    throws FormatException
  {
    if (paramInt >= ALPHANUMERIC_CHARS.length)
      throw FormatException.getFormatInstance();
    return ALPHANUMERIC_CHARS[paramInt];
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.qrcode.decoder.DecodedBitStreamParser
 * JD-Core Version:    0.6.2
 */