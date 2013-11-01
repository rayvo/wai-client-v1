package com.google.android.apps.unveil.service.notifications;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.text.format.DateUtils;
import android.widget.RemoteViews;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.service.BackgroundGogglesPreferencesActivity;
import java.util.Date;

final class NotificationBuilder
{
  private Bitmap bitmap;
  private CharSequence explanationText;
  private CharSequence headerText;
  private final boolean isError;
  private PendingIntent pendingIntent;
  private CharSequence tickerText;
  private long when;

  public NotificationBuilder(boolean paramBoolean)
  {
    this.isError = paramBoolean;
  }

  private static RemoteViews createContentView(Context paramContext, CharSequence paramCharSequence1, CharSequence paramCharSequence2, Bitmap paramBitmap, long paramLong)
  {
    RemoteViews localRemoteViews = new RemoteViews(paramContext.getPackageName(), R.layout.notification_custom_layout);
    if (!TextUtils.isEmpty(paramCharSequence1))
      localRemoteViews.setTextViewText(R.id.header, paramCharSequence1);
    if (!TextUtils.isEmpty(paramCharSequence2))
      localRemoteViews.setTextViewText(R.id.explanation, paramCharSequence2);
    if (paramBitmap != null)
      localRemoteViews.setImageViewBitmap(R.id.large_icon, paramBitmap);
    while (true)
    {
      localRemoteViews.setTextViewText(R.id.time, toDateString(paramContext, paramLong));
      return localRemoteViews;
      localRemoteViews.setViewVisibility(R.id.large_icon, 8);
    }
  }

  private static int getStatusIconId(boolean paramBoolean)
  {
    if (paramBoolean)
      return 17301624;
    return R.drawable.icon;
  }

  private static CharSequence toDateString(Context paramContext, long paramLong)
  {
    Date localDate = new Date(paramLong);
    if (DateUtils.isToday(paramLong))
      return android.text.format.DateFormat.getTimeFormat(paramContext).format(localDate);
    return android.text.format.DateFormat.getDateFormat(paramContext).format(localDate);
  }

  public NotificationBuilder addExplanationText(CharSequence paramCharSequence)
  {
    this.explanationText = paramCharSequence;
    return this;
  }

  public NotificationBuilder addHeaderText(CharSequence paramCharSequence)
  {
    this.headerText = paramCharSequence;
    return this;
  }

  public NotificationBuilder addImageBitmap(Bitmap paramBitmap)
  {
    this.bitmap = paramBitmap;
    return this;
  }

  public NotificationBuilder addPendingIntent(PendingIntent paramPendingIntent)
  {
    this.pendingIntent = paramPendingIntent;
    return this;
  }

  public NotificationBuilder addTickerText(CharSequence paramCharSequence)
  {
    this.tickerText = paramCharSequence;
    return this;
  }

  public NotificationBuilder addTime(long paramLong)
  {
    this.when = paramLong;
    return this;
  }

  public Notification build(Context paramContext)
  {
    Notification localNotification = new Notification(getStatusIconId(this.isError), this.tickerText, this.when);
    localNotification.flags = (0x18 | localNotification.flags);
    if (BackgroundGogglesPreferencesActivity.silenceNotifications(paramContext))
    {
      localNotification.vibrate = null;
      localNotification.sound = null;
    }
    while (this.bitmap != null)
    {
      localNotification.contentView = createContentView(paramContext, this.headerText, this.explanationText, this.bitmap, this.when);
      localNotification.contentIntent = this.pendingIntent;
      return localNotification;
      localNotification.defaults = -1;
    }
    localNotification.setLatestEventInfo(paramContext, this.headerText, this.explanationText, this.pendingIntent);
    return localNotification;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.notifications.NotificationBuilder
 * JD-Core Version:    0.6.2
 */