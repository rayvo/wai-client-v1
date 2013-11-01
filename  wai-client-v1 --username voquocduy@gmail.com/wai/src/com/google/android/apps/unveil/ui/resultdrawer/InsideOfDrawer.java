package com.google.android.apps.unveil.ui.resultdrawer;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.google.android.apps.unveil.env.UnveilLogger;

public abstract class InsideOfDrawer extends ScrollView
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final LinearLayout innards;
  private boolean scrollingEnabled;

  public InsideOfDrawer(Context paramContext)
  {
    super(paramContext);
    setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    this.innards = new LinearLayout(paramContext);
    this.innards.setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
    this.innards.setOrientation(1);
    addView(this.innards);
  }

  private static TextView makeSectionText(Context paramContext, boolean paramBoolean, String paramString)
  {
    TextView localTextView = new TextView(paramContext);
    localTextView.setText(paramString);
    localTextView.setTextColor(-1);
    if (paramBoolean)
    {
      localTextView.setPadding(15, 5, 5, 5);
      localTextView.setBackgroundColor(-12303292);
      return localTextView;
    }
    localTextView.setPadding(15, 15, 15, 15);
    return localTextView;
  }

  public void add(View paramView)
  {
    this.innards.addView(paramView);
  }

  public void addSection(String paramString1, String paramString2)
  {
    if (paramString1 != null)
      add(makeSectionText(getContext(), true, paramString1));
    add(makeSectionText(getContext(), false, paramString2));
  }

  public void disableScrolling()
  {
    this.scrollingEnabled = false;
  }

  public void enableScrolling()
  {
    this.scrollingEnabled = true;
  }

  public abstract int getAboveTheFoldHeight();

  public int getFullyOpenHeight()
  {
    return getInnardsHeight();
  }

  protected int getInnardsHeight()
  {
    return this.innards.getMeasuredHeight();
  }

  protected boolean isScrollingEnabled()
  {
    return this.scrollingEnabled;
  }

  public void onAnimationEnd()
  {
    setDrawingCacheEnabled(false);
  }

  public void onAnimationStart()
  {
    setDrawingCacheEnabled(true);
  }

  public void onConfigChanged(Configuration paramConfiguration)
  {
  }

  protected void onDraw(Canvas paramCanvas)
  {
    Bitmap localBitmap = getDrawingCache();
    if (localBitmap != null)
    {
      logger.i("Drawing Cached.", new Object[0]);
      paramCanvas.drawBitmap(localBitmap, 0.0F, 0.0F, null);
      return;
    }
    super.onDraw(paramCanvas);
  }

  public void onDrawerGrabbed()
  {
  }

  public void onStateReached(ResultDrawer.ExpansionState paramExpansionState)
  {
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (this.scrollingEnabled)
      return super.onTouchEvent(paramMotionEvent);
    return true;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.resultdrawer.InsideOfDrawer
 * JD-Core Version:    0.6.2
 */