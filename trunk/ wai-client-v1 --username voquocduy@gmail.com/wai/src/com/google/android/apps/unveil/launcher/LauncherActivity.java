package com.google.android.apps.unveil.launcher;

import android.content.ActivityNotFoundException;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.R.xml;
import com.google.android.apps.unveil.UnveilSettings;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.proxies.camera.ImageSequenceCamera;
import com.google.android.apps.unveil.sensors.proxies.camera.StaticImageCamera;
import com.google.android.apps.unveil.ui.BuildVersions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LauncherActivity extends PreferenceActivity
{
  private static final String GOGGLES_DEVEL_PACKAGE_NAME = "com.google.android.apps.unveil";
  private static final String GOGGLES_LAUNCH_ACTION = "com.google.android.apps.unveil.LAUNCH";
  private static final String GOGGLES_PACKAGE_NAME = "com.google.android.apps.unveil";
  private Button clearButton;
  private Button launchButton;
  private final UnveilLogger logger = new UnveilLogger();
  private SharedPreferences preferences;

  // ERROR //
  private UnveilSettings generateExtras()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 28	com/google/android/apps/unveil/launcher/LauncherActivity:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   4: ldc 59
    //   6: iconst_0
    //   7: anewarray 61	java/lang/Object
    //   10: invokevirtual 65	com/google/android/apps/unveil/env/UnveilLogger:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   13: aload_0
    //   14: getfield 67	com/google/android/apps/unveil/launcher/LauncherActivity:preferences	Landroid/content/SharedPreferences;
    //   17: aload_0
    //   18: getstatic 73	com/google/android/apps/unveil/R$string:previous_version_key	I
    //   21: invokevirtual 77	com/google/android/apps/unveil/launcher/LauncherActivity:getString	(I)Ljava/lang/String;
    //   24: ldc 79
    //   26: invokeinterface 84 3 0
    //   31: invokestatic 90	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   34: istore_2
    //   35: aload_0
    //   36: getstatic 93	com/google/android/apps/unveil/R$string:mock_latitude_key	I
    //   39: invokespecial 96	com/google/android/apps/unveil/launcher/LauncherActivity:loadString	(I)Ljava/lang/String;
    //   42: astore 4
    //   44: aload_0
    //   45: getstatic 99	com/google/android/apps/unveil/R$string:mock_longitude_key	I
    //   48: invokespecial 96	com/google/android/apps/unveil/launcher/LauncherActivity:loadString	(I)Ljava/lang/String;
    //   51: astore 5
    //   53: aload 4
    //   55: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   58: istore 6
    //   60: aconst_null
    //   61: astore 7
    //   63: aconst_null
    //   64: astore 8
    //   66: iload 6
    //   68: ifne +45 -> 113
    //   71: aload 5
    //   73: invokestatic 105	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   76: istore 9
    //   78: aconst_null
    //   79: astore 7
    //   81: aconst_null
    //   82: astore 8
    //   84: iload 9
    //   86: ifne +27 -> 113
    //   89: aload 4
    //   91: invokestatic 111	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   94: invokestatic 115	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   97: astore 7
    //   99: aload 5
    //   101: invokestatic 111	java/lang/Double:parseDouble	(Ljava/lang/String;)D
    //   104: invokestatic 115	java/lang/Double:valueOf	(D)Ljava/lang/Double;
    //   107: astore 10
    //   109: aload 10
    //   111: astore 8
    //   113: new 117	com/google/android/apps/unveil/UnveilSettings
    //   116: dup
    //   117: iload_2
    //   118: aload_0
    //   119: invokespecial 121	com/google/android/apps/unveil/launcher/LauncherActivity:getCameraString	()Ljava/lang/String;
    //   122: aload 7
    //   124: aload 8
    //   126: aload_0
    //   127: getstatic 124	com/google/android/apps/unveil/R$string:glPreview_key	I
    //   130: invokespecial 128	com/google/android/apps/unveil/launcher/LauncherActivity:loadBoolean	(I)Ljava/lang/Boolean;
    //   133: invokevirtual 134	java/lang/Boolean:booleanValue	()Z
    //   136: aload_0
    //   137: getstatic 137	com/google/android/apps/unveil/R$string:use_local_barcode_key	I
    //   140: invokespecial 128	com/google/android/apps/unveil/launcher/LauncherActivity:loadBoolean	(I)Ljava/lang/Boolean;
    //   143: invokevirtual 134	java/lang/Boolean:booleanValue	()Z
    //   146: aload_0
    //   147: getstatic 140	com/google/android/apps/unveil/R$string:preview_frame_key	I
    //   150: invokespecial 128	com/google/android/apps/unveil/launcher/LauncherActivity:loadBoolean	(I)Ljava/lang/Boolean;
    //   153: invokevirtual 134	java/lang/Boolean:booleanValue	()Z
    //   156: aload_0
    //   157: getstatic 143	com/google/android/apps/unveil/R$string:use_groundtruth_key	I
    //   160: invokespecial 128	com/google/android/apps/unveil/launcher/LauncherActivity:loadBoolean	(I)Ljava/lang/Boolean;
    //   163: invokevirtual 134	java/lang/Boolean:booleanValue	()Z
    //   166: invokespecial 146	com/google/android/apps/unveil/UnveilSettings:<init>	(ILjava/lang/String;Ljava/lang/Double;Ljava/lang/Double;ZZZZ)V
    //   169: areturn
    //   170: astore_1
    //   171: aload_0
    //   172: ldc 148
    //   174: invokespecial 52	com/google/android/apps/unveil/launcher/LauncherActivity:showToast	(Ljava/lang/String;)V
    //   177: aconst_null
    //   178: areturn
    //   179: astore_3
    //   180: aload_0
    //   181: ldc 150
    //   183: invokespecial 52	com/google/android/apps/unveil/launcher/LauncherActivity:showToast	(Ljava/lang/String;)V
    //   186: aconst_null
    //   187: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   13	35	170	java/lang/NumberFormatException
    //   35	60	179	java/lang/NumberFormatException
    //   71	78	179	java/lang/NumberFormatException
    //   89	109	179	java/lang/NumberFormatException
  }

  private String getCameraString()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(keyValue("static_image", loadString(R.string.camera_static_image_key)));
    localArrayList.add(keyValue("image_sequence_directory", loadString(R.string.camera_image_sequence_dir_key)));
    localArrayList.add(keyValue("lockstep_callbacks", loadBoolean(R.string.use_groundtruth_key).toString()));
    localArrayList.add(keyValue("skip_rendering", loadBoolean(R.string.glPreview_key).toString()));
    String str = TextUtils.join(",", localArrayList);
    return loadString(R.string.camera_proxy_key) + "[" + str + "]";
  }

  private String getGogglesPackageName()
  {
    if (this.preferences.getBoolean(getString(R.string.use_devel_package_key), false))
      return "com.google.android.apps.unveil";
    return "com.google.android.apps.unveil";
  }

  private String keyValue(String paramString1, String paramString2)
  {
    return paramString1 + "=" + paramString2;
  }

  private Boolean loadBoolean(int paramInt)
  {
    String str = getString(paramInt);
    try
    {
      Boolean localBoolean = Boolean.valueOf(this.preferences.getBoolean(str, false));
      return localBoolean;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return null;
  }

  private Float loadFloat(int paramInt)
  {
    String str = getString(paramInt);
    try
    {
      Float localFloat = Float.valueOf(this.preferences.getFloat(str, 0.0F));
      return localFloat;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return null;
  }

  private Integer loadInteger(int paramInt)
  {
    String str = getString(paramInt);
    try
    {
      Integer localInteger = Integer.valueOf(this.preferences.getInt(str, 0));
      return localInteger;
    }
    catch (ClassCastException localClassCastException)
    {
    }
    return null;
  }

  private String loadString(int paramInt)
  {
    String str = getString(paramInt);
    return this.preferences.getString(str, "");
  }

  private List<String> loadStringList(int paramInt)
  {
    String str = getString(paramInt);
    String[] arrayOfString = this.preferences.getString(str, "").split("\\s+");
    if (arrayOfString.length == 0)
      return null;
    return Arrays.asList(arrayOfString);
  }

  private void sendGogglesExtras(UnveilSettings paramUnveilSettings)
  {
    this.logger.v("sendGogglesExtras", new Object[0]);
    setButtonsEnabled(false);
    Intent localIntent = new Intent();
    localIntent.setPackage(getGogglesPackageName());
    localIntent.setAction("com.google.android.apps.unveil.LAUNCH");
    localIntent.putExtra("UnveilSettings", paramUnveilSettings);
    sendOrderedBroadcast(localIntent, null, new BroadcastReceiver()
    {
      public void onReceive(Context paramAnonymousContext, Intent paramAnonymousIntent)
      {
        if (getResultCode() == -1)
          LauncherActivity.this.startGoggles(true);
        while (true)
        {
          LauncherActivity.this.setButtonsEnabled(true);
          return;
          LauncherActivity.this.showToast("Failed to send launcher extras.");
        }
      }
    }
    , null, -1, null, null);
  }

  private void setButtonsEnabled(boolean paramBoolean)
  {
    try
    {
      this.launchButton.setEnabled(paramBoolean);
      this.clearButton.setEnabled(paramBoolean);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void showToast(String paramString)
  {
    Toast.makeText(this, paramString, 0).show();
  }

  private void startGoggles(boolean paramBoolean)
  {
    this.logger.v("startGoggles", new Object[0]);
    if (loadBoolean(R.string.use_ipv4__key).booleanValue())
    {
      System.setProperty("java.net.preferIPv4Stack", "true");
      System.setProperty("java.net.preferIPv6Addresses", "false");
    }
    Intent localIntent = new Intent();
    localIntent.addFlags(524288);
    String str1 = getGogglesPackageName();
    String str2 = loadString(R.string.launch_activity_key);
    String str3 = "com.google.android.apps.unveil." + str2;
    localIntent.setClassName(str1, str3);
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      showToast("Could not find activity " + str3 + ".");
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    this.logger.v("onCreate", new Object[0]);
    super.onCreate(paramBundle);
    addPreferencesFromResource(R.xml.launcher_preferences);
    setContentView(R.layout.launcher_main);
    getListView().addFooterView(View.inflate(this, R.layout.launcher_footer, null), null, false);
    BuildVersions.populate((TextView)findViewById(R.id.build_version));
    this.preferences = PreferenceManager.getDefaultSharedPreferences(this);
    this.launchButton = ((Button)findViewById(R.id.launch_button));
    this.launchButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        UnveilSettings localUnveilSettings = LauncherActivity.this.generateExtras();
        if (localUnveilSettings != null)
          LauncherActivity.this.sendGogglesExtras(localUnveilSettings);
      }
    });
    this.clearButton = ((Button)findViewById(R.id.clear_button));
    this.clearButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        LauncherActivity.this.startGoggles(false);
      }
    });
    StaticImageCamera.initializePreferences(this);
    ImageSequenceCamera.initializePreferences(this);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.launcher.LauncherActivity
 * JD-Core Version:    0.6.2
 */