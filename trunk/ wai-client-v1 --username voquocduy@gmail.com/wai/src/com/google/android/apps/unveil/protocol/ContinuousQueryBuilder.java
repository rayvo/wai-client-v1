package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.nonstop.RequestIds;
import com.google.android.apps.unveil.protocol.nonstop.RequestIds.RequestType;
import com.google.goggles.ExtendedGogglesConfigProtos.ExtendedGogglesConfig;
import com.google.goggles.ExtendedGogglesConfigProtos.ExtendedGogglesConfig.Builder;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesRequest;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesRequest.Builder;
import com.google.goggles.ExtendedGogglesProtos.TrackedResult;
import com.google.goggles.FlowProtos.FlowData;
import com.google.goggles.GogglesProtos.Audio;
import com.google.goggles.GogglesProtos.Audio.Builder;
import com.google.goggles.GogglesProtos.GogglesRequest.Builder;
import com.google.goggles.LocationProtos.Location;
import com.google.protobuf.ByteString;
import java.util.ArrayList;
import java.util.Iterator;

public class ContinuousQueryBuilder extends QueryBuilder
{
  private static final UnveilLogger logger = new UnveilLogger();
  private byte[] audioData;
  private int audioSamplingRate;
  private ByteString encryptedLocation;
  private FlowProtos.FlowData flowData;
  private Integer pushSequenceNumber;
  private Long requestIdBase;
  private Session session;
  private final ArrayList<ExtendedGogglesProtos.TrackedResult> trackedResults = new ArrayList();
  private boolean wantResults;

  private void populateAudioData(GogglesProtos.GogglesRequest.Builder paramBuilder, byte[] paramArrayOfByte, long paramLong)
  {
    GogglesProtos.Audio.Builder localBuilder = GogglesProtos.Audio.newBuilder();
    localBuilder.setData(ByteString.copyFrom(paramArrayOfByte));
    localBuilder.setSampleRateHz((float)paramLong);
    paramBuilder.setAudio(localBuilder);
  }

  public ContinuousQueryBuilder addAudioData(byte[] paramArrayOfByte, int paramInt)
  {
    this.audioData = paramArrayOfByte;
    this.audioSamplingRate = paramInt;
    return this;
  }

  public void addEncryptedLocation(UnveilContext paramUnveilContext, ExtendedGogglesProtos.ExtendedGogglesRequest.Builder paramBuilder, LocationProtos.Location paramLocation)
  {
    if (this.encryptedLocation == null)
    {
      logger.w("Encrypting location in a continuous request, this is inefficient.", new Object[0]);
      QueryBuilder.addEncryptedLocation(paramBuilder, paramLocation, paramUnveilContext.getLatLngEncrypter());
      return;
    }
    paramBuilder.setEncryptedLatLng(this.encryptedLocation);
  }

  public void addFlowData(FlowProtos.FlowData paramFlowData)
  {
    this.flowData = paramFlowData;
  }

  public ContinuousQueryBuilder addLocation(LocationProtos.Location paramLocation, ByteString paramByteString)
  {
    super.addLocation(paramLocation);
    this.encryptedLocation = paramByteString;
    return this;
  }

  @Deprecated
  public QueryBuilder addLocation(LocationProtos.Location paramLocation)
  {
    return super.addLocation(paramLocation);
  }

  public ContinuousQueryBuilder addPushSequenceNumber(Integer paramInteger)
  {
    this.pushSequenceNumber = paramInteger;
    return this;
  }

  public ContinuousQueryBuilder addRequestIdBase(Long paramLong)
  {
    this.requestIdBase = paramLong;
    return this;
  }

  public void addTrackedResult(ExtendedGogglesProtos.TrackedResult paramTrackedResult)
  {
    this.trackedResults.add(paramTrackedResult);
  }

  public GogglesProtos.GogglesRequest.Builder buildGogglesRequestBuilder(UnveilContext paramUnveilContext)
  {
    logger.resetTime("Making request", new Object[0]);
    GogglesProtos.GogglesRequest.Builder localBuilder = super.buildGogglesRequestBuilder(paramUnveilContext);
    ExtendedGogglesProtos.ExtendedGogglesRequest.Builder localBuilder1 = ExtendedGogglesProtos.ExtendedGogglesRequest.newBuilder((ExtendedGogglesProtos.ExtendedGogglesRequest)localBuilder.getExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest));
    Iterator localIterator = this.trackedResults.iterator();
    while (localIterator.hasNext())
      localBuilder1.addTrackedResults((ExtendedGogglesProtos.TrackedResult)localIterator.next());
    localBuilder1.setSessionId(this.session.getSessionId());
    localBuilder.setExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest, localBuilder1.build());
    if (this.flowData != null)
      localBuilder.setFlow(this.flowData);
    ExtendedGogglesConfigProtos.ExtendedGogglesConfig.newBuilder().setWantAnnotationResults(this.wantResults);
    QueryManagerParams localQueryManagerParams = new QueryManagerParams(((BaseApplication)paramUnveilContext).getConfigurationProvider());
    localQueryManagerParams.disableSimilarImages = true;
    localQueryManagerParams.wantAnnotationResults = this.wantResults;
    localQueryManagerParams.canStoreToSearchHistory = paramUnveilContext.isSearchHistoryEnabled();
    localQueryManagerParams.maxResults = 10;
    localBuilder.setGogglesConfig(localQueryManagerParams.toGogglesConfig());
    ((ExtendedGogglesProtos.ExtendedGogglesRequest)localBuilder.getExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest));
    if (this.wantResults)
      localBuilder1.setReturnResults(true);
    long l;
    if (this.requestIdBase != null)
    {
      l = this.requestIdBase.longValue();
      if (this.imageData != null)
        break label297;
    }
    label297: for (RequestIds.RequestType localRequestType = RequestIds.RequestType.CLIENT_ANNOTATION; ; localRequestType = RequestIds.RequestType.IMAGE)
    {
      localBuilder1.setRequestId(RequestIds.encodeFullRequestId(l, localRequestType));
      if (this.pushSequenceNumber != null)
        localBuilder1.setSequenceNumber(this.pushSequenceNumber.intValue());
      if (this.audioData != null)
        populateAudioData(localBuilder, this.audioData, this.audioSamplingRate);
      localBuilder.setExtension(ExtendedGogglesProtos.ExtendedGogglesRequest.extendedGogglesRequest, localBuilder1.build());
      return localBuilder;
    }
  }

  public ContinuousQueryBuilder setSession(Session paramSession)
  {
    this.session = paramSession;
    return this;
  }

  public ContinuousQueryBuilder setWantResults(boolean paramBoolean)
  {
    this.wantResults = paramBoolean;
    return this;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.ContinuousQueryBuilder
 * JD-Core Version:    0.6.2
 */