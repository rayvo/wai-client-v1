package com.google.android.apps.unveil.env;

import android.media.ToneGenerator;
import android.util.SparseArray;

public class TonePlayer
{
  private static final int DEFAULT_VOLUME = 50;
  private static SparseArray<ToneGenerator> generators;
  private static final UnveilLogger logger = new UnveilLogger();

  public static void errorTone()
  {
  }

  private static ToneGenerator getGenerator(int paramInt1, int paramInt2)
  {
    if (generators == null)
      generators = new SparseArray();
    Integer localInteger = Integer.valueOf(paramInt2 + paramInt1 * 10000);
    ToneGenerator localToneGenerator = (ToneGenerator)generators.get(localInteger.intValue());
    if (localToneGenerator == null)
    {
      localToneGenerator = new ToneGenerator(8, paramInt2);
      generators.put(localInteger.intValue(), localToneGenerator);
    }
    return localToneGenerator;
  }

  public static void logTone(int paramInt1, int paramInt2)
  {
    logTone(paramInt1, paramInt2, 50, false);
  }

  public static void logTone(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    if (!paramBoolean)
      return;
    startTone(paramInt1, paramInt2, paramInt3);
  }

  private static void startTone(int paramInt1, int paramInt2, int paramInt3)
  {
    getGenerator(paramInt1, paramInt3).startTone(paramInt1, paramInt2);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.TonePlayer
 * JD-Core Version:    0.6.2
 */