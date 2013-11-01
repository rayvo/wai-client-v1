package com.google.android.apps.unveil.env.gl;

import android.opengl.GLES20;

public class BoundingBoxLayer
{
  private BoundingBox[] boxes;
  private final PassthroughRenderer context;
  private final ShaderProgram program;

  public BoundingBoxLayer(PassthroughRenderer paramPassthroughRenderer, ShaderProgram paramShaderProgram)
  {
    this.context = paramPassthroughRenderer;
    this.program = paramShaderProgram;
  }

  public void generateBoxes(int paramInt)
  {
    try
    {
      this.boxes = new BoundingBox[paramInt];
      for (int i = 0; i < paramInt; i++)
        this.boxes[i] = new BoundingBox(this.context, this.program, 3.0F);
      return;
    }
    finally
    {
    }
  }

  public BoundingBox get(int paramInt)
  {
    try
    {
      BoundingBox localBoundingBox = this.boxes[paramInt];
      return localBoundingBox;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public int getCount()
  {
    try
    {
      int i = this.boxes.length;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void render()
  {
    try
    {
      GLES20.glColorMask(true, true, true, false);
      for (int i = 0; i < this.boxes.length; i++)
        if (this.boxes[i] != null)
          this.boxes[i].render();
      return;
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.BoundingBoxLayer
 * JD-Core Version:    0.6.2
 */