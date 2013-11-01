package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;
import java.nio.FloatBuffer;

public class Polygon
{
  private final FloatBuffer vertexBuffer;

  private Polygon(float[] paramArrayOfFloat)
  {
    this.vertexBuffer = Utils.generateFloatBuffer(paramArrayOfFloat);
  }

  public static Polygon generateRect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    float[] arrayOfFloat = new float[8];
    arrayOfFloat[0] = (-paramFloat3 / 2.0F);
    arrayOfFloat[1] = (-paramFloat4 / 2.0F);
    arrayOfFloat[2] = (-paramFloat3 / 2.0F);
    arrayOfFloat[3] = (paramFloat4 / 2.0F);
    arrayOfFloat[4] = (paramFloat3 / 2.0F);
    arrayOfFloat[5] = (paramFloat4 / 2.0F);
    arrayOfFloat[6] = (paramFloat3 / 2.0F);
    arrayOfFloat[7] = (-paramFloat4 / 2.0F);
    return new Polygon(arrayOfFloat);
  }

  public void bind(int paramInt)
  {
    GLES20.glVertexAttribPointer(paramInt, 2, 5126, false, 0, this.vertexBuffer);
    GLES20.glEnableVertexAttribArray(paramInt);
  }

  public void draw(DrawMode paramDrawMode)
  {
    switch (1.$SwitchMap$com$google$android$apps$unveil$env$gl$Polygon$DrawMode[paramDrawMode.ordinal()])
    {
    default:
      return;
    case 1:
      GLES20.glDrawArrays(2, 0, this.vertexBuffer.capacity() / 2);
      return;
    case 2:
    }
    GLES20.glDrawArrays(6, 0, this.vertexBuffer.capacity() / 2);
  }

  public static enum DrawMode
  {
    static
    {
      DrawMode[] arrayOfDrawMode = new DrawMode[2];
      arrayOfDrawMode[0] = OUTLINE;
      arrayOfDrawMode[1] = SOLID;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.Polygon
 * JD-Core Version:    0.6.2
 */