package com.google.android.apps.unveil.ui.history;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.NinePatch;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.Gallery;
import android.widget.Gallery.LayoutParams;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.AbstractProvider;
import com.google.android.apps.unveil.env.AbstractProvider.ItemsChangedListener;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.RegionQuadTree;
import com.google.android.apps.unveil.env.RegionQuadTree.Item;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryItem.UnveilGeoPoint;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MapOverlayManager
  implements MapViewWatcher.MapViewListener, ClusterCache.ClusterCacheListener, AbstractProvider.ItemsChangedListener
{
  private static final int BUBBLE_PIN_OFFSET_FROM_LEFT_DIP = 43;
  private static final int DEGREES_TO_E6 = 1000000;
  private static final double FOCUS_BUBBLE_WIDTH_FRACTION = 0.9D;
  private static final double FUDGE_FACTOR = 5.0D;
  private static final int HORIZONTAL_MARGIN_DIP = 14;
  private static final double INTER_THUMBNAIL_GAP_FRACTION = 0.1D;
  private static final double MAP_MARGIN = 1.1D;
  private static final int MAX_LATITUDE_SPAN = 180;
  private static final int MAX_LONGITUDE_SPAN = 360;
  private static final int MAX_STACKED_BUBBLES = 3;
  private static final int POINTER_HEIGHT_DIP = 13;
  private static final int SINGLE_HORIZONTAL_MARGIN_DIP = 7;
  private static final int SINGLE_POINTER_HEIGHT_DIP = 12;
  private static final int SINGLE_VERTICAL_MARGIN_DIP = 7;
  private static final int STACK_MARGIN_DIP = 3;
  private static final int THUMBNAIL_SIZE_DIP = 60;
  private static final int TOTAL_NINE_PATCH_BORDER_WIDTH_ALONG_ONE_DIMENSION = 2;
  private static final int VERTICAL_MARGIN_DIP = 14;
  private static int bubblePinOffsetFromLeft;
  private static NinePatch leftNinepatch;
  private static final UnveilLogger logger = new UnveilLogger();
  private static Bitmap singlePin;
  private static Bitmap singlePinTop;
  private static Bitmap stretchBitmap;
  private static NinePatch stretchNinepatch;
  private static Bitmap widerPinLeft;
  private boolean cacheInvalid;
  private final ClusterCache clusterCache;
  private final Drawable[] clusterDrawables = new Drawable[101];
  private final Context context;
  private FocusOverlay focusOverlay;
  private BitmapDrawable[] genericBubbles;
  private final int horizontalMargin;
  private final List<Overlay> mapOverlays;
  private final UnveilMapView mapView;
  private final MapViewWatcher mapViewWatcher;
  private final double markerSize;
  private int maxLat = -90000000;
  private int maxLng = -180000000;
  private int minLat = 90000000;
  private int minLng = 180000000;
  private final int pointerHeight;
  private ArrayList<SearchHistoryItem> possiblyTappedCluster;
  private ClusterCache.SearchHistoryOverlayItem possiblyTappedClusterItem;
  private SearchHistoryItem possiblyTappedItem;
  private final SearchResultItemizedOverlay resultsOverlay;
  private double screenSize;
  private final int singleHorizontalMargin;
  private final int singlePointerHeight;
  private final int singleVerticalMargin;
  private final int stackMargin;
  private final int thumbnailSize;
  Bitmap unknownImageBitmap;
  private final int verticalMargin;

  public MapOverlayManager(final Context paramContext, UnveilMapView paramUnveilMapView)
  {
    this.context = paramContext;
    this.mapView = paramUnveilMapView;
    this.horizontalMargin = PixelUtils.dipToPix(14.0F, paramContext);
    this.singleHorizontalMargin = PixelUtils.dipToPix(7.0F, paramContext);
    this.verticalMargin = PixelUtils.dipToPix(14.0F, paramContext);
    this.singleVerticalMargin = PixelUtils.dipToPix(7.0F, paramContext);
    this.pointerHeight = PixelUtils.dipToPix(13.0F, paramContext);
    this.singlePointerHeight = PixelUtils.dipToPix(12.0F, paramContext);
    this.thumbnailSize = PixelUtils.dipToPix(60.0F, paramContext);
    this.stackMargin = PixelUtils.dipToPix(3.0F, paramContext);
    bubblePinOffsetFromLeft = PixelUtils.dipToPix(43.0F, paramContext);
    if (singlePin == null)
      singlePin = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.ic_single_pin);
    if (widerPinLeft == null)
      widerPinLeft = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.ic_wider_pin_left);
    if (stretchBitmap == null)
      stretchBitmap = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.ic_wider_pin_stretch);
    if (leftNinepatch == null)
      leftNinepatch = new NinePatch(widerPinLeft, widerPinLeft.getNinePatchChunk(), null);
    if (stretchNinepatch == null)
      stretchNinepatch = new NinePatch(stretchBitmap, stretchBitmap.getNinePatchChunk(), null);
    if (singlePinTop == null)
      singlePinTop = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.ic_single_pin_top);
    ((UnveilApplication)((Activity)paramContext).getApplication()).getSearchHistoryProvider().addListener(this);
    paramUnveilMapView.setOnSingleTapConfirmedListener(new UnveilMapView.OnSingleTapConfirmedListener()
    {
      public void onSingleTapConfirmed()
      {
        if (MapOverlayManager.this.possiblyTappedItem != null)
        {
          MapOverlayManager.this.unfocus();
          ItemModels.replay(MapOverlayManager.this.possiblyTappedItem, paramContext);
        }
        while (true)
        {
          MapOverlayManager.access$102(MapOverlayManager.this, null);
          MapOverlayManager.access$202(MapOverlayManager.this, null);
          MapOverlayManager.access$002(MapOverlayManager.this, null);
          return;
          if (MapOverlayManager.this.possiblyTappedCluster != null)
          {
            if (MapOverlayManager.this.possiblyTappedClusterItem.toggleFocus())
              MapOverlayManager.this.focus(MapOverlayManager.this.possiblyTappedClusterItem);
            else
              MapOverlayManager.this.unfocus();
          }
          else
            MapOverlayManager.this.unfocus();
        }
      }

      public void onSingleTapDenied()
      {
        MapOverlayManager.access$002(MapOverlayManager.this, null);
        MapOverlayManager.access$102(MapOverlayManager.this, null);
        MapOverlayManager.access$202(MapOverlayManager.this, null);
      }
    });
    this.screenSize = Math.min(paramUnveilMapView.getWidth(), paramUnveilMapView.getHeight());
    this.mapViewWatcher = new MapViewWatcher(paramUnveilMapView, this);
    this.mapViewWatcher.start();
    this.resultsOverlay = new SearchResultItemizedOverlay(null);
    this.mapOverlays = paramUnveilMapView.getOverlays();
    this.mapOverlays.add(this.resultsOverlay);
    this.resultsOverlay.doPopulate();
    this.markerSize = this.thumbnailSize;
    this.clusterCache = new ClusterCache(this);
  }

  private void addOnScreenItemsToOverlay()
  {
    GeoPoint localGeoPoint = this.mapView.getMapCenter();
    int i = (int)(5.0D * this.mapView.getLongitudeSpan());
    int j = (int)(5.0D * this.mapView.getLatitudeSpan());
    int k = getRadius();
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(k);
    localUnveilLogger.d("Radius is %d", arrayOfObject);
    Iterator localIterator = this.clusterCache.getTree(k).getItemsInRegion(localGeoPoint.getLongitudeE6() - i / 2, localGeoPoint.getLatitudeE6() - j / 2, localGeoPoint.getLongitudeE6() + i / 2, localGeoPoint.getLatitudeE6() + j / 2).iterator();
    while (localIterator.hasNext())
    {
      ClusterCache.SearchHistoryOverlayItem localSearchHistoryOverlayItem = (ClusterCache.SearchHistoryOverlayItem)((RegionQuadTree.Item)localIterator.next()).getData();
      if ((!localSearchHistoryOverlayItem.isInOverlay()) && (!localSearchHistoryOverlayItem.isHidden()))
        this.resultsOverlay.addOverlayItem(localSearchHistoryOverlayItem);
    }
    this.resultsOverlay.doPopulate();
    this.mapView.invalidate();
  }

  private void applyThumbnail(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem, Picture paramPicture)
  {
    Bitmap localBitmap1 = paramPicture.peekBitmap();
    Bitmap localBitmap2 = wrapBitmapInStackedBubble(localBitmap1, paramSearchHistoryOverlayItem.getCount());
    BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.context.getResources(), localBitmap2);
    boundPointAt(localBitmapDrawable, PixelUtils.dipToPix(this.singleHorizontalMargin + localBitmap1.getWidth() / 2, this.context), 0);
    paramSearchHistoryOverlayItem.setMarker(localBitmapDrawable);
    paramSearchHistoryOverlayItem.setMarkerPicture(paramPicture);
  }

  private static void boundAtPoint(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = i / 2;
    int k = paramDrawable.getIntrinsicHeight();
    paramDrawable.setBounds(-j, 1 - k, i - j, 1);
  }

  private void boundPointAt(Drawable paramDrawable, int paramInt1, int paramInt2)
  {
    int i = paramDrawable.getIntrinsicWidth();
    int j = paramDrawable.getIntrinsicHeight();
    int k = PixelUtils.pixToDip(paramInt1, this.context);
    paramDrawable.setBounds(-k, paramInt2 - j, i - k, paramInt2);
  }

  private void createGenericBubbles()
  {
    if (this.genericBubbles == null)
    {
      this.genericBubbles = new BitmapDrawable[3];
      for (int i = 0; i < 3; i++)
      {
        int j = i + 1;
        Bitmap localBitmap = wrapBitmapInStackedBubble(getUnknownImageBitmap(), j);
        BitmapDrawable localBitmapDrawable = new BitmapDrawable(this.context.getResources(), localBitmap);
        boundAtPoint(localBitmapDrawable, this.singleHorizontalMargin + this.thumbnailSize / 2, 1 + localBitmapDrawable.getIntrinsicHeight());
        this.genericBubbles[i] = localBitmapDrawable;
      }
    }
  }

  private void drawBubbleToCanvas(Canvas paramCanvas, int paramInt1, int paramInt2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, int paramInt3, int paramInt4, int paramInt5)
  {
    paramCanvas.translate(paramInt4 - bubblePinOffsetFromLeft, paramInt5 - paramInt2);
    Paint localPaint = null;
    if (paramBoolean2)
    {
      paramCanvas.translate(-0.9F * (0.5F * -paramInt2), 0.5F * paramInt2);
      paramCanvas.skew(-0.9F, 0.0F);
      paramCanvas.scale(1.0F, 0.5F);
      localPaint = getShadowPaint();
    }
    int i = Math.max(1, Math.min(3, paramInt3));
    paramCanvas.translate(i * this.stackMargin, -this.stackMargin);
    while (i > 1)
    {
      paramCanvas.translate(-this.stackMargin, this.stackMargin);
      paramCanvas.drawBitmap(singlePinTop, 0.0F, 0.0F, null);
      i--;
    }
    paramCanvas.translate(-this.stackMargin, this.stackMargin);
    if (paramBoolean3)
    {
      int j = paramInt1 - this.stackMargin * (i - 1);
      int k = paramInt2 - this.stackMargin * (i - 1);
      int m = -2 + widerPinLeft.getWidth();
      leftNinepatch.draw(paramCanvas, new Rect(0, 0, m, k), localPaint);
      stretchNinepatch.draw(paramCanvas, new Rect(m, 0, j, k), localPaint);
      return;
    }
    paramCanvas.drawBitmap(singlePin, 0.0F, 0.0F, null);
  }

  private void focus(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
  {
    if (this.focusOverlay != null)
    {
      this.focusOverlay.getTarget().setHidden(false);
      this.mapOverlays.remove(this.focusOverlay);
    }
    paramSearchHistoryOverlayItem.setHidden(true);
    this.focusOverlay = new FocusOverlay(paramSearchHistoryOverlayItem);
    this.mapOverlays.add(this.focusOverlay);
    this.mapView.invalidate();
    this.resultsOverlay.removeItem(paramSearchHistoryOverlayItem);
    removeAllItemsFromOverlay();
    addOnScreenItemsToOverlay();
    this.resultsOverlay.doPopulate();
    int i = this.mapView.getHeight() / 2;
    int j = 2 * this.verticalMargin + this.pointerHeight + this.thumbnailSize;
    GeoPoint localGeoPoint1 = new GeoPoint(paramSearchHistoryOverlayItem.getPoint().getLatitudeE6(), paramSearchHistoryOverlayItem.getPoint().getLongitudeE6());
    Point localPoint = new Point();
    this.mapView.getProjection().toPixels(localGeoPoint1, localPoint);
    if (i < j)
      localPoint.y -= j - i;
    localPoint.x += getClusterBubbleWidth(paramSearchHistoryOverlayItem.getChildren(this.clusterCache).size()) / 2 - bubblePinOffsetFromLeft;
    GeoPoint localGeoPoint2 = this.mapView.getProjection().fromPixels(localPoint.x, localPoint.y);
    this.mapView.getController().animateTo(localGeoPoint2);
  }

  private int getClusterBubbleWidth(int paramInt)
  {
    return (int)Math.min(2 * this.horizontalMargin + this.thumbnailSize * Math.max(1, Math.min(5, paramInt)), 0.9D * Math.min(this.mapView.getHeight(), this.mapView.getWidth()));
  }

  private int getRadius()
  {
    if (this.screenSize == 0.0D)
      this.screenSize = Math.min(this.mapView.getWidth(), this.mapView.getHeight());
    return (int)Math.ceil(0.25D * Math.min(this.mapView.getLatitudeSpan(), this.mapView.getLongitudeSpan()) * this.markerSize / this.screenSize);
  }

  private Paint getShadowPaint()
  {
    Paint localPaint = new Paint();
    localPaint.setStyle(Paint.Style.FILL);
    localPaint.setARGB(127, 0, 0, 0);
    return localPaint;
  }

  private ThumbnailProvider getThumbnailCache()
  {
    return ((UnveilApplication)((Activity)this.context).getApplication()).getThumbnailCache();
  }

  private Bitmap getUnknownImageBitmap()
  {
    if (this.unknownImageBitmap == null)
      this.unknownImageBitmap = BitmapFactory.decodeResource(this.context.getResources(), R.drawable.no_thumbnail);
    return this.unknownImageBitmap;
  }

  private void removeAllItemsFromOverlay()
  {
    while (this.resultsOverlay.size() > 0)
      this.resultsOverlay.removeItem(-1 + this.resultsOverlay.size());
  }

  private void removeOffScreenItemsFromOverlay()
  {
    int i = (int)(5.0D * this.mapView.getLongitudeSpan());
    int j = (int)(5.0D * this.mapView.getLatitudeSpan());
    int k = this.mapView.getMapCenter().getLongitudeE6() - i / 2;
    int m = this.mapView.getMapCenter().getLatitudeE6() - j / 2;
    int n = this.mapView.getMapCenter().getLongitudeE6() + i / 2;
    int i1 = this.mapView.getMapCenter().getLatitudeE6() + j / 2;
    for (int i2 = -1 + this.resultsOverlay.size(); i2 >= 0; i2--)
    {
      GeoPoint localGeoPoint = this.resultsOverlay.get(i2).getPoint();
      if ((localGeoPoint.getLatitudeE6() < m) || (localGeoPoint.getLatitudeE6() > i1) || (localGeoPoint.getLongitudeE6() < k) || (localGeoPoint.getLongitudeE6() > n))
        this.resultsOverlay.removeItem(i2);
    }
  }

  private void setGenericBubble(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
  {
    int i = Math.min(3, paramSearchHistoryOverlayItem.getCount());
    if (this.genericBubbles == null)
      createGenericBubbles();
    paramSearchHistoryOverlayItem.setMarker(this.genericBubbles[(i - 1)]);
  }

  private void unfocus()
  {
    if (this.focusOverlay != null)
    {
      this.focusOverlay.getTarget().setFocus(false);
      this.focusOverlay.getTarget().setHidden(false);
      this.mapOverlays.remove(this.focusOverlay);
      this.focusOverlay = null;
      removeAllItemsFromOverlay();
      addOnScreenItemsToOverlay();
      this.resultsOverlay.doPopulate();
    }
    this.mapView.invalidate();
  }

  private Bitmap wrapBitmapInStackedBubble(Bitmap paramBitmap, int paramInt)
  {
    int i = Math.min(paramInt, 3);
    int j = this.thumbnailSize;
    int k = this.thumbnailSize;
    (j + 2 * this.singleHorizontalMargin);
    (k + 2 * this.singleVerticalMargin + this.singlePointerHeight);
    int m = singlePin.getWidth() + this.stackMargin * (i - 1);
    int n = singlePin.getHeight() + this.stackMargin * (i - 1);
    Bitmap localBitmap = Bitmap.createBitmap(m, n, Bitmap.Config.ARGB_8888);
    Canvas localCanvas = new Canvas(localBitmap);
    drawBubbleToCanvas(localCanvas, m, n, true, false, false, i, bubblePinOffsetFromLeft, n);
    localCanvas.drawBitmap(paramBitmap, new Rect(0, 0, -1 + this.thumbnailSize, -1 + this.thumbnailSize), new Rect(this.singleHorizontalMargin, this.singleVerticalMargin, -1 + (j + this.singleHorizontalMargin), -1 + (k + this.singleVerticalMargin)), null);
    return localBitmap;
  }

  public void addItem(SearchHistoryItem paramSearchHistoryItem)
  {
    GeoPoint localGeoPoint = new GeoPoint(paramSearchHistoryItem.getGeoPoint().getLatitudeE6(), paramSearchHistoryItem.getGeoPoint().getLongitudeE6());
    this.minLat = Math.min(this.minLat, localGeoPoint.getLatitudeE6());
    this.minLng = Math.min(this.minLng, localGeoPoint.getLongitudeE6());
    this.maxLat = Math.max(this.maxLat, localGeoPoint.getLatitudeE6());
    this.maxLng = Math.max(this.maxLng, localGeoPoint.getLongitudeE6());
    this.clusterCache.add(new ClusterCache.SearchHistoryOverlayItem(localGeoPoint, paramSearchHistoryItem.getLocation(), paramSearchHistoryItem));
  }

  protected void finalize()
    throws Throwable
  {
    while (true)
    {
      try
      {
        if (this.unknownImageBitmap != null)
        {
          this.unknownImageBitmap.recycle();
          this.unknownImageBitmap = null;
        }
        if (this.genericBubbles != null)
        {
          BitmapDrawable[] arrayOfBitmapDrawable = this.genericBubbles;
          int i = arrayOfBitmapDrawable.length;
          int j = 0;
          if (j >= i)
            break label91;
          arrayOfBitmapDrawable[j].getBitmap().recycle();
          j++;
          continue;
          if (k < 3)
          {
            this.genericBubbles[k] = null;
            k++;
            continue;
          }
        }
        return;
      }
      finally
      {
        super.finalize();
      }
      label91: int k = 0;
    }
  }

  public boolean isCacheInvalid()
  {
    return this.cacheInvalid;
  }

  public void onItemsChanged(AbstractProvider paramAbstractProvider)
  {
    this.cacheInvalid = true;
  }

  public void onMapViewChanged(boolean paramBoolean1, boolean paramBoolean2)
  {
    if (paramBoolean1)
    {
      if (this.focusOverlay != null)
        unfocus();
      removeAllItemsFromOverlay();
    }
    while (true)
    {
      addOnScreenItemsToOverlay();
      this.resultsOverlay.doPopulate();
      return;
      removeOffScreenItemsFromOverlay();
    }
  }

  public void onTapCluster(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem, ArrayList<SearchHistoryItem> paramArrayList)
  {
    this.possiblyTappedClusterItem = paramSearchHistoryOverlayItem;
    this.possiblyTappedCluster = paramArrayList;
    this.possiblyTappedItem = null;
  }

  public void onTapItem(SearchHistoryItem paramSearchHistoryItem, int paramInt)
  {
    this.possiblyTappedCluster = null;
    this.possiblyTappedClusterItem = null;
    this.possiblyTappedItem = paramSearchHistoryItem;
  }

  public void setItemMarker(final ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
  {
    String str = paramSearchHistoryOverlayItem.getSearchHistoryItem().getThumbnailUrl();
    getThumbnailCache().fetch(str, new ThumbnailProvider.ThumbnailListener()
    {
      public void onThumbnailLoading()
      {
        MapOverlayManager.this.setGenericBubble(paramSearchHistoryOverlayItem);
      }

      public void onThumbnailReady(Picture paramAnonymousPicture)
      {
        if (paramSearchHistoryOverlayItem.isInOverlay())
        {
          MapOverlayManager.this.applyThumbnail(paramSearchHistoryOverlayItem, paramAnonymousPicture);
          MapOverlayManager.this.resultsOverlay.doPopulate();
          MapOverlayManager.this.mapView.invalidate();
          MapOverlayManager.this.onMapViewChanged(false, false);
        }
      }
    }
    , ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
  }

  public void setMapBoundsToMinimalBoundingRegion()
  {
    int i = (int)Math.min(180000000.0D, 1.1D * Math.max(1000, this.maxLat - this.minLat));
    int j = (int)Math.min(360000000.0D, 1.1D * Math.max(1000, this.maxLng - this.minLng));
    this.mapView.getController().zoomToSpan(i, j);
    this.mapView.getController().setCenter(new GeoPoint((this.minLat + this.maxLat) / 2, (this.minLng + this.maxLng) / 2));
  }

  public void start()
  {
    this.mapViewWatcher.start();
  }

  public void stop()
  {
    this.mapViewWatcher.stop();
  }

  public void update()
  {
    this.clusterCache.update();
    this.resultsOverlay.doPopulate();
    this.mapView.invalidate();
    onMapViewChanged(true, true);
  }

  private class FocusOverlay extends Overlay
  {
    private boolean consumeAllMotionEvents = false;
    final Gallery gallery;
    final FrameLayout galleryParent;
    final ArrayList<SearchHistoryItem> items;
    final ClusterCache.SearchHistoryOverlayItem target;
    private boolean touching = false;

    public FocusOverlay(ClusterCache.SearchHistoryOverlayItem arg2)
    {
      Object localObject;
      this.target = localObject;
      this.items = localObject.getChildren(MapOverlayManager.this.clusterCache);
      int i = MapOverlayManager.this.getClusterBubbleWidth(this.items.size()) - 2 * MapOverlayManager.this.horizontalMargin;
      int j = MapOverlayManager.this.thumbnailSize;
      this.galleryParent = new FrameLayout(MapOverlayManager.this.context);
      this.galleryParent.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
      this.gallery = new Gallery(MapOverlayManager.this.context);
      this.gallery.setOnItemClickListener(new AdapterView.OnItemClickListener()
      {
        public void onItemClick(AdapterView paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          MapOverlayManager.this.unfocus();
          ItemModels.replay((SearchHistoryItem)MapOverlayManager.FocusOverlay.this.items.get(paramAnonymousInt), MapOverlayManager.this.context);
        }
      });
      this.gallery.setAdapter(new GalleryThumbnailAdapter(null));
      this.gallery.setSpacing((int)(0.1D * MapOverlayManager.this.thumbnailSize));
      this.gallery.setLayoutParams(new FrameLayout.LayoutParams(i, j));
      this.gallery.setSelection(this.items.size() / 2);
      this.galleryParent.addView(this.gallery);
      this.galleryParent.measure(View.MeasureSpec.makeMeasureSpec(i, 1073741824), View.MeasureSpec.makeMeasureSpec(j, 1073741824));
      this.galleryParent.layout(0, 0, this.galleryParent.getMeasuredWidth(), this.galleryParent.getMeasuredHeight());
    }

    public boolean draw(Canvas paramCanvas, MapView paramMapView, boolean paramBoolean, long paramLong)
    {
      paramCanvas.save();
      Point localPoint = new Point();
      paramMapView.getProjection().toPixels(this.target.getPoint(), localPoint);
      int i = this.galleryParent.getWidth();
      int j = this.galleryParent.getHeight();
      int k = i + 2 * MapOverlayManager.this.horizontalMargin;
      int m = j + 2 * MapOverlayManager.this.verticalMargin + MapOverlayManager.this.pointerHeight;
      int n = localPoint.x;
      int i1 = localPoint.y;
      if (paramBoolean)
        MapOverlayManager.this.drawBubbleToCanvas(paramCanvas, k, m, true, true, true, 1, n, i1);
      while (true)
      {
        paramCanvas.restore();
        return true;
        MapOverlayManager.this.drawBubbleToCanvas(paramCanvas, k, m, true, false, true, 1, n, i1);
        paramCanvas.translate(MapOverlayManager.this.horizontalMargin, MapOverlayManager.this.verticalMargin);
        this.galleryParent.draw(paramCanvas);
      }
    }

    public ClusterCache.SearchHistoryOverlayItem getTarget()
    {
      return this.target;
    }

    public boolean onTouchEvent(MotionEvent paramMotionEvent, MapView paramMapView)
    {
      Point localPoint = new Point();
      paramMapView.getProjection().toPixels(this.target.getPoint(), localPoint);
      float f1 = paramMotionEvent.getX();
      float f2 = paramMotionEvent.getY();
      int i = this.galleryParent.getWidth();
      int j = this.galleryParent.getHeight();
      int k = i + 2 * MapOverlayManager.this.horizontalMargin;
      int m = j + 2 * MapOverlayManager.this.verticalMargin + MapOverlayManager.this.pointerHeight;
      int n = localPoint.x - MapOverlayManager.bubblePinOffsetFromLeft;
      int i1 = localPoint.y - m;
      boolean bool;
      int i2;
      if ((f1 >= n) && (f2 >= i1) && (f1 < k + (localPoint.x - MapOverlayManager.bubblePinOffsetFromLeft)) && (f2 < j + (localPoint.y - m)))
      {
        bool = true;
        if ((paramMotionEvent.getAction() != 0) && (paramMotionEvent.getAction() != 2))
          break label273;
        i2 = 1;
        label185: if ((this.touching) || (i2 == 0))
          break label279;
        this.touching = true;
      }
      for (this.consumeAllMotionEvents = bool; ; this.consumeAllMotionEvents = false)
      {
        label273: label279: 
        do
        {
          if (bool)
          {
            int i3 = (int)(paramMotionEvent.getX() - n);
            int i4 = (int)(paramMotionEvent.getY() - i1);
            paramMotionEvent.setLocation(i3, i4);
            this.galleryParent.dispatchTouchEvent(paramMotionEvent);
            paramMotionEvent.setLocation(f1, f2);
          }
          return this.consumeAllMotionEvents;
          bool = false;
          break;
          i2 = 0;
          break label185;
        }
        while ((paramMotionEvent.getAction() != 3) && (paramMotionEvent.getAction() != 1) && (paramMotionEvent.getAction() != 4));
        this.touching = false;
      }
    }

    private class GalleryThumbnailAdapter extends BaseAdapter
    {
      private GalleryThumbnailAdapter()
      {
      }

      public int getCount()
      {
        return MapOverlayManager.FocusOverlay.this.target.getCount();
      }

      public Object getItem(int paramInt)
      {
        return Integer.valueOf(paramInt);
      }

      public long getItemId(int paramInt)
      {
        return paramInt;
      }

      public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
      {
        if ((paramInt < 0) || (paramInt > MapOverlayManager.FocusOverlay.this.target.getCount()))
        {
          View localView = new View(MapOverlayManager.this.context);
          localView.setLayoutParams(new Gallery.LayoutParams(MapOverlayManager.this.thumbnailSize, MapOverlayManager.this.thumbnailSize));
          return localView;
        }
        final ImageView localImageView = new ImageView(MapOverlayManager.this.context);
        SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)MapOverlayManager.FocusOverlay.this.items.get(paramInt);
        localImageView.setImageBitmap(MapOverlayManager.this.getUnknownImageBitmap());
        localImageView.setLayoutParams(new Gallery.LayoutParams(MapOverlayManager.this.thumbnailSize, MapOverlayManager.this.thumbnailSize));
        ((UnveilApplication)((Activity)MapOverlayManager.this.context).getApplication());
        ThumbnailProvider.ThumbnailListener local1 = new ThumbnailProvider.ThumbnailListener()
        {
          public void onThumbnailReady(Picture paramAnonymousPicture)
          {
            if (paramAnonymousPicture != null)
            {
              MapOverlayManager.FocusOverlay.this.gallery.invalidate();
              MapOverlayManager.FocusOverlay.this.galleryParent.invalidate();
              paramAnonymousPicture.populateWithBitmap(localImageView);
              localImageView.setLayoutParams(new Gallery.LayoutParams(MapOverlayManager.this.thumbnailSize, MapOverlayManager.this.thumbnailSize));
              localImageView.requestLayout();
            }
          }
        };
        MapOverlayManager.this.getThumbnailCache().fetch(localSearchHistoryItem.getThumbnailUrl(), local1, ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
        return localImageView;
      }
    }
  }

  private class SearchResultItemizedOverlay extends ItemizedOverlay<ClusterCache.SearchHistoryOverlayItem>
  {
    private final ArrayList<ClusterCache.SearchHistoryOverlayItem> overlayItems = new ArrayList();

    public SearchResultItemizedOverlay(Drawable arg2)
    {
      super();
    }

    public void addOverlayItem(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
    {
      this.overlayItems.add(paramSearchHistoryOverlayItem);
      paramSearchHistoryOverlayItem.setInOverlay(true);
      MapOverlayManager.this.setItemMarker(paramSearchHistoryOverlayItem);
    }

    public void clear()
    {
      Iterator localIterator = this.overlayItems.iterator();
      while (localIterator.hasNext())
        ((ClusterCache.SearchHistoryOverlayItem)localIterator.next()).setInOverlay(false);
      this.overlayItems.clear();
    }

    protected ClusterCache.SearchHistoryOverlayItem createItem(int paramInt)
    {
      return (ClusterCache.SearchHistoryOverlayItem)this.overlayItems.get(paramInt);
    }

    public void doPopulate()
    {
      setLastFocusedIndex(-1);
      super.populate();
    }

    public ClusterCache.SearchHistoryOverlayItem get(int paramInt)
    {
      return (ClusterCache.SearchHistoryOverlayItem)this.overlayItems.get(paramInt);
    }

    protected boolean onTap(int paramInt)
    {
      ((ClusterCache.SearchHistoryOverlayItem)this.overlayItems.get(paramInt)).onTap(MapOverlayManager.this.clusterCache, MapOverlayManager.this.context);
      return true;
    }

    public void removeItem(int paramInt)
    {
      ((ClusterCache.SearchHistoryOverlayItem)this.overlayItems.get(paramInt)).clearState();
      this.overlayItems.remove(paramInt);
    }

    public void removeItem(ClusterCache.SearchHistoryOverlayItem paramSearchHistoryOverlayItem)
    {
      for (int i = 0; ; i++)
        if (i < this.overlayItems.size())
        {
          if (this.overlayItems.get(i) == paramSearchHistoryOverlayItem)
          {
            this.overlayItems.remove(i);
            paramSearchHistoryOverlayItem.clearState();
          }
        }
        else
          return;
    }

    public int size()
    {
      return this.overlayItems.size();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.MapOverlayManager
 * JD-Core Version:    0.6.2
 */