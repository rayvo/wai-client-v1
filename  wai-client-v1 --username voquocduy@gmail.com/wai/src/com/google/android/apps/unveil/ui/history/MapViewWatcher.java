package com.google.android.apps.unveil.ui.history;

import android.os.Handler;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class MapViewWatcher
  implements Runnable
{
  private static final int POLLING_INTERVAL_MS = 500;
  private static final UnveilLogger logger = new UnveilLogger();
  private Handler handler;
  private final MapViewListener listener;
  private final MapView mapView;
  private GeoPoint previousCenter;
  private int previousZoomLevel;
  private boolean running = false;

  public MapViewWatcher(MapView paramMapView, MapViewListener paramMapViewListener)
  {
    this.mapView = paramMapView;
    this.listener = paramMapViewListener;
    this.previousCenter = paramMapView.getMapCenter();
    this.previousZoomLevel = paramMapView.getZoomLevel();
  }

  private void schedulePoll()
  {
    if (this.running)
    {
      this.handler = new Handler();
      this.handler.postDelayed(this, 500L);
    }
  }

  public void run()
  {
    GeoPoint localGeoPoint = this.mapView.getMapCenter();
    boolean bool1;
    if (localGeoPoint.getLatitudeE6() == this.previousCenter.getLatitudeE6())
    {
      int k = localGeoPoint.getLongitudeE6();
      int m = this.previousCenter.getLongitudeE6();
      bool1 = false;
      if (k == m);
    }
    else
    {
      bool1 = true;
    }
    int i = this.mapView.getZoomLevel();
    int j = this.previousZoomLevel;
    boolean bool2 = false;
    if (i != j)
      bool2 = true;
    if ((bool1) || (bool2))
      this.listener.onMapViewChanged(bool2, bool1);
    this.previousZoomLevel = i;
    this.previousCenter = localGeoPoint;
    schedulePoll();
  }

  public void start()
  {
    logger.i("mapviewWatcher.start", new Object[0]);
    if (!this.running)
    {
      logger.i("mapviewWatcher.start: was not running", new Object[0]);
      this.running = true;
      schedulePoll();
    }
  }

  public void stop()
  {
    logger.i("mapviewWatcher.stop", new Object[0]);
    if (this.running)
      this.handler.removeCallbacks(this);
    this.running = false;
  }

  public static abstract interface MapViewListener
  {
    public abstract void onMapViewChanged(boolean paramBoolean1, boolean paramBoolean2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.MapViewWatcher
 * JD-Core Version:    0.6.2
 */