package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.nio.IntBuffer;
import java.util.HashMap;
import java.util.Map;

public class ShaderProgram
{
  private final Map<ATTRIBUTE_SLOT, Integer> attributes = new HashMap();
  private final int id;
  private final UnveilLogger logger = new UnveilLogger();
  private final String name;
  private final Map<UNIFORM_SLOT, Integer> uniforms = new HashMap();
  private boolean valid;

  public ShaderProgram(String paramString)
  {
    this.name = paramString;
    this.id = GLES20.glCreateProgram();
    this.valid = true;
  }

  public void attach(Shader paramShader)
  {
    GLES20.glAttachShader(this.id, paramShader.id);
  }

  public int declareAttribute(ATTRIBUTE_SLOT paramATTRIBUTE_SLOT)
  {
    int i = GLES20.glGetAttribLocation(this.id, paramATTRIBUTE_SLOT.toString());
    if (i != -1)
      this.attributes.put(paramATTRIBUTE_SLOT, Integer.valueOf(i));
    while (true)
    {
      return ((Integer)this.attributes.get(paramATTRIBUTE_SLOT)).intValue();
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramATTRIBUTE_SLOT;
      arrayOfObject[1] = this.name;
      localUnveilLogger.e("Unable to find attribute %s in shader %s", arrayOfObject);
    }
  }

  public int declareUniform(UNIFORM_SLOT paramUNIFORM_SLOT)
  {
    int i = GLES20.glGetUniformLocation(this.id, paramUNIFORM_SLOT.toString());
    if (i != -1)
      this.uniforms.put(paramUNIFORM_SLOT, Integer.valueOf(i));
    while (true)
    {
      return ((Integer)this.uniforms.get(paramUNIFORM_SLOT)).intValue();
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramUNIFORM_SLOT;
      arrayOfObject[1] = this.name;
      localUnveilLogger.e("Unable to find attribute %s in shader %s", arrayOfObject);
    }
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

  public int getAttribute(ATTRIBUTE_SLOT paramATTRIBUTE_SLOT)
  {
    return ((Integer)this.attributes.get(paramATTRIBUTE_SLOT)).intValue();
  }

  public int getUniform(UNIFORM_SLOT paramUNIFORM_SLOT)
  {
    return ((Integer)this.uniforms.get(paramUNIFORM_SLOT)).intValue();
  }

  public void link()
  {
    GLES20.glLinkProgram(this.id);
    int[] arrayOfInt = new int[1];
    GLES20.glGetProgramiv(this.id, 35714, IntBuffer.wrap(arrayOfInt));
    if (arrayOfInt[0] == 1)
    {
      UnveilLogger localUnveilLogger2 = this.logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.name;
      localUnveilLogger2.i("Program %s linked successfully.", arrayOfObject2);
      return;
    }
    String str = GLES20.glGetProgramInfoLog(this.id);
    UnveilLogger localUnveilLogger1 = this.logger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = this.name;
    arrayOfObject1[1] = str;
    localUnveilLogger1.e("Program %s did not link:\n%s", arrayOfObject1);
    throw new RuntimeException("Program " + this.name + " failed to link");
  }

  public void release()
  {
    if (this.valid)
    {
      this.valid = false;
      GLES20.glDeleteProgram(this.id);
    }
  }

  public void use()
  {
    GLES20.glUseProgram(this.id);
  }

  public void validate()
  {
    GLES20.glValidateProgram(this.id);
    int[] arrayOfInt = new int[1];
    GLES20.glGetProgramiv(this.id, 35713, IntBuffer.wrap(arrayOfInt));
    if (arrayOfInt[0] == 1)
    {
      UnveilLogger localUnveilLogger2 = this.logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = this.name;
      localUnveilLogger2.i("Program %s validated successfully.", arrayOfObject2);
      return;
    }
    String str = GLES20.glGetShaderInfoLog(this.id);
    UnveilLogger localUnveilLogger1 = this.logger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = this.name;
    arrayOfObject1[1] = str;
    localUnveilLogger1.e("Program %s did not validate:\n%s", arrayOfObject1);
    throw new RuntimeException("Program " + this.name + " failed to validate");
  }

  public static enum ATTRIBUTE_SLOT
  {
    static
    {
      COLOR = new ATTRIBUTE_SLOT("COLOR", 1);
      TEXUV = new ATTRIBUTE_SLOT("TEXUV", 2);
      ATTRIBUTE_SLOT[] arrayOfATTRIBUTE_SLOT = new ATTRIBUTE_SLOT[3];
      arrayOfATTRIBUTE_SLOT[0] = VERTEX;
      arrayOfATTRIBUTE_SLOT[1] = COLOR;
      arrayOfATTRIBUTE_SLOT[2] = TEXUV;
    }
  }

  public static class Shader
  {
    private final int id;
    private final UnveilLogger logger = new UnveilLogger();
    private final String name;
    private boolean valid;

    public Shader(String paramString1, int paramInt, String paramString2)
    {
      this.name = paramString1;
      this.id = GLES20.glCreateShader(paramInt);
      this.valid = true;
      GLES20.glShaderSource(this.id, paramString2);
      GLES20.glCompileShader(this.id);
      int[] arrayOfInt = new int[1];
      GLES20.glGetShaderiv(this.id, 35713, IntBuffer.wrap(arrayOfInt));
      if (arrayOfInt[0] == 1)
      {
        UnveilLogger localUnveilLogger2 = this.logger;
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.name;
        localUnveilLogger2.i("Shader %s compiled successfully.", arrayOfObject2);
        return;
      }
      String str = GLES20.glGetShaderInfoLog(this.id);
      UnveilLogger localUnveilLogger1 = this.logger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = this.name;
      arrayOfObject1[1] = str;
      localUnveilLogger1.e("Shader %s did not compile:\n%s", arrayOfObject1);
      throw new RuntimeException("Shader " + this.name + " failed to compile");
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

    public void release()
    {
      if (this.valid)
      {
        this.valid = false;
        GLES20.glDeleteShader(this.id);
      }
    }
  }

  public static enum UNIFORM_SLOT
  {
    static
    {
      DISTORTION = new UNIFORM_SLOT("DISTORTION", 3);
      TEX_Y = new UNIFORM_SLOT("TEX_Y", 4);
      TEX_UV = new UNIFORM_SLOT("TEX_UV", 5);
      COLOR = new UNIFORM_SLOT("COLOR", 6);
      UNIFORM_SLOT[] arrayOfUNIFORM_SLOT = new UNIFORM_SLOT[7];
      arrayOfUNIFORM_SLOT[0] = PVMATRIX;
      arrayOfUNIFORM_SLOT[1] = TEX_MATRIX;
      arrayOfUNIFORM_SLOT[2] = TEX_OES;
      arrayOfUNIFORM_SLOT[3] = DISTORTION;
      arrayOfUNIFORM_SLOT[4] = TEX_Y;
      arrayOfUNIFORM_SLOT[5] = TEX_UV;
      arrayOfUNIFORM_SLOT[6] = COLOR;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.ShaderProgram
 * JD-Core Version:    0.6.2
 */