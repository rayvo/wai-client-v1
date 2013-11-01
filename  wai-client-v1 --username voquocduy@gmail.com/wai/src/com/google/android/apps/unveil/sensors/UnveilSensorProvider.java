package com.google.android.apps.unveil.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.view.OrientationEventListener;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.LatLngProtos.LatLng;
import com.google.goggles.LatLngProtos.LatLng.Builder;
import com.google.goggles.LocationProtos.Location;
import com.google.goggles.LocationProtos.Location.Builder;
import java.util.List;

public class UnveilSensorProvider
  implements SensorEventListener
{
  public static final int ORIENTATION_CLOCKWISE_180 = 180;
  public static final int ORIENTATION_CLOCKWISE_270 = 270;
  public static final int ORIENTATION_CLOCKWISE_90 = 90;
  public static final int ORIENTATION_NATURAL;
  private Sensor accelDevice;
  private final UnveilGenericSensor accelerationSensor = new UnveilGenericSensor();
  private final Stopwatch currentOrientationInterval;
  private int lastGoodOrientation = -1;
  private final UnveilLogger logger = new UnveilLogger();
  private Sensor magDevice;
  private final UnveilGenericSensor magneticSensor = new UnveilGenericSensor();
  private final OrientationEventListener orientationListener;
  private final UnveilOrientationSensor orientationSensor;
  private final SensorManager sensorManager;

  public UnveilSensorProvider(Context paramContext)
  {
    this.orientationSensor = new UnveilOrientationSensor(this, paramContext);
    this.sensorManager = ((SensorManager)paramContext.getSystemService("sensor"));
    this.currentOrientationInterval = new Stopwatch();
    this.currentOrientationInterval.start();
    if (this.sensorManager != null)
    {
      this.orientationListener = new OrientationEventListener(paramContext)
      {
        public void onOrientationChanged(int paramAnonymousInt)
        {
          if (paramAnonymousInt != -1)
          {
            UnveilSensorProvider.this.currentOrientationInterval.reset();
            UnveilSensorProvider.access$102(UnveilSensorProvider.this, paramAnonymousInt);
          }
        }
      };
      List localList1 = this.sensorManager.getSensorList(2);
      List localList2 = this.sensorManager.getSensorList(1);
      if (localList1.size() > 0)
        this.magDevice = ((Sensor)localList1.get(0));
      while (localList2.size() > 0)
      {
        this.accelDevice = ((Sensor)localList2.get(0));
        return;
        this.logger.w("Couldn't find a magnetic field sensor.", new Object[0]);
      }
      this.logger.w("Couldn't find an accelerometer.", new Object[0]);
      return;
    }
    this.logger.w("No SensorManager available.", new Object[0]);
    this.orientationListener = null;
  }

  public static int roundOrientation(int paramInt)
  {
    if (paramInt == -1)
      return -1;
    int i = paramInt % 360;
    if (i < 45)
      return 0;
    if (i < 135)
      return 90;
    if (i < 225)
      return 180;
    if (i < 315)
      return 270;
    return 0;
  }

  public static LocationProtos.Location toLocationProto(Location paramLocation)
  {
    if (paramLocation == null)
      return null;
    LatLngProtos.LatLng.Builder localBuilder = LatLngProtos.LatLng.newBuilder().setLatDegrees(paramLocation.getLatitude()).setLngDegrees(paramLocation.getLongitude());
    LocationProtos.Location.Builder localBuilder1 = LocationProtos.Location.newBuilder().setLatLng(localBuilder).setTimestampMs(paramLocation.getTime());
    if (paramLocation.hasAccuracy())
      localBuilder1.setLatLngAccuracyMeters(paramLocation.getAccuracy());
    if (paramLocation.hasAltitude())
      localBuilder1.setAltitudeMeters((float)paramLocation.getAltitude());
    return localBuilder1.build();
  }

  public UnveilSensor getAccelerationSensor()
  {
    return this.accelerationSensor;
  }

  public UnveilSensor getMagneticSensor()
  {
    return this.magneticSensor;
  }

  public UnveilSensor getOrientationSensor()
  {
    return this.orientationSensor;
  }

  public int getRoundedDeviceOrientation()
  {
    return roundOrientation(this.lastGoodOrientation);
  }

  public void off()
  {
    this.sensorManager.unregisterListener(this);
    this.orientationListener.disable();
  }

  public void on()
  {
    if (this.accelDevice != null)
      this.sensorManager.registerListener(this, this.accelDevice, 3);
    if (this.magDevice != null)
      this.sensorManager.registerListener(this, this.magDevice, 3);
    this.orientationListener.enable();
  }

  public void onAccuracyChanged(Sensor paramSensor, int paramInt)
  {
  }

  public void onSensorChanged(SensorEvent paramSensorEvent)
  {
    Sensor localSensor = paramSensorEvent.sensor;
    long l = paramSensorEvent.timestamp;
    try
    {
      if (localSensor.getType() == 1)
        this.accelerationSensor.set(l, paramSensorEvent.accuracy, paramSensorEvent.values);
      while (true)
      {
        return;
        if (localSensor.getType() != 2)
          break;
        this.magneticSensor.set(l, paramSensorEvent.accuracy, paramSensorEvent.values);
      }
    }
    finally
    {
    }
    throw new RuntimeException("Unexpected sensor type: " + localSensor.getType());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.UnveilSensorProvider
 * JD-Core Version:    0.6.2
 */