package com.google.android.apps.unveil.tracking;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import com.google.android.apps.unveil.env.BorderedText;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.DebugView.RenderCallback;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.ui.GlOverlay.RenderCallback;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javax.microedition.khronos.opengles.GL10;

public class TrackingEvaluator extends FrameProcessor
  implements DebugView.RenderCallback, GlOverlay.RenderCallback
{
  private static final String GT_FILE = "gt.txt";
  public static final String MANIFEST_FILE = "objects.txt";
  private static final String NULL_BOX = "NaN,NaN,NaN,NaN";
  private static final String TRACK_TEMPLATE = "track_%s.txt";
  private final EvaluationFinishedCallback callback;
  private final HashMap<String, RectF> currentGroundTruthPositions;
  private final HashMap<String, ObjectTracker.TrackedObject> currentTrackedObjects;
  private final HashMap<String, RectF> currentTrackedPositions;
  private final HashMap<String, ObjectTrack> groundTruthTracks;
  private final UnveilLogger logger = new UnveilLogger();
  private int numFramesProcessed = 0;
  private int numTrackedPositionsProcessed = 0;
  private ObjectTracker objectTracker;
  final Paint paint = new Paint();
  private final Resources resources;
  private final String sequenceDir;
  final BorderedText text = new BorderedText(40.0F);
  private float totalScore = 0.0F;
  private final BufferedWriter trackingOutput;
  private VisionGyro visionGyro;

  public TrackingEvaluator(Resources paramResources, String paramString1, String paramString2, EvaluationFinishedCallback paramEvaluationFinishedCallback)
  {
    this.resources = paramResources;
    String[] arrayOfString = paramString1.split("/");
    this.sequenceDir = arrayOfString[(-1 + arrayOfString.length)];
    this.groundTruthTracks = loadAllGroundTruths(paramString1);
    this.currentTrackedPositions = new HashMap();
    this.currentGroundTruthPositions = new HashMap();
    this.currentTrackedObjects = new HashMap();
    if (paramString2 != null);
    for (BufferedWriter localBufferedWriter = getOutputWriter(paramString2); ; localBufferedWriter = null)
    {
      this.trackingOutput = localBufferedWriter;
      this.callback = paramEvaluationFinishedCallback;
      return;
    }
  }

  static float getArea(RectF paramRectF)
  {
    return paramRectF.width() * paramRectF.height();
  }

  private BufferedWriter getOutputWriter(String paramString)
  {
    try
    {
      BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(paramString));
      return localBufferedWriter;
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException("Error opening output file: " + paramString);
  }

  private HashMap<String, ObjectTrack> loadAllGroundTruths(String paramString)
  {
    HashMap localHashMap = new HashMap();
    String str1 = paramString + "/" + "objects.txt";
    try
    {
      boolean bool = ResourceUtils.fromAssets(str1);
      String str3 = ResourceUtils.getPathFromPrefixedString(str1);
      this.logger.v("Loading file from " + str3, new Object[0]);
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(ResourceUtils.getInputStreamForFile(this.resources, bool, str3)));
      while (true)
      {
        String str4 = localBufferedReader.readLine();
        if (str4 == null)
        {
          localBufferedReader.close();
          return localHashMap;
        }
        if (str4.startsWith("object"))
        {
          this.logger.v("Object! '" + str4 + "'", new Object[0]);
          String[] arrayOfString = str4.split(" ");
          String str5 = new StringTokenizer(arrayOfString[1], ".").nextToken();
          String str6 = paramString + "/" + String.format("track_%s.txt", new Object[] { str5 });
          int i = Integer.parseInt(arrayOfString[2]);
          int j = Integer.parseInt(arrayOfString[3]);
          UnveilLogger localUnveilLogger = this.logger;
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = str5;
          arrayOfObject[1] = Integer.valueOf(i);
          arrayOfObject[2] = Integer.valueOf(j);
          localUnveilLogger.i("object %s, Start frame: %d, End frame: %d", arrayOfObject);
          ObjectTrack localObjectTrack2 = new ObjectTrack(str6, i, j);
          localHashMap.put(str5, localObjectTrack2);
        }
      }
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      String str2 = paramString + "/" + "gt.txt";
      this.logger.w("Couldn't find file %s, trying to load %s instead.", new Object[] { str1, str2 });
      ObjectTrack localObjectTrack1 = new ObjectTrack(str2, 0, -1);
      localHashMap.put("gt", localObjectTrack1);
      return localHashMap;
    }
    catch (IOException localIOException)
    {
    }
    throw new RuntimeException("Exception reading " + str1);
  }

  static float matchScore(RectF paramRectF1, RectF paramRectF2, RectF paramRectF3)
  {
    int i;
    int j;
    float f;
    if ((paramRectF1 == null) || (!RectF.intersects(paramRectF1, paramRectF3)))
    {
      i = 1;
      if (paramRectF2 != null)
      {
        boolean bool2 = RectF.intersects(paramRectF2, paramRectF3);
        j = 0;
        if (bool2);
      }
      else
      {
        j = 1;
      }
      if ((i == 0) || (j == 0))
        break label56;
      f = 1.0F;
    }
    label56: RectF localRectF;
    boolean bool1;
    do
    {
      do
      {
        do
        {
          return f;
          i = 0;
          break;
          f = 0.0F;
        }
        while (i != 0);
        f = 0.0F;
      }
      while (j != 0);
      paramRectF1.intersect(paramRectF3);
      paramRectF2.intersect(paramRectF3);
      localRectF = new RectF(paramRectF1);
      bool1 = localRectF.intersect(paramRectF2);
      f = 0.0F;
    }
    while (!bool1);
    return 2.0F * getArea(localRectF) / (getArea(paramRectF1) + getArea(paramRectF2));
  }

  private void maybeInitObjectTracker(TimestampedFrame paramTimestampedFrame)
  {
    if (this.objectTracker == null)
    {
      this.visionGyro = new VisionGyro();
      this.objectTracker = ObjectTracker.getInstance(paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight(), paramTimestampedFrame.getDownsampleFactor());
    }
  }

  private void maybeWritePosition(RectF paramRectF)
  {
    if (this.trackingOutput == null)
      return;
    Object[] arrayOfObject;
    if (paramRectF != null)
    {
      arrayOfObject = new Object[4];
      arrayOfObject[0] = Float.valueOf(paramRectF.left);
      arrayOfObject[1] = Float.valueOf(paramRectF.top);
      arrayOfObject[2] = Float.valueOf(paramRectF.right);
      arrayOfObject[3] = Float.valueOf(paramRectF.bottom);
    }
    for (String str = String.format("%.2f,%.2f,%.2f,%.2f\n", arrayOfObject); ; str = "NaN,NaN,NaN,NaN")
    {
      this.logger.i("position: %s", new Object[] { str });
      try
      {
        this.trackingOutput.write(str);
        this.trackingOutput.flush();
        return;
      }
      catch (IOException localIOException)
      {
        this.logger.e("Exception!", new Object[] { localIOException });
        return;
      }
    }
  }

  private void processTrackForFrame(String paramString, ObjectTrack paramObjectTrack, byte[] paramArrayOfByte)
  {
    RectF localRectF1 = paramObjectTrack.getPositionAtFrame(this.numFramesProcessed);
    this.currentGroundTruthPositions.put(paramString, localRectF1);
    if (this.numFramesProcessed == paramObjectTrack.startFrame)
    {
      UnveilLogger localUnveilLogger2 = this.logger;
      Object[] arrayOfObject2 = new Object[3];
      arrayOfObject2[0] = paramString;
      arrayOfObject2[1] = Integer.valueOf(this.numFramesProcessed);
      arrayOfObject2[2] = localRectF1;
      localUnveilLogger2.i("Track [%s] starting at %d: %s", arrayOfObject2);
      ObjectTracker.TrackedObject localTrackedObject2 = this.objectTracker.trackObject(localRectF1, paramArrayOfByte);
      this.currentTrackedObjects.put(paramString, localTrackedObject2);
    }
    ObjectTracker.TrackedObject localTrackedObject1 = (ObjectTracker.TrackedObject)this.currentTrackedObjects.get(paramString);
    RectF localRectF2 = localTrackedObject1.getTrackedPositionInPreviewFrame();
    this.currentTrackedPositions.put(paramString, localRectF2);
    Size localSize = getPreviewFrameSize();
    RectF localRectF3 = new RectF(0.0F, 0.0F, localSize.width, localSize.height);
    this.totalScore += matchScore(localRectF1, localRectF2, localRectF3);
    this.numTrackedPositionsProcessed = (1 + this.numTrackedPositionsProcessed);
    if (this.numFramesProcessed == paramObjectTrack.endFrame)
    {
      UnveilLogger localUnveilLogger1 = this.logger;
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = paramString;
      arrayOfObject1[1] = Integer.valueOf(this.numFramesProcessed);
      arrayOfObject1[2] = localRectF1;
      localUnveilLogger1.i("Track [%s] ending at %d: %s", arrayOfObject1);
      this.currentTrackedObjects.remove(paramString);
      localTrackedObject1.stopTracking();
    }
  }

  public void draw(Canvas paramCanvas)
  {
    while (true)
    {
      try
      {
        if (this.numFramesProcessed != 0)
        {
          ObjectTracker localObjectTracker = this.objectTracker;
          if (localObjectTracker != null);
        }
        else
        {
          return;
        }
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(6.0F);
        Iterator localIterator = this.currentGroundTruthPositions.keySet().iterator();
        if (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          RectF localRectF1 = (RectF)this.currentGroundTruthPositions.get(str);
          if (localRectF1 != null)
          {
            this.paint.setColor(-65536);
            RectF localRectF2 = new RectF(localRectF1);
            getFrameToCanvasMatrix().mapRect(localRectF2);
            paramCanvas.drawRect(localRectF2, this.paint);
          }
          RectF localRectF3 = (RectF)this.currentTrackedPositions.get(str);
          if (localRectF3 == null)
            continue;
          this.paint.setColor(-16711936);
          RectF localRectF4 = new RectF(localRectF3);
          getFrameToCanvasMatrix().mapRect(localRectF4);
          paramCanvas.drawRect(localRectF4, this.paint);
          continue;
        }
      }
      finally
      {
      }
      float f1 = 0.0F + this.text.getTextSize();
      this.text.drawText(paramCanvas, 0.0F, f1, "sequence: " + this.sequenceDir);
      float f2 = f1 + this.text.getTextSize();
      this.text.drawText(paramCanvas, 0.0F, f2, getTimeStats());
      float f3 = f2 + this.text.getTextSize();
      this.text.drawText(paramCanvas, 0.0F, f3, "Average score: " + getPerformance() + ", frame: " + this.numFramesProcessed);
    }
  }

  public void drawOverlay(GL10 paramGL10)
  {
    if (this.objectTracker != null)
      this.objectTracker.drawOverlay(paramGL10, getViewSize(), getRotation());
  }

  public float getPerformance()
  {
    return this.totalScore / this.numTrackedPositionsProcessed;
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

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    try
    {
      maybeInitObjectTracker(paramTimestampedFrame);
      DownsampledImage localDownsampledImage = paramTimestampedFrame.getDownsampledImage();
      float[] arrayOfFloat = this.visionGyro.nextFrame(localDownsampledImage.getBytes(), paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight());
      this.objectTracker.nextFrame(localDownsampledImage.getBytes(), paramTimestampedFrame.getTimestamp(), arrayOfFloat, false);
      this.currentGroundTruthPositions.clear();
      this.currentTrackedPositions.clear();
      int i = 0;
      Iterator localIterator = this.groundTruthTracks.keySet().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        ObjectTrack localObjectTrack = (ObjectTrack)this.groundTruthTracks.get(str);
        if ((this.numFramesProcessed >= localObjectTrack.startFrame) && (this.numFramesProcessed <= localObjectTrack.endFrame))
          processTrackForFrame(str, localObjectTrack, paramTimestampedFrame.getDownsampledImage().getBytes());
        i = Math.max(i, localObjectTrack.endFrame);
      }
      if (this.numFramesProcessed >= i)
      {
        BufferedWriter localBufferedWriter = this.trackingOutput;
        if (localBufferedWriter == null);
      }
      try
      {
        this.trackingOutput.close();
        this.objectTracker.release();
        this.objectTracker = null;
        this.callback.onEvaluationFinished();
        this.numFramesProcessed = (1 + this.numFramesProcessed);
        return;
      }
      catch (IOException localIOException)
      {
        while (true)
          this.logger.e(localIOException, "Exception!", new Object[0]);
      }
    }
    finally
    {
    }
  }

  protected void onShutdown()
  {
    if (this.objectTracker != null)
    {
      this.objectTracker.release();
      this.objectTracker = null;
    }
  }

  public static abstract interface EvaluationFinishedCallback
  {
    public abstract void onEvaluationFinished();
  }

  private class ObjectTrack
  {
    final int endFrame;
    private final ArrayList<RectF> positions;
    final int startFrame;

    ObjectTrack(String paramInt1, int paramInt2, int arg4)
    {
      this.startFrame = paramInt2;
      this.positions = new ArrayList();
      try
      {
        boolean bool1 = ResourceUtils.fromAssets(paramInt1);
        String str1 = ResourceUtils.getPathFromPrefixedString(paramInt1);
        TrackingEvaluator.this.logger.v("Loading file from " + str1, new Object[0]);
        BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(ResourceUtils.getInputStreamForFile(TrackingEvaluator.this.resources, bool1, str1)));
        String str2 = localBufferedReader.readLine();
        if (str2 == null)
        {
          localBufferedReader.close();
          int i;
          if (i != -1)
          {
            this.endFrame = i;
            int j = 1 + (this.endFrame - this.startFrame);
            if ((paramInt2 <= i) && (j == this.positions.size()))
              return;
            UnveilLogger localUnveilLogger = TrackingEvaluator.this.logger;
            Object[] arrayOfObject = new Object[5];
            arrayOfObject[0] = paramInt1;
            arrayOfObject[1] = Integer.valueOf(paramInt2);
            arrayOfObject[2] = Integer.valueOf(i);
            arrayOfObject[3] = Integer.valueOf(this.positions.size());
            arrayOfObject[4] = Integer.valueOf(j);
            localUnveilLogger.i("Track %s invalid! start:%d end:%d, read %d positions when expecting %d.", arrayOfObject);
            throw new RuntimeException("Invalid track!");
          }
        }
        else
        {
          boolean bool2 = str2.startsWith("NaN,NaN,NaN,NaN");
          localRectF = null;
          if (!bool2)
          {
            StringTokenizer localStringTokenizer = new StringTokenizer(str2, ", ");
            f1 = Float.parseFloat(localStringTokenizer.nextToken());
            f2 = Float.parseFloat(localStringTokenizer.nextToken());
            f3 = Float.parseFloat(localStringTokenizer.nextToken());
            f4 = Float.parseFloat(localStringTokenizer.nextToken());
            if ((f1 > f3) || (f2 > f4))
              throw new RuntimeException("Invalid rectangle! '" + str2 + "'");
          }
        }
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        while (true)
        {
          float f1;
          float f2;
          float f3;
          float f4;
          throw new RuntimeException("Couldn't find file: " + paramInt1);
          RectF localRectF = new RectF(f1, f2, f3, f4);
          this.positions.add(localRectF);
        }
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Exception reading " + paramInt1);
      }
      this.endFrame = (-1 + this.positions.size() + this.startFrame);
    }

    RectF getPositionAtFrame(int paramInt)
    {
      int i = paramInt - this.startFrame;
      if ((i >= this.positions.size()) || (i < 0))
        return null;
      return (RectF)this.positions.get(i);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.tracking.TrackingEvaluator
 * JD-Core Version:    0.6.2
 */