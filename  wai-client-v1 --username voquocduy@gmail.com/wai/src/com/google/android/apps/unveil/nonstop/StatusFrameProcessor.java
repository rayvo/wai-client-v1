package com.google.android.apps.unveil.nonstop;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.NumberUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.sensors.UnveilSensor;
import com.google.android.apps.unveil.sensors.UnveilSensor.Listener;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.ui.GlOverlay;
import java.util.Vector;

public class StatusFrameProcessor extends FrameProcessor
{
  private static final int CIRCULAR_QUEUE_SIZE = 40;
  private static final int MAX_SUPPORTED_FPS = 40;
  private static final UnveilLogger logger = new UnveilLogger();
  private final CameraManager cameraManager;
  private final int[] circularQueue = new int[40];
  private int currIndex = 0;
  private final Vector<String> debugText = new Vector();
  private final DebugView debugView;
  final int[] fpsBuckets = new int[41];
  private String fpsString = null;
  private final GlOverlay glDebugView;
  private long lastFrameTimestamp = 0L;
  private UnveilSensor.Listener listener;
  private int numFrames = 0;
  private final UnveilSensorProvider sensorProvider;
  private float[] sensorValues;
  private int totalTime = 0;

  public StatusFrameProcessor(CameraManager paramCameraManager, UnveilSensorProvider paramUnveilSensorProvider, DebugView paramDebugView, GlOverlay paramGlOverlay)
  {
    this.cameraManager = paramCameraManager;
    this.sensorProvider = paramUnveilSensorProvider;
    this.debugView = paramDebugView;
    this.glDebugView = paramGlOverlay;
  }

  private String getFpsString()
  {
    if (this.fpsString != null)
      return this.fpsString;
    int i = Math.min(this.numFrames, 40);
    if (i == 0)
      return "";
    Integer[] arrayOfInteger = new Integer[5];
    for (int j = 0; j < arrayOfInteger.length; j++)
      arrayOfInteger[j] = Integer.valueOf(-1);
    int k = 0;
    int m = 0;
    int n = 0;
    if (n <= 40)
    {
      if (this.fpsBuckets[n] == 0);
      while (true)
      {
        n++;
        break;
        k += this.fpsBuckets[n];
        if (k >= m * i / 4)
        {
          arrayOfInteger[m] = Integer.valueOf(n);
          m++;
        }
      }
    }
    float f = 1000.0F / (this.totalTime / i);
    this.fpsString = (NumberUtils.format(f, 2) + "  [" + TextUtils.join(",", arrayOfInteger) + "] " + this.currIndex + "/" + 40);
    return this.fpsString;
  }

  private void handleDelta(int paramInt)
  {
    if (paramInt <= 0)
      return;
    if (this.numFrames > 40)
    {
      int j = this.circularQueue[this.currIndex];
      int k = 1000 / j;
      if (k <= 40)
      {
        int[] arrayOfInt4 = this.fpsBuckets;
        arrayOfInt4[k] = (-1 + arrayOfInt4[k]);
        this.totalTime -= j;
      }
    }
    else
    {
      this.circularQueue[this.currIndex] = paramInt;
      int i = 1000 / paramInt;
      if (i > 40)
        break label144;
      int[] arrayOfInt2 = this.fpsBuckets;
      arrayOfInt2[i] = (1 + arrayOfInt2[i]);
    }
    while (true)
    {
      this.totalTime = (paramInt + this.totalTime);
      this.fpsString = null;
      return;
      int[] arrayOfInt3 = this.fpsBuckets;
      arrayOfInt3[40] = (-1 + arrayOfInt3[40]);
      break;
      label144: int[] arrayOfInt1 = this.fpsBuckets;
      arrayOfInt1[40] = (1 + arrayOfInt1[40]);
    }
  }

  public Vector<String> getDebugText()
  {
    try
    {
      this.debugText.clear();
      this.debugText.add(getFpsString());
      this.debugText.add("Screen: " + getViewSize() + ", Preview: " + getPreviewFrameSize());
      this.debugText.add("Camera state: " + this.cameraManager.getStateName());
      if (this.sensorValues != null)
        this.debugText.add("azimuth: " + this.sensorValues[0] + ", pitch: " + this.sensorValues[1] + ", roll: " + this.sensorValues[2]);
      Vector localVector = this.debugText;
      return localVector;
    }
    finally
    {
    }
  }

  protected void onPause()
  {
    if (this.sensorProvider != null)
      this.sensorProvider.getOrientationSensor().unregisterListener(this.listener);
  }

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    try
    {
      this.currIndex = ((-1 + this.numFrames) % 40);
      long l = paramTimestampedFrame.getTimestamp();
      if (this.numFrames > 0)
      {
        handleDelta((int)(l - this.lastFrameTimestamp));
        if ((this.currIndex == 0) && (logger.shouldShowVerbose()))
          logger.v(getFpsString(), new Object[0]);
      }
      this.lastFrameTimestamp = l;
      this.numFrames = (1 + this.numFrames);
      if (this.debugView.getVisibility() == 0)
      {
        this.debugView.invalidate();
        if (this.glDebugView != null)
          this.glDebugView.requestRender();
      }
      return;
    }
    finally
    {
    }
  }

  protected void onStart()
  {
    if (this.sensorProvider != null)
    {
      this.listener = new UnveilSensor.Listener()
      {
        public void onSet(UnveilSensor paramAnonymousUnveilSensor)
        {
          StatusFrameProcessor.access$002(StatusFrameProcessor.this, paramAnonymousUnveilSensor.getValues());
        }
      };
      this.sensorProvider.getOrientationSensor().registerListener(this.listener);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.StatusFrameProcessor
 * JD-Core Version:    0.6.2
 */