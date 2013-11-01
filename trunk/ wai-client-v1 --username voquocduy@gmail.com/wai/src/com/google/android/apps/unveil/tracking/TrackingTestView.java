package com.google.android.apps.unveil.tracking;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.PorterDuff.Mode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.UnveilSettings;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.gl.BoundingBox;
import com.google.android.apps.unveil.env.gl.BoundingBoxLayer;
import com.google.android.apps.unveil.env.gl.Utils.Color;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.ui.GlOverlay.RenderCallback;
import java.util.ArrayList;
import java.util.Iterator;
import javax.microedition.khronos.opengles.GL10;

public class TrackingTestView extends SurfaceView
{
  private static final int MAX_NUM_BOXES = 5;
  private static final float MIN_BOX_DIMENSION = 4.0F;
  private RectF boxToTrack;
  private RectF currTouchBox = null;
  private PointF endPoint = null;
  private Matrix frameToCanvas;
  private BoundingBoxLayer glBoxes = null;
  private final UnveilLogger logger = new UnveilLogger();
  private ObjectTracker objectTracker;
  private final ArrayList<ObjectTracker.TrackedObject> objects = new ArrayList();
  private final Paint paint;
  private PointF startPoint = null;
  private final SurfaceHolder surface;
  private final boolean usingGlOverlay;

