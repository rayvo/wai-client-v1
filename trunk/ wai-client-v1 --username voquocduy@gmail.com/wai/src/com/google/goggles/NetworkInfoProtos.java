package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class NetworkInfoProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class MobileNetworkInfo extends GeneratedMessageLite
    implements NetworkInfoProtos.MobileNetworkInfoOrBuilder
  {
    public static final int CARRIER_NAME_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final MobileNetworkInfo defaultInstance = new MobileNetworkInfo(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object carrierName_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private MobileNetworkInfo(Builder paramBuilder)
    {
      super();
    }

    private MobileNetworkInfo(boolean paramBoolean)
    {
    }

    private ByteString getCarrierNameBytes()
    {
      Object localObject = this.carrierName_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.carrierName_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static MobileNetworkInfo getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.type_ = Type.UNKNOWN;
      this.carrierName_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(MobileNetworkInfo paramMobileNetworkInfo)
    {
      return newBuilder().mergeFrom(paramMobileNetworkInfo);
    }

    public static MobileNetworkInfo parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MobileNetworkInfo parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MobileNetworkInfo parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MobileNetworkInfo parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getCarrierName()
    {
      Object localObject = this.carrierName_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.carrierName_ = str;
      return str;
    }

    public MobileNetworkInfo getDefaultInstanceForType()
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
        k = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getCarrierNameBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasCarrierName()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasType()
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
        paramCodedOutputStream.writeEnum(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getCarrierNameBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkInfoProtos.MobileNetworkInfo, Builder>
      implements NetworkInfoProtos.MobileNetworkInfoOrBuilder
    {
      private int bitField0_;
      private Object carrierName_ = "";
      private NetworkInfoProtos.MobileNetworkInfo.Type type_ = NetworkInfoProtos.MobileNetworkInfo.Type.UNKNOWN;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NetworkInfoProtos.MobileNetworkInfo buildParsed()
        throws InvalidProtocolBufferException
      {
        NetworkInfoProtos.MobileNetworkInfo localMobileNetworkInfo = buildPartial();
        if (!localMobileNetworkInfo.isInitialized())
          throw newUninitializedMessageException(localMobileNetworkInfo).asInvalidProtocolBufferException();
        return localMobileNetworkInfo;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NetworkInfoProtos.MobileNetworkInfo build()
      {
        NetworkInfoProtos.MobileNetworkInfo localMobileNetworkInfo = buildPartial();
        if (!localMobileNetworkInfo.isInitialized())
          throw newUninitializedMessageException(localMobileNetworkInfo);
        return localMobileNetworkInfo;
      }

      public NetworkInfoProtos.MobileNetworkInfo buildPartial()
      {
        int i = 1;
        NetworkInfoProtos.MobileNetworkInfo localMobileNetworkInfo = new NetworkInfoProtos.MobileNetworkInfo(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          NetworkInfoProtos.MobileNetworkInfo.access$302(localMobileNetworkInfo, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          NetworkInfoProtos.MobileNetworkInfo.access$402(localMobileNetworkInfo, this.carrierName_);
          NetworkInfoProtos.MobileNetworkInfo.access$502(localMobileNetworkInfo, i);
          return localMobileNetworkInfo;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = NetworkInfoProtos.MobileNetworkInfo.Type.UNKNOWN;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.carrierName_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearCarrierName()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.carrierName_ = NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance().getCarrierName();
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = NetworkInfoProtos.MobileNetworkInfo.Type.UNKNOWN;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getCarrierName()
      {
        Object localObject = this.carrierName_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.carrierName_ = str;
          return str;
        }
        return (String)localObject;
      }

      public NetworkInfoProtos.MobileNetworkInfo getDefaultInstanceForType()
      {
        return NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance();
      }

      public NetworkInfoProtos.MobileNetworkInfo.Type getType()
      {
        return this.type_;
      }

      public boolean hasCarrierName()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(NetworkInfoProtos.MobileNetworkInfo paramMobileNetworkInfo)
      {
        if (paramMobileNetworkInfo == NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance());
        do
        {
          return this;
          if (paramMobileNetworkInfo.hasType())
            setType(paramMobileNetworkInfo.getType());
        }
        while (!paramMobileNetworkInfo.hasCarrierName());
        setCarrierName(paramMobileNetworkInfo.getCarrierName());
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
            NetworkInfoProtos.MobileNetworkInfo.Type localType = NetworkInfoProtos.MobileNetworkInfo.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 18:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.carrierName_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setCarrierName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.carrierName_ = paramString;
        return this;
      }

      void setCarrierName(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.carrierName_ = paramByteString;
      }

      public Builder setType(NetworkInfoProtos.MobileNetworkInfo.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int CDMA_VALUE = 2;
      public static final int EDGE_VALUE = 3;
      public static final int EHRPD_VALUE = 103;
      public static final int EVDO_0_VALUE = 4;
      public static final int EVDO_A_VALUE = 5;
      public static final int EVDO_B_VALUE = 100;
      public static final int GPRS_VALUE = 6;
      public static final int HSDPA_VALUE = 7;
      public static final int HSPAP_VALUE = 104;
      public static final int HSPA_VALUE = 8;
      public static final int HSUPA_VALUE = 9;
      public static final int IDEN_VALUE = 101;
      public static final int LTE_VALUE = 102;
      public static final int ONExRTT_VALUE = 1;
      public static final int OTHER_VALUE = 15;
      public static final Type UNKNOWN = EVDO_B;
      public static final int UNKNOWN_VALUE = 100;
      public static final int UTMS_VALUE = 10;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public NetworkInfoProtos.MobileNetworkInfo.Type findValueByNumber(int paramAnonymousInt)
        {
          return NetworkInfoProtos.MobileNetworkInfo.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        EDGE = new Type("EDGE", 1, 1, 3);
        UTMS = new Type("UTMS", 2, 2, 10);
        HSDPA = new Type("HSDPA", 3, 3, 7);
        HSUPA = new Type("HSUPA", 4, 4, 9);
        HSPA = new Type("HSPA", 5, 5, 8);
        CDMA = new Type("CDMA", 6, 6, 2);
        EVDO_0 = new Type("EVDO_0", 7, 7, 4);
        EVDO_A = new Type("EVDO_A", 8, 8, 5);
        EVDO_B = new Type("EVDO_B", 9, 9, 100);
        ONExRTT = new Type("ONExRTT", 10, 10, 1);
        IDEN = new Type("IDEN", 11, 11, 101);
        LTE = new Type("LTE", 12, 12, 102);
        EHRPD = new Type("EHRPD", 13, 13, 103);
        HSPAP = new Type("HSPAP", 14, 14, 104);
        OTHER = new Type("OTHER", 15, 15, 15);
        Type[] arrayOfType = new Type[16];
        arrayOfType[0] = GPRS;
        arrayOfType[1] = EDGE;
        arrayOfType[2] = UTMS;
        arrayOfType[3] = HSDPA;
        arrayOfType[4] = HSUPA;
        arrayOfType[5] = HSPA;
        arrayOfType[6] = CDMA;
        arrayOfType[7] = EVDO_0;
        arrayOfType[8] = EVDO_A;
        arrayOfType[9] = EVDO_B;
        arrayOfType[10] = ONExRTT;
        arrayOfType[11] = IDEN;
        arrayOfType[12] = LTE;
        arrayOfType[13] = EHRPD;
        arrayOfType[14] = HSPAP;
        arrayOfType[15] = OTHER;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 6:
          return GPRS;
        case 3:
          return EDGE;
        case 10:
          return UTMS;
        case 7:
          return HSDPA;
        case 9:
          return HSUPA;
        case 8:
          return HSPA;
        case 2:
          return CDMA;
        case 4:
          return EVDO_0;
        case 5:
          return EVDO_A;
        case 100:
          return EVDO_B;
        case 1:
          return ONExRTT;
        case 101:
          return IDEN;
        case 102:
          return LTE;
        case 103:
          return EHRPD;
        case 104:
          return HSPAP;
        case 15:
        }
        return OTHER;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface MobileNetworkInfoOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getCarrierName();

    public abstract NetworkInfoProtos.MobileNetworkInfo.Type getType();

    public abstract boolean hasCarrierName();

    public abstract boolean hasType();
  }

  public static final class NetworkInfo extends GeneratedMessageLite
    implements NetworkInfoProtos.NetworkInfoOrBuilder
  {
    public static final int MOBILE_NETWORK_INFO_FIELD_NUMBER = 2;
    public static final int SIGNAL_STRENGTH_FIELD_NUMBER = 3;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final NetworkInfo defaultInstance = new NetworkInfo(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private NetworkInfoProtos.MobileNetworkInfo mobileNetworkInfo_;
    private int signalStrength_;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private NetworkInfo(Builder paramBuilder)
    {
      super();
    }

    private NetworkInfo(boolean paramBoolean)
    {
    }

    public static NetworkInfo getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.type_ = Type.UNKNOWN;
      this.mobileNetworkInfo_ = NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance();
      this.signalStrength_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$700();
    }

    public static Builder newBuilder(NetworkInfo paramNetworkInfo)
    {
      return newBuilder().mergeFrom(paramNetworkInfo);
    }

    public static NetworkInfo parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static NetworkInfo parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static NetworkInfo parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static NetworkInfo parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static NetworkInfo parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static NetworkInfo parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static NetworkInfo parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static NetworkInfo parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static NetworkInfo parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static NetworkInfo parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public NetworkInfo getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public NetworkInfoProtos.MobileNetworkInfo getMobileNetworkInfo()
    {
      return this.mobileNetworkInfo_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(2, this.mobileNetworkInfo_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.signalStrength_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getSignalStrength()
    {
      return this.signalStrength_;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasMobileNetworkInfo()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasSignalStrength()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasType()
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
        paramCodedOutputStream.writeEnum(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(2, this.mobileNetworkInfo_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.signalStrength_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<NetworkInfoProtos.NetworkInfo, Builder>
      implements NetworkInfoProtos.NetworkInfoOrBuilder
    {
      private int bitField0_;
      private NetworkInfoProtos.MobileNetworkInfo mobileNetworkInfo_ = NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance();
      private int signalStrength_;
      private NetworkInfoProtos.NetworkInfo.Type type_ = NetworkInfoProtos.NetworkInfo.Type.UNKNOWN;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private NetworkInfoProtos.NetworkInfo buildParsed()
        throws InvalidProtocolBufferException
      {
        NetworkInfoProtos.NetworkInfo localNetworkInfo = buildPartial();
        if (!localNetworkInfo.isInitialized())
          throw newUninitializedMessageException(localNetworkInfo).asInvalidProtocolBufferException();
        return localNetworkInfo;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public NetworkInfoProtos.NetworkInfo build()
      {
        NetworkInfoProtos.NetworkInfo localNetworkInfo = buildPartial();
        if (!localNetworkInfo.isInitialized())
          throw newUninitializedMessageException(localNetworkInfo);
        return localNetworkInfo;
      }

      public NetworkInfoProtos.NetworkInfo buildPartial()
      {
        int i = 1;
        NetworkInfoProtos.NetworkInfo localNetworkInfo = new NetworkInfoProtos.NetworkInfo(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          NetworkInfoProtos.NetworkInfo.access$902(localNetworkInfo, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          NetworkInfoProtos.NetworkInfo.access$1002(localNetworkInfo, this.mobileNetworkInfo_);
          if ((j & 0x4) == 4)
            i |= 4;
          NetworkInfoProtos.NetworkInfo.access$1102(localNetworkInfo, this.signalStrength_);
          NetworkInfoProtos.NetworkInfo.access$1202(localNetworkInfo, i);
          return localNetworkInfo;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = NetworkInfoProtos.NetworkInfo.Type.UNKNOWN;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.mobileNetworkInfo_ = NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.signalStrength_ = 0;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearMobileNetworkInfo()
      {
        this.mobileNetworkInfo_ = NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearSignalStrength()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.signalStrength_ = 0;
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = NetworkInfoProtos.NetworkInfo.Type.UNKNOWN;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public NetworkInfoProtos.NetworkInfo getDefaultInstanceForType()
      {
        return NetworkInfoProtos.NetworkInfo.getDefaultInstance();
      }

      public NetworkInfoProtos.MobileNetworkInfo getMobileNetworkInfo()
      {
        return this.mobileNetworkInfo_;
      }

      public int getSignalStrength()
      {
        return this.signalStrength_;
      }

      public NetworkInfoProtos.NetworkInfo.Type getType()
      {
        return this.type_;
      }

      public boolean hasMobileNetworkInfo()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasSignalStrength()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(NetworkInfoProtos.NetworkInfo paramNetworkInfo)
      {
        if (paramNetworkInfo == NetworkInfoProtos.NetworkInfo.getDefaultInstance());
        do
        {
          return this;
          if (paramNetworkInfo.hasType())
            setType(paramNetworkInfo.getType());
          if (paramNetworkInfo.hasMobileNetworkInfo())
            mergeMobileNetworkInfo(paramNetworkInfo.getMobileNetworkInfo());
        }
        while (!paramNetworkInfo.hasSignalStrength());
        setSignalStrength(paramNetworkInfo.getSignalStrength());
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
            NetworkInfoProtos.NetworkInfo.Type localType = NetworkInfoProtos.NetworkInfo.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 18:
            NetworkInfoProtos.MobileNetworkInfo.Builder localBuilder = NetworkInfoProtos.MobileNetworkInfo.newBuilder();
            if (hasMobileNetworkInfo())
              localBuilder.mergeFrom(getMobileNetworkInfo());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setMobileNetworkInfo(localBuilder.buildPartial());
            break;
          case 24:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.signalStrength_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder mergeMobileNetworkInfo(NetworkInfoProtos.MobileNetworkInfo paramMobileNetworkInfo)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.mobileNetworkInfo_ != NetworkInfoProtos.MobileNetworkInfo.getDefaultInstance()));
        for (this.mobileNetworkInfo_ = NetworkInfoProtos.MobileNetworkInfo.newBuilder(this.mobileNetworkInfo_).mergeFrom(paramMobileNetworkInfo).buildPartial(); ; this.mobileNetworkInfo_ = paramMobileNetworkInfo)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder setMobileNetworkInfo(NetworkInfoProtos.MobileNetworkInfo.Builder paramBuilder)
      {
        this.mobileNetworkInfo_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setMobileNetworkInfo(NetworkInfoProtos.MobileNetworkInfo paramMobileNetworkInfo)
      {
        if (paramMobileNetworkInfo == null)
          throw new NullPointerException();
        this.mobileNetworkInfo_ = paramMobileNetworkInfo;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setSignalStrength(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.signalStrength_ = paramInt;
        return this;
      }

      public Builder setType(NetworkInfoProtos.NetworkInfo.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int MOBILE_VALUE = 2;
      public static final int OTHER_VALUE = 15;
      public static final int UNKNOWN_VALUE = 100;
      public static final int WIFI_VALUE = 1;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public NetworkInfoProtos.NetworkInfo.Type findValueByNumber(int paramAnonymousInt)
        {
          return NetworkInfoProtos.NetworkInfo.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        MOBILE = new Type("MOBILE", 1, 1, 2);
        OTHER = new Type("OTHER", 2, 2, 15);
        UNKNOWN = new Type("UNKNOWN", 3, 3, 100);
        Type[] arrayOfType = new Type[4];
        arrayOfType[0] = WIFI;
        arrayOfType[1] = MOBILE;
        arrayOfType[2] = OTHER;
        arrayOfType[3] = UNKNOWN;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return WIFI;
        case 2:
          return MOBILE;
        case 15:
          return OTHER;
        case 100:
        }
        return UNKNOWN;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface NetworkInfoOrBuilder extends MessageLiteOrBuilder
  {
    public abstract NetworkInfoProtos.MobileNetworkInfo getMobileNetworkInfo();

    public abstract int getSignalStrength();

    public abstract NetworkInfoProtos.NetworkInfo.Type getType();

    public abstract boolean hasMobileNetworkInfo();

    public abstract boolean hasSignalStrength();

    public abstract boolean hasType();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.NetworkInfoProtos
 * JD-Core Version:    0.6.2
 */