package com.google.android.apps.unveil.env.gl;

import android.graphics.RectF;
import android.opengl.GLES20;

public class BoundingBox extends RectF
{
  public static final float DEFAULT_OUTLINE_WIDTH_PX = 3.0F;
  private final PVMMatrix PVM;
  private Utils.Color cF;
  private Utils.Color cL;
  private final PassthroughRenderer context;
  private boolean isActive = false;
  private final float lineWidth;
  private ShaderProgram program;
  private final Polygon quad;

  public BoundingBox(PassthroughRenderer paramPassthroughRenderer, ShaderProgram paramShaderProgram, float paramFloat)
  {
    this.context = paramPassthroughRenderer;
    this.program = paramShaderProgram;
    this.lineWidth = paramFloat;
    this.PVM = new PVMMatrix();
    this.quad = Polygon.generateRect(0.0F, 0.0F, 2.0F, 2.0F);
  }

  public void render()
  {
    if ((!this.isActive) || (this.program == null));
    do
    {
      return;
      this.program.use();
      RectF localRectF = this.context.pxToGLCoords(this);
      this.PVM.setScale(localRectF.width() / 2.0F, localRectF.height() / 2.0F);
      this.PVM.setTranslate(localRectF.centerX(), localRectF.centerY());
      this.PVM.bind(this.program.getUniform(ShaderProgram.UNIFORM_SLOT.PVMATRIX));
      this.quad.bind(this.program.getAttribute(ShaderProgram.ATTRIBUTE_SLOT.VERTEX));
      if (this.cF != null)
      {
        GLES20.glUniform4f(this.program.getUniform(ShaderProgram.UNIFORM_SLOT.COLOR), this.cF.r, this.cF.g, this.cF.b, this.cF.a);
        this.quad.draw(Polygon.DrawMode.SOLID);
      }
    }
    while ((this.lineWidth <= 0.0F) || (this.cL == null));
    GLES20.glUniform4f(this.program.getUniform(ShaderProgram.UNIFORM_SLOT.COLOR), this.cL.r, this.cL.g, this.cL.b, this.cL.a);
    GLES20.glLineWidth(this.lineWidth);
    this.quad.draw(Polygon.DrawMode.OUTLINE);
  }

  public void setActive(boolean paramBoolean)
  {
    this.isActive = paramBoolean;
  }

  public void setFillColor(Utils.Color paramColor)
  {
    this.cF = paramColor;
  }

  public void setLineColor(Utils.Color paramColor)
  {
    this.cL = paramColor;
  }

  public void setProgram(ShaderProgram paramShaderProgram)
  {
    this.program = paramShaderProgram;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.BoundingBox
 * JD-Core Version:    0.6.2
 */