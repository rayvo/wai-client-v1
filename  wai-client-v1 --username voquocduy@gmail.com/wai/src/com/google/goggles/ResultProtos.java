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

public final class ResultProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Result extends GeneratedMessageLite.ExtendableMessage<Result>
    implements ResultProtos.ResultOrBuilder
  {
    public static final int NAME_FIELD_NUMBER = 1;
    public static final int TYPE_FIELD_NUMBER = 2;
    private static final Result defaultInstance = new Result(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object name_;
    private Object type_;

    static
    {
      defaultInstance.initFields();
    }

    private Result(Builder paramBuilder)
    {
      super();
    }

    private Result(boolean paramBoolean)
    {
    }

    public static Result getDefaultInstance()
    {
      return defaultInstance;
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
      this.name_ = "";
      this.type_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Result paramResult)
    {
      return newBuilder().mergeFrom(paramResult);
    }

    public static Result parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Result parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Result parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Result parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Result parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Result parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Result parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Result parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Result parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Result parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Result getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getNameBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getTypeBytes());
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
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
    public boolean hasName()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasType()
    {
      return (0x2 & this.bitField0_) == 2;
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
        paramCodedOutputStream.writeBytes(1, getNameBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getTypeBytes());
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<ResultProtos.Result, Builder>
      implements ResultProtos.ResultOrBuilder
    {
      private int bitField0_;
      private Object name_ = "";
      private Object type_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ResultProtos.Result buildParsed()
        throws InvalidProtocolBufferException
      {
        ResultProtos.Result localResult = buildPartial();
        if (!localResult.isInitialized())
          throw newUninitializedMessageException(localResult).asInvalidProtocolBufferException();
        return localResult;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ResultProtos.Result build()
      {
        ResultProtos.Result localResult = buildPartial();
        if (!localResult.isInitialized())
          throw newUninitializedMessageException(localResult);
        return localResult;
      }

      public ResultProtos.Result buildPartial()
      {
        int i = 1;
        ResultProtos.Result localResult = new ResultProtos.Result(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ResultProtos.Result.access$302(localResult, this.name_);
          if ((j & 0x2) == 2)
            i |= 2;
          ResultProtos.Result.access$402(localResult, this.type_);
          ResultProtos.Result.access$502(localResult, i);
          return localResult;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.name_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearName()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.name_ = ResultProtos.Result.getDefaultInstance().getName();
        return this;
      }

      @Deprecated
      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.type_ = ResultProtos.Result.getDefaultInstance().getType();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ResultProtos.Result getDefaultInstanceForType()
      {
        return ResultProtos.Result.getDefaultInstance();
      }

      @Deprecated
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
      public boolean hasName()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasType()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(ResultProtos.Result paramResult)
      {
        if (paramResult == ResultProtos.Result.getDefaultInstance())
          return this;
        if (paramResult.hasName())
          setName(paramResult.getName());
        if (paramResult.hasType())
          setType(paramResult.getType());
        mergeExtensionFields(paramResult);
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
            this.name_ = paramCodedInputStream.readBytes();
            break;
          case 18:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.type_ = paramCodedInputStream.readBytes();
        }
      }

      @Deprecated
      public Builder setName(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.name_ = paramString;
        return this;
      }

      void setName(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.name_ = paramByteString;
      }

      @Deprecated
      public Builder setType(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.type_ = paramString;
        return this;
      }

      void setType(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.type_ = paramByteString;
      }
    }
  }

  public static abstract interface ResultOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<ResultProtos.Result>
  {
    @Deprecated
    public abstract String getName();

    @Deprecated
    public abstract String getType();

    @Deprecated
    public abstract boolean hasName();

    @Deprecated
    public abstract boolean hasType();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ResultProtos
 * JD-Core Version:    0.6.2
 */