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

public final class LatLngProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class LatLng extends GeneratedMessageLite
    implements LatLngProtos.LatLngOrBuilder
  {
    public static final int LAT_DEGREES_FIELD_NUMBER = 1;
    public static final int LNG_DEGREES_FIELD_NUMBER = 2;
    private static final LatLng defaultInstance = new LatLng(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private double latDegrees_;
    private double lngDegrees_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private LatLng(Builder paramBuilder)
    {
      super();
    }

    private LatLng(boolean paramBoolean)
    {
    }

    public static LatLng getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.latDegrees_ = 0.0D;
      this.lngDegrees_ = 0.0D;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(LatLng paramLatLng)
    {
      return newBuilder().mergeFrom(paramLatLng);
    }

    public static LatLng parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static LatLng parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static LatLng parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static LatLng parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static LatLng parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static LatLng parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static LatLng parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static LatLng parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static LatLng parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static LatLng parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public LatLng getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public double getLatDegrees()
    {
      return this.latDegrees_;
    }

    public double getLngDegrees()
    {
      return this.lngDegrees_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeDoubleSize(1, this.latDegrees_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeDoubleSize(2, this.lngDegrees_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasLatDegrees()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasLngDegrees()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasLatDegrees())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasLngDegrees())
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
        paramCodedOutputStream.writeDouble(1, this.latDegrees_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeDouble(2, this.lngDegrees_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<LatLngProtos.LatLng, Builder>
      implements LatLngProtos.LatLngOrBuilder
    {
      private int bitField0_;
      private double latDegrees_;
      private double lngDegrees_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private LatLngProtos.LatLng buildParsed()
        throws InvalidProtocolBufferException
      {
        LatLngProtos.LatLng localLatLng = buildPartial();
        if (!localLatLng.isInitialized())
          throw newUninitializedMessageException(localLatLng).asInvalidProtocolBufferException();
        return localLatLng;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public LatLngProtos.LatLng build()
      {
        LatLngProtos.LatLng localLatLng = buildPartial();
        if (!localLatLng.isInitialized())
          throw newUninitializedMessageException(localLatLng);
        return localLatLng;
      }

      public LatLngProtos.LatLng buildPartial()
      {
        int i = 1;
        LatLngProtos.LatLng localLatLng = new LatLngProtos.LatLng(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          LatLngProtos.LatLng.access$302(localLatLng, this.latDegrees_);
          if ((j & 0x2) == 2)
            i |= 2;
          LatLngProtos.LatLng.access$402(localLatLng, this.lngDegrees_);
          LatLngProtos.LatLng.access$502(localLatLng, i);
          return localLatLng;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.latDegrees_ = 0.0D;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.lngDegrees_ = 0.0D;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearLatDegrees()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.latDegrees_ = 0.0D;
        return this;
      }

      public Builder clearLngDegrees()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.lngDegrees_ = 0.0D;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public LatLngProtos.LatLng getDefaultInstanceForType()
      {
        return LatLngProtos.LatLng.getDefaultInstance();
      }

      public double getLatDegrees()
      {
        return this.latDegrees_;
      }

      public double getLngDegrees()
      {
        return this.lngDegrees_;
      }

      public boolean hasLatDegrees()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasLngDegrees()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        if (!hasLatDegrees());
        while (!hasLngDegrees())
          return false;
        return true;
      }

      public Builder mergeFrom(LatLngProtos.LatLng paramLatLng)
      {
        if (paramLatLng == LatLngProtos.LatLng.getDefaultInstance());
        do
        {
          return this;
          if (paramLatLng.hasLatDegrees())
            setLatDegrees(paramLatLng.getLatDegrees());
        }
        while (!paramLatLng.hasLngDegrees());
        setLngDegrees(paramLatLng.getLngDegrees());
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
          case 9:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.latDegrees_ = paramCodedInputStream.readDouble();
            break;
          case 17:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.lngDegrees_ = paramCodedInputStream.readDouble();
        }
      }

      public Builder setLatDegrees(double paramDouble)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.latDegrees_ = paramDouble;
        return this;
      }

      public Builder setLngDegrees(double paramDouble)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.lngDegrees_ = paramDouble;
        return this;
      }
    }
  }

  public static abstract interface LatLngOrBuilder extends MessageLiteOrBuilder
  {
    public abstract double getLatDegrees();

    public abstract double getLngDegrees();

    public abstract boolean hasLatDegrees();

    public abstract boolean hasLngDegrees();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.LatLngProtos
 * JD-Core Version:    0.6.2
 */