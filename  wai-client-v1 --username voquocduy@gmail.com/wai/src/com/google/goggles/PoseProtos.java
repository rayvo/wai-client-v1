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

public final class PoseProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Pose extends GeneratedMessageLite
    implements PoseProtos.PoseOrBuilder
  {
    public static final int LOCATION_FIELD_NUMBER = 2;
    public static final int ORIENTATION_FIELD_NUMBER = 1;
    private static final Pose defaultInstance = new Pose(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private LocationProtos.Location location_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private OrientationProtos.Orientation orientation_;

    static
    {
      defaultInstance.initFields();
    }

    private Pose(Builder paramBuilder)
    {
      super();
    }

    private Pose(boolean paramBoolean)
    {
    }

    public static Pose getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.location_ = LocationProtos.Location.getDefaultInstance();
      this.orientation_ = OrientationProtos.Orientation.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Pose paramPose)
    {
      return newBuilder().mergeFrom(paramPose);
    }

    public static Pose parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Pose parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Pose parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Pose parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Pose parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Pose parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Pose parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Pose parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Pose parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Pose parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Pose getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public LocationProtos.Location getLocation()
    {
      return this.location_;
    }

    public OrientationProtos.Orientation getOrientation()
    {
      return this.orientation_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x2 & this.bitField0_;
      int k = 0;
      if (j == 2)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.orientation_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(2, this.location_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasLocation()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasOrientation()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasLocation()) && (!getLocation().isInitialized()))
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
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(1, this.orientation_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(2, this.location_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PoseProtos.Pose, Builder>
      implements PoseProtos.PoseOrBuilder
    {
      private int bitField0_;
      private LocationProtos.Location location_ = LocationProtos.Location.getDefaultInstance();
      private OrientationProtos.Orientation orientation_ = OrientationProtos.Orientation.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private PoseProtos.Pose buildParsed()
        throws InvalidProtocolBufferException
      {
        PoseProtos.Pose localPose = buildPartial();
        if (!localPose.isInitialized())
          throw newUninitializedMessageException(localPose).asInvalidProtocolBufferException();
        return localPose;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public PoseProtos.Pose build()
      {
        PoseProtos.Pose localPose = buildPartial();
        if (!localPose.isInitialized())
          throw newUninitializedMessageException(localPose);
        return localPose;
      }

      public PoseProtos.Pose buildPartial()
      {
        int i = 1;
        PoseProtos.Pose localPose = new PoseProtos.Pose(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          PoseProtos.Pose.access$302(localPose, this.location_);
          if ((j & 0x2) == 2)
            i |= 2;
          PoseProtos.Pose.access$402(localPose, this.orientation_);
          PoseProtos.Pose.access$502(localPose, i);
          return localPose;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.location_ = LocationProtos.Location.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.orientation_ = OrientationProtos.Orientation.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearLocation()
      {
        this.location_ = LocationProtos.Location.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearOrientation()
      {
        this.orientation_ = OrientationProtos.Orientation.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public PoseProtos.Pose getDefaultInstanceForType()
      {
        return PoseProtos.Pose.getDefaultInstance();
      }

      public LocationProtos.Location getLocation()
      {
        return this.location_;
      }

      public OrientationProtos.Orientation getOrientation()
      {
        return this.orientation_;
      }

      public boolean hasLocation()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasOrientation()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return (!hasLocation()) || (getLocation().isInitialized());
      }

      public Builder mergeFrom(PoseProtos.Pose paramPose)
      {
        if (paramPose == PoseProtos.Pose.getDefaultInstance());
        do
        {
          return this;
          if (paramPose.hasLocation())
            mergeLocation(paramPose.getLocation());
        }
        while (!paramPose.hasOrientation());
        mergeOrientation(paramPose.getOrientation());
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
            OrientationProtos.Orientation.Builder localBuilder1 = OrientationProtos.Orientation.newBuilder();
            if (hasOrientation())
              localBuilder1.mergeFrom(getOrientation());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setOrientation(localBuilder1.buildPartial());
            break;
          case 18:
          }
          LocationProtos.Location.Builder localBuilder = LocationProtos.Location.newBuilder();
          if (hasLocation())
            localBuilder.mergeFrom(getLocation());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setLocation(localBuilder.buildPartial());
        }
      }

      public Builder mergeLocation(LocationProtos.Location paramLocation)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.location_ != LocationProtos.Location.getDefaultInstance()));
        for (this.location_ = LocationProtos.Location.newBuilder(this.location_).mergeFrom(paramLocation).buildPartial(); ; this.location_ = paramLocation)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeOrientation(OrientationProtos.Orientation paramOrientation)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.orientation_ != OrientationProtos.Orientation.getDefaultInstance()));
        for (this.orientation_ = OrientationProtos.Orientation.newBuilder(this.orientation_).mergeFrom(paramOrientation).buildPartial(); ; this.orientation_ = paramOrientation)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder setLocation(LocationProtos.Location.Builder paramBuilder)
      {
        this.location_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setLocation(LocationProtos.Location paramLocation)
      {
        if (paramLocation == null)
          throw new NullPointerException();
        this.location_ = paramLocation;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setOrientation(OrientationProtos.Orientation.Builder paramBuilder)
      {
        this.orientation_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setOrientation(OrientationProtos.Orientation paramOrientation)
      {
        if (paramOrientation == null)
          throw new NullPointerException();
        this.orientation_ = paramOrientation;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface PoseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract LocationProtos.Location getLocation();

    public abstract OrientationProtos.Orientation getOrientation();

    public abstract boolean hasLocation();

    public abstract boolean hasOrientation();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.PoseProtos
 * JD-Core Version:    0.6.2
 */