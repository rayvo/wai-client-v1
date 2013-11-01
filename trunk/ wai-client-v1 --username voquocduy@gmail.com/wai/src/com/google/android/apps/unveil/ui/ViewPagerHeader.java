package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.env.PixelUtils;

public class ViewPagerHeader<AdapterType extends PagerAdapter,  extends TitleProvider> extends LinearLayout
  implements ViewPager.OnPageChangeListener
{
  private static final int INDICATOR_HEIGHT_DP = 5;
  private static final int PADDING_DP = 4;
  private static final int SINGLE_TITLE_ADDITIONAL_PADDING_DP = 2;
  private static final int TEXT_SIZE_SP = 16;
  private final AdapterType adapter;
  private int firstVisiblePage;
  private final ClickListener headerClickListener;
  private final int indicatorHeight;
  private final Paint indicatorPaint;
  private final IndicatorStyle indicatorStyle;
  private float positionOffset;
  private int scrollState;
  private int selectedPage;
  private final int singleTitleLeftPadding;
  private final View.OnClickListener titleClickListener = new View.OnClickListener()
  {
    public void onClick(View paramAnonymousView)
    {
      ViewPagerHeader.this.headerClickListener.onTitleClicked(((Integer)paramAnonymousView.getTag()).intValue());
    }
  };

  public ViewPagerHeader(Context paramContext, AdapterType paramAdapterType, IndicatorStyle paramIndicatorStyle, ClickListener paramClickListener)
  {
    super(paramContext);
    this.adapter = paramAdapterType;
    this.indicatorStyle = paramIndicatorStyle;
    this.headerClickListener = paramClickListener;
    int i = PixelUtils.dipToPix(4.0F, paramContext);
    setPadding(i, i, i, i);
    setBackgroundColor(paramContext.getResources().getColor(R.color.light_gray_background));
    setWillNotDraw(false);
    setOrientation(0);
    this.singleTitleLeftPadding = PixelUtils.dipToPix(2.0F, paramContext);
    this.indicatorPaint = new Paint();
    this.indicatorPaint.setColor(paramContext.getResources().getColor(R.color.dark_background));
    this.indicatorHeight = PixelUtils.dipToPix(5.0F, paramContext);
    updateTitles();
    updateFonts();
  }

  private RectF calculateIndicatorBounds(RectF[] paramArrayOfRectF)
  {
    RectF localRectF3;
    if (this.scrollState == 0)
      localRectF3 = paramArrayOfRectF[this.selectedPage];
    while (true)
    {
      RectF localRectF4 = new RectF(localRectF3);
      localRectF4.bottom = getHeight();
      localRectF4.top = (localRectF4.bottom - this.indicatorHeight);
      return localRectF4;
      if (this.firstVisiblePage == -1 + paramArrayOfRectF.length)
      {
        localRectF3 = paramArrayOfRectF[this.firstVisiblePage];
      }
      else
      {
        RectF localRectF1 = paramArrayOfRectF[this.firstVisiblePage];
        RectF localRectF2 = paramArrayOfRectF[(1 + this.firstVisiblePage)];
        float f = localRectF1.left * (1.0F - this.positionOffset) + localRectF2.left * this.positionOffset;
        localRectF3 = new RectF(f, 0.0F, f + (localRectF1.width() * (1.0F - this.positionOffset) + localRectF2.width() * this.positionOffset), 0.0F);
      }
    }
  }

  private RectF[] calculateTitleBounds()
  {
    int i = this.adapter.getCount();
    RectF[] arrayOfRectF = new RectF[i];
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      arrayOfRectF[j] = new RectF(localView.getLeft(), localView.getTop(), localView.getRight(), localView.getBottom());
    }
    return arrayOfRectF;
  }

  private void drawArrowIndicator(Canvas paramCanvas, RectF[] paramArrayOfRectF)
  {
    RectF localRectF = calculateIndicatorBounds(paramArrayOfRectF);
    float f1 = localRectF.height();
    float f2 = (localRectF.left + localRectF.right) / 2.0F;
    Path localPath = new Path();
    localPath.moveTo(f2 - f1, localRectF.bottom);
    localPath.lineTo(f2, localRectF.top);
    localPath.lineTo(f2 + f1, localRectF.bottom);
    localPath.close();
    paramCanvas.drawPath(localPath, this.indicatorPaint);
  }

  private void drawUnderlineIndicator(Canvas paramCanvas, RectF[] paramArrayOfRectF)
  {
    paramCanvas.drawRect(calculateIndicatorBounds(paramArrayOfRectF), this.indicatorPaint);
  }

  private void updateFonts()
  {
    int i = 0;
    if (i < getChildCount())
    {
      TextView localTextView = (TextView)getChildAt(i);
      if (i == this.selectedPage)
      {
        localTextView.setTypeface(Typeface.DEFAULT_BOLD);
        localTextView.setTextColor(-1);
      }
      while (true)
      {
        i++;
        break;
        localTextView.setTypeface(Typeface.DEFAULT);
        localTextView.setTextColor(-3355444);
      }
    }
  }

  protected void onDraw(Canvas paramCanvas)
  {
    super.onDraw(paramCanvas);
    RectF[] arrayOfRectF;
    if (this.adapter.getCount() > 1)
      arrayOfRectF = calculateTitleBounds();
    switch (2.$SwitchMap$com$google$android$apps$unveil$ui$ViewPagerHeader$IndicatorStyle[this.indicatorStyle.ordinal()])
    {
    default:
      throw new AssertionError();
    case 1:
      drawArrowIndicator(paramCanvas, arrayOfRectF);
      return;
    case 2:
    }
    drawUnderlineIndicator(paramCanvas, arrayOfRectF);
  }

  public void onPageScrollStateChanged(int paramInt)
  {
    this.scrollState = paramInt;
    invalidate();
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    this.firstVisiblePage = paramInt1;
    this.positionOffset = paramFloat;
    invalidate();
  }

  public void onPageSelected(int paramInt)
  {
    this.selectedPage = paramInt;
    updateFonts();
    invalidate();
  }

  public void updateTitles()
  {
    removeAllViews();
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    localLayoutParams.weight = 1.0F;
    int i;
    int j;
    label36: TextView localTextView;
    if (this.adapter.getCount() == 1)
    {
      i = 1;
      j = 0;
      if (j >= this.adapter.getCount())
        return;
      localTextView = new TextView(getContext());
      localTextView.setText(Html.fromHtml(((TitleProvider)this.adapter).getTitleOf(getResources(), j)), TextView.BufferType.SPANNABLE);
      localTextView.setTextSize(2, 16.0F);
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setTag(Integer.valueOf(j));
      localTextView.setOnClickListener(this.titleClickListener);
      if (i == 0)
        break label166;
      localTextView.setGravity(3);
      localTextView.setPadding(this.singleTitleLeftPadding, 0, 0, 0);
    }
    while (true)
    {
      localTextView.setMaxLines(1);
      addView(localTextView);
      j++;
      break label36;
      i = 0;
      break;
      label166: localTextView.setGravity(1);
    }
  }

  public static abstract interface ClickListener
  {
    public abstract void onTitleClicked(int paramInt);
  }

  public static enum IndicatorStyle
  {
    static
    {
      ARROW = new IndicatorStyle("ARROW", 1);
      IndicatorStyle[] arrayOfIndicatorStyle = new IndicatorStyle[2];
      arrayOfIndicatorStyle[0] = UNDERLINE;
      arrayOfIndicatorStyle[1] = ARROW;
    }
  }

  public static abstract interface TitleProvider
  {
    public abstract String getTitleOf(Resources paramResources, int paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ViewPagerHeader
 * JD-Core Version:    0.6.2
 */