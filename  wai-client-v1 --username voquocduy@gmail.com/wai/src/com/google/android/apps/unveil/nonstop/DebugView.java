package com.google.android.apps.unveil.nonstop;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Iterator;

public class DebugView extends View
{
  private PreviewLooper callback;
  private final UnveilLogger logger = new UnveilLogger();
  private final ArrayList<RenderCallback> renderCallbacks = new ArrayList();

  public DebugView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void changeVisibility(final boolean paramBoolean)
  {
    post(new Runnable()
    {
      public void run()
      {
        DebugView localDebugView = DebugView.this;
        if (paramBoolean);
        for (int i = 0; ; i = 8)
        {
          localDebugView.setVisibility(i);
          return;
        }
      }
    });
  }

  public void addCallback(RenderCallback paramRenderCallback)
  {
    this.renderCallbacks.add(paramRenderCallback);
    changeVisibility(true);
  }

  public void cycleDebugMode(boolean paramBoolean)
  {
    this.logger.i("Toggling debug rendering.", new Object[0]);
    int i;
    if (getVisibility() == 0)
    {
      i = 1;
      if (((this.callback == null) || (!this.callback.changeMode(paramBoolean))) && (this.renderCallbacks.size() <= 0))
        break label76;
    }
    label76: for (boolean bool = true; ; bool = false)
    {
      if (i != bool)
      {
        changeVisibility(bool);
        requestLayout();
      }
      postInvalidate();
      return;
      i = 0;
      break;
    }
  }

  public void onDraw(Canvas paramCanvas)
  {
    try
    {
      Iterator localIterator = this.renderCallbacks.iterator();
      while (localIterator.hasNext())
        ((RenderCallback)localIterator.next()).draw(paramCanvas);
    }
    finally
    {
    }
    if (this.callback != null)
      this.callback.drawDebug(paramCanvas);
  }

  public void setCallback(PreviewLooper paramPreviewLooper)
  {
    try
    {
      this.callback = paramPreviewLooper;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static abstract interface RenderCallback
  {
    public abstract void draw(Canvas paramCanvas);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.DebugView
 * JD-Core Version:    0.6.2
 */