package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class GogglesReplayProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class GogglesReplayRequest extends GeneratedMessageLite
    implements GogglesReplayProtos.GogglesReplayRequestOrBuilder
  {
    public static final int GOGGLES_CONFIG_FIELD_NUMBER = 3;
    public static final int MS_SINCE_EPOCH_FIELD_NUMBER = 4;
    public static final int QUERY_ID_FIELD_NUMBER = 1;
    private static final GogglesReplayRequest defaultInstance = new GogglesReplayRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private GogglesConfigProtos.GogglesConfig gogglesConfig_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private long msSinceEpoch_;
    private Object queryId_;

    static
    {
      defaultInstance.initFields();
    }

    private GogglesReplayRequest(Builder paramBuilder)
    {
      super();
    }

    private GogglesReplayRequest(boolean paramBoolean)
    {
    }

    public static GogglesReplayRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getQueryIdBytes()
    {
      Object localObject = this.queryId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.queryId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.queryId_ = "";
      this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
      this.msSinceEpoch_ = 0L;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(GogglesReplayRequest paramGogglesReplayRequest)
    {
      return newBuilder().mergeFrom(paramGogglesReplayRequest);
    }

    public static GogglesReplayRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesReplayRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesReplayRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GogglesReplayRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GogglesReplayRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public GogglesConfigProtos.GogglesConfig getGogglesConfig()
    {
      return this.gogglesConfig_;
    }

    public long getMsSinceEpoch()
    {
      return this.msSinceEpoch_;
    }

    public String getQueryId()
    {
      Object localObject = this.queryId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.queryId_ = str;
      return str;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, getQueryIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(3, this.gogglesConfig_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt64Size(4, this.msSinceEpoch_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasGogglesConfig()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasMsSinceEpoch()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasQueryId()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasGogglesConfig()) && (!getGogglesConfig().isInitialized()))
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
        paramCodedOutputStream.writeBytes(1, getQueryIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(3, this.gogglesConfig_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt64(4, this.msSinceEpoch_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesReplayProtos.GogglesReplayRequest, Builder>
      implements GogglesReplayProtos.GogglesReplayRequestOrBuilder
    {
      private int bitField0_;
      private GogglesConfigProtos.GogglesConfig gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
      private long msSinceEpoch_;
      private Object queryId_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesReplayProtos.GogglesReplayRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesReplayProtos.GogglesReplayRequest localGogglesReplayRequest = buildPartial();
        if (!localGogglesReplayRequest.isInitialized())
          throw newUninitializedMessageException(localGogglesReplayRequest).asInvalidProtocolBufferException();
        return localGogglesReplayRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesReplayProtos.GogglesReplayRequest build()
      {
        GogglesReplayProtos.GogglesReplayRequest localGogglesReplayRequest = buildPartial();
        if (!localGogglesReplayRequest.isInitialized())
          throw newUninitializedMessageException(localGogglesReplayRequest);
        return localGogglesReplayRequest;
      }

      public GogglesReplayProtos.GogglesReplayRequest buildPartial()
      {
        int i = 1;
        GogglesReplayProtos.GogglesReplayRequest localGogglesReplayRequest = new GogglesReplayProtos.GogglesReplayRequest(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesReplayProtos.GogglesReplayRequest.access$302(localGogglesReplayRequest, this.queryId_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesReplayProtos.GogglesReplayRequest.access$402(localGogglesReplayRequest, this.gogglesConfig_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesReplayProtos.GogglesReplayRequest.access$502(localGogglesReplayRequest, this.msSinceEpoch_);
          GogglesReplayProtos.GogglesReplayRequest.access$602(localGogglesReplayRequest, i);
          return localGogglesReplayRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.queryId_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.msSinceEpoch_ = 0L;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearGogglesConfig()
      {
        this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearMsSinceEpoch()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.msSinceEpoch_ = 0L;
        return this;
      }

      public Builder clearQueryId()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.queryId_ = GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance().getQueryId();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesReplayProtos.GogglesReplayRequest getDefaultInstanceForType()
      {
        return GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance();
      }

      public GogglesConfigProtos.GogglesConfig getGogglesConfig()
      {
        return this.gogglesConfig_;
      }

      public long getMsSinceEpoch()
      {
        return this.msSinceEpoch_;
      }

      public String getQueryId()
      {
        Object localObject = this.queryId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.queryId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasGogglesConfig()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasMsSinceEpoch()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasQueryId()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return (!hasGogglesConfig()) || (getGogglesConfig().isInitialized());
      }

      public Builder mergeFrom(GogglesReplayProtos.GogglesReplayRequest paramGogglesReplayRequest)
      {
        if (paramGogglesReplayRequest == GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance());
        do
        {
          return this;
          if (paramGogglesReplayRequest.hasQueryId())
            setQueryId(paramGogglesReplayRequest.getQueryId());
          if (paramGogglesReplayRequest.hasGogglesConfig())
            mergeGogglesConfig(paramGogglesReplayRequest.getGogglesConfig());
        }
        while (!paramGogglesReplayRequest.hasMsSinceEpoch());
        setMsSinceEpoch(paramGogglesReplayRequest.getMsSinceEpoch());
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
            this.queryId_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            GogglesConfigProtos.GogglesConfig.Builder localBuilder = GogglesConfigProtos.GogglesConfig.newBuilder();
            if (hasGogglesConfig())
              localBuilder.mergeFrom(getGogglesConfig());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setGogglesConfig(localBuilder.buildPartial());
            break;
          case 32:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.msSinceEpoch_ = paramCodedInputStream.readInt64();
        }
      }

      public Builder mergeGogglesConfig(GogglesConfigProtos.GogglesConfig paramGogglesConfig)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.gogglesConfig_ != GogglesConfigProtos.GogglesConfig.getDefaultInstance()));
        for (this.gogglesConfig_ = GogglesConfigProtos.GogglesConfig.newBuilder(this.gogglesConfig_).mergeFrom(paramGogglesConfig).buildPartial(); ; this.gogglesConfig_ = paramGogglesConfig)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder setGogglesConfig(GogglesConfigProtos.GogglesConfig.Builder paramBuilder)
      {
        this.gogglesConfig_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setGogglesConfig(GogglesConfigProtos.GogglesConfig paramGogglesConfig)
      {
        if (paramGogglesConfig == null)
          throw new NullPointerException();
        this.gogglesConfig_ = paramGogglesConfig;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setMsSinceEpoch(long paramLong)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.msSinceEpoch_ = paramLong;
        return this;
      }

      public Builder setQueryId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.queryId_ = paramString;
        return this;
      }

      void setQueryId(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.queryId_ = paramByteString;
      }
    }
  }

  public static abstract interface GogglesReplayRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract GogglesConfigProtos.GogglesConfig getGogglesConfig();

    public abstract long getMsSinceEpoch();

    public abstract String getQueryId();

    public abstract boolean hasGogglesConfig();

    public abstract boolean hasMsSinceEpoch();

    public abstract boolean hasQueryId();
  }

  public static final class GogglesReplayResponse extends GeneratedMessageLite
    implements GogglesReplayProtos.GogglesReplayResponseOrBuilder
  {
    public static final int GOGGLES_RESPONSE_FIELD_NUMBER = 1;
    public static final int QUERY_IMAGE_FIELD_NUMBER = 2;
    public static final int QUERY_IMAGE_THUMBNAIL_FIELD_NUMBER = 3;
    public static final int USER_CONTRIBUTION_METADATA_FIELD_NUMBER = 4;
    private static final GogglesReplayResponse defaultInstance = new GogglesReplayResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private GogglesProtos.GogglesResponse gogglesResponse_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private GogglesProtos.Image queryImageThumbnail_;
    private GogglesProtos.Image queryImage_;
    private UserContributionMetadata userContributionMetadata_;

    static
    {
      defaultInstance.initFields();
    }

    private GogglesReplayResponse(Builder paramBuilder)
    {
      super();
    }

    private GogglesReplayResponse(boolean paramBoolean)
    {
    }

    public static GogglesReplayResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.gogglesResponse_ = GogglesProtos.GogglesResponse.getDefaultInstance();
      this.queryImage_ = GogglesProtos.Image.getDefaultInstance();
      this.queryImageThumbnail_ = GogglesProtos.Image.getDefaultInstance();
      this.userContributionMetadata_ = UserContributionMetadata.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$1900();
    }

    public static Builder newBuilder(GogglesReplayResponse paramGogglesReplayResponse)
    {
      return newBuilder().mergeFrom(paramGogglesReplayResponse);
    }

    public static GogglesReplayResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesReplayResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesReplayResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GogglesReplayResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GogglesReplayResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public GogglesProtos.GogglesResponse getGogglesResponse()
    {
      return this.gogglesResponse_;
    }

    public GogglesProtos.Image getQueryImage()
    {
      return this.queryImage_;
    }

    @Deprecated
    public GogglesProtos.Image getQueryImageThumbnail()
    {
      return this.queryImageThumbnail_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.gogglesResponse_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(2, this.queryImage_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(3, this.queryImageThumbnail_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(4, this.userContributionMetadata_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public UserContributionMetadata getUserContributionMetadata()
    {
      return this.userContributionMetadata_;
    }

    public boolean hasGogglesResponse()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasQueryImage()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    @Deprecated
    public boolean hasQueryImageThumbnail()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasUserContributionMetadata()
    {
      return (0x8 & this.bitField0_) == 8;
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
      if ((hasGogglesResponse()) && (!getGogglesResponse().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasQueryImage()) && (!getQueryImage().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasQueryImageThumbnail()) && (!getQueryImageThumbnail().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasUserContributionMetadata()) && (!getUserContributionMetadata().isInitialized()))
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
        paramCodedOutputStream.writeMessage(1, this.gogglesResponse_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(2, this.queryImage_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(3, this.queryImageThumbnail_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(4, this.userContributionMetadata_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<GogglesReplayProtos.GogglesReplayResponse, Builder>
      implements GogglesReplayProtos.GogglesReplayResponseOrBuilder
    {
      private int bitField0_;
      private GogglesProtos.GogglesResponse gogglesResponse_ = GogglesProtos.GogglesResponse.getDefaultInstance();
      private GogglesProtos.Image queryImageThumbnail_ = GogglesProtos.Image.getDefaultInstance();
      private GogglesProtos.Image queryImage_ = GogglesProtos.Image.getDefaultInstance();
      private GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata userContributionMetadata_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesReplayProtos.GogglesReplayResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesReplayProtos.GogglesReplayResponse localGogglesReplayResponse = buildPartial();
        if (!localGogglesReplayResponse.isInitialized())
          throw newUninitializedMessageException(localGogglesReplayResponse).asInvalidProtocolBufferException();
        return localGogglesReplayResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesReplayProtos.GogglesReplayResponse build()
      {
        GogglesReplayProtos.GogglesReplayResponse localGogglesReplayResponse = buildPartial();
        if (!localGogglesReplayResponse.isInitialized())
          throw newUninitializedMessageException(localGogglesReplayResponse);
        return localGogglesReplayResponse;
      }

      public GogglesReplayProtos.GogglesReplayResponse buildPartial()
      {
        int i = 1;
        GogglesReplayProtos.GogglesReplayResponse localGogglesReplayResponse = new GogglesReplayProtos.GogglesReplayResponse(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesReplayProtos.GogglesReplayResponse.access$2102(localGogglesReplayResponse, this.gogglesResponse_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesReplayProtos.GogglesReplayResponse.access$2202(localGogglesReplayResponse, this.queryImage_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesReplayProtos.GogglesReplayResponse.access$2302(localGogglesReplayResponse, this.queryImageThumbnail_);
          if ((j & 0x8) == 8)
            i |= 8;
          GogglesReplayProtos.GogglesReplayResponse.access$2402(localGogglesReplayResponse, this.userContributionMetadata_);
          GogglesReplayProtos.GogglesReplayResponse.access$2502(localGogglesReplayResponse, i);
          return localGogglesReplayResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.gogglesResponse_ = GogglesProtos.GogglesResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.queryImage_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.queryImageThumbnail_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.userContributionMetadata_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearGogglesResponse()
      {
        this.gogglesResponse_ = GogglesProtos.GogglesResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearQueryImage()
      {
        this.queryImage_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearQueryImageThumbnail()
      {
        this.queryImageThumbnail_ = GogglesProtos.Image.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearUserContributionMetadata()
      {
        this.userContributionMetadata_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public GogglesReplayProtos.GogglesReplayResponse getDefaultInstanceForType()
      {
        return GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance();
      }

      public GogglesProtos.GogglesResponse getGogglesResponse()
      {
        return this.gogglesResponse_;
      }

      public GogglesProtos.Image getQueryImage()
      {
        return this.queryImage_;
      }

      @Deprecated
      public GogglesProtos.Image getQueryImageThumbnail()
      {
        return this.queryImageThumbnail_;
      }

      public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata getUserContributionMetadata()
      {
        return this.userContributionMetadata_;
      }

      public boolean hasGogglesResponse()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasQueryImage()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      @Deprecated
      public boolean hasQueryImageThumbnail()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasUserContributionMetadata()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        if ((hasGogglesResponse()) && (!getGogglesResponse().isInitialized()));
        while (((hasQueryImage()) && (!getQueryImage().isInitialized())) || ((hasQueryImageThumbnail()) && (!getQueryImageThumbnail().isInitialized())) || ((hasUserContributionMetadata()) && (!getUserContributionMetadata().isInitialized())))
          return false;
        return true;
      }

      public Builder mergeFrom(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse)
      {
        if (paramGogglesReplayResponse == GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance());
        do
        {
          return this;
          if (paramGogglesReplayResponse.hasGogglesResponse())
            mergeGogglesResponse(paramGogglesReplayResponse.getGogglesResponse());
          if (paramGogglesReplayResponse.hasQueryImage())
            mergeQueryImage(paramGogglesReplayResponse.getQueryImage());
          if (paramGogglesReplayResponse.hasQueryImageThumbnail())
            mergeQueryImageThumbnail(paramGogglesReplayResponse.getQueryImageThumbnail());
        }
        while (!paramGogglesReplayResponse.hasUserContributionMetadata());
        mergeUserContributionMetadata(paramGogglesReplayResponse.getUserContributionMetadata());
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
            GogglesProtos.GogglesResponse.Builder localBuilder3 = GogglesProtos.GogglesResponse.newBuilder();
            if (hasGogglesResponse())
              localBuilder3.mergeFrom(getGogglesResponse());
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            setGogglesResponse(localBuilder3.buildPartial());
            break;
          case 18:
            GogglesProtos.Image.Builder localBuilder2 = GogglesProtos.Image.newBuilder();
            if (hasQueryImage())
              localBuilder2.mergeFrom(getQueryImage());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setQueryImage(localBuilder2.buildPartial());
            break;
          case 26:
            GogglesProtos.Image.Builder localBuilder1 = GogglesProtos.Image.newBuilder();
            if (hasQueryImageThumbnail())
              localBuilder1.mergeFrom(getQueryImageThumbnail());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setQueryImageThumbnail(localBuilder1.buildPartial());
            break;
          case 34:
          }
          GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.Builder localBuilder = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.newBuilder();
          if (hasUserContributionMetadata())
            localBuilder.mergeFrom(getUserContributionMetadata());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setUserContributionMetadata(localBuilder.buildPartial());
        }
      }

      public Builder mergeGogglesResponse(GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.gogglesResponse_ != GogglesProtos.GogglesResponse.getDefaultInstance()));
        for (this.gogglesResponse_ = GogglesProtos.GogglesResponse.newBuilder(this.gogglesResponse_).mergeFrom(paramGogglesResponse).buildPartial(); ; this.gogglesResponse_ = paramGogglesResponse)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeQueryImage(GogglesProtos.Image paramImage)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.queryImage_ != GogglesProtos.Image.getDefaultInstance()));
        for (this.queryImage_ = GogglesProtos.Image.newBuilder(this.queryImage_).mergeFrom(paramImage).buildPartial(); ; this.queryImage_ = paramImage)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeQueryImageThumbnail(GogglesProtos.Image paramImage)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.queryImageThumbnail_ != GogglesProtos.Image.getDefaultInstance()));
        for (this.queryImageThumbnail_ = GogglesProtos.Image.newBuilder(this.queryImageThumbnail_).mergeFrom(paramImage).buildPartial(); ; this.queryImageThumbnail_ = paramImage)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeUserContributionMetadata(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.userContributionMetadata_ != GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance()));
        for (this.userContributionMetadata_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.newBuilder(this.userContributionMetadata_).mergeFrom(paramUserContributionMetadata).buildPartial(); ; this.userContributionMetadata_ = paramUserContributionMetadata)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder setGogglesResponse(GogglesProtos.GogglesResponse.Builder paramBuilder)
      {
        this.gogglesResponse_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setGogglesResponse(GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (paramGogglesResponse == null)
          throw new NullPointerException();
        this.gogglesResponse_ = paramGogglesResponse;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setQueryImage(GogglesProtos.Image.Builder paramBuilder)
      {
        this.queryImage_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setQueryImage(GogglesProtos.Image paramImage)
      {
        if (paramImage == null)
          throw new NullPointerException();
        this.queryImage_ = paramImage;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setQueryImageThumbnail(GogglesProtos.Image.Builder paramBuilder)
      {
        this.queryImageThumbnail_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setQueryImageThumbnail(GogglesProtos.Image paramImage)
      {
        if (paramImage == null)
          throw new NullPointerException();
        this.queryImageThumbnail_ = paramImage;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setUserContributionMetadata(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.Builder paramBuilder)
      {
        this.userContributionMetadata_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setUserContributionMetadata(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata)
      {
        if (paramUserContributionMetadata == null)
          throw new NullPointerException();
        this.userContributionMetadata_ = paramUserContributionMetadata;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }
    }

    public static final class UserContributionMetadata extends GeneratedMessageLite
      implements GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadataOrBuilder
    {
      public static final int CONTRIBUTOR_FIELD_NUMBER = 5;
      public static final int CREATION_TIME_MS_SINCE_EPOCH_FIELD_NUMBER = 7;
      public static final int DESCRIPTION_FIELD_NUMBER = 2;
      public static final int REGION_FIELD_NUMBER = 3;
      public static final int TITLE_FIELD_NUMBER = 1;
      public static final int TYPE_FIELD_NUMBER = 4;
      public static final int WEBSEARCH_URL_FIELD_NUMBER = 6;
      private static final UserContributionMetadata defaultInstance = new UserContributionMetadata(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private AnnotationResultProtos.AnnotationResult.PersonInformation contributor_;
      private long creationTimeMsSinceEpoch_;
      private Object description_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private BoundingBoxProtos.BoundingBox region_;
      private Object title_;
      private Object type_;
      private Object websearchUrl_;

      static
      {
        defaultInstance.initFields();
      }

      private UserContributionMetadata(Builder paramBuilder)
      {
        super();
      }

      private UserContributionMetadata(boolean paramBoolean)
      {
      }

      public static UserContributionMetadata getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getDescriptionBytes()
      {
        Object localObject = this.description_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.description_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getTitleBytes()
      {
        Object localObject = this.title_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.title_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getTypeBytes()
      {
        Object localObject = this.type_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.type_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getWebsearchUrlBytes()
      {
        Object localObject = this.websearchUrl_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.websearchUrl_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.title_ = "";
        this.description_ = "";
        this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.type_ = "";
        this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        this.websearchUrl_ = "";
        this.creationTimeMsSinceEpoch_ = 0L;
      }

      public static Builder newBuilder()
      {
        return Builder.access$800();
      }

      public static Builder newBuilder(UserContributionMetadata paramUserContributionMetadata)
      {
        return newBuilder().mergeFrom(paramUserContributionMetadata);
      }

      public static UserContributionMetadata parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static UserContributionMetadata parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static UserContributionMetadata parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static UserContributionMetadata parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static UserContributionMetadata parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public AnnotationResultProtos.AnnotationResult.PersonInformation getContributor()
      {
        return this.contributor_;
      }

      public long getCreationTimeMsSinceEpoch()
      {
        return this.creationTimeMsSinceEpoch_;
      }

      public UserContributionMetadata getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public String getDescription()
      {
        Object localObject = this.description_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.description_ = str;
        return str;
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
          k = 0 + CodedOutputStream.computeBytesSize(1, getTitleBytes());
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeBytesSize(2, getDescriptionBytes());
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeMessageSize(3, this.region_);
        if ((0x8 & this.bitField0_) == 8)
          k += CodedOutputStream.computeBytesSize(4, getTypeBytes());
        if ((0x10 & this.bitField0_) == 16)
          k += CodedOutputStream.computeMessageSize(5, this.contributor_);
        if ((0x20 & this.bitField0_) == 32)
          k += CodedOutputStream.computeBytesSize(6, getWebsearchUrlBytes());
        if ((0x40 & this.bitField0_) == 64)
          k += CodedOutputStream.computeInt64Size(7, this.creationTimeMsSinceEpoch_);
        this.memoizedSerializedSize = k;
        return k;
      }

      public String getTitle()
      {
        Object localObject = this.title_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.title_ = str;
        return str;
      }

      public String getType()
      {
        Object localObject = this.type_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.type_ = str;
        return str;
      }

      public String getWebsearchUrl()
      {
        Object localObject = this.websearchUrl_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.websearchUrl_ = str;
        return str;
      }

      public boolean hasContributor()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasCreationTimeMsSinceEpoch()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasDescription()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasRegion()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasTitle()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasType()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasWebsearchUrl()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public final boolean isInitialized()
      {
        int i = this.memoizedIsInitialized;
        if (i != -1)
          return i == 1;
        if ((hasRegion()) && (!getRegion().isInitialized()))
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
          paramCodedOutputStream.writeBytes(1, getTitleBytes());
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeBytes(2, getDescriptionBytes());
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeMessage(3, this.region_);
        if ((0x8 & this.bitField0_) == 8)
          paramCodedOutputStream.writeBytes(4, getTypeBytes());
        if ((0x10 & this.bitField0_) == 16)
          paramCodedOutputStream.writeMessage(5, this.contributor_);
        if ((0x20 & this.bitField0_) == 32)
          paramCodedOutputStream.writeBytes(6, getWebsearchUrlBytes());
        if ((0x40 & this.bitField0_) == 64)
          paramCodedOutputStream.writeInt64(7, this.creationTimeMsSinceEpoch_);
      }

      public static final class Builder extends GeneratedMessageLite.Builder<GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata, Builder>
        implements GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadataOrBuilder
      {
        private int bitField0_;
        private AnnotationResultProtos.AnnotationResult.PersonInformation contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        private long creationTimeMsSinceEpoch_;
        private Object description_ = "";
        private BoundingBoxProtos.BoundingBox region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        private Object title_ = "";
        private Object type_ = "";
        private Object websearchUrl_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata buildParsed()
          throws InvalidProtocolBufferException
        {
          GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata localUserContributionMetadata = buildPartial();
          if (!localUserContributionMetadata.isInitialized())
            throw newUninitializedMessageException(localUserContributionMetadata).asInvalidProtocolBufferException();
          return localUserContributionMetadata;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata build()
        {
          GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata localUserContributionMetadata = buildPartial();
          if (!localUserContributionMetadata.isInitialized())
            throw newUninitializedMessageException(localUserContributionMetadata);
          return localUserContributionMetadata;
        }

        public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata buildPartial()
        {
          int i = 1;
          GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata localUserContributionMetadata = new GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1002(localUserContributionMetadata, this.title_);
            if ((j & 0x2) == 2)
              i |= 2;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1102(localUserContributionMetadata, this.description_);
            if ((j & 0x4) == 4)
              i |= 4;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1202(localUserContributionMetadata, this.region_);
            if ((j & 0x8) == 8)
              i |= 8;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1302(localUserContributionMetadata, this.type_);
            if ((j & 0x10) == 16)
              i |= 16;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1402(localUserContributionMetadata, this.contributor_);
            if ((j & 0x20) == 32)
              i |= 32;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1502(localUserContributionMetadata, this.websearchUrl_);
            if ((j & 0x40) == 64)
              i |= 64;
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1602(localUserContributionMetadata, this.creationTimeMsSinceEpoch_);
            GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.access$1702(localUserContributionMetadata, i);
            return localUserContributionMetadata;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.title_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.description_ = "";
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.type_ = "";
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          this.websearchUrl_ = "";
          this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
          this.creationTimeMsSinceEpoch_ = 0L;
          this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
          return this;
        }

        public Builder clearContributor()
        {
          this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          return this;
        }

        public Builder clearCreationTimeMsSinceEpoch()
        {
          this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
          this.creationTimeMsSinceEpoch_ = 0L;
          return this;
        }

        public Builder clearDescription()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.description_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance().getDescription();
          return this;
        }

        public Builder clearRegion()
        {
          this.region_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          return this;
        }

        public Builder clearTitle()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.title_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance().getTitle();
          return this;
        }

        public Builder clearType()
        {
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.type_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance().getType();
          return this;
        }

        public Builder clearWebsearchUrl()
        {
          this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
          this.websearchUrl_ = GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance().getWebsearchUrl();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public AnnotationResultProtos.AnnotationResult.PersonInformation getContributor()
        {
          return this.contributor_;
        }

        public long getCreationTimeMsSinceEpoch()
        {
          return this.creationTimeMsSinceEpoch_;
        }

        public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata getDefaultInstanceForType()
        {
          return GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance();
        }

        public String getDescription()
        {
          Object localObject = this.description_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.description_ = str;
            return str;
          }
          return (String)localObject;
        }

        public BoundingBoxProtos.BoundingBox getRegion()
        {
          return this.region_;
        }

        public String getTitle()
        {
          Object localObject = this.title_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.title_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getType()
        {
          Object localObject = this.type_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.type_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getWebsearchUrl()
        {
          Object localObject = this.websearchUrl_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.websearchUrl_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean hasContributor()
        {
          return (0x10 & this.bitField0_) == 16;
        }

        public boolean hasCreationTimeMsSinceEpoch()
        {
          return (0x40 & this.bitField0_) == 64;
        }

        public boolean hasDescription()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public boolean hasRegion()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public boolean hasTitle()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public boolean hasType()
        {
          return (0x8 & this.bitField0_) == 8;
        }

        public boolean hasWebsearchUrl()
        {
          return (0x20 & this.bitField0_) == 32;
        }

        public final boolean isInitialized()
        {
          return (!hasRegion()) || (getRegion().isInitialized());
        }

        public Builder mergeContributor(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
        {
          if (((0x10 & this.bitField0_) == 16) && (this.contributor_ != AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance()));
          for (this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder(this.contributor_).mergeFrom(paramPersonInformation).buildPartial(); ; this.contributor_ = paramPersonInformation)
          {
            this.bitField0_ = (0x10 | this.bitField0_);
            return this;
          }
        }

        public Builder mergeFrom(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata)
        {
          if (paramUserContributionMetadata == GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata.getDefaultInstance());
          do
          {
            return this;
            if (paramUserContributionMetadata.hasTitle())
              setTitle(paramUserContributionMetadata.getTitle());
            if (paramUserContributionMetadata.hasDescription())
              setDescription(paramUserContributionMetadata.getDescription());
            if (paramUserContributionMetadata.hasRegion())
              mergeRegion(paramUserContributionMetadata.getRegion());
            if (paramUserContributionMetadata.hasType())
              setType(paramUserContributionMetadata.getType());
            if (paramUserContributionMetadata.hasContributor())
              mergeContributor(paramUserContributionMetadata.getContributor());
            if (paramUserContributionMetadata.hasWebsearchUrl())
              setWebsearchUrl(paramUserContributionMetadata.getWebsearchUrl());
          }
          while (!paramUserContributionMetadata.hasCreationTimeMsSinceEpoch());
          setCreationTimeMsSinceEpoch(paramUserContributionMetadata.getCreationTimeMsSinceEpoch());
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
              this.title_ = paramCodedInputStream.readBytes();
              break;
            case 18:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.description_ = paramCodedInputStream.readBytes();
              break;
            case 26:
              BoundingBoxProtos.BoundingBox.Builder localBuilder1 = BoundingBoxProtos.BoundingBox.newBuilder();
              if (hasRegion())
                localBuilder1.mergeFrom(getRegion());
              paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
              setRegion(localBuilder1.buildPartial());
              break;
            case 34:
              this.bitField0_ = (0x8 | this.bitField0_);
              this.type_ = paramCodedInputStream.readBytes();
              break;
            case 42:
              AnnotationResultProtos.AnnotationResult.PersonInformation.Builder localBuilder = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder();
              if (hasContributor())
                localBuilder.mergeFrom(getContributor());
              paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
              setContributor(localBuilder.buildPartial());
              break;
            case 50:
              this.bitField0_ = (0x20 | this.bitField0_);
              this.websearchUrl_ = paramCodedInputStream.readBytes();
              break;
            case 56:
            }
            this.bitField0_ = (0x40 | this.bitField0_);
            this.creationTimeMsSinceEpoch_ = paramCodedInputStream.readInt64();
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

        public Builder setContributor(AnnotationResultProtos.AnnotationResult.PersonInformation.Builder paramBuilder)
        {
          this.contributor_ = paramBuilder.build();
          this.bitField0_ = (0x10 | this.bitField0_);
          return this;
        }

        public Builder setContributor(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
        {
          if (paramPersonInformation == null)
            throw new NullPointerException();
          this.contributor_ = paramPersonInformation;
          this.bitField0_ = (0x10 | this.bitField0_);
          return this;
        }

        public Builder setCreationTimeMsSinceEpoch(long paramLong)
        {
          this.bitField0_ = (0x40 | this.bitField0_);
          this.creationTimeMsSinceEpoch_ = paramLong;
          return this;
        }

        public Builder setDescription(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x2 | this.bitField0_);
          this.description_ = paramString;
          return this;
        }

        void setDescription(ByteString paramByteString)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.description_ = paramByteString;
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

        public Builder setTitle(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.title_ = paramString;
          return this;
        }

        void setTitle(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.title_ = paramByteString;
        }

        public Builder setType(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x8 | this.bitField0_);
          this.type_ = paramString;
          return this;
        }

        void setType(ByteString paramByteString)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          this.type_ = paramByteString;
        }

        public Builder setWebsearchUrl(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x20 | this.bitField0_);
          this.websearchUrl_ = paramString;
          return this;
        }

        void setWebsearchUrl(ByteString paramByteString)
        {
          this.bitField0_ = (0x20 | this.bitField0_);
          this.websearchUrl_ = paramByteString;
        }
      }
    }

    public static abstract interface UserContributionMetadataOrBuilder extends MessageLiteOrBuilder
    {
      public abstract AnnotationResultProtos.AnnotationResult.PersonInformation getContributor();

      public abstract long getCreationTimeMsSinceEpoch();

      public abstract String getDescription();

      public abstract BoundingBoxProtos.BoundingBox getRegion();

      public abstract String getTitle();

      public abstract String getType();

      public abstract String getWebsearchUrl();

      public abstract boolean hasContributor();

      public abstract boolean hasCreationTimeMsSinceEpoch();

      public abstract boolean hasDescription();

      public abstract boolean hasRegion();

      public abstract boolean hasTitle();

      public abstract boolean hasType();

      public abstract boolean hasWebsearchUrl();
    }
  }

  public static abstract interface GogglesReplayResponseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract GogglesProtos.GogglesResponse getGogglesResponse();

    public abstract GogglesProtos.Image getQueryImage();

    @Deprecated
    public abstract GogglesProtos.Image getQueryImageThumbnail();

    public abstract GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata getUserContributionMetadata();

    public abstract boolean hasGogglesResponse();

    public abstract boolean hasQueryImage();

    @Deprecated
    public abstract boolean hasQueryImageThumbnail();

    public abstract boolean hasUserContributionMetadata();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.GogglesReplayProtos
 * JD-Core Version:    0.6.2
 */