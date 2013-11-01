package com.google.android.apps.unveil.sensors;

import android.content.res.Resources;
import android.os.Handler;
import android.os.Looper;
import com.google.android.apps.unveil.env.HoneycombAsyncTask;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.proxies.camera.CameraProxy;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

class CameraProvider
{
  private static final Object cameraAcquisitionLock = new Object();
  private static CameraProxy currentCamera;
  private static Listener currentCameraOwner;
  private static final UnveilLogger logger = new UnveilLogger();
  private static Map<String, String> releasedCameras = new HashMap();
  private final Executor executor;
  private final Listener providerOwner;
  private Handler uiThreadHandler;

  public CameraProvider(Executor paramExecutor, Listener paramListener)
  {
    this.executor = paramExecutor;
    this.providerOwner = paramListener;
  }

  private CameraProxy connectCameraBlocking(String paramString, Map<String, String> paramMap, Resources paramResources)
  {
    logger.i("connectCameraBlocking", new Object[0]);
    if (currentCamera != null)
    {
      if (currentCameraOwner == this.providerOwner)
        break label87;
      logger.w("Already acquired a camera for somebody else!", new Object[0]);
      releaseCameraBlocking(currentCameraOwner, currentCamera);
    }
    try
    {
      currentCamera = openCamera(paramString, paramMap, paramResources);
      currentCameraOwner = this.providerOwner;
      logger.i("Acquired camera for the first time!", new Object[0]);
      CameraProxy localCameraProxy = currentCamera;
      return localCameraProxy;
      label87: logger.w("This object already owns a connected camera!", new Object[0]);
      return currentCamera;
    }
    catch (RuntimeException localRuntimeException)
    {
    }
    return null;
  }

  private CameraProxy openCamera(String paramString, Map<String, String> paramMap, Resources paramResources)
  {
    String str = "com.google.android.apps.unveil.sensors.proxies.camera." + paramString;
    try
    {
      Class localClass2 = Class.forName(str);
      localClass1 = localClass2;
      logger.i("Connecting to %s with params '%s'", new Object[] { paramString, paramMap });
      if (localClass1 != null)
      {
        Method[] arrayOfMethod = localClass1.getDeclaredMethods();
        int i = arrayOfMethod.length;
        j = 0;
        if (j < i)
        {
          localMethod = arrayOfMethod[j];
          if (!localMethod.getName().equals("open"));
        }
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      try
      {
        Method localMethod;
        Object[] arrayOfObject = new Object[3];
        arrayOfObject[0] = this.uiThreadHandler;
        arrayOfObject[1] = paramMap;
        arrayOfObject[2] = paramResources;
        CameraProxy localCameraProxy = (CameraProxy)localMethod.invoke(null, arrayOfObject);
        return localCameraProxy;
        localClassNotFoundException = localClassNotFoundException;
        logger.e(localClassNotFoundException, "Error getting camera proxy class for: %s", new Object[] { str });
        Class localClass1 = null;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        while (true)
        {
          int j;
          logger.e(localIllegalArgumentException, "Error opening camera proxy!", new Object[0]);
          j++;
        }
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        while (true)
          logger.e(localIllegalAccessException, "Error opening camera proxy!", new Object[0]);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        while (true)
          logger.e(localInvocationTargetException, "Error opening camera proxy!", new Object[0]);
      }
    }
    throw new RuntimeException("Error opening camera proxy!");
  }

  private void releaseCameraBlocking(Listener paramListener, CameraProxy paramCameraProxy)
  {
    logger.i("releaseCameraBlocking", new Object[0]);
    if (paramCameraProxy == null)
    {
      logger.e("Asked to release null camera!", new Object[0]);
      throw new RuntimeException("Null camera!");
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramCameraProxy.hashCode());
    String str1 = String.format("%h", arrayOfObject1);
    if (releasedCameras.containsKey(str1))
      logger.w("Already released this camera!", new Object[0]);
    releasedCameras.put(str1, str1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(paramListener.hashCode());
    String str2 = String.format("%h", arrayOfObject2);
    logger.i("Releasing Camera %s owned by CameraManager %s", new Object[] { str1, str2 });
    paramCameraProxy.release();
    if (currentCamera == paramCameraProxy)
    {
      currentCamera = null;
      currentCameraOwner = null;
      return;
    }
    logger.w("Asked to release non-current camera!", new Object[0]);
  }

  public void acquireCamera(final String paramString, final Map<String, String> paramMap, final Resources paramResources)
  {
    try
    {
      logger.i("acquireCamera", new Object[0]);
      if (Looper.myLooper() != Looper.getMainLooper())
        throw new AssertionError("CameraProvider.acquireCamera() not called from main thread!");
    }
    finally
    {
    }
    if (currentCamera != null);
    synchronized (cameraAcquisitionLock)
    {
      if ((currentCamera != null) && (this.providerOwner == currentCameraOwner))
      {
        this.providerOwner.onCameraAcquired(currentCamera);
        return;
      }
      if (this.uiThreadHandler == null)
        this.uiThreadHandler = new Handler();
      new HoneycombAsyncTask()
      {
        public CameraProxy doInBackground()
        {
          synchronized (CameraProvider.cameraAcquisitionLock)
          {
            CameraProxy localCameraProxy = CameraProvider.this.connectCameraBlocking(paramString, paramMap, paramResources);
            return localCameraProxy;
          }
        }

        protected void onPostExecute(CameraProxy paramAnonymousCameraProxy)
        {
          if (paramAnonymousCameraProxy != null)
          {
            CameraProvider.this.providerOwner.onCameraAcquired(paramAnonymousCameraProxy);
            return;
          }
          CameraProvider.this.providerOwner.onCameraAcquisitionError();
        }
      }
      .execute(this.executor, this.uiThreadHandler);
    }
  }

  public void releaseCamera(final CameraProxy paramCameraProxy)
  {
    try
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramCameraProxy.hashCode());
      localUnveilLogger.i("releaseCamera: %h", arrayOfObject);
      this.executor.execute(new Runnable()
      {
        public void run()
        {
          synchronized (CameraProvider.cameraAcquisitionLock)
          {
            CameraProvider.this.releaseCameraBlocking(CameraProvider.this.providerOwner, paramCameraProxy);
            return;
          }
        }
      });
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  static abstract interface Listener
  {
    public abstract void onCameraAcquired(CameraProxy paramCameraProxy);

    public abstract void onCameraAcquisitionError();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.CameraProvider
 * JD-Core Version:    0.6.2
 */