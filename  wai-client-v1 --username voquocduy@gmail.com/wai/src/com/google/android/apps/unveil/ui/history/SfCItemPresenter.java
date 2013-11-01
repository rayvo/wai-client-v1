package com.google.android.apps.unveil.ui.history;

import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SfCItem;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.service.notifications.NotificationFactory;
import java.util.List;

public class SfCItemPresenter extends ItemModels.Presenter<SfCItem>
{
  private static final View.OnClickListener ITEM_CLICK_LISTENER = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      SfCItemPresenter.viewItem(paramAnonymousView);
    }
  };
  private static final UnveilLogger logger = new UnveilLogger();

  private static void viewItem(View paramView)
  {
    SfCItem localSfCItem = (SfCItem)paramView.getTag();
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = localSfCItem.getId();
    localUnveilLogger.d("Viewing search from camera result with id %s", arrayOfObject);
    try
    {
      NotificationFactory.createResultsActivityIntent(paramView.getContext(), localSfCItem.getId()).send();
      return;
    }
    catch (PendingIntent.CanceledException localCanceledException)
    {
      logger.e("Canceled result showing for search by camera query", new Object[0]);
    }
  }

  void presentAsListItem(SfCItem paramSfCItem, View paramView)
  {
    paramView.setBackgroundResource(R.color.saved_query_background);
    TextView localTextView = (TextView)paramView.findViewById(R.id.title);
    paramSfCItem.getQueryResponse();
    String str = paramSfCItem.getTitle();
    if (TextUtils.isEmpty(str))
      localTextView.setVisibility(8);
    while (true)
    {
      ((TextView)paramView.findViewById(R.id.relative_time)).setText(ItemModels.getRelativeTimeString(paramView.getContext(), paramSfCItem.getTimestamp()));
      ((TextView)paramView.findViewById(R.id.location)).setVisibility(8);
      ((TextView)paramView.findViewById(R.id.notes)).setVisibility(8);
      paramView.setTag(paramSfCItem);
      paramView.setOnClickListener(ITEM_CLICK_LISTENER);
      return;
      localTextView.setVisibility(0);
      localTextView.setText(str);
    }
  }

  void updateThumbnail(SfCItem paramSfCItem, ImageView paramImageView, ThumbnailProvider paramThumbnailProvider)
  {
    if (paramSfCItem.getQueryResponse() == null);
    String str;
    do
    {
      return;
      str = ((ResultItem)paramSfCItem.getQueryResponse().getResults().get(0)).getThumbnailUrl();
    }
    while (paramImageView == null);
    paramImageView.setImageResource(R.drawable.no_thumbnail);
    paramImageView.setTag(null);
    paramThumbnailProvider.fetch(str, ThumbnailProvider.makeImageViewThumbnailListener(paramImageView, str), ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.SfCItemPresenter
 * JD-Core Version:    0.6.2
 */