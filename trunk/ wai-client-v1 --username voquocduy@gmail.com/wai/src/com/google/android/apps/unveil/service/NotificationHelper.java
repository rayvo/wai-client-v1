package com.google.android.apps.unveil.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.service.notifications.NotificationFactory;
import java.util.List;

public class NotificationHelper
{
  private static final int AUTH_ERROR_ID = 4;
  private static final int RESULTS_NOTIFICATION_ID = 1;
  private static final int WELCOME_NOTIFICATION_ID = 3;
  public static final UnveilLogger logger = new UnveilLogger();
  private final NotificationFactory notificationFactory = new NotificationFactory();
  private final NotificationManager notificationManager;
  private final PersistenceLayer persistence;

  public NotificationHelper(SharedPreferences paramSharedPreferences, NotificationManager paramNotificationManager)
  {
    this.notificationManager = paramNotificationManager;
    this.persistence = new PersistenceLayer(paramSharedPreferences);
  }

  private void launch(int paramInt, Notification paramNotification)
  {
    if (paramInt != 3)
      this.notificationManager.cancel(3);
    logger.i("Launching notification: %s", new Object[] { paramNotification });
    this.notificationManager.notify(paramInt, paramNotification);
  }

  private void showAuthNotification(Context paramContext, Intent paramIntent)
  {
    launch(4, this.notificationFactory.newAuthErrorNotification(paramContext, paramIntent));
  }

  private void showMultiQueryNotification(Context paramContext, Bitmap paramBitmap, List<String> paramList)
  {
    launch(1, this.notificationFactory.newMultiQueryNotification(paramContext, paramBitmap, paramList));
  }

  private void showSingleQueryNotification(Context paramContext, QueryResponse paramQueryResponse, Bitmap paramBitmap, String paramString)
  {
    launch(1, this.notificationFactory.newSingleQueryNotification(paramContext, paramQueryResponse, paramBitmap, paramString));
  }

  public void handleAuthError(Context paramContext, Intent paramIntent)
  {
    showAuthNotification(paramContext, paramIntent);
  }

  public void handleQueryResponse(Context paramContext, String paramString, QueryResponse paramQueryResponse, Bitmap paramBitmap)
  {
    this.persistence.onNewUnseenResult(paramString);
    List localList = this.persistence.getUnseenResultIds();
    if (localList.size() > 1)
    {
      showMultiQueryNotification(paramContext, paramBitmap, localList);
      return;
    }
    showSingleQueryNotification(paramContext, paramQueryResponse, paramBitmap, paramString);
  }

  public void handleSeenResults(List<String> paramList)
  {
    if (paramList != null)
      this.persistence.onSeenResults(paramList);
  }

  public void handleServiceDisabled()
  {
    this.notificationManager.cancelAll();
  }

  public void handleServiceEnabled(Context paramContext)
  {
    logger.i("Notifying of service enabled.", new Object[0]);
    launch(3, this.notificationFactory.newWelcomeNotification(paramContext));
  }

  private static final class PersistenceLayer
  {
    static final String UNSEEN_RESULTS_KEY = "unseen_results";
    private final ListPersister<String> idPersister;

    public PersistenceLayer(SharedPreferences paramSharedPreferences)
    {
      this.idPersister = new ListPersister(paramSharedPreferences, "unseen_results", ListPersister.STRING_SERIALIZER);
    }

    public List<String> getUnseenResultIds()
    {
      return this.idPersister.load();
    }

    public void onNewUnseenResult(String paramString)
    {
      this.idPersister.persistNew(paramString);
    }

    public void onSeenResults(List<String> paramList)
    {
      List localList = this.idPersister.load();
      localList.removeAll(localList);
      this.idPersister.persist(localList);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.NotificationHelper
 * JD-Core Version:    0.6.2
 */