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

public final class ContainerProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class ContainerRequest extends GeneratedMessageLite
    implements ContainerProtos.ContainerRequestOrBuilder
  {
    public static final int ANDROID_VERSION_INFO_REQUEST_FIELD_NUMBER = 14;
    public static final int GET_HISTORY_CONFIG_REQUEST_FIELD_NUMBER = 5;
    public static final int GOGGLES_REPLAY_REQUEST_FIELD_NUMBER = 7;
    public static final int GOGGLES_REQUESTS_FIELD_NUMBER = 1;
    public static final int NATIVE_CLIENT_LOG_EVENT_REQUEST_FIELD_NUMBER = 13;
    public static final int SEARCH_WINDOW_RETRIEVE_REQUESTS_FIELD_NUMBER = 16;
    public static final int SEARCH_WINDOW_UPDATE_REQUESTS_FIELD_NUMBER = 15;
    public static final int SESSION_CONFIG_REQUEST_FIELD_NUMBER = 12;
    public static final int SET_HISTORY_CONFIG_REQUEST_FIELD_NUMBER = 6;
    public static final int TRACE_REQUESTS_FIELD_NUMBER = 3;
    public static final int TRACING_COOKIE_REQUEST_FIELD_NUMBER = 4;
    private static final ContainerRequest defaultInstance = new ContainerRequest(true);
    private static final long serialVersionUID;
    private AndroidVersionInfoProtos.AndroidVersionInfoRequest androidVersionInfoRequest_;
    private int bitField0_;
    private HistoryConfigProtos.GetHistoryConfigRequest getHistoryConfigRequest_;
    private GogglesReplayProtos.GogglesReplayRequest gogglesReplayRequest_;
    private List<GogglesProtos.GogglesRequest> gogglesRequests_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<NativeClientLoggingProtos.NativeClientLogEventRequest> nativeClientLogEventRequest_;
    private List<ContainerProtos.DeprecatedMessage> searchWindowRetrieveRequests_;
    private List<ContainerProtos.DeprecatedMessage> searchWindowUpdateRequests_;
    private ContainerProtos.DeprecatedMessage sessionConfigRequest_;
    private HistoryConfigProtos.SetHistoryConfigRequest setHistoryConfigRequest_;
    private List<TracingProtos.TraceRequest> traceRequests_;
    private TracingCookieProtos.TracingCookieRequest tracingCookieRequest_;

    static
    {
      defaultInstance.initFields();
    }

    private ContainerRequest(Builder paramBuilder)
    {
      super();
    }

    private ContainerRequest(boolean paramBoolean)
    {
    }

    public static ContainerRequest getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.sessionConfigRequest_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
      this.getHistoryConfigRequest_ = HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance();
      this.setHistoryConfigRequest_ = HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance();
      this.tracingCookieRequest_ = TracingCookieProtos.TracingCookieRequest.getDefaultInstance();
      this.gogglesRequests_ = Collections.emptyList();
      this.gogglesReplayRequest_ = GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance();
      this.traceRequests_ = Collections.emptyList();
      this.nativeClientLogEventRequest_ = Collections.emptyList();
      this.androidVersionInfoRequest_ = AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance();
      this.searchWindowUpdateRequests_ = Collections.emptyList();
      this.searchWindowRetrieveRequests_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$400();
    }

    public static Builder newBuilder(ContainerRequest paramContainerRequest)
    {
      return newBuilder().mergeFrom(paramContainerRequest);
    }

    public static ContainerRequest parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ContainerRequest parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ContainerRequest parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ContainerRequest parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ContainerRequest parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ContainerRequest parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ContainerRequest parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ContainerRequest parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ContainerRequest parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ContainerRequest parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    @Deprecated
    public AndroidVersionInfoProtos.AndroidVersionInfoRequest getAndroidVersionInfoRequest()
    {
      return this.androidVersionInfoRequest_;
    }

    public ContainerRequest getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
    public HistoryConfigProtos.GetHistoryConfigRequest getGetHistoryConfigRequest()
    {
      return this.getHistoryConfigRequest_;
    }

    @Deprecated
    public GogglesReplayProtos.GogglesReplayRequest getGogglesReplayRequest()
    {
      return this.gogglesReplayRequest_;
    }

    @Deprecated
    public GogglesProtos.GogglesRequest getGogglesRequests(int paramInt)
    {
      return (GogglesProtos.GogglesRequest)this.gogglesRequests_.get(paramInt);
    }

    @Deprecated
    public int getGogglesRequestsCount()
    {
      return this.gogglesRequests_.size();
    }

    @Deprecated
    public List<GogglesProtos.GogglesRequest> getGogglesRequestsList()
    {
      return this.gogglesRequests_;
    }

    @Deprecated
    public GogglesProtos.GogglesRequestOrBuilder getGogglesRequestsOrBuilder(int paramInt)
    {
      return (GogglesProtos.GogglesRequestOrBuilder)this.gogglesRequests_.get(paramInt);
    }

    @Deprecated
    public List<? extends GogglesProtos.GogglesRequestOrBuilder> getGogglesRequestsOrBuilderList()
    {
      return this.gogglesRequests_;
    }

    @Deprecated
    public NativeClientLoggingProtos.NativeClientLogEventRequest getNativeClientLogEventRequest(int paramInt)
    {
      return (NativeClientLoggingProtos.NativeClientLogEventRequest)this.nativeClientLogEventRequest_.get(paramInt);
    }

    @Deprecated
    public int getNativeClientLogEventRequestCount()
    {
      return this.nativeClientLogEventRequest_.size();
    }

    @Deprecated
    public List<NativeClientLoggingProtos.NativeClientLogEventRequest> getNativeClientLogEventRequestList()
    {
      return this.nativeClientLogEventRequest_;
    }

    @Deprecated
    public NativeClientLoggingProtos.NativeClientLogEventRequestOrBuilder getNativeClientLogEventRequestOrBuilder(int paramInt)
    {
      return (NativeClientLoggingProtos.NativeClientLogEventRequestOrBuilder)this.nativeClientLogEventRequest_.get(paramInt);
    }

    @Deprecated
    public List<? extends NativeClientLoggingProtos.NativeClientLogEventRequestOrBuilder> getNativeClientLogEventRequestOrBuilderList()
    {
      return this.nativeClientLogEventRequest_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSearchWindowRetrieveRequests(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessage)this.searchWindowRetrieveRequests_.get(paramInt);
    }

    @Deprecated
    public int getSearchWindowRetrieveRequestsCount()
    {
      return this.searchWindowRetrieveRequests_.size();
    }

    @Deprecated
    public List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveRequestsList()
    {
      return this.searchWindowRetrieveRequests_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessageOrBuilder getSearchWindowRetrieveRequestsOrBuilder(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessageOrBuilder)this.searchWindowRetrieveRequests_.get(paramInt);
    }

    @Deprecated
    public List<? extends ContainerProtos.DeprecatedMessageOrBuilder> getSearchWindowRetrieveRequestsOrBuilderList()
    {
      return this.searchWindowRetrieveRequests_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSearchWindowUpdateRequests(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessage)this.searchWindowUpdateRequests_.get(paramInt);
    }

    @Deprecated
    public int getSearchWindowUpdateRequestsCount()
    {
      return this.searchWindowUpdateRequests_.size();
    }

    @Deprecated
    public List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateRequestsList()
    {
      return this.searchWindowUpdateRequests_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessageOrBuilder getSearchWindowUpdateRequestsOrBuilder(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessageOrBuilder)this.searchWindowUpdateRequests_.get(paramInt);
    }

    @Deprecated
    public List<? extends ContainerProtos.DeprecatedMessageOrBuilder> getSearchWindowUpdateRequestsOrBuilderList()
    {
      return this.searchWindowUpdateRequests_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.gogglesRequests_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.gogglesRequests_.get(j));
        j++;
      }
      for (int m = 0; m < this.traceRequests_.size(); m++)
        k += CodedOutputStream.computeMessageSize(3, (MessageLite)this.traceRequests_.get(m));
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(4, this.tracingCookieRequest_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(5, this.getHistoryConfigRequest_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(6, this.setHistoryConfigRequest_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeMessageSize(7, this.gogglesReplayRequest_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(12, this.sessionConfigRequest_);
      for (int n = 0; n < this.nativeClientLogEventRequest_.size(); n++)
        k += CodedOutputStream.computeMessageSize(13, (MessageLite)this.nativeClientLogEventRequest_.get(n));
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeMessageSize(14, this.androidVersionInfoRequest_);
      int i3;
      for (int i1 = 0; ; i1++)
      {
        int i2 = this.searchWindowUpdateRequests_.size();
        i3 = 0;
        if (i1 >= i2)
          break;
        k += CodedOutputStream.computeMessageSize(15, (MessageLite)this.searchWindowUpdateRequests_.get(i1));
      }
      while (i3 < this.searchWindowRetrieveRequests_.size())
      {
        k += CodedOutputStream.computeMessageSize(16, (MessageLite)this.searchWindowRetrieveRequests_.get(i3));
        i3++;
      }
      this.memoizedSerializedSize = k;
      return k;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSessionConfigRequest()
    {
      return this.sessionConfigRequest_;
    }

    @Deprecated
    public HistoryConfigProtos.SetHistoryConfigRequest getSetHistoryConfigRequest()
    {
      return this.setHistoryConfigRequest_;
    }

    @Deprecated
    public TracingProtos.TraceRequest getTraceRequests(int paramInt)
    {
      return (TracingProtos.TraceRequest)this.traceRequests_.get(paramInt);
    }

    @Deprecated
    public int getTraceRequestsCount()
    {
      return this.traceRequests_.size();
    }

    @Deprecated
    public List<TracingProtos.TraceRequest> getTraceRequestsList()
    {
      return this.traceRequests_;
    }

    @Deprecated
    public TracingProtos.TraceRequestOrBuilder getTraceRequestsOrBuilder(int paramInt)
    {
      return (TracingProtos.TraceRequestOrBuilder)this.traceRequests_.get(paramInt);
    }

    @Deprecated
    public List<? extends TracingProtos.TraceRequestOrBuilder> getTraceRequestsOrBuilderList()
    {
      return this.traceRequests_;
    }

    @Deprecated
    public TracingCookieProtos.TracingCookieRequest getTracingCookieRequest()
    {
      return this.tracingCookieRequest_;
    }

    @Deprecated
    public boolean hasAndroidVersionInfoRequest()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    @Deprecated
    public boolean hasGetHistoryConfigRequest()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    @Deprecated
    public boolean hasGogglesReplayRequest()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    @Deprecated
    public boolean hasSessionConfigRequest()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasSetHistoryConfigRequest()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    @Deprecated
    public boolean hasTracingCookieRequest()
    {
      return (0x8 & this.bitField0_) == 8;
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
      if ((hasSetHistoryConfigRequest()) && (!getSetHistoryConfigRequest().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      for (int k = 0; k < getGogglesRequestsCount(); k++)
        if (!getGogglesRequests(k).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasGogglesReplayRequest()) && (!getGogglesReplayRequest().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      for (int m = 0; m < getTraceRequestsCount(); m++)
        if (!getTraceRequests(m).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      for (int n = 0; n < getNativeClientLogEventRequestCount(); n++)
        if (!getNativeClientLogEventRequest(n).isInitialized())
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
      for (int i = 0; i < this.gogglesRequests_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.gogglesRequests_.get(i));
      for (int j = 0; j < this.traceRequests_.size(); j++)
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.traceRequests_.get(j));
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(4, this.tracingCookieRequest_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(5, this.getHistoryConfigRequest_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(6, this.setHistoryConfigRequest_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeMessage(7, this.gogglesReplayRequest_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(12, this.sessionConfigRequest_);
      for (int k = 0; k < this.nativeClientLogEventRequest_.size(); k++)
        paramCodedOutputStream.writeMessage(13, (MessageLite)this.nativeClientLogEventRequest_.get(k));
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeMessage(14, this.androidVersionInfoRequest_);
      int i1;
      for (int m = 0; ; m++)
      {
        int n = this.searchWindowUpdateRequests_.size();
        i1 = 0;
        if (m >= n)
          break;
        paramCodedOutputStream.writeMessage(15, (MessageLite)this.searchWindowUpdateRequests_.get(m));
      }
      while (i1 < this.searchWindowRetrieveRequests_.size())
      {
        paramCodedOutputStream.writeMessage(16, (MessageLite)this.searchWindowRetrieveRequests_.get(i1));
        i1++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContainerProtos.ContainerRequest, Builder>
      implements ContainerProtos.ContainerRequestOrBuilder
    {
      private AndroidVersionInfoProtos.AndroidVersionInfoRequest androidVersionInfoRequest_ = AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance();
      private int bitField0_;
      private HistoryConfigProtos.GetHistoryConfigRequest getHistoryConfigRequest_ = HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance();
      private GogglesReplayProtos.GogglesReplayRequest gogglesReplayRequest_ = GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance();
      private List<GogglesProtos.GogglesRequest> gogglesRequests_ = Collections.emptyList();
      private List<NativeClientLoggingProtos.NativeClientLogEventRequest> nativeClientLogEventRequest_ = Collections.emptyList();
      private List<ContainerProtos.DeprecatedMessage> searchWindowRetrieveRequests_ = Collections.emptyList();
      private List<ContainerProtos.DeprecatedMessage> searchWindowUpdateRequests_ = Collections.emptyList();
      private ContainerProtos.DeprecatedMessage sessionConfigRequest_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
      private HistoryConfigProtos.SetHistoryConfigRequest setHistoryConfigRequest_ = HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance();
      private List<TracingProtos.TraceRequest> traceRequests_ = Collections.emptyList();
      private TracingCookieProtos.TracingCookieRequest tracingCookieRequest_ = TracingCookieProtos.TracingCookieRequest.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ContainerProtos.ContainerRequest buildParsed()
        throws InvalidProtocolBufferException
      {
        ContainerProtos.ContainerRequest localContainerRequest = buildPartial();
        if (!localContainerRequest.isInitialized())
          throw newUninitializedMessageException(localContainerRequest).asInvalidProtocolBufferException();
        return localContainerRequest;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureGogglesRequestsIsMutable()
      {
        if ((0x10 & this.bitField0_) != 16)
        {
          this.gogglesRequests_ = new ArrayList(this.gogglesRequests_);
          this.bitField0_ = (0x10 | this.bitField0_);
        }
      }

      private void ensureNativeClientLogEventRequestIsMutable()
      {
        if ((0x80 & this.bitField0_) != 128)
        {
          this.nativeClientLogEventRequest_ = new ArrayList(this.nativeClientLogEventRequest_);
          this.bitField0_ = (0x80 | this.bitField0_);
        }
      }

      private void ensureSearchWindowRetrieveRequestsIsMutable()
      {
        if ((0x400 & this.bitField0_) != 1024)
        {
          this.searchWindowRetrieveRequests_ = new ArrayList(this.searchWindowRetrieveRequests_);
          this.bitField0_ = (0x400 | this.bitField0_);
        }
      }

      private void ensureSearchWindowUpdateRequestsIsMutable()
      {
        if ((0x200 & this.bitField0_) != 512)
        {
          this.searchWindowUpdateRequests_ = new ArrayList(this.searchWindowUpdateRequests_);
          this.bitField0_ = (0x200 | this.bitField0_);
        }
      }

      private void ensureTraceRequestsIsMutable()
      {
        if ((0x40 & this.bitField0_) != 64)
        {
          this.traceRequests_ = new ArrayList(this.traceRequests_);
          this.bitField0_ = (0x40 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      @Deprecated
      public Builder addAllGogglesRequests(Iterable<? extends GogglesProtos.GogglesRequest> paramIterable)
      {
        ensureGogglesRequestsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.gogglesRequests_);
        return this;
      }

      @Deprecated
      public Builder addAllNativeClientLogEventRequest(Iterable<? extends NativeClientLoggingProtos.NativeClientLogEventRequest> paramIterable)
      {
        ensureNativeClientLogEventRequestIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.nativeClientLogEventRequest_);
        return this;
      }

      @Deprecated
      public Builder addAllSearchWindowRetrieveRequests(Iterable<? extends ContainerProtos.DeprecatedMessage> paramIterable)
      {
        ensureSearchWindowRetrieveRequestsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.searchWindowRetrieveRequests_);
        return this;
      }

      @Deprecated
      public Builder addAllSearchWindowUpdateRequests(Iterable<? extends ContainerProtos.DeprecatedMessage> paramIterable)
      {
        ensureSearchWindowUpdateRequestsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.searchWindowUpdateRequests_);
        return this;
      }

      @Deprecated
      public Builder addAllTraceRequests(Iterable<? extends TracingProtos.TraceRequest> paramIterable)
      {
        ensureTraceRequestsIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.traceRequests_);
        return this;
      }

      @Deprecated
      public Builder addGogglesRequests(int paramInt, GogglesProtos.GogglesRequest.Builder paramBuilder)
      {
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addGogglesRequests(int paramInt, GogglesProtos.GogglesRequest paramGogglesRequest)
      {
        if (paramGogglesRequest == null)
          throw new NullPointerException();
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.add(paramInt, paramGogglesRequest);
        return this;
      }

      @Deprecated
      public Builder addGogglesRequests(GogglesProtos.GogglesRequest.Builder paramBuilder)
      {
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addGogglesRequests(GogglesProtos.GogglesRequest paramGogglesRequest)
      {
        if (paramGogglesRequest == null)
          throw new NullPointerException();
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.add(paramGogglesRequest);
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventRequest(int paramInt, NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
      {
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventRequest(int paramInt, NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest)
      {
        if (paramNativeClientLogEventRequest == null)
          throw new NullPointerException();
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.add(paramInt, paramNativeClientLogEventRequest);
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventRequest(NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
      {
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventRequest(NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest)
      {
        if (paramNativeClientLogEventRequest == null)
          throw new NullPointerException();
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.add(paramNativeClientLogEventRequest);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveRequests(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveRequests(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.add(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveRequests(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveRequests(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.add(paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateRequests(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateRequests(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.add(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateRequests(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateRequests(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.add(paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addTraceRequests(int paramInt, TracingProtos.TraceRequest.Builder paramBuilder)
      {
        ensureTraceRequestsIsMutable();
        this.traceRequests_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addTraceRequests(int paramInt, TracingProtos.TraceRequest paramTraceRequest)
      {
        if (paramTraceRequest == null)
          throw new NullPointerException();
        ensureTraceRequestsIsMutable();
        this.traceRequests_.add(paramInt, paramTraceRequest);
        return this;
      }

      @Deprecated
      public Builder addTraceRequests(TracingProtos.TraceRequest.Builder paramBuilder)
      {
        ensureTraceRequestsIsMutable();
        this.traceRequests_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addTraceRequests(TracingProtos.TraceRequest paramTraceRequest)
      {
        if (paramTraceRequest == null)
          throw new NullPointerException();
        ensureTraceRequestsIsMutable();
        this.traceRequests_.add(paramTraceRequest);
        return this;
      }

      public ContainerProtos.ContainerRequest build()
      {
        ContainerProtos.ContainerRequest localContainerRequest = buildPartial();
        if (!localContainerRequest.isInitialized())
          throw newUninitializedMessageException(localContainerRequest);
        return localContainerRequest;
      }

      public ContainerProtos.ContainerRequest buildPartial()
      {
        int i = 1;
        ContainerProtos.ContainerRequest localContainerRequest = new ContainerProtos.ContainerRequest(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ContainerProtos.ContainerRequest.access$602(localContainerRequest, this.sessionConfigRequest_);
          if ((j & 0x2) == 2)
            i |= 2;
          ContainerProtos.ContainerRequest.access$702(localContainerRequest, this.getHistoryConfigRequest_);
          if ((j & 0x4) == 4)
            i |= 4;
          ContainerProtos.ContainerRequest.access$802(localContainerRequest, this.setHistoryConfigRequest_);
          if ((j & 0x8) == 8)
            i |= 8;
          ContainerProtos.ContainerRequest.access$902(localContainerRequest, this.tracingCookieRequest_);
          if ((0x10 & this.bitField0_) == 16)
          {
            this.gogglesRequests_ = Collections.unmodifiableList(this.gogglesRequests_);
            this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          }
          ContainerProtos.ContainerRequest.access$1002(localContainerRequest, this.gogglesRequests_);
          if ((j & 0x20) == 32)
            i |= 16;
          ContainerProtos.ContainerRequest.access$1102(localContainerRequest, this.gogglesReplayRequest_);
          if ((0x40 & this.bitField0_) == 64)
          {
            this.traceRequests_ = Collections.unmodifiableList(this.traceRequests_);
            this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
          }
          ContainerProtos.ContainerRequest.access$1202(localContainerRequest, this.traceRequests_);
          if ((0x80 & this.bitField0_) == 128)
          {
            this.nativeClientLogEventRequest_ = Collections.unmodifiableList(this.nativeClientLogEventRequest_);
            this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
          }
          ContainerProtos.ContainerRequest.access$1302(localContainerRequest, this.nativeClientLogEventRequest_);
          if ((j & 0x100) == 256)
            i |= 32;
          ContainerProtos.ContainerRequest.access$1402(localContainerRequest, this.androidVersionInfoRequest_);
          if ((0x200 & this.bitField0_) == 512)
          {
            this.searchWindowUpdateRequests_ = Collections.unmodifiableList(this.searchWindowUpdateRequests_);
            this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
          }
          ContainerProtos.ContainerRequest.access$1502(localContainerRequest, this.searchWindowUpdateRequests_);
          if ((0x400 & this.bitField0_) == 1024)
          {
            this.searchWindowRetrieveRequests_ = Collections.unmodifiableList(this.searchWindowRetrieveRequests_);
            this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
          }
          ContainerProtos.ContainerRequest.access$1602(localContainerRequest, this.searchWindowRetrieveRequests_);
          ContainerProtos.ContainerRequest.access$1702(localContainerRequest, i);
          return localContainerRequest;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.sessionConfigRequest_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.getHistoryConfigRequest_ = HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.setHistoryConfigRequest_ = HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.tracingCookieRequest_ = TracingCookieProtos.TracingCookieRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.gogglesRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.gogglesReplayRequest_ = GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.traceRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.nativeClientLogEventRequest_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.androidVersionInfoRequest_ = AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.searchWindowUpdateRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.searchWindowRetrieveRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearAndroidVersionInfoRequest()
      {
        this.androidVersionInfoRequest_ = AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGetHistoryConfigRequest()
      {
        this.getHistoryConfigRequest_ = HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesReplayRequest()
      {
        this.gogglesReplayRequest_ = GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesRequests()
      {
        this.gogglesRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearNativeClientLogEventRequest()
      {
        this.nativeClientLogEventRequest_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSearchWindowRetrieveRequests()
      {
        this.searchWindowRetrieveRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSearchWindowUpdateRequests()
      {
        this.searchWindowUpdateRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSessionConfigRequest()
      {
        this.sessionConfigRequest_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSetHistoryConfigRequest()
      {
        this.setHistoryConfigRequest_ = HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearTraceRequests()
      {
        this.traceRequests_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearTracingCookieRequest()
      {
        this.tracingCookieRequest_ = TracingCookieProtos.TracingCookieRequest.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      @Deprecated
      public AndroidVersionInfoProtos.AndroidVersionInfoRequest getAndroidVersionInfoRequest()
      {
        return this.androidVersionInfoRequest_;
      }

      public ContainerProtos.ContainerRequest getDefaultInstanceForType()
      {
        return ContainerProtos.ContainerRequest.getDefaultInstance();
      }

      @Deprecated
      public HistoryConfigProtos.GetHistoryConfigRequest getGetHistoryConfigRequest()
      {
        return this.getHistoryConfigRequest_;
      }

      @Deprecated
      public GogglesReplayProtos.GogglesReplayRequest getGogglesReplayRequest()
      {
        return this.gogglesReplayRequest_;
      }

      @Deprecated
      public GogglesProtos.GogglesRequest getGogglesRequests(int paramInt)
      {
        return (GogglesProtos.GogglesRequest)this.gogglesRequests_.get(paramInt);
      }

      @Deprecated
      public int getGogglesRequestsCount()
      {
        return this.gogglesRequests_.size();
      }

      @Deprecated
      public List<GogglesProtos.GogglesRequest> getGogglesRequestsList()
      {
        return Collections.unmodifiableList(this.gogglesRequests_);
      }

      @Deprecated
      public NativeClientLoggingProtos.NativeClientLogEventRequest getNativeClientLogEventRequest(int paramInt)
      {
        return (NativeClientLoggingProtos.NativeClientLogEventRequest)this.nativeClientLogEventRequest_.get(paramInt);
      }

      @Deprecated
      public int getNativeClientLogEventRequestCount()
      {
        return this.nativeClientLogEventRequest_.size();
      }

      @Deprecated
      public List<NativeClientLoggingProtos.NativeClientLogEventRequest> getNativeClientLogEventRequestList()
      {
        return Collections.unmodifiableList(this.nativeClientLogEventRequest_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSearchWindowRetrieveRequests(int paramInt)
      {
        return (ContainerProtos.DeprecatedMessage)this.searchWindowRetrieveRequests_.get(paramInt);
      }

      @Deprecated
      public int getSearchWindowRetrieveRequestsCount()
      {
        return this.searchWindowRetrieveRequests_.size();
      }

      @Deprecated
      public List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveRequestsList()
      {
        return Collections.unmodifiableList(this.searchWindowRetrieveRequests_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSearchWindowUpdateRequests(int paramInt)
      {
        return (ContainerProtos.DeprecatedMessage)this.searchWindowUpdateRequests_.get(paramInt);
      }

      @Deprecated
      public int getSearchWindowUpdateRequestsCount()
      {
        return this.searchWindowUpdateRequests_.size();
      }

      @Deprecated
      public List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateRequestsList()
      {
        return Collections.unmodifiableList(this.searchWindowUpdateRequests_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSessionConfigRequest()
      {
        return this.sessionConfigRequest_;
      }

      @Deprecated
      public HistoryConfigProtos.SetHistoryConfigRequest getSetHistoryConfigRequest()
      {
        return this.setHistoryConfigRequest_;
      }

      @Deprecated
      public TracingProtos.TraceRequest getTraceRequests(int paramInt)
      {
        return (TracingProtos.TraceRequest)this.traceRequests_.get(paramInt);
      }

      @Deprecated
      public int getTraceRequestsCount()
      {
        return this.traceRequests_.size();
      }

      @Deprecated
      public List<TracingProtos.TraceRequest> getTraceRequestsList()
      {
        return Collections.unmodifiableList(this.traceRequests_);
      }

      @Deprecated
      public TracingCookieProtos.TracingCookieRequest getTracingCookieRequest()
      {
        return this.tracingCookieRequest_;
      }

      @Deprecated
      public boolean hasAndroidVersionInfoRequest()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      @Deprecated
      public boolean hasGetHistoryConfigRequest()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      @Deprecated
      public boolean hasGogglesReplayRequest()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      @Deprecated
      public boolean hasSessionConfigRequest()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasSetHistoryConfigRequest()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      @Deprecated
      public boolean hasTracingCookieRequest()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public final boolean isInitialized()
      {
        if ((hasSetHistoryConfigRequest()) && (!getSetHistoryConfigRequest().isInitialized()));
        label46: 
        do
        {
          return false;
          for (int i = 0; ; i++)
          {
            if (i >= getGogglesRequestsCount())
              break label46;
            if (!getGogglesRequests(i).isInitialized())
              break;
          }
        }
        while ((hasGogglesReplayRequest()) && (!getGogglesReplayRequest().isInitialized()));
        for (int j = 0; ; j++)
        {
          if (j >= getTraceRequestsCount())
            break label90;
          if (!getTraceRequests(j).isInitialized())
            break;
        }
        label90: for (int k = 0; ; k++)
        {
          if (k >= getNativeClientLogEventRequestCount())
            break label117;
          if (!getNativeClientLogEventRequest(k).isInitialized())
            break;
        }
        label117: return true;
      }

      @Deprecated
      public Builder mergeAndroidVersionInfoRequest(AndroidVersionInfoProtos.AndroidVersionInfoRequest paramAndroidVersionInfoRequest)
      {
        if (((0x100 & this.bitField0_) == 256) && (this.androidVersionInfoRequest_ != AndroidVersionInfoProtos.AndroidVersionInfoRequest.getDefaultInstance()));
        for (this.androidVersionInfoRequest_ = AndroidVersionInfoProtos.AndroidVersionInfoRequest.newBuilder(this.androidVersionInfoRequest_).mergeFrom(paramAndroidVersionInfoRequest).buildPartial(); ; this.androidVersionInfoRequest_ = paramAndroidVersionInfoRequest)
        {
          this.bitField0_ = (0x100 | this.bitField0_);
          return this;
        }
      }

      public Builder mergeFrom(ContainerProtos.ContainerRequest paramContainerRequest)
      {
        if (paramContainerRequest == ContainerProtos.ContainerRequest.getDefaultInstance());
        label388: label407: 
        while (true)
        {
          return this;
          if (paramContainerRequest.hasSessionConfigRequest())
            mergeSessionConfigRequest(paramContainerRequest.getSessionConfigRequest());
          if (paramContainerRequest.hasGetHistoryConfigRequest())
            mergeGetHistoryConfigRequest(paramContainerRequest.getGetHistoryConfigRequest());
          if (paramContainerRequest.hasSetHistoryConfigRequest())
            mergeSetHistoryConfigRequest(paramContainerRequest.getSetHistoryConfigRequest());
          if (paramContainerRequest.hasTracingCookieRequest())
            mergeTracingCookieRequest(paramContainerRequest.getTracingCookieRequest());
          if (!paramContainerRequest.gogglesRequests_.isEmpty())
          {
            if (this.gogglesRequests_.isEmpty())
            {
              this.gogglesRequests_ = paramContainerRequest.gogglesRequests_;
              this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
            }
          }
          else
          {
            if (paramContainerRequest.hasGogglesReplayRequest())
              mergeGogglesReplayRequest(paramContainerRequest.getGogglesReplayRequest());
            if (!paramContainerRequest.traceRequests_.isEmpty())
            {
              if (!this.traceRequests_.isEmpty())
                break label346;
              this.traceRequests_ = paramContainerRequest.traceRequests_;
              this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
            }
            label175: if (!paramContainerRequest.nativeClientLogEventRequest_.isEmpty())
            {
              if (!this.nativeClientLogEventRequest_.isEmpty())
                break label367;
              this.nativeClientLogEventRequest_ = paramContainerRequest.nativeClientLogEventRequest_;
              this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
            }
            label219: if (paramContainerRequest.hasAndroidVersionInfoRequest())
              mergeAndroidVersionInfoRequest(paramContainerRequest.getAndroidVersionInfoRequest());
            if (!paramContainerRequest.searchWindowUpdateRequests_.isEmpty())
            {
              if (!this.searchWindowUpdateRequests_.isEmpty())
                break label388;
              this.searchWindowUpdateRequests_ = paramContainerRequest.searchWindowUpdateRequests_;
              this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
            }
          }
          while (true)
          {
            if (paramContainerRequest.searchWindowRetrieveRequests_.isEmpty())
              break label407;
            if (!this.searchWindowRetrieveRequests_.isEmpty())
              break label409;
            this.searchWindowRetrieveRequests_ = paramContainerRequest.searchWindowRetrieveRequests_;
            this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
            return this;
            ensureGogglesRequestsIsMutable();
            this.gogglesRequests_.addAll(paramContainerRequest.gogglesRequests_);
            break;
            label346: ensureTraceRequestsIsMutable();
            this.traceRequests_.addAll(paramContainerRequest.traceRequests_);
            break label175;
            label367: ensureNativeClientLogEventRequestIsMutable();
            this.nativeClientLogEventRequest_.addAll(paramContainerRequest.nativeClientLogEventRequest_);
            break label219;
            ensureSearchWindowUpdateRequestsIsMutable();
            this.searchWindowUpdateRequests_.addAll(paramContainerRequest.searchWindowUpdateRequests_);
          }
        }
        label409: ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.addAll(paramContainerRequest.searchWindowRetrieveRequests_);
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
            GogglesProtos.GogglesRequest.Builder localBuilder10 = GogglesProtos.GogglesRequest.newBuilder();
            paramCodedInputStream.readMessage(localBuilder10, paramExtensionRegistryLite);
            addGogglesRequests(localBuilder10.buildPartial());
            break;
          case 26:
            TracingProtos.TraceRequest.Builder localBuilder9 = TracingProtos.TraceRequest.newBuilder();
            paramCodedInputStream.readMessage(localBuilder9, paramExtensionRegistryLite);
            addTraceRequests(localBuilder9.buildPartial());
            break;
          case 34:
            TracingCookieProtos.TracingCookieRequest.Builder localBuilder8 = TracingCookieProtos.TracingCookieRequest.newBuilder();
            if (hasTracingCookieRequest())
              localBuilder8.mergeFrom(getTracingCookieRequest());
            paramCodedInputStream.readMessage(localBuilder8, paramExtensionRegistryLite);
            setTracingCookieRequest(localBuilder8.buildPartial());
            break;
          case 42:
            HistoryConfigProtos.GetHistoryConfigRequest.Builder localBuilder7 = HistoryConfigProtos.GetHistoryConfigRequest.newBuilder();
            if (hasGetHistoryConfigRequest())
              localBuilder7.mergeFrom(getGetHistoryConfigRequest());
            paramCodedInputStream.readMessage(localBuilder7, paramExtensionRegistryLite);
            setGetHistoryConfigRequest(localBuilder7.buildPartial());
            break;
          case 50:
            HistoryConfigProtos.SetHistoryConfigRequest.Builder localBuilder6 = HistoryConfigProtos.SetHistoryConfigRequest.newBuilder();
            if (hasSetHistoryConfigRequest())
              localBuilder6.mergeFrom(getSetHistoryConfigRequest());
            paramCodedInputStream.readMessage(localBuilder6, paramExtensionRegistryLite);
            setSetHistoryConfigRequest(localBuilder6.buildPartial());
            break;
          case 58:
            GogglesReplayProtos.GogglesReplayRequest.Builder localBuilder5 = GogglesReplayProtos.GogglesReplayRequest.newBuilder();
            if (hasGogglesReplayRequest())
              localBuilder5.mergeFrom(getGogglesReplayRequest());
            paramCodedInputStream.readMessage(localBuilder5, paramExtensionRegistryLite);
            setGogglesReplayRequest(localBuilder5.buildPartial());
            break;
          case 98:
            ContainerProtos.DeprecatedMessage.Builder localBuilder4 = ContainerProtos.DeprecatedMessage.newBuilder();
            if (hasSessionConfigRequest())
              localBuilder4.mergeFrom(getSessionConfigRequest());
            paramCodedInputStream.readMessage(localBuilder4, paramExtensionRegistryLite);
            setSessionConfigRequest(localBuilder4.buildPartial());
            break;
          case 106:
            NativeClientLoggingProtos.NativeClientLogEventRequest.Builder localBuilder3 = NativeClientLoggingProtos.NativeClientLogEventRequest.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addNativeClientLogEventRequest(localBuilder3.buildPartial());
            break;
          case 114:
            AndroidVersionInfoProtos.AndroidVersionInfoRequest.Builder localBuilder = AndroidVersionInfoProtos.AndroidVersionInfoRequest.newBuilder();
            if (hasAndroidVersionInfoRequest())
              localBuilder.mergeFrom(getAndroidVersionInfoRequest());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setAndroidVersionInfoRequest(localBuilder.buildPartial());
            break;
          case 122:
            ContainerProtos.DeprecatedMessage.Builder localBuilder2 = ContainerProtos.DeprecatedMessage.newBuilder();
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            addSearchWindowUpdateRequests(localBuilder2.buildPartial());
            break;
          case 130:
          }
          ContainerProtos.DeprecatedMessage.Builder localBuilder1 = ContainerProtos.DeprecatedMessage.newBuilder();
          paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
          addSearchWindowRetrieveRequests(localBuilder1.buildPartial());
        }
      }

      @Deprecated
      public Builder mergeGetHistoryConfigRequest(HistoryConfigProtos.GetHistoryConfigRequest paramGetHistoryConfigRequest)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.getHistoryConfigRequest_ != HistoryConfigProtos.GetHistoryConfigRequest.getDefaultInstance()));
        for (this.getHistoryConfigRequest_ = HistoryConfigProtos.GetHistoryConfigRequest.newBuilder(this.getHistoryConfigRequest_).mergeFrom(paramGetHistoryConfigRequest).buildPartial(); ; this.getHistoryConfigRequest_ = paramGetHistoryConfigRequest)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeGogglesReplayRequest(GogglesReplayProtos.GogglesReplayRequest paramGogglesReplayRequest)
      {
        if (((0x20 & this.bitField0_) == 32) && (this.gogglesReplayRequest_ != GogglesReplayProtos.GogglesReplayRequest.getDefaultInstance()));
        for (this.gogglesReplayRequest_ = GogglesReplayProtos.GogglesReplayRequest.newBuilder(this.gogglesReplayRequest_).mergeFrom(paramGogglesReplayRequest).buildPartial(); ; this.gogglesReplayRequest_ = paramGogglesReplayRequest)
        {
          this.bitField0_ = (0x20 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeSessionConfigRequest(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.sessionConfigRequest_ != ContainerProtos.DeprecatedMessage.getDefaultInstance()));
        for (this.sessionConfigRequest_ = ContainerProtos.DeprecatedMessage.newBuilder(this.sessionConfigRequest_).mergeFrom(paramDeprecatedMessage).buildPartial(); ; this.sessionConfigRequest_ = paramDeprecatedMessage)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeSetHistoryConfigRequest(HistoryConfigProtos.SetHistoryConfigRequest paramSetHistoryConfigRequest)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.setHistoryConfigRequest_ != HistoryConfigProtos.SetHistoryConfigRequest.getDefaultInstance()));
        for (this.setHistoryConfigRequest_ = HistoryConfigProtos.SetHistoryConfigRequest.newBuilder(this.setHistoryConfigRequest_).mergeFrom(paramSetHistoryConfigRequest).buildPartial(); ; this.setHistoryConfigRequest_ = paramSetHistoryConfigRequest)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeTracingCookieRequest(TracingCookieProtos.TracingCookieRequest paramTracingCookieRequest)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.tracingCookieRequest_ != TracingCookieProtos.TracingCookieRequest.getDefaultInstance()));
        for (this.tracingCookieRequest_ = TracingCookieProtos.TracingCookieRequest.newBuilder(this.tracingCookieRequest_).mergeFrom(paramTracingCookieRequest).buildPartial(); ; this.tracingCookieRequest_ = paramTracingCookieRequest)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder removeGogglesRequests(int paramInt)
      {
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeNativeClientLogEventRequest(int paramInt)
      {
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeSearchWindowRetrieveRequests(int paramInt)
      {
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeSearchWindowUpdateRequests(int paramInt)
      {
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeTraceRequests(int paramInt)
      {
        ensureTraceRequestsIsMutable();
        this.traceRequests_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder setAndroidVersionInfoRequest(AndroidVersionInfoProtos.AndroidVersionInfoRequest.Builder paramBuilder)
      {
        this.androidVersionInfoRequest_ = paramBuilder.build();
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setAndroidVersionInfoRequest(AndroidVersionInfoProtos.AndroidVersionInfoRequest paramAndroidVersionInfoRequest)
      {
        if (paramAndroidVersionInfoRequest == null)
          throw new NullPointerException();
        this.androidVersionInfoRequest_ = paramAndroidVersionInfoRequest;
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGetHistoryConfigRequest(HistoryConfigProtos.GetHistoryConfigRequest.Builder paramBuilder)
      {
        this.getHistoryConfigRequest_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGetHistoryConfigRequest(HistoryConfigProtos.GetHistoryConfigRequest paramGetHistoryConfigRequest)
      {
        if (paramGetHistoryConfigRequest == null)
          throw new NullPointerException();
        this.getHistoryConfigRequest_ = paramGetHistoryConfigRequest;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesReplayRequest(GogglesReplayProtos.GogglesReplayRequest.Builder paramBuilder)
      {
        this.gogglesReplayRequest_ = paramBuilder.build();
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesReplayRequest(GogglesReplayProtos.GogglesReplayRequest paramGogglesReplayRequest)
      {
        if (paramGogglesReplayRequest == null)
          throw new NullPointerException();
        this.gogglesReplayRequest_ = paramGogglesReplayRequest;
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesRequests(int paramInt, GogglesProtos.GogglesRequest.Builder paramBuilder)
      {
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setGogglesRequests(int paramInt, GogglesProtos.GogglesRequest paramGogglesRequest)
      {
        if (paramGogglesRequest == null)
          throw new NullPointerException();
        ensureGogglesRequestsIsMutable();
        this.gogglesRequests_.set(paramInt, paramGogglesRequest);
        return this;
      }

      @Deprecated
      public Builder setNativeClientLogEventRequest(int paramInt, NativeClientLoggingProtos.NativeClientLogEventRequest.Builder paramBuilder)
      {
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setNativeClientLogEventRequest(int paramInt, NativeClientLoggingProtos.NativeClientLogEventRequest paramNativeClientLogEventRequest)
      {
        if (paramNativeClientLogEventRequest == null)
          throw new NullPointerException();
        ensureNativeClientLogEventRequestIsMutable();
        this.nativeClientLogEventRequest_.set(paramInt, paramNativeClientLogEventRequest);
        return this;
      }

      @Deprecated
      public Builder setSearchWindowRetrieveRequests(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setSearchWindowRetrieveRequests(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveRequestsIsMutable();
        this.searchWindowRetrieveRequests_.set(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder setSearchWindowUpdateRequests(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setSearchWindowUpdateRequests(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateRequestsIsMutable();
        this.searchWindowUpdateRequests_.set(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder setSessionConfigRequest(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        this.sessionConfigRequest_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSessionConfigRequest(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        this.sessionConfigRequest_ = paramDeprecatedMessage;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSetHistoryConfigRequest(HistoryConfigProtos.SetHistoryConfigRequest.Builder paramBuilder)
      {
        this.setHistoryConfigRequest_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSetHistoryConfigRequest(HistoryConfigProtos.SetHistoryConfigRequest paramSetHistoryConfigRequest)
      {
        if (paramSetHistoryConfigRequest == null)
          throw new NullPointerException();
        this.setHistoryConfigRequest_ = paramSetHistoryConfigRequest;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setTraceRequests(int paramInt, TracingProtos.TraceRequest.Builder paramBuilder)
      {
        ensureTraceRequestsIsMutable();
        this.traceRequests_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setTraceRequests(int paramInt, TracingProtos.TraceRequest paramTraceRequest)
      {
        if (paramTraceRequest == null)
          throw new NullPointerException();
        ensureTraceRequestsIsMutable();
        this.traceRequests_.set(paramInt, paramTraceRequest);
        return this;
      }

      @Deprecated
      public Builder setTracingCookieRequest(TracingCookieProtos.TracingCookieRequest.Builder paramBuilder)
      {
        this.tracingCookieRequest_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setTracingCookieRequest(TracingCookieProtos.TracingCookieRequest paramTracingCookieRequest)
      {
        if (paramTracingCookieRequest == null)
          throw new NullPointerException();
        this.tracingCookieRequest_ = paramTracingCookieRequest;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface ContainerRequestOrBuilder extends MessageLiteOrBuilder
  {
    @Deprecated
    public abstract AndroidVersionInfoProtos.AndroidVersionInfoRequest getAndroidVersionInfoRequest();

    @Deprecated
    public abstract HistoryConfigProtos.GetHistoryConfigRequest getGetHistoryConfigRequest();

    @Deprecated
    public abstract GogglesReplayProtos.GogglesReplayRequest getGogglesReplayRequest();

    @Deprecated
    public abstract GogglesProtos.GogglesRequest getGogglesRequests(int paramInt);

    @Deprecated
    public abstract int getGogglesRequestsCount();

    @Deprecated
    public abstract List<GogglesProtos.GogglesRequest> getGogglesRequestsList();

    @Deprecated
    public abstract NativeClientLoggingProtos.NativeClientLogEventRequest getNativeClientLogEventRequest(int paramInt);

    @Deprecated
    public abstract int getNativeClientLogEventRequestCount();

    @Deprecated
    public abstract List<NativeClientLoggingProtos.NativeClientLogEventRequest> getNativeClientLogEventRequestList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSearchWindowRetrieveRequests(int paramInt);

    @Deprecated
    public abstract int getSearchWindowRetrieveRequestsCount();

    @Deprecated
    public abstract List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveRequestsList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSearchWindowUpdateRequests(int paramInt);

    @Deprecated
    public abstract int getSearchWindowUpdateRequestsCount();

    @Deprecated
    public abstract List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateRequestsList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSessionConfigRequest();

    @Deprecated
    public abstract HistoryConfigProtos.SetHistoryConfigRequest getSetHistoryConfigRequest();

    @Deprecated
    public abstract TracingProtos.TraceRequest getTraceRequests(int paramInt);

    @Deprecated
    public abstract int getTraceRequestsCount();

    @Deprecated
    public abstract List<TracingProtos.TraceRequest> getTraceRequestsList();

    @Deprecated
    public abstract TracingCookieProtos.TracingCookieRequest getTracingCookieRequest();

    @Deprecated
    public abstract boolean hasAndroidVersionInfoRequest();

    @Deprecated
    public abstract boolean hasGetHistoryConfigRequest();

    @Deprecated
    public abstract boolean hasGogglesReplayRequest();

    @Deprecated
    public abstract boolean hasSessionConfigRequest();

    @Deprecated
    public abstract boolean hasSetHistoryConfigRequest();

    @Deprecated
    public abstract boolean hasTracingCookieRequest();
  }

  public static final class ContainerResponse extends GeneratedMessageLite
    implements ContainerProtos.ContainerResponseOrBuilder
  {
    public static final int GET_HISTORY_CONFIG_RESPONSE_FIELD_NUMBER = 5;
    public static final int GOGGLES_REPLAY_RESPONSE_FIELD_NUMBER = 7;
    public static final int GOGGLES_RESPONSES_FIELD_NUMBER = 1;
    public static final int NATIVE_CLIENT_LOG_EVENT_RESPONSE_FIELD_NUMBER = 13;
    public static final int SEARCH_WINDOW_RETRIEVE_RESPONSES_FIELD_NUMBER = 16;
    public static final int SEARCH_WINDOW_UPDATE_RESPONSES_FIELD_NUMBER = 15;
    public static final int SESSION_CONFIG_RESPONSE_FIELD_NUMBER = 12;
    public static final int SET_HISTORY_CONFIG_RESPONSE_FIELD_NUMBER = 6;
    public static final int TRACE_RESPONSES_FIELD_NUMBER = 3;
    public static final int TRACING_COOKIE_RESPONSE_FIELD_NUMBER = 4;
    public static final int VERSION_INFO_RESPONSE_FIELD_NUMBER = 14;
    private static final ContainerResponse defaultInstance = new ContainerResponse(true);
    private static final long serialVersionUID;
    private int bitField0_;
    private HistoryConfigProtos.GetHistoryConfigResponse getHistoryConfigResponse_;
    private GogglesReplayProtos.GogglesReplayResponse gogglesReplayResponse_;
    private List<GogglesProtos.GogglesResponse> gogglesResponses_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private List<NativeClientLoggingProtos.NativeClientLogEventResponse> nativeClientLogEventResponse_;
    private List<ContainerProtos.DeprecatedMessage> searchWindowRetrieveResponses_;
    private List<ContainerProtos.DeprecatedMessage> searchWindowUpdateResponses_;
    private ContainerProtos.DeprecatedMessage sessionConfigResponse_;
    private HistoryConfigProtos.SetHistoryConfigResponse setHistoryConfigResponse_;
    private List<TracingProtos.TraceResponse> traceResponses_;
    private TracingCookieProtos.TracingCookieResponse tracingCookieResponse_;
    private AndroidVersionInfoProtos.AndroidVersionInfoResponse versionInfoResponse_;

    static
    {
      defaultInstance.initFields();
    }

    private ContainerResponse(Builder paramBuilder)
    {
      super();
    }

    private ContainerResponse(boolean paramBoolean)
    {
    }

    public static ContainerResponse getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
      this.sessionConfigResponse_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
      this.getHistoryConfigResponse_ = HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance();
      this.setHistoryConfigResponse_ = HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance();
      this.tracingCookieResponse_ = TracingCookieProtos.TracingCookieResponse.getDefaultInstance();
      this.gogglesResponses_ = Collections.emptyList();
      this.gogglesReplayResponse_ = GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance();
      this.traceResponses_ = Collections.emptyList();
      this.nativeClientLogEventResponse_ = Collections.emptyList();
      this.versionInfoResponse_ = AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance();
      this.searchWindowUpdateResponses_ = Collections.emptyList();
      this.searchWindowRetrieveResponses_ = Collections.emptyList();
    }

    public static Builder newBuilder()
    {
      return Builder.access$1900();
    }

    public static Builder newBuilder(ContainerResponse paramContainerResponse)
    {
      return newBuilder().mergeFrom(paramContainerResponse);
    }

    public static ContainerResponse parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static ContainerResponse parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static ContainerResponse parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static ContainerResponse parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static ContainerResponse parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static ContainerResponse parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static ContainerResponse parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static ContainerResponse parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static ContainerResponse parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static ContainerResponse parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public ContainerResponse getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    @Deprecated
    public HistoryConfigProtos.GetHistoryConfigResponse getGetHistoryConfigResponse()
    {
      return this.getHistoryConfigResponse_;
    }

    @Deprecated
    public GogglesReplayProtos.GogglesReplayResponse getGogglesReplayResponse()
    {
      return this.gogglesReplayResponse_;
    }

    @Deprecated
    public GogglesProtos.GogglesResponse getGogglesResponses(int paramInt)
    {
      return (GogglesProtos.GogglesResponse)this.gogglesResponses_.get(paramInt);
    }

    @Deprecated
    public int getGogglesResponsesCount()
    {
      return this.gogglesResponses_.size();
    }

    @Deprecated
    public List<GogglesProtos.GogglesResponse> getGogglesResponsesList()
    {
      return this.gogglesResponses_;
    }

    @Deprecated
    public GogglesProtos.GogglesResponseOrBuilder getGogglesResponsesOrBuilder(int paramInt)
    {
      return (GogglesProtos.GogglesResponseOrBuilder)this.gogglesResponses_.get(paramInt);
    }

    @Deprecated
    public List<? extends GogglesProtos.GogglesResponseOrBuilder> getGogglesResponsesOrBuilderList()
    {
      return this.gogglesResponses_;
    }

    @Deprecated
    public NativeClientLoggingProtos.NativeClientLogEventResponse getNativeClientLogEventResponse(int paramInt)
    {
      return (NativeClientLoggingProtos.NativeClientLogEventResponse)this.nativeClientLogEventResponse_.get(paramInt);
    }

    @Deprecated
    public int getNativeClientLogEventResponseCount()
    {
      return this.nativeClientLogEventResponse_.size();
    }

    @Deprecated
    public List<NativeClientLoggingProtos.NativeClientLogEventResponse> getNativeClientLogEventResponseList()
    {
      return this.nativeClientLogEventResponse_;
    }

    @Deprecated
    public NativeClientLoggingProtos.NativeClientLogEventResponseOrBuilder getNativeClientLogEventResponseOrBuilder(int paramInt)
    {
      return (NativeClientLoggingProtos.NativeClientLogEventResponseOrBuilder)this.nativeClientLogEventResponse_.get(paramInt);
    }

    @Deprecated
    public List<? extends NativeClientLoggingProtos.NativeClientLogEventResponseOrBuilder> getNativeClientLogEventResponseOrBuilderList()
    {
      return this.nativeClientLogEventResponse_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSearchWindowRetrieveResponses(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessage)this.searchWindowRetrieveResponses_.get(paramInt);
    }

    @Deprecated
    public int getSearchWindowRetrieveResponsesCount()
    {
      return this.searchWindowRetrieveResponses_.size();
    }

    @Deprecated
    public List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveResponsesList()
    {
      return this.searchWindowRetrieveResponses_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessageOrBuilder getSearchWindowRetrieveResponsesOrBuilder(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessageOrBuilder)this.searchWindowRetrieveResponses_.get(paramInt);
    }

    @Deprecated
    public List<? extends ContainerProtos.DeprecatedMessageOrBuilder> getSearchWindowRetrieveResponsesOrBuilderList()
    {
      return this.searchWindowRetrieveResponses_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSearchWindowUpdateResponses(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessage)this.searchWindowUpdateResponses_.get(paramInt);
    }

    @Deprecated
    public int getSearchWindowUpdateResponsesCount()
    {
      return this.searchWindowUpdateResponses_.size();
    }

    @Deprecated
    public List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateResponsesList()
    {
      return this.searchWindowUpdateResponses_;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessageOrBuilder getSearchWindowUpdateResponsesOrBuilder(int paramInt)
    {
      return (ContainerProtos.DeprecatedMessageOrBuilder)this.searchWindowUpdateResponses_.get(paramInt);
    }

    @Deprecated
    public List<? extends ContainerProtos.DeprecatedMessageOrBuilder> getSearchWindowUpdateResponsesOrBuilderList()
    {
      return this.searchWindowUpdateResponses_;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0;
      int k = 0;
      while (j < this.gogglesResponses_.size())
      {
        k += CodedOutputStream.computeMessageSize(1, (MessageLite)this.gogglesResponses_.get(j));
        j++;
      }
      for (int m = 0; m < this.traceResponses_.size(); m++)
        k += CodedOutputStream.computeMessageSize(3, (MessageLite)this.traceResponses_.get(m));
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeMessageSize(4, this.tracingCookieResponse_);
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeMessageSize(5, this.getHistoryConfigResponse_);
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeMessageSize(6, this.setHistoryConfigResponse_);
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeMessageSize(7, this.gogglesReplayResponse_);
      if ((0x1 & this.bitField0_) == 1)
        k += CodedOutputStream.computeMessageSize(12, this.sessionConfigResponse_);
      for (int n = 0; n < this.nativeClientLogEventResponse_.size(); n++)
        k += CodedOutputStream.computeMessageSize(13, (MessageLite)this.nativeClientLogEventResponse_.get(n));
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeMessageSize(14, this.versionInfoResponse_);
      int i3;
      for (int i1 = 0; ; i1++)
      {
        int i2 = this.searchWindowUpdateResponses_.size();
        i3 = 0;
        if (i1 >= i2)
          break;
        k += CodedOutputStream.computeMessageSize(15, (MessageLite)this.searchWindowUpdateResponses_.get(i1));
      }
      while (i3 < this.searchWindowRetrieveResponses_.size())
      {
        k += CodedOutputStream.computeMessageSize(16, (MessageLite)this.searchWindowRetrieveResponses_.get(i3));
        i3++;
      }
      this.memoizedSerializedSize = k;
      return k;
    }

    @Deprecated
    public ContainerProtos.DeprecatedMessage getSessionConfigResponse()
    {
      return this.sessionConfigResponse_;
    }

    @Deprecated
    public HistoryConfigProtos.SetHistoryConfigResponse getSetHistoryConfigResponse()
    {
      return this.setHistoryConfigResponse_;
    }

    @Deprecated
    public TracingProtos.TraceResponse getTraceResponses(int paramInt)
    {
      return (TracingProtos.TraceResponse)this.traceResponses_.get(paramInt);
    }

    @Deprecated
    public int getTraceResponsesCount()
    {
      return this.traceResponses_.size();
    }

    @Deprecated
    public List<TracingProtos.TraceResponse> getTraceResponsesList()
    {
      return this.traceResponses_;
    }

    @Deprecated
    public TracingProtos.TraceResponseOrBuilder getTraceResponsesOrBuilder(int paramInt)
    {
      return (TracingProtos.TraceResponseOrBuilder)this.traceResponses_.get(paramInt);
    }

    @Deprecated
    public List<? extends TracingProtos.TraceResponseOrBuilder> getTraceResponsesOrBuilderList()
    {
      return this.traceResponses_;
    }

    @Deprecated
    public TracingCookieProtos.TracingCookieResponse getTracingCookieResponse()
    {
      return this.tracingCookieResponse_;
    }

    @Deprecated
    public AndroidVersionInfoProtos.AndroidVersionInfoResponse getVersionInfoResponse()
    {
      return this.versionInfoResponse_;
    }

    @Deprecated
    public boolean hasGetHistoryConfigResponse()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    @Deprecated
    public boolean hasGogglesReplayResponse()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    @Deprecated
    public boolean hasSessionConfigResponse()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    @Deprecated
    public boolean hasSetHistoryConfigResponse()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    @Deprecated
    public boolean hasTracingCookieResponse()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    @Deprecated
    public boolean hasVersionInfoResponse()
    {
      return (0x20 & this.bitField0_) == 32;
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
      if ((hasGetHistoryConfigResponse()) && (!getGetHistoryConfigResponse().isInitialized()))
      {
        this.memoizedIsInitialized = 0;
        return false;
      }
      for (int k = 0; k < getGogglesResponsesCount(); k++)
        if (!getGogglesResponses(k).isInitialized())
        {
          this.memoizedIsInitialized = 0;
          return false;
        }
      if ((hasGogglesReplayResponse()) && (!getGogglesReplayResponse().isInitialized()))
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
      for (int i = 0; i < this.gogglesResponses_.size(); i++)
        paramCodedOutputStream.writeMessage(1, (MessageLite)this.gogglesResponses_.get(i));
      for (int j = 0; j < this.traceResponses_.size(); j++)
        paramCodedOutputStream.writeMessage(3, (MessageLite)this.traceResponses_.get(j));
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeMessage(4, this.tracingCookieResponse_);
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeMessage(5, this.getHistoryConfigResponse_);
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeMessage(6, this.setHistoryConfigResponse_);
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeMessage(7, this.gogglesReplayResponse_);
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeMessage(12, this.sessionConfigResponse_);
      for (int k = 0; k < this.nativeClientLogEventResponse_.size(); k++)
        paramCodedOutputStream.writeMessage(13, (MessageLite)this.nativeClientLogEventResponse_.get(k));
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeMessage(14, this.versionInfoResponse_);
      int i1;
      for (int m = 0; ; m++)
      {
        int n = this.searchWindowUpdateResponses_.size();
        i1 = 0;
        if (m >= n)
          break;
        paramCodedOutputStream.writeMessage(15, (MessageLite)this.searchWindowUpdateResponses_.get(m));
      }
      while (i1 < this.searchWindowRetrieveResponses_.size())
      {
        paramCodedOutputStream.writeMessage(16, (MessageLite)this.searchWindowRetrieveResponses_.get(i1));
        i1++;
      }
    }

    public static final class Builder extends GeneratedMessageLite.Builder<ContainerProtos.ContainerResponse, Builder>
      implements ContainerProtos.ContainerResponseOrBuilder
    {
      private int bitField0_;
      private HistoryConfigProtos.GetHistoryConfigResponse getHistoryConfigResponse_ = HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance();
      private GogglesReplayProtos.GogglesReplayResponse gogglesReplayResponse_ = GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance();
      private List<GogglesProtos.GogglesResponse> gogglesResponses_ = Collections.emptyList();
      private List<NativeClientLoggingProtos.NativeClientLogEventResponse> nativeClientLogEventResponse_ = Collections.emptyList();
      private List<ContainerProtos.DeprecatedMessage> searchWindowRetrieveResponses_ = Collections.emptyList();
      private List<ContainerProtos.DeprecatedMessage> searchWindowUpdateResponses_ = Collections.emptyList();
      private ContainerProtos.DeprecatedMessage sessionConfigResponse_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
      private HistoryConfigProtos.SetHistoryConfigResponse setHistoryConfigResponse_ = HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance();
      private List<TracingProtos.TraceResponse> traceResponses_ = Collections.emptyList();
      private TracingCookieProtos.TracingCookieResponse tracingCookieResponse_ = TracingCookieProtos.TracingCookieResponse.getDefaultInstance();
      private AndroidVersionInfoProtos.AndroidVersionInfoResponse versionInfoResponse_ = AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance();

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ContainerProtos.ContainerResponse buildParsed()
        throws InvalidProtocolBufferException
      {
        ContainerProtos.ContainerResponse localContainerResponse = buildPartial();
        if (!localContainerResponse.isInitialized())
          throw newUninitializedMessageException(localContainerResponse).asInvalidProtocolBufferException();
        return localContainerResponse;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void ensureGogglesResponsesIsMutable()
      {
        if ((0x10 & this.bitField0_) != 16)
        {
          this.gogglesResponses_ = new ArrayList(this.gogglesResponses_);
          this.bitField0_ = (0x10 | this.bitField0_);
        }
      }

      private void ensureNativeClientLogEventResponseIsMutable()
      {
        if ((0x80 & this.bitField0_) != 128)
        {
          this.nativeClientLogEventResponse_ = new ArrayList(this.nativeClientLogEventResponse_);
          this.bitField0_ = (0x80 | this.bitField0_);
        }
      }

      private void ensureSearchWindowRetrieveResponsesIsMutable()
      {
        if ((0x400 & this.bitField0_) != 1024)
        {
          this.searchWindowRetrieveResponses_ = new ArrayList(this.searchWindowRetrieveResponses_);
          this.bitField0_ = (0x400 | this.bitField0_);
        }
      }

      private void ensureSearchWindowUpdateResponsesIsMutable()
      {
        if ((0x200 & this.bitField0_) != 512)
        {
          this.searchWindowUpdateResponses_ = new ArrayList(this.searchWindowUpdateResponses_);
          this.bitField0_ = (0x200 | this.bitField0_);
        }
      }

      private void ensureTraceResponsesIsMutable()
      {
        if ((0x40 & this.bitField0_) != 64)
        {
          this.traceResponses_ = new ArrayList(this.traceResponses_);
          this.bitField0_ = (0x40 | this.bitField0_);
        }
      }

      private void maybeForceBuilderInitialization()
      {
      }

      @Deprecated
      public Builder addAllGogglesResponses(Iterable<? extends GogglesProtos.GogglesResponse> paramIterable)
      {
        ensureGogglesResponsesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.gogglesResponses_);
        return this;
      }

      @Deprecated
      public Builder addAllNativeClientLogEventResponse(Iterable<? extends NativeClientLoggingProtos.NativeClientLogEventResponse> paramIterable)
      {
        ensureNativeClientLogEventResponseIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.nativeClientLogEventResponse_);
        return this;
      }

      @Deprecated
      public Builder addAllSearchWindowRetrieveResponses(Iterable<? extends ContainerProtos.DeprecatedMessage> paramIterable)
      {
        ensureSearchWindowRetrieveResponsesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.searchWindowRetrieveResponses_);
        return this;
      }

      @Deprecated
      public Builder addAllSearchWindowUpdateResponses(Iterable<? extends ContainerProtos.DeprecatedMessage> paramIterable)
      {
        ensureSearchWindowUpdateResponsesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.searchWindowUpdateResponses_);
        return this;
      }

      @Deprecated
      public Builder addAllTraceResponses(Iterable<? extends TracingProtos.TraceResponse> paramIterable)
      {
        ensureTraceResponsesIsMutable();
        GeneratedMessageLite.Builder.addAll(paramIterable, this.traceResponses_);
        return this;
      }

      @Deprecated
      public Builder addGogglesResponses(int paramInt, GogglesProtos.GogglesResponse.Builder paramBuilder)
      {
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addGogglesResponses(int paramInt, GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (paramGogglesResponse == null)
          throw new NullPointerException();
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.add(paramInt, paramGogglesResponse);
        return this;
      }

      @Deprecated
      public Builder addGogglesResponses(GogglesProtos.GogglesResponse.Builder paramBuilder)
      {
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addGogglesResponses(GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (paramGogglesResponse == null)
          throw new NullPointerException();
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.add(paramGogglesResponse);
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventResponse(int paramInt, NativeClientLoggingProtos.NativeClientLogEventResponse.Builder paramBuilder)
      {
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventResponse(int paramInt, NativeClientLoggingProtos.NativeClientLogEventResponse paramNativeClientLogEventResponse)
      {
        if (paramNativeClientLogEventResponse == null)
          throw new NullPointerException();
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.add(paramInt, paramNativeClientLogEventResponse);
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventResponse(NativeClientLoggingProtos.NativeClientLogEventResponse.Builder paramBuilder)
      {
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addNativeClientLogEventResponse(NativeClientLoggingProtos.NativeClientLogEventResponse paramNativeClientLogEventResponse)
      {
        if (paramNativeClientLogEventResponse == null)
          throw new NullPointerException();
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.add(paramNativeClientLogEventResponse);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveResponses(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveResponses(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.add(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveResponses(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowRetrieveResponses(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.add(paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateResponses(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateResponses(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.add(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateResponses(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addSearchWindowUpdateResponses(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.add(paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder addTraceResponses(int paramInt, TracingProtos.TraceResponse.Builder paramBuilder)
      {
        ensureTraceResponsesIsMutable();
        this.traceResponses_.add(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addTraceResponses(int paramInt, TracingProtos.TraceResponse paramTraceResponse)
      {
        if (paramTraceResponse == null)
          throw new NullPointerException();
        ensureTraceResponsesIsMutable();
        this.traceResponses_.add(paramInt, paramTraceResponse);
        return this;
      }

      @Deprecated
      public Builder addTraceResponses(TracingProtos.TraceResponse.Builder paramBuilder)
      {
        ensureTraceResponsesIsMutable();
        this.traceResponses_.add(paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder addTraceResponses(TracingProtos.TraceResponse paramTraceResponse)
      {
        if (paramTraceResponse == null)
          throw new NullPointerException();
        ensureTraceResponsesIsMutable();
        this.traceResponses_.add(paramTraceResponse);
        return this;
      }

      public ContainerProtos.ContainerResponse build()
      {
        ContainerProtos.ContainerResponse localContainerResponse = buildPartial();
        if (!localContainerResponse.isInitialized())
          throw newUninitializedMessageException(localContainerResponse);
        return localContainerResponse;
      }

      public ContainerProtos.ContainerResponse buildPartial()
      {
        int i = 1;
        ContainerProtos.ContainerResponse localContainerResponse = new ContainerProtos.ContainerResponse(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          ContainerProtos.ContainerResponse.access$2102(localContainerResponse, this.sessionConfigResponse_);
          if ((j & 0x2) == 2)
            i |= 2;
          ContainerProtos.ContainerResponse.access$2202(localContainerResponse, this.getHistoryConfigResponse_);
          if ((j & 0x4) == 4)
            i |= 4;
          ContainerProtos.ContainerResponse.access$2302(localContainerResponse, this.setHistoryConfigResponse_);
          if ((j & 0x8) == 8)
            i |= 8;
          ContainerProtos.ContainerResponse.access$2402(localContainerResponse, this.tracingCookieResponse_);
          if ((0x10 & this.bitField0_) == 16)
          {
            this.gogglesResponses_ = Collections.unmodifiableList(this.gogglesResponses_);
            this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
          }
          ContainerProtos.ContainerResponse.access$2502(localContainerResponse, this.gogglesResponses_);
          if ((j & 0x20) == 32)
            i |= 16;
          ContainerProtos.ContainerResponse.access$2602(localContainerResponse, this.gogglesReplayResponse_);
          if ((0x40 & this.bitField0_) == 64)
          {
            this.traceResponses_ = Collections.unmodifiableList(this.traceResponses_);
            this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
          }
          ContainerProtos.ContainerResponse.access$2702(localContainerResponse, this.traceResponses_);
          if ((0x80 & this.bitField0_) == 128)
          {
            this.nativeClientLogEventResponse_ = Collections.unmodifiableList(this.nativeClientLogEventResponse_);
            this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
          }
          ContainerProtos.ContainerResponse.access$2802(localContainerResponse, this.nativeClientLogEventResponse_);
          if ((j & 0x100) == 256)
            i |= 32;
          ContainerProtos.ContainerResponse.access$2902(localContainerResponse, this.versionInfoResponse_);
          if ((0x200 & this.bitField0_) == 512)
          {
            this.searchWindowUpdateResponses_ = Collections.unmodifiableList(this.searchWindowUpdateResponses_);
            this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
          }
          ContainerProtos.ContainerResponse.access$3002(localContainerResponse, this.searchWindowUpdateResponses_);
          if ((0x400 & this.bitField0_) == 1024)
          {
            this.searchWindowRetrieveResponses_ = Collections.unmodifiableList(this.searchWindowRetrieveResponses_);
            this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
          }
          ContainerProtos.ContainerResponse.access$3102(localContainerResponse, this.searchWindowRetrieveResponses_);
          ContainerProtos.ContainerResponse.access$3202(localContainerResponse, i);
          return localContainerResponse;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.sessionConfigResponse_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.getHistoryConfigResponse_ = HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.setHistoryConfigResponse_ = HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.tracingCookieResponse_ = TracingCookieProtos.TracingCookieResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.gogglesResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.gogglesReplayResponse_ = GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.traceResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.nativeClientLogEventResponse_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        this.versionInfoResponse_ = AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        this.searchWindowUpdateResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        this.searchWindowRetrieveResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGetHistoryConfigResponse()
      {
        this.getHistoryConfigResponse_ = HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesReplayResponse()
      {
        this.gogglesReplayResponse_ = GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearGogglesResponses()
      {
        this.gogglesResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearNativeClientLogEventResponse()
      {
        this.nativeClientLogEventResponse_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSearchWindowRetrieveResponses()
      {
        this.searchWindowRetrieveResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSearchWindowUpdateResponses()
      {
        this.searchWindowUpdateResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSessionConfigResponse()
      {
        this.sessionConfigResponse_ = ContainerProtos.DeprecatedMessage.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearSetHistoryConfigResponse()
      {
        this.setHistoryConfigResponse_ = HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearTraceResponses()
      {
        this.traceResponses_ = Collections.emptyList();
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearTracingCookieResponse()
      {
        this.tracingCookieResponse_ = TracingCookieProtos.TracingCookieResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder clearVersionInfoResponse()
      {
        this.versionInfoResponse_ = AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance();
        this.bitField0_ = (0xFFFFFEFF & this.bitField0_);
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public ContainerProtos.ContainerResponse getDefaultInstanceForType()
      {
        return ContainerProtos.ContainerResponse.getDefaultInstance();
      }

      @Deprecated
      public HistoryConfigProtos.GetHistoryConfigResponse getGetHistoryConfigResponse()
      {
        return this.getHistoryConfigResponse_;
      }

      @Deprecated
      public GogglesReplayProtos.GogglesReplayResponse getGogglesReplayResponse()
      {
        return this.gogglesReplayResponse_;
      }

      @Deprecated
      public GogglesProtos.GogglesResponse getGogglesResponses(int paramInt)
      {
        return (GogglesProtos.GogglesResponse)this.gogglesResponses_.get(paramInt);
      }

      @Deprecated
      public int getGogglesResponsesCount()
      {
        return this.gogglesResponses_.size();
      }

      @Deprecated
      public List<GogglesProtos.GogglesResponse> getGogglesResponsesList()
      {
        return Collections.unmodifiableList(this.gogglesResponses_);
      }

      @Deprecated
      public NativeClientLoggingProtos.NativeClientLogEventResponse getNativeClientLogEventResponse(int paramInt)
      {
        return (NativeClientLoggingProtos.NativeClientLogEventResponse)this.nativeClientLogEventResponse_.get(paramInt);
      }

      @Deprecated
      public int getNativeClientLogEventResponseCount()
      {
        return this.nativeClientLogEventResponse_.size();
      }

      @Deprecated
      public List<NativeClientLoggingProtos.NativeClientLogEventResponse> getNativeClientLogEventResponseList()
      {
        return Collections.unmodifiableList(this.nativeClientLogEventResponse_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSearchWindowRetrieveResponses(int paramInt)
      {
        return (ContainerProtos.DeprecatedMessage)this.searchWindowRetrieveResponses_.get(paramInt);
      }

      @Deprecated
      public int getSearchWindowRetrieveResponsesCount()
      {
        return this.searchWindowRetrieveResponses_.size();
      }

      @Deprecated
      public List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveResponsesList()
      {
        return Collections.unmodifiableList(this.searchWindowRetrieveResponses_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSearchWindowUpdateResponses(int paramInt)
      {
        return (ContainerProtos.DeprecatedMessage)this.searchWindowUpdateResponses_.get(paramInt);
      }

      @Deprecated
      public int getSearchWindowUpdateResponsesCount()
      {
        return this.searchWindowUpdateResponses_.size();
      }

      @Deprecated
      public List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateResponsesList()
      {
        return Collections.unmodifiableList(this.searchWindowUpdateResponses_);
      }

      @Deprecated
      public ContainerProtos.DeprecatedMessage getSessionConfigResponse()
      {
        return this.sessionConfigResponse_;
      }

      @Deprecated
      public HistoryConfigProtos.SetHistoryConfigResponse getSetHistoryConfigResponse()
      {
        return this.setHistoryConfigResponse_;
      }

      @Deprecated
      public TracingProtos.TraceResponse getTraceResponses(int paramInt)
      {
        return (TracingProtos.TraceResponse)this.traceResponses_.get(paramInt);
      }

      @Deprecated
      public int getTraceResponsesCount()
      {
        return this.traceResponses_.size();
      }

      @Deprecated
      public List<TracingProtos.TraceResponse> getTraceResponsesList()
      {
        return Collections.unmodifiableList(this.traceResponses_);
      }

      @Deprecated
      public TracingCookieProtos.TracingCookieResponse getTracingCookieResponse()
      {
        return this.tracingCookieResponse_;
      }

      @Deprecated
      public AndroidVersionInfoProtos.AndroidVersionInfoResponse getVersionInfoResponse()
      {
        return this.versionInfoResponse_;
      }

      @Deprecated
      public boolean hasGetHistoryConfigResponse()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      @Deprecated
      public boolean hasGogglesReplayResponse()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      @Deprecated
      public boolean hasSessionConfigResponse()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      @Deprecated
      public boolean hasSetHistoryConfigResponse()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      @Deprecated
      public boolean hasTracingCookieResponse()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      @Deprecated
      public boolean hasVersionInfoResponse()
      {
        return (0x100 & this.bitField0_) == 256;
      }

      public final boolean isInitialized()
      {
        if ((hasGetHistoryConfigResponse()) && (!getGetHistoryConfigResponse().isInitialized()));
        label46: 
        do
        {
          return false;
          for (int i = 0; ; i++)
          {
            if (i >= getGogglesResponsesCount())
              break label46;
            if (!getGogglesResponses(i).isInitialized())
              break;
          }
        }
        while ((hasGogglesReplayResponse()) && (!getGogglesReplayResponse().isInitialized()));
        return true;
      }

      public Builder mergeFrom(ContainerProtos.ContainerResponse paramContainerResponse)
      {
        if (paramContainerResponse == ContainerProtos.ContainerResponse.getDefaultInstance());
        label388: label407: 
        while (true)
        {
          return this;
          if (paramContainerResponse.hasSessionConfigResponse())
            mergeSessionConfigResponse(paramContainerResponse.getSessionConfigResponse());
          if (paramContainerResponse.hasGetHistoryConfigResponse())
            mergeGetHistoryConfigResponse(paramContainerResponse.getGetHistoryConfigResponse());
          if (paramContainerResponse.hasSetHistoryConfigResponse())
            mergeSetHistoryConfigResponse(paramContainerResponse.getSetHistoryConfigResponse());
          if (paramContainerResponse.hasTracingCookieResponse())
            mergeTracingCookieResponse(paramContainerResponse.getTracingCookieResponse());
          if (!paramContainerResponse.gogglesResponses_.isEmpty())
          {
            if (this.gogglesResponses_.isEmpty())
            {
              this.gogglesResponses_ = paramContainerResponse.gogglesResponses_;
              this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
            }
          }
          else
          {
            if (paramContainerResponse.hasGogglesReplayResponse())
              mergeGogglesReplayResponse(paramContainerResponse.getGogglesReplayResponse());
            if (!paramContainerResponse.traceResponses_.isEmpty())
            {
              if (!this.traceResponses_.isEmpty())
                break label346;
              this.traceResponses_ = paramContainerResponse.traceResponses_;
              this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
            }
            label175: if (!paramContainerResponse.nativeClientLogEventResponse_.isEmpty())
            {
              if (!this.nativeClientLogEventResponse_.isEmpty())
                break label367;
              this.nativeClientLogEventResponse_ = paramContainerResponse.nativeClientLogEventResponse_;
              this.bitField0_ = (0xFFFFFF7F & this.bitField0_);
            }
            label219: if (paramContainerResponse.hasVersionInfoResponse())
              mergeVersionInfoResponse(paramContainerResponse.getVersionInfoResponse());
            if (!paramContainerResponse.searchWindowUpdateResponses_.isEmpty())
            {
              if (!this.searchWindowUpdateResponses_.isEmpty())
                break label388;
              this.searchWindowUpdateResponses_ = paramContainerResponse.searchWindowUpdateResponses_;
              this.bitField0_ = (0xFFFFFDFF & this.bitField0_);
            }
          }
          while (true)
          {
            if (paramContainerResponse.searchWindowRetrieveResponses_.isEmpty())
              break label407;
            if (!this.searchWindowRetrieveResponses_.isEmpty())
              break label409;
            this.searchWindowRetrieveResponses_ = paramContainerResponse.searchWindowRetrieveResponses_;
            this.bitField0_ = (0xFFFFFBFF & this.bitField0_);
            return this;
            ensureGogglesResponsesIsMutable();
            this.gogglesResponses_.addAll(paramContainerResponse.gogglesResponses_);
            break;
            label346: ensureTraceResponsesIsMutable();
            this.traceResponses_.addAll(paramContainerResponse.traceResponses_);
            break label175;
            label367: ensureNativeClientLogEventResponseIsMutable();
            this.nativeClientLogEventResponse_.addAll(paramContainerResponse.nativeClientLogEventResponse_);
            break label219;
            ensureSearchWindowUpdateResponsesIsMutable();
            this.searchWindowUpdateResponses_.addAll(paramContainerResponse.searchWindowUpdateResponses_);
          }
        }
        label409: ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.addAll(paramContainerResponse.searchWindowRetrieveResponses_);
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
            GogglesProtos.GogglesResponse.Builder localBuilder10 = GogglesProtos.GogglesResponse.newBuilder();
            paramCodedInputStream.readMessage(localBuilder10, paramExtensionRegistryLite);
            addGogglesResponses(localBuilder10.buildPartial());
            break;
          case 26:
            TracingProtos.TraceResponse.Builder localBuilder9 = TracingProtos.TraceResponse.newBuilder();
            paramCodedInputStream.readMessage(localBuilder9, paramExtensionRegistryLite);
            addTraceResponses(localBuilder9.buildPartial());
            break;
          case 34:
            TracingCookieProtos.TracingCookieResponse.Builder localBuilder8 = TracingCookieProtos.TracingCookieResponse.newBuilder();
            if (hasTracingCookieResponse())
              localBuilder8.mergeFrom(getTracingCookieResponse());
            paramCodedInputStream.readMessage(localBuilder8, paramExtensionRegistryLite);
            setTracingCookieResponse(localBuilder8.buildPartial());
            break;
          case 42:
            HistoryConfigProtos.GetHistoryConfigResponse.Builder localBuilder7 = HistoryConfigProtos.GetHistoryConfigResponse.newBuilder();
            if (hasGetHistoryConfigResponse())
              localBuilder7.mergeFrom(getGetHistoryConfigResponse());
            paramCodedInputStream.readMessage(localBuilder7, paramExtensionRegistryLite);
            setGetHistoryConfigResponse(localBuilder7.buildPartial());
            break;
          case 50:
            HistoryConfigProtos.SetHistoryConfigResponse.Builder localBuilder6 = HistoryConfigProtos.SetHistoryConfigResponse.newBuilder();
            if (hasSetHistoryConfigResponse())
              localBuilder6.mergeFrom(getSetHistoryConfigResponse());
            paramCodedInputStream.readMessage(localBuilder6, paramExtensionRegistryLite);
            setSetHistoryConfigResponse(localBuilder6.buildPartial());
            break;
          case 58:
            GogglesReplayProtos.GogglesReplayResponse.Builder localBuilder5 = GogglesReplayProtos.GogglesReplayResponse.newBuilder();
            if (hasGogglesReplayResponse())
              localBuilder5.mergeFrom(getGogglesReplayResponse());
            paramCodedInputStream.readMessage(localBuilder5, paramExtensionRegistryLite);
            setGogglesReplayResponse(localBuilder5.buildPartial());
            break;
          case 98:
            ContainerProtos.DeprecatedMessage.Builder localBuilder4 = ContainerProtos.DeprecatedMessage.newBuilder();
            if (hasSessionConfigResponse())
              localBuilder4.mergeFrom(getSessionConfigResponse());
            paramCodedInputStream.readMessage(localBuilder4, paramExtensionRegistryLite);
            setSessionConfigResponse(localBuilder4.buildPartial());
            break;
          case 106:
            NativeClientLoggingProtos.NativeClientLogEventResponse.Builder localBuilder3 = NativeClientLoggingProtos.NativeClientLogEventResponse.newBuilder();
            paramCodedInputStream.readMessage(localBuilder3, paramExtensionRegistryLite);
            addNativeClientLogEventResponse(localBuilder3.buildPartial());
            break;
          case 114:
            AndroidVersionInfoProtos.AndroidVersionInfoResponse.Builder localBuilder = AndroidVersionInfoProtos.AndroidVersionInfoResponse.newBuilder();
            if (hasVersionInfoResponse())
              localBuilder.mergeFrom(getVersionInfoResponse());
            paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            setVersionInfoResponse(localBuilder.buildPartial());
            break;
          case 122:
            ContainerProtos.DeprecatedMessage.Builder localBuilder2 = ContainerProtos.DeprecatedMessage.newBuilder();
            paramCodedInputStream.readMessage(localBuilder2, paramExtensionRegistryLite);
            addSearchWindowUpdateResponses(localBuilder2.buildPartial());
            break;
          case 130:
          }
          ContainerProtos.DeprecatedMessage.Builder localBuilder1 = ContainerProtos.DeprecatedMessage.newBuilder();
          paramCodedInputStream.readMessage(localBuilder1, paramExtensionRegistryLite);
          addSearchWindowRetrieveResponses(localBuilder1.buildPartial());
        }
      }

      @Deprecated
      public Builder mergeGetHistoryConfigResponse(HistoryConfigProtos.GetHistoryConfigResponse paramGetHistoryConfigResponse)
      {
        if (((0x2 & this.bitField0_) == 2) && (this.getHistoryConfigResponse_ != HistoryConfigProtos.GetHistoryConfigResponse.getDefaultInstance()));
        for (this.getHistoryConfigResponse_ = HistoryConfigProtos.GetHistoryConfigResponse.newBuilder(this.getHistoryConfigResponse_).mergeFrom(paramGetHistoryConfigResponse).buildPartial(); ; this.getHistoryConfigResponse_ = paramGetHistoryConfigResponse)
        {
          this.bitField0_ = (0x2 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeGogglesReplayResponse(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse)
      {
        if (((0x20 & this.bitField0_) == 32) && (this.gogglesReplayResponse_ != GogglesReplayProtos.GogglesReplayResponse.getDefaultInstance()));
        for (this.gogglesReplayResponse_ = GogglesReplayProtos.GogglesReplayResponse.newBuilder(this.gogglesReplayResponse_).mergeFrom(paramGogglesReplayResponse).buildPartial(); ; this.gogglesReplayResponse_ = paramGogglesReplayResponse)
        {
          this.bitField0_ = (0x20 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeSessionConfigResponse(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (((0x1 & this.bitField0_) == 1) && (this.sessionConfigResponse_ != ContainerProtos.DeprecatedMessage.getDefaultInstance()));
        for (this.sessionConfigResponse_ = ContainerProtos.DeprecatedMessage.newBuilder(this.sessionConfigResponse_).mergeFrom(paramDeprecatedMessage).buildPartial(); ; this.sessionConfigResponse_ = paramDeprecatedMessage)
        {
          this.bitField0_ = (0x1 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeSetHistoryConfigResponse(HistoryConfigProtos.SetHistoryConfigResponse paramSetHistoryConfigResponse)
      {
        if (((0x4 & this.bitField0_) == 4) && (this.setHistoryConfigResponse_ != HistoryConfigProtos.SetHistoryConfigResponse.getDefaultInstance()));
        for (this.setHistoryConfigResponse_ = HistoryConfigProtos.SetHistoryConfigResponse.newBuilder(this.setHistoryConfigResponse_).mergeFrom(paramSetHistoryConfigResponse).buildPartial(); ; this.setHistoryConfigResponse_ = paramSetHistoryConfigResponse)
        {
          this.bitField0_ = (0x4 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeTracingCookieResponse(TracingCookieProtos.TracingCookieResponse paramTracingCookieResponse)
      {
        if (((0x8 & this.bitField0_) == 8) && (this.tracingCookieResponse_ != TracingCookieProtos.TracingCookieResponse.getDefaultInstance()));
        for (this.tracingCookieResponse_ = TracingCookieProtos.TracingCookieResponse.newBuilder(this.tracingCookieResponse_).mergeFrom(paramTracingCookieResponse).buildPartial(); ; this.tracingCookieResponse_ = paramTracingCookieResponse)
        {
          this.bitField0_ = (0x8 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder mergeVersionInfoResponse(AndroidVersionInfoProtos.AndroidVersionInfoResponse paramAndroidVersionInfoResponse)
      {
        if (((0x100 & this.bitField0_) == 256) && (this.versionInfoResponse_ != AndroidVersionInfoProtos.AndroidVersionInfoResponse.getDefaultInstance()));
        for (this.versionInfoResponse_ = AndroidVersionInfoProtos.AndroidVersionInfoResponse.newBuilder(this.versionInfoResponse_).mergeFrom(paramAndroidVersionInfoResponse).buildPartial(); ; this.versionInfoResponse_ = paramAndroidVersionInfoResponse)
        {
          this.bitField0_ = (0x100 | this.bitField0_);
          return this;
        }
      }

      @Deprecated
      public Builder removeGogglesResponses(int paramInt)
      {
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeNativeClientLogEventResponse(int paramInt)
      {
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeSearchWindowRetrieveResponses(int paramInt)
      {
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeSearchWindowUpdateResponses(int paramInt)
      {
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder removeTraceResponses(int paramInt)
      {
        ensureTraceResponsesIsMutable();
        this.traceResponses_.remove(paramInt);
        return this;
      }

      @Deprecated
      public Builder setGetHistoryConfigResponse(HistoryConfigProtos.GetHistoryConfigResponse.Builder paramBuilder)
      {
        this.getHistoryConfigResponse_ = paramBuilder.build();
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGetHistoryConfigResponse(HistoryConfigProtos.GetHistoryConfigResponse paramGetHistoryConfigResponse)
      {
        if (paramGetHistoryConfigResponse == null)
          throw new NullPointerException();
        this.getHistoryConfigResponse_ = paramGetHistoryConfigResponse;
        this.bitField0_ = (0x2 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesReplayResponse(GogglesReplayProtos.GogglesReplayResponse.Builder paramBuilder)
      {
        this.gogglesReplayResponse_ = paramBuilder.build();
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesReplayResponse(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse)
      {
        if (paramGogglesReplayResponse == null)
          throw new NullPointerException();
        this.gogglesReplayResponse_ = paramGogglesReplayResponse;
        this.bitField0_ = (0x20 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setGogglesResponses(int paramInt, GogglesProtos.GogglesResponse.Builder paramBuilder)
      {
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setGogglesResponses(int paramInt, GogglesProtos.GogglesResponse paramGogglesResponse)
      {
        if (paramGogglesResponse == null)
          throw new NullPointerException();
        ensureGogglesResponsesIsMutable();
        this.gogglesResponses_.set(paramInt, paramGogglesResponse);
        return this;
      }

      @Deprecated
      public Builder setNativeClientLogEventResponse(int paramInt, NativeClientLoggingProtos.NativeClientLogEventResponse.Builder paramBuilder)
      {
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setNativeClientLogEventResponse(int paramInt, NativeClientLoggingProtos.NativeClientLogEventResponse paramNativeClientLogEventResponse)
      {
        if (paramNativeClientLogEventResponse == null)
          throw new NullPointerException();
        ensureNativeClientLogEventResponseIsMutable();
        this.nativeClientLogEventResponse_.set(paramInt, paramNativeClientLogEventResponse);
        return this;
      }

      @Deprecated
      public Builder setSearchWindowRetrieveResponses(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setSearchWindowRetrieveResponses(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowRetrieveResponsesIsMutable();
        this.searchWindowRetrieveResponses_.set(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder setSearchWindowUpdateResponses(int paramInt, ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setSearchWindowUpdateResponses(int paramInt, ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        ensureSearchWindowUpdateResponsesIsMutable();
        this.searchWindowUpdateResponses_.set(paramInt, paramDeprecatedMessage);
        return this;
      }

      @Deprecated
      public Builder setSessionConfigResponse(ContainerProtos.DeprecatedMessage.Builder paramBuilder)
      {
        this.sessionConfigResponse_ = paramBuilder.build();
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSessionConfigResponse(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == null)
          throw new NullPointerException();
        this.sessionConfigResponse_ = paramDeprecatedMessage;
        this.bitField0_ = (0x1 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSetHistoryConfigResponse(HistoryConfigProtos.SetHistoryConfigResponse.Builder paramBuilder)
      {
        this.setHistoryConfigResponse_ = paramBuilder.build();
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setSetHistoryConfigResponse(HistoryConfigProtos.SetHistoryConfigResponse paramSetHistoryConfigResponse)
      {
        if (paramSetHistoryConfigResponse == null)
          throw new NullPointerException();
        this.setHistoryConfigResponse_ = paramSetHistoryConfigResponse;
        this.bitField0_ = (0x4 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setTraceResponses(int paramInt, TracingProtos.TraceResponse.Builder paramBuilder)
      {
        ensureTraceResponsesIsMutable();
        this.traceResponses_.set(paramInt, paramBuilder.build());
        return this;
      }

      @Deprecated
      public Builder setTraceResponses(int paramInt, TracingProtos.TraceResponse paramTraceResponse)
      {
        if (paramTraceResponse == null)
          throw new NullPointerException();
        ensureTraceResponsesIsMutable();
        this.traceResponses_.set(paramInt, paramTraceResponse);
        return this;
      }

      @Deprecated
      public Builder setTracingCookieResponse(TracingCookieProtos.TracingCookieResponse.Builder paramBuilder)
      {
        this.tracingCookieResponse_ = paramBuilder.build();
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setTracingCookieResponse(TracingCookieProtos.TracingCookieResponse paramTracingCookieResponse)
      {
        if (paramTracingCookieResponse == null)
          throw new NullPointerException();
        this.tracingCookieResponse_ = paramTracingCookieResponse;
        this.bitField0_ = (0x8 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setVersionInfoResponse(AndroidVersionInfoProtos.AndroidVersionInfoResponse.Builder paramBuilder)
      {
        this.versionInfoResponse_ = paramBuilder.build();
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }

      @Deprecated
      public Builder setVersionInfoResponse(AndroidVersionInfoProtos.AndroidVersionInfoResponse paramAndroidVersionInfoResponse)
      {
        if (paramAndroidVersionInfoResponse == null)
          throw new NullPointerException();
        this.versionInfoResponse_ = paramAndroidVersionInfoResponse;
        this.bitField0_ = (0x100 | this.bitField0_);
        return this;
      }
    }
  }

  public static abstract interface ContainerResponseOrBuilder extends MessageLiteOrBuilder
  {
    @Deprecated
    public abstract HistoryConfigProtos.GetHistoryConfigResponse getGetHistoryConfigResponse();

    @Deprecated
    public abstract GogglesReplayProtos.GogglesReplayResponse getGogglesReplayResponse();

    @Deprecated
    public abstract GogglesProtos.GogglesResponse getGogglesResponses(int paramInt);

    @Deprecated
    public abstract int getGogglesResponsesCount();

    @Deprecated
    public abstract List<GogglesProtos.GogglesResponse> getGogglesResponsesList();

    @Deprecated
    public abstract NativeClientLoggingProtos.NativeClientLogEventResponse getNativeClientLogEventResponse(int paramInt);

    @Deprecated
    public abstract int getNativeClientLogEventResponseCount();

    @Deprecated
    public abstract List<NativeClientLoggingProtos.NativeClientLogEventResponse> getNativeClientLogEventResponseList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSearchWindowRetrieveResponses(int paramInt);

    @Deprecated
    public abstract int getSearchWindowRetrieveResponsesCount();

    @Deprecated
    public abstract List<ContainerProtos.DeprecatedMessage> getSearchWindowRetrieveResponsesList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSearchWindowUpdateResponses(int paramInt);

    @Deprecated
    public abstract int getSearchWindowUpdateResponsesCount();

    @Deprecated
    public abstract List<ContainerProtos.DeprecatedMessage> getSearchWindowUpdateResponsesList();

    @Deprecated
    public abstract ContainerProtos.DeprecatedMessage getSessionConfigResponse();

    @Deprecated
    public abstract HistoryConfigProtos.SetHistoryConfigResponse getSetHistoryConfigResponse();

    @Deprecated
    public abstract TracingProtos.TraceResponse getTraceResponses(int paramInt);

    @Deprecated
    public abstract int getTraceResponsesCount();

    @Deprecated
    public abstract List<TracingProtos.TraceResponse> getTraceResponsesList();

    @Deprecated
    public abstract TracingCookieProtos.TracingCookieResponse getTracingCookieResponse();

    @Deprecated
    public abstract AndroidVersionInfoProtos.AndroidVersionInfoResponse getVersionInfoResponse();

    @Deprecated
    public abstract boolean hasGetHistoryConfigResponse();

    @Deprecated
    public abstract boolean hasGogglesReplayResponse();

    @Deprecated
    public abstract boolean hasSessionConfigResponse();

    @Deprecated
    public abstract boolean hasSetHistoryConfigResponse();

    @Deprecated
    public abstract boolean hasTracingCookieResponse();

    @Deprecated
    public abstract boolean hasVersionInfoResponse();
  }

  public static final class DeprecatedMessage extends GeneratedMessageLite
    implements ContainerProtos.DeprecatedMessageOrBuilder
  {
    private static final DeprecatedMessage defaultInstance = new DeprecatedMessage(true);
    private static final long serialVersionUID;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;

    static
    {
      defaultInstance.initFields();
    }

    private DeprecatedMessage(Builder paramBuilder)
    {
      super();
    }

    private DeprecatedMessage(boolean paramBoolean)
    {
    }

    public static DeprecatedMessage getDefaultInstance()
    {
      return defaultInstance;
    }

    private void initFields()
    {
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(DeprecatedMessage paramDeprecatedMessage)
    {
      return newBuilder().mergeFrom(paramDeprecatedMessage);
    }

    public static DeprecatedMessage parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static DeprecatedMessage parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static DeprecatedMessage parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static DeprecatedMessage parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static DeprecatedMessage parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public DeprecatedMessage getDefaultInstanceForType()
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

    public static final class Builder extends GeneratedMessageLite.Builder<ContainerProtos.DeprecatedMessage, Builder>
      implements ContainerProtos.DeprecatedMessageOrBuilder
    {
      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private ContainerProtos.DeprecatedMessage buildParsed()
        throws InvalidProtocolBufferException
      {
        ContainerProtos.DeprecatedMessage localDeprecatedMessage = buildPartial();
        if (!localDeprecatedMessage.isInitialized())
          throw newUninitializedMessageException(localDeprecatedMessage).asInvalidProtocolBufferException();
        return localDeprecatedMessage;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public ContainerProtos.DeprecatedMessage build()
      {
        ContainerProtos.DeprecatedMessage localDeprecatedMessage = buildPartial();
        if (!localDeprecatedMessage.isInitialized())
          throw newUninitializedMessageException(localDeprecatedMessage);
        return localDeprecatedMessage;
      }

      public ContainerProtos.DeprecatedMessage buildPartial()
      {
        return new ContainerProtos.DeprecatedMessage(this, null);
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

      public ContainerProtos.DeprecatedMessage getDefaultInstanceForType()
      {
        return ContainerProtos.DeprecatedMessage.getDefaultInstance();
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(ContainerProtos.DeprecatedMessage paramDeprecatedMessage)
      {
        if (paramDeprecatedMessage == ContainerProtos.DeprecatedMessage.getDefaultInstance());
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

  public static abstract interface DeprecatedMessageOrBuilder extends MessageLiteOrBuilder
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.ContainerProtos
 * JD-Core Version:    0.6.2
 */