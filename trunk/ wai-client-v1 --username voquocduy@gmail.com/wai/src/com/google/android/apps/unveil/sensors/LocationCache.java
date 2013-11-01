package com.google.android.apps.unveil.sensors;

import android.location.Location;
import com.google.android.apps.unveil.network.LatLngEncrypter;
import com.google.goggles.LocationProtos.Location;
import com.google.protobuf.ByteString;

public class LocationCache
  implements UnveilLocationProvider.Listener
{
  private ByteString cachedEncryptedLocation;
  private Location cachedLocation;
  private LocationProtos.Location cachedProtoLocation;
  private final LatLngEncrypter encrypter;
  private final float evictionMeters;

  LocationCache(UnveilLocationProvider paramUnveilLocationProvider, LatLngEncrypter paramLatLngEncrypter, float paramFloat)
  {
    this.encrypter = paramLatLngEncrypter;
    this.evictionMeters = paramFloat;
    paramUnveilLocationProvider.registerListener(this);
  }

  private boolean isClose(Location paramLocation)
  {
    if ((this.cachedLocation == null) || (paramLocation == null));
    while (this.cachedLocation.distanceTo(paramLocation) >= this.evictionMeters)
      return false;
    return true;
  }

  private void setCachedLocation(Location paramLocation)
  {
    this.cachedLocation = paramLocation;
    this.cachedProtoLocation = null;
    this.cachedEncryptedLocation = null;
  }

  private void updateEncryptedCache()
  {
    if (this.cachedLocation == null)
      return;
    this.cachedEncryptedLocation = this.encrypter.encryptLatLng(getLocation().getLatLng());
  }

  private void updateProtoCache()
  {
    if (this.cachedLocation == null)
      return;
    this.cachedProtoLocation = UnveilSensorProvider.toLocationProto(this.cachedLocation);
  }

  public ByteString getEncryptedLocation()
  {
    if (this.cachedEncryptedLocation == null)
      updateEncryptedCache();
    return this.cachedEncryptedLocation;
  }

  public LocationProtos.Location getLocation()
  {
    if (this.cachedProtoLocation == null)
      updateProtoCache();
    return this.cachedProtoLocation;
  }

  public void onLocationChanged(UnveilLocationProvider paramUnveilLocationProvider, Location paramLocation)
  {
    if (isClose(paramLocation))
      return;
    setCachedLocation(paramLocation);
  }

  public void onLocationDisabled(UnveilLocationProvider paramUnveilLocationProvider)
  {
  }

  public void onLocationEnabled(UnveilLocationProvider paramUnveilLocationProvider)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.LocationCache
 * JD-Core Version:    0.6.2
 */