package com.google.android.apps.unveil.protocol.nonstop;

import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;

public class FrameEncoder
{
  private static final int DIFFERENCE_THRESHOLD = 25;
  private static final int INITIAL_JPEG_QUALITY = 50;
  private static final UnveilLogger logger = new UnveilLogger();
  private int[] lastRequestImageSignature;

  private int getJpegQuality(TimestampedFrame paramTimestampedFrame)
  {
    return paramTimestampedFrame.recommendedQuality();
  }

  public EncodedFrame encode(TimestampedFrame paramTimestampedFrame)
  {
    int i = getJpegQuality(paramTimestampedFrame);
    return new EncodedFrame(ImageUtils.encodeJpegFromYUV420SP(paramTimestampedFrame.getRawData(), paramTimestampedFrame.getWidth(), paramTimestampedFrame.getHeight(), i), i, paramTimestampedFrame.getSize(), paramTimestampedFrame.getTimestamp(), null);
  }

  public static class EncodedFrame
  {
    public final byte[] jpegData;
    public final int quality;
    public final Size size;
    public final long timestamp;

    private EncodedFrame(byte[] paramArrayOfByte, int paramInt, Size paramSize, long paramLong)
    {
      this.jpegData = paramArrayOfByte;
      this.quality = paramInt;
      this.size = paramSize;
      this.timestamp = paramLong;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.FrameEncoder
 * JD-Core Version:    0.6.2
 */