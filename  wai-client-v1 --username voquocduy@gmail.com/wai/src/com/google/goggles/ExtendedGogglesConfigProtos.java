package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.GeneratedMessageLite.GeneratedExtension;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class ExtendedGogglesConfigProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(ExtendedGogglesConfig.extendedGogglesConfig);
  }

  public static final class ExtendedGogglesConfig extends GeneratedMessageLite
    implements ExtendedGogglesConfigProtos.ExtendedGogglesConfigOrBuilder
  {
    public static final int ANNOTATION_FIELD_NUMBER = 9;
    public static final int EXTENDED_GOGGLES_CONFIG_FIELD_NUMBER = 15697207;
    public static final int WANT_ANNOTATION_RESULTS_FIELD_NUMBER = 1;
    public static final int WANT_DISPLAY_RESULTS_FIELD_NUMBER = 5;
    public static final int WANT_EYE_CANDY_RESULTS_FIELD_NUMBER = 7;
    public static final int WANT_GENERAL_RESULTS_FIELD_NUMBER = 8;
    public static final int WANT_HTML_RESULTS_FIELD_NUMBER = 2;
    private static final ExtendedGogglesConfig defaultInstance = new ExtendedGogglesConfig(true);
    public static final GeneratedMessageLite.GeneratedExtension<GogglesConfigProtos.GogglesConfig, ExtendedGogglesConfig> extendedGogglesConfig = GeneratedMessageLite.newSingularGeneratedExtension(GogglesConfigProtos.GogglesConfig.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 15697207, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private Object annotation_;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private boolean wantAnnotationResults_;
    private boolean wantDisplayResults_;
    private boolean wantEyeCandyResults_;
    private boolean wantGeneralResults_;
    private boolean wantHtmlResults_;

    static
    {
      defaultInstance.initFields();
    }

    private ExtendedGogglesConfig(Builder paramBuilder)
    {
      super();
    }

    private ExtendedGogglesConfig(boolean paramBoolean)
    {
    }

    private ByteString getAnnotationBytes()
    {
      Object localObject = this.annotation_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.annotation_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static ExtendedGogglesConfig getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.wantAnnotationResults_ = false;
      this.wantHtmlResults_ = false;
      this.wantDisplayResults_ = false;
      this.wantEyeCandyResults_ = false;
      this.wantGeneralResults_ = false;
      this.annotation_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(ExtendedGogglesConfig paramExtendedGogglesConfig)
    {
      return newBuilder().mergeFrom(paramExtendedGogglesConfig);
    }

    public static ExtendedGogglesConfig parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesConfig parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesConfig parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ExtendedGogglesConfig parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getAnnotation()
    {
      Object localObject = this.annotation_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.annotation_ = str;
      return str;
    }

    public ExtendedGogglesConfig getDefaultInstanceForType()
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
        k = 0 + CodedOutputStream.computeBoolSize(1, this.wantAnnotationResults_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBoolSize(2, this.wantHtmlResults_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBoolSize(5, this.wantDisplayResults_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBoolSize(7, this.wantEyeCandyResults_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeBoolSize(8, this.wantGeneralResults_);
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeBytesSize(9, getAnnotationBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean getWantAnnotationResults()
    {
      return this.wantAnnotationResults_;
    }

    @Deprecated
    public boolean getWantDisplayResults()
    {
      return this.wantDisplayResults_;
    }

    public boolean getWantEyeCandyResults()
    {
      return this.wantEyeCandyResults_;
    }

    public boolean getWantGeneralResults()
    {
      return this.wantGeneralResults_;
    }

    @Deprecated
    public boolean getWantHtmlResults()
    {
      return this.wantHtmlResults_;
    }

    public boolean hasAnnotation()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasWantAnnotationResults()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasWantDisplayResults()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasWantEyeCandyResults()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasWantGeneralResults()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    @Deprecated
    public boolean hasWantHtmlResults()
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
        paramCodedOutputStream.writeBool(1, this.wantAnnotationResults_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBool(2, this.wantHtmlResults_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBool(5, this.wantDisplayResults_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBool(7, this.wantEyeCandyResults_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBool(8, this.wantGeneralResults_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(9, getAnnotationBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExtendedGogglesConfigProtos.ExtendedGogglesConfig, Builder>
      implements ExtendedGogglesConfigProtos.ExtendedGogglesConfigOrBuilder
    {
      private Object annotation_ = "";
      private int bitField0_;
      private boolean wantAnnotationResults_;
      private boolean wantDisplayResults_;
      private boolean wantEyeCandyResults_;
      private boolean wantGeneralResults_;
      private boolean wantHtmlResults_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ExtendedGogglesConfigProtos.ExtendedGogglesConfig buildParsed()
        throws InvalidProtocolBufferException
      {
        ExtendedGogglesConfigProtos.ExtendedGogglesConfig localExtendedGogglesConfig = buildPartial();
        if (!localExtendedGogglesConfig.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesConfig).asInvalidProtocolBufferException();
        return localExtendedGogglesConfig;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ExtendedGogglesConfigProtos.ExtendedGogglesConfig build()
      {
        ExtendedGogglesConfigProtos.ExtendedGogglesConfig localExtendedGogglesConfig = buildPartial();
        if (!localExtendedGogglesConfig.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesConfig);
        return localExtendedGogglesConfig;
      }

      public ExtendedGogglesConfigProtos.ExtendedGogglesConfig buildPartial()
      {
        int i = 1;
        ExtendedGogglesConfigProtos.ExtendedGogglesConfig localExtendedGogglesConfig = new ExtendedGogglesConfigProtos.ExtendedGogglesConfig(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$302(localExtendedGogglesConfig, this.wantAnnotationResults_);
          if ((j & 0x2) == 2)
            i |= 2;
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$402(localExtendedGogglesConfig, this.wantHtmlResults_);
          if ((j & 0x4) == 4)
            i |= 4;
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$502(localExtendedGogglesConfig, this.wantDisplayResults_);
          if ((j & 0x8) == 8)
            i |= 8;
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$602(localExtendedGogglesConfig, this.wantEyeCandyResults_);
          if ((j & 0x10) == 16)
            i |= 16;
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$702(localExtendedGogglesConfig, this.wantGeneralResults_);
          if ((j & 0x20) == 32)
            i |= 32;
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$802(localExtendedGogglesConfig, this.annotation_);
          ExtendedGogglesConfigProtos.ExtendedGogglesConfig.access$902(localExtendedGogglesConfig, i);
          return localExtendedGogglesConfig;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.wantAnnotationResults_ = false;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.wantHtmlResults_ = false;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.wantDisplayResults_ = false;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.wantEyeCandyResults_ = false;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.wantGeneralResults_ = false;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.annotation_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        return this;
      }

      public Builder clearAnnotation()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.annotation_ = ExtendedGogglesConfigProtos.ExtendedGogglesConfig.getDefaultInstance().getAnnotation();
        return this;
      }

      public Builder clearWantAnnotationResults()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.wantAnnotationResults_ = false;
        return this;
      }

      @Deprecated
      public Builder clearWantDisplayResults()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.wantDisplayResults_ = false;
        return this;
      }

      public Builder clearWantEyeCandyResults()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.wantEyeCandyResults_ = false;
        return this;
      }

      public Builder clearWantGeneralResults()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.wantGeneralResults_ = false;
        return this;
      }

      @Deprecated
      public Builder clearWantHtmlResults()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.wantHtmlResults_ = false;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getAnnotation()
      {
        Object localObject = this.annotation_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.annotation_ = str;
          return str;
        }
        return (String)localObject;
      }

      public ExtendedGogglesConfigProtos.ExtendedGogglesConfig getDefaultInstanceForType()
      {
        return ExtendedGogglesConfigProtos.ExtendedGogglesConfig.getDefaultInstance();
      }

      public boolean getWantAnnotationResults()
      {
        return this.wantAnnotationResults_;
      }

      @Deprecated
      public boolean getWantDisplayResults()
      {
        return this.wantDisplayResults_;
      }

      public boolean getWantEyeCandyResults()
      {
        return this.wantEyeCandyResults_;
      }

      public boolean getWantGeneralResults()
      {
        return this.wantGeneralResults_;
      }

      @Deprecated
      public boolean getWantHtmlResults()
      {
        return this.wantHtmlResults_;
      }

      public boolean hasAnnotation()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasWantAnnotationResults()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasWantDisplayResults()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasWantEyeCandyResults()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasWantGeneralResults()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      @Deprecated
      public boolean hasWantHtmlResults()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(ExtendedGogglesConfigProtos.ExtendedGogglesConfig paramExtendedGogglesConfig)
      {
        if (paramExtendedGogglesConfig == ExtendedGogglesConfigProtos.ExtendedGogglesConfig.getDefaultInstance());
        do
        {
          return this;
          if (paramExtendedGogglesConfig.hasWantAnnotationResults())
            setWantAnnotationResults(paramExtendedGogglesConfig.getWantAnnotationResults());
          if (paramExtendedGogglesConfig.hasWantHtmlResults())
            setWantHtmlResults(paramExtendedGogglesConfig.getWantHtmlResults());
          if (paramExtendedGogglesConfig.hasWantDisplayResults())
            setWantDisplayResults(paramExtendedGogglesConfig.getWantDisplayResults());
          if (paramExtendedGogglesConfig.hasWantEyeCandyResults())
            setWantEyeCandyResults(paramExtendedGogglesConfig.getWantEyeCandyResults());
          if (paramExtendedGogglesConfig.hasWantGeneralResults())
            setWantGeneralResults(paramExtendedGogglesConfig.getWantGeneralResults());
        }
        while (!paramExtendedGogglesConfig.hasAnnotation());
        setAnnotation(paramExtendedGogglesConfig.getAnnotation());
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
            this.wantAnnotationResults_ = paramCodedInputStream.readBool();
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.wantHtmlResults_ = paramCodedInputStream.readBool();
            break;
          case 40:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.wantDisplayResults_ = paramCodedInputStream.readBool();
            break;
          case 56:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.wantEyeCandyResults_ = paramCodedInputStream.readBool();
            break;
          case 64:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.wantGeneralResults_ = paramCodedInputStream.readBool();
            break;
          case 74:
          }
          this.bitField0_ = (0x20 | this.bitField0_);
          this.annotation_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setAnnotation(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.annotation_ = paramString;
        return this;
      }

      void setAnnotation(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.annotation_ = paramByteString;
      }

      public Builder setWantAnnotationResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.wantAnnotationResults_ = paramBoolean;
        return this;
      }

      @Deprecated
      public Builder setWantDisplayResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.wantDisplayResults_ = paramBoolean;
        return this;
      }

      public Builder setWantEyeCandyResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.wantEyeCandyResults_ = paramBoolean;
        return this;
      }

      public Builder setWantGeneralResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.wantGeneralResults_ = paramBoolean;
        return this;
      }

      @Deprecated
      public Builder setWantHtmlResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.wantHtmlResults_ = paramBoolean;
        return this;
      }
    }
  }

  public static abstract interface ExtendedGogglesConfigOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getAnnotation();

    public abstract boolean getWantAnnotationResults();

    @Deprecated
    public abstract boolean getWantDisplayResults();

    public abstract boolean getWantEyeCandyResults();

    public abstract boolean getWantGeneralResults();

    @Deprecated
    public abstract boolean getWantHtmlResults();

    public abstract boolean hasAnnotation();

    public abstract boolean hasWantAnnotationResults();

    @Deprecated
    public abstract boolean hasWantDisplayResults();

    public abstract boolean hasWantEyeCandyResults();

    public abstract boolean hasWantGeneralResults();

    @Deprecated
    public abstract boolean hasWantHtmlResults();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ExtendedGogglesConfigProtos
 * JD-Core Version:    0.6.2
 */