package com.google.android.apps.unveil.protocol.nonstop;

import android.graphics.Canvas;

public abstract interface DebugDrawer
{
  public static final DebugDrawer NO_OP = new DebugDrawer()
  {
    public void draw(Canvas paramAnonymousCanvas, int paramAnonymousInt, float paramAnonymousFloat)
    {
    }
  };

  public abstract void draw(Canvas paramCanvas, int paramInt, float paramFloat);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.DebugDrawer
 * JD-Core Version:    0.6.2
 */