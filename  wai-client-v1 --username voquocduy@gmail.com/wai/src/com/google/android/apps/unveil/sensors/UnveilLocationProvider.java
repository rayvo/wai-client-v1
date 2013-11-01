package com.google.android.apps.unveil.sensors;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class UnveilLocationProvider
  implements LocationListener
{
  private static final long MAX_CACHED_LOCATION_AGE_MILLIS = 60000L;
  private static final String TAG = "UnveilLocationListener";
  private final UnveilContext application;
  private final Context context;
  private final Set<Listener> listeners;
  private Location location;
  private final LocationManager locationManager;
  private final UnveilLogger logger = new UnveilLogger();

  public UnveilLocationProvider(UnveilContext paramUnveilContext, LocationManager paramLocationManager, Context paramContext)
  {
    this.application = paramUnveilContext;
    this.locationManager = paramLocationManager;
    this.context = paramContext;
    this.listeners = new HashSet();
  }

  private void eraseAllCachedLocationsAndDisable()
  {
    this.location = null;
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((Listener)localIterator.next()).onLocationChanged(this, null);
    off();
  }

  private boolean googleLocationDisabled()
  {
    return (GoogleLocationSettingHelper.isAvailable(this.context)) && (GoogleLocationSettingHelper.getUseLocationForServices(this.context) != 1);
  }

  public Location getLocation()
  {
    if (googleLocationDisabled())
      eraseAllCachedLocationsAndDisable();
    return this.location;
  }

  public LocationCache getLocationCache(float paramFloat)
  {
    return new LocationCache(this, this.application.getLatLngEncrypter(), paramFloat);
  }

  public void off()
  {
    this.locationManager.removeUpdates(this);
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((Listener)localIterator.next()).onLocationDisabled(this);
  }

  public void on()
  {
    if (googleLocationDisabled())
    {
      this.logger.i("Google location not available, not requesting location.", new Object[0]);
      eraseAllCachedLocationsAndDisable();
    }
    while (true)
    {
      return;
      label263: label360: label372: Iterator localIterator2;
      try
      {
        if (this.locationManager.isProviderEnabled("gps"))
        {
          this.locationManager.requestLocationUpdates("gps", 0L, 0.0F, this);
          i = 1;
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException1)
      {
        try
        {
          if (this.locationManager.isProviderEnabled("network"))
            this.locationManager.requestLocationUpdates("network", 0L, 0.0F, this);
          for (j = 1; ; j = 0)
          {
            Iterator localIterator1 = this.listeners.iterator();
            while (localIterator1.hasNext())
              ((Listener)localIterator1.next()).onLocationEnabled(this);
            this.logger.d("GPS location provider disabled.", new Object[0]);
            i = 0;
            break;
            localIllegalArgumentException1 = localIllegalArgumentException1;
            this.logger.w(localIllegalArgumentException1, "No GPS location provider; are you in the emulator?", new Object[0]);
            i = 0;
            break;
            this.logger.d("Wireless network location provider disabled.", new Object[0]);
          }
        }
        catch (IllegalArgumentException localIllegalArgumentException2)
        {
          int i;
          int j;
          while (true)
          {
            this.logger.w(localIllegalArgumentException2, "No network location provider; are you in the emulator?", new Object[0]);
            j = 0;
          }
          Location localLocation1 = null;
          if (i != 0)
            localLocation1 = this.locationManager.getLastKnownLocation("gps");
          Location localLocation2 = null;
          if (j != 0)
            localLocation2 = this.locationManager.getLastKnownLocation("network");
          Location localLocation3;
          if ((localLocation1 != null) && (localLocation2 != null))
            if (localLocation1.getTime() > localLocation2.getTime())
            {
              this.location = localLocation1;
              localLocation3 = this.application.getMockLocation();
              if (localLocation3 != null)
              {
                if (this.location != null)
                  break label360;
                this.location = localLocation3;
              }
            }
          while (this.location != null)
          {
            if (System.currentTimeMillis() - this.location.getTime() <= 60000L)
              break label372;
            this.location = null;
            return;
            this.location = localLocation2;
            break label263;
            if (localLocation1 != null)
            {
              this.location = localLocation1;
              break label263;
            }
            if (localLocation2 == null)
              break label263;
            this.location = localLocation2;
            break label263;
            this.location.set(localLocation3);
          }
          localIterator2 = this.listeners.iterator();
        }
      }
      while (localIterator2.hasNext())
        ((Listener)localIterator2.next()).onLocationChanged(this, this.location);
    }
  }

  public void onLocationChanged(Location paramLocation)
  {
    if (googleLocationDisabled())
    {
      this.logger.i("Got a new location, but location is disabled, turning off.", new Object[0]);
      eraseAllCachedLocationsAndDisable();
      return;
    }
    if (this.location == null)
      this.location = paramLocation;
    while (true)
    {
      Location localLocation = this.application.getMockLocation();
      if (localLocation != null)
        this.location.set(localLocation);
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((Listener)localIterator.next()).onLocationChanged(this, this.location);
      break;
      this.location.set(paramLocation);
    }
  }

  public void onProviderDisabled(String paramString)
  {
  }

  public void onProviderEnabled(String paramString)
  {
  }

  public void onStatusChanged(String paramString, int paramInt, Bundle paramBundle)
  {
  }

  public boolean registerListener(Listener paramListener)
  {
    return this.listeners.add(paramListener);
  }

  public boolean unregisterListener(Listener paramListener)
  {
    return this.listeners.remove(paramListener);
  }

  public static abstract interface Listener
  {
    public abstract void onLocationChanged(UnveilLocationProvider paramUnveilLocationProvider, Location paramLocation);

    public abstract void onLocationDisabled(UnveilLocationProvider paramUnveilLocationProvider);

    public abstract void onLocationEnabled(UnveilLocationProvider paramUnveilLocationProvider);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.UnveilLocationProvider
 * JD-Core Version:    0.6.2
 */