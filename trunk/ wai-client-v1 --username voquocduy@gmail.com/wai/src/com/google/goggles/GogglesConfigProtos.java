package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class GogglesConfigProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class GogglesConfig extends GeneratedMessageLite.ExtendableMessage<GogglesConfig>
    implements GogglesConfigProtos.GogglesConfigOrBuilder
  {
    public static final int CAN_LOG_IMAGE_FIELD_NUMBER = 7;
    public static final int CAN_STORE_TO_SEARCH_HISTORY_FIELD_NUMBER = 5;
    public static final int COUNTRY_FIELD_NUMBER = 6;
    public static final int LANGUAGE_PREF_FIELD_NUMBER = 4;
    public static final int MAX_RESULTS_FIELD_NUMBER = 1;
    private static final GogglesConfig defaultInstance = new GogglesConfig(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private boolean canLogImage_;
    private boolean canStoreToSearchHistory_;
    private Object country_;
    private Object languagePref_;
    private int maxResults_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private GogglesConfig(Builder paramBuilder)
    {
      super();
    }

    private GogglesConfig(boolean paramBoolean)
    {
    }

    private ByteString getCountryBytes()
    {
      Object localObject = this.country_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.country_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static GogglesConfig getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getLanguagePrefBytes()
    {
      Object localObject = this.languagePref_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.languagePref_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.maxResults_ = 10;
      this.languagePref_ = "";
      this.country_ = "";
      this.canStoreToSearchHistory_ = false;
      this.canLogImage_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(GogglesConfig paramGogglesConfig)
    {
      return newBuilder().mergeFrom(paramGogglesConfig);
    }

    public static GogglesConfig parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesConfig parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static GogglesConfig parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static GogglesConfig parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesConfig parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static GogglesConfig parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static GogglesConfig parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static GogglesConfig parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static GogglesConfig parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static GogglesConfig parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public boolean getCanLogImage()
    {
      return this.canLogImage_;
    }

    public boolean getCanStoreToSearchHistory()
    {
      return this.canStoreToSearchHistory_;
    }

    public String getCountry()
    {
      Object localObject = this.country_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.country_ = str;
      return str;
    }

    public GogglesConfig getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getLanguagePref()
    {
      Object localObject = this.languagePref_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.languagePref_ = str;
      return str;
    }

    public int getMaxResults()
    {
      return this.maxResults_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.maxResults_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(4, getLanguagePrefBytes());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBoolSize(5, this.canStoreToSearchHistory_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(6, getCountryBytes());
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeBoolSize(7, this.canLogImage_);
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public boolean hasCanLogImage()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasCanStoreToSearchHistory()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasCountry()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasLanguagePref()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasMaxResults()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!extensionsAreInitialized())
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
      GeneratedMessageLite.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeInt32(1, this.maxResults_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(4, getLanguagePrefBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBool(5, this.canStoreToSearchHistory_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(6, getCountryBytes());
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBool(7, this.canLogImage_);
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<GogglesConfigProtos.GogglesConfig, Builder>
      implements GogglesConfigProtos.GogglesConfigOrBuilder
    {
      private int bitField0_;
      private boolean canLogImage_;
      private boolean canStoreToSearchHistory_;
      private Object country_ = "";
      private Object languagePref_ = "";
      private int maxResults_ = 10;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private GogglesConfigProtos.GogglesConfig buildParsed()
        throws InvalidProtocolBufferException
      {
        GogglesConfigProtos.GogglesConfig localGogglesConfig = buildPartial();
        if (!localGogglesConfig.isInitialized())
          throw newUninitializedMessageException(localGogglesConfig).asInvalidProtocolBufferException();
        return localGogglesConfig;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public GogglesConfigProtos.GogglesConfig build()
      {
        GogglesConfigProtos.GogglesConfig localGogglesConfig = buildPartial();
        if (!localGogglesConfig.isInitialized())
          throw newUninitializedMessageException(localGogglesConfig);
        return localGogglesConfig;
      }

      public GogglesConfigProtos.GogglesConfig buildPartial()
      {
        int i = 1;
        GogglesConfigProtos.GogglesConfig localGogglesConfig = new GogglesConfigProtos.GogglesConfig(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          GogglesConfigProtos.GogglesConfig.access$302(localGogglesConfig, this.maxResults_);
          if ((j & 0x2) == 2)
            i |= 2;
          GogglesConfigProtos.GogglesConfig.access$402(localGogglesConfig, this.languagePref_);
          if ((j & 0x4) == 4)
            i |= 4;
          GogglesConfigProtos.GogglesConfig.access$502(localGogglesConfig, this.country_);
          if ((j & 0x8) == 8)
            i |= 8;
          GogglesConfigProtos.GogglesConfig.access$602(localGogglesConfig, this.canStoreToSearchHistory_);
          if ((j & 0x10) == 16)
            i |= 16;
          GogglesConfigProtos.GogglesConfig.access$702(localGogglesConfig, this.canLogImage_);
          GogglesConfigProtos.GogglesConfig.access$802(localGogglesConfig, i);
          return localGogglesConfig;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.maxResults_ = 10;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.languagePref_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.country_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.canStoreToSearchHistory_ = false;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.canLogImage_ = false;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      public Builder clearCanLogImage()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.canLogImage_ = false;
        return this;
      }

      public Builder clearCanStoreToSearchHistory()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.canStoreToSearchHistory_ = false;
        return this;
      }

      public Builder clearCountry()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.country_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance().getCountry();
        return this;
      }

      public Builder clearLanguagePref()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.languagePref_ = GogglesConfigProtos.GogglesConfig.getDefaultInstance().getLanguagePref();
        return this;
      }

      public Builder clearMaxResults()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.maxResults_ = 10;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public boolean getCanLogImage()
      {
        return this.canLogImage_;
      }

      public boolean getCanStoreToSearchHistory()
      {
        return this.canStoreToSearchHistory_;
      }

      public String getCountry()
      {
        Object localObject = this.country_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.country_ = str;
          return str;
        }
        return (String)localObject;
      }

      public GogglesConfigProtos.GogglesConfig getDefaultInstanceForType()
      {
        return GogglesConfigProtos.GogglesConfig.getDefaultInstance();
      }

      public String getLanguagePref()
      {
        Object localObject = this.languagePref_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.languagePref_ = str;
          return str;
        }
        return (String)localObject;
      }

      public int getMaxResults()
      {
        return this.maxResults_;
      }

      public boolean hasCanLogImage()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasCanStoreToSearchHistory()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasCountry()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasLanguagePref()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasMaxResults()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(GogglesConfigProtos.GogglesConfig paramGogglesConfig)
      {
        if (paramGogglesConfig == GogglesConfigProtos.GogglesConfig.getDefaultInstance())
          return this;
        if (paramGogglesConfig.hasMaxResults())
          setMaxResults(paramGogglesConfig.getMaxResults());
        if (paramGogglesConfig.hasLanguagePref())
          setLanguagePref(paramGogglesConfig.getLanguagePref());
        if (paramGogglesConfig.hasCountry())
          setCountry(paramGogglesConfig.getCountry());
        if (paramGogglesConfig.hasCanStoreToSearchHistory())
          setCanStoreToSearchHistory(paramGogglesConfig.getCanStoreToSearchHistory());
        if (paramGogglesConfig.hasCanLogImage())
          setCanLogImage(paramGogglesConfig.getCanLogImage());
        mergeExtensionFields(paramGogglesConfig);
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
            this.maxResults_ = paramCodedInputStream.readInt32();
            break;
          case 34:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.languagePref_ = paramCodedInputStream.readBytes();
            break;
          case 40:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.canStoreToSearchHistory_ = paramCodedInputStream.readBool();
            break;
          case 50:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.country_ = paramCodedInputStream.readBytes();
            break;
          case 56:
          }
          this.bitField0_ = (0x10 | this.bitField0_);
          this.canLogImage_ = paramCodedInputStream.readBool();
        }
      }

      public Builder setCanLogImage(boolean paramBoolean)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.canLogImage_ = paramBoolean;
        return this;
      }

      public Builder setCanStoreToSearchHistory(boolean paramBoolean)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.canStoreToSearchHistory_ = paramBoolean;
        return this;
      }

      public Builder setCountry(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.country_ = paramString;
        return this;
      }

      void setCountry(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.country_ = paramByteString;
      }

      public Builder setLanguagePref(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.languagePref_ = paramString;
        return this;
      }

      void setLanguagePref(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.languagePref_ = paramByteString;
      }

      public Builder setMaxResults(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.maxResults_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface GogglesConfigOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<GogglesConfigProtos.GogglesConfig>
  {
    public abstract boolean getCanLogImage();

    public abstract boolean getCanStoreToSearchHistory();

    public abstract String getCountry();

    public abstract String getLanguagePref();

    public abstract int getMaxResults();

    public abstract boolean hasCanLogImage();

    public abstract boolean hasCanStoreToSearchHistory();

    public abstract boolean hasCountry();

    public abstract boolean hasLanguagePref();

    public abstract boolean hasMaxResults();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.GogglesConfigProtos
 * JD-Core Version:    0.6.2
 */