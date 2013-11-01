package com.google.android.apps.unveil.barcode;

import android.graphics.Rect;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.client.result.ParsedResult;
import com.google.zxing.client.result.ResultParser;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

public class BarcodeRecognizer
{
  private static final int CONFIDENCE_LEVEL = 2;
  static final int MAX_BARCODE_FRAME_HEIGHT = 480;
  static final int MAX_BARCODE_FRAME_WIDTH = 800;
  private static final int MIN_BARCODE_FRAME_HEIGHT = 240;
  private static final int MIN_BARCODE_FRAME_WIDTH = 240;
  private static final MultiFormatReader ONE_AND_TWO_D_READER = newMultiFormatReader(createOneAndTwoDimenFormats());
  private static final List<BarcodeFormat> ONE_D_FORMATS;
  private static final MultiFormatReader ONE_D_READER = newMultiFormatReader(ONE_D_FORMATS);
  private static final int PRUNING_THRESHOLD = 2;
  private static final List<BarcodeFormat> TWO_D_FORMATS;
  private static final MultiFormatReader TWO_D_READER = newMultiFormatReader(TWO_D_FORMATS);
  private static final UnveilLogger logger = new UnveilLogger();
  private final Mode barcodeMode;
  private volatile Buffers globalBuffers;
  private final Observer observer;
  private final MultiStageRecognizerContext recognizerContext = new MultiStageRecognizerContext(null);

  static
  {
    BarcodeFormat[] arrayOfBarcodeFormat1 = new BarcodeFormat[4];
    arrayOfBarcodeFormat1[0] = BarcodeFormat.UPC_A;
    arrayOfBarcodeFormat1[1] = BarcodeFormat.EAN_13;
    arrayOfBarcodeFormat1[2] = BarcodeFormat.UPC_E;
    arrayOfBarcodeFormat1[3] = BarcodeFormat.EAN_8;
    ONE_D_FORMATS = Arrays.asList(arrayOfBarcodeFormat1);
    BarcodeFormat[] arrayOfBarcodeFormat2 = new BarcodeFormat[1];
    arrayOfBarcodeFormat2[0] = BarcodeFormat.QR_CODE;
    TWO_D_FORMATS = Arrays.asList(arrayOfBarcodeFormat2);
  }

  public BarcodeRecognizer(Mode paramMode, Observer paramObserver)
  {
    this.barcodeMode = paramMode;
    this.observer = paramObserver;
  }

  private static boolean acceptableBarcode(Result paramResult)
  {
    BarcodeFormat localBarcodeFormat = paramResult.getBarcodeFormat();
    return ((localBarcodeFormat != BarcodeFormat.UPC_A) && (localBarcodeFormat != BarcodeFormat.EAN_13) && (localBarcodeFormat != BarcodeFormat.UPC_E) && (localBarcodeFormat != BarcodeFormat.EAN_8)) || (getNumConsistentLines(paramResult) >= 2);
  }

  private static List<BarcodeFormat> createOneAndTwoDimenFormats()
  {
    if ((ONE_D_FORMATS == null) || (TWO_D_FORMATS == null))
      throw new IllegalStateException("Expected both one- and two-dimensional formats to be set.");
    ArrayList localArrayList = new ArrayList(ONE_D_FORMATS);
    localArrayList.addAll(TWO_D_FORMATS);
    return localArrayList;
  }

