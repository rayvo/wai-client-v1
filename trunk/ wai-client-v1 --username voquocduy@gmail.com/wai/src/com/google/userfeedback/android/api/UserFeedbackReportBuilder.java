package com.google.userfeedback.android.api;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Build.VERSION;
import android.telephony.TelephonyManager;
import com.x.google.common.io.protocol.ProtoBuf;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.zip.GZIPOutputStream;

public class UserFeedbackReportBuilder
{
  private static final Object GOOGLE_ACCOUNT = "com.google";
  private static final String REPORT_FILE_EXTENSION = ".proto.gz";
  private static final String TMP_FILE_EXTENSION = ".tmp";
  private UserFeedbackSpec spec;

  protected UserFeedbackReportBuilder(UserFeedbackSpec paramUserFeedbackSpec)
  {
    this.spec = paramUserFeedbackSpec;
  }

  // ERROR //
  private String collectLogcatOutput(List<String> paramList)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: new 63	java/io/BufferedReader
    //   5: dup
    //   6: new 65	java/io/InputStreamReader
    //   9: dup
    //   10: invokestatic 71	java/lang/Runtime:getRuntime	()Ljava/lang/Runtime;
    //   13: aload_1
    //   14: iconst_0
    //   15: anewarray 73	java/lang/String
    //   18: invokeinterface 79 2 0
    //   23: checkcast 81	[Ljava/lang/String;
    //   26: invokevirtual 85	java/lang/Runtime:exec	([Ljava/lang/String;)Ljava/lang/Process;
    //   29: invokevirtual 91	java/lang/Process:getInputStream	()Ljava/io/InputStream;
    //   32: invokespecial 94	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   35: invokespecial 97	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   38: astore_3
    //   39: new 99	java/lang/StringBuilder
    //   42: dup
    //   43: invokespecial 100	java/lang/StringBuilder:<init>	()V
    //   46: astore 4
    //   48: ldc 102
    //   50: invokestatic 108	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   53: astore 12
    //   55: aload_3
    //   56: invokevirtual 112	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   59: astore 13
    //   61: aload 13
    //   63: ifnull +48 -> 111
    //   66: aload 4
    //   68: aload 13
    //   70: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   73: pop
    //   74: aload 4
    //   76: aload 12
    //   78: invokevirtual 116	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: goto -27 -> 55
    //   85: astore 8
    //   87: aload_3
    //   88: astore_2
    //   89: ldc 118
    //   91: aload 8
    //   93: invokevirtual 121	java/io/IOException:getMessage	()Ljava/lang/String;
    //   96: invokestatic 127	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   99: pop
    //   100: aload_2
    //   101: ifnull +7 -> 108
    //   104: aload_2
    //   105: invokevirtual 130	java/io/BufferedReader:close	()V
    //   108: ldc 132
    //   110: areturn
    //   111: aload 4
    //   113: invokevirtual 135	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   116: astore 16
    //   118: aload_3
    //   119: ifnull +7 -> 126
    //   122: aload_3
    //   123: invokevirtual 130	java/io/BufferedReader:close	()V
    //   126: aload 16
    //   128: areturn
    //   129: astore 17
    //   131: ldc 118
    //   133: aload 17
    //   135: invokevirtual 121	java/io/IOException:getMessage	()Ljava/lang/String;
    //   138: invokestatic 127	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   141: pop
    //   142: goto -16 -> 126
    //   145: astore 10
    //   147: ldc 118
    //   149: aload 10
    //   151: invokevirtual 121	java/io/IOException:getMessage	()Ljava/lang/String;
    //   154: invokestatic 127	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   157: pop
    //   158: goto -50 -> 108
    //   161: astore 5
    //   163: aload_2
    //   164: ifnull +7 -> 171
    //   167: aload_2
    //   168: invokevirtual 130	java/io/BufferedReader:close	()V
    //   171: aload 5
    //   173: athrow
    //   174: astore 6
    //   176: ldc 118
    //   178: aload 6
    //   180: invokevirtual 121	java/io/IOException:getMessage	()Ljava/lang/String;
    //   183: invokestatic 127	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   186: pop
    //   187: goto -16 -> 171
    //   190: astore 5
    //   192: aload_3
    //   193: astore_2
    //   194: goto -31 -> 163
    //   197: astore 8
    //   199: aconst_null
    //   200: astore_2
    //   201: goto -112 -> 89
    //
    // Exception table:
    //   from	to	target	type
    //   39	55	85	java/io/IOException
    //   55	61	85	java/io/IOException
    //   66	82	85	java/io/IOException
    //   111	118	85	java/io/IOException
    //   122	126	129	java/io/IOException
    //   104	108	145	java/io/IOException
    //   2	39	161	finally
    //   89	100	161	finally
    //   167	171	174	java/io/IOException
    //   39	55	190	finally
    //   55	61	190	finally
    //   66	82	190	finally
    //   111	118	190	finally
    //   2	39	197	java/io/IOException
  }

  private String gatherLogs(String paramString)
  {
    ArrayList localArrayList = new ArrayList(Arrays.asList(new String[] { "logcat", "-v", "time", "-d" }));
    if (paramString != null)
      localArrayList.add(paramString);
    return collectLogcatOutput(localArrayList);
  }

  private File getFilesDataDir()
    throws IOException
  {
    File localFile = new File(this.spec.getContext().getFilesDir(), "reports");
    if ((!localFile.exists()) && (!localFile.mkdirs()))
      throw new IOException("Unable to create directory structure for base directory provided");
    return localFile;
  }

  private byte[] getScreenshotBytes(Bitmap paramBitmap)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    paramBitmap.compress(Bitmap.CompressFormat.JPEG, 70, localByteArrayOutputStream);
    return localByteArrayOutputStream.toByteArray();
  }

  private void populateBuildData(UserFeedbackReport paramUserFeedbackReport)
  {
    paramUserFeedbackReport.device = Build.DEVICE;
    paramUserFeedbackReport.buildId = Build.DISPLAY;
    paramUserFeedbackReport.buildType = Build.TYPE;
    paramUserFeedbackReport.model = Build.MODEL;
    paramUserFeedbackReport.board = Build.BOARD;
    paramUserFeedbackReport.brand = Build.BRAND;
    paramUserFeedbackReport.codename = Build.VERSION.CODENAME;
    paramUserFeedbackReport.incremental = Build.VERSION.INCREMENTAL;
    paramUserFeedbackReport.release = Build.VERSION.RELEASE;
    paramUserFeedbackReport.product = Build.PRODUCT;
    try
    {
      paramUserFeedbackReport.sdkInt = Integer.parseInt(Build.VERSION.SDK);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      paramUserFeedbackReport.sdkInt = -1;
    }
  }

  private void populateCommonData(UserFeedbackReport paramUserFeedbackReport)
  {
    paramUserFeedbackReport.description = UserFeedback.userFeedback().getFeedbackText();
    paramUserFeedbackReport.uiLanguage = Locale.getDefault().toString();
    if (UserFeedback.userFeedback().getSpec().getProductSpecificBinaryData().size() > 0)
      paramUserFeedbackReport.productSpecificBinaryData = UserFeedback.userFeedback().getSpec().getProductSpecificBinaryData();
  }

  private void populatePackageData(UserFeedbackReport paramUserFeedbackReport)
  {
    PackageManager localPackageManager = this.spec.getContext().getPackageManager();
    paramUserFeedbackReport.packageName = this.spec.getContext().getPackageName();
    try
    {
      ApplicationInfo localApplicationInfo = localPackageManager.getApplicationInfo(paramUserFeedbackReport.packageName, 0);
      PackageInfo localPackageInfo = localPackageManager.getPackageInfo(paramUserFeedbackReport.packageName, 0);
      paramUserFeedbackReport.processName = localApplicationInfo.processName;
      paramUserFeedbackReport.packageVersion = localPackageInfo.versionCode;
      paramUserFeedbackReport.packageVersionName = localPackageInfo.versionName;
      paramUserFeedbackReport.installerPackageName = localPackageManager.getInstallerPackageName(paramUserFeedbackReport.packageName);
      label86: paramUserFeedbackReport.processName = UserFeedback.userFeedback().getSpec().getActivity().getPackageName();
      paramUserFeedbackReport.isSystemApp = false;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      break label86;
    }
  }

  private void populateRunningApps(UserFeedbackReport paramUserFeedbackReport)
  {
    Iterator localIterator = ((ActivityManager)this.spec.getContext().getSystemService("activity")).getRunningAppProcesses().iterator();
    while (localIterator.hasNext())
    {
      ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = (ActivityManager.RunningAppProcessInfo)localIterator.next();
      paramUserFeedbackReport.runningApplications.add(localRunningAppProcessInfo.processName);
    }
  }

  private void populateSystemData(UserFeedbackReport paramUserFeedbackReport)
  {
    paramUserFeedbackReport.timestamp = System.currentTimeMillis();
    paramUserFeedbackReport.systemLog = gatherLogs(this.spec.getLogFilter());
    paramUserFeedbackReport.crashData = this.spec.getCrashData();
  }

  private void populateTelephonyData(UserFeedbackReport paramUserFeedbackReport)
  {
    TelephonyManager localTelephonyManager = (TelephonyManager)this.spec.getContext().getSystemService("phone");
    paramUserFeedbackReport.phoneType = localTelephonyManager.getPhoneType();
    paramUserFeedbackReport.networkType = localTelephonyManager.getNetworkType();
    paramUserFeedbackReport.networkName = localTelephonyManager.getNetworkOperatorName();
  }

  private void populateUserInitiatedFeedbackData(UserFeedbackReport paramUserFeedbackReport)
  {
    if ((this.spec.isScreenshotEnabled()) && (this.spec.getCurrentScreenshot() != null))
    {
      Bitmap localBitmap = this.spec.getCurrentScreenshot();
      if (!localBitmap.isRecycled())
      {
        paramUserFeedbackReport.screenshot = getScreenshotBytes(localBitmap);
        paramUserFeedbackReport.screenshotWidth = localBitmap.getWidth();
        paramUserFeedbackReport.screenshotHeight = localBitmap.getHeight();
      }
    }
    paramUserFeedbackReport.accounts = new ArrayList();
    try
    {
      for (Account localAccount : AccountManager.get(this.spec.getContext()).getAccounts())
        if (localAccount.type.equals(GOOGLE_ACCOUNT))
          paramUserFeedbackReport.accounts.add(localAccount.name);
    }
    catch (VerifyError localVerifyError)
    {
      paramUserFeedbackReport.numGoogleAccounts = paramUserFeedbackReport.accounts.size();
      paramUserFeedbackReport.categoryTag = this.spec.getCategoryTag();
      paramUserFeedbackReport.bucket = this.spec.getBucket();
      return;
    }
    catch (Exception localException)
    {
      label149: break label149;
    }
  }

  private ProtoBuf serializeReport(UserFeedbackReport paramUserFeedbackReport)
  {
    return new UserFeedbackSerializer(paramUserFeedbackReport).serialize();
  }

  private AsyncTask<Void, Void, Void> startBuildingReport(final UserFeedbackReport paramUserFeedbackReport)
  {
    return new AsyncTask()
    {
      protected Void doInBackground(Void[] paramAnonymousArrayOfVoid)
      {
        this.this$0.populateRunningApps(paramUserFeedbackReport);
        this.this$0.populateBuildData(paramUserFeedbackReport);
        this.this$0.populateCommonData(paramUserFeedbackReport);
        this.this$0.populateTelephonyData(paramUserFeedbackReport);
        this.this$0.populateSystemData(paramUserFeedbackReport);
        this.this$0.populatePackageData(paramUserFeedbackReport);
        this.this$0.populateUserInitiatedFeedbackData(paramUserFeedbackReport);
        return null;
      }
    }
    .execute(new Void[0]);
  }

  public AsyncTask<Void, Void, Void> buildReport(UserFeedbackReport paramUserFeedbackReport)
  {
    return startBuildingReport(paramUserFeedbackReport);
  }

  protected File getFormattedFeedbackReport(UserFeedbackReport paramUserFeedbackReport)
    throws IOException
  {
    ProtoBuf localProtoBuf = serializeReport(paramUserFeedbackReport);
    File localFile1 = getFilesDataDir();
    String str = System.currentTimeMillis() + "." + localProtoBuf.hashCode();
    File localFile2 = new File(localFile1, str + ".tmp");
    File localFile3 = new File(localFile1, str + ".proto.gz");
    try
    {
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(new FileOutputStream(localFile2));
      localProtoBuf.outputTo(localGZIPOutputStream);
      localGZIPOutputStream.close();
      if (!localFile2.renameTo(localFile3))
        throw new IOException("Failed to rename temporary file");
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
      throw localIOException;
    }
    finally
    {
      localFile2.delete();
    }
    localFile2.delete();
    return localFile3;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.userfeedback.android.api.UserFeedbackReportBuilder
 * JD-Core Version:    0.6.2
 */