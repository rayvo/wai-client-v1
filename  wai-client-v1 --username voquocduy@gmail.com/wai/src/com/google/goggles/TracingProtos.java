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

public final class TracingProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class PointVariable extends GeneratedMessageLite
    implements TracingProtos.PointVariableOrBuilder
  {
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final PointVariable defaultInstance = new PointVariable(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int timestampMs_;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private PointVariable(Builder paramBuilder)
    {
      super();
    }

    private PointVariable(boolean paramBoolean)
    {
    }

    public static PointVariable getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.type_ = Type.SUBSEQUENT_RESPONSE;
      this.timestampMs_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$800();
    }

    public static Builder newBuilder(PointVariable paramPointVariable)
    {
      return newBuilder().mergeFrom(paramPointVariable);
    }

    public static PointVariable parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static PointVariable parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static PointVariable parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static PointVariable parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static PointVariable parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static PointVariable parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static PointVariable parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static PointVariable parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static PointVariable parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static PointVariable parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public PointVariable getDefaultInstanceForType()
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
        k = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeInt32Size(2, this.timestampMs_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getTimestampMs()
    {
      return this.timestampMs_;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasTimestampMs()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasType()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasType())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasTimestampMs())
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
        paramCodedOutputStream.writeInt32(2, this.timestampMs_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.PointVariable, Builder>
      implements TracingProtos.PointVariableOrBuilder
    {
      private int bitField0_;
      private int timestampMs_;
      private TracingProtos.PointVariable.Type type_ = TracingProtos.PointVariable.Type.SUBSEQUENT_RESPONSE;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.PointVariable buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.PointVariable localPointVariable = buildPartial();
        if (!localPointVariable.isInitialized())
          throw newUninitializedMessageException(localPointVariable).asInvalidProtocolBufferException();
        return localPointVariable;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public TracingProtos.PointVariable build()
      {
        TracingProtos.PointVariable localPointVariable = buildPartial();
        if (!localPointVariable.isInitialized())
          throw newUninitializedMessageException(localPointVariable);
        return localPointVariable;
      }

      public TracingProtos.PointVariable buildPartial()
      {
        int i = 1;
        TracingProtos.PointVariable localPointVariable = new TracingProtos.PointVariable(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          TracingProtos.PointVariable.access$1002(localPointVariable, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          TracingProtos.PointVariable.access$1102(localPointVariable, this.timestampMs_);
          TracingProtos.PointVariable.access$1202(localPointVariable, i);
          return localPointVariable;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = TracingProtos.PointVariable.Type.SUBSEQUENT_RESPONSE;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.timestampMs_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearTimestampMs()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.timestampMs_ = 0;
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = TracingProtos.PointVariable.Type.SUBSEQUENT_RESPONSE;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingProtos.PointVariable getDefaultInstanceForType()
      {
        return TracingProtos.PointVariable.getDefaultInstance();
      }

      public int getTimestampMs()
      {
        return this.timestampMs_;
      }

      public TracingProtos.PointVariable.Type getType()
      {
        return this.type_;
      }

      public boolean hasTimestampMs()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasType());
        while (!hasTimestampMs())
          return false;
        return true;
      }

      public Builder mergeFrom(TracingProtos.PointVariable paramPointVariable)
      {
        if (paramPointVariable == TracingProtos.PointVariable.getDefaultInstance());
        do
        {
          return this;
          if (paramPointVariable.hasType())
            setType(paramPointVariable.getType());
        }
        while (!paramPointVariable.hasTimestampMs());
        setTimestampMs(paramPointVariable.getTimestampMs());
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
            TracingProtos.PointVariable.Type localType = TracingProtos.PointVariable.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 16:
          }
          this.bitField0_ = (0x2 | this.bitField0_);
          this.timestampMs_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setTimestampMs(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.timestampMs_ = paramInt;
        return this;
      }

      public Builder setType(TracingProtos.PointVariable.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int BARCODE_VALUE = 3;
      public static final int CAMERA_PREVIEW_START_VALUE = 6;
      public static final int CLIENT_ANNOTATION_SENT_VALUE = 8;
      public static final int HISTORY_VALUE = 7;
      public static final int PUGGLE_VALUE = 9;
      public static final int PULL_VALUE = 5;
      public static final int PUSH_VALUE = 4;
      public static final int SUBSEQUENT_RESPONSE_VALUE = 1;
      public static final int USER_VALUE = 2;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public TracingProtos.PointVariable.Type findValueByNumber(int paramAnonymousInt)
        {
          return TracingProtos.PointVariable.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        BARCODE = new Type("BARCODE", 2, 2, 3);
        PUSH = new Type("PUSH", 3, 3, 4);
        PULL = new Type("PULL", 4, 4, 5);
        CAMERA_PREVIEW_START = new Type("CAMERA_PREVIEW_START", 5, 5, 6);
        HISTORY = new Type("HISTORY", 6, 6, 7);
        PUGGLE = new Type("PUGGLE", 7, 7, 9);
        CLIENT_ANNOTATION_SENT = new Type("CLIENT_ANNOTATION_SENT", 8, 8, 8);
        Type[] arrayOfType = new Type[9];
        arrayOfType[0] = SUBSEQUENT_RESPONSE;
        arrayOfType[1] = USER;
        arrayOfType[2] = BARCODE;
        arrayOfType[3] = PUSH;
        arrayOfType[4] = PULL;
        arrayOfType[5] = CAMERA_PREVIEW_START;
        arrayOfType[6] = HISTORY;
        arrayOfType[7] = PUGGLE;
        arrayOfType[8] = CLIENT_ANNOTATION_SENT;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return SUBSEQUENT_RESPONSE;
        case 2:
          return USER;
        case 3:
          return BARCODE;
        case 4:
          return PUSH;
        case 5:
          return PULL;
        case 6:
          return CAMERA_PREVIEW_START;
        case 7:
          return HISTORY;
        case 9:
          return PUGGLE;
        case 8:
        }
        return CLIENT_ANNOTATION_SENT;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface PointVariableOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getTimestampMs();

    public abstract TracingProtos.PointVariable.Type getType();

    public abstract boolean hasTimestampMs();

    public abstract boolean hasType();
  }

  public static final class ProcessorStatus extends GeneratedMessageLite
    implements TracingProtos.ProcessorStatusOrBuilder
  {
    public static final int CUMULATIVE_PROCESS_TIME_MS_FIELD_NUMBER = 2;
    public static final int DUTY_PERIOD_FIELD_NUMBER = 4;
    public static final int NUM_FRAMES_FIELD_NUMBER = 3;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final ProcessorStatus defaultInstance = new ProcessorStatus(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int cumulativeProcessTimeMs_;
    private int dutyPeriod_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int numFrames_;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private ProcessorStatus(Builder paramBuilder)
    {
      super();
    }

    private ProcessorStatus(boolean paramBoolean)
    {
    }

    public static ProcessorStatus getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.type_ = Type.BARCODE_SCANNER;
      this.cumulativeProcessTimeMs_ = 0;
      this.numFrames_ = 0;
      this.dutyPeriod_ = 1;
    }

    public static Builder newBuilder()
    {
      return Builder.access$2700();
    }

    public static Builder newBuilder(ProcessorStatus paramProcessorStatus)
    {
      return newBuilder().mergeFrom(paramProcessorStatus);
    }

    public static ProcessorStatus parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ProcessorStatus parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ProcessorStatus parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ProcessorStatus parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ProcessorStatus parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ProcessorStatus parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ProcessorStatus parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ProcessorStatus parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ProcessorStatus parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ProcessorStatus parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public int getCumulativeProcessTimeMs()
    {
      return this.cumulativeProcessTimeMs_;
    }

    public ProcessorStatus getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getDutyPeriod()
    {
      return this.dutyPeriod_;
    }

    public int getNumFrames()
    {
      return this.numFrames_;
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
        k += CodedOutputStream.computeInt32Size(2, this.cumulativeProcessTimeMs_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.numFrames_);
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeInt32Size(4, this.dutyPeriod_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasCumulativeProcessTimeMs()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasDutyPeriod()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasNumFrames()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasType()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasType())
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
        paramCodedOutputStream.writeInt32(2, this.cumulativeProcessTimeMs_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.numFrames_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(4, this.dutyPeriod_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.ProcessorStatus, Builder>
      implements TracingProtos.ProcessorStatusOrBuilder
    {
      private int bitField0_;
      private int cumulativeProcessTimeMs_;
      private int dutyPeriod_ = 1;
      private int numFrames_;
      private TracingProtos.ProcessorStatus.Type type_ = TracingProtos.ProcessorStatus.Type.BARCODE_SCANNER;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.ProcessorStatus buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.ProcessorStatus localProcessorStatus = buildPartial();
        if (!localProcessorStatus.isInitialized())
          throw newUninitializedMessageException(localProcessorStatus).asInvalidProtocolBufferException();
        return localProcessorStatus;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public TracingProtos.ProcessorStatus build()
      {
        TracingProtos.ProcessorStatus localProcessorStatus = buildPartial();
        if (!localProcessorStatus.isInitialized())
          throw newUninitializedMessageException(localProcessorStatus);
        return localProcessorStatus;
      }

      public TracingProtos.ProcessorStatus buildPartial()
      {
        int i = 1;
        TracingProtos.ProcessorStatus localProcessorStatus = new TracingProtos.ProcessorStatus(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          TracingProtos.ProcessorStatus.access$2902(localProcessorStatus, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          TracingProtos.ProcessorStatus.access$3002(localProcessorStatus, this.cumulativeProcessTimeMs_);
          if ((j & 0x4) == 4)
            i |= 4;
          TracingProtos.ProcessorStatus.access$3102(localProcessorStatus, this.numFrames_);
          if ((j & 0x8) == 8)
            i |= 8;
          TracingProtos.ProcessorStatus.access$3202(localProcessorStatus, this.dutyPeriod_);
          TracingProtos.ProcessorStatus.access$3302(localProcessorStatus, i);
          return localProcessorStatus;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = TracingProtos.ProcessorStatus.Type.BARCODE_SCANNER;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.cumulativeProcessTimeMs_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.numFrames_ = 0;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.dutyPeriod_ = 1;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearCumulativeProcessTimeMs()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.cumulativeProcessTimeMs_ = 0;
        return this;
      }

      public Builder clearDutyPeriod()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.dutyPeriod_ = 1;
        return this;
      }

      public Builder clearNumFrames()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.numFrames_ = 0;
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = TracingProtos.ProcessorStatus.Type.BARCODE_SCANNER;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public int getCumulativeProcessTimeMs()
      {
        return this.cumulativeProcessTimeMs_;
      }

      public TracingProtos.ProcessorStatus getDefaultInstanceForType()
      {
        return TracingProtos.ProcessorStatus.getDefaultInstance();
      }

      public int getDutyPeriod()
      {
        return this.dutyPeriod_;
      }

      public int getNumFrames()
      {
        return this.numFrames_;
      }

      public TracingProtos.ProcessorStatus.Type getType()
      {
        return this.type_;
      }

      public boolean hasCumulativeProcessTimeMs()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasDutyPeriod()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasNumFrames()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        return hasType();
      }

      public Builder mergeFrom(TracingProtos.ProcessorStatus paramProcessorStatus)
      {
        if (paramProcessorStatus == TracingProtos.ProcessorStatus.getDefaultInstance());
        do
        {
          return this;
          if (paramProcessorStatus.hasType())
            setType(paramProcessorStatus.getType());
          if (paramProcessorStatus.hasCumulativeProcessTimeMs())
            setCumulativeProcessTimeMs(paramProcessorStatus.getCumulativeProcessTimeMs());
          if (paramProcessorStatus.hasNumFrames())
            setNumFrames(paramProcessorStatus.getNumFrames());
        }
        while (!paramProcessorStatus.hasDutyPeriod());
        setDutyPeriod(paramProcessorStatus.getDutyPeriod());
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
            TracingProtos.ProcessorStatus.Type localType = TracingProtos.ProcessorStatus.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.cumulativeProcessTimeMs_ = paramCodedInputStream.readInt32();
            break;
          case 24:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.numFrames_ = paramCodedInputStream.readInt32();
            break;
          case 32:
          }
          this.bitField0_ = (0x8 | this.bitField0_);
          this.dutyPeriod_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setCumulativeProcessTimeMs(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.cumulativeProcessTimeMs_ = paramInt;
        return this;
      }

      public Builder setDutyPeriod(int paramInt)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.dutyPeriod_ = paramInt;
        return this;
      }

      public Builder setNumFrames(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.numFrames_ = paramInt;
        return this;
      }

      public Builder setType(TracingProtos.ProcessorStatus.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int ANNOTATION_TRACKER_VALUE = 2;
      public static final int BARCODE_SCANNER_VALUE = 1;
      public static final int IMAGE_BLUR_PROCESSOR_VALUE = 3;
      public static final int REQUEST_PIPELINE_VALUE = 4;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public TracingProtos.ProcessorStatus.Type findValueByNumber(int paramAnonymousInt)
        {
          return TracingProtos.ProcessorStatus.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        ANNOTATION_TRACKER = new Type("ANNOTATION_TRACKER", 1, 1, 2);
        IMAGE_BLUR_PROCESSOR = new Type("IMAGE_BLUR_PROCESSOR", 2, 2, 3);
        REQUEST_PIPELINE = new Type("REQUEST_PIPELINE", 3, 3, 4);
        Type[] arrayOfType = new Type[4];
        arrayOfType[0] = BARCODE_SCANNER;
        arrayOfType[1] = ANNOTATION_TRACKER;
        arrayOfType[2] = IMAGE_BLUR_PROCESSOR;
        arrayOfType[3] = REQUEST_PIPELINE;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return BARCODE_SCANNER;
        case 2:
          return ANNOTATION_TRACKER;
        case 3:
          return IMAGE_BLUR_PROCESSOR;
        case 4:
        }
        return REQUEST_PIPELINE;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface ProcessorStatusOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getCumulativeProcessTimeMs();

    public abstract int getDutyPeriod();

    public abstract int getNumFrames();

    public abstract TracingProtos.ProcessorStatus.Type getType();

    public abstract boolean hasCumulativeProcessTimeMs();

    public abstract boolean hasDutyPeriod();

    public abstract boolean hasNumFrames();

    public abstract boolean hasType();
  }

  public static final class SpanVariable extends GeneratedMessageLite
    implements TracingProtos.SpanVariableOrBuilder
  {
    public static final int DURATION_MS_FIELD_NUMBER = 3;
    public static final int START_MS_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final SpanVariable defaultInstance = new SpanVariable(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private int durationMs_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private int startMs_;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private SpanVariable(Builder paramBuilder)
    {
      super();
    }

    private SpanVariable(boolean paramBoolean)
    {
    }

    public static SpanVariable getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.type_ = Type.START_TO_RENDERED;
      this.startMs_ = 0;
      this.durationMs_ = 0;
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(SpanVariable paramSpanVariable)
    {
      return newBuilder().mergeFrom(paramSpanVariable);
    }

    public static SpanVariable parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static SpanVariable parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static SpanVariable parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static SpanVariable parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static SpanVariable parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static SpanVariable parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static SpanVariable parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static SpanVariable parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static SpanVariable parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static SpanVariable parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public SpanVariable getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getDurationMs()
    {
      return this.durationMs_;
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
        k += CodedOutputStream.computeInt32Size(2, this.startMs_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeInt32Size(3, this.durationMs_);
      this.memoizedSerializedSize = k;
      return k;
    }

    public int getStartMs()
    {
      return this.startMs_;
    }

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasDurationMs()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasStartMs()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasType()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      if (!hasType())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      if (!hasStartMs())
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
        paramCodedOutputStream.writeInt32(2, this.startMs_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(3, this.durationMs_);
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.SpanVariable, Builder>
      implements TracingProtos.SpanVariableOrBuilder
    {
      private int bitField0_;
      private int durationMs_;
      private int startMs_;
      private TracingProtos.SpanVariable.Type type_ = TracingProtos.SpanVariable.Type.START_TO_RENDERED;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.SpanVariable buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.SpanVariable localSpanVariable = buildPartial();
        if (!localSpanVariable.isInitialized())
          throw newUninitializedMessageException(localSpanVariable).asInvalidProtocolBufferException();
        return localSpanVariable;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public TracingProtos.SpanVariable build()
      {
        TracingProtos.SpanVariable localSpanVariable = buildPartial();
        if (!localSpanVariable.isInitialized())
          throw newUninitializedMessageException(localSpanVariable);
        return localSpanVariable;
      }

      public TracingProtos.SpanVariable buildPartial()
      {
        int i = 1;
        TracingProtos.SpanVariable localSpanVariable = new TracingProtos.SpanVariable(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          TracingProtos.SpanVariable.access$302(localSpanVariable, this.type_);
          if ((j & 0x2) == 2)
            i |= 2;
          TracingProtos.SpanVariable.access$402(localSpanVariable, this.startMs_);
          if ((j & 0x4) == 4)
            i |= 4;
          TracingProtos.SpanVariable.access$502(localSpanVariable, this.durationMs_);
          TracingProtos.SpanVariable.access$602(localSpanVariable, i);
          return localSpanVariable;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = TracingProtos.SpanVariable.Type.START_TO_RENDERED;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.startMs_ = 0;
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.durationMs_ = 0;
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearDurationMs()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.durationMs_ = 0;
        return this;
      }

      public Builder clearStartMs()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.startMs_ = 0;
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = TracingProtos.SpanVariable.Type.START_TO_RENDERED;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingProtos.SpanVariable getDefaultInstanceForType()
      {
        return TracingProtos.SpanVariable.getDefaultInstance();
      }

      public int getDurationMs()
      {
        return this.durationMs_;
      }

      public int getStartMs()
      {
        return this.startMs_;
      }

      public TracingProtos.SpanVariable.Type getType()
      {
        return this.type_;
      }

      public boolean hasDurationMs()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasStartMs()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasType());
        while (!hasStartMs())
          return false;
        return true;
      }

      public Builder mergeFrom(TracingProtos.SpanVariable paramSpanVariable)
      {
        if (paramSpanVariable == TracingProtos.SpanVariable.getDefaultInstance());
        do
        {
          return this;
          if (paramSpanVariable.hasType())
            setType(paramSpanVariable.getType());
          if (paramSpanVariable.hasStartMs())
            setStartMs(paramSpanVariable.getStartMs());
        }
        while (!paramSpanVariable.hasDurationMs());
        setDurationMs(paramSpanVariable.getDurationMs());
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
            TracingProtos.SpanVariable.Type localType = TracingProtos.SpanVariable.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 16:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.startMs_ = paramCodedInputStream.readInt32();
            break;
          case 24:
          }
          this.bitField0_ = (0x4 | this.bitField0_);
          this.durationMs_ = paramCodedInputStream.readInt32();
        }
      }

      public Builder setDurationMs(int paramInt)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.durationMs_ = paramInt;
        return this;
      }

      public Builder setStartMs(int paramInt)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.startMs_ = paramInt;
        return this;
      }

      public Builder setType(TracingProtos.SpanVariable.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int ACQUIRE_TO_IMAGE_VALUE = 2;
      public static final int BARCODE_RECOGNIZER_LANDSCAPE_AND_QR_VALUE = 18;
      public static final int BARCODE_RECOGNIZER_LOAD_VALUE = 17;
      public static final int BARCODE_RECOGNIZER_PORTRAIT_VALUE = 19;
      public static final int BARCODE_TOTAL_LATENCY_VALUE = 20;
      public static final int CONTINUOUS_END_TO_END_VALUE = 10;
      public static final int CONTINUOUS_IMAGE_ENCODE_VALUE = 11;
      public static final int CONTINUOUS_IMAGE_TO_IMAGE_VALUE = 16;
      public static final int CONTINUOUS_PUSH_TO_PULL_VALUE = 13;
      public static final int CONTINUOUS_PUSH_VALUE = 12;
      public static final int CONTINUOUS_RENDER_RESPONSE_VALUE = 21;
      public static final int CONTINUOUS_REQUEST_TO_REQUEST_VALUE = 14;
      public static final int CONTINUOUS_THUMBNAIL_FETCH_VALUE = 15;
      public static final int FOCUS_VALUE = 3;
      public static final int IMAGE_REENCODE_VALUE = 4;
      public static final int RENDER_RESULT_VALUE = 6;
      public static final int REQUEST_TO_RESPONSE_VALUE = 5;
      public static final int START_TO_RENDERED_VALUE = 1;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public TracingProtos.SpanVariable.Type findValueByNumber(int paramAnonymousInt)
        {
          return TracingProtos.SpanVariable.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        ACQUIRE_TO_IMAGE = new Type("ACQUIRE_TO_IMAGE", 1, 1, 2);
        FOCUS = new Type("FOCUS", 2, 2, 3);
        IMAGE_REENCODE = new Type("IMAGE_REENCODE", 3, 3, 4);
        REQUEST_TO_RESPONSE = new Type("REQUEST_TO_RESPONSE", 4, 4, 5);
        RENDER_RESULT = new Type("RENDER_RESULT", 5, 5, 6);
        CONTINUOUS_END_TO_END = new Type("CONTINUOUS_END_TO_END", 6, 6, 10);
        CONTINUOUS_IMAGE_TO_IMAGE = new Type("CONTINUOUS_IMAGE_TO_IMAGE", 7, 7, 16);
        CONTINUOUS_IMAGE_ENCODE = new Type("CONTINUOUS_IMAGE_ENCODE", 8, 8, 11);
        CONTINUOUS_PUSH = new Type("CONTINUOUS_PUSH", 9, 9, 12);
        CONTINUOUS_PUSH_TO_PULL = new Type("CONTINUOUS_PUSH_TO_PULL", 10, 10, 13);
        CONTINUOUS_RENDER_RESPONSE = new Type("CONTINUOUS_RENDER_RESPONSE", 11, 11, 21);
        CONTINUOUS_REQUEST_TO_REQUEST = new Type("CONTINUOUS_REQUEST_TO_REQUEST", 12, 12, 14);
        CONTINUOUS_THUMBNAIL_FETCH = new Type("CONTINUOUS_THUMBNAIL_FETCH", 13, 13, 15);
        BARCODE_RECOGNIZER_LOAD = new Type("BARCODE_RECOGNIZER_LOAD", 14, 14, 17);
        BARCODE_RECOGNIZER_LANDSCAPE_AND_QR = new Type("BARCODE_RECOGNIZER_LANDSCAPE_AND_QR", 15, 15, 18);
        BARCODE_RECOGNIZER_PORTRAIT = new Type("BARCODE_RECOGNIZER_PORTRAIT", 16, 16, 19);
        BARCODE_TOTAL_LATENCY = new Type("BARCODE_TOTAL_LATENCY", 17, 17, 20);
        Type[] arrayOfType = new Type[18];
        arrayOfType[0] = START_TO_RENDERED;
        arrayOfType[1] = ACQUIRE_TO_IMAGE;
        arrayOfType[2] = FOCUS;
        arrayOfType[3] = IMAGE_REENCODE;
        arrayOfType[4] = REQUEST_TO_RESPONSE;
        arrayOfType[5] = RENDER_RESULT;
        arrayOfType[6] = CONTINUOUS_END_TO_END;
        arrayOfType[7] = CONTINUOUS_IMAGE_TO_IMAGE;
        arrayOfType[8] = CONTINUOUS_IMAGE_ENCODE;
        arrayOfType[9] = CONTINUOUS_PUSH;
        arrayOfType[10] = CONTINUOUS_PUSH_TO_PULL;
        arrayOfType[11] = CONTINUOUS_RENDER_RESPONSE;
        arrayOfType[12] = CONTINUOUS_REQUEST_TO_REQUEST;
        arrayOfType[13] = CONTINUOUS_THUMBNAIL_FETCH;
        arrayOfType[14] = BARCODE_RECOGNIZER_LOAD;
        arrayOfType[15] = BARCODE_RECOGNIZER_LANDSCAPE_AND_QR;
        arrayOfType[16] = BARCODE_RECOGNIZER_PORTRAIT;
        arrayOfType[17] = BARCODE_TOTAL_LATENCY;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        case 7:
        case 8:
        case 9:
        default:
          return null;
        case 1:
          return START_TO_RENDERED;
        case 2:
          return ACQUIRE_TO_IMAGE;
        case 3:
          return FOCUS;
        case 4:
          return IMAGE_REENCODE;
        case 5:
          return REQUEST_TO_RESPONSE;
        case 6:
          return RENDER_RESULT;
        case 10:
          return CONTINUOUS_END_TO_END;
        case 16:
          return CONTINUOUS_IMAGE_TO_IMAGE;
        case 11:
          return CONTINUOUS_IMAGE_ENCODE;
        case 12:
          return CONTINUOUS_PUSH;
        case 13:
          return CONTINUOUS_PUSH_TO_PULL;
        case 21:
          return CONTINUOUS_RENDER_RESPONSE;
        case 14:
          return CONTINUOUS_REQUEST_TO_REQUEST;
        case 15:
          return CONTINUOUS_THUMBNAIL_FETCH;
        case 17:
          return BARCODE_RECOGNIZER_LOAD;
        case 18:
          return BARCODE_RECOGNIZER_LANDSCAPE_AND_QR;
        case 19:
          return BARCODE_RECOGNIZER_PORTRAIT;
        case 20:
        }
        return BARCODE_TOTAL_LATENCY;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface SpanVariableOrBuilder extends MessageLiteOrBuilder
  {
    public abstract int getDurationMs();

    public abstract int getStartMs();

    public abstract TracingProtos.SpanVariable.Type getType();

    public abstract boolean hasDurationMs();

    public abstract boolean hasStartMs();

    public abstract boolean hasType();
  }

  public static final class TraceAction extends GeneratedMessageLite
    implements TracingProtos.TraceActionOrBuilder
  {
    public static final int ACTION_START_MS_FIELD_NUMBER = 4;
    public static final int DURATION_MS_FIELD_NUMBER = 5;
    public static final int GOGGLES_ID_FIELD_NUMBER = 9;
    public static final int POINT_VARIABLES_FIELD_NUMBER = 3;
    public static final int SERVER_DURATION_MS_FIELD_NUMBER = 10;
    public static final int SPAN_VARIABLES_FIELD_NUMBER = 2;
    public static final int TRACING_COOKIE_FIELD_NUMBER = 8;
    public static final int TRACKING_ID_FIELD_NUMBER = 11;
    public static final int TYPE_FIELD_NUMBER = 1;
    private static final TraceAction defaultInstance = new TraceAction(true);
    private static final long serialVersionUID;
    private long actionStartMs_;
    private int bitField0_;
    private int durationMs_;
    private long gogglesId_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<TracingProtos.PointVariable> pointVariables_;
    private int serverDurationMs_;
    private List<TracingProtos.SpanVariable> spanVariables_;
    private Object tracingCookie_;
    private Object trackingId_;
    private Type type_;

    static
    {
      defaultInstance.initFields();
    }

    private TraceAction(Builder paramBuilder)
    {
      super();
    }

    private TraceAction(boolean paramBoolean)
    {
    }

    public static TraceAction getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getTracingCookieBytes()
    {
      Object localObject = this.tracingCookie_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.tracingCookie_ = localByteString;
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
      this.type_ = Type.GOGGLES;
      this.spanVariables_ = Collections.emptyList();
      this.pointVariables_ = Collections.emptyList();
      this.actionStartMs_ = 0L;
      this.durationMs_ = 0;
      this.serverDurationMs_ = 0;
      this.tracingCookie_ = "";
      this.gogglesId_ = 0L;
      this.trackingId_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$1400();
    }

    public static Builder newBuilder(TraceAction paramTraceAction)
    {
      return newBuilder().mergeFrom(paramTraceAction);
    }

    public static TraceAction parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceAction parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceAction parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TraceAction parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceAction parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TraceAction parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TraceAction parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TraceAction parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceAction parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TraceAction parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public long getActionStartMs()
    {
      return this.actionStartMs_;
    }

    public TraceAction getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getDurationMs()
    {
      return this.durationMs_;
    }

    @Deprecated
    public long getGogglesId()
    {
      return this.gogglesId_;
    }

    public TracingProtos.PointVariable getPointVariables(int paramInt)
    {
      return (TracingProtos.PointVariable)this.pointVariables_.get(paramInt);
    }

    public int getPointVariablesCount()
    {
      return this.pointVariables_.size();
    }

    public List<TracingProtos.PointVariable> getPointVariablesList()
    {
      return this.pointVariables_;
    }

    public TracingProtos.PointVariableOrBuilder getPointVariablesOrBuilder(int paramInt)
    {
      return (TracingProtos.PointVariableOrBuilder)this.pointVariables_.get(paramInt);
    }

    public List<? extends TracingProtos.PointVariableOrBuilder> getPointVariablesOrBuilderList()
    {
      return this.pointVariables_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      if ((0x1 & this.bitField0_) == 1);
      for (int j = 0 + CodedOutputStream.computeEnumSize(1, this.type_.getNumber()); ; j = 0)
      {
        int k = 0;
        int m = j;
        int i1;
        while (true)
        {
          int n = this.spanVariables_.size();
          i1 = 0;
          if (k >= n)
            break;
          m += CodedOutputStream.computeMessageSize(2, (MessageLite)this.spanVariables_.get(k));
          k++;
        }
        while (i1 < this.pointVariables_.size())
        {
          m += CodedOutputStream.computeMessageSize(3, (MessageLite)this.pointVariables_.get(i1));
          i1++;
        }
        if ((0x2 & this.bitField0_) == 2)
          m += CodedOutputStream.computeInt64Size(4, this.actionStartMs_);
        if ((0x4 & this.bitField0_) == 4)
          m += CodedOutputStream.computeInt32Size(5, this.durationMs_);
        if ((0x10 & this.bitField0_) == 16)
          m += CodedOutputStream.computeBytesSize(8, getTracingCookieBytes());
        if ((0x20 & this.bitField0_) == 32)
          m += CodedOutputStream.computeFixed64Size(9, this.gogglesId_);
        if ((0x8 & this.bitField0_) == 8)
          m += CodedOutputStream.computeInt32Size(10, this.serverDurationMs_);
        if ((0x40 & this.bitField0_) == 64)
          m += CodedOutputStream.computeBytesSize(11, getTrackingIdBytes());
        this.memoizedSerializedSize = m;
        return m;
      }
    }

    public int getServerDurationMs()
    {
      return this.serverDurationMs_;
    }

    public TracingProtos.SpanVariable getSpanVariables(int paramInt)
    {
      return (TracingProtos.SpanVariable)this.spanVariables_.get(paramInt);
    }

    public int getSpanVariablesCount()
    {
      return this.spanVariables_.size();
    }

    public List<TracingProtos.SpanVariable> getSpanVariablesList()
    {
      return this.spanVariables_;
    }

    public TracingProtos.SpanVariableOrBuilder getSpanVariablesOrBuilder(int paramInt)
    {
      return (TracingProtos.SpanVariableOrBuilder)this.spanVariables_.get(paramInt);
    }

    public List<? extends TracingProtos.SpanVariableOrBuilder> getSpanVariablesOrBuilderList()
    {
      return this.spanVariables_;
    }

    public String getTracingCookie()
    {
      Object localObject = this.tracingCookie_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.tracingCookie_ = str;
      return str;
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

    public Type getType()
    {
      return this.type_;
    }

    public boolean hasActionStartMs()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public boolean hasDurationMs()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    @Deprecated
    public boolean hasGogglesId()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasServerDurationMs()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasTracingCookie()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasTrackingId()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasType()
    {
      return (0x1 & this.bitField0_) == 1;
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
      if (!hasType())
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      for (int k = 0; k < getSpanVariablesCount(); k++)
        if (!getSpanVariables(k).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (int m = 0; m < getPointVariablesCount(); m++)
        if (!getPointVariables(m).isInitialized())
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
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeEnum(1, this.type_.getNumber());
      int k;
      for (int i = 0; ; i++)
      {
        int j = this.spanVariables_.size();
        k = 0;
        if (i >= j)
          break;
        paramCodedOutputStream.writeMessage(2, (MessageLite)this.spanVariables_.get(i));
      }
      while (k < this.pointVariables_.size())
      {
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.pointVariables_.get(k));
        k++;
      }
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeInt64(4, this.actionStartMs_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeInt32(5, this.durationMs_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(8, getTracingCookieBytes());
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeFixed64(9, this.gogglesId_);
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeInt32(10, this.serverDurationMs_);
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(11, getTrackingIdBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.TraceAction, Builder>
      implements TracingProtos.TraceActionOrBuilder
    {
      private long actionStartMs_;
      private int bitField0_;
      private int durationMs_;
      private long gogglesId_;
      private List<TracingProtos.PointVariable> pointVariables_ = Collections.emptyList();
      private int serverDurationMs_;
      private List<TracingProtos.SpanVariable> spanVariables_ = Collections.emptyList();
      private Object tracingCookie_ = "";
      private Object trackingId_ = "";
      private TracingProtos.TraceAction.Type type_ = TracingProtos.TraceAction.Type.GOGGLES;

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.TraceAction buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.TraceAction localTraceAction = buildPartial();
        if (!localTraceAction.isInitialized())
          throw newUninitializedMessageException(localTraceAction).asInvalidProtocolBufferException();
        return localTraceAction;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensurePointVariablesIsMutable()
      {
        if ((0x4 & this.bitField0_) != 4)
        {
          this.pointVariables_ = new ArrayList(this.pointVariables_);
          this.bitField0_ = (0x4 | this.bitField0_);
        }
      }

      private void ensureSpanVariablesIsMutable()
      {
        if ((0x2 & this.bitField0_) != 2)
        {
          this.spanVariables_ = new ArrayList(this.spanVariables_);
          this.bitField0_ = (0x2 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllPointVariables(Iterable<? extends TracingProtos.PointVariable> paramIterable)
      {
        ensurePointVariablesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.pointVariables_);
        return this;
      }

      public Builder addAllSpanVariables(Iterable<? extends TracingProtos.SpanVariable> paramIterable)
      {
        ensureSpanVariablesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.spanVariables_);
        return this;
      }

      public Builder addPointVariables(int paramInt, TracingProtos.PointVariable.Builder paramBuilder)
      {
        ensurePointVariablesIsMutable();
        this.pointVariables_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addPointVariables(int paramInt, TracingProtos.PointVariable paramPointVariable)
      {
        if (paramPointVariable == null)
          throw new NullPointerException();
        ensurePointVariablesIsMutable();
        this.pointVariables_.add(paramInt, paramPointVariable);
        return this;
      }

      public Builder addPointVariables(TracingProtos.PointVariable.Builder paramBuilder)
      {
        ensurePointVariablesIsMutable();
        this.pointVariables_.add(paramBuilder.build());
        return this;
      }

      public Builder addPointVariables(TracingProtos.PointVariable paramPointVariable)
      {
        if (paramPointVariable == null)
          throw new NullPointerException();
        ensurePointVariablesIsMutable();
        this.pointVariables_.add(paramPointVariable);
        return this;
      }

      public Builder addSpanVariables(int paramInt, TracingProtos.SpanVariable.Builder paramBuilder)
      {
        ensureSpanVariablesIsMutable();
        this.spanVariables_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addSpanVariables(int paramInt, TracingProtos.SpanVariable paramSpanVariable)
      {
        if (paramSpanVariable == null)
          throw new NullPointerException();
        ensureSpanVariablesIsMutable();
        this.spanVariables_.add(paramInt, paramSpanVariable);
        return this;
      }

      public Builder addSpanVariables(TracingProtos.SpanVariable.Builder paramBuilder)
      {
        ensureSpanVariablesIsMutable();
        this.spanVariables_.add(paramBuilder.build());
        return this;
      }

      public Builder addSpanVariables(TracingProtos.SpanVariable paramSpanVariable)
      {
        if (paramSpanVariable == null)
          throw new NullPointerException();
        ensureSpanVariablesIsMutable();
        this.spanVariables_.add(paramSpanVariable);
        return this;
      }

      public TracingProtos.TraceAction build()
      {
        TracingProtos.TraceAction localTraceAction = buildPartial();
        if (!localTraceAction.isInitialized())
          throw newUninitializedMessageException(localTraceAction);
        return localTraceAction;
      }

      public TracingProtos.TraceAction buildPartial()
      {
        int i = 1;
        TracingProtos.TraceAction localTraceAction = new TracingProtos.TraceAction(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          TracingProtos.TraceAction.access$1602(localTraceAction, this.type_);
          if ((0x2 & this.bitField0_) == 2)
          {
            this.spanVariables_ = Collections.unmodifiableList(this.spanVariables_);
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          }
          TracingProtos.TraceAction.access$1702(localTraceAction, this.spanVariables_);
          if ((0x4 & this.bitField0_) == 4)
          {
            this.pointVariables_ = Collections.unmodifiableList(this.pointVariables_);
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          }
          TracingProtos.TraceAction.access$1802(localTraceAction, this.pointVariables_);
          if ((j & 0x8) == 8)
            i |= 2;
          TracingProtos.TraceAction.access$1902(localTraceAction, this.actionStartMs_);
          if ((j & 0x10) == 16)
            i |= 4;
          TracingProtos.TraceAction.access$2002(localTraceAction, this.durationMs_);
          if ((j & 0x20) == 32)
            i |= 8;
          TracingProtos.TraceAction.access$2102(localTraceAction, this.serverDurationMs_);
          if ((j & 0x40) == 64)
            i |= 16;
          TracingProtos.TraceAction.access$2202(localTraceAction, this.tracingCookie_);
          if ((j & 0x80) == 128)
            i |= 32;
          TracingProtos.TraceAction.access$2302(localTraceAction, this.gogglesId_);
          if ((j & 0x100) == 256)
            i |= 64;
          TracingProtos.TraceAction.access$2402(localTraceAction, this.trackingId_);
          TracingProtos.TraceAction.access$2502(localTraceAction, i);
          return localTraceAction;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.type_ = TracingProtos.TraceAction.Type.GOGGLES;
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.spanVariables_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.pointVariables_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.actionStartMs_ = 0L;
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.durationMs_ = 0;
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.serverDurationMs_ = 0;
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.tracingCookie_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.gogglesId_ = 0L;
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.trackingId_ = "";
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clearActionStartMs()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.actionStartMs_ = 0L;
        return this;
      }

      public Builder clearDurationMs()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.durationMs_ = 0;
        return this;
      }

      @Deprecated
      public Builder clearGogglesId()
      {
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.gogglesId_ = 0L;
        return this;
      }

      public Builder clearPointVariables()
      {
        this.pointVariables_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearServerDurationMs()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.serverDurationMs_ = 0;
        return this;
      }

      public Builder clearSpanVariables()
      {
        this.spanVariables_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearTracingCookie()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.tracingCookie_ = TracingProtos.TraceAction.getDefaultInstance().getTracingCookie();
        return this;
      }

      public Builder clearTrackingId()
      {
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.trackingId_ = TracingProtos.TraceAction.getDefaultInstance().getTrackingId();
        return this;
      }

      public Builder clearType()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.type_ = TracingProtos.TraceAction.Type.GOGGLES;
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public long getActionStartMs()
      {
        return this.actionStartMs_;
      }

      public TracingProtos.TraceAction getDefaultInstanceForType()
      {
        return TracingProtos.TraceAction.getDefaultInstance();
      }

      public int getDurationMs()
      {
        return this.durationMs_;
      }

      @Deprecated
      public long getGogglesId()
      {
        return this.gogglesId_;
      }

      public TracingProtos.PointVariable getPointVariables(int paramInt)
      {
        return (TracingProtos.PointVariable)this.pointVariables_.get(paramInt);
      }

      public int getPointVariablesCount()
      {
        return this.pointVariables_.size();
      }

      public List<TracingProtos.PointVariable> getPointVariablesList()
      {
        return Collections.unmodifiableList(this.pointVariables_);
      }

      public int getServerDurationMs()
      {
        return this.serverDurationMs_;
      }

      public TracingProtos.SpanVariable getSpanVariables(int paramInt)
      {
        return (TracingProtos.SpanVariable)this.spanVariables_.get(paramInt);
      }

      public int getSpanVariablesCount()
      {
        return this.spanVariables_.size();
      }

      public List<TracingProtos.SpanVariable> getSpanVariablesList()
      {
        return Collections.unmodifiableList(this.spanVariables_);
      }

      public String getTracingCookie()
      {
        Object localObject = this.tracingCookie_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.tracingCookie_ = str;
          return str;
        }
        return (String)localObject;
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

      public TracingProtos.TraceAction.Type getType()
      {
        return this.type_;
      }

      public boolean hasActionStartMs()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasDurationMs()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      @Deprecated
      public boolean hasGogglesId()
      {
        return (0x80 & this.bitField0_) == 128;
      }

      public boolean hasServerDurationMs()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasTracingCookie()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasTrackingId()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      public boolean hasType()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public final boolean isInitialized()
      {
        if (!hasType())
          return false;
        for (int i = 0; ; i++)
        {
          if (i >= getSpanVariablesCount())
            break label36;
          if (!getSpanVariables(i).isInitialized())
            break;
        }
        label36: for (int j = 0; ; j++)
        {
          if (j >= getPointVariablesCount())
            break label63;
          if (!getPointVariables(j).isInitialized())
            break;
        }
        label63: return true;
      }

      public Builder mergeFrom(TracingProtos.TraceAction paramTraceAction)
      {
        if (paramTraceAction == TracingProtos.TraceAction.getDefaultInstance())
          return this;
        if (paramTraceAction.hasType())
          setType(paramTraceAction.getType());
        if (!paramTraceAction.spanVariables_.isEmpty())
        {
          if (this.spanVariables_.isEmpty())
          {
            this.spanVariables_ = paramTraceAction.spanVariables_;
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
          }
        }
        else
          label68: if (!paramTraceAction.pointVariables_.isEmpty())
          {
            if (!this.pointVariables_.isEmpty())
              break label230;
            this.pointVariables_ = paramTraceAction.pointVariables_;
            this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
          }
        while (true)
        {
          if (paramTraceAction.hasActionStartMs())
            setActionStartMs(paramTraceAction.getActionStartMs());
          if (paramTraceAction.hasDurationMs())
            setDurationMs(paramTraceAction.getDurationMs());
          if (paramTraceAction.hasServerDurationMs())
            setServerDurationMs(paramTraceAction.getServerDurationMs());
          if (paramTraceAction.hasTracingCookie())
            setTracingCookie(paramTraceAction.getTracingCookie());
          if (paramTraceAction.hasGogglesId())
            setGogglesId(paramTraceAction.getGogglesId());
          if (!paramTraceAction.hasTrackingId())
            break;
          setTrackingId(paramTraceAction.getTrackingId());
          return this;
          ensureSpanVariablesIsMutable();
          this.spanVariables_.addAll(paramTraceAction.spanVariables_);
          break label68;
          label230: ensurePointVariablesIsMutable();
          this.pointVariables_.addAll(paramTraceAction.pointVariables_);
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
          case 8:
            TracingProtos.TraceAction.Type localType = TracingProtos.TraceAction.Type.valueOf(paramCodedInputStream.readEnum());
            if (localType == null)
              continue;
            this.bitField0_ = (0x1 | this.bitField0_);
            this.type_ = localType;
            break;
          case 18:
            TracingProtos.SpanVariable.Builder localBuilder1 = TracingProtos.SpanVariable.newBuilder();
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            addSpanVariables(localBuilder1.buildPartial());
            break;
          case 26:
            TracingProtos.PointVariable.Builder localBuilder = TracingProtos.PointVariable.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addPointVariables(localBuilder.buildPartial());
            break;
          case 32:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.actionStartMs_ = paramCodedInputStream.readInt64();
            break;
          case 40:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.durationMs_ = paramCodedInputStream.readInt32();
            break;
          case 66:
            this.bitField0_ = (0x40 | this.bitField0_);
            this.tracingCookie_ = paramCodedInputStream.readBytes();
            break;
          case 73:
            this.bitField0_ = (0x80 | this.bitField0_);
            this.gogglesId_ = paramCodedInputStream.readFixed64();
            break;
          case 80:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.serverDurationMs_ = paramCodedInputStream.readInt32();
            break;
          case 90:
          }
          this.bitField0_ = (0x100 | this.bitField0_);
          this.trackingId_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder removePointVariables(int paramInt)
      {
        ensurePointVariablesIsMutable();
        this.pointVariables_.remove(paramInt);
        return this;
      }

      public Builder removeSpanVariables(int paramInt)
      {
        ensureSpanVariablesIsMutable();
        this.spanVariables_.remove(paramInt);
        return this;
      }

      public Builder setActionStartMs(long paramLong)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.actionStartMs_ = paramLong;
        return this;
      }

      public Builder setDurationMs(int paramInt)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.durationMs_ = paramInt;
        return this;
      }

      @Deprecated
      public Builder setGogglesId(long paramLong)
      {
        this.bitField0_ = (0x80 | this.bitField0_);
        this.gogglesId_ = paramLong;
        return this;
      }

      public Builder setPointVariables(int paramInt, TracingProtos.PointVariable.Builder paramBuilder)
      {
        ensurePointVariablesIsMutable();
        this.pointVariables_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setPointVariables(int paramInt, TracingProtos.PointVariable paramPointVariable)
      {
        if (paramPointVariable == null)
          throw new NullPointerException();
        ensurePointVariablesIsMutable();
        this.pointVariables_.set(paramInt, paramPointVariable);
        return this;
      }

      public Builder setServerDurationMs(int paramInt)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.serverDurationMs_ = paramInt;
        return this;
      }

      public Builder setSpanVariables(int paramInt, TracingProtos.SpanVariable.Builder paramBuilder)
      {
        ensureSpanVariablesIsMutable();
        this.spanVariables_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setSpanVariables(int paramInt, TracingProtos.SpanVariable paramSpanVariable)
      {
        if (paramSpanVariable == null)
          throw new NullPointerException();
        ensureSpanVariablesIsMutable();
        this.spanVariables_.set(paramInt, paramSpanVariable);
        return this;
      }

      public Builder setTracingCookie(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.tracingCookie_ = paramString;
        return this;
      }

      void setTracingCookie(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.tracingCookie_ = paramByteString;
      }

      public Builder setTrackingId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x100 | this.bitField0_);
        this.trackingId_ = paramString;
        return this;
      }

      void setTrackingId(ByteString paramByteString)
      {
        this.bitField0_ = (0x100 | this.bitField0_);
        this.trackingId_ = paramByteString;
      }

      public Builder setType(TracingProtos.TraceAction.Type paramType)
      {
        if (paramType == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.type_ = paramType;
        return this;
      }
    }

    public static enum Type
      implements Internal.EnumLite
    {
      public static final int CONTINUOUS_GOGGLES_VALUE = 2;
      public static final int GOGGLES_VALUE = 1;
      private static Internal.EnumLiteMap<Type> internalValueMap = new Internal.EnumLiteMap()
      {
        public TracingProtos.TraceAction.Type findValueByNumber(int paramAnonymousInt)
        {
          return TracingProtos.TraceAction.Type.valueOf(paramAnonymousInt);
        }
      };
      private final int value;

      static
      {
        CONTINUOUS_GOGGLES = new Type("CONTINUOUS_GOGGLES", 1, 1, 2);
        Type[] arrayOfType = new Type[2];
        arrayOfType[0] = GOGGLES;
        arrayOfType[1] = CONTINUOUS_GOGGLES;
      }

      private Type(int paramInt1, int paramInt2)
      {
        this.value = paramInt2;
      }

      public static Internal.EnumLiteMap<Type> internalGetValueMap()
      {
        return internalValueMap;
      }

      public static Type valueOf(int paramInt)
      {
        switch (paramInt)
        {
        default:
          return null;
        case 1:
          return GOGGLES;
        case 2:
        }
        return CONTINUOUS_GOGGLES;
      }

      public final int getNumber()
      {
        return this.value;
      }
    }
  }

  public static abstract interface TraceActionOrBuilder extends MessageLiteOrBuilder
  {
    public abstract long getActionStartMs();

    public abstract int getDurationMs();

    @Deprecated
    public abstract long getGogglesId();

    public abstract TracingProtos.PointVariable getPointVariables(int paramInt);

    public abstract int getPointVariablesCount();

    public abstract List<TracingProtos.PointVariable> getPointVariablesList();

    public abstract int getServerDurationMs();

    public abstract TracingProtos.SpanVariable getSpanVariables(int paramInt);

    public abstract int getSpanVariablesCount();

    public abstract List<TracingProtos.SpanVariable> getSpanVariablesList();

    public abstract String getTracingCookie();

    public abstract String getTrackingId();

    public abstract TracingProtos.TraceAction.Type getType();

    public abstract boolean hasActionStartMs();

    public abstract boolean hasDurationMs();

    @Deprecated
    public abstract boolean hasGogglesId();

    public abstract boolean hasServerDurationMs();

    public abstract boolean hasTracingCookie();

    public abstract boolean hasTrackingId();

    public abstract boolean hasType();
  }

  public static final class TraceRequest extends GeneratedMessageLite
    implements TracingProtos.TraceRequestOrBuilder
  {
    public static final int DEVICE_INFO_FIELD_NUMBER = 2;
    public static final int NETWORK_INFO_FIELD_NUMBER = 3;
    public static final int PROCESSOR_STATUS_FIELD_NUMBER = 4;
    public static final int TRACE_ACTION_FIELD_NUMBER = 1;
    private static final TraceRequest defaultInstance = new TraceRequest(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private DeviceInfoProtos.DeviceInfo deviceInfo_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private NetworkInfoProtos.NetworkInfo networkInfo_;
    private List<TracingProtos.ProcessorStatus> processorStatus_;
    private List<TracingProtos.TraceAction> traceAction_;

    static
    {
      defaultInstance.initFields();
    }

    private TraceRequest(Builder paramBuilder)
    {
      super();
    }

    private TraceRequest(boolean paramBoolean)
    {
    }

    public static TraceRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.traceAction_ = Collections.emptyList();
      this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
      this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
      this.processorStatus_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$3500();
    }

    public static Builder newBuilder(TraceRequest paramTraceRequest)
    {
      return newBuilder().mergeFrom(paramTraceRequest);
    }

    public static TraceRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TraceRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TraceRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TraceRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TraceRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TraceRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public TraceRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public DeviceInfoProtos.DeviceInfo getDeviceInfo()
    {
      return this.deviceInfo_;
    }

    public NetworkInfoProtos.NetworkInfo getNetworkInfo()
    {
      return this.networkInfo_;
    }

    public TracingProtos.ProcessorStatus getProcessorStatus(int paramInt)
    {
      return (TracingProtos.ProcessorStatus)this.processorStatus_.get(paramInt);
    }

    public int getProcessorStatusCount()
    {
      return this.processorStatus_.size();
    }

    public List<TracingProtos.ProcessorStatus> getProcessorStatusList()
    {
      return this.processorStatus_;
    }

    public TracingProtos.ProcessorStatusOrBuilder getProcessorStatusOrBuilder(int paramInt)
    {
      return (TracingProtos.ProcessorStatusOrBuilder)this.processorStatus_.get(paramInt);
    }

    public List<? extends TracingProtos.ProcessorStatusOrBuilder> getProcessorStatusOrBuilderList()
    {
      return this.processorStatus_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.traceAction_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.traceAction_.get(j));
        j++;
      }
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(2, this.deviceInfo_);
      int m = 0x2 & this.bitField0_;
      int n = 0;
      if (m == 2)
        k += CodedOutputStream.computeMessageSize(3, this.networkInfo_);
      while (n < this.processorStatus_.size())
      {
        k += CodedOutputStream.computeMessageSize(4, (MessageLite)this.processorStatus_.get(n));
        n++;
      }
      this.memoizedSerializedSize = k;
      return k;
    }

    public TracingProtos.TraceAction getTraceAction(int paramInt)
    {
      return (TracingProtos.TraceAction)this.traceAction_.get(paramInt);
    }

    public int getTraceActionCount()
    {
      return this.traceAction_.size();
    }

    public List<TracingProtos.TraceAction> getTraceActionList()
    {
      return this.traceAction_;
    }

    public TracingProtos.TraceActionOrBuilder getTraceActionOrBuilder(int paramInt)
    {
      return (TracingProtos.TraceActionOrBuilder)this.traceAction_.get(paramInt);
    }

    public List<? extends TracingProtos.TraceActionOrBuilder> getTraceActionOrBuilderList()
    {
      return this.traceAction_;
    }

    public boolean hasDeviceInfo()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasNetworkInfo()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      for (int j = 0; j < getTraceActionCount(); j++)
        if (!getTraceAction(j).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (int k = 0; k < getProcessorStatusCount(); k++)
        if (!getProcessorStatus(k).isInitialized())
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
      for (int i = 0; i < this.traceAction_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.traceAction_.get(i));
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(2, this.deviceInfo_);
      int j = 0x2 & this.bitField0_;
      int k = 0;
      if (j == 2)
        paramCodedOutputStream.writeMessage(3, this.networkInfo_);
      while (k < this.processorStatus_.size())
      {
        paramCodedOutputStream.writeMessage(4, (MessageLite)this.processorStatus_.get(k));
        k++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.TraceRequest, Builder>
      implements TracingProtos.TraceRequestOrBuilder
    {
      private int bitField0_;
      private DeviceInfoProtos.DeviceInfo deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
      private NetworkInfoProtos.NetworkInfo networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
      private List<TracingProtos.ProcessorStatus> processorStatus_ = Collections.emptyList();
      private List<TracingProtos.TraceAction> traceAction_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.TraceRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.TraceRequest localTraceRequest = buildPartial();
        if (!localTraceRequest.isInitialized())
          throw newUninitializedMessageException(localTraceRequest).asInvalidProtocolBufferException();
        return localTraceRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureProcessorStatusIsMutable()
      {
        if ((0x8 & this.bitField0_) != 8)
        {
          this.processorStatus_ = new ArrayList(this.processorStatus_);
          this.bitField0_ = (0x8 | this.bitField0_);
        }
      }

      private void ensureTraceActionIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.traceAction_ = new ArrayList(this.traceAction_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllProcessorStatus(Iterable<? extends TracingProtos.ProcessorStatus> paramIterable)
      {
        ensureProcessorStatusIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.processorStatus_);
        return this;
      }

      public Builder addAllTraceAction(Iterable<? extends TracingProtos.TraceAction> paramIterable)
      {
        ensureTraceActionIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.traceAction_);
        return this;
      }

      public Builder addProcessorStatus(int paramInt, TracingProtos.ProcessorStatus.Builder paramBuilder)
      {
        ensureProcessorStatusIsMutable();
        this.processorStatus_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addProcessorStatus(int paramInt, TracingProtos.ProcessorStatus paramProcessorStatus)
      {
        if (paramProcessorStatus == null)
          throw new NullPointerException();
        ensureProcessorStatusIsMutable();
        this.processorStatus_.add(paramInt, paramProcessorStatus);
        return this;
      }

      public Builder addProcessorStatus(TracingProtos.ProcessorStatus.Builder paramBuilder)
      {
        ensureProcessorStatusIsMutable();
        this.processorStatus_.add(paramBuilder.build());
        return this;
      }

      public Builder addProcessorStatus(TracingProtos.ProcessorStatus paramProcessorStatus)
      {
        if (paramProcessorStatus == null)
          throw new NullPointerException();
        ensureProcessorStatusIsMutable();
        this.processorStatus_.add(paramProcessorStatus);
        return this;
      }

      public Builder addTraceAction(int paramInt, TracingProtos.TraceAction.Builder paramBuilder)
      {
        ensureTraceActionIsMutable();
        this.traceAction_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addTraceAction(int paramInt, TracingProtos.TraceAction paramTraceAction)
      {
        if (paramTraceAction == null)
          throw new NullPointerException();
        ensureTraceActionIsMutable();
        this.traceAction_.add(paramInt, paramTraceAction);
        return this;
      }

      public Builder addTraceAction(TracingProtos.TraceAction.Builder paramBuilder)
      {
        ensureTraceActionIsMutable();
        this.traceAction_.add(paramBuilder.build());
        return this;
      }

      public Builder addTraceAction(TracingProtos.TraceAction paramTraceAction)
      {
        if (paramTraceAction == null)
          throw new NullPointerException();
        ensureTraceActionIsMutable();
        this.traceAction_.add(paramTraceAction);
        return this;
      }

      public TracingProtos.TraceRequest build()
      {
        TracingProtos.TraceRequest localTraceRequest = buildPartial();
        if (!localTraceRequest.isInitialized())
          throw newUninitializedMessageException(localTraceRequest);
        return localTraceRequest;
      }

      public TracingProtos.TraceRequest buildPartial()
      {
        int i = 1;
        TracingProtos.TraceRequest localTraceRequest = new TracingProtos.TraceRequest(this, null);
        int j = this.bitField0_;
        if ((0x1 & this.bitField0_) == i)
        {
          this.traceAction_ = Collections.unmodifiableList(this.traceAction_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        TracingProtos.TraceRequest.access$3702(localTraceRequest, this.traceAction_);
        if ((j & 0x2) == 2);
        while (true)
        {
          TracingProtos.TraceRequest.access$3802(localTraceRequest, this.deviceInfo_);
          if ((j & 0x4) == 4)
            i |= 2;
          TracingProtos.TraceRequest.access$3902(localTraceRequest, this.networkInfo_);
          if ((0x8 & this.bitField0_) == 8)
          {
            this.processorStatus_ = Collections.unmodifiableList(this.processorStatus_);
            this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          }
          TracingProtos.TraceRequest.access$4002(localTraceRequest, this.processorStatus_);
          TracingProtos.TraceRequest.access$4102(localTraceRequest, i);
          return localTraceRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.traceAction_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.processorStatus_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearDeviceInfo()
      {
        this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearNetworkInfo()
      {
        this.networkInfo_ = NetworkInfoProtos.NetworkInfo.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      public Builder clearProcessorStatus()
      {
        this.processorStatus_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clearTraceAction()
      {
        this.traceAction_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingProtos.TraceRequest getDefaultInstanceForType()
      {
        return TracingProtos.TraceRequest.getDefaultInstance();
      }

      public DeviceInfoProtos.DeviceInfo getDeviceInfo()
      {
        return this.deviceInfo_;
      }

      public NetworkInfoProtos.NetworkInfo getNetworkInfo()
      {
        return this.networkInfo_;
      }

      public TracingProtos.ProcessorStatus getProcessorStatus(int paramInt)
      {
        return (TracingProtos.ProcessorStatus)this.processorStatus_.get(paramInt);
      }

      public int getProcessorStatusCount()
      {
        return this.processorStatus_.size();
      }

      public List<TracingProtos.ProcessorStatus> getProcessorStatusList()
      {
        return Collections.unmodifiableList(this.processorStatus_);
      }

      public TracingProtos.TraceAction getTraceAction(int paramInt)
      {
        return (TracingProtos.TraceAction)this.traceAction_.get(paramInt);
      }

      public int getTraceActionCount()
      {
        return this.traceAction_.size();
      }

      public List<TracingProtos.TraceAction> getTraceActionList()
      {
        return Collections.unmodifiableList(this.traceAction_);
      }

      public boolean hasDeviceInfo()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public boolean hasNetworkInfo()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public final boolean isInitialized()
      {
        for (int i = 0; i < getTraceActionCount(); i++)
          if (!getTraceAction(i).isInitialized())
            return false;
        for (int j = 0; ; j++)
        {
          if (j >= getProcessorStatusCount())
            break label56;
          if (!getProcessorStatus(j).isInitialized())
            break;
        }
        label56: return true;
      }

      public Builder mergeDeviceInfo(DeviceInfoProtos.DeviceInfo paramDeviceInfo)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.deviceInfo_ != DeviceInfoProtos.DeviceInfo.getDefaultInstance()));
        for (this.deviceInfo_ = DeviceInfoProtos.DeviceInfo.newBuilder(this.deviceInfo_).mergeFrom(paramDeviceInfo).buildPartial(); ; this.deviceInfo_ = paramDeviceInfo)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(TracingProtos.TraceRequest paramTraceRequest)
      {
        if (paramTraceRequest == TracingProtos.TraceRequest.getDefaultInstance())
          return this;
        if (!paramTraceRequest.traceAction_.isEmpty())
        {
          if (!this.traceAction_.isEmpty())
            break label129;
          this.traceAction_ = paramTraceRequest.traceAction_;
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        while (true)
        {
          if (paramTraceRequest.hasDeviceInfo())
            mergeDeviceInfo(paramTraceRequest.getDeviceInfo());
          if (paramTraceRequest.hasNetworkInfo())
            mergeNetworkInfo(paramTraceRequest.getNetworkInfo());
          if (paramTraceRequest.processorStatus_.isEmpty())
            break;
          if (!this.processorStatus_.isEmpty())
            break label150;
          this.processorStatus_ = paramTraceRequest.processorStatus_;
          this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
          return this;
          label129: ensureTraceActionIsMutable();
          this.traceAction_.addAll(paramTraceRequest.traceAction_);
        }
        label150: ensureProcessorStatusIsMutable();
        this.processorStatus_.addAll(paramTraceRequest.processorStatus_);
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
            TracingProtos.TraceAction.Builder localBuilder3 = TracingProtos.TraceAction.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addTraceAction(localBuilder3.buildPartial());
            break;
          case 18:
            DeviceInfoProtos.DeviceInfo.Builder localBuilder2 = DeviceInfoProtos.DeviceInfo.newBuilder();
            if (hasDeviceInfo())
              localBuilder2.mergeFrom(getDeviceInfo());
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            setDeviceInfo(localBuilder2.buildPartial());
            break;
          case 26:
            NetworkInfoProtos.NetworkInfo.Builder localBuilder1 = NetworkInfoProtos.NetworkInfo.newBuilder();
            if (hasNetworkInfo())
              localBuilder1.mergeFrom(getNetworkInfo());
            paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
            setNetworkInfo(localBuilder1.buildPartial());
            break;
          case 34:
          }
          TracingProtos.ProcessorStatus.Builder localBuilder = TracingProtos.ProcessorStatus.newBuilder();
          paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
          addProcessorStatus(localBuilder.buildPartial());
        }
      }

      public Builder mergeNetworkInfo(NetworkInfoProtos.NetworkInfo paramNetworkInfo)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.networkInfo_ != NetworkInfoProtos.NetworkInfo.getDefaultInstance()));
        for (this.networkInfo_ = NetworkInfoProtos.NetworkInfo.newBuilder(this.networkInfo_).mergeFrom(paramNetworkInfo).buildPartial(); ; this.networkInfo_ = paramNetworkInfo)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      public Builder removeProcessorStatus(int paramInt)
      {
        ensureProcessorStatusIsMutable();
        this.processorStatus_.remove(paramInt);
        return this;
      }

      public Builder removeTraceAction(int paramInt)
      {
        ensureTraceActionIsMutable();
        this.traceAction_.remove(paramInt);
        return this;
      }

      public Builder setDeviceInfo(DeviceInfoProtos.DeviceInfo.Builder paramBuilder)
      {
        this.deviceInfo_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setDeviceInfo(DeviceInfoProtos.DeviceInfo paramDeviceInfo)
      {
        if (paramDeviceInfo == null)
          throw new NullPointerException();
        this.deviceInfo_ = paramDeviceInfo;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      public Builder setNetworkInfo(NetworkInfoProtos.NetworkInfo.Builder paramBuilder)
      {
        this.networkInfo_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setNetworkInfo(NetworkInfoProtos.NetworkInfo paramNetworkInfo)
      {
        if (paramNetworkInfo == null)
          throw new NullPointerException();
        this.networkInfo_ = paramNetworkInfo;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      public Builder setProcessorStatus(int paramInt, TracingProtos.ProcessorStatus.Builder paramBuilder)
      {
        ensureProcessorStatusIsMutable();
        this.processorStatus_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setProcessorStatus(int paramInt, TracingProtos.ProcessorStatus paramProcessorStatus)
      {
        if (paramProcessorStatus == null)
          throw new NullPointerException();
        ensureProcessorStatusIsMutable();
        this.processorStatus_.set(paramInt, paramProcessorStatus);
        return this;
      }

      public Builder setTraceAction(int paramInt, TracingProtos.TraceAction.Builder paramBuilder)
      {
        ensureTraceActionIsMutable();
        this.traceAction_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setTraceAction(int paramInt, TracingProtos.TraceAction paramTraceAction)
      {
        if (paramTraceAction == null)
          throw new NullPointerException();
        ensureTraceActionIsMutable();
        this.traceAction_.set(paramInt, paramTraceAction);
        return this;
      }
    }
  }

  public static abstract interface TraceRequestOrBuilder extends MessageLiteOrBuilder
  {
    public abstract DeviceInfoProtos.DeviceInfo getDeviceInfo();

    public abstract NetworkInfoProtos.NetworkInfo getNetworkInfo();

    public abstract TracingProtos.ProcessorStatus getProcessorStatus(int paramInt);

    public abstract int getProcessorStatusCount();

    public abstract List<TracingProtos.ProcessorStatus> getProcessorStatusList();

    public abstract TracingProtos.TraceAction getTraceAction(int paramInt);

    public abstract int getTraceActionCount();

    public abstract List<TracingProtos.TraceAction> getTraceActionList();

    public abstract boolean hasDeviceInfo();

    public abstract boolean hasNetworkInfo();
  }

  public static final class TraceResponse extends GeneratedMessageLite
    implements TracingProtos.TraceResponseOrBuilder
  {
    private static final TraceResponse defaultInstance = new TraceResponse(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private TraceResponse(Builder paramBuilder)
    {
      super();
    }

    private TraceResponse(boolean paramBoolean)
    {
    }

    public static TraceResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$4300();
    }

    public static Builder newBuilder(TraceResponse paramTraceResponse)
    {
      return newBuilder().mergeFrom(paramTraceResponse);
    }

    public static TraceResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static TraceResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static TraceResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static TraceResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static TraceResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static TraceResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static TraceResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static TraceResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public TraceResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      this.memoizedSerializedSize = 0;
      return 0;
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
    }

    public static final class Builder extends GeneratedMessageLite.Builder<TracingProtos.TraceResponse, Builder>
      implements TracingProtos.TraceResponseOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private TracingProtos.TraceResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        TracingProtos.TraceResponse localTraceResponse = buildPartial();
        if (!localTraceResponse.isInitialized())
          throw newUninitializedMessageException(localTraceResponse).asInvalidProtocolBufferException();
        return localTraceResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public TracingProtos.TraceResponse build()
      {
        TracingProtos.TraceResponse localTraceResponse = buildPartial();
        if (!localTraceResponse.isInitialized())
          throw newUninitializedMessageException(localTraceResponse);
        return localTraceResponse;
      }

      public TracingProtos.TraceResponse buildPartial()
      {
        return new TracingProtos.TraceResponse(this, null);
      }

      public Builder clear()
      {
        super.clear();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public TracingProtos.TraceResponse getDefaultInstanceForType()
      {
        return TracingProtos.TraceResponse.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(TracingProtos.TraceResponse paramTraceResponse)
      {
        if (paramTraceResponse == TracingProtos.TraceResponse.getDefaultInstance());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        int i;
        do
        {
          i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
          case 0:
          }
        }
        while (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i));
        return this;
      }
    }
  }

  public static abstract interface TraceResponseOrBuilder extends MessageLiteOrBuilder
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.TracingProtos
 * JD-Core Version:    0.6.2
 */