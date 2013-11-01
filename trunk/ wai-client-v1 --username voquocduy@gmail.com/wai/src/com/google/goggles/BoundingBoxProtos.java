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

public final class BoundingBoxProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class BoundingBox extends GeneratedMessageLite
    implements BoundingBoxProtos.BoundingBoxOrBuilder
  {
    public static final int HEIGHT_FIELD_NUMBER = 4;
    public static final int WIDTH_FIELD_NUMBER = 2;
    public static final int X_FIELD_NUMBER = 1;
    public static final int Y_FIELD_NUMBER = 3;
    private static final BoundingBox defaultInstance = new BoundingBox(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int height_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int width_;
    private int x_;
    private int y_;

    static
    {
      defaultInstance.initFields();
    }

    private BoundingBox(Builder paramBuilder)
    {
      super();
    }

    private BoundingBox(boolean paramBoolean)
    {
    }

    public static BoundingBox getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.x_ = 0;
      this.width_ = 0;
      this.y_ = 0;
      this.height_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(BoundingBox paramBoundingBox)
    {
      return newBuilder().mergeFrom(paramBoundingBox);
    }

    public static BoundingBox parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static BoundingBox parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static BoundingBox parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static BoundingBox parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static BoundingBox parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static BoundingBox parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static BoundingBox parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static BoundingBox parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static BoundingBox parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static BoundingBox parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public BoundingBox getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getHeight()
    {
      return this.height_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.x_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeInt32Size(2, this.width_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.y_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeInt32Size(4, this.height_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getWidth()
    {
      return this.width_;
    }

    public int getX()
    {
      return this.x_;
    }

    public int getY()
    {
      return this.y_;
    }

    public boolean hasHeight()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasWidth()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasX()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasY()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = 1;
      int j = this.memoizedIsInitialized;
      if (j != -1)
      {
        if (j == i);
        while (true)
        {
          return i;
          i = 0;
        }
      }
      if (!hasX())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasWidth())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasY())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasHeight())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      this.memoizedIsInitialized = i;
      return i;
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
        paramCodedOutputStream.writeInt32(1, this.x_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt32(2, this.width_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.y_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(4, this.height_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<BoundingBoxProtos.BoundingBox, Builder>
      implements BoundingBoxProtos.BoundingBoxOrBuilder
    {
      private int bitField0_;
      private int height_;
      private int width_;
      private int x_;
      private int y_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private BoundingBoxProtos.BoundingBox buildParsed()
        throws InvalidProtocolBufferException
      {
        BoundingBoxProtos.BoundingBox localBoundingBox = buildPartial();
        if (!localBoundingBox.isInitialized())
          throw newUninitializedMessageException(localBoundingBox).asInvalidProtocolBufferException();
        return localBoundingBox;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public BoundingBoxProtos.BoundingBox build()
      {
        BoundingBoxProtos.BoundingBox localBoundingBox = buildPartial();
        if (!localBoundingBox.isInitialized())
          throw newUninitializedMessageException(localBoundingBox);
        return localBoundingBox;
      }

      public BoundingBoxProtos.BoundingBox buildPartial()
      {
        int i = 1;
        BoundingBoxProtos.BoundingBox localBoundingBox = new BoundingBoxProtos.BoundingBox(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          BoundingBoxProtos.BoundingBox.access$302(localBoundingBox, this.x_);
          if ((j & 0x2) == 2)
            i |= 2;
          BoundingBoxProtos.BoundingBox.access$402(localBoundingBox, this.width_);
          if ((j & 0x4) == 4)
            i |= 4;
          BoundingBoxProtos.BoundingBox.access$502(localBoundingBox, this.y_);
          if ((j & 0x8) == 8)
            i |= 8;
          BoundingBoxProtos.BoundingBox.access$602(localBoundingBox, this.height_);
          BoundingBoxProtos.BoundingBox.access$702(localBoundingBox, i);
          return localBoundingBox;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.x_ = 0;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.width_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.y_ = 0;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.height_ = 0;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearHeight()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.height_ = 0;
        return this;
      }

      public Builder clearWidth()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.width_ = 0;
        return this;
      }

      public Builder clearX()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.x_ = 0;
        return this;
      }

      public Builder clearY()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.y_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public BoundingBoxProtos.BoundingBox getDefaultInstanceForType()
      {
        return BoundingBoxProtos.BoundingBox.getDefaultInstance();
      }

      public int getHeight()
      {
        return this.height_;
      }

      public int getWidth()
      {
        return this.width_;
      }

      public int getX()
      {
        return this.x_;
      }

      public int getY()
      {
        return this.y_;
      }

      public boolean hasHeight()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasWidth()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasX()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasY()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        if (!hasX());
        while ((!hasWidth()) || (!hasY()) || (!hasHeight()))
          return false;
        return true;
      }

      public Builder mergeFrom(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == BoundingBoxProtos.BoundingBox.getDefaultInstance());
        do
        {
          return this;
          if (paramBoundingBox.hasX())
            setX(paramBoundingBox.getX());
          if (paramBoundingBox.hasWidth())
            setWidth(paramBoundingBox.getWidth());
          if (paramBoundingBox.hasY())
            setY(paramBoundingBox.getY());
        }
        while (!paramBoundingBox.hasHeight());
        setHeight(paramBoundingBox.getHeight());
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
            this.x_ = paramCodedInputStream.readInt32();
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.width_ = paramCodedInputStream.readInt32();
            break;
          case 24:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.y_ = paramCodedInputStream.readInt32();
            break;
          case 32:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.height_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setHeight(int paramInt)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.height_ = paramInt;
        return this;
      }

      public Builder setWidth(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.width_ = paramInt;
        return this;
      }

      public Builder setX(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.x_ = paramInt;
        return this;
      }

      public Builder setY(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.y_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface BoundingBoxOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getHeight();

    public abstract int getWidth();

    public abstract int getX();

    public abstract int getY();

    public abstract boolean hasHeight();

    public abstract boolean hasWidth();

    public abstract boolean hasX();

    public abstract boolean hasY();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.BoundingBoxProtos
 * JD-Core Version:    0.6.2
 */