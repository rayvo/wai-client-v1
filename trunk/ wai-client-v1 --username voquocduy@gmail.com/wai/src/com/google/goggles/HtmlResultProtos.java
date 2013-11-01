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

public final class HtmlResultProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(HtmlResult.htmlResult);
  }

  public static final class HtmlResult extends GeneratedMessageLite
    implements HtmlResultProtos.HtmlResultOrBuilder
  {
    public static final int HTML_DOCUMENT_FIELD_NUMBER = 1;
    public static final int HTML_RESULT_FIELD_NUMBER = 15693652;
    public static final int URL_FIELD_NUMBER = 2;
    public static final int VALID_MS_FIELD_NUMBER = 3;
    private static final HtmlResult defaultInstance = new HtmlResult(true);
    public static final GeneratedMessageLite.GeneratedExtension<ResultProtos.Result, HtmlResult> htmlResult = GeneratedMessageLite.newSingularGeneratedExtension(ResultProtos.Result.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 15693652, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object htmlDocument_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object url_;
    private int validMs_;

    static
    {
      defaultInstance.initFields();
    }

    private HtmlResult(Builder paramBuilder)
    {
      super();
    }

    private HtmlResult(boolean paramBoolean)
    {
    }

    public static HtmlResult getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getHtmlDocumentBytes()
    {
      Object localObject = this.htmlDocument_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.htmlDocument_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getUrlBytes()
    {
      Object localObject = this.url_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.url_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.htmlDocument_ = "";
      this.url_ = "";
      this.validMs_ = 3600000;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(HtmlResult paramHtmlResult)
    {
      return newBuilder().mergeFrom(paramHtmlResult);
    }

    public static HtmlResult parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static HtmlResult parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static HtmlResult parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static HtmlResult parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static HtmlResult parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static HtmlResult parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static HtmlResult parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static HtmlResult parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static HtmlResult parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static HtmlResult parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public HtmlResult getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
    public String getHtmlDocument()
    {
      Object localObject = this.htmlDocument_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.htmlDocument_ = str;
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getHtmlDocumentBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.validMs_);
      this.memoizedSerializedSize = k;
      return k;
    }

    @Deprecated
    public String getUrl()
    {
      Object localObject = this.url_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.url_ = str;
      return str;
    }

    @Deprecated
    public int getValidMs()
    {
      return this.validMs_;
    }

    @Deprecated
    public boolean hasHtmlDocument()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasUrl()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    @Deprecated
    public boolean hasValidMs()
    {
      return (0x4 & this.bitField0_) == 4;
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
        paramCodedOutputStream.writeBytes(1, getHtmlDocumentBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.validMs_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<HtmlResultProtos.HtmlResult, Builder>
      implements HtmlResultProtos.HtmlResultOrBuilder
    {
      private int bitField0_;
      private Object htmlDocument_ = "";
      private Object url_ = "";
      private int validMs_ = 3600000;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private HtmlResultProtos.HtmlResult buildParsed()
        throws InvalidProtocolBufferException
      {
        HtmlResultProtos.HtmlResult localHtmlResult = buildPartial();
        if (!localHtmlResult.isInitialized())
          throw newUninitializedMessageException(localHtmlResult).asInvalidProtocolBufferException();
        return localHtmlResult;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public HtmlResultProtos.HtmlResult build()
      {
        HtmlResultProtos.HtmlResult localHtmlResult = buildPartial();
        if (!localHtmlResult.isInitialized())
          throw newUninitializedMessageException(localHtmlResult);
        return localHtmlResult;
      }

      public HtmlResultProtos.HtmlResult buildPartial()
      {
        int i = 1;
        HtmlResultProtos.HtmlResult localHtmlResult = new HtmlResultProtos.HtmlResult(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          HtmlResultProtos.HtmlResult.access$302(localHtmlResult, this.htmlDocument_);
          if ((j & 0x2) == 2)
            i |= 2;
          HtmlResultProtos.HtmlResult.access$402(localHtmlResult, this.url_);
          if ((j & 0x4) == 4)
            i |= 4;
          HtmlResultProtos.HtmlResult.access$502(localHtmlResult, this.validMs_);
          HtmlResultProtos.HtmlResult.access$602(localHtmlResult, i);
          return localHtmlResult;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.htmlDocument_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.url_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.validMs_ = 3600000;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearHtmlDocument()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.htmlDocument_ = HtmlResultProtos.HtmlResult.getDefaultInstance().getHtmlDocument();
        return this;
      }

      @Deprecated
      public Builder clearUrl()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.url_ = HtmlResultProtos.HtmlResult.getDefaultInstance().getUrl();
        return this;
      }

      @Deprecated
      public Builder clearValidMs()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.validMs_ = 3600000;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public HtmlResultProtos.HtmlResult getDefaultInstanceForType()
      {
        return HtmlResultProtos.HtmlResult.getDefaultInstance();
      }

      @Deprecated
      public String getHtmlDocument()
      {
        Object localObject = this.htmlDocument_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.htmlDocument_ = str;
          return str;
        }
        return (String)localObject;
      }

      @Deprecated
      public String getUrl()
      {
        Object localObject = this.url_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.url_ = str;
          return str;
        }
        return (String)localObject;
      }

      @Deprecated
      public int getValidMs()
      {
        return this.validMs_;
      }

      @Deprecated
      public boolean hasHtmlDocument()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasUrl()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      @Deprecated
      public boolean hasValidMs()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(HtmlResultProtos.HtmlResult paramHtmlResult)
      {
        if (paramHtmlResult == HtmlResultProtos.HtmlResult.getDefaultInstance());
        do
        {
          return this;
          if (paramHtmlResult.hasHtmlDocument())
            setHtmlDocument(paramHtmlResult.getHtmlDocument());
          if (paramHtmlResult.hasUrl())
            setUrl(paramHtmlResult.getUrl());
        }
        while (!paramHtmlResult.hasValidMs());
        setValidMs(paramHtmlResult.getValidMs());
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
            this.htmlDocument_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.url_ = paramCodedInputStream.readBytes();
            break;
          case 24:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.validMs_ = paramCodedInputStream.readInt32();
        }
      }

      @Deprecated
      public Builder setHtmlDocument(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.htmlDocument_ = paramString;
        return this;
      }

      void setHtmlDocument(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.htmlDocument_ = paramByteString;
      }

      @Deprecated
      public Builder setUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.url_ = paramString;
        return this;
      }

      void setUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.url_ = paramByteString;
      }

      @Deprecated
      public Builder setValidMs(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.validMs_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface HtmlResultOrBuilder extends MessageLiteOrBuilder
  {
    @Deprecated
    public abstract String getHtmlDocument();

    @Deprecated
    public abstract String getUrl();

    @Deprecated
    public abstract int getValidMs();

    @Deprecated
    public abstract boolean hasHtmlDocument();

    @Deprecated
    public abstract boolean hasUrl();

    @Deprecated
    public abstract boolean hasValidMs();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.HtmlResultProtos
 * JD-Core Version:    0.6.2
 */