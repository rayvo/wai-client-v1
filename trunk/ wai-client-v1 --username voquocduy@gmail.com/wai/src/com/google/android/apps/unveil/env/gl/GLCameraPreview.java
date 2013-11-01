package com.google.android.apps.unveil.env.gl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.UnveilSettings;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;

public class GLCameraPreview extends GLSurfaceView
{
  private final PassthroughRenderer renderer;

  public GLCameraPreview(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    if (!((UnveilContext)paramContext.getApplicationContext()).getSettings().useGLES2Overlay)
    {
      this.renderer = null;
      setRenderer(null);
      return;
    }
    setVisibility(0);
    setZOrderMediaOverlay(true);
    setEGLContextClientVersion(2);
    setEGLConfigChooser(8, 8, 8, 8, 0, 0);
    getHolder().setFormat(-3);
    this.renderer = new PassthroughRenderer(paramContext, new PassthroughRenderer.RenderCompletedCallback()
    {
      public void frameRendered()
      {
      }
    });
    setRenderer(this.renderer);
    setRenderMode(0);
  }

  public BoundingBoxLayer getBoundingBoxLayer()
  {
    if (this.renderer != null)
      return this.renderer.createBoundingBoxLayer();
    return null;
  }

  public FrameProcessor getFrameLoader()
  {
    return new GLPreviewFrameLoader();
  }

  public void handleClick()
  {
    if (getVisibility() == 4)
      setVisibility(0);
    while (getVisibility() != 0)
      return;
    setVisibility(4);
  }

  class GLPreviewFrameLoader extends FrameProcessor
  {
    GLPreviewFrameLoader()
    {
    }

    protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
    {
      if ((GLCameraPreview.this.getVisibility() == 0) && (GLCameraPreview.this.renderer != null))
      {
        GLCameraPreview.this.renderer.LoadNV21Data(paramTimestampedFrame.getRawData(), new Size(paramTimestampedFrame.getWidth(), paramTimestampedFrame.getHeight()));
        GLCameraPreview.this.requestRender();
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.gl.GLCameraPreview
 * JD-Core Version:    0.6.2
 */