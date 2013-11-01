package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

public class NV21Quad
{
  private final int height;
  private ByteBuffer nv21Data;
  private final ShaderProgram program;
  Polygon quad;
  private final int size;
  private final FloatBuffer texCoords;
  private final Texture uvTex;
  private boolean valid;
  private final int width;
  private final Texture yTex;

  public NV21Quad(ShaderProgram paramShaderProgram, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    this.program = paramShaderProgram;
    this.width = paramInt1;
    this.height = paramInt2;
    this.size = ((int)Math.pow(2.0D, Math.ceil(Math.log(Math.max(paramInt1, paramInt2)) / Math.log(2.0D))));
    this.quad = Polygon.generateRect(0.0F, 0.0F, 2.0F, 2.0F);
    float f1 = paramInt1 / this.size;
    float f2 = paramInt2 / this.size;
    if (paramBoolean);
    for (this.texCoords = Utils.generateFloatBuffer(new float[] { 0.0F, f2, 0.0F, 0.0F, f1, 0.0F, f1, f2 }); ; this.texCoords = Utils.generateFloatBuffer(new float[] { f1, f2, 0.0F, f2, 0.0F, 0.0F, f1, 0.0F }))
    {
      this.yTex = new Texture(this.size, this.size, Texture.Format.LUMINANCE);
      this.uvTex = new Texture(this.size / 2, this.size / 2, Texture.Format.LUMINANCE_ALPHA);
      this.valid = true;
      return;
    }
  }

  public void LoadNV21Data(byte[] paramArrayOfByte)
  {
    this.nv21Data = ByteBuffer.wrap(paramArrayOfByte);
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

  int getHeight()
  {
    return this.height;
  }

  int getWidth()
  {
    return this.width;
  }

  public void release()
  {
    if (this.valid)
    {
      this.valid = false;
      this.yTex.release();
      this.uvTex.release();
    }
  }

  public void render()
  {
    if (this.nv21Data == null)
      return;
    this.program.use();
    int i = this.program.getAttribute(ShaderProgram.ATTRIBUTE_SLOT.TEXUV);
    GLES20.glVertexAttribPointer(i, 2, 5126, false, 0, this.texCoords);
    GLES20.glEnableVertexAttribArray(i);
    this.nv21Data.position(0);
    this.yTex.bindTU(33984);
    this.yTex.setData(this.nv21Data, this.width, this.height);
    this.nv21Data.position(this.width * this.height);
    this.uvTex.bindTU(33985);
    this.uvTex.setData(this.nv21Data.slice(), this.width / 2, this.height / 2);
    GLES20.glUniform1i(this.program.getUniform(ShaderProgram.UNIFORM_SLOT.TEX_Y), 0);
    GLES20.glUniform1i(this.program.getUniform(ShaderProgram.UNIFORM_SLOT.TEX_UV), 1);
    this.quad.bind(this.program.getAttribute(ShaderProgram.ATTRIBUTE_SLOT.VERTEX));
    this.quad.draw(Polygon.DrawMode.SOLID);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.NV21Quad
 * JD-Core Version:    0.6.2
 */