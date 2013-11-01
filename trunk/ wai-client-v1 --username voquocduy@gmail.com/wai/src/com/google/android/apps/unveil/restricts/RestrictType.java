package com.google.android.apps.unveil.restricts;

import android.content.Context;
import com.google.android.apps.unveil.R.string;

public enum RestrictType
{
  private final int resId;

  static
  {
    PRETTY_NAME_CATEGORY = new RestrictType("PRETTY_NAME_CATEGORY", 1, R.string.restricts_type_pretty_name_category);
    GENDER = new RestrictType("GENDER", 2, R.string.restricts_type_gender);
    COLOR = new RestrictType("COLOR", 3, R.string.restricts_type_color);
    BRAND = new RestrictType("BRAND", 4, R.string.restricts_type_brand);
    RestrictType[] arrayOfRestrictType = new RestrictType[5];
    arrayOfRestrictType[0] = PRODUCT_CATEGORY;
    arrayOfRestrictType[1] = PRETTY_NAME_CATEGORY;
    arrayOfRestrictType[2] = GENDER;
    arrayOfRestrictType[3] = COLOR;
    arrayOfRestrictType[4] = BRAND;
  }

  private RestrictType(int paramInt)
  {
    this.resId = paramInt;
  }

  public static RestrictType fromString(String paramString, Context paramContext)
  {
    for (RestrictType localRestrictType : values())
      if (paramContext.getString(localRestrictType.resId).contentEquals(paramString))
        return localRestrictType;
    return null;
  }

  public String getName(Context paramContext)
  {
    return paramContext.getString(this.resId);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.RestrictType
 * JD-Core Version:    0.6.2
 */