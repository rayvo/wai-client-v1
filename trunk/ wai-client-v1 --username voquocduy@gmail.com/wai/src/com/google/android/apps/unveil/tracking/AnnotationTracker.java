package com.google.android.apps.unveil.tracking;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Pair;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.nonstop.VisionGyroProcessor;
import com.google.android.apps.unveil.protocol.ContinuousQueryBuilder;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue.ActiveFrame;
import com.google.android.apps.unveil.protocol.nonstop.EventListener;
import com.google.android.apps.unveil.results.BasicAnnotation;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.ui.GlOverlay.RenderCallback;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.FlowProtos.FlowData;
import com.google.goggles.FlowProtos.FlowData.Builder;
import com.google.goggles.TracingProtos.ProcessorStatus.Type;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.microedition.khronos.opengles.GL10;

public class AnnotationTracker extends FrameProcessor
  implements GlOverlay.RenderCallback, EventListener
{
  private static final float CORRELATION_THRESHOLD_FOR_UPDATE = 0.75F;
  private static final float PIXEL_EPSILON = 0.001F;
  private final HashMap<String, BasicAnnotation> allAnnotationsFromSession;
  private final HashMap<String, TrackedAnnotation> allTrackedAnnotationsFromSession;
  private final ArrayList<TrackedAnnotation> currentRenderedAnnotations;
  private AnnotationEventListener eventListener;
  private final UnveilLogger logger = new UnveilLogger();
  private ObjectTracker objectTracker;
  private final AnnotationRenderer<?> renderer;
  private final boolean shouldAllowServerPositionUpdates;
  private final boolean shouldRemoveMissingResults;
  private final boolean shouldTrackRepeatedResults;
  private final ArrayList<TrackedAnnotation> trackedAnnotations;
  private final VisionGyroProcessor visionGyroProcessor;

  public AnnotationTracker(AnnotationRenderer<?> paramAnnotationRenderer, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, VisionGyroProcessor paramVisionGyroProcessor)
  {
    this.renderer = paramAnnotationRenderer;
    this.shouldRemoveMissingResults = paramBoolean1;
    this.shouldTrackRepeatedResults = paramBoolean2;
    this.shouldAllowServerPositionUpdates = paramBoolean3;
    this.visionGyroProcessor = paramVisionGyroProcessor;
    this.trackedAnnotations = new ArrayList();
    this.currentRenderedAnnotations = new ArrayList();
    this.allTrackedAnnotationsFromSession = new HashMap();
    this.allAnnotationsFromSession = new HashMap();
  }

  private void addToTrackedList(TrackedAnnotation paramTrackedAnnotation, RectF paramRectF, DownsampledImage paramDownsampledImage)
  {
    ObjectTracker.TrackedObject localTrackedObject = this.objectTracker.trackObject(paramRectF, paramDownsampledImage.getTimestamp(), paramDownsampledImage.getBytes());
    localTrackedObject.registerAppearance(paramRectF, paramDownsampledImage.getBytes());
    paramTrackedAnnotation.setTrackedObject(localTrackedObject);
    paramTrackedAnnotation.markPositionAtTime(getLastFrameTime());
    this.trackedAnnotations.add(paramTrackedAnnotation);
    if (this.renderer != null)
      this.renderer.addAnnotation(paramTrackedAnnotation);
    if (this.eventListener != null)
      this.eventListener.onTrackingStarted(paramTrackedAnnotation);
  }

  private void createAnnotation(String paramString, ResultItem paramResultItem, DownsampledImage paramDownsampledImage, RectF paramRectF)
  {
    boolean bool;
    Object localObject;
    if ((paramDownsampledImage != null) && (paramRectF != null))
    {
      bool = true;
      if (!bool)
        break label89;
      TrackedAnnotation localTrackedAnnotation = new TrackedAnnotation(paramResultItem);
      addToTrackedList(localTrackedAnnotation, paramRectF, paramDownsampledImage);
      localObject = localTrackedAnnotation;
      this.allTrackedAnnotationsFromSession.put(paramString, localTrackedAnnotation);
    }
    while (true)
    {
      this.allAnnotationsFromSession.put(paramString, localObject);
      if (this.eventListener != null)
        this.eventListener.onNewResultAppeared((BasicAnnotation)localObject, bool);
      return;
      bool = false;
      break;
      label89: localObject = new BasicAnnotation(paramResultItem);
    }
  }

  private void maybeInitObjectTracker(TimestampedFrame paramTimestampedFrame)
  {
    if (this.objectTracker == null)
    {
      clearTrackedAnnotations();
      this.objectTracker = ObjectTracker.getInstance(paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight(), paramTimestampedFrame.getDownsampleFactor());
    }
  }

  private void maybeReleaseObjectTracker()
  {
    if (this.objectTracker != null)
    {
      this.objectTracker.release();
      this.objectTracker = null;
    }
  }

  private boolean maybeUpdateWithServerPosition(ObjectTracker.TrackedObject paramTrackedObject, RectF paramRectF, DownsampledImage paramDownsampledImage)
  {
    if (paramDownsampledImage.getTimestamp() <= paramTrackedObject.getLastExternalPositionTime());
    while (true)
    {
      return false;
      RectF localRectF = paramTrackedObject.getTrackedPositionInPreviewFrame();
      if ((localRectF.width() < paramRectF.width()) && (localRectF.height() < paramRectF.height()));
      for (int i = 1; (i != 0) || (paramTrackedObject.getCurrentCorrelation() < 0.75F); i = 0)
      {
        paramTrackedObject.registerAppearance(paramRectF, paramDownsampledImage.getBytes());
        paramTrackedObject.setExternalPosition(paramRectF, paramDownsampledImage.getTimestamp());
        return true;
      }
    }
  }

  private void onResult(String paramString, ResultItem paramResultItem, DownsampledImage paramDownsampledImage)
  {
    if (paramDownsampledImage == null)
      this.logger.w("No frame for result %s!", new Object[] { paramString });
    if (!paramResultItem.getAnnotationResult().hasBoundingBoxRequestId())
    {
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramResultItem.getAnnotationResult().toString();
      localUnveilLogger.i("No bounding box resultItem: %s", arrayOfObject);
    }
    Rect localRect = paramResultItem.getBoundingBox();
    RectF localRectF;
    if (localRect != null)
      localRectF = new RectF(localRect.left, localRect.top, localRect.right - 0.001F, localRect.bottom - 0.001F);
    while (this.allAnnotationsFromSession.containsKey(paramString))
      if (this.allTrackedAnnotationsFromSession.containsKey(paramString))
      {
        updateTrackedAnnotation(paramString, paramResultItem, paramDownsampledImage, localRectF);
        return;
        localRectF = null;
      }
      else
      {
        updateBasicAnnotation(paramString, paramResultItem);
        return;
      }
    createAnnotation(paramString, paramResultItem, paramDownsampledImage, localRectF);
  }

  private void onResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    while (true)
    {
      try
      {
        if (this.objectTracker == null)
        {
          this.logger.e("Results returned with no ObjectTracker initialized!", new Object[0]);
          return;
        }
        if (this.shouldRemoveMissingResults)
        {
          localArrayList1 = new ArrayList();
          Iterator localIterator1 = paramList.iterator();
          if (!localIterator1.hasNext())
            break label171;
          Pair localPair = (Pair)localIterator1.next();
          ResultItem localResultItem = (ResultItem)localPair.first;
          String str = localResultItem.getAnnotationResult().getResultId();
          if (localPair.second == null)
            break label165;
          localDownsampledImage = ((ActiveFrameQueue.ActiveFrame)localPair.second).getDownsampledImage();
          onResult(str, localResultItem, localDownsampledImage);
          if (localArrayList1 == null)
            continue;
          BasicAnnotation localBasicAnnotation = (BasicAnnotation)this.allAnnotationsFromSession.get(str);
          if (localBasicAnnotation == null)
            continue;
          localArrayList1.add(localBasicAnnotation);
          continue;
        }
      }
      finally
      {
      }
      ArrayList localArrayList1 = null;
      continue;
      label165: DownsampledImage localDownsampledImage = null;
      continue;
      label171: if (localArrayList1 != null)
      {
        ArrayList localArrayList2 = new ArrayList();
        Iterator localIterator2 = this.trackedAnnotations.iterator();
        while (localIterator2.hasNext())
        {
          TrackedAnnotation localTrackedAnnotation = (TrackedAnnotation)localIterator2.next();
          if (!localArrayList1.contains(localTrackedAnnotation))
            localArrayList2.add(localTrackedAnnotation);
        }
        Iterator localIterator3 = localArrayList2.iterator();
        while (localIterator3.hasNext())
          beginRemoval((TrackedAnnotation)localIterator3.next());
      }
    }
  }

  private void removeFromTrackedList(TrackedAnnotation paramTrackedAnnotation)
  {
    if (this.objectTracker != null)
      paramTrackedAnnotation.getTrackedObject().stopTracking();
    paramTrackedAnnotation.setTrackedObject(null);
    if (this.renderer != null)
      this.renderer.removeAnnotation(paramTrackedAnnotation);
    this.trackedAnnotations.remove(paramTrackedAnnotation);
    if (this.eventListener != null)
      this.eventListener.onTrackingStopped(paramTrackedAnnotation);
  }

  private void updateBasicAnnotation(String paramString, ResultItem paramResultItem)
  {
    BasicAnnotation localBasicAnnotation = (BasicAnnotation)this.allAnnotationsFromSession.get(paramString);
    localBasicAnnotation.updateResultItem(paramResultItem);
    if (this.eventListener != null)
      this.eventListener.onResultUpdated(localBasicAnnotation, false);
  }

  private void updateTrackedAnnotation(String paramString, ResultItem paramResultItem, DownsampledImage paramDownsampledImage, RectF paramRectF)
  {
    TrackedAnnotation localTrackedAnnotation = (TrackedAnnotation)this.allTrackedAnnotationsFromSession.get(paramString);
    long l = localTrackedAnnotation.getPreviewFrameTimestamp();
    localTrackedAnnotation.updateResultItem(paramResultItem);
    int i;
    ObjectTracker.TrackedObject localTrackedObject;
    if (localTrackedAnnotation.getPreviewFrameTimestamp() > l)
    {
      i = 1;
      if ((paramDownsampledImage != null) && (paramRectF != null) && (i != 0))
      {
        localTrackedObject = localTrackedAnnotation.getTrackedObject();
        if ((!this.shouldTrackRepeatedResults) || (localTrackedObject != null))
          break label114;
        addToTrackedList(localTrackedAnnotation, paramRectF, paramDownsampledImage);
      }
    }
    while (true)
    {
      localTrackedAnnotation.updateResultItem(paramResultItem);
      if (this.eventListener != null)
        this.eventListener.onResultUpdated(localTrackedAnnotation, true);
      return;
      i = 0;
      break;
      label114: if ((this.shouldAllowServerPositionUpdates) && (localTrackedObject != null))
      {
        localTrackedAnnotation.markPositionAtTime(getLastFrameTime());
        RectF localRectF1 = localTrackedAnnotation.getLastKnownPositionInFrame();
        boolean bool = maybeUpdateWithServerPosition(localTrackedObject, paramRectF, paramDownsampledImage);
        if (this.renderer != null)
        {
          if (bool)
          {
            localTrackedAnnotation.markPositionAtTime(getLastFrameTime());
            RectF localRectF2 = localTrackedAnnotation.getLastKnownPositionInFrame();
            this.renderer.onObjectJumped(localTrackedAnnotation, localRectF1, localRectF2);
          }
          this.renderer.cancelRemoval(localTrackedAnnotation);
        }
      }
    }
  }

  public void addTrackingDataToQuery(ContinuousQueryBuilder paramContinuousQueryBuilder, long paramLong)
  {
    try
    {
      if (this.objectTracker == null)
        throw new IllegalStateException("Object tracker was set to null, why is a request being made?");
    }
    finally
    {
    }
    Iterator localIterator1 = this.trackedAnnotations.iterator();
    while (localIterator1.hasNext())
      paramContinuousQueryBuilder.addTrackedResult(((TrackedAnnotation)localIterator1.next()).toTrackedResult(paramLong));
    List localList = this.objectTracker.pollAccumulatedFlowData(paramLong);
    FlowProtos.FlowData.Builder localBuilder = FlowProtos.FlowData.newBuilder();
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(localList.size());
    localUnveilLogger.v("Adding %d frames of flowdata", arrayOfObject);
    Iterator localIterator2 = localList.iterator();
    while (localIterator2.hasNext())
      localBuilder.addPrePackedCorrespondences(ByteString.copyFrom((byte[])localIterator2.next()));
    paramContinuousQueryBuilder.addFlowData(localBuilder.build());
  }

  protected void animateAndRender()
  {
    if (this.renderer == null)
      return;
    this.renderer.animate();
    this.currentRenderedAnnotations.clear();
    this.currentRenderedAnnotations.addAll(this.trackedAnnotations);
    Iterator localIterator = this.currentRenderedAnnotations.iterator();
    while (localIterator.hasNext())
    {
      TrackedAnnotation localTrackedAnnotation = (TrackedAnnotation)localIterator.next();
      if (this.renderer.isNoLongerRendering(localTrackedAnnotation))
        removeFromTrackedList(localTrackedAnnotation);
    }
    this.renderer.requestRenderIfDirty();
  }

  protected void beginRemoval(TrackedAnnotation paramTrackedAnnotation)
  {
    if (this.renderer == null)
    {
      removeFromTrackedList(paramTrackedAnnotation);
      return;
    }
    this.renderer.beginRemoval(paramTrackedAnnotation);
  }

  public void clearSessionAndTracked()
  {
    try
    {
      clearTrackedAnnotations();
      this.allTrackedAnnotationsFromSession.clear();
      this.allAnnotationsFromSession.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void clearTrackedAnnotations()
  {
    try
    {
      Iterator localIterator = new ArrayList(this.trackedAnnotations).iterator();
      while (localIterator.hasNext())
        removeFromTrackedList((TrackedAnnotation)localIterator.next());
    }
    finally
    {
    }
    animateAndRender();
  }

  public void drawOverlay(GL10 paramGL10)
  {
    try
    {
      if ((isDebugActive()) && (this.objectTracker != null))
        this.objectTracker.drawOverlay(paramGL10, getViewSize(), getRotation());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void finalize()
  {
    maybeReleaseObjectTracker();
  }

  protected TracingProtos.ProcessorStatus.Type getProcessorType()
  {
    return TracingProtos.ProcessorStatus.Type.ANNOTATION_TRACKER;
  }

  public int getTrackedAnnotationCount()
  {
    try
    {
      int i = this.trackedAnnotations.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public List<TrackedAnnotation> getTrackedAnnotations()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.trackedAnnotations);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onDrawDebug(Canvas paramCanvas)
  {
    try
    {
      if (this.objectTracker != null)
        this.objectTracker.drawDebug(paramCanvas, getFrameToCanvasMatrix());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onHighQualityRequest(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onNetworkError(int paramInt)
  {
  }

  public void onNewFrame(TimestampedFrame paramTimestampedFrame)
  {
  }

  public void onNewRequest(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    onResults(paramList);
  }

  public void onPause()
  {
    try
    {
      maybeReleaseObjectTracker();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    ArrayList localArrayList;
    while (true)
    {
      try
      {
        maybeInitObjectTracker(paramTimestampedFrame);
        if (this.visionGyroProcessor != null)
        {
          arrayOfFloat = this.visionGyroProcessor.getTransformationMatrixAndClearPrevious(getLastFrameTime(), paramTimestampedFrame.getTimestamp());
          this.objectTracker.nextFrame(paramTimestampedFrame.getDownsampledImage().getBytes(), paramTimestampedFrame.getTimestamp(), arrayOfFloat, isDebugActive());
          localArrayList = new ArrayList();
          Iterator localIterator1 = this.trackedAnnotations.iterator();
          if (!localIterator1.hasNext())
            break;
          TrackedAnnotation localTrackedAnnotation = (TrackedAnnotation)localIterator1.next();
          localTrackedAnnotation.markPositionAtTime(paramTimestampedFrame.getTimestamp());
          if (this.renderer != null)
          {
            RectF localRectF1 = localTrackedAnnotation.getPositionInFrameAtTime(getLastFrameTime());
            RectF localRectF2 = localTrackedAnnotation.getLastKnownPositionInFrame();
            if ((localRectF1 != null) && (localRectF2 != null))
              this.renderer.onObjectMoved(localTrackedAnnotation, localRectF1, localRectF2);
          }
          if (localTrackedAnnotation.getTrackedObject().visibleInLastPreviewFrame())
            continue;
          localArrayList.add(localTrackedAnnotation);
          continue;
        }
      }
      finally
      {
      }
      float[] arrayOfFloat = null;
    }
    Iterator localIterator2 = localArrayList.iterator();
    while (localIterator2.hasNext())
      beginRemoval((TrackedAnnotation)localIterator2.next());
    animateAndRender();
  }

  public void onPullReceived()
  {
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
  }

  public void onPushSent(TimestampedFrame.Metadata paramMetadata, byte[] paramArrayOfByte)
  {
  }

  public void onSessionReset()
  {
  }

  public void setListener(AnnotationEventListener paramAnnotationEventListener)
  {
    try
    {
      this.eventListener = paramAnnotationEventListener;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static abstract interface AnnotationEventListener
  {
    public abstract void onNewResultAppeared(BasicAnnotation paramBasicAnnotation, boolean paramBoolean);

    public abstract void onResultUpdated(BasicAnnotation paramBasicAnnotation, boolean paramBoolean);

    public abstract void onTrackingStarted(TrackedAnnotation paramTrackedAnnotation);

    public abstract void onTrackingStopped(TrackedAnnotation paramTrackedAnnotation);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.AnnotationTracker
 * JD-Core Version:    0.6.2
 */