package com.google.zxing;

import java.util.Hashtable;

public abstract interface Reader
{
  public abstract Result decode(BinaryBitmap paramBinaryBitmap)
    throws NotFoundException, ChecksumException, FormatException;

  public abstract Result decode(BinaryBitmap paramBinaryBitmap, Hashtable paramHashtable)
    throws NotFoundException, ChecksumException, FormatException;

  public abstract void reset();
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.Reader
 * JD-Core Version:    0.6.2
 */