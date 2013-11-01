package com.google.android.apps.unveil.ui.result;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import com.google.android.apps.unveil.R.drawable;

class BoundingBoxView extends View
{
  private static final int[] BOUNDING_BOX_COLORS;
  private static final int[] SELECTED_BOUNDING_BOX_COLORS = arrayOfInt2;
  private static final int UNSELECTED_BBOX_ALPHA = 100;
  private boolean faded;
  private boolean highlighted;
  private final boolean isAdvertisement;
  private final boolean isUserGenerated;
  private int resultIndex;

  static
  {
    int[] arrayOfInt1 = new int[3];
    arrayOfInt1[0] = R.drawable.bbox_green;
    arrayOfInt1[1] = R.drawable.bbox_blue;
    arrayOfInt1[2] = R.drawable.bbox_red;
    BOUNDING_BOX_COLORS = arrayOfInt1;
    int[] arrayOfInt2 = new int[3];
    arrayOfInt2[0] = R.drawable.bbox_green_selected;
    arrayOfInt2[1] = R.drawable.bbox_blue_selected;
    arrayOfInt2[2] = R.drawable.bbox_red_selected;
  }

  public BoundingBoxView(Context paramContext, boolean paramBoolean1, boolean paramBoolean2, int paramInt)
  {
    super(paramContext);
    this.isAdvertisement = paramBoolean1;
    this.isUserGenerated = paramBoolean2;
    this.resultIndex = paramInt;
    updateBackground();
  }

  private void setHighlightedBackground()
  {
    if ((this.isAdvertisement) || (this.isUserGenerated))
    {
      setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bbox_yellow_selected));
      return;
    }
    setBackgroundDrawable(getContext().getResources().getDrawable(SELECTED_BOUNDING_BOX_COLORS[(this.resultIndex % SELECTED_BOUNDING_BOX_COLORS.length)]));
  }

  private void setUnhighlightedBackground()
  {
    if ((this.isAdvertisement) || (this.isUserGenerated))
    {
      setBackgroundDrawable(getContext().getResources().getDrawable(R.drawable.bbox_yellow));
      return;
    }
    setBackgroundDrawable(getContext().getResources().getDrawable(BOUNDING_BOX_COLORS[(this.resultIndex % BOUNDING_BOX_COLORS.length)]));
  }

  private void updateBackground()
  {
    if (this.highlighted)
      setHighlightedBackground();
    while (this.faded)
    {
      getBackground().setAlpha(100);
      return;
      setUnhighlightedBackground();
    }
    getBackground().setAlpha(255);
  }

  public boolean isFaded()
  {
    return this.faded;
  }

  public boolean isHighlited()
  {
    return this.highlighted;
  }

  public void setFaded(boolean paramBoolean)
  {
    this.faded = paramBoolean;
    updateBackground();
  }

  public void setHighlighted(boolean paramBoolean)
  {
    if (this.highlighted == paramBoolean)
      return;
    this.highlighted = paramBoolean;
    updateBackground();
  }

  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("Bounding Box at ").append(getLeft()).append(", ").append(getTop()).append(" of size ").append(getWidth()).append("w x ").append(getHeight()).append("h").append(" (");
    String str1;
    StringBuilder localStringBuilder2;
    if (this.highlighted)
    {
      str1 = "highlit";
      localStringBuilder2 = localStringBuilder1.append(str1).append(",");
      if (!this.faded)
        break label119;
    }
    label119: for (String str2 = "faded"; ; str2 = "unfaded")
    {
      return str2 + ")";
      str1 = "unhighlight";
      break;
    }
  }

  public void updateIndex(int paramInt)
  {
    this.resultIndex = paramInt;
    updateBackground();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.BoundingBoxView
 * JD-Core Version:    0.6.2
 */