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

public final class DisplayResultProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(DisplayResult.displayResult);
  }

  public static final class DisplayResult extends GeneratedMessageLite
    implements DisplayResultProtos.DisplayResultOrBuilder
  {
    public static final int DISPLAY_RESULT_FIELD_NUMBER = 16045192;
    public static final int SUBTITLE_FIELD_NUMBER = 2;
    public static final int SUBTYPE_FIELD_NUMBER = 4;
    public static final int TITLE_FIELD_NUMBER = 1;
    public static final int TYPE_FIELD_NUMBER = 3;
    private static final DisplayResult defaultInstance = new DisplayResult(true);
    public static final GeneratedMessageLite.GeneratedExtension<ResultProtos.Result, DisplayResult> displayResult = GeneratedMessageLite.newSingularGeneratedExtension(ResultProtos.Result.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 16045192, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object subtitle_;
    private Object subtype_;
    private Object title_;
    private Object type_;

    static
    {
      defaultInstance.initFields();
    }

    private DisplayResult(Builder paramBuilder)
    {
      super();
    }

    private DisplayResult(boolean paramBoolean)
    {
    }

    public static DisplayResult getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getSubtitleBytes()
    {
      Object localObject = this.subtitle_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.subtitle_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getSubtypeBytes()
    {
      Object localObject = this.subtype_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.subtype_ = localByteString;
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

    private void initFields()
    {
      this.title_ = "";
      this.subtitle_ = "";
      this.type_ = "";
      this.subtype_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(DisplayResult paramDisplayResult)
    {
      return newBuilder().mergeFrom(paramDisplayResult);
    }

    public static DisplayResult parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static DisplayResult parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static DisplayResult parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static DisplayResult parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static DisplayResult parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static DisplayResult parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static DisplayResult parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static DisplayResult parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static DisplayResult parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static DisplayResult parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public DisplayResult getDefaultInstanceForType()
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getTitleBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getSubtitleBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(3, getTypeBytes());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBytesSize(4, getSubtypeBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    @Deprecated
    public String getSubtitle()
    {
      Object localObject = this.subtitle_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.subtitle_ = str;
      return str;
    }

    @Deprecated
    public String getSubtype()
    {
      Object localObject = this.subtype_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.subtype_ = str;
      return str;
    }

    @Deprecated
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

    @Deprecated
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

    @Deprecated
    public boolean hasSubtitle()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    @Deprecated
    public boolean hasSubtype()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    @Deprecated
    public boolean hasTitle()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasType()
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
        paramCodedOutputStream.writeBytes(1, getTitleBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getSubtitleBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(3, getTypeBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(4, getSubtypeBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<DisplayResultProtos.DisplayResult, Builder>
      implements DisplayResultProtos.DisplayResultOrBuilder
    {
      private int bitField0_;
      private Object subtitle_ = "";
      private Object subtype_ = "";
      private Object title_ = "";
      private Object type_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private DisplayResultProtos.DisplayResult buildParsed()
        throws InvalidProtocolBufferException
      {
        DisplayResultProtos.DisplayResult localDisplayResult = buildPartial();
        if (!localDisplayResult.isInitialized())
          throw newUninitializedMessageException(localDisplayResult).asInvalidProtocolBufferException();
        return localDisplayResult;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public DisplayResultProtos.DisplayResult build()
      {
        DisplayResultProtos.DisplayResult localDisplayResult = buildPartial();
        if (!localDisplayResult.isInitialized())
          throw newUninitializedMessageException(localDisplayResult);
        return localDisplayResult;
      }

      public DisplayResultProtos.DisplayResult buildPartial()
      {
        int i = 1;
        DisplayResultProtos.DisplayResult localDisplayResult = new DisplayResultProtos.DisplayResult(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          DisplayResultProtos.DisplayResult.access$302(localDisplayResult, this.title_);
          if ((j & 0x2) == 2)
            i |= 2;
          DisplayResultProtos.DisplayResult.access$402(localDisplayResult, this.subtitle_);
          if ((j & 0x4) == 4)
            i |= 4;
          DisplayResultProtos.DisplayResult.access$502(localDisplayResult, this.type_);
          if ((j & 0x8) == 8)
            i |= 8;
          DisplayResultProtos.DisplayResult.access$602(localDisplayResult, this.subtype_);
          DisplayResultProtos.DisplayResult.access$702(localDisplayResult, i);
          return localDisplayResult;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.title_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.subtitle_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.type_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.subtype_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSubtitle()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.subtitle_ = DisplayResultProtos.DisplayResult.getDefaultInstance().getSubtitle();
        return this;
      }

      @Deprecated
      public Builder clearSubtype()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.subtype_ = DisplayResultProtos.DisplayResult.getDefaultInstance().getSubtype();
        return this;
      }

      @Deprecated
      public Builder clearTitle()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.title_ = DisplayResultProtos.DisplayResult.getDefaultInstance().getTitle();
        return this;
      }

      @Deprecated
      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.type_ = DisplayResultProtos.DisplayResult.getDefaultInstance().getType();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public DisplayResultProtos.DisplayResult getDefaultInstanceForType()
      {
        return DisplayResultProtos.DisplayResult.getDefaultInstance();
      }

      @Deprecated
      public String getSubtitle()
      {
        Object localObject = this.subtitle_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.subtitle_ = str;
          return str;
        }
        return (String)localObject;
      }

      @Deprecated
      public String getSubtype()
      {
        Object localObject = this.subtype_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.subtype_ = str;
          return str;
        }
        return (String)localObject;
      }

      @Deprecated
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

      @Deprecated
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

      @Deprecated
      public boolean hasSubtitle()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      @Deprecated
      public boolean hasSubtype()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      @Deprecated
      public boolean hasTitle()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasType()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(DisplayResultProtos.DisplayResult paramDisplayResult)
      {
        if (paramDisplayResult == DisplayResultProtos.DisplayResult.getDefaultInstance());
        do
        {
          return this;
          if (paramDisplayResult.hasTitle())
            setTitle(paramDisplayResult.getTitle());
          if (paramDisplayResult.hasSubtitle())
            setSubtitle(paramDisplayResult.getSubtitle());
          if (paramDisplayResult.hasType())
            setType(paramDisplayResult.getType());
        }
        while (!paramDisplayResult.hasSubtype());
        setSubtype(paramDisplayResult.getSubtype());
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
            this.subtitle_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.type_ = paramCodedInputStream.readBytes();
            break;
          case 34:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.subtype_ = paramCodedInputStream.readBytes();
        }
      }

      @Deprecated
      public Builder setSubtitle(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.subtitle_ = paramString;
        return this;
      }

      void setSubtitle(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.subtitle_ = paramByteString;
      }

      @Deprecated
      public Builder setSubtype(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.subtype_ = paramString;
        return this;
      }

      void setSubtype(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.subtype_ = paramByteString;
      }

      @Deprecated
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

      @Deprecated
      public Builder setType(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.type_ = paramString;
        return this;
      }

      void setType(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.type_ = paramByteString;
      }
    }
  }

  public static abstract interface DisplayResultOrBuilder extends MessageLiteOrBuilder
  {
    @Deprecated
    public abstract String getSubtitle();

    @Deprecated
    public abstract String getSubtype();

    @Deprecated
    public abstract String getTitle();

    @Deprecated
    public abstract String getType();

    @Deprecated
    public abstract boolean hasSubtitle();

    @Deprecated
    public abstract boolean hasSubtype();

    @Deprecated
    public abstract boolean hasTitle();

    @Deprecated
    public abstract boolean hasType();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.DisplayResultProtos
 * JD-Core Version:    0.6.2
 */