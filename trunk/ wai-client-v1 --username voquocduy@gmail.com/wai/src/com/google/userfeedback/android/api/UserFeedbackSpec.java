package com.google.userfeedback.android.api;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.View;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.List;

public class UserFeedbackSpec
{
  private Bitmap mAlternateScreenshot;
  private String mBucket;
  private String mCategoryTag;
  private UserFeedbackCrashData mCrashData;
  private View mCurrentView;
  private String mLogFilter;
  private Activity mParentActivity;
  private List<ProductSpecificBinaryDataHolder> mProductBinaryData;
  private boolean mScreenshotEnabled;
  private String mSelectedAccount;
  private boolean mShouldShowPopupOnAnonymousSubmission;
  private boolean mShouldShowPopupOnEmptyDescription;
  private UiConfigurationOptions mUiConfigurationOptions;

  public UserFeedbackSpec(Activity paramActivity, View paramView, String paramString1, String paramString2)
  {
    this(paramActivity, paramView, paramString1, paramString2, null, true);
  }

  public UserFeedbackSpec(Activity paramActivity, View paramView, String paramString1, String paramString2, String paramString3)
  {
    this(paramActivity, paramView, paramString1, paramString2, paramString3, true);
  }

  private UserFeedbackSpec(Activity paramActivity, View paramView, String paramString1, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.mParentActivity = paramActivity;
    if (paramView != null)
    {
      this.mCurrentView = paramView.getRootView();
      this.mCurrentView.setDrawingCacheEnabled(true);
    }
    this.mLogFilter = paramString1;
    this.mCategoryTag = paramString2;
    this.mProductBinaryData = new LinkedList();
    this.mScreenshotEnabled = paramBoolean;
    this.mAlternateScreenshot = null;
    this.mShouldShowPopupOnAnonymousSubmission = false;
    this.mShouldShowPopupOnEmptyDescription = false;
    this.mCrashData = null;
    this.mBucket = paramString3;
    this.mUiConfigurationOptions = null;
  }

  public UserFeedbackSpec(Activity paramActivity, String paramString1, String paramString2)
  {
    this(paramActivity, null, paramString1, paramString2, null, false);
  }

  private byte[] getBytes(Object paramObject)
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    try
    {
      ObjectOutputStream localObjectOutputStream = new ObjectOutputStream(localByteArrayOutputStream);
      localObjectOutputStream.writeObject(paramObject);
      localObjectOutputStream.flush();
      localObjectOutputStream.close();
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      localByteArrayOutputStream.close();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  @Deprecated
  public UserFeedbackSpec addProductSpecificBinaryData(String paramString1, String paramString2, Object paramObject)
  {
    this.mProductBinaryData.add(new ProductSpecificBinaryDataHolder(paramString1, paramString2, getBytes(paramObject)));
    return this;
  }

  public UserFeedbackSpec addProductSpecificBinaryData(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    this.mProductBinaryData.add(new ProductSpecificBinaryDataHolder(paramString1, paramString2, paramArrayOfByte));
    return this;
  }

  public Activity getActivity()
  {
    return this.mParentActivity;
  }

  public String getBucket()
  {
    return this.mBucket;
  }

  public String getCategoryTag()
  {
    return this.mCategoryTag;
  }

  public Context getContext()
  {
    return this.mParentActivity.getApplicationContext();
  }

  public UserFeedbackCrashData getCrashData()
  {
    return this.mCrashData;
  }

  public Bitmap getCurrentScreenshot()
  {
    boolean bool = isScreenshotEnabled();
    Bitmap localBitmap1 = null;
    if (bool)
    {
      if (this.mAlternateScreenshot == null)
        break label25;
      localBitmap1 = this.mAlternateScreenshot;
    }
    label25: View localView;
    do
    {
      return localBitmap1;
      localView = this.mCurrentView;
      localBitmap1 = null;
    }
    while (localView == null);
    try
    {
      Bitmap localBitmap2 = this.mCurrentView.getDrawingCache();
      return localBitmap2;
    }
    catch (Exception localException)
    {
      Log.e("GFEEDBACK", "Error generating screenshot: " + localException.getMessage(), localException);
    }
    return null;
  }

  public String getLogFilter()
  {
    return this.mLogFilter;
  }

  List<ProductSpecificBinaryDataHolder> getProductSpecificBinaryData()
  {
    return this.mProductBinaryData;
  }

  public String getSelectedAccount()
  {
    return this.mSelectedAccount;
  }

  public UiConfigurationOptions getUiConfigurationOptions()
  {
    return this.mUiConfigurationOptions;
  }

  public boolean isScreenshotEnabled()
  {
    return this.mScreenshotEnabled;
  }

  public UserFeedbackSpec setCrashData(UserFeedbackCrashData paramUserFeedbackCrashData)
  {
    this.mCrashData = paramUserFeedbackCrashData;
    return this;
  }

  public void setScreenshot(Bitmap paramBitmap)
  {
    setScreenshotEnabled(true);
    this.mAlternateScreenshot = paramBitmap;
  }

  public UserFeedbackSpec setScreenshotEnabled(boolean paramBoolean)
  {
    this.mScreenshotEnabled = paramBoolean;
    return this;
  }

  public void setSelectedAccount(String paramString)
  {
    this.mSelectedAccount = paramString;
  }

  public UserFeedbackSpec setUiConfigurationOptions(UiConfigurationOptions paramUiConfigurationOptions)
  {
    this.mUiConfigurationOptions = paramUiConfigurationOptions;
    return this;
  }

  boolean shouldShowPopupForAnonymousSubmission()
  {
    return this.mShouldShowPopupOnAnonymousSubmission;
  }

  boolean shouldShowPopupForEmptyDescription()
  {
    return this.mShouldShowPopupOnEmptyDescription;
  }

  public UserFeedbackSpec showPopupOnAnonymousSubmission()
  {
    this.mShouldShowPopupOnAnonymousSubmission = true;
    return this;
  }

  public UserFeedbackSpec showPopupOnEmptyDescription()
  {
    this.mShouldShowPopupOnEmptyDescription = true;
    return this;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.userfeedback.android.api.UserFeedbackSpec
 * JD-Core Version:    0.6.2
 */