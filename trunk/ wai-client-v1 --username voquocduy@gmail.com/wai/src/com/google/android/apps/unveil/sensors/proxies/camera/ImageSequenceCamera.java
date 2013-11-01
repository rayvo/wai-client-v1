package com.google.android.apps.unveil.sensors.proxies.camera;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Handler;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Pair;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.StreamLoader;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ImageSequenceCamera extends FakeCamera
{
  public static final String IMAGE_SEQUENCE_DIRECTORY_KEY = "image_sequence_directory";
  public static final String JPEG_EXTENSION = ".jpg";
  private static final int MAX_CACHE_BYTE_SIZE = 12582912;
  public static final String PNG_EXTENSION = ".png";
  public static final String SEQUENCES_DIRECTORY = "image_sequences";
  public static final String SEQUENCES_LOCAL_PATH = "/sdcard/goggles_data/image_sequences";
  private static final UnveilLogger logger = new UnveilLogger();
  private String[] allFiles;
  private int fileNumber = 0;
  private boolean fromAssets;
  private Map<String, FakeCamera.RawFrame> imageCache;
  private final StreamLoader loader = new StreamLoader();
  private FakeCamera.RawFrame rawFrame;
  private Size rawFrameSize;
  private String sequencePath;

  private ImageSequenceCamera(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    super(paramHandler, paramMap, paramResources);
    String str = getExtraValue("image_sequence_directory", "");
    this.fromAssets = ResourceUtils.fromAssets(str);
    this.sequencePath = ResourceUtils.getPathFromPrefixedString(str);
    UnveilLogger localUnveilLogger1 = logger;
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = this.sequencePath;
    localUnveilLogger1.i("Loading image sequence from %s", arrayOfObject1);
    try
    {
      this.allFiles = ResourceUtils.listFiles(paramResources, this.fromAssets, this.sequencePath, new FilenameFilter()
      {
        public boolean accept(File paramAnonymousFile, String paramAnonymousString)
        {
          return (paramAnonymousString.contains(".jpg")) || (paramAnonymousString.contains(".png"));
        }
      });
      Arrays.sort(this.allFiles);
      UnveilLogger localUnveilLogger2 = logger;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(this.allFiles.length);
      localUnveilLogger2.i("Found %d image files", arrayOfObject2);
      ParametersProxy localParametersProxy = getParameters();
      this.rawFrameSize = getImageSize(this.allFiles[0]);
      localParametersProxy.set("preview-size-values", this.rawFrameSize.toString());
      UnveilLogger localUnveilLogger3 = logger;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = this.rawFrameSize;
      localUnveilLogger3.v("Set size to: %s", arrayOfObject3);
      setParameters(localParametersProxy);
      if ((this.allFiles.length * ImageUtils.getYUVByteSize(this.rawFrameSize.width, this.rawFrameSize.height) <= 12582912) && (((String)paramMap.get("skip_rendering")).equals("true")) && (!((String)paramMap.get("lockstep_callbacks")).equals("true")))
        this.imageCache = new HashMap();
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Invalid directory given: " + str, localIOException);
    }
  }

  private boolean decodeJpeg(byte[] paramArrayOfByte, FakeCamera.RawFrame paramRawFrame)
  {
    boolean bool = true;
    int[] arrayOfInt = new int[2];
    ImageUtils.decodeJpegToYUV420SP(paramArrayOfByte, paramRawFrame.getYuvDataAndClearRgbData(), arrayOfInt);
    Size localSize = paramRawFrame.frameSize;
    if ((arrayOfInt[0] != localSize.width) || (arrayOfInt[bool] != localSize.height))
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = localSize;
      arrayOfObject[bool] = Integer.valueOf(arrayOfInt[0]);
      arrayOfObject[2] = Integer.valueOf(arrayOfInt[bool]);
      localUnveilLogger.w("Invalid dimensions for image. Expected %s but got %d x %d", arrayOfObject);
      bool = false;
    }
    return bool;
  }

  private boolean decodePng(byte[] paramArrayOfByte, FakeCamera.RawFrame paramRawFrame)
  {
    Bitmap localBitmap = BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length);
    if (localBitmap == null)
      return false;
    Size localSize = paramRawFrame.frameSize;
    if ((localBitmap.getWidth() != localSize.width) || (localBitmap.getHeight() != localSize.height))
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = this.rawFrameSize;
      arrayOfObject[1] = Integer.valueOf(localBitmap.getWidth());
      arrayOfObject[2] = Integer.valueOf(localBitmap.getHeight());
      localUnveilLogger.e("Invalid dimensions for image. Expected %s but got %d x %d", arrayOfObject);
      return false;
    }
    localBitmap.getPixels(paramRawFrame.getRgbData(), 0, localSize.width, 0, 0, localSize.width, localSize.height);
    localBitmap.recycle();
    return true;
  }

  private byte[] getBytesForImage(String paramString)
  {
    try
    {
      InputStream localInputStream = ResourceUtils.getInputStreamForFile(this.resources, this.fromAssets, paramString);
      this.loader.reset();
      byte[] arrayOfByte = this.loader.read(localInputStream);
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Couldn't open stream: " + paramString, localIOException);
    }
  }

  public static Pair<String, String>[] getImageSequenceDirectories(Activity paramActivity)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString1 = new File("/sdcard/goggles_data/image_sequences").list();
    if (arrayOfString1 != null)
    {
      int k = arrayOfString1.length;
      for (int m = 0; m < k; m++)
      {
        String str2 = arrayOfString1[m];
        localArrayList.add(new StringPair("sdcard:" + str2, "sdcard:/sdcard/goggles_data/image_sequences/" + str2));
      }
    }
    try
    {
      String[] arrayOfString2 = paramActivity.getAssets().list("image_sequences");
      if (arrayOfString2 != null)
      {
        int i = arrayOfString2.length;
        for (int j = 0; j < i; j++)
        {
          String str1 = arrayOfString2[j];
          localArrayList.add(new StringPair("assets:" + str1, "assets:image_sequences/" + str1));
        }
      }
    }
    catch (IOException localIOException)
    {
      logger.e("Couldn't list assets directory!", new Object[0]);
      StringPair[] arrayOfStringPair = (StringPair[])localArrayList.toArray(new StringPair[localArrayList.size()]);
      Arrays.sort(arrayOfStringPair);
      return arrayOfStringPair;
    }
  }

  private Size getImageSize(String paramString)
  {
    try
    {
      InputStream localInputStream = ResourceUtils.getInputStreamForFile(this.resources, this.fromAssets, paramString);
      BitmapFactory.Options localOptions = new BitmapFactory.Options();
      localOptions.inJustDecodeBounds = true;
      BitmapFactory.decodeStream(localInputStream, null, localOptions);
      localInputStream.close();
      Size localSize = new Size(localOptions.outWidth, localOptions.outHeight);
      return localSize;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Couldn't open stream!", localIOException);
    }
  }

  public static void initializePreferences(PreferenceActivity paramPreferenceActivity)
  {
    Pair[] arrayOfPair = getImageSequenceDirectories(paramPreferenceActivity);
    if (arrayOfPair.length == 0)
    {
      paramPreferenceActivity.findPreference(paramPreferenceActivity.getString(R.string.camera_image_sequence_dir_key)).setEnabled(false);
      return;
    }
    ListPreference localListPreference = (ListPreference)paramPreferenceActivity.findPreference(paramPreferenceActivity.getString(R.string.camera_image_sequence_dir_key));
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = arrayOfPair.length;
    for (int j = 0; j < i; j++)
    {
      Pair localPair = arrayOfPair[j];
      localArrayList1.add(localPair.first);
      localArrayList2.add(localPair.second);
    }
    localListPreference.setEntries((CharSequence[])localArrayList1.toArray(new String[localArrayList1.size()]));
    localListPreference.setEntryValues((CharSequence[])localArrayList2.toArray(new String[localArrayList1.size()]));
  }

  public static CameraProxy open(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    if ((camera == null) || (((String)paramMap.get("lockstep_callbacks")).equals("true")))
      camera = new ImageSequenceCamera(paramHandler, paramMap, paramResources);
    return camera;
  }

  protected FakeCamera.RawFrame generateFrame()
  {
    String str = this.allFiles[this.fileNumber];
    this.fileNumber = ((1 + this.fileNumber) % this.allFiles.length);
    if ((this.imageCache != null) && (this.imageCache.containsKey(str)))
      return (FakeCamera.RawFrame)this.imageCache.get(str);
    byte[] arrayOfByte = getBytesForImage(str);
    if (this.rawFrame == null)
      this.rawFrame = new FakeCamera.RawFrame(this, this.rawFrameSize.width, this.rawFrameSize.height, new byte[ImageUtils.getYUVByteSize(this.rawFrameSize.width, this.rawFrameSize.height)]);
    if (str.endsWith(".jpg"))
    {
      if (!decodeJpeg(arrayOfByte, this.rawFrame))
        throw new RuntimeException("Jpeg decoding failed for " + str);
    }
    else if ((str.endsWith(".png")) && (!decodePng(arrayOfByte, this.rawFrame)))
      throw new RuntimeException("Png decoding failed for " + str);
    FakeCamera.RawFrame localRawFrame = this.rawFrame;
    if (this.imageCache != null)
    {
      this.imageCache.put(str, this.rawFrame);
      this.rawFrame = null;
    }
    return localRawFrame;
  }

  static class StringPair extends Pair<String, String>
    implements Comparable<StringPair>
  {
    public StringPair(String paramString1, String paramString2)
    {
      super(paramString2);
    }

    public int compareTo(StringPair paramStringPair)
    {
      return ((String)this.first).compareTo((String)paramStringPair.first);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.ImageSequenceCamera
 * JD-Core Version:    0.6.2
 */