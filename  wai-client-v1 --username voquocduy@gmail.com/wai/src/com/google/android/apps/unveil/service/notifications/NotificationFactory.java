package com.google.android.apps.unveil.service.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import com.google.android.apps.unveil.R.plurals;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.ResultsActivity;
import com.google.android.apps.unveil.SearchHistoryActivity;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.ArrayList;
import java.util.List;

public final class NotificationFactory
{
  private static final UnveilLogger logger = new UnveilLogger();

  private static PendingIntent createCameraIntent(Context paramContext)
  {
    return wrapInPendingIntent(paramContext, new Intent("android.media.action.STILL_IMAGE_CAMERA"));
  }

  private static PendingIntent createMultiQueryIntent(Context paramContext, List<String> paramList)
  {
    Intent localIntent = new Intent(paramContext, SearchHistoryActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.load_sfc_results");
    localIntent.putExtra("log_notification_count", paramList.size());
    localIntent.putExtra("highlight_moments", new ArrayList(paramList));
    return wrapInPendingIntent(paramContext, localIntent);
  }

  public static PendingIntent createResultsActivityIntent(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ResultsActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.load_sfc_results");
    localIntent.putExtra("sfc_id", paramString);
    localIntent.putExtra("log_notification_count", 1);
    localIntent.putExtra("seen_result_ids", wrapInList(paramString));
    return wrapInPendingIntent(paramContext, localIntent);
  }

  public static <E> ArrayList<E> wrapInList(E paramE)
  {
    ArrayList localArrayList = new ArrayList(1);
    localArrayList.add(paramE);
    return localArrayList;
  }

  private static PendingIntent wrapInPendingIntent(Context paramContext, Intent paramIntent)
  {
    paramIntent.addFlags(335544320);
    PendingIntent.getActivity(paramContext, 0, paramIntent, 1073741824).cancel();
    return PendingIntent.getActivity(paramContext, 0, paramIntent, 1073741824);
  }

  public Notification newAuthErrorNotification(Context paramContext, Intent paramIntent)
  {
    return new NotificationBuilder(true).addPendingIntent(wrapInPendingIntent(paramContext, paramIntent)).addTickerText(paramContext.getString(R.string.search_from_camera_error)).addHeaderText(paramContext.getString(R.string.search_from_camera_error)).addExplanationText(paramContext.getString(R.string.authentication_failed)).addTime(System.currentTimeMillis()).build(paramContext);
  }

  public Notification newMultiQueryNotification(Context paramContext, Bitmap paramBitmap, List<String> paramList)
  {
    NotificationBuilder localNotificationBuilder = new NotificationBuilder(false).addPendingIntent(createMultiQueryIntent(paramContext, paramList)).addTickerText(paramContext.getString(R.string.background_goggle_new_goggles_results)).addHeaderText(paramContext.getString(R.string.background_goggle_new_goggles_results));
    Resources localResources = paramContext.getResources();
    int i = R.plurals.background_goggle_images_with_matches;
    int j = paramList.size();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramList.size());
    return localNotificationBuilder.addExplanationText(localResources.getQuantityString(i, j, arrayOfObject)).addImageBitmap(paramBitmap).addTime(System.currentTimeMillis()).build(paramContext);
  }

  public Notification newSingleQueryNotification(Context paramContext, QueryResponse paramQueryResponse, Bitmap paramBitmap, String paramString)
  {
    PendingIntent localPendingIntent = createResultsActivityIntent(paramContext, paramString);
    String str1 = paramContext.getString(R.string.background_goggle_new_goggles_results);
    String str2 = ((ResultItem)paramQueryResponse.getResults().get(0)).getTitle();
    String str3 = Grammar.makeResultsExplanationText(paramContext, paramQueryResponse.getResults());
    return new NotificationBuilder(false).addPendingIntent(localPendingIntent).addTickerText(str1).addHeaderText(str2).addExplanationText(str3).addImageBitmap(paramBitmap).addTime(System.currentTimeMillis()).build(paramContext);
  }

  public Notification newWelcomeNotification(Context paramContext)
  {
    return new NotificationBuilder(false).addPendingIntent(createCameraIntent(paramContext)).addTickerText(paramContext.getString(R.string.background_goggle_welcome_ticker)).addHeaderText(paramContext.getString(R.string.background_goggle_welcome_header)).addExplanationText(paramContext.getString(R.string.background_goggle_welcome_explanation)).addTime(System.currentTimeMillis()).build(paramContext);
  }

  static class Grammar
  {
    public static String makeResultsExplanationText(Context paramContext, List<ResultItem> paramList)
    {
      String str = ((ResultItem)paramList.get(0)).getType();
      int i = paramList.size();
      if (i == 1)
        return str;
      int j = R.plurals.background_goggle_s_and_n_more_results;
      if (i == 2)
        return paramContext.getResources().getQuantityString(j, i - 1, new Object[] { str });
      Resources localResources = paramContext.getResources();
      int k = i - 1;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = str;
      arrayOfObject[1] = Integer.valueOf(i - 1);
      return localResources.getQuantityString(j, k, arrayOfObject);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.notifications.NotificationFactory
 * JD-Core Version:    0.6.2
 */