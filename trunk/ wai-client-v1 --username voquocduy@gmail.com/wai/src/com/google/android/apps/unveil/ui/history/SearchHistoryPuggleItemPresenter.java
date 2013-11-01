package com.google.android.apps.unveil.ui.history;

import android.graphics.Rect;
import android.view.View;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryPuggleItem;
import com.google.goggles.BoundingBoxProtos.BoundingBox;

class SearchHistoryPuggleItemPresenter extends ItemModels.Presenter<SearchHistoryPuggleItem>
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final SearchHistoryItemPresenter superPresenter;

  public SearchHistoryPuggleItemPresenter(SearchHistoryItemPresenter paramSearchHistoryItemPresenter)
  {
    this.superPresenter = paramSearchHistoryItemPresenter;
  }

  void presentAsListItem(SearchHistoryPuggleItem paramSearchHistoryPuggleItem, View paramView)
  {
    this.superPresenter.presentAsListItem(paramSearchHistoryPuggleItem, paramView);
  }

  void updateThumbnail(SearchHistoryPuggleItem paramSearchHistoryPuggleItem, final ImageView paramImageView, ThumbnailProvider paramThumbnailProvider)
  {
    paramThumbnailProvider.fetch(paramSearchHistoryPuggleItem.getThumbnailUrl(), new ThumbnailProvider.ThumbnailListener()
    {
      public void onThumbnailLoading()
      {
        paramImageView.setImageResource(R.drawable.no_thumbnail);
        paramImageView.setTag(null);
      }

      public void onThumbnailReady(Picture paramAnonymousPicture)
      {
        if (paramAnonymousPicture.getCropArea() == null)
          paramAnonymousPicture.setCropArea(new Rect(this.val$box.getX(), this.val$box.getY(), this.val$box.getX() + this.val$box.getWidth(), this.val$box.getY() + this.val$box.getHeight()));
        if ((paramAnonymousPicture.getSize().width > this.val$box.getX() + this.val$box.getWidth()) && (paramAnonymousPicture.getSize().height > this.val$box.getY() + this.val$box.getHeight()))
          paramAnonymousPicture = paramAnonymousPicture.getCroppedPicture();
        while (true)
        {
          paramAnonymousPicture.populateWithBitmap(paramImageView);
          return;
          SearchHistoryPuggleItemPresenter.logger.e("Ignoring bad thumbnail crop.", new Object[0]);
        }
      }
    }
    , ThumbnailProvider.SizeSpec.FIFE_DEFAULT);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.SearchHistoryPuggleItemPresenter
 * JD-Core Version:    0.6.2
 */