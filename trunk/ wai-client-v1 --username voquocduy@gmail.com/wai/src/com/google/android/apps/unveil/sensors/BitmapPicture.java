package com.google.android.apps.unveil.sensors;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.nio.ByteBuffer;

public class BitmapPicture extends Picture
{
  private static final UnveilLogger logger = new UnveilLogger();
  private Bitmap cachedBitmap;
  private volatile boolean cachedBitmapReady;
  private BitmapDrawable cachedDrawable;
  private byte[] cachedJpegData;
  private byte[] cachedYuvData;
  private final Size size;

  public BitmapPicture(Bitmap paramBitmap, int paramInt)
  {
    this(paramBitmap, paramInt, -1);
  }

  public BitmapPicture(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    super(paramInt1, paramInt2);
    if (paramBitmap.getConfig() != Bitmap.Config.RGB_565)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = paramBitmap.getConfig();
      localUnveilLogger.w("Bitmap was %s instead of the required RGB_565!", arrayOfObject);
    }
    this.size = new Size(paramBitmap.getWidth(), paramBitmap.getHeight());
    this.cachedBitmap = paramBitmap;
  }

  public BitmapPicture(byte[] paramArrayOfByte, final int paramInt1, final int paramInt2, int paramInt3)
  {
    super(paramInt3);
    this.size = new Size(paramInt1, paramInt2);
    this.cachedYuvData = ((byte[])paramArrayOfByte.clone());
    Thread localThread = new Thread(new Runnable()
    {
      public void run()
      {
        BitmapPicture localBitmapPicture = BitmapPicture.this;
        try
        {
          byte[] arrayOfByte = new byte[2 * (paramInt1 * paramInt2)];
          if (BitmapPicture.this.cachedYuvData != null)
          {
            ImageUtils.convertYUV420SPToRGB565(BitmapPicture.this.cachedYuvData, arrayOfByte, paramInt1, paramInt2);
            ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte);
            BitmapPicture.access$102(BitmapPicture.this, Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.RGB_565));
            BitmapPicture.this.cachedBitmap.copyPixelsFromBuffer(localByteBuffer);
          }
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
        }
        finally
        {
          BitmapPicture.access$302(BitmapPicture.this, true);
          BitmapPicture.this.notify();
        }
      }
    });
    localThread.setName("BitmapPicture processing thread to convert YUV420 to RGB565.");
    localThread.start();
  }

  public int getByteSize()
  {
    checkNotRecycled();
    return 4 * (this.size.width * this.size.height);
  }

  public Picture getCroppedPicture()
  {
    try
    {
      Rect localRect = getCropArea();
      if (localRect != null)
      {
        logger.e("Getting cropped picture!", new Object[0]);
        Size localSize = getSize();
        if ((localRect.width() == localSize.width) && (localRect.height() == localSize.height));
      }
      for (BitmapPicture localBitmapPicture = new BitmapPicture(Bitmap.createBitmap(peekBitmap(), localRect.left, localRect.top, localRect.width(), localRect.height()), getOrientation()); ; localBitmapPicture = this)
        return localBitmapPicture;
    }
    finally
    {
    }
  }

  public BitmapDrawable getDrawable()
  {
    try
    {
      checkNotRecycled();
      if (this.cachedDrawable == null)
        this.cachedDrawable = new BitmapDrawable(peekBitmap());
      BitmapDrawable localBitmapDrawable = this.cachedDrawable;
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
      int i;
      if (this.cachedJpegData == null)
      {
        i = ImageUtils.getJpegQualityRecommendation(this.size);
        if (this.cachedYuvData == null)
          break label63;
      }
      label63: for (this.cachedJpegData = ImageUtils.encodeJpegFromYUV420SP(this.cachedYuvData, this.size.width, this.size.height, i); ; this.cachedJpegData = ImageUtils.compressBitmap(peekBitmap(), i))
      {
        byte[] arrayOfByte = this.cachedJpegData;
        return arrayOfByte;
      }
    }
    finally
    {
    }
  }

  public Size getSize()
  {
    try
    {
      checkNotRecycled();
      Size localSize = this.size;
      return localSize;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public byte[] getYuvData()
  {
    try
    {
      checkNotRecycled();
      if (this.cachedYuvData == null)
      {
        this.cachedYuvData = new byte[ImageUtils.getYUVByteSize(this.size.width, this.size.height)];
        Bitmap localBitmap = peekBitmap();
        byte[] arrayOfByte2 = new byte[2 * (this.size.width * this.size.height)];
        localBitmap.copyPixelsToBuffer(ByteBuffer.wrap(arrayOfByte2));
        ImageUtils.convertRGB565ToYUV420SP(arrayOfByte2, this.cachedYuvData, this.size.width, this.size.height);
      }
      byte[] arrayOfByte1 = this.cachedYuvData;
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
      while (!this.cachedBitmapReady)
      {
        Bitmap localBitmap2 = this.cachedBitmap;
        if (localBitmap2 != null)
          break;
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          logger.e(localInterruptedException, "Exception!", new Object[0]);
        }
      }
    }
    finally
    {
    }
    Bitmap localBitmap1 = this.cachedBitmap;
    return localBitmap1;
  }

  public void recycle()
  {
    try
    {
      if (isRecycled())
        logger.w("Requested recycling, but bitmap picture is already recycled.", new Object[0]);
      while (true)
      {
        return;
        super.recycle();
        if (this.cachedBitmap != null)
          this.cachedBitmap.recycle();
        if (this.cachedDrawable != null)
          this.cachedDrawable.getBitmap().recycle();
        this.cachedYuvData = null;
        this.cachedBitmap = null;
        this.cachedDrawable = null;
        this.cachedJpegData = null;
      }
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.BitmapPicture
 * JD-Core Version:    0.6.2
 */