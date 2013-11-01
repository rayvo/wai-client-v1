package com.google.android.apps.unveil.ui.resultdrawer;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.ui.AnimationAdapter;
import com.google.android.apps.unveil.ui.DirectedAlphaAnimationHelper;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;

public class ResultDrawer extends LinearLayout
{
  private static final boolean FLING_ENABLED = true;
  private static final UnveilLogger logger = new UnveilLogger();
  private int actionBarHeight;
  private final DirectedAlphaAnimationHelper animationHelper;
  private int cachedParentHeight;
  private final ExpansionHelper expansionHelper;
  private final GestureHelper gestureHelper;
  private final int ghostViewHeight;
  private final GripBarView gripBar;
  private final InsideOfDrawer insideOfDrawer;
  private final ViewGroup insideWrapper;

  public ResultDrawer(Context paramContext, final InsideOfDrawer paramInsideOfDrawer)
  {
    super(paramContext);
    setLayoutParams(new FrameLayout.LayoutParams(-1, -2));
    setOrientation(1);
    View localView = new View(paramContext);
    addView(localView);
    this.gripBar = new GripBarView(paramContext);
    this.gripBar.setOnTouchListener(new View.OnTouchListener()
    {
      public boolean onTouch(View paramAnonymousView, MotionEvent paramAnonymousMotionEvent)
      {
        ResultDrawer.logger.i("Grip Bar Touch: %s", new Object[] { paramAnonymousMotionEvent });
        return false;
      }
    });
    addView(this.gripBar);
    WindowManager localWindowManager = (WindowManager)getContext().getSystemService("window");
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    localWindowManager.getDefaultDisplay().getMetrics(localDisplayMetrics);
    this.ghostViewHeight = ((int)(0.3937F * localDisplayMetrics.densityDpi) - this.gripBar.getDrawableHeight());
    localView.setLayoutParams(new LinearLayout.LayoutParams(-1, this.ghostViewHeight));
    this.insideWrapper = new FrameLayout(paramContext);
    this.insideWrapper.setBackgroundColor(-16777216);
    this.insideWrapper.setLayoutParams(new LinearLayout.LayoutParams(-1, calculateSkirtHeight(paramContext)));
    addView(this.insideWrapper);
    this.insideOfDrawer = paramInsideOfDrawer;
    this.insideWrapper.addView(paramInsideOfDrawer);
    paramInsideOfDrawer.requestLayout();
    this.gestureHelper = new GestureHelper(null);
    this.animationHelper = new DirectedAlphaAnimationHelper()
    {
      public void clearAnimation()
      {
        ResultDrawer.this.clearAnimation();
        paramInsideOfDrawer.onAnimationEnd();
      }

      public void handleAlpha(float paramAnonymousFloat)
      {
        ResultDrawer.ExpansionHelper.access$1600(ResultDrawer.this.expansionHelper, paramAnonymousFloat);
      }
    };
    this.expansionHelper = new ExpansionHelper(((UnveilContext)paramContext.getApplicationContext()).getClickTracker());
    ExpansionHelper.access$702(this.expansionHelper, ExpansionState.SEMI);
  }

  private int calculateSkirtHeight(Context paramContext)
  {
    Display localDisplay = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay();
    return Math.max(localDisplay.getHeight(), localDisplay.getWidth());
  }

  private int getParentHeight()
  {
    return this.cachedParentHeight;
  }

  private void setLayoutForHeight(int paramInt)
  {
    if (getParent() == null)
      return;
    int i = getTop();
    offsetTopAndBottom(getParentHeight() - paramInt - i);
    ((View)getParent()).invalidate();
  }

  public boolean canTransitionToSemi()
  {
    return (this.expansionHelper.state == ExpansionState.OPEN) && (this.expansionHelper.allowSemiState());
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    return this.gestureHelper.dispatchTouchEvent(this, paramMotionEvent);
  }

  public ExpansionState getExpansionState()
  {
    return this.expansionHelper.state;
  }

