package com.google.android.apps.unveil.sensors;

public class UnveilGenericSensor extends UnveilSensor
{
  private float[] values;

  public float[] getValues()
  {
    return this.values;
  }

  void set(long paramLong, int paramInt, float[] paramArrayOfFloat)
  {
    this.currentReadingNanos = paramLong;
    this.accuracy = paramInt;
    if (this.accuracy != 0)
    {
      if (this.values == null)
        this.values = new float[3];
      this.values[0] = paramArrayOfFloat[0];
      this.values[1] = paramArrayOfFloat[1];
      this.values[2] = paramArrayOfFloat[2];
      triggerOnSet();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.UnveilGenericSensor
 * JD-Core Version:    0.6.2
 */