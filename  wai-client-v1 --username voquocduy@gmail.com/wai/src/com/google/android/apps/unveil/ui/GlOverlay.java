package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GlOverlay extends GLSurfaceView
  implements GLSurfaceView.Renderer
{
  private final ArrayList<RenderCallback> renderCallbacks = new ArrayList();

  public GlOverlay(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    setEGLConfigChooser(8, 8, 8, 8, 0, 0);
    setRenderer(this);
    getHolder().setFormat(-2);
    setRenderMode(0);
  }

  private void reset(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    paramGL10.glViewport(0, 0, paramInt1, paramInt2);
    paramGL10.glMatrixMode(5889);
    GLU.gluOrtho2D(paramGL10, 0.0F, 1.0F, 0.0F, 1.0F);
    paramGL10.glLoadIdentity();
    paramGL10.glMatrixMode(5888);
    paramGL10.glLoadIdentity();
  }

  public void addRenderCallback(RenderCallback paramRenderCallback)
  {
    try
    {
      this.renderCallbacks.add(paramRenderCallback);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onDrawFrame(GL10 paramGL10)
  {
    try
    {
      paramGL10.glClear(16640);
      Iterator localIterator = this.renderCallbacks.iterator();
      while (localIterator.hasNext())
      {
        RenderCallback localRenderCallback = (RenderCallback)localIterator.next();
        reset(paramGL10, getWidth(), getHeight());
        localRenderCallback.drawOverlay(paramGL10);
      }
    }
    finally
    {
    }
  }

  public void onSurfaceChanged(GL10 paramGL10, int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
      paramInt2 = 1;
    try
    {
      reset(paramGL10, paramInt1, paramInt2);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onSurfaceCreated(GL10 paramGL10, EGLConfig paramEGLConfig)
  {
    try
    {
      paramGL10.glHint(3152, 4353);
      paramGL10.glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
      paramGL10.glEnable(2884);
      paramGL10.glShadeModel(7425);
      paramGL10.glEnable(2929);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static abstract interface RenderCallback
  {
    public abstract void drawOverlay(GL10 paramGL10);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.GlOverlay
 * JD-Core Version:    0.6.2
 */