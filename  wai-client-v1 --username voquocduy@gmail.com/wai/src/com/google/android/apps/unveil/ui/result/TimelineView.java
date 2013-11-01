package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Scroller;
import com.google.android.apps.unveil.R.anim;
import com.google.android.apps.unveil.R.color;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PixelUtils;
import com.google.android.apps.unveil.env.ThumbnailProvider;
import com.google.android.apps.unveil.env.ThumbnailProvider.SizeSpec;
import com.google.android.apps.unveil.env.ThumbnailProvider.ThumbnailListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.SpeechEngine;
import com.google.android.apps.unveil.nonstop.SpeechEngine.Slot;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.android.apps.unveil.results.BasicAnnotation;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.tracking.ContinuousAnnotationRenderer.DestinationProvider;
import com.google.android.apps.unveil.ui.AnimationAdapter;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TimelineView extends ViewGroup
  implements GestureDetector.OnGestureListener, ContinuousAnnotationRenderer.DestinationProvider
{
  private static final int CHILD_SIZE_DIP = 80;
  private static final int OFFSET_DIP = 30;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Animation.AnimationListener animationAppendListener = new AnimationAdapter()
  {
    public void onAnimationEnd(Animation paramAnonymousAnimation)
    {
      TimelineView.this.requestAlignmentDueToSystemEvent();
      TimelineView.this.onScrollDone();
    }
  };
  private final Drawable center;
  private final int childSizeSpec;
  private final ClickLogger clickLogger = new ClickLogger(null);
  private final GestureDetector gestureDetector;
  private final Handler handler = new Handler();
  private final Drawable leftEdge;
  private final Scroller lensScroller;
  private EventListener listener;
  private final int offsetPixels;
  private boolean requestAlignAfterScroll;
  private final Drawable rightEdge;
  private final Runnable scrollDoneRunner = new Runnable()
  {
    public void run()
    {
      View localView = TimelineView.this.getChildInLens();
      String str;
      if ((localView == null) || (localView.getTag() == null))
      {
        UnveilLogger localUnveilLogger = TimelineView.logger;
        Object[] arrayOfObject = new Object[1];
        if (localView == null)
        {
          str = "no view in lens.";
          arrayOfObject[0] = str;
          localUnveilLogger.w("Unexpected scrolling state: %s", arrayOfObject);
        }
      }
      do
      {
        return;
        str = "view has no tag.";
        break;
        ResultItem localResultItem = ((BasicAnnotation)localView.getTag()).getResult();
        TimelineView.this.clickLogger.onScrollEnd(TimelineView.this.getContext(), localResultItem, TimelineView.this.getResults().indexOf(localResultItem));
      }
      while ((TimelineView.this.listener == null) || (localView == TimelineView.this.getChildAt(0)));
      TimelineView.this.listener.onScrollChanged();
    }
  };
  private final Scroller scroller;
  private SpeechEngine speechEngine;
  private View tappedView;
  private final ThumbnailProvider thumbnailCache;
  private final TraceTracker traceTracker;
  private final LinkedList<View> viewPool = new LinkedList();
  private boolean waitingScrollDone;

  public TimelineView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    UnveilApplication localUnveilApplication = (UnveilApplication)paramContext.getApplicationContext();
    this.thumbnailCache = localUnveilApplication.getThumbnailCache();
    this.gestureDetector = new GestureDetector(this);
    this.childSizeSpec = View.MeasureSpec.makeMeasureSpec(PixelUtils.dipToPix(80.0F, getContext()), 1073741824);
    this.scroller = new Scroller(getContext());
    setBackgroundColor(getResources().getColor(R.color.result_item_background));
    this.offsetPixels = PixelUtils.dipToPix(30.0F, getContext());
    this.lensScroller = new Scroller(getContext());
    this.lensScroller.startScroll(this.offsetPixels, 0, 0, 0);
    this.lensScroller.abortAnimation();
    this.traceTracker = localUnveilApplication.getTraceTracker();
    this.leftEdge = getContext().getResources().getDrawable(R.drawable.ic_lens_left);
    this.center = getContext().getResources().getDrawable(R.drawable.ic_lens_center);
    this.rightEdge = getContext().getResources().getDrawable(R.drawable.ic_lens_right);
  }

  private void append(BasicAnnotation paramBasicAnnotation, int paramInt)
  {
    boolean bool = shouldAnimateNewItems();
    if (bool)
      slideToRight(0);
    View localView = makeView(paramBasicAnnotation, paramInt);
    addView(localView, 0);
    if (bool)
    {
      Animation localAnimation = AnimationUtils.loadAnimation(getContext(), R.anim.pop_in);
      localAnimation.setAnimationListener(this.animationAppendListener);
      localView.startAnimation(localAnimation);
      return;
    }
    scrollBy(View.MeasureSpec.getSize(this.childSizeSpec), 0);
  }

  private void bind(View paramView, BasicAnnotation paramBasicAnnotation, int paramInt)
  {
    ResultItem localResultItem = paramBasicAnnotation.getResult();
    ImageView localImageView = (ImageView)paramView.findViewById(R.id.thumbnail);
    localImageView.setVisibility(0);
    if (localImageView.getDrawable() == null)
      localImageView.setImageDrawable(getContext().getResources().getDrawable(R.drawable.no_thumbnail));
    paramView.setTag(R.id.thumbnail, Integer.valueOf(paramInt));
    paramView.setContentDescription(paramBasicAnnotation.getResult().getTtsDescription());
    String str1 = localResultItem.getThumbnailUrl();
    if (TextUtils.isEmpty(str1));
    String str2;
    do
    {
      return;
      str2 = (String)localImageView.getTag(R.id.thumbnail);
    }
    while ((!TextUtils.isEmpty(str2)) && (str2.equals(str1)));
    updateThumbnail(localImageView, str1);
  }

  private void clearTappedView()
  {
    if (this.tappedView != null)
    {
      this.tappedView.setPressed(false);
      this.tappedView = null;
    }
  }

  private void doRequestAlignment()
  {
    this.requestAlignAfterScroll = true;
    this.waitingScrollDone = true;
    invalidate();
  }

  private void drawLens(Canvas paramCanvas)
  {
    this.lensScroller.computeScrollOffset();
    int i = View.MeasureSpec.getSize(this.childSizeSpec);
    int j = getScrollX() + this.lensScroller.getCurrX();
    this.leftEdge.setBounds(getScrollX(), 0, j, getHeight());
    this.leftEdge.draw(paramCanvas);
    this.rightEdge.setBounds(j + i, 0, getScrollX() + getVisibleWidth(), getHeight());
    this.rightEdge.draw(paramCanvas);
    this.center.setBounds(j, 0, j + i, getHeight());
    this.center.draw(paramCanvas);
  }

  private View getChildByX(int paramInt)
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
    {
      View localView = getChildAt(j);
      if ((localView.getLeft() <= paramInt) && (localView.getRight() > paramInt))
        return localView;
    }
    return null;
  }

  private View getChildInLens()
  {
    this.lensScroller.computeScrollOffset();
    return getChildByX(getLensCenterX());
  }

  private View getDisplayedViewFor(BasicAnnotation paramBasicAnnotation)
  {
    int i = getChildCount();
    for (int j = 0; j < i; j++)
      if (getChildAt(j).getTag() == paramBasicAnnotation)
        return getChildAt(j);
    return null;
  }

  private int getLensCenterX()
  {
    return 30 + (40 + (getScrollX() + this.lensScroller.getCurrX()));
  }

  private int getMaxScrollX()
  {
    int i = View.MeasureSpec.getSize(this.childSizeSpec);
    int j = i + this.lensScroller.getCurrX();
    return i * getChildCount() - j;
  }

  private int getMinScrollX()
  {
    return -this.lensScroller.getCurrX();
  }

  private int getVisibleWidth()
  {
    Rect localRect = new Rect();
    getGlobalVisibleRect(localRect);
    return localRect.width();
  }

  private void keepAligned()
  {
    int i = View.MeasureSpec.getSize(this.childSizeSpec);
    int j = getScrollX() + this.lensScroller.getCurrX();
    int k = j - i * (j / i);
    if (k == 0)
    {
      invalidate();
      return;
    }
    if (k < i / 2)
    {
      smoothScrollTo(getScrollX() - k);
      return;
    }
    smoothScrollTo(i + getScrollX() - k);
  }

  private View makeView(BasicAnnotation paramBasicAnnotation, int paramInt)
  {
    View localView = (View)this.viewPool.poll();
    if (localView == null)
      localView = View.inflate(getContext(), R.layout.result_bar_item, null);
    while (true)
    {
      localView.setTag(paramBasicAnnotation);
      localView.setOnFocusChangeListener(new View.OnFocusChangeListener()
      {
        public void onFocusChange(View paramAnonymousView, boolean paramAnonymousBoolean)
        {
          if (paramAnonymousBoolean)
            TimelineView.this.onChildFocused(paramAnonymousView);
        }
      });
      localView.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (TimelineView.this.listener != null)
            TimelineView.this.listener.onResultClicked(((BasicAnnotation)paramAnonymousView.getTag()).getResult());
        }
      });
      bind(localView, paramBasicAnnotation, paramInt);
      return localView;
      localView.clearAnimation();
    }
  }

  private void notifyScrolling()
  {
    if (this.listener != null)
      this.listener.onScrolling((getScrollX() + this.lensScroller.getCurrX()) / (getChildCount() * View.MeasureSpec.getSize(this.childSizeSpec)));
  }

  private void onChildFocused(View paramView)
  {
    int i = View.MeasureSpec.getSize(this.childSizeSpec);
    int j = getScrollX();
    int k = this.lensScroller.getCurrX();
    if (paramView.getLeft() < j)
    {
      smoothScrollTo(j - i);
      this.lensScroller.startScroll(k, 0, i + (paramView.getLeft() - j - k), 0);
    }
    while (true)
    {
      this.clickLogger.onScrollStart(ScrollReason.TAP);
      this.waitingScrollDone = true;
      invalidate();
      BasicAnnotation localBasicAnnotation = (BasicAnnotation)paramView.getTag();
      this.speechEngine.say(SpeechEngine.Slot.RESULT, localBasicAnnotation.getResult().getTtsDescription());
      return;
      if (paramView.getRight() - j - getVisibleWidth() > 0)
      {
        smoothScrollTo(j + i);
        this.lensScroller.startScroll(k, 0, paramView.getLeft() - j - k - i, 0);
      }
      else
      {
        this.lensScroller.startScroll(k, 0, paramView.getLeft() - j - k, 0);
      }
    }
  }

  private void onScrollDone()
  {
    this.handler.post(this.scrollDoneRunner);
  }

  private void requestAlignmentDueToSystemEvent()
  {
    doRequestAlignment();
  }

  private void requestAlignmentDueToUserInput()
  {
    this.clickLogger.onScrollStart(ScrollReason.GESTURE);
    doRequestAlignment();
  }

  private boolean shouldAnimateNewItems()
  {
    return (getScrollX() <= 0) && (this.lensScroller.getCurrX() <= this.offsetPixels);
  }

  private void slideByOne(boolean paramBoolean)
  {
    View localView1 = getChildInLens();
    if (localView1 == null)
      return;
    if (paramBoolean);
    for (int i = localView1.getRight() + localView1.getMeasuredWidth() / 2; ; i = localView1.getLeft() - localView1.getMeasuredWidth() / 2)
    {
      View localView2 = getChildByX(i);
      if (localView2 == null)
        break;
      onChildFocused(localView2);
      localView2.sendAccessibilityEvent(8);
      return;
    }
    onChildFocused(localView1);
    localView1.sendAccessibilityEvent(8);
  }

  private void slideToRight(int paramInt)
  {
    int i = getChildCount();
    if (i > paramInt)
      for (int j = paramInt; j < i; j++)
        getChildAt(j).startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.slide_right));
  }

  private void smoothScrollTo(int paramInt)
  {
    this.waitingScrollDone = true;
    int i = getScrollX();
    this.scroller.startScroll(i, 0, paramInt - i, 0);
    invalidate();
  }

  private boolean update(BasicAnnotation paramBasicAnnotation, int paramInt)
  {
    View localView = getDisplayedViewFor(paramBasicAnnotation);
    if (localView != null)
    {
      bind(localView, paramBasicAnnotation, paramInt);
      return true;
    }
    return false;
  }

  private void updateThumbnail(ImageView paramImageView, final String paramString)
  {
    final ThumbnailProvider.ThumbnailListener localThumbnailListener = ThumbnailProvider.makeImageViewThumbnailListener(paramImageView, paramString);
    this.thumbnailCache.fetch(paramString, new ThumbnailProvider.ThumbnailListener()
    {
      private Integer actionNumber;

      public void onThumbnailLoading()
      {
        this.actionNumber = Integer.valueOf(paramString.hashCode());
        TimelineView.this.traceTracker.beginTraceAction(this.actionNumber.intValue());
        TimelineView.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_THUMBNAIL_FETCH, this.actionNumber.intValue());
      }

      public void onThumbnailReady(Picture paramAnonymousPicture)
      {
        localThumbnailListener.onThumbnailReady(paramAnonymousPicture);
        if (this.actionNumber != null)
          TimelineView.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_THUMBNAIL_FETCH, this.actionNumber.intValue());
      }
    }
    , ThumbnailProvider.SizeSpec.FIFE_ORIGINAL);
    paramImageView.setTag(R.id.thumbnail, paramString);
  }

  public void addResult(BasicAnnotation paramBasicAnnotation, int paramInt)
  {
    append(paramBasicAnnotation, paramInt);
  }

  public BasicAnnotation computeSelectedAnnotation()
  {
    Object localObject = null;
    int i = getLensCenterX();
    int j = getChildCount();
    int k = 0;
    if (k < j)
    {
      View localView = getChildAt(k);
      if ((localView.getLeft() <= i) && (localView.getRight() > i))
        localObject = localView;
      ImageView localImageView = (ImageView)localView.findViewById(R.id.thumbnail);
      Integer localInteger;
      if (localImageView != null)
      {
        localInteger = (Integer)localView.getTag(R.id.thumbnail);
        if ((localInteger != null) && (localInteger.intValue() != 0))
          break label106;
        localImageView.setBackgroundColor(0);
      }
      while (true)
      {
        k++;
        break;
        label106: float[] arrayOfFloat = new float[3];
        Color.colorToHSV(localInteger.intValue(), arrayOfFloat);
        if (localObject != localView)
          arrayOfFloat[2] = (0.7F * arrayOfFloat[2]);
        localImageView.setBackgroundColor(Color.HSVToColor(225, arrayOfFloat));
      }
    }
    if (localObject != null)
      return (BasicAnnotation)localObject.getTag();
    return null;
  }

  public void dispatchDraw(Canvas paramCanvas)
  {
    boolean bool1 = this.lensScroller.computeScrollOffset();
    super.dispatchDraw(paramCanvas);
    drawLens(paramCanvas);
    boolean bool2 = this.scroller.computeScrollOffset();
    if (bool2)
      scrollTo(this.scroller.getCurrX(), 0);
    if ((bool1) || (bool2))
    {
      notifyScrolling();
      invalidate();
    }
    do
    {
      return;
      if (this.requestAlignAfterScroll)
      {
        keepAligned();
        this.requestAlignAfterScroll = false;
        return;
      }
    }
    while (!this.waitingScrollDone);
    onScrollDone();
    this.waitingScrollDone = false;
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = paramMotionEvent.getAction();
    boolean bool = this.gestureDetector.onTouchEvent(paramMotionEvent);
    if ((i == 3) || (i == 4) || (i == 1))
    {
      if (!bool)
        requestAlignmentDueToUserInput();
      clearTappedView();
    }
    return true;
  }

  public RectF getDestination(BasicAnnotation paramBasicAnnotation)
  {
    View localView = getDisplayedViewFor(paramBasicAnnotation);
    int[] arrayOfInt = new int[2];
    localView.getLocationOnScreen(arrayOfInt);
    return new RectF(arrayOfInt[0], arrayOfInt[1], arrayOfInt[0] + localView.getWidth(), arrayOfInt[1] + localView.getHeight());
  }

  public List<ResultModel> getResults()
  {
    int i = getChildCount();
    ArrayList localArrayList = new ArrayList(i);
    for (int j = 0; j < i; j++)
      localArrayList.add(((BasicAnnotation)getChildAt(j).getTag()).getResult());
    return localArrayList;
  }

  public boolean onDown(MotionEvent paramMotionEvent)
  {
    this.tappedView = getChildByX((int)paramMotionEvent.getX() + getScrollX());
    return false;
  }

  public boolean onFling(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    this.scroller.fling(getScrollX(), 0, (int)-paramFloat1, 0, getMinScrollX(), getMaxScrollX(), 0, 0);
    requestAlignmentDueToUserInput();
    return false;
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    int i = getChildCount();
    int j = paramInt1;
    for (int k = 0; k < i; k++)
    {
      View localView = getChildAt(k);
      int m = localView.getMeasuredWidth();
      localView.layout(j, paramInt2, j + m, paramInt4);
      j += m;
    }
    if (getScrollX() == 0)
      scrollBy(-this.lensScroller.getCurrX(), 0);
  }

  public void onLongPress(MotionEvent paramMotionEvent)
  {
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    int i = getChildCount();
    if (i == 0)
    {
      setMeasuredDimension(0, 0);
      return;
    }
    int j = 0;
    for (int k = 0; k < i; k++)
    {
      View localView = getChildAt(k);
      localView.measure(this.childSizeSpec, this.childSizeSpec);
      j += localView.getMeasuredWidth();
    }
    int m = View.MeasureSpec.getMode(paramInt1);
    int n = View.MeasureSpec.getSize(paramInt1);
    if (m != 0)
      j = Math.max(n, j);
    setMeasuredDimension(j, View.MeasureSpec.getSize(this.childSizeSpec));
  }

  public boolean onScroll(MotionEvent paramMotionEvent1, MotionEvent paramMotionEvent2, float paramFloat1, float paramFloat2)
  {
    int i = getScrollX();
    float f = paramFloat1;
    if (f + i < getMinScrollX())
      f = getMinScrollX() - i;
    while (true)
    {
      scrollBy((int)f, 0);
      notifyScrolling();
      return false;
      if (f + i > getMaxScrollX())
        f = getMaxScrollX() - i;
    }
  }

  public void onShowPress(MotionEvent paramMotionEvent)
  {
    if (this.tappedView != null)
      this.tappedView.setPressed(true);
  }

  public boolean onSingleTapUp(MotionEvent paramMotionEvent)
  {
    if (this.tappedView != null)
    {
      ResultItem localResultItem = ((BasicAnnotation)this.tappedView.getTag()).getResult();
      ClickTracker.logResultClick(getContext(), NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CONTINUOUS_TIMELINE_THUMBNAIL_TAP, localResultItem, getResults().indexOf(localResultItem));
      if (getChildInLens() == this.tappedView)
        if (this.listener != null)
          this.listener.onResultClicked(localResultItem);
      while (true)
      {
        return true;
        onChildFocused(this.tappedView);
      }
    }
    return false;
  }

  public void resetScroll()
  {
    smoothScrollTo(-this.offsetPixels);
    requestAlignmentDueToSystemEvent();
    int i = this.lensScroller.getCurrX();
    this.lensScroller.startScroll(i, 0, this.offsetPixels - i, 0);
  }

  public void setListener(EventListener paramEventListener)
  {
    this.listener = paramEventListener;
  }

  public void setSpeechEngine(SpeechEngine paramSpeechEngine)
  {
    this.speechEngine = paramSpeechEngine;
  }

  public void slideLensLeft()
  {
    slideByOne(false);
  }

  public void slideLensRight()
  {
    slideByOne(true);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[TimelineView results: ");
    localStringBuilder.append(getResults());
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  public void updateResult(final BasicAnnotation paramBasicAnnotation, final int paramInt)
  {
    post(new Runnable()
    {
      public void run()
      {
        TimelineView.this.update(paramBasicAnnotation, paramInt);
      }
    });
  }

  private static final class ClickLogger
  {
    private TimelineView.ScrollReason pendingScrollReason = TimelineView.ScrollReason.NONE;

    void onScrollEnd(Context paramContext, ResultItem paramResultItem, int paramInt)
    {
      try
      {
        TimelineView.ScrollReason localScrollReason1 = this.pendingScrollReason;
        TimelineView.ScrollReason localScrollReason2 = TimelineView.ScrollReason.NONE;
        if (localScrollReason1 == localScrollReason2);
        while (true)
        {
          return;
          if (this.pendingScrollReason == TimelineView.ScrollReason.GESTURE)
            ClickTracker.logResultClick(paramContext, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.CONTINUOUS_TIMELINE_SWIPE, paramResultItem, paramInt);
          this.pendingScrollReason = TimelineView.ScrollReason.NONE;
        }
      }
      finally
      {
      }
    }

    void onScrollStart(TimelineView.ScrollReason paramScrollReason)
    {
      try
      {
        if (paramScrollReason == TimelineView.ScrollReason.NONE)
          throw new IllegalArgumentException();
      }
      finally
      {
      }
      if (paramScrollReason == TimelineView.ScrollReason.GESTURE)
      {
        TimelineView.ScrollReason localScrollReason1 = this.pendingScrollReason;
        TimelineView.ScrollReason localScrollReason2 = TimelineView.ScrollReason.TAP;
        if (localScrollReason1 != localScrollReason2);
      }
      while (true)
      {
        return;
        this.pendingScrollReason = paramScrollReason;
      }
    }
  }

  public static abstract interface EventListener extends ResultClickListener
  {
    public abstract void onScrollChanged();

    public abstract void onScrolling(float paramFloat);
  }

  static enum ScrollReason
  {
    static
    {
      GESTURE = new ScrollReason("GESTURE", 1);
      TAP = new ScrollReason("TAP", 2);
      ScrollReason[] arrayOfScrollReason = new ScrollReason[3];
      arrayOfScrollReason[0] = NONE;
      arrayOfScrollReason[1] = GESTURE;
      arrayOfScrollReason[2] = TAP;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.TimelineView
 * JD-Core Version:    0.6.2
 */