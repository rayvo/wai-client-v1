package com.google.android.apps.unveil.nonstop;

import android.graphics.Canvas;
import android.graphics.Matrix;
import com.google.android.apps.unveil.env.BorderedText;
import com.google.android.apps.unveil.env.DownsampledImage;
import com.google.android.apps.unveil.env.NumberUtils;
import com.google.android.apps.unveil.tracking.VisionGyro;
import java.util.LinkedList;
import java.util.List;

public class VisionGyroProcessor extends FrameProcessor
{
  private static final int MAX_MATRICES = 50;
  private final VisionGyro gyro = new VisionGyro();
  private float[] lastMatrix;
  private final Listener listener;
  private final List<TimestampedMatrix> matrices = new LinkedList();

  public VisionGyroProcessor(Listener paramListener)
  {
    this.listener = paramListener;
  }

  public float getMagnitudeSquared()
  {
    try
    {
      float[] arrayOfFloat = this.lastMatrix;
      if (arrayOfFloat == null);
      float f1;
      float f2;
      float f3;
      for (float f4 = 0.0F; ; f4 = f1 + f2 * f3)
      {
        return f4;
        f1 = this.lastMatrix[2] * this.lastMatrix[2];
        f2 = this.lastMatrix[5];
        f3 = this.lastMatrix[5];
      }
    }
    finally
    {
    }
  }

  public float[] getTransformationMatrixAndClearPrevious(long paramLong1, long paramLong2)
  {
    Matrix localMatrix1 = new Matrix();
    Matrix localMatrix2 = new Matrix();
    TimestampedMatrix localTimestampedMatrix;
    do
      synchronized (this.matrices)
      {
        if (this.matrices.size() <= 0)
          break;
        localTimestampedMatrix = (TimestampedMatrix)this.matrices.get(0);
        if ((localTimestampedMatrix.startTime >= paramLong1) && (localTimestampedMatrix.stopTime <= paramLong2))
        {
          localMatrix2.setValues(localTimestampedMatrix.matrix);
          localMatrix1.postConcat(localMatrix2);
          this.matrices.remove(0);
        }
      }
    while (localTimestampedMatrix.startTime < paramLong2);
    float[] arrayOfFloat = new float[9];
    localMatrix1.getValues(arrayOfFloat);
    return arrayOfFloat;
  }

  public void onDrawDebug(Canvas paramCanvas)
  {
    float f1 = 300.0F;
    try
    {
      BorderedText localBorderedText = new BorderedText(20.0F);
      if (this.lastMatrix != null)
        for (int i = 2; i >= 0; i--)
        {
          localBorderedText.drawText(paramCanvas, 20.0F, f1, "" + NumberUtils.format(this.lastMatrix[(0 + i * 3)], 2) + ", " + NumberUtils.format(this.lastMatrix[(1 + i * 3)], 2) + ", " + NumberUtils.format(this.lastMatrix[(2 + i * 3)], 2));
          float f2 = localBorderedText.getTextSize();
          f1 -= f2;
        }
      return;
    }
    finally
    {
    }
  }

  protected void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    try
    {
      this.lastMatrix = this.gyro.nextFrame(paramTimestampedFrame.getDownsampledImage().getBytes(), paramTimestampedFrame.getDownsampledWidth(), paramTimestampedFrame.getDownsampledHeight());
      synchronized (this.matrices)
      {
        this.matrices.add(new TimestampedMatrix(getLastFrameTime(), paramTimestampedFrame.getTimestamp(), this.lastMatrix));
        if (this.matrices.size() > 50)
          this.matrices.remove(0);
      }
    }
    finally
    {
    }
    if (this.listener != null)
      this.listener.onGyroChanged(this.lastMatrix);
  }

  public void onShutdown()
  {
    try
    {
      this.gyro.destroy();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public static abstract interface Listener
  {
    public abstract void onGyroChanged(float[] paramArrayOfFloat);
  }

  private class TimestampedMatrix
  {
    private final float[] matrix;
    private final long startTime;
    private final long stopTime;

    TimestampedMatrix(long arg2, long arg4, float[] arg6)
    {
      this.startTime = ???;
      Object localObject1;
      this.stopTime = localObject1;
      Object localObject2;
      this.matrix = localObject2;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.VisionGyroProcessor
 * JD-Core Version:    0.6.2
 */