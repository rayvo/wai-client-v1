package com.google.android.apps.unveil.env.gl;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Utils
{
  public static FloatBuffer allocateFloatBuffer(int paramInt)
  {
    return ByteBuffer.allocateDirect(paramInt * 4).order(ByteOrder.nativeOrder()).asFloatBuffer();
  }

  public static FloatBuffer generateFloatBuffer(float[] paramArrayOfFloat)
  {
    FloatBuffer localFloatBuffer = allocateFloatBuffer(paramArrayOfFloat.length);
    for (int i = 0; i < paramArrayOfFloat.length; i++)
      localFloatBuffer.put(i, paramArrayOfFloat[i]);
    localFloatBuffer.position(0);
    return localFloatBuffer;
  }

  public static class Color
  {
    public static Color BLACK = new Color(0.0F, 0.0F, 0.0F, 1.0F);
    public static Color BLUE = new Color(0.0F, 0.0F, 1.0F, 1.0F);
    public static Color GREEN;
    public static Color RED = new Color(1.0F, 0.0F, 0.0F, 1.0F);
    public static Color WHITE = new Color(1.0F, 1.0F, 1.0F, 1.0F);
    public float a;
    public float b;
    public float g;
    public float r;

    static
    {
      GREEN = new Color(0.0F, 1.0F, 0.0F, 1.0F);
    }

    public Color(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
    {
      this.r = paramFloat1;
      this.g = paramFloat2;
      this.b = paramFloat3;
      this.a = paramFloat4;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.Utils
 * JD-Core Version:    0.6.2
 */