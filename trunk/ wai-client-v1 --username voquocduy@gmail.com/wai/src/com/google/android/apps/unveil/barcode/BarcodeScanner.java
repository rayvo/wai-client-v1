package com.google.android.apps.unveil.barcode;

import android.os.SystemClock;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.nonstop.FrameProcessor;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.ui.RegionSelectorView;
import com.google.goggles.TracingProtos.ProcessorStatus.Type;
import java.util.Vector;

public class BarcodeScanner extends FrameProcessor
{
  private static final int BARCODE_LIFE_MILLIS = 5000;
  private static final UnveilLogger logger = new UnveilLogger();
  private Barcode barcode;
  private BarcodeListener barcodeListener;
  private final Object barcodeLock = new Object();
  private long barcodeMillis;
  private final BarcodeRecognizer barcodeRecognizer;
  private final Vector<String> debugText = new Vector();
  private volatile boolean enabled = true;
  private int framesProcessed;
  private long lastProcessDuration;
  private final RegionSelectorView regionSelector;
  private boolean runSinceLastTime = false;
  private final boolean useAdaptiveDutyCycle;
  private final Viewport viewport;

  public BarcodeScanner(BarcodeListener paramBarcodeListener, Viewport paramViewport, RegionSelectorView paramRegionSelectorView, BarcodeRecognizer.Mode paramMode, BarcodeRecognizer.Observer paramObserver, boolean paramBoolean)
  {
    this.barcodeRecognizer = new BarcodeRecognizer(paramMode, paramObserver);
    this.barcodeListener = paramBarcodeListener;
    this.viewport = paramViewport;
    this.regionSelector = paramRegionSelectorView;
    this.useAdaptiveDutyCycle = paramBoolean;
  }

  private static boolean regionSelectorContainsBarcode(RegionSelectorView paramRegionSelectorView, Barcode paramBarcode)
  {
    return (paramRegionSelectorView == null) || (paramRegionSelectorView.containsRect(paramBarcode.getBoundingBox()));
  }

  private void setBarcode(Barcode paramBarcode)
  {
    synchronized (this.barcodeLock)
    {
      this.barcode = paramBarcode;
      this.barcodeMillis = SystemClock.uptimeMillis();
      return;
    }
  }

  private void updateDutyCycle()
  {
    if (!this.useAdaptiveDutyCycle)
      return;
    if (this.framesProcessed == 0)
    {
      setDutyCycle(2);
      return;
    }
    if (this.barcode != null)
    {
      disable();
      return;
    }
    int i = 2 + this.framesProcessed / 25;
    if (i < 5)
    {
      setDutyCycle(i);
      return;
    }
    disable();
  }

  public void disable()
  {
    this.enabled = false;
  }

  public void enable()
  {
    this.enabled = true;
  }

  public Barcode getBarcode()
  {
    synchronized (this.barcodeLock)
    {
      if ((this.barcode != null) && (this.barcodeMillis > SystemClock.uptimeMillis() - 5000L))
      {
        Barcode localBarcode = this.barcode;
        return localBarcode;
      }
      return null;
    }
  }

  public BarcodeListener getBarcodeListener()
  {
    return this.barcodeListener;
  }

  public Vector<String> getDebugText()
  {
    String str1;
    Vector localVector2;
    if (this.runSinceLastTime)
    {
      this.debugText.clear();
      Vector localVector1 = this.debugText;
      if (this.enabled)
        break label70;
      str1 = "DISABLED";
      localVector1.add(str1);
      localVector2 = this.debugText;
      if (getBarcode() != null)
        break label108;
    }
    label70: label108: for (String str2 = "no barcode found."; ; str2 = "found barcode.")
    {
      localVector2.add(str2);
      this.runSinceLastTime = false;
      return this.debugText;
      str1 = "processed in " + this.lastProcessDuration + " ms with DC of " + getDutyCycle();
      break;
    }
  }

  protected TracingProtos.ProcessorStatus.Type getProcessorType()
  {
    return TracingProtos.ProcessorStatus.Type.BARCODE_SCANNER;
  }

  public void onInit(Size paramSize)
  {
    if (paramSize == null)
    {
      logger.e("Tried to initialize BarcodeScanner with a null size. Disabling barcode scanning.", new Object[0]);
      this.enabled = false;
      return;
    }
    this.barcodeRecognizer.createBuffers(paramSize.width, paramSize.height);
  }

  public void onProcessFrame(TimestampedFrame paramTimestampedFrame)
  {
    this.runSinceLastTime = true;
    if (!this.enabled)
    {
      this.lastProcessDuration = -1L;
      discardFrame();
      return;
    }
    long l = System.currentTimeMillis();
    Barcode localBarcode = this.barcodeRecognizer.multiStageRecognize(paramTimestampedFrame);
    this.lastProcessDuration = (System.currentTimeMillis() - l);
    this.framesProcessed = (1 + this.framesProcessed);
    if ((localBarcode != null) && ((this.viewport == null) || (this.viewport.isBarcodeBoxGood(localBarcode.getBoundingBox()))) && (regionSelectorContainsBarcode(this.regionSelector, localBarcode)))
    {
      logger.v("Set barcode to %s", new Object[] { localBarcode });
      setBarcode(localBarcode);
      if (this.barcodeListener != null)
      {
        logger.v("Notifying barcode listener.", new Object[0]);
        this.barcodeListener.onBarcodeRecognized(paramTimestampedFrame, localBarcode);
      }
    }
    updateDutyCycle();
  }

  public void reset()
  {
    this.barcode = null;
    this.barcodeMillis = 0L;
    this.runSinceLastTime = false;
    this.framesProcessed = 0;
    this.lastProcessDuration = 0L;
    this.debugText.clear();
    enable();
    updateDutyCycle();
  }

  public void setBarcodeListener(BarcodeListener paramBarcodeListener)
  {
    this.barcodeListener = paramBarcodeListener;
  }

  public static abstract interface BarcodeListener
  {
    public abstract void onBarcodeRecognized(TimestampedFrame paramTimestampedFrame, Barcode paramBarcode);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.BarcodeScanner
 * JD-Core Version:    0.6.2
 */