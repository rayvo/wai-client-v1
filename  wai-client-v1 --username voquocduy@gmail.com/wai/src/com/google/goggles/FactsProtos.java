package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnmodifiableLazyStringList;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class FactsProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Fact extends GeneratedMessageLite
    implements FactsProtos.FactOrBuilder
  {
    public static final int ATTRIBUTE_FIELD_NUMBER = 1;
    public static final int CONFIDENCE_LEVEL_FIELD_NUMBER = 3;
    public static final int URLS_FIELD_NUMBER = 4;
    public static final int VALUE_FIELD_NUMBER = 2;
    private static final Fact defaultInstance = new Fact(true);
    private static final long serialVersionUID;
    private Object attribute_;
    private int bitField0_;
    private ConfidenceLevel confidenceLevel_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private LazyStringList urls_;
    private Object value_;

    static
    {
      defaultInstance.initFields();
    }

    private Fact(Builder paramBuilder)
    {
      super();
    }

    private Fact(boolean paramBoolean)
    {
    }

    private ByteString getAttributeBytes()
    {
      Object localObject = this.attribute_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.attribute_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static Fact getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getValueBytes()
    {
      Object localObject = this.value_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.value_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.attribute_ = "";
      this.value_ = "";
      this.confidenceLevel_ = ConfidenceLevel.LOW_CONFIDENCE;
      this.urls_ = LazyStringArrayList.EMPTY;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Fact paramFact)
    {
      return newBuilder().mergeFrom(paramFact);
    }

    public static Fact parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Fact parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Fact parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Fact parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Fact parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Fact parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Fact parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Fact parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Fact parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Fact parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getAttribute()
    {
      Object localObject = this.attribute_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.attribute_ = str;
      return str;
    }

    public ConfidenceLevel getConfidenceLevel()
    {
      return this.confidenceLevel_;
    }

    public Fact getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = 0;
      int j = this.memoizedSerializedSize;
      if (j != -1)
        return j;
      if ((0x1 & this.bitField0_) == 1);
      for (int k = 0 + CodedOutputStream.computeBytesSize(1, getAttributeBytes()); ; k = 0)
      {
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeBytesSize(2, getValueBytes());
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeEnumSize(3, this.confidenceLevel_.getNumber());
        int m = 0;
        while (i < this.urls_.size())
        {
          m += CodedOutputStream.computeBytesSizeNoTag(this.urls_.getByteString(i));
          i++;
        }
        int n = k + m + 1 * getUrlsList().size();
        this.memoizedSerializedSize = n;
        return n;
      }
    }

    public String getUrls(int paramInt)
    {
      return (String)this.urls_.get(paramInt);
    }

    public int getUrlsCount()
    {
      return this.urls_.size();
    }

    public List<String> getUrlsList()
    {
      return this.urls_;
    }

    public String getValue()
    {
      Object localObject = this.value_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.value_ = str;
      return str;
    }

    public boolean hasAttribute()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasConfidenceLevel()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasValue()
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
        paramCodedOutputStream.writeBytes(1, getAttributeBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getValueBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeEnum(3, this.confidenceLevel_.getNumber());
      for (int i = 0; i < this.urls_.size(); i++)
        paramCodedOutputStream.writeBytes(4, this.urls_.getByteString(i));
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FactsProtos.Fact, Builder>
      implements FactsProtos.FactOrBuilder
    {
      private Object attribute_ = "";
      private int bitField0_;
      private FactsProtos.Fact.ConfidenceLevel confidenceLevel_ = FactsProtos.Fact.ConfidenceLevel.LOW_CONFIDENCE;
      private LazyStringList urls_ = LazyStringArrayList.EMPTY;
      private Object value_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private FactsProtos.Fact buildParsed()
        throws InvalidProtocolBufferException
      {
        FactsProtos.Fact localFact = buildPartial();
        if (!localFact.isInitialized())
          throw newUninitializedMessageException(localFact).asInvalidProtocolBufferException();
        return localFact;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureUrlsIsMutable()
      {
        if ((0x8 & this.bitField0_) != 8)
        {
          this.urls_ = new LazyStringArrayList(this.urls_);
          this.bitField0_ = (0x8 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllUrls(Iterable<String> paramIterable)
      {
        ensureUrlsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.urls_);
        return this;
      }

      public Builder addUrls(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureUrlsIsMutable();
        this.urls_.add(paramString);
        return this;
      }

      void addUrls(ByteString paramByteString)
      {
        ensureUrlsIsMutable();
        this.urls_.add(paramByteString);
      }

      public FactsProtos.Fact build()
      {
        FactsProtos.Fact localFact = buildPartial();
        if (!localFact.isInitialized())
          throw newUninitializedMessageException(localFact);
        return localFact;
      }

      public FactsProtos.Fact buildPartial()
      {
        int i = 1;
        FactsProtos.Fact localFact = new FactsProtos.Fact(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          FactsProtos.Fact.access$302(localFact, this.attribute_);
          if ((j & 0x2) == 2)
            i |= 2;
          FactsProtos.Fact.access$402(localFact, this.value_);
          if ((j & 0x4) == 4)
            i |= 4;
          FactsProtos.Fact.access$502(localFact, this.confidenceLevel_);
          if ((0x8 & this.bitField0_) == 8)
          {
            this.urls_ = new UnmodifiableLazyStringList(this.urls_);
            this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          }
          FactsProtos.Fact.access$602(localFact, this.urls_);
          FactsProtos.Fact.access$702(localFact, i);
          return localFact;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.attribute_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.value_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.confidenceLevel_ = FactsProtos.Fact.ConfidenceLevel.LOW_CONFIDENCE;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.urls_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearAttribute()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.attribute_ = FactsProtos.Fact.getDefaultInstance().getAttribute();
        return this;
      }

      public Builder clearConfidenceLevel()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.confidenceLevel_ = FactsProtos.Fact.ConfidenceLevel.LOW_CONFIDENCE;
        return this;
      }

      public Builder clearUrls()
      {
        this.urls_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearValue()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.value_ = FactsProtos.Fact.getDefaultInstance().getValue();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getAttribute()
      {
        Object localObject = this.attribute_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.attribute_ = str;
          return str;
        }
        return (String)localObject;
      }

      public FactsProtos.Fact.ConfidenceLevel getConfidenceLevel()
      {
        return this.confidenceLevel_;
      }

      public FactsProtos.Fact getDefaultInstanceForType()
      {
        return FactsProtos.Fact.getDefaultInstance();
      }

      public String getUrls(int paramInt)
      {
        return (String)this.urls_.get(paramInt);
      }

      public int getUrlsCount()
      {
        return this.urls_.size();
      }

      public List<String> getUrlsList()
      {
        return Collections.unmodifiableList(this.urls_);
      }

      public String getValue()
      {
        Object localObject = this.value_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.value_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasAttribute()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasConfidenceLevel()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasValue()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(FactsProtos.Fact paramFact)
      {
        if (paramFact == FactsProtos.Fact.getDefaultInstance());
        do
        {
          return this;
          if (paramFact.hasAttribute())
            setAttribute(paramFact.getAttribute());
          if (paramFact.hasValue())
            setValue(paramFact.getValue());
          if (paramFact.hasConfidenceLevel())
            setConfidenceLevel(paramFact.getConfidenceLevel());
        }
        while (paramFact.urls_.isEmpty());
        if (this.urls_.isEmpty())
        {
          this.urls_ = paramFact.urls_;
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
        }
        ensureUrlsIsMutable();
        this.urls_.addAll(paramFact.urls_);
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
            this.attribute_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.value_ = paramCodedInputStream.readBytes();
            break;
          case 24:
            FactsProtos.Fact.ConfidenceLevel localConfidenceLevel = FactsProtos.Fact.ConfidenceLevel.valueOf(paramCodedInputStream.readEnum());
            if (localConfidenceLevel == null)
              continue;
            this.bitField0_ = (0x4 | this.bitField0_);
            this.confidenceLevel_ = localConfidenceLevel;
            break;
          case 34:
          }
          ensureUrlsIsMutable();
          this.urls_.add(paramCodedInputStream.readBytes());
        }
      }

      public Builder setAttribute(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.attribute_ = paramString;
        return this;
      }

      void setAttribute(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.attribute_ = paramByteString;
      }

      public Builder setConfidenceLevel(FactsProtos.Fact.ConfidenceLevel paramConfidenceLevel)
      {
        if (paramConfidenceLevel == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.confidenceLevel_ = paramConfidenceLevel;
        return this;
      }

      public Builder setUrls(int paramInt, String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureUrlsIsMutable();
        this.urls_.set(paramInt, paramString);
        return this;
      }

      public Builder setValue(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.value_ = paramString;
        return this;
      }

      void setValue(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.value_ = paramByteString;
      }
    }

    public static enum ConfidenceLevel
      implements Internal.EnumLite
    {
      public static final int HIGH_CONFIDENCE_VALUE = 2;
      public static final int LOW_CONFIDENCE_VALUE = 0;
      public static final int MEDIUM_CONFIDENCE_VALUE = 1;
      private static Internal.EnumLiteMap<ConfidenceLevel> internalValueMap = new Internal.EnumLiteMap()
      {
        public FactsProtos.Fact.ConfidenceLevel findValueByNumber(int paramAnonymousInt)
        {
          return FactsProtos.Fact.ConfidenceLevel.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        HIGH_CONFIDENCE = new ConfidenceLevel("HIGH_CONFIDENCE", 2, 2, 2);
        ConfidenceLevel[] arrayOfConfidenceLevel = new ConfidenceLevel[3];
        arrayOfConfidenceLevel[0] = LOW_CONFIDENCE;
        arrayOfConfidenceLevel[1] = MEDIUM_CONFIDENCE;
        arrayOfConfidenceLevel[2] = HIGH_CONFIDENCE;
      }

      private ConfidenceLevel(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<ConfidenceLevel> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static ConfidenceLevel valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return LOW_CONFIDENCE;
        case 1:
          return MEDIUM_CONFIDENCE;
        case 2:
        }
        return HIGH_CONFIDENCE;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface FactOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getAttribute();

    public abstract FactsProtos.Fact.ConfidenceLevel getConfidenceLevel();

    public abstract String getUrls(int paramInt);

    public abstract int getUrlsCount();

    public abstract List<String> getUrlsList();

    public abstract String getValue();

    public abstract boolean hasAttribute();

    public abstract boolean hasConfidenceLevel();

    public abstract boolean hasValue();
  }

  public static final class Facts extends GeneratedMessageLite
    implements FactsProtos.FactsOrBuilder
  {
    public static final int EXPERIMENTAL_FIELD_NUMBER = 3;
    public static final int PRIMARY_FIELD_NUMBER = 1;
    public static final int SECONDARY_FIELD_NUMBER = 2;
    private static final Facts defaultInstance = new Facts(true);
    private static final long serialVersionUID;
    private List<FactsProtos.Fact> experimental_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<FactsProtos.Fact> primary_;
    private List<FactsProtos.Fact> secondary_;

    static
    {
      defaultInstance.initFields();
    }

    private Facts(Builder paramBuilder)
    {
      super();
    }

    private Facts(boolean paramBoolean)
    {
    }

    public static Facts getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.primary_ = Collections.emptyList();
      this.secondary_ = Collections.emptyList();
      this.experimental_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$900();
    }

    public static Builder newBuilder(Facts paramFacts)
    {
      return newBuilder().mergeFrom(paramFacts);
    }

    public static Facts parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Facts parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Facts parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Facts parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Facts parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Facts parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Facts parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Facts parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Facts parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Facts parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Facts getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public FactsProtos.Fact getExperimental(int paramInt)
    {
      return (FactsProtos.Fact)this.experimental_.get(paramInt);
    }

    public int getExperimentalCount()
    {
      return this.experimental_.size();
    }

    public List<FactsProtos.Fact> getExperimentalList()
    {
      return this.experimental_;
    }

    public FactsProtos.FactOrBuilder getExperimentalOrBuilder(int paramInt)
    {
      return (FactsProtos.FactOrBuilder)this.experimental_.get(paramInt);
    }

    public List<? extends FactsProtos.FactOrBuilder> getExperimentalOrBuilderList()
    {
      return this.experimental_;
    }

    public FactsProtos.Fact getPrimary(int paramInt)
    {
      return (FactsProtos.Fact)this.primary_.get(paramInt);
    }

    public int getPrimaryCount()
    {
      return this.primary_.size();
    }

    public List<FactsProtos.Fact> getPrimaryList()
    {
      return this.primary_;
    }

    public FactsProtos.FactOrBuilder getPrimaryOrBuilder(int paramInt)
    {
      return (FactsProtos.FactOrBuilder)this.primary_.get(paramInt);
    }

    public List<? extends FactsProtos.FactOrBuilder> getPrimaryOrBuilderList()
    {
      return this.primary_;
    }

    public FactsProtos.Fact getSecondary(int paramInt)
    {
      return (FactsProtos.Fact)this.secondary_.get(paramInt);
    }

    public int getSecondaryCount()
    {
      return this.secondary_.size();
    }

    public List<FactsProtos.Fact> getSecondaryList()
    {
      return this.secondary_;
    }

    public FactsProtos.FactOrBuilder getSecondaryOrBuilder(int paramInt)
    {
      return (FactsProtos.FactOrBuilder)this.secondary_.get(paramInt);
    }

    public List<? extends FactsProtos.FactOrBuilder> getSecondaryOrBuilderList()
    {
      return this.secondary_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.primary_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.primary_.get(j));
        j++;
      }
      int i1;
      for (int m = 0; ; m++)
      {
        int n = this.secondary_.size();
        i1 = 0;
        if (m >= n)
          break;
        k += CodedOutputStream.computeMessageSize(2, (MessageLite)this.secondary_.get(m));
      }
      while (i1 < this.experimental_.size())
      {
        k += CodedOutputStream.computeMessageSize(3, (MessageLite)this.experimental_.get(i1));
        i1++;
      }
      this.memoizedSerializedSize = k;
      return k;
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
      for (int i = 0; i < this.primary_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.primary_.get(i));
      int m;
      for (int j = 0; ; j++)
      {
        int k = this.secondary_.size();
        m = 0;
        if (j >= k)
          break;
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.secondary_.get(j));
      }
      while (m < this.experimental_.size())
      {
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.experimental_.get(m));
        m++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FactsProtos.Facts, Builder>
      implements FactsProtos.FactsOrBuilder
    {
      private int bitField0_;
      private List<FactsProtos.Fact> experimental_ = Collections.emptyList();
      private List<FactsProtos.Fact> primary_ = Collections.emptyList();
      private List<FactsProtos.Fact> secondary_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private FactsProtos.Facts buildParsed()
        throws InvalidProtocolBufferException
      {
        FactsProtos.Facts localFacts = buildPartial();
        if (!localFacts.isInitialized())
          throw newUninitializedMessageException(localFacts).asInvalidProtocolBufferException();
        return localFacts;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureExperimentalIsMutable()
      {
        if ((0x4 & this.bitField0_) != 4)
        {
          this.experimental_ = new ArrayList(this.experimental_);
          this.bitField0_ = (0x4 | this.bitField0_);
        }
      }

      private void ensurePrimaryIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.primary_ = new ArrayList(this.primary_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void ensureSecondaryIsMutable()
      {
        if ((0x2 & this.bitField0_) != 2)
        {
          this.secondary_ = new ArrayList(this.secondary_);
          this.bitField0_ = (0x2 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllExperimental(Iterable<? extends FactsProtos.Fact> paramIterable)
      {
        ensureExperimentalIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.experimental_);
        return this;
      }

      public Builder addAllPrimary(Iterable<? extends FactsProtos.Fact> paramIterable)
      {
        ensurePrimaryIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.primary_);
        return this;
      }

      public Builder addAllSecondary(Iterable<? extends FactsProtos.Fact> paramIterable)
      {
        ensureSecondaryIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.secondary_);
        return this;
      }

      public Builder addExperimental(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensureExperimentalIsMutable();
        this.experimental_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addExperimental(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureExperimentalIsMutable();
        this.experimental_.add(paramInt, paramFact);
        return this;
      }

      public Builder addExperimental(FactsProtos.Fact.Builder paramBuilder)
      {
        ensureExperimentalIsMutable();
        this.experimental_.add(paramBuilder.build());
        return this;
      }

      public Builder addExperimental(FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureExperimentalIsMutable();
        this.experimental_.add(paramFact);
        return this;
      }

      public Builder addPrimary(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensurePrimaryIsMutable();
        this.primary_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addPrimary(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensurePrimaryIsMutable();
        this.primary_.add(paramInt, paramFact);
        return this;
      }

      public Builder addPrimary(FactsProtos.Fact.Builder paramBuilder)
      {
        ensurePrimaryIsMutable();
        this.primary_.add(paramBuilder.build());
        return this;
      }

      public Builder addPrimary(FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensurePrimaryIsMutable();
        this.primary_.add(paramFact);
        return this;
      }

      public Builder addSecondary(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensureSecondaryIsMutable();
        this.secondary_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addSecondary(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureSecondaryIsMutable();
        this.secondary_.add(paramInt, paramFact);
        return this;
      }

      public Builder addSecondary(FactsProtos.Fact.Builder paramBuilder)
      {
        ensureSecondaryIsMutable();
        this.secondary_.add(paramBuilder.build());
        return this;
      }

      public Builder addSecondary(FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureSecondaryIsMutable();
        this.secondary_.add(paramFact);
        return this;
      }

      public FactsProtos.Facts build()
      {
        FactsProtos.Facts localFacts = buildPartial();
        if (!localFacts.isInitialized())
          throw newUninitializedMessageException(localFacts);
        return localFacts;
      }

      public FactsProtos.Facts buildPartial()
      {
        FactsProtos.Facts localFacts = new FactsProtos.Facts(this, null);
        if ((0x1 & this.bitField0_) == 1)
        {
          this.primary_ = Collections.unmodifiableList(this.primary_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        FactsProtos.Facts.access$1102(localFacts, this.primary_);
        if ((0x2 & this.bitField0_) == 2)
        {
          this.secondary_ = Collections.unmodifiableList(this.secondary_);
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        }
        FactsProtos.Facts.access$1202(localFacts, this.secondary_);
        if ((0x4 & this.bitField0_) == 4)
        {
          this.experimental_ = Collections.unmodifiableList(this.experimental_);
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        }
        FactsProtos.Facts.access$1302(localFacts, this.experimental_);
        return localFacts;
      }

      public Builder clear()
      {
        super.clear();
        this.primary_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.secondary_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.experimental_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearExperimental()
      {
        this.experimental_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearPrimary()
      {
        this.primary_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearSecondary()
      {
        this.secondary_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public FactsProtos.Facts getDefaultInstanceForType()
      {
        return FactsProtos.Facts.getDefaultInstance();
      }

      public FactsProtos.Fact getExperimental(int paramInt)
      {
        return (FactsProtos.Fact)this.experimental_.get(paramInt);
      }

      public int getExperimentalCount()
      {
        return this.experimental_.size();
      }

      public List<FactsProtos.Fact> getExperimentalList()
      {
        return Collections.unmodifiableList(this.experimental_);
      }

      public FactsProtos.Fact getPrimary(int paramInt)
      {
        return (FactsProtos.Fact)this.primary_.get(paramInt);
      }

      public int getPrimaryCount()
      {
        return this.primary_.size();
      }

      public List<FactsProtos.Fact> getPrimaryList()
      {
        return Collections.unmodifiableList(this.primary_);
      }

      public FactsProtos.Fact getSecondary(int paramInt)
      {
        return (FactsProtos.Fact)this.secondary_.get(paramInt);
      }

      public int getSecondaryCount()
      {
        return this.secondary_.size();
      }

      public List<FactsProtos.Fact> getSecondaryList()
      {
        return Collections.unmodifiableList(this.secondary_);
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(FactsProtos.Facts paramFacts)
      {
        if (paramFacts == FactsProtos.Facts.getDefaultInstance());
        label161: label180: 
        while (true)
        {
          return this;
          if (!paramFacts.primary_.isEmpty())
          {
            if (this.primary_.isEmpty())
            {
              this.primary_ = paramFacts.primary_;
              this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
            }
          }
          else if (!paramFacts.secondary_.isEmpty())
          {
            if (!this.secondary_.isEmpty())
              break label161;
            this.secondary_ = paramFacts.secondary_;
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          }
          while (true)
          {
            if (paramFacts.experimental_.isEmpty())
              break label180;
            if (!this.experimental_.isEmpty())
              break label182;
            this.experimental_ = paramFacts.experimental_;
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
            return this;
            ensurePrimaryIsMutable();
            this.primary_.addAll(paramFacts.primary_);
            break;
            ensureSecondaryIsMutable();
            this.secondary_.addAll(paramFacts.secondary_);
          }
        }
        label182: ensureExperimentalIsMutable();
        this.experimental_.addAll(paramFacts.experimental_);
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
            FactsProtos.Fact.Builder localBuilder3 = FactsProtos.Fact.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addPrimary(localBuilder3.buildPartial());
            break;
          case 18:
            FactsProtos.Fact.Builder localBuilder2 = FactsProtos.Fact.newBuilder();
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            addSecondary(localBuilder2.buildPartial());
            break;
          case 26:
          }
          FactsProtos.Fact.Builder localBuilder1 = FactsProtos.Fact.newBuilder();
          paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
          addExperimental(localBuilder1.buildPartial());
        }
      }

      public Builder removeExperimental(int paramInt)
      {
        ensureExperimentalIsMutable();
        this.experimental_.remove(paramInt);
        return this;
      }

      public Builder removePrimary(int paramInt)
      {
        ensurePrimaryIsMutable();
        this.primary_.remove(paramInt);
        return this;
      }

      public Builder removeSecondary(int paramInt)
      {
        ensureSecondaryIsMutable();
        this.secondary_.remove(paramInt);
        return this;
      }

      public Builder setExperimental(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensureExperimentalIsMutable();
        this.experimental_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setExperimental(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureExperimentalIsMutable();
        this.experimental_.set(paramInt, paramFact);
        return this;
      }

      public Builder setPrimary(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensurePrimaryIsMutable();
        this.primary_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setPrimary(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensurePrimaryIsMutable();
        this.primary_.set(paramInt, paramFact);
        return this;
      }

      public Builder setSecondary(int paramInt, FactsProtos.Fact.Builder paramBuilder)
      {
        ensureSecondaryIsMutable();
        this.secondary_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setSecondary(int paramInt, FactsProtos.Fact paramFact)
      {
        if (paramFact == null)
          throw new NullPointerException();
        ensureSecondaryIsMutable();
        this.secondary_.set(paramInt, paramFact);
        return this;
      }
    }
  }

  public static abstract interface FactsOrBuilder extends MessageLiteOrBuilder
  {
    public abstract FactsProtos.Fact getExperimental(int paramInt);

    public abstract int getExperimentalCount();

    public abstract List<FactsProtos.Fact> getExperimentalList();

    public abstract FactsProtos.Fact getPrimary(int paramInt);

    public abstract int getPrimaryCount();

    public abstract List<FactsProtos.Fact> getPrimaryList();

    public abstract FactsProtos.Fact getSecondary(int paramInt);

    public abstract int getSecondaryCount();

    public abstract List<FactsProtos.Fact> getSecondaryList();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.FactsProtos
 * JD-Core Version:    0.6.2
 */