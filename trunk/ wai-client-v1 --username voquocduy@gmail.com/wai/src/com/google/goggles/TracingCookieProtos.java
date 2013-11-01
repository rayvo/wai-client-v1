package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.Collections;
import java.util.List;

public final class TracingCookieProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class TracingCookieRequest extends GeneratedMessageLite
    implements TracingCookieProtos.TracingCookieRequestOrBuilder
  {
    public static final int MAX_COOKIES_FIELD_NUMBER = 1;
    private static final TracingCookieRequest defaultInstance = new TracingCookieRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int maxCookies_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private TracingCookieRequest(Builder paramBuilder)
    {
      super();
    }

    private TracingCookieRequest(boolean paramBoolean)
    {
    }

    public static TracingCookieRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.maxCookies_ = 5;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(TracingCookieRequest paramTracingCookieRequest)
    {
      return newBuilder().mergeFrom(paramTracingCookieRequest);
    }

    public static TracingCookieRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TracingCookieRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TracingCookieRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TracingCookieRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TracingCookieRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public TracingCookieRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getMaxCookies()
    {
      return this.maxCookies_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeInt32Size(1, this.maxCookies_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasMaxCookies()
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
        paramCodedOutputStream.writeInt32(1, this.maxCookies_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingCookieProtos.TracingCookieRequest, Builder>
      implements TracingCookieProtos.TracingCookieRequestOrBuilder
    {
      private int bitField0_;
      private int maxCookies_ = 5;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingCookieProtos.TracingCookieRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingCookieProtos.TracingCookieRequest localTracingCookieRequest = buildPartial();
        if (!localTracingCookieRequest.isInitialized())
          throw newUninitializedMessageException(localTracingCookieRequest).asInvalidProtocolBufferException();
        return localTracingCookieRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public TracingCookieProtos.TracingCookieRequest build()
      {
        TracingCookieProtos.TracingCookieRequest localTracingCookieRequest = buildPartial();
        if (!localTracingCookieRequest.isInitialized())
          throw newUninitializedMessageException(localTracingCookieRequest);
        return localTracingCookieRequest;
      }

      public TracingCookieProtos.TracingCookieRequest buildPartial()
      {
        int i = 1;
        TracingCookieProtos.TracingCookieRequest localTracingCookieRequest = new TracingCookieProtos.TracingCookieRequest(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          TracingCookieProtos.TracingCookieRequest.access$302(localTracingCookieRequest, this.maxCookies_);
          TracingCookieProtos.TracingCookieRequest.access$402(localTracingCookieRequest, i);
          return localTracingCookieRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.maxCookies_ = 5;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearMaxCookies()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.maxCookies_ = 5;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingCookieProtos.TracingCookieRequest getDefaultInstanceForType()
      {
        return TracingCookieProtos.TracingCookieRequest.getDefaultInstance();
      }

      public int getMaxCookies()
      {
        return this.maxCookies_;
      }

      public boolean hasMaxCookies()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(TracingCookieProtos.TracingCookieRequest paramTracingCookieRequest)
      {
        if (paramTracingCookieRequest == TracingCookieProtos.TracingCookieRequest.getDefaultInstance());
        while (!paramTracingCookieRequest.hasMaxCookies())
          return this;
        setMaxCookies(paramTracingCookieRequest.getMaxCookies());
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
          }
          this.bitField0_ = (0x1 | this.bitField0_);
          this.maxCookies_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setMaxCookies(int paramInt)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.maxCookies_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface TracingCookieRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getMaxCookies();

    public abstract boolean hasMaxCookies();
  }

  public static final class TracingCookieResponse extends GeneratedMessageLite
    implements TracingCookieProtos.TracingCookieResponseOrBuilder
  {
    public static final int TRACING_COOKIES_FIELD_NUMBER = 1;
    public static final int VALID_MS_FIELD_NUMBER = 2;
    private static final TracingCookieResponse defaultInstance = new TracingCookieResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private LazyStringList tracingCookies_;
    private int validMs_;

    static
    {
      defaultInstance.initFields();
    }

    private TracingCookieResponse(Builder paramBuilder)
    {
      super();
    }

    private TracingCookieResponse(boolean paramBoolean)
    {
    }

    public static TracingCookieResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.tracingCookies_ = LazyStringArrayList.EMPTY;
      this.validMs_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$600();
    }

    public static Builder newBuilder(TracingCookieResponse paramTracingCookieResponse)
    {
      return newBuilder().mergeFrom(paramTracingCookieResponse);
    }

    public static TracingCookieResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TracingCookieResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TracingCookieResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TracingCookieResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TracingCookieResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public TracingCookieResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.tracingCookies_.size())
      {
        k += CodedOutputStream.computeBytesSizeNoTag(this.tracingCookies_.getByteString(j));
        j++;
      }
      int m = 0 + k + 1 * getTracingCookiesList().size();
      if ((0x1 & this.bitField0_) == 1)
        m += CodedOutputStream.computeInt32Size(2, this.validMs_);
      this.memoizedSerializedSize = m;
      return m;
    }

    public String getTracingCookies(int paramInt)
    {
      return (String)this.tracingCookies_.get(paramInt);
    }

    public int getTracingCookiesCount()
    {
      return this.tracingCookies_.size();
    }

    public List<String> getTracingCookiesList()
    {
      return this.tracingCookies_;
    }

    public int getValidMs()
    {
      return this.validMs_;
    }

    public boolean hasValidMs()
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
      for (int i = 0; i < this.tracingCookies_.size(); i++)
        paramCodedOutputStream.writeBytes(1, this.tracingCookies_.getByteString(i));
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeInt32(2, this.validMs_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingCookieProtos.TracingCookieResponse, Builder>
      implements TracingCookieProtos.TracingCookieResponseOrBuilder
    {
      private int bitField0_;
      private LazyStringList tracingCookies_ = LazyStringArrayList.EMPTY;
      private int validMs_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingCookieProtos.TracingCookieResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingCookieProtos.TracingCookieResponse localTracingCookieResponse = buildPartial();
        if (!localTracingCookieResponse.isInitialized())
          throw newUninitializedMessageException(localTracingCookieResponse).asInvalidProtocolBufferException();
        return localTracingCookieResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureTracingCookiesIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.tracingCookies_ = new LazyStringArrayList(this.tracingCookies_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllTracingCookies(Iterable<String> paramIterable)
      {
        ensureTracingCookiesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.tracingCookies_);
        return this;
      }

      public Builder addTracingCookies(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureTracingCookiesIsMutable();
        this.tracingCookies_.add(paramString);
        return this;
      }

      void addTracingCookies(ByteString paramByteString)
      {
        ensureTracingCookiesIsMutable();
        this.tracingCookies_.add(paramByteString);
      }

      public TracingCookieProtos.TracingCookieResponse build()
      {
        TracingCookieProtos.TracingCookieResponse localTracingCookieResponse = buildPartial();
        if (!localTracingCookieResponse.isInitialized())
          throw newUninitializedMessageException(localTracingCookieResponse);
        return localTracingCookieResponse;
      }

      public TracingCookieProtos.TracingCookieResponse buildPartial()
      {
        int i = 1;
        TracingCookieProtos.TracingCookieResponse localTracingCookieResponse = new TracingCookieProtos.TracingCookieResponse(this, null);
        int j = this.bitField0_;
        if ((0x1 & this.bitField0_) == i)
        {
          this.tracingCookies_ = new UnmodifiableLazyStringList(this.tracingCookies_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        TracingCookieProtos.TracingCookieResponse.access$802(localTracingCookieResponse, this.tracingCookies_);
        if ((j & 0x2) == 2);
        while (true)
        {
          TracingCookieProtos.TracingCookieResponse.access$902(localTracingCookieResponse, this.validMs_);
          TracingCookieProtos.TracingCookieResponse.access$1002(localTracingCookieResponse, i);
          return localTracingCookieResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.tracingCookies_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.validMs_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearTracingCookies()
      {
        this.tracingCookies_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearValidMs()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.validMs_ = 0;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingCookieProtos.TracingCookieResponse getDefaultInstanceForType()
      {
        return TracingCookieProtos.TracingCookieResponse.getDefaultInstance();
      }

      public String getTracingCookies(int paramInt)
      {
        return (String)this.tracingCookies_.get(paramInt);
      }

      public int getTracingCookiesCount()
      {
        return this.tracingCookies_.size();
      }

      public List<String> getTracingCookiesList()
      {
        return Collections.unmodifiableList(this.tracingCookies_);
      }

      public int getValidMs()
      {
        return this.validMs_;
      }

      public boolean hasValidMs()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(TracingCookieProtos.TracingCookieResponse paramTracingCookieResponse)
      {
        if (paramTracingCookieResponse == TracingCookieProtos.TracingCookieResponse.getDefaultInstance());
        while (true)
        {
          return this;
          if (!paramTracingCookieResponse.tracingCookies_.isEmpty())
          {
            if (!this.tracingCookies_.isEmpty())
              break label70;
            this.tracingCookies_ = paramTracingCookieResponse.tracingCookies_;
            this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          }
          while (paramTracingCookieResponse.hasValidMs())
          {
            setValidMs(paramTracingCookieResponse.getValidMs());
            return this;
            label70: ensureTracingCookiesIsMutable();
            this.tracingCookies_.addAll(paramTracingCookieResponse.tracingCookies_);
          }
        }
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
            ensureTracingCookiesIsMutable();
            this.tracingCookies_.add(paramCodedInputStream.readBytes());
            break;
          case 16:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.validMs_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setTracingCookies(int paramInt, String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureTracingCookiesIsMutable();
        this.tracingCookies_.set(paramInt, paramString);
        return this;
      }

      public Builder setValidMs(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.validMs_ = paramInt;
        return this;
      }
    }
  }

  public static abstract interface TracingCookieResponseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getTracingCookies(int paramInt);

    public abstract int getTracingCookiesCount();

    public abstract List<String> getTracingCookiesList();

    public abstract int getValidMs();

    public abstract boolean hasValidMs();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.TracingCookieProtos
 * JD-Core Version:    0.6.2
 */