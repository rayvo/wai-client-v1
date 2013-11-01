package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.content.Intent;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.ResultsActivity;
import com.google.android.apps.unveil.RunQueryActivity;
import com.google.android.apps.unveil.RunQueryActivity.Recipe;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.history.ItemModel;
import com.google.android.apps.unveil.history.NoResultsItem;
import com.google.android.apps.unveil.history.SavedQuery;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryPuggleItem;
import com.google.android.apps.unveil.history.SearchHistoryUpsell;
import com.google.android.apps.unveil.history.SfCItem;
import com.google.goggles.BoundingBoxProtos.BoundingBox;

public class ItemModels
{
  public static String getRelativeTimeString(Context paramContext, long paramLong)
  {
    long l1 = System.currentTimeMillis();
    long l2 = paramLong - l1;
    if ((l2 > -60000L) && (l2 < 60000L))
      return paramContext.getString(R.string.just_now);
    if (paramLong < l1)
      return (String)DateUtils.getRelativeTimeSpanString(paramLong, l1, 1000L);
    return DateUtils.formatDateTime(paramContext, paramLong, 524288);
  }

  private static Presenter newPresenter(ItemModel paramItemModel)
  {
    if ((paramItemModel instanceof SavedQuery))
      return new SavedQueryPresenter();
    if ((paramItemModel instanceof SearchHistoryUpsell))
      return new UpsellPresenter();
    if ((paramItemModel instanceof NoResultsItem))
      return new NoResultsPresenter();
    if ((paramItemModel instanceof SearchHistoryItem))
      return new SearchHistoryItemPresenter();
    if ((paramItemModel instanceof SearchHistoryPuggleItem))
      return new SearchHistoryPuggleItemPresenter(new SearchHistoryItemPresenter());
    if ((paramItemModel instanceof SfCItem))
      return new SfCItemPresenter();
    throw new UnsupportedOperationException();
  }

  public static void presentAsListItem(ItemModel paramItemModel, View paramView)
  {
    newPresenter(paramItemModel).presentAsListItem(paramItemModel, paramView);
  }

  public static void replay(SearchHistoryItem paramSearchHistoryItem, Context paramContext)
  {
    if ((paramSearchHistoryItem instanceof SearchHistoryPuggleItem))
    {
      replayPuggle((SearchHistoryPuggleItem)paramSearchHistoryItem, paramContext);
      return;
    }
    Intent localIntent = new Intent(paramContext, ResultsActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.replay");
    localIntent.putExtra("replay_id", paramSearchHistoryItem.getMomentId());
    paramContext.startActivity(localIntent);
  }

  private static void replayPuggle(SearchHistoryPuggleItem paramSearchHistoryPuggleItem, Context paramContext)
  {
    Intent localIntent = new Intent(paramContext, ResultsActivity.class);
    localIntent.setAction("com.google.android.apps.unveil.replay");
    localIntent.putExtra("replay_id", paramSearchHistoryPuggleItem.getMomentId());
    localIntent.putExtra("text_restrict;", paramSearchHistoryPuggleItem.getTitle());
    if (paramSearchHistoryPuggleItem.hasBox())
      localIntent.putExtra("box", paramSearchHistoryPuggleItem.getBox().toByteArray());
    paramContext.startActivity(localIntent);
  }

  public static Intent toIntent(SavedQuery paramSavedQuery, Context paramContext, UnveilContext paramUnveilContext)
  {
    Intent localIntent = new Intent(paramContext, RunQueryActivity.class);
    localIntent.putExtra("saved_query_id", paramSavedQuery.getId());
    localIntent.putExtra("recipe", RunQueryActivity.Recipe.SAVE_FOR_LATER);
    return localIntent;
  }

  public static void updateThumbnail(ItemModel paramItemModel, ImageView paramImageView, ThumbnailProvider paramThumbnailProvider)
  {
    newPresenter(paramItemModel).updateThumbnail(paramItemModel, paramImageView, paramThumbnailProvider);
  }

  public static abstract class Presenter<M extends ItemModel>
  {
    abstract void presentAsListItem(M paramM, View paramView);

    void updateThumbnail(M paramM, ImageView paramImageView, ThumbnailProvider paramThumbnailProvider)
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.ItemModels
 * JD-Core Version:    0.6.2
 */