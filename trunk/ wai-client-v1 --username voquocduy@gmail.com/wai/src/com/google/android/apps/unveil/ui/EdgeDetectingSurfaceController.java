package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.SystemClock;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.env.ImageUtils;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.sensors.CameraManager;
import java.nio.ByteBuffer;

public class EdgeDetectingSurfaceController
{
  private static final int SCAN_AREA_WIDTH_PIXELS = 40;
  private static final int SCAN_COLOR = Color.rgb(24, 64, 186);
  private long animationStartTimeMs;
  private Picture background;
  private Direction direction;
  private Thread edgeCreatorThread;
  private volatile Picture edges;
  private final UnveilLogger logger = new UnveilLogger();
  private final Paint paint;
  private Picture scaledBackground;
  private final Bitmap scanner;
  private final Bitmap scannerRotated;
  private final SurfaceView targetSurface;

  public EdgeDetectingSurfaceController(Context paramContext, SurfaceView paramSurfaceView)
  {
    this.targetSurface = paramSurfaceView;
    this.targetSurface.setZOrderMediaOverlay(true);
    this.paint = new Paint();
    this.paint.setColor(SCAN_COLOR);
    this.scanner = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.glowing_line);
    this.scannerRotated = BitmapFactory.decodeResource(paramContext.getResources(), R.drawable.glowing_line_rotated);
    this.targetSurface.getHolder().addCallback(new SurfaceHolder.Callback()
    {
      public void surfaceChanged(SurfaceHolder paramAnonymousSurfaceHolder, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3)
      {
        if (EdgeDetectingSurfaceController.this.background != null)
          EdgeDetectingSurfaceController.this.render();
      }

      public void surfaceCreated(SurfaceHolder paramAnonymousSurfaceHolder)
      {
      }

      public void surfaceDestroyed(SurfaceHolder paramAnonymousSurfaceHolder)
      {
      }
    });
  }

  private void clearTempBackgroundData()
  {
    this.scaledBackground = null;
    this.edges = null;
    this.edgeCreatorThread = null;
  }

  void clearAnimationStartTime()
  {
    this.animationStartTimeMs = 0L;
  }

  public void clearBackground()
  {
    try
    {
      this.logger.d("clearing Background", new Object[0]);
      this.background = null;
      clearTempBackgroundData();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean generateOverlayInBackground()
  {
    boolean bool = false;
    while (true)
    {
      try
      {
        this.logger.d("generateOverlayInBackground()", new Object[0]);
        if (this.background == null)
        {
          this.logger.e("generateOverlayInBackground() called without an active background", new Object[0]);
          return bool;
        }
        if (this.edgeCreatorThread != null)
        {
          this.logger.e("generateOverlayInBackground() while already running", new Object[0]);
          bool = false;
          continue;
        }
      }
      finally
      {
      }
      this.edgeCreatorThread = new Thread(new Runnable()
      {
        public void run()
        {
          while (true)
          {
            Bitmap localBitmap;
            synchronized (EdgeDetectingSurfaceController.this)
            {
              if (EdgeDetectingSurfaceController.this.background != null)
              {
                EdgeDetectingSurfaceController.this.logger.i("Running edge detection worker", new Object[0]);
                int i = EdgeDetectingSurfaceController.this.background.getSize().width;
                int j = EdgeDetectingSurfaceController.this.background.getSize().height;
                byte[] arrayOfByte1 = EdgeDetectingSurfaceController.this.background.getYuvData();
                byte[] arrayOfByte2 = new byte[i * j];
                ByteBuffer localByteBuffer = ByteBuffer.wrap(arrayOfByte2);
                long l = SystemClock.uptimeMillis();
                ImageUtils.computeEdgeBitmap(i, j, arrayOfByte1, arrayOfByte2);
                localBitmap = Bitmap.createBitmap(i, j, Bitmap.Config.ALPHA_8);
                localBitmap.copyPixelsFromBuffer(localByteBuffer);
                if (CameraManager.isFrontFacingCamera(EdgeDetectingSurfaceController.this.targetSurface.getContext()))
                {
                  Matrix localMatrix = new Matrix();
                  localMatrix.preScale(-1.0F, 1.0F);
                  EdgeDetectingSurfaceController.access$302(EdgeDetectingSurfaceController.this, PictureFactory.createBitmap(Bitmap.createBitmap(localBitmap, 0, 0, localBitmap.getWidth(), localBitmap.getHeight(), localMatrix, true), 0));
                  UnveilLogger localUnveilLogger = EdgeDetectingSurfaceController.this.logger;
                  Object[] arrayOfObject = new Object[1];
                  arrayOfObject[0] = Long.valueOf(SystemClock.uptimeMillis() - l);
                  localUnveilLogger.v("Edge detection took: %d", arrayOfObject);
                }
              }
              else
              {
                EdgeDetectingSurfaceController.this.logger.i("Bailing from edge detection worker because background is null", new Object[0]);
                return;
              }
            }
            EdgeDetectingSurfaceController.access$302(EdgeDetectingSurfaceController.this, PictureFactory.createBitmap(localBitmap, 0));
          }
        }
      }
      , "Background edge and feature detection thread.");
      this.edgeCreatorThread.setPriority(1);
      if ((this.edgeCreatorThread != null) && (this.edgeCreatorThread.getState() == Thread.State.NEW))
      {
        this.logger.i("Starting edge detection worker", new Object[0]);
        this.edgeCreatorThread.start();
      }
      bool = true;
    }
  }

  public void recycle()
  {
    try
    {
      this.logger.d("recyle()ing", new Object[0]);
      this.edges = null;
      this.background = null;
      this.scaledBackground = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void render()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   6: astore_2
    //   7: aload_2
    //   8: ifnonnull +6 -> 14
    //   11: aload_0
    //   12: monitorexit
    //   13: return
    //   14: aload_0
    //   15: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   18: invokevirtual 91	android/view/SurfaceView:getHolder	()Landroid/view/SurfaceHolder;
    //   21: astore_3
    //   22: aload_3
    //   23: invokeinterface 183 1 0
    //   28: astore 4
    //   30: aload 4
    //   32: ifnull -21 -> 11
    //   35: fconst_1
    //   36: fstore 5
    //   38: aload_0
    //   39: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   42: ifnonnull +718 -> 760
    //   45: aload_0
    //   46: getfield 46	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   49: ldc 185
    //   51: iconst_0
    //   52: anewarray 4	java/lang/Object
    //   55: invokevirtual 129	com/google/android/apps/unveil/env/UnveilLogger:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   58: aload_0
    //   59: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   62: invokevirtual 189	android/view/SurfaceView:getWidth	()I
    //   65: i2f
    //   66: aload_0
    //   67: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   70: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   73: invokevirtual 198	android/graphics/Bitmap:getWidth	()I
    //   76: i2f
    //   77: fdiv
    //   78: fstore 35
    //   80: aload_0
    //   81: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   84: invokevirtual 201	android/view/SurfaceView:getHeight	()I
    //   87: i2f
    //   88: aload_0
    //   89: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   92: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   95: invokevirtual 202	android/graphics/Bitmap:getHeight	()I
    //   98: i2f
    //   99: fdiv
    //   100: fstore 36
    //   102: fload 35
    //   104: fconst_1
    //   105: fcmpg
    //   106: iflt +10 -> 116
    //   109: fload 36
    //   111: fconst_1
    //   112: fcmpg
    //   113: ifge +602 -> 715
    //   116: fload 35
    //   118: fload 36
    //   120: invokestatic 208	java/lang/Math:min	(FF)F
    //   123: fstore 37
    //   125: fload 37
    //   127: fstore 5
    //   129: new 210	android/graphics/Matrix
    //   132: dup
    //   133: invokespecial 211	android/graphics/Matrix:<init>	()V
    //   136: astore 38
    //   138: aload_0
    //   139: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   142: invokevirtual 215	android/view/SurfaceView:getContext	()Landroid/content/Context;
    //   145: invokestatic 221	com/google/android/apps/unveil/sensors/CameraManager:isFrontFacingCamera	(Landroid/content/Context;)Z
    //   148: ifeq +12 -> 160
    //   151: aload 38
    //   153: ldc 222
    //   155: fconst_1
    //   156: invokevirtual 226	android/graphics/Matrix:preScale	(FF)Z
    //   159: pop
    //   160: aload 38
    //   162: fload 5
    //   164: fload 5
    //   166: invokevirtual 226	android/graphics/Matrix:preScale	(FF)Z
    //   169: pop
    //   170: aload_0
    //   171: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   174: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   177: iconst_0
    //   178: iconst_0
    //   179: aload_0
    //   180: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   183: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   186: invokevirtual 198	android/graphics/Bitmap:getWidth	()I
    //   189: aload_0
    //   190: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   193: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   196: invokevirtual 202	android/graphics/Bitmap:getHeight	()I
    //   199: aload 38
    //   201: iconst_1
    //   202: invokestatic 230	android/graphics/Bitmap:createBitmap	(Landroid/graphics/Bitmap;IIIILandroid/graphics/Matrix;Z)Landroid/graphics/Bitmap;
    //   205: astore 41
    //   207: aload 41
    //   209: ifnull +13 -> 222
    //   212: aload_0
    //   213: aload 41
    //   215: iconst_0
    //   216: invokestatic 235	com/google/android/apps/unveil/env/PictureFactory:createBitmap	(Landroid/graphics/Bitmap;I)Lcom/google/android/apps/unveil/env/Picture;
    //   219: putfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   222: aload_0
    //   223: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   226: ifnull -215 -> 11
    //   229: aload 4
    //   231: aload_0
    //   232: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   235: invokevirtual 189	android/view/SurfaceView:getWidth	()I
    //   238: aload_0
    //   239: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   242: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   245: getfield 244	com/google/android/apps/unveil/env/Size:width	I
    //   248: isub
    //   249: iconst_2
    //   250: idiv
    //   251: i2f
    //   252: aload_0
    //   253: getfield 48	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:targetSurface	Landroid/view/SurfaceView;
    //   256: invokevirtual 201	android/view/SurfaceView:getHeight	()I
    //   259: aload_0
    //   260: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   263: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   266: getfield 247	com/google/android/apps/unveil/env/Size:height	I
    //   269: isub
    //   270: iconst_2
    //   271: idiv
    //   272: i2f
    //   273: invokevirtual 253	android/graphics/Canvas:translate	(FF)V
    //   276: aload 4
    //   278: aload_0
    //   279: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   282: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   285: fconst_0
    //   286: fconst_0
    //   287: aconst_null
    //   288: invokevirtual 257	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;FFLandroid/graphics/Paint;)V
    //   291: aload_0
    //   292: getfield 122	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:animationStartTimeMs	J
    //   295: lconst_0
    //   296: lcmp
    //   297: ifeq +402 -> 699
    //   300: aload 4
    //   302: iconst_0
    //   303: iconst_0
    //   304: aload_0
    //   305: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   308: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   311: getfield 244	com/google/android/apps/unveil/env/Size:width	I
    //   314: aload_0
    //   315: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   318: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   321: getfield 247	com/google/android/apps/unveil/env/Size:height	I
    //   324: invokevirtual 261	android/graphics/Canvas:clipRect	(IIII)Z
    //   327: pop
    //   328: invokestatic 267	java/lang/System:currentTimeMillis	()J
    //   331: aload_0
    //   332: getfield 122	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:animationStartTimeMs	J
    //   335: lsub
    //   336: l2d
    //   337: ldc2_w 268
    //   340: ddiv
    //   341: dstore 7
    //   343: dload 7
    //   345: dstore 9
    //   347: aload_0
    //   348: getfield 271	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:direction	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   351: getstatic 276	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction:TOP_TO_BOTTOM	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   354: if_acmpeq +594 -> 948
    //   357: aload_0
    //   358: getfield 271	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:direction	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   361: getstatic 279	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction:LEFT_TO_RIGHT	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   364: if_acmpne +6 -> 370
    //   367: goto +581 -> 948
    //   370: ldc_w 280
    //   373: dload 9
    //   375: d2f
    //   376: invokestatic 286	android/util/FloatMath:cos	(F)F
    //   379: fconst_2
    //   380: fdiv
    //   381: fadd
    //   382: fstore 11
    //   384: ldc 222
    //   386: dload 9
    //   388: d2f
    //   389: invokestatic 289	android/util/FloatMath:sin	(F)F
    //   392: fmul
    //   393: fstore 12
    //   395: dload 7
    //   397: dconst_1
    //   398: dcmpg
    //   399: ifge +389 -> 788
    //   402: aload_0
    //   403: getfield 59	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:paint	Landroid/graphics/Paint;
    //   406: ldc2_w 290
    //   409: dload 7
    //   411: dmul
    //   412: d2i
    //   413: invokevirtual 294	android/graphics/Paint:setAlpha	(I)V
    //   416: aload_0
    //   417: getfield 271	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:direction	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   420: getstatic 297	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction:BOTTOM_TO_TOP	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   423: if_acmpeq +536 -> 959
    //   426: aload_0
    //   427: getfield 271	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:direction	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   430: getstatic 276	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction:TOP_TO_BOTTOM	Lcom/google/android/apps/unveil/ui/EdgeDetectingSurfaceController$Direction;
    //   433: if_acmpne +532 -> 965
    //   436: goto +523 -> 959
    //   439: aload_0
    //   440: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   443: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   446: getfield 244	com/google/android/apps/unveil/env/Size:width	I
    //   449: istore 14
    //   451: aload_0
    //   452: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   455: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   458: getfield 247	com/google/android/apps/unveil/env/Size:height	I
    //   461: istore 15
    //   463: iload 13
    //   465: ifeq +506 -> 971
    //   468: iload 15
    //   470: istore 16
    //   472: bipush 216
    //   474: fload 11
    //   476: iload 16
    //   478: bipush 40
    //   480: iadd
    //   481: i2f
    //   482: fmul
    //   483: f2i
    //   484: iadd
    //   485: istore 17
    //   487: iload 17
    //   489: bipush 40
    //   491: iadd
    //   492: istore 18
    //   494: iload 13
    //   496: ifeq +319 -> 815
    //   499: new 299	android/graphics/Rect
    //   502: dup
    //   503: iconst_0
    //   504: iload 17
    //   506: iload 14
    //   508: iload 18
    //   510: invokespecial 302	android/graphics/Rect:<init>	(IIII)V
    //   513: astore 19
    //   515: aload 4
    //   517: invokevirtual 305	android/graphics/Canvas:save	()I
    //   520: pop
    //   521: fload 12
    //   523: fconst_0
    //   524: fcmpg
    //   525: ifge +276 -> 801
    //   528: aload 4
    //   530: ldc_w 306
    //   533: iload 14
    //   535: iconst_2
    //   536: idiv
    //   537: i2f
    //   538: ldc_w 307
    //   541: invokevirtual 311	android/graphics/Canvas:rotate	(FFF)V
    //   544: aload 4
    //   546: fconst_0
    //   547: bipush 40
    //   549: iload 16
    //   551: isub
    //   552: i2f
    //   553: invokevirtual 253	android/graphics/Canvas:translate	(FF)V
    //   556: iload 16
    //   558: iload 18
    //   560: isub
    //   561: istore 21
    //   563: iload 16
    //   565: iload 17
    //   567: isub
    //   568: istore 22
    //   570: new 299	android/graphics/Rect
    //   573: dup
    //   574: iconst_0
    //   575: iload 21
    //   577: iload 14
    //   579: iload 22
    //   581: invokespecial 302	android/graphics/Rect:<init>	(IIII)V
    //   584: astore 23
    //   586: aload_0
    //   587: getfield 87	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scannerRotated	Landroid/graphics/Bitmap;
    //   590: astore 24
    //   592: aload_0
    //   593: getfield 59	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:paint	Landroid/graphics/Paint;
    //   596: astore 25
    //   598: aload 4
    //   600: aload 24
    //   602: aconst_null
    //   603: aload 23
    //   605: aload 25
    //   607: invokevirtual 314	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V
    //   610: aload 4
    //   612: invokevirtual 317	android/graphics/Canvas:restore	()V
    //   615: aload_0
    //   616: getfield 114	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:edges	Lcom/google/android/apps/unveil/env/Picture;
    //   619: ifnull +80 -> 699
    //   622: new 299	android/graphics/Rect
    //   625: dup
    //   626: aload 19
    //   628: getfield 320	android/graphics/Rect:left	I
    //   631: i2f
    //   632: fload 5
    //   634: fdiv
    //   635: f2i
    //   636: aload 19
    //   638: getfield 323	android/graphics/Rect:top	I
    //   641: i2f
    //   642: fload 5
    //   644: fdiv
    //   645: f2i
    //   646: aload 19
    //   648: getfield 326	android/graphics/Rect:right	I
    //   651: i2f
    //   652: fload 5
    //   654: fdiv
    //   655: f2i
    //   656: aload 19
    //   658: getfield 329	android/graphics/Rect:bottom	I
    //   661: i2f
    //   662: fload 5
    //   664: fdiv
    //   665: f2i
    //   666: invokespecial 302	android/graphics/Rect:<init>	(IIII)V
    //   669: astore 26
    //   671: aload_0
    //   672: getfield 114	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:edges	Lcom/google/android/apps/unveil/env/Picture;
    //   675: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   678: astore 27
    //   680: aload_0
    //   681: getfield 59	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:paint	Landroid/graphics/Paint;
    //   684: astore 28
    //   686: aload 4
    //   688: aload 27
    //   690: aload 26
    //   692: aload 19
    //   694: aload 28
    //   696: invokevirtual 314	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V
    //   699: aload_3
    //   700: aload 4
    //   702: invokeinterface 333 2 0
    //   707: goto -696 -> 11
    //   710: astore_1
    //   711: aload_0
    //   712: monitorexit
    //   713: aload_1
    //   714: athrow
    //   715: fload 35
    //   717: fconst_1
    //   718: fcmpl
    //   719: ifgt +10 -> 729
    //   722: fload 36
    //   724: fconst_1
    //   725: fcmpl
    //   726: ifle -597 -> 129
    //   729: fload 35
    //   731: fload 36
    //   733: invokestatic 208	java/lang/Math:min	(FF)F
    //   736: fstore 5
    //   738: goto -609 -> 129
    //   741: astore 39
    //   743: aload_0
    //   744: getfield 46	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   747: ldc_w 335
    //   750: iconst_0
    //   751: anewarray 4	java/lang/Object
    //   754: invokevirtual 140	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   757: goto -535 -> 222
    //   760: aload_0
    //   761: getfield 117	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scaledBackground	Lcom/google/android/apps/unveil/env/Picture;
    //   764: invokevirtual 239	com/google/android/apps/unveil/env/Picture:getSize	()Lcom/google/android/apps/unveil/env/Size;
    //   767: getfield 244	com/google/android/apps/unveil/env/Size:width	I
    //   770: i2f
    //   771: aload_0
    //   772: getfield 106	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:background	Lcom/google/android/apps/unveil/env/Picture;
    //   775: invokevirtual 195	com/google/android/apps/unveil/env/Picture:peekBitmap	()Landroid/graphics/Bitmap;
    //   778: invokevirtual 198	android/graphics/Bitmap:getWidth	()I
    //   781: i2f
    //   782: fdiv
    //   783: fstore 5
    //   785: goto -563 -> 222
    //   788: aload_0
    //   789: getfield 59	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:paint	Landroid/graphics/Paint;
    //   792: sipush 255
    //   795: invokevirtual 294	android/graphics/Paint:setAlpha	(I)V
    //   798: goto -382 -> 416
    //   801: new 299	android/graphics/Rect
    //   804: dup
    //   805: aload 19
    //   807: invokespecial 338	android/graphics/Rect:<init>	(Landroid/graphics/Rect;)V
    //   810: astore 23
    //   812: goto -226 -> 586
    //   815: new 299	android/graphics/Rect
    //   818: dup
    //   819: iload 17
    //   821: iconst_0
    //   822: iload 18
    //   824: iload 15
    //   826: invokespecial 302	android/graphics/Rect:<init>	(IIII)V
    //   829: astore 19
    //   831: aload 4
    //   833: invokevirtual 305	android/graphics/Canvas:save	()I
    //   836: pop
    //   837: fload 12
    //   839: fconst_0
    //   840: fcmpg
    //   841: ifge +93 -> 934
    //   844: aload 4
    //   846: ldc_w 306
    //   849: ldc_w 307
    //   852: iload 15
    //   854: iconst_2
    //   855: idiv
    //   856: i2f
    //   857: invokevirtual 311	android/graphics/Canvas:rotate	(FFF)V
    //   860: aload 4
    //   862: bipush 40
    //   864: iload 16
    //   866: isub
    //   867: i2f
    //   868: fconst_0
    //   869: invokevirtual 253	android/graphics/Canvas:translate	(FF)V
    //   872: iload 16
    //   874: iload 18
    //   876: isub
    //   877: istore 30
    //   879: iload 16
    //   881: iload 17
    //   883: isub
    //   884: istore 31
    //   886: new 299	android/graphics/Rect
    //   889: dup
    //   890: iload 30
    //   892: iconst_0
    //   893: iload 31
    //   895: iload 15
    //   897: invokespecial 302	android/graphics/Rect:<init>	(IIII)V
    //   900: astore 32
    //   902: aload_0
    //   903: getfield 82	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:scanner	Landroid/graphics/Bitmap;
    //   906: astore 33
    //   908: aload_0
    //   909: getfield 59	com/google/android/apps/unveil/ui/EdgeDetectingSurfaceController:paint	Landroid/graphics/Paint;
    //   912: astore 34
    //   914: aload 4
    //   916: aload 33
    //   918: aconst_null
    //   919: aload 32
    //   921: aload 34
    //   923: invokevirtual 314	android/graphics/Canvas:drawBitmap	(Landroid/graphics/Bitmap;Landroid/graphics/Rect;Landroid/graphics/Rect;Landroid/graphics/Paint;)V
    //   926: aload 4
    //   928: invokevirtual 317	android/graphics/Canvas:restore	()V
    //   931: goto -316 -> 615
    //   934: new 299	android/graphics/Rect
    //   937: dup
    //   938: aload 19
    //   940: invokespecial 338	android/graphics/Rect:<init>	(Landroid/graphics/Rect;)V
    //   943: astore 32
    //   945: goto -43 -> 902
    //   948: dload 9
    //   950: ldc2_w 339
    //   953: dadd
    //   954: dstore 9
    //   956: goto -586 -> 370
    //   959: iconst_1
    //   960: istore 13
    //   962: goto -523 -> 439
    //   965: iconst_0
    //   966: istore 13
    //   968: goto -529 -> 439
    //   971: iload 14
    //   973: istore 16
    //   975: goto -503 -> 472
    //
    // Exception table:
    //   from	to	target	type
    //   2	7	710	finally
    //   14	30	710	finally
    //   38	102	710	finally
    //   116	125	710	finally
    //   129	160	710	finally
    //   160	207	710	finally
    //   212	222	710	finally
    //   222	343	710	finally
    //   347	367	710	finally
    //   370	395	710	finally
    //   402	416	710	finally
    //   416	436	710	finally
    //   439	463	710	finally
    //   499	521	710	finally
    //   528	556	710	finally
    //   570	586	710	finally
    //   586	615	710	finally
    //   615	699	710	finally
    //   699	707	710	finally
    //   729	738	710	finally
    //   743	757	710	finally
    //   760	785	710	finally
    //   788	798	710	finally
    //   801	812	710	finally
    //   815	837	710	finally
    //   844	872	710	finally
    //   886	902	710	finally
    //   902	931	710	finally
    //   934	945	710	finally
    //   129	160	741	java/lang/OutOfMemoryError
    //   160	207	741	java/lang/OutOfMemoryError
    //   212	222	741	java/lang/OutOfMemoryError
  }

  void setAnimationStartTime()
  {
    this.animationStartTimeMs = System.currentTimeMillis();
  }

  public void setBackground(Picture paramPicture)
  {
    if (paramPicture == null);
    while (true)
    {
      try
      {
        this.logger.e("setBackground() recieved a null picture", new Object[0]);
        this.background = null;
        clearTempBackgroundData();
        return;
        if (paramPicture.peekBitmap() == null)
        {
          this.logger.e("setBackground() recieved a picture with a null bitmap", new Object[0]);
          this.background = null;
          continue;
        }
      }
      finally
      {
      }
      this.logger.d("setting Background", new Object[0]);
      this.background = paramPicture;
    }
  }

  public void setDirection(Direction paramDirection)
  {
    this.direction = paramDirection;
  }

  public static enum Direction
  {
    static
    {
      BOTTOM_TO_TOP = new Direction("BOTTOM_TO_TOP", 3);
      Direction[] arrayOfDirection = new Direction[4];
      arrayOfDirection[0] = LEFT_TO_RIGHT;
      arrayOfDirection[1] = RIGHT_TO_LEFT;
      arrayOfDirection[2] = TOP_TO_BOTTOM;
      arrayOfDirection[3] = BOTTOM_TO_TOP;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.EdgeDetectingSurfaceController
 * JD-Core Version:    0.6.2
 */