package com.google.android.apps.unveil.ui.resultdrawer;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.FrameLayout.LayoutParams;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.ui.result.ResultStackView;

public class ResultsInsideOfDrawer extends InsideOfDrawer
{
  private final ResultStackView resultStack;
  private final Button sendToGoogle;

  public ResultsInsideOfDrawer(Context paramContext, ResultStackView paramResultStackView, View.OnClickListener paramOnClickListener)
  {
    super(paramContext);
    this.resultStack = paramResultStackView;
    paramResultStackView.setLayoutParams(new FrameLayout.LayoutParams(-1, -2, 80));
    paramResultStackView.setOrientation(1);
    paramResultStackView.setClickable(true);
    add(paramResultStackView);
    this.sendToGoogle = ((Button)View.inflate(paramContext, R.layout.send_to_google_button, null));
    this.sendToGoogle.setOnClickListener(paramOnClickListener);
    add(this.sendToGoogle);
  }

  public int getAboveTheFoldHeight()
  {
    return this.resultStack.getHeight();
  }

  public ResultStackView getResultStack()
  {
    return this.resultStack;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    if (!isScrollingEnabled())
      return this.resultStack.onTouchEvent(paramMotionEvent);
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.resultdrawer.ResultsInsideOfDrawer
 * JD-Core Version:    0.6.2
 */