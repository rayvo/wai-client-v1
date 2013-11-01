package com.google.android.apps.unveil.sensors;

import android.hardware.Camera;
import android.hardware.Camera.PreviewCallback;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.sensors.proxies.camera.CameraProxy;
import java.util.ArrayList;

public class PreviewFetcher
  implements Camera.PreviewCallback
{
  protected final CameraProxy camera;
  private ArrayList<byte[]> previewBuffers = null;
  protected final Camera.PreviewCallback previewCallback;
  private final Size size;

  public PreviewFetcher(CameraProxy paramCameraProxy, Camera.PreviewCallback paramPreviewCallback, Size paramSize)
  {
    this.camera = paramCameraProxy;
    this.previewCallback = paramPreviewCallback;
    this.size = paramSize;
  }

  public void addPreviewBuffer(byte[] paramArrayOfByte)
  {
    synchronized (this.previewBuffers)
    {
      this.previewBuffers.add(paramArrayOfByte);
      return;
    }
  }

  protected int getPreviewBufferSize(Size paramSize)
  {
    return ImageUtils.getYUVByteSize(paramSize.width, paramSize.height);
  }

  public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
  {
    this.previewCallback.onPreviewFrame(paramArrayOfByte, paramCamera);
  }

  protected void requestFrame()
  {
    synchronized (this.previewBuffers)
    {
      if (this.previewBuffers.isEmpty())
      {
        arrayOfByte = new byte[getPreviewBufferSize(this.size)];
        this.camera.addCallbackBuffer(arrayOfByte);
        return;
      }
      byte[] arrayOfByte = (byte[])this.previewBuffers.remove(0);
    }
  }

  protected void start()
  {
    this.previewBuffers = new ArrayList();
    this.camera.setPreviewCallbackWithBuffer(this);
  }

  protected void stop()
  {
    this.previewBuffers = null;
    this.camera.setPreviewCallback(null);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.PreviewFetcher
 * JD-Core Version:    0.6.2
 */