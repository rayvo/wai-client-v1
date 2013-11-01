package com.google.android.apps.unveil.env;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamLoader
{
  private static final UnveilLogger LOGGER = new UnveilLogger();
  private static final int STREAM_BUFFER_SIZE_BYTES = 65536;
  private final byte[] buffer = new byte[65536];
  private final ByteArrayOutputStream outStream = new ByteArrayOutputStream();

  public static void copy(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    byte[] arrayOfByte = new byte[65536];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
      if (i == -1)
        break;
      paramOutputStream.write(arrayOfByte, 0, i);
    }
  }

  public static byte[] getByteArrayForStream(InputStream paramInputStream)
  {
    Check.checkNotNull(paramInputStream);
    return new StreamLoader().read(paramInputStream);
  }

  public byte[] read(InputStream paramInputStream)
  {
    try
    {
      while (true)
      {
        int i = paramInputStream.read(this.buffer);
        if (i == -1)
          break;
        this.outStream.write(this.buffer, 0, i);
      }
    }
    catch (IOException localIOException)
    {
      LOGGER.e("Error reading input: %s", new Object[] { localIOException });
      while (true)
      {
        return this.outStream.toByteArray();
        ResourceUtils.closeStream(paramInputStream);
      }
    }
    finally
    {
      ResourceUtils.closeStream(paramInputStream);
    }
  }

  public void reset()
  {
    this.outStream.reset();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.StreamLoader
 * JD-Core Version:    0.6.2
 */