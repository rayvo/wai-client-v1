package com.google.android.apps.unveil.env.feedback;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.Window;
import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.userfeedback.android.api.UserFeedback;
import com.google.userfeedback.android.api.UserFeedbackSpec;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class FeedbackUtils
{
  public static void onFeedbackOptionsItemSelected(Activity paramActivity, Map<String, String> paramMap)
  {
    UserFeedbackSpec localUserFeedbackSpec = new UserFeedbackSpec(paramActivity, paramActivity.getWindow().getDecorView(), "goggles:V *:E", "goggles_android_client_external");
    BaseApplication localBaseApplication = (BaseApplication)paramActivity.getApplication();
    localUserFeedbackSpec.addProductSpecificBinaryData("frontend", "text/plain", localBaseApplication.getFrontendUrl().toString().getBytes());
    String str = localBaseApplication.getClickTracker().getTrackingId();
    if (!TextUtils.isEmpty(str))
      localUserFeedbackSpec.addProductSpecificBinaryData("trackingId", "text/plain", str.getBytes());
    if (paramMap != null)
    {
      Iterator localIterator = paramMap.entrySet().iterator();
      while (localIterator.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        localUserFeedbackSpec.addProductSpecificBinaryData((String)localEntry.getKey(), "text/plain", ((String)localEntry.getValue()).getBytes());
      }
    }
    if (Build.VERSION.SDK_INT >= 14)
    {
      paramActivity.bindService(new Intent("android.intent.action.BUG_REPORT"), new ServiceConnection()
      {
        public void onServiceConnected(ComponentName paramAnonymousComponentName, IBinder paramAnonymousIBinder)
        {
          try
          {
            paramAnonymousIBinder.transact(1, Parcel.obtain(), null, 0);
            return;
          }
          catch (RemoteException localRemoteException)
          {
            localRemoteException.printStackTrace();
          }
        }

        public void onServiceDisconnected(ComponentName paramAnonymousComponentName)
        {
        }
      }
      , 1);
      return;
    }
    new UserFeedback().startFeedback(localUserFeedbackSpec);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.feedback.FeedbackUtils
 * JD-Core Version:    0.6.2
 */