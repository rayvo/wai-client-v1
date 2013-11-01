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

public final class AndroidVersionInfoProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class AndroidVersionInfoRequest extends GeneratedMessageLite
    implements AndroidVersionInfoProtos.AndroidVersionInfoRequestOrBuilder
  {
    public static final int VERSION_CODE_FIELD_NUMBER = 1;
    private static final AndroidVersionInfoRequest defaultInstance = new AndroidVersionInfoRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int versionCode_;

    static
    {
      defaultInstance.initFields();
    }

    private AndroidVersionInfoRequest(Builder paramBuilder)
    {
      super();
    }

    private AndroidVersionInfoRequest(boolean paramBoolean)
    {
    }

    public static AndroidVersionInfoRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.versionCode_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(AndroidVersionInfoRequest paramAndroidVersionInfoRequest)
    {
      return newBuilder().mergeFrom(paramAndroidVersionInfoRequest);
    }

    public static AndroidVersionInfoRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static AndroidVersionInfoRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static AndroidVersionInfoRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static AndroidVersionInfoRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public AndroidVersionInfoRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.versionCode_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getVersionCode()
    {
      return this.versionCode_;
    }

    public boolean hasVersionCode()
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
        paramCodedOutputStream.writeInt32(1, this.versionCode_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AndroidVersionInfoProtos.AndroidVersionInfoRequest, Builder>
      implements AndroidVersionInfoProtos.AndroidVersionInfoRequestOrBuilder
    {
      private int bitField0_;
      private int versionCode_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private AndroidVersionInfoProtos.AndroidVersionInfoRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        AndroidVersionInfoProtos.AndroidVersionInfoRequest localAndroidVersionInfoRequest = buildPartial();
        if (!localAndroidVersionInfoRequest.isInitialized())
          throw newUninitializedMessageException(localAndroidVersionInfoRequest).asInvalidProtocolBufferException();
        return localAndroidVersionInfoRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoRequest build()
      {
        AndroidVersionInfoProtos.AndroidVersionInfoRequest localAndroidVersionInfoRequest = buildPartial();
        if (!localAndroidVersionInfoRequest.isInitialized())
          throw newUninitializedMessageException(localAndroidVersionInfoRequest);
        return localAndroidVersionInfoRequest;
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoRequest buildPartial()
      {
        int i = 1;
        AndroidVersionInfoProtos.AndroidVersionInfoRequest localAndroidVersionInfoRequest = new AndroidVersionInfoProtos.AndroidVersionInfoRequest(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          AndroidVersionInfoProtos.AndroidVersionInfoRequest.access$302(localAndroidVersionInfoRequest, this.versionCode_);
          AndroidVersionInfoProtos.AndroidVersionInfoRequest.access$402(localAndroidVersionInfoRequest, i);
          return localAndroidVersionInfoRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.versionCode_ = 0;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearVersionCode()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.versionCode_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoRequest getDefaultInstanceForType()
      {
        return AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance();
      }

      public int getVersionCode()
      {
        return this.versionCode_;
      }

      public boolean hasVersionCode()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(AndroidVersionInfoProtos.AndroidVersionInfoRequest paramAndroidVersionInfoRequest)
      {
        if (paramAndroidVersionInfoRequest == AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance());
        while (!paramAndroidVersionInfoRequest.hasVersionCode())
          return this;
        setVersionCode(paramAndroidVersionInfoRequest.getVersionCode());
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
          this.versionCode_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setVersionCode(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.versionCode_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface AndroidVersionInfoRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getVersionCode();

    public abstract boolean hasVersionCode();
  }

  public static final class AndroidVersionInfoResponse extends GeneratedMessageLite
    implements AndroidVersionInfoProtos.AndroidVersionInfoResponseOrBuilder
  {
    public static final int LATEST_VERSION_CODE_FIELD_NUMBER = 1;
    public static final int SHOULD_UPGRADE_FIELD_NUMBER = 2;
    private static final AndroidVersionInfoResponse defaultInstance = new AndroidVersionInfoResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int latestVersionCode_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private boolean shouldUpgrade_;

    static
    {
      defaultInstance.initFields();
    }

    private AndroidVersionInfoResponse(Builder paramBuilder)
    {
      super();
    }

    private AndroidVersionInfoResponse(boolean paramBoolean)
    {
    }

    public static AndroidVersionInfoResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.latestVersionCode_ = 0;
      this.shouldUpgrade_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$600();
    }

    public static Builder newBuilder(AndroidVersionInfoResponse paramAndroidVersionInfoResponse)
    {
      return newBuilder().mergeFrom(paramAndroidVersionInfoResponse);
    }

    public static AndroidVersionInfoResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static AndroidVersionInfoResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static AndroidVersionInfoResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static AndroidVersionInfoResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public AndroidVersionInfoResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getLatestVersionCode()
    {
      return this.latestVersionCode_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.latestVersionCode_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBoolSize(2, this.shouldUpgrade_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean getShouldUpgrade()
    {
      return this.shouldUpgrade_;
    }

    public boolean hasLatestVersionCode()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasShouldUpgrade()
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
        paramCodedOutputStream.writeInt32(1, this.latestVersionCode_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBool(2, this.shouldUpgrade_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AndroidVersionInfoProtos.AndroidVersionInfoResponse, Builder>
      implements AndroidVersionInfoProtos.AndroidVersionInfoResponseOrBuilder
    {
      private int bitField0_;
      private int latestVersionCode_;
      private boolean shouldUpgrade_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private AndroidVersionInfoProtos.AndroidVersionInfoResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        AndroidVersionInfoProtos.AndroidVersionInfoResponse localAndroidVersionInfoResponse = buildPartial();
        if (!localAndroidVersionInfoResponse.isInitialized())
          throw newUninitializedMessageException(localAndroidVersionInfoResponse).asInvalidProtocolBufferException();
        return localAndroidVersionInfoResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoResponse build()
      {
        AndroidVersionInfoProtos.AndroidVersionInfoResponse localAndroidVersionInfoResponse = buildPartial();
        if (!localAndroidVersionInfoResponse.isInitialized())
          throw newUninitializedMessageException(localAndroidVersionInfoResponse);
        return localAndroidVersionInfoResponse;
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoResponse buildPartial()
      {
        int i = 1;
        AndroidVersionInfoProtos.AndroidVersionInfoResponse localAndroidVersionInfoResponse = new AndroidVersionInfoProtos.AndroidVersionInfoResponse(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          AndroidVersionInfoProtos.AndroidVersionInfoResponse.access$802(localAndroidVersionInfoResponse, this.latestVersionCode_);
          if ((j & 0x2) == 2)
            i |= 2;
          AndroidVersionInfoProtos.AndroidVersionInfoResponse.access$902(localAndroidVersionInfoResponse, this.shouldUpgrade_);
          AndroidVersionInfoProtos.AndroidVersionInfoResponse.access$1002(localAndroidVersionInfoResponse, i);
          return localAndroidVersionInfoResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.latestVersionCode_ = 0;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.shouldUpgrade_ = false;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearLatestVersionCode()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.latestVersionCode_ = 0;
        return this;
      }

      public Builder clearShouldUpgrade()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.shouldUpgrade_ = false;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public AndroidVersionInfoProtos.AndroidVersionInfoResponse getDefaultInstanceForType()
      {
        return AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance();
      }

      public int getLatestVersionCode()
      {
        return this.latestVersionCode_;
      }

      public boolean getShouldUpgrade()
      {
        return this.shouldUpgrade_;
      }

      public boolean hasLatestVersionCode()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasShouldUpgrade()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(AndroidVersionInfoProtos.AndroidVersionInfoResponse paramAndroidVersionInfoResponse)
      {
        if (paramAndroidVersionInfoResponse == AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance());
        do
        {
          return this;
          if (paramAndroidVersionInfoResponse.hasLatestVersionCode())
            setLatestVersionCode(paramAndroidVersionInfoResponse.getLatestVersionCode());
        }
        while (!paramAndroidVersionInfoResponse.hasShouldUpgrade());
        setShouldUpgrade(paramAndroidVersionInfoResponse.getShouldUpgrade());
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
            this.latestVersionCode_ = paramCodedInputStream.readInt32();
            break;
          case 16:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.shouldUpgrade_ = paramCodedInputStream.readBool();
        }
      }

      public Builder setLatestVersionCode(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.latestVersionCode_ = paramInt;
        return this;
      }

      public Builder setShouldUpgrade(boolean paramBoolean)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.shouldUpgrade_ = paramBoolean;
        return this;
      }
    }
  }

  public static abstract interface AndroidVersionInfoResponseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getLatestVersionCode();

    public abstract boolean getShouldUpgrade();

    public abstract boolean hasLatestVersionCode();

    public abstract boolean hasShouldUpgrade();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.AndroidVersionInfoProtos
 * JD-Core Version:    0.6.2
 */