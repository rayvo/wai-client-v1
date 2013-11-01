package com.google.android.apps.unveil.env;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.apps.unveil.Settings;
import java.util.List;

public class AccessibilityUtils
{
  private final AccessibilityManager accessibilityManager;
  private final String className;
  private final boolean isSpeechSettingEnabled;
  private final String packageName;

  public AccessibilityUtils(Context paramContext, int paramInt)
  {
    this.accessibilityManager = ((AccessibilityManager)paramContext.getSystemService("accessibility"));
    this.packageName = paramContext.getPackageName();
    this.className = paramContext.getClass().getName();
    this.isSpeechSettingEnabled = Settings.getBoolean(paramContext, paramInt, true);
  }

  public boolean isAccessibilityEnabled()
  {
    return (this.accessibilityManager != null) && (this.accessibilityManager.isEnabled());
  }

  public boolean isSpokenAccessibilityEnabled()
  {
    return (isAccessibilityEnabled()) && (this.isSpeechSettingEnabled);
  }

  public void makeViewsAccessible(View paramView)
  {
    if (!this.accessibilityManager.isEnabled());
    do
      while (true)
      {
        return;
        if (!(paramView instanceof ViewGroup))
          break;
        ViewGroup localViewGroup = (ViewGroup)paramView;
        for (int i = 0; i < localViewGroup.getChildCount(); i++)
          makeViewsAccessible(localViewGroup.getChildAt(i));
      }
    while ((!(paramView instanceof TextView)) && ((!(paramView instanceof ImageView)) || (paramView.getContentDescription() == null)));
    paramView.setFocusable(true);
  }

  public void readTextForAccessibility(String paramString)
  {
    if (!isAccessibilityEnabled())
      return;
    AccessibilityEvent localAccessibilityEvent = AccessibilityEvent.obtain(32);
    localAccessibilityEvent.setPackageName(this.packageName);
    localAccessibilityEvent.setClassName(this.className);
    localAccessibilityEvent.setEnabled(true);
    localAccessibilityEvent.getText().add(paramString);
    this.accessibilityManager.sendAccessibilityEvent(localAccessibilityEvent);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.AccessibilityUtils
 * JD-Core Version:    0.6.2
 */