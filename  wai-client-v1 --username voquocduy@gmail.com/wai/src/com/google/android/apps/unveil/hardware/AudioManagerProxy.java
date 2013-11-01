package com.google.android.apps.unveil.hardware;

import android.content.Context;
import android.media.AudioManager;

public class AudioManagerProxy
{
  private AudioManager audioManager;

  public AudioManagerProxy(Context paramContext)
  {
    if (paramContext == null)
      return;
    this.audioManager = ((AudioManager)paramContext.getSystemService("audio"));
  }

  public void playSoundEffect(int paramInt)
  {
    this.audioManager.playSoundEffect(paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.hardware.AudioManagerProxy
 * JD-Core Version:    0.6.2
 */