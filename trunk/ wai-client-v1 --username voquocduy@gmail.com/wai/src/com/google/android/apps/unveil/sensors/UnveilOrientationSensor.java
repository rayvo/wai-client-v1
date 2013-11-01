package com.google.android.apps.unveil.sensors;

import android.content.Context;
import android.hardware.SensorManager;
import android.util.Log;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.NumberUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;

public class UnveilOrientationSensor extends UnveilSensor
  implements UnveilSensor.Listener
{
  private static final float AZIMUTH_OUTLIER_DELTA_DISCARD_THRESHOLD = 10.0F;
  private static final float AZIMUTH_OUTLIER_THRESHOLD = 30.0F;
  private static final float SMOOTHING_FACTOR = 0.4F;
  private static final String TAG = "OrientationSensor";
  private static final UnveilLogger logger = new UnveilLogger();
  private final UnveilSensor accelerometer;
  private final Context context;
  private final float[] correctedRotationMatrix;
  private boolean forceLandscape;
  private long lastCalculatedTime;
  private final UnveilSensor magnetometer;
  private final float[] portraitRotationMatrix;
  private float prevAzimuthDelta;
  private final UnveilSensorProvider sensorProvider;
  private float[] values;

  public UnveilOrientationSensor(UnveilSensorProvider paramUnveilSensorProvider, Context paramContext)
  {
    this.sensorProvider = paramUnveilSensorProvider;
    this.context = paramContext;
    this.accelerometer = paramUnveilSensorProvider.getAccelerationSensor();
    this.magnetometer = paramUnveilSensorProvider.getMagneticSensor();
    this.accelerometer.registerListener(this);
    this.magnetometer.registerListener(this);
    this.prevAzimuthDelta = 0.0F;
    this.portraitRotationMatrix = new float[16];
    this.correctedRotationMatrix = new float[16];
  }

  private boolean canCalculateValues()
  {
    return (this.accelerometer.getValues() != null) && (this.magnetometer.getValues() != null) && (this.accelerometer.accuracy != 0) && (this.magnetometer.accuracy != 0);
  }

  private boolean isOutlier(float paramFloat1, float paramFloat2)
  {
    int i;
    if (Math.abs(paramFloat1) > 30.0F)
    {
      i = 1;
      if (Math.abs(NumberUtils.normalizeDegrees(paramFloat2 - paramFloat1)) <= 10.0F)
        break label46;
    }
    label46: for (int j = 1; ; j = 0)
    {
      if ((i == 0) || (j == 0))
        break label52;
      return true;
      i = 0;
      break;
    }
    label52: return false;
  }

  public float[] getValues()
  {
    int i;
    int j;
    if (((UnveilContext)this.context.getApplicationContext()).getViewport().getNaturalOrientation(this.context) == 1)
    {
      i = 270;
      j = this.sensorProvider.getRoundedDeviceOrientation();
      if (j == -1)
        j = 0;
      if (!this.forceLandscape)
        break label63;
    }
    while (true)
    {
      return getValues(i);
      i = 0;
      break;
      label63: i = j;
    }
  }

  public float[] getValues(int paramInt)
  {
    if ((!canCalculateValues()) || (this.lastCalculatedTime == this.currentReadingNanos))
      return this.values;
    float[] arrayOfFloat1 = this.accelerometer.getValues();
    float[] arrayOfFloat2 = this.magnetometer.getValues();
    float[] arrayOfFloat3 = new float[3];
    if (!SensorManager.getRotationMatrix(this.portraitRotationMatrix, null, arrayOfFloat1, arrayOfFloat2))
      return this.values;
    if (this.values == null)
      this.values = new float[3];
    int i = 1;
    int j = 2;
    switch (paramInt)
    {
    case 0:
    default:
    case 270:
    case 180:
    case 90:
    }
    while (true)
    {
      if (!SensorManager.remapCoordinateSystem(this.portraitRotationMatrix, i, j, this.correctedRotationMatrix))
        Log.e("OrientationSensor", "Could not remap coord system");
      SensorManager.getOrientation(this.correctedRotationMatrix, arrayOfFloat3);
      float f1 = this.values[0];
      float f2 = NumberUtils.normalizeDegrees(arrayOfFloat3[0] - f1);
      if (!isOutlier(f2, this.prevAzimuthDelta))
        this.values[0] = NumberUtils.normalizeDegrees(f1 + 0.4F * f2);
      this.values[1] = NumberUtils.radiansToNormalizedDegrees(arrayOfFloat3[1]);
      this.values[2] = NumberUtils.radiansToNormalizedDegrees(arrayOfFloat3[2]);
      this.prevAzimuthDelta = f2;
      this.lastCalculatedTime = this.currentReadingNanos;
      return this.values;
      i = 2;
      j = 129;
      continue;
      i = 129;
      j = 130;
      continue;
      i = 130;
      j = 1;
    }
  }

  public void onSet(UnveilSensor paramUnveilSensor)
  {
    long l;
    if (this.accelerometer.currentReadingNanos > this.magnetometer.currentReadingNanos)
    {
      l = this.accelerometer.currentReadingNanos;
      this.currentReadingNanos = l;
      if (this.accelerometer.accuracy <= this.magnetometer.accuracy)
        break label86;
    }
    label86: for (int i = this.magnetometer.accuracy; ; i = this.accelerometer.accuracy)
    {
      this.accuracy = i;
      if (canCalculateValues())
        triggerOnSet();
      return;
      l = this.magnetometer.currentReadingNanos;
      break;
    }
  }

  public void setForceLandscape(boolean paramBoolean)
  {
    this.forceLandscape = paramBoolean;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.UnveilOrientationSensor
 * JD-Core Version:    0.6.2
 */