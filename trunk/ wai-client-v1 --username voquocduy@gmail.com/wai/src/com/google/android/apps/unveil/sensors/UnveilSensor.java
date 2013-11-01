package com.google.android.apps.unveil.sensors;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class UnveilSensor
{
  public int accuracy;
  public long currentReadingNanos;
  private final Set<Listener> listeners = new HashSet();

  public abstract float[] getValues();

  public boolean registerListener(Listener paramListener)
  {
    return this.listeners.add(paramListener);
  }

  protected void triggerOnSet()
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((Listener)localIterator.next()).onSet(this);
  }

  public boolean unregisterListener(Listener paramListener)
  {
    return this.listeners.remove(paramListener);
  }

  public static abstract interface Listener
  {
    public abstract void onSet(UnveilSensor paramUnveilSensor);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.UnveilSensor
 * JD-Core Version:    0.6.2
 */