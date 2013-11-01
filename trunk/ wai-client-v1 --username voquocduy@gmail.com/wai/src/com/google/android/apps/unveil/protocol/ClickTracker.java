package com.google.android.apps.unveil.protocol;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.text.TextUtils;
import android.view.View;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.UnveilResponse;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.NativeClientLoggingProtos;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class ClickTracker
{
  public static final int DEFAULT_MAX_PARALLEL_REQUESTS = 2;
  private static final Set<NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET> TRACKING_ID_REQUIRED_CLICKS = EnumSet.of(localCLICK_TARGET, arrayOfCLICK_TARGET);
  private static final UnveilLogger logger = new UnveilLogger();
  private final SharedPreferences.OnSharedPreferenceChangeListener clickTrackingPreferenceChangeListener;
  private final ClickLogConnector connector;
  private final ArrayList<NativeClientLoggingProtos.NativeClientLogEventRequest.Builder> logRequestsWithoutTrackingIds;
  private final AbstractConnector.ResponseHandler<NativeClientLoggingProtos.NativeClientLogEventResponse> responseHandler = new AbstractConnector.ResponseHandler()
  {
    public void onNetworkError()
    {
      ClickTracker.logger.e("Failed to send logs.", new Object[0]);
    }

    public void onResponse(UnveilResponse<NativeClientLoggingProtos.NativeClientLogEventResponse> paramAnonymousUnveilResponse)
    {
      ClickTracker.logger.i("Succesffully sent click log.", new Object[0]);
    }

    public void onServerErrorCode(int paramAnonymousInt)
    {
      UnveilLogger localUnveilLogger = ClickTracker.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramAnonymousInt);
      localUnveilLogger.e("Server responded %d to log request.", arrayOfObject);
    }
  };
  private String sessionId;
  private String trackingId;

  static
  {
    CLICK_TARGET localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_CLICK_IN_RESULTS_LIST_VALUE);
    CLICK_TARGET[] arrayOfCLICK_TARGET = new NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET[38];
    arrayOfCLICK_TARGET[0] = CLICK_TARGET.valueOf(CLICK_TARGET.BOUNDING_BOX_CLICK_ON_RESULTS_PAGE_VALUE);
    arrayOfCLICK_TARGET[1] = CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_CLICK_VIA_SCROLL_BALL_ON_RESULTS_PAGE_VALUE);
    arrayOfCLICK_TARGET[2] = CLICK_TARGET.valueOf(CLICK_TARGET.BACK_BUTTON_FROM_RESULTS_LIST_PAGE_VALUE);
    arrayOfCLICK_TARGET[3] = CLICK_TARGET.valueOf(CLICK_TARGET.BACK_BUTTON_FROM_RESULT_PAGE_VALUE);
    arrayOfCLICK_TARGET[4] = CLICK_TARGET.valueOf(CLICK_TARGET.NEW_SEARCH_BUTTON_FROM_RESULT_PAGE_VALUE);
    arrayOfCLICK_TARGET[5] = CLICK_TARGET.valueOf(CLICK_TARGET.EYECANDY_RESULT_CLICK_VALUE);
    arrayOfCLICK_TARGET[6] = CLICK_TARGET.valueOf(CLICK_TARGET.RATINGS_OPENED_ON_RESULTS_LIST_PAGE_VALUE);
    arrayOfCLICK_TARGET[7] = CLICK_TARGET.valueOf(CLICK_TARGET.RATINGS_CLOSED_ON_RESULTS_LIST_PAGE_VALUE);
    arrayOfCLICK_TARGET[8] = CLICK_TARGET.valueOf(CLICK_TARGET.TAP_OUTSIDE_BOUNDING_BOX_HIDE_RESULTS_LIST_VALUE);
    arrayOfCLICK_TARGET[9] = CLICK_TARGET.valueOf(CLICK_TARGET.TAP_OUTSIDE_BOUNDING_BOX_SHOW_RESULTS_LIST_VALUE);
    arrayOfCLICK_TARGET[10] = CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_HTML_CACHED_VALUE);
    arrayOfCLICK_TARGET[11] = CLICK_TARGET.valueOf(CLICK_TARGET.DRAWER_OPENED_VALUE);
    arrayOfCLICK_TARGET[12] = CLICK_TARGET.valueOf(CLICK_TARGET.DRAWER_SEMI_VALUE);
    arrayOfCLICK_TARGET[13] = CLICK_TARGET.valueOf(CLICK_TARGET.DRAWER_CLOSED_VALUE);
    arrayOfCLICK_TARGET[14] = CLICK_TARGET.valueOf(CLICK_TARGET.SHOW_FULL_SIMILAR_IMAGE_VALUE);
    arrayOfCLICK_TARGET[15] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_CLICK_LIST_VIEW_VALUE);
    arrayOfCLICK_TARGET[16] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_CLICK_GRID_VIEW_VALUE);
    arrayOfCLICK_TARGET[17] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_REFINE_QUERY_SPEECH_VALUE);
    arrayOfCLICK_TARGET[18] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_REFINE_QUERY_TYPE_VALUE);
    arrayOfCLICK_TARGET[19] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_RESULT_CLICK_IN_RESULT_LIST_VALUE);
    arrayOfCLICK_TARGET[20] = CLICK_TARGET.valueOf(CLICK_TARGET.PUGGLE_EXTERNAL_CLICK_IN_RESULT_VIEW_VALUE);
    arrayOfCLICK_TARGET[21] = CLICK_TARGET.valueOf(CLICK_TARGET.BACK_BUTTON_FROM_PUGGLE_RESULT_VALUE);
    arrayOfCLICK_TARGET[22] = CLICK_TARGET.valueOf(CLICK_TARGET.SUGGEST_A_RESULT_BUTTON_CLICK_VALUE);
    arrayOfCLICK_TARGET[23] = CLICK_TARGET.valueOf(CLICK_TARGET.TAG_IMAGE_CONTINUE_VALUE);
    arrayOfCLICK_TARGET[24] = CLICK_TARGET.valueOf(CLICK_TARGET.TAG_IMAGE_CANCEL_VALUE);
    arrayOfCLICK_TARGET[25] = CLICK_TARGET.valueOf(CLICK_TARGET.TAG_IMAGE_BACK_VALUE);
    arrayOfCLICK_TARGET[26] = CLICK_TARGET.valueOf(CLICK_TARGET.DESCRIBE_SUBMIT_VALUE);
    arrayOfCLICK_TARGET[27] = CLICK_TARGET.valueOf(CLICK_TARGET.DESCRIBE_CANCEL_VALUE);
    arrayOfCLICK_TARGET[28] = CLICK_TARGET.valueOf(CLICK_TARGET.DESCRIBE_BACK_VALUE);
    arrayOfCLICK_TARGET[29] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_TIMELINE_THUMBNAIL_TAP_VALUE);
    arrayOfCLICK_TARGET[30] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_TIMELINE_SWIPE_VALUE);
    arrayOfCLICK_TARGET[31] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_VIEW_ALL_RESULTS_VALUE);
    arrayOfCLICK_TARGET[32] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_ALL_RESULTS_RESULT_ITEM_TAP_VALUE);
    arrayOfCLICK_TARGET[33] = CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_SHOW_EXPANDED_RESULT_VALUE);
    arrayOfCLICK_TARGET[34] = CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_DISMBIGUATION_ACTION_VALUE);
    arrayOfCLICK_TARGET[35] = CLICK_TARGET.valueOf(CLICK_TARGET.RESULT_EXPANDED_RESULT_ACTION_VALUE);
    arrayOfCLICK_TARGET[36] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_CLICK_TO_PAUSE_VALUE);
    arrayOfCLICK_TARGET[37] = CLICK_TARGET.valueOf(CLICK_TARGET.CONTINUOUS_AUTOMATED_PAUSE_VALUE);
  }

  public ClickTracker(SharedPreferences paramSharedPreferences, Resources paramResources, ClickLogConnector paramClickLogConnector)
  {
    this.connector = paramClickLogConnector;
    this.logRequestsWithoutTrackingIds = new ArrayList();
    this.clickTrackingPreferenceChangeListener = new ClickTrackingPreferenceChangeListener(this, paramResources);
    paramSharedPreferences.registerOnSharedPreferenceChangeListener(this.clickTrackingPreferenceChangeListener);
  }

  private void addToRequests(NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
  {
    if ((paramBuilder.hasClientClick()) && (TRACKING_ID_REQUIRED_CLICKS.contains(paramBuilder.getClientClick().getClickTarget())))
    {
      maybeSendRequestWithTrackingId(paramBuilder);
      return;
    }
    sendRequest(paramBuilder);
  }

  private static ClickTracker get(Context paramContext)
  {
    return ((UnveilContext)paramContext.getApplicationContext()).getClickTracker();
  }

  public static void logActionClick(View paramView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, ResultItem paramResultItem, int paramInt)
  {
    get(paramView.getContext()).logActionClick(paramCLICK_TARGET, paramResultItem, paramInt);
  }

  private void logActionClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, ResultItem paramResultItem, int paramInt)
  {
    addToRequests(newRequestBuilder().setClientClick(NativeClientLoggingProtos.NativeClientClick.newBuilder().setClickTarget(paramCLICK_TARGET).setResultPosition(paramResultItem.getResultPosition()).setResultId(paramResultItem.getAnnotationResult().getResultId()).setActionPosition(paramInt)));
  }

  public static void logClick(Context paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET)
  {
    get(paramContext).logClick(paramCLICK_TARGET);
  }

  public static void logClick(View paramView, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET)
  {
    logClick(paramView.getContext(), paramCLICK_TARGET);
  }

  public static void logResultClick(Context paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, ResultItem paramResultItem, int paramInt)
  {
    get(paramContext).logResultClick(paramCLICK_TARGET, paramResultItem, paramInt);
  }

  private void logResultClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, ResultItem paramResultItem, int paramInt)
  {
    NativeClientLoggingProtos.NativeClientClick.Builder localBuilder = NativeClientLoggingProtos.NativeClientClick.newBuilder().setClickTarget(paramCLICK_TARGET).setResultPosition(paramResultItem.getResultPosition()).setResultId(paramResultItem.getAnnotationResult().getResultId()).setDisplayPosition(paramInt);
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder1 = newRequestBuilder();
    localBuilder1.setClientClick(localBuilder);
    addToRequests(localBuilder1);
  }

  private void maybeSendRequestWithTrackingId(NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
  {
    if (TextUtils.isEmpty(this.trackingId))
    {
      this.logRequestsWithoutTrackingIds.add(paramBuilder);
      return;
    }
    paramBuilder.setTrackingId(this.trackingId);
    sendRequest(paramBuilder);
  }

  public static ClickLogConnector newDefaultLogConnector(AbstractConnector paramAbstractConnector)
  {
    return new DefaultClickLogConnector(paramAbstractConnector, Executors.newFixedThreadPool(2));
  }

  private static NativeClientLoggingProtos.NativeClientLogEventRequest.Builder newRequestBuilder()
  {
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = NativeClientLoggingProtos.NativeClientLogEventRequest.newBuilder();
    localBuilder.setMsSinceEpoch(System.currentTimeMillis());
    return localBuilder;
  }

  private void sendRequest(NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
  {
    if (!TextUtils.isEmpty(this.sessionId))
      paramBuilder.setSessionId(this.sessionId);
    this.connector.sendRequest(paramBuilder.build(), this.responseHandler);
  }

  public void clearTrackingId()
  {
    this.trackingId = null;
  }

  public String getTrackingId()
  {
    return this.trackingId;
  }

  public void logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET)
  {
    logger.i("Logging simple click: %s", new Object[] { paramCLICK_TARGET });
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = newRequestBuilder();
    NativeClientLoggingProtos.NativeClientClick.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientClick.newBuilder();
    localBuilder1.setClickTarget(paramCLICK_TARGET);
    localBuilder.setClientClick(localBuilder1);
    addToRequests(localBuilder);
  }

  public void logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, int paramInt)
  {
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = newRequestBuilder();
    NativeClientLoggingProtos.NativeClientClick.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientClick.newBuilder();
    localBuilder1.setClickTarget(paramCLICK_TARGET);
    localBuilder1.setResultPosition(paramInt);
    localBuilder.setClientClick(localBuilder1);
    addToRequests(localBuilder);
  }

  public void logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET, int paramInt1, int paramInt2)
  {
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = newRequestBuilder();
    NativeClientLoggingProtos.NativeClientClick.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientClick.newBuilder();
    localBuilder1.setClickTarget(paramCLICK_TARGET);
    localBuilder1.setResultPosition(paramInt1);
    localBuilder1.setResultsShownAboveFold(paramInt2);
    localBuilder.setClientClick(localBuilder1);
    addToRequests(localBuilder);
  }

  public void logInstall(String paramString1, String paramString2)
  {
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = NativeClientLoggingProtos.NativeClientLogEventRequest.newBuilder();
    localBuilder.setMsSinceEpoch(System.currentTimeMillis());
    NativeClientLoggingProtos.NativeClientInstall.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientInstall.newBuilder();
    if (!paramString1.equals("0"))
      localBuilder1.setUpgradeFrom(paramString1);
    localBuilder1.setInstalledVersion(paramString2);
    localBuilder.setClientInstall(localBuilder1);
    addToRequests(localBuilder);
  }

  public void logNotificationClick(int paramInt)
  {
    NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = newRequestBuilder();
    NativeClientLoggingProtos.NativeClientClick.Builder localBuilder1 = NativeClientLoggingProtos.NativeClientClick.newBuilder();
    localBuilder1.setClickTarget(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.SEARCH_FROM_CAMERA_GOGGLES_NOTIFICATION);
    localBuilder1.setNotificationResultsCount(paramInt);
    localBuilder.setClientClick(localBuilder1);
    addToRequests(localBuilder);
  }

  public void setSessionId(String paramString)
  {
    this.sessionId = paramString;
  }

  public void setTrackingId(String paramString)
  {
    if (paramString != null)
    {
      try
      {
        this.trackingId = paramString;
        Iterator localIterator = this.logRequestsWithoutTrackingIds.iterator();
        while (localIterator.hasNext())
        {
          NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder = (NativeClientLoggingProtos.NativeClientLogEventRequest.Builder)localIterator.next();
          localBuilder.setTrackingId(paramString);
          sendRequest(localBuilder);
        }
      }
      finally
      {
      }
      this.logRequestsWithoutTrackingIds.clear();
    }
  }

  public static abstract interface ClickLogConnector
  {
    public abstract void sendRequest(NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest, AbstractConnector.ResponseHandler<NativeClientLoggingProtos.NativeClientLogEventResponse> paramResponseHandler);
  }

  static class DefaultClickLogConnector
    implements ClickTracker.ClickLogConnector
  {
    private static final Class<NativeClientLoggingProtos.NativeClientLogEventResponse> RESPONSE_CLASS = NativeClientLoggingProtos.NativeClientLogEventResponse.class;
    private final AbstractConnector connector;
    private final Executor executor;

    public DefaultClickLogConnector(AbstractConnector paramAbstractConnector, Executor paramExecutor)
    {
      this.connector = paramAbstractConnector;
      this.executor = paramExecutor;
    }

    public void sendRequest(NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest, AbstractConnector.ResponseHandler<NativeClientLoggingProtos.NativeClientLogEventResponse> paramResponseHandler)
    {
      this.executor.execute(this.connector.requestRunnable(paramNativeClientLogEventRequest, RESPONSE_CLASS, "", paramResponseHandler));
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.ClickTracker
 * JD-Core Version:    0.6.2
 */