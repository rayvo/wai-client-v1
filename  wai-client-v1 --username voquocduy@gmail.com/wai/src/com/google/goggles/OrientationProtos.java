package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class OrientationProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Orientation extends GeneratedMessageLite
    implements OrientationProtos.OrientationOrBuilder
  {
    public static final int AZIMUTH_DEGREES_FIELD_NUMBER = 1;
    public static final int PITCH_DEGREES_FIELD_NUMBER = 2;
    public static final int ROLL_DEGREES_FIELD_NUMBER = 3;
    public static final int SENSOR_ACCURACY_FIELD_NUMBER = 4;
    private static final Orientation defaultInstance = new Orientation(true);
    private static final long serialVersionUID;
    private float azimuthDegrees_;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private float pitchDegrees_;
    private float rollDegrees_;
    private SensorAccuracyProtos.SensorAccuracy sensorAccuracy_;

    static
    {
      defaultInstance.initFields();
    }

    private Orientation(Builder paramBuilder)
    {
      super();
    }

    private Orientation(boolean paramBoolean)
    {
    }

    public static Orientation getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.azimuthDegrees_ = 0.0F;
      this.pitchDegrees_ = 0.0F;
      this.rollDegrees_ = 0.0F;
      this.sensorAccuracy_ = SensorAccuracyProtos.SensorAccuracy.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Orientation paramOrientation)
    {
      return newBuilder().mergeFrom(paramOrientation);
    }

    public static Orientation parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Orientation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Orientation parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Orientation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Orientation parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Orientation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Orientation parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Orientation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Orientation parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Orientation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public float getAzimuthDegrees()
    {
      return this.azimuthDegrees_;
    }

    public Orientation getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public float getPitchDegrees()
    {
      return this.pitchDegrees_;
    }

    public float getRollDegrees()
    {
      return this.rollDegrees_;
    }

    public SensorAccuracyProtos.SensorAccuracy getSensorAccuracy()
    {
      return this.sensorAccuracy_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeFloatSize(1, this.azimuthDegrees_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeFloatSize(2, this.pitchDegrees_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeFloatSize(3, this.rollDegrees_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(4, this.sensorAccuracy_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasAzimuthDegrees()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasPitchDegrees()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasRollDegrees()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasSensorAccuracy()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeFloat(1, this.azimuthDegrees_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeFloat(2, this.pitchDegrees_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeFloat(3, this.rollDegrees_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(4, this.sensorAccuracy_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<OrientationProtos.Orientation, Builder>
      implements OrientationProtos.OrientationOrBuilder
    {
      private float azimuthDegrees_;
      private int bitField0_;
      private float pitchDegrees_;
      private float rollDegrees_;
      private SensorAccuracyProtos.SensorAccuracy sensorAccuracy_ = SensorAccuracyProtos.SensorAccuracy.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private OrientationProtos.Orientation buildParsed()
        throws InvalidProtocolBufferException
      {
        OrientationProtos.Orientation localOrientation = buildPartial();
        if (!localOrientation.isInitialized())
          throw newUninitializedMessageException(localOrientation).asInvalidProtocolBufferException();
        return localOrientation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public OrientationProtos.Orientation build()
      {
        OrientationProtos.Orientation localOrientation = buildPartial();
        if (!localOrientation.isInitialized())
          throw newUninitializedMessageException(localOrientation);
        return localOrientation;
      }

      public OrientationProtos.Orientation buildPartial()
      {
        int i = 1;
        OrientationProtos.Orientation localOrientation = new OrientationProtos.Orientation(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          OrientationProtos.Orientation.access$302(localOrientation, this.azimuthDegrees_);
          if ((j & 0x2) == 2)
            i |= 2;
          OrientationProtos.Orientation.access$402(localOrientation, this.pitchDegrees_);
          if ((j & 0x4) == 4)
            i |= 4;
          OrientationProtos.Orientation.access$502(localOrientation, this.rollDegrees_);
          if ((j & 0x8) == 8)
            i |= 8;
          OrientationProtos.Orientation.access$602(localOrientation, this.sensorAccuracy_);
          OrientationProtos.Orientation.access$702(localOrientation, i);
          return localOrientation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.azimuthDegrees_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.pitchDegrees_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.rollDegrees_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.sensorAccuracy_ = SensorAccuracyProtos.SensorAccuracy.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearAzimuthDegrees()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.azimuthDegrees_ = 0.0F;
        return this;
      }

      public Builder clearPitchDegrees()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.pitchDegrees_ = 0.0F;
        return this;
      }

      public Builder clearRollDegrees()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.rollDegrees_ = 0.0F;
        return this;
      }

      public Builder clearSensorAccuracy()
      {
        this.sensorAccuracy_ = SensorAccuracyProtos.SensorAccuracy.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public float getAzimuthDegrees()
      {
        return this.azimuthDegrees_;
      }

      public OrientationProtos.Orientation getDefaultInstanceForType()
      {
        return OrientationProtos.Orientation.getDefaultInstance();
      }

      public float getPitchDegrees()
      {
        return this.pitchDegrees_;
      }

      public float getRollDegrees()
      {
        return this.rollDegrees_;
      }

      public SensorAccuracyProtos.SensorAccuracy getSensorAccuracy()
      {
        return this.sensorAccuracy_;
      }

      public boolean hasAzimuthDegrees()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasPitchDegrees()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasRollDegrees()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasSensorAccuracy()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(OrientationProtos.Orientation paramOrientation)
      {
        if (paramOrientation == OrientationProtos.Orientation.getDefaultInstance());
        do
        {
          return this;
          if (paramOrientation.hasAzimuthDegrees())
            setAzimuthDegrees(paramOrientation.getAzimuthDegrees());
          if (paramOrientation.hasPitchDegrees())
            setPitchDegrees(paramOrientation.getPitchDegrees());
          if (paramOrientation.hasRollDegrees())
            setRollDegrees(paramOrientation.getRollDegrees());
        }
        while (!paramOrientation.hasSensorAccuracy());
        mergeSensorAccuracy(paramOrientation.getSensorAccuracy());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 13:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.azimuthDegrees_ = paramCodedInputStream.readFloat();
            break;
          case 21:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.pitchDegrees_ = paramCodedInputStream.readFloat();
            break;
          case 29:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.rollDegrees_ = paramCodedInputStream.readFloat();
            break;
          case 34:
          }
          SensorAccuracyProtos.SensorAccuracy.Builder localBuilder = SensorAccuracyProtos.SensorAccuracy.newBuilder();
          if (hasSensorAccuracy())
            localBuilder.mergeFrom(getSensorAccuracy());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setSensorAccuracy(localBuilder.buildPartial());
        }
      }

      public Builder mergeSensorAccuracy(SensorAccuracyProtos.SensorAccuracy paramSensorAccuracy)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.sensorAccuracy_ != SensorAccuracyProtos.SensorAccuracy.getDefaultInstance()));
        for (this.sensorAccuracy_ = SensorAccuracyProtos.SensorAccuracy.newBuilder(this.sensorAccuracy_).mergeFrom(paramSensorAccuracy).buildPartial(); ; this.sensorAccuracy_ = paramSensorAccuracy)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder setAzimuthDegrees(float paramFloat)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.azimuthDegrees_ = paramFloat;
        return this;
      }

      public Builder setPitchDegrees(float paramFloat)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.pitchDegrees_ = paramFloat;
        return this;
      }

      public Builder setRollDegrees(float paramFloat)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.rollDegrees_ = paramFloat;
        return this;
      }

      public Builder setSensorAccuracy(SensorAccuracyProtos.SensorAccuracy.Builder paramBuilder)
      {
        this.sensorAccuracy_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setSensorAccuracy(SensorAccuracyProtos.SensorAccuracy paramSensorAccuracy)
      {
        if (paramSensorAccuracy == null)
          throw new NullPointerException();
        this.sensorAccuracy_ = paramSensorAccuracy;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface OrientationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract float getAzimuthDegrees();

    public abstract float getPitchDegrees();

    public abstract float getRollDegrees();

    public abstract SensorAccuracyProtos.SensorAccuracy getSensorAccuracy();

    public abstract boolean hasAzimuthDegrees();

    public abstract boolean hasPitchDegrees();

    public abstract boolean hasRollDegrees();

    public abstract boolean hasSensorAccuracy();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.OrientationProtos
 * JD-Core Version:    0.6.2
 */