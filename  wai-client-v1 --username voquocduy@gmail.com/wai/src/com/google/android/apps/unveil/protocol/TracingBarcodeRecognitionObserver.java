package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.barcode.BarcodeRecognizer.Observer;
import com.google.android.apps.unveil.barcode.BarcodeRecognizer.Stage;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.TracingProtos.SpanVariable.Type;

public class TracingBarcodeRecognitionObserver
  implements BarcodeRecognizer.Observer
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final TraceTracker traceTracker;

  public TracingBarcodeRecognitionObserver(TraceTracker paramTraceTracker)
  {
    this.traceTracker = paramTraceTracker;
  }

  private static TracingProtos.SpanVariable.Type spanFor(BarcodeRecognizer.Stage paramStage)
  {
    switch (1.$SwitchMap$com$google$android$apps$unveil$barcode$BarcodeRecognizer$Stage[paramStage.ordinal()])
    {
    default:
      throw new AssertionError();
    case 1:
      return TracingProtos.SpanVariable.Type.BARCODE_RECOGNIZER_LANDSCAPE_AND_QR;
    case 2:
      return TracingProtos.SpanVariable.Type.BARCODE_RECOGNIZER_LOAD;
    case 3:
    }
    return TracingProtos.SpanVariable.Type.BARCODE_RECOGNIZER_PORTRAIT;
  }

  public void onDoneAnalyzing(long paramLong, int paramInt)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(paramLong);
    arrayOfObject[1] = Integer.valueOf(paramInt);
    localUnveilLogger.d("Adding BARCODE_TOTAL_LATENCY of length %d to frame %d", arrayOfObject);
    this.traceTracker.addDurationInterval(TracingProtos.SpanVariable.Type.BARCODE_TOTAL_LATENCY, (int)paramLong, paramInt);
  }

  public void onEndStage(BarcodeRecognizer.Stage paramStage, int paramInt)
  {
    this.traceTracker.endInterval(spanFor(paramStage), paramInt);
  }

  public void onStartStage(BarcodeRecognizer.Stage paramStage, int paramInt)
  {
    this.traceTracker.beginTraceAction(paramInt);
    this.traceTracker.beginInterval(spanFor(paramStage), paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.TracingBarcodeRecognitionObserver
 * JD-Core Version:    0.6.2
 */