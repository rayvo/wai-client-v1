package com.google.android.apps.unveil.env;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import com.google.android.apps.unveil.R.id;

public abstract class Picture
{
  public static final int SOURCE_CAMERA = 1;
  public static final int SOURCE_SHARED = 2;
  public static final int SOURCE_UNKNOWN = -1;
  private Rect cropArea;
  private final long id = System.currentTimeMillis();
  private int orientation;
  private volatile boolean recycled = false;
  protected final int source;

  protected Picture(int paramInt)
  {
    this(paramInt, -1);
  }

  protected Picture(int paramInt1, int paramInt2)
  {
    this.orientation = paramInt1;
    this.source = paramInt2;
  }

  protected final void checkNotRecycled()
  {
    if (this.recycled)
      try
      {
        throw new RuntimeException();
      }
      catch (RuntimeException localRuntimeException)
      {
        new UnveilLogger().e(localRuntimeException, "Exception!", new Object[0]);
      }
  }

  public abstract int getByteSize();

  public final Bitmap getCopyOfBitmap()
  {
    Bitmap localBitmap = peekBitmap();
    if (localBitmap == null)
      return null;
    return localBitmap.copy(localBitmap.getConfig(), false);
  }

  public Rect getCropArea()
  {
    return this.cropArea;
  }

  public abstract Picture getCroppedPicture();

  public abstract BitmapDrawable getDrawable();

  public long getId()
  {
    return this.id;
  }

  public abstract byte[] getJpegData();

  public int getOrientation()
  {
    return this.orientation;
  }

  public abstract Size getSize();

  public final int getSource()
  {
    return this.source;
  }

  public abstract byte[] getYuvData();

  public final boolean isRecycled()
  {
    return this.recycled;
  }

  public abstract Bitmap peekBitmap();

  public final void populateWithBitmap(ImageView paramImageView)
  {
    paramImageView.setImageBitmap(peekBitmap());
    paramImageView.setTag(R.id.image_view_bitmap, this);
  }

  public void recycle()
  {
    checkNotRecycled();
    this.recycled = true;
  }

  public void setCropArea(Rect paramRect)
  {
    if (this.cropArea != null)
      throw new RuntimeException("Crop area already set!");
    this.cropArea = paramRect;
  }

  public void setOrientation(int paramInt)
  {
    this.orientation = paramInt;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Picture
 * JD-Core Version:    0.6.2
 */