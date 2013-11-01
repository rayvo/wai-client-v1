package com.google.android.apps.unveil.protocol;

import android.content.ContentValues;
import android.database.Cursor;
import android.graphics.Rect;
import android.text.TextUtils;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.barcode.Barcode;
import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.InfoProvider;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.network.LatLngEncrypter;
import com.google.android.apps.unveil.network.NetworkInfoProvider;
import com.google.android.apps.unveil.sensors.UnveilSensor;
import com.google.android.apps.unveil.sensors.UnveilSensorProvider;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.ClientAnnotationProtos.ClientAnnotation.Builder;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesRequest;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesRequest.Builder;
import com.google.goggles.GogglesProtos.CropRegion;
import com.google.goggles.GogglesProtos.CropRegion.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.GogglesProtos.Image;
import com.google.goggles.GogglesProtos.Image.Builder;
import com.google.goggles.GogglesProtos.ImageData;
import com.google.goggles.GogglesProtos.ImageData.Builder;
import com.google.goggles.GogglesProtos.ImageData.ImageEncoding;
import com.google.goggles.GogglesProtos.ImageRotation;
import com.google.goggles.GogglesProtos.ImageRotation.Builder;
import com.google.goggles.GogglesProtos.ImageRotation.CWOffsetFromRightSideUp;
import com.google.goggles.GogglesReplayProtos.GogglesReplayRequest;
import com.google.goggles.GogglesReplayProtos.GogglesReplayRequest.Builder;
import com.google.goggles.LocationProtos.Location;
import com.google.goggles.LocationProtos.Location.Builder;
import com.google.goggles.OrientationProtos.Orientation;
import com.google.goggles.OrientationProtos.Orientation.Builder;
import com.google.goggles.PoseProtos.Pose;
import com.google.goggles.PoseProtos.Pose.Builder;
import com.google.goggles.RestrictsProtos.Restricts;
import com.google.goggles.SensorAccuracyProtos.SensorAccuracy;
import com.google.goggles.SensorAccuracyProtos.SensorAccuracy.Builder;
import com.google.goggles.SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy;
import com.google.protobuf.ByteString;
import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OptionalDataException;
import java.io.Serializable;

