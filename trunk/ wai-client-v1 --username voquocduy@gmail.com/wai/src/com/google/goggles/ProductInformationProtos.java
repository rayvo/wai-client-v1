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

public final class ProductInformationProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class ProductInformation extends GeneratedMessageLite
    implements ProductInformationProtos.ProductInformationOrBuilder
  {
    public static final int AUTHOR_FIELD_NUMBER = 4;
    public static final int BRAND_FIELD_NUMBER = 10;
    public static final int CATALOG_URL_FIELD_NUMBER = 14;
    public static final int CATEGORIES_FIELD_NUMBER = 13;
    public static final int CID_FIELD_NUMBER = 8;
    public static final int CLUSTER_ID_FIELD_NUMBER = 12;
    public static final int GENDER_FIELD_NUMBER = 11;
    public static final int ISBN_FIELD_NUMBER = 7;
    public static final int NUM_REVIEWS_FIELD_NUMBER = 2;
    public static final int OFFERS_FIELD_NUMBER = 9;
    public static final int PRICES_FIELD_NUMBER = 3;
    public static final int PUBLICATION_YEAR_FIELD_NUMBER = 6;
    public static final int PUBLISHER_FIELD_NUMBER = 5;
    public static final int STAR_RATING_FIELD_NUMBER = 1;
    private static final ProductInformation defaultInstance = new ProductInformation(true);
    private static final long serialVersionUID;
    private Object author_;
    private int bitField0_;
    private Object brand_;
    private Object catalogUrl_;
    private List<RestrictsProtos.Category> categories_;
    private Object cid_;
    private Object clusterId_;
    private RestrictsProtos.Restricts.Gender gender_;
    private Object isbn_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int numReviews_;
    private List<OfferInformation> offers_;
    private List<PriceInformation> prices_;
    private Object publicationYear_;
    private Object publisher_;
    private float starRating_;

    static
    {
      defaultInstance.initFields();
    }

    private ProductInformation(Builder paramBuilder)
    {
      super();
    }

    private ProductInformation(boolean paramBoolean)
    {
    }

    private ByteString getAuthorBytes()
    {
      Object localObject = this.author_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.author_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getBrandBytes()
    {
      Object localObject = this.brand_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.brand_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getCatalogUrlBytes()
    {
      Object localObject = this.catalogUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.catalogUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getCidBytes()
    {
      Object localObject = this.cid_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.cid_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getClusterIdBytes()
    {
      Object localObject = this.clusterId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.clusterId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static ProductInformation getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getIsbnBytes()
    {
      Object localObject = this.isbn_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.isbn_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getPublicationYearBytes()
    {
      Object localObject = this.publicationYear_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.publicationYear_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getPublisherBytes()
    {
      Object localObject = this.publisher_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.publisher_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.starRating_ = 0.0F;
      this.numReviews_ = 0;
      this.prices_ = Collections.emptyList();
      this.author_ = "";
      this.publisher_ = "";
      this.publicationYear_ = "";
      this.isbn_ = "";
      this.cid_ = "";
      this.offers_ = Collections.emptyList();
      this.brand_ = "";
      this.gender_ = RestrictsProtos.Restricts.Gender.WOMEN;
      this.clusterId_ = "";
      this.categories_ = Collections.emptyList();
      this.catalogUrl_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$1700();
    }

    public static Builder newBuilder(ProductInformation paramProductInformation)
    {
      return newBuilder().mergeFrom(paramProductInformation);
    }

    public static ProductInformation parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ProductInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ProductInformation parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ProductInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ProductInformation parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ProductInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ProductInformation parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ProductInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ProductInformation parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ProductInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getAuthor()
    {
      Object localObject = this.author_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.author_ = str;
      return str;
    }

    public String getBrand()
    {
      Object localObject = this.brand_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.brand_ = str;
      return str;
    }

    public String getCatalogUrl()
    {
      Object localObject = this.catalogUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.catalogUrl_ = str;
      return str;
    }

    public RestrictsProtos.Category getCategories(int paramInt)
    {
      return (RestrictsProtos.Category)this.categories_.get(paramInt);
    }

    public int getCategoriesCount()
    {
      return this.categories_.size();
    }

    public List<RestrictsProtos.Category> getCategoriesList()
    {
      return this.categories_;
    }

    public RestrictsProtos.CategoryOrBuilder getCategoriesOrBuilder(int paramInt)
    {
      return (RestrictsProtos.CategoryOrBuilder)this.categories_.get(paramInt);
    }

    public List<? extends RestrictsProtos.CategoryOrBuilder> getCategoriesOrBuilderList()
    {
      return this.categories_;
    }

    public String getCid()
    {
      Object localObject = this.cid_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.cid_ = str;
      return str;
    }

    public String getClusterId()
    {
      Object localObject = this.clusterId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.clusterId_ = str;
      return str;
    }

    public ProductInformation getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public RestrictsProtos.Restricts.Gender getGender()
    {
      return this.gender_;
    }

    public String getIsbn()
    {
      Object localObject = this.isbn_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.isbn_ = str;
      return str;
    }

    public int getNumReviews()
    {
      return this.numReviews_;
    }

    public OfferInformation getOffers(int paramInt)
    {
      return (OfferInformation)this.offers_.get(paramInt);
    }

    public int getOffersCount()
    {
      return this.offers_.size();
    }

    public List<OfferInformation> getOffersList()
    {
      return this.offers_;
    }

    public OfferInformationOrBuilder getOffersOrBuilder(int paramInt)
    {
      return (OfferInformationOrBuilder)this.offers_.get(paramInt);
    }

    public List<? extends OfferInformationOrBuilder> getOffersOrBuilderList()
    {
      return this.offers_;
    }

    public PriceInformation getPrices(int paramInt)
    {
      return (PriceInformation)this.prices_.get(paramInt);
    }

    public int getPricesCount()
    {
      return this.prices_.size();
    }

    public List<PriceInformation> getPricesList()
    {
      return this.prices_;
    }

    public PriceInformationOrBuilder getPricesOrBuilder(int paramInt)
    {
      return (PriceInformationOrBuilder)this.prices_.get(paramInt);
    }

    public List<? extends PriceInformationOrBuilder> getPricesOrBuilderList()
    {
      return this.prices_;
    }

    public String getPublicationYear()
    {
      Object localObject = this.publicationYear_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.publicationYear_ = str;
      return str;
    }

    public String getPublisher()
    {
      Object localObject = this.publisher_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.publisher_ = str;
      return str;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      if ((0x1 & this.bitField0_) == 1);
      for (int j = 0 + CodedOutputStream.computeFloatSize(1, this.starRating_); ; j = 0)
      {
        if ((0x2 & this.bitField0_) == 2)
          j += CodedOutputStream.computeInt32Size(2, this.numReviews_);
        int k = 0;
        int m = j;
        while (k < this.prices_.size())
        {
          m += CodedOutputStream.computeMessageSize(3, (MessageLite)this.prices_.get(k));
          k++;
        }
        if ((0x4 & this.bitField0_) == 4)
          m += CodedOutputStream.computeBytesSize(4, getAuthorBytes());
        if ((0x8 & this.bitField0_) == 8)
          m += CodedOutputStream.computeBytesSize(5, getPublisherBytes());
        if ((0x10 & this.bitField0_) == 16)
          m += CodedOutputStream.computeBytesSize(6, getPublicationYearBytes());
        if ((0x20 & this.bitField0_) == 32)
          m += CodedOutputStream.computeBytesSize(7, getIsbnBytes());
        if ((0x40 & this.bitField0_) == 64)
          m += CodedOutputStream.computeBytesSize(8, getCidBytes());
        for (int n = 0; n < this.offers_.size(); n++)
          m += CodedOutputStream.computeMessageSize(9, (MessageLite)this.offers_.get(n));
        if ((0x80 & this.bitField0_) == 128)
          m += CodedOutputStream.computeBytesSize(10, getBrandBytes());
        if ((0x100 & this.bitField0_) == 256)
          m += CodedOutputStream.computeEnumSize(11, this.gender_.getNumber());
        int i1 = 0x200 & this.bitField0_;
        int i2 = 0;
        if (i1 == 512)
          m += CodedOutputStream.computeBytesSize(12, getClusterIdBytes());
        while (i2 < this.categories_.size())
        {
          m += CodedOutputStream.computeMessageSize(13, (MessageLite)this.categories_.get(i2));
          i2++;
        }
        if ((0x400 & this.bitField0_) == 1024)
          m += CodedOutputStream.computeBytesSize(14, getCatalogUrlBytes());
        this.memoizedSerializedSize = m;
        return m;
      }
    }

    public float getStarRating()
    {
      return this.starRating_;
    }

    public boolean hasAuthor()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasBrand()
    {
      return (0x80 & this.bitField0_) == 128;
    }

    public boolean hasCatalogUrl()
    {
      return (0x400 & this.bitField0_) == 1024;
    }

    public boolean hasCid()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasClusterId()
    {
      return (0x200 & this.bitField0_) == 512;
    }

    public boolean hasGender()
    {
      return (0x100 & this.bitField0_) == 256;
    }

    public boolean hasIsbn()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasNumReviews()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasPublicationYear()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasPublisher()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasStarRating()
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
        paramCodedOutputStream.writeFloat(1, this.starRating_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt32(2, this.numReviews_);
      for (int i = 0; i < this.prices_.size(); i++)
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.prices_.get(i));
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(4, getAuthorBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(5, getPublisherBytes());
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(6, getPublicationYearBytes());
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(7, getIsbnBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(8, getCidBytes());
      for (int j = 0; j < this.offers_.size(); j++)
        paramCodedOutputStream.writeMessage(9, (MessageLite)this.offers_.get(j));
      if ((0x80 & this.bitField0_) == 128)
        paramCodedOutputStream.writeBytes(10, getBrandBytes());
      if ((0x100 & this.bitField0_) == 256)
        paramCodedOutputStream.writeEnum(11, this.gender_.getNumber());
      int k = 0x200 & this.bitField0_;
      int m = 0;
      if (k == 512)
        paramCodedOutputStream.writeBytes(12, getClusterIdBytes());
      while (m < this.categories_.size())
      {
        paramCodedOutputStream.writeMessage(13, (MessageLite)this.categories_.get(m));
        m++;
      }
      if ((0x400 & this.bitField0_) == 1024)
        paramCodedOutputStream.writeBytes(14, getCatalogUrlBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ProductInformationProtos.ProductInformation, Builder>
      implements ProductInformationProtos.ProductInformationOrBuilder
    {
      private Object author_ = "";
      private int bitField0_;
      private Object brand_ = "";
      private Object catalogUrl_ = "";
      private List<RestrictsProtos.Category> categories_ = Collections.emptyList();
      private Object cid_ = "";
      private Object clusterId_ = "";
      private RestrictsProtos.Restricts.Gender gender_ = RestrictsProtos.Restricts.Gender.WOMEN;
      private Object isbn_ = "";
      private int numReviews_;
      private List<ProductInformationProtos.ProductInformation.OfferInformation> offers_ = Collections.emptyList();
      private List<ProductInformationProtos.ProductInformation.PriceInformation> prices_ = Collections.emptyList();
      private Object publicationYear_ = "";
      private Object publisher_ = "";
      private float starRating_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ProductInformationProtos.ProductInformation buildParsed()
        throws InvalidProtocolBufferException
      {
        ProductInformationProtos.ProductInformation localProductInformation = buildPartial();
        if (!localProductInformation.isInitialized())
          throw newUninitializedMessageException(localProductInformation).asInvalidProtocolBufferException();
        return localProductInformation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureCategoriesIsMutable()
      {
        if ((0x1000 & this.bitField0_) != 4096)
        {
          this.categories_ = new ArrayList(this.categories_);
          this.bitField0_ = (0x1000 | this.bitField0_);
        }
      }

      private void ensureOffersIsMutable()
      {
        if ((0x100 & this.bitField0_) != 256)
        {
          this.offers_ = new ArrayList(this.offers_);
          this.bitField0_ = (0x100 | this.bitField0_);
        }
      }

      private void ensurePricesIsMutable()
      {
        if ((0x4 & this.bitField0_) != 4)
        {
          this.prices_ = new ArrayList(this.prices_);
          this.bitField0_ = (0x4 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllCategories(Iterable<? extends RestrictsProtos.Category> paramIterable)
      {
        ensureCategoriesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.categories_);
        return this;
      }

      public Builder addAllOffers(Iterable<? extends ProductInformationProtos.ProductInformation.OfferInformation> paramIterable)
      {
        ensureOffersIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.offers_);
        return this;
      }

      public Builder addAllPrices(Iterable<? extends ProductInformationProtos.ProductInformation.PriceInformation> paramIterable)
      {
        ensurePricesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.prices_);
        return this;
      }

      public Builder addCategories(int paramInt, RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addCategories(int paramInt, RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.add(paramInt, paramCategory);
        return this;
      }

      public Builder addCategories(RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.add(paramBuilder.build());
        return this;
      }

      public Builder addCategories(RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.add(paramCategory);
        return this;
      }

      public Builder addOffers(int paramInt, ProductInformationProtos.ProductInformation.OfferInformation.Builder paramBuilder)
      {
        ensureOffersIsMutable();
        this.offers_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addOffers(int paramInt, ProductInformationProtos.ProductInformation.OfferInformation paramOfferInformation)
      {
        if (paramOfferInformation == null)
          throw new NullPointerException();
        ensureOffersIsMutable();
        this.offers_.add(paramInt, paramOfferInformation);
        return this;
      }

      public Builder addOffers(ProductInformationProtos.ProductInformation.OfferInformation.Builder paramBuilder)
      {
        ensureOffersIsMutable();
        this.offers_.add(paramBuilder.build());
        return this;
      }

      public Builder addOffers(ProductInformationProtos.ProductInformation.OfferInformation paramOfferInformation)
      {
        if (paramOfferInformation == null)
          throw new NullPointerException();
        ensureOffersIsMutable();
        this.offers_.add(paramOfferInformation);
        return this;
      }

      public Builder addPrices(int paramInt, ProductInformationProtos.ProductInformation.PriceInformation.Builder paramBuilder)
      {
        ensurePricesIsMutable();
        this.prices_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addPrices(int paramInt, ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
      {
        if (paramPriceInformation == null)
          throw new NullPointerException();
        ensurePricesIsMutable();
        this.prices_.add(paramInt, paramPriceInformation);
        return this;
      }

      public Builder addPrices(ProductInformationProtos.ProductInformation.PriceInformation.Builder paramBuilder)
      {
        ensurePricesIsMutable();
        this.prices_.add(paramBuilder.build());
        return this;
      }

      public Builder addPrices(ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
      {
        if (paramPriceInformation == null)
          throw new NullPointerException();
        ensurePricesIsMutable();
        this.prices_.add(paramPriceInformation);
        return this;
      }

      public ProductInformationProtos.ProductInformation build()
      {
        ProductInformationProtos.ProductInformation localProductInformation = buildPartial();
        if (!localProductInformation.isInitialized())
          throw newUninitializedMessageException(localProductInformation);
        return localProductInformation;
      }

      public ProductInformationProtos.ProductInformation buildPartial()
      {
        int i = 1;
        ProductInformationProtos.ProductInformation localProductInformation = new ProductInformationProtos.ProductInformation(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ProductInformationProtos.ProductInformation.access$1902(localProductInformation, this.starRating_);
          if ((j & 0x2) == 2)
            i |= 2;
          ProductInformationProtos.ProductInformation.access$2002(localProductInformation, this.numReviews_);
          if ((0x4 & this.bitField0_) == 4)
          {
            this.prices_ = Collections.unmodifiableList(this.prices_);
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          }
          ProductInformationProtos.ProductInformation.access$2102(localProductInformation, this.prices_);
          if ((j & 0x8) == 8)
            i |= 4;
          ProductInformationProtos.ProductInformation.access$2202(localProductInformation, this.author_);
          if ((j & 0x10) == 16)
            i |= 8;
          ProductInformationProtos.ProductInformation.access$2302(localProductInformation, this.publisher_);
          if ((j & 0x20) == 32)
            i |= 16;
          ProductInformationProtos.ProductInformation.access$2402(localProductInformation, this.publicationYear_);
          if ((j & 0x40) == 64)
            i |= 32;
          ProductInformationProtos.ProductInformation.access$2502(localProductInformation, this.isbn_);
          if ((j & 0x80) == 128)
            i |= 64;
          ProductInformationProtos.ProductInformation.access$2602(localProductInformation, this.cid_);
          if ((0x100 & this.bitField0_) == 256)
          {
            this.offers_ = Collections.unmodifiableList(this.offers_);
            this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
          }
          ProductInformationProtos.ProductInformation.access$2702(localProductInformation, this.offers_);
          if ((j & 0x200) == 512)
            i |= 128;
          ProductInformationProtos.ProductInformation.access$2802(localProductInformation, this.brand_);
          if ((j & 0x400) == 1024)
            i |= 256;
          ProductInformationProtos.ProductInformation.access$2902(localProductInformation, this.gender_);
          if ((j & 0x800) == 2048)
            i |= 512;
          ProductInformationProtos.ProductInformation.access$3002(localProductInformation, this.clusterId_);
          if ((0x1000 & this.bitField0_) == 4096)
          {
            this.categories_ = Collections.unmodifiableList(this.categories_);
            this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
          }
          ProductInformationProtos.ProductInformation.access$3102(localProductInformation, this.categories_);
          if ((j & 0x2000) == 8192)
            i |= 1024;
          ProductInformationProtos.ProductInformation.access$3202(localProductInformation, this.catalogUrl_);
          ProductInformationProtos.ProductInformation.access$3302(localProductInformation, i);
          return localProductInformation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.starRating_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.numReviews_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.prices_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.author_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.publisher_ = "";
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.publicationYear_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.isbn_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.cid_ = "";
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.offers_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.brand_ = "";
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.gender_ = RestrictsProtos.Restricts.Gender.WOMEN;
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        this.clusterId_ = "";
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        this.categories_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
        this.catalogUrl_ = "";
        this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
        return this;
      }

      public Builder clearAuthor()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.author_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getAuthor();
        return this;
      }

      public Builder clearBrand()
      {
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.brand_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getBrand();
        return this;
      }

      public Builder clearCatalogUrl()
      {
        this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
        this.catalogUrl_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getCatalogUrl();
        return this;
      }

      public Builder clearCategories()
      {
        this.categories_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
        return this;
      }

      public Builder clearCid()
      {
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.cid_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getCid();
        return this;
      }

      public Builder clearClusterId()
      {
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        this.clusterId_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getClusterId();
        return this;
      }

      public Builder clearGender()
      {
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        this.gender_ = RestrictsProtos.Restricts.Gender.WOMEN;
        return this;
      }

      public Builder clearIsbn()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.isbn_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getIsbn();
        return this;
      }

      public Builder clearNumReviews()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.numReviews_ = 0;
        return this;
      }

      public Builder clearOffers()
      {
        this.offers_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clearPrices()
      {
        this.prices_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearPublicationYear()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.publicationYear_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getPublicationYear();
        return this;
      }

      public Builder clearPublisher()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.publisher_ = ProductInformationProtos.ProductInformation.getDefaultInstance().getPublisher();
        return this;
      }

      public Builder clearStarRating()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.starRating_ = 0.0F;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getAuthor()
      {
        Object localObject = this.author_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.author_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getBrand()
      {
        Object localObject = this.brand_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.brand_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getCatalogUrl()
      {
        Object localObject = this.catalogUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.catalogUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public RestrictsProtos.Category getCategories(int paramInt)
      {
        return (RestrictsProtos.Category)this.categories_.get(paramInt);
      }

      public int getCategoriesCount()
      {
        return this.categories_.size();
      }

      public List<RestrictsProtos.Category> getCategoriesList()
      {
        return Collections.unmodifiableList(this.categories_);
      }

      public String getCid()
      {
        Object localObject = this.cid_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.cid_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getClusterId()
      {
        Object localObject = this.clusterId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.clusterId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public ProductInformationProtos.ProductInformation getDefaultInstanceForType()
      {
        return ProductInformationProtos.ProductInformation.getDefaultInstance();
      }

      public RestrictsProtos.Restricts.Gender getGender()
      {
        return this.gender_;
      }

      public String getIsbn()
      {
        Object localObject = this.isbn_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.isbn_ = str;
          return str;
        }
        return (String)localObject;
      }

      public int getNumReviews()
      {
        return this.numReviews_;
      }

      public ProductInformationProtos.ProductInformation.OfferInformation getOffers(int paramInt)
      {
        return (ProductInformationProtos.ProductInformation.OfferInformation)this.offers_.get(paramInt);
      }

      public int getOffersCount()
      {
        return this.offers_.size();
      }

      public List<ProductInformationProtos.ProductInformation.OfferInformation> getOffersList()
      {
        return Collections.unmodifiableList(this.offers_);
      }

      public ProductInformationProtos.ProductInformation.PriceInformation getPrices(int paramInt)
      {
        return (ProductInformationProtos.ProductInformation.PriceInformation)this.prices_.get(paramInt);
      }

      public int getPricesCount()
      {
        return this.prices_.size();
      }

      public List<ProductInformationProtos.ProductInformation.PriceInformation> getPricesList()
      {
        return Collections.unmodifiableList(this.prices_);
      }

      public String getPublicationYear()
      {
        Object localObject = this.publicationYear_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.publicationYear_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getPublisher()
      {
        Object localObject = this.publisher_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.publisher_ = str;
          return str;
        }
        return (String)localObject;
      }

      public float getStarRating()
      {
        return this.starRating_;
      }

      public boolean hasAuthor()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasBrand()
      {
        return (0x200 & this.bitField0_) == 512;
      }

      public boolean hasCatalogUrl()
      {
        return (0x2000 & this.bitField0_) == 8192;
      }

      public boolean hasCid()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasClusterId()
      {
        return (0x800 & this.bitField0_) == 2048;
      }

      public boolean hasGender()
      {
        return (0x400 & this.bitField0_) == 1024;
      }

      public boolean hasIsbn()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasNumReviews()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasPublicationYear()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasPublisher()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasStarRating()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(ProductInformationProtos.ProductInformation paramProductInformation)
      {
        if (paramProductInformation == ProductInformationProtos.ProductInformation.getDefaultInstance());
        label208: label339: label360: label379: 
        while (true)
        {
          return this;
          if (paramProductInformation.hasStarRating())
            setStarRating(paramProductInformation.getStarRating());
          if (paramProductInformation.hasNumReviews())
            setNumReviews(paramProductInformation.getNumReviews());
          if (!paramProductInformation.prices_.isEmpty())
          {
            if (this.prices_.isEmpty())
            {
              this.prices_ = paramProductInformation.prices_;
              this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
            }
          }
          else
          {
            if (paramProductInformation.hasAuthor())
              setAuthor(paramProductInformation.getAuthor());
            if (paramProductInformation.hasPublisher())
              setPublisher(paramProductInformation.getPublisher());
            if (paramProductInformation.hasPublicationYear())
              setPublicationYear(paramProductInformation.getPublicationYear());
            if (paramProductInformation.hasIsbn())
              setIsbn(paramProductInformation.getIsbn());
            if (paramProductInformation.hasCid())
              setCid(paramProductInformation.getCid());
            if (!paramProductInformation.offers_.isEmpty())
            {
              if (!this.offers_.isEmpty())
                break label339;
              this.offers_ = paramProductInformation.offers_;
              this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
            }
            if (paramProductInformation.hasBrand())
              setBrand(paramProductInformation.getBrand());
            if (paramProductInformation.hasGender())
              setGender(paramProductInformation.getGender());
            if (paramProductInformation.hasClusterId())
              setClusterId(paramProductInformation.getClusterId());
            if (!paramProductInformation.categories_.isEmpty())
            {
              if (!this.categories_.isEmpty())
                break label360;
              this.categories_ = paramProductInformation.categories_;
              this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
            }
          }
          while (true)
          {
            if (!paramProductInformation.hasCatalogUrl())
              break label379;
            setCatalogUrl(paramProductInformation.getCatalogUrl());
            return this;
            ensurePricesIsMutable();
            this.prices_.addAll(paramProductInformation.prices_);
            break;
            ensureOffersIsMutable();
            this.offers_.addAll(paramProductInformation.offers_);
            break label208;
            ensureCategoriesIsMutable();
            this.categories_.addAll(paramProductInformation.categories_);
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
          case 13:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.starRating_ = paramCodedInputStream.readFloat();
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.numReviews_ = paramCodedInputStream.readInt32();
            break;
          case 26:
            ProductInformationProtos.ProductInformation.PriceInformation.Builder localBuilder2 = ProductInformationProtos.ProductInformation.PriceInformation.newBuilder();
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            addPrices(localBuilder2.buildPartial());
            break;
          case 34:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.author_ = paramCodedInputStream.readBytes();
            break;
          case 42:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.publisher_ = paramCodedInputStream.readBytes();
            break;
          case 50:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.publicationYear_ = paramCodedInputStream.readBytes();
            break;
          case 58:
            this.bitField0_ = (0x40 | this.bitField0_);
            this.isbn_ = paramCodedInputStream.readBytes();
            break;
          case 66:
            this.bitField0_ = (0x80 | this.bitField0_);
            this.cid_ = paramCodedInputStream.readBytes();
            break;
          case 74:
            ProductInformationProtos.ProductInformation.OfferInformation.Builder localBuilder1 = ProductInformationProtos.ProductInformation.OfferInformation.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addOffers(localBuilder1.buildPartial());
            break;
          case 82:
            this.bitField0_ = (0x200 | this.bitField0_);
            this.brand_ = paramCodedInputStream.readBytes();
            break;
          case 88:
            RestrictsProtos.Restricts.Gender localGender = RestrictsProtos.Restricts.Gender.valueOf(paramCodedInputStream.readEnum());
            if (localGender == null)
              continue;
            this.bitField0_ = (0x400 | this.bitField0_);
            this.gender_ = localGender;
            break;
          case 98:
            this.bitField0_ = (0x800 | this.bitField0_);
            this.clusterId_ = paramCodedInputStream.readBytes();
            break;
          case 106:
            RestrictsProtos.Category.Builder localBuilder = RestrictsProtos.Category.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addCategories(localBuilder.buildPartial());
            break;
          case 114:
          }
          this.bitField0_ = (0x2000 | this.bitField0_);
          this.catalogUrl_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder removeCategories(int paramInt)
      {
        ensureCategoriesIsMutable();
        this.categories_.remove(paramInt);
        return this;
      }

      public Builder removeOffers(int paramInt)
      {
        ensureOffersIsMutable();
        this.offers_.remove(paramInt);
        return this;
      }

      public Builder removePrices(int paramInt)
      {
        ensurePricesIsMutable();
        this.prices_.remove(paramInt);
        return this;
      }

      public Builder setAuthor(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.author_ = paramString;
        return this;
      }

      void setAuthor(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.author_ = paramByteString;
      }

      public Builder setBrand(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x200 | this.bitField0_);
        this.brand_ = paramString;
        return this;
      }

      void setBrand(ByteString paramByteString)
      {
        this.bitField0_ = (0x200 | this.bitField0_);
        this.brand_ = paramByteString;
      }

      public Builder setCatalogUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2000 | this.bitField0_);
        this.catalogUrl_ = paramString;
        return this;
      }

      void setCatalogUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x2000 | this.bitField0_);
        this.catalogUrl_ = paramByteString;
      }

      public Builder setCategories(int paramInt, RestrictsProtos.Category.Builder paramBuilder)
      {
        ensureCategoriesIsMutable();
        this.categories_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setCategories(int paramInt, RestrictsProtos.Category paramCategory)
      {
        if (paramCategory == null)
          throw new NullPointerException();
        ensureCategoriesIsMutable();
        this.categories_.set(paramInt, paramCategory);
        return this;
      }

      public Builder setCid(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x80 | this.bitField0_);
        this.cid_ = paramString;
        return this;
      }

      void setCid(ByteString paramByteString)
      {
        this.bitField0_ = (0x80 | this.bitField0_);
        this.cid_ = paramByteString;
      }

      public Builder setClusterId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x800 | this.bitField0_);
        this.clusterId_ = paramString;
        return this;
      }

      void setClusterId(ByteString paramByteString)
      {
        this.bitField0_ = (0x800 | this.bitField0_);
        this.clusterId_ = paramByteString;
      }

      public Builder setGender(RestrictsProtos.Restricts.Gender paramGender)
      {
        if (paramGender == null)
          throw new NullPointerException();
        this.bitField0_ = (0x400 | this.bitField0_);
        this.gender_ = paramGender;
        return this;
      }

      public Builder setIsbn(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.isbn_ = paramString;
        return this;
      }

      void setIsbn(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.isbn_ = paramByteString;
      }

      public Builder setNumReviews(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.numReviews_ = paramInt;
        return this;
      }

      public Builder setOffers(int paramInt, ProductInformationProtos.ProductInformation.OfferInformation.Builder paramBuilder)
      {
        ensureOffersIsMutable();
        this.offers_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setOffers(int paramInt, ProductInformationProtos.ProductInformation.OfferInformation paramOfferInformation)
      {
        if (paramOfferInformation == null)
          throw new NullPointerException();
        ensureOffersIsMutable();
        this.offers_.set(paramInt, paramOfferInformation);
        return this;
      }

      public Builder setPrices(int paramInt, ProductInformationProtos.ProductInformation.PriceInformation.Builder paramBuilder)
      {
        ensurePricesIsMutable();
        this.prices_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setPrices(int paramInt, ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
      {
        if (paramPriceInformation == null)
          throw new NullPointerException();
        ensurePricesIsMutable();
        this.prices_.set(paramInt, paramPriceInformation);
        return this;
      }

      public Builder setPublicationYear(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.publicationYear_ = paramString;
        return this;
      }

      void setPublicationYear(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.publicationYear_ = paramByteString;
      }

      public Builder setPublisher(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x10 | this.bitField0_);
        this.publisher_ = paramString;
        return this;
      }

      void setPublisher(ByteString paramByteString)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.publisher_ = paramByteString;
      }

      public Builder setStarRating(float paramFloat)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.starRating_ = paramFloat;
        return this;
      }
    }

    public static final class OfferInformation extends GeneratedMessageLite
      implements ProductInformationProtos.ProductInformation.OfferInformationOrBuilder
    {
      public static final int ID_FIELD_NUMBER = 1;
      public static final int MERCHANT_FIELD_NUMBER = 3;
      public static final int PRICE_FIELD_NUMBER = 5;
      public static final int URL_FIELD_NUMBER = 2;
      private static final OfferInformation defaultInstance = new OfferInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private Object id_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object merchant_;
      private ProductInformationProtos.ProductInformation.PriceInformation price_;
      private Object url_;

      static
      {
        defaultInstance.initFields();
      }

      private OfferInformation(Builder paramBuilder)
      {
        super();
      }

      private OfferInformation(boolean paramBoolean)
      {
      }

      public static OfferInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getIdBytes()
      {
        Object localObject = this.id_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.id_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getMerchantBytes()
      {
        Object localObject = this.merchant_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.merchant_ = localByteString;
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
        this.id_ = "";
        this.url_ = "";
        this.merchant_ = "";
        this.price_ = ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance();
      }

      public static Builder newBuilder()
      {
        return Builder.access$900();
      }

      public static Builder newBuilder(OfferInformation paramOfferInformation)
      {
        return newBuilder().mergeFrom(paramOfferInformation);
      }

      public static OfferInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static OfferInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static OfferInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static OfferInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static OfferInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static OfferInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static OfferInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static OfferInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static OfferInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static OfferInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public OfferInformation getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public String getId()
      {
        Object localObject = this.id_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.id_ = str;
        return str;
      }

      public String getMerchant()
      {
        Object localObject = this.merchant_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.merchant_ = str;
        return str;
      }

      public ProductInformationProtos.ProductInformation.PriceInformation getPrice()
      {
        return this.price_;
      }

      public int getSerializedSize()
      {
        int i = this.memoizedSerializedSize;
        if (i != -1)
          return i;
        int j = 0x1 & this.bitField0_;
        int k = 0;
        if (j == 1)
          k = 0 + CodedOutputStream.computeBytesSize(1, getIdBytes());
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeBytesSize(2, getUrlBytes());
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeBytesSize(3, getMerchantBytes());
        if ((0x8 & this.bitField0_) == 8)
          k += CodedOutputStream.computeMessageSize(5, this.price_);
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

      public boolean hasId()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasMerchant()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasPrice()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasUrl()
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
          paramCodedOutputStream.writeBytes(1, getIdBytes());
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeBytes(2, getUrlBytes());
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeBytes(3, getMerchantBytes());
        if ((0x8 & this.bitField0_) == 8)
          paramCodedOutputStream.writeMessage(5, this.price_);
      }

      public static final class Builder extends GeneratedMessageLite.Builder<ProductInformationProtos.ProductInformation.OfferInformation, Builder>
        implements ProductInformationProtos.ProductInformation.OfferInformationOrBuilder
      {
        private int bitField0_;
        private Object id_ = "";
        private Object merchant_ = "";
        private ProductInformationProtos.ProductInformation.PriceInformation price_ = ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance();
        private Object url_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private ProductInformationProtos.ProductInformation.OfferInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          ProductInformationProtos.ProductInformation.OfferInformation localOfferInformation = buildPartial();
          if (!localOfferInformation.isInitialized())
            throw newUninitializedMessageException(localOfferInformation).asInvalidProtocolBufferException();
          return localOfferInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ProductInformationProtos.ProductInformation.OfferInformation build()
        {
          ProductInformationProtos.ProductInformation.OfferInformation localOfferInformation = buildPartial();
          if (!localOfferInformation.isInitialized())
            throw newUninitializedMessageException(localOfferInformation);
          return localOfferInformation;
        }

        public ProductInformationProtos.ProductInformation.OfferInformation buildPartial()
        {
          int i = 1;
          ProductInformationProtos.ProductInformation.OfferInformation localOfferInformation = new ProductInformationProtos.ProductInformation.OfferInformation(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            ProductInformationProtos.ProductInformation.OfferInformation.access$1102(localOfferInformation, this.id_);
            if ((j & 0x2) == 2)
              i |= 2;
            ProductInformationProtos.ProductInformation.OfferInformation.access$1202(localOfferInformation, this.url_);
            if ((j & 0x4) == 4)
              i |= 4;
            ProductInformationProtos.ProductInformation.OfferInformation.access$1302(localOfferInformation, this.merchant_);
            if ((j & 0x8) == 8)
              i |= 8;
            ProductInformationProtos.ProductInformation.OfferInformation.access$1402(localOfferInformation, this.price_);
            ProductInformationProtos.ProductInformation.OfferInformation.access$1502(localOfferInformation, i);
            return localOfferInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.id_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.url_ = "";
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.merchant_ = "";
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.price_ = ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
        }

        public Builder clearId()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.id_ = ProductInformationProtos.ProductInformation.OfferInformation.getDefaultInstance().getId();
          return this;
        }

        public Builder clearMerchant()
        {
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.merchant_ = ProductInformationProtos.ProductInformation.OfferInformation.getDefaultInstance().getMerchant();
          return this;
        }

        public Builder clearPrice()
        {
          this.price_ = ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
        }

        public Builder clearUrl()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.url_ = ProductInformationProtos.ProductInformation.OfferInformation.getDefaultInstance().getUrl();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public ProductInformationProtos.ProductInformation.OfferInformation getDefaultInstanceForType()
        {
          return ProductInformationProtos.ProductInformation.OfferInformation.getDefaultInstance();
        }

        public String getId()
        {
          Object localObject = this.id_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.id_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getMerchant()
        {
          Object localObject = this.merchant_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.merchant_ = str;
            return str;
          }
          return (String)localObject;
        }

        public ProductInformationProtos.ProductInformation.PriceInformation getPrice()
        {
          return this.price_;
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

        public boolean hasId()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public boolean hasMerchant()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public boolean hasPrice()
        {
          return (0x8 & this.bitField0_) == 8;
        }

        public boolean hasUrl()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(ProductInformationProtos.ProductInformation.OfferInformation paramOfferInformation)
        {
          if (paramOfferInformation == ProductInformationProtos.ProductInformation.OfferInformation.getDefaultInstance());
          do
          {
            return this;
            if (paramOfferInformation.hasId())
              setId(paramOfferInformation.getId());
            if (paramOfferInformation.hasUrl())
              setUrl(paramOfferInformation.getUrl());
            if (paramOfferInformation.hasMerchant())
              setMerchant(paramOfferInformation.getMerchant());
          }
          while (!paramOfferInformation.hasPrice());
          mergePrice(paramOfferInformation.getPrice());
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
              this.id_ = paramCodedInputStream.readBytes();
              break;
            case 18:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.url_ = paramCodedInputStream.readBytes();
              break;
            case 26:
              this.bitField0_ = (0x4 | this.bitField0_);
              this.merchant_ = paramCodedInputStream.readBytes();
              break;
            case 42:
            }
            ProductInformationProtos.ProductInformation.PriceInformation.Builder localBuilder = ProductInformationProtos.ProductInformation.PriceInformation.newBuilder();
            if (hasPrice())
              localBuilder.mergeFrom(getPrice());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setPrice(localBuilder.buildPartial());
          }
        }

        public Builder mergePrice(ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
        {
          if (((0x8 & this.bitField0_) == 8) && (this.price_ != ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance()));
          for (this.price_ = ProductInformationProtos.ProductInformation.PriceInformation.newBuilder(this.price_).mergeFrom(paramPriceInformation).buildPartial(); ; this.price_ = paramPriceInformation)
          {
            this.bitField0_ = (0x8 | this.bitField0_);
            return this;
          }
        }

        public Builder setId(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.id_ = paramString;
          return this;
        }

        void setId(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.id_ = paramByteString;
        }

        public Builder setMerchant(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x4 | this.bitField0_);
          this.merchant_ = paramString;
          return this;
        }

        void setMerchant(ByteString paramByteString)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          this.merchant_ = paramByteString;
        }

        public Builder setPrice(ProductInformationProtos.ProductInformation.PriceInformation.Builder paramBuilder)
        {
          this.price_ = paramBuilder.build();
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }

        public Builder setPrice(ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
        {
          if (paramPriceInformation == null)
            throw new NullPointerException();
          this.price_ = paramPriceInformation;
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }

        public Builder setUrl(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x2 | this.bitField0_);
          this.url_ = paramString;
          return this;
        }

        void setUrl(ByteString paramByteString)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.url_ = paramByteString;
        }
      }
    }

    public static abstract interface OfferInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract String getId();

      public abstract String getMerchant();

      public abstract ProductInformationProtos.ProductInformation.PriceInformation getPrice();

      public abstract String getUrl();

      public abstract boolean hasId();

      public abstract boolean hasMerchant();

      public abstract boolean hasPrice();

      public abstract boolean hasUrl();
    }

    public static final class PriceInformation extends GeneratedMessageLite
      implements ProductInformationProtos.ProductInformation.PriceInformationOrBuilder
    {
      public static final int CONDITION_FIELD_NUMBER = 4;
      public static final int HIGH_PRICE_FIELD_NUMBER = 3;
      public static final int LOW_PRICE_FIELD_NUMBER = 2;
      public static final int PRICE_SUMMARY_FIELD_NUMBER = 1;
      private static final PriceInformation defaultInstance = new PriceInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private Condition condition_;
      private double highPrice_;
      private double lowPrice_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object priceSummary_;

      static
      {
        defaultInstance.initFields();
      }

      private PriceInformation(Builder paramBuilder)
      {
        super();
      }

      private PriceInformation(boolean paramBoolean)
      {
      }

      public static PriceInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getPriceSummaryBytes()
      {
        Object localObject = this.priceSummary_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.priceSummary_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.priceSummary_ = "";
        this.lowPrice_ = 0.0D;
        this.highPrice_ = 0.0D;
        this.condition_ = Condition.UNSET;
      }

      public static Builder newBuilder()
      {
        return Builder.access$100();
      }

      public static Builder newBuilder(PriceInformation paramPriceInformation)
      {
        return newBuilder().mergeFrom(paramPriceInformation);
      }

      public static PriceInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static PriceInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static PriceInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static PriceInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static PriceInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static PriceInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static PriceInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static PriceInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static PriceInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static PriceInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public Condition getCondition()
      {
        return this.condition_;
      }

      public PriceInformation getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public double getHighPrice()
      {
        return this.highPrice_;
      }

      public double getLowPrice()
      {
        return this.lowPrice_;
      }

      public String getPriceSummary()
      {
        Object localObject = this.priceSummary_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.priceSummary_ = str;
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
          k = 0 + CodedOutputStream.computeBytesSize(1, getPriceSummaryBytes());
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeDoubleSize(2, this.lowPrice_);
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeDoubleSize(3, this.highPrice_);
        if ((0x8 & this.bitField0_) == 8)
          k += CodedOutputStream.computeEnumSize(4, this.condition_.getNumber());
        this.memoizedSerializedSize = k;
        return k;
      }

      public boolean hasCondition()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasHighPrice()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasLowPrice()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasPriceSummary()
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
          paramCodedOutputStream.writeBytes(1, getPriceSummaryBytes());
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeDouble(2, this.lowPrice_);
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeDouble(3, this.highPrice_);
        if ((0x8 & this.bitField0_) == 8)
          paramCodedOutputStream.writeEnum(4, this.condition_.getNumber());
      }

      public static final class Builder extends GeneratedMessageLite.Builder<ProductInformationProtos.ProductInformation.PriceInformation, Builder>
        implements ProductInformationProtos.ProductInformation.PriceInformationOrBuilder
      {
        private int bitField0_;
        private ProductInformationProtos.ProductInformation.PriceInformation.Condition condition_ = ProductInformationProtos.ProductInformation.PriceInformation.Condition.UNSET;
        private double highPrice_;
        private double lowPrice_;
        private Object priceSummary_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private ProductInformationProtos.ProductInformation.PriceInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          ProductInformationProtos.ProductInformation.PriceInformation localPriceInformation = buildPartial();
          if (!localPriceInformation.isInitialized())
            throw newUninitializedMessageException(localPriceInformation).asInvalidProtocolBufferException();
          return localPriceInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ProductInformationProtos.ProductInformation.PriceInformation build()
        {
          ProductInformationProtos.ProductInformation.PriceInformation localPriceInformation = buildPartial();
          if (!localPriceInformation.isInitialized())
            throw newUninitializedMessageException(localPriceInformation);
          return localPriceInformation;
        }

        public ProductInformationProtos.ProductInformation.PriceInformation buildPartial()
        {
          int i = 1;
          ProductInformationProtos.ProductInformation.PriceInformation localPriceInformation = new ProductInformationProtos.ProductInformation.PriceInformation(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            ProductInformationProtos.ProductInformation.PriceInformation.access$302(localPriceInformation, this.priceSummary_);
            if ((j & 0x2) == 2)
              i |= 2;
            ProductInformationProtos.ProductInformation.PriceInformation.access$402(localPriceInformation, this.lowPrice_);
            if ((j & 0x4) == 4)
              i |= 4;
            ProductInformationProtos.ProductInformation.PriceInformation.access$502(localPriceInformation, this.highPrice_);
            if ((j & 0x8) == 8)
              i |= 8;
            ProductInformationProtos.ProductInformation.PriceInformation.access$602(localPriceInformation, this.condition_);
            ProductInformationProtos.ProductInformation.PriceInformation.access$702(localPriceInformation, i);
            return localPriceInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.priceSummary_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.lowPrice_ = 0.0D;
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.highPrice_ = 0.0D;
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.condition_ = ProductInformationProtos.ProductInformation.PriceInformation.Condition.UNSET;
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
        }

        public Builder clearCondition()
        {
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.condition_ = ProductInformationProtos.ProductInformation.PriceInformation.Condition.UNSET;
          return this;
        }

        public Builder clearHighPrice()
        {
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.highPrice_ = 0.0D;
          return this;
        }

        public Builder clearLowPrice()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.lowPrice_ = 0.0D;
          return this;
        }

        public Builder clearPriceSummary()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.priceSummary_ = ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance().getPriceSummary();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public ProductInformationProtos.ProductInformation.PriceInformation.Condition getCondition()
        {
          return this.condition_;
        }

        public ProductInformationProtos.ProductInformation.PriceInformation getDefaultInstanceForType()
        {
          return ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance();
        }

        public double getHighPrice()
        {
          return this.highPrice_;
        }

        public double getLowPrice()
        {
          return this.lowPrice_;
        }

        public String getPriceSummary()
        {
          Object localObject = this.priceSummary_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.priceSummary_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean hasCondition()
        {
          return (0x8 & this.bitField0_) == 8;
        }

        public boolean hasHighPrice()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public boolean hasLowPrice()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public boolean hasPriceSummary()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(ProductInformationProtos.ProductInformation.PriceInformation paramPriceInformation)
        {
          if (paramPriceInformation == ProductInformationProtos.ProductInformation.PriceInformation.getDefaultInstance());
          do
          {
            return this;
            if (paramPriceInformation.hasPriceSummary())
              setPriceSummary(paramPriceInformation.getPriceSummary());
            if (paramPriceInformation.hasLowPrice())
              setLowPrice(paramPriceInformation.getLowPrice());
            if (paramPriceInformation.hasHighPrice())
              setHighPrice(paramPriceInformation.getHighPrice());
          }
          while (!paramPriceInformation.hasCondition());
          setCondition(paramPriceInformation.getCondition());
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
              this.priceSummary_ = paramCodedInputStream.readBytes();
              break;
            case 17:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.lowPrice_ = paramCodedInputStream.readDouble();
              break;
            case 25:
              this.bitField0_ = (0x4 | this.bitField0_);
              this.highPrice_ = paramCodedInputStream.readDouble();
              break;
            case 32:
            }
            ProductInformationProtos.ProductInformation.PriceInformation.Condition localCondition = ProductInformationProtos.ProductInformation.PriceInformation.Condition.valueOf(paramCodedInputStream.readEnum());
            if (localCondition != null)
            {
              this.bitField0_ = (0x8 | this.bitField0_);
              this.condition_ = localCondition;
            }
          }
        }

        public Builder setCondition(ProductInformationProtos.ProductInformation.PriceInformation.Condition paramCondition)
        {
          if (paramCondition == null)
            throw new NullPointerException();
          this.bitField0_ = (0x8 | this.bitField0_);
          this.condition_ = paramCondition;
          return this;
        }

        public Builder setHighPrice(double paramDouble)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          this.highPrice_ = paramDouble;
          return this;
        }

        public Builder setLowPrice(double paramDouble)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.lowPrice_ = paramDouble;
          return this;
        }

        public Builder setPriceSummary(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.priceSummary_ = paramString;
          return this;
        }

        void setPriceSummary(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.priceSummary_ = paramByteString;
        }
      }

      public static enum Condition
        implements Internal.EnumLite
      {
        public static final int NEW_VALUE = 1;
        public static final int OTHER_VALUE = 4;
        public static final int REFURBISHED_VALUE = 2;
        public static final int UNSET_VALUE = 0;
        public static final int USED_VALUE = 3;
        private static Internal.EnumLiteMap<Condition> internalValueMap = new Internal.EnumLiteMap()
        {
          public ProductInformationProtos.ProductInformation.PriceInformation.Condition findValueByNumber(int paramAnonymousInt)
          {
            return ProductInformationProtos.ProductInformation.PriceInformation.Condition.valueOf(paramAnonymousInt);
          }
        };
        private final int value;

        static
        {
          NEW = new Condition("NEW", 1, 1, 1);
          REFURBISHED = new Condition("REFURBISHED", 2, 2, 2);
          USED = new Condition("USED", 3, 3, 3);
          OTHER = new Condition("OTHER", 4, 4, 4);
          Condition[] arrayOfCondition = new Condition[5];
          arrayOfCondition[0] = UNSET;
          arrayOfCondition[1] = NEW;
          arrayOfCondition[2] = REFURBISHED;
          arrayOfCondition[3] = USED;
          arrayOfCondition[4] = OTHER;
        }

        private Condition(int paramInt1, int paramInt2)
        {
          this.value = paramInt2;
        }

        public static Internal.EnumLiteMap<Condition> internalGetValueMap()
        {
          return internalValueMap;
        }

        public static Condition valueOf(int paramInt)
        {
          switch (paramInt)
          {
          default:
            return null;
          case 0:
            return UNSET;
          case 1:
            return NEW;
          case 2:
            return REFURBISHED;
          case 3:
            return USED;
          case 4:
          }
          return OTHER;
        }

        public final int getNumber()
        {
          return this.value;
        }
      }
    }

    public static abstract interface PriceInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract ProductInformationProtos.ProductInformation.PriceInformation.Condition getCondition();

      public abstract double getHighPrice();

      public abstract double getLowPrice();

      public abstract String getPriceSummary();

      public abstract boolean hasCondition();

      public abstract boolean hasHighPrice();

      public abstract boolean hasLowPrice();

      public abstract boolean hasPriceSummary();
    }
  }

  public static abstract interface ProductInformationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getAuthor();

    public abstract String getBrand();

    public abstract String getCatalogUrl();

    public abstract RestrictsProtos.Category getCategories(int paramInt);

    public abstract int getCategoriesCount();

    public abstract List<RestrictsProtos.Category> getCategoriesList();

    public abstract String getCid();

    public abstract String getClusterId();

    public abstract RestrictsProtos.Restricts.Gender getGender();

    public abstract String getIsbn();

    public abstract int getNumReviews();

    public abstract ProductInformationProtos.ProductInformation.OfferInformation getOffers(int paramInt);

    public abstract int getOffersCount();

    public abstract List<ProductInformationProtos.ProductInformation.OfferInformation> getOffersList();

    public abstract ProductInformationProtos.ProductInformation.PriceInformation getPrices(int paramInt);

    public abstract int getPricesCount();

    public abstract List<ProductInformationProtos.ProductInformation.PriceInformation> getPricesList();

    public abstract String getPublicationYear();

    public abstract String getPublisher();

    public abstract float getStarRating();

    public abstract boolean hasAuthor();

    public abstract boolean hasBrand();

    public abstract boolean hasCatalogUrl();

    public abstract boolean hasCid();

    public abstract boolean hasClusterId();

    public abstract boolean hasGender();

    public abstract boolean hasIsbn();

    public abstract boolean hasNumReviews();

    public abstract boolean hasPublicationYear();

    public abstract boolean hasPublisher();

    public abstract boolean hasStarRating();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ProductInformationProtos
 * JD-Core Version:    0.6.2
 */