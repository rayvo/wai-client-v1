package com.google.android.apps.unveil.env;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import com.google.android.apps.unveil.sensors.BitmapPicture;
import com.google.android.apps.unveil.sensors.JpegPicture;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class PictureFactory
{
  public static final int BYTES_PER_PIXEL = 4;
  private static final UnveilLogger logger = new UnveilLogger();

  private static void closeSafely(InputStream paramInputStream)
  {
    try
    {
      paramInputStream.close();
      return;
    }
    catch (IOException localIOException)
    {
      logger.e(localIOException, "Failed to close stream.", new Object[0]);
    }
  }

  private static void configureForDownsample(int paramInt, BitmapFactory.Options paramOptions)
  {
    int i = paramOptions.outWidth;
    int j = paramOptions.outHeight;
    int k = 1;
    while (4 * (i * j) > paramInt)
    {
      k *= 2;
      i /= 2;
      j /= 2;
    }
    paramOptions.inJustDecodeBounds = false;
    paramOptions.inSampleSize = k;
  }

  public static Picture create(Picture paramPicture)
  {
    return new WrappedPicture(paramPicture);
  }

  public static Picture createBitmap(ContentResolver paramContentResolver, Uri paramUri, int paramInt1, int paramInt2)
    throws FileNotFoundException, PictureFactory.ImageDecodingException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    InputStream localInputStream1 = paramContentResolver.openInputStream(paramUri);
    BitmapFactory.decodeStream(localInputStream1, null, localOptions);
    closeSafely(localInputStream1);
    configureForDownsample(paramInt1, localOptions);
    InputStream localInputStream2 = paramContentResolver.openInputStream(paramUri);
    Bitmap localBitmap = BitmapFactory.decodeStream(paramContentResolver.openInputStream(paramUri), null, localOptions);
    closeSafely(localInputStream2);
    if (localBitmap == null)
      throw new ImageDecodingException("BitmapFactory failed to decode stream");
    return new WrappedPicture(new BitmapPicture(localBitmap, paramInt2));
  }

  public static Picture createBitmap(Bitmap paramBitmap, int paramInt)
  {
    return new WrappedPicture(new BitmapPicture(paramBitmap, paramInt));
  }

  public static Picture createBitmap(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    return new WrappedPicture(new BitmapPicture(paramArrayOfByte, paramInt1, paramInt2, paramInt3));
  }

  public static Picture createBitmapWithDownsampling(byte[] paramArrayOfByte, int paramInt)
    throws PictureFactory.ImageDecodingException
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
    configureForDownsample(paramInt, localOptions);
    Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length, localOptions);
    if (localBitmap == null)
      throw new ImageDecodingException("Failed to downsample image");
    return createBitmap(localBitmap, 0);
  }

  public static Picture createJpeg(Picture paramPicture)
  {
    return new WrappedPicture(new JpegPicture(paramPicture.getJpegData(), paramPicture.getOrientation(), paramPicture.getSource()));
  }

  public static Picture createJpeg(byte[] paramArrayOfByte, int paramInt)
  {
    return new WrappedPicture(new JpegPicture(paramArrayOfByte, paramInt));
  }

  public static Picture createJpeg(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new WrappedPicture(new JpegPicture(paramArrayOfByte, paramInt1, paramInt2));
  }

  public static Picture loadAndDownsample(Context paramContext, Uri paramUri, int paramInt)
    throws PictureLoadingException
  {
    Picture localPicture1 = ImageUtils.getPicture(paramContext.getContentResolver(), paramUri, paramInt);
    if (localPicture1 == null)
      throw new PictureLoadingException("Loading raw image failed.");
    ImageUtils.RotatePhotoTask localRotatePhotoTask = new ImageUtils.RotatePhotoTask(localPicture1, 408960);
    localRotatePhotoTask.execute(new Void[0]);
    Picture localPicture2;
    try
    {
      localPicture2 = (Picture)localRotatePhotoTask.get();
      if (localPicture2 == null)
        throw new PictureLoadingException("Rotating failed");
    }
    catch (Exception localException)
    {
      throw new PictureLoadingException("Rotating/downscaling failed", localException);
    }
    return localPicture2;
  }

  public static class ImageDecodingException extends Exception
  {
    public ImageDecodingException(String paramString)
    {
      super();
    }
  }

  private static class WrappedPicture extends Picture
  {
    private final Picture picture;
    private final PictureTracker tracker = PictureTracker.getInstance();

    public WrappedPicture(Picture paramPicture)
    {
      super(paramPicture.getSource());
      this.picture = paramPicture;
      this.tracker.track(this, paramPicture);
    }

    public int getByteSize()
    {
      return this.picture.getByteSize();
    }

    public Rect getCropArea()
    {
      return this.picture.getCropArea();
    }

    public Picture getCroppedPicture()
    {
      Picture localPicture = this.picture.getCroppedPicture();
      if (localPicture == this.picture)
        return this;
      WrappedPicture localWrappedPicture = new WrappedPicture(localPicture);
      this.tracker.track(localWrappedPicture, localPicture);
      return localWrappedPicture;
    }

    public BitmapDrawable getDrawable()
    {
      return this.picture.getDrawable();
    }

    public long getId()
    {
      return this.picture.getId();
    }

    public byte[] getJpegData()
    {
      return this.picture.getJpegData();
    }

    public int getOrientation()
    {
      return this.picture.getOrientation();
    }

    public Size getSize()
    {
      return this.picture.getSize();
    }

    public byte[] getYuvData()
    {
      return this.picture.getYuvData();
    }

    public Bitmap peekBitmap()
    {
      return this.picture.peekBitmap();
    }

    public void recycle()
    {
      this.picture.recycle();
    }

    public void setCropArea(Rect paramRect)
    {
      this.picture.setCropArea(paramRect);
    }

    public void setOrientation(int paramInt)
    {
      this.picture.setOrientation(paramInt);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.PictureFactory
 * JD-Core Version:    0.6.2
 */