  public TrackingTestView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.usingGlOverlay = ((UnveilContext)paramContext.getApplicationContext()).getSettings().useGLES2Overlay;
    if (!this.usingGlOverlay)
    {
      this.paint = new Paint();
      this.paint.setStyle(Paint.Style.STROKE);
      this.paint.setStrokeWidth(3.0F);
      this.surface = getHolder();
      this.surface.setFormat(-3);
      setZOrderMediaOverlay(true);
      return;
    }
    this.paint = null;
    this.surface = null;
  }

  private void render()
  {
    Canvas localCanvas = this.surface.lockCanvas();
    if (localCanvas == null)
      return;
    localCanvas.drawColor(0, PorterDuff.Mode.SRC);
    if (this.currTouchBox != null)
    {
      this.paint.setColor(-16711681);
      localCanvas.drawRect(this.currTouchBox, this.paint);
    }
    Iterator localIterator = this.objects.iterator();
    while (localIterator.hasNext())
    {
      ObjectTracker.TrackedObject localTrackedObject = (ObjectTracker.TrackedObject)localIterator.next();
      RectF localRectF = localTrackedObject.getTrackedPositionInPreviewFrame();
      if (localRectF != null)
      {
        this.frameToCanvas.mapRect(localRectF);
        Paint localPaint = this.paint;
        if (localTrackedObject.visibleInLastPreviewFrame());
        for (int i = -16711936; ; i = -65536)
        {
          localPaint.setColor(i);
          localCanvas.drawRect(localRectF, this.paint);
          break;
        }
      }
    }
    this.surface.unlockCanvasAndPost(localCanvas);
  }

  private void reset()
  {
    try
    {
      this.objects.clear();
      if (this.objectTracker != null)
      {
        this.objectTracker.release();
        this.objectTracker = null;
        this.frameToCanvas = null;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static RectF toRectF(PointF paramPointF1, PointF paramPointF2)
  {
    if ((paramPointF1 == null) || (paramPointF2 == null))
      return null;
    RectF localRectF = new RectF(paramPointF1.x, paramPointF1.y, paramPointF2.x, paramPointF2.y);
    localRectF.sort();
    return localRectF;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = 1;
    while (true)
    {
      try
      {
        if (this.objectTracker == null)
        {
          int j = super.onTouchEvent(paramMotionEvent);
          i = j;
          return i;
        }
        if (this.startPoint == null)
        {
          if (paramMotionEvent.getAction() == 0)
          {
            this.startPoint = new PointF(paramMotionEvent.getX(), paramMotionEvent.getY());
            if (this.objects.size() >= 5)
              reset();
          }
          this.currTouchBox = toRectF(this.startPoint, this.endPoint);
          if (!this.usingGlOverlay)
            break label284;
          if (this.currTouchBox == null)
            break label269;
          this.glBoxes.get(0).set(this.currTouchBox);
          this.glBoxes.get(0).setActive(true);
          continue;
        }
      }
      finally
      {
      }
      this.endPoint = new PointF(paramMotionEvent.getX(), paramMotionEvent.getY());
      if (paramMotionEvent.getAction() == i)
      {
        RectF localRectF = toRectF(this.startPoint, this.endPoint);
        Matrix localMatrix = new Matrix();
        this.frameToCanvas.invert(localMatrix);
        localMatrix.mapRect(localRectF);
        this.startPoint = null;
        this.endPoint = null;
        if ((localRectF.width() > 4.0F) && (localRectF.height() > 4.0F))
        {
          this.logger.v("Setting box to %s", new Object[] { localRectF });
          this.currTouchBox = null;
          this.boxToTrack = localRectF;
          continue;
          label269: this.glBoxes.get(0).setActive(false);
          continue;
          label284: render();
        }
      }
    }
  }

  public void setBoundingBoxLayer(BoundingBoxLayer paramBoundingBoxLayer)
  {
    if (paramBoundingBoxLayer != null);
    try
    {
      this.glBoxes = paramBoundingBoxLayer;
      this.glBoxes.generateBoxes(6);
      this.glBoxes.get(0).setLineColor(Utils.Color.WHITE);
      this.glBoxes.get(0).setFillColor(new Utils.Color(0.0F, 0.0F, 0.0F, 0.25F));
      this.glBoxes.get(1).setLineColor(Utils.Color.RED);
      this.glBoxes.get(1).setFillColor(new Utils.Color(1.0F, 0.0F, 0.0F, 0.5F));
      this.glBoxes.get(2).setLineColor(Utils.Color.GREEN);
      this.glBoxes.get(2).setFillColor(new Utils.Color(0.0F, 1.0F, 0.0F, 0.5F));
      this.glBoxes.get(3).setLineColor(Utils.Color.BLUE);
      this.glBoxes.get(3).setFillColor(new Utils.Color(0.0F, 0.0F, 1.0F, 0.5F));
      for (int i = 4; i < 6; i++)
      {
        this.glBoxes.get(i).setLineColor(Utils.Color.BLACK);
        this.glBoxes.get(i).setFillColor(new Utils.Color(0.0F, 0.0F, 0.0F, 0.5F));
      }
      return;
    }
    finally
    {
    }
  }

  public class TrackingTestViewProcessor extends FrameProcessor
    implements GlOverlay.RenderCallback
  {
    private VisionGyro visionGyro;

    public TrackingTestViewProcessor()
    {
    }

    public void drawOverlay(GL10 paramGL10)
    {
      try
      {
        if (TrackingTestView.this.objectTracker != null)
          TrackingTestView.this.objectTracker.drawOverlay(paramGL10, getViewSize(), getRotation());
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    public void onDrawDebug(Canvas paramCanvas)
    {
      if (TrackingTestView.this.objectTracker != null)
        TrackingTestView.this.objectTracker.drawDebug(paramCanvas, TrackingTestView.this.frameToCanvas);
    }

    public void onPause()
    {
      TrackingTestView.this.reset();
    }

    protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
    {
      while (true)
      {
        int j;
        synchronized (TrackingTestView.this)
        {
          if (TrackingTestView.this.objectTracker == null)
          {
            paramTimestampedFrame.getSize();
            this.visionGyro = new VisionGyro();
            TrackingTestView.access$002(TrackingTestView.this, ObjectTracker.getInstance(paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight(), paramTimestampedFrame.getDownsampleFactor()));
            TrackingTestView.access$102(TrackingTestView.this, getFrameToCanvasMatrix());
          }
          DownsampledImage localDownsampledImage = paramTimestampedFrame.getDownsampledImage();
          float[] arrayOfFloat = this.visionGyro.nextFrame(localDownsampledImage.getBytes(), paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight());
          TrackingTestView.this.objectTracker.nextFrame(localDownsampledImage.getBytes(), paramTimestampedFrame.getTimestamp(), arrayOfFloat, isDebugActive());
          if (TrackingTestView.this.usingGlOverlay)
          {
            int i = 0;
            if (i < TrackingTestView.this.objects.size())
            {
              RectF localRectF = ((ObjectTracker.TrackedObject)TrackingTestView.this.objects.get(i)).getTrackedPositionInPreviewFrame();
              if (localRectF != null)
              {
                TrackingTestView.this.glBoxes.get(i + 1).set(localRectF);
                TrackingTestView.this.frameToCanvas.mapRect(TrackingTestView.this.glBoxes.get(i + 1));
              }
              BoundingBox localBoundingBox = TrackingTestView.this.glBoxes.get(i + 1);
              if (localRectF == null)
                break label379;
              bool = true;
              localBoundingBox.setActive(bool);
              i++;
              continue;
            }
            j = 1 + TrackingTestView.this.objects.size();
            if (j < 6)
            {
              if (TrackingTestView.this.glBoxes.get(j) == null)
                break label385;
              TrackingTestView.this.glBoxes.get(j).setActive(false);
              break label385;
            }
          }
          else
          {
            TrackingTestView.this.render();
          }
          if (TrackingTestView.this.boxToTrack != null)
          {
            TrackingTestView.this.objects.add(TrackingTestView.this.objectTracker.trackObject(TrackingTestView.this.boxToTrack, paramTimestampedFrame.getDownsampledImage().getBytes()));
            TrackingTestView.access$602(TrackingTestView.this, null);
          }
          return;
        }
        label379: boolean bool = false;
        continue;
        label385: j++;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.TrackingTestView
 * JD-Core Version:    0.6.2
 */