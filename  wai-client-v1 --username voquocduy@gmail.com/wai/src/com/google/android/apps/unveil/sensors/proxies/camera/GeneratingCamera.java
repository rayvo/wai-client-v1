package com.google.android.apps.unveil.sensors.proxies.camera;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Handler;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.Map;

public class GeneratingCamera extends FakeCamera
{
  private Bitmap internalBitmap;
  private final UnveilLogger logger = new UnveilLogger();
  private int rVal = 0;
  private FakeCamera.RawFrame rawFrame;

  private GeneratingCamera(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    super(paramHandler, paramMap, paramResources);
  }

  public static CameraProxy open(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    if (camera == null)
      camera = new GeneratingCamera(paramHandler, paramMap, paramResources);
    return camera;
  }

  protected FakeCamera.RawFrame generateFrame()
  {
    int i = getWidth();
    int j = getHeight();
    if (this.internalBitmap == null)
    {
      this.internalBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      this.rawFrame = new FakeCamera.RawFrame(this, this.internalBitmap);
    }
    Canvas localCanvas = new Canvas(this.internalBitmap);
    Paint localPaint = new Paint();
    this.rVal = ((1 + this.rVal) % 255);
    localCanvas.drawARGB(255, this.rVal, this.rVal / 2, this.rVal);
    localPaint.setColor(-256);
    localCanvas.drawCircle(i / 2, j / 2, Math.min(i, j) / 2.2F, localPaint);
    localPaint.setColor(-65536);
    localCanvas.drawCircle(this.rVal / 255.0F * i, this.rVal / 255.0F * j, 10.0F, localPaint);
    localPaint.setColor(-16777216);
    localPaint.setTextSize(24.0F);
    localCanvas.drawText("This is a fake picture " + i + "x" + j, (int)(100.0D + 40.0D * Math.sin(3.141592653589793D * (2.0F * (this.rVal / 255.0F)))), (int)(240.0D + 100.0D * Math.sin(3.141592653589793D * (2.0F * (this.rVal / 255.0F)))), localPaint);
    this.rawFrame.loadRgb(this.internalBitmap);
    return this.rawFrame;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.GeneratingCamera
 * JD-Core Version:    0.6.2
 */