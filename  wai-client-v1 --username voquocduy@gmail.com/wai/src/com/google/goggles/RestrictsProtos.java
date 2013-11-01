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
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class RestrictsProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Category extends GeneratedMessageLite
    implements RestrictsProtos.CategoryOrBuilder
  {
    public static final int DISPLAY_NAME_FIELD_NUMBER = 4;
    public static final int DOMAIN_FIELD_NUMBER = 1;
    public static final int DOMAIN_SPECIFIC_ID_FIELD_NUMBER = 3;
    public static final int NAME_FIELD_NUMBER = 2;
    private static final Category defaultInstance = new Category(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object displayName_;
    private int domainSpecificId_;
    private Domain domain_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object name_;

    static
    {
      defaultInstance.initFields();
    }

    private Category(Builder paramBuilder)
    {
      super();
    }

    private Category(boolean paramBoolean)
    {
    }

    public static Category getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getDisplayNameBytes()
    {
      Object localObject = this.displayName_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.displayName_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getNameBytes()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.name_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.domain_ = Domain.PRETTY_NAME;
      this.name_ = "";
      this.displayName_ = "";
      this.domainSpecificId_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$800();
    }

    public static Builder newBuilder(Category paramCategory)
    {
      return newBuilder().mergeFrom(paramCategory);
    }

    public static Category parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Category parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Category parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Category parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Category parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Category parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Category parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Category parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Category parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Category parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Category getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getDisplayName()
    {
      Object localObject = this.displayName_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.displayName_ = str;
      return str;
    }

    public Domain getDomain()
    {
      return this.domain_;
    }

    public int getDomainSpecificId()
    {
      return this.domainSpecificId_;
    }

    public String getName()
    {
      Object localObject = this.name_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.name_ = str;
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
        k = 0 + CodedOutputStream.computeEnumSize(1, this.domain_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getNameBytes());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeInt32Size(3, this.domainSpecificId_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(4, getDisplayNameBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasDisplayName()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasDomain()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasDomainSpecificId()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasName()
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
        paramCodedOutputStream.writeEnum(1, this.domain_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getNameBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(3, this.domainSpecificId_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(4, getDisplayNameBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictsProtos.Category, Builder>
      implements RestrictsProtos.CategoryOrBuilder
    {
      private int bitField0_;
      private Object displayName_ = "";
      private int domainSpecificId_;
      private RestrictsProtos.Category.Domain domain_ = RestrictsProtos.Category.Domain.PRETTY_NAME;
      private Object name_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private RestrictsProtos.Category buildParsed()
        throws InvalidProtocolBufferException
      {
        RestrictsProtos.Category localCategory = buildPartial();
        if (!localCategory.isInitialized())
          throw newUninitializedMessageException(localCategory).asInvalidProtocolBufferException();
        return localCategory;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public RestrictsProtos.Category build()
      {
        RestrictsProtos.Category localCategory = buildPartial();
        if (!localCategory.isInitialized())
          throw newUninitializedMessageException(localCategory);
        return localCategory;
      }

      public RestrictsProtos.Category buildPartial()
      {
        int i = 1;
        RestrictsProtos.Category localCategory = new RestrictsProtos.Category(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          RestrictsProtos.Category.access$1002(localCategory, this.domain_);
          if ((j & 0x2) == 2)
            i |= 2;
          RestrictsProtos.Category.access$1102(localCategory, this.name_);
          if ((j & 0x4) == 4)
            i |= 4;
          RestrictsProtos.Category.access$1202(localCategory, this.displayName_);
          if ((j & 0x8) == 8)
            i |= 8;
          RestrictsProtos.Category.access$1302(localCategory, this.domainSpecificId_);
          RestrictsProtos.Category.access$1402(localCategory, i);
          return localCategory;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.domain_ = RestrictsProtos.Category.Domain.PRETTY_NAME;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.name_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.displayName_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.domainSpecificId_ = 0;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearDisplayName()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.displayName_ = RestrictsProtos.Category.getDefaultInstance().getDisplayName();
        return this;
      }

      public Builder clearDomain()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.domain_ = RestrictsProtos.Category.Domain.PRETTY_NAME;
        return this;
      }

      public Builder clearDomainSpecificId()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.domainSpecificId_ = 0;
        return this;
      }

      public Builder clearName()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.name_ = RestrictsProtos.Category.getDefaultInstance().getName();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public RestrictsProtos.Category getDefaultInstanceForType()
      {
        return RestrictsProtos.Category.getDefaultInstance();
      }

      public String getDisplayName()
      {
        Object localObject = this.displayName_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.displayName_ = str;
          return str;
        }
        return (String)localObject;
      }

      public RestrictsProtos.Category.Domain getDomain()
      {
        return this.domain_;
      }

      public int getDomainSpecificId()
      {
        return this.domainSpecificId_;
      }

      public String getName()
      {
        Object localObject = this.name_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.name_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasDisplayName()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasDomain()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasDomainSpecificId()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasName()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == RestrictsProtos.Category.getDefaultInstance());
        do
        {
          return this;
          if (paramCategory.hasDomain())
            setDomain(paramCategory.getDomain());
          if (paramCategory.hasName())
            setName(paramCategory.getName());
          if (paramCategory.hasDisplayName())
            setDisplayName(paramCategory.getDisplayName());
        }
        while (!paramCategory.hasDomainSpecificId());
        setDomainSpecificId(paramCategory.getDomainSpecificId());
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
            RestrictsProtos.Category.Domain localDomain = RestrictsProtos.Category.Domain.valueOf(paramCodedInputStream.readEnum());
            if (localDomain == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.domain_ = localDomain;
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 24:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.domainSpecificId_ = paramCodedInputStream.readInt32();
            break;
          case 34:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.displayName_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setDisplayName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.displayName_ = paramString;
        return this;
      }

      void setDisplayName(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.displayName_ = paramByteString;
      }

      public Builder setDomain(RestrictsProtos.Category.Domain paramDomain)
      {
        if (paramDomain == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.domain_ = paramDomain;
        return this;
      }

      public Builder setDomainSpecificId(int paramInt)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.domainSpecificId_ = paramInt;
        return this;
      }

      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.name_ = paramString;
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.name_ = paramByteString;
      }
    }

    public static enum Domain
      implements Internal.EnumLite
    {
      public static final int PRETTY_NAME_VALUE = 0;
      public static final int PRODUCT_VALUE = 1;
      private static Internal.EnumLiteMap<Domain> internalValueMap = new Internal.EnumLiteMap()
      {
        public RestrictsProtos.Category.Domain findValueByNumber(int paramAnonymousInt)
        {
          return RestrictsProtos.Category.Domain.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        Domain[] arrayOfDomain = new Domain[2];
        arrayOfDomain[0] = PRETTY_NAME;
        arrayOfDomain[1] = PRODUCT;
      }

      private Domain(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Domain> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Domain valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return PRETTY_NAME;
        case 1:
        }
        return PRODUCT;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface CategoryOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getDisplayName();

    public abstract RestrictsProtos.Category.Domain getDomain();

    public abstract int getDomainSpecificId();

    public abstract String getName();

    public abstract boolean hasDisplayName();

    public abstract boolean hasDomain();

    public abstract boolean hasDomainSpecificId();

    public abstract boolean hasName();
  }

  public static final class ColorEnum extends GeneratedMessageLite
    implements RestrictsProtos.ColorEnumOrBuilder
  {
    private static final ColorEnum defaultInstance = new ColorEnum(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private ColorEnum(Builder paramBuilder)
    {
      super();
    }

    private ColorEnum(boolean paramBoolean)
    {
    }

    public static ColorEnum getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$1600();
    }

    public static Builder newBuilder(ColorEnum paramColorEnum)
    {
      return newBuilder().mergeFrom(paramColorEnum);
    }

    public static ColorEnum parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ColorEnum parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ColorEnum parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ColorEnum parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ColorEnum parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ColorEnum parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ColorEnum parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ColorEnum parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ColorEnum parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ColorEnum parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ColorEnum getDefaultInstanceForType()
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

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictsProtos.ColorEnum, Builder>
      implements RestrictsProtos.ColorEnumOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private RestrictsProtos.ColorEnum buildParsed()
        throws InvalidProtocolBufferException
      {
        RestrictsProtos.ColorEnum localColorEnum = buildPartial();
        if (!localColorEnum.isInitialized())
          throw newUninitializedMessageException(localColorEnum).asInvalidProtocolBufferException();
        return localColorEnum;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public RestrictsProtos.ColorEnum build()
      {
        RestrictsProtos.ColorEnum localColorEnum = buildPartial();
        if (!localColorEnum.isInitialized())
          throw newUninitializedMessageException(localColorEnum);
        return localColorEnum;
      }

      public RestrictsProtos.ColorEnum buildPartial()
      {
        return new RestrictsProtos.ColorEnum(this, null);
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

      public RestrictsProtos.ColorEnum getDefaultInstanceForType()
      {
        return RestrictsProtos.ColorEnum.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(RestrictsProtos.ColorEnum paramColorEnum)
      {
        if (paramColorEnum == RestrictsProtos.ColorEnum.getDefaultInstance());
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

    public static enum Color
      implements Internal.EnumLite
    {
      public static final int AQUAMARINE_VALUE = 1;
      public static final int AQUA_VALUE = 0;
      public static final int AZURE_VALUE = 2;
      public static final int BEIGE_VALUE = 3;
      public static final int BLACK_VALUE = 4;
      public static final int BLUE_VALUE = 5;
      public static final int BROWN_VALUE = 6;
      public static final int CHARTREUSE_VALUE = 7;
      public static final int CRIMSON_VALUE = 8;
      public static final int CYAN_VALUE = 9;
      public static final int FUCHSIA_VALUE = 10;
      public static final int GOLDEN_VALUE = 12;
      public static final int GOLD_VALUE = 11;
      public static final int GRAY_VALUE = 13;
      public static final int GREEN_VALUE = 14;
      public static final int GREY_VALUE = 15;
      public static final int INDIGO_VALUE = 16;
      public static final int IVORY_VALUE = 17;
      public static final int KHAKI_VALUE = 18;
      public static final int LAVENDER_VALUE = 19;
      public static final int LIME_VALUE = 20;
      public static final int MAGENTA_VALUE = 21;
      public static final int MAROON_VALUE = 22;
      public static final int NAVY_VALUE = 23;
      public static final int OLIVE_VALUE = 24;
      public static final int ORANGE_VALUE = 25;
      public static final int PINK_VALUE = 26;
      public static final int PURPLE_VALUE = 27;
      public static final int RED_VALUE = 28;
      public static final int ROSE_VALUE = 29;
      public static final int SIENNA_VALUE = 30;
      public static final int SILVER_VALUE = 31;
      public static final int TEAL_VALUE = 32;
      public static final int WHITE_VALUE = 33;
      public static final int YELLOW_VALUE = 34;
      private static Internal.EnumLiteMap<Color> internalValueMap = new Internal.EnumLiteMap()
      {
        public RestrictsProtos.ColorEnum.Color findValueByNumber(int paramAnonymousInt)
        {
          return RestrictsProtos.ColorEnum.Color.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        Color[] arrayOfColor = new Color[35];
        arrayOfColor[0] = AQUA;
        arrayOfColor[1] = AQUAMARINE;
        arrayOfColor[2] = AZURE;
        arrayOfColor[3] = BEIGE;
        arrayOfColor[4] = BLACK;
        arrayOfColor[5] = BLUE;
        arrayOfColor[6] = BROWN;
        arrayOfColor[7] = CHARTREUSE;
        arrayOfColor[8] = CRIMSON;
        arrayOfColor[9] = CYAN;
        arrayOfColor[10] = FUCHSIA;
        arrayOfColor[11] = GOLD;
        arrayOfColor[12] = GOLDEN;
        arrayOfColor[13] = GRAY;
        arrayOfColor[14] = GREEN;
        arrayOfColor[15] = GREY;
        arrayOfColor[16] = INDIGO;
        arrayOfColor[17] = IVORY;
        arrayOfColor[18] = KHAKI;
        arrayOfColor[19] = LAVENDER;
        arrayOfColor[20] = LIME;
        arrayOfColor[21] = MAGENTA;
        arrayOfColor[22] = MAROON;
        arrayOfColor[23] = NAVY;
        arrayOfColor[24] = OLIVE;
        arrayOfColor[25] = ORANGE;
        arrayOfColor[26] = PINK;
        arrayOfColor[27] = PURPLE;
        arrayOfColor[28] = RED;
        arrayOfColor[29] = ROSE;
        arrayOfColor[30] = SIENNA;
        arrayOfColor[31] = SILVER;
        arrayOfColor[32] = TEAL;
        arrayOfColor[33] = WHITE;
        arrayOfColor[34] = YELLOW;
      }

      private Color(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Color> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Color valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return AQUA;
        case 1:
          return AQUAMARINE;
        case 2:
          return AZURE;
        case 3:
          return BEIGE;
        case 4:
          return BLACK;
        case 5:
          return BLUE;
        case 6:
          return BROWN;
        case 7:
          return CHARTREUSE;
        case 8:
          return CRIMSON;
        case 9:
          return CYAN;
        case 10:
          return FUCHSIA;
        case 11:
          return GOLD;
        case 12:
          return GOLDEN;
        case 13:
          return GRAY;
        case 14:
          return GREEN;
        case 15:
          return GREY;
        case 16:
          return INDIGO;
        case 17:
          return IVORY;
        case 18:
          return KHAKI;
        case 19:
          return LAVENDER;
        case 20:
          return LIME;
        case 21:
          return MAGENTA;
        case 22:
          return MAROON;
        case 23:
          return NAVY;
        case 24:
          return OLIVE;
        case 25:
          return ORANGE;
        case 26:
          return PINK;
        case 27:
          return PURPLE;
        case 28:
          return RED;
        case 29:
          return ROSE;
        case 30:
          return SIENNA;
        case 31:
          return SILVER;
        case 32:
          return TEAL;
        case 33:
          return WHITE;
        case 34:
        }
        return YELLOW;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface ColorEnumOrBuilder extends MessageLiteOrBuilder
  {
  }

  public static final class Restricts extends GeneratedMessageLite
    implements RestrictsProtos.RestrictsOrBuilder
  {
    public static final int BRANDS_FIELD_NUMBER = 2;
    public static final int CATEGORIES_FIELD_NUMBER = 3;
    public static final int COLORS_FIELD_NUMBER = 5;
    public static final int GENDERS_FIELD_NUMBER = 4;
    private static final Restricts defaultInstance = new Restricts(true);
    private static final long serialVersionUID;
    private LazyStringList brands_;
    private List<RestrictsProtos.Category> categories_;
    private List<RestrictsProtos.ColorEnum.Color> colors_;
    private List<Gender> genders_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private Restricts(Builder paramBuilder)
    {
      super();
    }

    private Restricts(boolean paramBoolean)
    {
    }

    public static Restricts getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.categories_ = Collections.emptyList();
      this.brands_ = LazyStringArrayList.EMPTY;
      this.genders_ = Collections.emptyList();
      this.colors_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Restricts paramRestricts)
    {
      return newBuilder().mergeFrom(paramRestricts);
    }

    public static Restricts parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Restricts parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Restricts parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Restricts parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Restricts parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Restricts parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Restricts parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Restricts parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Restricts parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Restricts parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getBrands(int paramInt)
    {
      return (String)this.brands_.get(paramInt);
    }

    public int getBrandsCount()
    {
      return this.brands_.size();
    }

    public List<String> getBrandsList()
    {
      return this.brands_;
    }

    public RestrictsProtos.Category getCategories(int paramInt)
    {
      return (RestrictsProtos.Category)this.categories_.get(paramInt);
    }

    public int getCategoriesCount()
    {
      return this.categories_.size();
    }

    public List<RestrictsProtos.Category> getCategoriesList()
    {
      return this.categories_;
    }

    public RestrictsProtos.CategoryOrBuilder getCategoriesOrBuilder(int paramInt)
    {
      return (RestrictsProtos.CategoryOrBuilder)this.categories_.get(paramInt);
    }

    public List<? extends RestrictsProtos.CategoryOrBuilder> getCategoriesOrBuilderList()
    {
      return this.categories_;
    }

    public RestrictsProtos.ColorEnum.Color getColors(int paramInt)
    {
      return (RestrictsProtos.ColorEnum.Color)this.colors_.get(paramInt);
    }

    public int getColorsCount()
    {
      return this.colors_.size();
    }

    public List<RestrictsProtos.ColorEnum.Color> getColorsList()
    {
      return this.colors_;
    }

    public Restricts getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public Gender getGenders(int paramInt)
    {
      return (Gender)this.genders_.get(paramInt);
    }

    public int getGendersCount()
    {
      return this.genders_.size();
    }

    public List<Gender> getGendersList()
    {
      return this.genders_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      int j = this.memoizedSerializedSize;
      if (j != -1)
        return j;
      int k = 0;
      int m = 0;
      while (k < this.brands_.size())
      {
        m += CodedOutputStream.computeBytesSizeNoTag(this.brands_.getByteString(k));
        k++;
      }
      int n = 0 + m + 1 * getBrandsList().size();
      int i1 = 0;
      int i2 = n;
      while (i1 < this.categories_.size())
      {
        i2 += CodedOutputStream.computeMessageSize(3, (MessageLite)this.categories_.get(i1));
        i1++;
      }
      int i3 = 0;
      int i4 = 0;
      while (i3 < this.genders_.size())
      {
        i4 += CodedOutputStream.computeEnumSizeNoTag(((Gender)this.genders_.get(i3)).getNumber());
        i3++;
      }
      int i5 = i2 + i4 + 1 * this.genders_.size();
      int i8;
      for (int i6 = 0; i < this.colors_.size(); i6 = i8)
      {
        i8 = i6 + CodedOutputStream.computeEnumSizeNoTag(((RestrictsProtos.ColorEnum.Color)this.colors_.get(i)).getNumber());
        i++;
      }
      int i7 = i5 + i6 + 1 * this.colors_.size();
      this.memoizedSerializedSize = i7;
      return i7;
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
      for (int i = 0; i < this.brands_.size(); i++)
        paramCodedOutputStream.writeBytes(2, this.brands_.getByteString(i));
      for (int j = 0; j < this.categories_.size(); j++)
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.categories_.get(j));
      int n;
      for (int k = 0; ; k++)
      {
        int m = this.genders_.size();
        n = 0;
        if (k >= m)
          break;
        paramCodedOutputStream.writeEnum(4, ((Gender)this.genders_.get(k)).getNumber());
      }
      while (n < this.colors_.size())
      {
        paramCodedOutputStream.writeEnum(5, ((RestrictsProtos.ColorEnum.Color)this.colors_.get(n)).getNumber());
        n++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<RestrictsProtos.Restricts, Builder>
      implements RestrictsProtos.RestrictsOrBuilder
    {
      private int bitField0_;
      private LazyStringList brands_ = LazyStringArrayList.EMPTY;
      private List<RestrictsProtos.Category> categories_ = Collections.emptyList();
      private List<RestrictsProtos.ColorEnum.Color> colors_ = Collections.emptyList();
      private List<RestrictsProtos.Restricts.Gender> genders_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private RestrictsProtos.Restricts buildParsed()
        throws InvalidProtocolBufferException
      {
        RestrictsProtos.Restricts localRestricts = buildPartial();
        if (!localRestricts.isInitialized())
          throw newUninitializedMessageException(localRestricts).asInvalidProtocolBufferException();
        return localRestricts;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureBrandsIsMutable()
      {
        if ((0x2 & this.bitField0_) != 2)
        {
          this.brands_ = new LazyStringArrayList(this.brands_);
          this.bitField0_ = (0x2 | this.bitField0_);
        }
      }

      private void ensureCategoriesIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.categories_ = new ArrayList(this.categories_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void ensureColorsIsMutable()
      {
        if ((0x8 & this.bitField0_) != 8)
        {
          this.colors_ = new ArrayList(this.colors_);
          this.bitField0_ = (0x8 | this.bitField0_);
        }
      }

      private void ensureGendersIsMutable()
      {
        if ((0x4 & this.bitField0_) != 4)
        {
          this.genders_ = new ArrayList(this.genders_);
          this.bitField0_ = (0x4 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllBrands(Iterable<String> paramIterable)
      {
        ensureBrandsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.brands_);
        return this;
      }

      public Builder addAllCategories(Iterable<? extends RestrictsProtos.Category> paramIterable)
      {
        ensureCategoriesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.categories_);
        return this;
      }

      public Builder addAllColors(Iterable<? extends RestrictsProtos.ColorEnum.Color> paramIterable)
      {
        ensureColorsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.colors_);
        return this;
      }

      public Builder addAllGenders(Iterable<? extends RestrictsProtos.Restricts.Gender> paramIterable)
      {
        ensureGendersIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.genders_);
        return this;
      }

      public Builder addBrands(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureBrandsIsMutable();
        this.brands_.add(paramString);
        return this;
      }

      void addBrands(ByteString paramByteString)
      {
        ensureBrandsIsMutable();
        this.brands_.add(paramByteString);
      }

      public Builder addCategories(int paramInt, RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addCategories(int paramInt, RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.add(paramInt, paramCategory);
        return this;
      }

      public Builder addCategories(RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.add(paramBuilder.build());
        return this;
      }

      public Builder addCategories(RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.add(paramCategory);
        return this;
      }

      public Builder addColors(RestrictsProtos.ColorEnum.Color paramColor)
      {
        if (paramColor == null)
          throw new NullPointerException();
        ensureColorsIsMutable();
        this.colors_.add(paramColor);
        return this;
      }

      public Builder addGenders(RestrictsProtos.Restricts.Gender paramGender)
      {
        if (paramGender == null)
          throw new NullPointerException();
        ensureGendersIsMutable();
        this.genders_.add(paramGender);
        return this;
      }

      public RestrictsProtos.Restricts build()
      {
        RestrictsProtos.Restricts localRestricts = buildPartial();
        if (!localRestricts.isInitialized())
          throw newUninitializedMessageException(localRestricts);
        return localRestricts;
      }

      public RestrictsProtos.Restricts buildPartial()
      {
        RestrictsProtos.Restricts localRestricts = new RestrictsProtos.Restricts(this, null);
        if ((0x1 & this.bitField0_) == 1)
        {
          this.categories_ = Collections.unmodifiableList(this.categories_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        RestrictsProtos.Restricts.access$302(localRestricts, this.categories_);
        if ((0x2 & this.bitField0_) == 2)
        {
          this.brands_ = new UnmodifiableLazyStringList(this.brands_);
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        }
        RestrictsProtos.Restricts.access$402(localRestricts, this.brands_);
        if ((0x4 & this.bitField0_) == 4)
        {
          this.genders_ = Collections.unmodifiableList(this.genders_);
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        }
        RestrictsProtos.Restricts.access$502(localRestricts, this.genders_);
        if ((0x8 & this.bitField0_) == 8)
        {
          this.colors_ = Collections.unmodifiableList(this.colors_);
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        }
        RestrictsProtos.Restricts.access$602(localRestricts, this.colors_);
        return localRestricts;
      }

      public Builder clear()
      {
        super.clear();
        this.categories_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.brands_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.genders_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.colors_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearBrands()
      {
        this.brands_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearCategories()
      {
        this.categories_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearColors()
      {
        this.colors_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearGenders()
      {
        this.genders_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getBrands(int paramInt)
      {
        return (String)this.brands_.get(paramInt);
      }

      public int getBrandsCount()
      {
        return this.brands_.size();
      }

      public List<String> getBrandsList()
      {
        return Collections.unmodifiableList(this.brands_);
      }

      public RestrictsProtos.Category getCategories(int paramInt)
      {
        return (RestrictsProtos.Category)this.categories_.get(paramInt);
      }

      public int getCategoriesCount()
      {
        return this.categories_.size();
      }

      public List<RestrictsProtos.Category> getCategoriesList()
      {
        return Collections.unmodifiableList(this.categories_);
      }

      public RestrictsProtos.ColorEnum.Color getColors(int paramInt)
      {
        return (RestrictsProtos.ColorEnum.Color)this.colors_.get(paramInt);
      }

      public int getColorsCount()
      {
        return this.colors_.size();
      }

      public List<RestrictsProtos.ColorEnum.Color> getColorsList()
      {
        return Collections.unmodifiableList(this.colors_);
      }

      public RestrictsProtos.Restricts getDefaultInstanceForType()
      {
        return RestrictsProtos.Restricts.getDefaultInstance();
      }

      public RestrictsProtos.Restricts.Gender getGenders(int paramInt)
      {
        return (RestrictsProtos.Restricts.Gender)this.genders_.get(paramInt);
      }

      public int getGendersCount()
      {
        return this.genders_.size();
      }

      public List<RestrictsProtos.Restricts.Gender> getGendersList()
      {
        return Collections.unmodifiableList(this.genders_);
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(RestrictsProtos.Restricts paramRestricts)
      {
        if (paramRestricts == RestrictsProtos.Restricts.getDefaultInstance());
        label95: label225: label244: 
        while (true)
        {
          return this;
          if (!paramRestricts.categories_.isEmpty())
          {
            if (this.categories_.isEmpty())
            {
              this.categories_ = paramRestricts.categories_;
              this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
            }
          }
          else
          {
            if (!paramRestricts.brands_.isEmpty())
            {
              if (!this.brands_.isEmpty())
                break label204;
              this.brands_ = paramRestricts.brands_;
              this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            }
            if (!paramRestricts.genders_.isEmpty())
            {
              if (!this.genders_.isEmpty())
                break label225;
              this.genders_ = paramRestricts.genders_;
              this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
            }
          }
          while (true)
          {
            if (paramRestricts.colors_.isEmpty())
              break label244;
            if (!this.colors_.isEmpty())
              break label246;
            this.colors_ = paramRestricts.colors_;
            this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
            return this;
            ensureCategoriesIsMutable();
            this.categories_.addAll(paramRestricts.categories_);
            break;
            ensureBrandsIsMutable();
            this.brands_.addAll(paramRestricts.brands_);
            break label95;
            ensureGendersIsMutable();
            this.genders_.addAll(paramRestricts.genders_);
          }
        }
        label204: label246: ensureColorsIsMutable();
        this.colors_.addAll(paramRestricts.colors_);
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
          case 18:
            ensureBrandsIsMutable();
            this.brands_.add(paramCodedInputStream.readBytes());
            break;
          case 26:
            RestrictsProtos.Category.Builder localBuilder = RestrictsProtos.Category.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addCategories(localBuilder.buildPartial());
            break;
          case 32:
            RestrictsProtos.Restricts.Gender localGender2 = RestrictsProtos.Restricts.Gender.valueOf(paramCodedInputStream.readEnum());
            if (localGender2 == null)
              continue;
            addGenders(localGender2);
            break;
          case 34:
            int k = paramCodedInputStream.pushLimit(paramCodedInputStream.readRawVarint32());
            while (paramCodedInputStream.getBytesUntilLimit() > 0)
            {
              RestrictsProtos.Restricts.Gender localGender1 = RestrictsProtos.Restricts.Gender.valueOf(paramCodedInputStream.readEnum());
              if (localGender1 != null)
                addGenders(localGender1);
            }
            paramCodedInputStream.popLimit(k);
            break;
          case 40:
            RestrictsProtos.ColorEnum.Color localColor2 = RestrictsProtos.ColorEnum.Color.valueOf(paramCodedInputStream.readEnum());
            if (localColor2 == null)
              continue;
            addColors(localColor2);
            break;
          case 42:
          }
          int j = paramCodedInputStream.pushLimit(paramCodedInputStream.readRawVarint32());
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
          {
            RestrictsProtos.ColorEnum.Color localColor1 = RestrictsProtos.ColorEnum.Color.valueOf(paramCodedInputStream.readEnum());
            if (localColor1 != null)
              addColors(localColor1);
          }
          paramCodedInputStream.popLimit(j);
        }
      }

      public Builder removeCategories(int paramInt)
      {
        ensureCategoriesIsMutable();
        this.categories_.remove(paramInt);
        return this;
      }

      public Builder setBrands(int paramInt, String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureBrandsIsMutable();
        this.brands_.set(paramInt, paramString);
        return this;
      }

      public Builder setCategories(int paramInt, RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setCategories(int paramInt, RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.set(paramInt, paramCategory);
        return this;
      }

      public Builder setColors(int paramInt, RestrictsProtos.ColorEnum.Color paramColor)
      {
        if (paramColor == null)
          throw new NullPointerException();
        ensureColorsIsMutable();
        this.colors_.set(paramInt, paramColor);
        return this;
      }

      public Builder setGenders(int paramInt, RestrictsProtos.Restricts.Gender paramGender)
      {
        if (paramGender == null)
          throw new NullPointerException();
        ensureGendersIsMutable();
        this.genders_.set(paramInt, paramGender);
        return this;
      }
    }

    public static enum Gender
      implements Internal.EnumLite
    {
      public static final int MEN_VALUE = 2;
      public static final int NEUTRAL_VALUE = 3;
      public static final int UNKNOWN_VALUE = 4;
      public static final int WOMEN_VALUE = 1;
      private static Internal.EnumLiteMap<Gender> internalValueMap = new Internal.EnumLiteMap()
      {
        public RestrictsProtos.Restricts.Gender findValueByNumber(int paramAnonymousInt)
        {
          return RestrictsProtos.Restricts.Gender.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        MEN = new Gender("MEN", 1, 1, 2);
        NEUTRAL = new Gender("NEUTRAL", 2, 2, 3);
        UNKNOWN = new Gender("UNKNOWN", 3, 3, 4);
        Gender[] arrayOfGender = new Gender[4];
        arrayOfGender[0] = WOMEN;
        arrayOfGender[1] = MEN;
        arrayOfGender[2] = NEUTRAL;
        arrayOfGender[3] = UNKNOWN;
      }

      private Gender(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Gender> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Gender valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return WOMEN;
        case 2:
          return MEN;
        case 3:
          return NEUTRAL;
        case 4:
        }
        return UNKNOWN;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface RestrictsOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getBrands(int paramInt);

    public abstract int getBrandsCount();

    public abstract List<String> getBrandsList();

    public abstract RestrictsProtos.Category getCategories(int paramInt);

    public abstract int getCategoriesCount();

    public abstract List<RestrictsProtos.Category> getCategoriesList();

    public abstract RestrictsProtos.ColorEnum.Color getColors(int paramInt);

    public abstract int getColorsCount();

    public abstract List<RestrictsProtos.ColorEnum.Color> getColorsList();

    public abstract RestrictsProtos.Restricts.Gender getGenders(int paramInt);

    public abstract int getGendersCount();

    public abstract List<RestrictsProtos.Restricts.Gender> getGendersList();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.RestrictsProtos
 * JD-Core Version:    0.6.2
 */