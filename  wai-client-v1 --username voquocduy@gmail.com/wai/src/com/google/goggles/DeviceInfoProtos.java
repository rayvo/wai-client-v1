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

public final class DeviceInfoProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class DeviceInfo extends GeneratedMessageLite
    implements DeviceInfoProtos.DeviceInfoOrBuilder
  {
    public static final int BUILD_VERSION_FIELD_NUMBER = 2;
    public static final int MODEL_FIELD_NUMBER = 4;
    public static final int PLATFORM_FIELD_NUMBER = 1;
    private static final DeviceInfo defaultInstance = new DeviceInfo(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object buildVersion_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object model_;
    private Object platform_;

    static
    {
      defaultInstance.initFields();
    }

    private DeviceInfo(Builder paramBuilder)
    {
      super();
    }

    private DeviceInfo(boolean paramBoolean)
    {
    }

    private ByteString getBuildVersionBytes()
    {
      Object localObject = this.buildVersion_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.buildVersion_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static DeviceInfo getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getModelBytes()
    {
      Object localObject = this.model_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.model_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getPlatformBytes()
    {
      Object localObject = this.platform_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.platform_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.platform_ = "";
      this.buildVersion_ = "";
      this.model_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(DeviceInfo paramDeviceInfo)
    {
      return newBuilder().mergeFrom(paramDeviceInfo);
    }

    public static DeviceInfo parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static DeviceInfo parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static DeviceInfo parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static DeviceInfo parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static DeviceInfo parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static DeviceInfo parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static DeviceInfo parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static DeviceInfo parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static DeviceInfo parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static DeviceInfo parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getBuildVersion()
    {
      Object localObject = this.buildVersion_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.buildVersion_ = str;
      return str;
    }

    public DeviceInfo getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getModel()
    {
      Object localObject = this.model_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.model_ = str;
      return str;
    }

    public String getPlatform()
    {
      Object localObject = this.platform_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.platform_ = str;
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getPlatformBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getBuildVersionBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(4, getModelBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasBuildVersion()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasModel()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasPlatform()
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
        paramCodedOutputStream.writeBytes(1, getPlatformBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getBuildVersionBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(4, getModelBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DeviceInfoProtos.DeviceInfo, Builder>
      implements DeviceInfoProtos.DeviceInfoOrBuilder
    {
      private int bitField0_;
      private Object buildVersion_ = "";
      private Object model_ = "";
      private Object platform_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private DeviceInfoProtos.DeviceInfo buildParsed()
        throws InvalidProtocolBufferException
      {
        DeviceInfoProtos.DeviceInfo localDeviceInfo = buildPartial();
        if (!localDeviceInfo.isInitialized())
          throw newUninitializedMessageException(localDeviceInfo).asInvalidProtocolBufferException();
        return localDeviceInfo;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public DeviceInfoProtos.DeviceInfo build()
      {
        DeviceInfoProtos.DeviceInfo localDeviceInfo = buildPartial();
        if (!localDeviceInfo.isInitialized())
          throw newUninitializedMessageException(localDeviceInfo);
        return localDeviceInfo;
      }

      public DeviceInfoProtos.DeviceInfo buildPartial()
      {
        int i = 1;
        DeviceInfoProtos.DeviceInfo localDeviceInfo = new DeviceInfoProtos.DeviceInfo(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          DeviceInfoProtos.DeviceInfo.access$302(localDeviceInfo, this.platform_);
          if ((j & 0x2) == 2)
            i |= 2;
          DeviceInfoProtos.DeviceInfo.access$402(localDeviceInfo, this.buildVersion_);
          if ((j & 0x4) == 4)
            i |= 4;
          DeviceInfoProtos.DeviceInfo.access$502(localDeviceInfo, this.model_);
          DeviceInfoProtos.DeviceInfo.access$602(localDeviceInfo, i);
          return localDeviceInfo;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.platform_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.buildVersion_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.model_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearBuildVersion()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.buildVersion_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance().getBuildVersion();
        return this;
      }

      public Builder clearModel()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.model_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance().getModel();
        return this;
      }

      public Builder clearPlatform()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.platform_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance().getPlatform();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getBuildVersion()
      {
        Object localObject = this.buildVersion_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.buildVersion_ = str;
          return str;
        }
        return (String)localObject;
      }

      public DeviceInfoProtos.DeviceInfo getDefaultInstanceForType()
      {
        return DeviceInfoProtos.DeviceInfo.getDefaultInstance();
      }

      public String getModel()
      {
        Object localObject = this.model_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.model_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getPlatform()
      {
        Object localObject = this.platform_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.platform_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasBuildVersion()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasModel()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasPlatform()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(DeviceInfoProtos.DeviceInfo paramDeviceInfo)
      {
        if (paramDeviceInfo == DeviceInfoProtos.DeviceInfo.getDefaultInstance());
        do
        {
          return this;
          if (paramDeviceInfo.hasPlatform())
            setPlatform(paramDeviceInfo.getPlatform());
          if (paramDeviceInfo.hasBuildVersion())
            setBuildVersion(paramDeviceInfo.getBuildVersion());
        }
        while (!paramDeviceInfo.hasModel());
        setModel(paramDeviceInfo.getModel());
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
            this.platform_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.buildVersion_ = paramCodedInputStream.readBytes();
            break;
          case 34:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.model_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setBuildVersion(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.buildVersion_ = paramString;
        return this;
      }

      void setBuildVersion(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.buildVersion_ = paramByteString;
      }

      public Builder setModel(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.model_ = paramString;
        return this;
      }

      void setModel(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.model_ = paramByteString;
      }

      public Builder setPlatform(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.platform_ = paramString;
        return this;
      }

      void setPlatform(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.platform_ = paramByteString;
      }
    }
  }

  public static abstract interface DeviceInfoOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getBuildVersion();

    public abstract String getModel();

    public abstract String getPlatform();

    public abstract boolean hasBuildVersion();

    public abstract boolean hasModel();

    public abstract boolean hasPlatform();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.DeviceInfoProtos
 * JD-Core Version:    0.6.2
 */