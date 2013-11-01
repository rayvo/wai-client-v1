package com.google.android.apps.unveil.tracking;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public abstract class AnnotationRenderer<RenderDetailsType extends RenderDetails>
{
  private static final UnveilLogger logger = new UnveilLogger();
  protected final Map<TrackedAnnotation, RenderDetailsType> currentlyRenderedAnnotations = new HashMap();
  private volatile boolean dirty;
  private Matrix frameToCanvas;
  private final SurfaceHolder surface;
  protected final View view;

  public AnnotationRenderer(View paramView)
  {
    if ((paramView instanceof SurfaceView))
    {
      this.surface = ((SurfaceView)paramView).getHolder();
      this.surface.setFormat(-3);
      this.view = null;
      return;
    }
    this.view = paramView;
    this.surface = null;
  }

  public void addAnnotation(TrackedAnnotation paramTrackedAnnotation)
  {
    if (this.currentlyRenderedAnnotations.containsKey(paramTrackedAnnotation))
    {
      logger.w("Tried to add previously added annotation!", new Object[0]);
      return;
    }
    this.currentlyRenderedAnnotations.put(paramTrackedAnnotation, createRenderDetails(paramTrackedAnnotation));
    makeDirty();
  }

  protected void animate()
  {
    long l = SystemClock.elapsedRealtime();
    Iterator localIterator = this.currentlyRenderedAnnotations.values().iterator();
    while (localIterator.hasNext())
    {
      ((RenderDetails)localIterator.next()).animate(l);
      makeDirty();
    }
  }

  public abstract void beginRemoval(TrackedAnnotation paramTrackedAnnotation);

  public abstract void cancelRemoval(TrackedAnnotation paramTrackedAnnotation);

  protected abstract RenderDetailsType createRenderDetails(TrackedAnnotation paramTrackedAnnotation);

  protected RectF getDirtyArea()
  {
    return null;
  }

  protected Matrix getFrameToCanvas()
  {
    return this.frameToCanvas;
  }

  public boolean isNoLongerRendering(TrackedAnnotation paramTrackedAnnotation)
  {
    return this.currentlyRenderedAnnotations.containsKey(paramTrackedAnnotation);
  }

  protected void makeDirty()
  {
    this.dirty = true;
  }

  public void onObjectJumped(TrackedAnnotation paramTrackedAnnotation, RectF paramRectF1, RectF paramRectF2)
  {
  }

  public void onObjectMoved(TrackedAnnotation paramTrackedAnnotation, RectF paramRectF1, RectF paramRectF2)
  {
  }

  public void removeAnnotation(TrackedAnnotation paramTrackedAnnotation)
  {
    this.currentlyRenderedAnnotations.remove(paramTrackedAnnotation);
    makeDirty();
  }

  public void render(Canvas paramCanvas)
  {
    render(paramCanvas, this.currentlyRenderedAnnotations);
    this.dirty = false;
  }

  protected abstract void render(Canvas paramCanvas, Map<TrackedAnnotation, RenderDetailsType> paramMap);

  public void requestRender()
  {
    if (this.surface != null)
    {
      Canvas localCanvas = this.surface.lockCanvas();
      if (localCanvas == null)
        return;
      render(localCanvas, this.currentlyRenderedAnnotations);
      this.surface.unlockCanvasAndPost(localCanvas);
      this.dirty = false;
      return;
    }
    RectF localRectF = getDirtyArea();
    if (localRectF != null)
    {
      Rect localRect = new Rect();
      localRectF.roundOut(localRect);
      this.view.invalidate(localRect);
      return;
    }
    this.view.invalidate();
  }

  public void requestRenderIfDirty()
  {
    if (this.dirty)
      requestRender();
  }

  public void setFrameToCanvas(Matrix paramMatrix)
  {
    this.frameToCanvas = paramMatrix;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.AnnotationRenderer
 * JD-Core Version:    0.6.2
 */