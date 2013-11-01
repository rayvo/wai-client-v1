package com.google.android.apps.unveil.intents;

import android.content.Intent;
import com.google.android.apps.unveil.env.UnveilLogger;

public class Intents
{
  public static final String EXTRA_VERSION = "version";
  public static final IntentRecipe NONE = new None();
  private static final UnveilLogger logger = new UnveilLogger();

  public static IntentRecipe classify(Intent paramIntent)
  {
    if (ZxingScan.matches(paramIntent))
      return new ZxingScan(paramIntent);
    return NONE;
  }

  public static abstract class IntentRecipe
  {
    private final boolean showCaptureButtons;
    private final boolean showOptionsMenu;

    public IntentRecipe(boolean paramBoolean1, boolean paramBoolean2)
    {
      this.showCaptureButtons = paramBoolean1;
      this.showOptionsMenu = paramBoolean2;
    }

    public final boolean showCaptureButtons()
    {
      return this.showCaptureButtons;
    }

    public final boolean showOptionsMenu()
    {
      return this.showOptionsMenu;
    }
  }

  private static final class None extends Intents.IntentRecipe
  {
    public None()
    {
      super(true);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.intents.Intents
 * JD-Core Version:    0.6.2
 */