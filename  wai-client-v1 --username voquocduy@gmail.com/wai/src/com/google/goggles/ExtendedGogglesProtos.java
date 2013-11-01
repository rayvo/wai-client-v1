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
import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import com.google.protobuf.WireFormat.FieldType;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ExtendedGogglesProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
    paramExtensionRegistryLite.add(ExtendedGogglesRequest.extendedGogglesRequest);
    paramExtensionRegistryLite.add(ExtendedGogglesResponse.extendedGogglesResponse);
  }

  public static final class ExtendedGogglesRequest extends GeneratedMessageLite
    implements ExtendedGogglesProtos.ExtendedGogglesRequestOrBuilder
  {
    public static final int CLIENT_ANNOTATIONS_FIELD_NUMBER = 5;
    public static final int DEPRECATED_FIELD1_FIELD_NUMBER = 8;
    public static final int DEVICE_INFO_FIELD_NUMBER = 9;
    public static final int ENCRYPTED_LAT_LNG_FIELD_NUMBER = 1;
    public static final int EXTENDED_GOGGLES_REQUEST_FIELD_NUMBER = 15705794;
    public static final int MS_SINCE_EPOCH_FIELD_NUMBER = 7;
    public static final int NETWORK_INFO_FIELD_NUMBER = 10;
    public static final int REQUEST_ID_FIELD_NUMBER = 12;
    public static final int RETURN_RESULTS_FIELD_NUMBER = 13;
    public static final int SEQUENCE_NUMBER_FIELD_NUMBER = 15;
    public static final int SESSION_ID_FIELD_NUMBER = 11;
    public static final int SOURCE_LANGUAGE_FIELD_NUMBER = 16;
    public static final int TRACKED_RESULTS_FIELD_NUMBER = 14;
    private static final ExtendedGogglesRequest defaultInstance = new ExtendedGogglesRequest(true);
    public static final GeneratedMessageLite.GeneratedExtension<GogglesProtos.GogglesRequest, ExtendedGogglesRequest> extendedGogglesRequest = GeneratedMessageLite.newSingularGeneratedExtension(GogglesProtos.GogglesRequest.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 15705794, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private int bitField0_;
    private List<ClientAnnotationProtos.ClientAnnotation> clientAnnotations_;
    private long deprecatedField1_;
    private DeviceInfoProtos.DeviceInfo deviceInfo_;
    private ByteString encryptedLatLng_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private long msSinceEpoch_;
    private NetworkInfoProtos.NetworkInfo networkInfo_;
    private Object requestId_;
    private boolean returnResults_;
    private int sequenceNumber_;
    private Object sessionId_;
    private Object sourceLanguage_;
    private List<ExtendedGogglesProtos.TrackedResult> trackedResults_;

    static
    {
      defaultInstance.initFields();
    }

    private ExtendedGogglesRequest(Builder paramBuilder)
    {
      super();
    }

    private ExtendedGogglesRequest(boolean paramBoolean)
    {
    }

    public static ExtendedGogglesRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getRequestIdBytes()
    {
      Object localObject = this.requestId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.requestId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getSessionIdBytes()
    {
      Object localObject = this.sessionId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.sessionId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getSourceLanguageBytes()
    {
      Object localObject = this.sourceLanguage_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.sourceLanguage_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.encryptedLatLng_ = ByteString.EMPTY;
      this.clientAnnotations_ = Collections.emptyList();
      this.msSinceEpoch_ = 0L;
      this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
      this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
      this.sourceLanguage_ = "";
      this.sessionId_ = "";
      this.requestId_ = "";
      this.sequenceNumber_ = 0;
      this.returnResults_ = false;
      this.trackedResults_ = Collections.emptyList();
      this.deprecatedField1_ = 0L;
    }

    public static Builder newBuilder()
    {
      return Builder.access$700();
    }

    public static Builder newBuilder(ExtendedGogglesRequest paramExtendedGogglesRequest)
    {
      return newBuilder().mergeFrom(paramExtendedGogglesRequest);
    }

    public static ExtendedGogglesRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ExtendedGogglesRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ClientAnnotationProtos.ClientAnnotation getClientAnnotations(int paramInt)
    {
      return (ClientAnnotationProtos.ClientAnnotation)this.clientAnnotations_.get(paramInt);
    }

    public int getClientAnnotationsCount()
    {
      return this.clientAnnotations_.size();
    }

    public List<ClientAnnotationProtos.ClientAnnotation> getClientAnnotationsList()
    {
      return this.clientAnnotations_;
    }

    public ClientAnnotationProtos.ClientAnnotationOrBuilder getClientAnnotationsOrBuilder(int paramInt)
    {
      return (ClientAnnotationProtos.ClientAnnotationOrBuilder)this.clientAnnotations_.get(paramInt);
    }

    public List<? extends ClientAnnotationProtos.ClientAnnotationOrBuilder> getClientAnnotationsOrBuilderList()
    {
      return this.clientAnnotations_;
    }

    public ExtendedGogglesRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
    public long getDeprecatedField1()
    {
      return this.deprecatedField1_;
    }

    public DeviceInfoProtos.DeviceInfo getDeviceInfo()
    {
      return this.deviceInfo_;
    }

    public ByteString getEncryptedLatLng()
    {
      return this.encryptedLatLng_;
    }

    public long getMsSinceEpoch()
    {
      return this.msSinceEpoch_;
    }

    public NetworkInfoProtos.NetworkInfo getNetworkInfo()
    {
      return this.networkInfo_;
    }

    public String getRequestId()
    {
      Object localObject = this.requestId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.requestId_ = str;
      return str;
    }

    public boolean getReturnResults()
    {
      return this.returnResults_;
    }

    public int getSequenceNumber()
    {
      return this.sequenceNumber_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      if ((0x1 & this.bitField0_) == 1);
      for (int j = 0 + CodedOutputStream.computeBytesSize(1, this.encryptedLatLng_); ; j = 0)
      {
        int k = 0;
        int m = j;
        while (k < this.clientAnnotations_.size())
        {
          m += CodedOutputStream.computeMessageSize(5, (MessageLite)this.clientAnnotations_.get(k));
          k++;
        }
        if ((0x2 & this.bitField0_) == 2)
          m += CodedOutputStream.computeInt64Size(7, this.msSinceEpoch_);
        if ((0x200 & this.bitField0_) == 512)
          m += CodedOutputStream.computeUInt64Size(8, this.deprecatedField1_);
        if ((0x4 & this.bitField0_) == 4)
          m += CodedOutputStream.computeMessageSize(9, this.deviceInfo_);
        if ((0x8 & this.bitField0_) == 8)
          m += CodedOutputStream.computeMessageSize(10, this.networkInfo_);
        if ((0x20 & this.bitField0_) == 32)
          m += CodedOutputStream.computeBytesSize(11, getSessionIdBytes());
        if ((0x40 & this.bitField0_) == 64)
          m += CodedOutputStream.computeBytesSize(12, getRequestIdBytes());
        int n = 0x100 & this.bitField0_;
        int i1 = 0;
        if (n == 256)
          m += CodedOutputStream.computeBoolSize(13, this.returnResults_);
        while (i1 < this.trackedResults_.size())
        {
          m += CodedOutputStream.computeMessageSize(14, (MessageLite)this.trackedResults_.get(i1));
          i1++;
        }
        if ((0x80 & this.bitField0_) == 128)
          m += CodedOutputStream.computeInt32Size(15, this.sequenceNumber_);
        if ((0x10 & this.bitField0_) == 16)
          m += CodedOutputStream.computeBytesSize(16, getSourceLanguageBytes());
        this.memoizedSerializedSize = m;
        return m;
      }
    }

    public String getSessionId()
    {
      Object localObject = this.sessionId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.sessionId_ = str;
      return str;
    }

    public String getSourceLanguage()
    {
      Object localObject = this.sourceLanguage_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.sourceLanguage_ = str;
      return str;
    }

    public ExtendedGogglesProtos.TrackedResult getTrackedResults(int paramInt)
    {
      return (ExtendedGogglesProtos.TrackedResult)this.trackedResults_.get(paramInt);
    }

    public int getTrackedResultsCount()
    {
      return this.trackedResults_.size();
    }

    public List<ExtendedGogglesProtos.TrackedResult> getTrackedResultsList()
    {
      return this.trackedResults_;
    }

    public ExtendedGogglesProtos.TrackedResultOrBuilder getTrackedResultsOrBuilder(int paramInt)
    {
      return (ExtendedGogglesProtos.TrackedResultOrBuilder)this.trackedResults_.get(paramInt);
    }

    public List<? extends ExtendedGogglesProtos.TrackedResultOrBuilder> getTrackedResultsOrBuilderList()
    {
      return this.trackedResults_;
    }

    @Deprecated
    public boolean hasDeprecatedField1()
    {
      return (0x200 & this.bitField0_) == 512;
    }

    public boolean hasDeviceInfo()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasEncryptedLatLng()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasMsSinceEpoch()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasNetworkInfo()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasRequestId()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasReturnResults()
    {
      return (0x100 & this.bitField0_) == 256;
    }

    public boolean hasSequenceNumber()
    {
      return (0x80 & this.bitField0_) == 128;
    }

    public boolean hasSessionId()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasSourceLanguage()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getClientAnnotationsCount(); j++)
        if (!getClientAnnotations(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (int k = 0; k < getTrackedResultsCount(); k++)
        if (!getTrackedResults(k).isInitialized())
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
        paramCodedOutputStream.writeBytes(1, this.encryptedLatLng_);
      for (int i = 0; i < this.clientAnnotations_.size(); i++)
        paramCodedOutputStream.writeMessage(5, (MessageLite)this.clientAnnotations_.get(i));
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt64(7, this.msSinceEpoch_);
      if ((0x200 & this.bitField0_) == 512)
        paramCodedOutputStream.writeUInt64(8, this.deprecatedField1_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(9, this.deviceInfo_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(10, this.networkInfo_);
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(11, getSessionIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(12, getRequestIdBytes());
      int j = 0x100 & this.bitField0_;
      int k = 0;
      if (j == 256)
        paramCodedOutputStream.writeBool(13, this.returnResults_);
      while (k < this.trackedResults_.size())
      {
        paramCodedOutputStream.writeMessage(14, (MessageLite)this.trackedResults_.get(k));
        k++;
      }
      if ((0x80 & this.bitField0_) == 128)
        paramCodedOutputStream.writeInt32(15, this.sequenceNumber_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(16, getSourceLanguageBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExtendedGogglesProtos.ExtendedGogglesRequest, Builder>
      implements ExtendedGogglesProtos.ExtendedGogglesRequestOrBuilder
    {
      private int bitField0_;
      private List<ClientAnnotationProtos.ClientAnnotation> clientAnnotations_ = Collections.emptyList();
      private long deprecatedField1_;
      private DeviceInfoProtos.DeviceInfo deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
      private ByteString encryptedLatLng_ = ByteString.EMPTY;
      private long msSinceEpoch_;
      private NetworkInfoProtos.NetworkInfo networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
      private Object requestId_ = "";
      private boolean returnResults_;
      private int sequenceNumber_;
      private Object sessionId_ = "";
      private Object sourceLanguage_ = "";
      private List<ExtendedGogglesProtos.TrackedResult> trackedResults_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ExtendedGogglesProtos.ExtendedGogglesRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        ExtendedGogglesProtos.ExtendedGogglesRequest localExtendedGogglesRequest = buildPartial();
        if (!localExtendedGogglesRequest.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesRequest).asInvalidProtocolBufferException();
        return localExtendedGogglesRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureClientAnnotationsIsMutable()
      {
        if ((0x2 & this.bitField0_) != 2)
        {
          this.clientAnnotations_ = new ArrayList(this.clientAnnotations_);
          this.bitField0_ = (0x2 | this.bitField0_);
        }
      }

      private void ensureTrackedResultsIsMutable()
      {
        if ((0x400 & this.bitField0_) != 1024)
        {
          this.trackedResults_ = new ArrayList(this.trackedResults_);
          this.bitField0_ = (0x400 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllClientAnnotations(Iterable<? extends ClientAnnotationProtos.ClientAnnotation> paramIterable)
      {
        ensureClientAnnotationsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.clientAnnotations_);
        return this;
      }

      public Builder addAllTrackedResults(Iterable<? extends ExtendedGogglesProtos.TrackedResult> paramIterable)
      {
        ensureTrackedResultsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.trackedResults_);
        return this;
      }

      public Builder addClientAnnotations(int paramInt, ClientAnnotationProtos.ClientAnnotation.Builder paramBuilder)
      {
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addClientAnnotations(int paramInt, ClientAnnotationProtos.ClientAnnotation paramClientAnnotation)
      {
        if (paramClientAnnotation == null)
          throw new NullPointerException();
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.add(paramInt, paramClientAnnotation);
        return this;
      }

      public Builder addClientAnnotations(ClientAnnotationProtos.ClientAnnotation.Builder paramBuilder)
      {
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.add(paramBuilder.build());
        return this;
      }

      public Builder addClientAnnotations(ClientAnnotationProtos.ClientAnnotation paramClientAnnotation)
      {
        if (paramClientAnnotation == null)
          throw new NullPointerException();
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.add(paramClientAnnotation);
        return this;
      }

      public Builder addTrackedResults(int paramInt, ExtendedGogglesProtos.TrackedResult.Builder paramBuilder)
      {
        ensureTrackedResultsIsMutable();
        this.trackedResults_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addTrackedResults(int paramInt, ExtendedGogglesProtos.TrackedResult paramTrackedResult)
      {
        if (paramTrackedResult == null)
          throw new NullPointerException();
        ensureTrackedResultsIsMutable();
        this.trackedResults_.add(paramInt, paramTrackedResult);
        return this;
      }

      public Builder addTrackedResults(ExtendedGogglesProtos.TrackedResult.Builder paramBuilder)
      {
        ensureTrackedResultsIsMutable();
        this.trackedResults_.add(paramBuilder.build());
        return this;
      }

      public Builder addTrackedResults(ExtendedGogglesProtos.TrackedResult paramTrackedResult)
      {
        if (paramTrackedResult == null)
          throw new NullPointerException();
        ensureTrackedResultsIsMutable();
        this.trackedResults_.add(paramTrackedResult);
        return this;
      }

      public ExtendedGogglesProtos.ExtendedGogglesRequest build()
      {
        ExtendedGogglesProtos.ExtendedGogglesRequest localExtendedGogglesRequest = buildPartial();
        if (!localExtendedGogglesRequest.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesRequest);
        return localExtendedGogglesRequest;
      }

      public ExtendedGogglesProtos.ExtendedGogglesRequest buildPartial()
      {
        int i = 1;
        ExtendedGogglesProtos.ExtendedGogglesRequest localExtendedGogglesRequest = new ExtendedGogglesProtos.ExtendedGogglesRequest(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$902(localExtendedGogglesRequest, this.encryptedLatLng_);
          if ((0x2 & this.bitField0_) == 2)
          {
            this.clientAnnotations_ = Collections.unmodifiableList(this.clientAnnotations_);
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          }
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1002(localExtendedGogglesRequest, this.clientAnnotations_);
          if ((j & 0x4) == 4)
            i |= 2;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1102(localExtendedGogglesRequest, this.msSinceEpoch_);
          if ((j & 0x8) == 8)
            i |= 4;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1202(localExtendedGogglesRequest, this.deviceInfo_);
          if ((j & 0x10) == 16)
            i |= 8;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1302(localExtendedGogglesRequest, this.networkInfo_);
          if ((j & 0x20) == 32)
            i |= 16;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1402(localExtendedGogglesRequest, this.sourceLanguage_);
          if ((j & 0x40) == 64)
            i |= 32;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1502(localExtendedGogglesRequest, this.sessionId_);
          if ((j & 0x80) == 128)
            i |= 64;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1602(localExtendedGogglesRequest, this.requestId_);
          if ((j & 0x100) == 256)
            i |= 128;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1702(localExtendedGogglesRequest, this.sequenceNumber_);
          if ((j & 0x200) == 512)
            i |= 256;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1802(localExtendedGogglesRequest, this.returnResults_);
          if ((0x400 & this.bitField0_) == 1024)
          {
            this.trackedResults_ = Collections.unmodifiableList(this.trackedResults_);
            this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
          }
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$1902(localExtendedGogglesRequest, this.trackedResults_);
          if ((j & 0x800) == 2048)
            i |= 512;
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$2002(localExtendedGogglesRequest, this.deprecatedField1_);
          ExtendedGogglesProtos.ExtendedGogglesRequest.access$2102(localExtendedGogglesRequest, i);
          return localExtendedGogglesRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.encryptedLatLng_ = ByteString.EMPTY;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.clientAnnotations_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.msSinceEpoch_ = 0L;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.sourceLanguage_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.sessionId_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.requestId_ = "";
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.sequenceNumber_ = 0;
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.returnResults_ = false;
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.trackedResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        this.deprecatedField1_ = 0L;
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        return this;
      }

      public Builder clearClientAnnotations()
      {
        this.clientAnnotations_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearDeprecatedField1()
      {
        this.bitField0_ = (0xFFFFF7FF & this.bitField0_);
        this.deprecatedField1_ = 0L;
        return this;
      }

      public Builder clearDeviceInfo()
      {
        this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearEncryptedLatLng()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.encryptedLatLng_ = ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance().getEncryptedLatLng();
        return this;
      }

      public Builder clearMsSinceEpoch()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.msSinceEpoch_ = 0L;
        return this;
      }

      public Builder clearNetworkInfo()
      {
        this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      public Builder clearRequestId()
      {
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.requestId_ = ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance().getRequestId();
        return this;
      }

      public Builder clearReturnResults()
      {
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.returnResults_ = false;
        return this;
      }

      public Builder clearSequenceNumber()
      {
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.sequenceNumber_ = 0;
        return this;
      }

      public Builder clearSessionId()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.sessionId_ = ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance().getSessionId();
        return this;
      }

      public Builder clearSourceLanguage()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.sourceLanguage_ = ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance().getSourceLanguage();
        return this;
      }

      public Builder clearTrackedResults()
      {
        this.trackedResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ClientAnnotationProtos.ClientAnnotation getClientAnnotations(int paramInt)
      {
        return (ClientAnnotationProtos.ClientAnnotation)this.clientAnnotations_.get(paramInt);
      }

      public int getClientAnnotationsCount()
      {
        return this.clientAnnotations_.size();
      }

      public List<ClientAnnotationProtos.ClientAnnotation> getClientAnnotationsList()
      {
        return Collections.unmodifiableList(this.clientAnnotations_);
      }

      public ExtendedGogglesProtos.ExtendedGogglesRequest getDefaultInstanceForType()
      {
        return ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance();
      }

      @Deprecated
      public long getDeprecatedField1()
      {
        return this.deprecatedField1_;
      }

      public DeviceInfoProtos.DeviceInfo getDeviceInfo()
      {
        return this.deviceInfo_;
      }

      public ByteString getEncryptedLatLng()
      {
        return this.encryptedLatLng_;
      }

      public long getMsSinceEpoch()
      {
        return this.msSinceEpoch_;
      }

      public NetworkInfoProtos.NetworkInfo getNetworkInfo()
      {
        return this.networkInfo_;
      }

      public String getRequestId()
      {
        Object localObject = this.requestId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.requestId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean getReturnResults()
      {
        return this.returnResults_;
      }

      public int getSequenceNumber()
      {
        return this.sequenceNumber_;
      }

      public String getSessionId()
      {
        Object localObject = this.sessionId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.sessionId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getSourceLanguage()
      {
        Object localObject = this.sourceLanguage_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.sourceLanguage_ = str;
          return str;
        }
        return (String)localObject;
      }

      public ExtendedGogglesProtos.TrackedResult getTrackedResults(int paramInt)
      {
        return (ExtendedGogglesProtos.TrackedResult)this.trackedResults_.get(paramInt);
      }

      public int getTrackedResultsCount()
      {
        return this.trackedResults_.size();
      }

      public List<ExtendedGogglesProtos.TrackedResult> getTrackedResultsList()
      {
        return Collections.unmodifiableList(this.trackedResults_);
      }

      @Deprecated
      public boolean hasDeprecatedField1()
      {
        return (0x800 & this.bitField0_) == 2048;
      }

      public boolean hasDeviceInfo()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasEncryptedLatLng()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasMsSinceEpoch()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasNetworkInfo()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasRequestId()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasReturnResults()
      {
        return (0x200 & this.bitField0_) == 512;
      }

      public boolean hasSequenceNumber()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      public boolean hasSessionId()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasSourceLanguage()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getClientAnnotationsCount(); i++)
          if (!getClientAnnotations(i).isInitialized())
            return false;
        for (int j = 0; ; j++)
        {
          if (j >= getTrackedResultsCount())
            break label56;
          if (!getTrackedResults(j).isInitialized())
            break;
        }
        label56: return true;
      }

      public Builder mergeDeviceInfo(DeviceInfoProtos.DeviceInfo paramDeviceInfo)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.deviceInfo_ != DeviceInfoProtos.DeviceInfo.getDefaultInstance()));
        for (this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.newBuilder(this.deviceInfo_).mergeFrom(paramDeviceInfo).buildPartial(); ; this.deviceInfo_ = paramDeviceInfo)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(ExtendedGogglesProtos.ExtendedGogglesRequest paramExtendedGogglesRequest)
      {
        if (paramExtendedGogglesRequest == ExtendedGogglesProtos.ExtendedGogglesRequest.getDefaultInstance());
        label279: label298: 
        while (true)
        {
          return this;
          if (paramExtendedGogglesRequest.hasEncryptedLatLng())
            setEncryptedLatLng(paramExtendedGogglesRequest.getEncryptedLatLng());
          if (!paramExtendedGogglesRequest.clientAnnotations_.isEmpty())
          {
            if (this.clientAnnotations_.isEmpty())
            {
              this.clientAnnotations_ = paramExtendedGogglesRequest.clientAnnotations_;
              this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            }
          }
          else
          {
            if (paramExtendedGogglesRequest.hasMsSinceEpoch())
              setMsSinceEpoch(paramExtendedGogglesRequest.getMsSinceEpoch());
            if (paramExtendedGogglesRequest.hasDeviceInfo())
              mergeDeviceInfo(paramExtendedGogglesRequest.getDeviceInfo());
            if (paramExtendedGogglesRequest.hasNetworkInfo())
              mergeNetworkInfo(paramExtendedGogglesRequest.getNetworkInfo());
            if (paramExtendedGogglesRequest.hasSourceLanguage())
              setSourceLanguage(paramExtendedGogglesRequest.getSourceLanguage());
            if (paramExtendedGogglesRequest.hasSessionId())
              setSessionId(paramExtendedGogglesRequest.getSessionId());
            if (paramExtendedGogglesRequest.hasRequestId())
              setRequestId(paramExtendedGogglesRequest.getRequestId());
            if (paramExtendedGogglesRequest.hasSequenceNumber())
              setSequenceNumber(paramExtendedGogglesRequest.getSequenceNumber());
            if (paramExtendedGogglesRequest.hasReturnResults())
              setReturnResults(paramExtendedGogglesRequest.getReturnResults());
            if (!paramExtendedGogglesRequest.trackedResults_.isEmpty())
            {
              if (!this.trackedResults_.isEmpty())
                break label279;
              this.trackedResults_ = paramExtendedGogglesRequest.trackedResults_;
              this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
            }
          }
          while (true)
          {
            if (!paramExtendedGogglesRequest.hasDeprecatedField1())
              break label298;
            setDeprecatedField1(paramExtendedGogglesRequest.getDeprecatedField1());
            return this;
            ensureClientAnnotationsIsMutable();
            this.clientAnnotations_.addAll(paramExtendedGogglesRequest.clientAnnotations_);
            break;
            ensureTrackedResultsIsMutable();
            this.trackedResults_.addAll(paramExtendedGogglesRequest.trackedResults_);
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
            this.bitField0_ = (0x1 | this.bitField0_);
            this.encryptedLatLng_ = paramCodedInputStream.readBytes();
            break;
          case 42:
            ClientAnnotationProtos.ClientAnnotation.Builder localBuilder3 = ClientAnnotationProtos.ClientAnnotation.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addClientAnnotations(localBuilder3.buildPartial());
            break;
          case 56:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.msSinceEpoch_ = paramCodedInputStream.readInt64();
            break;
          case 64:
            this.bitField0_ = (0x800 | this.bitField0_);
            this.deprecatedField1_ = paramCodedInputStream.readUInt64();
            break;
          case 74:
            DeviceInfoProtos.DeviceInfo.Builder localBuilder2 = DeviceInfoProtos.DeviceInfo.newBuilder();
            if (hasDeviceInfo())
              localBuilder2.mergeFrom(getDeviceInfo());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setDeviceInfo(localBuilder2.buildPartial());
            break;
          case 82:
            NetworkInfoProtos.NetworkInfo.Builder localBuilder1 = NetworkInfoProtos.NetworkInfo.newBuilder();
            if (hasNetworkInfo())
              localBuilder1.mergeFrom(getNetworkInfo());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setNetworkInfo(localBuilder1.buildPartial());
            break;
          case 90:
            this.bitField0_ = (0x40 | this.bitField0_);
            this.sessionId_ = paramCodedInputStream.readBytes();
            break;
          case 98:
            this.bitField0_ = (0x80 | this.bitField0_);
            this.requestId_ = paramCodedInputStream.readBytes();
            break;
          case 104:
            this.bitField0_ = (0x200 | this.bitField0_);
            this.returnResults_ = paramCodedInputStream.readBool();
            break;
          case 114:
            ExtendedGogglesProtos.TrackedResult.Builder localBuilder = ExtendedGogglesProtos.TrackedResult.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addTrackedResults(localBuilder.buildPartial());
            break;
          case 120:
            this.bitField0_ = (0x100 | this.bitField0_);
            this.sequenceNumber_ = paramCodedInputStream.readInt32();
            break;
          case 130:
          }
          this.bitField0_ = (0x20 | this.bitField0_);
          this.sourceLanguage_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder mergeNetworkInfo(NetworkInfoProtos.NetworkInfo paramNetworkInfo)
      {
        if (((0x10 & this.bitField0_) == 16) && (this.networkInfo_ != NetworkInfoProtos.NetworkInfo.getDefaultInstance()));
        for (this.networkInfo_ = NetworkInfoProtos.NetworkInfo.newBuilder(this.networkInfo_).mergeFrom(paramNetworkInfo).buildPartial(); ; this.networkInfo_ = paramNetworkInfo)
        {
          this.bitField0_ = (0x10 | this.bitField0_);
          return this;
        }
      }

      public Builder removeClientAnnotations(int paramInt)
      {
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.remove(paramInt);
        return this;
      }

      public Builder removeTrackedResults(int paramInt)
      {
        ensureTrackedResultsIsMutable();
        this.trackedResults_.remove(paramInt);
        return this;
      }

      public Builder setClientAnnotations(int paramInt, ClientAnnotationProtos.ClientAnnotation.Builder paramBuilder)
      {
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setClientAnnotations(int paramInt, ClientAnnotationProtos.ClientAnnotation paramClientAnnotation)
      {
        if (paramClientAnnotation == null)
          throw new NullPointerException();
        ensureClientAnnotationsIsMutable();
        this.clientAnnotations_.set(paramInt, paramClientAnnotation);
        return this;
      }

      @Deprecated
      public Builder setDeprecatedField1(long paramLong)
      {
        this.bitField0_ = (0x800 | this.bitField0_);
        this.deprecatedField1_ = paramLong;
        return this;
      }

      public Builder setDeviceInfo(DeviceInfoProtos.DeviceInfo.Builder paramBuilder)
      {
        this.deviceInfo_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setDeviceInfo(DeviceInfoProtos.DeviceInfo paramDeviceInfo)
      {
        if (paramDeviceInfo == null)
          throw new NullPointerException();
        this.deviceInfo_ = paramDeviceInfo;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      public Builder setEncryptedLatLng(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.encryptedLatLng_ = paramByteString;
        return this;
      }

      public Builder setMsSinceEpoch(long paramLong)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.msSinceEpoch_ = paramLong;
        return this;
      }

      public Builder setNetworkInfo(NetworkInfoProtos.NetworkInfo.Builder paramBuilder)
      {
        this.networkInfo_ = paramBuilder.build();
        this.bitField0_ = (0x10 | this.bitField0_);
        return this;
      }

      public Builder setNetworkInfo(NetworkInfoProtos.NetworkInfo paramNetworkInfo)
      {
        if (paramNetworkInfo == null)
          throw new NullPointerException();
        this.networkInfo_ = paramNetworkInfo;
        this.bitField0_ = (0x10 | this.bitField0_);
        return this;
      }

      public Builder setRequestId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x80 | this.bitField0_);
        this.requestId_ = paramString;
        return this;
      }

      void setRequestId(ByteString paramByteString)
      {
        this.bitField0_ = (0x80 | this.bitField0_);
        this.requestId_ = paramByteString;
      }

      public Builder setReturnResults(boolean paramBoolean)
      {
        this.bitField0_ = (0x200 | this.bitField0_);
        this.returnResults_ = paramBoolean;
        return this;
      }

      public Builder setSequenceNumber(int paramInt)
      {
        this.bitField0_ = (0x100 | this.bitField0_);
        this.sequenceNumber_ = paramInt;
        return this;
      }

      public Builder setSessionId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.sessionId_ = paramString;
        return this;
      }

      void setSessionId(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.sessionId_ = paramByteString;
      }

      public Builder setSourceLanguage(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.sourceLanguage_ = paramString;
        return this;
      }

      void setSourceLanguage(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.sourceLanguage_ = paramByteString;
      }

      public Builder setTrackedResults(int paramInt, ExtendedGogglesProtos.TrackedResult.Builder paramBuilder)
      {
        ensureTrackedResultsIsMutable();
        this.trackedResults_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setTrackedResults(int paramInt, ExtendedGogglesProtos.TrackedResult paramTrackedResult)
      {
        if (paramTrackedResult == null)
          throw new NullPointerException();
        ensureTrackedResultsIsMutable();
        this.trackedResults_.set(paramInt, paramTrackedResult);
        return this;
      }
    }
  }

  public static abstract interface ExtendedGogglesRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ClientAnnotationProtos.ClientAnnotation getClientAnnotations(int paramInt);

    public abstract int getClientAnnotationsCount();

    public abstract List<ClientAnnotationProtos.ClientAnnotation> getClientAnnotationsList();

    @Deprecated
    public abstract long getDeprecatedField1();

    public abstract DeviceInfoProtos.DeviceInfo getDeviceInfo();

    public abstract ByteString getEncryptedLatLng();

    public abstract long getMsSinceEpoch();

    public abstract NetworkInfoProtos.NetworkInfo getNetworkInfo();

    public abstract String getRequestId();

    public abstract boolean getReturnResults();

    public abstract int getSequenceNumber();

    public abstract String getSessionId();

    public abstract String getSourceLanguage();

    public abstract ExtendedGogglesProtos.TrackedResult getTrackedResults(int paramInt);

    public abstract int getTrackedResultsCount();

    public abstract List<ExtendedGogglesProtos.TrackedResult> getTrackedResultsList();

    @Deprecated
    public abstract boolean hasDeprecatedField1();

    public abstract boolean hasDeviceInfo();

    public abstract boolean hasEncryptedLatLng();

    public abstract boolean hasMsSinceEpoch();

    public abstract boolean hasNetworkInfo();

    public abstract boolean hasRequestId();

    public abstract boolean hasReturnResults();

    public abstract boolean hasSequenceNumber();

    public abstract boolean hasSessionId();

    public abstract boolean hasSourceLanguage();
  }

  public static final class ExtendedGogglesResponse extends GeneratedMessageLite
    implements ExtendedGogglesProtos.ExtendedGogglesResponseOrBuilder
  {
    public static final int EXTENDED_GOGGLES_RESPONSE_FIELD_NUMBER = 15705729;
    public static final int EYE_CANDY_RESULTS_FIELD_NUMBER = 1;
    public static final int GOGGLES_ID_FIELD_NUMBER = 2;
    public static final int MOMENT_ID_FIELD_NUMBER = 5;
    public static final int PUGGLE_RESULTS_FIELD_NUMBER = 7;
    public static final int TRACKING_ID_FIELD_NUMBER = 6;
    private static final ExtendedGogglesResponse defaultInstance = new ExtendedGogglesResponse(true);
    public static final GeneratedMessageLite.GeneratedExtension<GogglesProtos.GogglesResponse, ExtendedGogglesResponse> extendedGogglesResponse = GeneratedMessageLite.newSingularGeneratedExtension(GogglesProtos.GogglesResponse.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 15705729, WireFormat.FieldType.MESSAGE);
    private static final long serialVersionUID;
    private int bitField0_;
    private List<ResultProtos.Result> eyeCandyResults_;
    private long gogglesId_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object momentId_;
    private List<ResultProtos.Result> puggleResults_;
    private Object trackingId_;

    static
    {
      defaultInstance.initFields();
    }

    private ExtendedGogglesResponse(Builder paramBuilder)
    {
      super();
    }

    private ExtendedGogglesResponse(boolean paramBoolean)
    {
    }

    public static ExtendedGogglesResponse getDefaultInstance()
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

    private ByteString getTrackingIdBytes()
    {
      Object localObject = this.trackingId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.trackingId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.eyeCandyResults_ = Collections.emptyList();
      this.gogglesId_ = 0L;
      this.momentId_ = "";
      this.trackingId_ = "";
      this.puggleResults_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$2300();
    }

    public static Builder newBuilder(ExtendedGogglesResponse paramExtendedGogglesResponse)
    {
      return newBuilder().mergeFrom(paramExtendedGogglesResponse);
    }

    public static ExtendedGogglesResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ExtendedGogglesResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ExtendedGogglesResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ExtendedGogglesResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public ResultProtos.Result getEyeCandyResults(int paramInt)
    {
      return (ResultProtos.Result)this.eyeCandyResults_.get(paramInt);
    }

    public int getEyeCandyResultsCount()
    {
      return this.eyeCandyResults_.size();
    }

    public List<ResultProtos.Result> getEyeCandyResultsList()
    {
      return this.eyeCandyResults_;
    }

    public ResultProtos.ResultOrBuilder getEyeCandyResultsOrBuilder(int paramInt)
    {
      return (ResultProtos.ResultOrBuilder)this.eyeCandyResults_.get(paramInt);
    }

    public List<? extends ResultProtos.ResultOrBuilder> getEyeCandyResultsOrBuilderList()
    {
      return this.eyeCandyResults_;
    }

    @Deprecated
    public long getGogglesId()
    {
      return this.gogglesId_;
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

    public ResultProtos.Result getPuggleResults(int paramInt)
    {
      return (ResultProtos.Result)this.puggleResults_.get(paramInt);
    }

    public int getPuggleResultsCount()
    {
      return this.puggleResults_.size();
    }

    public List<ResultProtos.Result> getPuggleResultsList()
    {
      return this.puggleResults_;
    }

    public ResultProtos.ResultOrBuilder getPuggleResultsOrBuilder(int paramInt)
    {
      return (ResultProtos.ResultOrBuilder)this.puggleResults_.get(paramInt);
    }

    public List<? extends ResultProtos.ResultOrBuilder> getPuggleResultsOrBuilderList()
    {
      return this.puggleResults_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.eyeCandyResults_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.eyeCandyResults_.get(j));
        j++;
      }
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeFixed64Size(2, this.gogglesId_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(5, getMomentIdBytes());
      int m = 0x4 & this.bitField0_;
      int n = 0;
      if (m == 4)
        k += CodedOutputStream.computeBytesSize(6, getTrackingIdBytes());
      while (n < this.puggleResults_.size())
      {
        k += CodedOutputStream.computeMessageSize(7, (MessageLite)this.puggleResults_.get(n));
        n++;
      }
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getTrackingId()
    {
      Object localObject = this.trackingId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.trackingId_ = str;
      return str;
    }

    @Deprecated
    public boolean hasGogglesId()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasMomentId()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasTrackingId()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getEyeCandyResultsCount(); j++)
        if (!getEyeCandyResults(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (int k = 0; k < getPuggleResultsCount(); k++)
        if (!getPuggleResults(k).isInitialized())
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
      for (int i = 0; i < this.eyeCandyResults_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.eyeCandyResults_.get(i));
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeFixed64(2, this.gogglesId_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(5, getMomentIdBytes());
      int j = 0x4 & this.bitField0_;
      int k = 0;
      if (j == 4)
        paramCodedOutputStream.writeBytes(6, getTrackingIdBytes());
      while (k < this.puggleResults_.size())
      {
        paramCodedOutputStream.writeMessage(7, (MessageLite)this.puggleResults_.get(k));
        k++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExtendedGogglesProtos.ExtendedGogglesResponse, Builder>
      implements ExtendedGogglesProtos.ExtendedGogglesResponseOrBuilder
    {
      private int bitField0_;
      private List<ResultProtos.Result> eyeCandyResults_ = Collections.emptyList();
      private long gogglesId_;
      private Object momentId_ = "";
      private List<ResultProtos.Result> puggleResults_ = Collections.emptyList();
      private Object trackingId_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ExtendedGogglesProtos.ExtendedGogglesResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        ExtendedGogglesProtos.ExtendedGogglesResponse localExtendedGogglesResponse = buildPartial();
        if (!localExtendedGogglesResponse.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesResponse).asInvalidProtocolBufferException();
        return localExtendedGogglesResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureEyeCandyResultsIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.eyeCandyResults_ = new ArrayList(this.eyeCandyResults_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void ensurePuggleResultsIsMutable()
      {
        if ((0x10 & this.bitField0_) != 16)
        {
          this.puggleResults_ = new ArrayList(this.puggleResults_);
          this.bitField0_ = (0x10 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllEyeCandyResults(Iterable<? extends ResultProtos.Result> paramIterable)
      {
        ensureEyeCandyResultsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.eyeCandyResults_);
        return this;
      }

      public Builder addAllPuggleResults(Iterable<? extends ResultProtos.Result> paramIterable)
      {
        ensurePuggleResultsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.puggleResults_);
        return this;
      }

      public Builder addEyeCandyResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addEyeCandyResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.add(paramInt, paramResult);
        return this;
      }

      public Builder addEyeCandyResults(ResultProtos.Result.Builder paramBuilder)
      {
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.add(paramBuilder.build());
        return this;
      }

      public Builder addEyeCandyResults(ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.add(paramResult);
        return this;
      }

      public Builder addPuggleResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensurePuggleResultsIsMutable();
        this.puggleResults_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addPuggleResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensurePuggleResultsIsMutable();
        this.puggleResults_.add(paramInt, paramResult);
        return this;
      }

      public Builder addPuggleResults(ResultProtos.Result.Builder paramBuilder)
      {
        ensurePuggleResultsIsMutable();
        this.puggleResults_.add(paramBuilder.build());
        return this;
      }

      public Builder addPuggleResults(ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensurePuggleResultsIsMutable();
        this.puggleResults_.add(paramResult);
        return this;
      }

      public ExtendedGogglesProtos.ExtendedGogglesResponse build()
      {
        ExtendedGogglesProtos.ExtendedGogglesResponse localExtendedGogglesResponse = buildPartial();
        if (!localExtendedGogglesResponse.isInitialized())
          throw newUninitializedMessageException(localExtendedGogglesResponse);
        return localExtendedGogglesResponse;
      }

      public ExtendedGogglesProtos.ExtendedGogglesResponse buildPartial()
      {
        int i = 1;
        ExtendedGogglesProtos.ExtendedGogglesResponse localExtendedGogglesResponse = new ExtendedGogglesProtos.ExtendedGogglesResponse(this, null);
        int j = this.bitField0_;
        if ((0x1 & this.bitField0_) == i)
        {
          this.eyeCandyResults_ = Collections.unmodifiableList(this.eyeCandyResults_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        ExtendedGogglesProtos.ExtendedGogglesResponse.access$2502(localExtendedGogglesResponse, this.eyeCandyResults_);
        if ((j & 0x2) == 2);
        while (true)
        {
          ExtendedGogglesProtos.ExtendedGogglesResponse.access$2602(localExtendedGogglesResponse, this.gogglesId_);
          if ((j & 0x4) == 4)
            i |= 2;
          ExtendedGogglesProtos.ExtendedGogglesResponse.access$2702(localExtendedGogglesResponse, this.momentId_);
          if ((j & 0x8) == 8)
            i |= 4;
          ExtendedGogglesProtos.ExtendedGogglesResponse.access$2802(localExtendedGogglesResponse, this.trackingId_);
          if ((0x10 & this.bitField0_) == 16)
          {
            this.puggleResults_ = Collections.unmodifiableList(this.puggleResults_);
            this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          }
          ExtendedGogglesProtos.ExtendedGogglesResponse.access$2902(localExtendedGogglesResponse, this.puggleResults_);
          ExtendedGogglesProtos.ExtendedGogglesResponse.access$3002(localExtendedGogglesResponse, i);
          return localExtendedGogglesResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.eyeCandyResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.gogglesId_ = 0L;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.momentId_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.trackingId_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.puggleResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      public Builder clearEyeCandyResults()
      {
        this.eyeCandyResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesId()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.gogglesId_ = 0L;
        return this;
      }

      public Builder clearMomentId()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.momentId_ = ExtendedGogglesProtos.ExtendedGogglesResponse.getDefaultInstance().getMomentId();
        return this;
      }

      public Builder clearPuggleResults()
      {
        this.puggleResults_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      public Builder clearTrackingId()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.trackingId_ = ExtendedGogglesProtos.ExtendedGogglesResponse.getDefaultInstance().getTrackingId();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ExtendedGogglesProtos.ExtendedGogglesResponse getDefaultInstanceForType()
      {
        return ExtendedGogglesProtos.ExtendedGogglesResponse.getDefaultInstance();
      }

      public ResultProtos.Result getEyeCandyResults(int paramInt)
      {
        return (ResultProtos.Result)this.eyeCandyResults_.get(paramInt);
      }

      public int getEyeCandyResultsCount()
      {
        return this.eyeCandyResults_.size();
      }

      public List<ResultProtos.Result> getEyeCandyResultsList()
      {
        return Collections.unmodifiableList(this.eyeCandyResults_);
      }

      @Deprecated
      public long getGogglesId()
      {
        return this.gogglesId_;
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

      public ResultProtos.Result getPuggleResults(int paramInt)
      {
        return (ResultProtos.Result)this.puggleResults_.get(paramInt);
      }

      public int getPuggleResultsCount()
      {
        return this.puggleResults_.size();
      }

      public List<ResultProtos.Result> getPuggleResultsList()
      {
        return Collections.unmodifiableList(this.puggleResults_);
      }

      public String getTrackingId()
      {
        Object localObject = this.trackingId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.trackingId_ = str;
          return str;
        }
        return (String)localObject;
      }

      @Deprecated
      public boolean hasGogglesId()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasMomentId()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasTrackingId()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getEyeCandyResultsCount(); i++)
          if (!getEyeCandyResults(i).isInitialized())
            return false;
        for (int j = 0; ; j++)
        {
          if (j >= getPuggleResultsCount())
            break label56;
          if (!getPuggleResults(j).isInitialized())
            break;
        }
        label56: return true;
      }

      public Builder mergeFrom(ExtendedGogglesProtos.ExtendedGogglesResponse paramExtendedGogglesResponse)
      {
        if (paramExtendedGogglesResponse == ExtendedGogglesProtos.ExtendedGogglesResponse.getDefaultInstance())
          return this;
        if (!paramExtendedGogglesResponse.eyeCandyResults_.isEmpty())
        {
          if (!this.eyeCandyResults_.isEmpty())
            break label145;
          this.eyeCandyResults_ = paramExtendedGogglesResponse.eyeCandyResults_;
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        while (true)
        {
          if (paramExtendedGogglesResponse.hasGogglesId())
            setGogglesId(paramExtendedGogglesResponse.getGogglesId());
          if (paramExtendedGogglesResponse.hasMomentId())
            setMomentId(paramExtendedGogglesResponse.getMomentId());
          if (paramExtendedGogglesResponse.hasTrackingId())
            setTrackingId(paramExtendedGogglesResponse.getTrackingId());
          if (paramExtendedGogglesResponse.puggleResults_.isEmpty())
            break;
          if (!this.puggleResults_.isEmpty())
            break label166;
          this.puggleResults_ = paramExtendedGogglesResponse.puggleResults_;
          this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          return this;
          label145: ensureEyeCandyResultsIsMutable();
          this.eyeCandyResults_.addAll(paramExtendedGogglesResponse.eyeCandyResults_);
        }
        label166: ensurePuggleResultsIsMutable();
        this.puggleResults_.addAll(paramExtendedGogglesResponse.puggleResults_);
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
            ResultProtos.Result.Builder localBuilder2 = ResultProtos.Result.newBuilder();
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            addEyeCandyResults(localBuilder2.buildPartial());
            break;
          case 17:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.gogglesId_ = paramCodedInputStream.readFixed64();
            break;
          case 42:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.momentId_ = paramCodedInputStream.readBytes();
            break;
          case 50:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.trackingId_ = paramCodedInputStream.readBytes();
            break;
          case 58:
          }
          ResultProtos.Result.Builder localBuilder1 = ResultProtos.Result.newBuilder();
          paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
          addPuggleResults(localBuilder1.buildPartial());
        }
      }

      public Builder removeEyeCandyResults(int paramInt)
      {
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.remove(paramInt);
        return this;
      }

      public Builder removePuggleResults(int paramInt)
      {
        ensurePuggleResultsIsMutable();
        this.puggleResults_.remove(paramInt);
        return this;
      }

      public Builder setEyeCandyResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setEyeCandyResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensureEyeCandyResultsIsMutable();
        this.eyeCandyResults_.set(paramInt, paramResult);
        return this;
      }

      @Deprecated
      public Builder setGogglesId(long paramLong)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.gogglesId_ = paramLong;
        return this;
      }

      public Builder setMomentId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.momentId_ = paramString;
        return this;
      }

      void setMomentId(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.momentId_ = paramByteString;
      }

      public Builder setPuggleResults(int paramInt, ResultProtos.Result.Builder paramBuilder)
      {
        ensurePuggleResultsIsMutable();
        this.puggleResults_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setPuggleResults(int paramInt, ResultProtos.Result paramResult)
      {
        if (paramResult == null)
          throw new NullPointerException();
        ensurePuggleResultsIsMutable();
        this.puggleResults_.set(paramInt, paramResult);
        return this;
      }

      public Builder setTrackingId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.trackingId_ = paramString;
        return this;
      }

      void setTrackingId(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.trackingId_ = paramByteString;
      }
    }
  }

  public static abstract interface ExtendedGogglesResponseOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ResultProtos.Result getEyeCandyResults(int paramInt);

    public abstract int getEyeCandyResultsCount();

    public abstract List<ResultProtos.Result> getEyeCandyResultsList();

    @Deprecated
    public abstract long getGogglesId();

    public abstract String getMomentId();

    public abstract ResultProtos.Result getPuggleResults(int paramInt);

    public abstract int getPuggleResultsCount();

    public abstract List<ResultProtos.Result> getPuggleResultsList();

    public abstract String getTrackingId();

    @Deprecated
    public abstract boolean hasGogglesId();

    public abstract boolean hasMomentId();

    public abstract boolean hasTrackingId();
  }

  public static final class TrackedResult extends GeneratedMessageLite
    implements ExtendedGogglesProtos.TrackedResultOrBuilder
  {
    public static final int BOUNDING_BOX_FIELD_NUMBER = 2;
    public static final int RESULT_ID_FIELD_NUMBER = 1;
    private static final TrackedResult defaultInstance = new TrackedResult(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private BoundingBoxProtos.BoundingBox boundingBox_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object resultId_;

    static
    {
      defaultInstance.initFields();
    }

    private TrackedResult(Builder paramBuilder)
    {
      super();
    }

    private TrackedResult(boolean paramBoolean)
    {
    }

    public static TrackedResult getDefaultInstance()
    {
      return defaultInstance;
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

    private void initFields()
    {
      this.resultId_ = "";
      this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(TrackedResult paramTrackedResult)
    {
      return newBuilder().mergeFrom(paramTrackedResult);
    }

    public static TrackedResult parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TrackedResult parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TrackedResult parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TrackedResult parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TrackedResult parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TrackedResult parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TrackedResult parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TrackedResult parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TrackedResult parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TrackedResult parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public BoundingBoxProtos.BoundingBox getBoundingBox()
    {
      return this.boundingBox_;
    }

    public TrackedResult getDefaultInstanceForType()
    {
      return defaultInstance;
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

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, getResultIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(2, this.boundingBox_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasBoundingBox()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasResultId()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if ((hasBoundingBox()) && (!getBoundingBox().isInitialized()))
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
        paramCodedOutputStream.writeBytes(1, getResultIdBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(2, this.boundingBox_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ExtendedGogglesProtos.TrackedResult, Builder>
      implements ExtendedGogglesProtos.TrackedResultOrBuilder
    {
      private int bitField0_;
      private BoundingBoxProtos.BoundingBox boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      private Object resultId_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ExtendedGogglesProtos.TrackedResult buildParsed()
        throws InvalidProtocolBufferException
      {
        ExtendedGogglesProtos.TrackedResult localTrackedResult = buildPartial();
        if (!localTrackedResult.isInitialized())
          throw newUninitializedMessageException(localTrackedResult).asInvalidProtocolBufferException();
        return localTrackedResult;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ExtendedGogglesProtos.TrackedResult build()
      {
        ExtendedGogglesProtos.TrackedResult localTrackedResult = buildPartial();
        if (!localTrackedResult.isInitialized())
          throw newUninitializedMessageException(localTrackedResult);
        return localTrackedResult;
      }

      public ExtendedGogglesProtos.TrackedResult buildPartial()
      {
        int i = 1;
        ExtendedGogglesProtos.TrackedResult localTrackedResult = new ExtendedGogglesProtos.TrackedResult(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ExtendedGogglesProtos.TrackedResult.access$302(localTrackedResult, this.resultId_);
          if ((j & 0x2) == 2)
            i |= 2;
          ExtendedGogglesProtos.TrackedResult.access$402(localTrackedResult, this.boundingBox_);
          ExtendedGogglesProtos.TrackedResult.access$502(localTrackedResult, i);
          return localTrackedResult;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.resultId_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearBoundingBox()
      {
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearResultId()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.resultId_ = ExtendedGogglesProtos.TrackedResult.getDefaultInstance().getResultId();
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

      public ExtendedGogglesProtos.TrackedResult getDefaultInstanceForType()
      {
        return ExtendedGogglesProtos.TrackedResult.getDefaultInstance();
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

      public boolean hasBoundingBox()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasResultId()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return (!hasBoundingBox()) || (getBoundingBox().isInitialized());
      }

      public Builder mergeBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.boundingBox_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
        for (this.boundingBox_ = BoundingBoxProtos.BoundingBox.newBuilder(this.boundingBox_).mergeFrom(paramBoundingBox).buildPartial(); ; this.boundingBox_ = paramBoundingBox)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(ExtendedGogglesProtos.TrackedResult paramTrackedResult)
      {
        if (paramTrackedResult == ExtendedGogglesProtos.TrackedResult.getDefaultInstance());
        do
        {
          return this;
          if (paramTrackedResult.hasResultId())
            setResultId(paramTrackedResult.getResultId());
        }
        while (!paramTrackedResult.hasBoundingBox());
        mergeBoundingBox(paramTrackedResult.getBoundingBox());
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
            this.resultId_ = paramCodedInputStream.readBytes();
            break;
          case 18:
          }
          BoundingBoxProtos.BoundingBox.Builder localBuilder = BoundingBoxProtos.BoundingBox.newBuilder();
          if (hasBoundingBox())
            localBuilder.mergeFrom(getBoundingBox());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setBoundingBox(localBuilder.buildPartial());
        }
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
      {
        this.boundingBox_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == null)
          throw new NullPointerException();
        this.boundingBox_ = paramBoundingBox;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setResultId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.resultId_ = paramString;
        return this;
      }

      void setResultId(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.resultId_ = paramByteString;
      }
    }
  }

  public static abstract interface TrackedResultOrBuilder extends MessageLiteOrBuilder
  {
    public abstract BoundingBoxProtos.BoundingBox getBoundingBox();

    public abstract String getResultId();

    public abstract boolean hasBoundingBox();

    public abstract boolean hasResultId();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ExtendedGogglesProtos
 * JD-Core Version:    0.6.2
 */