package com.google.android.apps.unveil.protocol.nonstop;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;
import com.google.android.apps.unveil.env.BorderedText;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.nonstop.TimestampedFrame.Metadata;
import com.google.android.apps.unveil.results.ResultItem;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

public class TimestampedFrameDebugDrawer
  implements DebugDrawer
{
  private static final int DEBUG_TEXT_SIZE = 16;
  private final BorderedText borderText = new BorderedText(16.0F);
  private final byte[] cachedJpeg;
  private Picture cachedThumbnail;
  private final int frameNum;
  private final boolean isBlurred;
  private final TimestampedFrame.Metadata metadata;
  private final int rotationAngle;
  private final Size size;

  public TimestampedFrameDebugDrawer(TimestampedFrame paramTimestampedFrame, byte[] paramArrayOfByte)
  {
    this.rotationAngle = paramTimestampedFrame.getRotationAngle();
    this.size = paramTimestampedFrame.getSize();
    this.metadata = paramTimestampedFrame.getMetadata();
    this.isBlurred = paramTimestampedFrame.isBlurred();
    this.frameNum = paramTimestampedFrame.getFrameNum();
    this.cachedJpeg = paramArrayOfByte;
  }

  private static Picture createThumbnail(byte[] paramArrayOfByte, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramArrayOfByte == null);
    Bitmap localBitmap1;
    do
    {
      return null;
      localBitmap1 = PictureFactory.createJpeg(paramArrayOfByte, 0).peekBitmap();
    }
    while (localBitmap1 == null);
    Bitmap localBitmap2 = Bitmap.createBitmap(paramInt1, paramInt2, Bitmap.Config.RGB_565);
    Size localSize1 = new Size(localBitmap1);
    Size localSize2 = new Size(localBitmap2);
    if (paramBoolean);
    for (int i = 90; ; i = 0)
    {
      Matrix localMatrix = ImageUtils.getTransformationMatrix(localSize1, localSize2, i);
      new Canvas(localBitmap2).drawBitmap(localBitmap1, localMatrix, new Paint());
      return PictureFactory.createBitmap(localBitmap2, 0);
    }
  }

  private void drawResults(Canvas paramCanvas, Matrix paramMatrix)
  {
    Map localMap = getMetadata().results;
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      ResultItem localResultItem = (ResultItem)localMap.get((String)localIterator.next());
      Paint localPaint = new Paint();
      localPaint.setStrokeWidth(2.0F);
      localPaint.setStyle(Paint.Style.STROKE);
      localPaint.setColor(-16711936);
      if ((localResultItem.getBoundingBox() != null) && (localResultItem.getBoundingBox().right - localResultItem.getBoundingBox().left > 0))
      {
        RectF localRectF = new RectF(localResultItem.getBoundingBox());
        paramMatrix.mapRect(localRectF);
        paramCanvas.drawRoundRect(localRectF, 1.0F, 1.0F, localPaint);
      }
    }
  }

  private void drawText(Canvas paramCanvas, int paramInt1, int paramInt2)
  {
    int i = -65536;
    if (!getMetadata().results.isEmpty())
      i = -16711936;
    while (true)
    {
      this.borderText.setInteriorColor(i);
      int j = paramInt1 + 4;
      int k = paramInt2;
      Iterator localIterator = getDebugText().iterator();
      while (localIterator.hasNext())
      {
        String str = (String)localIterator.next();
        k += 18;
        this.borderText.drawText(paramCanvas, j, k, str);
      }
      if (getMetadata().getQuerySentMillis() != -1L)
        i = -256;
    }
  }

  private byte[] getCachedJpeg()
  {
    return this.cachedJpeg;
  }

  private Vector<String> getDebugText()
  {
    Vector localVector = new Vector();
    localVector.add("#: " + this.frameNum);
    localVector.add("blr: " + this.isBlurred);
    if (getCachedJpeg() != null)
    {
      localVector.add("JPG: " + getCachedJpeg().length / 1024 + "kB");
      localVector.add("lcl: " + getMetadata().getEncodeFinishedTime() + "ms");
    }
    localVector.addAll(getMetadata().getDebugText());
    return localVector;
  }

  private int getHeight()
  {
    return this.size.height;
  }

  private TimestampedFrame.Metadata getMetadata()
  {
    return this.metadata;
  }

  private Picture getThumbnail(int paramInt1, int paramInt2, boolean paramBoolean)
  {
    try
    {
      if (this.cachedThumbnail == null)
        this.cachedThumbnail = createThumbnail(getCachedJpeg(), paramInt1, paramInt2, paramBoolean);
      Picture localPicture = this.cachedThumbnail;
      return localPicture;
    }
    finally
    {
    }
  }

  private int getWidth()
  {
    return this.size.width;
  }

  public void draw(Canvas paramCanvas, int paramInt, float paramFloat)
  {
    while (true)
    {
      try
      {
        if (this.rotationAngle != 90)
        {
          int n = this.rotationAngle;
          bool = false;
          if (n != 270)
          {
            int i;
            int k;
            int m;
            Paint localPaint;
            if (bool)
            {
              i = getHeight();
              float f = paramFloat / i;
              if (bool)
              {
                j = getWidth();
                k = (int)(f * j);
                m = (int)(0.5F + paramFloat * paramInt);
                localPaint = new Paint();
                if (getCachedJpeg() == null)
                  continue;
                Bitmap localBitmap = getThumbnail((int)paramFloat, k, bool).peekBitmap();
                if (localBitmap != null)
                  paramCanvas.drawBitmap(localBitmap, m, 0.0F, localPaint);
                Matrix localMatrix = new Matrix();
                localMatrix.postScale(f, f);
                if (bool)
                {
                  localMatrix.postTranslate(-k / 2, -paramFloat / 2.0F);
                  localMatrix.postRotate(this.rotationAngle);
                  localMatrix.postTranslate(paramFloat / 2.0F, k / 2);
                }
                localMatrix.postTranslate(m, 0.0F);
                drawResults(paramCanvas, localMatrix);
                drawText(paramCanvas, m, k);
              }
            }
            else
            {
              i = getWidth();
              continue;
            }
            int j = getHeight();
            continue;
            localPaint.setColor(-12303292);
            paramCanvas.drawRect(new Rect(m, 0, (int)(paramFloat + m), k), localPaint);
            continue;
          }
        }
      }
      finally
      {
      }
      boolean bool = true;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.TimestampedFrameDebugDrawer
 * JD-Core Version:    0.6.2
 */