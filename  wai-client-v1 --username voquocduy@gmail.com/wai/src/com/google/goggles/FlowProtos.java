package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
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

public final class FlowProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class FlowData extends GeneratedMessageLite
    implements FlowProtos.FlowDataOrBuilder
  {
    public static final int FRAME_CHANGE_FIELD_NUMBER = 1;
    public static final int PRE_PACKED_CORRESPONDENCES_FIELD_NUMBER = 2;
    private static final FlowData defaultInstance = new FlowData(true);
    private static final long serialVersionUID;
    private List<FlowProtos.FrameChange> frameChange_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<ByteString> prePackedCorrespondences_;

    static
    {
      defaultInstance.initFields();
    }

    private FlowData(Builder paramBuilder)
    {
      super();
    }

    private FlowData(boolean paramBoolean)
    {
    }

    public static FlowData getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.frameChange_ = Collections.emptyList();
      this.prePackedCorrespondences_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$500();
    }

    public static Builder newBuilder(FlowData paramFlowData)
    {
      return newBuilder().mergeFrom(paramFlowData);
    }

    public static FlowData parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FlowData parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FlowData parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FlowData parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FlowData parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FlowData parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static FlowData parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FlowData parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FlowData parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FlowData parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public FlowData getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public FlowProtos.FrameChange getFrameChange(int paramInt)
    {
      return (FlowProtos.FrameChange)this.frameChange_.get(paramInt);
    }

    public int getFrameChangeCount()
    {
      return this.frameChange_.size();
    }

    public List<FlowProtos.FrameChange> getFrameChangeList()
    {
      return this.frameChange_;
    }

    public FlowProtos.FrameChangeOrBuilder getFrameChangeOrBuilder(int paramInt)
    {
      return (FlowProtos.FrameChangeOrBuilder)this.frameChange_.get(paramInt);
    }

    public List<? extends FlowProtos.FrameChangeOrBuilder> getFrameChangeOrBuilderList()
    {
      return this.frameChange_;
    }

    public ByteString getPrePackedCorrespondences(int paramInt)
    {
      return (ByteString)this.prePackedCorrespondences_.get(paramInt);
    }

    public int getPrePackedCorrespondencesCount()
    {
      return this.prePackedCorrespondences_.size();
    }

    public List<ByteString> getPrePackedCorrespondencesList()
    {
      return this.prePackedCorrespondences_;
    }

    public int getSerializedSize()
    {
      int i = 0;
      int j = this.memoizedSerializedSize;
      if (j != -1)
        return j;
      int k = 0;
      int m = 0;
      while (k < this.frameChange_.size())
      {
        m += CodedOutputStream.computeMessageSize(1, (MessageLite)this.frameChange_.get(k));
        k++;
      }
      int i2;
      for (int n = 0; i < this.prePackedCorrespondences_.size(); n = i2)
      {
        i2 = n + CodedOutputStream.computeBytesSizeNoTag((ByteString)this.prePackedCorrespondences_.get(i));
        i++;
      }
      int i1 = m + n + 1 * getPrePackedCorrespondencesList().size();
      this.memoizedSerializedSize = i1;
      return i1;
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
      int k;
      for (int i = 0; ; i++)
      {
        int j = this.frameChange_.size();
        k = 0;
        if (i >= j)
          break;
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.frameChange_.get(i));
      }
      while (k < this.prePackedCorrespondences_.size())
      {
        paramCodedOutputStream.writeBytes(2, (ByteString)this.prePackedCorrespondences_.get(k));
        k++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FlowProtos.FlowData, Builder>
      implements FlowProtos.FlowDataOrBuilder
    {
      private int bitField0_;
      private List<FlowProtos.FrameChange> frameChange_ = Collections.emptyList();
      private List<ByteString> prePackedCorrespondences_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private FlowProtos.FlowData buildParsed()
        throws InvalidProtocolBufferException
      {
        FlowProtos.FlowData localFlowData = buildPartial();
        if (!localFlowData.isInitialized())
          throw newUninitializedMessageException(localFlowData).asInvalidProtocolBufferException();
        return localFlowData;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureFrameChangeIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.frameChange_ = new ArrayList(this.frameChange_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void ensurePrePackedCorrespondencesIsMutable()
      {
        if ((0x2 & this.bitField0_) != 2)
        {
          this.prePackedCorrespondences_ = new ArrayList(this.prePackedCorrespondences_);
          this.bitField0_ = (0x2 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllFrameChange(Iterable<? extends FlowProtos.FrameChange> paramIterable)
      {
        ensureFrameChangeIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.frameChange_);
        return this;
      }

      public Builder addAllPrePackedCorrespondences(Iterable<? extends ByteString> paramIterable)
      {
        ensurePrePackedCorrespondencesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.prePackedCorrespondences_);
        return this;
      }

      public Builder addFrameChange(int paramInt, FlowProtos.FrameChange.Builder paramBuilder)
      {
        ensureFrameChangeIsMutable();
        this.frameChange_.add(paramInt, paramBuilder.build());
        return this;
      }

      public Builder addFrameChange(int paramInt, FlowProtos.FrameChange paramFrameChange)
      {
        if (paramFrameChange == null)
          throw new NullPointerException();
        ensureFrameChangeIsMutable();
        this.frameChange_.add(paramInt, paramFrameChange);
        return this;
      }

      public Builder addFrameChange(FlowProtos.FrameChange.Builder paramBuilder)
      {
        ensureFrameChangeIsMutable();
        this.frameChange_.add(paramBuilder.build());
        return this;
      }

      public Builder addFrameChange(FlowProtos.FrameChange paramFrameChange)
      {
        if (paramFrameChange == null)
          throw new NullPointerException();
        ensureFrameChangeIsMutable();
        this.frameChange_.add(paramFrameChange);
        return this;
      }

      public Builder addPrePackedCorrespondences(ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        ensurePrePackedCorrespondencesIsMutable();
        this.prePackedCorrespondences_.add(paramByteString);
        return this;
      }

      public FlowProtos.FlowData build()
      {
        FlowProtos.FlowData localFlowData = buildPartial();
        if (!localFlowData.isInitialized())
          throw newUninitializedMessageException(localFlowData);
        return localFlowData;
      }

      public FlowProtos.FlowData buildPartial()
      {
        FlowProtos.FlowData localFlowData = new FlowProtos.FlowData(this, null);
        if ((0x1 & this.bitField0_) == 1)
        {
          this.frameChange_ = Collections.unmodifiableList(this.frameChange_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        FlowProtos.FlowData.access$702(localFlowData, this.frameChange_);
        if ((0x2 & this.bitField0_) == 2)
        {
          this.prePackedCorrespondences_ = Collections.unmodifiableList(this.prePackedCorrespondences_);
          this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        }
        FlowProtos.FlowData.access$802(localFlowData, this.prePackedCorrespondences_);
        return localFlowData;
      }

      public Builder clear()
      {
        super.clear();
        this.frameChange_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.prePackedCorrespondences_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clearFrameChange()
      {
        this.frameChange_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearPrePackedCorrespondences()
      {
        this.prePackedCorrespondences_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public FlowProtos.FlowData getDefaultInstanceForType()
      {
        return FlowProtos.FlowData.getDefaultInstance();
      }

      public FlowProtos.FrameChange getFrameChange(int paramInt)
      {
        return (FlowProtos.FrameChange)this.frameChange_.get(paramInt);
      }

      public int getFrameChangeCount()
      {
        return this.frameChange_.size();
      }

      public List<FlowProtos.FrameChange> getFrameChangeList()
      {
        return Collections.unmodifiableList(this.frameChange_);
      }

      public ByteString getPrePackedCorrespondences(int paramInt)
      {
        return (ByteString)this.prePackedCorrespondences_.get(paramInt);
      }

      public int getPrePackedCorrespondencesCount()
      {
        return this.prePackedCorrespondences_.size();
      }

      public List<ByteString> getPrePackedCorrespondencesList()
      {
        return Collections.unmodifiableList(this.prePackedCorrespondences_);
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(FlowProtos.FlowData paramFlowData)
      {
        if (paramFlowData == FlowProtos.FlowData.getDefaultInstance());
        while (true)
        {
          return this;
          if (!paramFlowData.frameChange_.isEmpty())
          {
            if (!this.frameChange_.isEmpty())
              break label97;
            this.frameChange_ = paramFlowData.frameChange_;
            this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          }
          while (!paramFlowData.prePackedCorrespondences_.isEmpty())
          {
            if (!this.prePackedCorrespondences_.isEmpty())
              break label118;
            this.prePackedCorrespondences_ = paramFlowData.prePackedCorrespondences_;
            this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
            return this;
            label97: ensureFrameChangeIsMutable();
            this.frameChange_.addAll(paramFlowData.frameChange_);
          }
        }
        label118: ensurePrePackedCorrespondencesIsMutable();
        this.prePackedCorrespondences_.addAll(paramFlowData.prePackedCorrespondences_);
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
            FlowProtos.FrameChange.Builder localBuilder = FlowProtos.FrameChange.newBuilder();
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            addFrameChange(localBuilder.buildPartial());
            break;
          case 18:
          }
          ensurePrePackedCorrespondencesIsMutable();
          this.prePackedCorrespondences_.add(paramCodedInputStream.readBytes());
        }
      }

      public Builder removeFrameChange(int paramInt)
      {
        ensureFrameChangeIsMutable();
        this.frameChange_.remove(paramInt);
        return this;
      }

      public Builder setFrameChange(int paramInt, FlowProtos.FrameChange.Builder paramBuilder)
      {
        ensureFrameChangeIsMutable();
        this.frameChange_.set(paramInt, paramBuilder.build());
        return this;
      }

      public Builder setFrameChange(int paramInt, FlowProtos.FrameChange paramFrameChange)
      {
        if (paramFrameChange == null)
          throw new NullPointerException();
        ensureFrameChangeIsMutable();
        this.frameChange_.set(paramInt, paramFrameChange);
        return this;
      }

      public Builder setPrePackedCorrespondences(int paramInt, ByteString paramByteString)
      {
        if (paramByteString == null)
          throw new NullPointerException();
        ensurePrePackedCorrespondencesIsMutable();
        this.prePackedCorrespondences_.set(paramInt, paramByteString);
        return this;
      }
    }
  }

  public static abstract interface FlowDataOrBuilder extends MessageLiteOrBuilder
  {
    public abstract FlowProtos.FrameChange getFrameChange(int paramInt);

    public abstract int getFrameChangeCount();

    public abstract List<FlowProtos.FrameChange> getFrameChangeList();

    public abstract ByteString getPrePackedCorrespondences(int paramInt);

    public abstract int getPrePackedCorrespondencesCount();

    public abstract List<ByteString> getPrePackedCorrespondencesList();
  }

  public static final class FrameChange extends GeneratedMessageLite
    implements FlowProtos.FrameChangeOrBuilder
  {
    public static final int DELTAS_FIELD_NUMBER = 2;
    private static final FrameChange defaultInstance = new FrameChange(true);
    private static final long serialVersionUID;
    private int deltasMemoizedSerializedSize = -1;
    private List<Float> deltas_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private FrameChange(Builder paramBuilder)
    {
      super();
    }

    private FrameChange(boolean paramBoolean)
    {
    }

    public static FrameChange getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.deltas_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(FrameChange paramFrameChange)
    {
      return newBuilder().mergeFrom(paramFrameChange);
    }

    public static FrameChange parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static FrameChange parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static FrameChange parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static FrameChange parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static FrameChange parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static FrameChange parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static FrameChange parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static FrameChange parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static FrameChange parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static FrameChange parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public FrameChange getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public float getDeltas(int paramInt)
    {
      return ((Float)this.deltas_.get(paramInt)).floatValue();
    }

    public int getDeltasCount()
    {
      return this.deltas_.size();
    }

    public List<Float> getDeltasList()
    {
      return this.deltas_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 4 * getDeltasList().size();
      int k = 0 + j;
      if (!getDeltasList().isEmpty())
        k = k + 1 + CodedOutputStream.computeInt32SizeNoTag(j);
      this.deltasMemoizedSerializedSize = j;
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
      if (getDeltasList().size() > 0)
      {
        paramCodedOutputStream.writeRawVarint32(18);
        paramCodedOutputStream.writeRawVarint32(this.deltasMemoizedSerializedSize);
      }
      for (int i = 0; i < this.deltas_.size(); i++)
        paramCodedOutputStream.writeFloatNoTag(((Float)this.deltas_.get(i)).floatValue());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<FlowProtos.FrameChange, Builder>
      implements FlowProtos.FrameChangeOrBuilder
    {
      private int bitField0_;
      private List<Float> deltas_ = Collections.emptyList();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private FlowProtos.FrameChange buildParsed()
        throws InvalidProtocolBufferException
      {
        FlowProtos.FrameChange localFrameChange = buildPartial();
        if (!localFrameChange.isInitialized())
          throw newUninitializedMessageException(localFrameChange).asInvalidProtocolBufferException();
        return localFrameChange;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureDeltasIsMutable()
      {
        if ((0x1 & this.bitField0_) != 1)
        {
          this.deltas_ = new ArrayList(this.deltas_);
          this.bitField0_ = (0x1 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public Builder addAllDeltas(Iterable<? extends Float> paramIterable)
      {
        ensureDeltasIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.deltas_);
        return this;
      }

      public Builder addDeltas(float paramFloat)
      {
        ensureDeltasIsMutable();
        this.deltas_.add(Float.valueOf(paramFloat));
        return this;
      }

      public FlowProtos.FrameChange build()
      {
        FlowProtos.FrameChange localFrameChange = buildPartial();
        if (!localFrameChange.isInitialized())
          throw newUninitializedMessageException(localFrameChange);
        return localFrameChange;
      }

      public FlowProtos.FrameChange buildPartial()
      {
        FlowProtos.FrameChange localFrameChange = new FlowProtos.FrameChange(this, null);
        if ((0x1 & this.bitField0_) == 1)
        {
          this.deltas_ = Collections.unmodifiableList(this.deltas_);
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        }
        FlowProtos.FrameChange.access$302(localFrameChange, this.deltas_);
        return localFrameChange;
      }

      public Builder clear()
      {
        super.clear();
        this.deltas_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clearDeltas()
      {
        this.deltas_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public FlowProtos.FrameChange getDefaultInstanceForType()
      {
        return FlowProtos.FrameChange.getDefaultInstance();
      }

      public float getDeltas(int paramInt)
      {
        return ((Float)this.deltas_.get(paramInt)).floatValue();
      }

      public int getDeltasCount()
      {
        return this.deltas_.size();
      }

      public List<Float> getDeltasList()
      {
        return Collections.unmodifiableList(this.deltas_);
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(FlowProtos.FrameChange paramFrameChange)
      {
        if (paramFrameChange == FlowProtos.FrameChange.getDefaultInstance());
        while (paramFrameChange.deltas_.isEmpty())
          return this;
        if (this.deltas_.isEmpty())
        {
          this.deltas_ = paramFrameChange.deltas_;
          this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
          return this;
        }
        ensureDeltasIsMutable();
        this.deltas_.addAll(paramFrameChange.deltas_);
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
          case 21:
            ensureDeltasIsMutable();
            this.deltas_.add(Float.valueOf(paramCodedInputStream.readFloat()));
            break;
          case 18:
          }
          int j = paramCodedInputStream.pushLimit(paramCodedInputStream.readRawVarint32());
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
            addDeltas(paramCodedInputStream.readFloat());
          paramCodedInputStream.popLimit(j);
        }
      }

      public Builder setDeltas(int paramInt, float paramFloat)
      {
        ensureDeltasIsMutable();
        this.deltas_.set(paramInt, Float.valueOf(paramFloat));
        return this;
      }
    }
  }

  public static abstract interface FrameChangeOrBuilder extends MessageLiteOrBuilder
  {
    public abstract float getDeltas(int paramInt);

    public abstract int getDeltasCount();

    public abstract List<Float> getDeltasList();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.FlowProtos
 * JD-Core Version:    0.6.2
 */