package com.google.android.apps.unveil.history;

import com.google.goggles.BoundingBoxProtos.BoundingBox;

public class SearchHistoryPuggleItem extends SearchHistoryItem
{
  private final BoundingBoxProtos.BoundingBox box;
  private final String textRestrict;

  public SearchHistoryPuggleItem(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2, String paramString6, Double paramDouble1, Double paramDouble2, String paramString7, BoundingBoxProtos.BoundingBox paramBoundingBox)
  {
    super(paramString1, paramString2, paramString3, paramLong, paramString4, paramString5, paramBoolean1, paramBoolean2, paramString6, paramDouble1, paramDouble2);
    this.textRestrict = paramString7;
    this.box = paramBoundingBox;
  }

  public BoundingBoxProtos.BoundingBox getBox()
  {
    return this.box;
  }

  public String getTitle()
  {
    return this.textRestrict;
  }

  public boolean hasBox()
  {
    return this.box != null;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryPuggleItem
 * JD-Core Version:    0.6.2
 */