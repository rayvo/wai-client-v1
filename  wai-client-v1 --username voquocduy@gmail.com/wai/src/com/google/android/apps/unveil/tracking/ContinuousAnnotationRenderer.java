package com.google.android.apps.unveil.tracking;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Join;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import com.google.android.apps.unveil.env.MorphingRect;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.BasicAnnotation;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ContinuousAnnotationRenderer extends AnnotationRenderer<ContinuousRenderDetails>
{
  private static final int[] ANNOTATION_COLORS = arrayOfInt;
  private static final int BACK_UP_BRACKET_COLOR = -7829368;
  public static final float BLEND_DISTANCE = 640.0F;
  public static final float BLEND_SECONDS = 6.0F;
  private static final int BRACKET_OPACITY = 180;
  private static final int BRACKET_OPACITY_MAGNITUDE = 25;
  private static final float BRACKET_OPACITY_PERIOD = 2.0F;
  private static final int EDGE_LENGTH_DIP = 36;
  private static final long FADE_IN_MS = 400L;
  private static final long FADE_OUT_MS = 200L;
  private static final float MAX_EDGE_PORTION = 0.4F;
  private static final float STROKE_WIDTH_DIP = 4.6F;
  private static final float UNSELECTED_OPACITY_MULTIPLIER = 0.7F;
  private static final float UNSELECTED_SATURATION_MULTIPLIER = 0.8F;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Paint boxPaint;
  private final Map<BasicAnnotation, Integer> colorMap;
  private final ColorQueue colorProvider;
  private final int maxSimultaneousAnnotations;
  private final DestinationProvider provider;

  static
  {
    int[] arrayOfInt = new int[7];
    arrayOfInt[0] = Color.rgb(0, 203, 37);
    arrayOfInt[1] = Color.rgb(51, 105, 232);
    arrayOfInt[2] = Color.rgb(213, 15, 37);
    arrayOfInt[3] = Color.rgb(245, 149, 42);
    arrayOfInt[4] = Color.rgb(245, 129, 202);
    arrayOfInt[5] = -256;
    arrayOfInt[6] = -16711681;
  }

  public ContinuousAnnotationRenderer(View paramView, DestinationProvider paramDestinationProvider, int paramInt)
  {
    super(paramView);
    this.provider = paramDestinationProvider;
    this.maxSimultaneousAnnotations = paramInt;
    this.colorMap = new HashMap();
    this.boxPaint = new Paint();
    this.boxPaint.setStyle(Paint.Style.STROKE);
    this.boxPaint.setStrokeCap(Paint.Cap.ROUND);
    this.boxPaint.setStrokeJoin(Paint.Join.ROUND);
    this.colorProvider = new ColorQueue(ANNOTATION_COLORS, -7829368);
    makeDirty();
  }

  private void drawBracket(Canvas paramCanvas, ContinuousRenderDetails paramContinuousRenderDetails, boolean paramBoolean)
  {
    RectF localRectF = paramContinuousRenderDetails.getRenderPosition(new Size(paramCanvas.getWidth(), paramCanvas.getHeight()));
    if (localRectF == null)
      return;
    int i;
    int j;
    if (paramBoolean)
    {
      float f = (float)SystemClock.elapsedRealtime() / 1000.0F;
      i = (int)('´' + 25.0D * Math.sin(3.141592653589793D * (2.0F * (f / 2.0F))));
      j = (int)(i * paramContinuousRenderDetails.fadeAlpha);
      if (!paramBoolean)
        break label233;
      this.boxPaint.setStrokeWidth(PixelUtils.dipToPix(9.2F, this.view.getContext()));
      this.boxPaint.setColor(-16777216);
      this.boxPaint.setAlpha(j);
      drawBracket(localRectF, this.boxPaint, paramCanvas);
    }
    while (true)
    {
      this.boxPaint.setStrokeWidth(PixelUtils.dipToPix(4.6F, this.view.getContext()));
      int k = paramContinuousRenderDetails.color;
      if (!paramBoolean)
      {
        float[] arrayOfFloat = new float[3];
        Color.colorToHSV(k, arrayOfFloat);
        arrayOfFloat[2] = (0.8F * arrayOfFloat[2]);
        k = Color.HSVToColor(arrayOfFloat);
      }
      this.boxPaint.setColor(k);
      this.boxPaint.setAlpha(j);
      drawBracket(localRectF, this.boxPaint, paramCanvas);
      return;
      i = (int)(0.7F * '´');
      break;
      label233: this.boxPaint.setAntiAlias(false);
    }
  }

  private void drawBracket(RectF paramRectF, Paint paramPaint, Canvas paramCanvas)
  {
    float f = Math.min(PixelUtils.dipToPix(36.0F, this.view.getContext()), 0.4F * paramRectF.width());
    Path localPath = new Path();
    localPath.moveTo(f + paramRectF.left, paramRectF.top);
    localPath.lineTo(paramRectF.left, paramRectF.top);
    localPath.lineTo(paramRectF.left, paramRectF.bottom);
    localPath.lineTo(f + paramRectF.left, paramRectF.bottom);
    localPath.moveTo(paramRectF.right - f, paramRectF.top);
    localPath.lineTo(paramRectF.right, paramRectF.top);
    localPath.lineTo(paramRectF.right, paramRectF.bottom);
    localPath.lineTo(paramRectF.right - f, paramRectF.bottom);
    paramCanvas.drawPath(localPath, this.boxPaint);
  }

  public void addAnnotation(TrackedAnnotation paramTrackedAnnotation)
  {
    if (this.currentlyRenderedAnnotations.size() >= this.maxSimultaneousAnnotations)
    {
      Object localObject = null;
      Iterator localIterator = this.currentlyRenderedAnnotations.keySet().iterator();
      while (localIterator.hasNext())
      {
        TrackedAnnotation localTrackedAnnotation = (TrackedAnnotation)localIterator.next();
        if ((localObject == null) || (localTrackedAnnotation.getPreviewFrameTimestamp() < localObject.getPreviewFrameTimestamp()))
          localObject = localTrackedAnnotation;
      }
      beginRemoval(localObject);
    }
    super.addAnnotation(paramTrackedAnnotation);
  }

  public void beginRemoval(TrackedAnnotation paramTrackedAnnotation)
  {
    ((ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramTrackedAnnotation)).setShouldFadeIn(false);
  }

  public void cancelRemoval(TrackedAnnotation paramTrackedAnnotation)
  {
    ((ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramTrackedAnnotation)).setShouldFadeIn(true);
  }

  protected ContinuousRenderDetails createRenderDetails(TrackedAnnotation paramTrackedAnnotation)
  {
    if (this.colorMap.containsKey(paramTrackedAnnotation));
    for (int i = this.colorProvider.getColor(((Integer)this.colorMap.get(paramTrackedAnnotation)).intValue()); ; i = this.colorProvider.getColor())
      return new ContinuousRenderDetails(paramTrackedAnnotation, i);
  }

  public int getColor(BasicAnnotation paramBasicAnnotation)
  {
    if (!this.currentlyRenderedAnnotations.containsKey(paramBasicAnnotation));
    ContinuousRenderDetails localContinuousRenderDetails;
    do
    {
      return 0;
      localContinuousRenderDetails = (ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramBasicAnnotation);
    }
    while (localContinuousRenderDetails.fadeAlpha <= 0.0F);
    return localContinuousRenderDetails.color;
  }

  public boolean isNoLongerRendering(TrackedAnnotation paramTrackedAnnotation)
  {
    return ((ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramTrackedAnnotation)).fadeAlpha <= 0.0F;
  }

  public void onObjectJumped(TrackedAnnotation paramTrackedAnnotation, RectF paramRectF1, RectF paramRectF2)
  {
    ((ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramTrackedAnnotation)).onJump(paramRectF1, paramRectF2);
  }

  public void onObjectMoved(TrackedAnnotation paramTrackedAnnotation, RectF paramRectF1, RectF paramRectF2)
  {
    ((ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(paramTrackedAnnotation)).onMove(paramRectF1, paramRectF2);
  }

  public void removeAnnotation(TrackedAnnotation paramTrackedAnnotation)
  {
    ContinuousRenderDetails localContinuousRenderDetails = (ContinuousRenderDetails)this.currentlyRenderedAnnotations.remove(paramTrackedAnnotation);
    this.colorProvider.returnColor(localContinuousRenderDetails.color);
    this.colorMap.put(paramTrackedAnnotation, Integer.valueOf(localContinuousRenderDetails.color));
    makeDirty();
  }

  protected void render(Canvas paramCanvas, Map<TrackedAnnotation, ContinuousRenderDetails> paramMap)
  {
    BasicAnnotation localBasicAnnotation = this.provider.computeSelectedAnnotation();
    ContinuousRenderDetails localContinuousRenderDetails1 = (ContinuousRenderDetails)this.currentlyRenderedAnnotations.get(localBasicAnnotation);
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext())
    {
      ContinuousRenderDetails localContinuousRenderDetails2 = (ContinuousRenderDetails)localIterator.next();
      if (localContinuousRenderDetails2 != localContinuousRenderDetails1)
        drawBracket(paramCanvas, localContinuousRenderDetails2, false);
    }
    if (localContinuousRenderDetails1 != null)
      drawBracket(paramCanvas, localContinuousRenderDetails1, true);
  }

  protected class ContinuousRenderDetails extends RenderDetails
  {
    protected MorphingRect blendedTrackedPosition;
    protected final int color;
    private float fadeAlpha;
    private long lastTime;
    private boolean shouldFadeIn;

    public ContinuousRenderDetails(TrackedAnnotation paramInt, int arg3)
    {
      super();
      int i;
      this.color = i;
      this.fadeAlpha = 0.001F;
      this.lastTime = -1L;
      this.shouldFadeIn = true;
      this.blendedTrackedPosition = new MorphingRect(paramInt.getLastKnownPositionInFrame(), 640.0F, 6.0F);
    }

    private RectF getScreenPosition()
    {
      RectF localRectF = new RectF(this.blendedTrackedPosition);
      ContinuousAnnotationRenderer.this.getFrameToCanvas().mapRect(localRectF);
      return localRectF;
    }

    protected void animate(long paramLong)
    {
      this.blendedTrackedPosition.morph();
      float f1;
      float f2;
      if (this.lastTime != -1L)
      {
        f1 = this.fadeAlpha;
        f2 = (float)(paramLong - this.lastTime);
        if (!this.shouldFadeIn)
          break label70;
      }
      label70: for (long l = 400L; ; l = -200L)
      {
        this.fadeAlpha = Math.min(1.0F, Math.max(0.0F, f1 + f2 / (float)l));
        this.lastTime = paramLong;
        return;
      }
    }

    public RectF getRenderPosition(Size paramSize)
    {
      return getScreenPosition();
    }

    public void onJump(RectF paramRectF1, RectF paramRectF2)
    {
      this.blendedTrackedPosition.applyBlended(paramRectF2.left - paramRectF1.left, paramRectF2.top - paramRectF1.top, paramRectF2.right - paramRectF1.right, paramRectF2.bottom - paramRectF1.bottom);
    }

    public void onMove(RectF paramRectF1, RectF paramRectF2)
    {
      this.blendedTrackedPosition.applyInstantly(paramRectF2.left - paramRectF1.left, paramRectF2.top - paramRectF1.top, paramRectF2.right - paramRectF1.right, paramRectF2.bottom - paramRectF1.bottom);
    }

    public void setShouldFadeIn(boolean paramBoolean)
    {
      this.shouldFadeIn = paramBoolean;
    }
  }

  public static abstract interface DestinationProvider
  {
    public abstract BasicAnnotation computeSelectedAnnotation();

    public abstract RectF getDestination(BasicAnnotation paramBasicAnnotation);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.ContinuousAnnotationRenderer
 * JD-Core Version:    0.6.2
 */