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
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class UrlGroupProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class Url extends GeneratedMessageLite
    implements UrlGroupProtos.UrlOrBuilder
  {
    public static final int DESCRIPTION_FIELD_NUMBER = 2;
    public static final int PURPOSE_FIELD_NUMBER = 3;
    public static final int RECOMMENDED_QUICK_ACTION_FIELD_NUMBER = 4;
    public static final int URL_FIELD_NUMBER = 1;
    private static final Url defaultInstance = new Url(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object description_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Purpose purpose_;
    private boolean recommendedQuickAction_;
    private Object url_;

    static
    {
      defaultInstance.initFields();
    }

    private Url(Builder paramBuilder)
    {
      super();
    }

    private Url(boolean paramBoolean)
    {
    }

    public static Url getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getDescriptionBytes()
    {
      Object localObject = this.description_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.description_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getUrlBytes()
    {
      Object localObject = this.url_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.url_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.url_ = "";
      this.description_ = "";
      this.purpose_ = Purpose.UNKNOWN;
      this.recommendedQuickAction_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(Url paramUrl)
    {
      return newBuilder().mergeFrom(paramUrl);
    }

    public static Url parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static Url parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static Url parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static Url parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static Url parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static Url parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static Url parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static Url parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static Url parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static Url parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public Url getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getDescription()
    {
      Object localObject = this.description_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.description_ = str;
      return str;
    }

    public Purpose getPurpose()
    {
      return this.purpose_;
    }

    public boolean getRecommendedQuickAction()
    {
      return this.recommendedQuickAction_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, getUrlBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getDescriptionBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeEnumSize(3, this.purpose_.getNumber());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBoolSize(4, this.recommendedQuickAction_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getUrl()
    {
      Object localObject = this.url_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.url_ = str;
      return str;
    }

    public boolean hasDescription()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasPurpose()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasRecommendedQuickAction()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasUrl()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasUrl())
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
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeBytes(1, getUrlBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getDescriptionBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeEnum(3, this.purpose_.getNumber());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBool(4, this.recommendedQuickAction_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UrlGroupProtos.Url, Builder>
      implements UrlGroupProtos.UrlOrBuilder
    {
      private int bitField0_;
      private Object description_ = "";
      private UrlGroupProtos.Url.Purpose purpose_ = UrlGroupProtos.Url.Purpose.UNKNOWN;
      private boolean recommendedQuickAction_;
      private Object url_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private UrlGroupProtos.Url buildParsed()
        throws InvalidProtocolBufferException
      {
        UrlGroupProtos.Url localUrl = buildPartial();
        if (!localUrl.isInitialized())
          throw newUninitializedMessageException(localUrl).asInvalidProtocolBufferException();
        return localUrl;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public UrlGroupProtos.Url build()
      {
        UrlGroupProtos.Url localUrl = buildPartial();
        if (!localUrl.isInitialized())
          throw newUninitializedMessageException(localUrl);
        return localUrl;
      }

      public UrlGroupProtos.Url buildPartial()
      {
        int i = 1;
        UrlGroupProtos.Url localUrl = new UrlGroupProtos.Url(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          UrlGroupProtos.Url.access$302(localUrl, this.url_);
          if ((j & 0x2) == 2)
            i |= 2;
          UrlGroupProtos.Url.access$402(localUrl, this.description_);
          if ((j & 0x4) == 4)
            i |= 4;
          UrlGroupProtos.Url.access$502(localUrl, this.purpose_);
          if ((j & 0x8) == 8)
            i |= 8;
          UrlGroupProtos.Url.access$602(localUrl, this.recommendedQuickAction_);
          UrlGroupProtos.Url.access$702(localUrl, i);
          return localUrl;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.url_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.description_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.purpose_ = UrlGroupProtos.Url.Purpose.UNKNOWN;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.recommendedQuickAction_ = false;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearDescription()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.description_ = UrlGroupProtos.Url.getDefaultInstance().getDescription();
        return this;
      }

      public Builder clearPurpose()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.purpose_ = UrlGroupProtos.Url.Purpose.UNKNOWN;
        return this;
      }

      public Builder clearRecommendedQuickAction()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.recommendedQuickAction_ = false;
        return this;
      }

      public Builder clearUrl()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.url_ = UrlGroupProtos.Url.getDefaultInstance().getUrl();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public UrlGroupProtos.Url getDefaultInstanceForType()
      {
        return UrlGroupProtos.Url.getDefaultInstance();
      }

      public String getDescription()
      {
        Object localObject = this.description_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.description_ = str;
          return str;
        }
        return (String)localObject;
      }

      public UrlGroupProtos.Url.Purpose getPurpose()
      {
        return this.purpose_;
      }

      public boolean getRecommendedQuickAction()
      {
        return this.recommendedQuickAction_;
      }

      public String getUrl()
      {
        Object localObject = this.url_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.url_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasDescription()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasPurpose()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasRecommendedQuickAction()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasUrl()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return hasUrl();
      }

      public Builder mergeFrom(UrlGroupProtos.Url paramUrl)
      {
        if (paramUrl == UrlGroupProtos.Url.getDefaultInstance());
        do
        {
          return this;
          if (paramUrl.hasUrl())
            setUrl(paramUrl.getUrl());
          if (paramUrl.hasDescription())
            setDescription(paramUrl.getDescription());
          if (paramUrl.hasPurpose())
            setPurpose(paramUrl.getPurpose());
        }
        while (!paramUrl.hasRecommendedQuickAction());
        setRecommendedQuickAction(paramUrl.getRecommendedQuickAction());
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
            this.url_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.description_ = paramCodedInputStream.readBytes();
            break;
          case 24:
            UrlGroupProtos.Url.Purpose localPurpose = UrlGroupProtos.Url.Purpose.valueOf(paramCodedInputStream.readEnum());
            if (localPurpose == null)
              continue;
            this.bitField0_ = (0x4 | this.bitField0_);
            this.purpose_ = localPurpose;
            break;
          case 32:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.recommendedQuickAction_ = paramCodedInputStream.readBool();
        }
      }

      public Builder setDescription(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.description_ = paramString;
        return this;
      }

      void setDescription(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.description_ = paramByteString;
      }

      public Builder setPurpose(UrlGroupProtos.Url.Purpose paramPurpose)
      {
        if (paramPurpose == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.purpose_ = paramPurpose;
        return this;
      }

      public Builder setRecommendedQuickAction(boolean paramBoolean)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.recommendedQuickAction_ = paramBoolean;
        return this;
      }

      public Builder setUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.url_ = paramString;
        return this;
      }

      void setUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.url_ = paramByteString;
      }
    }

    public static enum Purpose
      implements Internal.EnumLite
    {
      public static final int ADD_CONTACT_VALUE = 9;
      public static final int BOOK_SEARCH_VALUE = 4;
      public static final int COPY_VALUE = 10;
      public static final int DIRECT_PRODUCT_LINK_VALUE = 3;
      public static final int EMAIL_VALUE = 8;
      public static final int GENERIC_VALUE = 1;
      public static final int MAP_SEARCH_VALUE = 2;
      public static final int PRODUCT_SEARCH_QUERY_VALUE = 12;
      public static final int SUDOKU_VALUE = 11;
      public static final int TELEPHONE_VALUE = 7;
      public static final int TRANSLATE_VALUE = 6;
      public static final int UNKNOWN_VALUE = 0;
      public static final int WEB_SEARCH_VALUE = 5;
      private static Internal.EnumLiteMap<Purpose> internalValueMap = new Internal.EnumLiteMap()
      {
        public UrlGroupProtos.Url.Purpose findValueByNumber(int paramAnonymousInt)
        {
          return UrlGroupProtos.Url.Purpose.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        GENERIC = new Purpose("GENERIC", 1, 1, 1);
        MAP_SEARCH = new Purpose("MAP_SEARCH", 2, 2, 2);
        DIRECT_PRODUCT_LINK = new Purpose("DIRECT_PRODUCT_LINK", 3, 3, 3);
        BOOK_SEARCH = new Purpose("BOOK_SEARCH", 4, 4, 4);
        WEB_SEARCH = new Purpose("WEB_SEARCH", 5, 5, 5);
        TRANSLATE = new Purpose("TRANSLATE", 6, 6, 6);
        TELEPHONE = new Purpose("TELEPHONE", 7, 7, 7);
        EMAIL = new Purpose("EMAIL", 8, 8, 8);
        ADD_CONTACT = new Purpose("ADD_CONTACT", 9, 9, 9);
        COPY = new Purpose("COPY", 10, 10, 10);
        SUDOKU = new Purpose("SUDOKU", 11, 11, 11);
        PRODUCT_SEARCH_QUERY = new Purpose("PRODUCT_SEARCH_QUERY", 12, 12, 12);
        Purpose[] arrayOfPurpose = new Purpose[13];
        arrayOfPurpose[0] = UNKNOWN;
        arrayOfPurpose[1] = GENERIC;
        arrayOfPurpose[2] = MAP_SEARCH;
        arrayOfPurpose[3] = DIRECT_PRODUCT_LINK;
        arrayOfPurpose[4] = BOOK_SEARCH;
        arrayOfPurpose[5] = WEB_SEARCH;
        arrayOfPurpose[6] = TRANSLATE;
        arrayOfPurpose[7] = TELEPHONE;
        arrayOfPurpose[8] = EMAIL;
        arrayOfPurpose[9] = ADD_CONTACT;
        arrayOfPurpose[10] = COPY;
        arrayOfPurpose[11] = SUDOKU;
        arrayOfPurpose[12] = PRODUCT_SEARCH_QUERY;
      }

      private Purpose(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Purpose> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Purpose valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 0:
          return UNKNOWN;
        case 1:
          return GENERIC;
        case 2:
          return MAP_SEARCH;
        case 3:
          return DIRECT_PRODUCT_LINK;
        case 4:
          return BOOK_SEARCH;
        case 5:
          return WEB_SEARCH;
        case 6:
          return TRANSLATE;
        case 7:
          return TELEPHONE;
        case 8:
          return EMAIL;
        case 9:
          return ADD_CONTACT;
        case 10:
          return COPY;
        case 11:
          return SUDOKU;
        case 12:
        }
        return PRODUCT_SEARCH_QUERY;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static final class UrlGroup extends GeneratedMessageLite
    implements UrlGroupProtos.UrlGroupOrBuilder
  {
    public static final int URL_FIELD_NUMBER = 1;
    private static final UrlGroup defaultInstance = new UrlGroup(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<UrlGroupProtos.Url> url_;

    static
    {
      defaultInstance.initFields();
    }

    private UrlGroup(Builder paramBuilder)
    {
      super();
    }

    private UrlGroup(boolean paramBoolean)
    {
    }

    public static UrlGroup getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.url_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$900();
    }

    public static Builder newBuilder(UrlGroup paramUrlGroup)
    {
      return newBuilder().mergeFrom(paramUrlGroup);
    }

    public static UrlGroup parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static UrlGroup parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static UrlGroup parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static UrlGroup parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static UrlGroup parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static UrlGroup parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static UrlGroup parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static UrlGroup parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static UrlGroup parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static UrlGroup parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public UrlGroup getDefaultInstanceForType()
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
      while (j < this.url_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.url_.get(j));
        j++;
      }
      this.memoizedSerializedSize = k;
      return k;
    }

    public UrlGroupProtos.Url getUrl(int paramInt)
    {
      return (UrlGroupProtos.Url)this.url_.get(paramInt);
    }

    public int getUrlCount()
    {
      return this.url_.size();
    }

    public List<UrlGroupProtos.Url> getUrlList()
    {
      return this.url_;
    }

    public UrlGroupProtos.UrlOrBuilder getUrlOrBuilder(int paramInt)
    {
      return (UrlGroupProtos.UrlOrBuilder)this.url_.get(paramInt);
    }

    public List<? extends UrlGroupProtos.UrlOrBuilder> getUrlOrBuilderList()
    {
      return this.url_;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getUrlCount(); j++)
        if (!getUrl(j).isInitialized())
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
      for (int i = 0; i < this.url_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.url_.get(i));
    }

    public static final class Builder extends GeneratedMessageLite.Builder<UrlGroupProtos.UrlGroup, Builder>
      implements UrlGroupProtos.UrlGroupOrBuilder
    {
      private int bitField0_;
      private List<UrlGroupProtos.Url> url_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private UrlGroupProtos.UrlGroup buildParsed()
        throws InvalidProtocolBufferException
      {
        UrlGroupProtos.UrlGroup localUrlGroup = buildPartial();
        if (!localUrlGroup.isInitialized())
          throw newUninitializedMessageException(localUrlGroup).asInvalidProtocolBufferException();
        return localUrlGroup;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureUrlIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.url_ = new ArrayList(this.url_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllUrl(Iterable<? extends UrlGroupProtos.Url> paramIterable)
      {
        ensureUrlIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.url_);
        return this;
      }

      public Builder addUrl(int paramInt, UrlGroupProtos.Url.Builder paramBuilder)
      {
        ensureUrlIsMutable();
        this.url_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addUrl(int paramInt, UrlGroupProtos.Url paramUrl)
      {
        if (paramUrl == null)
          throw new NullPointerException();
        ensureUrlIsMutable();
        this.url_.add(paramInt, paramUrl);
        return this;
      }

      public Builder addUrl(UrlGroupProtos.Url.Builder paramBuilder)
      {
        ensureUrlIsMutable();
        this.url_.add(paramBuilder.build());
        return this;
      }

      public Builder addUrl(UrlGroupProtos.Url paramUrl)
      {
        if (paramUrl == null)
          throw new NullPointerException();
        ensureUrlIsMutable();
        this.url_.add(paramUrl);
        return this;
      }

      public UrlGroupProtos.UrlGroup build()
      {
        UrlGroupProtos.UrlGroup localUrlGroup = buildPartial();
        if (!localUrlGroup.isInitialized())
          throw newUninitializedMessageException(localUrlGroup);
        return localUrlGroup;
      }

      public UrlGroupProtos.UrlGroup buildPartial()
      {
        UrlGroupProtos.UrlGroup localUrlGroup = new UrlGroupProtos.UrlGroup(this, null);
        if ((0x1 & this.bitField0_) == 1)
        {
          this.url_ = Collections.unmodifiableList(this.url_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        UrlGroupProtos.UrlGroup.access$1102(localUrlGroup, this.url_);
        return localUrlGroup;
      }

      public Builder clear()
      {
        super.clear();
        this.url_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearUrl()
      {
        this.url_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public UrlGroupProtos.UrlGroup getDefaultInstanceForType()
      {
        return UrlGroupProtos.UrlGroup.getDefaultInstance();
      }

      public UrlGroupProtos.Url getUrl(int paramInt)
      {
        return (UrlGroupProtos.Url)this.url_.get(paramInt);
      }

      public int getUrlCount()
      {
        return this.url_.size();
      }

      public List<UrlGroupProtos.Url> getUrlList()
      {
        return Collections.unmodifiableList(this.url_);
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getUrlCount(); i++)
          if (!getUrl(i).isInitialized())
            return false;
        return true;
      }

      public Builder mergeFrom(UrlGroupProtos.UrlGroup paramUrlGroup)
      {
        if (paramUrlGroup == UrlGroupProtos.UrlGroup.getDefaultInstance());
        while (paramUrlGroup.url_.isEmpty())
          return this;
        if (this.url_.isEmpty())
        {
          this.url_ = paramUrlGroup.url_;
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          return this;
        }
        ensureUrlIsMutable();
        this.url_.addAll(paramUrlGroup.url_);
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
          UrlGroupProtos.Url.Builder localBuilder = UrlGroupProtos.Url.newBuilder();
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          addUrl(localBuilder.buildPartial());
        }
      }

      public Builder removeUrl(int paramInt)
      {
        ensureUrlIsMutable();
        this.url_.remove(paramInt);
        return this;
      }

      public Builder setUrl(int paramInt, UrlGroupProtos.Url.Builder paramBuilder)
      {
        ensureUrlIsMutable();
        this.url_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setUrl(int paramInt, UrlGroupProtos.Url paramUrl)
      {
        if (paramUrl == null)
          throw new NullPointerException();
        ensureUrlIsMutable();
        this.url_.set(paramInt, paramUrl);
        return this;
      }
    }
  }

  public static abstract interface UrlGroupOrBuilder extends MessageLiteOrBuilder
  {
    public abstract UrlGroupProtos.Url getUrl(int paramInt);

    public abstract int getUrlCount();

    public abstract List<UrlGroupProtos.Url> getUrlList();
  }

  public static abstract interface UrlOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getDescription();

    public abstract UrlGroupProtos.Url.Purpose getPurpose();

    public abstract boolean getRecommendedQuickAction();

    public abstract String getUrl();

    public abstract boolean hasDescription();

    public abstract boolean hasPurpose();

    public abstract boolean hasRecommendedQuickAction();

    public abstract boolean hasUrl();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.UrlGroupProtos
 * JD-Core Version:    0.6.2
 */