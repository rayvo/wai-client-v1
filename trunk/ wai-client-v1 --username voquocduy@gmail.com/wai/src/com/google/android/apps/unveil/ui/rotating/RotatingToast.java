package com.google.android.apps.unveil.ui.rotating;

import android.app.Activity;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingToast
{
  private static final UnveilLogger logger = new UnveilLogger();
  private static boolean toastVisible = false;
  private final int FADE_OUT_TIME_MS = 1000;
  private final RelativeLayout layout;
  private final ViewGroup parent;
  private final int showTime;
  private final RotatingTextView textView;

  private RotatingToast(Activity paramActivity, ViewGroup paramViewGroup, String paramString, int paramInt)
  {
    this.layout = ((RelativeLayout)LayoutInflater.from(paramActivity).inflate(R.layout.toast, null));
    this.textView = ((RotatingTextView)this.layout.findViewById(R.id.toast_text));
    this.textView.setText(paramString);
    ((RotatingLayout)this.layout.findViewById(R.id.toast_rotating_layout)).initializeChildren();
    this.parent = paramViewGroup;
    this.showTime = paramInt;
  }

  public static RotatingToast makeText(Activity paramActivity, ViewGroup paramViewGroup, CharSequence paramCharSequence, int paramInt)
  {
    if (paramInt == 1);
    for (int i = 3500; ; i = 2000)
      return new RotatingToast(paramActivity, paramViewGroup, paramCharSequence.toString(), i);
  }

  private void scheduleFadeOut()
  {
    AlphaAnimation localAlphaAnimation = new AlphaAnimation(1.0F, 0.0F);
    localAlphaAnimation.setDuration(1000L);
    localAlphaAnimation.setRepeatCount(0);
    AnimationSet localAnimationSet = new AnimationSet(true);
    localAnimationSet.addAnimation(localAlphaAnimation);
    localAnimationSet.setStartTime(AnimationUtils.currentAnimationTimeMillis() + this.showTime);
    LayoutAnimationController localLayoutAnimationController = new LayoutAnimationController(localAnimationSet, 0.0F);
    this.layout.setLayoutAnimation(localLayoutAnimationController);
    scheduleRemoval();
  }

  private void scheduleRemoval()
  {
    new Handler().postDelayed(new Runnable()
    {
      public void run()
      {
        RotatingToast.access$002(false);
        RotatingToast.this.parent.removeView(RotatingToast.this.layout);
      }
    }
    , -100 + (1000 + this.showTime));
  }

  public void show()
  {
    if (!toastVisible)
    {
      toastVisible = true;
      this.parent.addView(this.layout);
      scheduleFadeOut();
      this.layout.requestLayout();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingToast
 * JD-Core Version:    0.6.2
 */