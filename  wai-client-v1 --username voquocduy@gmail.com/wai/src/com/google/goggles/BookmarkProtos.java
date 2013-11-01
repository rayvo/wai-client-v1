package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.GeneratedMessageLite.ExtendableBuilder;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessage.ExtensionWriter;
import com.google.protobuf.GeneratedMessageLite.ExtendableMessageOrBuilder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class BookmarkProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Bookmark extends GeneratedMessageLite.ExtendableMessage<Bookmark>
    implements BookmarkProtos.BookmarkOrBuilder
  {
    public static final int AUTO_OPEN_FIELD_NUMBER = 7;
    public static final int CREATE_TIME_SEC_FIELD_NUMBER = 3;
    public static final int LATITUDE_FIELD_NUMBER = 5;
    public static final int LONGITUDE_FIELD_NUMBER = 6;
    public static final int OBJECT_ID_FIELD_NUMBER = 1;
    public static final int OPERATION_FIELD_NUMBER = 4;
    public static final int OWNER_ACCOUNT_FIELD_NUMBER = 2;
    private static final Bookmark defaultInstance = new Bookmark(true);
    private static final long serialVersionUID;
    private boolean autoOpen_;
    private int bitField0_;
    private long createTimeSec_;
    private double latitude_;
    private double longitude_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object objectId_;
    private Operation operation_;
    private Object ownerAccount_;

    static
    {
      defaultInstance.initFields();
    }

    private Bookmark(Builder paramBuilder)
    {
      super();
    }

    private Bookmark(boolean paramBoolean)
    {
    }

    public static Bookmark getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getObjectIdBytes()
    {
      Object localObject = this.objectId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.objectId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getOwnerAccountBytes()
    {
      Object localObject = this.ownerAccount_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.ownerAccount_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.objectId_ = "";
      this.ownerAccount_ = "";
      this.createTimeSec_ = 0L;
      this.operation_ = Operation.getDefaultInstance();
      this.latitude_ = 0.0D;
      this.longitude_ = 0.0D;
      this.autoOpen_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$800();
    }

    public static Builder newBuilder(Bookmark paramBookmark)
    {
      return newBuilder().mergeFrom(paramBookmark);
    }

    public static Bookmark parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Bookmark parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Bookmark parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Bookmark parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Bookmark parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Bookmark parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Bookmark parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Bookmark parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Bookmark parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Bookmark parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public boolean getAutoOpen()
    {
      return this.autoOpen_;
    }

    public long getCreateTimeSec()
    {
      return this.createTimeSec_;
    }

    public Bookmark getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public double getLatitude()
    {
      return this.latitude_;
    }

    public double getLongitude()
    {
      return this.longitude_;
    }

    public String getObjectId()
    {
      Object localObject = this.objectId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.objectId_ = str;
      return str;
    }

    public Operation getOperation()
    {
      return this.operation_;
    }

    public String getOwnerAccount()
    {
      Object localObject = this.ownerAccount_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.ownerAccount_ = str;
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getObjectIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getOwnerAccountBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt64Size(3, this.createTimeSec_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(4, this.operation_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeDoubleSize(5, this.latitude_);
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeDoubleSize(6, this.longitude_);
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeBoolSize(7, this.autoOpen_);
      int m = k + extensionsSerializedSize();
      this.memoizedSerializedSize = m;
      return m;
    }

    public boolean hasAutoOpen()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasCreateTimeSec()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasLatitude()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasLongitude()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasObjectId()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasOperation()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasOwnerAccount()
    {
      return (0x2 & this.bitField0_) == 2;
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
      if (!hasObjectId())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasOwnerAccount())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasCreateTimeSec())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasOperation())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasAutoOpen())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!extensionsAreInitialized())
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
      GeneratedMessageLite.ExtendableMessage.ExtensionWriter localExtensionWriter = newExtensionWriter();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeBytes(1, getObjectIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getOwnerAccountBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt64(3, this.createTimeSec_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(4, this.operation_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeDouble(5, this.latitude_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeDouble(6, this.longitude_);
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBool(7, this.autoOpen_);
      localExtensionWriter.writeUntil(536870912, paramCodedOutputStream);
    }

    public static final class Builder extends GeneratedMessageLite.ExtendableBuilder<BookmarkProtos.Bookmark, Builder>
      implements BookmarkProtos.BookmarkOrBuilder
    {
      private boolean autoOpen_;
      private int bitField0_;
      private long createTimeSec_;
      private double latitude_;
      private double longitude_;
      private Object objectId_ = "";
      private BookmarkProtos.Bookmark.Operation operation_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance();
      private Object ownerAccount_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private BookmarkProtos.Bookmark buildParsed()
        throws InvalidProtocolBufferException
      {
        BookmarkProtos.Bookmark localBookmark = buildPartial();
        if (!localBookmark.isInitialized())
          throw newUninitializedMessageException(localBookmark).asInvalidProtocolBufferException();
        return localBookmark;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public BookmarkProtos.Bookmark build()
      {
        BookmarkProtos.Bookmark localBookmark = buildPartial();
        if (!localBookmark.isInitialized())
          throw newUninitializedMessageException(localBookmark);
        return localBookmark;
      }

      public BookmarkProtos.Bookmark buildPartial()
      {
        int i = 1;
        BookmarkProtos.Bookmark localBookmark = new BookmarkProtos.Bookmark(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          BookmarkProtos.Bookmark.access$1002(localBookmark, this.objectId_);
          if ((j & 0x2) == 2)
            i |= 2;
          BookmarkProtos.Bookmark.access$1102(localBookmark, this.ownerAccount_);
          if ((j & 0x4) == 4)
            i |= 4;
          BookmarkProtos.Bookmark.access$1202(localBookmark, this.createTimeSec_);
          if ((j & 0x8) == 8)
            i |= 8;
          BookmarkProtos.Bookmark.access$1302(localBookmark, this.operation_);
          if ((j & 0x10) == 16)
            i |= 16;
          BookmarkProtos.Bookmark.access$1402(localBookmark, this.latitude_);
          if ((j & 0x20) == 32)
            i |= 32;
          BookmarkProtos.Bookmark.access$1502(localBookmark, this.longitude_);
          if ((j & 0x40) == 64)
            i |= 64;
          BookmarkProtos.Bookmark.access$1602(localBookmark, this.autoOpen_);
          BookmarkProtos.Bookmark.access$1702(localBookmark, i);
          return localBookmark;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.objectId_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.ownerAccount_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.createTimeSec_ = 0L;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.operation_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.latitude_ = 0.0D;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.longitude_ = 0.0D;
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.autoOpen_ = false;
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      public Builder clearAutoOpen()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.autoOpen_ = false;
        return this;
      }

      public Builder clearCreateTimeSec()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.createTimeSec_ = 0L;
        return this;
      }

      public Builder clearLatitude()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.latitude_ = 0.0D;
        return this;
      }

      public Builder clearLongitude()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.longitude_ = 0.0D;
        return this;
      }

      public Builder clearObjectId()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.objectId_ = BookmarkProtos.Bookmark.getDefaultInstance().getObjectId();
        return this;
      }

      public Builder clearOperation()
      {
        this.operation_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearOwnerAccount()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.ownerAccount_ = BookmarkProtos.Bookmark.getDefaultInstance().getOwnerAccount();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public boolean getAutoOpen()
      {
        return this.autoOpen_;
      }

      public long getCreateTimeSec()
      {
        return this.createTimeSec_;
      }

      public BookmarkProtos.Bookmark getDefaultInstanceForType()
      {
        return BookmarkProtos.Bookmark.getDefaultInstance();
      }

      public double getLatitude()
      {
        return this.latitude_;
      }

      public double getLongitude()
      {
        return this.longitude_;
      }

      public String getObjectId()
      {
        Object localObject = this.objectId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.objectId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public BookmarkProtos.Bookmark.Operation getOperation()
      {
        return this.operation_;
      }

      public String getOwnerAccount()
      {
        Object localObject = this.ownerAccount_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.ownerAccount_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasAutoOpen()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasCreateTimeSec()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasLatitude()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasLongitude()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasObjectId()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasOperation()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasOwnerAccount()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        if (!hasObjectId());
        while ((!hasOwnerAccount()) || (!hasCreateTimeSec()) || (!hasOperation()) || (!hasAutoOpen()) || (!extensionsAreInitialized()))
          return false;
        return true;
      }

      public Builder mergeFrom(BookmarkProtos.Bookmark paramBookmark)
      {
        if (paramBookmark == BookmarkProtos.Bookmark.getDefaultInstance())
          return this;
        if (paramBookmark.hasObjectId())
          setObjectId(paramBookmark.getObjectId());
        if (paramBookmark.hasOwnerAccount())
          setOwnerAccount(paramBookmark.getOwnerAccount());
        if (paramBookmark.hasCreateTimeSec())
          setCreateTimeSec(paramBookmark.getCreateTimeSec());
        if (paramBookmark.hasOperation())
          mergeOperation(paramBookmark.getOperation());
        if (paramBookmark.hasLatitude())
          setLatitude(paramBookmark.getLatitude());
        if (paramBookmark.hasLongitude())
          setLongitude(paramBookmark.getLongitude());
        if (paramBookmark.hasAutoOpen())
          setAutoOpen(paramBookmark.getAutoOpen());
        mergeExtensionFields(paramBookmark);
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
            this.objectId_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.ownerAccount_ = paramCodedInputStream.readBytes();
            break;
          case 24:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.createTimeSec_ = paramCodedInputStream.readInt64();
            break;
          case 34:
            BookmarkProtos.Bookmark.Operation.Builder localBuilder = BookmarkProtos.Bookmark.Operation.newBuilder();
            if (hasOperation())
              localBuilder.mergeFrom(getOperation());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setOperation(localBuilder.buildPartial());
            break;
          case 41:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.latitude_ = paramCodedInputStream.readDouble();
            break;
          case 49:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.longitude_ = paramCodedInputStream.readDouble();
            break;
          case 56:
          }
          this.bitField0_ = (0x40 | this.bitField0_);
          this.autoOpen_ = paramCodedInputStream.readBool();
        }
      }

      public Builder mergeOperation(BookmarkProtos.Bookmark.Operation paramOperation)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.operation_ != BookmarkProtos.Bookmark.Operation.getDefaultInstance()));
        for (this.operation_ = BookmarkProtos.Bookmark.Operation.newBuilder(this.operation_).mergeFrom(paramOperation).buildPartial(); ; this.operation_ = paramOperation)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder setAutoOpen(boolean paramBoolean)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.autoOpen_ = paramBoolean;
        return this;
      }

      public Builder setCreateTimeSec(long paramLong)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.createTimeSec_ = paramLong;
        return this;
      }

      public Builder setLatitude(double paramDouble)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.latitude_ = paramDouble;
        return this;
      }

      public Builder setLongitude(double paramDouble)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.longitude_ = paramDouble;
        return this;
      }

      public Builder setObjectId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.objectId_ = paramString;
        return this;
      }

      void setObjectId(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.objectId_ = paramByteString;
      }

      public Builder setOperation(BookmarkProtos.Bookmark.Operation.Builder paramBuilder)
      {
        this.operation_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setOperation(BookmarkProtos.Bookmark.Operation paramOperation)
      {
        if (paramOperation == null)
          throw new NullPointerException();
        this.operation_ = paramOperation;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setOwnerAccount(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.ownerAccount_ = paramString;
        return this;
      }

      void setOwnerAccount(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.ownerAccount_ = paramByteString;
      }
    }

    public static final class Operation extends GeneratedMessageLite
      implements BookmarkProtos.Bookmark.OperationOrBuilder
    {
      public static final int ACTION_NAME_FIELD_NUMBER = 1;
      public static final int ACTIVITY_CLASS_FIELD_NUMBER = 3;
      public static final int ACTIVITY_PACKAGE_FIELD_NUMBER = 2;
      private static final Operation defaultInstance = new Operation(true);
      private static final long serialVersionUID;
      private Object actionName_;
      private Object activityClass_;
      private Object activityPackage_;
      private int bitField0_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;

      static
      {
        defaultInstance.initFields();
      }

      private Operation(Builder paramBuilder)
      {
        super();
      }

      private Operation(boolean paramBoolean)
      {
      }

      private ByteString getActionNameBytes()
      {
        Object localObject = this.actionName_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.actionName_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getActivityClassBytes()
      {
        Object localObject = this.activityClass_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.activityClass_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getActivityPackageBytes()
      {
        Object localObject = this.activityPackage_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.activityPackage_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      public static Operation getDefaultInstance()
      {
        return defaultInstance;
      }

      private void initFields()
      {
        this.actionName_ = "";
        this.activityPackage_ = "";
        this.activityClass_ = "";
      }

      public static Builder newBuilder()
      {
        return Builder.access$100();
      }

      public static Builder newBuilder(Operation paramOperation)
      {
        return newBuilder().mergeFrom(paramOperation);
      }

      public static Operation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static Operation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static Operation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static Operation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static Operation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static Operation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static Operation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static Operation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static Operation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static Operation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public String getActionName()
      {
        Object localObject = this.actionName_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.actionName_ = str;
        return str;
      }

      public String getActivityClass()
      {
        Object localObject = this.activityClass_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.activityClass_ = str;
        return str;
      }

      public String getActivityPackage()
      {
        Object localObject = this.activityPackage_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.activityPackage_ = str;
        return str;
      }

      public Operation getDefaultInstanceForType()
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
          k = 0 + CodedOutputStream.computeBytesSize(1, getActionNameBytes());
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeBytesSize(2, getActivityPackageBytes());
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeBytesSize(3, getActivityClassBytes());
        this.memoizedSerializedSize = k;
        return k;
      }

      public boolean hasActionName()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasActivityClass()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasActivityPackage()
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
          paramCodedOutputStream.writeBytes(1, getActionNameBytes());
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeBytes(2, getActivityPackageBytes());
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeBytes(3, getActivityClassBytes());
      }

      public static final class Builder extends GeneratedMessageLite.Builder<BookmarkProtos.Bookmark.Operation, Builder>
        implements BookmarkProtos.Bookmark.OperationOrBuilder
      {
        private Object actionName_ = "";
        private Object activityClass_ = "";
        private Object activityPackage_ = "";
        private int bitField0_;

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private BookmarkProtos.Bookmark.Operation buildParsed()
          throws InvalidProtocolBufferException
        {
          BookmarkProtos.Bookmark.Operation localOperation = buildPartial();
          if (!localOperation.isInitialized())
            throw newUninitializedMessageException(localOperation).asInvalidProtocolBufferException();
          return localOperation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public BookmarkProtos.Bookmark.Operation build()
        {
          BookmarkProtos.Bookmark.Operation localOperation = buildPartial();
          if (!localOperation.isInitialized())
            throw newUninitializedMessageException(localOperation);
          return localOperation;
        }

        public BookmarkProtos.Bookmark.Operation buildPartial()
        {
          int i = 1;
          BookmarkProtos.Bookmark.Operation localOperation = new BookmarkProtos.Bookmark.Operation(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            BookmarkProtos.Bookmark.Operation.access$302(localOperation, this.actionName_);
            if ((j & 0x2) == 2)
              i |= 2;
            BookmarkProtos.Bookmark.Operation.access$402(localOperation, this.activityPackage_);
            if ((j & 0x4) == 4)
              i |= 4;
            BookmarkProtos.Bookmark.Operation.access$502(localOperation, this.activityClass_);
            BookmarkProtos.Bookmark.Operation.access$602(localOperation, i);
            return localOperation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.actionName_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.activityPackage_ = "";
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.activityClass_ = "";
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          return this;
        }

        public Builder clearActionName()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.actionName_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance().getActionName();
          return this;
        }

        public Builder clearActivityClass()
        {
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.activityClass_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance().getActivityClass();
          return this;
        }

        public Builder clearActivityPackage()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.activityPackage_ = BookmarkProtos.Bookmark.Operation.getDefaultInstance().getActivityPackage();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public String getActionName()
        {
          Object localObject = this.actionName_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.actionName_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getActivityClass()
        {
          Object localObject = this.activityClass_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.activityClass_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getActivityPackage()
        {
          Object localObject = this.activityPackage_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.activityPackage_ = str;
            return str;
          }
          return (String)localObject;
        }

        public BookmarkProtos.Bookmark.Operation getDefaultInstanceForType()
        {
          return BookmarkProtos.Bookmark.Operation.getDefaultInstance();
        }

        public boolean hasActionName()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public boolean hasActivityClass()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public boolean hasActivityPackage()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(BookmarkProtos.Bookmark.Operation paramOperation)
        {
          if (paramOperation == BookmarkProtos.Bookmark.Operation.getDefaultInstance());
          do
          {
            return this;
            if (paramOperation.hasActionName())
              setActionName(paramOperation.getActionName());
            if (paramOperation.hasActivityPackage())
              setActivityPackage(paramOperation.getActivityPackage());
          }
          while (!paramOperation.hasActivityClass());
          setActivityClass(paramOperation.getActivityClass());
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
              this.actionName_ = paramCodedInputStream.readBytes();
              break;
            case 18:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.activityPackage_ = paramCodedInputStream.readBytes();
              break;
            case 26:
            }
            this.bitField0_ = (0x4 | this.bitField0_);
            this.activityClass_ = paramCodedInputStream.readBytes();
          }
        }

        public Builder setActionName(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.actionName_ = paramString;
          return this;
        }

        void setActionName(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.actionName_ = paramByteString;
        }

        public Builder setActivityClass(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x4 | this.bitField0_);
          this.activityClass_ = paramString;
          return this;
        }

        void setActivityClass(ByteString paramByteString)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          this.activityClass_ = paramByteString;
        }

        public Builder setActivityPackage(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x2 | this.bitField0_);
          this.activityPackage_ = paramString;
          return this;
        }

        void setActivityPackage(ByteString paramByteString)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.activityPackage_ = paramByteString;
        }
      }
    }

    public static abstract interface OperationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract String getActionName();

      public abstract String getActivityClass();

      public abstract String getActivityPackage();

      public abstract boolean hasActionName();

      public abstract boolean hasActivityClass();

      public abstract boolean hasActivityPackage();
    }
  }

  public static abstract interface BookmarkOrBuilder extends GeneratedMessageLite.ExtendableMessageOrBuilder<BookmarkProtos.Bookmark>
  {
    public abstract boolean getAutoOpen();

    public abstract long getCreateTimeSec();

    public abstract double getLatitude();

    public abstract double getLongitude();

    public abstract String getObjectId();

    public abstract BookmarkProtos.Bookmark.Operation getOperation();

    public abstract String getOwnerAccount();

    public abstract boolean hasAutoOpen();

    public abstract boolean hasCreateTimeSec();

    public abstract boolean hasLatitude();

    public abstract boolean hasLongitude();

    public abstract boolean hasObjectId();

    public abstract boolean hasOperation();

    public abstract boolean hasOwnerAccount();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.BookmarkProtos
 * JD-Core Version:    0.6.2
 */