package com.google.android.apps.unveil.nonstop;

import android.os.Environment;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameSaver extends FrameProcessor
{
  private static final long INTER_SAVE_DELAY_MS = 200L;
  private static final String PREVIEW_DIR = "/goggles_sessions";
  private static final boolean SAVE_PREVIEW;
  private final UnveilLogger logger = new UnveilLogger();
  private Stopwatch saveTimer;
  private String sessionDir;

  private String createSessionDir()
  {
    Date localDate = new Date();
    String str1 = new SimpleDateFormat("yyyy_MM_dd").format(localDate);
    String str2 = new SimpleDateFormat("hh_mm_ss").format(localDate);
    File localFile = new File(Environment.getExternalStorageDirectory().getPath() + "/goggles_sessions" + "/" + str1 + "/" + str2);
    UnveilLogger localUnveilLogger = this.logger;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = localFile.getAbsolutePath();
    localUnveilLogger.i("Creating session dir: %s", arrayOfObject);
    localFile.mkdirs();
    return localFile.getAbsolutePath();
  }

  private void saveFrameToDisk(TimestampedFrame paramTimestampedFrame)
  {
    StringBuilder localStringBuilder1 = new StringBuilder().append("previewframe-");
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramTimestampedFrame.getFrameNum());
    StringBuilder localStringBuilder2 = localStringBuilder1.append(String.format("%04d", arrayOfObject1)).append("-");
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(paramTimestampedFrame.getOpticalFlowDelta());
    String str1 = String.format("%03d", arrayOfObject2) + ".yuv";
    String str2 = this.sessionDir + "/" + str1;
    this.logger.v("Saving file: %s", new Object[] { str2 });
    File localFile = new File(str2);
    byte[] arrayOfByte = paramTimestampedFrame.getRawData();
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      localFileOutputStream.write(arrayOfByte);
      localFileOutputStream.close();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      localFileNotFoundException.printStackTrace();
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  public void onInit(Size paramSize)
  {
  }

  public void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.FrameSaver
 * JD-Core Version:    0.6.2
 */