  protected boolean handleAlpha(float paramFloat)
  {
    this.expansionHelper.setHeightForAlpha(paramFloat);
    return true;
  }

  public boolean isAnimating()
  {
    return this.animationHelper.isAnimating();
  }

  public boolean isVisible()
  {
    return this.expansionHelper.state != ExpansionState.SHUT;
  }

  public void onConfigChanged(Configuration paramConfiguration)
  {
    this.insideOfDrawer.onConfigChanged(paramConfiguration);
    requestLayout();
    ((View)getParent()).invalidate();
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if (this.cachedParentHeight != paramInt4)
    {
      this.cachedParentHeight = paramInt4;
      this.expansionHelper.onParentHeightChanged();
      ((View)getParent()).invalidate();
    }
    DrawerGesture localDrawerGesture = this.gestureHelper.currentGesture;
    if (isAnimating())
      return;
    if ((localDrawerGesture != null) && (localDrawerGesture.type == ResultDrawer.DrawerGesture.Type.BAR))
    {
      logger.i("Refusing to set layout for our height as we are currently being dragged.", new Object[0]);
      setLayoutForHeight(localDrawerGesture.midGestureHeight);
      return;
    }
    setLayoutForHeight(this.expansionHelper.getHeightForState(this.expansionHelper.state));
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    View.MeasureSpec.getSize(paramInt1);
    int i = View.MeasureSpec.getSize(paramInt2);
    int j = i + this.ghostViewHeight;
    setMeasuredDimension(View.MeasureSpec.getSize(paramInt1), j);
    int k = View.MeasureSpec.makeMeasureSpec(i - this.gripBar.getHeight(), -2147483648);
    this.insideOfDrawer.measure(paramInt1, k);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    logger.i("Touch event routed to us: %s", new Object[] { paramMotionEvent });
    return this.gestureHelper.dispatchTouchEvent(this, paramMotionEvent);
  }

  public void setActionBarHeight(int paramInt)
  {
    this.actionBarHeight = paramInt;
  }

  public void toggleExpansion()
  {
    switch (3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$ExpansionState[this.expansionHelper.state.ordinal()])
    {
    default:
      return;
    case 2:
      this.expansionHelper.transitionToState(ExpansionState.OPEN, false);
      return;
    case 1:
    case 3:
    }
    this.expansionHelper.transitionToState(ExpansionState.SEMI, false);
  }

  private static class DrawerGesture
  {
    public int midGestureHeight;
    public final ResultDrawer.ExpansionState startingState;
    public final Type type;
    public float yOffset;

    public DrawerGesture(Type paramType, float paramFloat, ResultDrawer.ExpansionState paramExpansionState)
    {
      this.yOffset = paramFloat;
      this.type = paramType;
      this.startingState = paramExpansionState;
    }

    static enum Type
    {
      static
      {
        ABOVE_THE_FOLD = new Type("ABOVE_THE_FOLD", 1);
        REST_OF_INSIDE = new Type("REST_OF_INSIDE", 2);
        Type[] arrayOfType = new Type[3];
        arrayOfType[0] = BAR;
        arrayOfType[1] = ABOVE_THE_FOLD;
        arrayOfType[2] = REST_OF_INSIDE;
      }
    }
  }

  public class ExpansionHelper
  {
    private static final float MAX_SEMI_HEIGHT_RATIO = 0.9F;
    private ResultDrawer.ExpansionState state;
    private final ClickTracker tracker;

    public ExpansionHelper(ClickTracker arg2)
    {
      Object localObject;
      this.tracker = localObject;
    }

    private void animateTo(ResultDrawer.ExpansionState paramExpansionState, boolean paramBoolean)
    {
      startAnimation(calculateDistanceTo(paramExpansionState), paramBoolean, paramExpansionState);
    }

    private int calculateDistanceTo(ResultDrawer.ExpansionState paramExpansionState)
    {
      return Math.abs(getCurrentVisibleHeight() - getHeightForState(paramExpansionState));
    }

