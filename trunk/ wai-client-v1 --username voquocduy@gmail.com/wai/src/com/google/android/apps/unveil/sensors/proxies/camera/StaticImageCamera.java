package com.google.android.apps.unveil.sensors.proxies.camera;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Matrix.ScaleToFit;
import android.graphics.RectF;
import android.os.Handler;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.text.TextUtils;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.ResourceUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class StaticImageCamera extends FakeCamera
{
  private static final String LOCAL_ROOT_PATH = "/sdcard/goggles_data/static_images";
  public static final String STATIC_IMAGE_DIRECTORY = "static_images";
  public static final String STATIC_IMAGE_KEY = "static_image";
  private static final UnveilLogger logger = new UnveilLogger();
  private Bitmap internalBitmap;
  private int rVal = 0;
  private FakeCamera.RawFrame rawFrame;
  private Bitmap scaledImage;
  private int step = -1;

  private StaticImageCamera(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    super(paramHandler, paramMap, paramResources);
  }

  private static Bitmap createScaledImage(Bitmap paramBitmap, int paramInt1, int paramInt2)
  {
    RectF localRectF1 = new RectF(0.0F, 0.0F, -1 + paramBitmap.getWidth(), -1 + paramBitmap.getHeight());
    RectF localRectF2 = new RectF(0.0F, 0.0F, paramInt1 - 1, paramInt2 - 1);
    Matrix localMatrix = new Matrix();
    localMatrix.setRectToRect(localRectF1, localRectF2, Matrix.ScaleToFit.START);
    localMatrix.mapRect(localRectF1);
    return Bitmap.createScaledBitmap(paramBitmap, (int)localRectF1.width(), (int)localRectF1.height(), true);
  }

  private static ImageSequenceCamera.StringPair[] getStaticImages(PreferenceActivity paramPreferenceActivity)
  {
    ArrayList localArrayList = new ArrayList();
    String[] arrayOfString1 = new File("/sdcard/goggles_data/static_images").list();
    if (arrayOfString1 != null)
    {
      int k = arrayOfString1.length;
      for (int m = 0; m < k; m++)
      {
        String str2 = arrayOfString1[m];
        localArrayList.add(new ImageSequenceCamera.StringPair("sdcard:" + str2, "sdcard:/sdcard/goggles_data/static_images/" + str2));
      }
    }
    try
    {
      String[] arrayOfString2 = paramPreferenceActivity.getAssets().list("static_images");
      if (arrayOfString2 != null)
      {
        int i = arrayOfString2.length;
        for (int j = 0; j < i; j++)
        {
          String str1 = arrayOfString2[j];
          localArrayList.add(new ImageSequenceCamera.StringPair("assets:" + str1, "assets:static_images/" + str1));
        }
      }
    }
    catch (IOException localIOException)
    {
      logger.e("Couldn't list assets directory!", new Object[0]);
    }
    return (ImageSequenceCamera.StringPair[])localArrayList.toArray(new ImageSequenceCamera.StringPair[localArrayList.size()]);
  }

  public static void initializePreferences(PreferenceActivity paramPreferenceActivity)
  {
    ImageSequenceCamera.StringPair[] arrayOfStringPair = getStaticImages(paramPreferenceActivity);
    if (arrayOfStringPair.length == 0)
    {
      paramPreferenceActivity.findPreference(paramPreferenceActivity.getString(R.string.camera_static_image_key)).setEnabled(false);
      return;
    }
    ListPreference localListPreference = (ListPreference)paramPreferenceActivity.findPreference(paramPreferenceActivity.getString(R.string.camera_static_image_key));
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int i = arrayOfStringPair.length;
    for (int j = 0; j < i; j++)
    {
      ImageSequenceCamera.StringPair localStringPair = arrayOfStringPair[j];
      localArrayList1.add(localStringPair.first);
      localArrayList2.add(localStringPair.second);
    }
    localListPreference.setEntries((CharSequence[])localArrayList1.toArray(new String[localArrayList1.size()]));
    localListPreference.setEntryValues((CharSequence[])localArrayList2.toArray(new String[localArrayList1.size()]));
  }

  public static CameraProxy open(Handler paramHandler, Map<String, String> paramMap, Resources paramResources)
  {
    if (camera == null)
      camera = new StaticImageCamera(paramHandler, paramMap, paramResources);
    return camera;
  }

  protected FakeCamera.RawFrame generateFrame()
  {
    int i = getWidth();
    int j = getHeight();
    String str1;
    Object localObject;
    if (this.internalBitmap == null)
    {
      this.internalBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ARGB_8888);
      str1 = getExtraValue("static_image", "");
      if (!TextUtils.isEmpty(str1))
        break label254;
      logger.i("Loading default image.", new Object[0]);
      localObject = BitmapFactory.decodeStream(this.resources.openRawResource(R.drawable.android_logo));
    }
    while (true)
    {
      this.scaledImage = createScaledImage((Bitmap)localObject, getWidth(), getHeight());
      ((Bitmap)localObject).recycle();
      this.rawFrame = new FakeCamera.RawFrame(this, this.internalBitmap);
      Canvas localCanvas = new Canvas(this.internalBitmap);
      localCanvas.drawARGB(255, this.rVal, this.rVal / 2, this.rVal);
      float f1 = this.rVal / 255.0F * i / 5.0F;
      float f2 = this.rVal / 255.0F * j / 5.0F;
      localCanvas.drawBitmap(this.scaledImage, f1, f2, null);
      this.rawFrame.loadRgb(this.internalBitmap);
      if ((this.rVal == 254) || (this.rVal == 0))
        this.step = (-1 * this.step);
      this.rVal = ((this.rVal + this.step) % 255);
      return this.rawFrame;
      label254: logger.i("Loading image %s.", new Object[] { str1 });
      boolean bool = ResourceUtils.fromAssets(str1);
      String str2;
      if (bool)
        str2 = ResourceUtils.getPathFromPrefixedString(str1);
      try
      {
        while (true)
        {
          Bitmap localBitmap = BitmapFactory.decodeStream(ResourceUtils.getInputStreamForFile(this.resources, bool, str2));
          localObject = localBitmap;
          break;
          str2 = str1;
        }
      }
      catch (IOException localIOException)
      {
        logger.e(localIOException, "Failed to decode asset %s; switching to default image", new Object[] { str2 });
        localObject = BitmapFactory.decodeStream(this.resources.openRawResource(R.drawable.android_logo));
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.sensors.proxies.camera.StaticImageCamera
 * JD-Core Version:    0.6.2
 */