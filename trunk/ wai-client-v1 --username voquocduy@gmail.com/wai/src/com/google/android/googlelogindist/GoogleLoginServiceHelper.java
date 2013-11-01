package com.google.android.googlelogindist;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.googleapps.GoogleLoginCredentialsResult;

public class GoogleLoginServiceHelper
{
  private static final boolean DEBUG = false;
  private static final boolean LOCAL_LOGV = false;
  private static final String OPTIONAL_MESSAGE = "optional_message";
  private static final String REQUEST_CODE = "requestCode";
  private static String REQUIRE_GOOGLE_FALSE = new String("false");
  private static String REQUIRE_GOOGLE_TRUE = new String("true");
  private static final String SERVICE = "service";
  private static final String TAG = "GoogleLoginServiceHelper";

  public static void getAccount(Activity paramActivity, final int paramInt, final boolean paramBoolean)
  {
    new Thread()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: new 35	android/os/Bundle
        //   3: dup
        //   4: invokespecial 36	android/os/Bundle:<init>	()V
        //   7: astore_1
        //   8: iconst_m1
        //   9: istore_2
        //   10: aconst_null
        //   11: astore_3
        //   12: new 38	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper
        //   15: dup
        //   16: aload_0
        //   17: getfield 21	com/google/android/googlelogindist/GoogleLoginServiceHelper$1:val$activity	Landroid/app/Activity;
        //   20: invokevirtual 44	android/app/Activity:getApplication	()Landroid/app/Application;
        //   23: invokespecial 47	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:<init>	(Landroid/content/Context;)V
        //   26: astore 4
        //   28: iconst_1
        //   29: anewarray 49	java/lang/String
        //   32: astore 7
        //   34: aload 7
        //   36: iconst_0
        //   37: aload 4
        //   39: aload_0
        //   40: getfield 23	com/google/android/googlelogindist/GoogleLoginServiceHelper$1:val$requireGoogle	Z
        //   43: invokevirtual 52	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:getAccount	(Z)Ljava/lang/String;
        //   46: aastore
        //   47: aload_1
        //   48: ldc 54
        //   50: aload 7
        //   52: invokevirtual 58	android/os/Bundle:putStringArray	(Ljava/lang/String;[Ljava/lang/String;)V
        //   55: aload 4
        //   57: ifnull +83 -> 140
        //   60: aload 4
        //   62: invokevirtual 61	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   65: aload_0
        //   66: getfield 25	com/google/android/googlelogindist/GoogleLoginServiceHelper$1:val$handler	Landroid/os/Handler;
        //   69: aload_0
        //   70: getfield 21	com/google/android/googlelogindist/GoogleLoginServiceHelper$1:val$activity	Landroid/app/Activity;
        //   73: iload_2
        //   74: aload_0
        //   75: getfield 27	com/google/android/googlelogindist/GoogleLoginServiceHelper$1:val$requestCode	I
        //   78: aload_1
        //   79: invokestatic 65	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   82: return
        //   83: astore 5
        //   85: aload_1
        //   86: ldc 67
        //   88: aload 5
        //   90: invokevirtual 71	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException:getErrorCode	()I
        //   93: invokevirtual 75	android/os/Bundle:putInt	(Ljava/lang/String;I)V
        //   96: iconst_0
        //   97: istore_2
        //   98: aload_3
        //   99: ifnull -34 -> 65
        //   102: aload_3
        //   103: invokevirtual 61	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   106: iconst_0
        //   107: istore_2
        //   108: goto -43 -> 65
        //   111: astore 6
        //   113: aload_3
        //   114: ifnull +7 -> 121
        //   117: aload_3
        //   118: invokevirtual 61	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   121: aload 6
        //   123: athrow
        //   124: astore 6
        //   126: aload 4
        //   128: astore_3
        //   129: goto -16 -> 113
        //   132: astore 5
        //   134: aload 4
        //   136: astore_3
        //   137: goto -52 -> 85
        //   140: goto -75 -> 65
        //
        // Exception table:
        //   from	to	target	type
        //   12	28	83	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   12	28	111	finally
        //   85	96	111	finally
        //   28	55	124	finally
        //   28	55	132	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
      }
    }
    .start();
  }

  public static void getCredentials(Activity paramActivity, final int paramInt, final Bundle paramBundle, final String paramString1, final String paramString2, final boolean paramBoolean)
  {
    new Thread()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore_1
        //   2: aconst_null
        //   3: astore_2
        //   4: new 46	android/os/Bundle
        //   7: dup
        //   8: invokespecial 47	android/os/Bundle:<init>	()V
        //   11: astore_3
        //   12: new 49	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper
        //   15: dup
        //   16: aload_0
        //   17: getfield 26	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$activity	Landroid/app/Activity;
        //   20: invokevirtual 55	android/app/Activity:getApplication	()Landroid/app/Application;
        //   23: invokespecial 58	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:<init>	(Landroid/content/Context;)V
        //   26: astore 4
        //   28: aload_0
        //   29: getfield 28	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$accountName	Ljava/lang/String;
        //   32: invokestatic 62	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$100	()Ljava/lang/String;
        //   35: if_acmpne +134 -> 169
        //   38: aload 4
        //   40: iconst_0
        //   41: invokevirtual 66	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:getAccount	(Z)Ljava/lang/String;
        //   44: astore 7
        //   46: aload_0
        //   47: getfield 30	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$service	Ljava/lang/String;
        //   50: astore 8
        //   52: aload_0
        //   53: getfield 32	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$promptUser	Z
        //   56: ifne +295 -> 351
        //   59: aload 4
        //   61: aload 7
        //   63: aload 8
        //   65: iload_1
        //   66: invokevirtual 69	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:getCredentials	(Ljava/lang/String;Ljava/lang/String;Z)Lcom/google/android/googleapps/GoogleLoginCredentialsResult;
        //   69: astore 9
        //   71: aload_3
        //   72: ldc 71
        //   74: aload_0
        //   75: getfield 34	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$requestExtras	Landroid/os/Bundle;
        //   78: invokevirtual 75	android/os/Bundle:putBundle	(Ljava/lang/String;Landroid/os/Bundle;)V
        //   81: aload 9
        //   83: invokevirtual 80	com/google/android/googleapps/GoogleLoginCredentialsResult:getCredentialsString	()Ljava/lang/String;
        //   86: ifnull +113 -> 199
        //   89: aload_3
        //   90: ldc 82
        //   92: aload 9
        //   94: invokevirtual 80	com/google/android/googleapps/GoogleLoginCredentialsResult:getCredentialsString	()Ljava/lang/String;
        //   97: invokevirtual 86	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   100: aload_3
        //   101: ldc 88
        //   103: aload 9
        //   105: invokevirtual 90	com/google/android/googleapps/GoogleLoginCredentialsResult:getAccount	()Ljava/lang/String;
        //   108: invokevirtual 86	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   111: ldc 92
        //   113: aload_0
        //   114: getfield 30	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$service	Ljava/lang/String;
        //   117: invokevirtual 98	java/lang/String:equals	(Ljava/lang/Object;)Z
        //   120: ifeq +21 -> 141
        //   123: aload_3
        //   124: ldc 100
        //   126: aload 4
        //   128: aload 9
        //   130: invokevirtual 90	com/google/android/googleapps/GoogleLoginCredentialsResult:getAccount	()Ljava/lang/String;
        //   133: ldc 100
        //   135: invokevirtual 104	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:peekCredentials	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   138: invokevirtual 86	android/os/Bundle:putString	(Ljava/lang/String;Ljava/lang/String;)V
        //   141: aload_0
        //   142: getfield 36	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$handler	Landroid/os/Handler;
        //   145: aload_0
        //   146: getfield 26	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$activity	Landroid/app/Activity;
        //   149: iconst_m1
        //   150: aload_0
        //   151: getfield 38	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$requestCode	I
        //   154: aload_3
        //   155: invokestatic 108	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   158: aload 4
        //   160: ifnull +8 -> 168
        //   163: aload 4
        //   165: invokevirtual 111	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   168: return
        //   169: aload_0
        //   170: getfield 28	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$accountName	Ljava/lang/String;
        //   173: invokestatic 114	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$200	()Ljava/lang/String;
        //   176: if_acmpne +14 -> 190
        //   179: aload 4
        //   181: iconst_1
        //   182: invokevirtual 66	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:getAccount	(Z)Ljava/lang/String;
        //   185: astore 7
        //   187: goto -141 -> 46
        //   190: aload_0
        //   191: getfield 28	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$accountName	Ljava/lang/String;
        //   194: astore 7
        //   196: goto -150 -> 46
        //   199: aload 9
        //   201: invokevirtual 118	com/google/android/googleapps/GoogleLoginCredentialsResult:getCredentialsIntent	()Landroid/content/Intent;
        //   204: ifnull +67 -> 271
        //   207: aload_0
        //   208: getfield 32	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$promptUser	Z
        //   211: ifeq +32 -> 243
        //   214: aload_0
        //   215: getfield 36	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$handler	Landroid/os/Handler;
        //   218: new 120	com/google/android/googlelogindist/GoogleLoginServiceHelper$2$1
        //   221: dup
        //   222: aload_0
        //   223: aload 9
        //   225: invokespecial 123	com/google/android/googlelogindist/GoogleLoginServiceHelper$2$1:<init>	(Lcom/google/android/googlelogindist/GoogleLoginServiceHelper$2;Lcom/google/android/googleapps/GoogleLoginCredentialsResult;)V
        //   228: invokevirtual 129	android/os/Handler:post	(Ljava/lang/Runnable;)Z
        //   231: pop
        //   232: aload 4
        //   234: ifnull +8 -> 242
        //   237: aload 4
        //   239: invokevirtual 111	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   242: return
        //   243: aload_0
        //   244: getfield 36	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$handler	Landroid/os/Handler;
        //   247: aload_0
        //   248: getfield 26	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$activity	Landroid/app/Activity;
        //   251: iconst_m1
        //   252: aload_0
        //   253: getfield 38	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$requestCode	I
        //   256: aload_3
        //   257: invokestatic 108	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   260: aload 4
        //   262: ifnull +8 -> 270
        //   265: aload 4
        //   267: invokevirtual 111	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   270: return
        //   271: new 131	java/lang/RuntimeException
        //   274: dup
        //   275: ldc 133
        //   277: invokespecial 136	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
        //   280: athrow
        //   281: astore 6
        //   283: aload 4
        //   285: astore_2
        //   286: aload_3
        //   287: ldc 138
        //   289: aload 6
        //   291: invokevirtual 142	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException:getErrorCode	()I
        //   294: invokevirtual 146	android/os/Bundle:putInt	(Ljava/lang/String;I)V
        //   297: aload_0
        //   298: getfield 36	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$handler	Landroid/os/Handler;
        //   301: aload_0
        //   302: getfield 26	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$activity	Landroid/app/Activity;
        //   305: iconst_0
        //   306: aload_0
        //   307: getfield 38	com/google/android/googlelogindist/GoogleLoginServiceHelper$2:val$requestCode	I
        //   310: aload_3
        //   311: invokestatic 108	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   314: aload_2
        //   315: ifnull -147 -> 168
        //   318: aload_2
        //   319: invokevirtual 111	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   322: return
        //   323: astore 5
        //   325: aload_2
        //   326: ifnull +7 -> 333
        //   329: aload_2
        //   330: invokevirtual 111	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   333: aload 5
        //   335: athrow
        //   336: astore 5
        //   338: aload 4
        //   340: astore_2
        //   341: goto -16 -> 325
        //   344: astore 6
        //   346: aconst_null
        //   347: astore_2
        //   348: goto -62 -> 286
        //   351: iconst_0
        //   352: istore_1
        //   353: goto -294 -> 59
        //
        // Exception table:
        //   from	to	target	type
        //   28	46	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   46	59	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   59	141	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   141	158	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   169	187	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   190	196	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   199	232	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   243	260	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   271	281	281	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   12	28	323	finally
        //   286	314	323	finally
        //   28	46	336	finally
        //   46	59	336	finally
        //   59	141	336	finally
        //   141	158	336	finally
        //   169	187	336	finally
        //   190	196	336	finally
        //   199	232	336	finally
        //   243	260	336	finally
        //   271	281	336	finally
        //   12	28	344	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
      }
    }
    .start();
  }

  public static void getCredentials(Activity paramActivity, int paramInt, Bundle paramBundle, boolean paramBoolean1, String paramString, boolean paramBoolean2)
  {
    if (paramBoolean1);
    for (String str = REQUIRE_GOOGLE_TRUE; ; str = REQUIRE_GOOGLE_FALSE)
    {
      getCredentials(paramActivity, paramInt, paramBundle, str, paramString, paramBoolean2);
      return;
    }
  }

  public static void invalidateAuthToken(Activity paramActivity, final int paramInt, final String paramString)
  {
    new Thread()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_1
        //   2: new 35	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper
        //   5: dup
        //   6: aload_0
        //   7: getfield 21	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$activity	Landroid/app/Activity;
        //   10: invokevirtual 41	android/app/Activity:getApplication	()Landroid/app/Application;
        //   13: invokespecial 44	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:<init>	(Landroid/content/Context;)V
        //   16: astore_2
        //   17: aload_2
        //   18: aload_0
        //   19: getfield 23	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$authToken	Ljava/lang/String;
        //   22: invokevirtual 47	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:invalidateAuthToken	(Ljava/lang/String;)V
        //   25: aload_2
        //   26: ifnull +7 -> 33
        //   29: aload_2
        //   30: invokevirtual 50	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   33: aload_0
        //   34: getfield 25	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$handler	Landroid/os/Handler;
        //   37: aload_0
        //   38: getfield 21	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$activity	Landroid/app/Activity;
        //   41: iconst_m1
        //   42: aload_0
        //   43: getfield 27	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$requestCode	I
        //   46: aconst_null
        //   47: invokestatic 54	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   50: return
        //   51: astore_3
        //   52: new 56	android/os/Bundle
        //   55: dup
        //   56: invokespecial 57	android/os/Bundle:<init>	()V
        //   59: astore 4
        //   61: aload 4
        //   63: ldc 59
        //   65: aload_3
        //   66: invokevirtual 63	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException:getErrorCode	()I
        //   69: invokevirtual 67	android/os/Bundle:putInt	(Ljava/lang/String;I)V
        //   72: aload_0
        //   73: getfield 25	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$handler	Landroid/os/Handler;
        //   76: aload_0
        //   77: getfield 21	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$activity	Landroid/app/Activity;
        //   80: iconst_0
        //   81: aload_0
        //   82: getfield 27	com/google/android/googlelogindist/GoogleLoginServiceHelper$3:val$requestCode	I
        //   85: aload 4
        //   87: invokestatic 54	com/google/android/googlelogindist/GoogleLoginServiceHelper:access$000	(Landroid/os/Handler;Landroid/app/Activity;IILandroid/os/Bundle;)V
        //   90: aload_1
        //   91: ifnull -41 -> 50
        //   94: aload_1
        //   95: invokevirtual 50	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   98: return
        //   99: astore 5
        //   101: aload_1
        //   102: ifnull +7 -> 109
        //   105: aload_1
        //   106: invokevirtual 50	com/google/android/googlelogindist/GoogleLoginServiceBlockingHelper:close	()V
        //   109: aload 5
        //   111: athrow
        //   112: astore 5
        //   114: aload_2
        //   115: astore_1
        //   116: goto -15 -> 101
        //   119: astore_3
        //   120: aload_2
        //   121: astore_1
        //   122: goto -70 -> 52
        //
        // Exception table:
        //   from	to	target	type
        //   2	17	51	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
        //   2	17	99	finally
        //   52	90	99	finally
        //   17	25	112	finally
        //   17	25	119	com/google/android/googlelogindist/GoogleLoginServiceNotFoundException
      }
    }
    .start();
  }

  private static void postActivityResult(Handler paramHandler, Activity paramActivity, final int paramInt1, final int paramInt2, final Bundle paramBundle)
  {
    paramHandler.post(new Runnable()
    {
      public void run()
      {
        GoogleLoginServiceHelper.sendActivityResult(this.val$activity, paramInt1, paramInt2, paramBundle);
      }
    });
  }

  private static void sendActivityResult(Activity paramActivity, int paramInt1, int paramInt2, Bundle paramBundle)
  {
    PendingIntent localPendingIntent = paramActivity.createPendingResult(paramInt2, null, 0);
    if (localPendingIntent != null);
    try
    {
      Intent localIntent = new Intent();
      if (paramBundle != null)
        localIntent.putExtras(paramBundle);
      localPendingIntent.send(paramActivity, paramInt1, localIntent);
      return;
    }
    catch (PendingIntent.CanceledException localCanceledException)
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.googlelogindist.GoogleLoginServiceHelper
 * JD-Core Version:    0.6.2
 */