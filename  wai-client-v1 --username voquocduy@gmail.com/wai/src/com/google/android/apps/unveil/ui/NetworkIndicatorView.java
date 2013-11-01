package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.View;
import android.view.View.MeasureSpec;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.Timeout;
import com.google.android.apps.unveil.env.Timeout.TimeoutCallback;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.protocol.nonstop.ActiveFrameQueue.ActiveFrame;
import com.google.android.apps.unveil.protocol.nonstop.EventListener;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.Iterator;
import java.util.List;

public class NetworkIndicatorView extends View
  implements EventListener
{
  private static final int[] COLORS = { -65536, -16711936, -16776961, -65281, -16711681 };
  private static final int SEARCHING_TIMEOUT_MILLIS = 8000;
  private static final int SEARCH_FAILED = 0;
  private static final int SEARCH_SUCCEED = -1;
  public static final int SIMILAR_THRESHOLD = 50;
  public static final int TIMEOUT_MILLIS = 500;
  private final Handler handler;
  private int lastSceneColorIndex;
  private volatile int lastSceneQueries;
  private volatile int lastSceneResults;
  private int[] lastSceneSignature;
  private long lastSceneStartMillis;
  private final Paint paint = new Paint();
  private volatile boolean pullReceiving;
  private final Timeout pullReceivingTimeout;
  private volatile boolean pushReceiving;
  private final Timeout pushReceivingTimeout;
  private volatile boolean pushSending;
  private final Timeout pushSendingTimeout;
  private final Drawable receivingOff;
  private final Drawable receivingOn;
  private volatile int searchingCountDown;
  private final Runnable searchingRunnable;
  private final Drawable sendingOff;
  private final Drawable sendingOn;
  private final Drawable sessionReset;
  private volatile boolean sessionResetting;
  private final Timeout sessionResettingTimeout;

  public NetworkIndicatorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    Resources localResources = paramContext.getResources();
    this.sendingOn = localResources.getDrawable(R.drawable.sending_on);
    this.sendingOff = localResources.getDrawable(R.drawable.sending_off);
    this.receivingOn = localResources.getDrawable(R.drawable.receiving_on);
    this.receivingOff = localResources.getDrawable(R.drawable.receiving_off);
    this.sessionReset = localResources.getDrawable(R.drawable.session_reset);
    this.pushSendingTimeout = new Timeout(new Timeout.TimeoutCallback()
    {
      public void onTimeout()
      {
        NetworkIndicatorView.access$002(NetworkIndicatorView.this, false);
        NetworkIndicatorView.this.doInvalidate(true);
      }
    }
    , 500);
    this.pushReceivingTimeout = new Timeout(new Timeout.TimeoutCallback()
    {
      public void onTimeout()
      {
        NetworkIndicatorView.access$202(NetworkIndicatorView.this, false);
        NetworkIndicatorView.this.doInvalidate(true);
      }
    }
    , 500);
    this.sessionResettingTimeout = new Timeout(new Timeout.TimeoutCallback()
    {
      public void onTimeout()
      {
        NetworkIndicatorView.access$302(NetworkIndicatorView.this, false);
        NetworkIndicatorView.this.doInvalidate(true);
      }
    }
    , 500);
    this.pullReceivingTimeout = new Timeout(new Timeout.TimeoutCallback()
    {
      public void onTimeout()
      {
        NetworkIndicatorView.access$402(NetworkIndicatorView.this, false);
        NetworkIndicatorView.this.doInvalidate(true);
      }
    }
    , 500);
    this.paint.setTextSize(16.0F);
    this.paint.setFakeBoldText(true);
    this.paint.setAntiAlias(true);
    this.handler = new Handler();
    this.searchingRunnable = new Runnable()
    {
      public void run()
      {
        if ((NetworkIndicatorView.this.searchingCountDown != -1) && (NetworkIndicatorView.this.searchingCountDown != 0))
        {
          NetworkIndicatorView.access$510(NetworkIndicatorView.this);
          NetworkIndicatorView.this.handler.postDelayed(this, 1000L);
        }
        NetworkIndicatorView.this.doInvalidate(true);
      }
    };
  }

  private boolean belongsToCurrentScene(TimestampedFrame.Metadata paramMetadata)
  {
    return paramMetadata.getTimestamp() >= this.lastSceneStartMillis;
  }

  private void doInvalidate(boolean paramBoolean)
  {
    if (getVisibility() == 0)
    {
      if (paramBoolean)
        invalidate(0, 0, getWidth(), getHeight());
    }
    else
      return;
    postInvalidate(0, 0, getWidth(), getHeight());
  }

  private void onNewScene(TimestampedFrame paramTimestampedFrame)
  {
    this.lastSceneSignature = paramTimestampedFrame.getSignature();
    this.lastSceneStartMillis = paramTimestampedFrame.getTimestamp();
    this.lastSceneQueries = 0;
    this.lastSceneResults = 0;
    this.lastSceneColorIndex = ((1 + this.lastSceneColorIndex) % COLORS.length);
    this.searchingCountDown = 8;
    this.handler.removeCallbacks(this.searchingRunnable);
    this.handler.post(this.searchingRunnable);
  }

  public void onDraw(Canvas paramCanvas)
  {
    int i = 99;
    Drawable localDrawable1;
    Drawable localDrawable2;
    label28: Drawable localDrawable3;
    label41: int n;
    int i1;
    label224: String str2;
    label259: String str3;
    if (this.pushSending)
    {
      localDrawable1 = this.sendingOn;
      if (!this.pushReceiving)
        break label504;
      localDrawable2 = this.receivingOn;
      if (!this.pullReceiving)
        break label513;
      localDrawable3 = this.receivingOn;
      if (this.sessionResetting)
      {
        this.sessionReset.setBounds(0, 0, 0 + this.sessionReset.getIntrinsicWidth(), this.sessionReset.getIntrinsicHeight());
        this.sessionReset.draw(paramCanvas);
      }
      int j = 0 + this.sessionReset.getIntrinsicWidth();
      localDrawable1.setBounds(j, 0, j + localDrawable1.getIntrinsicWidth(), localDrawable1.getIntrinsicHeight());
      localDrawable1.draw(paramCanvas);
      int k = j + localDrawable1.getIntrinsicWidth();
      localDrawable2.setBounds(k, 0, k + localDrawable2.getIntrinsicWidth(), localDrawable2.getIntrinsicHeight());
      localDrawable2.draw(paramCanvas);
      int m = k + localDrawable2.getIntrinsicWidth();
      localDrawable3.setBounds(m, 0, m + localDrawable3.getIntrinsicWidth(), localDrawable3.getIntrinsicHeight());
      localDrawable3.draw(paramCanvas);
      n = m + localDrawable3.getIntrinsicWidth();
      this.paint.setColor(COLORS[this.lastSceneColorIndex]);
      if (this.lastSceneQueries <= i)
        break label522;
      i1 = i;
      String str1 = String.valueOf(i1);
      StringBuilder localStringBuilder = new StringBuilder().append(str1).append(":");
      if (this.lastSceneResults <= i)
        break label531;
      str2 = String.valueOf(i);
      switch (this.searchingCountDown)
      {
      default:
        str3 = str2 + " " + this.searchingCountDown;
      case -1:
      case 0:
      }
    }
    while (true)
    {
      float f = this.paint.measureText("99:99   ");
      Rect localRect = new Rect(n, 0, n + (int)f, getHeight());
      paramCanvas.drawRect(localRect, this.paint);
      this.paint.getTextBounds(str3, 0, str3.length(), localRect);
      int i2 = (getHeight() - localRect.height()) / 2;
      int i3 = ((int)f - localRect.width()) / 2;
      localRect.top = i2;
      localRect.bottom = (getHeight() - i2);
      localRect.right = (n + localRect.width() - i3);
      localRect.left = (n + i3);
      this.paint.setColor(-1);
      paramCanvas.drawText(str3, localRect.left, getHeight() - i2, this.paint);
      return;
      localDrawable1 = this.sendingOff;
      break;
      label504: localDrawable2 = this.receivingOff;
      break label28;
      label513: localDrawable3 = this.receivingOff;
      break label41;
      label522: i1 = this.lastSceneQueries;
      break label224;
      label531: i = this.lastSceneResults;
      break label259;
      str3 = str2 + " O";
      continue;
      str3 = str2 + " X";
    }
  }

  public void onHighQualityRequest(TimestampedFrame.Metadata paramMetadata)
  {
    if (belongsToCurrentScene(paramMetadata))
    {
      this.lastSceneQueries = (1 + this.lastSceneQueries);
      if (this.lastSceneQueries > 99)
        this.lastSceneQueries = 0;
    }
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    int i = View.MeasureSpec.getMode(paramInt1);
    int j = View.MeasureSpec.getSize(paramInt1);
    int k = View.MeasureSpec.getMode(paramInt2);
    int m = View.MeasureSpec.getSize(paramInt2);
    if (i != 1073741824)
    {
      int i1 = this.sessionReset.getIntrinsicWidth() + this.sendingOn.getIntrinsicWidth() + 2 * this.receivingOn.getIntrinsicWidth() + (int)this.paint.measureText("99:99   ");
      if ((i != -2147483648) || (i1 < j))
        j = i1;
    }
    if (k != 1073741824)
    {
      int n = this.sessionReset.getIntrinsicHeight();
      if ((k != -2147483648) || (n < j))
        m = n;
    }
    setMeasuredDimension(j, m);
  }

  public void onNetworkError(int paramInt)
  {
  }

  public void onNewFrame(TimestampedFrame paramTimestampedFrame)
  {
    if (this.lastSceneSignature == null)
      onNewScene(paramTimestampedFrame);
    int[] arrayOfInt;
    do
    {
      return;
      arrayOfInt = paramTimestampedFrame.getSignature();
    }
    while (TimestampedFrame.diffSignature(this.lastSceneSignature, arrayOfInt) <= 50);
    onNewScene(paramTimestampedFrame);
  }

  public void onNewRequest(TimestampedFrame.Metadata paramMetadata)
  {
    if (belongsToCurrentScene(paramMetadata))
    {
      this.lastSceneQueries = (1 + this.lastSceneQueries);
      if (this.lastSceneQueries > 99)
        this.lastSceneQueries = 0;
    }
  }

  public void onNewResults(List<Pair<ResultItem, ActiveFrameQueue.ActiveFrame>> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Pair localPair = (Pair)localIterator.next();
      if ((localPair.second != null) && (belongsToCurrentScene(((ActiveFrameQueue.ActiveFrame)localPair.second).getMetadata())))
      {
        this.lastSceneResults = (1 + this.lastSceneResults);
        if (this.lastSceneResults > 99)
          this.lastSceneResults = 0;
        this.searchingCountDown = -1;
      }
    }
  }

  public void onPullReceived()
  {
    this.pullReceiving = true;
    this.pullReceivingTimeout.reset();
    doInvalidate(false);
  }

  public void onPushReceived(TimestampedFrame.Metadata paramMetadata)
  {
    this.pushReceiving = true;
    this.pushReceivingTimeout.reset();
    doInvalidate(false);
  }

  public void onPushSent(TimestampedFrame.Metadata paramMetadata, byte[] paramArrayOfByte)
  {
    this.pushSending = true;
    this.pushSendingTimeout.reset();
    doInvalidate(false);
  }

  public void onSessionReset()
  {
    this.sessionResetting = true;
    this.sessionResettingTimeout.reset();
    doInvalidate(false);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.NetworkIndicatorView
 * JD-Core Version:    0.6.2
 */