public class QueryBuilder
  implements Serializable
{
  private static final long ONE_POINT_NINE = 3L;
  private static final long ONE_POINT_SEVEN = 2L;
  private static final long ONE_POINT_SIX = 1L;
  protected static final int ORIENTATION_NOT_PROVIDED = -1;
  private static final UnveilLogger logger = new UnveilLogger();
  public static final long serialVersionUID = 7839872687343347150L;
  private static final long subVersionUID = 3L;
  protected Barcode barcode;
  private boolean canLogImage;
  private Size cropContextSize;
  private BoundingBoxProtos.BoundingBox cropRect;
  private String docid;
  protected byte[] imageData;
  private boolean imageRotated;
  protected Size imageSize;
  private String imageUrl;
  private int jpegQuality = -1;
  protected LocationProtos.Location location;
  protected Long msSinceEpoch;
  protected int orientationRelativeToCamera = -1;
  private int orientationRelativeToDevice;
  private String queryId;
  private QueryResponseFactory.QueryType queryType = QueryResponseFactory.QueryType.IMAGE;
  private RestrictsProtos.Restricts restricts;
  private int sequenceNumber;
  protected GogglesProtos.GogglesRequest.Source source = GogglesProtos.GogglesRequest.Source.UNKNOWN;
  private String sourceLanguage;

  protected static void addEncryptedLocation(ExtendedGogglesProtos.ExtendedGogglesRequest.Builder paramBuilder, LocationProtos.Location paramLocation, LatLngEncrypter paramLatLngEncrypter)
  {
    ByteString localByteString = paramLatLngEncrypter.encryptLatLng(paramLocation.getLatLng());
    if (localByteString != null)
      paramBuilder.setEncryptedLatLng(localByteString);
  }

  private GogglesProtos.GogglesRequest.Builder buildWithContextualDependencies(Viewport paramViewport, UnveilSensor paramUnveilSensor, NetworkInfoProvider paramNetworkInfoProvider, LatLngEncrypter paramLatLngEncrypter, GogglesProtos.GogglesRequest.Builder paramBuilder, ExtendedGogglesProtos.ExtendedGogglesRequest.Builder paramBuilder1)
  {
    Barcode localBarcode;
    ClientAnnotationProtos.ClientAnnotation.Builder localBuilder3;
    PoseProtos.Pose.Builder localBuilder;
    OrientationProtos.Orientation.Builder localBuilder1;
    int i;
    SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy localDiscreteSensorAccuracy;
    if (this.barcode != null)
    {
      localBarcode = this.barcode;
      logger.v("Sending a client-detected barcode", new Object[0]);
      if (this.imageData != null)
      {
        if (this.orientationRelativeToCamera == -1)
          logger.w("Sending an image and local barcode without orientation relative to camera.", new Object[0]);
        localBuilder3 = localBarcode.toRotatedClientAnnotation(this.imageSize, this.orientationRelativeToCamera, paramViewport);
        paramBuilder1.addClientAnnotations(localBuilder3);
      }
    }
    else
    {
      localBuilder = PoseProtos.Pose.newBuilder();
      localBuilder1 = OrientationProtos.Orientation.newBuilder();
      float[] arrayOfFloat = paramUnveilSensor.getValues();
      if (arrayOfFloat != null)
      {
        localBuilder1.setAzimuthDegrees(arrayOfFloat[0]);
        localBuilder1.setPitchDegrees(arrayOfFloat[1]);
        localBuilder1.setRollDegrees(arrayOfFloat[2]);
        i = paramUnveilSensor.accuracy;
        if (i != 3)
          break label327;
        localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.HIGH;
      }
    }
    while (true)
    {
      localBuilder1.setSensorAccuracy(SensorAccuracyProtos.SensorAccuracy.newBuilder().setDiscreteAccuracy(localDiscreteSensorAccuracy));
      localBuilder.setOrientation(localBuilder1);
      if (this.location != null)
      {
        LocationProtos.Location localLocation = this.location;
        addEncryptedLocation(paramBuilder1, localLocation, paramLatLngEncrypter);
        LocationProtos.Location.Builder localBuilder2 = LocationProtos.Location.newBuilder();
        localBuilder2.setLatLngAccuracyMeters(localLocation.getLatLngAccuracyMeters());
        localBuilder2.setAltitudeMeters(localLocation.getAltitudeMeters());
        localBuilder2.setTimestampMs(localLocation.getTimestampMs());
        localBuilder.setLocation(localBuilder2);
      }
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Boolean.valueOf(localBuilder.hasLocation());
      arrayOfObject[1] = Boolean.valueOf(localBuilder.hasOrientation());
      localUnveilLogger.i("Pose: %s %s", arrayOfObject);
      paramBuilder.setPose(localBuilder);
      if (paramNetworkInfoProvider != null)
        paramBuilder1.setNetworkInfo(paramNetworkInfoProvider.getNetworkInfo());
      paramBuilder.setExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest, paramBuilder1.build());
      return paramBuilder;
      localBuilder3 = localBarcode.toClientAnnotation();
      break;
      label327: if (i == 2)
      {
        localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.MEDIUM;
      }
      else if (i == 1)
      {
        localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.LOW;
      }
      else if (i == 0)
      {
        localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.UNRELIABLE;
      }
      else
      {
        logger.d("Couldn't get orientation accuracy; assuming Medium.", new Object[0]);
        localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.MEDIUM;
      }
    }
  }

  private void buildWithoutDependencies(GogglesProtos.GogglesRequest.Builder paramBuilder, ExtendedGogglesProtos.ExtendedGogglesRequest.Builder paramBuilder1)
  {
    paramBuilder.setImage(buildImageBuilder());
    if (this.msSinceEpoch != null)
      paramBuilder1.setMsSinceEpoch(this.msSinceEpoch.longValue());
    while (true)
    {
      if (!TextUtils.isEmpty(this.sourceLanguage))
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = this.sourceLanguage;
        localUnveilLogger.w("Force source language to %s", arrayOfObject);
        paramBuilder1.setSourceLanguage(this.sourceLanguage);
      }
      paramBuilder1.setDeviceInfo(InfoProvider.getDeviceInfo());
      paramBuilder.setSource(this.source);
      if (this.restricts != null)
        paramBuilder.setRestricts(this.restricts);
      paramBuilder.setExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest, paramBuilder1.build());
      return;
      logger.w("No msSinceEpoch set, assuming currentTimeMillis()", new Object[0]);
      paramBuilder1.setMsSinceEpoch(System.currentTimeMillis());
    }
  }

  public static QueryBuilder from(Cursor paramCursor)
  {
    QueryBuilder localQueryBuilder = new QueryBuilder();
    try
    {
      localQueryBuilder.setValuesFromCursor(paramCursor);
      return localQueryBuilder;
    }
    catch (QueryBuilderParseException localQueryBuilderParseException)
    {
      logger.e(localQueryBuilderParseException, "Could not parse query from the given cursor.", new Object[0]);
    }
    return null;
  }

  private GogglesProtos.ImageRotation.CWOffsetFromRightSideUp getCWOffsetFromRightSideUp(int paramInt)
  {
    switch (paramInt)
    {
    default:
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.w("Unexpected orientation value: %d", arrayOfObject);
      return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ZERO_DEGREES;
    case 0:
      return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ZERO_DEGREES;
    case 90:
      return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.TWO_SEVENTY_DEGREES;
    case 180:
      return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ONE_EIGHTY_DEGREES;
    case 270:
    }
    return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.NINETY_DEGREES;
  }

  // ERROR //
  public static QueryBuilder parseFrom(byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: new 402	java/io/ObjectInputStream
    //   5: dup
    //   6: new 404	java/io/ByteArrayInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 407	java/io/ByteArrayInputStream:<init>	([B)V
    //   14: invokespecial 410	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_2
    //   18: aload_2
    //   19: invokevirtual 414	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   22: checkcast 2	com/google/android/apps/unveil/protocol/QueryBuilder
    //   25: astore 9
    //   27: aload_2
    //   28: ifnull +7 -> 35
    //   31: aload_2
    //   32: invokevirtual 417	java/io/ObjectInputStream:close	()V
    //   35: aload 9
    //   37: areturn
    //   38: astore 10
    //   40: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   43: aload 10
    //   45: ldc_w 419
    //   48: iconst_0
    //   49: anewarray 4	java/lang/Object
    //   52: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   55: goto -20 -> 35
    //   58: astore_3
    //   59: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   62: aload_3
    //   63: ldc_w 421
    //   66: iconst_0
    //   67: anewarray 4	java/lang/Object
    //   70: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   73: aload_1
    //   74: ifnull +7 -> 81
    //   77: aload_1
    //   78: invokevirtual 417	java/io/ObjectInputStream:close	()V
    //   81: aconst_null
    //   82: areturn
    //   83: astore 6
    //   85: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   88: aload 6
    //   90: ldc_w 419
    //   93: iconst_0
    //   94: anewarray 4	java/lang/Object
    //   97: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   100: goto -19 -> 81
    //   103: astore 7
    //   105: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   108: aload 7
    //   110: ldc_w 423
    //   113: iconst_0
    //   114: anewarray 4	java/lang/Object
    //   117: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   120: aload_1
    //   121: ifnull +7 -> 128
    //   124: aload_1
    //   125: invokevirtual 417	java/io/ObjectInputStream:close	()V
    //   128: aconst_null
    //   129: areturn
    //   130: astore 8
    //   132: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   135: aload 8
    //   137: ldc_w 419
    //   140: iconst_0
    //   141: anewarray 4	java/lang/Object
    //   144: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   147: goto -19 -> 128
    //   150: astore 4
    //   152: aload_1
    //   153: ifnull +7 -> 160
    //   156: aload_1
    //   157: invokevirtual 417	java/io/ObjectInputStream:close	()V
    //   160: aload 4
    //   162: athrow
    //   163: astore 5
    //   165: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   168: aload 5
    //   170: ldc_w 419
    //   173: iconst_0
    //   174: anewarray 4	java/lang/Object
    //   177: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   180: goto -20 -> 160
    //   183: astore 4
    //   185: aload_2
    //   186: astore_1
    //   187: goto -35 -> 152
    //   190: astore 7
    //   192: aload_2
    //   193: astore_1
    //   194: goto -89 -> 105
    //   197: astore_3
    //   198: aload_2
    //   199: astore_1
    //   200: goto -141 -> 59
    //
    // Exception table:
    //   from	to	target	type
    //   31	35	38	java/io/IOException
    //   2	18	58	java/io/IOException
    //   77	81	83	java/io/IOException
    //   2	18	103	java/lang/ClassNotFoundException
    //   124	128	130	java/io/IOException
    //   2	18	150	finally
    //   59	73	150	finally
    //   105	120	150	finally
    //   156	160	163	java/io/IOException
    //   18	27	183	finally
    //   18	27	190	java/lang/ClassNotFoundException
    //   18	27	197	java/io/IOException
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    readPreOnePointSixFields(paramObjectInputStream);
    try
    {
      long l = paramObjectInputStream.readLong();
      if (l == 1L)
      {
        readOnePointSixFields(paramObjectInputStream);
        return;
      }
      if (l == 2L)
      {
        readOnePointSixFields(paramObjectInputStream);
        readOnePointSevenFields(paramObjectInputStream);
        return;
      }
      if (l == 3L)
      {
        readOnePointSixFields(paramObjectInputStream);
        readOnePointSevenFields(paramObjectInputStream);
        readOnePointNineFields(paramObjectInputStream);
      }
      return;
    }
    catch (EOFException localEOFException)
    {
    }
  }

  private void readOnePointNineFields(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.restricts = ((RestrictsProtos.Restricts)paramObjectInputStream.readObject());
  }

  private void readOnePointSevenFields(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.cropContextSize = ((Size)paramObjectInputStream.readObject());
  }

  private void readOnePointSixFields(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.imageRotated = paramObjectInputStream.readBoolean();
    this.source = ((GogglesProtos.GogglesRequest.Source)paramObjectInputStream.readObject());
  }

  private void readPreOnePointSixFields(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.orientationRelativeToDevice = paramObjectInputStream.readInt();
    this.orientationRelativeToCamera = paramObjectInputStream.readInt();
    this.cropRect = ((BoundingBoxProtos.BoundingBox)paramObjectInputStream.readObject());
    this.barcode = ((Barcode)paramObjectInputStream.readObject());
    this.location = ((LocationProtos.Location)paramObjectInputStream.readObject());
    this.sequenceNumber = paramObjectInputStream.readInt();
    ((String)paramObjectInputStream.readObject());
    ((String)paramObjectInputStream.readObject());
    if (paramObjectInputStream.readInt() > 0)
      this.imageData = ((byte[])paramObjectInputStream.readObject());
    this.jpegQuality = paramObjectInputStream.readInt();
    try
    {
      this.queryType = QueryResponseFactory.QueryType.safeValueOf((String)paramObjectInputStream.readObject());
      this.queryId = ((String)paramObjectInputStream.readObject());
      this.imageSize = ((Size)paramObjectInputStream.readObject());
      this.msSinceEpoch = ((Long)paramObjectInputStream.readObject());
    }
    catch (OptionalDataException localOptionalDataException)
    {
      try
      {
        paramObjectInputStream.readObject();
        return;
        localOptionalDataException = localOptionalDataException;
        this.queryType = QueryResponseFactory.QueryType.valueOf(paramObjectInputStream.readInt());
      }
      catch (Exception localException)
      {
        logger.e(localException, "failed to deserialize camera config protos.", new Object[0]);
      }
    }
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    writePreOneSixFields(paramObjectOutputStream);
    paramObjectOutputStream.writeLong(3L);
    writeOnePointSixFields(paramObjectOutputStream);
    writeOnePointSevenFields(paramObjectOutputStream);
    writeOnePointNineFields(paramObjectOutputStream);
  }

  private void writeOnePointNineFields(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(this.restricts);
  }

  private void writeOnePointSevenFields(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(this.cropContextSize);
  }

  private void writeOnePointSixFields(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeBoolean(this.imageRotated);
    paramObjectOutputStream.writeObject(this.source);
  }

  private void writePreOneSixFields(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeInt(this.orientationRelativeToDevice);
    paramObjectOutputStream.writeInt(this.orientationRelativeToCamera);
    paramObjectOutputStream.writeObject(this.cropRect);
    paramObjectOutputStream.writeObject(this.barcode);
    paramObjectOutputStream.writeObject(this.location);
    paramObjectOutputStream.writeInt(this.sequenceNumber);
    paramObjectOutputStream.writeObject("");
    paramObjectOutputStream.writeObject("");
    if (this.imageData != null)
    {
      paramObjectOutputStream.writeInt(this.imageData.length);
      paramObjectOutputStream.writeObject(this.imageData);
    }
    while (true)
    {
      paramObjectOutputStream.writeInt(this.jpegQuality);
      paramObjectOutputStream.writeObject(this.queryType.name());
      paramObjectOutputStream.writeObject(this.queryId);
      paramObjectOutputStream.writeObject(this.imageSize);
      paramObjectOutputStream.writeObject(this.msSinceEpoch);
      paramObjectOutputStream.writeObject(null);
      return;
      paramObjectOutputStream.writeInt(0);
    }
  }

  public QueryBuilder addCropRect(Rect paramRect, int paramInt1, int paramInt2)
  {
    if (paramRect == null)
    {
      this.cropRect = null;
      this.cropContextSize = null;
      return this;
    }
    this.cropRect = GeometryUtils.toBoundingBox(paramRect);
    this.cropContextSize = new Size(paramInt1, paramInt2);
    return this;
  }

  public QueryBuilder addDocid(String paramString)
  {
    this.docid = paramString;
    return this;
  }

  public QueryBuilder addImageData(byte[] paramArrayOfByte, Size paramSize)
  {
    return addImageData(paramArrayOfByte, paramSize, true);
  }

  public QueryBuilder addImageData(byte[] paramArrayOfByte, Size paramSize, boolean paramBoolean)
  {
    this.imageData = paramArrayOfByte;
    this.imageSize = paramSize;
    this.imageRotated = paramBoolean;
    return this;
  }

  public QueryBuilder addImageUrl(String paramString)
  {
    this.imageUrl = paramString;
    return this;
  }

  public QueryBuilder addJpegQuality(int paramInt)
  {
    this.jpegQuality = paramInt;
    return this;
  }

  public QueryBuilder addLocalBarcode(Barcode paramBarcode)
  {
    this.barcode = paramBarcode;
    return this;
  }

  public QueryBuilder addLocation(LocationProtos.Location paramLocation)
  {
    this.location = paramLocation;
    return this;
  }

  public QueryBuilder addMsSinceEpoch(Long paramLong)
  {
    this.msSinceEpoch = paramLong;
    return this;
  }

  public QueryBuilder addOrientationRelativeToCamera(int paramInt)
  {
    this.orientationRelativeToCamera = paramInt;
    return this;
  }

  public QueryBuilder addOrientationRelativeToDevice(int paramInt)
  {
    this.orientationRelativeToDevice = paramInt;
    return this;
  }

  public QueryBuilder addQueryId(String paramString)
  {
    this.queryId = paramString;
    return this;
  }

  public QueryBuilder addRestricts(RestrictsProtos.Restricts paramRestricts)
  {
    this.restricts = paramRestricts;
    return this;
  }

  public QueryBuilder addSourceLanguage(String paramString)
  {
    this.sourceLanguage = paramString;
    return this;
  }

  public GogglesProtos.GogglesRequest.Builder buildGogglesRequestBuilder()
  {
    GogglesProtos.GogglesRequest.Builder localBuilder = GogglesProtos.GogglesRequest.newBuilder();
    ExtendedGogglesProtos.ExtendedGogglesRequest.Builder localBuilder1 = ExtendedGogglesProtos.ExtendedGogglesRequest.newBuilder();
    if (this.barcode != null)
      throw new IllegalStateException("Cannot attach barcode parameter without UnveilContext");
    if (this.location != null)
      throw new IllegalStateException("Cannot attach location parameter without UnveilContext");
    buildWithoutDependencies(localBuilder, localBuilder1);
    return localBuilder;
  }

  public GogglesProtos.GogglesRequest.Builder buildGogglesRequestBuilder(UnveilContext paramUnveilContext)
  {
    GogglesProtos.GogglesRequest.Builder localBuilder = GogglesProtos.GogglesRequest.newBuilder();
    ExtendedGogglesProtos.ExtendedGogglesRequest.Builder localBuilder1 = ExtendedGogglesProtos.ExtendedGogglesRequest.newBuilder();
    buildWithoutDependencies(localBuilder, localBuilder1);
    return buildWithContextualDependencies(paramUnveilContext.getViewport(), paramUnveilContext.getSensorProvider().getOrientationSensor(), paramUnveilContext.getNetworkInfoProvider(), paramUnveilContext.getLatLngEncrypter(), localBuilder, localBuilder1);
  }

  protected GogglesProtos.Image.Builder buildImageBuilder()
  {
    GogglesProtos.Image.Builder localBuilder = GogglesProtos.Image.newBuilder();
    if (this.imageData != null)
    {
      GogglesProtos.ImageData.Builder localBuilder3 = GogglesProtos.ImageData.newBuilder();
      localBuilder3.setEncodedImage(ByteString.copyFrom(this.imageData));
      localBuilder3.setImageEncoding(GogglesProtos.ImageData.ImageEncoding.JPEG);
      if (this.jpegQuality != -1)
        localBuilder3.setQuality(this.jpegQuality);
      localBuilder.setImageData(localBuilder3);
    }
    GogglesProtos.ImageRotation.Builder localBuilder1 = GogglesProtos.ImageRotation.newBuilder();
    localBuilder1.setOffsetAlreadyApplied(this.imageRotated);
    if (!this.imageRotated)
      localBuilder1.setImageOffsetFromRightSideUp(getCWOffsetFromRightSideUp(this.orientationRelativeToCamera));
    localBuilder.setExtension(GogglesProtos.ImageRotation.imageRotation, localBuilder1.build());
    if ((this.cropRect != null) && (this.cropContextSize != null))
    {
      GogglesProtos.CropRegion.Builder localBuilder2 = GogglesProtos.CropRegion.newBuilder();
      localBuilder2.setRegion(this.cropRect);
      localBuilder2.setWidth(this.cropContextSize.width);
      localBuilder2.setHeight(this.cropContextSize.height);
    }
    if (!TextUtils.isEmpty(this.imageUrl))
      localBuilder.setImageUrl(this.imageUrl);
    if (!TextUtils.isEmpty(this.docid))
      localBuilder.setDocid(this.docid);
    return localBuilder;
  }

  protected GogglesReplayProtos.GogglesReplayRequest.Builder buildReplayRequestBuilder()
  {
    GogglesReplayProtos.GogglesReplayRequest.Builder localBuilder = GogglesReplayProtos.GogglesReplayRequest.newBuilder();
    if (this.queryId != null)
      localBuilder.setQueryId(this.queryId);
    while (true)
    {
      localBuilder.setMsSinceEpoch(System.currentTimeMillis());
      return localBuilder;
      logger.e("Creating a GogglesReplayRequest without a queryId.", new Object[0]);
    }
  }

  public boolean canGeneratePicture()
  {
    return (this.imageData != null) && (this.imageSize != null);
  }

  public boolean getCanLogImage()
  {
    return this.canLogImage;
  }

  protected Size getCropContextSize()
  {
    return this.cropContextSize;
  }

  protected BoundingBoxProtos.BoundingBox getCropRect()
  {
    return this.cropRect;
  }

  protected Size getImageSize()
  {
    return this.imageSize;
  }

  Barcode getLocalBarcode()
  {
    return this.barcode;
  }

  protected int getOrientationRelativeToCamera()
  {
    return this.orientationRelativeToCamera;
  }

  protected int getOrientationRelativeToDevice()
  {
    return this.orientationRelativeToDevice;
  }

  public String getQueryId()
  {
    return this.queryId;
  }

  public QueryResponseFactory.QueryType getQueryType()
  {
    return this.queryType;
  }

  public RestrictsProtos.Restricts getRestricts()
  {
    return this.restricts;
  }

  public int getSequenceNumber()
  {
    return this.sequenceNumber;
  }

  public GogglesProtos.GogglesRequest.Source getSource()
  {
    return this.source;
  }

  public boolean hasLocation()
  {
    return this.location != null;
  }

  public void setAsReplayType()
  {
    this.queryType = QueryResponseFactory.QueryType.REPLAY;
  }

  public QueryBuilder setCanLogImage(boolean paramBoolean)
  {
    this.canLogImage = paramBoolean;
    return this;
  }

  public void setQueryType(QueryResponseFactory.QueryType paramQueryType)
  {
    this.queryType = paramQueryType;
  }

  protected void setSequenceNumber(int paramInt)
  {
    this.sequenceNumber = paramInt;
  }

  public void setSource(GogglesProtos.GogglesRequest.Source paramSource)
  {
    this.source = paramSource;
  }

  // ERROR //
  protected void setValuesFromCursor(Cursor paramCursor)
    throws QueryBuilder.QueryBuilderParseException
  {
    // Byte code:
    //   0: aload_0
    //   1: aload_1
    //   2: aload_1
    //   3: ldc_w 755
    //   6: invokeinterface 761 2 0
    //   11: invokeinterface 765 2 0
    //   16: putfield 462	com/google/android/apps/unveil/protocol/QueryBuilder:orientationRelativeToDevice	I
    //   19: aload_0
    //   20: aload_1
    //   21: aload_1
    //   22: ldc_w 767
    //   25: invokeinterface 761 2 0
    //   30: invokeinterface 765 2 0
    //   35: putfield 68	com/google/android/apps/unveil/protocol/QueryBuilder:orientationRelativeToCamera	I
    //   38: aload_1
    //   39: aload_1
    //   40: ldc_w 769
    //   43: invokeinterface 761 2 0
    //   48: invokeinterface 773 2 0
    //   53: astore_2
    //   54: aload_2
    //   55: ifnull +411 -> 466
    //   58: aload_0
    //   59: aload_2
    //   60: invokestatic 776	com/google/goggles/BoundingBoxProtos$BoundingBox:parseFrom	([B)Lcom/google/goggles/BoundingBoxProtos$BoundingBox;
    //   63: putfield 466	com/google/android/apps/unveil/protocol/QueryBuilder:cropRect	Lcom/google/goggles/BoundingBoxProtos$BoundingBox;
    //   66: aload_0
    //   67: aload_1
    //   68: aload_1
    //   69: ldc_w 778
    //   72: invokeinterface 761 2 0
    //   77: invokeinterface 782 2 0
    //   82: invokestatic 786	com/google/android/apps/unveil/env/Size:parseFromString	(Ljava/lang/String;)Lcom/google/android/apps/unveil/env/Size;
    //   85: putfield 447	com/google/android/apps/unveil/protocol/QueryBuilder:cropContextSize	Lcom/google/android/apps/unveil/env/Size;
    //   88: aload_1
    //   89: aload_1
    //   90: ldc_w 787
    //   93: invokeinterface 761 2 0
    //   98: invokeinterface 773 2 0
    //   103: astore_3
    //   104: aload_3
    //   105: ifnull +11 -> 116
    //   108: aload_0
    //   109: aload_3
    //   110: invokestatic 790	com/google/android/apps/unveil/barcode/Barcode:parseFrom	([B)Lcom/google/android/apps/unveil/barcode/Barcode;
    //   113: putfield 108	com/google/android/apps/unveil/protocol/QueryBuilder:barcode	Lcom/google/android/apps/unveil/barcode/Barcode;
    //   116: aload_0
    //   117: aload_1
    //   118: aload_1
    //   119: ldc_w 792
    //   122: invokeinterface 761 2 0
    //   127: invokeinterface 765 2 0
    //   132: putfield 468	com/google/android/apps/unveil/protocol/QueryBuilder:sequenceNumber	I
    //   135: aload_0
    //   136: aload_1
    //   137: aload_1
    //   138: ldc_w 794
    //   141: invokeinterface 761 2 0
    //   146: invokeinterface 773 2 0
    //   151: putfield 116	com/google/android/apps/unveil/protocol/QueryBuilder:imageData	[B
    //   154: aload_0
    //   155: aload_1
    //   156: aload_1
    //   157: ldc_w 796
    //   160: invokeinterface 761 2 0
    //   165: invokeinterface 765 2 0
    //   170: putfield 70	com/google/android/apps/unveil/protocol/QueryBuilder:jpegQuality	I
    //   173: aload_0
    //   174: aload_1
    //   175: aload_1
    //   176: ldc_w 798
    //   179: invokeinterface 761 2 0
    //   184: invokeinterface 765 2 0
    //   189: invokestatic 480	com/google/android/apps/unveil/protocol/QueryResponseFactory$QueryType:valueOf	(I)Lcom/google/android/apps/unveil/protocol/QueryResponseFactory$QueryType;
    //   192: putfield 77	com/google/android/apps/unveil/protocol/QueryBuilder:queryType	Lcom/google/android/apps/unveil/protocol/QueryResponseFactory$QueryType;
    //   195: aload_0
    //   196: aload_1
    //   197: aload_1
    //   198: ldc_w 800
    //   201: invokeinterface 761 2 0
    //   206: invokeinterface 782 2 0
    //   211: putfield 477	com/google/android/apps/unveil/protocol/QueryBuilder:queryId	Ljava/lang/String;
    //   214: aload_0
    //   215: aload_1
    //   216: aload_1
    //   217: ldc_w 802
    //   220: invokeinterface 761 2 0
    //   225: invokeinterface 782 2 0
    //   230: invokestatic 786	com/google/android/apps/unveil/env/Size:parseFromString	(Ljava/lang/String;)Lcom/google/android/apps/unveil/env/Size;
    //   233: putfield 123	com/google/android/apps/unveil/protocol/QueryBuilder:imageSize	Lcom/google/android/apps/unveil/env/Size;
    //   236: aload_0
    //   237: aload_1
    //   238: aload_1
    //   239: ldc_w 804
    //   242: invokeinterface 761 2 0
    //   247: invokeinterface 808 2 0
    //   252: invokestatic 811	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   255: putfield 305	com/google/android/apps/unveil/protocol/QueryBuilder:msSinceEpoch	Ljava/lang/Long;
    //   258: aload_1
    //   259: aload_1
    //   260: ldc_w 813
    //   263: invokeinterface 761 2 0
    //   268: invokeinterface 765 2 0
    //   273: iconst_1
    //   274: if_icmpne +195 -> 469
    //   277: iconst_1
    //   278: istore 4
    //   280: aload_0
    //   281: iload 4
    //   283: putfield 452	com/google/android/apps/unveil/protocol/QueryBuilder:imageRotated	Z
    //   286: aload_1
    //   287: aload_1
    //   288: ldc_w 814
    //   291: invokeinterface 761 2 0
    //   296: invokeinterface 782 2 0
    //   301: astore 5
    //   303: aload 5
    //   305: invokestatic 322	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   308: ifeq +167 -> 475
    //   311: aload_0
    //   312: getstatic 82	com/google/goggles/GogglesProtos$GogglesRequest$Source:UNKNOWN	Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   315: putfield 84	com/google/android/apps/unveil/protocol/QueryBuilder:source	Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   318: aload_1
    //   319: aload_1
    //   320: ldc_w 815
    //   323: invokeinterface 761 2 0
    //   328: invokeinterface 773 2 0
    //   333: astore 7
    //   335: aload 7
    //   337: ifnull +193 -> 530
    //   340: aload_0
    //   341: aload 7
    //   343: invokestatic 818	com/google/goggles/RestrictsProtos$Restricts:parseFrom	([B)Lcom/google/goggles/RestrictsProtos$Restricts;
    //   346: putfield 344	com/google/android/apps/unveil/protocol/QueryBuilder:restricts	Lcom/google/goggles/RestrictsProtos$Restricts;
    //   349: aload_0
    //   350: aload_1
    //   351: aload_1
    //   352: ldc_w 820
    //   355: invokeinterface 761 2 0
    //   360: invokeinterface 782 2 0
    //   365: putfield 542	com/google/android/apps/unveil/protocol/QueryBuilder:imageUrl	Ljava/lang/String;
    //   368: aload_0
    //   369: aload_1
    //   370: aload_1
    //   371: ldc_w 821
    //   374: invokeinterface 761 2 0
    //   379: invokeinterface 782 2 0
    //   384: putfield 534	com/google/android/apps/unveil/protocol/QueryBuilder:docid	Ljava/lang/String;
    //   387: aload_0
    //   388: aload_1
    //   389: aload_1
    //   390: ldc_w 823
    //   393: invokeinterface 761 2 0
    //   398: invokeinterface 782 2 0
    //   403: putfield 316	com/google/android/apps/unveil/protocol/QueryBuilder:sourceLanguage	Ljava/lang/String;
    //   406: aload_1
    //   407: aload_1
    //   408: ldc_w 825
    //   411: invokeinterface 761 2 0
    //   416: invokeinterface 765 2 0
    //   421: iconst_1
    //   422: if_icmpne +111 -> 533
    //   425: iconst_1
    //   426: istore 8
    //   428: aload_0
    //   429: iload 8
    //   431: putfield 722	com/google/android/apps/unveil/protocol/QueryBuilder:canLogImage	Z
    //   434: return
    //   435: astore 11
    //   437: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   440: aload 11
    //   442: ldc_w 827
    //   445: iconst_0
    //   446: anewarray 4	java/lang/Object
    //   449: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   452: new 359	com/google/android/apps/unveil/protocol/QueryBuilder$QueryBuilderParseException
    //   455: dup
    //   456: aload 11
    //   458: invokespecial 830	com/google/android/apps/unveil/protocol/QueryBuilder$QueryBuilderParseException:<init>	(Ljava/lang/Exception;)V
    //   461: astore 12
    //   463: aload 12
    //   465: athrow
    //   466: goto -400 -> 66
    //   469: iconst_0
    //   470: istore 4
    //   472: goto -192 -> 280
    //   475: aload_0
    //   476: aload 5
    //   478: invokestatic 833	com/google/goggles/GogglesProtos$GogglesRequest$Source:valueOf	(Ljava/lang/String;)Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   481: putfield 84	com/google/android/apps/unveil/protocol/QueryBuilder:source	Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   484: goto -166 -> 318
    //   487: astore 6
    //   489: aload_0
    //   490: getstatic 82	com/google/goggles/GogglesProtos$GogglesRequest$Source:UNKNOWN	Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   493: putfield 84	com/google/android/apps/unveil/protocol/QueryBuilder:source	Lcom/google/goggles/GogglesProtos$GogglesRequest$Source;
    //   496: goto -178 -> 318
    //   499: astore 9
    //   501: getstatic 65	com/google/android/apps/unveil/protocol/QueryBuilder:logger	Lcom/google/android/apps/unveil/env/UnveilLogger;
    //   504: aload 9
    //   506: ldc_w 835
    //   509: iconst_0
    //   510: anewarray 4	java/lang/Object
    //   513: invokevirtual 370	com/google/android/apps/unveil/env/UnveilLogger:e	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   516: new 359	com/google/android/apps/unveil/protocol/QueryBuilder$QueryBuilderParseException
    //   519: dup
    //   520: aload 9
    //   522: invokespecial 830	com/google/android/apps/unveil/protocol/QueryBuilder$QueryBuilderParseException:<init>	(Ljava/lang/Exception;)V
    //   525: astore 10
    //   527: aload 10
    //   529: athrow
    //   530: goto -181 -> 349
    //   533: iconst_0
    //   534: istore 8
    //   536: goto -108 -> 428
    //
    // Exception table:
    //   from	to	target	type
    //   58	66	435	com/google/protobuf/InvalidProtocolBufferException
    //   475	484	487	java/lang/IllegalArgumentException
    //   340	349	499	com/google/protobuf/InvalidProtocolBufferException
  }

  public byte[] toByteArray()
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(this);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      logger.e(localIOException, "Could not serialize %s", new Object[] { this });
    }
    return null;
  }

  public ContentValues toContentValues()
  {
    ContentValues localContentValues = new ContentValues();
    localContentValues.put("orientation_relative_to_device", Integer.valueOf(this.orientationRelativeToDevice));
    localContentValues.put("orientation_relative_to_camera", Integer.valueOf(this.orientationRelativeToCamera));
    if (this.cropRect != null)
      localContentValues.put("crop_rect", this.cropRect.toByteArray());
    if (this.cropContextSize != null)
      localContentValues.put("crop_context_size", this.cropContextSize.toString());
    if (this.barcode != null)
      localContentValues.put("barcode", this.barcode.toByteArray());
    localContentValues.put("sequence_number", Integer.valueOf(this.sequenceNumber));
    if (this.imageData != null)
      localContentValues.put("image_data", this.imageData);
    localContentValues.put("jpeg_quality", Integer.valueOf(this.jpegQuality));
    localContentValues.put("query_type", Integer.valueOf(this.queryType.id));
    localContentValues.put("replay_id", this.queryId);
    if (this.imageSize != null)
      localContentValues.put("image_size", this.imageSize.toString());
    localContentValues.put("ms_since_epoch", this.msSinceEpoch);
    if (this.imageRotated);
    for (int i = 0; ; i = 1)
    {
      localContentValues.put("image_rotated", Integer.valueOf(i));
      localContentValues.put("source", this.source.toString());
      if (this.restricts != null)
        localContentValues.put("restricts", this.restricts.toByteArray());
      if (this.imageUrl != null)
        localContentValues.put("image_url", this.imageUrl);
      if (this.docid != null)
        localContentValues.put("docid", this.docid);
      if (!TextUtils.isEmpty(this.sourceLanguage))
        localContentValues.put("source_language", this.sourceLanguage);
      if (this.canLogImage)
        localContentValues.put("can_log_image", Boolean.valueOf(this.canLogImage));
      return localContentValues;
    }
  }

  public Picture toPicture()
  {
    if ((this.imageData == null) || (this.imageSize == null))
      throw new IllegalStateException("Cannot recover a Picture from this QueryBuilder because addImageData has not been called. " + this);
    return PictureFactory.createJpeg(this.imageData, this.orientationRelativeToCamera);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("QueryBuilder [orientationRelativeToDevice=").append(this.orientationRelativeToDevice).append(", orientationRelativeToCamera=").append(this.orientationRelativeToCamera).append(", ");
    if (this.cropRect != null)
      localStringBuilder.append("cropRect=").append(this.cropRect).append(", ");
    if (this.cropContextSize != null)
      localStringBuilder.append("cropContextSize=").append(this.cropContextSize).append(", ");
    if (this.barcode != null)
      localStringBuilder.append("barcode=").append(this.barcode).append(", ");
    if (this.location != null)
      localStringBuilder.append("location=").append(this.location).append(", ");
    localStringBuilder.append("sequenceNumber=").append(this.sequenceNumber).append(", ");
    localStringBuilder.append("jpegQuality=").append(this.jpegQuality).append(", queryType=").append(this.queryType).append(", ");
    if (this.queryId != null)
      localStringBuilder.append("queryId=").append(this.queryId).append(", ");
    if (this.imageData != null)
      localStringBuilder.append("imageData=").append(this.imageData.length).append(" bytes").append(", ");
    localStringBuilder.append("imageRotated=").append(this.imageRotated).append(", ");
    if (this.imageSize != null)
      localStringBuilder.append("imageSize=").append(this.imageSize).append(", ");
    if (this.msSinceEpoch != null)
      localStringBuilder.append("msSinceEpoch=").append(this.msSinceEpoch).append(", ");
    if (!TextUtils.isEmpty(this.sourceLanguage))
      localStringBuilder.append("sourceLanguage=").append(this.sourceLanguage).append(", ");
    localStringBuilder.append("canLogImage=").append(this.canLogImage).append(", ");
    if (this.source != null)
      localStringBuilder.append("source=").append(this.source);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }

  protected static class QueryBuilderParseException extends Exception
  {
    public QueryBuilderParseException(Exception paramException)
    {
      super();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryBuilder
 * JD-Core Version:    0.6.2
 */