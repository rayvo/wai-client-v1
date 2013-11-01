package com.google.android.apps.unveil.sensors.proxies.camera;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.OnZoomChangeListener;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Handler;
import android.view.SurfaceHolder;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.PlatformVersionUtils.TextureView;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class FakeCamera
  implements CameraProxy
{
  private static final int FRAME_DELAY_FOR_FAILURE_MS = 1000;
  public static final String LOCKSTEP_KEY = "lockstep_callbacks";
  private static final int MIN_FRAME_DELAY_MS = 10;
  public static final String SKIP_RENDERING_KEY = "skip_rendering";
  protected static FakeCamera camera;
  private final List<byte[]> bufferQueue;
  private Camera.PreviewCallback callback;
  private volatile boolean callbackRunning = false;
  private CallbackType callbackType;
  private SurfaceHolder cameraSurfaceHolder;
  private final Map<String, String> extraSettings;
  private Camera.AutoFocusCallback focusCallback;
  private final Handler handler;
  private RawFrame lastFrameData;
  private int[] localRgbBuffer;
  private final UnveilLogger logger = new UnveilLogger();
  private final Paint paint;
  private FakeParameters parameters;
  private boolean previewActive;
  private int previewHeight;
  private int previewWidth;
  private int renderRotation;
  protected Resources resources;
  private final boolean skipRendering;
  private final boolean waitForCallbacks;

  protected FakeCamera(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    this.extraSettings = paramMap;
    this.resources = paramResources;
    this.paint = new Paint();
    this.paint.setFilterBitmap(false);
    this.parameters = new FakeParameters();
    this.bufferQueue = new ArrayList();
    this.callback = null;
    this.callbackType = CallbackType.ONESHOT;
    this.handler = paramHandler;
    this.waitForCallbacks = getExtraValue("lockstep_callbacks", "").equals("true");
    this.skipRendering = getExtraValue("skip_rendering", "").equals("true");
  }

  private boolean drawFrame(RawFrame paramRawFrame)
  {
    if (this.cameraSurfaceHolder == null)
      return false;
    Canvas localCanvas = this.cameraSurfaceHolder.lockCanvas();
    if (localCanvas == null)
    {
      this.logger.e("couldn't get canvas!", new Object[0]);
      return false;
    }
    Matrix localMatrix = ImageUtils.getTransformationMatrix(paramRawFrame.frameSize, new Size(localCanvas.getWidth(), localCanvas.getHeight()), getDisplayOrientation());
    localCanvas.save();
    localCanvas.concat(localMatrix);
    localCanvas.drawBitmap(paramRawFrame.getRgbData(), 0, paramRawFrame.frameSize.width, 0, 0, paramRawFrame.frameSize.width, paramRawFrame.frameSize.height, true, new Paint());
    localCanvas.restore();
    this.cameraSurfaceHolder.unlockCanvasAndPost(localCanvas);
    return true;
  }

  private void frameLoop()
  {
    long l1 = 1000 / this.parameters.getInt("preview-frame-rate");
    Stopwatch localStopwatch = new Stopwatch();
    localStopwatch.start();
    while (this.previewActive)
    {
      maybeWaitForCallbacks();
      localStopwatch.reset();
      boolean bool = renderFrameAndCallBack();
      long l2;
      try
      {
        l2 = localStopwatch.getElapsedMilliseconds();
        if (bool)
          break label102;
        this.logger.w("renderFrameAndCallBack() was unsuccessful. Sleeping for a while.", new Object[0]);
        Thread.sleep(1000L);
      }
      catch (InterruptedException localInterruptedException)
      {
        this.logger.e(localInterruptedException, "Problem sleeping.", new Object[0]);
      }
      continue;
      label102: long l3 = l1 - l2;
      Thread.sleep(Math.max(10L, l3));
    }
  }

  private void handleCallback(RawFrame paramRawFrame)
  {
    if ((this.callback == null) || (this.callbackRunning))
      return;
    final Camera.PreviewCallback localPreviewCallback = this.callback;
    this.callbackRunning = true;
    int i = 5.$SwitchMap$com$google$android$apps$unveil$sensors$proxies$camera$FakeCamera$CallbackType[this.callbackType.ordinal()];
    byte[] arrayOfByte1 = null;
    switch (i)
    {
    default:
      if (paramRawFrame.yuvData == null)
        ImageUtils.convertARGB8888ToYUV420SP(paramRawFrame.rgbData, arrayOfByte1, this.previewWidth, this.previewHeight);
      break;
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      while (true)
      {
        final byte[] arrayOfByte2 = arrayOfByte1;
        this.handler.post(new Runnable()
        {
          public void run()
          {
            localPreviewCallback.onPreviewFrame(arrayOfByte2, null);
            FakeCamera.access$702(FakeCamera.this, false);
            synchronized (FakeCamera.this)
            {
              FakeCamera.this.notify();
              return;
            }
          }
        });
        return;
        synchronized (this.bufferQueue)
        {
          if (this.bufferQueue.size() > 0)
            arrayOfByte1 = (byte[])this.bufferQueue.remove(0);
        }
      }
      this.callbackRunning = false;
      return;
      this.callback = null;
      arrayOfByte1 = new byte[ImageUtils.getYUVByteSize(this.previewWidth, this.previewHeight)];
      break;
      System.arraycopy(paramRawFrame.yuvData, 0, arrayOfByte1, 0, arrayOfByte1.length);
    }
  }

  private void maybeWaitForCallbacks()
  {
    if (!this.waitForCallbacks)
      return;
    while (true)
    {
      try
      {
        if (!this.previewActive)
          break;
        if ((this.callback != null) && (!this.callbackRunning) && ((this.callbackType != CallbackType.WITH_BUFFER) || (this.bufferQueue.size() > 0)))
          return;
      }
      finally
      {
      }
      try
      {
        wait();
      }
      catch (Exception localException)
      {
        this.logger.e(localException, "Exception!", new Object[0]);
      }
    }
  }

  private boolean renderFrameAndCallBack()
  {
    try
    {
      boolean bool1 = this.previewActive;
      bool2 = false;
      if (!bool1);
      while (true)
      {
        return bool2;
        if (this.previewWidth != 0)
        {
          int i = this.previewHeight;
          if (i != 0)
            break;
        }
        try
        {
          Thread.sleep(1000L);
          bool2 = false;
        }
        catch (InterruptedException localInterruptedException)
        {
          localInterruptedException.printStackTrace();
          bool2 = false;
        }
      }
    }
    finally
    {
    }
    if (this.localRgbBuffer == null)
      this.localRgbBuffer = new int[getWidth() * getHeight()];
    this.lastFrameData = generateFrame();
    if (this.skipRendering);
    boolean bool3;
    for (boolean bool2 = true; ; bool2 = bool3)
    {
      handleCallback(this.lastFrameData);
      break;
      bool3 = drawFrame(this.lastFrameData);
    }
  }

  public void addCallbackBuffer(byte[] paramArrayOfByte)
  {
    try
    {
      synchronized (this.bufferQueue)
      {
        this.bufferQueue.add(paramArrayOfByte);
        notify();
        return;
      }
    }
    finally
    {
    }
  }

  public void autoFocus(Camera.AutoFocusCallback paramAutoFocusCallback)
  {
    this.focusCallback = paramAutoFocusCallback;
    new Thread()
    {
      public void run()
      {
        try
        {
          sleep(100L);
          FakeCamera.this.focusCallback.onAutoFocus(true, null);
          return;
        }
        catch (InterruptedException localInterruptedException)
        {
          while (true)
            localInterruptedException.printStackTrace();
        }
      }
    }
    .start();
  }

  public void cancelAutoFocus()
  {
  }

  protected abstract RawFrame generateFrame();

  protected int getDisplayOrientation()
  {
    return this.renderRotation;
  }

  protected String getExtraValue(String paramString1, String paramString2)
  {
    if ((this.extraSettings == null) || (!this.extraSettings.containsKey(paramString1)))
      return paramString2;
    return (String)this.extraSettings.get(paramString1);
  }

  protected int getHeight()
  {
    return this.previewHeight;
  }

  public ParametersProxy getParameters()
  {
    return new FakeParameters(this.parameters);
  }

  protected int getWidth()
  {
    return this.previewWidth;
  }

  public void release()
  {
    this.previewActive = false;
    camera = null;
  }

  public void setDisplayOrientation(int paramInt)
  {
    this.renderRotation = paramInt;
  }

  public void setOneShotPreviewCallback(Camera.PreviewCallback paramPreviewCallback)
  {
    try
    {
      this.callbackType = CallbackType.ONESHOT;
      this.callback = paramPreviewCallback;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setParameters(ParametersProxy paramParametersProxy)
  {
    try
    {
      this.parameters = new FakeParameters((FakeParameters)paramParametersProxy);
      this.previewHeight = this.parameters.getPreviewSize().height;
      this.previewWidth = this.parameters.getPreviewSize().width;
      UnveilLogger localUnveilLogger = this.logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = this.parameters.getPictureSize();
      localUnveilLogger.v("Setting picture size to %s", arrayOfObject);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setPreviewCallback(Camera.PreviewCallback paramPreviewCallback)
  {
    try
    {
      this.callbackType = CallbackType.REPEATING;
      this.callback = paramPreviewCallback;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setPreviewCallbackWithBuffer(Camera.PreviewCallback paramPreviewCallback)
  {
    try
    {
      this.callback = paramPreviewCallback;
      if (paramPreviewCallback != null);
      for (this.callbackType = CallbackType.WITH_BUFFER; ; this.callbackType = CallbackType.ONESHOT)
      {
        notify();
        return;
      }
    }
    finally
    {
    }
  }

  public void setPreviewDisplay(SurfaceHolder paramSurfaceHolder)
  {
    this.cameraSurfaceHolder = paramSurfaceHolder;
    paramSurfaceHolder.setType(0);
  }

  public void setPreviewTexture(PlatformVersionUtils.TextureView paramTextureView)
  {
    throw new NoSuchMethodError("FakeCamera doesn't support setPreviewTexture");
  }

  public void setZoomChangeListener(Camera.OnZoomChangeListener paramOnZoomChangeListener)
  {
    this.logger.w("FakeCamera.setZoomChangeListener(): zooming not supported!", new Object[0]);
  }

  public void startPreview()
  {
    this.previewActive = true;
    this.logger.i("Starting preview loop.", new Object[0]);
    new Thread()
    {
      public void run()
      {
        FakeCamera.this.frameLoop();
      }
    }
    .start();
  }

  public void startSmoothZoom(int paramInt)
  {
    this.logger.w("FakeCamera.startSmoothZoom(): zooming not supported!", new Object[0]);
  }

  public void stopPreview()
  {
    try
    {
      this.previewActive = false;
      notify();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void takePicture(Camera.ShutterCallback paramShutterCallback, Camera.PictureCallback paramPictureCallback1, final Camera.PictureCallback paramPictureCallback2)
  {
    new Thread()
    {
      public void run()
      {
        try
        {
          sleep(100L);
        }
        catch (InterruptedException localInterruptedException)
        {
          try
          {
            int[] arrayOfInt;
            if (FakeCamera.RawFrame.access$300(FakeCamera.this.lastFrameData) == null)
            {
              arrayOfInt = new int[FakeCamera.this.lastFrameData.frameSize.width * FakeCamera.this.lastFrameData.frameSize.height];
              ImageUtils.convertYUV420SPToARGB8888(FakeCamera.RawFrame.access$400(FakeCamera.this.lastFrameData), arrayOfInt, FakeCamera.this.lastFrameData.frameSize.width, FakeCamera.this.lastFrameData.frameSize.height, false);
            }
            while (true)
            {
              Bitmap localBitmap1 = Bitmap.createBitmap(arrayOfInt, FakeCamera.this.lastFrameData.frameSize.width, FakeCamera.this.lastFrameData.frameSize.height, Bitmap.Config.ARGB_8888);
              Size localSize = FakeCamera.this.parameters.getPictureSize();
              Bitmap localBitmap2 = Bitmap.createScaledBitmap(localBitmap1, localSize.width, localSize.height, false);
              ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
              localBitmap2.compress(Bitmap.CompressFormat.JPEG, FakeCamera.this.parameters.getInt("jpeg-quality"), localByteArrayOutputStream);
              final byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
              FakeCamera.this.handler.post(new Runnable()
              {
                public void run()
                {
                  FakeCamera.3.this.val$callback.onPictureTaken(arrayOfByte, null);
                }
              });
              return;
              localInterruptedException = localInterruptedException;
              localInterruptedException.printStackTrace();
              break;
              arrayOfInt = FakeCamera.RawFrame.access$300(FakeCamera.this.lastFrameData);
            }
          }
          finally
          {
          }
        }
      }
    }
    .start();
  }

  private static enum CallbackType
  {
    static
    {
      CallbackType[] arrayOfCallbackType = new CallbackType[3];
      arrayOfCallbackType[0] = ONESHOT;
      arrayOfCallbackType[1] = REPEATING;
      arrayOfCallbackType[2] = WITH_BUFFER;
    }
  }

  protected class RawFrame
  {
    final Size frameSize;
    private boolean isRgbDataDirty = true;
    private int[] rgbData;
    private byte[] yuvData;

    public RawFrame(int paramInt1, int paramArrayOfByte, byte[] arg4)
    {
      this.frameSize = new Size(paramInt1, paramArrayOfByte);
      Object localObject;
      this.yuvData = localObject;
    }

    public RawFrame(int paramInt1, int paramArrayOfInt, int[] arg4)
    {
      this.frameSize = new Size(paramInt1, paramArrayOfInt);
      Object localObject;
      this.rgbData = localObject;
      this.isRgbDataDirty = false;
    }

    public RawFrame(Bitmap arg2)
    {
      this(localBitmap.getWidth(), localBitmap.getHeight(), new int[localBitmap.getWidth() * localBitmap.getHeight()]);
      loadRgb(localBitmap);
    }

    public int[] getRgbData()
    {
      if (this.rgbData == null)
        this.rgbData = new int[this.frameSize.width * this.frameSize.height];
      if (this.isRgbDataDirty)
      {
        ImageUtils.convertYUV420SPToARGB8888(this.yuvData, this.rgbData, this.frameSize.width, this.frameSize.height, false);
        this.isRgbDataDirty = false;
      }
      return this.rgbData;
    }

    public byte[] getYuvDataAndClearRgbData()
    {
      this.isRgbDataDirty = true;
      return this.yuvData;
    }

    public void loadRgb(Bitmap paramBitmap)
    {
      paramBitmap.getPixels(this.rgbData, 0, this.frameSize.width, 0, 0, this.frameSize.width, this.frameSize.height);
      this.isRgbDataDirty = false;
      this.yuvData = null;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.FakeCamera
 * JD-Core Version:    0.6.2
 */