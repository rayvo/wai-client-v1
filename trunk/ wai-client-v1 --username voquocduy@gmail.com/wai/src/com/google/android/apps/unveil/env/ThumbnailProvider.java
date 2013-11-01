package com.google.android.apps.unveil.env;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.widget.ImageView;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.cache.AbstractCache;
import com.google.android.apps.unveil.env.cache.DatabaseCache;
import com.google.android.apps.unveil.env.cache.DatabaseCache.PictureFactory;
import com.google.android.apps.unveil.env.cache.LayeredCache;
import com.google.android.apps.unveil.env.cache.MemoryCache;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.AbstractConnector.ConnectorException;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.utils.Stats;
import com.google.android.apps.unveil.network.utils.Stats.Tags;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.client.methods.HttpGet;

public class ThumbnailProvider
{
  private static final int DEFAULT_MAX_IN_DISK_PICTURES = 100;
  private static final int DEFAULT_MAX_IN_MEMORY_PICTURES = 20;
  private static final float DEFAULT_RECLAIM_RATIO = 0.3F;
  private static final int FETCH_THREAD_KEEP_ALIVE_TIME_SECONDS = 10;
  private static final int MAX_SIMULTANEOUS_FETCHES = 4;
  public static final int SIZE_DIP = 80;
  private static final UnveilLogger logger = new UnveilLogger();
  private final DefaultHttpRequestFactory anonymousRequests;
  private final DefaultHttpRequestFactory authenticatedRequests;
  private final AbstractCache<String, Picture> cache;
  private final AbstractConnector connector;
  private final Context context;
  private boolean enabled = true;
  private final Map<String, List<ThumbnailListener>> pendingFetches = new HashMap();
  private final ExecutorService threadPool;
  private final Handler uiThreadHandler;

  ThumbnailProvider(Context paramContext, Handler paramHandler, AbstractCache<String, Picture> paramAbstractCache, ExecutorService paramExecutorService, DefaultHttpRequestFactory paramDefaultHttpRequestFactory1, AbstractConnector paramAbstractConnector, DefaultHttpRequestFactory paramDefaultHttpRequestFactory2)
  {
    this.context = paramContext;
    this.threadPool = paramExecutorService;
    this.uiThreadHandler = paramHandler;
    this.cache = paramAbstractCache;
    this.authenticatedRequests = paramDefaultHttpRequestFactory1;
    this.anonymousRequests = paramDefaultHttpRequestFactory2;
    this.connector = paramAbstractConnector;
  }

  private List<ThumbnailListener> createAndPushFetcher(String paramString, Runnable paramRunnable)
  {
    List localList = Collections.synchronizedList(new ArrayList());
    this.threadPool.submit(paramRunnable);
    this.pendingFetches.put(paramString, localList);
    return localList;
  }

  private Picture get(String paramString)
  {
    return (Picture)this.cache.get(paramString);
  }

  public static final ThumbnailProvider makeDefault(Context paramContext, ContentResolver paramContentResolver, Uri paramUri, Handler paramHandler)
  {
    AbstractCache localAbstractCache = LayeredCache.layer(new MemoryCache(20, 0.3F), DatabaseCache.makeDefault(paramContentResolver, paramUri, 100, 0.3F, new BitmapPictureFactory(null)));
    ExecutorService localExecutorService = ExecutorServiceFactory.newLoggingExecutor(ExecutorServiceFactory.Order.LIFO, 4, 10);
    UnveilContext localUnveilContext = (UnveilContext)paramContext.getApplicationContext();
    return new ThumbnailProvider(paramContext, paramHandler, localAbstractCache, localExecutorService, DefaultHttpRequestFactory.newAuthenticatedRequestFactory(localUnveilContext), localUnveilContext.getConnector(), DefaultHttpRequestFactory.newAnonymousRequestFactory(localUnveilContext));
  }

  private Runnable makeFetchRunnable(final String paramString1, final String paramString2)
  {
    return new Runnable()
    {
      public void run()
      {
        HttpGet localHttpGet;
        if (ThumbnailProvider.this.isGoogleUrl(paramString2))
          localHttpGet = ThumbnailProvider.this.authenticatedRequests.newGetRequest(paramString2);
        while (true)
        {
          ThumbnailProvider.FetchResponseHandler localFetchResponseHandler = new ThumbnailProvider.FetchResponseHandler(ThumbnailProvider.this, paramString1, null);
          Stats.setThreadTag(Stats.Tags.THUMBNAIL_FETCH);
          try
          {
            localFetchResponseHandler.onResponseReceived(ThumbnailProvider.this.connector.blockingRequest(localHttpGet));
            return;
            localHttpGet = ThumbnailProvider.this.anonymousRequests.newGetRequest(paramString2);
            localHttpGet.setHeader("Connection", "close");
          }
          catch (AbstractConnector.ConnectorException localConnectorException)
          {
            localFetchResponseHandler.onResponseReceived(null);
          }
        }
      }
    };
  }

