package com.x.google.masf;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ByteArrayInputStreamProvider
  implements InputStreamProvider
{
  private byte[] data;

  public ByteArrayInputStreamProvider(byte[] paramArrayOfByte)
  {
    this.data = paramArrayOfByte;
  }

  public void dispose()
  {
  }

  public InputStream getInputStream()
  {
    return new ByteArrayInputStream(this.data);
  }

  public int getStreamLength()
  {
    return this.data.length;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.masf.ByteArrayInputStreamProvider
 * JD-Core Version:    0.6.2
 */