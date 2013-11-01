package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.SearchHistoryActivity;
import com.google.android.apps.unveil.Settings;
import com.google.android.apps.unveil.TipsActivity;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SavedQuery;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.history.SavedQueryProvider.SavedQueryCursor;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PromotionView
{
  private static final int[] TIP_PROMOTION_LAYOUTS = arrayOfInt;
  private static final UnveilLogger logger = new UnveilLogger();

  static
  {
    int[] arrayOfInt = new int[5];
    arrayOfInt[0] = R.layout.promotion_good_items;
    arrayOfInt[1] = R.layout.promotion_good_items_2;
    arrayOfInt[2] = R.layout.promotion_good_items_3;
    arrayOfInt[3] = R.layout.promotion_bad_items;
    arrayOfInt[4] = R.layout.promotion_translation;
  }

  private PromotionView()
  {
    throw new UnsupportedOperationException("Cannot instantiate PromotionView");
  }

  public static View get(Context paramContext)
  {
    SavedQueryProvider localSavedQueryProvider = ((UnveilApplication)paramContext.getApplicationContext()).getSavedQueryProvider();
    if (localSavedQueryProvider.getQueryCount() > 0)
      return new PendingSearchPromotionView(paramContext, localSavedQueryProvider, null);
    if ((!Settings.getBoolean(paramContext, R.string.background_goggle_key)) && (Math.random() < 1.0D / (1 + TIP_PROMOTION_LAYOUTS.length)))
      return new BackgroundGogglesPromotionView(paramContext, null);
    return new TipPromotionView(paramContext, TIP_PROMOTION_LAYOUTS[((int)(Math.random() * TIP_PROMOTION_LAYOUTS.length))], null);
  }

  public static boolean hasPendingSearchPromotionChild(ViewGroup paramViewGroup)
  {
    for (int i = 0; i < paramViewGroup.getChildCount(); i++)
      if ((paramViewGroup.getChildAt(i) instanceof PendingSearchPromotionView))
        return true;
    return false;
  }

  static final class BackgroundGogglesPromotionView extends FrameLayout
  {
    private BackgroundGogglesPromotionView(final Context paramContext)
    {
      super();
      addView(LayoutInflater.from(paramContext).inflate(R.layout.promotion_background, null));
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ClickTracker.logClick(paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PROMOTION_PENDING_SEARCHES);
          Intent localIntent = new Intent(paramContext, TipsActivity.class);
          paramContext.startActivity(localIntent);
        }
      });
      setFocusable(true);
    }
  }

  static final class PendingSearchPromotionView extends FrameLayout
  {
    private static final int BORDER_THICKNESS_DP = 1;
    private static final int MAX_THUMBNAILS = 4;
    private static final int STACK_OFFSET_DP = 3;
    private static final int THUMBNAIL_SIZE_DP = 72;
    private final int thumbnailBorderThickness;
    private final int thumbnailSize;
    private final int thumbnailStackOffset;

    private PendingSearchPromotionView(final Context paramContext, SavedQueryProvider paramSavedQueryProvider)
    {
      super();
      addView(LayoutInflater.from(paramContext).inflate(R.layout.promotion_pending_searches, null));
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ClickTracker.logClick(paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PROMOTION_PENDING_SEARCHES);
          PromotionView.logger.i("Promotion clicked", new Object[0]);
          Intent localIntent = new Intent(paramContext, SearchHistoryActivity.class);
          paramContext.startActivity(localIntent);
        }
      });
      setFocusable(true);
      this.thumbnailSize = PixelUtils.dipToPix(72.0F, paramContext);
      this.thumbnailStackOffset = PixelUtils.dipToPix(3.0F, paramContext);
      this.thumbnailBorderThickness = PixelUtils.dipToPix(1.0F, paramContext);
      populateThumbnails((ViewGroup)findViewById(R.id.thumbnail_container), getQueries(paramSavedQueryProvider));
    }

    private List<SavedQuery> getQueries(SavedQueryProvider paramSavedQueryProvider)
    {
      SavedQueryProvider.SavedQueryCursor localSavedQueryCursor = paramSavedQueryProvider.getQueries();
      LinkedList localLinkedList = new LinkedList();
      for (int i = 0; (i < 4) && (localSavedQueryCursor.hasNext()); i++)
        localLinkedList.add(localSavedQueryCursor.next());
      localSavedQueryCursor.close();
      return localLinkedList;
    }

    private View makeThumbnailView(SavedQuery paramSavedQuery, int paramInt1, int paramInt2)
    {
      FrameLayout localFrameLayout = new FrameLayout(getContext());
      RelativeLayout.LayoutParams localLayoutParams = new RelativeLayout.LayoutParams(this.thumbnailSize, this.thumbnailSize);
      int i = paramInt1 * this.thumbnailStackOffset;
      int j = this.thumbnailStackOffset * (paramInt2 - 1 - paramInt1);
      localLayoutParams.setMargins(i, i, j, j);
      localFrameLayout.setLayoutParams(localLayoutParams);
      int k = this.thumbnailBorderThickness;
      localFrameLayout.setPadding(k, k, k, k);
      localFrameLayout.setBackgroundColor(-12303292);
      ImageView localImageView = new ImageView(getContext());
      paramSavedQuery.getCachedQueryImage().populateWithBitmap(localImageView);
      localImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
      localFrameLayout.addView(localImageView);
      return localFrameLayout;
    }

    private void populateThumbnails(ViewGroup paramViewGroup, List<SavedQuery> paramList)
    {
      int i = paramList.size();
      int j = this.thumbnailSize + (i - 1) * this.thumbnailStackOffset;
      paramViewGroup.setLayoutParams(new LinearLayout.LayoutParams(j, j));
      ArrayList localArrayList = new ArrayList(i);
      for (int k = 0; k < i; k++)
        localArrayList.add(makeThumbnailView((SavedQuery)paramList.get(k), k, i));
      Collections.reverse(localArrayList);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
        paramViewGroup.addView((View)localIterator.next());
    }
  }

  static final class TipPromotionView extends FrameLayout
  {
    private TipPromotionView(final Context paramContext, int paramInt)
    {
      super();
      addView(LayoutInflater.from(paramContext).inflate(paramInt, null));
      setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ClickTracker.logClick(paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PROMOTION_TIP);
          PromotionView.logger.i("Promotion clicked", new Object[0]);
          Intent localIntent = new Intent(paramContext, TipsActivity.class);
          paramContext.startActivity(localIntent);
        }
      });
      setFocusable(true);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.PromotionView
 * JD-Core Version:    0.6.2
 */