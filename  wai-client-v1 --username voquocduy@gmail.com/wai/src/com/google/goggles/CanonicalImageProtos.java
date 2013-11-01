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

public final class CanonicalImageProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class CanonicalImage extends GeneratedMessageLite.ExtendableMessage<CanonicalImage>
    implements CanonicalImageProtos.CanonicalImageOrBuilder
  {
    public static final int IMAGE_URL_FIELD_NUMBER = 1;
    public static final int PREVIEW_URL_FIELD_NUMBER = 4;
    public static final int REFERRER_URL_FIELD_NUMBER = 3;
    public static final int THUMBNAIL_URL_FIELD_NUMBER = 2;
    private static final CanonicalImage defaultInstance = new CanonicalImage(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object imageUrl_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object previewUrl_;
    private Object referrerUrl_;
    private Object thumbnailUrl_;

    static
    {
      defaultInstance.initFields();
    }

    private CanonicalImage(Builder paramBuilder)
    {
      super();
    }

    private CanonicalImage(boolean paramBoolean)
    {
    }

    public static CanonicalImage getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getImageUrlBytes()
    {
      Object localObject = this.imageUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.imageUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getPreviewUrlBytes()
    {
      Object localObject = this.previewUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.previewUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getReferrerUrlBytes()
    {
      Object localObject = this.referrerUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.referrerUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getThumbnailUrlBytes()
    {
      Object localObject = this.thumbnailUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.thumbnailUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.imageUrl_ = "";
      this.thumbnailUrl_ = "";
      this.previewUrl_ = "";
      this.referrerUrl_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(CanonicalImage paramCanonicalImage)
    {
      return newBuilder().mergeFrom(paramCanonicalImage);
    }

    public static CanonicalImage parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static CanonicalImage parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static CanonicalImage parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static CanonicalImage parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static CanonicalImage parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static CanonicalImage parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static CanonicalImage parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static CanonicalImage parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static CanonicalImage parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static CanonicalImage parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public CanonicalImage getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getImageUrl()
    {
      Object localObject = this.imageUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.imageUrl_ = str;
      return str;
    }

    public String getPreviewUrl()
    {
      Object localObject = this.previewUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.previewUrl_ = str;
      return str;
    }

    public String getReferrerUrl()
    {
      Object localObject = this.referrerUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.referrerUrl_ = str;
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getImageUrlBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getThumbnailUrlBytes());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBytesSize(3, getReferrerUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(4, getPreviewUrlBytes());
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public String getThumbnailUrl()
    {
      Object localObject = this.thumbnailUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.thumbnailUrl_ = str;
      return str;
    }

    public boolean hasImageUrl()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasPreviewUrl()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasReferrerUrl()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasThumbnailUrl()
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
        paramCodedOutputStream.writeBytes(1, getImageUrlBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getThumbnailUrlBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(3, getReferrerUrlBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(4, getPreviewUrlBytes());
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<CanonicalImageProtos.CanonicalImage, Builder>
      implements CanonicalImageProtos.CanonicalImageOrBuilder
    {
      private int bitField0_;
      private Object imageUrl_ = "";
      private Object previewUrl_ = "";
      private Object referrerUrl_ = "";
      private Object thumbnailUrl_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private CanonicalImageProtos.CanonicalImage buildParsed()
        throws InvalidProtocolBufferException
      {
        CanonicalImageProtos.CanonicalImage localCanonicalImage = buildPartial();
        if (!localCanonicalImage.isInitialized())
          throw newUninitializedMessageException(localCanonicalImage).asInvalidProtocolBufferException();
        return localCanonicalImage;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public CanonicalImageProtos.CanonicalImage build()
      {
        CanonicalImageProtos.CanonicalImage localCanonicalImage = buildPartial();
        if (!localCanonicalImage.isInitialized())
          throw newUninitializedMessageException(localCanonicalImage);
        return localCanonicalImage;
      }

      public CanonicalImageProtos.CanonicalImage buildPartial()
      {
        int i = 1;
        CanonicalImageProtos.CanonicalImage localCanonicalImage = new CanonicalImageProtos.CanonicalImage(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          CanonicalImageProtos.CanonicalImage.access$302(localCanonicalImage, this.imageUrl_);
          if ((j & 0x2) == 2)
            i |= 2;
          CanonicalImageProtos.CanonicalImage.access$402(localCanonicalImage, this.thumbnailUrl_);
          if ((j & 0x4) == 4)
            i |= 4;
          CanonicalImageProtos.CanonicalImage.access$502(localCanonicalImage, this.previewUrl_);
          if ((j & 0x8) == 8)
            i |= 8;
          CanonicalImageProtos.CanonicalImage.access$602(localCanonicalImage, this.referrerUrl_);
          CanonicalImageProtos.CanonicalImage.access$702(localCanonicalImage, i);
          return localCanonicalImage;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.imageUrl_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.thumbnailUrl_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.previewUrl_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.referrerUrl_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearImageUrl()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.imageUrl_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance().getImageUrl();
        return this;
      }

      public Builder clearPreviewUrl()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.previewUrl_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance().getPreviewUrl();
        return this;
      }

      public Builder clearReferrerUrl()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.referrerUrl_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance().getReferrerUrl();
        return this;
      }

      public Builder clearThumbnailUrl()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.thumbnailUrl_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance().getThumbnailUrl();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public CanonicalImageProtos.CanonicalImage getDefaultInstanceForType()
      {
        return CanonicalImageProtos.CanonicalImage.getDefaultInstance();
      }

      public String getImageUrl()
      {
        Object localObject = this.imageUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.imageUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getPreviewUrl()
      {
        Object localObject = this.previewUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.previewUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getReferrerUrl()
      {
        Object localObject = this.referrerUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.referrerUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getThumbnailUrl()
      {
        Object localObject = this.thumbnailUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.thumbnailUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasImageUrl()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasPreviewUrl()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasReferrerUrl()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasThumbnailUrl()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return extensionsAreInitialized();
      }

      public Builder mergeFrom(CanonicalImageProtos.CanonicalImage paramCanonicalImage)
      {
        if (paramCanonicalImage == CanonicalImageProtos.CanonicalImage.getDefaultInstance())
          return this;
        if (paramCanonicalImage.hasImageUrl())
          setImageUrl(paramCanonicalImage.getImageUrl());
        if (paramCanonicalImage.hasThumbnailUrl())
          setThumbnailUrl(paramCanonicalImage.getThumbnailUrl());
        if (paramCanonicalImage.hasPreviewUrl())
          setPreviewUrl(paramCanonicalImage.getPreviewUrl());
        if (paramCanonicalImage.hasReferrerUrl())
          setReferrerUrl(paramCanonicalImage.getReferrerUrl());
        mergeExtensionFields(paramCanonicalImage);
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
            this.imageUrl_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.thumbnailUrl_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.referrerUrl_ = paramCodedInputStream.readBytes();
            break;
          case 34:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.previewUrl_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setImageUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.imageUrl_ = paramString;
        return this;
      }

      void setImageUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.imageUrl_ = paramByteString;
      }

      public Builder setPreviewUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.previewUrl_ = paramString;
        return this;
      }

      void setPreviewUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.previewUrl_ = paramByteString;
      }

      public Builder setReferrerUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.referrerUrl_ = paramString;
        return this;
      }

      void setReferrerUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.referrerUrl_ = paramByteString;
      }

      public Builder setThumbnailUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.thumbnailUrl_ = paramString;
        return this;
      }

      void setThumbnailUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.thumbnailUrl_ = paramByteString;
      }
    }
  }

  public static abstract interface CanonicalImageOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<CanonicalImageProtos.CanonicalImage>
  {
    public abstract String getImageUrl();

    public abstract String getPreviewUrl();

    public abstract String getReferrerUrl();

    public abstract String getThumbnailUrl();

    public abstract boolean hasImageUrl();

    public abstract boolean hasPreviewUrl();

    public abstract boolean hasReferrerUrl();

    public abstract boolean hasThumbnailUrl();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.CanonicalImageProtos
 * JD-Core Version:    0.6.2
 */