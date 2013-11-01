package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.TextView;
import com.google.android.apps.unveil.R.string;

public class ContinuousStateTextView extends TextView
{
  private static final int INTERVAL_MILLIS = 500;
  private static final int MAX_DOTS = 3;
  private static final int MSG_SEARCHING = 1;
  private int currentDots;
  private final Handler handler = new AnimationHandler(null);
  private final String noConnectionText = getResources().getString(R.string.continuous_no_signal);
  private final String pauseText = getResources().getString(R.string.continuous_paused);
  private final String searchText = getResources().getString(R.string.continuous_searching);

  public ContinuousStateTextView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.handler.sendMessage(this.handler.obtainMessage(1));
  }

  private void setSearchingText()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(this.searchText);
    switch (this.currentDots)
    {
    default:
    case 3:
    case 2:
    case 1:
    }
    while (true)
    {
      setText(localStringBuilder.toString());
      this.currentDots = ((1 + this.currentDots) % 4);
      return;
      localStringBuilder.append('.');
      localStringBuilder.append('.');
      localStringBuilder.append('.');
    }
  }

  public void noConnection()
  {
    try
    {
      this.handler.removeMessages(1);
      setText(this.noConnectionText);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void pause()
  {
    try
    {
      this.handler.removeMessages(1);
      setText(this.pauseText);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void resume()
  {
    try
    {
      boolean bool = this.handler.hasMessages(1);
      if (bool);
      while (true)
      {
        return;
        this.currentDots = 0;
        this.handler.sendMessage(this.handler.obtainMessage(1));
      }
    }
    finally
    {
    }
  }

  private class AnimationHandler extends Handler
  {
    private AnimationHandler()
    {
    }

    public void handleMessage(Message paramMessage)
    {
      ContinuousStateTextView.this.setSearchingText();
      ContinuousStateTextView.this.handler.sendMessageDelayed(ContinuousStateTextView.this.handler.obtainMessage(1), 500L);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ContinuousStateTextView
 * JD-Core Version:    0.6.2
 */