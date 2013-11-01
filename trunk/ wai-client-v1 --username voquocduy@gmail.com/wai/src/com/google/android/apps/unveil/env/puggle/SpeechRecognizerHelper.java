package com.google.android.apps.unveil.env.puggle;

import android.content.Context;
import android.widget.TextView;
import java.util.List;

public class SpeechRecognizerHelper
{
  SpeechRecognizerListener speechRecognizerListener;

  public static final SpeechRecognizerHelper setup(Context paramContext, TextView paramTextView, SpeechRecognizerListener paramSpeechRecognizerListener)
  {
    FroyoSpeechRecognizerHelper localFroyoSpeechRecognizerHelper = new FroyoSpeechRecognizerHelper(paramContext, paramTextView);
    localFroyoSpeechRecognizerHelper.speechRecognizerListener = paramSpeechRecognizerListener;
    return localFroyoSpeechRecognizerHelper;
  }

  public static abstract interface SpeechRecognizerListener
  {
    public abstract void onRecognizedTextRestricts(List<String> paramList);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.puggle.SpeechRecognizerHelper
 * JD-Core Version:    0.6.2
 */