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

public final class PuggleDebuggingProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class PuggleDebugging extends GeneratedMessageLite
    implements PuggleDebuggingProtos.PuggleDebuggingOrBuilder
  {
    public static final int SEGMENTATION_FIELD_NUMBER = 1;
    private static final PuggleDebugging defaultInstance = new PuggleDebugging(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private ByteString segmentation_;

    static
    {
      defaultInstance.initFields();
    }

    private PuggleDebugging(Builder paramBuilder)
    {
      super();
    }

    private PuggleDebugging(boolean paramBoolean)
    {
    }

    public static PuggleDebugging getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.segmentation_ = ByteString.EMPTY;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(PuggleDebugging paramPuggleDebugging)
    {
      return newBuilder().mergeFrom(paramPuggleDebugging);
    }

    public static PuggleDebugging parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static PuggleDebugging parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static PuggleDebugging parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static PuggleDebugging parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static PuggleDebugging parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static PuggleDebugging parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static PuggleDebugging parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static PuggleDebugging parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static PuggleDebugging parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static PuggleDebugging parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public PuggleDebugging getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public ByteString getSegmentation()
    {
      return this.segmentation_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, this.segmentation_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasSegmentation()
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
        paramCodedOutputStream.writeBytes(1, this.segmentation_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<PuggleDebuggingProtos.PuggleDebugging, Builder>
      implements PuggleDebuggingProtos.PuggleDebuggingOrBuilder
    {
      private int bitField0_;
      private ByteString segmentation_ = ByteString.EMPTY;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private PuggleDebuggingProtos.PuggleDebugging buildParsed()
        throws InvalidProtocolBufferException
      {
        PuggleDebuggingProtos.PuggleDebugging localPuggleDebugging = buildPartial();
        if (!localPuggleDebugging.isInitialized())
          throw newUninitializedMessageException(localPuggleDebugging).asInvalidProtocolBufferException();
        return localPuggleDebugging;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public PuggleDebuggingProtos.PuggleDebugging build()
      {
        PuggleDebuggingProtos.PuggleDebugging localPuggleDebugging = buildPartial();
        if (!localPuggleDebugging.isInitialized())
          throw newUninitializedMessageException(localPuggleDebugging);
        return localPuggleDebugging;
      }

      public PuggleDebuggingProtos.PuggleDebugging buildPartial()
      {
        int i = 1;
        PuggleDebuggingProtos.PuggleDebugging localPuggleDebugging = new PuggleDebuggingProtos.PuggleDebugging(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          PuggleDebuggingProtos.PuggleDebugging.access$302(localPuggleDebugging, this.segmentation_);
          PuggleDebuggingProtos.PuggleDebugging.access$402(localPuggleDebugging, i);
          return localPuggleDebugging;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.segmentation_ = ByteString.EMPTY;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearSegmentation()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.segmentation_ = PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance().getSegmentation();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public PuggleDebuggingProtos.PuggleDebugging getDefaultInstanceForType()
      {
        return PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance();
      }

      public ByteString getSegmentation()
      {
        return this.segmentation_;
      }

      public boolean hasSegmentation()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(PuggleDebuggingProtos.PuggleDebugging paramPuggleDebugging)
      {
        if (paramPuggleDebugging == PuggleDebuggingProtos.PuggleDebugging.getDefaultInstance());
        while (!paramPuggleDebugging.hasSegmentation())
          return this;
        setSegmentation(paramPuggleDebugging.getSegmentation());
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
          this.bitField0_ = (0x1 | this.bitField0_);
          this.segmentation_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setSegmentation(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.segmentation_ = paramByteString;
        return this;
      }
    }
  }

  public static abstract interface PuggleDebuggingOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ByteString getSegmentation();

    public abstract boolean hasSegmentation();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.PuggleDebuggingProtos
 * JD-Core Version:    0.6.2
 */