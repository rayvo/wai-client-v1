package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal.EnumLite;
import com.google.protobuf.Internal.EnumLiteMap;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class SensorAccuracyProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class SensorAccuracy extends GeneratedMessageLite
    implements SensorAccuracyProtos.SensorAccuracyOrBuilder
  {
    public static final int DISCRETE_ACCURACY_FIELD_NUMBER = 1;
    private static final SensorAccuracy defaultInstance = new SensorAccuracy(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private DiscreteSensorAccuracy discreteAccuracy_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private SensorAccuracy(Builder paramBuilder)
    {
      super();
    }

    private SensorAccuracy(boolean paramBoolean)
    {
    }

    public static SensorAccuracy getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.discreteAccuracy_ = DiscreteSensorAccuracy.MEDIUM;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(SensorAccuracy paramSensorAccuracy)
    {
      return newBuilder().mergeFrom(paramSensorAccuracy);
    }

    public static SensorAccuracy parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static SensorAccuracy parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static SensorAccuracy parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static SensorAccuracy parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static SensorAccuracy parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static SensorAccuracy parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static SensorAccuracy parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static SensorAccuracy parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static SensorAccuracy parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static SensorAccuracy parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public SensorAccuracy getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public DiscreteSensorAccuracy getDiscreteAccuracy()
    {
      return this.discreteAccuracy_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeEnumSize(1, this.discreteAccuracy_.getNumber());
      this.memoizedSerializedSize = k;
      return k;
    }

    public boolean hasDiscreteAccuracy()
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
        paramCodedOutputStream.writeEnum(1, this.discreteAccuracy_.getNumber());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<SensorAccuracyProtos.SensorAccuracy, Builder>
      implements SensorAccuracyProtos.SensorAccuracyOrBuilder
    {
      private int bitField0_;
      private SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy discreteAccuracy_ = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.MEDIUM;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private SensorAccuracyProtos.SensorAccuracy buildParsed()
        throws InvalidProtocolBufferException
      {
        SensorAccuracyProtos.SensorAccuracy localSensorAccuracy = buildPartial();
        if (!localSensorAccuracy.isInitialized())
          throw newUninitializedMessageException(localSensorAccuracy).asInvalidProtocolBufferException();
        return localSensorAccuracy;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public SensorAccuracyProtos.SensorAccuracy build()
      {
        SensorAccuracyProtos.SensorAccuracy localSensorAccuracy = buildPartial();
        if (!localSensorAccuracy.isInitialized())
          throw newUninitializedMessageException(localSensorAccuracy);
        return localSensorAccuracy;
      }

      public SensorAccuracyProtos.SensorAccuracy buildPartial()
      {
        int i = 1;
        SensorAccuracyProtos.SensorAccuracy localSensorAccuracy = new SensorAccuracyProtos.SensorAccuracy(this, null);
        if ((0x1 & this.bitField0_) == i);
        while (true)
        {
          SensorAccuracyProtos.SensorAccuracy.access$302(localSensorAccuracy, this.discreteAccuracy_);
          SensorAccuracyProtos.SensorAccuracy.access$402(localSensorAccuracy, i);
          return localSensorAccuracy;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.discreteAccuracy_ = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.MEDIUM;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearDiscreteAccuracy()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.discreteAccuracy_ = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.MEDIUM;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public SensorAccuracyProtos.SensorAccuracy getDefaultInstanceForType()
      {
        return SensorAccuracyProtos.SensorAccuracy.getDefaultInstance();
      }

      public SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy getDiscreteAccuracy()
      {
        return this.discreteAccuracy_;
      }

      public boolean hasDiscreteAccuracy()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(SensorAccuracyProtos.SensorAccuracy paramSensorAccuracy)
      {
        if (paramSensorAccuracy == SensorAccuracyProtos.SensorAccuracy.getDefaultInstance());
        while (!paramSensorAccuracy.hasDiscreteAccuracy())
          return this;
        setDiscreteAccuracy(paramSensorAccuracy.getDiscreteAccuracy());
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
          SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy localDiscreteSensorAccuracy = SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.valueOf(paramCodedInputStream.readEnum());
          if (localDiscreteSensorAccuracy != null)
          {
            this.bitField0_ = (0x1 | this.bitField0_);
            this.discreteAccuracy_ = localDiscreteSensorAccuracy;
          }
        }
      }

      public Builder setDiscreteAccuracy(SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy paramDiscreteSensorAccuracy)
      {
        if (paramDiscreteSensorAccuracy == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.discreteAccuracy_ = paramDiscreteSensorAccuracy;
        return this;
      }
    }

    public static enum DiscreteSensorAccuracy
      implements Internal.EnumLite
    {
      public static final int HIGH_VALUE = 1;
      public static final int LOW_VALUE = 3;
      public static final int MEDIUM_VALUE = 2;
      public static final int UNRELIABLE_VALUE = 4;
      private static Internal.EnumLiteMap<DiscreteSensorAccuracy> internalValueMap = new Internal.EnumLiteMap()
      {
        public SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy findValueByNumber(int paramAnonymousInt)
        {
          return SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        LOW = new DiscreteSensorAccuracy("LOW", 2, 2, 3);
        UNRELIABLE = new DiscreteSensorAccuracy("UNRELIABLE", 3, 3, 4);
        DiscreteSensorAccuracy[] arrayOfDiscreteSensorAccuracy = new DiscreteSensorAccuracy[4];
        arrayOfDiscreteSensorAccuracy[0] = HIGH;
        arrayOfDiscreteSensorAccuracy[1] = MEDIUM;
        arrayOfDiscreteSensorAccuracy[2] = LOW;
        arrayOfDiscreteSensorAccuracy[3] = UNRELIABLE;
      }

      private DiscreteSensorAccuracy(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<DiscreteSensorAccuracy> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static DiscreteSensorAccuracy valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return HIGH;
        case 2:
          return MEDIUM;
        case 3:
          return LOW;
        case 4:
        }
        return UNRELIABLE;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface SensorAccuracyOrBuilder extends MessageLiteOrBuilder
  {
    public abstract SensorAccuracyProtos.SensorAccuracy.DiscreteSensorAccuracy getDiscreteAccuracy();

    public abstract boolean hasDiscreteAccuracy();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.SensorAccuracyProtos
 * JD-Core Version:    0.6.2
 */