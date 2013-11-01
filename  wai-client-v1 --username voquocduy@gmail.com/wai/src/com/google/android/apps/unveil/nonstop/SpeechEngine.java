package com.google.android.apps.unveil.nonstop;

import android.content.Context;
import android.provider.Settings.Secure;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech.OnUtteranceCompletedListener;
import android.widget.Toast;
import com.google.android.apps.unveil.env.AccessibilityUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.hardware.AudioManagerProxy;
import com.google.android.apps.unveil.hardware.VibratorProxy;
import java.util.EnumMap;
import java.util.HashMap;

public class SpeechEngine
{
  public static final String PING = "ping";
  private static final UnveilLogger logger = new UnveilLogger();
  private final AudioManagerProxy audioManager;
  private final boolean isSpeechEnabled;
  private boolean isTTSready = false;
  private final EnumMap<Slot, String> speechQueue = new EnumMap(Slot.class);
  private TextToSpeech textToSpeech;
  private final TextToSpeech.OnInitListener ttsInitCallback = new TextToSpeech.OnInitListener()
  {
    public void onInit(int paramAnonymousInt)
    {
      SpeechEngine.logger.v("onInit()", new Object[0]);
      while (true)
      {
        synchronized (SpeechEngine.this)
        {
          if (SpeechEngine.this.textToSpeech == null)
            return;
          if (paramAnonymousInt == 0)
          {
            SpeechEngine.this.textToSpeech.setOnUtteranceCompletedListener(new TextToSpeech.OnUtteranceCompletedListener()
            {
              public void onUtteranceCompleted(String paramAnonymous2String)
              {
                SpeechEngine.logger.v("onUtteranceCompleted(%s)", new Object[] { paramAnonymous2String });
                SpeechEngine.this.speakNextItem();
              }
            });
            SpeechEngine.access$302(SpeechEngine.this, true);
            SpeechEngine.logger.v("TextToSpeech is ready", new Object[0]);
            return;
          }
        }
        SpeechEngine.logger.e("TTS failed to initialize", new Object[0]);
      }
    }
  };
  private final VibratorProxy vibrator;

  public SpeechEngine(Context paramContext, int paramInt1, int paramInt2)
  {
    this.isSpeechEnabled = new AccessibilityUtils(paramContext, paramInt2).isSpokenAccessibilityEnabled();
    if (this.isSpeechEnabled)
    {
      String str = Settings.Secure.getString(paramContext.getContentResolver(), "enabled_accessibility_services");
      if ((str != null) && (!str.contains("com.google.android.marvin.talkback.TalkBackService")))
        Toast.makeText(paramContext, paramInt1, 1).show();
    }
    this.vibrator = new VibratorProxy(paramContext);
    this.audioManager = new AudioManagerProxy(paramContext);
  }

  protected SpeechEngine(boolean paramBoolean, VibratorProxy paramVibratorProxy, AudioManagerProxy paramAudioManagerProxy)
  {
    this.isSpeechEnabled = paramBoolean;
    this.vibrator = paramVibratorProxy;
    this.audioManager = paramAudioManagerProxy;
  }

  private void speakNextItem()
  {
    while (true)
    {
      int j;
      String str;
      HashMap localHashMap;
      try
      {
        if (!this.isTTSready)
        {
          logger.e("SpeechEngine.speakNextItem() when TextToSpeech wasn't ready.", new Object[0]);
          return;
        }
        if (this.speechQueue.isEmpty())
          continue;
        Slot[] arrayOfSlot = Slot.values();
        int i = arrayOfSlot.length;
        j = 0;
        str = null;
        if (j < i)
        {
          Slot localSlot = arrayOfSlot[j];
          if (this.speechQueue.containsKey(localSlot))
          {
            str = (String)this.speechQueue.get(localSlot);
            this.speechQueue.remove(localSlot);
          }
        }
        else
        {
          localHashMap = new HashMap();
          if (!str.equals("ping"))
            break label203;
          if (!this.vibrator.hasVibrator())
            break label192;
          this.vibrator.vibrate(100L);
          localHashMap.put("utteranceId", "ping");
          str = "";
          logger.v("speakNextItem(%s)", new Object[] { str });
          this.textToSpeech.speak(str, 1, localHashMap);
          continue;
        }
      }
      finally
      {
      }
      j++;
      continue;
      label192: this.audioManager.playSoundEffect(0);
      continue;
      label203: localHashMap.put("utteranceId", str);
    }
  }

  protected TextToSpeech constructTextToSpeech(Context paramContext, TextToSpeech.OnInitListener paramOnInitListener)
  {
    return new TextToSpeech(paramContext, paramOnInitListener);
  }

  public void finalize()
  {
    if (this.isTTSready)
      logger.e("SpeechEngine.shutdown() not called. Leaking android.speech.tts.TextToSpeech!!!!!!", new Object[0]);
  }

  public void say(Slot paramSlot, String paramString)
  {
    label104: 
    while (true)
    {
      try
      {
        boolean bool = this.isSpeechEnabled;
        if (!bool)
          return;
        if (!this.isTTSready)
        {
          logger.e("SpeechEngine.say() called before TextToSpeech is ready.", new Object[0]);
          continue;
        }
      }
      finally
      {
      }
      logger.v("say(%s, %s)", new Object[] { paramSlot, paramString });
      if (paramString == null)
        this.speechQueue.remove(paramSlot);
      while (true)
      {
        if (this.textToSpeech.isSpeaking())
          break label104;
        speakNextItem();
        break;
        this.speechQueue.put(paramSlot, paramString);
      }
    }
  }

  public void shutdown()
  {
    try
    {
      boolean bool = this.isSpeechEnabled;
      if (!bool);
      while (true)
      {
        return;
        logger.v("shutdown()", new Object[0]);
        this.textToSpeech.shutdown();
        this.textToSpeech = null;
        this.isTTSready = false;
      }
    }
    finally
    {
    }
  }

  public void startup(Context paramContext)
  {
    try
    {
      boolean bool = this.isSpeechEnabled;
      if (!bool);
      while (true)
      {
        return;
        logger.v("startup()", new Object[0]);
        this.textToSpeech = constructTextToSpeech(paramContext, this.ttsInitCallback);
      }
    }
    finally
    {
    }
  }

  public static enum Slot
  {
    static
    {
      RESULT = new Slot("RESULT", 1);
      SONAR_PING = new Slot("SONAR_PING", 2);
      Slot[] arrayOfSlot = new Slot[3];
      arrayOfSlot[0] = STATUS;
      arrayOfSlot[1] = RESULT;
      arrayOfSlot[2] = SONAR_PING;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.nonstop.SpeechEngine
 * JD-Core Version:    0.6.2
 */