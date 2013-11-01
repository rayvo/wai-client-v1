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

public final class LocationProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Location extends GeneratedMessageLite
    implements LocationProtos.LocationOrBuilder
  {
    public static final int ALTITUDE_ACCURACY_METERS_FIELD_NUMBER = 5;
    public static final int ALTITUDE_METERS_FIELD_NUMBER = 4;
    public static final int LAT_LNG_ACCURACY_METERS_FIELD_NUMBER = 2;
    public static final int LAT_LNG_FIELD_NUMBER = 1;
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 3;
    private static final Location defaultInstance = new Location(true);
    private static final long serialVersionUID;
    private float altitudeAccuracyMeters_;
    private float altitudeMeters_;
    private int bitField0_;
    private float latLngAccuracyMeters_;
    private LatLngProtos.LatLng latLng_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private long timestampMs_;

    static
    {
      defaultInstance.initFields();
    }

    private Location(Builder paramBuilder)
    {
      super();
    }

    private Location(boolean paramBoolean)
    {
    }

    public static Location getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
      this.latLngAccuracyMeters_ = 0.0F;
      this.altitudeMeters_ = 0.0F;
      this.altitudeAccuracyMeters_ = 0.0F;
      this.timestampMs_ = 0L;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Location paramLocation)
    {
      return newBuilder().mergeFrom(paramLocation);
    }

    public static Location parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Location parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Location parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Location parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Location parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Location parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Location parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Location parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Location parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Location parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public float getAltitudeAccuracyMeters()
    {
      return this.altitudeAccuracyMeters_;
    }

    public float getAltitudeMeters()
    {
      return this.altitudeMeters_;
    }

    public Location getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public LatLngProtos.LatLng getLatLng()
    {
      return this.latLng_;
    }

    public float getLatLngAccuracyMeters()
    {
      return this.latLngAccuracyMeters_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.latLng_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeFloatSize(2, this.latLngAccuracyMeters_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeInt64Size(3, this.timestampMs_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeFloatSize(4, this.altitudeMeters_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeFloatSize(5, this.altitudeAccuracyMeters_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public long getTimestampMs()
    {
      return this.timestampMs_;
    }

    public boolean hasAltitudeAccuracyMeters()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasAltitudeMeters()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasLatLng()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasLatLngAccuracyMeters()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasTimestampMs()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasLatLng()) && (!getLatLng().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
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
        paramCodedOutputStream.writeMessage(1, this.latLng_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeFloat(2, this.latLngAccuracyMeters_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeInt64(3, this.timestampMs_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeFloat(4, this.altitudeMeters_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeFloat(5, this.altitudeAccuracyMeters_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LocationProtos.Location, Builder>
      implements LocationProtos.LocationOrBuilder
    {
      private float altitudeAccuracyMeters_;
      private float altitudeMeters_;
      private int bitField0_;
      private float latLngAccuracyMeters_;
      private LatLngProtos.LatLng latLng_ = LatLngProtos.LatLng.getDefaultInstance();
      private long timestampMs_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private LocationProtos.Location buildParsed()
        throws InvalidProtocolBufferException
      {
        LocationProtos.Location localLocation = buildPartial();
        if (!localLocation.isInitialized())
          throw newUninitializedMessageException(localLocation).asInvalidProtocolBufferException();
        return localLocation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public LocationProtos.Location build()
      {
        LocationProtos.Location localLocation = buildPartial();
        if (!localLocation.isInitialized())
          throw newUninitializedMessageException(localLocation);
        return localLocation;
      }

      public LocationProtos.Location buildPartial()
      {
        int i = 1;
        LocationProtos.Location localLocation = new LocationProtos.Location(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          LocationProtos.Location.access$302(localLocation, this.latLng_);
          if ((j & 0x2) == 2)
            i |= 2;
          LocationProtos.Location.access$402(localLocation, this.latLngAccuracyMeters_);
          if ((j & 0x4) == 4)
            i |= 4;
          LocationProtos.Location.access$502(localLocation, this.altitudeMeters_);
          if ((j & 0x8) == 8)
            i |= 8;
          LocationProtos.Location.access$602(localLocation, this.altitudeAccuracyMeters_);
          if ((j & 0x10) == 16)
            i |= 16;
          LocationProtos.Location.access$702(localLocation, this.timestampMs_);
          LocationProtos.Location.access$802(localLocation, i);
          return localLocation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.latLngAccuracyMeters_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.altitudeMeters_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.altitudeAccuracyMeters_ = 0.0F;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.timestampMs_ = 0L;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      public Builder clearAltitudeAccuracyMeters()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.altitudeAccuracyMeters_ = 0.0F;
        return this;
      }

      public Builder clearAltitudeMeters()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.altitudeMeters_ = 0.0F;
        return this;
      }

      public Builder clearLatLng()
      {
        this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearLatLngAccuracyMeters()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.latLngAccuracyMeters_ = 0.0F;
        return this;
      }

      public Builder clearTimestampMs()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.timestampMs_ = 0L;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public float getAltitudeAccuracyMeters()
      {
        return this.altitudeAccuracyMeters_;
      }

      public float getAltitudeMeters()
      {
        return this.altitudeMeters_;
      }

      public LocationProtos.Location getDefaultInstanceForType()
      {
        return LocationProtos.Location.getDefaultInstance();
      }

      public LatLngProtos.LatLng getLatLng()
      {
        return this.latLng_;
      }

      public float getLatLngAccuracyMeters()
      {
        return this.latLngAccuracyMeters_;
      }

      public long getTimestampMs()
      {
        return this.timestampMs_;
      }

      public boolean hasAltitudeAccuracyMeters()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasAltitudeMeters()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasLatLng()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasLatLngAccuracyMeters()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasTimestampMs()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public final boolean isInitialized()
      {
        return (!hasLatLng()) || (getLatLng().isInitialized());
      }

      public Builder mergeFrom(LocationProtos.Location paramLocation)
      {
        if (paramLocation == LocationProtos.Location.getDefaultInstance());
        do
        {
          return this;
          if (paramLocation.hasLatLng())
            mergeLatLng(paramLocation.getLatLng());
          if (paramLocation.hasLatLngAccuracyMeters())
            setLatLngAccuracyMeters(paramLocation.getLatLngAccuracyMeters());
          if (paramLocation.hasAltitudeMeters())
            setAltitudeMeters(paramLocation.getAltitudeMeters());
          if (paramLocation.hasAltitudeAccuracyMeters())
            setAltitudeAccuracyMeters(paramLocation.getAltitudeAccuracyMeters());
        }
        while (!paramLocation.hasTimestampMs());
        setTimestampMs(paramLocation.getTimestampMs());
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
          case 10:
            LatLngProtos.LatLng.Builder localBuilder = LatLngProtos.LatLng.newBuilder();
            if (hasLatLng())
              localBuilder.mergeFrom(getLatLng());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setLatLng(localBuilder.buildPartial());
            break;
          case 21:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.latLngAccuracyMeters_ = paramCodedInputStream.readFloat();
            break;
          case 24:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.timestampMs_ = paramCodedInputStream.readInt64();
            break;
          case 37:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.altitudeMeters_ = paramCodedInputStream.readFloat();
            break;
          case 45:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.altitudeAccuracyMeters_ = paramCodedInputStream.readFloat();
        }
      }

      public Builder mergeLatLng(LatLngProtos.LatLng paramLatLng)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.latLng_ != LatLngProtos.LatLng.getDefaultInstance()));
        for (this.latLng_ = LatLngProtos.LatLng.newBuilder(this.latLng_).mergeFrom(paramLatLng).buildPartial(); ; this.latLng_ = paramLatLng)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder setAltitudeAccuracyMeters(float paramFloat)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.altitudeAccuracyMeters_ = paramFloat;
        return this;
      }

      public Builder setAltitudeMeters(float paramFloat)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.altitudeMeters_ = paramFloat;
        return this;
      }

      public Builder setLatLng(LatLngProtos.LatLng.Builder paramBuilder)
      {
        this.latLng_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setLatLng(LatLngProtos.LatLng paramLatLng)
      {
        if (paramLatLng == null)
          throw new NullPointerException();
        this.latLng_ = paramLatLng;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setLatLngAccuracyMeters(float paramFloat)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.latLngAccuracyMeters_ = paramFloat;
        return this;
      }

      public Builder setTimestampMs(long paramLong)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.timestampMs_ = paramLong;
        return this;
      }
    }
  }

  public static abstract interface LocationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract float getAltitudeAccuracyMeters();

    public abstract float getAltitudeMeters();

    public abstract LatLngProtos.LatLng getLatLng();

    public abstract float getLatLngAccuracyMeters();

    public abstract long getTimestampMs();

    public abstract boolean hasAltitudeAccuracyMeters();

    public abstract boolean hasAltitudeMeters();

    public abstract boolean hasLatLng();

    public abstract boolean hasLatLngAccuracyMeters();

    public abstract boolean hasTimestampMs();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.LocationProtos
 * JD-Core Version:    0.6.2
 */