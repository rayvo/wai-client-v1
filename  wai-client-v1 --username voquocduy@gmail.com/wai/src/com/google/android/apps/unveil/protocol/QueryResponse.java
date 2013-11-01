package com.google.android.apps.unveil.protocol;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ContributedResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import com.google.goggles.RestrictsProtos.Restricts;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QueryResponse
  implements Serializable
{
  public static final String LOCALLY_GENERATED_RESPONSE_MOMENT_ID = "locallyGeneratedMoment";
  private static final UnveilLogger logger = new UnveilLogger();
  static final long serialVersionUID = 1L;
  private final List<ResultModel> allResults;
  private final List<ResultItem> eyeCandyResults;
  private final String momentId;
  private final List<ResultItem> puggleResults;
  private final String queryImageUrl;
  private final QueryResponseFactory.QueryType queryType;
  private final long responseReceivedTimestamp;
  private final List<ResultItem> results;
  private final int rotation;
  private final RestrictsProtos.Restricts suggestedRestricts;
  private final GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata userContribution;

  public QueryResponse(QueryResponse paramQueryResponse)
  {
    this(paramQueryResponse.results, paramQueryResponse.eyeCandyResults, paramQueryResponse.puggleResults, paramQueryResponse.suggestedRestricts, paramQueryResponse.queryType, paramQueryResponse.queryImageUrl, paramQueryResponse.rotation, paramQueryResponse.momentId, paramQueryResponse.userContribution, paramQueryResponse.responseReceivedTimestamp);
  }

  public QueryResponse(List<ResultItem> paramList1, List<ResultItem> paramList2, List<ResultItem> paramList3, RestrictsProtos.Restricts paramRestricts, QueryResponseFactory.QueryType paramQueryType, String paramString1, int paramInt, String paramString2, GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata, long paramLong)
  {
    this.responseReceivedTimestamp = paramLong;
    this.results = Collections.unmodifiableList(new ArrayList(paramList1));
    this.eyeCandyResults = Collections.unmodifiableList(new ArrayList(paramList2));
    this.puggleResults = Collections.unmodifiableList(new ArrayList(paramList3));
    this.suggestedRestricts = paramRestricts;
    this.queryType = paramQueryType;
    if (paramString1 != null);
    for (this.queryImageUrl = paramString1; ; this.queryImageUrl = "")
    {
      this.rotation = paramInt;
      this.momentId = paramString2;
      this.userContribution = paramUserContributionMetadata;
      this.allResults = new ArrayList();
      if (getUserContribution() != null)
        this.allResults.add(new ContributedResultItem(paramUserContributionMetadata, paramQueryType, paramString2, paramString1));
      this.allResults.addAll(paramList1);
      return;
    }
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    QueryResponse localQueryResponse;
    do
    {
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (!(paramObject instanceof QueryResponse))
          return false;
        localQueryResponse = (QueryResponse)paramObject;
        if (this.allResults == null)
        {
          if (localQueryResponse.allResults != null)
            return false;
        }
        else if (!this.allResults.equals(localQueryResponse.allResults))
          return false;
        if (this.eyeCandyResults == null)
        {
          if (localQueryResponse.eyeCandyResults != null)
            return false;
        }
        else if (!this.eyeCandyResults.equals(localQueryResponse.eyeCandyResults))
          return false;
        if (this.puggleResults == null)
        {
          if (localQueryResponse.puggleResults != null)
            return false;
        }
        else if (!this.puggleResults.equals(localQueryResponse.puggleResults))
          return false;
        if (this.momentId == null)
        {
          if (localQueryResponse.momentId != null)
            return false;
        }
        else if (!this.momentId.equals(localQueryResponse.momentId))
          return false;
        if (this.queryImageUrl == null)
        {
          if (localQueryResponse.queryImageUrl != null)
            return false;
        }
        else if (!this.queryImageUrl.equals(localQueryResponse.queryImageUrl))
          return false;
        if (this.queryType != localQueryResponse.queryType)
          return false;
        if (this.responseReceivedTimestamp != localQueryResponse.responseReceivedTimestamp)
          return false;
        if (this.results == null)
        {
          if (localQueryResponse.results != null)
            return false;
        }
        else if (!this.results.equals(localQueryResponse.results))
          return false;
        if (this.rotation != localQueryResponse.rotation)
          return false;
        if (this.userContribution != null)
          break;
      }
      while (localQueryResponse.userContribution == null);
      return false;
    }
    while (this.userContribution.equals(localQueryResponse.userContribution));
    return false;
  }

  public List<ResultModel> getAllResults()
  {
    return this.allResults;
  }

  public List<ResultItem> getEyeCandyResults()
  {
    return this.eyeCandyResults;
  }

  public String getMomentId()
  {
    return this.momentId;
  }

  public List<ResultItem> getPuggleResults()
  {
    return this.puggleResults;
  }

  public String getQueryImageUrl()
  {
    return this.queryImageUrl;
  }

  public QueryResponseFactory.QueryType getQueryType()
  {
    return this.queryType;
  }

  public long getResponseReceivedTimestamp()
  {
    return this.responseReceivedTimestamp;
  }

  public List<ResultItem> getResults()
  {
    return this.results;
  }

  public int getRotation()
  {
    return this.rotation;
  }

  public RestrictsProtos.Restricts getSuggestedRestricts()
  {
    return this.suggestedRestricts;
  }

  public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata getUserContribution()
  {
    if ((this.userContribution != null) && (!TextUtils.isEmpty(this.userContribution.getTitle())))
      return this.userContribution;
    return null;
  }

  public boolean hasEyeCandyResults()
  {
    return !this.eyeCandyResults.isEmpty();
  }

  public boolean hasPuggleResults()
  {
    return !this.puggleResults.isEmpty();
  }

  public boolean hasResults()
  {
    return !this.results.isEmpty();
  }

  public int hashCode()
  {
    int i;
    int k;
    label26: int n;
    label44: int i2;
    label64: int i4;
    label84: int i6;
    label104: int i8;
    label141: int i9;
    int i10;
    if (this.allResults == null)
    {
      i = 0;
      int j = 31 * (i + 31);
      if (this.eyeCandyResults != null)
        break label190;
      k = 0;
      int m = 31 * (j + k);
      if (this.puggleResults != null)
        break label201;
      n = 0;
      int i1 = 31 * (m + n);
      if (this.momentId != null)
        break label213;
      i2 = 0;
      int i3 = 31 * (i1 + i2);
      if (this.queryImageUrl != null)
        break label225;
      i4 = 0;
      int i5 = 31 * (i3 + i4);
      if (this.queryType != null)
        break label237;
      i6 = 0;
      int i7 = 31 * (31 * (i5 + i6) + (int)(this.responseReceivedTimestamp ^ this.responseReceivedTimestamp >>> 32));
      if (this.results != null)
        break label249;
      i8 = 0;
      i9 = 31 * (31 * (i7 + i8) + this.rotation);
      GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata localUserContributionMetadata = this.userContribution;
      i10 = 0;
      if (localUserContributionMetadata != null)
        break label261;
    }
    while (true)
    {
      return i9 + i10;
      i = this.allResults.hashCode();
      break;
      label190: k = this.eyeCandyResults.hashCode();
      break label26;
      label201: n = this.puggleResults.hashCode();
      break label44;
      label213: i2 = this.momentId.hashCode();
      break label64;
      label225: i4 = this.queryImageUrl.hashCode();
      break label84;
      label237: i6 = this.queryType.hashCode();
      break label104;
      label249: i8 = this.results.hashCode();
      break label141;
      label261: i10 = this.userContribution.hashCode();
    }
  }

  public String toString()
  {
    return "QueryResponse [results=" + this.results + ", eyeCandyResults=" + this.eyeCandyResults + ", puggleResults=" + this.puggleResults + ", queryType=" + this.queryType + ", momentId=" + this.momentId + ", queryImageUrl=" + this.queryImageUrl + ", rotation=" + this.rotation + "]";
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryResponse
 * JD-Core Version:    0.6.2
 */