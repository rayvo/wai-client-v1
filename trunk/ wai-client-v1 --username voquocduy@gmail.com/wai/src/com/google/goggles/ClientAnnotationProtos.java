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
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class ClientAnnotationProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class ClientAnnotation extends GeneratedMessageLite
    implements ClientAnnotationProtos.ClientAnnotationOrBuilder
  {
    public static final int BARCODE_FIELD_NUMBER = 10;
    public static final int BOUNDING_BOX_FIELD_NUMBER = 1;
    public static final int SCORE_FIELD_NUMBER = 2;
    private static final ClientAnnotation defaultInstance = new ClientAnnotation(true);
    private static final long serialVersionUID;
    private ClientAnnotationProtos.ClientBarcode barcode_;
    private int bitField0_;
    private BoundingBoxProtos.BoundingBox boundingBox_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private float score_;

    static
    {
      defaultInstance.initFields();
    }

    private ClientAnnotation(Builder paramBuilder)
    {
      super();
    }

    private ClientAnnotation(boolean paramBoolean)
    {
    }

    public static ClientAnnotation getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      this.score_ = 0.0F;
      this.barcode_ = ClientAnnotationProtos.ClientBarcode.getDefaultInstance();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(ClientAnnotation paramClientAnnotation)
    {
      return newBuilder().mergeFrom(paramClientAnnotation);
    }

    public static ClientAnnotation parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ClientAnnotation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ClientAnnotation parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ClientAnnotation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ClientAnnotation parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ClientAnnotation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ClientAnnotation parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ClientAnnotation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ClientAnnotation parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ClientAnnotation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ClientAnnotationProtos.ClientBarcode getBarcode()
    {
      return this.barcode_;
    }

    public BoundingBoxProtos.BoundingBox getBoundingBox()
    {
      return this.boundingBox_;
    }

    public ClientAnnotation getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public float getScore()
    {
      return this.score_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeMessageSize(1, this.boundingBox_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeFloatSize(2, this.score_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(10, this.barcode_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasBarcode()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasBoundingBox()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasScore()
    {
      return (0x2 & this.bitField0_) == 2;
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
      if ((hasBarcode()) && (!getBarcode().isInitialized()))
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
        paramCodedOutputStream.writeMessage(1, this.boundingBox_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeFloat(2, this.score_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(10, this.barcode_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ClientAnnotationProtos.ClientAnnotation, Builder>
      implements ClientAnnotationProtos.ClientAnnotationOrBuilder
    {
      private ClientAnnotationProtos.ClientBarcode barcode_ = ClientAnnotationProtos.ClientBarcode.getDefaultInstance();
      private int bitField0_;
      private BoundingBoxProtos.BoundingBox boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
      private float score_;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ClientAnnotationProtos.ClientAnnotation buildParsed()
        throws InvalidProtocolBufferException
      {
        ClientAnnotationProtos.ClientAnnotation localClientAnnotation = buildPartial();
        if (!localClientAnnotation.isInitialized())
          throw newUninitializedMessageException(localClientAnnotation).asInvalidProtocolBufferException();
        return localClientAnnotation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ClientAnnotationProtos.ClientAnnotation build()
      {
        ClientAnnotationProtos.ClientAnnotation localClientAnnotation = buildPartial();
        if (!localClientAnnotation.isInitialized())
          throw newUninitializedMessageException(localClientAnnotation);
        return localClientAnnotation;
      }

      public ClientAnnotationProtos.ClientAnnotation buildPartial()
      {
        int i = 1;
        ClientAnnotationProtos.ClientAnnotation localClientAnnotation = new ClientAnnotationProtos.ClientAnnotation(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ClientAnnotationProtos.ClientAnnotation.access$302(localClientAnnotation, this.boundingBox_);
          if ((j & 0x2) == 2)
            i |= 2;
          ClientAnnotationProtos.ClientAnnotation.access$402(localClientAnnotation, this.score_);
          if ((j & 0x4) == 4)
            i |= 4;
          ClientAnnotationProtos.ClientAnnotation.access$502(localClientAnnotation, this.barcode_);
          ClientAnnotationProtos.ClientAnnotation.access$602(localClientAnnotation, i);
          return localClientAnnotation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.score_ = 0.0F;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.barcode_ = ClientAnnotationProtos.ClientBarcode.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearBarcode()
      {
        this.barcode_ = ClientAnnotationProtos.ClientBarcode.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearBoundingBox()
      {
        this.boundingBox_ = BoundingBoxProtos.BoundingBox.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearScore()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.score_ = 0.0F;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ClientAnnotationProtos.ClientBarcode getBarcode()
      {
        return this.barcode_;
      }

      public BoundingBoxProtos.BoundingBox getBoundingBox()
      {
        return this.boundingBox_;
      }

      public ClientAnnotationProtos.ClientAnnotation getDefaultInstanceForType()
      {
        return ClientAnnotationProtos.ClientAnnotation.getDefaultInstance();
      }

      public float getScore()
      {
        return this.score_;
      }

      public boolean hasBarcode()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasBoundingBox()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasScore()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        if ((hasBoundingBox()) && (!getBoundingBox().isInitialized()));
        while ((hasBarcode()) && (!getBarcode().isInitialized()))
          return false;
        return true;
      }

      public Builder mergeBarcode(ClientAnnotationProtos.ClientBarcode paramClientBarcode)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.barcode_ != ClientAnnotationProtos.ClientBarcode.getDefaultInstance()));
        for (this.barcode_ = ClientAnnotationProtos.ClientBarcode.newBuilder(this.barcode_).mergeFrom(paramClientBarcode).buildPartial(); ; this.barcode_ = paramClientBarcode)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.boundingBox_ != BoundingBoxProtos.BoundingBox.getDefaultInstance()));
        for (this.boundingBox_ = BoundingBoxProtos.BoundingBox.newBuilder(this.boundingBox_).mergeFrom(paramBoundingBox).buildPartial(); ; this.boundingBox_ = paramBoundingBox)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(ClientAnnotationProtos.ClientAnnotation paramClientAnnotation)
      {
        if (paramClientAnnotation == ClientAnnotationProtos.ClientAnnotation.getDefaultInstance());
        do
        {
          return this;
          if (paramClientAnnotation.hasBoundingBox())
            mergeBoundingBox(paramClientAnnotation.getBoundingBox());
          if (paramClientAnnotation.hasScore())
            setScore(paramClientAnnotation.getScore());
        }
        while (!paramClientAnnotation.hasBarcode());
        mergeBarcode(paramClientAnnotation.getBarcode());
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
            BoundingBoxProtos.BoundingBox.Builder localBuilder1 = BoundingBoxProtos.BoundingBox.newBuilder();
            if (hasBoundingBox())
              localBuilder1.mergeFrom(getBoundingBox());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setBoundingBox(localBuilder1.buildPartial());
            break;
          case 21:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.score_ = paramCodedInputStream.readFloat();
            break;
          case 82:
          }
          ClientAnnotationProtos.ClientBarcode.Builder localBuilder = ClientAnnotationProtos.ClientBarcode.newBuilder();
          if (hasBarcode())
            localBuilder.mergeFrom(getBarcode());
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          setBarcode(localBuilder.buildPartial());
        }
      }

      public Builder setBarcode(ClientAnnotationProtos.ClientBarcode.Builder paramBuilder)
      {
        this.barcode_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setBarcode(ClientAnnotationProtos.ClientBarcode paramClientBarcode)
      {
        if (paramClientBarcode == null)
          throw new NullPointerException();
        this.barcode_ = paramClientBarcode;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox.Builder paramBuilder)
      {
        this.boundingBox_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setBoundingBox(BoundingBoxProtos.BoundingBox paramBoundingBox)
      {
        if (paramBoundingBox == null)
          throw new NullPointerException();
        this.boundingBox_ = paramBoundingBox;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      public Builder setScore(float paramFloat)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.score_ = paramFloat;
        return this;
      }
    }
  }

  public static abstract interface ClientAnnotationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ClientAnnotationProtos.ClientBarcode getBarcode();

    public abstract BoundingBoxProtos.BoundingBox getBoundingBox();

    public abstract float getScore();

    public abstract boolean hasBarcode();

    public abstract boolean hasBoundingBox();

    public abstract boolean hasScore();
  }

  public static final class ClientBarcode extends GeneratedMessageLite
    implements ClientAnnotationProtos.ClientBarcodeOrBuilder
  {
    public static final int FORMAT_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    public static final int VALUE_FIELD_NUMBER = 3;
    private static final ClientBarcode defaultInstance = new ClientBarcode(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private BarcodeFormat format_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private BarcodeType type_;
    private Object value_;

    static
    {
      defaultInstance.initFields();
    }

    private ClientBarcode(Builder paramBuilder)
    {
      super();
    }

    private ClientBarcode(boolean paramBoolean)
    {
    }

    public static ClientBarcode getDefaultInstance()
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
      this.type_ = BarcodeType.ADDRESS_BOOK;
      this.format_ = BarcodeFormat.QR_CODE;
      this.value_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$800();
    }

    public static Builder newBuilder(ClientBarcode paramClientBarcode)
    {
      return newBuilder().mergeFrom(paramClientBarcode);
    }

    public static ClientBarcode parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ClientBarcode parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ClientBarcode parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ClientBarcode parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ClientBarcode parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ClientBarcode parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ClientBarcode parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ClientBarcode parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ClientBarcode parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ClientBarcode parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ClientBarcode getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public BarcodeFormat getFormat()
    {
      return this.format_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeEnumSize(2, this.format_.getNumber());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(3, getValueBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public BarcodeType getType()
    {
      return this.type_;
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

    public boolean hasFormat()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasType()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasValue()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasValue())
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
        paramCodedOutputStream.writeEnum(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeEnum(2, this.format_.getNumber());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(3, getValueBytes());
    }

    public static enum BarcodeFormat
      implements Internal.EnumLite
    {
      public static final int CODE_128_VALUE = 7;
      public static final int CODE_39_VALUE = 8;
      public static final int DATAMATRIX_VALUE = 2;
      public static final int EAN_13_VALUE = 6;
      public static final int EAN_8_VALUE = 5;
      public static final int ITF_VALUE = 9;
      public static final int OTHER_FORMAT_VALUE = 100;
      public static final int PDF417_VALUE = 10;
      public static final int QR_CODE_VALUE = 1;
      public static final int UPC_A_VALUE = 4;
      public static final int UPC_E_VALUE = 3;
      private static Internal.EnumLiteMap<BarcodeFormat> internalValueMap = new Internal.EnumLiteMap()
      {
        public ClientAnnotationProtos.ClientBarcode.BarcodeFormat findValueByNumber(int paramAnonymousInt)
        {
          return ClientAnnotationProtos.ClientBarcode.BarcodeFormat.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        DATAMATRIX = new BarcodeFormat("DATAMATRIX", 1, 1, 2);
        UPC_E = new BarcodeFormat("UPC_E", 2, 2, 3);
        UPC_A = new BarcodeFormat("UPC_A", 3, 3, 4);
        EAN_8 = new BarcodeFormat("EAN_8", 4, 4, 5);
        EAN_13 = new BarcodeFormat("EAN_13", 5, 5, 6);
        CODE_128 = new BarcodeFormat("CODE_128", 6, 6, 7);
        CODE_39 = new BarcodeFormat("CODE_39", 7, 7, 8);
        ITF = new BarcodeFormat("ITF", 8, 8, 9);
        PDF417 = new BarcodeFormat("PDF417", 9, 9, 10);
        OTHER_FORMAT = new BarcodeFormat("OTHER_FORMAT", 10, 10, 100);
        BarcodeFormat[] arrayOfBarcodeFormat = new BarcodeFormat[11];
        arrayOfBarcodeFormat[0] = QR_CODE;
        arrayOfBarcodeFormat[1] = DATAMATRIX;
        arrayOfBarcodeFormat[2] = UPC_E;
        arrayOfBarcodeFormat[3] = UPC_A;
        arrayOfBarcodeFormat[4] = EAN_8;
        arrayOfBarcodeFormat[5] = EAN_13;
        arrayOfBarcodeFormat[6] = CODE_128;
        arrayOfBarcodeFormat[7] = CODE_39;
        arrayOfBarcodeFormat[8] = ITF;
        arrayOfBarcodeFormat[9] = PDF417;
        arrayOfBarcodeFormat[10] = OTHER_FORMAT;
      }

      private BarcodeFormat(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<BarcodeFormat> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static BarcodeFormat valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return QR_CODE;
        case 2:
          return DATAMATRIX;
        case 3:
          return UPC_E;
        case 4:
          return UPC_A;
        case 5:
          return EAN_8;
        case 6:
          return EAN_13;
        case 7:
          return CODE_128;
        case 8:
          return CODE_39;
        case 9:
          return ITF;
        case 10:
          return PDF417;
        case 100:
        }
        return OTHER_FORMAT;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }

    public static enum BarcodeType
      implements Internal.EnumLite
    {
      public static final int ADDRESS_BOOK_VALUE = 1;
      public static final int EMAIL_ADDRESS_VALUE = 2;
      public static final int ISBN_VALUE = 8;
      public static final int OTHER_TYPE_VALUE = 100;
      public static final int PRODUCT_VALUE = 3;
      public static final int SMS_VALUE = 7;
      public static final int TEL_VALUE = 6;
      public static final int TEXT_VALUE = 5;
      public static final int URI_VALUE = 4;
      private static Internal.EnumLiteMap<BarcodeType> internalValueMap = new Internal.EnumLiteMap()
      {
        public ClientAnnotationProtos.ClientBarcode.BarcodeType findValueByNumber(int paramAnonymousInt)
        {
          return ClientAnnotationProtos.ClientBarcode.BarcodeType.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        TEXT = new BarcodeType("TEXT", 4, 4, 5);
        TEL = new BarcodeType("TEL", 5, 5, 6);
        SMS = new BarcodeType("SMS", 6, 6, 7);
        ISBN = new BarcodeType("ISBN", 7, 7, 8);
        OTHER_TYPE = new BarcodeType("OTHER_TYPE", 8, 8, 100);
        BarcodeType[] arrayOfBarcodeType = new BarcodeType[9];
        arrayOfBarcodeType[0] = ADDRESS_BOOK;
        arrayOfBarcodeType[1] = EMAIL_ADDRESS;
        arrayOfBarcodeType[2] = PRODUCT;
        arrayOfBarcodeType[3] = URI;
        arrayOfBarcodeType[4] = TEXT;
        arrayOfBarcodeType[5] = TEL;
        arrayOfBarcodeType[6] = SMS;
        arrayOfBarcodeType[7] = ISBN;
        arrayOfBarcodeType[8] = OTHER_TYPE;
      }

      private BarcodeType(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<BarcodeType> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static BarcodeType valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return ADDRESS_BOOK;
        case 2:
          return EMAIL_ADDRESS;
        case 3:
          return PRODUCT;
        case 4:
          return URI;
        case 5:
          return TEXT;
        case 6:
          return TEL;
        case 7:
          return SMS;
        case 8:
          return ISBN;
        case 100:
        }
        return OTHER_TYPE;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ClientAnnotationProtos.ClientBarcode, Builder>
      implements ClientAnnotationProtos.ClientBarcodeOrBuilder
    {
      private int bitField0_;
      private ClientAnnotationProtos.ClientBarcode.BarcodeFormat format_ = ClientAnnotationProtos.ClientBarcode.BarcodeFormat.QR_CODE;
      private ClientAnnotationProtos.ClientBarcode.BarcodeType type_ = ClientAnnotationProtos.ClientBarcode.BarcodeType.ADDRESS_BOOK;
      private Object value_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ClientAnnotationProtos.ClientBarcode buildParsed()
        throws InvalidProtocolBufferException
      {
        ClientAnnotationProtos.ClientBarcode localClientBarcode = buildPartial();
        if (!localClientBarcode.isInitialized())
          throw newUninitializedMessageException(localClientBarcode).asInvalidProtocolBufferException();
        return localClientBarcode;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ClientAnnotationProtos.ClientBarcode build()
      {
        ClientAnnotationProtos.ClientBarcode localClientBarcode = buildPartial();
        if (!localClientBarcode.isInitialized())
          throw newUninitializedMessageException(localClientBarcode);
        return localClientBarcode;
      }

      public ClientAnnotationProtos.ClientBarcode buildPartial()
      {
        int i = 1;
        ClientAnnotationProtos.ClientBarcode localClientBarcode = new ClientAnnotationProtos.ClientBarcode(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ClientAnnotationProtos.ClientBarcode.access$1002(localClientBarcode, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          ClientAnnotationProtos.ClientBarcode.access$1102(localClientBarcode, this.format_);
          if ((j & 0x4) == 4)
            i |= 4;
          ClientAnnotationProtos.ClientBarcode.access$1202(localClientBarcode, this.value_);
          ClientAnnotationProtos.ClientBarcode.access$1302(localClientBarcode, i);
          return localClientBarcode;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = ClientAnnotationProtos.ClientBarcode.BarcodeType.ADDRESS_BOOK;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.format_ = ClientAnnotationProtos.ClientBarcode.BarcodeFormat.QR_CODE;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.value_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearFormat()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.format_ = ClientAnnotationProtos.ClientBarcode.BarcodeFormat.QR_CODE;
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = ClientAnnotationProtos.ClientBarcode.BarcodeType.ADDRESS_BOOK;
        return this;
      }

      public Builder clearValue()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.value_ = ClientAnnotationProtos.ClientBarcode.getDefaultInstance().getValue();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ClientAnnotationProtos.ClientBarcode getDefaultInstanceForType()
      {
        return ClientAnnotationProtos.ClientBarcode.getDefaultInstance();
      }

      public ClientAnnotationProtos.ClientBarcode.BarcodeFormat getFormat()
      {
        return this.format_;
      }

      public ClientAnnotationProtos.ClientBarcode.BarcodeType getType()
      {
        return this.type_;
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

      public boolean hasFormat()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasValue()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        return hasValue();
      }

      public Builder mergeFrom(ClientAnnotationProtos.ClientBarcode paramClientBarcode)
      {
        if (paramClientBarcode == ClientAnnotationProtos.ClientBarcode.getDefaultInstance());
        do
        {
          return this;
          if (paramClientBarcode.hasType())
            setType(paramClientBarcode.getType());
          if (paramClientBarcode.hasFormat())
            setFormat(paramClientBarcode.getFormat());
        }
        while (!paramClientBarcode.hasValue());
        setValue(paramClientBarcode.getValue());
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
            ClientAnnotationProtos.ClientBarcode.BarcodeType localBarcodeType = ClientAnnotationProtos.ClientBarcode.BarcodeType.valueOf(paramCodedInputStream.readEnum());
            if (localBarcodeType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localBarcodeType;
            break;
          case 16:
            ClientAnnotationProtos.ClientBarcode.BarcodeFormat localBarcodeFormat = ClientAnnotationProtos.ClientBarcode.BarcodeFormat.valueOf(paramCodedInputStream.readEnum());
            if (localBarcodeFormat == null)
              continue;
            this.bitField0_ = (0x2 | this.bitField0_);
            this.format_ = localBarcodeFormat;
            break;
          case 26:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.value_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setFormat(ClientAnnotationProtos.ClientBarcode.BarcodeFormat paramBarcodeFormat)
      {
        if (paramBarcodeFormat == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.format_ = paramBarcodeFormat;
        return this;
      }

      public Builder setType(ClientAnnotationProtos.ClientBarcode.BarcodeType paramBarcodeType)
      {
        if (paramBarcodeType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramBarcodeType;
        return this;
      }

      public Builder setValue(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.value_ = paramString;
        return this;
      }

      void setValue(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.value_ = paramByteString;
      }
    }
  }

  public static abstract interface ClientBarcodeOrBuilder extends MessageLiteOrBuilder
  {
    public abstract ClientAnnotationProtos.ClientBarcode.BarcodeFormat getFormat();

    public abstract ClientAnnotationProtos.ClientBarcode.BarcodeType getType();

    public abstract String getValue();

    public abstract boolean hasFormat();

    public abstract boolean hasType();

    public abstract boolean hasValue();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ClientAnnotationProtos
 * JD-Core Version:    0.6.2
 */