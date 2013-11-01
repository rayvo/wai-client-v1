package com.google.android.apps.unveil.history;

import java.io.Serializable;

public class SearchHistoryItem extends ItemModel
  implements Serializable
{
  private final boolean isSearchable;
  private final boolean isShared;
  private final Double lat;
  private final Double lng;
  private final String locationDescription;
  private final String momentId;
  private final String note;
  private final String thumbnailUrl;
  private final long timestamp;
  private final String title;
  private final String url;

  public SearchHistoryItem(String paramString1, String paramString2, String paramString3, long paramLong, String paramString4, String paramString5, boolean paramBoolean1, boolean paramBoolean2, String paramString6, Double paramDouble1, Double paramDouble2)
  {
    this.momentId = paramString1;
    this.title = paramString2;
    this.url = paramString3;
    this.timestamp = paramLong;
    this.thumbnailUrl = paramString4;
    this.note = paramString5;
    this.isShared = paramBoolean1;
    this.isSearchable = paramBoolean2;
    this.locationDescription = paramString6;
    this.lat = paramDouble1;
    this.lng = paramDouble2;
  }

  private int degToE6(double paramDouble)
  {
    return (int)(1000000.0D * paramDouble);
  }

  public UnveilGeoPoint getGeoPoint()
  {
    if ((this.lat == null) || (this.lng == null))
      return null;
    return new UnveilGeoPoint(degToE6(this.lat.doubleValue()), degToE6(this.lng.doubleValue()));
  }

  public Double getLat()
  {
    return this.lat;
  }

  public Double getLng()
  {
    return this.lng;
  }

  public String getLocation()
  {
    return this.locationDescription;
  }

  public String getMomentId()
  {
    return this.momentId;
  }

  public String getMomentUrl()
  {
    return this.url;
  }

  public String getNote()
  {
    return this.note;
  }

  public String getThumbnailUrl()
  {
    return this.thumbnailUrl;
  }

  public long getTimestamp()
  {
    return this.timestamp;
  }

  public String getTitle()
  {
    return this.title;
  }

  public boolean isSearchable()
  {
    return this.isSearchable;
  }

  public boolean isShared()
  {
    return this.isShared;
  }

  public static class UnveilGeoPoint
  {
    private final int latitudeE6;
    private final int longitudeE6;

    public UnveilGeoPoint(int paramInt1, int paramInt2)
    {
      this.latitudeE6 = paramInt1;
      this.longitudeE6 = paramInt2;
    }

    public int getLatitudeE6()
    {
      return this.latitudeE6;
    }

    public int getLongitudeE6()
    {
      return this.longitudeE6;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SearchHistoryItem
 * JD-Core Version:    0.6.2
 */