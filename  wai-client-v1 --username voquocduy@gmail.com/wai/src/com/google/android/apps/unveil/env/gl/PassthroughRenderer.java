package com.google.android.apps.unveil.env.gl;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.RectF;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView.Renderer;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.apps.unveil.R.raw;
import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class PassthroughRenderer
  implements GLSurfaceView.Renderer
{
  private static final UnveilLogger logger = new UnveilLogger();
  private BoundingBoxLayer boundingBoxes = null;
  private ShaderProgram boxProgram;
  private final RenderCompletedCallback callback;
  private final Display display;
  private Size previewSize;
  private ShaderProgram quadProgram;
  private final Resources resources;
  private NV21Quad screen;
  private Size screenSize;

  public PassthroughRenderer(Context paramContext, RenderCompletedCallback paramRenderCompletedCallback)
  {
    this.resources = paramContext.getResources();
    this.callback = paramRenderCompletedCallback;
    this.display = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
  }

  private void createScreen()
  {
    int i = 1;
    int j = this.display.getRotation();
    ShaderProgram localShaderProgram = this.quadProgram;
    int k = this.previewSize.width;
    int m = this.previewSize.height;
    if ((j == i) || (j == 3));
    while (true)
    {
      this.screen = new NV21Quad(localShaderProgram, k, m, i);
      return;
      i = 0;
    }
  }

  public void LoadNV21Data(byte[] paramArrayOfByte, Size paramSize)
  {
    while (true)
    {
      try
      {
        ShaderProgram localShaderProgram = this.quadProgram;
        if (localShaderProgram == null)
          return;
        if (!paramSize.equals(this.previewSize))
        {
          this.previewSize = paramSize;
          continue;
        }
      }
      finally
      {
      }
      if (this.screen != null)
        this.screen.LoadNV21Data(paramArrayOfByte);
    }
  }

  public BoundingBoxLayer createBoundingBoxLayer()
  {
    try
    {
      if (this.boundingBoxes == null)
        this.boundingBoxes = new BoundingBoxLayer(this, this.boxProgram);
      BoundingBoxLayer localBoundingBoxLayer = this.boundingBoxes;
      return localBoundingBoxLayer;
    }
    finally
    {
    }
  }

  public void onDrawFrame(GL10 paramGL10)
  {
    try
    {
      if (this.previewSize != null)
      {
        if (this.screen != null)
          break label81;
        createScreen();
      }
      while (true)
      {
        GLES20.glDisable(2929);
        GLES20.glEnable(3042);
        GLES20.glColorMask(true, true, true, true);
        GLES20.glBlendFunc(770, 771);
        this.screen.render();
        if (this.boundingBoxes != null)
          this.boundingBoxes.render();
        this.callback.frameRendered();
        return;
        label81: if (!this.previewSize.equals(new Size(this.screen.getWidth(), this.screen.getHeight())))
        {
          this.screen.release();
          createScreen();
        }
      }
    }
    finally
    {
    }
  }

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    this.screenSize = new Size(paramInt1, paramInt2);
  }

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    try
    {
      ShaderProgram.Shader localShader1 = new ShaderProgram.Shader("vShader", 35633, ResourceUtils.readTxtFileFromResource(this.resources, R.raw.passthrough_ver_tex));
      ShaderProgram.Shader localShader2 = new ShaderProgram.Shader("fShader", 35632, ResourceUtils.readTxtFileFromResource(this.resources, R.raw.yuv2rgb));
      ShaderProgram.Shader localShader3 = new ShaderProgram.Shader("vShader", 35633, ResourceUtils.readTxtFileFromResource(this.resources, R.raw.transform_ver_tex));
      ShaderProgram.Shader localShader4 = new ShaderProgram.Shader("fShader", 35632, ResourceUtils.readTxtFileFromResource(this.resources, R.raw.passthrough_color));
      this.quadProgram = new ShaderProgram("NV21QuadShader");
      this.quadProgram.attach(localShader1);
      this.quadProgram.attach(localShader2);
      this.quadProgram.link();
      this.quadProgram.declareAttribute(ShaderProgram.ATTRIBUTE_SLOT.VERTEX);
      this.quadProgram.declareAttribute(ShaderProgram.ATTRIBUTE_SLOT.TEXUV);
      this.quadProgram.declareUniform(ShaderProgram.UNIFORM_SLOT.TEX_Y);
      this.quadProgram.declareUniform(ShaderProgram.UNIFORM_SLOT.TEX_UV);
      this.boxProgram = new ShaderProgram("BoundingBoxShader");
      this.boxProgram.attach(localShader3);
      this.boxProgram.attach(localShader4);
      this.boxProgram.link();
      this.boxProgram.declareAttribute(ShaderProgram.ATTRIBUTE_SLOT.VERTEX);
      this.boxProgram.declareUniform(ShaderProgram.UNIFORM_SLOT.COLOR);
      this.boxProgram.declareUniform(ShaderProgram.UNIFORM_SLOT.PVMATRIX);
      if (this.boundingBoxes != null)
        for (int i = 0; i < this.boundingBoxes.getCount(); i++)
          this.boundingBoxes.get(i).setProgram(this.boxProgram);
      return;
    }
    finally
    {
    }
  }

  public RectF pxToGLCoords(RectF paramRectF)
  {
    if (this.screenSize == null)
    {
      logger.e("pxToGLCoords called before onSurfaceChanged", new Object[0]);
      return new RectF(0.0F, 0.0F, 0.0F, 0.0F);
    }
    RectF localRectF = new RectF();
    localRectF.left = (2.0F * paramRectF.left / this.screenSize.width - 1.0F);
    localRectF.right = (2.0F * paramRectF.right / this.screenSize.width - 1.0F);
    localRectF.top = (1.0F - 2.0F * paramRectF.top / this.screenSize.height);
    localRectF.bottom = (1.0F - 2.0F * paramRectF.bottom / this.screenSize.height);
    return localRectF;
  }

  public static abstract interface RenderCompletedCallback
  {
    public abstract void frameRendered();
  }

  public static abstract interface Renderable
  {
    public abstract void render();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.PassthroughRenderer
 * JD-Core Version:    0.6.2
 */