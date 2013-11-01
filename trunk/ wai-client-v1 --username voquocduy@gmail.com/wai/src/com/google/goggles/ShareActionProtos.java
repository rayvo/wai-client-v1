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

public final class ShareActionProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class ShareAction extends GeneratedMessageLite
    implements ShareActionProtos.ShareActionOrBuilder
  {
    public static final int TEXT_FIELD_NUMBER = 1;
    private static final ShareAction defaultInstance = new ShareAction(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object text_;

    static
    {
      defaultInstance.initFields();
    }

    private ShareAction(Builder paramBuilder)
    {
      super();
    }

    private ShareAction(boolean paramBoolean)
    {
    }

    public static ShareAction getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getTextBytes()
    {
      Object localObject = this.text_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.text_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.text_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(ShareAction paramShareAction)
    {
      return newBuilder().mergeFrom(paramShareAction);
    }

    public static ShareAction parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ShareAction parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ShareAction parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ShareAction parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ShareAction parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ShareAction parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ShareAction parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ShareAction parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ShareAction parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ShareAction parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ShareAction getDefaultInstanceForType()
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
        k = 0 + CodedOutputStream.computeBytesSize(1, getTextBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getText()
    {
      Object localObject = this.text_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.text_ = str;
      return str;
    }

    public boolean hasText()
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
        paramCodedOutputStream.writeBytes(1, getTextBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ShareActionProtos.ShareAction, Builder>
      implements ShareActionProtos.ShareActionOrBuilder
    {
      private int bitField0_;
      private Object text_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ShareActionProtos.ShareAction buildParsed()
        throws InvalidProtocolBufferException
      {
        ShareActionProtos.ShareAction localShareAction = buildPartial();
        if (!localShareAction.isInitialized())
          throw newUninitializedMessageException(localShareAction).asInvalidProtocolBufferException();
        return localShareAction;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ShareActionProtos.ShareAction build()
      {
        ShareActionProtos.ShareAction localShareAction = buildPartial();
        if (!localShareAction.isInitialized())
          throw newUninitializedMessageException(localShareAction);
        return localShareAction;
      }

      public ShareActionProtos.ShareAction buildPartial()
      {
        int i = 1;
        ShareActionProtos.ShareAction localShareAction = new ShareActionProtos.ShareAction(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          ShareActionProtos.ShareAction.access$302(localShareAction, this.text_);
          ShareActionProtos.ShareAction.access$402(localShareAction, i);
          return localShareAction;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.text_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearText()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.text_ = ShareActionProtos.ShareAction.getDefaultInstance().getText();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ShareActionProtos.ShareAction getDefaultInstanceForType()
      {
        return ShareActionProtos.ShareAction.getDefaultInstance();
      }

      public String getText()
      {
        Object localObject = this.text_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.text_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasText()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(ShareActionProtos.ShareAction paramShareAction)
      {
        if (paramShareAction == ShareActionProtos.ShareAction.getDefaultInstance());
        while (!paramShareAction.hasText())
          return this;
        setText(paramShareAction.getText());
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
          this.text_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setText(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.text_ = paramString;
        return this;
      }

      void setText(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.text_ = paramByteString;
      }
    }
  }

  public static abstract interface ShareActionOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getText();

    public abstract boolean hasText();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ShareActionProtos
 * JD-Core Version:    0.6.2
 */