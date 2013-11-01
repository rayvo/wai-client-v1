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

public final class HistoryConfigProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class GetHistoryConfigRequest extends GeneratedMessageLite
    implements HistoryConfigProtos.GetHistoryConfigRequestOrBuilder
  {
    private static final GetHistoryConfigRequest defaultInstance = new GetHistoryConfigRequest(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private GetHistoryConfigRequest(Builder paramBuilder)
    {
      super();
    }

    private GetHistoryConfigRequest(boolean paramBoolean)
    {
    }

    public static GetHistoryConfigRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$600();
    }

    public static Builder newBuilder(GetHistoryConfigRequest paramGetHistoryConfigRequest)
    {
      return newBuilder().mergeFrom(paramGetHistoryConfigRequest);
    }

    public static GetHistoryConfigRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GetHistoryConfigRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GetHistoryConfigRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GetHistoryConfigRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GetHistoryConfigRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      this.memoizedSerializedSize = 0;
      return 0;
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
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryConfigProtos.GetHistoryConfigRequest, Builder>
      implements HistoryConfigProtos.GetHistoryConfigRequestOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HistoryConfigProtos.GetHistoryConfigRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        HistoryConfigProtos.GetHistoryConfigRequest localGetHistoryConfigRequest = buildPartial();
        if (!localGetHistoryConfigRequest.isInitialized())
          throw newUninitializedMessageException(localGetHistoryConfigRequest).asInvalidProtocolBufferException();
        return localGetHistoryConfigRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HistoryConfigProtos.GetHistoryConfigRequest build()
      {
        HistoryConfigProtos.GetHistoryConfigRequest localGetHistoryConfigRequest = buildPartial();
        if (!localGetHistoryConfigRequest.isInitialized())
          throw newUninitializedMessageException(localGetHistoryConfigRequest);
        return localGetHistoryConfigRequest;
      }

      public HistoryConfigProtos.GetHistoryConfigRequest buildPartial()
      {
        return new HistoryConfigProtos.GetHistoryConfigRequest(this, null);
      }

      public Builder clear()
      {
        super.clear();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HistoryConfigProtos.GetHistoryConfigRequest getDefaultInstanceForType()
      {
        return HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(HistoryConfigProtos.GetHistoryConfigRequest paramGetHistoryConfigRequest)
      {
        if (paramGetHistoryConfigRequest == HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        int i;
        do
        {
          i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
          case 0:
          }
        }
        while (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i));
        return this;
      }
    }
  }

  public static abstract interface GetHistoryConfigRequestOrBuilder extends MessageLiteOrBuilder
  {
  }

  public static final class GetHistoryConfigResponse extends GeneratedMessageLite
    implements HistoryConfigProtos.GetHistoryConfigResponseOrBuilder
  {
    public static final int HISTORY_CONFIG_FIELD_NUMBER = 1;
    private static final GetHistoryConfigResponse defaultInstance = new GetHistoryConfigResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private HistoryConfigProtos.HistoryConfig historyConfig_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private GetHistoryConfigResponse(Builder paramBuilder)
    {
      super();
    }

    private GetHistoryConfigResponse(boolean paramBoolean)
    {
    }

    public static GetHistoryConfigResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$900();
    }

    public static Builder newBuilder(GetHistoryConfigResponse paramGetHistoryConfigResponse)
    {
      return newBuilder().mergeFrom(paramGetHistoryConfigResponse);
    }

    public static GetHistoryConfigResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GetHistoryConfigResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GetHistoryConfigResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GetHistoryConfigResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public GetHistoryConfigResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public HistoryConfigProtos.HistoryConfig getHistoryConfig()
    {
      return this.historyConfig_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.historyConfig_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasHistoryConfig()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasHistoryConfig())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!getHistoryConfig().isInitialized())
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
        paramCodedOutputStream.writeMessage(1, this.historyConfig_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryConfigProtos.GetHistoryConfigResponse, Builder>
      implements HistoryConfigProtos.GetHistoryConfigResponseOrBuilder
    {
      private int bitField0_;
      private HistoryConfigProtos.HistoryConfig historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HistoryConfigProtos.GetHistoryConfigResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        HistoryConfigProtos.GetHistoryConfigResponse localGetHistoryConfigResponse = buildPartial();
        if (!localGetHistoryConfigResponse.isInitialized())
          throw newUninitializedMessageException(localGetHistoryConfigResponse).asInvalidProtocolBufferException();
        return localGetHistoryConfigResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HistoryConfigProtos.GetHistoryConfigResponse build()
      {
        HistoryConfigProtos.GetHistoryConfigResponse localGetHistoryConfigResponse = buildPartial();
        if (!localGetHistoryConfigResponse.isInitialized())
          throw newUninitializedMessageException(localGetHistoryConfigResponse);
        return localGetHistoryConfigResponse;
      }

      public HistoryConfigProtos.GetHistoryConfigResponse buildPartial()
      {
        int i = 1;
        HistoryConfigProtos.GetHistoryConfigResponse localGetHistoryConfigResponse = new HistoryConfigProtos.GetHistoryConfigResponse(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          HistoryConfigProtos.GetHistoryConfigResponse.access$1102(localGetHistoryConfigResponse, this.historyConfig_);
          HistoryConfigProtos.GetHistoryConfigResponse.access$1202(localGetHistoryConfigResponse, i);
          return localGetHistoryConfigResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearHistoryConfig()
      {
        this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HistoryConfigProtos.GetHistoryConfigResponse getDefaultInstanceForType()
      {
        return HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance();
      }

      public HistoryConfigProtos.HistoryConfig getHistoryConfig()
      {
        return this.historyConfig_;
      }

      public boolean hasHistoryConfig()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasHistoryConfig());
        while (!getHistoryConfig().isInitialized())
          return false;
        return true;
      }

      public Builder mergeFrom(HistoryConfigProtos.GetHistoryConfigResponse paramGetHistoryConfigResponse)
      {
        if (paramGetHistoryConfigResponse == HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance());
        while (!paramGetHistoryConfigResponse.hasHistoryConfig())
          return this;
        mergeHistoryConfig(paramGetHistoryConfigResponse.getHistoryConfig());
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
          }
          HistoryConfigProtos.HistoryConfig.Builder localBuilder = HistoryConfigProtos.HistoryConfig.newBuilder();
          if (hasHistoryConfig())
            localBuilder.mergeFrom(getHistoryConfig());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setHistoryConfig(localBuilder.buildPartial());
        }
      }

      public Builder mergeHistoryConfig(HistoryConfigProtos.HistoryConfig paramHistoryConfig)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.historyConfig_ != HistoryConfigProtos.HistoryConfig.getDefaultInstance()));
        for (this.historyConfig_ = HistoryConfigProtos.HistoryConfig.newBuilder(this.historyConfig_).mergeFrom(paramHistoryConfig).buildPartial(); ; this.historyConfig_ = paramHistoryConfig)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder setHistoryConfig(HistoryConfigProtos.HistoryConfig.Builder paramBuilder)
      {
        this.historyConfig_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setHistoryConfig(HistoryConfigProtos.HistoryConfig paramHistoryConfig)
      {
        if (paramHistoryConfig == null)
          throw new NullPointerException();
        this.historyConfig_ = paramHistoryConfig;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface GetHistoryConfigResponseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract HistoryConfigProtos.HistoryConfig getHistoryConfig();

    public abstract boolean hasHistoryConfig();
  }

  public static final class HistoryConfig extends GeneratedMessageLite
    implements HistoryConfigProtos.HistoryConfigOrBuilder
  {
    public static final int RECORDING_FIELD_NUMBER = 1;
    private static final HistoryConfig defaultInstance = new HistoryConfig(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private boolean recording_;

    static
    {
      defaultInstance.initFields();
    }

    private HistoryConfig(Builder paramBuilder)
    {
      super();
    }

    private HistoryConfig(boolean paramBoolean)
    {
    }

    public static HistoryConfig getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.recording_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(HistoryConfig paramHistoryConfig)
    {
      return newBuilder().mergeFrom(paramHistoryConfig);
    }

    public static HistoryConfig parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static HistoryConfig parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static HistoryConfig parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static HistoryConfig parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static HistoryConfig parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static HistoryConfig parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static HistoryConfig parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static HistoryConfig parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static HistoryConfig parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static HistoryConfig parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public HistoryConfig getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public boolean getRecording()
    {
      return this.recording_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBoolSize(1, this.recording_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasRecording()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasRecording())
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
        paramCodedOutputStream.writeBool(1, this.recording_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryConfigProtos.HistoryConfig, Builder>
      implements HistoryConfigProtos.HistoryConfigOrBuilder
    {
      private int bitField0_;
      private boolean recording_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HistoryConfigProtos.HistoryConfig buildParsed()
        throws InvalidProtocolBufferException
      {
        HistoryConfigProtos.HistoryConfig localHistoryConfig = buildPartial();
        if (!localHistoryConfig.isInitialized())
          throw newUninitializedMessageException(localHistoryConfig).asInvalidProtocolBufferException();
        return localHistoryConfig;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HistoryConfigProtos.HistoryConfig build()
      {
        HistoryConfigProtos.HistoryConfig localHistoryConfig = buildPartial();
        if (!localHistoryConfig.isInitialized())
          throw newUninitializedMessageException(localHistoryConfig);
        return localHistoryConfig;
      }

      public HistoryConfigProtos.HistoryConfig buildPartial()
      {
        int i = 1;
        HistoryConfigProtos.HistoryConfig localHistoryConfig = new HistoryConfigProtos.HistoryConfig(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          HistoryConfigProtos.HistoryConfig.access$302(localHistoryConfig, this.recording_);
          HistoryConfigProtos.HistoryConfig.access$402(localHistoryConfig, i);
          return localHistoryConfig;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.recording_ = false;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearRecording()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.recording_ = false;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HistoryConfigProtos.HistoryConfig getDefaultInstanceForType()
      {
        return HistoryConfigProtos.HistoryConfig.getDefaultInstance();
      }

      public boolean getRecording()
      {
        return this.recording_;
      }

      public boolean hasRecording()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return hasRecording();
      }

      public Builder mergeFrom(HistoryConfigProtos.HistoryConfig paramHistoryConfig)
      {
        if (paramHistoryConfig == HistoryConfigProtos.HistoryConfig.getDefaultInstance());
        while (!paramHistoryConfig.hasRecording())
          return this;
        setRecording(paramHistoryConfig.getRecording());
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
          }
          this.bitField0_ = (0x1 | this.bitField0_);
          this.recording_ = paramCodedInputStream.readBool();
        }
      }

      public Builder setRecording(boolean paramBoolean)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.recording_ = paramBoolean;
        return this;
      }
    }
  }

  public static abstract interface HistoryConfigOrBuilder extends MessageLiteOrBuilder
  {
    public abstract boolean getRecording();

    public abstract boolean hasRecording();
  }

  public static final class SetHistoryConfigRequest extends GeneratedMessageLite
    implements HistoryConfigProtos.SetHistoryConfigRequestOrBuilder
  {
    public static final int HISTORY_CONFIG_FIELD_NUMBER = 1;
    private static final SetHistoryConfigRequest defaultInstance = new SetHistoryConfigRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private HistoryConfigProtos.HistoryConfig historyConfig_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private SetHistoryConfigRequest(Builder paramBuilder)
    {
      super();
    }

    private SetHistoryConfigRequest(boolean paramBoolean)
    {
    }

    public static SetHistoryConfigRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$1400();
    }

    public static Builder newBuilder(SetHistoryConfigRequest paramSetHistoryConfigRequest)
    {
      return newBuilder().mergeFrom(paramSetHistoryConfigRequest);
    }

    public static SetHistoryConfigRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static SetHistoryConfigRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static SetHistoryConfigRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static SetHistoryConfigRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public SetHistoryConfigRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public HistoryConfigProtos.HistoryConfig getHistoryConfig()
    {
      return this.historyConfig_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.historyConfig_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasHistoryConfig()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasHistoryConfig())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!getHistoryConfig().isInitialized())
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
        paramCodedOutputStream.writeMessage(1, this.historyConfig_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryConfigProtos.SetHistoryConfigRequest, Builder>
      implements HistoryConfigProtos.SetHistoryConfigRequestOrBuilder
    {
      private int bitField0_;
      private HistoryConfigProtos.HistoryConfig historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HistoryConfigProtos.SetHistoryConfigRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        HistoryConfigProtos.SetHistoryConfigRequest localSetHistoryConfigRequest = buildPartial();
        if (!localSetHistoryConfigRequest.isInitialized())
          throw newUninitializedMessageException(localSetHistoryConfigRequest).asInvalidProtocolBufferException();
        return localSetHistoryConfigRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HistoryConfigProtos.SetHistoryConfigRequest build()
      {
        HistoryConfigProtos.SetHistoryConfigRequest localSetHistoryConfigRequest = buildPartial();
        if (!localSetHistoryConfigRequest.isInitialized())
          throw newUninitializedMessageException(localSetHistoryConfigRequest);
        return localSetHistoryConfigRequest;
      }

      public HistoryConfigProtos.SetHistoryConfigRequest buildPartial()
      {
        int i = 1;
        HistoryConfigProtos.SetHistoryConfigRequest localSetHistoryConfigRequest = new HistoryConfigProtos.SetHistoryConfigRequest(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          HistoryConfigProtos.SetHistoryConfigRequest.access$1602(localSetHistoryConfigRequest, this.historyConfig_);
          HistoryConfigProtos.SetHistoryConfigRequest.access$1702(localSetHistoryConfigRequest, i);
          return localSetHistoryConfigRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearHistoryConfig()
      {
        this.historyConfig_ = HistoryConfigProtos.HistoryConfig.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HistoryConfigProtos.SetHistoryConfigRequest getDefaultInstanceForType()
      {
        return HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance();
      }

      public HistoryConfigProtos.HistoryConfig getHistoryConfig()
      {
        return this.historyConfig_;
      }

      public boolean hasHistoryConfig()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasHistoryConfig());
        while (!getHistoryConfig().isInitialized())
          return false;
        return true;
      }

      public Builder mergeFrom(HistoryConfigProtos.SetHistoryConfigRequest paramSetHistoryConfigRequest)
      {
        if (paramSetHistoryConfigRequest == HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance());
        while (!paramSetHistoryConfigRequest.hasHistoryConfig())
          return this;
        mergeHistoryConfig(paramSetHistoryConfigRequest.getHistoryConfig());
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
          }
          HistoryConfigProtos.HistoryConfig.Builder localBuilder = HistoryConfigProtos.HistoryConfig.newBuilder();
          if (hasHistoryConfig())
            localBuilder.mergeFrom(getHistoryConfig());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setHistoryConfig(localBuilder.buildPartial());
        }
      }

      public Builder mergeHistoryConfig(HistoryConfigProtos.HistoryConfig paramHistoryConfig)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.historyConfig_ != HistoryConfigProtos.HistoryConfig.getDefaultInstance()));
        for (this.historyConfig_ = HistoryConfigProtos.HistoryConfig.newBuilder(this.historyConfig_).mergeFrom(paramHistoryConfig).buildPartial(); ; this.historyConfig_ = paramHistoryConfig)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder setHistoryConfig(HistoryConfigProtos.HistoryConfig.Builder paramBuilder)
      {
        this.historyConfig_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setHistoryConfig(HistoryConfigProtos.HistoryConfig paramHistoryConfig)
      {
        if (paramHistoryConfig == null)
          throw new NullPointerException();
        this.historyConfig_ = paramHistoryConfig;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface SetHistoryConfigRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract HistoryConfigProtos.HistoryConfig getHistoryConfig();

    public abstract boolean hasHistoryConfig();
  }

  public static final class SetHistoryConfigResponse extends GeneratedMessageLite
    implements HistoryConfigProtos.SetHistoryConfigResponseOrBuilder
  {
    private static final SetHistoryConfigResponse defaultInstance = new SetHistoryConfigResponse(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private SetHistoryConfigResponse(Builder paramBuilder)
    {
      super();
    }

    private SetHistoryConfigResponse(boolean paramBoolean)
    {
    }

    public static SetHistoryConfigResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$1900();
    }

    public static Builder newBuilder(SetHistoryConfigResponse paramSetHistoryConfigResponse)
    {
      return newBuilder().mergeFrom(paramSetHistoryConfigResponse);
    }

    public static SetHistoryConfigResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static SetHistoryConfigResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static SetHistoryConfigResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static SetHistoryConfigResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public SetHistoryConfigResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      this.memoizedSerializedSize = 0;
      return 0;
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
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HistoryConfigProtos.SetHistoryConfigResponse, Builder>
      implements HistoryConfigProtos.SetHistoryConfigResponseOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HistoryConfigProtos.SetHistoryConfigResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        HistoryConfigProtos.SetHistoryConfigResponse localSetHistoryConfigResponse = buildPartial();
        if (!localSetHistoryConfigResponse.isInitialized())
          throw newUninitializedMessageException(localSetHistoryConfigResponse).asInvalidProtocolBufferException();
        return localSetHistoryConfigResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HistoryConfigProtos.SetHistoryConfigResponse build()
      {
        HistoryConfigProtos.SetHistoryConfigResponse localSetHistoryConfigResponse = buildPartial();
        if (!localSetHistoryConfigResponse.isInitialized())
          throw newUninitializedMessageException(localSetHistoryConfigResponse);
        return localSetHistoryConfigResponse;
      }

      public HistoryConfigProtos.SetHistoryConfigResponse buildPartial()
      {
        return new HistoryConfigProtos.SetHistoryConfigResponse(this, null);
      }

      public Builder clear()
      {
        super.clear();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HistoryConfigProtos.SetHistoryConfigResponse getDefaultInstanceForType()
      {
        return HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(HistoryConfigProtos.SetHistoryConfigResponse paramSetHistoryConfigResponse)
      {
        if (paramSetHistoryConfigResponse == HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        int i;
        do
        {
          i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
          case 0:
          }
        }
        while (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i));
        return this;
      }
    }
  }

  public static abstract interface SetHistoryConfigResponseOrBuilder extends MessageLiteOrBuilder
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.HistoryConfigProtos
 * JD-Core Version:    0.6.2
 */