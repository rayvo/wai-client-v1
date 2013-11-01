package com.google.android.apps.unveil.env.puggle;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Collections;

public class FroyoSpeechRecognizerHelper extends SpeechRecognizerHelper
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Context context;
  private final SpeechRecognizer speechRecognizer;

  public FroyoSpeechRecognizerHelper(final Context paramContext, final TextView paramTextView)
  {
    this.context = paramContext;
    paramTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        if (paramTextView.getText().toString().contentEquals(paramContext.getResources().getString(R.string.speak)))
        {
          FroyoSpeechRecognizerHelper.this.speechRecognizer.startListening(new Intent());
          return;
        }
        FroyoSpeechRecognizerHelper.this.speechRecognizer.stopListening();
        paramTextView.setText(R.string.speak);
      }
    });
    this.speechRecognizer = SpeechRecognizer.createSpeechRecognizer(paramContext);
    this.speechRecognizer.setRecognitionListener(new TextRestrictRecognitionListener(paramTextView));
  }

  private class TextRestrictRecognitionListener
    implements RecognitionListener
  {
    private final TextView speechButton;

    public TextRestrictRecognitionListener(TextView arg2)
    {
      Object localObject;
      this.speechButton = localObject;
    }

    public void onBeginningOfSpeech()
    {
    }

    public void onBufferReceived(byte[] paramArrayOfByte)
    {
    }

    public void onEndOfSpeech()
    {
      this.speechButton.setText(R.string.analyzing);
    }

    public void onError(int paramInt)
    {
      FroyoSpeechRecognizerHelper.logger.i("onError" + paramInt, new Object[0]);
      this.speechButton.setText(R.string.speak);
      Toast.makeText(FroyoSpeechRecognizerHelper.this.context, R.string.no_speech_recognized, 0);
    }

    public void onEvent(int paramInt, Bundle paramBundle)
    {
    }

    public void onPartialResults(Bundle paramBundle)
    {
    }

    public void onReadyForSpeech(Bundle paramBundle)
    {
      this.speechButton.setText(R.string.listening);
    }

    public void onResults(Bundle paramBundle)
    {
      FroyoSpeechRecognizerHelper.logger.i("onResults", new Object[0]);
      ArrayList localArrayList = paramBundle.getStringArrayList("results_recognition");
      Collections.reverse(localArrayList);
      FroyoSpeechRecognizerHelper.this.speechRecognizerListener.onRecognizedTextRestricts(localArrayList);
      this.speechButton.setText(R.string.speak);
    }

    public void onRmsChanged(float paramFloat)
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.puggle.FroyoSpeechRecognizerHelper
 * JD-Core Version:    0.6.2
 */