package com.google.android.apps.unveil.sensors;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Rect;
import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Looper;
import android.util.AttributeSet;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.PlatformVersionUtils.TextureView;
import com.google.android.apps.unveil.env.Provider;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.sensors.proxies.camera.CameraProxy;
import com.google.android.apps.unveil.sensors.proxies.camera.FelixCamera;
import com.google.android.apps.unveil.sensors.proxies.camera.ParametersProxy;
import com.google.android.apps.unveil.sensors.proxies.camera.RealCamera;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class CameraManager extends SurfaceView
  implements SurfaceHolder.Callback, Camera.PictureCallback, CameraProvider.Listener
{
  public static final String FLASH_MODE_UNSUPPORTED = "unsupported";
  public static final float RATIO_SLOP = 0.1F;
  private static final int STATE_FOCUSED = 2;
  private static final int STATE_FOCUSING = 1;
  private static final int STATE_IDLE = 0;
  private static final int STATE_SNAPPED = 4;
  private static final int STATE_SNAPPING = 3;
  private static final UnveilLogger logger = new UnveilLogger();
  private static HashMap<String, Size> specialCases;
  private volatile boolean acquireCameraOnVisibilityChange = true;
  private boolean acquisitionPending;
  private CameraProxy camera;
  private CameraProvider cameraAcquirer;
  private ParametersProxy cameraParameters;
  private Provider<Map<String, String>> cameraParamsProvider;
  private int cameraRotation;
  private Provider<String> cameraTypeProvider;
  private volatile int currentDisplayRotation;
  private volatile int currentOrientation;
  private PictureQuality currentQualitySetting = PictureQuality.NORMAL_QUALITY;
  private int currentZoomLevel;
  private Size deviceFullScreenDisplaySize;
  private final Executor executor = Executors.newSingleThreadExecutor(new ThreadFactory()
  {
    public Thread newThread(Runnable paramAnonymousRunnable)
    {
      Thread localThread = new Thread(paramAnonymousRunnable);
      localThread.setName("CameraManager Executor");
      return localThread;
    }
  });
  private String flashMode = "off";
  private volatile String flashSettingAfterFocus = null;
  private final List<FocusCallback> focusListeners = new ArrayList();
  private Size forcedPreviewSize = null;
  private Boolean isContinuousFocusSupported;
  private Provider<Boolean> isContinuousProvider;
  private Boolean isFlashSupported;
  private Boolean isFocusSupported;
  private final Set<Listener> listeners = new HashSet();
  private int maxZoomLevel;
  private volatile int naturalOrientation;
  private volatile ParametersProxy pendingSetParameters = null;
  private volatile boolean pendingStartPreview = false;
  private final List<PictureCallback> pictureListeners = new ArrayList();
  private PreviewFetcher previewFetcher;
  private final Object previewFetcherLock = new Object();
  private volatile boolean previewing = false;
  private volatile boolean releaseRequiredAfterFocus;
  private Provider<UnveilSensorProvider> sensorProviderProvider;
  private boolean shouldBatchSettingOfParameters = false;
  private final List<ShutterCallback> shutterListeners = new ArrayList();
  private volatile boolean snapRequiredAfterFocus;
  private volatile int state = 0;
  private PlatformVersionUtils.TextureView textureView;
  private boolean zoomSupported;

  public CameraManager(Context paramContext)
  {
    super(paramContext);
    init(paramContext);
  }

  public CameraManager(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    init(paramContext);
  }

  public CameraManager(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    init(paramContext);
  }

  public CameraManager(Context paramContext, UnveilContext paramUnveilContext)
  {
    super(paramContext);
    init(null, paramUnveilContext);
  }

  private void configureCameraAndStartPreview(int paramInt1, int paramInt2)
    throws IOException
  {
    setBatchCameraParameters(true);
    while (true)
    {
      try
      {
        setOptimalPreviewSize(paramInt1, paramInt2);
        setPictureQuality(this.currentQualitySetting);
        queryFlashSupport();
        if (this.textureView != null)
        {
          this.camera.setPreviewTexture(this.textureView);
          correctDisplayOrientation();
          setFlashMode(this.flashMode);
          queryFocusSupport();
          enableAutoFocus();
          Iterator localIterator = this.listeners.iterator();
          if (!localIterator.hasNext())
            break;
          ((Listener)localIterator.next()).onCameraPreviewSizeChanged();
          continue;
        }
      }
      catch (RuntimeException localRuntimeException)
      {
        logger.e(localRuntimeException, "Failed to set optimal preview size.", new Object[0]);
        setBatchCameraParameters(false);
        return;
      }
      this.camera.setPreviewDisplay(getHolder());
    }
    setBatchCameraParameters(false);
    logger.i("Starting preview!", new Object[0]);
    this.previewing = true;
    this.camera.startPreview();
    queryZoomSupport();
    this.currentZoomLevel = 0;
  }

  private void configureSurfaceHolder()
  {
    if ((this.cameraTypeProvider != null) && ((((String)this.cameraTypeProvider.get()).startsWith(RealCamera.class.getSimpleName())) || (((String)this.cameraTypeProvider.get()).startsWith(FelixCamera.class.getSimpleName()))))
      getHolder().setType(3);
  }

  private void correctDisplayOrientation()
  {
    if (Build.VERSION.SDK_INT <= 8)
    {
      correctDisplayOrientationForFroyo();
      return;
    }
    this.camera.setDisplayOrientation(getCameraToDisplayRotation(getContext()));
  }

  private void correctDisplayOrientationForFroyo()
  {
    this.cameraRotation = 0;
    switch (this.currentDisplayRotation)
    {
    default:
    case 0:
    case 1:
    case 2:
    case 3:
    }
    while (true)
    {
      if (this.naturalOrientation == 1)
      {
        this.cameraRotation = (90 + this.cameraRotation);
        if (this.cameraRotation == 360)
          this.cameraRotation = 0;
      }
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(this.cameraRotation);
      localUnveilLogger.i("Rotating camera %d degrees", arrayOfObject);
      this.camera.setDisplayOrientation(this.cameraRotation);
      return;
      this.cameraRotation = 0;
      continue;
      this.cameraRotation = 270;
      continue;
      this.cameraRotation = 180;
      continue;
      this.cameraRotation = 90;
    }
  }

  private void enableAutoFocus()
  {
    if (this.camera == null);
    while ((this.isFocusSupported == null) || (!this.isFocusSupported.booleanValue()))
      return;
    ParametersProxy localParametersProxy = getCameraParameters();
    localParametersProxy.set("focus-mode", "auto");
    try
    {
      setCameraParameters(localParametersProxy);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      logger.e(localRuntimeException, "Unable to set focus mode to: %s", new Object[] { "auto" });
    }
  }

  static Size findOptimalSize(List<Size> paramList, int paramInt1, int paramInt2, boolean paramBoolean, int paramInt3)
  {
    if (logger.shouldShowVerbose())
    {
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[5];
      arrayOfObject3[0] = Size.sizeListToString(paramList);
      arrayOfObject3[1] = Integer.valueOf(paramInt1);
      arrayOfObject3[2] = Integer.valueOf(paramInt2);
      arrayOfObject3[3] = Boolean.valueOf(paramBoolean);
      arrayOfObject3[4] = Integer.valueOf(paramInt3);
      localUnveilLogger3.v("findOptimalSize([%s], %d, %d, %b, %d)", arrayOfObject3);
    }
    float f1 = paramInt1 / paramInt2;
    float f2 = f1 * 0.1F;
    if (paramBoolean)
    {
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Float.valueOf(f1);
      arrayOfObject2[1] = Float.valueOf(f2);
      localUnveilLogger2.v("Target ratio is %f, allowed slop of %f", arrayOfObject2);
    }
    int i = paramInt1 * paramInt2;
    int j = 2147483647;
    Object localObject = null;
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      Size localSize = (Size)localIterator.next();
      if ((paramInt3 <= 0) || (localSize.width * localSize.height <= paramInt3))
      {
        float f3 = Math.abs(f1 - localSize.width / localSize.height);
        if ((!paramBoolean) || (f3 <= f2))
        {
          int k = Math.abs(localSize.width * localSize.height - i);
          if (k < j)
          {
            j = k;
            localObject = localSize;
          }
        }
      }
    }
    if ((localObject == null) && (paramBoolean))
    {
      logger.w("Couldn't find size that meets aspect ratio requirement, trying without.", new Object[0]);
      return findOptimalSize(paramList, paramInt1, paramInt2, false, paramInt3);
    }
    if (localObject == null)
      logger.e("No optimal size!", new Object[0]);
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = localObject.toString();
    localUnveilLogger1.v("Optimal size is %s", arrayOfObject1);
    return localObject;
  }

  private ParametersProxy getCameraParameters()
  {
    try
    {
      if ((this.cameraParameters == null) && (this.camera != null))
      {
        if (this.pendingSetParameters == null)
          break label53;
        logger.w("getParameters returning deferred value set while taking a picture!", new Object[0]);
      }
      label53: for (this.cameraParameters = this.pendingSetParameters; ; this.cameraParameters = this.camera.getParameters())
      {
        ParametersProxy localParametersProxy = this.cameraParameters;
        return localParametersProxy;
      }
    }
    finally
    {
    }
  }

  public static int getCameraToDisplayRotation(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 8)
    {
      logger.w("getCameraToDisplayRotation() called on API <= 8!", new Object[0]);
      return 0;
    }
    int i = ((WindowManager)paramContext.getSystemService("window")).getDefaultDisplay().getRotation();
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(0, localCameraInfo);
    int j = 0;
    switch (i)
    {
    default:
      if (localCameraInfo.facing != 1)
        break;
    case 0:
    case 1:
    case 2:
    case 3:
    }
    for (int k = (360 - (j + localCameraInfo.orientation) % 360) % 360; ; k = (360 + (localCameraInfo.orientation - j)) % 360)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(i);
      arrayOfObject[1] = Integer.valueOf(k);
      localUnveilLogger.d("getDisplayRotation() %d, %d", arrayOfObject);
      return k;
      j = 0;
      break;
      j = 90;
      break;
      j = 180;
      break;
      j = 270;
      break;
    }
  }

  private Size getSpecialCasePreviewSize()
  {
    if (specialCases == null)
    {
      specialCases = new HashMap();
      specialCases.put("SPH-M900", new Size(640, 480));
      specialCases.put("DROIDX", new Size(800, 448));
      specialCases.put("XT720", new Size(848, 480));
      specialCases.put("Nexus S", new Size(640, 480));
      specialCases.put("Droid", new Size(640, 480));
      specialCases.put("SGH-T999", new Size(640, 480));
    }
    if (specialCases.containsKey(Build.MODEL))
    {
      Size localSize = (Size)specialCases.get(Build.MODEL);
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localSize;
      arrayOfObject[1] = Build.MODEL;
      localUnveilLogger.w("Special case: using %s for preview size on %s.", arrayOfObject);
      return localSize;
    }
    return null;
  }

  private void init(Context paramContext)
  {
    if ((paramContext.getApplicationContext() instanceof UnveilContext))
    {
      init(paramContext, (UnveilContext)paramContext.getApplicationContext());
      return;
    }
    init(paramContext, null);
  }

  private void init(Context paramContext, final UnveilContext paramUnveilContext)
  {
    if (paramUnveilContext != null)
    {
      this.sensorProviderProvider = new Provider()
      {
        public UnveilSensorProvider get()
        {
          return paramUnveilContext.getSensorProvider();
        }
      };
      this.isContinuousProvider = new Provider()
      {
        public Boolean get()
        {
          return Boolean.valueOf(paramUnveilContext.isContinuous());
        }
      };
      this.cameraTypeProvider = new Provider()
      {
        public String get()
        {
          return paramUnveilContext.getCameraType();
        }
      };
      this.cameraParamsProvider = new Provider()
      {
        public Map<String, String> get()
        {
          return paramUnveilContext.getCameraParams();
        }
      };
    }
    SurfaceHolder localSurfaceHolder = getHolder();
    localSurfaceHolder.setSizeFromLayout();
    localSurfaceHolder.addCallback(this);
    configureSurfaceHolder();
    if (paramContext != null)
      updateCurrentRotationAndOrientation(paramContext.getResources().getConfiguration().orientation);
    this.cameraAcquirer = new CameraProvider(this.executor, this);
  }

  public static boolean isFrontFacingCamera(Context paramContext)
  {
    if (Build.VERSION.SDK_INT <= 8)
      logger.w("isFrontFacingCamera() called on API <= 8!", new Object[0]);
    int i;
    label60: 
    do
    {
      return false;
      i = Camera.getNumberOfCameras();
      Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
      for (int j = 0; ; j++)
      {
        if (j >= i)
          break label60;
        Camera.getCameraInfo(j, localCameraInfo);
        if (localCameraInfo.facing == 0)
          break;
      }
    }
    while (i <= 0);
    return true;
  }

  private boolean isTakingPicture()
  {
    return (this.state == 3) || (this.state == 4);
  }

  private void maybeSetPendingCameraParameters()
  {
    while (true)
    {
      try
      {
        if (this.camera == null)
        {
          logger.w("No camera yet to set parameters on.", new Object[0]);
          return;
        }
        if (this.pendingSetParameters == null)
          continue;
        if (this.shouldBatchSettingOfParameters)
          logger.e("maybeSetPendingCameraParameters() directly called while cache is active.", new Object[0]);
        this.cameraParameters = null;
        if (isTakingPicture())
        {
          logger.w("Taking picture, aborting setParameters.", new Object[0]);
          continue;
        }
      }
      finally
      {
      }
      if (isFocusing())
      {
        logger.w("Focusing, aborting setParameters.", new Object[0]);
      }
      else
      {
        logger.i("Setting camera parameters.", new Object[0]);
        this.camera.setParameters(this.pendingSetParameters);
        this.pendingSetParameters = null;
        getCameraParameters();
      }
    }
  }

  private void queryFlashSupport()
  {
    if ((this.camera == null) || (this.isFlashSupported != null));
    String str1;
    do
    {
      return;
      str1 = getCameraParameters().get("flash-mode-values");
    }
    while (str1 == null);
    int i = 0;
    int j = 0;
    String[] arrayOfString = str1.split(",");
    int k = arrayOfString.length;
    int m = 0;
    if (m < k)
    {
      String str2 = arrayOfString[m];
      if (str2.equals("torch"))
        i = 1;
      while (true)
      {
        m++;
        break;
        if (str2.equals("off"))
          j = 1;
      }
    }
    if ((j != 0) && (i != 0));
    for (boolean bool = true; ; bool = false)
    {
      this.isFlashSupported = Boolean.valueOf(bool);
      return;
    }
  }

  private void queryFocusSupport()
  {
    if ((this.camera == null) || (this.isFocusSupported != null) || (this.isContinuousFocusSupported != null));
    String str1;
    do
    {
      return;
      str1 = getCameraParameters().get("focus-mode-values");
    }
    while (str1 == null);
    String[] arrayOfString = str1.split(",");
    int i = arrayOfString.length;
    int j = 0;
    label53: String str2;
    if (j < i)
    {
      str2 = arrayOfString[j];
      if (!str2.equals("auto"))
        break label90;
      this.isFocusSupported = Boolean.valueOf(true);
    }
    while (true)
    {
      j++;
      break label53;
      break;
      label90: if (str2.equals("continuous-picture"))
        this.isContinuousFocusSupported = Boolean.valueOf(true);
    }
  }

  private void queryZoomSupport()
  {
    ParametersProxy localParametersProxy = getCameraParameters();
    this.zoomSupported = localParametersProxy.isSmoothZoomSupported();
    if (this.zoomSupported)
      this.maxZoomLevel = localParametersProxy.getMaxZoom();
  }

  private void releaseCamera()
  {
    try
    {
      if (this.state == 1)
      {
        logger.w("Deferring camera release until after focus", new Object[0]);
        this.releaseRequiredAfterFocus = true;
      }
      while (true)
      {
        return;
        forceReleaseCamera();
      }
    }
    finally
    {
    }
  }

  private int roundOffOrientation(int paramInt)
  {
    if (paramInt != -1)
      return paramInt + 90;
    return 0;
  }

  private void setBatchCameraParameters(boolean paramBoolean)
  {
    try
    {
      this.shouldBatchSettingOfParameters = paramBoolean;
      if (!this.shouldBatchSettingOfParameters)
        maybeSetPendingCameraParameters();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void setCameraParameters(ParametersProxy paramParametersProxy)
    throws RuntimeException
  {
    try
    {
      this.pendingSetParameters = paramParametersProxy;
      this.cameraParameters = null;
      if (!this.shouldBatchSettingOfParameters)
        maybeSetPendingCameraParameters();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void setOptimalPictureSize(int paramInt1, int paramInt2)
    throws RuntimeException
  {
    ParametersProxy localParametersProxy = getCameraParameters();
    if ((this.camera == null) || (localParametersProxy == null))
      return;
    List localList;
    try
    {
      localList = localParametersProxy.getSupportedPictureSizes();
      if (localList == null)
        throw new RuntimeException("Failed to find picture sizes in camera parameters.");
    }
    catch (RuntimeException localRuntimeException)
    {
      UnveilLogger localUnveilLogger1 = logger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(paramInt1);
      arrayOfObject1[1] = Integer.valueOf(paramInt2);
      localUnveilLogger1.e(localRuntimeException, "No suitable picture size available, forcing %dx%d", arrayOfObject1);
      localParametersProxy.setPictureSize(paramInt1, paramInt2);
    }
    while (true)
    {
      setCameraParameters(localParametersProxy);
      Size localSize1 = getPictureSize();
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(localSize1.width);
      arrayOfObject2[1] = Integer.valueOf(localSize1.height);
      localUnveilLogger2.v("Set picture size to %dx%d", arrayOfObject2);
      return;
      Size localSize2 = findOptimalSize(localList, paramInt1, paramInt2, false, 0);
      if (localSize2 == null)
        throw new RuntimeException("Could not find a suitable picture size.");
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[2];
      arrayOfObject3[0] = Integer.valueOf(localSize2.width);
      arrayOfObject3[1] = Integer.valueOf(localSize2.height);
      localUnveilLogger3.v("Attempting to set optimal picture size %dx%d", arrayOfObject3);
      localParametersProxy.setPictureSize(localSize2.width, localSize2.height);
    }
  }

  private void setOptimalPreviewSize(int paramInt1, int paramInt2)
    throws RuntimeException
  {
    ParametersProxy localParametersProxy = getCameraParameters();
    if ((this.camera == null) || (localParametersProxy == null))
      return;
    if (this.forcedPreviewSize != null);
    for (Size localSize1 = this.forcedPreviewSize; localSize1 == null; localSize1 = getOptimalPreviewSize(paramInt1, paramInt2))
      throw new RuntimeException("Could not find a suitable preview size.");
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = Integer.valueOf(localSize1.width);
    arrayOfObject1[1] = Integer.valueOf(localSize1.height);
    if (this.forcedPreviewSize == null);
    for (String str = ""; ; str = "based on forced preview size")
    {
      arrayOfObject1[2] = str;
      localUnveilLogger1.v("Attempting to set optimal preview size %dx%d %s", arrayOfObject1);
      localParametersProxy.setPreviewSize(localSize1.width, localSize1.height);
      setCameraParameters(localParametersProxy);
      Size localSize2 = getPreviewSize();
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(localSize2.width);
      arrayOfObject2[1] = Integer.valueOf(localSize2.height);
      localUnveilLogger2.v("Set preview size to %dx%d", arrayOfObject2);
      return;
    }
  }

  private void setPictureQuality(PictureQuality paramPictureQuality)
  {
    if (this.camera == null);
    while (true)
    {
      return;
      ParametersProxy localParametersProxy = getCameraParameters();
      localParametersProxy.set("jpeg-quality", paramPictureQuality.jpegQuality);
      Iterator localIterator;
      try
      {
        setCameraParameters(localParametersProxy);
        setOptimalPictureSize(paramPictureQuality.desiredWidth, paramPictureQuality.desiredHeight);
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        logger.e(localRuntimeException, "Unable to set quality to: %s", new Object[] { paramPictureQuality });
        localIterator = this.listeners.iterator();
      }
      while (localIterator.hasNext())
        ((Listener)localIterator.next()).onCameraQualityError();
    }
  }

  private boolean updateCurrentRotationAndOrientation(int paramInt)
  {
    this.naturalOrientation = Viewport.computeNaturalOrientation(getContext());
    Display localDisplay = ((WindowManager)getContext().getSystemService("window")).getDefaultDisplay();
    if ((this.currentDisplayRotation != localDisplay.getRotation()) || (this.currentOrientation != paramInt))
    {
      this.currentDisplayRotation = localDisplay.getRotation();
      this.currentOrientation = paramInt;
      return true;
    }
    return false;
  }

  public void acquireCamera()
  {
    while (true)
    {
      try
      {
        logger.i("acquireCamera", new Object[0]);
        if ((this.camera == null) && (!this.acquisitionPending))
        {
          logger.v("CameraManager is requesting a camera to manage.", new Object[0]);
          this.acquisitionPending = true;
          if (Looper.myLooper() == Looper.getMainLooper())
          {
            this.cameraAcquirer.acquireCamera((String)this.cameraTypeProvider.get(), (Map)this.cameraParamsProvider.get(), getResources());
            return;
          }
          post(new Runnable()
          {
            public void run()
            {
              CameraManager.this.cameraAcquirer.acquireCamera((String)CameraManager.this.cameraTypeProvider.get(), (Map)CameraManager.this.cameraParamsProvider.get(), CameraManager.this.getResources());
            }
          });
          continue;
        }
      }
      finally
      {
      }
      if (this.camera == null)
        logger.v("CameraManager is already waiting for a pending camera request.", new Object[0]);
      else
        logger.i("CameraManager already has a camera, ignoring call to acquireCamera.", new Object[0]);
    }
  }

  public void addPreviewBuffer(byte[] paramArrayOfByte)
  {
    synchronized (this.previewFetcherLock)
    {
      if (this.previewFetcher != null)
      {
        this.previewFetcher.addPreviewBuffer(paramArrayOfByte);
        return;
      }
      logger.w("Tried to give buffer to null PreviewFetcher", new Object[0]);
    }
  }

  public void clearCachedCameraParams()
  {
    this.cameraParameters = null;
  }

  public void enableContinuousFocus(boolean paramBoolean)
  {
    if (this.camera == null);
    do
    {
      return;
      if (!paramBoolean)
      {
        enableAutoFocus();
        return;
      }
    }
    while ((this.isContinuousFocusSupported == null) || (!this.isContinuousFocusSupported.booleanValue()));
    ParametersProxy localParametersProxy = getCameraParameters();
    localParametersProxy.set("focus-mode", "continuous-picture");
    try
    {
      setCameraParameters(localParametersProxy);
      return;
    }
    catch (RuntimeException localRuntimeException)
    {
      logger.e(localRuntimeException, "Unable to set focus mode to: %s", new Object[] { "continuous-picture" });
    }
  }

  public void focus(FocusCallback paramFocusCallback)
  {
    while (true)
    {
      try
      {
        CameraProxy localCameraProxy = this.camera;
        if (localCameraProxy == null)
          return;
        if (this.state == 2)
        {
          logger.i("Focus just finished and callbacks are being invoked, ignoring focus request!", new Object[0]);
          continue;
        }
      }
      finally
      {
      }
      if (isTakingPicture())
      {
        logger.i("A picture is being taken now, ignoring focus request!", new Object[0]);
      }
      else
      {
        if (paramFocusCallback != null)
          this.focusListeners.add(paramFocusCallback);
        if (this.state != 1)
        {
          this.state = 1;
          Camera.AutoFocusCallback local11 = new Camera.AutoFocusCallback()
          {
            public void onAutoFocus(boolean paramAnonymousBoolean, Camera paramAnonymousCamera)
            {
              synchronized (CameraManager.this)
              {
                CameraManager.access$002(CameraManager.this, 2);
                CameraManager.this.maybeSetPendingCameraParameters();
                Iterator localIterator = CameraManager.this.focusListeners.iterator();
                if (localIterator.hasNext())
                  ((CameraManager.FocusCallback)localIterator.next()).onFocus(paramAnonymousBoolean);
              }
              CameraManager.this.focusListeners.clear();
              CameraManager.access$002(CameraManager.this, 0);
              if (CameraManager.this.releaseRequiredAfterFocus)
              {
                CameraManager.access$402(CameraManager.this, false);
                CameraManager.this.releaseCamera();
              }
              do
              {
                return;
                if (CameraManager.this.snapRequiredAfterFocus)
                {
                  CameraManager.access$602(CameraManager.this, false);
                  CameraManager.this.takePicture(null, null);
                  return;
                }
              }
              while (CameraManager.this.flashSettingAfterFocus == null);
              CameraManager.this.setFlashMode(CameraManager.this.flashSettingAfterFocus);
              CameraManager.access$702(CameraManager.this, null);
            }
          };
          try
          {
            this.camera.autoFocus(local11);
          }
          catch (RuntimeException localRuntimeException)
          {
            this.state = 0;
            Iterator localIterator = this.focusListeners.iterator();
            while (localIterator.hasNext())
              ((FocusCallback)localIterator.next()).onFocus(false);
            this.focusListeners.clear();
          }
        }
      }
    }
  }

  public void forceReleaseCamera()
  {
    try
    {
      if (this.camera != null)
      {
        if (this.state == 1)
        {
          logger.d("Attempting cancelAutoFocus call.", new Object[0]);
          this.camera.cancelAutoFocus();
        }
        requestOneFrame(null);
        if (this.previewing)
          this.camera.stopPreview();
        this.cameraAcquirer.releaseCamera(this.camera);
        this.camera = null;
        this.cameraParameters = null;
      }
      this.pendingStartPreview = false;
      this.previewing = false;
      this.releaseRequiredAfterFocus = false;
      this.snapRequiredAfterFocus = false;
      this.flashSettingAfterFocus = null;
      this.state = 0;
      return;
    }
    finally
    {
    }
  }

  public int getCameraRotation()
  {
    return this.cameraRotation;
  }

  public Executor getExecutor()
  {
    return this.executor;
  }

  public String getExpectedFlashMode()
  {
    if ((this.isFlashSupported == null) || (!this.isFlashSupported.booleanValue()))
      return "unsupported";
    if (this.flashSettingAfterFocus != null)
      return this.flashSettingAfterFocus;
    return this.flashMode;
  }

  public boolean getMirrored()
  {
    if (Build.VERSION.SDK_INT <= 8)
      logger.w("getMirrored() called on API <= 8!", new Object[0]);
    while ((this.cameraTypeProvider != null) && (!((String)this.cameraTypeProvider.get()).startsWith(RealCamera.class.getSimpleName())) && (!((String)this.cameraTypeProvider.get()).startsWith(FelixCamera.class.getSimpleName())))
      return false;
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    Camera.getCameraInfo(0, localCameraInfo);
    if (localCameraInfo.facing == 1);
    for (boolean bool = true; ; bool = false)
      return bool;
  }

  public Size getOptimalPreviewSize(int paramInt1, int paramInt2)
    throws RuntimeException
  {
    acquireCamera();
    ParametersProxy localParametersProxy = getCameraParameters();
    Size localSize;
    if ((this.camera == null) || (localParametersProxy == null))
      localSize = null;
    do
    {
      return localSize;
      if (this.camera.getClass() != RealCamera.class)
        break;
      localSize = getSpecialCasePreviewSize();
    }
    while (localSize != null);
    List localList = localParametersProxy.getSupportedPreviewSizes();
    if (localList == null)
      throw new RuntimeException("Failed to find preview sizes in camera parameters.");
    if (((Boolean)this.isContinuousProvider.get()).booleanValue());
    for (int i = 408960; ; i = 0)
      return findOptimalSize(localList, paramInt1, paramInt2, true, i);
  }

  public PictureQuality getPictureQuality()
  {
    return this.currentQualitySetting;
  }

  public Size getPictureSize()
  {
    ParametersProxy localParametersProxy = getCameraParameters();
    if (localParametersProxy == null)
      return null;
    return localParametersProxy.getPictureSize();
  }

  public Size getPreviewSize()
  {
    if (this.forcedPreviewSize != null)
      return this.forcedPreviewSize;
    ParametersProxy localParametersProxy = getCameraParameters();
    if (localParametersProxy == null)
      return null;
    return localParametersProxy.getPreviewSize();
  }

  public String getStateName()
  {
    switch (this.state)
    {
    default:
      return "unknown";
    case 2:
      return "FOCUSED";
    case 1:
      return "FOCUSING";
    case 0:
      return "IDLE";
    case 4:
      return "SNAPPED";
    case 3:
    }
    return "SNAPPING";
  }

  public void init(Provider<UnveilSensorProvider> paramProvider, Provider<Boolean> paramProvider1, Provider<String> paramProvider2, Provider<Map<String, String>> paramProvider3)
  {
    this.sensorProviderProvider = paramProvider;
    this.isContinuousProvider = paramProvider1;
    this.cameraTypeProvider = paramProvider2;
    this.cameraParamsProvider = paramProvider3;
    configureSurfaceHolder();
  }

  public void initDefaults()
  {
    init(new Provider()
    {
      public UnveilSensorProvider get()
      {
        return null;
      }
    }
    , new Provider()
    {
      public Boolean get()
      {
        return Boolean.valueOf(true);
      }
    }
    , new Provider()
    {
      public String get()
      {
        return RealCamera.class.getSimpleName();
      }
    }
    , new Provider()
    {
      public Map<String, String> get()
      {
        return new HashMap();
      }
    });
  }

  public boolean isCameraConnected()
  {
    try
    {
      CameraProxy localCameraProxy = this.camera;
      if (localCameraProxy != null)
      {
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  public boolean isContinuousFocusSupported()
  {
    if (this.isContinuousFocusSupported == null)
      return false;
    return this.isContinuousFocusSupported.booleanValue();
  }

  public boolean isFlashSupported()
  {
    if (this.isFlashSupported == null)
      return false;
    return this.isFlashSupported.booleanValue();
  }

  public boolean isFocusSupported()
  {
    if (this.isFocusSupported == null)
      return false;
    return this.isFocusSupported.booleanValue();
  }

  public boolean isFocusing()
  {
    return this.state == 1;
  }

  public boolean isPreviewing()
  {
    return this.previewing;
  }

  public void onCameraAcquired(CameraProxy paramCameraProxy)
  {
    try
    {
      logger.i("onCameraAcquired", new Object[0]);
      this.acquisitionPending = false;
      this.camera = paramCameraProxy;
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((Listener)localIterator.next()).onCameraConnected();
    }
    finally
    {
    }
    maybeSetPendingCameraParameters();
    if (this.pendingStartPreview)
    {
      logger.i("Starting preview!", new Object[0]);
      startPreview();
    }
  }

  public void onCameraAcquisitionError()
  {
    try
    {
      this.acquisitionPending = false;
      Iterator localIterator = this.listeners.iterator();
      while (localIterator.hasNext())
        ((Listener)localIterator.next()).onCameraAcquisitionError();
    }
    finally
    {
    }
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    if ((updateCurrentRotationAndOrientation(paramConfiguration.orientation)) && (this.previewing))
    {
      stopPreview();
      correctDisplayOrientation();
      startPreview();
    }
  }

  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = Integer.valueOf(paramInt3);
    arrayOfObject[4] = Integer.valueOf(paramInt4);
    localUnveilLogger.v("onLayout: %b, %d, %d, %d, %d", arrayOfObject);
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((Listener)localIterator.next()).onCameraLayoutComplete();
  }

  public void onPictureTaken(byte[] paramArrayOfByte, Camera paramCamera)
  {
    while (true)
    {
      try
      {
        maybeSetPendingCameraParameters();
        if (paramCamera == null)
          return;
        if (this.pictureListeners.isEmpty())
          break label105;
        Picture localPicture = PictureFactory.createJpeg(paramArrayOfByte, roundOffOrientation(((UnveilSensorProvider)this.sensorProviderProvider.get()).getRoundedDeviceOrientation()));
        Iterator localIterator = this.pictureListeners.iterator();
        if (localIterator.hasNext())
        {
          ((PictureCallback)localIterator.next()).onPictureTaken(localPicture);
          continue;
        }
      }
      finally
      {
      }
      this.pictureListeners.clear();
      label105: this.state = 0;
      if (this.pendingStartPreview)
        startPreview();
    }
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    localUnveilLogger.i("onWindowVisibilityChanged: %d", arrayOfObject);
    if (this.acquireCameraOnVisibilityChange)
    {
      if (paramInt == 0)
        acquireCamera();
    }
    else
      return;
    releaseCamera();
  }

  public void registerListener(Listener paramListener)
  {
    this.listeners.add(paramListener);
  }

  public void requestOneFrame(Camera.PreviewCallback paramPreviewCallback)
  {
    if (this.camera == null)
    {
      logger.w("No camera, can't comply with frame request.", new Object[0]);
      return;
    }
    synchronized (this.previewFetcherLock)
    {
      if ((this.previewFetcher != null) && (paramPreviewCallback != null) && (this.previewFetcher.previewCallback == paramPreviewCallback))
        break label108;
      if (this.previewFetcher != null)
      {
        this.previewFetcher.stop();
        this.previewFetcher = null;
      }
      if (paramPreviewCallback == null)
        return;
    }
    this.previewFetcher = new PreviewFetcher(this.camera, paramPreviewCallback, getPreviewSize());
    this.previewFetcher.start();
    label108: this.previewFetcher.requestFrame();
  }

  public void requestPictureQuality(PictureQuality paramPictureQuality)
  {
    this.currentQualitySetting = paramPictureQuality;
    setPictureQuality(paramPictureQuality);
  }

  public void setAcquireCameraOnVisibilityChange(boolean paramBoolean)
  {
    this.acquireCameraOnVisibilityChange = paramBoolean;
  }

  public void setCameraProxy(CameraProxy paramCameraProxy)
  {
    this.camera = paramCameraProxy;
    this.cameraParameters = null;
    try
    {
      configureCameraAndStartPreview(640, 480);
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  public void setFlashMode(String paramString)
  {
    if ((this.camera == null) || (paramString == null) || ((!paramString.equals("off")) && (!paramString.equals("torch"))));
    while (true)
    {
      return;
      if (this.state == 1)
      {
        logger.w("Deferring flash setting until focus complete.", new Object[0]);
        this.flashSettingAfterFocus = paramString;
        return;
      }
      if ((this.isFlashSupported != null) && (this.isFlashSupported.booleanValue()))
      {
        this.flashMode = paramString;
        ParametersProxy localParametersProxy = getCameraParameters();
        localParametersProxy.set("flash-mode", paramString);
        Iterator localIterator;
        try
        {
          setCameraParameters(localParametersProxy);
          return;
        }
        catch (RuntimeException localRuntimeException)
        {
          logger.e(localRuntimeException, "Unable to set flash mode to: %s", new Object[] { paramString });
          localIterator = this.listeners.iterator();
        }
        while (localIterator.hasNext())
          ((Listener)localIterator.next()).onCameraFlashControlError();
      }
    }
  }

  public void setForcedPreviewSize(Size paramSize)
  {
    this.forcedPreviewSize = paramSize;
  }

  public void setFullScreenDisplaySize(Size paramSize)
  {
    try
    {
      this.deviceFullScreenDisplaySize = paramSize;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void setZoomLevel(int paramInt)
  {
    while (true)
    {
      try
      {
        if ((this.camera == null) || (!isPreviewing()))
        {
          logger.e("Too early to zoom!", new Object[0]);
          return;
        }
        if (!this.zoomSupported)
        {
          logger.w("Zooming not supported!", new Object[0]);
          continue;
        }
      }
      finally
      {
      }
      if (paramInt > this.maxZoomLevel)
      {
        UnveilLogger localUnveilLogger2 = logger;
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = Integer.valueOf(paramInt);
        arrayOfObject2[1] = Integer.valueOf(this.maxZoomLevel);
        localUnveilLogger2.e("Zoom is too great! %d requested, max is %d", arrayOfObject2);
      }
      else if (paramInt != this.currentZoomLevel)
      {
        UnveilLogger localUnveilLogger1 = logger;
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = Integer.valueOf(paramInt);
        localUnveilLogger1.v("Setting zoom level to %d", arrayOfObject1);
        this.camera.startSmoothZoom(paramInt);
        this.currentZoomLevel = paramInt;
      }
    }
  }

  public void startPreview()
  {
    while (true)
    {
      try
      {
        logger.i("Starting preview!", new Object[0]);
        acquireCamera();
        if (this.camera == null)
        {
          if (this.acquisitionPending)
          {
            logger.w("Deferring startPreview due to acquisitionPending.", new Object[0]);
            this.pendingStartPreview = true;
            return;
          }
          logger.e("Failed to acquire camera, can't start preview", new Object[0]);
          continue;
        }
      }
      finally
      {
      }
      if (isTakingPicture())
      {
        logger.w("Deferring startPreview due to picture taking.", new Object[0]);
        this.pendingStartPreview = true;
      }
      else if (isFocusing())
      {
        logger.w("Deferring startPreview due to focusing.", new Object[0]);
        this.pendingStartPreview = true;
      }
      else
      {
        if (!this.previewing)
          break;
        logger.i("Already previewing.", new Object[0]);
        this.pendingStartPreview = false;
      }
    }
    int i;
    int j;
    if (this.deviceFullScreenDisplaySize == null)
    {
      i = getWidth();
      j = getHeight();
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Integer.valueOf(i);
      arrayOfObject2[1] = Integer.valueOf(j);
      localUnveilLogger2.e("Full display size is null, falling back to CameraManager view size of %dx%d.", arrayOfObject2);
    }
    while (true)
    {
      label216: logger.w("Too early to preview, no device size or CameraManager View size known yet.", new Object[0]);
      this.pendingStartPreview = true;
      break;
      i = this.deviceFullScreenDisplaySize.width;
      j = this.deviceFullScreenDisplaySize.height;
      UnveilLogger localUnveilLogger1 = logger;
      Object[] arrayOfObject1 = new Object[2];
      arrayOfObject1[0] = Integer.valueOf(i);
      arrayOfObject1[1] = Integer.valueOf(j);
      localUnveilLogger1.i("Using full display size of %dx%d to pick preview size.", arrayOfObject1);
      label370: 
      do
      {
        Iterator localIterator;
        try
        {
          if (this.currentOrientation != 1)
            break label370;
          configureCameraAndStartPreview(j, i);
        }
        catch (IOException localIOException)
        {
          logger.e(localIOException, "Unable to acquire/configure camera hardware.", new Object[0]);
          releaseCamera();
          localIterator = this.listeners.iterator();
        }
        while (localIterator.hasNext())
          ((Listener)localIterator.next()).onCameraAcquisitionError();
        break;
        configureCameraAndStartPreview(i, j);
        break;
        if (i == 0)
          break label216;
      }
      while (j != 0);
    }
  }

  public void stopPreview()
  {
    try
    {
      if (this.camera == null)
        logger.e("Can't stop preview on a null camera.", new Object[0]);
      while (true)
      {
        return;
        this.camera.stopPreview();
        this.pendingStartPreview = false;
        this.previewing = false;
        this.releaseRequiredAfterFocus = false;
        this.snapRequiredAfterFocus = false;
        this.flashSettingAfterFocus = null;
        this.state = 0;
      }
    }
    finally
    {
    }
  }

  public void surfaceChanged(SurfaceHolder paramSurfaceHolder, int paramInt1, int paramInt2, int paramInt3)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Size.dimensionsAsString(paramInt2, paramInt3);
    localUnveilLogger.i("surfaceChanged: %s", arrayOfObject);
    if (getVisibility() == 0)
    {
      acquireCamera();
      if (this.camera == null)
      {
        logger.e("Failed to acquire camera before setting preview display", new Object[0]);
        return;
      }
      try
      {
        if (this.textureView != null)
        {
          this.camera.setPreviewTexture(this.textureView);
          return;
        }
      }
      catch (IOException localIOException)
      {
        logger.e(localIOException, "Failed to set preview display", new Object[0]);
        return;
      }
      this.camera.setPreviewDisplay(getHolder());
      return;
    }
    releaseCamera();
  }

  public void surfaceCreated(SurfaceHolder paramSurfaceHolder)
  {
  }

  public void surfaceDestroyed(SurfaceHolder paramSurfaceHolder)
  {
    releaseCamera();
  }

  public void takePicture(ShutterCallback paramShutterCallback, PictureCallback paramPictureCallback)
  {
    while (true)
    {
      try
      {
        CameraProxy localCameraProxy = this.camera;
        if (localCameraProxy == null)
          return;
        if (isTakingPicture())
          continue;
        if (paramShutterCallback != null)
          this.shutterListeners.add(paramShutterCallback);
        if (paramPictureCallback != null)
          this.pictureListeners.add(paramPictureCallback);
        if (this.state == 3)
          continue;
        if ((this.state == 1) || (this.state == 2))
        {
          this.snapRequiredAfterFocus = true;
          continue;
        }
      }
      finally
      {
      }
      this.state = 3;
      logger.time("Before taking picture via hardware camera.", new Object[0]);
      this.previewing = false;
      Camera.ShutterCallback local10 = new Camera.ShutterCallback()
      {
        public void onShutter()
        {
          synchronized (CameraManager.this)
          {
            CameraManager.access$002(CameraManager.this, 4);
            Iterator localIterator = CameraManager.this.shutterListeners.iterator();
            if (localIterator.hasNext())
              ((CameraManager.ShutterCallback)localIterator.next()).onShutter();
          }
          CameraManager.this.shutterListeners.clear();
        }
      };
      this.camera.takePicture(local10, null, this);
      logger.time("After taking picture via hardware camera.", new Object[0]);
    }
  }

  public void takePictureFromFrame(Picture paramPicture, PictureCallback paramPictureCallback)
  {
    paramPicture.setOrientation(roundOffOrientation(((UnveilSensorProvider)this.sensorProviderProvider.get()).getRoundedDeviceOrientation()));
    paramPictureCallback.onPictureTaken(paramPicture);
  }

  public void takePictureFromFrame(TimestampedFrame paramTimestampedFrame, PictureCallback paramPictureCallback)
  {
    int i = roundOffOrientation(((UnveilSensorProvider)this.sensorProviderProvider.get()).getRoundedDeviceOrientation());
    takePictureFromFrame(PictureFactory.createBitmap(paramTimestampedFrame.getRawData(), paramTimestampedFrame.getWidth(), paramTimestampedFrame.getHeight(), i), paramPictureCallback);
  }

  public Rect transformToPictureCoordinates(Rect paramRect)
  {
    return transformToPictureCoordinatesInner(paramRect, getWidth(), getHeight());
  }

  Rect transformToPictureCoordinatesInner(Rect paramRect, int paramInt1, int paramInt2)
  {
    if (this.camera == null)
      return null;
    Size localSize1 = getPreviewSize();
    Size localSize2 = getPictureSize();
    Rect localRect1 = null;
    if (paramRect != null)
    {
      localRect1 = new Rect(paramRect);
      float f1 = localSize1.width / paramInt1;
      float f2 = localSize1.height / paramInt2;
      int i = (int)(f1 * localRect1.left);
      localRect1.left = i;
      int j = (int)(f1 * localRect1.right);
      localRect1.right = j;
      int k = (int)(f2 * localRect1.top);
      localRect1.top = k;
      int m = (int)(f2 * localRect1.bottom);
      localRect1.bottom = m;
    }
    int n = localSize2.width;
    int i1 = localSize2.height;
    int i2 = localSize1.width;
    int i3 = localSize1.height;
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(n);
    arrayOfObject1[1] = Integer.valueOf(i1);
    localUnveilLogger1.v("pictureMajorAxis: %d pictureMinorAxis %d", arrayOfObject1);
    UnveilLogger localUnveilLogger2 = logger;
    Object[] arrayOfObject2 = new Object[2];
    arrayOfObject2[0] = Integer.valueOf(i2);
    arrayOfObject2[1] = Integer.valueOf(i3);
    localUnveilLogger2.v("previewMajorAxis: %d previewMinorAxis: %d", arrayOfObject2);
    float f3 = Math.min(n / i2, i1 / i3);
    int i4;
    int i5;
    if (f3 > 1.0F)
    {
      UnveilLogger localUnveilLogger6 = logger;
      Object[] arrayOfObject6 = new Object[1];
      arrayOfObject6[0] = Float.valueOf(f3);
      localUnveilLogger6.v("scale up factor: %f", arrayOfObject6);
      if (paramRect == null)
        i4 = Math.round(f3 * i2);
      for (i5 = Math.round(f3 * i3); ; i5 = Math.round(f3 * localRect1.height()))
      {
        UnveilLogger localUnveilLogger4 = logger;
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = Integer.valueOf(i4);
        arrayOfObject4[1] = Integer.valueOf(i5);
        localUnveilLogger4.v("newMajorAxis: %d newMinorAxis: %d", arrayOfObject4);
        int i6 = (n - i4) / 2;
        int i7 = (i1 - i5) / 2;
        int i8 = i6 + i4;
        int i9 = i7 + i5;
        UnveilLogger localUnveilLogger5 = logger;
        Object[] arrayOfObject5 = new Object[4];
        arrayOfObject5[0] = Integer.valueOf(i6);
        arrayOfObject5[1] = Integer.valueOf(i7);
        arrayOfObject5[2] = Integer.valueOf(i8);
        arrayOfObject5[3] = Integer.valueOf(i9);
        localUnveilLogger5.v("left: %d top: %d right: %d bottom: %d", arrayOfObject5);
        Rect localRect2 = new Rect(i6, i7, i8, i9);
        return localRect2;
        i4 = Math.round(f3 * localRect1.width());
      }
    }
    float f4 = n / i1;
    if (paramRect == null);
    for (float f5 = i2 / i3 / f4; ; f5 = localRect1.width() / localRect1.height() / f4)
    {
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Float.valueOf(f5);
      localUnveilLogger3.v("fix aspect ratio factor: %f", arrayOfObject3);
      i4 = n;
      i5 = Math.round(i1 / f5);
      break;
    }
  }

  public void unregisterListener(Listener paramListener)
  {
    this.listeners.remove(paramListener);
  }

  public void useTextureView(PlatformVersionUtils.TextureView paramTextureView)
  {
    this.textureView = paramTextureView;
  }

  public static abstract interface FocusCallback
  {
    public abstract void onFocus(boolean paramBoolean);
  }

  public static abstract interface Listener
  {
    public abstract void onCameraAcquisitionError();

    public abstract void onCameraConnected();

    public abstract void onCameraFlashControlError();

    public abstract void onCameraLayoutComplete();

    public abstract void onCameraPreviewSizeChanged();

    public abstract void onCameraQualityError();
  }

  public static abstract interface PictureCallback
  {
    public abstract void onPictureTaken(Picture paramPicture);
  }

  public static class PictureQuality
  {
    public static final PictureQuality HIGH_QUALITY = new PictureQuality(1600, 1200, 95, 100);
    public static final PictureQuality NORMAL_QUALITY = new PictureQuality(512, 384, 95, 95);
    public static final PictureQuality PRIME_QUALITY = new PictureQuality(4096, 4096, 95, 100);
    public final int desiredHeight;
    public final int desiredWidth;
    public final int jpegQuality;
    public final int pixelCount;
    public final int recompressJpegQuality;

    public PictureQuality(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      this.desiredWidth = paramInt1;
      this.desiredHeight = paramInt2;
      this.pixelCount = (paramInt1 * paramInt2);
      this.jpegQuality = paramInt3;
      this.recompressJpegQuality = paramInt4;
    }

    public String toString()
    {
      return "[desiredWidth=" + this.desiredWidth + "," + "desiredHeight=" + this.desiredHeight + "," + "jpegQuality=" + this.jpegQuality + "," + "recompressJpegQuality=" + this.recompressJpegQuality + "]";
    }
  }

  public static abstract interface ShutterCallback
  {
    public abstract void onShutter();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.CameraManager
 * JD-Core Version:    0.6.2
 */