  public static ThumbnailListener makeImageViewThumbnailListener(ImageView paramImageView, String paramString)
  {
    return new ImageViewThumbnailListener(paramImageView, paramString);
  }

  private void maybeAddAuxillaryListener(ThumbnailListener paramThumbnailListener, String paramString, List<ThumbnailListener> paramList)
  {
    Picture localPicture = (Picture)this.cache.get(paramString);
    if (localPicture != null)
    {
      paramThumbnailListener.onThumbnailReady(localPicture);
      return;
    }
    paramList.add(paramThumbnailListener);
  }

  private static Picture toPicture(byte[] paramArrayOfByte)
  {
    Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    if (localBitmap != null)
      return PictureFactory.createBitmap(localBitmap, 0);
    return null;
  }

  public void fetch(String paramString, ThumbnailListener paramThumbnailListener, SizeSpec paramSizeSpec)
  {
    if (!this.enabled)
      return;
    if (TextUtils.isEmpty(paramString))
      throw new IllegalArgumentException("keyUrl must not be empty.");
    if (Looper.myLooper() != Looper.getMainLooper())
      throw new AssertionError();
    Picture localPicture = get(paramString);
    if (localPicture != null)
    {
      paramThumbnailListener.onThumbnailReady(localPicture);
      return;
    }
    paramThumbnailListener.onThumbnailLoading();
    String str = paramSizeSpec.getSizedUrl(this.context, paramString);
    synchronized (this.pendingFetches)
    {
      List localList = (List)this.pendingFetches.get(paramString);
      if (localList == null)
        localList = createAndPushFetcher(paramString, makeFetchRunnable(paramString, str));
      maybeAddAuxillaryListener(paramThumbnailListener, paramString, localList);
      return;
    }
  }

  protected boolean isGoogleUrl(String paramString)
  {
    return (ThumbnailProvider.SizeSpec.FifeSpec.isFifeUrl(paramString)) || (paramString.startsWith(((UnveilContext)this.context.getApplicationContext()).getFrontendUrl().toString())) || (Pattern.compile("^((http(s)?):)?\\/\\/(www\\.)google\\.com\\/").matcher(paramString).find()) || (paramString.contains("gstatic"));
  }

  Picture put(String paramString, byte[] paramArrayOfByte)
  {
    Picture localPicture = toPicture(paramArrayOfByte);
    if (localPicture == null)
    {
      logger.e("Could not cache picture for key %s", new Object[] { paramString });
      return null;
    }
    this.cache.put(paramString, localPicture);
    return localPicture;
  }

  public void setFetchEnabled(boolean paramBoolean)
  {
    if (!paramBoolean)
      logger.w("Thumbnail fetching is disabled! Please make sure this only happens in tests.", new Object[0]);
    this.enabled = paramBoolean;
  }

  private static final class BitmapPictureFactory
    implements DatabaseCache.PictureFactory
  {
    public Picture toPicture(byte[] paramArrayOfByte)
    {
      return ThumbnailProvider.toPicture(paramArrayOfByte);
    }
  }

  private class FetchResponseHandler
  {
    private final String keyUrl;

    private FetchResponseHandler(String arg2)
    {
      Object localObject;
      this.keyUrl = localObject;
    }

    protected void onResponseReceived(FetchResponse paramFetchResponse)
    {
      if (FetchResponse.isValid(paramFetchResponse))
      {
        final Picture localPicture = ThumbnailProvider.this.put(this.keyUrl, paramFetchResponse.getData());
        if (localPicture == null)
          return;
        synchronized (ThumbnailProvider.this.pendingFetches)
        {
          final ArrayList localArrayList = new ArrayList((Collection)ThumbnailProvider.this.pendingFetches.get(this.keyUrl));
          ThumbnailProvider.this.pendingFetches.remove(this.keyUrl);
          ThumbnailProvider.this.uiThreadHandler.post(new Runnable()
          {
            public void run()
            {
              Iterator localIterator = localArrayList.iterator();
              while (localIterator.hasNext())
                ((ThumbnailProvider.ThumbnailListener)localIterator.next()).onThumbnailReady(localPicture);
            }
          });
          return;
        }
      }
      UnveilLogger localUnveilLogger = ThumbnailProvider.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.keyUrl;
      if (paramFetchResponse == null);
      while (true)
      {
        arrayOfObject[1] = paramFetchResponse;
        localUnveilLogger.e("Invalid thumbnail fetch response for (%s): %s", arrayOfObject);
        return;
        paramFetchResponse = String.valueOf(paramFetchResponse.getStatusCode());
      }
    }
  }

