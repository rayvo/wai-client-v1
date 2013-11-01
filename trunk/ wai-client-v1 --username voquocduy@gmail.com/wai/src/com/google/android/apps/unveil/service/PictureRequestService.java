package com.google.android.apps.unveil.service;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.provider.MediaStore.Images.Media;
import android.text.TextUtils;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.PictureLoadingException;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.media.ImageLoader;
import com.google.android.apps.unveil.env.media.ImageLoader.DescriptionExclusionPolicy;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.env.media.ImageLoader.Policy;
import com.google.android.apps.unveil.env.media.ImageLoader.ThrottlingPolicy;
import com.google.android.apps.unveil.env.media.InvalidUriException;
import com.google.android.apps.unveil.env.media.MediaStoreMerger;
import com.google.android.apps.unveil.env.media.MediaStoreMergerAdapter;
import com.google.android.apps.unveil.env.media.MediaStoreMergerAdapter.MediaStoreMergerFactory;
import com.google.android.apps.unveil.protocol.QueryBuilder;
import com.google.android.apps.unveil.protocol.QueryManager;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.LatLngProtos.LatLng;
import com.google.goggles.LatLngProtos.LatLng.Builder;
import com.google.goggles.LocationProtos.Location;
import com.google.goggles.LocationProtos.Location.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class PictureRequestService extends AuthenticatedService
{
  private static final Pattern ACCEPTABLE_PATHS_PATTERN = Pattern.compile("/([^/]*camera[^/]*|dcim)/", 2);
  private static final String ACTION_DISABLE_REQUEST = "com.google.android.apps.unveil.service.DISABLE_REQUEST";
  private static final String ACTION_ENABLE_REQUEST = "com.google.android.apps.unveil.service.ENABLE_REQUEST";
  private static final String ACTION_ON_SEEN_REQUEST = "com.google.android.apps.unveil.service.ON_SEEN_REQUEST";
  private static final String ACTION_POLL_REQUEST = "com.google.android.apps.unveil.service.POLL_REQUEST";
  private static final double BACKOFF_EXPONENT = 2.0D;
  private static final List<Uri> CONTENT_URIS;
  public static final String EXTRA_SEEN_RESULT_IDS_KEY = "seen_result_ids";
  private static final double FUZZ_FRACTION = 0.1000000014901161D;
  private static final long MAX_BACKOFF_INTERVAL_MS = 480000L;
  private static final long POLL_PERIOD_MS = 60000L;
  private static final int THROTTLE_LIMIT = 12;
  private static final UnveilLogger logger = new UnveilLogger();
  private BackoffHandler backoffHandler;
  private FreshnessHelper freshnessHelper;
  private ImageLoader imageLoader;
  private NotificationHelper notifier;
  private QueryExecutor queryExecutor;
  private TokenInvalidator tokenInvalidator;

  static
  {
    Uri[] arrayOfUri = new Uri[2];
    arrayOfUri[0] = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    arrayOfUri[1] = MediaStore.Images.Media.INTERNAL_CONTENT_URI;
    CONTENT_URIS = Arrays.asList(arrayOfUri);
  }

  private boolean canUseCurrentNetwork()
  {
    ConnectivityManager localConnectivityManager = (ConnectivityManager)getSystemService("connectivity");
    return BackgroundGogglesPreferencesActivity.MobileConnectionSettings.get(this).isBackgroundGogglingAllowed(localConnectivityManager.getActiveNetworkInfo());
  }

  private List<ImageLoader.Policy<ImageLoader.Image>> getImageLoadingPolicies(FreshnessHelper paramFreshnessHelper)
  {
    ArrayList localArrayList = new ArrayList(4);
    localArrayList.add(new FreshnessPolicy(paramFreshnessHelper));
    localArrayList.add(new PathFilterPolicy(ACCEPTABLE_PATHS_PATTERN));
    localArrayList.add(new ImageLoader.DescriptionExclusionPolicy("Goggles-generated Image"));
    localArrayList.add(new ImageLoader.ThrottlingPolicy(12));
    return localArrayList;
  }

  static LocationProtos.Location getLocation(ImageLoader.Image paramImage)
  {
    LatLngProtos.LatLng.Builder localBuilder = LatLngProtos.LatLng.newBuilder().setLatDegrees(paramImage.latitude).setLngDegrees(paramImage.longitude);
    return LocationProtos.Location.newBuilder().setLatLng(localBuilder).build();
  }

  private int handleIncomingActionOnSeen(Intent paramIntent, int paramInt1, int paramInt2)
  {
    List localList = (List)paramIntent.getSerializableExtra("seen_result_ids");
    this.notifier.handleSeenResults(localList);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  private int handleIncomingActionPoll(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if (!canUseCurrentNetwork())
    {
      scheduleNextPoll();
      return 2;
    }
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  private boolean isBackgroundGogglingEnabled()
  {
    return Settings.getBoolean(this, R.string.background_goggle_key);
  }

  private static Intent makeDisableIntent(Context paramContext)
  {
    return makeIntentForAction(paramContext, "com.google.android.apps.unveil.service.DISABLE_REQUEST");
  }

  private static Intent makeEnableIntent(Context paramContext)
  {
    return makeIntentForAction(paramContext, "com.google.android.apps.unveil.service.ENABLE_REQUEST");
  }

  private static Intent makeIntentForAction(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramString);
    localIntent.setPackage(paramContext.getPackageName());
    return localIntent;
  }

  public static Intent makeOnSeenIntent(Context paramContext, List<String> paramList)
  {
    Intent localIntent = makeIntentForAction(paramContext, "com.google.android.apps.unveil.service.ON_SEEN_REQUEST");
    if ((paramList != null) && (!paramList.isEmpty()))
      localIntent.putExtra("seen_result_ids", new ArrayList(paramList));
    return localIntent;
  }

  public static Intent makePollIntent(Context paramContext)
  {
    return makeIntentForAction(paramContext, "com.google.android.apps.unveil.service.POLL_REQUEST");
  }

  public static void notifyOfSeenItems(Context paramContext, Intent paramIntent)
  {
    paramContext.startService(makeOnSeenIntent(paramContext, (List)paramIntent.getSerializableExtra("seen_result_ids")));
  }

  public static void onDisable(Context paramContext)
  {
    paramContext.startService(makeDisableIntent(paramContext));
  }

  public static void onEnable(Context paramContext)
  {
    paramContext.startService(makeEnableIntent(paramContext));
  }

  private void scheduleNextPoll()
  {
    Context localContext = getApplicationContext();
    ((AlarmManager)localContext.getSystemService("alarm")).set(1, Math.max(60000L + System.currentTimeMillis(), this.backoffHandler.getEarliestAllowedQueryTime()), PendingIntent.getService(localContext, -1, makePollIntent(localContext), 134217728));
  }

  private void sendAndClearQueries()
  {
    if (this.backoffHandler.canQuery(System.currentTimeMillis()))
      this.queryExecutor.execute(this.imageLoader.getImages());
  }

  static QueryBuilder toQueryBuilder(ImageLoader.Image paramImage, Picture paramPicture)
  {
    QueryBuilder localQueryBuilder = new QueryBuilder().addImageData(paramPicture.getJpegData(), paramPicture.getSize());
    if ((paramImage.latitude != 0.0D) || (paramImage.longitude != 0.0D))
      localQueryBuilder.addLocation(getLocation(paramImage));
    localQueryBuilder.setSource(GogglesProtos.GogglesRequest.Source.BACKGROUND);
    return localQueryBuilder;
  }

  public Picture blockingLoadAndDownsample(ImageLoader.Image paramImage)
    throws PictureLoadingException
  {
    if ((paramImage == null) || (paramImage.imageUri == null))
      throw new PictureLoadingException("No image uri specified.");
    logger.i("Creating a Picture from %s", new Object[] { paramImage });
    Picture localPicture1 = ImageUtils.getPicture(getContentResolver(), paramImage);
    if (localPicture1 == null)
      throw new PictureLoadingException("Loading raw image failed.");
    byte[] arrayOfByte;
    try
    {
      arrayOfByte = ImageUtils.rotateAndCompressImage(localPicture1, localPicture1.getOrientation(), 408960);
      if (arrayOfByte == null)
        throw new PictureLoadingException("Failed to rotate and compress image.");
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      throw new PictureLoadingException("OutOfMemoryError when rotating photo.");
    }
    Picture localPicture2 = PictureFactory.createJpeg(arrayOfByte, localPicture1.getOrientation(), localPicture1.getSource());
    return localPicture2;
  }

  int getStartMode(Intent paramIntent, int paramInt1, int paramInt2)
  {
    return 2;
  }

  protected void onAuthFailure(Intent paramIntent1, int paramInt1, int paramInt2, Intent paramIntent2)
  {
    onAuthSuccess(paramIntent1, paramInt1, paramInt2);
  }

  protected void onAuthSuccess(Intent paramIntent, int paramInt1, int paramInt2)
  {
    String str = paramIntent.getAction();
    if (str.equals("com.google.android.apps.unveil.service.POLL_REQUEST"))
      sendAndClearQueries();
    while (true)
    {
      scheduleNextPoll();
      return;
      if (str.equals("com.google.android.apps.unveil.service.ENABLE_REQUEST"))
      {
        this.freshnessHelper.setFreshnessTimestamp(System.currentTimeMillis());
        this.notifier.handleServiceEnabled(this);
      }
    }
  }

  public void onCreate()
  {
    super.onCreate();
    SharedPreferences localSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    this.notifier = new NotificationHelper(localSharedPreferences, (NotificationManager)getSystemService("notification"));
    this.freshnessHelper = new FreshnessHelper(localSharedPreferences);
    this.imageLoader = new ImageLoader(new MediaStoreMergerAdapter(new MediaStoreMergerAdapter.MediaStoreMergerFactory()
    {
      public MediaStoreMerger makeMediaStoreMerger()
      {
        try
        {
          MediaStoreMerger localMediaStoreMerger = MediaStoreMerger.fromUris(PictureRequestService.this.getApplicationContext().getContentResolver(), PictureRequestService.CONTENT_URIS);
          return localMediaStoreMerger;
        }
        catch (InvalidUriException localInvalidUriException)
        {
          throw new AssertionError(localInvalidUriException);
        }
      }
    }), getImageLoadingPolicies(this.freshnessHelper));
    this.backoffHandler = new BackoffHandler(localSharedPreferences, 60000L, 2.0D, 480000L, 0.1000000014901161D);
    UnveilContext localUnveilContext = this.application;
    QueryFactory localQueryFactory = new QueryFactory(null);
    QueryManager localQueryManager = this.application.getQueryManager();
    QueryExecutor.PostExecutionHook[] arrayOfPostExecutionHook = new QueryExecutor.PostExecutionHook[2];
    arrayOfPostExecutionHook[0] = new FreshnessHook(this.freshnessHelper);
    arrayOfPostExecutionHook[1] = this.backoffHandler.makeHook();
    this.queryExecutor = new QueueingQueryExecutor(localUnveilContext, localQueryFactory, localQueryManager, Arrays.asList(arrayOfPostExecutionHook));
    this.tokenInvalidator = new AuthenticatedServiceTokenInvalidator(null);
  }

  protected void onNoAuth(Intent paramIntent, int paramInt1, int paramInt2)
  {
    onAuthSuccess(paramIntent, paramInt1, paramInt2);
  }

  public int onStartCommand(Intent paramIntent, int paramInt1, int paramInt2)
  {
    if ((paramIntent == null) || (TextUtils.isEmpty(paramIntent.getAction())))
    {
      if (isBackgroundGogglingEnabled())
        scheduleNextPoll();
      return getStartMode(paramIntent, paramInt1, paramInt2);
    }
    String str = paramIntent.getAction();
    if (!isBackgroundGogglingEnabled())
    {
      if (str.equals("com.google.android.apps.unveil.service.DISABLE_REQUEST"))
        this.notifier.handleServiceDisabled();
      stopSelf();
      return getStartMode(paramIntent, paramInt1, paramInt2);
    }
    if (str.equals("com.google.android.apps.unveil.service.POLL_REQUEST"))
      return handleIncomingActionPoll(paramIntent, paramInt1, paramInt2);
    if (str.equals("com.google.android.apps.unveil.service.ON_SEEN_REQUEST"))
      return handleIncomingActionOnSeen(paramIntent, paramInt1, paramInt2);
    return super.onStartCommand(paramIntent, paramInt1, paramInt2);
  }

  private final class AuthenticatedServiceTokenInvalidator
    implements PictureRequestService.TokenInvalidator
  {
    private AuthenticatedServiceTokenInvalidator()
    {
    }

    public void invalidateAuthToken()
    {
      PictureRequestService.this.invalidateAuthToken();
    }
  }

  private class QueryFactory
    implements QueryExecutor.BackgroundQueryFactory
  {
    private QueryFactory()
    {
    }

    private BackgroundQuery createQuery(ImageLoader.Image paramImage)
      throws PictureLoadingException
    {
      Picture localPicture = PictureRequestService.this.blockingLoadAndDownsample(paramImage);
      QueryBuilder localQueryBuilder = PictureRequestService.toQueryBuilder(paramImage, localPicture);
      Bitmap localBitmap = localPicture.peekBitmap();
      if (localBitmap == null)
      {
        PictureRequestService.logger.e("Failed to get query bitmap.", new Object[0]);
        return null;
      }
      return new BackgroundQuery(localQueryBuilder, localBitmap, paramImage.dateTaken, makeListener(paramImage.imageUri, paramImage.orientation, localQueryBuilder, localBitmap));
    }

    private BackgroundQueryListener makeListener(Uri paramUri, int paramInt, QueryBuilder paramQueryBuilder, Bitmap paramBitmap)
    {
      return new BackgroundQueryListener(PictureRequestService.this, PictureRequestService.this.notifier, paramUri, paramBitmap, paramInt, PictureRequestService.this.tokenInvalidator);
    }

    public void make(ImageLoader.Image paramImage, final QueryExecutor.OnBackgroundQueryReadyListener paramOnBackgroundQueryReadyListener)
    {
      new AsyncTask()
      {
        protected BackgroundQuery doInBackground(ImageLoader.Image[] paramAnonymousArrayOfImage)
        {
          try
          {
            BackgroundQuery localBackgroundQuery = PictureRequestService.QueryFactory.this.createQuery(paramAnonymousArrayOfImage[0]);
            return localBackgroundQuery;
          }
          catch (PictureLoadingException localPictureLoadingException)
          {
            PictureRequestService.logger.e(localPictureLoadingException, "Failed to create BackgroundQuery", new Object[0]);
          }
          return null;
        }

        protected void onPostExecute(BackgroundQuery paramAnonymousBackgroundQuery)
        {
          paramOnBackgroundQueryReadyListener.onBackgroundQueryReady(paramAnonymousBackgroundQuery);
        }
      }
      .execute(new ImageLoader.Image[] { paramImage });
    }
  }

  public static abstract interface TokenInvalidator
  {
    public abstract void invalidateAuthToken();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.PictureRequestService
 * JD-Core Version:    0.6.2
 */