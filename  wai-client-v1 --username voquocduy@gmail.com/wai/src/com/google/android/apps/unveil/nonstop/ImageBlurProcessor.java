package com.google.android.apps.unveil.nonstop;

import android.os.SystemClock;
import com.google.android.apps.unveil.env.NumberUtils;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.CameraManager;
import com.google.android.apps.unveil.sensors.CameraManager.FocusCallback;
import com.google.goggles.TracingProtos.ProcessorStatus.Type;
import java.util.Vector;

public class ImageBlurProcessor extends FrameProcessor
  implements CameraManager.FocusCallback
{
  private static final float BRIGHTNESS_FACTOR = 1.2F;
  private static final float DISTANCE_WEIGHTING_POWER = 1.5F;
  private static final long FATIGUE_BASE_MS = 4000L;
  private static final int FATIGUE_RESET_DIFF_PERCENT = 30;
  protected static final long FOCUS_DELAY_MS = 150L;
  private static final float FOCUS_FATIGUE_FACTOR = 1.5F;
  private static final int MAX_FOCUSES_WITH_SAME_IMAGE = 3;
  protected static final long MAX_TIME_BETWEEN_FOCUS_REQUESTS_MS = 8000L;
  protected static final int MIN_DIFF_PERCENT_FOCUS = 8;
  protected static final long MIN_TIME_BETWEEN_EXPOSURE_FOCUS_REQUESTS_MS = 100L;
  private static final float SCALE_INPUT_FACTOR = 1.5F;
  private final CameraManager cameraManager;
  private final Stopwatch consecutiveBlurTimer = new Stopwatch();
  private final Vector<String> debugText = new Vector();
  private long focusFatigue;
  private int[] focusedSignature;
  private int focusesWithSameImage = 0;
  private volatile boolean justFocused = false;
  private long lastBlurDuration;
  private int[] lastBuckets;
  private long lastDiffDuration = 0L;
  private int lastDiffPercent = 0;
  private final Stopwatch lastFocusTimer = new Stopwatch();
  private boolean lastFrameBlurred;
  private final UnveilLogger logger = new UnveilLogger();
  private long maxTimeBasedOffHistogram;
  private boolean requestedFirstFocus = false;
  private boolean runSinceLastTime = false;

  public ImageBlurProcessor(CameraManager paramCameraManager)
  {
    this.cameraManager = paramCameraManager;
  }

  private long getInterFocusDelayFromExposure()
  {
    if (this.lastBuckets == null)
    {
      this.logger.w("Hopefully this is a test!", new Object[0]);
      return 8000L;
    }
    int i = this.lastBuckets.length;
    float f1 = 1.0F + NumberUtils.getMeanIndex(this.lastBuckets);
    float f2 = i / 2.0F;
    float f3 = Math.abs(f1 - f2);
    if (f1 > f2)
      f3 *= 1.2F;
    float f4 = i - f2;
    return ()(8000.0F - 7900.0F * (float)Math.pow(Math.min(1.5F * f3, f4) / f4, 1.5D));
  }

  private void logHighFreq(String paramString)
  {
  }

  private boolean shouldFocusOnFrame(TimestampedFrame paramTimestampedFrame)
  {
    if (!this.cameraManager.isFocusSupported())
    {
      logHighFreq("Focus not supported!");
      return false;
    }
    if (!this.requestedFirstFocus)
    {
      logHighFreq("Requesting first focus!");
      return true;
    }
    updateDiffPercent(paramTimestampedFrame);
    if (this.lastDiffPercent > 30)
      this.focusFatigue = -150L;
    this.maxTimeBasedOffHistogram = getInterFocusDelayFromExposure();
    long l = this.lastFocusTimer.getElapsedMilliseconds();
    if (l < this.focusFatigue)
    {
      logHighFreq("Not enough time has gone by, not focusing.");
      return false;
    }
    if (this.lastDiffPercent >= 8)
      this.focusesWithSameImage = 0;
    if (this.focusesWithSameImage >= 3)
    {
      logHighFreq("Focused too many times on this frame!");
      return false;
    }
    if ((this.lastDiffPercent > 8) || (this.lastFrameBlurred))
    {
      updateBlurred(paramTimestampedFrame);
      if (!this.lastFrameBlurred)
        this.focusedSignature = paramTimestampedFrame.getSignature();
      if (this.consecutiveBlurTimer.getElapsedMilliseconds() > 150L)
      {
        logHighFreq("Focusing based on image difference.");
        return true;
      }
    }
    if (l > this.maxTimeBasedOffHistogram)
    {
      logHighFreq("Focusing based on exposure.");
      return true;
    }
    logHighFreq("No need to focus.");
    return false;
  }

  private void updateBlurred(TimestampedFrame paramTimestampedFrame)
  {
    long l = SystemClock.currentThreadTimeMillis();
    this.lastFrameBlurred = paramTimestampedFrame.isBlurred();
    this.lastBlurDuration = (SystemClock.currentThreadTimeMillis() - l);
    if (this.lastFrameBlurred)
    {
      this.consecutiveBlurTimer.start();
      return;
    }
    this.consecutiveBlurTimer.stop();
    this.consecutiveBlurTimer.reset();
  }

  private void updateDiffPercent(TimestampedFrame paramTimestampedFrame)
  {
    if (this.focusedSignature == null)
    {
      this.lastDiffPercent = 100;
      return;
    }
    long l = SystemClock.currentThreadTimeMillis();
    this.lastDiffPercent = paramTimestampedFrame.diffSignature(this.focusedSignature);
    this.lastDiffDuration = (SystemClock.currentThreadTimeMillis() - l);
  }

  public Vector<String> getDebugText()
  {
    while (true)
    {
      try
      {
        if (this.runSinceLastTime)
        {
          this.debugText.clear();
          Vector localVector2 = this.debugText;
          StringBuilder localStringBuilder = new StringBuilder();
          if (this.lastFrameBlurred)
          {
            str1 = "blurred";
            localVector2.add(str1 + ": " + this.lastBlurDuration + "ms");
            this.debugText.add("lastDiffPercent: " + this.lastDiffPercent + " " + this.lastDiffDuration + "ms, num same: " + this.focusesWithSameImage);
            String str2 = "";
            int[] arrayOfInt = this.lastBuckets;
            int i = arrayOfInt.length;
            int j = 0;
            if (j < i)
            {
              int k = arrayOfInt[j];
              str2 = str2 + k / 100 + ", ";
              j++;
              continue;
            }
            this.debugText.add("buckets: " + str2);
            this.debugText.add("buckets median: " + NumberUtils.getMedianIndex(this.lastBuckets) + "  mean: " + NumberUtils.getMeanIndex(this.lastBuckets) + "  stdDev: " + NumberUtils.getNormalizedStdDev(this.lastBuckets));
            this.debugText.add("Since last focus: " + this.lastFocusTimer.getElapsedMilliseconds() + "ms");
            this.debugText.add("Exposure delay: " + this.maxTimeBasedOffHistogram + "ms");
            this.debugText.add("Focus fatigue: " + this.focusFatigue + "ms");
            this.runSinceLastTime = false;
          }
        }
        else
        {
          Vector localVector1 = this.debugText;
          return localVector1;
        }
      }
      finally
      {
      }
      String str1 = "focused";
    }
  }

  protected TracingProtos.ProcessorStatus.Type getProcessorType()
  {
    return TracingProtos.ProcessorStatus.Type.IMAGE_BLUR_PROCESSOR;
  }

  public boolean isLastFrameBlurred()
  {
    return this.lastFrameBlurred;
  }

  public void onFocus(boolean paramBoolean)
  {
    this.justFocused = true;
  }

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    while (true)
    {
      try
      {
        this.runSinceLastTime = true;
        boolean bool = this.cameraManager.isFocusing();
        paramTimestampedFrame.setTakenWhileFocusing(bool);
        if (bool)
          return;
        if (this.justFocused)
        {
          this.lastFocusTimer.reset();
          this.lastFocusTimer.start();
          this.consecutiveBlurTimer.stop();
          this.consecutiveBlurTimer.reset();
          if (this.focusedSignature == null)
          {
            this.focusesWithSameImage = 1;
            this.focusedSignature = paramTimestampedFrame.getSignature();
          }
          this.justFocused = false;
        }
        this.lastBuckets = paramTimestampedFrame.getBucketDistribution();
        if (!shouldFocusOnFrame(paramTimestampedFrame))
          continue;
        if (this.lastDiffPercent <= 8)
        {
          this.focusesWithSameImage = (1 + this.focusesWithSameImage);
          synchronized (this.cameraManager)
          {
            if (!this.cameraManager.isFocusing())
            {
              logHighFreq("Requesting focus.");
              requestFocus();
            }
          }
        }
      }
      finally
      {
      }
      this.focusedSignature = null;
    }
  }

  protected void onStart()
  {
    this.requestedFirstFocus = false;
    this.lastFrameBlurred = true;
    this.focusedSignature = null;
    this.lastDiffPercent = 0;
    this.focusFatigue = -4000L;
    this.focusesWithSameImage = 0;
  }

  protected void requestFocus()
  {
    this.requestedFirstFocus = true;
    this.focusFatigue = Math.max(-4000L, this.focusFatigue - this.lastFocusTimer.getElapsedMilliseconds());
    this.focusFatigue = (()(1.5F * (8000.0F + (float)this.focusFatigue) - 8000.0F));
    this.focusFatigue = Math.min(8000L, this.focusFatigue);
    this.cameraManager.focus(this);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.ImageBlurProcessor
 * JD-Core Version:    0.6.2
 */