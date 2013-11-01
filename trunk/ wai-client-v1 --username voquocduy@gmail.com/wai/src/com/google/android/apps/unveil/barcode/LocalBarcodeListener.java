package com.google.android.apps.unveil.barcode;

import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import java.util.LinkedHashSet;
import java.util.Set;

public class LocalBarcodeListener
  implements BarcodeScanner.BarcodeListener
{
  private final Callback callback;
  private final Set<Barcode> lastScannedBarcodes = new LinkedHashSet();

  public LocalBarcodeListener(Callback paramCallback)
  {
    this.callback = paramCallback;
  }

  public void clearLastScannedBarcodes()
  {
    this.lastScannedBarcodes.clear();
  }

  public void onBarcodeRecognized(TimestampedFrame paramTimestampedFrame, Barcode paramBarcode)
  {
    if (this.lastScannedBarcodes.contains(paramBarcode))
      return;
    this.lastScannedBarcodes.add(paramBarcode);
    this.callback.onBarcodeRecognized(paramTimestampedFrame, paramBarcode);
  }

  public static abstract interface Callback
  {
    public abstract void onBarcodeRecognized(TimestampedFrame paramTimestampedFrame, Barcode paramBarcode);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.LocalBarcodeListener
 * JD-Core Version:    0.6.2
 */