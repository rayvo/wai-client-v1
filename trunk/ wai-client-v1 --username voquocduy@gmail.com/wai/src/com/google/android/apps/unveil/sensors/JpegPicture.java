package com.google.android.apps.unveil.sensors;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.nio.ByteBuffer;

public class JpegPicture extends Picture
{
  private static final UnveilLogger logger = new UnveilLogger();
  private BitmapDrawable bitmapDrawableCache = null;
  private byte[] jpegDataCache;
  private Size sizeCache = null;
  private byte[] yuvDataCache;

  public JpegPicture(byte[] paramArrayOfByte, int paramInt)
  {
    super(paramInt);
    this.jpegDataCache = paramArrayOfByte;
  }

  public JpegPicture(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    this.jpegDataCache = paramArrayOfByte;
  }

  public int getByteSize()
  {
    try
    {
      checkNotRecycled();
      int i = this.jpegDataCache.length;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Picture getCroppedPicture()
  {
    while (true)
    {
      try
      {
        checkNotRecycled();
        Rect localRect = getCropArea();
        Object localObject2;
        if (localRect != null)
        {
          Size localSize = getSize();
          if ((localRect.width() != localSize.width) || (localRect.height() != localSize.height))
          {
            Bitmap localBitmap = ImageUtils.cropBitmap(peekBitmap(), localRect);
            if (localBitmap == null)
            {
              localObject2 = null;
              return localObject2;
            }
            localObject2 = new BitmapPicture(localBitmap, getOrientation());
            continue;
          }
        }
      }
      finally
      {
      }
      localObject2 = this;
    }
  }

  public BitmapDrawable getDrawable()
  {
    try
    {
      checkNotRecycled();
      if (this.bitmapDrawableCache == null)
      {
        Bitmap localBitmap = peekBitmap();
        if (localBitmap != null)
          this.bitmapDrawableCache = new BitmapDrawable(localBitmap);
      }
      BitmapDrawable localBitmapDrawable = this.bitmapDrawableCache;
      return localBitmapDrawable;
    }
    finally
    {
    }
  }

  public byte[] getJpegData()
  {
    try
    {
      checkNotRecycled();
      byte[] arrayOfByte = this.jpegDataCache;
      return arrayOfByte;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public Size getSize()
  {
    try
    {
      checkNotRecycled();
      if (this.sizeCache == null)
      {
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeByteArray(this.jpegDataCache, 0, this.jpegDataCache.length, localOptions);
        this.sizeCache = new Size(localOptions.outWidth, localOptions.outHeight);
      }
      Size localSize = this.sizeCache;
      return localSize;
    }
    finally
    {
    }
  }

  public byte[] getYuvData()
  {
    try
    {
      checkNotRecycled();
      if (this.yuvDataCache == null)
      {
        Size localSize = getSize();
        byte[] arrayOfByte2 = new byte[2 * (localSize.width * localSize.height)];
        ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte2);
        Bitmap localBitmap = peekBitmap();
        if (localBitmap != null)
          localBitmap.copyPixelsToBuffer(localByteBuffer);
        this.yuvDataCache = new byte[ImageUtils.getYUVByteSize(localSize.width, localSize.height)];
        ImageUtils.convertRGB565ToYUV420SP(arrayOfByte2, this.yuvDataCache, localSize.width, localSize.height);
      }
      byte[] arrayOfByte1 = this.yuvDataCache;
      return arrayOfByte1;
    }
    finally
    {
    }
  }

  public Bitmap peekBitmap()
  {
    try
    {
      checkNotRecycled();
      Object localObject2;
      if (this.bitmapDrawableCache != null)
      {
        Bitmap localBitmap2 = this.bitmapDrawableCache.getBitmap();
        localObject2 = localBitmap2;
      }
      while (true)
      {
        return localObject2;
        BitmapFactory.Options localOptions = new BitmapFactory.Options();
        localOptions.inPurgeable = true;
        localOptions.inPreferredConfig = Bitmap.Config.RGB_565;
        localObject2 = BitmapFactory.decodeByteArray(this.jpegDataCache, 0, this.jpegDataCache.length, localOptions);
        if (localObject2 == null)
        {
          logger.e("Critical failure when decoding JPEG", new Object[0]);
          localObject2 = null;
        }
        else
        {
          if (((Bitmap)localObject2).getConfig() != Bitmap.Config.RGB_565)
          {
            Bitmap localBitmap1 = ((Bitmap)localObject2).copy(Bitmap.Config.RGB_565, false);
            ((Bitmap)localObject2).recycle();
            localObject2 = localBitmap1;
          }
          if (this.sizeCache == null)
            this.sizeCache = new Size(((Bitmap)localObject2).getWidth(), ((Bitmap)localObject2).getHeight());
        }
      }
    }
    finally
    {
    }
  }

  public void recycle()
  {
    try
    {
      if (isRecycled())
        new UnveilLogger().w("Requested recycling, but jpeg picture is already recycled.", new Object[0]);
      while (true)
      {
        return;
        super.recycle();
        if (this.bitmapDrawableCache != null)
          this.bitmapDrawableCache.getBitmap().recycle();
        this.jpegDataCache = null;
        this.yuvDataCache = null;
        this.bitmapDrawableCache = null;
        this.sizeCache = null;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.JpegPicture
 * JD-Core Version:    0.6.2
 */