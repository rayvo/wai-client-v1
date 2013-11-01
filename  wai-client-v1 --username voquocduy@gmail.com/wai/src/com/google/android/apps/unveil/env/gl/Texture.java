package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

public class Texture
{
  private ByteBuffer buffer;
  int format;
  private final int id;
  boolean valid;

  private Texture()
  {
    int[] arrayOfInt = new int[1];
    GLES20.glGenTextures(1, IntBuffer.wrap(arrayOfInt));
    this.id = arrayOfInt[0];
    GLES20.glBindTexture(36197, this.id);
    GLES20.glTexParameteri(36197, 10241, 9728);
    GLES20.glTexParameteri(36197, 10240, 9728);
    this.valid = true;
  }

  public Texture(int paramInt1, int paramInt2, Format paramFormat)
  {
    int[] arrayOfInt = new int[1];
    GLES20.glGenTextures(1, IntBuffer.wrap(arrayOfInt));
    this.id = arrayOfInt[0];
    switch (1.$SwitchMap$com$google$android$apps$unveil$env$gl$Texture$Format[paramFormat.ordinal()])
    {
    default:
      throw new IllegalArgumentException("Unsupported format: " + paramFormat);
    case 1:
      this.format = 6409;
    case 2:
    }
    for (this.buffer = ByteBuffer.allocate(1 * (paramInt1 * paramInt2)); ; this.buffer = ByteBuffer.allocate(2 * (paramInt1 * paramInt2)))
    {
      GLES20.glBindTexture(3553, this.id);
      GLES20.glTexImage2D(3553, 0, this.format, paramInt1, paramInt2, 0, this.format, 5121, this.buffer);
      GLES20.glTexParameteri(3553, 10241, 9728);
      GLES20.glTexParameteri(3553, 10240, 9728);
      this.valid = true;
      return;
      this.format = 6410;
    }
  }

  public static Texture constructOesTexture()
  {
    return new Texture();
  }

  public void bindTU(int paramInt)
  {
    GLES20.glActiveTexture(paramInt);
    GLES20.glBindTexture(3553, this.id);
  }

  protected void finalize()
    throws Throwable
  {
    try
    {
      release();
      return;
    }
    finally
    {
      super.finalize();
    }
  }

  public int getId()
  {
    return this.id;
  }

  public void release()
  {
    if (this.valid)
    {
      this.valid = false;
      int[] arrayOfInt = new int[1];
      arrayOfInt[0] = this.id;
      GLES20.glDeleteTextures(1, IntBuffer.wrap(arrayOfInt));
    }
  }

  public void setData(ByteBuffer paramByteBuffer, int paramInt1, int paramInt2)
  {
    GLES20.glTexSubImage2D(3553, 0, 0, 0, paramInt1, paramInt2, this.format, 5121, paramByteBuffer);
  }

  public static enum Format
  {
    static
    {
      Format[] arrayOfFormat = new Format[4];
      arrayOfFormat[0] = LUMINANCE;
      arrayOfFormat[1] = LUMINANCE_ALPHA;
      arrayOfFormat[2] = RGB;
      arrayOfFormat[3] = RGBA;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.Texture
 * JD-Core Version:    0.6.2
 */