package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.RestrictsProtos.Restricts;
import java.io.Serializable;
import java.util.List;

public class SingleShotQueryResponse extends QueryResponse
{
  private final RequestMetrics requestMetrics;
  private final String trackingId;

  public SingleShotQueryResponse(List<ResultItem> paramList1, List<ResultItem> paramList2, List<ResultItem> paramList3, RestrictsProtos.Restricts paramRestricts, QueryResponseFactory.QueryType paramQueryType, String paramString1, int paramInt, String paramString2, GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata, long paramLong, RequestMetrics paramRequestMetrics, String paramString3)
  {
    super(paramList1, paramList2, paramList3, paramRestricts, paramQueryType, paramString1, paramInt, paramString2, paramUserContributionMetadata, paramLong);
    this.requestMetrics = paramRequestMetrics;
    this.trackingId = paramString3;
  }

  public RequestMetrics getRequestMetrics()
  {
    return this.requestMetrics;
  }

  public String getTrackingId()
  {
    return this.trackingId;
  }

  public static class RequestMetrics
    implements Serializable
  {
    public final Size imageSize;
    public final int requestByteSize;
    public final long requestRoundTripTime;

    public RequestMetrics(Size paramSize, int paramInt, long paramLong)
    {
      this.imageSize = paramSize;
      this.requestByteSize = paramInt;
      this.requestRoundTripTime = paramLong;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.SingleShotQueryResponse
 * JD-Core Version:    0.6.2
 */