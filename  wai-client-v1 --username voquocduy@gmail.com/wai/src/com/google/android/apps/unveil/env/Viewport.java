package com.google.android.apps.unveil.env;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.view.Display;
import android.view.WindowManager;

public class Viewport
  implements Parcelable
{
  public static final Parcelable.Creator<Viewport> CREATOR = new ParcelCreator(null);
  private static final UnveilLogger logger = new UnveilLogger();
  private Rect latestBarcodeQueryCrop;
  private int latestBarcodeQueryRotation;
  private int naturalOrientation = 0;
  private Size previewSize;

  public Viewport(int paramInt)
  {
    this.naturalOrientation = paramInt;
  }

  public static int computeNaturalOrientation(Context paramContext)
  {
    int i = 1;
    int j = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getOrientation();
    int k = paramContext.getResources().getConfiguration().orientation;
    UnveilLogger localUnveilLogger;
    Object[] arrayOfObject;
    String str1;
    if ((k != 2) && (k != 1))
    {
      logger.d("Current orientation is neither landscape nor portrait! Fallback as portrait", new Object[0]);
      localUnveilLogger = logger;
      arrayOfObject = new Object[3];
      if (k != 2)
        break label146;
      str1 = "landscape";
      label73: arrayOfObject[0] = str1;
      arrayOfObject[1] = Integer.valueOf(j * 90);
      if (i != 2)
        break label153;
    }
    label146: label153: for (String str2 = "landscape."; ; str2 = "portrait.")
    {
      arrayOfObject[2] = str2;
      localUnveilLogger.d("Current orientation %s, screen rotated %d counter-clockwisely ==> natural orientation %s", arrayOfObject);
      return i;
      if ((j == 1) || (j == 3))
      {
        if (k == 2);
        for (i = 1; ; i = 2)
          break;
      }
      i = k;
      break;
      str1 = "portrait";
      break label73;
    }
  }

  static Rect expandToSquare(Rect paramRect, Size paramSize)
  {
    if ((paramSize == null) || (paramRect == null))
      throw new IllegalArgumentException();
    Rect localRect = new Rect(paramRect);
    if (paramRect.width() > paramRect.height())
    {
      if (paramRect.centerY() > paramSize.height / 2)
      {
        localRect.top = (localRect.bottom - paramRect.width());
        return localRect;
      }
      localRect.bottom = (localRect.top + paramRect.width());
      return localRect;
    }
    if (paramRect.centerX() < paramSize.width / 2)
    {
      localRect.right = (localRect.left + paramRect.height());
      return localRect;
    }
    localRect.left = (localRect.right - paramRect.height());
    return localRect;
  }

  public int computeImageRotationDegree(Context paramContext, Picture paramPicture)
  {
    return computeRotationToLandscape(paramContext, paramPicture) - paramPicture.getOrientation();
  }

  public int computeRotationToLandscape(Context paramContext, Picture paramPicture)
  {
    int i = getNaturalOrientation(paramContext);
    int j;
    switch (((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getOrientation())
    {
    default:
      j = 0;
    case 2:
    case 3:
    case 1:
    }
    while (true)
    {
      if ((paramPicture.getSource() != 2) && (i == 1))
        j += 90;
      return j;
      j = -180;
      continue;
      j = -270;
      continue;
      j = -90;
    }
  }

  public int describeContents()
  {
    return 0;
  }

  public int deviceRotationToCameraRotation(int paramInt)
  {
    if (this.naturalOrientation == 1)
      paramInt = (paramInt + 90) % 360;
    return paramInt;
  }

  public Rect getLatestBarcodeQueryCrop()
  {
    return new Rect(this.latestBarcodeQueryCrop);
  }

  public int getLatestBarcodeQueryRotation()
  {
    return this.latestBarcodeQueryRotation;
  }

  public int getNaturalOrientation(Context paramContext)
  {
    return this.naturalOrientation;
  }

  Size getPreviewSize()
  {
    return this.previewSize;
  }

  public Size getRotatedBarcodeQueryCropSize(int paramInt)
  {
    if (this.latestBarcodeQueryCrop == null)
      return null;
    return Size.getRotatedSize(new Size(this.latestBarcodeQueryCrop.width(), this.latestBarcodeQueryCrop.height()), paramInt);
  }

  public Size getRotatedPreviewSize(int paramInt)
  {
    if (this.previewSize == null)
      return null;
    if (this.latestBarcodeQueryCrop != null)
      return getRotatedBarcodeQueryCropSize(paramInt);
    return Size.getRotatedSize(this.previewSize, paramInt);
  }

  public boolean isBarcodeBoxGood(Rect paramRect)
  {
    if (this.previewSize == null)
    {
      logger.w("Cannot determine barcode box goodness without a preview size.", new Object[0]);
      return false;
    }
    Rect localRect = expandToSquare(paramRect, this.previewSize);
    int i = localRect.width();
    localRect.left -= i;
    localRect.right = (i + localRect.right);
    localRect.top -= i;
    localRect.bottom = (i + localRect.bottom);
    return localRect.contains(this.previewSize.width / 2, this.previewSize.height / 2);
  }

  public Rect rotateAndScaleBarcodeBox(Rect paramRect, int paramInt, Size paramSize)
  {
    if (this.previewSize == null)
    {
      logger.w("Failed to rotate and scale bounding box %s because we don't have a preview size", new Object[] { paramRect });
      return paramRect;
    }
    Rect localRect = rotateBarcodeBox(paramRect, paramInt);
    if (this.latestBarcodeQueryCrop != null);
    for (int i = getRotatedBarcodeQueryCropSize(paramInt).width; ; i = getRotatedPreviewSize(paramInt).width)
    {
      float f = paramSize.width / i;
      localRect.left = ((int)(f * localRect.left));
      localRect.top = ((int)(f * localRect.top));
      localRect.right = ((int)(f * localRect.right));
      localRect.bottom = ((int)(f * localRect.bottom));
      return localRect;
    }
  }

  public Rect rotateBarcodeBox(Rect paramRect, int paramInt)
  {
    if (this.previewSize == null)
    {
      logger.e("Tried to rotate a box without setting the preview size. Bailing out on rotation.", new Object[0]);
      return paramRect;
    }
    int i = this.previewSize.width;
    int j = this.previewSize.height;
    Rect localRect = this.latestBarcodeQueryCrop;
    int k = 0;
    int m = 0;
    if (localRect != null)
    {
      m = this.latestBarcodeQueryCrop.top;
      k = this.latestBarcodeQueryCrop.left;
      i = this.latestBarcodeQueryCrop.width();
      j = this.latestBarcodeQueryCrop.height();
    }
    int n = paramRect.top - m;
    int i1 = paramRect.bottom - m;
    int i2 = paramRect.left - k;
    int i3 = paramRect.right - k;
    switch (paramInt)
    {
    default:
    case 180:
    case 90:
    case 270:
    }
    while (true)
    {
      return new Rect(i2, n, i3, i1);
      n = m + (j - paramRect.bottom);
      i1 = n + paramRect.height();
      i2 = k + (i - paramRect.right);
      i3 = i2 + paramRect.width();
      continue;
      n = paramRect.left - k;
      i1 = n + paramRect.width();
      i2 = m + (j - paramRect.top) - paramRect.height();
      i3 = i2 + paramRect.height();
      continue;
      i1 = k + (i - paramRect.left);
      n = i1 - paramRect.width();
      i2 = paramRect.top - m;
      i3 = i2 + paramRect.height();
    }
  }

  public void setLatestBarcodeQueryCrop(Rect paramRect)
  {
    if (paramRect == null);
    for (Rect localRect = null; ; localRect = new Rect(paramRect))
    {
      this.latestBarcodeQueryCrop = localRect;
      return;
    }
  }

  public void setLatestBarcodeQueryRotation(int paramInt)
  {
    this.latestBarcodeQueryRotation = paramInt;
  }

  public void setPreviewSize(Size paramSize)
  {
    this.previewSize = paramSize;
  }

  public void updateNaturalOrientation(int paramInt)
  {
    this.naturalOrientation = paramInt;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.naturalOrientation);
    paramParcel.writeParcelable(this.latestBarcodeQueryCrop, 0);
    paramParcel.writeInt(this.latestBarcodeQueryRotation);
    paramParcel.writeSerializable(this.previewSize);
  }

  private static class ParcelCreator
    implements Parcelable.Creator<Viewport>
  {
    public Viewport createFromParcel(Parcel paramParcel)
    {
      Viewport localViewport = new Viewport(paramParcel.readInt());
      localViewport.setLatestBarcodeQueryCrop((Rect)paramParcel.readParcelable(Rect.class.getClassLoader()));
      localViewport.setLatestBarcodeQueryRotation(paramParcel.readInt());
      localViewport.setPreviewSize((Size)paramParcel.readSerializable());
      return localViewport;
    }

    public Viewport[] newArray(int paramInt)
    {
      return new Viewport[paramInt];
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Viewport
 * JD-Core Version:    0.6.2
 */