package com.google.android.apps.unveil.restricts;

import android.content.Context;
import com.google.android.apps.unveil.R.string;
import com.google.goggles.RestrictsProtos.Restricts.Gender;

public enum GenderMap
{
  private final RestrictsProtos.Restricts.Gender gender;
  private final int resId;

  static
  {
    MEN = new GenderMap("MEN", 1, RestrictsProtos.Restricts.Gender.MEN, R.string.restricts_gender_men);
    NEUTRAL = new GenderMap("NEUTRAL", 2, RestrictsProtos.Restricts.Gender.NEUTRAL, R.string.restricts_gender_neutral);
    UNKNOWN = new GenderMap("UNKNOWN", 3, RestrictsProtos.Restricts.Gender.UNKNOWN, R.string.restricts_gender_unknown);
    GenderMap[] arrayOfGenderMap = new GenderMap[4];
    arrayOfGenderMap[0] = WOMEN;
    arrayOfGenderMap[1] = MEN;
    arrayOfGenderMap[2] = NEUTRAL;
    arrayOfGenderMap[3] = UNKNOWN;
  }

  private GenderMap(RestrictsProtos.Restricts.Gender paramGender, int paramInt)
  {
    this.gender = paramGender;
    this.resId = paramInt;
  }

  public String getName(Context paramContext)
  {
    return paramContext.getString(this.resId);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.GenderMap
 * JD-Core Version:    0.6.2
 */