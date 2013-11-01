package com.google.android.apps.unveil.intents;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.apps.unveil.barcode.Barcode;
import com.google.android.apps.unveil.env.UnveilLogger;

public class ZxingScan extends Intents.IntentRecipe
{
  private static final String PRODUCTS_UK_URL = "http://www.google.co.uk/m/products/scan";
  private static final String PRODUCTS_URL = "http://www.google.com/m/products/scan";
  private static final CharSequence RETURN_CODE_PLACEHOLDER = "{CODE}";
  private static final String RETURN_URL_PARAM = "ret";
  private static final String SCAN_ACTION = "com.google.zxing.client.android.SCAN";
  private static final String VIEW_ACTION = "android.intent.action.VIEW";
  private static final String ZXING_URL = "http://zxing.appspot.com/scan";
  private static final UnveilLogger logger = new UnveilLogger();
  private final Intent incoming;

  public ZxingScan(Intent paramIntent)
  {
    super(false, false);
    if (getScanType(paramIntent) == null)
      throw new IllegalArgumentException(paramIntent + " cannot be handled by a ZxingScan recipe.");
    this.incoming = paramIntent;
  }

  private static ScanType getScanType(Intent paramIntent)
  {
    String str = paramIntent.getAction();
    if ("com.google.zxing.client.android.SCAN".equals(str))
      return ScanType.NATIVE_APP;
    if (("android.intent.action.VIEW".equals(str)) && (paramIntent.getData() != null))
      return getScanTypeForUri(paramIntent.getDataString());
    return null;
  }

  private static ScanType getScanTypeForUri(String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (paramString.startsWith("http://zxing.appspot.com/scan"))
        return ScanType.SCAN_BY_ZXING_URL;
      if ((paramString.startsWith("http://www.google.co.uk/m/products/scan")) || (paramString.startsWith("http://www.google.com/m/products/scan")))
        return ScanType.SCAN_BY_PRODUCT_URL;
    }
    return null;
  }

  private String getUrlForBarcode(Barcode paramBarcode)
  {
    switch (1.$SwitchMap$com$google$android$apps$unveil$intents$ZxingScan$ScanType[getScanTypeForUri(this.incoming.getDataString()).ordinal()])
    {
    default:
      throw new AssertionError("Unknown scan type");
    case 1:
      String str2 = this.incoming.getDataString();
      int i = str2.lastIndexOf("/scan");
      return str2.substring(0, i) + "?q=" + paramBarcode.getValue() + "&source=goggles";
    case 2:
      String str1 = getUrlTemplate(this.incoming);
      if (str1 != null)
        return str1.replace(RETURN_CODE_PLACEHOLDER, paramBarcode.getValue());
      return "";
    case 3:
    }
    throw new UnsupportedOperationException("Cannot create a URL when scanning by native app intent.");
  }

  private static String getUrlTemplate(Intent paramIntent)
  {
    if (paramIntent.getData() != null)
      return paramIntent.getData().getQueryParameter("ret");
    return null;
  }

  public static boolean matches(Intent paramIntent)
  {
    return getScanType(paramIntent) != null;
  }

  public HandleStrategy getHandleStrategy()
  {
    return getScanType(this.incoming).handleStrategy;
  }

  public Intent getIntentToFire(Barcode paramBarcode)
  {
    if (getHandleStrategy() != HandleStrategy.START_ACTIVITY)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = getHandleStrategy();
      localUnveilLogger.w("Creating an intent to start another activity even though our handle strategy is %s", arrayOfObject);
    }
    Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(getUrlForBarcode(paramBarcode)));
    localIntent.addFlags(524288);
    return localIntent;
  }

  public Intent getResponse(Barcode paramBarcode)
  {
    if (getHandleStrategy() != HandleStrategy.ACTIVITY_RESULT)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = getHandleStrategy();
      localUnveilLogger.w("Creating an intent to return as an activity result even though handle strategy is %s", arrayOfObject);
    }
    Intent localIntent = new Intent(this.incoming.getAction());
    localIntent.putExtra("SCAN_RESULT", paramBarcode.getValue());
    localIntent.putExtra("SCAN_RESULT_FORMAT", paramBarcode.getFormat().toString());
    return localIntent;
  }

  public static class Extras
  {
    public static class Outgoing
    {
      public static final String SCAN_RESULT = "SCAN_RESULT";
      public static final String SCAN_RESULT_FORMAT = "SCAN_RESULT_FORMAT";
    }
  }

  public static enum HandleStrategy
  {
    static
    {
      HandleStrategy[] arrayOfHandleStrategy = new HandleStrategy[2];
      arrayOfHandleStrategy[0] = ACTIVITY_RESULT;
      arrayOfHandleStrategy[1] = START_ACTIVITY;
    }
  }

  private static enum ScanType
  {
    public final ZxingScan.HandleStrategy handleStrategy;

    static
    {
      SCAN_BY_PRODUCT_URL = new ScanType("SCAN_BY_PRODUCT_URL", 2, ZxingScan.HandleStrategy.START_ACTIVITY);
      ScanType[] arrayOfScanType = new ScanType[3];
      arrayOfScanType[0] = NATIVE_APP;
      arrayOfScanType[1] = SCAN_BY_ZXING_URL;
      arrayOfScanType[2] = SCAN_BY_PRODUCT_URL;
    }

    private ScanType(ZxingScan.HandleStrategy paramHandleStrategy)
    {
      this.handleStrategy = paramHandleStrategy;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.intents.ZxingScan
 * JD-Core Version:    0.6.2
 */