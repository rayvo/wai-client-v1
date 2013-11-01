package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import com.google.android.apps.unveil.R.styleable;

public class ToggleImageButton extends ImageButton
{
  private StateListener listener;
  private Drawable offDrawable;
  private boolean on = false;
  private Drawable onDrawable;

  public ToggleImageButton(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext, paramAttributeSet);
  }

  public ToggleImageButton(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext, paramAttributeSet);
  }

  private void init(Context paramContext, AttributeSet paramAttributeSet)
  {
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ToggleImageButton, 0, 0);
    this.offDrawable = localTypedArray.getDrawable(0);
    this.onDrawable = localTypedArray.getDrawable(1);
    localTypedArray.recycle();
    updateDisplay();
    setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ToggleImageButton localToggleImageButton = ToggleImageButton.this;
        if (!ToggleImageButton.this.on);
        for (boolean bool = true; ; bool = false)
        {
          ToggleImageButton.access$002(localToggleImageButton, bool);
          ToggleImageButton.this.updateDisplay();
          if (ToggleImageButton.this.listener != null)
            ToggleImageButton.this.listener.onStateChanged(ToggleImageButton.this.on);
          return;
        }
      }
    });
  }

  private void updateDisplay()
  {
    if (this.on);
    for (Drawable localDrawable = this.onDrawable; ; localDrawable = this.offDrawable)
    {
      setImageDrawable(localDrawable);
      return;
    }
  }

  public boolean isOn()
  {
    return this.on;
  }

  public void setOn(boolean paramBoolean)
  {
    this.on = paramBoolean;
    updateDisplay();
  }

  public void setToggleListener(StateListener paramStateListener)
  {
    this.listener = paramStateListener;
  }

  public static abstract interface StateListener
  {
    public abstract void onStateChanged(boolean paramBoolean);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ToggleImageButton
 * JD-Core Version:    0.6.2
 */