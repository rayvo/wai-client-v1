package com.google.android.apps.unveil.env;

import android.graphics.Bitmap;
import android.hardware.Camera.Size;
import android.text.TextUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Size
  implements Comparable<Size>, Serializable
{
  public static final long serialVersionUID = 7689808733290872361L;
  public final int height;
  public final int width;

  public Size(int paramInt1, int paramInt2)
  {
    this.width = paramInt1;
    this.height = paramInt2;
  }

  public Size(Bitmap paramBitmap)
  {
    this.width = paramBitmap.getWidth();
    this.height = paramBitmap.getHeight();
  }

  public Size(Camera.Size paramSize)
  {
    this.width = paramSize.width;
    this.height = paramSize.height;
  }

  public static final String dimensionsAsString(int paramInt1, int paramInt2)
  {
    return paramInt1 + "x" + paramInt2;
  }

  public static Size getRotatedSize(Size paramSize, int paramInt)
  {
    if (paramInt % 180 != 0)
      paramSize = new Size(paramSize.height, paramSize.width);
    return paramSize;
  }

  public static Size parseFromString(String paramString)
  {
    if (TextUtils.isEmpty(paramString));
    String[] arrayOfString;
    do
    {
      return null;
      arrayOfString = paramString.trim().split("x");
    }
    while (arrayOfString.length != 2);
    try
    {
      Size localSize = new Size(Integer.parseInt(arrayOfString[0]), Integer.parseInt(arrayOfString[1]));
      return localSize;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return null;
  }

  public static String sizeListToString(List<Size> paramList)
  {
    String str = "";
    if ((paramList != null) && (paramList.size() > 0))
    {
      str = ((Size)paramList.get(0)).toString();
      for (int i = 1; i < paramList.size(); i++)
        str = str + "," + ((Size)paramList.get(i)).toString();
    }
    return str;
  }

  public static List<Size> sizeStringToList(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramString != null)
    {
      String[] arrayOfString = paramString.split(",");
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        Size localSize = parseFromString(arrayOfString[j]);
        if (localSize != null)
          localArrayList.add(localSize);
      }
    }
    return localArrayList;
  }

  public final float aspectRatio()
  {
    return this.width / this.height;
  }

  public int compareTo(Size paramSize)
  {
    return this.width * this.height - paramSize.width * paramSize.height;
  }

  public boolean equals(Object paramObject)
  {
    if (paramObject == null);
    Size localSize;
    do
    {
      do
        return false;
      while (!(paramObject instanceof Size));
      localSize = (Size)paramObject;
    }
    while ((this.width != localSize.width) || (this.height != localSize.height));
    return true;
  }

  public int hashCode()
  {
    return 32713 * this.width + this.height;
  }

  public String toString()
  {
    return dimensionsAsString(this.width, this.height);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Size
 * JD-Core Version:    0.6.2
 */