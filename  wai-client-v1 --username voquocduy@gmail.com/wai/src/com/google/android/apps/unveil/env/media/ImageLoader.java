package com.google.android.apps.unveil.env.media;

import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ImageLoader
{
  private final ImageSource imageSource;
  private final List<Policy<Image>> policies;

  public ImageLoader(ImageSource paramImageSource, List<Policy<Image>> paramList)
  {
    this.imageSource = paramImageSource;
    this.policies = paramList;
  }

  public List<Image> getImages()
  {
    List localList = this.imageSource.getImages();
    Iterator localIterator = this.policies.iterator();
    while (localIterator.hasNext())
      localList = ((Policy)localIterator.next()).apply(localList);
    return localList;
  }

  public static class DescriptionExclusionPolicy
    implements ImageLoader.Policy<ImageLoader.Image>
  {
    private final String descriptionToIgnore;

    public DescriptionExclusionPolicy(String paramString)
    {
      this.descriptionToIgnore = paramString;
    }

    public List<ImageLoader.Image> apply(List<ImageLoader.Image> paramList)
    {
      ArrayList localArrayList = new ArrayList(paramList.size());
      Iterator localIterator = paramList.iterator();
      while (localIterator.hasNext())
      {
        ImageLoader.Image localImage = (ImageLoader.Image)localIterator.next();
        String str = localImage.description;
        if ((TextUtils.isEmpty(str)) || (!str.equals(this.descriptionToIgnore)))
          localArrayList.add(localImage);
      }
      return localArrayList;
    }
  }

  public static class Image
    implements Comparable<Image>
  {
    public final long dateTaken;
    public final String description;
    public final Uri imageUri;
    public final double latitude;
    public final double longitude;
    public final int orientation;
    public final String path;

    public Image(Uri paramUri, String paramString1, long paramLong, int paramInt, double paramDouble1, double paramDouble2, String paramString2)
    {
      this.imageUri = paramUri;
      this.description = paramString1;
      this.dateTaken = paramLong;
      this.orientation = paramInt;
      this.latitude = paramDouble1;
      this.longitude = paramDouble2;
      this.path = paramString2;
    }

    public int compareTo(Image paramImage)
    {
      if (paramImage == null);
      while (this.dateTaken > paramImage.dateTaken)
        return 1;
      if (this.dateTaken == paramImage.dateTaken)
        return 0;
      return -1;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Image [imageUri=").append(this.imageUri).append(", description=").append(this.description).append(", dateTaken=").append(this.dateTaken).append(", orientation=").append(this.orientation).append("]");
      return localStringBuilder.toString();
    }
  }

  public static abstract interface ImageSource
  {
    public abstract List<ImageLoader.Image> getImages();
  }

  public static abstract interface Policy<E>
  {
    public abstract List<E> apply(List<E> paramList);
  }

  public static class ThrottlingPolicy<E>
    implements ImageLoader.Policy<E>
  {
    private final int limit;

    public ThrottlingPolicy(int paramInt)
    {
      this.limit = paramInt;
    }

    public List<E> apply(List<E> paramList)
    {
      if (paramList.size() > this.limit)
        paramList = paramList.subList(0, this.limit);
      return paramList;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.media.ImageLoader
 * JD-Core Version:    0.6.2
 */