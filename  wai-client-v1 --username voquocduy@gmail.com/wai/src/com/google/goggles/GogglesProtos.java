package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.GeneratedMessageLite.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder;
import com.google.protobuf.GeneratedMessageLite.GeneratedExtension;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class GogglesProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(ImageRotation.imageRotation);
  }

  public static final class Audio extends GeneratedMessageLite
    implements GogglesProtos.AudioOrBuilder
  {
    public static final int DATA_FIELD_NUMBER = 1;
    public static final int SAMPLE_RATE_HZ_FIELD_NUMBER = 3;
    private static final Audio defaultInstance = new Audio(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private ByteString data_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private float sampleRateHz_;

    static
    {
      defaultInstance.initFields();
    }

    private Audio(Builder paramBuilder)
    {
      super();
    }

    private Audio(boolean paramBoolean)
    {
    }

    public static Audio getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.data_ = ByteString.EMPTY;
      this.sampleRateHz_ = 0.0F;
    }

    public static Builder newBuilder()
    {
      return Builder.access$3400();
    }

    public static Builder newBuilder(Audio paramAudio)
    {
      return newBuilder().mergeFrom(paramAudio);
    }

    public static Audio parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Audio parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Audio parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Audio parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Audio parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Audio parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Audio parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Audio parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Audio parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Audio parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ByteString getData()
    {
      return this.data_;
    }

    public Audio getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public float getSampleRateHz()
    {
      return this.sampleRateHz_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, this.data_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeFloatSize(3, this.sampleRateHz_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasData()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasSampleRateHz()
    {
      return (0x2 & this.bitField0_) == 2;
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
        paramCodedOutputStream.writeBytes(1, this.data_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeFloat(3, this.sampleRateHz_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesProtos.Audio, Builder>
      implements GogglesProtos.AudioOrBuilder
    {
      private int bitField0_;
      private ByteString data_ = ByteString.EMPTY;
      private float sampleRateHz_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.Audio buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.Audio localAudio = buildPartial();
        if (!localAudio.isInitialized())
          throw newUninitializedMessageException(localAudio).asInvalidProtocolBufferException();
        return localAudio;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.Audio build()
      {
        GogglesProtos.Audio localAudio = buildPartial();
        if (!localAudio.isInitialized())
          throw newUninitializedMessageException(localAudio);
        return localAudio;
      }

      public GogglesProtos.Audio buildPartial()
      {
        int i = 1;
        GogglesProtos.Audio localAudio = new GogglesProtos.Audio(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.Audio.access$3602(localAudio, this.data_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.Audio.access$3702(localAudio, this.sampleRateHz_);
          GogglesProtos.Audio.access$3802(localAudio, i);
          return localAudio;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.data_ = ByteString.EMPTY;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.sampleRateHz_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearData()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.data_ = GogglesProtos.Audio.getDefaultInstance().getData();
        return this;
      }

      public Builder clearSampleRateHz()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.sampleRateHz_ = 0.0F;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ByteString getData()
      {
        return this.data_;
      }

      public GogglesProtos.Audio getDefaultInstanceForType()
      {
        return GogglesProtos.Audio.getDefaultInstance();
      }

      public float getSampleRateHz()
      {
        return this.sampleRateHz_;
      }

      public boolean hasData()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasSampleRateHz()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(GogglesProtos.Audio paramAudio)
      {
        if (paramAudio == GogglesProtos.Audio.getDefaultInstance());
        do
        {
          return this;
          if (paramAudio.hasData())
            setData(paramAudio.getData());
        }
        while (!paramAudio.hasSampleRateHz());
        setSampleRateHz(paramAudio.getSampleRateHz());
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
            this.bitField0_ = (0x1 | this.bitField0_);
            this.data_ = paramCodedInputStream.readBytes();
            break;
          case 29:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.sampleRateHz_ = paramCodedInputStream.readFloat();
        }
      }

      public Builder setData(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.data_ = paramByteString;
        return this;
      }

      public Builder setSampleRateHz(float paramFloat)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.sampleRateHz_ = paramFloat;
        return this;
      }
    }
  }

  public static abstract interface AudioOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ByteString getData();

    public abstract float getSampleRateHz();

    public abstract boolean hasData();

    public abstract boolean hasSampleRateHz();
  }

  public static final class CropRegion extends GeneratedMessageLite
    implements GogglesProtos.CropRegionOrBuilder
  {
    public static final int HEIGHT_FIELD_NUMBER = 2;
    public static final int REGION_FIELD_NUMBER = 3;
    public static final int WIDTH_FIELD_NUMBER = 1;
    private static final CropRegion defaultInstance = new CropRegion(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int height_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private BoundingBoxProtos.BoundingBox region_;
    private int width_;

    static
    {
      defaultInstance.initFields();
    }

    private CropRegion(Builder paramBuilder)
    {
      super();
    }

    private CropRegion(boolean paramBoolean)
    {
    }

    public static CropRegion getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.width_ = 0;
      this.height_ = 0;
      this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(CropRegion paramCropRegion)
    {
      return newBuilder().mergeFrom(paramCropRegion);
    }

    public static CropRegion parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static CropRegion parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static CropRegion parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static CropRegion parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static CropRegion parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static CropRegion parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static CropRegion parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static CropRegion parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static CropRegion parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static CropRegion parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public CropRegion getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getHeight()
    {
      return this.height_;
    }

    public BoundingBoxProtos.BoundingBox getRegion()
    {
      return this.region_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.width_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeInt32Size(2, this.height_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(3, this.region_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getWidth()
    {
      return this.width_;
    }

    public boolean hasHeight()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasRegion()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasWidth()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = 1;
      int j = this.memoizedIsInitialized;
      if (j != -1)
      {
        if (j == i);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      if (!hasWidth())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasHeight())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasRegion())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!getRegion().isInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = i;
      return i;
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
        paramCodedOutputStream.writeInt32(1, this.width_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt32(2, this.height_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(3, this.region_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesProtos.CropRegion, Builder>
      implements GogglesProtos.CropRegionOrBuilder
    {
      private int bitField0_;
      private int height_;
      private BoundingBoxProtos.BoundingBox region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      private int width_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.CropRegion buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.CropRegion localCropRegion = buildPartial();
        if (!localCropRegion.isInitialized())
          throw newUninitializedMessageException(localCropRegion).asInvalidProtocolBufferException();
        return localCropRegion;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.CropRegion build()
      {
        GogglesProtos.CropRegion localCropRegion = buildPartial();
        if (!localCropRegion.isInitialized())
          throw newUninitializedMessageException(localCropRegion);
        return localCropRegion;
      }

      public GogglesProtos.CropRegion buildPartial()
      {
        int i = 1;
        GogglesProtos.CropRegion localCropRegion = new GogglesProtos.CropRegion(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.CropRegion.access$302(localCropRegion, this.width_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.CropRegion.access$402(localCropRegion, this.height_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesProtos.CropRegion.access$502(localCropRegion, this.region_);
          GogglesProtos.CropRegion.access$602(localCropRegion, i);
          return localCropRegion;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.width_ = 0;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.height_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearHeight()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.height_ = 0;
        return this;
      }

      public Builder clearRegion()
      {
        this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearWidth()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.width_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesProtos.CropRegion getDefaultInstanceForType()
      {
        return GogglesProtos.CropRegion.getDefaultInstance();
      }

      public int getHeight()
      {
        return this.height_;
      }

      public BoundingBoxProtos.BoundingBox getRegion()
      {
        return this.region_;
      }

      public int getWidth()
      {
        return this.width_;
      }

      public boolean hasHeight()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasRegion()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasWidth()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasWidth());
        while ((!hasHeight()) || (!hasRegion()) || (!getRegion().isInitialized()))
          return false;
        return true;
      }

      public Builder mergeFrom(GogglesProtos.CropRegion paramCropRegion)
      {
        if (paramCropRegion == GogglesProtos.CropRegion.getDefaultInstance());
        do
        {
          return this;
          if (paramCropRegion.hasWidth())
            setWidth(paramCropRegion.getWidth());
          if (paramCropRegion.hasHeight())
            setHeight(paramCropRegion.getHeight());
        }
        while (!paramCropRegion.hasRegion());
        mergeRegion(paramCropRegion.getRegion());
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
          case 8:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.width_ = paramCodedInputStream.readInt32();
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.height_ = paramCodedInputStream.readInt32();
            break;
          case 26:
          }
          BoundingBoxProtos.BoundingBox.Builder localBuilder = BoundingBoxProtos.BoundingBox.newBuilder();
          if (hasRegion())
            localBuilder.mergeFrom(getRegion());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setRegion(localBuilder.buildPartial());
        }
      }

      public Builder mergeRegion(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.region_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
        for (this.region_ = BoundingBoxProtos.BoundingBox.newBuilder(this.region_).mergeFrom(paramBoundingBox).buildPartial(); ; this.region_ = paramBoundingBox)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder setHeight(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.height_ = paramInt;
        return this;
      }

      public Builder setRegion(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
      {
        this.region_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setRegion(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == null)
          throw new NullPointerException();
        this.region_ = paramBoundingBox;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setWidth(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.width_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface CropRegionOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getHeight();

    public abstract BoundingBoxProtos.BoundingBox getRegion();

    public abstract int getWidth();

    public abstract boolean hasHeight();

    public abstract boolean hasRegion();

    public abstract boolean hasWidth();
  }

  public static final class GogglesRequest extends GeneratedMessageLite.ExtendableMessage<GogglesRequest>
    implements GogglesProtos.GogglesRequestOrBuilder
  {
    public static final int AUDIO_FIELD_NUMBER = 3;
    public static final int CAPTURE_TYPE_FIELD_NUMBER = 7;
    public static final int FLOW_FIELD_NUMBER = 5;
    public static final int GOGGLES_CONFIG_FIELD_NUMBER = 10;
    public static final int IMAGE_FIELD_NUMBER = 1;
    public static final int POSE_FIELD_NUMBER = 2;
    public static final int RESTRICTS_FIELD_NUMBER = 8;
    public static final int SOURCE_FIELD_NUMBER = 6;
    public static final int TEXT_FIELD_NUMBER = 4;
    private static final GogglesRequest defaultInstance = new GogglesRequest(true);
    private static final long serialVersionUID;
    private GogglesProtos.Audio audio_;
    private int bitField0_;
    private CaptureType captureType_;
    private FlowProtos.FlowData flow_;
    private GogglesConfigProtos.GogglesConfig gogglesConfig_;
    private GogglesProtos.Image image_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private PoseProtos.Pose pose_;
    private RestrictsProtos.Restricts restricts_;
    private Source source_;
    private Object text_;

    static
    {
      defaultInstance.initFields();
    }

    private GogglesRequest(Builder paramBuilder)
    {
      super();
    }

    private GogglesRequest(boolean paramBoolean)
    {
    }

    public static GogglesRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getTextBytes()
    {
      Object localObject = this.text_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.text_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
      this.image_ = GogglesProtos.Image.getDefaultInstance();
      this.pose_ = PoseProtos.Pose.getDefaultInstance();
      this.audio_ = GogglesProtos.Audio.getDefaultInstance();
      this.text_ = "";
      this.flow_ = FlowProtos.FlowData.getDefaultInstance();
      this.source_ = Source.UNKNOWN;
      this.captureType_ = CaptureType.SINGLE;
      this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$4000();
    }

    public static Builder newBuilder(GogglesRequest paramGogglesRequest)
    {
      return newBuilder().mergeFrom(paramGogglesRequest);
    }

    public static GogglesRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GogglesRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GogglesRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GogglesRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GogglesRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GogglesRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GogglesProtos.Audio getAudio()
    {
      return this.audio_;
    }

    public CaptureType getCaptureType()
    {
      return this.captureType_;
    }

    public GogglesRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public FlowProtos.FlowData getFlow()
    {
      return this.flow_;
    }

    public GogglesConfigProtos.GogglesConfig getGogglesConfig()
    {
      return this.gogglesConfig_;
    }

    public GogglesProtos.Image getImage()
    {
      return this.image_;
    }

    public PoseProtos.Pose getPose()
    {
      return this.pose_;
    }

    public RestrictsProtos.Restricts getRestricts()
    {
      return this.restricts_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x2 & this.bitField0_;
      int k = 0;
      if (j == 2)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.image_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(2, this.pose_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(3, this.audio_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeBytesSize(4, getTextBytes());
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeMessageSize(5, this.flow_);
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeEnumSize(6, this.source_.getNumber());
      if ((0x80 & this.bitField0_) == 128)
        k += CodedOutputStream.computeEnumSize(7, this.captureType_.getNumber());
      if ((0x100 & this.bitField0_) == 256)
        k += CodedOutputStream.computeMessageSize(8, this.restricts_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(10, this.gogglesConfig_);
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public Source getSource()
    {
      return this.source_;
    }

    public String getText()
    {
      Object localObject = this.text_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.text_ = str;
      return str;
    }

    public boolean hasAudio()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasCaptureType()
    {
      return (0x80 & this.bitField0_) == 128;
    }

    public boolean hasFlow()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasGogglesConfig()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasImage()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasPose()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasRestricts()
    {
      return (0x100 & this.bitField0_) == 256;
    }

    public boolean hasSource()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasText()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public final boolean isInitialized()
    {
      int i = 1;
      int j = this.memoizedIsInitialized;
      if (j != -1)
      {
        if (j == i);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      if ((hasGogglesConfig()) && (!getGogglesConfig().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasImage()) && (!getImage().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasPose()) && (!getPose().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = i;
      return i;
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
      GeneratedMessageLite.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(1, this.image_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(2, this.pose_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(3, this.audio_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(4, getTextBytes());
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeMessage(5, this.flow_);
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeEnum(6, this.source_.getNumber());
      if ((0x80 & this.bitField0_) == 128)
        paramCodedOutputStream.writeEnum(7, this.captureType_.getNumber());
      if ((0x100 & this.bitField0_) == 256)
        paramCodedOutputStream.writeMessage(8, this.restricts_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(10, this.gogglesConfig_);
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<GogglesProtos.GogglesRequest, Builder>
      implements GogglesProtos.GogglesRequestOrBuilder
    {
      private GogglesProtos.Audio audio_ = GogglesProtos.Audio.getDefaultInstance();
      private int bitField0_;
      private GogglesProtos.GogglesRequest.CaptureType captureType_ = GogglesProtos.GogglesRequest.CaptureType.SINGLE;
      private FlowProtos.FlowData flow_ = FlowProtos.FlowData.getDefaultInstance();
      private GogglesConfigProtos.GogglesConfig gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
      private GogglesProtos.Image image_ = GogglesProtos.Image.getDefaultInstance();
      private PoseProtos.Pose pose_ = PoseProtos.Pose.getDefaultInstance();
      private RestrictsProtos.Restricts restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
      private GogglesProtos.GogglesRequest.Source source_ = GogglesProtos.GogglesRequest.Source.UNKNOWN;
      private Object text_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.GogglesRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.GogglesRequest localGogglesRequest = buildPartial();
        if (!localGogglesRequest.isInitialized())
          throw newUninitializedMessageException(localGogglesRequest).asInvalidProtocolBufferException();
        return localGogglesRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.GogglesRequest build()
      {
        GogglesProtos.GogglesRequest localGogglesRequest = buildPartial();
        if (!localGogglesRequest.isInitialized())
          throw newUninitializedMessageException(localGogglesRequest);
        return localGogglesRequest;
      }

      public GogglesProtos.GogglesRequest buildPartial()
      {
        int i = 1;
        GogglesProtos.GogglesRequest localGogglesRequest = new GogglesProtos.GogglesRequest(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.GogglesRequest.access$4202(localGogglesRequest, this.gogglesConfig_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.GogglesRequest.access$4302(localGogglesRequest, this.image_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesProtos.GogglesRequest.access$4402(localGogglesRequest, this.pose_);
          if ((j & 0x8) == 8)
            i |= 8;
          GogglesProtos.GogglesRequest.access$4502(localGogglesRequest, this.audio_);
          if ((j & 0x10) == 16)
            i |= 16;
          GogglesProtos.GogglesRequest.access$4602(localGogglesRequest, this.text_);
          if ((j & 0x20) == 32)
            i |= 32;
          GogglesProtos.GogglesRequest.access$4702(localGogglesRequest, this.flow_);
          if ((j & 0x40) == 64)
            i |= 64;
          GogglesProtos.GogglesRequest.access$4802(localGogglesRequest, this.source_);
          if ((j & 0x80) == 128)
            i |= 128;
          GogglesProtos.GogglesRequest.access$4902(localGogglesRequest, this.captureType_);
          if ((j & 0x100) == 256)
            i |= 256;
          GogglesProtos.GogglesRequest.access$5002(localGogglesRequest, this.restricts_);
          GogglesProtos.GogglesRequest.access$5102(localGogglesRequest, i);
          return localGogglesRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.image_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.pose_ = PoseProtos.Pose.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.audio_ = GogglesProtos.Audio.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.text_ = "";
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.flow_ = FlowProtos.FlowData.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.source_ = GogglesProtos.GogglesRequest.Source.UNKNOWN;
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.captureType_ = GogglesProtos.GogglesRequest.CaptureType.SINGLE;
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clearAudio()
      {
        this.audio_ = GogglesProtos.Audio.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearCaptureType()
      {
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.captureType_ = GogglesProtos.GogglesRequest.CaptureType.SINGLE;
        return this;
      }

      public Builder clearFlow()
      {
        this.flow_ = FlowProtos.FlowData.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        return this;
      }

      public Builder clearGogglesConfig()
      {
        this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearImage()
      {
        this.image_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearPose()
      {
        this.pose_ = PoseProtos.Pose.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearRestricts()
      {
        this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clearSource()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.source_ = GogglesProtos.GogglesRequest.Source.UNKNOWN;
        return this;
      }

      public Builder clearText()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.text_ = GogglesProtos.GogglesRequest.getDefaultInstance().getText();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesProtos.Audio getAudio()
      {
        return this.audio_;
      }

      public GogglesProtos.GogglesRequest.CaptureType getCaptureType()
      {
        return this.captureType_;
      }

      public GogglesProtos.GogglesRequest getDefaultInstanceForType()
      {
        return GogglesProtos.GogglesRequest.getDefaultInstance();
      }

      public FlowProtos.FlowData getFlow()
      {
        return this.flow_;
      }

      public GogglesConfigProtos.GogglesConfig getGogglesConfig()
      {
        return this.gogglesConfig_;
      }

      public GogglesProtos.Image getImage()
      {
        return this.image_;
      }

      public PoseProtos.Pose getPose()
      {
        return this.pose_;
      }

      public RestrictsProtos.Restricts getRestricts()
      {
        return this.restricts_;
      }

      public GogglesProtos.GogglesRequest.Source getSource()
      {
        return this.source_;
      }

      public String getText()
      {
        Object localObject = this.text_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.text_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasAudio()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasCaptureType()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasFlow()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasGogglesConfig()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasImage()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasPose()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasRestricts()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      public boolean hasSource()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasText()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public final boolean isInitialized()
      {
        if ((hasGogglesConfig()) && (!getGogglesConfig().isInitialized()));
        while (((hasImage()) && (!getImage().isInitialized())) || ((hasPose()) && (!getPose().isInitialized())) || (!extensionsAreInitialized()))
          return false;
        return true;
      }

      public Builder mergeAudio(GogglesProtos.Audio paramAudio)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.audio_ != GogglesProtos.Audio.getDefaultInstance()));
        for (this.audio_ = GogglesProtos.Audio.newBuilder(this.audio_).mergeFrom(paramAudio).buildPartial(); ; this.audio_ = paramAudio)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFlow(FlowProtos.FlowData paramFlowData)
      {
        if (((0x20 & this.bitField0_) == 32) && (this.flow_ != FlowProtos.FlowData.getDefaultInstance()));
        for (this.flow_ = FlowProtos.FlowData.newBuilder(this.flow_).mergeFrom(paramFlowData).buildPartial(); ; this.flow_ = paramFlowData)
        {
          this.bitField0_ = (0x20 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(GogglesProtos.GogglesRequest paramGogglesRequest)
      {
        if (paramGogglesRequest == GogglesProtos.GogglesRequest.getDefaultInstance())
          return this;
        if (paramGogglesRequest.hasGogglesConfig())
          mergeGogglesConfig(paramGogglesRequest.getGogglesConfig());
        if (paramGogglesRequest.hasImage())
          mergeImage(paramGogglesRequest.getImage());
        if (paramGogglesRequest.hasPose())
          mergePose(paramGogglesRequest.getPose());
        if (paramGogglesRequest.hasAudio())
          mergeAudio(paramGogglesRequest.getAudio());
        if (paramGogglesRequest.hasText())
          setText(paramGogglesRequest.getText());
        if (paramGogglesRequest.hasFlow())
          mergeFlow(paramGogglesRequest.getFlow());
        if (paramGogglesRequest.hasSource())
          setSource(paramGogglesRequest.getSource());
        if (paramGogglesRequest.hasCaptureType())
          setCaptureType(paramGogglesRequest.getCaptureType());
        if (paramGogglesRequest.hasRestricts())
          mergeRestricts(paramGogglesRequest.getRestricts());
        mergeExtensionFields(paramGogglesRequest);
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
            GogglesProtos.Image.Builder localBuilder5 = GogglesProtos.Image.newBuilder();
            if (hasImage())
              localBuilder5.mergeFrom(getImage());
            paramCodedInputStream.readMessage(localBuilder5, paramExtensionRegistryLite);
            setImage(localBuilder5.buildPartial());
            break;
          case 18:
            PoseProtos.Pose.Builder localBuilder4 = PoseProtos.Pose.newBuilder();
            if (hasPose())
              localBuilder4.mergeFrom(getPose());
            paramCodedInputStream.readMessage(localBuilder4, paramExtensionRegistryLite);
            setPose(localBuilder4.buildPartial());
            break;
          case 26:
            GogglesProtos.Audio.Builder localBuilder3 = GogglesProtos.Audio.newBuilder();
            if (hasAudio())
              localBuilder3.mergeFrom(getAudio());
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            setAudio(localBuilder3.buildPartial());
            break;
          case 34:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.text_ = paramCodedInputStream.readBytes();
            break;
          case 42:
            FlowProtos.FlowData.Builder localBuilder2 = FlowProtos.FlowData.newBuilder();
            if (hasFlow())
              localBuilder2.mergeFrom(getFlow());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setFlow(localBuilder2.buildPartial());
            break;
          case 48:
            GogglesProtos.GogglesRequest.Source localSource = GogglesProtos.GogglesRequest.Source.valueOf(paramCodedInputStream.readEnum());
            if (localSource == null)
              continue;
            this.bitField0_ = (0x40 | this.bitField0_);
            this.source_ = localSource;
            break;
          case 56:
            GogglesProtos.GogglesRequest.CaptureType localCaptureType = GogglesProtos.GogglesRequest.CaptureType.valueOf(paramCodedInputStream.readEnum());
            if (localCaptureType == null)
              continue;
            this.bitField0_ = (0x80 | this.bitField0_);
            this.captureType_ = localCaptureType;
            break;
          case 66:
            RestrictsProtos.Restricts.Builder localBuilder1 = RestrictsProtos.Restricts.newBuilder();
            if (hasRestricts())
              localBuilder1.mergeFrom(getRestricts());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setRestricts(localBuilder1.buildPartial());
            break;
          case 82:
          }
          GogglesConfigProtos.GogglesConfig.Builder localBuilder = GogglesConfigProtos.GogglesConfig.newBuilder();
          if (hasGogglesConfig())
            localBuilder.mergeFrom(getGogglesConfig());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setGogglesConfig(localBuilder.buildPartial());
        }
      }

      public Builder mergeGogglesConfig(GogglesConfigProtos.GogglesConfig paramGogglesConfig)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.gogglesConfig_ != GogglesConfigProtos.GogglesConfig.getDefaultInstance()));
        for (this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.newBuilder(this.gogglesConfig_).mergeFrom(paramGogglesConfig).buildPartial(); ; this.gogglesConfig_ = paramGogglesConfig)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeImage(GogglesProtos.Image paramImage)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.image_ != GogglesProtos.Image.getDefaultInstance()));
        for (this.image_ = GogglesProtos.Image.newBuilder(this.image_).mergeFrom(paramImage).buildPartial(); ; this.image_ = paramImage)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder mergePose(PoseProtos.Pose paramPose)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.pose_ != PoseProtos.Pose.getDefaultInstance()));
        for (this.pose_ = PoseProtos.Pose.newBuilder(this.pose_).mergeFrom(paramPose).buildPartial(); ; this.pose_ = paramPose)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (((0x100 & this.bitField0_) == 256) && (this.restricts_ != RestrictsProtos.Restricts.getDefaultInstance()));
        for (this.restricts_ = RestrictsProtos.Restricts.newBuilder(this.restricts_).mergeFrom(paramRestricts).buildPartial(); ; this.restricts_ = paramRestricts)
        {
          this.bitField0_ = (0x100 | this.bitField0_);
          return this;
        }
      }

      public Builder setAudio(GogglesProtos.Audio.Builder paramBuilder)
      {
        this.audio_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setAudio(GogglesProtos.Audio paramAudio)
      {
        if (paramAudio == null)
          throw new NullPointerException();
        this.audio_ = paramAudio;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setCaptureType(GogglesProtos.GogglesRequest.CaptureType paramCaptureType)
      {
        if (paramCaptureType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x80 | this.bitField0_);
        this.captureType_ = paramCaptureType;
        return this;
      }

      public Builder setFlow(FlowProtos.FlowData.Builder paramBuilder)
      {
        this.flow_ = paramBuilder.build();
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      public Builder setFlow(FlowProtos.FlowData paramFlowData)
      {
        if (paramFlowData == null)
          throw new NullPointerException();
        this.flow_ = paramFlowData;
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      public Builder setGogglesConfig(GogglesConfigProtos.GogglesConfig.Builder paramBuilder)
      {
        this.gogglesConfig_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setGogglesConfig(GogglesConfigProtos.GogglesConfig paramGogglesConfig)
      {
        if (paramGogglesConfig == null)
          throw new NullPointerException();
        this.gogglesConfig_ = paramGogglesConfig;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setImage(GogglesProtos.Image.Builder paramBuilder)
      {
        this.image_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setImage(GogglesProtos.Image paramImage)
      {
        if (paramImage == null)
          throw new NullPointerException();
        this.image_ = paramImage;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setPose(PoseProtos.Pose.Builder paramBuilder)
      {
        this.pose_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setPose(PoseProtos.Pose paramPose)
      {
        if (paramPose == null)
          throw new NullPointerException();
        this.pose_ = paramPose;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setRestricts(RestrictsProtos.Restricts.Builder paramBuilder)
      {
        this.restricts_ = paramBuilder.build();
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      public Builder setRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (paramRestricts == null)
          throw new NullPointerException();
        this.restricts_ = paramRestricts;
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      public Builder setSource(GogglesProtos.GogglesRequest.Source paramSource)
      {
        if (paramSource == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.source_ = paramSource;
        return this;
      }

      public Builder setText(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x10 | this.bitField0_);
        this.text_ = paramString;
        return this;
      }

      void setText(ByteString paramByteString)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.text_ = paramByteString;
      }
    }

    public static enum CaptureType
      implements Internal.EnumLite
    {
      public static final int CONTINUOUS_VALUE = 1;
      public static final int SINGLE_VALUE;
      private static Internal.EnumLiteMap<CaptureType> internalValueMap = new Internal.EnumLiteMap()
      {
        public GogglesProtos.GogglesRequest.CaptureType findValueByNumber(int paramAnonymousInt)
        {
          return GogglesProtos.GogglesRequest.CaptureType.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        CONTINUOUS = new CaptureType("CONTINUOUS", 1, 1, 1);
        CaptureType[] arrayOfCaptureType = new CaptureType[2];
        arrayOfCaptureType[0] = SINGLE;
        arrayOfCaptureType[1] = CONTINUOUS;
      }

      private CaptureType(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<CaptureType> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static CaptureType valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return SINGLE;
        case 1:
        }
        return CONTINUOUS;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }

    public static enum Source
      implements Internal.EnumLite
    {
      public static final int BACKGROUND_VALUE = 4;
      public static final int FELIX_SEARCH_VALUE = 8;
      public static final int FELIX_TEXT_VALUE = 9;
      public static final int LIVE_VALUE = 1;
      public static final int PUGGLE_VALUE = 5;
      public static final int QUIMBY_VALUE = 6;
      public static final int SAVED_FOR_LATER_VALUE = 3;
      public static final int SHARED_VALUE = 2;
      public static final int SUPERHERO_VALUE = 10;
      public static final int TRANSLATE_VALUE = 7;
      public static final int UNKNOWN_VALUE;
      private static Internal.EnumLiteMap<Source> internalValueMap = new Internal.EnumLiteMap()
      {
        public GogglesProtos.GogglesRequest.Source findValueByNumber(int paramAnonymousInt)
        {
          return GogglesProtos.GogglesRequest.Source.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        LIVE = new Source("LIVE", 1, 1, 1);
        SHARED = new Source("SHARED", 2, 2, 2);
        SAVED_FOR_LATER = new Source("SAVED_FOR_LATER", 3, 3, 3);
        BACKGROUND = new Source("BACKGROUND", 4, 4, 4);
        PUGGLE = new Source("PUGGLE", 5, 5, 5);
        QUIMBY = new Source("QUIMBY", 6, 6, 6);
        TRANSLATE = new Source("TRANSLATE", 7, 7, 7);
        FELIX_SEARCH = new Source("FELIX_SEARCH", 8, 8, 8);
        FELIX_TEXT = new Source("FELIX_TEXT", 9, 9, 9);
        SUPERHERO = new Source("SUPERHERO", 10, 10, 10);
        Source[] arrayOfSource = new Source[11];
        arrayOfSource[0] = UNKNOWN;
        arrayOfSource[1] = LIVE;
        arrayOfSource[2] = SHARED;
        arrayOfSource[3] = SAVED_FOR_LATER;
        arrayOfSource[4] = BACKGROUND;
        arrayOfSource[5] = PUGGLE;
        arrayOfSource[6] = QUIMBY;
        arrayOfSource[7] = TRANSLATE;
        arrayOfSource[8] = FELIX_SEARCH;
        arrayOfSource[9] = FELIX_TEXT;
        arrayOfSource[10] = SUPERHERO;
      }

      private Source(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Source> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Source valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return UNKNOWN;
        case 1:
          return LIVE;
        case 2:
          return SHARED;
        case 3:
          return SAVED_FOR_LATER;
        case 4:
          return BACKGROUND;
        case 5:
          return PUGGLE;
        case 6:
          return QUIMBY;
        case 7:
          return TRANSLATE;
        case 8:
          return FELIX_SEARCH;
        case 9:
          return FELIX_TEXT;
        case 10:
        }
        return SUPERHERO;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface GogglesRequestOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<GogglesProtos.GogglesRequest>
  {
    public abstract GogglesProtos.Audio getAudio();

    public abstract GogglesProtos.GogglesRequest.CaptureType getCaptureType();

    public abstract FlowProtos.FlowData getFlow();

    public abstract GogglesConfigProtos.GogglesConfig getGogglesConfig();

    public abstract GogglesProtos.Image getImage();

    public abstract PoseProtos.Pose getPose();

    public abstract RestrictsProtos.Restricts getRestricts();

    public abstract GogglesProtos.GogglesRequest.Source getSource();

    public abstract String getText();

    public abstract boolean hasAudio();

    public abstract boolean hasCaptureType();

    public abstract boolean hasFlow();

    public abstract boolean hasGogglesConfig();

    public abstract boolean hasImage();

    public abstract boolean hasPose();

    public abstract boolean hasRestricts();

    public abstract boolean hasSource();

    public abstract boolean hasText();
  }

  public static final class GogglesResponse extends GeneratedMessageLite.ExtendableMessage<GogglesResponse>
    implements GogglesProtos.GogglesResponseOrBuilder
  {
    public static final int CORRECTED_POSE_FIELD_NUMBER = 3;
    public static final int PUGGLE_DEBUGGING_FIELD_NUMBER = 4;
    public static final int RESULTS_FIELD_NUMBER = 1;
    public static final int SUGGESTED_RESTRICTS_FIELD_NUMBER = 5;
    private static final GogglesResponse defaultInstance = new GogglesResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private PoseProtos.Pose correctedPose_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private PuggleDebuggingProtos.PuggleDebugging puggleDebugging_;
    private List<ResultProtos.Result> results_;
    private RestrictsProtos.Restricts suggestedRestricts_;

    static
    {
      defaultInstance.initFields();
    }

    private GogglesResponse(Builder paramBuilder)
    {
      super();
    }

    private GogglesResponse(boolean paramBoolean)
    {
    }

    public static GogglesResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.results_ = Collections.emptyList();
      this.correctedPose_ = PoseProtos.Pose.getDefaultInstance();
      this.puggleDebugging_ = PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance();
      this.suggestedRestricts_ = RestrictsProtos.Restricts.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$5300();
    }

    public static Builder newBuilder(GogglesResponse paramGogglesResponse)
    {
      return newBuilder().mergeFrom(paramGogglesResponse);
    }

    public static GogglesResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GogglesResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GogglesResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GogglesResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GogglesResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GogglesResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public PoseProtos.Pose getCorrectedPose()
    {
      return this.correctedPose_;
    }

    public GogglesResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public PuggleDebuggingProtos.PuggleDebugging getPuggleDebugging()
    {
      return this.puggleDebugging_;
    }

    public ResultProtos.Result getResults(int paramInt)
    {
      return (ResultProtos.Result)this.results_.get(paramInt);
    }

    public int getResultsCount()
    {
      return this.results_.size();
    }

    public List<ResultProtos.Result> getResultsList()
    {
      return this.results_;
    }

    public ResultProtos.ResultOrBuilder getResultsOrBuilder(int paramInt)
    {
      return (ResultProtos.ResultOrBuilder)this.results_.get(paramInt);
    }

    public List<? extends ResultProtos.ResultOrBuilder> getResultsOrBuilderList()
    {
      return this.results_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.results_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.results_.get(j));
        j++;
      }
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(3, this.correctedPose_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(4, this.puggleDebugging_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(5, this.suggestedRestricts_);
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public RestrictsProtos.Restricts getSuggestedRestricts()
    {
      return this.suggestedRestricts_;
    }

    public boolean hasCorrectedPose()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasPuggleDebugging()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasSuggestedRestricts()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = 1;
      int j = this.memoizedIsInitialized;
      if (j != -1)
      {
        if (j == i);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      for (int k = 0; k < getResultsCount(); k++)
        if (!getResults(k).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasCorrectedPose()) && (!getCorrectedPose().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = i;
      return i;
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
      GeneratedMessageLite.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      for (int i = 0; i < this.results_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.results_.get(i));
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(3, this.correctedPose_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(4, this.puggleDebugging_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(5, this.suggestedRestricts_);
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<GogglesProtos.GogglesResponse, Builder>
      implements GogglesProtos.GogglesResponseOrBuilder
    {
      private int bitField0_;
      private PoseProtos.Pose correctedPose_ = PoseProtos.Pose.getDefaultInstance();
      private PuggleDebuggingProtos.PuggleDebugging puggleDebugging_ = PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance();
      private List<ResultProtos.Result> results_ = Collections.emptyList();
      private RestrictsProtos.Restricts suggestedRestricts_ = RestrictsProtos.Restricts.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.GogglesResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.GogglesResponse localGogglesResponse = buildPartial();
        if (!localGogglesResponse.isInitialized())
          throw newUninitializedMessageException(localGogglesResponse).asInvalidProtocolBufferException();
        return localGogglesResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureResultsIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.results_ = new ArrayList(this.results_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllResults(Iterable<? extends ResultProtos.Result> paramIterable)
      {
        ensureResultsIsMutable();
        GeneratedMessageLite.ExtendableBuilder.addAll(paramIterable, this.results_);
        return this;
      }

      public Builder addResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensureResultsIsMutable();
        this.results_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureResultsIsMutable();
        this.results_.add(paramInt, paramResult);
        return this;
      }

      public Builder addResults(ResultProtos.Result.Builder paramBuilder)
      {
        ensureResultsIsMutable();
        this.results_.add(paramBuilder.build());
        return this;
      }

      public Builder addResults(ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureResultsIsMutable();
        this.results_.add(paramResult);
        return this;
      }

      public GogglesProtos.GogglesResponse build()
      {
        GogglesProtos.GogglesResponse localGogglesResponse = buildPartial();
        if (!localGogglesResponse.isInitialized())
          throw newUninitializedMessageException(localGogglesResponse);
        return localGogglesResponse;
      }

      public GogglesProtos.GogglesResponse buildPartial()
      {
        int i = 1;
        GogglesProtos.GogglesResponse localGogglesResponse = new GogglesProtos.GogglesResponse(this, null);
        int j = this.bitField0_;
        if ((0x1 & this.bitField0_) == i)
        {
          this.results_ = Collections.unmodifiableList(this.results_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        GogglesProtos.GogglesResponse.access$5502(localGogglesResponse, this.results_);
        if ((j & 0x2) == 2);
        while (true)
        {
          GogglesProtos.GogglesResponse.access$5602(localGogglesResponse, this.correctedPose_);
          if ((j & 0x4) == 4)
            i |= 2;
          GogglesProtos.GogglesResponse.access$5702(localGogglesResponse, this.puggleDebugging_);
          if ((j & 0x8) == 8)
            i |= 4;
          GogglesProtos.GogglesResponse.access$5802(localGogglesResponse, this.suggestedRestricts_);
          GogglesProtos.GogglesResponse.access$5902(localGogglesResponse, i);
          return localGogglesResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.results_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.correctedPose_ = PoseProtos.Pose.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.puggleDebugging_ = PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.suggestedRestricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearCorrectedPose()
      {
        this.correctedPose_ = PoseProtos.Pose.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearPuggleDebugging()
      {
        this.puggleDebugging_ = PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearResults()
      {
        this.results_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearSuggestedRestricts()
      {
        this.suggestedRestricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public PoseProtos.Pose getCorrectedPose()
      {
        return this.correctedPose_;
      }

      public GogglesProtos.GogglesResponse getDefaultInstanceForType()
      {
        return GogglesProtos.GogglesResponse.getDefaultInstance();
      }

      public PuggleDebuggingProtos.PuggleDebugging getPuggleDebugging()
      {
        return this.puggleDebugging_;
      }

      public ResultProtos.Result getResults(int paramInt)
      {
        return (ResultProtos.Result)this.results_.get(paramInt);
      }

      public int getResultsCount()
      {
        return this.results_.size();
      }

      public List<ResultProtos.Result> getResultsList()
      {
        return Collections.unmodifiableList(this.results_);
      }

      public RestrictsProtos.Restricts getSuggestedRestricts()
      {
        return this.suggestedRestricts_;
      }

      public boolean hasCorrectedPose()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasPuggleDebugging()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasSuggestedRestricts()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        int i = 0;
        if (i < getResultsCount())
          if (getResults(i).isInitialized());
        while (((hasCorrectedPose()) && (!getCorrectedPose().isInitialized())) || (!extensionsAreInitialized()))
        {
          return false;
          i++;
          break;
        }
        return true;
      }

      public Builder mergeCorrectedPose(PoseProtos.Pose paramPose)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.correctedPose_ != PoseProtos.Pose.getDefaultInstance()));
        for (this.correctedPose_ = PoseProtos.Pose.newBuilder(this.correctedPose_).mergeFrom(paramPose).buildPartial(); ; this.correctedPose_ = paramPose)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (paramGogglesResponse == GogglesProtos.GogglesResponse.getDefaultInstance())
          return this;
        if (!paramGogglesResponse.results_.isEmpty())
        {
          if (!this.results_.isEmpty())
            break label107;
          this.results_ = paramGogglesResponse.results_;
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        while (true)
        {
          if (paramGogglesResponse.hasCorrectedPose())
            mergeCorrectedPose(paramGogglesResponse.getCorrectedPose());
          if (paramGogglesResponse.hasPuggleDebugging())
            mergePuggleDebugging(paramGogglesResponse.getPuggleDebugging());
          if (paramGogglesResponse.hasSuggestedRestricts())
            mergeSuggestedRestricts(paramGogglesResponse.getSuggestedRestricts());
          mergeExtensionFields(paramGogglesResponse);
          return this;
          label107: ensureResultsIsMutable();
          this.results_.addAll(paramGogglesResponse.results_);
        }
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
            ResultProtos.Result.Builder localBuilder3 = ResultProtos.Result.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addResults(localBuilder3.buildPartial());
            break;
          case 26:
            PoseProtos.Pose.Builder localBuilder2 = PoseProtos.Pose.newBuilder();
            if (hasCorrectedPose())
              localBuilder2.mergeFrom(getCorrectedPose());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setCorrectedPose(localBuilder2.buildPartial());
            break;
          case 34:
            PuggleDebuggingProtos.PuggleDebugging.Builder localBuilder1 = PuggleDebuggingProtos.PuggleDebugging.newBuilder();
            if (hasPuggleDebugging())
              localBuilder1.mergeFrom(getPuggleDebugging());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setPuggleDebugging(localBuilder1.buildPartial());
            break;
          case 42:
          }
          RestrictsProtos.Restricts.Builder localBuilder = RestrictsProtos.Restricts.newBuilder();
          if (hasSuggestedRestricts())
            localBuilder.mergeFrom(getSuggestedRestricts());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setSuggestedRestricts(localBuilder.buildPartial());
        }
      }

      public Builder mergePuggleDebugging(PuggleDebuggingProtos.PuggleDebugging paramPuggleDebugging)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.puggleDebugging_ != PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance()));
        for (this.puggleDebugging_ = PuggleDebuggingProtos.PuggleDebugging.newBuilder(this.puggleDebugging_).mergeFrom(paramPuggleDebugging).buildPartial(); ; this.puggleDebugging_ = paramPuggleDebugging)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeSuggestedRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.suggestedRestricts_ != RestrictsProtos.Restricts.getDefaultInstance()));
        for (this.suggestedRestricts_ = RestrictsProtos.Restricts.newBuilder(this.suggestedRestricts_).mergeFrom(paramRestricts).buildPartial(); ; this.suggestedRestricts_ = paramRestricts)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder removeResults(int paramInt)
      {
        ensureResultsIsMutable();
        this.results_.remove(paramInt);
        return this;
      }

      public Builder setCorrectedPose(PoseProtos.Pose.Builder paramBuilder)
      {
        this.correctedPose_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setCorrectedPose(PoseProtos.Pose paramPose)
      {
        if (paramPose == null)
          throw new NullPointerException();
        this.correctedPose_ = paramPose;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setPuggleDebugging(PuggleDebuggingProtos.PuggleDebugging.Builder paramBuilder)
      {
        this.puggleDebugging_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setPuggleDebugging(PuggleDebuggingProtos.PuggleDebugging paramPuggleDebugging)
      {
        if (paramPuggleDebugging == null)
          throw new NullPointerException();
        this.puggleDebugging_ = paramPuggleDebugging;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensureResultsIsMutable();
        this.results_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureResultsIsMutable();
        this.results_.set(paramInt, paramResult);
        return this;
      }

      public Builder setSuggestedRestricts(RestrictsProtos.Restricts.Builder paramBuilder)
      {
        this.suggestedRestricts_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setSuggestedRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (paramRestricts == null)
          throw new NullPointerException();
        this.suggestedRestricts_ = paramRestricts;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface GogglesResponseOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<GogglesProtos.GogglesResponse>
  {
    public abstract PoseProtos.Pose getCorrectedPose();

    public abstract PuggleDebuggingProtos.PuggleDebugging getPuggleDebugging();

    public abstract ResultProtos.Result getResults(int paramInt);

    public abstract int getResultsCount();

    public abstract List<ResultProtos.Result> getResultsList();

    public abstract RestrictsProtos.Restricts getSuggestedRestricts();

    public abstract boolean hasCorrectedPose();

    public abstract boolean hasPuggleDebugging();

    public abstract boolean hasSuggestedRestricts();
  }

  public static final class Image extends GeneratedMessageLite.ExtendableMessage<Image>
    implements GogglesProtos.ImageOrBuilder
  {
    public static final int DOCID_FIELD_NUMBER = 4;
    public static final int IMAGE_DATA_FIELD_NUMBER = 1;
    public static final int IMAGE_URL_FIELD_NUMBER = 2;
    public static final int ROI_FIELD_NUMBER = 3;
    private static final Image defaultInstance = new Image(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object docid_;
    private GogglesProtos.ImageData imageData_;
    private Object imageUrl_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private BoundingBoxProtos.BoundingBox roi_;

    static
    {
      defaultInstance.initFields();
    }

    private Image(Builder paramBuilder)
    {
      super();
    }

    private Image(boolean paramBoolean)
    {
    }

    public static Image getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getDocidBytes()
    {
      Object localObject = this.docid_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.docid_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getImageUrlBytes()
    {
      Object localObject = this.imageUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.imageUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.imageData_ = GogglesProtos.ImageData.getDefaultInstance();
      this.imageUrl_ = "";
      this.roi_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      this.docid_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$2000();
    }

    public static Builder newBuilder(Image paramImage)
    {
      return newBuilder().mergeFrom(paramImage);
    }

    public static Image parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Image parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Image parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Image parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Image parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Image parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Image parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Image parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Image parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Image parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Image getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getDocid()
    {
      Object localObject = this.docid_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.docid_ = str;
      return str;
    }

    public GogglesProtos.ImageData getImageData()
    {
      return this.imageData_;
    }

    public String getImageUrl()
    {
      Object localObject = this.imageUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.imageUrl_ = str;
      return str;
    }

    public BoundingBoxProtos.BoundingBox getRoi()
    {
      return this.roi_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.imageData_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getImageUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(3, this.roi_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBytesSize(4, getDocidBytes());
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public boolean hasDocid()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasImageData()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasImageUrl()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasRoi()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = 1;
      int j = this.memoizedIsInitialized;
      if (j != -1)
      {
        if (j == i);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      if ((hasImageData()) && (!getImageData().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasRoi()) && (!getRoi().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!extensionsAreInitialized())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = i;
      return i;
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
      GeneratedMessageLite.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(1, this.imageData_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getImageUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(3, this.roi_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(4, getDocidBytes());
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<GogglesProtos.Image, Builder>
      implements GogglesProtos.ImageOrBuilder
    {
      private int bitField0_;
      private Object docid_ = "";
      private GogglesProtos.ImageData imageData_ = GogglesProtos.ImageData.getDefaultInstance();
      private Object imageUrl_ = "";
      private BoundingBoxProtos.BoundingBox roi_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.Image buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.Image localImage = buildPartial();
        if (!localImage.isInitialized())
          throw newUninitializedMessageException(localImage).asInvalidProtocolBufferException();
        return localImage;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.Image build()
      {
        GogglesProtos.Image localImage = buildPartial();
        if (!localImage.isInitialized())
          throw newUninitializedMessageException(localImage);
        return localImage;
      }

      public GogglesProtos.Image buildPartial()
      {
        int i = 1;
        GogglesProtos.Image localImage = new GogglesProtos.Image(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.Image.access$2202(localImage, this.imageData_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.Image.access$2302(localImage, this.imageUrl_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesProtos.Image.access$2402(localImage, this.roi_);
          if ((j & 0x8) == 8)
            i |= 8;
          GogglesProtos.Image.access$2502(localImage, this.docid_);
          GogglesProtos.Image.access$2602(localImage, i);
          return localImage;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.imageData_ = GogglesProtos.ImageData.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.imageUrl_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.roi_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.docid_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearDocid()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.docid_ = GogglesProtos.Image.getDefaultInstance().getDocid();
        return this;
      }

      public Builder clearImageData()
      {
        this.imageData_ = GogglesProtos.ImageData.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearImageUrl()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.imageUrl_ = GogglesProtos.Image.getDefaultInstance().getImageUrl();
        return this;
      }

      public Builder clearRoi()
      {
        this.roi_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesProtos.Image getDefaultInstanceForType()
      {
        return GogglesProtos.Image.getDefaultInstance();
      }

      public String getDocid()
      {
        Object localObject = this.docid_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.docid_ = str;
          return str;
        }
        return (String)localObject;
      }

      public GogglesProtos.ImageData getImageData()
      {
        return this.imageData_;
      }

      public String getImageUrl()
      {
        Object localObject = this.imageUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.imageUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public BoundingBoxProtos.BoundingBox getRoi()
      {
        return this.roi_;
      }

      public boolean hasDocid()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasImageData()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasImageUrl()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasRoi()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        if ((hasImageData()) && (!getImageData().isInitialized()));
        while (((hasRoi()) && (!getRoi().isInitialized())) || (!extensionsAreInitialized()))
          return false;
        return true;
      }

      public Builder mergeFrom(GogglesProtos.Image paramImage)
      {
        if (paramImage == GogglesProtos.Image.getDefaultInstance())
          return this;
        if (paramImage.hasImageData())
          mergeImageData(paramImage.getImageData());
        if (paramImage.hasImageUrl())
          setImageUrl(paramImage.getImageUrl());
        if (paramImage.hasRoi())
          mergeRoi(paramImage.getRoi());
        if (paramImage.hasDocid())
          setDocid(paramImage.getDocid());
        mergeExtensionFields(paramImage);
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
            GogglesProtos.ImageData.Builder localBuilder1 = GogglesProtos.ImageData.newBuilder();
            if (hasImageData())
              localBuilder1.mergeFrom(getImageData());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setImageData(localBuilder1.buildPartial());
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.imageUrl_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            BoundingBoxProtos.BoundingBox.Builder localBuilder = BoundingBoxProtos.BoundingBox.newBuilder();
            if (hasRoi())
              localBuilder.mergeFrom(getRoi());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setRoi(localBuilder.buildPartial());
            break;
          case 34:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.docid_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder mergeImageData(GogglesProtos.ImageData paramImageData)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.imageData_ != GogglesProtos.ImageData.getDefaultInstance()));
        for (this.imageData_ = GogglesProtos.ImageData.newBuilder(this.imageData_).mergeFrom(paramImageData).buildPartial(); ; this.imageData_ = paramImageData)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeRoi(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.roi_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
        for (this.roi_ = BoundingBoxProtos.BoundingBox.newBuilder(this.roi_).mergeFrom(paramBoundingBox).buildPartial(); ; this.roi_ = paramBoundingBox)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder setDocid(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.docid_ = paramString;
        return this;
      }

      void setDocid(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.docid_ = paramByteString;
      }

      public Builder setImageData(GogglesProtos.ImageData.Builder paramBuilder)
      {
        this.imageData_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setImageData(GogglesProtos.ImageData paramImageData)
      {
        if (paramImageData == null)
          throw new NullPointerException();
        this.imageData_ = paramImageData;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setImageUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.imageUrl_ = paramString;
        return this;
      }

      void setImageUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.imageUrl_ = paramByteString;
      }

      public Builder setRoi(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
      {
        this.roi_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setRoi(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == null)
          throw new NullPointerException();
        this.roi_ = paramBoundingBox;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }
    }
  }

  public static final class ImageData extends GeneratedMessageLite
    implements GogglesProtos.ImageDataOrBuilder
  {
    public static final int CROP_REGION_FIELD_NUMBER = 9;
    public static final int ENCODED_IMAGE_FIELD_NUMBER = 1;
    public static final int HEIGHT_FIELD_NUMBER = 8;
    public static final int IMAGE_ENCODING_FIELD_NUMBER = 2;
    public static final int IMAGE_OFFSET_FROM_RIGHT_SIDE_UP_FIELD_NUMBER = 4;
    public static final int OFFSET_ALREADY_APPLIED_FIELD_NUMBER = 6;
    public static final int QUALITY_FIELD_NUMBER = 3;
    public static final int WIDTH_FIELD_NUMBER = 7;
    private static final ImageData defaultInstance = new ImageData(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private GogglesProtos.CropRegion cropRegion_;
    private ByteString encodedImage_;
    private int height_;
    private ImageEncoding imageEncoding_;
    private CWOffsetFromRightSideUp imageOffsetFromRightSideUp_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private boolean offsetAlreadyApplied_;
    private int quality_;
    private int width_;

    static
    {
      defaultInstance.initFields();
    }

    private ImageData(Builder paramBuilder)
    {
      super();
    }

    private ImageData(boolean paramBoolean)
    {
    }

    public static ImageData getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.imageEncoding_ = ImageEncoding.JPEG;
      this.quality_ = 0;
      this.encodedImage_ = ByteString.EMPTY;
      this.width_ = 0;
      this.height_ = 0;
      this.imageOffsetFromRightSideUp_ = CWOffsetFromRightSideUp.ZERO_DEGREES;
      this.offsetAlreadyApplied_ = false;
      this.cropRegion_ = GogglesProtos.CropRegion.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$800();
    }

    public static Builder newBuilder(ImageData paramImageData)
    {
      return newBuilder().mergeFrom(paramImageData);
    }

    public static ImageData parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ImageData parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ImageData parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ImageData parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ImageData parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ImageData parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ImageData parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ImageData parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ImageData parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ImageData parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GogglesProtos.CropRegion getCropRegion()
    {
      return this.cropRegion_;
    }

    public ImageData getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public ByteString getEncodedImage()
    {
      return this.encodedImage_;
    }

    public int getHeight()
    {
      return this.height_;
    }

    public ImageEncoding getImageEncoding()
    {
      return this.imageEncoding_;
    }

    @Deprecated
    public CWOffsetFromRightSideUp getImageOffsetFromRightSideUp()
    {
      return this.imageOffsetFromRightSideUp_;
    }

    @Deprecated
    public boolean getOffsetAlreadyApplied()
    {
      return this.offsetAlreadyApplied_;
    }

    public int getQuality()
    {
      return this.quality_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x4 & this.bitField0_;
      int k = 0;
      if (j == 4)
        k = 0 + CodedOutputStream.computeBytesSize(1, this.encodedImage_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeEnumSize(2, this.imageEncoding_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeInt32Size(3, this.quality_);
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeEnumSize(4, this.imageOffsetFromRightSideUp_.getNumber());
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeBoolSize(6, this.offsetAlreadyApplied_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeInt32Size(7, this.width_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeInt32Size(8, this.height_);
      if ((0x80 & this.bitField0_) == 128)
        k += CodedOutputStream.computeMessageSize(9, this.cropRegion_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getWidth()
    {
      return this.width_;
    }

    public boolean hasCropRegion()
    {
      return (0x80 & this.bitField0_) == 128;
    }

    public boolean hasEncodedImage()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasHeight()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasImageEncoding()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasImageOffsetFromRightSideUp()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    @Deprecated
    public boolean hasOffsetAlreadyApplied()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasQuality()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasWidth()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasCropRegion()) && (!getCropRegion().isInitialized()))
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
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(1, this.encodedImage_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeEnum(2, this.imageEncoding_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt32(3, this.quality_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeEnum(4, this.imageOffsetFromRightSideUp_.getNumber());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBool(6, this.offsetAlreadyApplied_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(7, this.width_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeInt32(8, this.height_);
      if ((0x80 & this.bitField0_) == 128)
        paramCodedOutputStream.writeMessage(9, this.cropRegion_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesProtos.ImageData, Builder>
      implements GogglesProtos.ImageDataOrBuilder
    {
      private int bitField0_;
      private GogglesProtos.CropRegion cropRegion_ = GogglesProtos.CropRegion.getDefaultInstance();
      private ByteString encodedImage_ = ByteString.EMPTY;
      private int height_;
      private GogglesProtos.ImageData.ImageEncoding imageEncoding_ = GogglesProtos.ImageData.ImageEncoding.JPEG;
      private GogglesProtos.ImageData.CWOffsetFromRightSideUp imageOffsetFromRightSideUp_ = GogglesProtos.ImageData.CWOffsetFromRightSideUp.ZERO_DEGREES;
      private boolean offsetAlreadyApplied_;
      private int quality_;
      private int width_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.ImageData buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.ImageData localImageData = buildPartial();
        if (!localImageData.isInitialized())
          throw newUninitializedMessageException(localImageData).asInvalidProtocolBufferException();
        return localImageData;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.ImageData build()
      {
        GogglesProtos.ImageData localImageData = buildPartial();
        if (!localImageData.isInitialized())
          throw newUninitializedMessageException(localImageData);
        return localImageData;
      }

      public GogglesProtos.ImageData buildPartial()
      {
        int i = 1;
        GogglesProtos.ImageData localImageData = new GogglesProtos.ImageData(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.ImageData.access$1002(localImageData, this.imageEncoding_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.ImageData.access$1102(localImageData, this.quality_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesProtos.ImageData.access$1202(localImageData, this.encodedImage_);
          if ((j & 0x8) == 8)
            i |= 8;
          GogglesProtos.ImageData.access$1302(localImageData, this.width_);
          if ((j & 0x10) == 16)
            i |= 16;
          GogglesProtos.ImageData.access$1402(localImageData, this.height_);
          if ((j & 0x20) == 32)
            i |= 32;
          GogglesProtos.ImageData.access$1502(localImageData, this.imageOffsetFromRightSideUp_);
          if ((j & 0x40) == 64)
            i |= 64;
          GogglesProtos.ImageData.access$1602(localImageData, this.offsetAlreadyApplied_);
          if ((j & 0x80) == 128)
            i |= 128;
          GogglesProtos.ImageData.access$1702(localImageData, this.cropRegion_);
          GogglesProtos.ImageData.access$1802(localImageData, i);
          return localImageData;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.imageEncoding_ = GogglesProtos.ImageData.ImageEncoding.JPEG;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.quality_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.encodedImage_ = ByteString.EMPTY;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.width_ = 0;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.height_ = 0;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.imageOffsetFromRightSideUp_ = GogglesProtos.ImageData.CWOffsetFromRightSideUp.ZERO_DEGREES;
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.offsetAlreadyApplied_ = false;
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.cropRegion_ = GogglesProtos.CropRegion.getDefaultInstance();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        return this;
      }

      public Builder clearCropRegion()
      {
        this.cropRegion_ = GogglesProtos.CropRegion.getDefaultInstance();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        return this;
      }

      public Builder clearEncodedImage()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.encodedImage_ = GogglesProtos.ImageData.getDefaultInstance().getEncodedImage();
        return this;
      }

      public Builder clearHeight()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.height_ = 0;
        return this;
      }

      public Builder clearImageEncoding()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.imageEncoding_ = GogglesProtos.ImageData.ImageEncoding.JPEG;
        return this;
      }

      @Deprecated
      public Builder clearImageOffsetFromRightSideUp()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.imageOffsetFromRightSideUp_ = GogglesProtos.ImageData.CWOffsetFromRightSideUp.ZERO_DEGREES;
        return this;
      }

      @Deprecated
      public Builder clearOffsetAlreadyApplied()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.offsetAlreadyApplied_ = false;
        return this;
      }

      public Builder clearQuality()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.quality_ = 0;
        return this;
      }

      public Builder clearWidth()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.width_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesProtos.CropRegion getCropRegion()
      {
        return this.cropRegion_;
      }

      public GogglesProtos.ImageData getDefaultInstanceForType()
      {
        return GogglesProtos.ImageData.getDefaultInstance();
      }

      public ByteString getEncodedImage()
      {
        return this.encodedImage_;
      }

      public int getHeight()
      {
        return this.height_;
      }

      public GogglesProtos.ImageData.ImageEncoding getImageEncoding()
      {
        return this.imageEncoding_;
      }

      @Deprecated
      public GogglesProtos.ImageData.CWOffsetFromRightSideUp getImageOffsetFromRightSideUp()
      {
        return this.imageOffsetFromRightSideUp_;
      }

      @Deprecated
      public boolean getOffsetAlreadyApplied()
      {
        return this.offsetAlreadyApplied_;
      }

      public int getQuality()
      {
        return this.quality_;
      }

      public int getWidth()
      {
        return this.width_;
      }

      public boolean hasCropRegion()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasEncodedImage()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasHeight()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasImageEncoding()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasImageOffsetFromRightSideUp()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      @Deprecated
      public boolean hasOffsetAlreadyApplied()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasQuality()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasWidth()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        return (!hasCropRegion()) || (getCropRegion().isInitialized());
      }

      public Builder mergeCropRegion(GogglesProtos.CropRegion paramCropRegion)
      {
        if (((0x80 & this.bitField0_) == 128) && (this.cropRegion_ != GogglesProtos.CropRegion.getDefaultInstance()));
        for (this.cropRegion_ = GogglesProtos.CropRegion.newBuilder(this.cropRegion_).mergeFrom(paramCropRegion).buildPartial(); ; this.cropRegion_ = paramCropRegion)
        {
          this.bitField0_ = (0x80 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(GogglesProtos.ImageData paramImageData)
      {
        if (paramImageData == GogglesProtos.ImageData.getDefaultInstance());
        do
        {
          return this;
          if (paramImageData.hasImageEncoding())
            setImageEncoding(paramImageData.getImageEncoding());
          if (paramImageData.hasQuality())
            setQuality(paramImageData.getQuality());
          if (paramImageData.hasEncodedImage())
            setEncodedImage(paramImageData.getEncodedImage());
          if (paramImageData.hasWidth())
            setWidth(paramImageData.getWidth());
          if (paramImageData.hasHeight())
            setHeight(paramImageData.getHeight());
          if (paramImageData.hasImageOffsetFromRightSideUp())
            setImageOffsetFromRightSideUp(paramImageData.getImageOffsetFromRightSideUp());
          if (paramImageData.hasOffsetAlreadyApplied())
            setOffsetAlreadyApplied(paramImageData.getOffsetAlreadyApplied());
        }
        while (!paramImageData.hasCropRegion());
        mergeCropRegion(paramImageData.getCropRegion());
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
            this.bitField0_ = (0x4 | this.bitField0_);
            this.encodedImage_ = paramCodedInputStream.readBytes();
            break;
          case 16:
            GogglesProtos.ImageData.ImageEncoding localImageEncoding = GogglesProtos.ImageData.ImageEncoding.valueOf(paramCodedInputStream.readEnum());
            if (localImageEncoding == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.imageEncoding_ = localImageEncoding;
            break;
          case 24:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.quality_ = paramCodedInputStream.readInt32();
            break;
          case 32:
            GogglesProtos.ImageData.CWOffsetFromRightSideUp localCWOffsetFromRightSideUp = GogglesProtos.ImageData.CWOffsetFromRightSideUp.valueOf(paramCodedInputStream.readEnum());
            if (localCWOffsetFromRightSideUp == null)
              continue;
            this.bitField0_ = (0x20 | this.bitField0_);
            this.imageOffsetFromRightSideUp_ = localCWOffsetFromRightSideUp;
            break;
          case 48:
            this.bitField0_ = (0x40 | this.bitField0_);
            this.offsetAlreadyApplied_ = paramCodedInputStream.readBool();
            break;
          case 56:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.width_ = paramCodedInputStream.readInt32();
            break;
          case 64:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.height_ = paramCodedInputStream.readInt32();
            break;
          case 74:
          }
          GogglesProtos.CropRegion.Builder localBuilder = GogglesProtos.CropRegion.newBuilder();
          if (hasCropRegion())
            localBuilder.mergeFrom(getCropRegion());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setCropRegion(localBuilder.buildPartial());
        }
      }

      public Builder setCropRegion(GogglesProtos.CropRegion.Builder paramBuilder)
      {
        this.cropRegion_ = paramBuilder.build();
        this.bitField0_ = (0x80 | this.bitField0_);
        return this;
      }

      public Builder setCropRegion(GogglesProtos.CropRegion paramCropRegion)
      {
        if (paramCropRegion == null)
          throw new NullPointerException();
        this.cropRegion_ = paramCropRegion;
        this.bitField0_ = (0x80 | this.bitField0_);
        return this;
      }

      public Builder setEncodedImage(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.encodedImage_ = paramByteString;
        return this;
      }

      public Builder setHeight(int paramInt)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.height_ = paramInt;
        return this;
      }

      public Builder setImageEncoding(GogglesProtos.ImageData.ImageEncoding paramImageEncoding)
      {
        if (paramImageEncoding == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.imageEncoding_ = paramImageEncoding;
        return this;
      }

      @Deprecated
      public Builder setImageOffsetFromRightSideUp(GogglesProtos.ImageData.CWOffsetFromRightSideUp paramCWOffsetFromRightSideUp)
      {
        if (paramCWOffsetFromRightSideUp == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.imageOffsetFromRightSideUp_ = paramCWOffsetFromRightSideUp;
        return this;
      }

      @Deprecated
      public Builder setOffsetAlreadyApplied(boolean paramBoolean)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.offsetAlreadyApplied_ = paramBoolean;
        return this;
      }

      public Builder setQuality(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.quality_ = paramInt;
        return this;
      }

      public Builder setWidth(int paramInt)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.width_ = paramInt;
        return this;
      }
    }

    public static enum CWOffsetFromRightSideUp
      implements Internal.EnumLite
    {
      public static final int NINETY_DEGREES_VALUE = 2;
      public static final int ONE_EIGHTY_DEGREES_VALUE = 3;
      public static final int TWO_SEVENTY_DEGREES_VALUE = 4;
      public static final int ZERO_DEGREES_VALUE = 1;
      private static Internal.EnumLiteMap<CWOffsetFromRightSideUp> internalValueMap = new Internal.EnumLiteMap()
      {
        public GogglesProtos.ImageData.CWOffsetFromRightSideUp findValueByNumber(int paramAnonymousInt)
        {
          return GogglesProtos.ImageData.CWOffsetFromRightSideUp.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        NINETY_DEGREES = new CWOffsetFromRightSideUp("NINETY_DEGREES", 1, 1, 2);
        ONE_EIGHTY_DEGREES = new CWOffsetFromRightSideUp("ONE_EIGHTY_DEGREES", 2, 2, 3);
        TWO_SEVENTY_DEGREES = new CWOffsetFromRightSideUp("TWO_SEVENTY_DEGREES", 3, 3, 4);
        CWOffsetFromRightSideUp[] arrayOfCWOffsetFromRightSideUp = new CWOffsetFromRightSideUp[4];
        arrayOfCWOffsetFromRightSideUp[0] = ZERO_DEGREES;
        arrayOfCWOffsetFromRightSideUp[1] = NINETY_DEGREES;
        arrayOfCWOffsetFromRightSideUp[2] = ONE_EIGHTY_DEGREES;
        arrayOfCWOffsetFromRightSideUp[3] = TWO_SEVENTY_DEGREES;
      }

      private CWOffsetFromRightSideUp(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<CWOffsetFromRightSideUp> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static CWOffsetFromRightSideUp valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return ZERO_DEGREES;
        case 2:
          return NINETY_DEGREES;
        case 3:
          return ONE_EIGHTY_DEGREES;
        case 4:
        }
        return TWO_SEVENTY_DEGREES;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }

    public static enum ImageEncoding
      implements Internal.EnumLite
    {
      public static final int JPEG_VALUE = 1;
      private static Internal.EnumLiteMap<ImageEncoding> internalValueMap = new Internal.EnumLiteMap()
      {
        public GogglesProtos.ImageData.ImageEncoding findValueByNumber(int paramAnonymousInt)
        {
          return GogglesProtos.ImageData.ImageEncoding.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        ImageEncoding[] arrayOfImageEncoding = new ImageEncoding[1];
        arrayOfImageEncoding[0] = JPEG;
      }

      private ImageEncoding(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<ImageEncoding> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static ImageEncoding valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
        }
        return JPEG;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface ImageDataOrBuilder extends MessageLiteOrBuilder
  {
    public abstract GogglesProtos.CropRegion getCropRegion();

    public abstract ByteString getEncodedImage();

    public abstract int getHeight();

    public abstract GogglesProtos.ImageData.ImageEncoding getImageEncoding();

    @Deprecated
    public abstract GogglesProtos.ImageData.CWOffsetFromRightSideUp getImageOffsetFromRightSideUp();

    @Deprecated
    public abstract boolean getOffsetAlreadyApplied();

    public abstract int getQuality();

    public abstract int getWidth();

    public abstract boolean hasCropRegion();

    public abstract boolean hasEncodedImage();

    public abstract boolean hasHeight();

    public abstract boolean hasImageEncoding();

    @Deprecated
    public abstract boolean hasImageOffsetFromRightSideUp();

    @Deprecated
    public abstract boolean hasOffsetAlreadyApplied();

    public abstract boolean hasQuality();

    public abstract boolean hasWidth();
  }

  public static abstract interface ImageOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<GogglesProtos.Image>
  {
    public abstract String getDocid();

    public abstract GogglesProtos.ImageData getImageData();

    public abstract String getImageUrl();

    public abstract BoundingBoxProtos.BoundingBox getRoi();

    public abstract boolean hasDocid();

    public abstract boolean hasImageData();

    public abstract boolean hasImageUrl();

    public abstract boolean hasRoi();
  }

  public static final class ImageRotation extends GeneratedMessageLite
    implements GogglesProtos.ImageRotationOrBuilder
  {
    public static final int IMAGE_OFFSET_FROM_RIGHT_SIDE_UP_FIELD_NUMBER = 2;
    public static final int IMAGE_ROTATION_FIELD_NUMBER = 20054927;
    public static final int OFFSET_ALREADY_APPLIED_FIELD_NUMBER = 1;
    private static final ImageRotation defaultInstance = new ImageRotation(true);
    public static final GeneratedMessageLite.GeneratedExtension<GogglesProtos.Image, ImageRotation> imageRotation = GeneratedMessageLite.newSingularGeneratedExtension(GogglesProtos.Image.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 20054927, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private int bitField0_;
    private CWOffsetFromRightSideUp imageOffsetFromRightSideUp_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private boolean offsetAlreadyApplied_;

    static
    {
      defaultInstance.initFields();
    }

    private ImageRotation(Builder paramBuilder)
    {
      super();
    }

    private ImageRotation(boolean paramBoolean)
    {
    }

    public static ImageRotation getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.offsetAlreadyApplied_ = false;
      this.imageOffsetFromRightSideUp_ = CWOffsetFromRightSideUp.ZERO_DEGREES;
    }

    public static Builder newBuilder()
    {
      return Builder.access$2800();
    }

    public static Builder newBuilder(ImageRotation paramImageRotation)
    {
      return newBuilder().mergeFrom(paramImageRotation);
    }

    public static ImageRotation parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ImageRotation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ImageRotation parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ImageRotation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ImageRotation parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ImageRotation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ImageRotation parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ImageRotation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ImageRotation parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ImageRotation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ImageRotation getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public CWOffsetFromRightSideUp getImageOffsetFromRightSideUp()
    {
      return this.imageOffsetFromRightSideUp_;
    }

    public boolean getOffsetAlreadyApplied()
    {
      return this.offsetAlreadyApplied_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBoolSize(1, this.offsetAlreadyApplied_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeEnumSize(2, this.imageOffsetFromRightSideUp_.getNumber());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasImageOffsetFromRightSideUp()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasOffsetAlreadyApplied()
    {
      return (0x1 & this.bitField0_) == 1;
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
        paramCodedOutputStream.writeBool(1, this.offsetAlreadyApplied_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeEnum(2, this.imageOffsetFromRightSideUp_.getNumber());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesProtos.ImageRotation, Builder>
      implements GogglesProtos.ImageRotationOrBuilder
    {
      private int bitField0_;
      private GogglesProtos.ImageRotation.CWOffsetFromRightSideUp imageOffsetFromRightSideUp_ = GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ZERO_DEGREES;
      private boolean offsetAlreadyApplied_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesProtos.ImageRotation buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesProtos.ImageRotation localImageRotation = buildPartial();
        if (!localImageRotation.isInitialized())
          throw newUninitializedMessageException(localImageRotation).asInvalidProtocolBufferException();
        return localImageRotation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesProtos.ImageRotation build()
      {
        GogglesProtos.ImageRotation localImageRotation = buildPartial();
        if (!localImageRotation.isInitialized())
          throw newUninitializedMessageException(localImageRotation);
        return localImageRotation;
      }

      public GogglesProtos.ImageRotation buildPartial()
      {
        int i = 1;
        GogglesProtos.ImageRotation localImageRotation = new GogglesProtos.ImageRotation(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesProtos.ImageRotation.access$3002(localImageRotation, this.offsetAlreadyApplied_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesProtos.ImageRotation.access$3102(localImageRotation, this.imageOffsetFromRightSideUp_);
          GogglesProtos.ImageRotation.access$3202(localImageRotation, i);
          return localImageRotation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.offsetAlreadyApplied_ = false;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.imageOffsetFromRightSideUp_ = GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ZERO_DEGREES;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearImageOffsetFromRightSideUp()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.imageOffsetFromRightSideUp_ = GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.ZERO_DEGREES;
        return this;
      }

      public Builder clearOffsetAlreadyApplied()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.offsetAlreadyApplied_ = false;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesProtos.ImageRotation getDefaultInstanceForType()
      {
        return GogglesProtos.ImageRotation.getDefaultInstance();
      }

      public GogglesProtos.ImageRotation.CWOffsetFromRightSideUp getImageOffsetFromRightSideUp()
      {
        return this.imageOffsetFromRightSideUp_;
      }

      public boolean getOffsetAlreadyApplied()
      {
        return this.offsetAlreadyApplied_;
      }

      public boolean hasImageOffsetFromRightSideUp()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasOffsetAlreadyApplied()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(GogglesProtos.ImageRotation paramImageRotation)
      {
        if (paramImageRotation == GogglesProtos.ImageRotation.getDefaultInstance());
        do
        {
          return this;
          if (paramImageRotation.hasOffsetAlreadyApplied())
            setOffsetAlreadyApplied(paramImageRotation.getOffsetAlreadyApplied());
        }
        while (!paramImageRotation.hasImageOffsetFromRightSideUp());
        setImageOffsetFromRightSideUp(paramImageRotation.getImageOffsetFromRightSideUp());
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
          case 8:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.offsetAlreadyApplied_ = paramCodedInputStream.readBool();
            break;
          case 16:
          }
          GogglesProtos.ImageRotation.CWOffsetFromRightSideUp localCWOffsetFromRightSideUp = GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.valueOf(paramCodedInputStream.readEnum());
          if (localCWOffsetFromRightSideUp != null)
          {
            this.bitField0_ = (0x2 | this.bitField0_);
            this.imageOffsetFromRightSideUp_ = localCWOffsetFromRightSideUp;
          }
        }
      }

      public Builder setImageOffsetFromRightSideUp(GogglesProtos.ImageRotation.CWOffsetFromRightSideUp paramCWOffsetFromRightSideUp)
      {
        if (paramCWOffsetFromRightSideUp == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.imageOffsetFromRightSideUp_ = paramCWOffsetFromRightSideUp;
        return this;
      }

      public Builder setOffsetAlreadyApplied(boolean paramBoolean)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.offsetAlreadyApplied_ = paramBoolean;
        return this;
      }
    }

    public static enum CWOffsetFromRightSideUp
      implements Internal.EnumLite
    {
      public static final int NINETY_DEGREES_VALUE = 2;
      public static final int ONE_EIGHTY_DEGREES_VALUE = 3;
      public static final int TWO_SEVENTY_DEGREES_VALUE = 4;
      public static final int ZERO_DEGREES_VALUE = 1;
      private static Internal.EnumLiteMap<CWOffsetFromRightSideUp> internalValueMap = new Internal.EnumLiteMap()
      {
        public GogglesProtos.ImageRotation.CWOffsetFromRightSideUp findValueByNumber(int paramAnonymousInt)
        {
          return GogglesProtos.ImageRotation.CWOffsetFromRightSideUp.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        NINETY_DEGREES = new CWOffsetFromRightSideUp("NINETY_DEGREES", 1, 1, 2);
        ONE_EIGHTY_DEGREES = new CWOffsetFromRightSideUp("ONE_EIGHTY_DEGREES", 2, 2, 3);
        TWO_SEVENTY_DEGREES = new CWOffsetFromRightSideUp("TWO_SEVENTY_DEGREES", 3, 3, 4);
        CWOffsetFromRightSideUp[] arrayOfCWOffsetFromRightSideUp = new CWOffsetFromRightSideUp[4];
        arrayOfCWOffsetFromRightSideUp[0] = ZERO_DEGREES;
        arrayOfCWOffsetFromRightSideUp[1] = NINETY_DEGREES;
        arrayOfCWOffsetFromRightSideUp[2] = ONE_EIGHTY_DEGREES;
        arrayOfCWOffsetFromRightSideUp[3] = TWO_SEVENTY_DEGREES;
      }

      private CWOffsetFromRightSideUp(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<CWOffsetFromRightSideUp> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static CWOffsetFromRightSideUp valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return ZERO_DEGREES;
        case 2:
          return NINETY_DEGREES;
        case 3:
          return ONE_EIGHTY_DEGREES;
        case 4:
        }
        return TWO_SEVENTY_DEGREES;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface ImageRotationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract GogglesProtos.ImageRotation.CWOffsetFromRightSideUp getImageOffsetFromRightSideUp();

    public abstract boolean getOffsetAlreadyApplied();

    public abstract boolean hasImageOffsetFromRightSideUp();

    public abstract boolean hasOffsetAlreadyApplied();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.GogglesProtos
 * JD-Core Version:    0.6.2
 */