package com.google.android.apps.unveil.env.puggle;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.PuggleResultItem;
import java.util.Comparator;

public class PuggleResultItems
{
  private static final UnveilLogger logger = new UnveilLogger();

  public static double safeConvertPriceStringToDouble(String paramString)
  {
    String str = paramString.replace("$", "").replaceAll(",", "");
    try
    {
      double d = Double.valueOf(str).doubleValue();
      return d;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      logger.e("Price from ItemList is not parceable: %s", new Object[] { paramString });
    }
    return 0.0D;
  }

  public static class PriceComparator
    implements Comparator<PuggleResultItem>
  {
    private int sign;

    public PriceComparator(boolean paramBoolean)
    {
      if (paramBoolean)
      {
        this.sign = 1;
        return;
      }
      this.sign = -1;
    }

    public int compare(PuggleResultItem paramPuggleResultItem1, PuggleResultItem paramPuggleResultItem2)
    {
      if ((TextUtils.isEmpty(paramPuggleResultItem1.getPrice())) && (TextUtils.isEmpty(paramPuggleResultItem2.getPrice())))
        return 0;
      if (TextUtils.isEmpty(paramPuggleResultItem1.getPrice()))
        return 1;
      if (TextUtils.isEmpty(paramPuggleResultItem2.getPrice()))
        return -1;
      return this.sign * (int)(paramPuggleResultItem1.price - paramPuggleResultItem2.price);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.puggle.PuggleResultItems
 * JD-Core Version:    0.6.2
 */