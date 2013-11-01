package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.android.apps.unveil.ui.rotating.RotatingButton;
import com.google.android.apps.unveil.ui.rotating.RotatingLayout;
import com.google.android.apps.unveil.ui.rotating.RotatingTextView;
import java.util.List;

public class ProgressView extends FrameLayout
{
  static final long kAnimationDelayMs = 20L;
  static final long kAnimationDurationMs = 2000L;
  private boolean animating = false;
  private boolean animationStopRequested;
  private final RotatingButton cancelButton;
  private final Context context;
  private final EdgeDetectingSurfaceController edgeSurfaceController;
  private final SurfaceView edgeSurfaceView;
  private final int edgeSurfaceViewIndex;
  private final RelativeLayout layout;
  private final UnveilLogger logger = new UnveilLogger();
  private final LinearLayout progressArea;
  private final RotatingTextView progressMessage;
  private final RotatingLayout rotatingLayout;
  private final RotatingButton saveForLaterButton;

  public ProgressView(Context paramContext)
  {
    super(paramContext);
    this.context = paramContext;
    this.layout = ((RelativeLayout)LayoutInflater.from(paramContext).inflate(R.layout.progress_view, null));
    this.progressArea = ((LinearLayout)this.layout.findViewById(R.id.progress_area));
    this.edgeSurfaceView = ((SurfaceView)this.layout.findViewById(R.id.interstitial_query_image_edges));
    this.edgeSurfaceViewIndex = this.layout.indexOfChild(this.edgeSurfaceView);
    this.edgeSurfaceController = new EdgeDetectingSurfaceController(paramContext, this.edgeSurfaceView);
    this.progressMessage = ((RotatingTextView)this.layout.findViewById(R.id.progress_message));
    this.progressMessage.setText(paramContext.getText(R.string.working));
    this.progressMessage.setTextColor(-1);
    this.cancelButton = ((RotatingButton)this.layout.findViewById(R.id.cancel_query_button));
    this.saveForLaterButton = ((RotatingButton)this.layout.findViewById(R.id.save_for_later_button));
    this.rotatingLayout = ((RotatingLayout)this.layout.findViewById(R.id.progress_rotating_layout));
    addView(this.layout);
  }

  private void animateView()
  {
    if (this.animationStopRequested)
      try
      {
        this.animating = false;
        this.animationStopRequested = false;
        return;
      }
      finally
      {
      }
    if (getVisibility() != 0)
    {
      this.logger.w("View not visible, halting animation.", new Object[0]);
      this.animating = false;
      return;
    }
    EdgeDetectingSurfaceController.Direction localDirection;
    if (this.rotatingLayout.locked)
      localDirection = EdgeDetectingSurfaceController.Direction.LEFT_TO_RIGHT;
    while (true)
    {
      this.edgeSurfaceController.setDirection(localDirection);
      this.edgeSurfaceController.render();
      postDelayed(new Runnable()
      {
        public void run()
        {
          ProgressView.this.animateView();
        }
      }
      , 20L);
      return;
      UnveilApplication localUnveilApplication = (UnveilApplication)this.context.getApplicationContext();
      int i = localUnveilApplication.getSensorProvider().getRoundedDeviceOrientation();
      if (i == -1)
        i = 0;
      if (localUnveilApplication.getViewport().getNaturalOrientation(this.context) == 2)
        i += 270;
      switch (i)
      {
      default:
        UnveilLogger localUnveilLogger = this.logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        localUnveilLogger.w("Invalid rotation value: %d", arrayOfObject);
      case 0:
        localDirection = EdgeDetectingSurfaceController.Direction.BOTTOM_TO_TOP;
        break;
      case 270:
        localDirection = EdgeDetectingSurfaceController.Direction.LEFT_TO_RIGHT;
        break;
      case 180:
        localDirection = EdgeDetectingSurfaceController.Direction.TOP_TO_BOTTOM;
        break;
      case 90:
        localDirection = EdgeDetectingSurfaceController.Direction.RIGHT_TO_LEFT;
      }
    }
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    if (this.animating)
    {
      paramAccessibilityEvent.getText().add(getContext().getString(R.string.processing));
      return true;
    }
    return false;
  }

  public void displaySaveForLaterButton(final View.OnClickListener paramOnClickListener)
  {
    this.progressMessage.setVisibility(4);
    this.saveForLaterButton.setVisibility(0);
    this.saveForLaterButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        paramOnClickListener.onClick(paramAnonymousView);
        ProgressView.this.cancelButton.performClick();
      }
    });
    this.progressArea.invalidate();
  }

  public void hideSaveForLaterButton()
  {
    this.progressMessage.setVisibility(0);
    this.saveForLaterButton.setVisibility(8);
    this.progressArea.invalidate();
  }

  public void recycle()
  {
    this.edgeSurfaceController.recycle();
  }

  public void setBackgroundPicture(Picture paramPicture)
  {
    stopAnimation();
    if ((paramPicture == null) || (paramPicture.peekBitmap() == null))
    {
      this.edgeSurfaceController.clearBackground();
      if (this.layout.indexOfChild(this.edgeSurfaceView) != -1)
        this.layout.removeView(this.edgeSurfaceView);
      return;
    }
    this.edgeSurfaceController.setBackground(paramPicture.getCroppedPicture());
    if (this.layout.indexOfChild(this.edgeSurfaceView) == -1)
      this.layout.addView(this.edgeSurfaceView, this.edgeSurfaceViewIndex);
    this.edgeSurfaceController.render();
  }

  public void setCancelClickListener(View.OnClickListener paramOnClickListener)
  {
    ((RotatingButton)this.layout.findViewById(R.id.cancel_query_button)).setOnClickListener(paramOnClickListener);
  }

  public void setRotatingWidgetsLocked(boolean paramBoolean)
  {
    this.rotatingLayout.setLocked(paramBoolean);
  }

  public void showMessageArea(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      Animation localAnimation = AnimationUtils.loadAnimation(this.context, 17432576);
      this.progressArea.startAnimation(localAnimation);
    }
    LinearLayout localLinearLayout = this.progressArea;
    if (paramBoolean);
    for (int i = 0; ; i = 4)
    {
      localLinearLayout.setVisibility(i);
      return;
    }
  }

  public void startAnimation()
  {
    this.logger.i("startAnimation", new Object[0]);
    if (!this.edgeSurfaceController.generateOverlayInBackground())
      this.logger.e("Unable to generate overlay. Bailing startAnimation()", new Object[0]);
    while (true)
    {
      return;
      try
      {
        this.animationStopRequested = false;
        boolean bool = this.animating;
        int i = 0;
        if (!bool)
        {
          this.animating = true;
          i = 1;
        }
        if (i == 0)
          continue;
        this.edgeSurfaceController.setAnimationStartTime();
        animateView();
        sendAccessibilityEvent(8);
        return;
      }
      finally
      {
      }
    }
  }

  public void stopAnimation()
  {
    try
    {
      this.logger.i("stopAnimation", new Object[0]);
      this.animationStopRequested = true;
      this.edgeSurfaceController.clearAnimationStartTime();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ProgressView
 * JD-Core Version:    0.6.2
 */