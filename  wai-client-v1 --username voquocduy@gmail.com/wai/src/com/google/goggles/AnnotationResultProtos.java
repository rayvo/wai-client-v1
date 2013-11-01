package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.GeneratedMessageLite.GeneratedExtension;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.LazyStringArrayList;
import com.google.protobuf.LazyStringList;
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.UnmodifiableLazyStringList;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class AnnotationResultProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(AnnotationResult.annotationResult);
  }

  public static final class AnnotationResult extends GeneratedMessageLite
    implements AnnotationResultProtos.AnnotationResultOrBuilder
  {
    public static final int ANNOTATION_RESULT_FIELD_NUMBER = 15690847;
    public static final int BOUNDING_BOX_FIELD_NUMBER = 1;
    public static final int BOUNDING_BOX_REQUEST_ID_FIELD_NUMBER = 13;
    public static final int CANONICAL_IMAGE_FIELD_NUMBER = 3;
    public static final int CONTRIBUTION_INFO_FIELD_NUMBER = 23;
    public static final int DIRECT_URL_FIELD_NUMBER = 20;
    public static final int FACTS_FIELD_NUMBER = 15;
    public static final int IS_AD_FIELD_NUMBER = 26;
    public static final int IS_SIMILAR_PRODUCT_FIELD_NUMBER = 31;
    public static final int IS_WITHDRAWN_FIELD_NUMBER = 25;
    public static final int LANGUAGE_FIELD_NUMBER = 6;
    public static final int LAT_LNG_FIELD_NUMBER = 5;
    public static final int LOCATION_TEXT_FIELD_NUMBER = 24;
    public static final int MORE_LIKE_THIS_ACTION_FIELD_NUMBER = 29;
    public static final int MUSIC_INFO_FIELD_NUMBER = 27;
    public static final int PERSON_INFO_FIELD_NUMBER = 16;
    public static final int PLACE_INFO_FIELD_NUMBER = 17;
    public static final int PRODUCT_INFO_FIELD_NUMBER = 21;
    public static final int RESULT_ID_FIELD_NUMBER = 12;
    public static final int RESULT_URL_FIELD_NUMBER = 11;
    public static final int SHARE_ACTION_FIELD_NUMBER = 30;
    public static final int SUBTITLE_FIELD_NUMBER = 8;
    public static final int SUBTYPE_FIELD_NUMBER = 10;
    public static final int TEXT_INFO_FIELD_NUMBER = 22;
    public static final int TITLE_FIELD_NUMBER = 7;
    public static final int TTS_DESCRIPTION_FIELD_NUMBER = 28;
    public static final int TYPE_FIELD_NUMBER = 9;
    public static final int URLS_FIELD_NUMBER = 4;
    public static final int URL_GROUPS_FIELD_NUMBER = 14;
    public static final int WEBSEARCH_URL_FIELD_NUMBER = 19;
    public static final GeneratedMessageLite.GeneratedExtension<ResultProtos.Result, AnnotationResult> annotationResult = GeneratedMessageLite.newSingularGeneratedExtension(ResultProtos.Result.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 15690847, WireFormat.FieldType.MESSAGE);
    private static final AnnotationResult defaultInstance = new AnnotationResult(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private Object boundingBoxRequestId_;
    private BoundingBoxProtos.BoundingBox boundingBox_;
    private CanonicalImageProtos.CanonicalImage canonicalImage_;
    private ContributionInformation contributionInfo_;
    private Object directUrl_;
    private FactsProtos.Facts facts_;
    private boolean isAd_;
    private boolean isSimilarProduct_;
    private boolean isWithdrawn_;
    private Object language_;
    private LatLngProtos.LatLng latLng_;
    private Object locationText_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private MoreLikeThisActionProtos.MoreLikeThisAction moreLikeThisAction_;
    private MusicInformationProtos.MusicInformation musicInfo_;
    private PersonInformation personInfo_;
    private PlaceInformation placeInfo_;
    private ProductInformationProtos.ProductInformation productInfo_;
    private Object resultId_;
    private Object resultUrl_;
    private ShareActionProtos.ShareAction shareAction_;
    private Object subtitle_;
    private Object subtype_;
    private TextInformation textInfo_;
    private Object title_;
    private Object ttsDescription_;
    private Object type_;
    private List<UrlGroupProtos.UrlGroup> urlGroups_;
    private LazyStringList urls_;
    private Object websearchUrl_;

    static
    {
      defaultInstance.initFields();
    }

    private AnnotationResult(Builder paramBuilder)
    {
      super();
    }

    private AnnotationResult(boolean paramBoolean)
    {
    }

    private ByteString getBoundingBoxRequestIdBytes()
    {
      Object localObject = this.boundingBoxRequestId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.boundingBoxRequestId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static AnnotationResult getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getDirectUrlBytes()
    {
      Object localObject = this.directUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.directUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getLanguageBytes()
    {
      Object localObject = this.language_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.language_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getLocationTextBytes()
    {
      Object localObject = this.locationText_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.locationText_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getResultIdBytes()
    {
      Object localObject = this.resultId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.resultId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getResultUrlBytes()
    {
      Object localObject = this.resultUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.resultUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getSubtitleBytes()
    {
      Object localObject = this.subtitle_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.subtitle_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getSubtypeBytes()
    {
      Object localObject = this.subtype_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.subtype_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getTitleBytes()
    {
      Object localObject = this.title_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.title_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getTtsDescriptionBytes()
    {
      Object localObject = this.ttsDescription_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.ttsDescription_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getTypeBytes()
    {
      Object localObject = this.type_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.type_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getWebsearchUrlBytes()
    {
      Object localObject = this.websearchUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.websearchUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.title_ = "";
      this.subtitle_ = "";
      this.type_ = "";
      this.subtype_ = "";
      this.ttsDescription_ = "";
      this.resultUrl_ = "";
      this.directUrl_ = "";
      this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      this.canonicalImage_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance();
      this.urls_ = LazyStringArrayList.EMPTY;
      this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
      this.locationText_ = "";
      this.language_ = "";
      this.urlGroups_ = Collections.emptyList();
      this.websearchUrl_ = "";
      this.facts_ = FactsProtos.Facts.getDefaultInstance();
      this.personInfo_ = PersonInformation.getDefaultInstance();
      this.placeInfo_ = PlaceInformation.getDefaultInstance();
      this.productInfo_ = ProductInformationProtos.ProductInformation.getDefaultInstance();
      this.textInfo_ = TextInformation.getDefaultInstance();
      this.contributionInfo_ = ContributionInformation.getDefaultInstance();
      this.musicInfo_ = MusicInformationProtos.MusicInformation.getDefaultInstance();
      this.isWithdrawn_ = false;
      this.isAd_ = false;
      this.resultId_ = "";
      this.boundingBoxRequestId_ = "";
      this.moreLikeThisAction_ = MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance();
      this.shareAction_ = ShareActionProtos.ShareAction.getDefaultInstance();
      this.isSimilarProduct_ = false;
    }

    public static Builder newBuilder()
    {
      return Builder.access$3700();
    }

    public static Builder newBuilder(AnnotationResult paramAnnotationResult)
    {
      return newBuilder().mergeFrom(paramAnnotationResult);
    }

    public static AnnotationResult parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static AnnotationResult parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static AnnotationResult parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static AnnotationResult parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static AnnotationResult parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static AnnotationResult parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static AnnotationResult parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static AnnotationResult parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static AnnotationResult parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static AnnotationResult parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public BoundingBoxProtos.BoundingBox getBoundingBox()
    {
      return this.boundingBox_;
    }

    public String getBoundingBoxRequestId()
    {
      Object localObject = this.boundingBoxRequestId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.boundingBoxRequestId_ = str;
      return str;
    }

    public CanonicalImageProtos.CanonicalImage getCanonicalImage()
    {
      return this.canonicalImage_;
    }

    public ContributionInformation getContributionInfo()
    {
      return this.contributionInfo_;
    }

    public AnnotationResult getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getDirectUrl()
    {
      Object localObject = this.directUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.directUrl_ = str;
      return str;
    }

    public FactsProtos.Facts getFacts()
    {
      return this.facts_;
    }

    public boolean getIsAd()
    {
      return this.isAd_;
    }

    public boolean getIsSimilarProduct()
    {
      return this.isSimilarProduct_;
    }

    public boolean getIsWithdrawn()
    {
      return this.isWithdrawn_;
    }

    public String getLanguage()
    {
      Object localObject = this.language_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.language_ = str;
      return str;
    }

    public LatLngProtos.LatLng getLatLng()
    {
      return this.latLng_;
    }

    public String getLocationText()
    {
      Object localObject = this.locationText_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.locationText_ = str;
      return str;
    }

    public MoreLikeThisActionProtos.MoreLikeThisAction getMoreLikeThisAction()
    {
      return this.moreLikeThisAction_;
    }

    public MusicInformationProtos.MusicInformation getMusicInfo()
    {
      return this.musicInfo_;
    }

    public PersonInformation getPersonInfo()
    {
      return this.personInfo_;
    }

    public PlaceInformation getPlaceInfo()
    {
      return this.placeInfo_;
    }

    public ProductInformationProtos.ProductInformation getProductInfo()
    {
      return this.productInfo_;
    }

    public String getResultId()
    {
      Object localObject = this.resultId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.resultId_ = str;
      return str;
    }

    public String getResultUrl()
    {
      Object localObject = this.resultUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.resultUrl_ = str;
      return str;
    }

    public int getSerializedSize()
    {
      int i = 0;
      int j = this.memoizedSerializedSize;
      if (j != -1)
        return j;
      if ((0x80 & this.bitField0_) == 128);
      for (int k = 0 + CodedOutputStream.computeMessageSize(1, this.boundingBox_); ; k = 0)
      {
        if ((0x100 & this.bitField0_) == 256)
          k += CodedOutputStream.computeMessageSize(3, this.canonicalImage_);
        int m = 0;
        int n = 0;
        while (m < this.urls_.size())
        {
          n += CodedOutputStream.computeBytesSizeNoTag(this.urls_.getByteString(m));
          m++;
        }
        int i1 = k + n + 1 * getUrlsList().size();
        if ((0x200 & this.bitField0_) == 512)
          i1 += CodedOutputStream.computeMessageSize(5, this.latLng_);
        if ((0x800 & this.bitField0_) == 2048)
          i1 += CodedOutputStream.computeBytesSize(6, getLanguageBytes());
        if ((0x1 & this.bitField0_) == 1)
          i1 += CodedOutputStream.computeBytesSize(7, getTitleBytes());
        if ((0x2 & this.bitField0_) == 2)
          i1 += CodedOutputStream.computeBytesSize(8, getSubtitleBytes());
        if ((0x4 & this.bitField0_) == 4)
          i1 += CodedOutputStream.computeBytesSize(9, getTypeBytes());
        if ((0x8 & this.bitField0_) == 8)
          i1 += CodedOutputStream.computeBytesSize(10, getSubtypeBytes());
        if ((0x20 & this.bitField0_) == 32)
          i1 += CodedOutputStream.computeBytesSize(11, getResultUrlBytes());
        if ((0x400000 & this.bitField0_) == 4194304)
          i1 += CodedOutputStream.computeBytesSize(12, getResultIdBytes());
        if ((0x800000 & this.bitField0_) == 8388608)
          i1 += CodedOutputStream.computeBytesSize(13, getBoundingBoxRequestIdBytes());
        int i3;
        for (int i2 = i1; i < this.urlGroups_.size(); i2 = i3)
        {
          i3 = i2 + CodedOutputStream.computeMessageSize(14, (MessageLite)this.urlGroups_.get(i));
          i++;
        }
        if ((0x2000 & this.bitField0_) == 8192)
          i2 += CodedOutputStream.computeMessageSize(15, this.facts_);
        if ((0x4000 & this.bitField0_) == 16384)
          i2 += CodedOutputStream.computeMessageSize(16, this.personInfo_);
        if ((0x8000 & this.bitField0_) == 32768)
          i2 += CodedOutputStream.computeMessageSize(17, this.placeInfo_);
        if ((0x1000 & this.bitField0_) == 4096)
          i2 += CodedOutputStream.computeBytesSize(19, getWebsearchUrlBytes());
        if ((0x40 & this.bitField0_) == 64)
          i2 += CodedOutputStream.computeBytesSize(20, getDirectUrlBytes());
        if ((0x10000 & this.bitField0_) == 65536)
          i2 += CodedOutputStream.computeMessageSize(21, this.productInfo_);
        if ((0x20000 & this.bitField0_) == 131072)
          i2 += CodedOutputStream.computeMessageSize(22, this.textInfo_);
        if ((0x40000 & this.bitField0_) == 262144)
          i2 += CodedOutputStream.computeMessageSize(23, this.contributionInfo_);
        if ((0x400 & this.bitField0_) == 1024)
          i2 += CodedOutputStream.computeBytesSize(24, getLocationTextBytes());
        if ((0x100000 & this.bitField0_) == 1048576)
          i2 += CodedOutputStream.computeBoolSize(25, this.isWithdrawn_);
        if ((0x200000 & this.bitField0_) == 2097152)
          i2 += CodedOutputStream.computeBoolSize(26, this.isAd_);
        if ((0x80000 & this.bitField0_) == 524288)
          i2 += CodedOutputStream.computeMessageSize(27, this.musicInfo_);
        if ((0x10 & this.bitField0_) == 16)
          i2 += CodedOutputStream.computeBytesSize(28, getTtsDescriptionBytes());
        if ((0x1000000 & this.bitField0_) == 16777216)
          i2 += CodedOutputStream.computeMessageSize(29, this.moreLikeThisAction_);
        if ((0x2000000 & this.bitField0_) == 33554432)
          i2 += CodedOutputStream.computeMessageSize(30, this.shareAction_);
        if ((0x4000000 & this.bitField0_) == 67108864)
          i2 += CodedOutputStream.computeBoolSize(31, this.isSimilarProduct_);
        this.memoizedSerializedSize = i2;
        return i2;
      }
    }

    public ShareActionProtos.ShareAction getShareAction()
    {
      return this.shareAction_;
    }

    public String getSubtitle()
    {
      Object localObject = this.subtitle_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.subtitle_ = str;
      return str;
    }

    public String getSubtype()
    {
      Object localObject = this.subtype_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.subtype_ = str;
      return str;
    }

    public TextInformation getTextInfo()
    {
      return this.textInfo_;
    }

    public String getTitle()
    {
      Object localObject = this.title_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.title_ = str;
      return str;
    }

    public String getTtsDescription()
    {
      Object localObject = this.ttsDescription_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.ttsDescription_ = str;
      return str;
    }

    public String getType()
    {
      Object localObject = this.type_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.type_ = str;
      return str;
    }

    public UrlGroupProtos.UrlGroup getUrlGroups(int paramInt)
    {
      return (UrlGroupProtos.UrlGroup)this.urlGroups_.get(paramInt);
    }

    public int getUrlGroupsCount()
    {
      return this.urlGroups_.size();
    }

    public List<UrlGroupProtos.UrlGroup> getUrlGroupsList()
    {
      return this.urlGroups_;
    }

    public UrlGroupProtos.UrlGroupOrBuilder getUrlGroupsOrBuilder(int paramInt)
    {
      return (UrlGroupProtos.UrlGroupOrBuilder)this.urlGroups_.get(paramInt);
    }

    public List<? extends UrlGroupProtos.UrlGroupOrBuilder> getUrlGroupsOrBuilderList()
    {
      return this.urlGroups_;
    }

    @Deprecated
    public String getUrls(int paramInt)
    {
      return (String)this.urls_.get(paramInt);
    }

    @Deprecated
    public int getUrlsCount()
    {
      return this.urls_.size();
    }

    @Deprecated
    public List<String> getUrlsList()
    {
      return this.urls_;
    }

    public String getWebsearchUrl()
    {
      Object localObject = this.websearchUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.websearchUrl_ = str;
      return str;
    }

    public boolean hasBoundingBox()
    {
      return (0x80 & this.bitField0_) == 128;
    }

    public boolean hasBoundingBoxRequestId()
    {
      return (0x800000 & this.bitField0_) == 8388608;
    }

    public boolean hasCanonicalImage()
    {
      return (0x100 & this.bitField0_) == 256;
    }

    public boolean hasContributionInfo()
    {
      return (0x40000 & this.bitField0_) == 262144;
    }

    public boolean hasDirectUrl()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasFacts()
    {
      return (0x2000 & this.bitField0_) == 8192;
    }

    public boolean hasIsAd()
    {
      return (0x200000 & this.bitField0_) == 2097152;
    }

    public boolean hasIsSimilarProduct()
    {
      return (0x4000000 & this.bitField0_) == 67108864;
    }

    public boolean hasIsWithdrawn()
    {
      return (0x100000 & this.bitField0_) == 1048576;
    }

    public boolean hasLanguage()
    {
      return (0x800 & this.bitField0_) == 2048;
    }

    public boolean hasLatLng()
    {
      return (0x200 & this.bitField0_) == 512;
    }

    public boolean hasLocationText()
    {
      return (0x400 & this.bitField0_) == 1024;
    }

    public boolean hasMoreLikeThisAction()
    {
      return (0x1000000 & this.bitField0_) == 16777216;
    }

    public boolean hasMusicInfo()
    {
      return (0x80000 & this.bitField0_) == 524288;
    }

    public boolean hasPersonInfo()
    {
      return (0x4000 & this.bitField0_) == 16384;
    }

    public boolean hasPlaceInfo()
    {
      return (0x8000 & this.bitField0_) == 32768;
    }

    public boolean hasProductInfo()
    {
      return (0x10000 & this.bitField0_) == 65536;
    }

    public boolean hasResultId()
    {
      return (0x400000 & this.bitField0_) == 4194304;
    }

    public boolean hasResultUrl()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasShareAction()
    {
      return (0x2000000 & this.bitField0_) == 33554432;
    }

    public boolean hasSubtitle()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasSubtype()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasTextInfo()
    {
      return (0x20000 & this.bitField0_) == 131072;
    }

    public boolean hasTitle()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasTtsDescription()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasType()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasWebsearchUrl()
    {
      return (0x1000 & this.bitField0_) == 4096;
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
      if ((hasBoundingBox()) && (!getBoundingBox().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasCanonicalImage()) && (!getCanonicalImage().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if ((hasLatLng()) && (!getLatLng().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      for (int k = 0; k < getUrlGroupsCount(); k++)
        if (!getUrlGroups(k).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasTextInfo()) && (!getTextInfo().isInitialized()))
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
      if ((0x80 & this.bitField0_) == 128)
        paramCodedOutputStream.writeMessage(1, this.boundingBox_);
      if ((0x100 & this.bitField0_) == 256)
        paramCodedOutputStream.writeMessage(3, this.canonicalImage_);
      for (int i = 0; i < this.urls_.size(); i++)
        paramCodedOutputStream.writeBytes(4, this.urls_.getByteString(i));
      if ((0x200 & this.bitField0_) == 512)
        paramCodedOutputStream.writeMessage(5, this.latLng_);
      if ((0x800 & this.bitField0_) == 2048)
        paramCodedOutputStream.writeBytes(6, getLanguageBytes());
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeBytes(7, getTitleBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(8, getSubtitleBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(9, getTypeBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(10, getSubtypeBytes());
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(11, getResultUrlBytes());
      if ((0x400000 & this.bitField0_) == 4194304)
        paramCodedOutputStream.writeBytes(12, getResultIdBytes());
      int j = 0x800000 & this.bitField0_;
      int k = 0;
      if (j == 8388608)
        paramCodedOutputStream.writeBytes(13, getBoundingBoxRequestIdBytes());
      while (k < this.urlGroups_.size())
      {
        paramCodedOutputStream.writeMessage(14, (MessageLite)this.urlGroups_.get(k));
        k++;
      }
      if ((0x2000 & this.bitField0_) == 8192)
        paramCodedOutputStream.writeMessage(15, this.facts_);
      if ((0x4000 & this.bitField0_) == 16384)
        paramCodedOutputStream.writeMessage(16, this.personInfo_);
      if ((0x8000 & this.bitField0_) == 32768)
        paramCodedOutputStream.writeMessage(17, this.placeInfo_);
      if ((0x1000 & this.bitField0_) == 4096)
        paramCodedOutputStream.writeBytes(19, getWebsearchUrlBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(20, getDirectUrlBytes());
      if ((0x10000 & this.bitField0_) == 65536)
        paramCodedOutputStream.writeMessage(21, this.productInfo_);
      if ((0x20000 & this.bitField0_) == 131072)
        paramCodedOutputStream.writeMessage(22, this.textInfo_);
      if ((0x40000 & this.bitField0_) == 262144)
        paramCodedOutputStream.writeMessage(23, this.contributionInfo_);
      if ((0x400 & this.bitField0_) == 1024)
        paramCodedOutputStream.writeBytes(24, getLocationTextBytes());
      if ((0x100000 & this.bitField0_) == 1048576)
        paramCodedOutputStream.writeBool(25, this.isWithdrawn_);
      if ((0x200000 & this.bitField0_) == 2097152)
        paramCodedOutputStream.writeBool(26, this.isAd_);
      if ((0x80000 & this.bitField0_) == 524288)
        paramCodedOutputStream.writeMessage(27, this.musicInfo_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(28, getTtsDescriptionBytes());
      if ((0x1000000 & this.bitField0_) == 16777216)
        paramCodedOutputStream.writeMessage(29, this.moreLikeThisAction_);
      if ((0x2000000 & this.bitField0_) == 33554432)
        paramCodedOutputStream.writeMessage(30, this.shareAction_);
      if ((0x4000000 & this.bitField0_) == 67108864)
        paramCodedOutputStream.writeBool(31, this.isSimilarProduct_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult, Builder>
      implements AnnotationResultProtos.AnnotationResultOrBuilder
    {
      private int bitField0_;
      private Object boundingBoxRequestId_ = "";
      private BoundingBoxProtos.BoundingBox boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      private CanonicalImageProtos.CanonicalImage canonicalImage_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance();
      private AnnotationResultProtos.AnnotationResult.ContributionInformation contributionInfo_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance();
      private Object directUrl_ = "";
      private FactsProtos.Facts facts_ = FactsProtos.Facts.getDefaultInstance();
      private boolean isAd_;
      private boolean isSimilarProduct_;
      private boolean isWithdrawn_;
      private Object language_ = "";
      private LatLngProtos.LatLng latLng_ = LatLngProtos.LatLng.getDefaultInstance();
      private Object locationText_ = "";
      private MoreLikeThisActionProtos.MoreLikeThisAction moreLikeThisAction_ = MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance();
      private MusicInformationProtos.MusicInformation musicInfo_ = MusicInformationProtos.MusicInformation.getDefaultInstance();
      private AnnotationResultProtos.AnnotationResult.PersonInformation personInfo_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
      private AnnotationResultProtos.AnnotationResult.PlaceInformation placeInfo_ = AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance();
      private ProductInformationProtos.ProductInformation productInfo_ = ProductInformationProtos.ProductInformation.getDefaultInstance();
      private Object resultId_ = "";
      private Object resultUrl_ = "";
      private ShareActionProtos.ShareAction shareAction_ = ShareActionProtos.ShareAction.getDefaultInstance();
      private Object subtitle_ = "";
      private Object subtype_ = "";
      private AnnotationResultProtos.AnnotationResult.TextInformation textInfo_ = AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance();
      private Object title_ = "";
      private Object ttsDescription_ = "";
      private Object type_ = "";
      private List<UrlGroupProtos.UrlGroup> urlGroups_ = Collections.emptyList();
      private LazyStringList urls_ = LazyStringArrayList.EMPTY;
      private Object websearchUrl_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private AnnotationResultProtos.AnnotationResult buildParsed()
        throws InvalidProtocolBufferException
      {
        AnnotationResultProtos.AnnotationResult localAnnotationResult = buildPartial();
        if (!localAnnotationResult.isInitialized())
          throw newUninitializedMessageException(localAnnotationResult).asInvalidProtocolBufferException();
        return localAnnotationResult;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureUrlGroupsIsMutable()
      {
        if ((0x2000 & this.bitField0_) != 8192)
        {
          this.urlGroups_ = new ArrayList(this.urlGroups_);
          this.bitField0_ = (0x2000 | this.bitField0_);
        }
      }

      private void ensureUrlsIsMutable()
      {
        if ((0x200 & this.bitField0_) != 512)
        {
          this.urls_ = new LazyStringArrayList(this.urls_);
          this.bitField0_ = (0x200 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllUrlGroups(Iterable<? extends UrlGroupProtos.UrlGroup> paramIterable)
      {
        ensureUrlGroupsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.urlGroups_);
        return this;
      }

      @Deprecated
      public Builder addAllUrls(Iterable<String> paramIterable)
      {
        ensureUrlsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.urls_);
        return this;
      }

      public Builder addUrlGroups(int paramInt, UrlGroupProtos.UrlGroup.Builder paramBuilder)
      {
        ensureUrlGroupsIsMutable();
        this.urlGroups_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addUrlGroups(int paramInt, UrlGroupProtos.UrlGroup paramUrlGroup)
      {
        if (paramUrlGroup == null)
          throw new NullPointerException();
        ensureUrlGroupsIsMutable();
        this.urlGroups_.add(paramInt, paramUrlGroup);
        return this;
      }

      public Builder addUrlGroups(UrlGroupProtos.UrlGroup.Builder paramBuilder)
      {
        ensureUrlGroupsIsMutable();
        this.urlGroups_.add(paramBuilder.build());
        return this;
      }

      public Builder addUrlGroups(UrlGroupProtos.UrlGroup paramUrlGroup)
      {
        if (paramUrlGroup == null)
          throw new NullPointerException();
        ensureUrlGroupsIsMutable();
        this.urlGroups_.add(paramUrlGroup);
        return this;
      }

      @Deprecated
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

      public AnnotationResultProtos.AnnotationResult build()
      {
        AnnotationResultProtos.AnnotationResult localAnnotationResult = buildPartial();
        if (!localAnnotationResult.isInitialized())
          throw newUninitializedMessageException(localAnnotationResult);
        return localAnnotationResult;
      }

      public AnnotationResultProtos.AnnotationResult buildPartial()
      {
        AnnotationResultProtos.AnnotationResult localAnnotationResult = new AnnotationResultProtos.AnnotationResult(this, null);
        int i = this.bitField0_;
        int j = i & 0x1;
        int k = 0;
        if (j == 1)
          k = 1;
        AnnotationResultProtos.AnnotationResult.access$3902(localAnnotationResult, this.title_);
        if ((i & 0x2) == 2)
          k |= 2;
        AnnotationResultProtos.AnnotationResult.access$4002(localAnnotationResult, this.subtitle_);
        if ((i & 0x4) == 4)
          k |= 4;
        AnnotationResultProtos.AnnotationResult.access$4102(localAnnotationResult, this.type_);
        if ((i & 0x8) == 8)
          k |= 8;
        AnnotationResultProtos.AnnotationResult.access$4202(localAnnotationResult, this.subtype_);
        if ((i & 0x10) == 16)
          k |= 16;
        AnnotationResultProtos.AnnotationResult.access$4302(localAnnotationResult, this.ttsDescription_);
        if ((i & 0x20) == 32)
          k |= 32;
        AnnotationResultProtos.AnnotationResult.access$4402(localAnnotationResult, this.resultUrl_);
        if ((i & 0x40) == 64)
          k |= 64;
        AnnotationResultProtos.AnnotationResult.access$4502(localAnnotationResult, this.directUrl_);
        if ((i & 0x80) == 128)
          k |= 128;
        AnnotationResultProtos.AnnotationResult.access$4602(localAnnotationResult, this.boundingBox_);
        if ((i & 0x100) == 256)
          k |= 256;
        AnnotationResultProtos.AnnotationResult.access$4702(localAnnotationResult, this.canonicalImage_);
        if ((0x200 & this.bitField0_) == 512)
        {
          this.urls_ = new UnmodifiableLazyStringList(this.urls_);
          this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        }
        AnnotationResultProtos.AnnotationResult.access$4802(localAnnotationResult, this.urls_);
        if ((i & 0x400) == 1024)
          k |= 512;
        AnnotationResultProtos.AnnotationResult.access$4902(localAnnotationResult, this.latLng_);
        if ((i & 0x800) == 2048)
          k |= 1024;
        AnnotationResultProtos.AnnotationResult.access$5002(localAnnotationResult, this.locationText_);
        if ((i & 0x1000) == 4096)
          k |= 2048;
        AnnotationResultProtos.AnnotationResult.access$5102(localAnnotationResult, this.language_);
        if ((0x2000 & this.bitField0_) == 8192)
        {
          this.urlGroups_ = Collections.unmodifiableList(this.urlGroups_);
          this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
        }
        AnnotationResultProtos.AnnotationResult.access$5202(localAnnotationResult, this.urlGroups_);
        if ((i & 0x4000) == 16384)
          k |= 4096;
        AnnotationResultProtos.AnnotationResult.access$5302(localAnnotationResult, this.websearchUrl_);
        if ((i & 0x8000) == 32768)
          k |= 8192;
        AnnotationResultProtos.AnnotationResult.access$5402(localAnnotationResult, this.facts_);
        if ((i & 0x10000) == 65536)
          k |= 16384;
        AnnotationResultProtos.AnnotationResult.access$5502(localAnnotationResult, this.personInfo_);
        if ((i & 0x20000) == 131072)
          k |= 32768;
        AnnotationResultProtos.AnnotationResult.access$5602(localAnnotationResult, this.placeInfo_);
        if ((i & 0x40000) == 262144)
          k |= 65536;
        AnnotationResultProtos.AnnotationResult.access$5702(localAnnotationResult, this.productInfo_);
        if ((i & 0x80000) == 524288)
          k |= 131072;
        AnnotationResultProtos.AnnotationResult.access$5802(localAnnotationResult, this.textInfo_);
        if ((0x100000 & i) == 1048576)
          k |= 262144;
        AnnotationResultProtos.AnnotationResult.access$5902(localAnnotationResult, this.contributionInfo_);
        if ((0x200000 & i) == 2097152)
          k |= 524288;
        AnnotationResultProtos.AnnotationResult.access$6002(localAnnotationResult, this.musicInfo_);
        if ((0x400000 & i) == 4194304)
          k |= 1048576;
        AnnotationResultProtos.AnnotationResult.access$6102(localAnnotationResult, this.isWithdrawn_);
        if ((0x800000 & i) == 8388608)
          k |= 2097152;
        AnnotationResultProtos.AnnotationResult.access$6202(localAnnotationResult, this.isAd_);
        if ((0x1000000 & i) == 16777216)
          k |= 4194304;
        AnnotationResultProtos.AnnotationResult.access$6302(localAnnotationResult, this.resultId_);
        if ((0x2000000 & i) == 33554432)
          k |= 8388608;
        AnnotationResultProtos.AnnotationResult.access$6402(localAnnotationResult, this.boundingBoxRequestId_);
        if ((0x4000000 & i) == 67108864)
          k |= 16777216;
        AnnotationResultProtos.AnnotationResult.access$6502(localAnnotationResult, this.moreLikeThisAction_);
        if ((0x8000000 & i) == 134217728)
          k |= 33554432;
        AnnotationResultProtos.AnnotationResult.access$6602(localAnnotationResult, this.shareAction_);
        if ((i & 0x10000000) == 268435456)
          k |= 67108864;
        AnnotationResultProtos.AnnotationResult.access$6702(localAnnotationResult, this.isSimilarProduct_);
        AnnotationResultProtos.AnnotationResult.access$6802(localAnnotationResult, k);
        return localAnnotationResult;
      }

      public Builder clear()
      {
        super.clear();
        this.title_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.subtitle_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.type_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.subtype_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.ttsDescription_ = "";
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.resultUrl_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.directUrl_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.canonicalImage_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.urls_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        this.locationText_ = "";
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        this.language_ = "";
        this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
        this.urlGroups_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
        this.websearchUrl_ = "";
        this.bitField0_ = (0xFFFFBFFF & this.bitField0_);
        this.facts_ = FactsProtos.Facts.getDefaultInstance();
        this.bitField0_ = (0xFFFF7FFF & this.bitField0_);
        this.personInfo_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFEFFFF & this.bitField0_);
        this.placeInfo_ = AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFDFFFF & this.bitField0_);
        this.productInfo_ = ProductInformationProtos.ProductInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFBFFFF & this.bitField0_);
        this.textInfo_ = AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance();
        this.bitField0_ = (0xFFF7FFFF & this.bitField0_);
        this.contributionInfo_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance();
        this.bitField0_ = (0xFFEFFFFF & this.bitField0_);
        this.musicInfo_ = MusicInformationProtos.MusicInformation.getDefaultInstance();
        this.bitField0_ = (0xFFDFFFFF & this.bitField0_);
        this.isWithdrawn_ = false;
        this.bitField0_ = (0xFFBFFFFF & this.bitField0_);
        this.isAd_ = false;
        this.bitField0_ = (0xFF7FFFFF & this.bitField0_);
        this.resultId_ = "";
        this.bitField0_ = (0xFEFFFFFF & this.bitField0_);
        this.boundingBoxRequestId_ = "";
        this.bitField0_ = (0xFDFFFFFF & this.bitField0_);
        this.moreLikeThisAction_ = MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance();
        this.bitField0_ = (0xFBFFFFFF & this.bitField0_);
        this.shareAction_ = ShareActionProtos.ShareAction.getDefaultInstance();
        this.bitField0_ = (0xF7FFFFFF & this.bitField0_);
        this.isSimilarProduct_ = false;
        this.bitField0_ = (0xEFFFFFFF & this.bitField0_);
        return this;
      }

      public Builder clearBoundingBox()
      {
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        return this;
      }

      public Builder clearBoundingBoxRequestId()
      {
        this.bitField0_ = (0xFDFFFFFF & this.bitField0_);
        this.boundingBoxRequestId_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getBoundingBoxRequestId();
        return this;
      }

      public Builder clearCanonicalImage()
      {
        this.canonicalImage_ = CanonicalImageProtos.CanonicalImage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clearContributionInfo()
      {
        this.contributionInfo_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance();
        this.bitField0_ = (0xFFEFFFFF & this.bitField0_);
        return this;
      }

      public Builder clearDirectUrl()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.directUrl_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getDirectUrl();
        return this;
      }

      public Builder clearFacts()
      {
        this.facts_ = FactsProtos.Facts.getDefaultInstance();
        this.bitField0_ = (0xFFFF7FFF & this.bitField0_);
        return this;
      }

      public Builder clearIsAd()
      {
        this.bitField0_ = (0xFF7FFFFF & this.bitField0_);
        this.isAd_ = false;
        return this;
      }

      public Builder clearIsSimilarProduct()
      {
        this.bitField0_ = (0xEFFFFFFF & this.bitField0_);
        this.isSimilarProduct_ = false;
        return this;
      }

      public Builder clearIsWithdrawn()
      {
        this.bitField0_ = (0xFFBFFFFF & this.bitField0_);
        this.isWithdrawn_ = false;
        return this;
      }

      public Builder clearLanguage()
      {
        this.bitField0_ = (0xFFFFEFFF & this.bitField0_);
        this.language_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getLanguage();
        return this;
      }

      public Builder clearLatLng()
      {
        this.latLng_ = LatLngProtos.LatLng.getDefaultInstance();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      public Builder clearLocationText()
      {
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        this.locationText_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getLocationText();
        return this;
      }

      public Builder clearMoreLikeThisAction()
      {
        this.moreLikeThisAction_ = MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance();
        this.bitField0_ = (0xFBFFFFFF & this.bitField0_);
        return this;
      }

      public Builder clearMusicInfo()
      {
        this.musicInfo_ = MusicInformationProtos.MusicInformation.getDefaultInstance();
        this.bitField0_ = (0xFFDFFFFF & this.bitField0_);
        return this;
      }

      public Builder clearPersonInfo()
      {
        this.personInfo_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFEFFFF & this.bitField0_);
        return this;
      }

      public Builder clearPlaceInfo()
      {
        this.placeInfo_ = AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFDFFFF & this.bitField0_);
        return this;
      }

      public Builder clearProductInfo()
      {
        this.productInfo_ = ProductInformationProtos.ProductInformation.getDefaultInstance();
        this.bitField0_ = (0xFFFBFFFF & this.bitField0_);
        return this;
      }

      public Builder clearResultId()
      {
        this.bitField0_ = (0xFEFFFFFF & this.bitField0_);
        this.resultId_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getResultId();
        return this;
      }

      public Builder clearResultUrl()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.resultUrl_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getResultUrl();
        return this;
      }

      public Builder clearShareAction()
      {
        this.shareAction_ = ShareActionProtos.ShareAction.getDefaultInstance();
        this.bitField0_ = (0xF7FFFFFF & this.bitField0_);
        return this;
      }

      public Builder clearSubtitle()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.subtitle_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getSubtitle();
        return this;
      }

      public Builder clearSubtype()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.subtype_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getSubtype();
        return this;
      }

      public Builder clearTextInfo()
      {
        this.textInfo_ = AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance();
        this.bitField0_ = (0xFFF7FFFF & this.bitField0_);
        return this;
      }

      public Builder clearTitle()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.title_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getTitle();
        return this;
      }

      public Builder clearTtsDescription()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.ttsDescription_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getTtsDescription();
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.type_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getType();
        return this;
      }

      public Builder clearUrlGroups()
      {
        this.urlGroups_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearUrls()
      {
        this.urls_ = LazyStringArrayList.EMPTY;
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        return this;
      }

      public Builder clearWebsearchUrl()
      {
        this.bitField0_ = (0xFFFFBFFF & this.bitField0_);
        this.websearchUrl_ = AnnotationResultProtos.AnnotationResult.getDefaultInstance().getWebsearchUrl();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public BoundingBoxProtos.BoundingBox getBoundingBox()
      {
        return this.boundingBox_;
      }

      public String getBoundingBoxRequestId()
      {
        Object localObject = this.boundingBoxRequestId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.boundingBoxRequestId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public CanonicalImageProtos.CanonicalImage getCanonicalImage()
      {
        return this.canonicalImage_;
      }

      public AnnotationResultProtos.AnnotationResult.ContributionInformation getContributionInfo()
      {
        return this.contributionInfo_;
      }

      public AnnotationResultProtos.AnnotationResult getDefaultInstanceForType()
      {
        return AnnotationResultProtos.AnnotationResult.getDefaultInstance();
      }

      public String getDirectUrl()
      {
        Object localObject = this.directUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.directUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public FactsProtos.Facts getFacts()
      {
        return this.facts_;
      }

      public boolean getIsAd()
      {
        return this.isAd_;
      }

      public boolean getIsSimilarProduct()
      {
        return this.isSimilarProduct_;
      }

      public boolean getIsWithdrawn()
      {
        return this.isWithdrawn_;
      }

      public String getLanguage()
      {
        Object localObject = this.language_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.language_ = str;
          return str;
        }
        return (String)localObject;
      }

      public LatLngProtos.LatLng getLatLng()
      {
        return this.latLng_;
      }

      public String getLocationText()
      {
        Object localObject = this.locationText_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.locationText_ = str;
          return str;
        }
        return (String)localObject;
      }

      public MoreLikeThisActionProtos.MoreLikeThisAction getMoreLikeThisAction()
      {
        return this.moreLikeThisAction_;
      }

      public MusicInformationProtos.MusicInformation getMusicInfo()
      {
        return this.musicInfo_;
      }

      public AnnotationResultProtos.AnnotationResult.PersonInformation getPersonInfo()
      {
        return this.personInfo_;
      }

      public AnnotationResultProtos.AnnotationResult.PlaceInformation getPlaceInfo()
      {
        return this.placeInfo_;
      }

      public ProductInformationProtos.ProductInformation getProductInfo()
      {
        return this.productInfo_;
      }

      public String getResultId()
      {
        Object localObject = this.resultId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.resultId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getResultUrl()
      {
        Object localObject = this.resultUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.resultUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public ShareActionProtos.ShareAction getShareAction()
      {
        return this.shareAction_;
      }

      public String getSubtitle()
      {
        Object localObject = this.subtitle_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.subtitle_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getSubtype()
      {
        Object localObject = this.subtype_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.subtype_ = str;
          return str;
        }
        return (String)localObject;
      }

      public AnnotationResultProtos.AnnotationResult.TextInformation getTextInfo()
      {
        return this.textInfo_;
      }

      public String getTitle()
      {
        Object localObject = this.title_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.title_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getTtsDescription()
      {
        Object localObject = this.ttsDescription_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.ttsDescription_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getType()
      {
        Object localObject = this.type_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.type_ = str;
          return str;
        }
        return (String)localObject;
      }

      public UrlGroupProtos.UrlGroup getUrlGroups(int paramInt)
      {
        return (UrlGroupProtos.UrlGroup)this.urlGroups_.get(paramInt);
      }

      public int getUrlGroupsCount()
      {
        return this.urlGroups_.size();
      }

      public List<UrlGroupProtos.UrlGroup> getUrlGroupsList()
      {
        return Collections.unmodifiableList(this.urlGroups_);
      }

      @Deprecated
      public String getUrls(int paramInt)
      {
        return (String)this.urls_.get(paramInt);
      }

      @Deprecated
      public int getUrlsCount()
      {
        return this.urls_.size();
      }

      @Deprecated
      public List<String> getUrlsList()
      {
        return Collections.unmodifiableList(this.urls_);
      }

      public String getWebsearchUrl()
      {
        Object localObject = this.websearchUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.websearchUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasBoundingBox()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasBoundingBoxRequestId()
      {
        return (0x2000000 & this.bitField0_) == 33554432;
      }

      public boolean hasCanonicalImage()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      public boolean hasContributionInfo()
      {
        return (0x100000 & this.bitField0_) == 1048576;
      }

      public boolean hasDirectUrl()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasFacts()
      {
        return (0x8000 & this.bitField0_) == 32768;
      }

      public boolean hasIsAd()
      {
        return (0x800000 & this.bitField0_) == 8388608;
      }

      public boolean hasIsSimilarProduct()
      {
        return (0x10000000 & this.bitField0_) == 268435456;
      }

      public boolean hasIsWithdrawn()
      {
        return (0x400000 & this.bitField0_) == 4194304;
      }

      public boolean hasLanguage()
      {
        return (0x1000 & this.bitField0_) == 4096;
      }

      public boolean hasLatLng()
      {
        return (0x400 & this.bitField0_) == 1024;
      }

      public boolean hasLocationText()
      {
        return (0x800 & this.bitField0_) == 2048;
      }

      public boolean hasMoreLikeThisAction()
      {
        return (0x4000000 & this.bitField0_) == 67108864;
      }

      public boolean hasMusicInfo()
      {
        return (0x200000 & this.bitField0_) == 2097152;
      }

      public boolean hasPersonInfo()
      {
        return (0x10000 & this.bitField0_) == 65536;
      }

      public boolean hasPlaceInfo()
      {
        return (0x20000 & this.bitField0_) == 131072;
      }

      public boolean hasProductInfo()
      {
        return (0x40000 & this.bitField0_) == 262144;
      }

      public boolean hasResultId()
      {
        return (0x1000000 & this.bitField0_) == 16777216;
      }

      public boolean hasResultUrl()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasShareAction()
      {
        return (0x8000000 & this.bitField0_) == 134217728;
      }

      public boolean hasSubtitle()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasSubtype()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasTextInfo()
      {
        return (0x80000 & this.bitField0_) == 524288;
      }

      public boolean hasTitle()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasTtsDescription()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasType()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasWebsearchUrl()
      {
        return (0x4000 & this.bitField0_) == 16384;
      }

      public final boolean isInitialized()
      {
        if ((hasBoundingBox()) && (!getBoundingBox().isInitialized()));
        label80: 
        do
        {
          do
            return false;
          while (((hasCanonicalImage()) && (!getCanonicalImage().isInitialized())) || ((hasLatLng()) && (!getLatLng().isInitialized())));
          for (int i = 0; ; i++)
          {
            if (i >= getUrlGroupsCount())
              break label80;
            if (!getUrlGroups(i).isInitialized())
              break;
          }
        }
        while ((hasTextInfo()) && (!getTextInfo().isInitialized()));
        return true;
      }

      public Builder mergeBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (((0x80 & this.bitField0_) == 128) && (this.boundingBox_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
        for (this.boundingBox_ = BoundingBoxProtos.BoundingBox.newBuilder(this.boundingBox_).mergeFrom(paramBoundingBox).buildPartial(); ; this.boundingBox_ = paramBoundingBox)
        {
          this.bitField0_ = (0x80 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeCanonicalImage(CanonicalImageProtos.CanonicalImage paramCanonicalImage)
      {
        if (((0x100 & this.bitField0_) == 256) && (this.canonicalImage_ != CanonicalImageProtos.CanonicalImage.getDefaultInstance()));
        for (this.canonicalImage_ = CanonicalImageProtos.CanonicalImage.newBuilder(this.canonicalImage_).mergeFrom(paramCanonicalImage).buildPartial(); ; this.canonicalImage_ = paramCanonicalImage)
        {
          this.bitField0_ = (0x100 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeContributionInfo(AnnotationResultProtos.AnnotationResult.ContributionInformation paramContributionInformation)
      {
        if (((0x100000 & this.bitField0_) == 1048576) && (this.contributionInfo_ != AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance()));
        for (this.contributionInfo_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.newBuilder(this.contributionInfo_).mergeFrom(paramContributionInformation).buildPartial(); ; this.contributionInfo_ = paramContributionInformation)
        {
          this.bitField0_ = (0x100000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFacts(FactsProtos.Facts paramFacts)
      {
        if (((0x8000 & this.bitField0_) == 32768) && (this.facts_ != FactsProtos.Facts.getDefaultInstance()));
        for (this.facts_ = FactsProtos.Facts.newBuilder(this.facts_).mergeFrom(paramFacts).buildPartial(); ; this.facts_ = paramFacts)
        {
          this.bitField0_ = (0x8000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(AnnotationResultProtos.AnnotationResult paramAnnotationResult)
      {
        if (paramAnnotationResult == AnnotationResultProtos.AnnotationResult.getDefaultInstance())
          return this;
        if (paramAnnotationResult.hasTitle())
          setTitle(paramAnnotationResult.getTitle());
        if (paramAnnotationResult.hasSubtitle())
          setSubtitle(paramAnnotationResult.getSubtitle());
        if (paramAnnotationResult.hasType())
          setType(paramAnnotationResult.getType());
        if (paramAnnotationResult.hasSubtype())
          setSubtype(paramAnnotationResult.getSubtype());
        if (paramAnnotationResult.hasTtsDescription())
          setTtsDescription(paramAnnotationResult.getTtsDescription());
        if (paramAnnotationResult.hasResultUrl())
          setResultUrl(paramAnnotationResult.getResultUrl());
        if (paramAnnotationResult.hasDirectUrl())
          setDirectUrl(paramAnnotationResult.getDirectUrl());
        if (paramAnnotationResult.hasBoundingBox())
          mergeBoundingBox(paramAnnotationResult.getBoundingBox());
        if (paramAnnotationResult.hasCanonicalImage())
          mergeCanonicalImage(paramAnnotationResult.getCanonicalImage());
        if (!paramAnnotationResult.urls_.isEmpty())
        {
          if (this.urls_.isEmpty())
          {
            this.urls_ = paramAnnotationResult.urls_;
            this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
          }
        }
        else
        {
          label197: if (paramAnnotationResult.hasLatLng())
            mergeLatLng(paramAnnotationResult.getLatLng());
          if (paramAnnotationResult.hasLocationText())
            setLocationText(paramAnnotationResult.getLocationText());
          if (paramAnnotationResult.hasLanguage())
            setLanguage(paramAnnotationResult.getLanguage());
          if (!paramAnnotationResult.urlGroups_.isEmpty())
          {
            if (!this.urlGroups_.isEmpty())
              break label552;
            this.urlGroups_ = paramAnnotationResult.urlGroups_;
            this.bitField0_ = (0xFFFFDFFF & this.bitField0_);
          }
        }
        while (true)
        {
          if (paramAnnotationResult.hasWebsearchUrl())
            setWebsearchUrl(paramAnnotationResult.getWebsearchUrl());
          if (paramAnnotationResult.hasFacts())
            mergeFacts(paramAnnotationResult.getFacts());
          if (paramAnnotationResult.hasPersonInfo())
            mergePersonInfo(paramAnnotationResult.getPersonInfo());
          if (paramAnnotationResult.hasPlaceInfo())
            mergePlaceInfo(paramAnnotationResult.getPlaceInfo());
          if (paramAnnotationResult.hasProductInfo())
            mergeProductInfo(paramAnnotationResult.getProductInfo());
          if (paramAnnotationResult.hasTextInfo())
            mergeTextInfo(paramAnnotationResult.getTextInfo());
          if (paramAnnotationResult.hasContributionInfo())
            mergeContributionInfo(paramAnnotationResult.getContributionInfo());
          if (paramAnnotationResult.hasMusicInfo())
            mergeMusicInfo(paramAnnotationResult.getMusicInfo());
          if (paramAnnotationResult.hasIsWithdrawn())
            setIsWithdrawn(paramAnnotationResult.getIsWithdrawn());
          if (paramAnnotationResult.hasIsAd())
            setIsAd(paramAnnotationResult.getIsAd());
          if (paramAnnotationResult.hasResultId())
            setResultId(paramAnnotationResult.getResultId());
          if (paramAnnotationResult.hasBoundingBoxRequestId())
            setBoundingBoxRequestId(paramAnnotationResult.getBoundingBoxRequestId());
          if (paramAnnotationResult.hasMoreLikeThisAction())
            mergeMoreLikeThisAction(paramAnnotationResult.getMoreLikeThisAction());
          if (paramAnnotationResult.hasShareAction())
            mergeShareAction(paramAnnotationResult.getShareAction());
          if (!paramAnnotationResult.hasIsSimilarProduct())
            break;
          setIsSimilarProduct(paramAnnotationResult.getIsSimilarProduct());
          return this;
          ensureUrlsIsMutable();
          this.urls_.addAll(paramAnnotationResult.urls_);
          break label197;
          label552: ensureUrlGroupsIsMutable();
          this.urlGroups_.addAll(paramAnnotationResult.urlGroups_);
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
            BoundingBoxProtos.BoundingBox.Builder localBuilder12 = BoundingBoxProtos.BoundingBox.newBuilder();
            if (hasBoundingBox())
              localBuilder12.mergeFrom(getBoundingBox());
            paramCodedInputStream.readMessage(localBuilder12, paramExtensionRegistryLite);
            setBoundingBox(localBuilder12.buildPartial());
            break;
          case 26:
            CanonicalImageProtos.CanonicalImage.Builder localBuilder11 = CanonicalImageProtos.CanonicalImage.newBuilder();
            if (hasCanonicalImage())
              localBuilder11.mergeFrom(getCanonicalImage());
            paramCodedInputStream.readMessage(localBuilder11, paramExtensionRegistryLite);
            setCanonicalImage(localBuilder11.buildPartial());
            break;
          case 34:
            ensureUrlsIsMutable();
            this.urls_.add(paramCodedInputStream.readBytes());
            break;
          case 42:
            LatLngProtos.LatLng.Builder localBuilder10 = LatLngProtos.LatLng.newBuilder();
            if (hasLatLng())
              localBuilder10.mergeFrom(getLatLng());
            paramCodedInputStream.readMessage(localBuilder10, paramExtensionRegistryLite);
            setLatLng(localBuilder10.buildPartial());
            break;
          case 50:
            this.bitField0_ = (0x1000 | this.bitField0_);
            this.language_ = paramCodedInputStream.readBytes();
            break;
          case 58:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.title_ = paramCodedInputStream.readBytes();
            break;
          case 66:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.subtitle_ = paramCodedInputStream.readBytes();
            break;
          case 74:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.type_ = paramCodedInputStream.readBytes();
            break;
          case 82:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.subtype_ = paramCodedInputStream.readBytes();
            break;
          case 90:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.resultUrl_ = paramCodedInputStream.readBytes();
            break;
          case 98:
            this.bitField0_ = (0x1000000 | this.bitField0_);
            this.resultId_ = paramCodedInputStream.readBytes();
            break;
          case 106:
            this.bitField0_ = (0x2000000 | this.bitField0_);
            this.boundingBoxRequestId_ = paramCodedInputStream.readBytes();
            break;
          case 114:
            UrlGroupProtos.UrlGroup.Builder localBuilder9 = UrlGroupProtos.UrlGroup.newBuilder();
            paramCodedInputStream.readMessage(localBuilder9, paramExtensionRegistryLite);
            addUrlGroups(localBuilder9.buildPartial());
            break;
          case 122:
            FactsProtos.Facts.Builder localBuilder8 = FactsProtos.Facts.newBuilder();
            if (hasFacts())
              localBuilder8.mergeFrom(getFacts());
            paramCodedInputStream.readMessage(localBuilder8, paramExtensionRegistryLite);
            setFacts(localBuilder8.buildPartial());
            break;
          case 130:
            AnnotationResultProtos.AnnotationResult.PersonInformation.Builder localBuilder7 = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder();
            if (hasPersonInfo())
              localBuilder7.mergeFrom(getPersonInfo());
            paramCodedInputStream.readMessage(localBuilder7, paramExtensionRegistryLite);
            setPersonInfo(localBuilder7.buildPartial());
            break;
          case 138:
            AnnotationResultProtos.AnnotationResult.PlaceInformation.Builder localBuilder6 = AnnotationResultProtos.AnnotationResult.PlaceInformation.newBuilder();
            if (hasPlaceInfo())
              localBuilder6.mergeFrom(getPlaceInfo());
            paramCodedInputStream.readMessage(localBuilder6, paramExtensionRegistryLite);
            setPlaceInfo(localBuilder6.buildPartial());
            break;
          case 154:
            this.bitField0_ = (0x4000 | this.bitField0_);
            this.websearchUrl_ = paramCodedInputStream.readBytes();
            break;
          case 162:
            this.bitField0_ = (0x40 | this.bitField0_);
            this.directUrl_ = paramCodedInputStream.readBytes();
            break;
          case 170:
            ProductInformationProtos.ProductInformation.Builder localBuilder5 = ProductInformationProtos.ProductInformation.newBuilder();
            if (hasProductInfo())
              localBuilder5.mergeFrom(getProductInfo());
            paramCodedInputStream.readMessage(localBuilder5, paramExtensionRegistryLite);
            setProductInfo(localBuilder5.buildPartial());
            break;
          case 178:
            AnnotationResultProtos.AnnotationResult.TextInformation.Builder localBuilder4 = AnnotationResultProtos.AnnotationResult.TextInformation.newBuilder();
            if (hasTextInfo())
              localBuilder4.mergeFrom(getTextInfo());
            paramCodedInputStream.readMessage(localBuilder4, paramExtensionRegistryLite);
            setTextInfo(localBuilder4.buildPartial());
            break;
          case 186:
            AnnotationResultProtos.AnnotationResult.ContributionInformation.Builder localBuilder3 = AnnotationResultProtos.AnnotationResult.ContributionInformation.newBuilder();
            if (hasContributionInfo())
              localBuilder3.mergeFrom(getContributionInfo());
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            setContributionInfo(localBuilder3.buildPartial());
            break;
          case 194:
            this.bitField0_ = (0x800 | this.bitField0_);
            this.locationText_ = paramCodedInputStream.readBytes();
            break;
          case 200:
            this.bitField0_ = (0x400000 | this.bitField0_);
            this.isWithdrawn_ = paramCodedInputStream.readBool();
            break;
          case 208:
            this.bitField0_ = (0x800000 | this.bitField0_);
            this.isAd_ = paramCodedInputStream.readBool();
            break;
          case 218:
            MusicInformationProtos.MusicInformation.Builder localBuilder2 = MusicInformationProtos.MusicInformation.newBuilder();
            if (hasMusicInfo())
              localBuilder2.mergeFrom(getMusicInfo());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setMusicInfo(localBuilder2.buildPartial());
            break;
          case 226:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.ttsDescription_ = paramCodedInputStream.readBytes();
            break;
          case 234:
            MoreLikeThisActionProtos.MoreLikeThisAction.Builder localBuilder1 = MoreLikeThisActionProtos.MoreLikeThisAction.newBuilder();
            if (hasMoreLikeThisAction())
              localBuilder1.mergeFrom(getMoreLikeThisAction());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setMoreLikeThisAction(localBuilder1.buildPartial());
            break;
          case 242:
            ShareActionProtos.ShareAction.Builder localBuilder = ShareActionProtos.ShareAction.newBuilder();
            if (hasShareAction())
              localBuilder.mergeFrom(getShareAction());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setShareAction(localBuilder.buildPartial());
            break;
          case 248:
          }
          this.bitField0_ = (0x10000000 | this.bitField0_);
          this.isSimilarProduct_ = paramCodedInputStream.readBool();
        }
      }

      public Builder mergeLatLng(LatLngProtos.LatLng paramLatLng)
      {
        if (((0x400 & this.bitField0_) == 1024) && (this.latLng_ != LatLngProtos.LatLng.getDefaultInstance()));
        for (this.latLng_ = LatLngProtos.LatLng.newBuilder(this.latLng_).mergeFrom(paramLatLng).buildPartial(); ; this.latLng_ = paramLatLng)
        {
          this.bitField0_ = (0x400 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeMoreLikeThisAction(MoreLikeThisActionProtos.MoreLikeThisAction paramMoreLikeThisAction)
      {
        if (((0x4000000 & this.bitField0_) == 67108864) && (this.moreLikeThisAction_ != MoreLikeThisActionProtos.MoreLikeThisAction.getDefaultInstance()));
        for (this.moreLikeThisAction_ = MoreLikeThisActionProtos.MoreLikeThisAction.newBuilder(this.moreLikeThisAction_).mergeFrom(paramMoreLikeThisAction).buildPartial(); ; this.moreLikeThisAction_ = paramMoreLikeThisAction)
        {
          this.bitField0_ = (0x4000000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeMusicInfo(MusicInformationProtos.MusicInformation paramMusicInformation)
      {
        if (((0x200000 & this.bitField0_) == 2097152) && (this.musicInfo_ != MusicInformationProtos.MusicInformation.getDefaultInstance()));
        for (this.musicInfo_ = MusicInformationProtos.MusicInformation.newBuilder(this.musicInfo_).mergeFrom(paramMusicInformation).buildPartial(); ; this.musicInfo_ = paramMusicInformation)
        {
          this.bitField0_ = (0x200000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergePersonInfo(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
      {
        if (((0x10000 & this.bitField0_) == 65536) && (this.personInfo_ != AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance()));
        for (this.personInfo_ = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder(this.personInfo_).mergeFrom(paramPersonInformation).buildPartial(); ; this.personInfo_ = paramPersonInformation)
        {
          this.bitField0_ = (0x10000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergePlaceInfo(AnnotationResultProtos.AnnotationResult.PlaceInformation paramPlaceInformation)
      {
        if (((0x20000 & this.bitField0_) == 131072) && (this.placeInfo_ != AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance()));
        for (this.placeInfo_ = AnnotationResultProtos.AnnotationResult.PlaceInformation.newBuilder(this.placeInfo_).mergeFrom(paramPlaceInformation).buildPartial(); ; this.placeInfo_ = paramPlaceInformation)
        {
          this.bitField0_ = (0x20000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeProductInfo(ProductInformationProtos.ProductInformation paramProductInformation)
      {
        if (((0x40000 & this.bitField0_) == 262144) && (this.productInfo_ != ProductInformationProtos.ProductInformation.getDefaultInstance()));
        for (this.productInfo_ = ProductInformationProtos.ProductInformation.newBuilder(this.productInfo_).mergeFrom(paramProductInformation).buildPartial(); ; this.productInfo_ = paramProductInformation)
        {
          this.bitField0_ = (0x40000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeShareAction(ShareActionProtos.ShareAction paramShareAction)
      {
        if (((0x8000000 & this.bitField0_) == 134217728) && (this.shareAction_ != ShareActionProtos.ShareAction.getDefaultInstance()));
        for (this.shareAction_ = ShareActionProtos.ShareAction.newBuilder(this.shareAction_).mergeFrom(paramShareAction).buildPartial(); ; this.shareAction_ = paramShareAction)
        {
          this.bitField0_ = (0x8000000 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeTextInfo(AnnotationResultProtos.AnnotationResult.TextInformation paramTextInformation)
      {
        if (((0x80000 & this.bitField0_) == 524288) && (this.textInfo_ != AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance()));
        for (this.textInfo_ = AnnotationResultProtos.AnnotationResult.TextInformation.newBuilder(this.textInfo_).mergeFrom(paramTextInformation).buildPartial(); ; this.textInfo_ = paramTextInformation)
        {
          this.bitField0_ = (0x80000 | this.bitField0_);
          return this;
        }
      }

      public Builder removeUrlGroups(int paramInt)
      {
        ensureUrlGroupsIsMutable();
        this.urlGroups_.remove(paramInt);
        return this;
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
      {
        this.boundingBox_ = paramBuilder.build();
        this.bitField0_ = (0x80 | this.bitField0_);
        return this;
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == null)
          throw new NullPointerException();
        this.boundingBox_ = paramBoundingBox;
        this.bitField0_ = (0x80 | this.bitField0_);
        return this;
      }

      public Builder setBoundingBoxRequestId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2000000 | this.bitField0_);
        this.boundingBoxRequestId_ = paramString;
        return this;
      }

      void setBoundingBoxRequestId(ByteString paramByteString)
      {
        this.bitField0_ = (0x2000000 | this.bitField0_);
        this.boundingBoxRequestId_ = paramByteString;
      }

      public Builder setCanonicalImage(CanonicalImageProtos.CanonicalImage.Builder paramBuilder)
      {
        this.canonicalImage_ = paramBuilder.build();
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      public Builder setCanonicalImage(CanonicalImageProtos.CanonicalImage paramCanonicalImage)
      {
        if (paramCanonicalImage == null)
          throw new NullPointerException();
        this.canonicalImage_ = paramCanonicalImage;
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      public Builder setContributionInfo(AnnotationResultProtos.AnnotationResult.ContributionInformation.Builder paramBuilder)
      {
        this.contributionInfo_ = paramBuilder.build();
        this.bitField0_ = (0x100000 | this.bitField0_);
        return this;
      }

      public Builder setContributionInfo(AnnotationResultProtos.AnnotationResult.ContributionInformation paramContributionInformation)
      {
        if (paramContributionInformation == null)
          throw new NullPointerException();
        this.contributionInfo_ = paramContributionInformation;
        this.bitField0_ = (0x100000 | this.bitField0_);
        return this;
      }

      public Builder setDirectUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.directUrl_ = paramString;
        return this;
      }

      void setDirectUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.directUrl_ = paramByteString;
      }

      public Builder setFacts(FactsProtos.Facts.Builder paramBuilder)
      {
        this.facts_ = paramBuilder.build();
        this.bitField0_ = (0x8000 | this.bitField0_);
        return this;
      }

      public Builder setFacts(FactsProtos.Facts paramFacts)
      {
        if (paramFacts == null)
          throw new NullPointerException();
        this.facts_ = paramFacts;
        this.bitField0_ = (0x8000 | this.bitField0_);
        return this;
      }

      public Builder setIsAd(boolean paramBoolean)
      {
        this.bitField0_ = (0x800000 | this.bitField0_);
        this.isAd_ = paramBoolean;
        return this;
      }

      public Builder setIsSimilarProduct(boolean paramBoolean)
      {
        this.bitField0_ = (0x10000000 | this.bitField0_);
        this.isSimilarProduct_ = paramBoolean;
        return this;
      }

      public Builder setIsWithdrawn(boolean paramBoolean)
      {
        this.bitField0_ = (0x400000 | this.bitField0_);
        this.isWithdrawn_ = paramBoolean;
        return this;
      }

      public Builder setLanguage(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1000 | this.bitField0_);
        this.language_ = paramString;
        return this;
      }

      void setLanguage(ByteString paramByteString)
      {
        this.bitField0_ = (0x1000 | this.bitField0_);
        this.language_ = paramByteString;
      }

      public Builder setLatLng(LatLngProtos.LatLng.Builder paramBuilder)
      {
        this.latLng_ = paramBuilder.build();
        this.bitField0_ = (0x400 | this.bitField0_);
        return this;
      }

      public Builder setLatLng(LatLngProtos.LatLng paramLatLng)
      {
        if (paramLatLng == null)
          throw new NullPointerException();
        this.latLng_ = paramLatLng;
        this.bitField0_ = (0x400 | this.bitField0_);
        return this;
      }

      public Builder setLocationText(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x800 | this.bitField0_);
        this.locationText_ = paramString;
        return this;
      }

      void setLocationText(ByteString paramByteString)
      {
        this.bitField0_ = (0x800 | this.bitField0_);
        this.locationText_ = paramByteString;
      }

      public Builder setMoreLikeThisAction(MoreLikeThisActionProtos.MoreLikeThisAction.Builder paramBuilder)
      {
        this.moreLikeThisAction_ = paramBuilder.build();
        this.bitField0_ = (0x4000000 | this.bitField0_);
        return this;
      }

      public Builder setMoreLikeThisAction(MoreLikeThisActionProtos.MoreLikeThisAction paramMoreLikeThisAction)
      {
        if (paramMoreLikeThisAction == null)
          throw new NullPointerException();
        this.moreLikeThisAction_ = paramMoreLikeThisAction;
        this.bitField0_ = (0x4000000 | this.bitField0_);
        return this;
      }

      public Builder setMusicInfo(MusicInformationProtos.MusicInformation.Builder paramBuilder)
      {
        this.musicInfo_ = paramBuilder.build();
        this.bitField0_ = (0x200000 | this.bitField0_);
        return this;
      }

      public Builder setMusicInfo(MusicInformationProtos.MusicInformation paramMusicInformation)
      {
        if (paramMusicInformation == null)
          throw new NullPointerException();
        this.musicInfo_ = paramMusicInformation;
        this.bitField0_ = (0x200000 | this.bitField0_);
        return this;
      }

      public Builder setPersonInfo(AnnotationResultProtos.AnnotationResult.PersonInformation.Builder paramBuilder)
      {
        this.personInfo_ = paramBuilder.build();
        this.bitField0_ = (0x10000 | this.bitField0_);
        return this;
      }

      public Builder setPersonInfo(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
      {
        if (paramPersonInformation == null)
          throw new NullPointerException();
        this.personInfo_ = paramPersonInformation;
        this.bitField0_ = (0x10000 | this.bitField0_);
        return this;
      }

      public Builder setPlaceInfo(AnnotationResultProtos.AnnotationResult.PlaceInformation.Builder paramBuilder)
      {
        this.placeInfo_ = paramBuilder.build();
        this.bitField0_ = (0x20000 | this.bitField0_);
        return this;
      }

      public Builder setPlaceInfo(AnnotationResultProtos.AnnotationResult.PlaceInformation paramPlaceInformation)
      {
        if (paramPlaceInformation == null)
          throw new NullPointerException();
        this.placeInfo_ = paramPlaceInformation;
        this.bitField0_ = (0x20000 | this.bitField0_);
        return this;
      }

      public Builder setProductInfo(ProductInformationProtos.ProductInformation.Builder paramBuilder)
      {
        this.productInfo_ = paramBuilder.build();
        this.bitField0_ = (0x40000 | this.bitField0_);
        return this;
      }

      public Builder setProductInfo(ProductInformationProtos.ProductInformation paramProductInformation)
      {
        if (paramProductInformation == null)
          throw new NullPointerException();
        this.productInfo_ = paramProductInformation;
        this.bitField0_ = (0x40000 | this.bitField0_);
        return this;
      }

      public Builder setResultId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1000000 | this.bitField0_);
        this.resultId_ = paramString;
        return this;
      }

      void setResultId(ByteString paramByteString)
      {
        this.bitField0_ = (0x1000000 | this.bitField0_);
        this.resultId_ = paramByteString;
      }

      public Builder setResultUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.resultUrl_ = paramString;
        return this;
      }

      void setResultUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.resultUrl_ = paramByteString;
      }

      public Builder setShareAction(ShareActionProtos.ShareAction.Builder paramBuilder)
      {
        this.shareAction_ = paramBuilder.build();
        this.bitField0_ = (0x8000000 | this.bitField0_);
        return this;
      }

      public Builder setShareAction(ShareActionProtos.ShareAction paramShareAction)
      {
        if (paramShareAction == null)
          throw new NullPointerException();
        this.shareAction_ = paramShareAction;
        this.bitField0_ = (0x8000000 | this.bitField0_);
        return this;
      }

      public Builder setSubtitle(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.subtitle_ = paramString;
        return this;
      }

      void setSubtitle(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.subtitle_ = paramByteString;
      }

      public Builder setSubtype(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.subtype_ = paramString;
        return this;
      }

      void setSubtype(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.subtype_ = paramByteString;
      }

      public Builder setTextInfo(AnnotationResultProtos.AnnotationResult.TextInformation.Builder paramBuilder)
      {
        this.textInfo_ = paramBuilder.build();
        this.bitField0_ = (0x80000 | this.bitField0_);
        return this;
      }

      public Builder setTextInfo(AnnotationResultProtos.AnnotationResult.TextInformation paramTextInformation)
      {
        if (paramTextInformation == null)
          throw new NullPointerException();
        this.textInfo_ = paramTextInformation;
        this.bitField0_ = (0x80000 | this.bitField0_);
        return this;
      }

      public Builder setTitle(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.title_ = paramString;
        return this;
      }

      void setTitle(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.title_ = paramByteString;
      }

      public Builder setTtsDescription(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x10 | this.bitField0_);
        this.ttsDescription_ = paramString;
        return this;
      }

      void setTtsDescription(ByteString paramByteString)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.ttsDescription_ = paramByteString;
      }

      public Builder setType(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.type_ = paramString;
        return this;
      }

      void setType(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.type_ = paramByteString;
      }

      public Builder setUrlGroups(int paramInt, UrlGroupProtos.UrlGroup.Builder paramBuilder)
      {
        ensureUrlGroupsIsMutable();
        this.urlGroups_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setUrlGroups(int paramInt, UrlGroupProtos.UrlGroup paramUrlGroup)
      {
        if (paramUrlGroup == null)
          throw new NullPointerException();
        ensureUrlGroupsIsMutable();
        this.urlGroups_.set(paramInt, paramUrlGroup);
        return this;
      }

      @Deprecated
      public Builder setUrls(int paramInt, String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        ensureUrlsIsMutable();
        this.urls_.set(paramInt, paramString);
        return this;
      }

      public Builder setWebsearchUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4000 | this.bitField0_);
        this.websearchUrl_ = paramString;
        return this;
      }

      void setWebsearchUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x4000 | this.bitField0_);
        this.websearchUrl_ = paramByteString;
      }
    }

    public static final class ContributionInformation extends GeneratedMessageLite
      implements AnnotationResultProtos.AnnotationResult.ContributionInformationOrBuilder
    {
      public static final int CONTRIBUTOR_FIELD_NUMBER = 1;
      public static final int CREATION_TIMESTAMP_MS_FIELD_NUMBER = 3;
      public static final int MOMENT_ID_FIELD_NUMBER = 7;
      public static final int REPORT_ABUSE_URL_FIELD_NUMBER = 5;
      public static final int USER_IS_OWNER_FIELD_NUMBER = 6;
      public static final int USER_SUBMITTED_DESCRIPTION_FIELD_NUMBER = 4;
      private static final ContributionInformation defaultInstance = new ContributionInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private AnnotationResultProtos.AnnotationResult.PersonInformation contributor_;
      private long creationTimestampMs_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object momentId_;
      private Object reportAbuseUrl_;
      private boolean userIsOwner_;
      private Object userSubmittedDescription_;

      static
      {
        defaultInstance.initFields();
      }

      private ContributionInformation(Builder paramBuilder)
      {
        super();
      }

      private ContributionInformation(boolean paramBoolean)
      {
      }

      public static ContributionInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getMomentIdBytes()
      {
        Object localObject = this.momentId_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.momentId_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getReportAbuseUrlBytes()
      {
        Object localObject = this.reportAbuseUrl_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.reportAbuseUrl_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getUserSubmittedDescriptionBytes()
      {
        Object localObject = this.userSubmittedDescription_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.userSubmittedDescription_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        this.creationTimestampMs_ = 0L;
        this.userSubmittedDescription_ = "";
        this.reportAbuseUrl_ = "";
        this.userIsOwner_ = false;
        this.momentId_ = "";
      }

      public static Builder newBuilder()
      {
        return Builder.access$2700();
      }

      public static Builder newBuilder(ContributionInformation paramContributionInformation)
      {
        return newBuilder().mergeFrom(paramContributionInformation);
      }

      public static ContributionInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static ContributionInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static ContributionInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static ContributionInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static ContributionInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static ContributionInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static ContributionInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static ContributionInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static ContributionInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static ContributionInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public AnnotationResultProtos.AnnotationResult.PersonInformation getContributor()
      {
        return this.contributor_;
      }

      public long getCreationTimestampMs()
      {
        return this.creationTimestampMs_;
      }

      public ContributionInformation getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public String getMomentId()
      {
        Object localObject = this.momentId_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.momentId_ = str;
        return str;
      }

      public String getReportAbuseUrl()
      {
        Object localObject = this.reportAbuseUrl_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.reportAbuseUrl_ = str;
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
          k = 0 + CodedOutputStream.computeMessageSize(1, this.contributor_);
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeInt64Size(3, this.creationTimestampMs_);
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeBytesSize(4, getUserSubmittedDescriptionBytes());
        if ((0x8 & this.bitField0_) == 8)
          k += CodedOutputStream.computeBytesSize(5, getReportAbuseUrlBytes());
        if ((0x10 & this.bitField0_) == 16)
          k += CodedOutputStream.computeBoolSize(6, this.userIsOwner_);
        if ((0x20 & this.bitField0_) == 32)
          k += CodedOutputStream.computeBytesSize(7, getMomentIdBytes());
        this.memoizedSerializedSize = k;
        return k;
      }

      public boolean getUserIsOwner()
      {
        return this.userIsOwner_;
      }

      public String getUserSubmittedDescription()
      {
        Object localObject = this.userSubmittedDescription_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.userSubmittedDescription_ = str;
        return str;
      }

      public boolean hasContributor()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasCreationTimestampMs()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasMomentId()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasReportAbuseUrl()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasUserIsOwner()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasUserSubmittedDescription()
      {
        return (0x4 & this.bitField0_) == 4;
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
          paramCodedOutputStream.writeMessage(1, this.contributor_);
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeInt64(3, this.creationTimestampMs_);
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeBytes(4, getUserSubmittedDescriptionBytes());
        if ((0x8 & this.bitField0_) == 8)
          paramCodedOutputStream.writeBytes(5, getReportAbuseUrlBytes());
        if ((0x10 & this.bitField0_) == 16)
          paramCodedOutputStream.writeBool(6, this.userIsOwner_);
        if ((0x20 & this.bitField0_) == 32)
          paramCodedOutputStream.writeBytes(7, getMomentIdBytes());
      }

      public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult.ContributionInformation, Builder>
        implements AnnotationResultProtos.AnnotationResult.ContributionInformationOrBuilder
      {
        private int bitField0_;
        private AnnotationResultProtos.AnnotationResult.PersonInformation contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        private long creationTimestampMs_;
        private Object momentId_ = "";
        private Object reportAbuseUrl_ = "";
        private boolean userIsOwner_;
        private Object userSubmittedDescription_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private AnnotationResultProtos.AnnotationResult.ContributionInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          AnnotationResultProtos.AnnotationResult.ContributionInformation localContributionInformation = buildPartial();
          if (!localContributionInformation.isInitialized())
            throw newUninitializedMessageException(localContributionInformation).asInvalidProtocolBufferException();
          return localContributionInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public AnnotationResultProtos.AnnotationResult.ContributionInformation build()
        {
          AnnotationResultProtos.AnnotationResult.ContributionInformation localContributionInformation = buildPartial();
          if (!localContributionInformation.isInitialized())
            throw newUninitializedMessageException(localContributionInformation);
          return localContributionInformation;
        }

        public AnnotationResultProtos.AnnotationResult.ContributionInformation buildPartial()
        {
          int i = 1;
          AnnotationResultProtos.AnnotationResult.ContributionInformation localContributionInformation = new AnnotationResultProtos.AnnotationResult.ContributionInformation(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$2902(localContributionInformation, this.contributor_);
            if ((j & 0x2) == 2)
              i |= 2;
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3002(localContributionInformation, this.creationTimestampMs_);
            if ((j & 0x4) == 4)
              i |= 4;
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3102(localContributionInformation, this.userSubmittedDescription_);
            if ((j & 0x8) == 8)
              i |= 8;
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3202(localContributionInformation, this.reportAbuseUrl_);
            if ((j & 0x10) == 16)
              i |= 16;
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3302(localContributionInformation, this.userIsOwner_);
            if ((j & 0x20) == 32)
              i |= 32;
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3402(localContributionInformation, this.momentId_);
            AnnotationResultProtos.AnnotationResult.ContributionInformation.access$3502(localContributionInformation, i);
            return localContributionInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.creationTimestampMs_ = 0L;
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.userSubmittedDescription_ = "";
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.reportAbuseUrl_ = "";
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.userIsOwner_ = false;
          this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          this.momentId_ = "";
          this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
          return this;
        }

        public Builder clearContributor()
        {
          this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          return this;
        }

        public Builder clearCreationTimestampMs()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.creationTimestampMs_ = 0L;
          return this;
        }

        public Builder clearMomentId()
        {
          this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
          this.momentId_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance().getMomentId();
          return this;
        }

        public Builder clearReportAbuseUrl()
        {
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.reportAbuseUrl_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance().getReportAbuseUrl();
          return this;
        }

        public Builder clearUserIsOwner()
        {
          this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          this.userIsOwner_ = false;
          return this;
        }

        public Builder clearUserSubmittedDescription()
        {
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.userSubmittedDescription_ = AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance().getUserSubmittedDescription();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public AnnotationResultProtos.AnnotationResult.PersonInformation getContributor()
        {
          return this.contributor_;
        }

        public long getCreationTimestampMs()
        {
          return this.creationTimestampMs_;
        }

        public AnnotationResultProtos.AnnotationResult.ContributionInformation getDefaultInstanceForType()
        {
          return AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance();
        }

        public String getMomentId()
        {
          Object localObject = this.momentId_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.momentId_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getReportAbuseUrl()
        {
          Object localObject = this.reportAbuseUrl_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.reportAbuseUrl_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean getUserIsOwner()
        {
          return this.userIsOwner_;
        }

        public String getUserSubmittedDescription()
        {
          Object localObject = this.userSubmittedDescription_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.userSubmittedDescription_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean hasContributor()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public boolean hasCreationTimestampMs()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public boolean hasMomentId()
        {
          return (0x20 & this.bitField0_) == 32;
        }

        public boolean hasReportAbuseUrl()
        {
          return (0x8 & this.bitField0_) == 8;
        }

        public boolean hasUserIsOwner()
        {
          return (0x10 & this.bitField0_) == 16;
        }

        public boolean hasUserSubmittedDescription()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeContributor(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
        {
          if (((0x1 & this.bitField0_) == 1) && (this.contributor_ != AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance()));
          for (this.contributor_ = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder(this.contributor_).mergeFrom(paramPersonInformation).buildPartial(); ; this.contributor_ = paramPersonInformation)
          {
            this.bitField0_ = (0x1 | this.bitField0_);
            return this;
          }
        }

        public Builder mergeFrom(AnnotationResultProtos.AnnotationResult.ContributionInformation paramContributionInformation)
        {
          if (paramContributionInformation == AnnotationResultProtos.AnnotationResult.ContributionInformation.getDefaultInstance());
          do
          {
            return this;
            if (paramContributionInformation.hasContributor())
              mergeContributor(paramContributionInformation.getContributor());
            if (paramContributionInformation.hasCreationTimestampMs())
              setCreationTimestampMs(paramContributionInformation.getCreationTimestampMs());
            if (paramContributionInformation.hasUserSubmittedDescription())
              setUserSubmittedDescription(paramContributionInformation.getUserSubmittedDescription());
            if (paramContributionInformation.hasReportAbuseUrl())
              setReportAbuseUrl(paramContributionInformation.getReportAbuseUrl());
            if (paramContributionInformation.hasUserIsOwner())
              setUserIsOwner(paramContributionInformation.getUserIsOwner());
          }
          while (!paramContributionInformation.hasMomentId());
          setMomentId(paramContributionInformation.getMomentId());
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
              AnnotationResultProtos.AnnotationResult.PersonInformation.Builder localBuilder = AnnotationResultProtos.AnnotationResult.PersonInformation.newBuilder();
              if (hasContributor())
                localBuilder.mergeFrom(getContributor());
              paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
              setContributor(localBuilder.buildPartial());
              break;
            case 24:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.creationTimestampMs_ = paramCodedInputStream.readInt64();
              break;
            case 34:
              this.bitField0_ = (0x4 | this.bitField0_);
              this.userSubmittedDescription_ = paramCodedInputStream.readBytes();
              break;
            case 42:
              this.bitField0_ = (0x8 | this.bitField0_);
              this.reportAbuseUrl_ = paramCodedInputStream.readBytes();
              break;
            case 48:
              this.bitField0_ = (0x10 | this.bitField0_);
              this.userIsOwner_ = paramCodedInputStream.readBool();
              break;
            case 58:
            }
            this.bitField0_ = (0x20 | this.bitField0_);
            this.momentId_ = paramCodedInputStream.readBytes();
          }
        }

        public Builder setContributor(AnnotationResultProtos.AnnotationResult.PersonInformation.Builder paramBuilder)
        {
          this.contributor_ = paramBuilder.build();
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }

        public Builder setContributor(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
        {
          if (paramPersonInformation == null)
            throw new NullPointerException();
          this.contributor_ = paramPersonInformation;
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }

        public Builder setCreationTimestampMs(long paramLong)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.creationTimestampMs_ = paramLong;
          return this;
        }

        public Builder setMomentId(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x20 | this.bitField0_);
          this.momentId_ = paramString;
          return this;
        }

        void setMomentId(ByteString paramByteString)
        {
          this.bitField0_ = (0x20 | this.bitField0_);
          this.momentId_ = paramByteString;
        }

        public Builder setReportAbuseUrl(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x8 | this.bitField0_);
          this.reportAbuseUrl_ = paramString;
          return this;
        }

        void setReportAbuseUrl(ByteString paramByteString)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          this.reportAbuseUrl_ = paramByteString;
        }

        public Builder setUserIsOwner(boolean paramBoolean)
        {
          this.bitField0_ = (0x10 | this.bitField0_);
          this.userIsOwner_ = paramBoolean;
          return this;
        }

        public Builder setUserSubmittedDescription(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x4 | this.bitField0_);
          this.userSubmittedDescription_ = paramString;
          return this;
        }

        void setUserSubmittedDescription(ByteString paramByteString)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          this.userSubmittedDescription_ = paramByteString;
        }
      }
    }

    public static abstract interface ContributionInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract AnnotationResultProtos.AnnotationResult.PersonInformation getContributor();

      public abstract long getCreationTimestampMs();

      public abstract String getMomentId();

      public abstract String getReportAbuseUrl();

      public abstract boolean getUserIsOwner();

      public abstract String getUserSubmittedDescription();

      public abstract boolean hasContributor();

      public abstract boolean hasCreationTimestampMs();

      public abstract boolean hasMomentId();

      public abstract boolean hasReportAbuseUrl();

      public abstract boolean hasUserIsOwner();

      public abstract boolean hasUserSubmittedDescription();
    }

    public static final class PersonInformation extends GeneratedMessageLite
      implements AnnotationResultProtos.AnnotationResult.PersonInformationOrBuilder
    {
      public static final int DISPLAY_NAME_FIELD_NUMBER = 2;
      public static final int PROFILE_PHOTO_URL_FIELD_NUMBER = 4;
      public static final int PROFILE_URL_FIELD_NUMBER = 3;
      public static final int USER_ID_FIELD_NUMBER = 1;
      private static final PersonInformation defaultInstance = new PersonInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private Object displayName_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object profilePhotoUrl_;
      private Object profileUrl_;
      private Object userId_;

      static
      {
        defaultInstance.initFields();
      }

      private PersonInformation(Builder paramBuilder)
      {
        super();
      }

      private PersonInformation(boolean paramBoolean)
      {
      }

      public static PersonInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getDisplayNameBytes()
      {
        Object localObject = this.displayName_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.displayName_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getProfilePhotoUrlBytes()
      {
        Object localObject = this.profilePhotoUrl_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.profilePhotoUrl_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getProfileUrlBytes()
      {
        Object localObject = this.profileUrl_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.profileUrl_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private ByteString getUserIdBytes()
      {
        Object localObject = this.userId_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.userId_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.userId_ = "";
        this.displayName_ = "";
        this.profileUrl_ = "";
        this.profilePhotoUrl_ = "";
      }

      public static Builder newBuilder()
      {
        return Builder.access$100();
      }

      public static Builder newBuilder(PersonInformation paramPersonInformation)
      {
        return newBuilder().mergeFrom(paramPersonInformation);
      }

      public static PersonInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static PersonInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static PersonInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static PersonInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static PersonInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static PersonInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static PersonInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static PersonInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static PersonInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static PersonInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public PersonInformation getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public String getDisplayName()
      {
        Object localObject = this.displayName_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.displayName_ = str;
        return str;
      }

      public String getProfilePhotoUrl()
      {
        Object localObject = this.profilePhotoUrl_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.profilePhotoUrl_ = str;
        return str;
      }

      public String getProfileUrl()
      {
        Object localObject = this.profileUrl_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.profileUrl_ = str;
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
          k = 0 + CodedOutputStream.computeBytesSize(1, getUserIdBytes());
        if ((0x2 & this.bitField0_) == 2)
          k += CodedOutputStream.computeBytesSize(2, getDisplayNameBytes());
        if ((0x4 & this.bitField0_) == 4)
          k += CodedOutputStream.computeBytesSize(3, getProfileUrlBytes());
        if ((0x8 & this.bitField0_) == 8)
          k += CodedOutputStream.computeBytesSize(4, getProfilePhotoUrlBytes());
        this.memoizedSerializedSize = k;
        return k;
      }

      public String getUserId()
      {
        Object localObject = this.userId_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.userId_ = str;
        return str;
      }

      public boolean hasDisplayName()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasProfilePhotoUrl()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasProfileUrl()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasUserId()
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
          paramCodedOutputStream.writeBytes(1, getUserIdBytes());
        if ((0x2 & this.bitField0_) == 2)
          paramCodedOutputStream.writeBytes(2, getDisplayNameBytes());
        if ((0x4 & this.bitField0_) == 4)
          paramCodedOutputStream.writeBytes(3, getProfileUrlBytes());
        if ((0x8 & this.bitField0_) == 8)
          paramCodedOutputStream.writeBytes(4, getProfilePhotoUrlBytes());
      }

      public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult.PersonInformation, Builder>
        implements AnnotationResultProtos.AnnotationResult.PersonInformationOrBuilder
      {
        private int bitField0_;
        private Object displayName_ = "";
        private Object profilePhotoUrl_ = "";
        private Object profileUrl_ = "";
        private Object userId_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private AnnotationResultProtos.AnnotationResult.PersonInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          AnnotationResultProtos.AnnotationResult.PersonInformation localPersonInformation = buildPartial();
          if (!localPersonInformation.isInitialized())
            throw newUninitializedMessageException(localPersonInformation).asInvalidProtocolBufferException();
          return localPersonInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public AnnotationResultProtos.AnnotationResult.PersonInformation build()
        {
          AnnotationResultProtos.AnnotationResult.PersonInformation localPersonInformation = buildPartial();
          if (!localPersonInformation.isInitialized())
            throw newUninitializedMessageException(localPersonInformation);
          return localPersonInformation;
        }

        public AnnotationResultProtos.AnnotationResult.PersonInformation buildPartial()
        {
          int i = 1;
          AnnotationResultProtos.AnnotationResult.PersonInformation localPersonInformation = new AnnotationResultProtos.AnnotationResult.PersonInformation(this, null);
          int j = this.bitField0_;
          if ((j & 0x1) == i);
          while (true)
          {
            AnnotationResultProtos.AnnotationResult.PersonInformation.access$302(localPersonInformation, this.userId_);
            if ((j & 0x2) == 2)
              i |= 2;
            AnnotationResultProtos.AnnotationResult.PersonInformation.access$402(localPersonInformation, this.displayName_);
            if ((j & 0x4) == 4)
              i |= 4;
            AnnotationResultProtos.AnnotationResult.PersonInformation.access$502(localPersonInformation, this.profileUrl_);
            if ((j & 0x8) == 8)
              i |= 8;
            AnnotationResultProtos.AnnotationResult.PersonInformation.access$602(localPersonInformation, this.profilePhotoUrl_);
            AnnotationResultProtos.AnnotationResult.PersonInformation.access$702(localPersonInformation, i);
            return localPersonInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.userId_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.displayName_ = "";
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.profileUrl_ = "";
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.profilePhotoUrl_ = "";
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
        }

        public Builder clearDisplayName()
        {
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          this.displayName_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance().getDisplayName();
          return this;
        }

        public Builder clearProfilePhotoUrl()
        {
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          this.profilePhotoUrl_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance().getProfilePhotoUrl();
          return this;
        }

        public Builder clearProfileUrl()
        {
          this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          this.profileUrl_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance().getProfileUrl();
          return this;
        }

        public Builder clearUserId()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.userId_ = AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance().getUserId();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public AnnotationResultProtos.AnnotationResult.PersonInformation getDefaultInstanceForType()
        {
          return AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance();
        }

        public String getDisplayName()
        {
          Object localObject = this.displayName_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.displayName_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getProfilePhotoUrl()
        {
          Object localObject = this.profilePhotoUrl_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.profilePhotoUrl_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getProfileUrl()
        {
          Object localObject = this.profileUrl_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.profileUrl_ = str;
            return str;
          }
          return (String)localObject;
        }

        public String getUserId()
        {
          Object localObject = this.userId_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.userId_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean hasDisplayName()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public boolean hasProfilePhotoUrl()
        {
          return (0x8 & this.bitField0_) == 8;
        }

        public boolean hasProfileUrl()
        {
          return (0x4 & this.bitField0_) == 4;
        }

        public boolean hasUserId()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(AnnotationResultProtos.AnnotationResult.PersonInformation paramPersonInformation)
        {
          if (paramPersonInformation == AnnotationResultProtos.AnnotationResult.PersonInformation.getDefaultInstance());
          do
          {
            return this;
            if (paramPersonInformation.hasUserId())
              setUserId(paramPersonInformation.getUserId());
            if (paramPersonInformation.hasDisplayName())
              setDisplayName(paramPersonInformation.getDisplayName());
            if (paramPersonInformation.hasProfileUrl())
              setProfileUrl(paramPersonInformation.getProfileUrl());
          }
          while (!paramPersonInformation.hasProfilePhotoUrl());
          setProfilePhotoUrl(paramPersonInformation.getProfilePhotoUrl());
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
              this.userId_ = paramCodedInputStream.readBytes();
              break;
            case 18:
              this.bitField0_ = (0x2 | this.bitField0_);
              this.displayName_ = paramCodedInputStream.readBytes();
              break;
            case 26:
              this.bitField0_ = (0x4 | this.bitField0_);
              this.profileUrl_ = paramCodedInputStream.readBytes();
              break;
            case 34:
            }
            this.bitField0_ = (0x8 | this.bitField0_);
            this.profilePhotoUrl_ = paramCodedInputStream.readBytes();
          }
        }

        public Builder setDisplayName(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x2 | this.bitField0_);
          this.displayName_ = paramString;
          return this;
        }

        void setDisplayName(ByteString paramByteString)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          this.displayName_ = paramByteString;
        }

        public Builder setProfilePhotoUrl(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x8 | this.bitField0_);
          this.profilePhotoUrl_ = paramString;
          return this;
        }

        void setProfilePhotoUrl(ByteString paramByteString)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          this.profilePhotoUrl_ = paramByteString;
        }

        public Builder setProfileUrl(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x4 | this.bitField0_);
          this.profileUrl_ = paramString;
          return this;
        }

        void setProfileUrl(ByteString paramByteString)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          this.profileUrl_ = paramByteString;
        }

        public Builder setUserId(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.userId_ = paramString;
          return this;
        }

        void setUserId(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.userId_ = paramByteString;
        }
      }
    }

    public static abstract interface PersonInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract String getDisplayName();

      public abstract String getProfilePhotoUrl();

      public abstract String getProfileUrl();

      public abstract String getUserId();

      public abstract boolean hasDisplayName();

      public abstract boolean hasProfilePhotoUrl();

      public abstract boolean hasProfileUrl();

      public abstract boolean hasUserId();
    }

    public static final class PlaceInformation extends GeneratedMessageLite
      implements AnnotationResultProtos.AnnotationResult.PlaceInformationOrBuilder
    {
      public static final int PLACE_REFERENCE_FIELD_NUMBER = 1;
      private static final PlaceInformation defaultInstance = new PlaceInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object placeReference_;

      static
      {
        defaultInstance.initFields();
      }

      private PlaceInformation(Builder paramBuilder)
      {
        super();
      }

      private PlaceInformation(boolean paramBoolean)
      {
      }

      public static PlaceInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getPlaceReferenceBytes()
      {
        Object localObject = this.placeReference_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.placeReference_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.placeReference_ = "";
      }

      public static Builder newBuilder()
      {
        return Builder.access$900();
      }

      public static Builder newBuilder(PlaceInformation paramPlaceInformation)
      {
        return newBuilder().mergeFrom(paramPlaceInformation);
      }

      public static PlaceInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static PlaceInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static PlaceInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static PlaceInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static PlaceInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static PlaceInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static PlaceInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static PlaceInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static PlaceInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static PlaceInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public PlaceInformation getDefaultInstanceForType()
      {
        return defaultInstance;
      }

      public String getPlaceReference()
      {
        Object localObject = this.placeReference_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.placeReference_ = str;
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
          k = 0 + CodedOutputStream.computeBytesSize(1, getPlaceReferenceBytes());
        this.memoizedSerializedSize = k;
        return k;
      }

      public boolean hasPlaceReference()
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
          paramCodedOutputStream.writeBytes(1, getPlaceReferenceBytes());
      }

      public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult.PlaceInformation, Builder>
        implements AnnotationResultProtos.AnnotationResult.PlaceInformationOrBuilder
      {
        private int bitField0_;
        private Object placeReference_ = "";

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private AnnotationResultProtos.AnnotationResult.PlaceInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          AnnotationResultProtos.AnnotationResult.PlaceInformation localPlaceInformation = buildPartial();
          if (!localPlaceInformation.isInitialized())
            throw newUninitializedMessageException(localPlaceInformation).asInvalidProtocolBufferException();
          return localPlaceInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public AnnotationResultProtos.AnnotationResult.PlaceInformation build()
        {
          AnnotationResultProtos.AnnotationResult.PlaceInformation localPlaceInformation = buildPartial();
          if (!localPlaceInformation.isInitialized())
            throw newUninitializedMessageException(localPlaceInformation);
          return localPlaceInformation;
        }

        public AnnotationResultProtos.AnnotationResult.PlaceInformation buildPartial()
        {
          int i = 1;
          AnnotationResultProtos.AnnotationResult.PlaceInformation localPlaceInformation = new AnnotationResultProtos.AnnotationResult.PlaceInformation(this, null);
          if ((0x1 & this.bitField0_) == i);
          while (true)
          {
            AnnotationResultProtos.AnnotationResult.PlaceInformation.access$1102(localPlaceInformation, this.placeReference_);
            AnnotationResultProtos.AnnotationResult.PlaceInformation.access$1202(localPlaceInformation, i);
            return localPlaceInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.placeReference_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          return this;
        }

        public Builder clearPlaceReference()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.placeReference_ = AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance().getPlaceReference();
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public AnnotationResultProtos.AnnotationResult.PlaceInformation getDefaultInstanceForType()
        {
          return AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance();
        }

        public String getPlaceReference()
        {
          Object localObject = this.placeReference_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.placeReference_ = str;
            return str;
          }
          return (String)localObject;
        }

        public boolean hasPlaceReference()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
          return true;
        }

        public Builder mergeFrom(AnnotationResultProtos.AnnotationResult.PlaceInformation paramPlaceInformation)
        {
          if (paramPlaceInformation == AnnotationResultProtos.AnnotationResult.PlaceInformation.getDefaultInstance());
          while (!paramPlaceInformation.hasPlaceReference())
            return this;
          setPlaceReference(paramPlaceInformation.getPlaceReference());
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
            this.placeReference_ = paramCodedInputStream.readBytes();
          }
        }

        public Builder setPlaceReference(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.placeReference_ = paramString;
          return this;
        }

        void setPlaceReference(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.placeReference_ = paramByteString;
        }
      }
    }

    public static abstract interface PlaceInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract String getPlaceReference();

      public abstract boolean hasPlaceReference();
    }

    public static final class TextInformation extends GeneratedMessageLite
      implements AnnotationResultProtos.AnnotationResult.TextInformationOrBuilder
    {
      public static final int TRANSLATED_TITLE_FIELD_NUMBER = 1;
      public static final int WORDS_FIELD_NUMBER = 2;
      private static final TextInformation defaultInstance = new TextInformation(true);
      private static final long serialVersionUID;
      private int bitField0_;
      private byte memoizedIsInitialized = -1;
      private int memoizedSerializedSize = -1;
      private Object translatedTitle_;
      private List<Word> words_;

      static
      {
        defaultInstance.initFields();
      }

      private TextInformation(Builder paramBuilder)
      {
        super();
      }

      private TextInformation(boolean paramBoolean)
      {
      }

      public static TextInformation getDefaultInstance()
      {
        return defaultInstance;
      }

      private ByteString getTranslatedTitleBytes()
      {
        Object localObject = this.translatedTitle_;
        if ((localObject instanceof String))
        {
          ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
          this.translatedTitle_ = localByteString;
          return localByteString;
        }
        return (ByteString)localObject;
      }

      private void initFields()
      {
        this.translatedTitle_ = "";
        this.words_ = Collections.emptyList();
      }

      public static Builder newBuilder()
      {
        return Builder.access$2100();
      }

      public static Builder newBuilder(TextInformation paramTextInformation)
      {
        return newBuilder().mergeFrom(paramTextInformation);
      }

      public static TextInformation parseDelimitedFrom(InputStream paramInputStream)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream))
          return localBuilder.buildParsed();
        return null;
      }

      public static TextInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        Builder localBuilder = newBuilder();
        if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
          return localBuilder.buildParsed();
        return null;
      }

      public static TextInformation parseFrom(ByteString paramByteString)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
      }

      public static TextInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
      }

      public static TextInformation parseFrom(CodedInputStream paramCodedInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
      }

      public static TextInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
      }

      public static TextInformation parseFrom(InputStream paramInputStream)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
      }

      public static TextInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
      }

      public static TextInformation parseFrom(byte[] paramArrayOfByte)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
      }

      public static TextInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
        throws InvalidProtocolBufferException
      {
        return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
      }

      public TextInformation getDefaultInstanceForType()
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
        for (int k = 0 + CodedOutputStream.computeBytesSize(1, getTranslatedTitleBytes()); ; k = 0)
        {
          int n;
          for (int m = k; i < this.words_.size(); m = n)
          {
            n = m + CodedOutputStream.computeMessageSize(2, (MessageLite)this.words_.get(i));
            i++;
          }
          this.memoizedSerializedSize = m;
          return m;
        }
      }

      public String getTranslatedTitle()
      {
        Object localObject = this.translatedTitle_;
        if ((localObject instanceof String))
          return (String)localObject;
        ByteString localByteString = (ByteString)localObject;
        String str = localByteString.toStringUtf8();
        if (Internal.isValidUtf8(localByteString))
          this.translatedTitle_ = str;
        return str;
      }

      public Word getWords(int paramInt)
      {
        return (Word)this.words_.get(paramInt);
      }

      public int getWordsCount()
      {
        return this.words_.size();
      }

      public List<Word> getWordsList()
      {
        return this.words_;
      }

      public WordOrBuilder getWordsOrBuilder(int paramInt)
      {
        return (WordOrBuilder)this.words_.get(paramInt);
      }

      public List<? extends WordOrBuilder> getWordsOrBuilderList()
      {
        return this.words_;
      }

      public boolean hasTranslatedTitle()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        int i = this.memoizedIsInitialized;
        if (i != -1)
          return i == 1;
        for (int j = 0; j < getWordsCount(); j++)
          if (!getWords(j).isInitialized())
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
          paramCodedOutputStream.writeBytes(1, getTranslatedTitleBytes());
        for (int i = 0; i < this.words_.size(); i++)
          paramCodedOutputStream.writeMessage(2, (MessageLite)this.words_.get(i));
      }

      public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult.TextInformation, Builder>
        implements AnnotationResultProtos.AnnotationResult.TextInformationOrBuilder
      {
        private int bitField0_;
        private Object translatedTitle_ = "";
        private List<AnnotationResultProtos.AnnotationResult.TextInformation.Word> words_ = Collections.emptyList();

        private Builder()
        {
          maybeForceBuilderInitialization();
        }

        private AnnotationResultProtos.AnnotationResult.TextInformation buildParsed()
          throws InvalidProtocolBufferException
        {
          AnnotationResultProtos.AnnotationResult.TextInformation localTextInformation = buildPartial();
          if (!localTextInformation.isInitialized())
            throw newUninitializedMessageException(localTextInformation).asInvalidProtocolBufferException();
          return localTextInformation;
        }

        private static Builder create()
        {
          return new Builder();
        }

        private void ensureWordsIsMutable()
        {
          if ((0x2 & this.bitField0_) != 2)
          {
            this.words_ = new ArrayList(this.words_);
            this.bitField0_ = (0x2 | this.bitField0_);
          }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public Builder addAllWords(Iterable<? extends AnnotationResultProtos.AnnotationResult.TextInformation.Word> paramIterable)
        {
          ensureWordsIsMutable();
          GeneratedMessageLite.Builder.addAll(paramIterable, this.words_);
          return this;
        }

        public Builder addWords(int paramInt, AnnotationResultProtos.AnnotationResult.TextInformation.Word.Builder paramBuilder)
        {
          ensureWordsIsMutable();
          this.words_.add(paramInt, paramBuilder.build());
          return this;
        }

        public Builder addWords(int paramInt, AnnotationResultProtos.AnnotationResult.TextInformation.Word paramWord)
        {
          if (paramWord == null)
            throw new NullPointerException();
          ensureWordsIsMutable();
          this.words_.add(paramInt, paramWord);
          return this;
        }

        public Builder addWords(AnnotationResultProtos.AnnotationResult.TextInformation.Word.Builder paramBuilder)
        {
          ensureWordsIsMutable();
          this.words_.add(paramBuilder.build());
          return this;
        }

        public Builder addWords(AnnotationResultProtos.AnnotationResult.TextInformation.Word paramWord)
        {
          if (paramWord == null)
            throw new NullPointerException();
          ensureWordsIsMutable();
          this.words_.add(paramWord);
          return this;
        }

        public AnnotationResultProtos.AnnotationResult.TextInformation build()
        {
          AnnotationResultProtos.AnnotationResult.TextInformation localTextInformation = buildPartial();
          if (!localTextInformation.isInitialized())
            throw newUninitializedMessageException(localTextInformation);
          return localTextInformation;
        }

        public AnnotationResultProtos.AnnotationResult.TextInformation buildPartial()
        {
          int i = 1;
          AnnotationResultProtos.AnnotationResult.TextInformation localTextInformation = new AnnotationResultProtos.AnnotationResult.TextInformation(this, null);
          if ((0x1 & this.bitField0_) == i);
          while (true)
          {
            AnnotationResultProtos.AnnotationResult.TextInformation.access$2302(localTextInformation, this.translatedTitle_);
            if ((0x2 & this.bitField0_) == 2)
            {
              this.words_ = Collections.unmodifiableList(this.words_);
              this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            }
            AnnotationResultProtos.AnnotationResult.TextInformation.access$2402(localTextInformation, this.words_);
            AnnotationResultProtos.AnnotationResult.TextInformation.access$2502(localTextInformation, i);
            return localTextInformation;
            i = 0;
          }
        }

        public Builder clear()
        {
          super.clear();
          this.translatedTitle_ = "";
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.words_ = Collections.emptyList();
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          return this;
        }

        public Builder clearTranslatedTitle()
        {
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          this.translatedTitle_ = AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance().getTranslatedTitle();
          return this;
        }

        public Builder clearWords()
        {
          this.words_ = Collections.emptyList();
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          return this;
        }

        public Builder clone()
        {
          return create().mergeFrom(buildPartial());
        }

        public AnnotationResultProtos.AnnotationResult.TextInformation getDefaultInstanceForType()
        {
          return AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance();
        }

        public String getTranslatedTitle()
        {
          Object localObject = this.translatedTitle_;
          if (!(localObject instanceof String))
          {
            String str = ((ByteString)localObject).toStringUtf8();
            this.translatedTitle_ = str;
            return str;
          }
          return (String)localObject;
        }

        public AnnotationResultProtos.AnnotationResult.TextInformation.Word getWords(int paramInt)
        {
          return (AnnotationResultProtos.AnnotationResult.TextInformation.Word)this.words_.get(paramInt);
        }

        public int getWordsCount()
        {
          return this.words_.size();
        }

        public List<AnnotationResultProtos.AnnotationResult.TextInformation.Word> getWordsList()
        {
          return Collections.unmodifiableList(this.words_);
        }

        public boolean hasTranslatedTitle()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
          for (int i = 0; i < getWordsCount(); i++)
            if (!getWords(i).isInitialized())
              return false;
          return true;
        }

        public Builder mergeFrom(AnnotationResultProtos.AnnotationResult.TextInformation paramTextInformation)
        {
          if (paramTextInformation == AnnotationResultProtos.AnnotationResult.TextInformation.getDefaultInstance());
          do
          {
            return this;
            if (paramTextInformation.hasTranslatedTitle())
              setTranslatedTitle(paramTextInformation.getTranslatedTitle());
          }
          while (paramTextInformation.words_.isEmpty());
          if (this.words_.isEmpty())
          {
            this.words_ = paramTextInformation.words_;
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            return this;
          }
          ensureWordsIsMutable();
          this.words_.addAll(paramTextInformation.words_);
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
              this.translatedTitle_ = paramCodedInputStream.readBytes();
              break;
            case 18:
            }
            AnnotationResultProtos.AnnotationResult.TextInformation.Word.Builder localBuilder = AnnotationResultProtos.AnnotationResult.TextInformation.Word.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addWords(localBuilder.buildPartial());
          }
        }

        public Builder removeWords(int paramInt)
        {
          ensureWordsIsMutable();
          this.words_.remove(paramInt);
          return this;
        }

        public Builder setTranslatedTitle(String paramString)
        {
          if (paramString == null)
            throw new NullPointerException();
          this.bitField0_ = (0x1 | this.bitField0_);
          this.translatedTitle_ = paramString;
          return this;
        }

        void setTranslatedTitle(ByteString paramByteString)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          this.translatedTitle_ = paramByteString;
        }

        public Builder setWords(int paramInt, AnnotationResultProtos.AnnotationResult.TextInformation.Word.Builder paramBuilder)
        {
          ensureWordsIsMutable();
          this.words_.set(paramInt, paramBuilder.build());
          return this;
        }

        public Builder setWords(int paramInt, AnnotationResultProtos.AnnotationResult.TextInformation.Word paramWord)
        {
          if (paramWord == null)
            throw new NullPointerException();
          ensureWordsIsMutable();
          this.words_.set(paramInt, paramWord);
          return this;
        }
      }

      public static final class Word extends GeneratedMessageLite
        implements AnnotationResultProtos.AnnotationResult.TextInformation.WordOrBuilder
      {
        public static final int BOX_FIELD_NUMBER = 1;
        public static final int CHARACTER_BOXES_FIELD_NUMBER = 3;
        public static final int TEXT_FIELD_NUMBER = 2;
        private static final Word defaultInstance = new Word(true);
        private static final long serialVersionUID;
        private int bitField0_;
        private BoundingBoxProtos.BoundingBox box_;
        private List<BoundingBoxProtos.BoundingBox> characterBoxes_;
        private byte memoizedIsInitialized = -1;
        private int memoizedSerializedSize = -1;
        private Object text_;

        static
        {
          defaultInstance.initFields();
        }

        private Word(Builder paramBuilder)
        {
          super();
        }

        private Word(boolean paramBoolean)
        {
        }

        public static Word getDefaultInstance()
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
          this.box_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
          this.text_ = "";
          this.characterBoxes_ = Collections.emptyList();
        }

        public static Builder newBuilder()
        {
          return Builder.access$1400();
        }

        public static Builder newBuilder(Word paramWord)
        {
          return newBuilder().mergeFrom(paramWord);
        }

        public static Word parseDelimitedFrom(InputStream paramInputStream)
          throws IOException
        {
          Builder localBuilder = newBuilder();
          if (localBuilder.mergeDelimitedFrom(paramInputStream))
            return localBuilder.buildParsed();
          return null;
        }

        public static Word parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          Builder localBuilder = newBuilder();
          if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
            return localBuilder.buildParsed();
          return null;
        }

        public static Word parseFrom(ByteString paramByteString)
          throws InvalidProtocolBufferException
        {
          return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
        }

        public static Word parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
          throws InvalidProtocolBufferException
        {
          return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
        }

        public static Word parseFrom(CodedInputStream paramCodedInputStream)
          throws IOException
        {
          return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
        }

        public static Word parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
        }

        public static Word parseFrom(InputStream paramInputStream)
          throws IOException
        {
          return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
        }

        public static Word parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
          throws IOException
        {
          return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
        }

        public static Word parseFrom(byte[] paramArrayOfByte)
          throws InvalidProtocolBufferException
        {
          return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
        }

        public static Word parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
          throws InvalidProtocolBufferException
        {
          return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
        }

        public BoundingBoxProtos.BoundingBox getBox()
        {
          return this.box_;
        }

        public BoundingBoxProtos.BoundingBox getCharacterBoxes(int paramInt)
        {
          return (BoundingBoxProtos.BoundingBox)this.characterBoxes_.get(paramInt);
        }

        public int getCharacterBoxesCount()
        {
          return this.characterBoxes_.size();
        }

        public List<BoundingBoxProtos.BoundingBox> getCharacterBoxesList()
        {
          return this.characterBoxes_;
        }

        public BoundingBoxProtos.BoundingBoxOrBuilder getCharacterBoxesOrBuilder(int paramInt)
        {
          return (BoundingBoxProtos.BoundingBoxOrBuilder)this.characterBoxes_.get(paramInt);
        }

        public List<? extends BoundingBoxProtos.BoundingBoxOrBuilder> getCharacterBoxesOrBuilderList()
        {
          return this.characterBoxes_;
        }

        public Word getDefaultInstanceForType()
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
          for (int k = 0 + CodedOutputStream.computeMessageSize(1, this.box_); ; k = 0)
          {
            if ((0x2 & this.bitField0_) == 2)
              k += CodedOutputStream.computeBytesSize(2, getTextBytes());
            int n;
            for (int m = k; i < this.characterBoxes_.size(); m = n)
            {
              n = m + CodedOutputStream.computeMessageSize(3, (MessageLite)this.characterBoxes_.get(i));
              i++;
            }
            this.memoizedSerializedSize = m;
            return m;
          }
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

        public boolean hasBox()
        {
          return (0x1 & this.bitField0_) == 1;
        }

        public boolean hasText()
        {
          return (0x2 & this.bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
          int i = this.memoizedIsInitialized;
          if (i != -1)
            return i == 1;
          if ((hasBox()) && (!getBox().isInitialized()))
          {
            this.memoizedIsInitialized = 0;
            return false;
          }
          for (int j = 0; j < getCharacterBoxesCount(); j++)
            if (!getCharacterBoxes(j).isInitialized())
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
            paramCodedOutputStream.writeMessage(1, this.box_);
          if ((0x2 & this.bitField0_) == 2)
            paramCodedOutputStream.writeBytes(2, getTextBytes());
          for (int i = 0; i < this.characterBoxes_.size(); i++)
            paramCodedOutputStream.writeMessage(3, (MessageLite)this.characterBoxes_.get(i));
        }

        public static final class Builder extends GeneratedMessageLite.Builder<AnnotationResultProtos.AnnotationResult.TextInformation.Word, Builder>
          implements AnnotationResultProtos.AnnotationResult.TextInformation.WordOrBuilder
        {
          private int bitField0_;
          private BoundingBoxProtos.BoundingBox box_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
          private List<BoundingBoxProtos.BoundingBox> characterBoxes_ = Collections.emptyList();
          private Object text_ = "";

          private Builder()
          {
            maybeForceBuilderInitialization();
          }

          private AnnotationResultProtos.AnnotationResult.TextInformation.Word buildParsed()
            throws InvalidProtocolBufferException
          {
            AnnotationResultProtos.AnnotationResult.TextInformation.Word localWord = buildPartial();
            if (!localWord.isInitialized())
              throw newUninitializedMessageException(localWord).asInvalidProtocolBufferException();
            return localWord;
          }

          private static Builder create()
          {
            return new Builder();
          }

          private void ensureCharacterBoxesIsMutable()
          {
            if ((0x4 & this.bitField0_) != 4)
            {
              this.characterBoxes_ = new ArrayList(this.characterBoxes_);
              this.bitField0_ = (0x4 | this.bitField0_);
            }
          }

          private void maybeForceBuilderInitialization()
          {
          }

          public Builder addAllCharacterBoxes(Iterable<? extends BoundingBoxProtos.BoundingBox> paramIterable)
          {
            ensureCharacterBoxesIsMutable();
            GeneratedMessageLite.Builder.addAll(paramIterable, this.characterBoxes_);
            return this;
          }

          public Builder addCharacterBoxes(int paramInt, BoundingBoxProtos.BoundingBox.Builder paramBuilder)
          {
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.add(paramInt, paramBuilder.build());
            return this;
          }

          public Builder addCharacterBoxes(int paramInt, BoundingBoxProtos.BoundingBox paramBoundingBox)
          {
            if (paramBoundingBox == null)
              throw new NullPointerException();
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.add(paramInt, paramBoundingBox);
            return this;
          }

          public Builder addCharacterBoxes(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
          {
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.add(paramBuilder.build());
            return this;
          }

          public Builder addCharacterBoxes(BoundingBoxProtos.BoundingBox paramBoundingBox)
          {
            if (paramBoundingBox == null)
              throw new NullPointerException();
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.add(paramBoundingBox);
            return this;
          }

          public AnnotationResultProtos.AnnotationResult.TextInformation.Word build()
          {
            AnnotationResultProtos.AnnotationResult.TextInformation.Word localWord = buildPartial();
            if (!localWord.isInitialized())
              throw newUninitializedMessageException(localWord);
            return localWord;
          }

          public AnnotationResultProtos.AnnotationResult.TextInformation.Word buildPartial()
          {
            int i = 1;
            AnnotationResultProtos.AnnotationResult.TextInformation.Word localWord = new AnnotationResultProtos.AnnotationResult.TextInformation.Word(this, null);
            int j = this.bitField0_;
            if ((j & 0x1) == i);
            while (true)
            {
              AnnotationResultProtos.AnnotationResult.TextInformation.Word.access$1602(localWord, this.box_);
              if ((j & 0x2) == 2)
                i |= 2;
              AnnotationResultProtos.AnnotationResult.TextInformation.Word.access$1702(localWord, this.text_);
              if ((0x4 & this.bitField0_) == 4)
              {
                this.characterBoxes_ = Collections.unmodifiableList(this.characterBoxes_);
                this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
              }
              AnnotationResultProtos.AnnotationResult.TextInformation.Word.access$1802(localWord, this.characterBoxes_);
              AnnotationResultProtos.AnnotationResult.TextInformation.Word.access$1902(localWord, i);
              return localWord;
              i = 0;
            }
          }

          public Builder clear()
          {
            super.clear();
            this.box_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
            this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
            this.text_ = "";
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            this.characterBoxes_ = Collections.emptyList();
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
            return this;
          }

          public Builder clearBox()
          {
            this.box_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
            this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
            return this;
          }

          public Builder clearCharacterBoxes()
          {
            this.characterBoxes_ = Collections.emptyList();
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
            return this;
          }

          public Builder clearText()
          {
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            this.text_ = AnnotationResultProtos.AnnotationResult.TextInformation.Word.getDefaultInstance().getText();
            return this;
          }

          public Builder clone()
          {
            return create().mergeFrom(buildPartial());
          }

          public BoundingBoxProtos.BoundingBox getBox()
          {
            return this.box_;
          }

          public BoundingBoxProtos.BoundingBox getCharacterBoxes(int paramInt)
          {
            return (BoundingBoxProtos.BoundingBox)this.characterBoxes_.get(paramInt);
          }

          public int getCharacterBoxesCount()
          {
            return this.characterBoxes_.size();
          }

          public List<BoundingBoxProtos.BoundingBox> getCharacterBoxesList()
          {
            return Collections.unmodifiableList(this.characterBoxes_);
          }

          public AnnotationResultProtos.AnnotationResult.TextInformation.Word getDefaultInstanceForType()
          {
            return AnnotationResultProtos.AnnotationResult.TextInformation.Word.getDefaultInstance();
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

          public boolean hasBox()
          {
            return (0x1 & this.bitField0_) == 1;
          }

          public boolean hasText()
          {
            return (0x2 & this.bitField0_) == 2;
          }

          public final boolean isInitialized()
          {
            if ((hasBox()) && (!getBox().isInitialized()))
              return false;
            for (int i = 0; ; i++)
            {
              if (i >= getCharacterBoxesCount())
                break label46;
              if (!getCharacterBoxes(i).isInitialized())
                break;
            }
            label46: return true;
          }

          public Builder mergeBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
          {
            if (((0x1 & this.bitField0_) == 1) && (this.box_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
            for (this.box_ = BoundingBoxProtos.BoundingBox.newBuilder(this.box_).mergeFrom(paramBoundingBox).buildPartial(); ; this.box_ = paramBoundingBox)
            {
              this.bitField0_ = (0x1 | this.bitField0_);
              return this;
            }
          }

          public Builder mergeFrom(AnnotationResultProtos.AnnotationResult.TextInformation.Word paramWord)
          {
            if (paramWord == AnnotationResultProtos.AnnotationResult.TextInformation.Word.getDefaultInstance());
            do
            {
              return this;
              if (paramWord.hasBox())
                mergeBox(paramWord.getBox());
              if (paramWord.hasText())
                setText(paramWord.getText());
            }
            while (paramWord.characterBoxes_.isEmpty());
            if (this.characterBoxes_.isEmpty())
            {
              this.characterBoxes_ = paramWord.characterBoxes_;
              this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
              return this;
            }
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.addAll(paramWord.characterBoxes_);
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
                BoundingBoxProtos.BoundingBox.Builder localBuilder2 = BoundingBoxProtos.BoundingBox.newBuilder();
                if (hasBox())
                  localBuilder2.mergeFrom(getBox());
                paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
                setBox(localBuilder2.buildPartial());
                break;
              case 18:
                this.bitField0_ = (0x2 | this.bitField0_);
                this.text_ = paramCodedInputStream.readBytes();
                break;
              case 26:
              }
              BoundingBoxProtos.BoundingBox.Builder localBuilder1 = BoundingBoxProtos.BoundingBox.newBuilder();
              paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
              addCharacterBoxes(localBuilder1.buildPartial());
            }
          }

          public Builder removeCharacterBoxes(int paramInt)
          {
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.remove(paramInt);
            return this;
          }

          public Builder setBox(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
          {
            this.box_ = paramBuilder.build();
            this.bitField0_ = (0x1 | this.bitField0_);
            return this;
          }

          public Builder setBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
          {
            if (paramBoundingBox == null)
              throw new NullPointerException();
            this.box_ = paramBoundingBox;
            this.bitField0_ = (0x1 | this.bitField0_);
            return this;
          }

          public Builder setCharacterBoxes(int paramInt, BoundingBoxProtos.BoundingBox.Builder paramBuilder)
          {
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.set(paramInt, paramBuilder.build());
            return this;
          }

          public Builder setCharacterBoxes(int paramInt, BoundingBoxProtos.BoundingBox paramBoundingBox)
          {
            if (paramBoundingBox == null)
              throw new NullPointerException();
            ensureCharacterBoxesIsMutable();
            this.characterBoxes_.set(paramInt, paramBoundingBox);
            return this;
          }

          public Builder setText(String paramString)
          {
            if (paramString == null)
              throw new NullPointerException();
            this.bitField0_ = (0x2 | this.bitField0_);
            this.text_ = paramString;
            return this;
          }

          void setText(ByteString paramByteString)
          {
            this.bitField0_ = (0x2 | this.bitField0_);
            this.text_ = paramByteString;
          }
        }
      }

      public static abstract interface WordOrBuilder extends MessageLiteOrBuilder
      {
        public abstract BoundingBoxProtos.BoundingBox getBox();

        public abstract BoundingBoxProtos.BoundingBox getCharacterBoxes(int paramInt);

        public abstract int getCharacterBoxesCount();

        public abstract List<BoundingBoxProtos.BoundingBox> getCharacterBoxesList();

        public abstract String getText();

        public abstract boolean hasBox();

        public abstract boolean hasText();
      }
    }

    public static abstract interface TextInformationOrBuilder extends MessageLiteOrBuilder
    {
      public abstract String getTranslatedTitle();

      public abstract AnnotationResultProtos.AnnotationResult.TextInformation.Word getWords(int paramInt);

      public abstract int getWordsCount();

      public abstract List<AnnotationResultProtos.AnnotationResult.TextInformation.Word> getWordsList();

      public abstract boolean hasTranslatedTitle();
    }
  }

  public static abstract interface AnnotationResultOrBuilder extends MessageLiteOrBuilder
  {
    public abstract BoundingBoxProtos.BoundingBox getBoundingBox();

    public abstract String getBoundingBoxRequestId();

    public abstract CanonicalImageProtos.CanonicalImage getCanonicalImage();

    public abstract AnnotationResultProtos.AnnotationResult.ContributionInformation getContributionInfo();

    public abstract String getDirectUrl();

    public abstract FactsProtos.Facts getFacts();

    public abstract boolean getIsAd();

    public abstract boolean getIsSimilarProduct();

    public abstract boolean getIsWithdrawn();

    public abstract String getLanguage();

    public abstract LatLngProtos.LatLng getLatLng();

    public abstract String getLocationText();

    public abstract MoreLikeThisActionProtos.MoreLikeThisAction getMoreLikeThisAction();

    public abstract MusicInformationProtos.MusicInformation getMusicInfo();

    public abstract AnnotationResultProtos.AnnotationResult.PersonInformation getPersonInfo();

    public abstract AnnotationResultProtos.AnnotationResult.PlaceInformation getPlaceInfo();

    public abstract ProductInformationProtos.ProductInformation getProductInfo();

    public abstract String getResultId();

    public abstract String getResultUrl();

    public abstract ShareActionProtos.ShareAction getShareAction();

    public abstract String getSubtitle();

    public abstract String getSubtype();

    public abstract AnnotationResultProtos.AnnotationResult.TextInformation getTextInfo();

    public abstract String getTitle();

    public abstract String getTtsDescription();

    public abstract String getType();

    public abstract UrlGroupProtos.UrlGroup getUrlGroups(int paramInt);

    public abstract int getUrlGroupsCount();

    public abstract List<UrlGroupProtos.UrlGroup> getUrlGroupsList();

    @Deprecated
    public abstract String getUrls(int paramInt);

    @Deprecated
    public abstract int getUrlsCount();

    @Deprecated
    public abstract List<String> getUrlsList();

    public abstract String getWebsearchUrl();

    public abstract boolean hasBoundingBox();

    public abstract boolean hasBoundingBoxRequestId();

    public abstract boolean hasCanonicalImage();

    public abstract boolean hasContributionInfo();

    public abstract boolean hasDirectUrl();

    public abstract boolean hasFacts();

    public abstract boolean hasIsAd();

    public abstract boolean hasIsSimilarProduct();

    public abstract boolean hasIsWithdrawn();

    public abstract boolean hasLanguage();

    public abstract boolean hasLatLng();

    public abstract boolean hasLocationText();

    public abstract boolean hasMoreLikeThisAction();

    public abstract boolean hasMusicInfo();

    public abstract boolean hasPersonInfo();

    public abstract boolean hasPlaceInfo();

    public abstract boolean hasProductInfo();

    public abstract boolean hasResultId();

    public abstract boolean hasResultUrl();

    public abstract boolean hasShareAction();

    public abstract boolean hasSubtitle();

    public abstract boolean hasSubtype();

    public abstract boolean hasTextInfo();

    public abstract boolean hasTitle();

    public abstract boolean hasTtsDescription();

    public abstract boolean hasType();

    public abstract boolean hasWebsearchUrl();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.AnnotationResultProtos
 * JD-Core Version:    0.6.2
 */