  private static class ImageViewThumbnailListener extends ThumbnailProvider.ThumbnailListener
  {
    private final ImageView imageView;
    private final String url;

    public ImageViewThumbnailListener(ImageView paramImageView, String paramString)
    {
      this.imageView = paramImageView;
      this.url = paramString;
      paramImageView.setTag(paramString);
    }

    public void onThumbnailReady(Picture paramPicture)
    {
      if (this.url.equals(this.imageView.getTag()))
      {
        this.imageView.setImageBitmap(paramPicture.peekBitmap());
        this.imageView.setTag(paramPicture);
      }
    }
  }

  public static class SizeSpec
  {
    public static final SizeSpec FIFE_DEFAULT = new FifeDpSpec(80, 80);
    private static final int FIFE_MAX_SIZE_PX = 1600;
    public static final SizeSpec FIFE_ORIGINAL = new FifeSpec(1600, 1600);
    public static final SizeSpec ORIGINAL = new SizeSpec();

    public static SizeSpec makeCustomFifeSpec(int paramInt1, int paramInt2)
    {
      return new FifeDpSpec(paramInt1, paramInt2);
    }

    public String getSizedUrl(Context paramContext, String paramString)
    {
      return paramString;
    }

    static class FifeDpSpec extends ThumbnailProvider.SizeSpec.FifeSpec
    {
      public FifeDpSpec(int paramInt1, int paramInt2)
      {
        super(paramInt2);
      }

      public String getSizedUrl(Context paramContext, String paramString)
      {
        return ThumbnailProvider.SizeSpec.FifeSpec.getSizedUrl(paramString, PixelUtils.dipToPix(this.width, paramContext), PixelUtils.dipToPix(this.height, paramContext), true);
      }
    }

    static class FifeSpec extends ThumbnailProvider.SizeSpec
    {
      private static final Pattern FIFE_HOSTED_IMAGE_URL_RE = Pattern.compile("^((http(s)?):)?\\/\\/((((lh[3-6]\\.((ggpht)|(googleusercontent)|(google)))|([1-4]\\.bp\\.blogspot)|(bp[0-3]\\.blogger))\\.com)|(www\\.google\\.com\\/visualsearch\\/lh))\\/");
      private static final String PATH_DELIMITER = "=";
      protected final int height;
      protected final int width;

      public FifeSpec(int paramInt1, int paramInt2)
      {
        this.width = paramInt1;
        this.height = paramInt2;
      }

      static String everythingButFifeParameters(String paramString)
      {
        if (isFifeUrl(paramString))
        {
          int i = paramString.indexOf("=");
          if (i == -1)
            return paramString;
          return paramString.substring(0, i);
        }
        ThumbnailProvider.logger.w("Tried to strip FIFE parameters from non-FIFE url: %s", new Object[] { paramString });
        return paramString;
      }

      protected static String getSizedUrl(String paramString, int paramInt1, int paramInt2, boolean paramBoolean)
      {
        if (!isFifeUrl(paramString))
        {
          ThumbnailProvider.logger.w("Tried to apply FIFE size parameters to non-FIFE url: %s", new Object[] { paramString });
          return paramString;
        }
        String str1 = everythingButFifeParameters(paramString);
        if ((paramBoolean) && (paramInt2 != paramInt1))
          ThumbnailProvider.logger.w("Square FIFE crop requested but width != height, returned image will be scaled up to larger of the two dimensions.", new Object[0]);
        Object[] arrayOfObject = new Object[4];
        arrayOfObject[0] = str1;
        arrayOfObject[1] = Integer.valueOf(Math.min(paramInt2, 1600));
        arrayOfObject[2] = Integer.valueOf(Math.min(paramInt1, 1600));
        if (paramBoolean);
        for (String str2 = "-c"; ; str2 = "")
        {
          arrayOfObject[3] = str2;
          return String.format("%s=w%d-h%d%s", arrayOfObject);
        }
      }

      static boolean isFifeUrl(String paramString)
      {
        return FIFE_HOSTED_IMAGE_URL_RE.matcher(paramString).find();
      }

      public String getSizedUrl(Context paramContext, String paramString)
      {
        return getSizedUrl(paramString, this.height, this.width, false);
      }
    }
  }

  public static abstract class ThumbnailListener
  {
    public void onThumbnailLoading()
    {
    }

    public abstract void onThumbnailReady(Picture paramPicture);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.ThumbnailProvider
 * JD-Core Version:    0.6.2
 */