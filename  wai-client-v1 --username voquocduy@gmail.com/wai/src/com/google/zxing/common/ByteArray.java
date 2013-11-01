package com.google.zxing.common;

public final class ByteArray
{
  private static final int INITIAL_SIZE = 32;
  private byte[] bytes;
  private int size;

  public ByteArray()
  {
    this.bytes = null;
    this.size = 0;
  }

  public ByteArray(int paramInt)
  {
    this.bytes = new byte[paramInt];
    this.size = paramInt;
  }

  public ByteArray(byte[] paramArrayOfByte)
  {
    this.bytes = paramArrayOfByte;
    this.size = this.bytes.length;
  }

  public void appendByte(int paramInt)
  {
    if ((this.size == 0) || (this.size >= this.bytes.length))
      reserve(Math.max(32, this.size << 1));
    this.bytes[this.size] = ((byte)paramInt);
    this.size = (1 + this.size);
  }

  public int at(int paramInt)
  {
    return 0xFF & this.bytes[paramInt];
  }

  public boolean isEmpty()
  {
    return this.size == 0;
  }

  public void reserve(int paramInt)
  {
    if ((this.bytes == null) || (this.bytes.length < paramInt))
    {
      byte[] arrayOfByte = new byte[paramInt];
      if (this.bytes != null)
        System.arraycopy(this.bytes, 0, arrayOfByte, 0, this.bytes.length);
      this.bytes = arrayOfByte;
    }
  }

  public void set(int paramInt1, int paramInt2)
  {
    this.bytes[paramInt1] = ((byte)paramInt2);
  }

  public void set(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    this.bytes = new byte[paramInt2];
    this.size = paramInt2;
    for (int i = 0; i < paramInt2; i++)
      this.bytes[i] = paramArrayOfByte[(paramInt1 + i)];
  }

  public int size()
  {
    return this.size;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.zxing.common.ByteArray
 * JD-Core Version:    0.6.2
 */