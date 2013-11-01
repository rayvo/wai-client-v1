package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;

public class PVMMatrix
{
  private static final int ROWS = 4;
  private final float[] buffer = { 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F, 1.0F };

  private void set(int paramInt1, int paramInt2, float paramFloat)
  {
    this.buffer[(paramInt2 + paramInt1 * 4)] = paramFloat;
  }

  public void bind(int paramInt)
  {
    GLES20.glUniformMatrix4fv(paramInt, 1, false, this.buffer, 0);
  }

  public void setScale(float paramFloat1, float paramFloat2)
  {
    set(0, 0, paramFloat1);
    set(1, 1, paramFloat2);
  }

  public void setTranslate(float paramFloat1, float paramFloat2)
  {
    set(3, 0, paramFloat1);
    set(3, 1, paramFloat2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.PVMMatrix
 * JD-Core Version:    0.6.2
 */