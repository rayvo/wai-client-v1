package com.google.android.apps.unveil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.proxies.camera.ImageSequenceCamera;
import java.util.ArrayList;

public class TrackingSequenceActivity extends Activity
{
  private static final int REQUEST_CODE = 42;
  private static final boolean USE_GL_RENDERER;
  private String activeSequence;
  private float averagePerformance;
  private float averageTimePerFrame;
  private final UnveilLogger logger = new UnveilLogger();
  private int numSequences;
  private boolean resultReceived;

  private String getCameraString(String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(keyValue("image_sequence_directory", paramString));
    localArrayList.add(keyValue("lockstep_callbacks", "true"));
    localArrayList.add(keyValue("skip_rendering", "false"));
    String str = TextUtils.join(",", localArrayList);
    return ImageSequenceCamera.class.getSimpleName() + "[" + str + "]";
  }

  private String keyValue(String paramString1, String paramString2)
  {
    return paramString1 + "=" + paramString2;
  }

  private void startTestSequence(String paramString)
  {
    UnveilSettings localUnveilSettings = new UnveilSettings(2147483647, getCameraString(paramString), null, null, false, false, true, true);
    Intent localIntent = new Intent();
    localIntent.putExtra("UnveilSettings", localUnveilSettings);
    localIntent.setClass(getApplicationContext(), TrackingTestActivity.class);
    startActivityForResult(localIntent, 42);
  }

  private void waitForResult()
  {
    try
    {
      while (true)
      {
        boolean bool = this.resultReceived;
        if (bool)
          break;
        try
        {
          wait();
        }
        catch (InterruptedException localInterruptedException)
        {
          this.logger.e("Exception!", new Object[] { localInterruptedException });
        }
      }
    }
    finally
    {
    }
    this.resultReceived = false;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 42)
    {
      if (paramInt2 == -1);
      try
      {
        this.resultReceived = true;
        float f1 = paramIntent.getFloatExtra("performance_key", -1.0F);
        float f2 = paramIntent.getFloatExtra("cpu_key", -1.0F);
        this.averagePerformance = (f1 + this.averagePerformance);
        this.averageTimePerFrame = (f2 + this.averageTimePerFrame);
        this.numSequences = (1 + this.numSequences);
        UnveilLogger localUnveilLogger2 = this.logger;
        Object[] arrayOfObject2 = new Object[6];
        arrayOfObject2[0] = Integer.valueOf(this.numSequences);
        arrayOfObject2[1] = this.activeSequence;
        arrayOfObject2[2] = Float.valueOf(f1);
        arrayOfObject2[3] = Float.valueOf(this.averagePerformance / this.numSequences);
        arrayOfObject2[4] = Float.valueOf(f2);
        arrayOfObject2[5] = Float.valueOf(this.averageTimePerFrame / this.numSequences);
        localUnveilLogger2.i(">>>>>>>>>>>>>>>>>>>>>>>>>>>> Finished sequence %4d %80s! Score: %1.4f score (%1.4f running average), %6.3f ms/frame (%6.3f running average)", arrayOfObject2);
        notify();
        while (true)
        {
          return;
          UnveilLogger localUnveilLogger1 = this.logger;
          Object[] arrayOfObject1 = new Object[1];
          arrayOfObject1[0] = this.activeSequence;
          localUnveilLogger1.e("Failure on sequence %s", arrayOfObject1);
          setResult(0);
          finish();
        }
      }
      finally
      {
      }
    }
  }

  public void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    new Thread(new Runnable()
    {
      public void run()
      {
        Pair[] arrayOfPair = ImageSequenceCamera.getImageSequenceDirectories(TrackingSequenceActivity.this);
        TrackingSequenceActivity.access$002(TrackingSequenceActivity.this, 0.0F);
        TrackingSequenceActivity.access$102(TrackingSequenceActivity.this, 0.0F);
        TrackingSequenceActivity.access$202(TrackingSequenceActivity.this, 0);
        int i = arrayOfPair.length;
        int j = 0;
        if (j < i)
        {
          Pair localPair = arrayOfPair[j];
          TrackingSequenceActivity.access$302(TrackingSequenceActivity.this, (String)localPair.second);
          if (((String)localPair.second).startsWith("asset"));
          while (true)
          {
            j++;
            break;
            UnveilLogger localUnveilLogger = TrackingSequenceActivity.this.logger;
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = TrackingSequenceActivity.this.activeSequence;
            localUnveilLogger.i("<<<<<<<<<<<< EVALUATING %s >>>>>>>>>>>>>>", arrayOfObject);
            TrackingSequenceActivity.access$502(TrackingSequenceActivity.this, false);
            TrackingSequenceActivity.this.startTestSequence(TrackingSequenceActivity.this.activeSequence);
            TrackingSequenceActivity.this.waitForResult();
          }
        }
        TrackingSequenceActivity.this.setResult(-1);
        TrackingSequenceActivity.this.finish();
      }
    }).start();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.TrackingSequenceActivity
 * JD-Core Version:    0.6.2
 */