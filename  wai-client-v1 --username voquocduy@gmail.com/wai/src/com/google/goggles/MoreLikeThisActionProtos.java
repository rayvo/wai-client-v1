package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class MoreLikeThisActionProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class MoreLikeThisAction extends GeneratedMessageLite
    implements MoreLikeThisActionProtos.MoreLikeThisActionOrBuilder
  {
    public static final int DOCID_FIELD_NUMBER = 2;
    public static final int RESTRICTS_FIELD_NUMBER = 1;
    private static final MoreLikeThisAction defaultInstance = new MoreLikeThisAction(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object docid_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private RestrictsProtos.Restricts restricts_;

    static
    {
      defaultInstance.initFields();
    }

    private MoreLikeThisAction(Builder paramBuilder)
    {
      super();
    }

    private MoreLikeThisAction(boolean paramBoolean)
    {
    }

    public static MoreLikeThisAction getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getDocidBytes()
    {
      Object localObject = this.docid_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.docid_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
      this.docid_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(MoreLikeThisAction paramMoreLikeThisAction)
    {
      return newBuilder().mergeFrom(paramMoreLikeThisAction);
    }

    public static MoreLikeThisAction parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MoreLikeThisAction parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MoreLikeThisAction parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MoreLikeThisAction parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public MoreLikeThisAction getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getDocid()
    {
      Object localObject = this.docid_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.docid_ = str;
      return str;
    }

    public RestrictsProtos.Restricts getRestricts()
    {
      return this.restricts_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.restricts_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getDocidBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasDocid()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasRestricts()
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
        paramCodedOutputStream.writeMessage(1, this.restricts_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getDocidBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MoreLikeThisActionProtos.MoreLikeThisAction, Builder>
      implements MoreLikeThisActionProtos.MoreLikeThisActionOrBuilder
    {
      private int bitField0_;
      private Object docid_ = "";
      private RestrictsProtos.Restricts restricts_ = RestrictsProtos.Restricts.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private MoreLikeThisActionProtos.MoreLikeThisAction buildParsed()
        throws InvalidProtocolBufferException
      {
        MoreLikeThisActionProtos.MoreLikeThisAction localMoreLikeThisAction = buildPartial();
        if (!localMoreLikeThisAction.isInitialized())
          throw newUninitializedMessageException(localMoreLikeThisAction).asInvalidProtocolBufferException();
        return localMoreLikeThisAction;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public MoreLikeThisActionProtos.MoreLikeThisAction build()
      {
        MoreLikeThisActionProtos.MoreLikeThisAction localMoreLikeThisAction = buildPartial();
        if (!localMoreLikeThisAction.isInitialized())
          throw newUninitializedMessageException(localMoreLikeThisAction);
        return localMoreLikeThisAction;
      }

      public MoreLikeThisActionProtos.MoreLikeThisAction buildPartial()
      {
        int i = 1;
        MoreLikeThisActionProtos.MoreLikeThisAction localMoreLikeThisAction = new MoreLikeThisActionProtos.MoreLikeThisAction(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          MoreLikeThisActionProtos.MoreLikeThisAction.access$302(localMoreLikeThisAction, this.restricts_);
          if ((j & 0x2) == 2)
            i |= 2;
          MoreLikeThisActionProtos.MoreLikeThisAction.access$402(localMoreLikeThisAction, this.docid_);
          MoreLikeThisActionProtos.MoreLikeThisAction.access$502(localMoreLikeThisAction, i);
          return localMoreLikeThisAction;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.docid_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearDocid()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.docid_ = MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance().getDocid();
        return this;
      }

      public Builder clearRestricts()
      {
        this.restricts_ = RestrictsProtos.Restricts.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public MoreLikeThisActionProtos.MoreLikeThisAction getDefaultInstanceForType()
      {
        return MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance();
      }

      public String getDocid()
      {
        Object localObject = this.docid_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.docid_ = str;
          return str;
        }
        return (String)localObject;
      }

      public RestrictsProtos.Restricts getRestricts()
      {
        return this.restricts_;
      }

      public boolean hasDocid()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasRestricts()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(MoreLikeThisActionProtos.MoreLikeThisAction paramMoreLikeThisAction)
      {
        if (paramMoreLikeThisAction == MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance());
        do
        {
          return this;
          if (paramMoreLikeThisAction.hasRestricts())
            mergeRestricts(paramMoreLikeThisAction.getRestricts());
        }
        while (!paramMoreLikeThisAction.hasDocid());
        setDocid(paramMoreLikeThisAction.getDocid());
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
            RestrictsProtos.Restricts.Builder localBuilder = RestrictsProtos.Restricts.newBuilder();
            if (hasRestricts())
              localBuilder.mergeFrom(getRestricts());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setRestricts(localBuilder.buildPartial());
            break;
          case 18:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.docid_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder mergeRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.restricts_ != RestrictsProtos.Restricts.getDefaultInstance()));
        for (this.restricts_ = RestrictsProtos.Restricts.newBuilder(this.restricts_).mergeFrom(paramRestricts).buildPartial(); ; this.restricts_ = paramRestricts)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder setDocid(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.docid_ = paramString;
        return this;
      }

      void setDocid(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.docid_ = paramByteString;
      }

      public Builder setRestricts(RestrictsProtos.Restricts.Builder paramBuilder)
      {
        this.restricts_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setRestricts(RestrictsProtos.Restricts paramRestricts)
      {
        if (paramRestricts == null)
          throw new NullPointerException();
        this.restricts_ = paramRestricts;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface MoreLikeThisActionOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getDocid();

    public abstract RestrictsProtos.Restricts getRestricts();

    public abstract boolean hasDocid();

    public abstract boolean hasRestricts();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.MoreLikeThisActionProtos
 * JD-Core Version:    0.6.2
 */