    private float calculateRatioFor(ResultDrawer.ExpansionState paramExpansionState)
    {
      return getHeightForState(paramExpansionState) / ResultDrawer.this.getParentHeight();
    }

    private int calculateSemiShutHeight()
    {
      return ResultDrawer.this.ghostViewHeight + ResultDrawer.this.gripBar.getHeight() + ResultDrawer.this.insideOfDrawer.getAboveTheFoldHeight();
    }

    private int calculateToggleDuration(boolean paramBoolean)
    {
      return (int)(ResultDrawer.this.expansionHelper.calculateSemiShutHeight() / 0.75F);
    }

    private void hide(boolean paramBoolean)
    {
      ResultDrawer.logger.i("Hiding.", new Object[0]);
      ResultDrawer.this.animationHelper.startAnimation(getCurrentExpansionRatio(), calculateRatioFor(ResultDrawer.ExpansionState.SHUT), new AnimationAdapter()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          ResultDrawer.this.insideOfDrawer.disableScrolling();
          ResultDrawer.this.insideOfDrawer.onStateReached(ResultDrawer.ExpansionHelper.this.state);
        }
      }
      , calculateToggleDuration(paramBoolean));
    }

    private void onParentHeightChanged()
    {
      if ((this.state == ResultDrawer.ExpansionState.SEMI) && (!allowSemiState()))
        transitionToState(ResultDrawer.ExpansionState.OPEN, true);
    }

    private void setHeightForAlpha(float paramFloat)
    {
      int i = ResultDrawer.this.getParentHeight();
      ResultDrawer.this.setLayoutForHeight((int)(paramFloat * i));
    }

    private void startAnimation(int paramInt, boolean paramBoolean, final ResultDrawer.ExpansionState paramExpansionState)
    {
      float f = calculateRatioFor(paramExpansionState);
      ResultDrawer.this.animationHelper.startAnimation(getCurrentExpansionRatio(), f, new AnimationAdapter()
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          ResultDrawer.this.insideOfDrawer.onStateReached(paramExpansionState);
        }
      }
      , paramInt);
    }

    public boolean allowSemiState()
    {
      return calculateSemiShutHeight() < (int)(0.9F * ResultDrawer.this.getParentHeight());
    }

    public float getCurrentExpansionRatio()
    {
      float f = ResultDrawer.this.getParentHeight();
      return getCurrentVisibleHeight() / f;
    }

    public int getCurrentVisibleHeight()
    {
      return ResultDrawer.this.getParentHeight() - ResultDrawer.this.getTop();
    }

    public int getHeightForState(ResultDrawer.ExpansionState paramExpansionState)
    {
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$ExpansionState[paramExpansionState.ordinal()])
      {
      default:
        throw new AssertionError("Unexpected state.");
      case 1:
        return Math.min(ResultDrawer.this.insideOfDrawer.getFullyOpenHeight() + ResultDrawer.this.gripBar.getHeight() + ResultDrawer.this.ghostViewHeight, ResultDrawer.this.getParentHeight() + ResultDrawer.this.ghostViewHeight) - ResultDrawer.this.actionBarHeight;
      case 2:
        return calculateSemiShutHeight();
      case 3:
      }
      return ResultDrawer.this.gripBar.getHeight() + ResultDrawer.this.ghostViewHeight;
    }

    public void transitionToState(ResultDrawer.ExpansionState paramExpansionState, boolean paramBoolean)
    {
      if ((paramExpansionState == ResultDrawer.ExpansionState.SEMI) && (!allowSemiState()))
      {
        if (this.state == ResultDrawer.ExpansionState.OPEN)
        {
          transitionToState(ResultDrawer.ExpansionState.SHUT, paramBoolean);
          return;
        }
        transitionToState(ResultDrawer.ExpansionState.OPEN, paramBoolean);
        return;
      }
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$ExpansionState[paramExpansionState.ordinal()])
      {
      default:
        throw new AssertionError("Unexpected state.");
      case 1:
        animateTo(ResultDrawer.ExpansionState.OPEN, paramBoolean);
        ResultDrawer.this.insideOfDrawer.enableScrolling();
        this.tracker.logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DRAWER_OPENED);
      case 2:
      case 3:
      }
      while (true)
      {
        this.state = paramExpansionState;
        return;
        animateTo(ResultDrawer.ExpansionState.SEMI, paramBoolean);
        ResultDrawer.this.insideOfDrawer.smoothScrollTo(0, 0);
        ResultDrawer.this.insideOfDrawer.disableScrolling();
        this.tracker.logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DRAWER_SEMI);
        continue;
        boolean bool = false;
        if (!paramBoolean)
          bool = true;
        hide(bool);
        this.tracker.logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.DRAWER_CLOSED);
      }
    }
  }

  public static enum ExpansionState
  {
    static
    {
      ExpansionState[] arrayOfExpansionState = new ExpansionState[3];
      arrayOfExpansionState[0] = OPEN;
      arrayOfExpansionState[1] = SEMI;
      arrayOfExpansionState[2] = SHUT;
    }
  }

  private class GestureHelper
  {
    private static final float RELEASE_VELOCITY_PX_PER_MS = 0.75F;
    private static final float THROW_THRESHOLD = 250.0F;
    private ResultDrawer.DrawerGesture currentGesture;
    private final GestureDetector gestureDetector = new GestureDetector(new GestureDetector.SimpleOnGestureListener()
    {
      public boolean onFling(MotionEvent paramAnonymousMotionEvent1, MotionEvent paramAnonymousMotionEvent2, float paramAnonymousFloat1, float paramAnonymousFloat2)
      {
        if (ResultDrawer.GestureHelper.this.currentGesture.type != ResultDrawer.DrawerGesture.Type.BAR)
          return false;
        if (Math.abs(paramAnonymousFloat2) > 250.0F)
        {
          ResultDrawer.GestureHelper.this.onFling(paramAnonymousMotionEvent2, paramAnonymousFloat2);
          return true;
        }
        ResultDrawer.GestureHelper.this.logger.i("Ignoring onFling because its speed was too low.", new Object[0]);
        return false;
      }

      public boolean onSingleTapUp(MotionEvent paramAnonymousMotionEvent)
      {
        boolean bool1 = paramAnonymousMotionEvent.getY() < ResultDrawer.this.gripBar.getBottom();
        boolean bool2 = false;
        if (bool1)
        {
          if (ResultDrawer.GestureHelper.this.currentGesture != null)
            ResultDrawer.GestureHelper.access$002(ResultDrawer.GestureHelper.this, null);
          ResultDrawer.this.toggleExpansion();
          ResultDrawer.this.gripBar.setPressed(false);
          bool2 = true;
        }
        return bool2;
      }
    });
    private final UnveilLogger logger = new UnveilLogger();
    private int previousPointerId = -1;

    private GestureHelper()
    {
    }

    private ResultDrawer.DrawerGesture.Type classifyDown(MotionEvent paramMotionEvent)
    {
      int i = ResultDrawer.this.getTop();
      int j = ResultDrawer.this.insideOfDrawer.getScrollY();
      int k = i + ResultDrawer.this.gripBar.getHeight() + ResultDrawer.this.ghostViewHeight - j;
      int m = k + ResultDrawer.this.insideOfDrawer.getAboveTheFoldHeight() - j;
      float f = paramMotionEvent.getRawY();
      if (f > m)
        return ResultDrawer.DrawerGesture.Type.REST_OF_INSIDE;
      if (f > k)
        return ResultDrawer.DrawerGesture.Type.ABOVE_THE_FOLD;
      return ResultDrawer.DrawerGesture.Type.BAR;
    }

    private void dispatchTouchEventToInsides(MotionEvent paramMotionEvent)
    {
      paramMotionEvent.offsetLocation(0.0F, -ResultDrawer.this.insideWrapper.getTop());
      if (this.previousPointerId != paramMotionEvent.getPointerId(0))
      {
        int i = paramMotionEvent.getAction();
        this.previousPointerId = paramMotionEvent.getPointerId(0);
        paramMotionEvent.setAction(3);
        ResultDrawer.this.insideOfDrawer.dispatchTouchEvent(paramMotionEvent);
        paramMotionEvent.setAction(i);
      }
      ResultDrawer.this.insideOfDrawer.dispatchTouchEvent(paramMotionEvent);
    }

    private ResultDrawer.ExpansionState getFlingTargetState(ResultDrawer.ExpansionState paramExpansionState1, ResultDrawer.ExpansionState paramExpansionState2, boolean paramBoolean)
    {
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$ExpansionState[paramExpansionState1.ordinal()])
      {
      default:
        throw new AssertionError(paramExpansionState1);
      case 1:
        if (paramBoolean)
        {
          if (paramExpansionState2 == ResultDrawer.ExpansionState.SHUT)
            return ResultDrawer.ExpansionState.SHUT;
          return ResultDrawer.ExpansionState.SEMI;
        }
        return ResultDrawer.ExpansionState.OPEN;
      case 2:
        if (paramBoolean)
          return ResultDrawer.ExpansionState.SHUT;
        return ResultDrawer.ExpansionState.OPEN;
      case 3:
      }
      if (paramBoolean)
        return ResultDrawer.ExpansionState.SHUT;
      if (paramExpansionState2 == ResultDrawer.ExpansionState.OPEN)
        return ResultDrawer.ExpansionState.OPEN;
      return ResultDrawer.ExpansionState.SEMI;
    }

    private int getHeightForMotionEvent(MotionEvent paramMotionEvent)
    {
      return (int)(((View)ResultDrawer.this.getParent()).getHeight() - paramMotionEvent.getRawY() - this.currentGesture.yOffset + ResultDrawer.this.ghostViewHeight);
    }

    private ResultDrawer.ExpansionState getReleaseTargetState(float paramFloat)
    {
      float f1 = 1.0F - paramFloat / ResultDrawer.this.getParentHeight();
      float f2;
      float f3;
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$ExpansionState[this.currentGesture.startingState.ordinal()])
      {
      default:
        throw new AssertionError(this.currentGesture.startingState + " is not a valid state.");
      case 1:
        f2 = 0.95F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.OPEN);
        f3 = 2.0F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.SHUT);
      case 2:
      case 3:
      }
      while (f1 < f3)
      {
        return ResultDrawer.ExpansionState.SHUT;
        f2 = 1.05F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.SEMI);
        f3 = 2.0F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.SHUT);
        continue;
        f2 = 1.55F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.SEMI);
        f3 = 1.25F * ResultDrawer.this.expansionHelper.calculateRatioFor(ResultDrawer.ExpansionState.SHUT);
      }
      if (f1 < f2)
        return ResultDrawer.ExpansionState.SEMI;
      return ResultDrawer.ExpansionState.OPEN;
    }

    private void handleTouchDown(MotionEvent paramMotionEvent, float paramFloat)
    {
      startGesture(paramMotionEvent, paramFloat);
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$DrawerGesture$Type[this.currentGesture.type.ordinal()])
      {
      default:
        return;
      case 1:
        this.currentGesture.midGestureHeight = getHeightForMotionEvent(paramMotionEvent);
        ResultDrawer.this.insideOfDrawer.onDrawerGrabbed();
        ResultDrawer.this.gripBar.setPressed(true);
        return;
      case 2:
      case 3:
      }
      dispatchTouchEventToInsides(paramMotionEvent);
    }

    private void handleTouchEnd(MotionEvent paramMotionEvent)
    {
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$DrawerGesture$Type[this.currentGesture.type.ordinal()])
      {
      default:
        throw new AssertionError(this.currentGesture.type);
      case 1:
        onBarRelease(paramMotionEvent);
        return;
      case 2:
        dispatchTouchEventToInsides(paramMotionEvent);
        return;
      case 3:
      }
      dispatchTouchEventToInsides(paramMotionEvent);
    }

    private void handleTouchMove(MotionEvent paramMotionEvent)
    {
      switch (ResultDrawer.3.$SwitchMap$com$google$android$apps$unveil$ui$resultdrawer$ResultDrawer$DrawerGesture$Type[this.currentGesture.type.ordinal()])
      {
      default:
        return;
      case 1:
        onDrag(paramMotionEvent);
        return;
      case 2:
        if (isOverBar(paramMotionEvent))
        {
          this.currentGesture = new ResultDrawer.DrawerGesture(ResultDrawer.DrawerGesture.Type.BAR, ResultDrawer.this.getTop() + ResultDrawer.this.ghostViewHeight - paramMotionEvent.getRawY(), this.currentGesture.startingState);
          onDrag(paramMotionEvent);
          MotionEvent localMotionEvent = MotionEvent.obtain(paramMotionEvent);
          localMotionEvent.setAction(3);
          dispatchTouchEventToInsides(localMotionEvent);
          ResultDrawer.this.insideOfDrawer.onDrawerGrabbed();
          ResultDrawer.this.gripBar.setPressed(true);
          return;
        }
        dispatchTouchEventToInsides(paramMotionEvent);
        return;
      case 3:
      }
      dispatchTouchEventToInsides(paramMotionEvent);
    }

    private boolean isOverBar(MotionEvent paramMotionEvent)
    {
      return paramMotionEvent.getY() < ResultDrawer.this.gripBar.getHeight();
    }

    private void onBarRelease(MotionEvent paramMotionEvent)
    {
      ResultDrawer.this.expansionHelper.transitionToState(getReleaseTargetState(paramMotionEvent.getRawY()), false);
      ResultDrawer.this.gripBar.setPressed(false);
    }

    private void onDrag(MotionEvent paramMotionEvent)
    {
      int i = getHeightForMotionEvent(paramMotionEvent);
      int j = ResultDrawer.this.expansionHelper.getHeightForState(ResultDrawer.ExpansionState.OPEN);
      if (i > j);
      for (int k = (i + j) / 2; ; k = i)
      {
        this.currentGesture.midGestureHeight = k;
        ResultDrawer.this.setLayoutForHeight(k);
        return;
      }
    }

    private void onFling(MotionEvent paramMotionEvent, float paramFloat)
    {
      this.logger.i("Got a fling: %s", new Object[] { paramMotionEvent });
      ResultDrawer.ExpansionState localExpansionState1 = getReleaseTargetState(paramMotionEvent.getRawY());
      ResultDrawer.ExpansionState localExpansionState2 = this.currentGesture.startingState;
      if (paramFloat > 0.0F);
      for (boolean bool = true; ; bool = false)
      {
        ResultDrawer.ExpansionState localExpansionState3 = getFlingTargetState(localExpansionState2, localExpansionState1, bool);
        ResultDrawer.this.expansionHelper.transitionToState(localExpansionState3, true);
        ResultDrawer.this.gripBar.setPressed(false);
        this.currentGesture = null;
        return;
      }
    }

    private void startGesture(MotionEvent paramMotionEvent, float paramFloat)
    {
      this.currentGesture = new ResultDrawer.DrawerGesture(classifyDown(paramMotionEvent), ResultDrawer.this.getTop() + ResultDrawer.this.ghostViewHeight - paramFloat, ResultDrawer.access$600(ResultDrawer.this).state);
    }

    public boolean dispatchTouchEvent(View paramView, MotionEvent paramMotionEvent)
    {
      if (this.gestureDetector.onTouchEvent(paramMotionEvent))
        return true;
      float f = paramMotionEvent.getRawY();
      switch (paramMotionEvent.getAction())
      {
      default:
        return true;
      case 0:
        handleTouchDown(paramMotionEvent, f);
        return true;
      case 2:
        handleTouchMove(paramMotionEvent);
        return true;
      case 1:
      case 3:
      }
      handleTouchEnd(paramMotionEvent);
      this.currentGesture = null;
      return true;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.resultdrawer.ResultDrawer
 * JD-Core Version:    0.6.2
 */