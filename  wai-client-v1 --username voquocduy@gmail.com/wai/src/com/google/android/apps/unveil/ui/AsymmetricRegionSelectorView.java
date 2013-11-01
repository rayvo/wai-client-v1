package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;

public class AsymmetricRegionSelectorView extends RegionSelectorView
{
  private final UnveilLogger logger = new UnveilLogger();
  private Rect maxExtents;

  public AsymmetricRegionSelectorView(Context paramContext)
  {
    super(paramContext);
  }

  public AsymmetricRegionSelectorView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  private void limitRegion()
  {
    if ((this.region != null) && (this.maxExtents != null))
    {
      this.region.left = Math.max(this.region.left, this.maxExtents.left);
      this.region.top = Math.max(this.region.top, this.maxExtents.top);
      this.region.bottom = Math.min(this.region.bottom, this.maxExtents.bottom);
      this.region.right = Math.min(this.region.right, this.maxExtents.right);
    }
  }

  private static Rect scaleRect(Rect paramRect, Size paramSize1, Size paramSize2)
  {
    paramRect.left = (paramRect.left * paramSize2.width / paramSize1.width);
    paramRect.right = (paramRect.right * paramSize2.width / paramSize1.width);
    paramRect.top = (paramRect.top * paramSize2.height / paramSize1.height);
    paramRect.bottom = (paramRect.bottom * paramSize2.height / paramSize1.height);
    return paramRect;
  }

  public Rect getRegionInImageCoordinates(Size paramSize)
  {
    Rect localRect = new Rect(this.region);
    if (this.maxExtents != null)
    {
      localRect.left -= this.maxExtents.left;
      localRect.right -= this.maxExtents.left;
      localRect.top -= this.maxExtents.top;
      localRect.bottom -= this.maxExtents.top;
      return scaleRect(localRect, new Size(this.maxExtents.width(), this.maxExtents.height()), paramSize);
    }
    return scaleRect(localRect, new Size(getWidth(), getHeight()), paramSize);
  }

  protected boolean handleTouchAt(float paramFloat1, float paramFloat2)
  {
    updateResizeState(paramFloat1, paramFloat2);
    int i = (int)paramFloat1;
    int j = (int)paramFloat2;
    Rect localRect = new Rect(this.region);
    if (this.currentResizeState.resizeLeft)
      localRect.left = (i - this.region.left + this.offsetLeft + localRect.left);
    if (this.currentResizeState.resizeRight)
      localRect.right = (i - this.region.right + this.offsetRight + localRect.right);
    if (this.currentResizeState.resizeTop)
      localRect.top = (j - this.region.top + this.offsetTop + localRect.top);
    if (this.currentResizeState.resizeBottom)
      localRect.bottom = (j - this.region.bottom + this.offsetBottom + localRect.bottom);
    int k;
    int m;
    label194: int n;
    label204: int i1;
    label214: int i4;
    label360: int i5;
    if (this.maxExtents == null)
    {
      k = getWidth();
      if (this.maxExtents != null)
        break label420;
      m = getHeight();
      if (this.maxExtents != null)
        break label432;
      n = 0;
      if (this.maxExtents != null)
        break label444;
      i1 = 0;
      if (localRect.top < i1 + 4)
        localRect.top = (i1 + 4);
      if (localRect.left < n + 4)
        localRect.left = (n + 4);
      int i2 = n + k - localRect.right;
      int i3 = i1 + m - localRect.bottom;
      if (i2 < 4)
        localRect.right = (-4 + (n + k));
      if (i3 < 4)
        localRect.bottom = (-4 + (i1 + m));
      i4 = (int)(30.0F * this.density);
      if (localRect.height() < i4)
      {
        if (i3 <= i4)
          break label456;
        localRect.bottom = (i4 + localRect.top);
      }
      i5 = (int)(30.0F * this.density);
      if (localRect.width() < i5)
      {
        if (i2 <= i5)
          break label472;
        localRect.right = (i5 + localRect.left);
      }
    }
    while (true)
    {
      this.region = localRect;
      return true;
      k = this.maxExtents.width();
      break;
      label420: m = this.maxExtents.height();
      break label194;
      label432: n = this.maxExtents.left;
      break label204;
      label444: i1 = this.maxExtents.top;
      break label214;
      label456: localRect.top = (localRect.bottom - i4);
      break label360;
      label472: localRect.left = (localRect.right - i5);
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (this.region == null)
    {
      super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
      return;
    }
    super.onLayout(paramBoolean, this.region.left, this.region.top, this.region.right, this.region.bottom);
  }

  public void setMaxExtents(Rect paramRect)
  {
    if ((this.maxExtents != null) && (!paramRect.equals(this.maxExtents)))
    {
      Rect localRect1 = this.region;
      localRect1.left -= this.maxExtents.left;
      Rect localRect2 = this.region;
      localRect2.right -= this.maxExtents.left;
      Rect localRect3 = this.region;
      localRect3.top -= this.maxExtents.top;
      Rect localRect4 = this.region;
      localRect4.bottom -= this.maxExtents.top;
      this.region = scaleRect(this.region, new Size(this.maxExtents.width(), this.maxExtents.height()), new Size(paramRect.width(), paramRect.height()));
      Rect localRect5 = this.region;
      localRect5.left += paramRect.left;
      Rect localRect6 = this.region;
      localRect6.right += paramRect.left;
      Rect localRect7 = this.region;
      localRect7.top += paramRect.top;
      Rect localRect8 = this.region;
      localRect8.bottom += paramRect.top;
    }
    this.maxExtents = paramRect;
    limitRegion();
  }

  protected void updateOffsetAndResizeState(boolean paramBoolean1, boolean paramBoolean2, float paramFloat1, float paramFloat2, int paramInt)
  {
    int i = (int)paramFloat1;
    int j = (int)paramFloat2;
    if (paramBoolean1)
    {
      if ((Math.abs(this.region.left - paramFloat1) < paramInt) && (!this.currentResizeState.resizeLeft))
      {
        this.currentResizeState.resizeLeft = true;
        this.offsetLeft = (this.region.left - i);
      }
      if ((Math.abs(this.region.right - paramFloat1) < paramInt) && (!this.currentResizeState.resizeRight))
      {
        this.currentResizeState.resizeRight = true;
        this.offsetRight = (this.region.right - i);
      }
    }
    if (paramBoolean2)
    {
      if ((Math.abs(this.region.top - paramFloat2) < paramInt) && (!this.currentResizeState.resizeTop))
      {
        this.currentResizeState.resizeTop = true;
        this.offsetTop = (this.region.top - j);
      }
      if ((Math.abs(this.region.bottom - paramFloat2) < paramInt) && (!this.currentResizeState.resizeBottom))
      {
        this.currentResizeState.resizeBottom = true;
        this.offsetBottom = (this.region.bottom - j);
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.AsymmetricRegionSelectorView
 * JD-Core Version:    0.6.2
 */