  // ERROR //
  private static Result decodeHelper(MultiFormatReader paramMultiFormatReader, BinaryBitmap paramBinaryBitmap)
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: invokevirtual 137	com/google/zxing/MultiFormatReader:decodeWithState	(Lcom/google/zxing/BinaryBitmap;)Lcom/google/zxing/Result;
    //   5: astore 5
    //   7: aload 5
    //   9: astore 4
    //   11: aload_0
    //   12: invokevirtual 140	com/google/zxing/MultiFormatReader:reset	()V
    //   15: aload 4
    //   17: ifnull +14 -> 31
    //   20: aload 4
    //   22: invokestatic 142	com/google/android/apps/unveil/barcode/BarcodeRecognizer:acceptableBarcode	(Lcom/google/zxing/Result;)Z
    //   25: ifne +6 -> 31
    //   28: aconst_null
    //   29: astore 4
    //   31: aload 4
    //   33: areturn
    //   34: astore_3
    //   35: aload_0
    //   36: invokevirtual 140	com/google/zxing/MultiFormatReader:reset	()V
    //   39: aconst_null
    //   40: astore 4
    //   42: goto -27 -> 15
    //   45: astore_2
    //   46: aload_0
    //   47: invokevirtual 140	com/google/zxing/MultiFormatReader:reset	()V
    //   50: aload_2
    //   51: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   0	7	34	com/google/zxing/ReaderException
    //   0	7	45	finally
  }

  private static Rect getBarcodeScanningRect(int paramInt1, int paramInt2)
  {
    int i = Math.min(paramInt1, 800);
    int j = Math.min(paramInt2, 480);
    int k = (paramInt1 - i) / 2;
    int m = (paramInt2 - j) / 2;
    return new Rect(k, m, k + i, m + j);
  }

  private static int getNumConsistentLines(Result paramResult)
  {
    int i = 2;
    Hashtable localHashtable = paramResult.getResultMetadata();
    if ((localHashtable != null) && (localHashtable.containsKey(ResultMetadataType.CONSISTENT_LINES)))
      i = ((Integer)localHashtable.get(ResultMetadataType.CONSISTENT_LINES)).intValue();
    return i;
  }

  static Rect maybeTranslate(Rect paramRect, int paramInt1, int paramInt2)
  {
    UnveilLogger localUnveilLogger = logger;
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = paramRect;
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    localUnveilLogger.d("Possibly translating %s; imageWidth=%d, imageHeight=%d", arrayOfObject);
    if ((paramInt1 <= 800) && (paramInt2 <= 480))
      return paramRect;
    int i = 0;
    if (paramInt1 > 800)
      i = (paramInt1 - 800) / 2;
    int j = 0;
    if (paramInt2 > 480)
      j = (paramInt2 - 480) / 2;
    Rect localRect = new Rect(paramRect);
    localRect.offset(i, j);
    return localRect;
  }

  private static MultiFormatReader newMultiFormatReader(List<BarcodeFormat> paramList)
  {
    if (paramList == null)
      throw new IllegalStateException("'formats' was null.");
    Hashtable localHashtable = new Hashtable(2);
    localHashtable.put(DecodeHintType.POSSIBLE_FORMATS, new Vector(paramList));
    localHashtable.put(DecodeHintType.CONFIDENCE_LEVEL, Integer.valueOf(2));
    MultiFormatReader localMultiFormatReader = new MultiFormatReader();
    localMultiFormatReader.setHints(localHashtable);
    return localMultiFormatReader;
  }

  static Rect resultPointsToRect(ResultPoint[] paramArrayOfResultPoint, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramArrayOfResultPoint.length < 1)
      throw new IllegalArgumentException("Must have at least one point");
    if (paramArrayOfResultPoint.length > 4)
      logger.v("More than 4 points in the ResultPoint array", new Object[0]);
    int i = (int)paramArrayOfResultPoint[0].getX();
    int j = (int)paramArrayOfResultPoint[0].getY();
    int k = (int)paramArrayOfResultPoint[0].getX();
    int m = (int)paramArrayOfResultPoint[0].getY();
    int n = 1;
    if (n < paramArrayOfResultPoint.length)
    {
      if (paramArrayOfResultPoint[n].getX() < i)
      {
        i = (int)paramArrayOfResultPoint[n].getX();
        label104: if (paramArrayOfResultPoint[n].getY() >= j)
          break label161;
        j = (int)paramArrayOfResultPoint[n].getY();
      }
      while (true)
      {
        n++;
        break;
        if (paramArrayOfResultPoint[n].getX() <= k)
          break label104;
        k = (int)paramArrayOfResultPoint[n].getX();
        break label104;
        label161: if (paramArrayOfResultPoint[n].getY() > m)
          m = (int)paramArrayOfResultPoint[n].getY();
      }
    }
    if (paramBoolean)
      return rotate(new Rect(i, j, k, m), paramInt1);
    return new Rect(i, j, k, m);
  }

  static Barcode resultToBarcode(Result paramResult, boolean paramBoolean, int paramInt1, int paramInt2)
  {
    if (paramResult == null)
      return null;
    ParsedResult localParsedResult = ResultParser.parseResult(paramResult);
    Rect localRect = maybeTranslate(resultPointsToRect(paramResult.getResultPoints(), paramBoolean, paramInt1, paramInt2), paramInt1, paramInt2);
    return new Barcode(localParsedResult.getType(), paramResult.getBarcodeFormat(), localParsedResult.getDisplayResult(), localRect);
  }

  static Rect rotate(Rect paramRect, int paramInt)
  {
    int i = Math.min(paramInt, 800);
    return new Rect(i - paramRect.bottom, paramRect.left, i - paramRect.top, paramRect.right);
  }

  public void createBuffers(int paramInt1, int paramInt2)
  {
    Rect localRect = getBarcodeScanningRect(paramInt1, paramInt2);
    try
    {
      localBuffers = new Buffers(paramInt1, paramInt2, localRect.left, localRect.top, localRect.width(), localRect.height());
      this.globalBuffers = localBuffers;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      while (true)
      {
        logger.e("Couldn't create buffers; disabling barcode recognition.", new Object[0]);
        Buffers localBuffers = null;
      }
    }
  }

  public Barcode multiStageRecognize(TimestampedFrame paramTimestampedFrame)
  {
    Buffers localBuffers = this.globalBuffers;
    if (localBuffers == null)
    {
      logger.e("No buffers for barcode recognition have been allocated.", new Object[0]);
      return null;
    }
    int i = paramTimestampedFrame.getWidth();
    int j = paramTimestampedFrame.getHeight();
    if ((localBuffers.dataWidth != i) || (localBuffers.dataHeight != j))
    {
      logger.e("The full preview frame size does not match the full frame in our buffers.", new Object[0]);
      return null;
    }
    byte[] arrayOfByte = paramTimestampedFrame.getRawData();
    int k = paramTimestampedFrame.getFrameNum();
    switch (1.$SwitchMap$com$google$android$apps$unveil$barcode$BarcodeRecognizer$Stage[this.recognizerContext.stage.ordinal()])
    {
    default:
      throw new AssertionError("Invalid switch case reached.");
    case 1:
      this.observer.onStartStage(Stage.LOAD, k);
      this.recognizerContext.startLoadTimestamp = System.currentTimeMillis();
      PlanarYUVLuminanceSource localPlanarYUVLuminanceSource = new PlanarYUVLuminanceSource(arrayOfByte, i, j, localBuffers.left, localBuffers.top, localBuffers.width, localBuffers.height, localBuffers.croppedCopySpace);
      UnveilHybridBinarizer localUnveilHybridBinarizer = new UnveilHybridBinarizer(localPlanarYUVLuminanceSource, localBuffers.bitMatrixBits, localBuffers.blackPoints);
      localUnveilHybridBinarizer.getBlackMatrix();
      this.recognizerContext.regularBitmap = new BinaryBitmap(localUnveilHybridBinarizer);
      RotatedPlanarYUVLuminanceSource localRotatedPlanarYUVLuminanceSource = new RotatedPlanarYUVLuminanceSource(arrayOfByte, i, j, localBuffers.top, i - localBuffers.left - localBuffers.width, localBuffers.height, localBuffers.width, localBuffers.croppedCopySpace);
      RotatedUnveilHybridBinarizer localRotatedUnveilHybridBinarizer = new RotatedUnveilHybridBinarizer(localRotatedPlanarYUVLuminanceSource, localBuffers.bitMatrixBits, localBuffers.blackPoints);
      this.recognizerContext.rotatedBitmap = new BinaryBitmap(localRotatedUnveilHybridBinarizer);
      this.recognizerContext.stage = Stage.LANDSCAPE_AND_QR;
      this.observer.onEndStage(Stage.LOAD, k);
      if (this.barcodeMode == Mode.MUTLISTAGE)
        return null;
    case 2:
      this.observer.onStartStage(Stage.LANDSCAPE_AND_QR, k);
      Result localResult2 = decodeHelper(ONE_AND_TWO_D_READER, this.recognizerContext.regularBitmap);
      if (localResult2 != null)
      {
        this.recognizerContext.stage = Stage.LOAD;
        this.observer.onEndStage(Stage.LANDSCAPE_AND_QR, k);
        this.observer.onDoneAnalyzing(System.currentTimeMillis() - this.recognizerContext.startLoadTimestamp, k);
        return resultToBarcode(localResult2, false, i, j);
      }
      this.recognizerContext.stage = Stage.PORTRAIT;
      this.observer.onEndStage(Stage.LANDSCAPE_AND_QR, k);
      if (this.barcodeMode == Mode.MUTLISTAGE)
        return null;
      break;
    case 3:
    }
    this.observer.onStartStage(Stage.PORTRAIT, k);
    Result localResult1 = decodeHelper(ONE_D_READER, this.recognizerContext.rotatedBitmap);
    if (localResult1 != null)
    {
      this.recognizerContext.stage = Stage.LOAD;
      this.observer.onEndStage(Stage.PORTRAIT, k);
      this.observer.onDoneAnalyzing(System.currentTimeMillis() - this.recognizerContext.startLoadTimestamp, k);
      return resultToBarcode(localResult1, true, i, j);
    }
    this.recognizerContext.stage = Stage.LOAD;
    this.observer.onDoneAnalyzing(System.currentTimeMillis() - this.recognizerContext.startLoadTimestamp, k);
    this.observer.onEndStage(Stage.PORTRAIT, k);
    return null;
  }

  static class Buffers
  {
    final int[] bitMatrixBits;
    final byte[] blackPoints;
    final byte[] croppedCopySpace;
    final int dataHeight;
    final int dataWidth;
    final int height;
    final int left;
    final int top;
    final int width;

    Buffers(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
    {
      if ((paramInt1 <= 0) || (paramInt2 <= 0) || (paramInt3 < 0) || (paramInt4 < 0) || (paramInt5 <= 0) || (paramInt6 <= 0))
        throw new IllegalStateException("Argument less than or equal to zero.");
      if ((paramInt3 + paramInt5 > paramInt1) || (paramInt4 + paramInt6 > paramInt2))
        throw new IllegalStateException("Cropped box goes past original box.");
      if ((paramInt5 < 240) || (paramInt6 < 240))
        throw new IllegalStateException("Cropped box is too small.");
      this.dataWidth = paramInt1;
      this.dataHeight = paramInt2;
      this.left = paramInt3;
      this.top = paramInt4;
      this.width = paramInt5;
      this.height = paramInt6;
      this.bitMatrixBits = new int[Math.max(paramInt6 * (paramInt5 + 31 >> 5), paramInt5 * (paramInt6 + 31 >> 5))];
      this.blackPoints = new byte[(paramInt5 >> 3) * (paramInt6 >> 3)];
      this.croppedCopySpace = new byte[paramInt5 * paramInt6];
    }
  }

  public static enum Mode
  {
    static
    {
      Mode[] arrayOfMode = new Mode[2];
      arrayOfMode[0] = MUTLISTAGE;
      arrayOfMode[1] = SINGLE_STAGE;
    }
  }

  private class MultiStageRecognizerContext
  {
    BinaryBitmap regularBitmap = null;
    BinaryBitmap rotatedBitmap = null;
    BarcodeRecognizer.Stage stage = BarcodeRecognizer.Stage.LOAD;
    long startLoadTimestamp;

    private MultiStageRecognizerContext()
    {
    }
  }

  public static abstract interface Observer
  {
    public abstract void onDoneAnalyzing(long paramLong, int paramInt);

    public abstract void onEndStage(BarcodeRecognizer.Stage paramStage, int paramInt);

    public abstract void onStartStage(BarcodeRecognizer.Stage paramStage, int paramInt);
  }

  public static enum Stage
  {
    static
    {
      LANDSCAPE_AND_QR = new Stage("LANDSCAPE_AND_QR", 1);
      PORTRAIT = new Stage("PORTRAIT", 2);
      Stage[] arrayOfStage = new Stage[3];
      arrayOfStage[0] = LOAD;
      arrayOfStage[1] = LANDSCAPE_AND_QR;
      arrayOfStage[2] = PORTRAIT;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.BarcodeRecognizer
 * JD-Core Version:    0.6.2
 */