package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.graphics.drawable.Drawable;
import com.google.android.apps.unveil.env.AbstractProvider.ItemsChangedListener;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.RegionQuadTree;
import com.google.android.apps.unveil.env.RegionQuadTree.Item;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class ClusterCache
{
  private static final int INITIAL_RADIUS = 20;
  private static final int LAT_SPAN_E6 = 180000000;
  private static final int LNG_SPAN_E6 = 360000000;
  private static final int MIN_LAT_E6 = -90000000;
  private static final int MIN_LNG_E6 = -180000000;
  private static final int NUM_LEVELS = 20;
  private static final double SCALE_FACTOR = 2.0D;
  private static final UnveilLogger logger = new UnveilLogger();
  private final RegionQuadTree<SearchHistoryOverlayItem> clusterCenters = new RegionQuadTree(-180000000, -90000000, 360000000, 180000000);
  private ClusterCache coarserCache;
  private ClusterCacheListener listener;
  private final int radius;
  private RegionQuadTree<SearchHistoryOverlayItem> toBeProcessed = new RegionQuadTree(-180000000, -90000000, 360000000, 180000000);

  private ClusterCache(int paramInt1, int paramInt2)
  {
    this.radius = paramInt2;
    if (paramInt1 >= 1)
      this.coarserCache = new ClusterCache(paramInt1 - 1, (int)(2.0D * paramInt2));
  }

  public ClusterCache(ClusterCacheListener paramClusterCacheListener)
  {
    this.radius = 1;
    this.listener = paramClusterCacheListener;
    this.coarserCache = new ClusterCache(19, 20);
  }

  private boolean addMarkerToExistingCluster(SearchHistoryOverlayItem paramSearchHistoryOverlayItem, Set<SearchHistoryOverlayItem> paramSet)
  {
    if (this.radius <= 1);
    List localList;
    do
    {
      return false;
      localList = this.clusterCenters.getItemsInRegion(paramSearchHistoryOverlayItem.getPoint().getLongitudeE6() - this.radius, paramSearchHistoryOverlayItem.getPoint().getLatitudeE6() - this.radius, paramSearchHistoryOverlayItem.getPoint().getLongitudeE6() + this.radius, paramSearchHistoryOverlayItem.getPoint().getLatitudeE6() + this.radius);
    }
    while (localList.size() == 0);
    SearchHistoryOverlayItem localSearchHistoryOverlayItem = (SearchHistoryOverlayItem)((RegionQuadTree.Item)localList.get(0)).getData();
    localSearchHistoryOverlayItem.setCount(localSearchHistoryOverlayItem.getCount() + paramSearchHistoryOverlayItem.getCount());
    BoundsTracker localBoundsTracker = paramSearchHistoryOverlayItem.getBoundsTracker();
    if (localSearchHistoryOverlayItem.getBoundsTracker() == null)
      localSearchHistoryOverlayItem.setBoundsTracker(new BoundsTracker(localSearchHistoryOverlayItem.getPoint().getLatitudeE6(), localSearchHistoryOverlayItem.getPoint().getLongitudeE6()));
    if (localBoundsTracker != null)
    {
      localSearchHistoryOverlayItem.getBoundsTracker().updateBounds(localBoundsTracker.getMinLat(), localBoundsTracker.getMinLng());
      localSearchHistoryOverlayItem.getBoundsTracker().updateBounds(localBoundsTracker.getMaxLat(), localBoundsTracker.getMaxLng());
    }
    while (true)
    {
      if ((this.coarserCache != null) && (!paramSet.contains(localSearchHistoryOverlayItem)))
        this.coarserCache.add(paramSearchHistoryOverlayItem);
      return true;
      localSearchHistoryOverlayItem.getBoundsTracker().updateBounds(paramSearchHistoryOverlayItem.getPoint().getLatitudeE6(), paramSearchHistoryOverlayItem.getPoint().getLongitudeE6());
    }
  }

  private void createNewCluster(SearchHistoryOverlayItem paramSearchHistoryOverlayItem, Set<SearchHistoryOverlayItem> paramSet)
  {
    if (this.radius > 1);
    for (SearchHistoryOverlayItem localSearchHistoryOverlayItem = createNewClusterWithNearbyMarkers(paramSearchHistoryOverlayItem, paramSet); ; localSearchHistoryOverlayItem = createNewSingletonCluster(paramSearchHistoryOverlayItem, paramSet))
    {
      this.clusterCenters.add(localSearchHistoryOverlayItem.getPoint().getLongitudeE6(), localSearchHistoryOverlayItem.getPoint().getLatitudeE6(), localSearchHistoryOverlayItem);
      if (this.coarserCache != null)
      {
        this.coarserCache.add(localSearchHistoryOverlayItem);
        paramSet.add(localSearchHistoryOverlayItem);
      }
      return;
    }
  }

  private SearchHistoryOverlayItem createNewClusterWithNearbyMarkers(SearchHistoryOverlayItem paramSearchHistoryOverlayItem, Set<SearchHistoryOverlayItem> paramSet)
  {
    int i = 0;
    double d1 = 0.0D;
    double d2 = 0.0D;
    List localList = this.toBeProcessed.getItemsInRegion(paramSearchHistoryOverlayItem.getPoint().getLongitudeE6() - this.radius, paramSearchHistoryOverlayItem.getPoint().getLatitudeE6() - this.radius, paramSearchHistoryOverlayItem.getPoint().getLongitudeE6() + this.radius, paramSearchHistoryOverlayItem.getPoint().getLatitudeE6() + this.radius);
    BoundsTracker localBoundsTracker = new BoundsTracker(paramSearchHistoryOverlayItem.getPoint().getLatitudeE6(), paramSearchHistoryOverlayItem.getPoint().getLongitudeE6());
    String str = null;
    Iterator localIterator = localList.iterator();
    label310: 
    while (localIterator.hasNext())
    {
      SearchHistoryOverlayItem localSearchHistoryOverlayItem2 = (SearchHistoryOverlayItem)((RegionQuadTree.Item)localIterator.next()).getData();
      if (!paramSet.contains(localSearchHistoryOverlayItem2))
      {
        paramSet.add(localSearchHistoryOverlayItem2);
        d1 += localSearchHistoryOverlayItem2.getCount() * localSearchHistoryOverlayItem2.getPoint().getLongitudeE6() / 1000000.0D;
        d2 += localSearchHistoryOverlayItem2.getCount() * localSearchHistoryOverlayItem2.getPoint().getLatitudeE6() / 1000000.0D;
        i += localSearchHistoryOverlayItem2.getCount();
        if (localSearchHistoryOverlayItem2.getBoundsTracker() == null)
          localBoundsTracker.updateBounds(localSearchHistoryOverlayItem2.getPoint().getLatitudeE6(), localSearchHistoryOverlayItem2.getPoint().getLongitudeE6());
        while (true)
        {
          if ((str != null) && (localSearchHistoryOverlayItem2.getTitle().length() <= str.length()))
            break label310;
          str = localSearchHistoryOverlayItem2.getTitle();
          break;
          localBoundsTracker.updateBounds(localSearchHistoryOverlayItem2.getBoundsTracker().getMinLat(), localSearchHistoryOverlayItem2.getBoundsTracker().getMinLng());
          localBoundsTracker.updateBounds(localSearchHistoryOverlayItem2.getBoundsTracker().getMaxLat(), localSearchHistoryOverlayItem2.getBoundsTracker().getMaxLng());
        }
      }
    }
    if (i == 0)
      throw new AssertionError("count is 0");
    double d3 = d1 / i;
    double d4 = d2 / i;
    int j = (int)(1000000.0D * d3);
    int k = (int)(1000000.0D * d4);
    GeoPoint localGeoPoint = new GeoPoint(k, j);
    if (str != null);
    while (true)
    {
      SearchHistoryOverlayItem localSearchHistoryOverlayItem1 = new SearchHistoryOverlayItem(localGeoPoint, str, paramSearchHistoryOverlayItem.getSearchHistoryItem());
      localSearchHistoryOverlayItem1.setBoundsTracker(localBoundsTracker);
      localSearchHistoryOverlayItem1.setCount(i);
      return localSearchHistoryOverlayItem1;
      str = paramSearchHistoryOverlayItem.getTitle();
    }
  }

  private SearchHistoryOverlayItem createNewSingletonCluster(SearchHistoryOverlayItem paramSearchHistoryOverlayItem, Set<SearchHistoryOverlayItem> paramSet)
  {
    SearchHistoryOverlayItem localSearchHistoryOverlayItem = new SearchHistoryOverlayItem(paramSearchHistoryOverlayItem.getPoint(), paramSearchHistoryOverlayItem.getTitle(), paramSearchHistoryOverlayItem.getSearchHistoryItem());
    localSearchHistoryOverlayItem.setCount(paramSearchHistoryOverlayItem.getCount());
    return localSearchHistoryOverlayItem;
  }

  public void add(SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
  {
    this.toBeProcessed.add(paramSearchHistoryOverlayItem.getPoint().getLongitudeE6(), paramSearchHistoryOverlayItem.getPoint().getLatitudeE6(), paramSearchHistoryOverlayItem);
  }

  public int getActualRadius(int paramInt)
  {
    if ((this.radius < paramInt) && (this.coarserCache != null))
      return this.coarserCache.getActualRadius(paramInt);
    return this.radius;
  }

  public RegionQuadTree<SearchHistoryOverlayItem> getTree(int paramInt)
  {
    if ((this.radius < paramInt) && (this.coarserCache != null))
      return this.coarserCache.getTree(paramInt);
    return this.clusterCenters;
  }

  public void update()
  {
    List localList = this.toBeProcessed.getItemsInRegion(-180000000, -90000000, 180000000, 90000000);
    HashSet localHashSet = new HashSet();
    Iterator localIterator = localList.iterator();
    while (localIterator.hasNext())
    {
      SearchHistoryOverlayItem localSearchHistoryOverlayItem = (SearchHistoryOverlayItem)((RegionQuadTree.Item)localIterator.next()).getData();
      if (!localHashSet.contains(localSearchHistoryOverlayItem))
      {
        if (!addMarkerToExistingCluster(localSearchHistoryOverlayItem, localHashSet))
          createNewCluster(localSearchHistoryOverlayItem, localHashSet);
        localHashSet.add(localSearchHistoryOverlayItem);
      }
    }
    if (this.coarserCache != null)
      this.coarserCache.update();
    this.toBeProcessed = new RegionQuadTree(-180000000, -90000000, 360000000, 180000000);
  }

  private class BoundsTracker
  {
    private int maxLat;
    private int maxLng;
    private int minLat;
    private int minLng;

    public BoundsTracker(int paramInt1, int arg3)
    {
      this.maxLat = paramInt1;
      this.minLat = paramInt1;
      int i;
      this.maxLng = i;
      this.minLng = i;
    }

    public int getMaxLat()
    {
      return this.maxLat;
    }

    public int getMaxLng()
    {
      return this.maxLng;
    }

    public int getMinLat()
    {
      return this.minLat;
    }

    public int getMinLng()
    {
      return this.minLng;
    }

    public String toString()
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Integer.valueOf(this.minLat);
      arrayOfObject[1] = Integer.valueOf(this.minLng);
      arrayOfObject[2] = Integer.valueOf(this.maxLat);
      arrayOfObject[3] = Integer.valueOf(this.maxLng);
      return String.format(" (%d,%d) - (%d,%d)", arrayOfObject);
    }

    public void updateBounds(int paramInt1, int paramInt2)
    {
      this.minLat = Math.min(this.minLat, paramInt1);
      this.minLng = Math.min(this.minLng, paramInt2);
      this.maxLat = Math.max(this.maxLat, paramInt1);
      this.maxLng = Math.max(this.maxLng, paramInt2);
    }
  }

  public static abstract interface ClusterCacheListener
  {
    public abstract void onTapCluster(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem, ArrayList<SearchHistoryItem> paramArrayList);

    public abstract void onTapItem(SearchHistoryItem paramSearchHistoryItem, int paramInt);
  }

  public static class SearchHistoryOverlayItem extends OverlayItem
  {
    private ClusterCache.BoundsTracker boundsTracker;
    private int count = 1;
    private AbstractProvider.ItemsChangedListener fetchListener;
    private boolean hidden = false;
    private boolean inOverlay = false;
    private boolean isFocused = false;
    private Picture picture;
    private final SearchHistoryItem searchHistoryItem;

    public SearchHistoryOverlayItem(GeoPoint paramGeoPoint, String paramString, SearchHistoryItem paramSearchHistoryItem)
    {
      super(paramString, "");
      this.searchHistoryItem = paramSearchHistoryItem;
    }

    public void clearState()
    {
      setMarkerPicture(null);
      setMarker(null);
      setInOverlay(false);
    }

    public ClusterCache.BoundsTracker getBoundsTracker()
    {
      return this.boundsTracker;
    }

    public ArrayList<SearchHistoryItem> getChildren(ClusterCache paramClusterCache)
    {
      RegionQuadTree localRegionQuadTree = paramClusterCache.getTree(0);
      ArrayList localArrayList = new ArrayList();
      if (this.boundsTracker != null)
      {
        Iterator localIterator = localRegionQuadTree.getItemsInRegion(this.boundsTracker.getMinLng(), this.boundsTracker.getMinLat(), this.boundsTracker.getMaxLng(), this.boundsTracker.getMaxLat()).iterator();
        while (localIterator.hasNext())
        {
          RegionQuadTree.Item localItem = (RegionQuadTree.Item)localIterator.next();
          if (localItem.getData() != null)
            localArrayList.add(((SearchHistoryOverlayItem)localItem.getData()).getSearchHistoryItem());
        }
      }
      if (getSearchHistoryItem() != null)
        localArrayList.add(getSearchHistoryItem());
      return localArrayList;
    }

    public int getCount()
    {
      return this.count;
    }

    public double getLatitude()
    {
      return getPoint().getLatitudeE6() / 1000000.0D;
    }

    public double getLongitude()
    {
      return getPoint().getLongitudeE6() / 1000000.0D;
    }

    public SearchHistoryItem getSearchHistoryItem()
    {
      return this.searchHistoryItem;
    }

    public boolean isHidden()
    {
      return this.hidden;
    }

    public boolean isInOverlay()
    {
      return this.inOverlay;
    }

    public void onTap(ClusterCache paramClusterCache, Context paramContext)
    {
      ArrayList localArrayList = getChildren(paramClusterCache);
      if (localArrayList.size() == 1)
      {
        paramClusterCache.listener.onTapItem((SearchHistoryItem)localArrayList.get(0), getMarker(0).getIntrinsicHeight());
        return;
      }
      paramClusterCache.listener.onTapCluster(this, localArrayList);
    }

    public void setBoundsTracker(ClusterCache.BoundsTracker paramBoundsTracker)
    {
      this.boundsTracker = paramBoundsTracker;
    }

    public void setCount(int paramInt)
    {
      this.count = paramInt;
    }

    public void setFetchListener(AbstractProvider.ItemsChangedListener paramItemsChangedListener)
    {
      this.fetchListener = paramItemsChangedListener;
    }

    public void setFocus(boolean paramBoolean)
    {
      this.isFocused = paramBoolean;
    }

    public void setHidden(boolean paramBoolean)
    {
      this.hidden = paramBoolean;
    }

    public void setInOverlay(boolean paramBoolean)
    {
      this.inOverlay = paramBoolean;
    }

    public void setMarkerPicture(Picture paramPicture)
    {
      this.picture = paramPicture;
    }

    public String toString()
    {
      StringBuilder localStringBuilder1 = new StringBuilder().append("SearchHistoryOverlayItem ").append(super.toString());
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(getPoint().getLatitudeE6());
      arrayOfObject[1] = Integer.valueOf(getPoint().getLongitudeE6());
      arrayOfObject[2] = Integer.valueOf(this.count);
      StringBuilder localStringBuilder2 = localStringBuilder1.append(String.format(" @(%d,%d) n=%d", arrayOfObject));
      if (this.boundsTracker == null);
      for (String str = "bounds=n/a"; ; str = "bounds=" + this.boundsTracker.toString())
        return str;
    }

    public boolean toggleFocus()
    {
      if (!this.isFocused);
      for (boolean bool = true; ; bool = false)
      {
        this.isFocused = bool;
        return this.isFocused;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.ClusterCache
 * JD-Core Version:    0.6.2
 */