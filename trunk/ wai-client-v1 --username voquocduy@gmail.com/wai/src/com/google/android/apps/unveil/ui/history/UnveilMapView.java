package com.google.android.apps.unveil.ui.history;

import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;

public class UnveilMapView extends MapView
  implements GestureDetector.OnDoubleTapListener
{
  private static final UnveilLogger logger = new UnveilLogger();
  private GestureDetector gestureDetector = new GestureDetector(paramContext, new GestureDetector.OnGestureListener()
  {
    public boolean onDown(MotionEvent paramAnonymousMotionEvent)
    {
      return false;
    }

    public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return false;
    }

    public void onLongPress(MotionEvent paramAnonymousMotionEvent)
    {
    }

    public boolean onScroll(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
    {
      return false;
    }

    public void onShowPress(MotionEvent paramAnonymousMotionEvent)
    {
    }

    public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
    {
      return false;
    }
  });
  private OnSingleTapConfirmedListener singleTapConfirmedListener;

  public UnveilMapView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    this.gestureDetector.setOnDoubleTapListener(this);
  }

  public boolean onDoubleTap(MotionEvent paramMotionEvent)
  {
    this.singleTapConfirmedListener.onSingleTapDenied();
    getController().zoomInFixing((int)paramMotionEvent.getX(), (int)paramMotionEvent.getY());
    return true;
  }

  public boolean onDoubleTapEvent(MotionEvent paramMotionEvent)
  {
    return false;
  }

  public boolean onSingleTapConfirmed(MotionEvent paramMotionEvent)
  {
    if (this.singleTapConfirmedListener != null)
    {
      this.singleTapConfirmedListener.onSingleTapConfirmed();
      return true;
    }
    return false;
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    return super.onTouchEvent(paramMotionEvent) | this.gestureDetector.onTouchEvent(paramMotionEvent);
  }

  public void setOnSingleTapConfirmedListener(OnSingleTapConfirmedListener paramOnSingleTapConfirmedListener)
  {
    this.singleTapConfirmedListener = paramOnSingleTapConfirmedListener;
  }

  public static abstract interface OnSingleTapConfirmedListener
  {
    public abstract void onSingleTapConfirmed();

    public abstract void onSingleTapDenied();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.history.UnveilMapView
 * JD-Core Version:    0.6.2
 */