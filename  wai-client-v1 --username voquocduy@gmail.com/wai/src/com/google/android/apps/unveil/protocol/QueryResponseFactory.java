package com.google.android.apps.unveil.protocol;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.android.apps.unveil.results.PuggleResultItem;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesResponse;
import com.google.goggles.GogglesProtos.GogglesRequest.Source;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.GogglesProtos.Image;
import com.google.goggles.GogglesProtos.ImageRotation;
import com.google.goggles.GogglesProtos.ImageRotation.CWOffsetFromRightSideUp;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse;
import com.google.goggles.ResultProtos.Result;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class QueryResponseFactory
{
  private static final UnveilLogger logger = new UnveilLogger();

  static List<ResultItem> adaptToResultItems(List<ResultProtos.Result> paramList, QueryType paramQueryType, GogglesProtos.GogglesRequest.Source paramSource)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    int i = 0;
    if (i < paramList.size())
    {
      ResultProtos.Result localResult = (ResultProtos.Result)paramList.get(i);
      if (paramSource == GogglesProtos.GogglesRequest.Source.PUGGLE)
        localArrayList.add(new PuggleResultItem(localResult, paramQueryType, i));
      while (true)
      {
        i++;
        break;
        localArrayList.add(new ResultItem(localResult, paramQueryType, i));
      }
    }
    return localArrayList;
  }

  static int extractImageRotation(GogglesProtos.Image paramImage)
  {
    if (!paramImage.hasExtension(GogglesProtos.ImageRotation.imageRotation))
    {
      logger.w("Cannot find ImageRotation extension in Image, assuming 0 degree rotation.", new Object[0]);
      return 0;
    }
    GogglesProtos.ImageRotation.CWOffsetFromRightSideUp localCWOffsetFromRightSideUp = ((GogglesProtos.ImageRotation)paramImage.getExtension(GogglesProtos.ImageRotation.imageRotation)).getImageOffsetFromRightSideUp();
    switch (1.$SwitchMap$com$google$goggles$GogglesProtos$ImageRotation$CWOffsetFromRightSideUp[localCWOffsetFromRightSideUp.ordinal()])
    {
    default:
      return 0;
    case 1:
      return 90;
    case 2:
      return 180;
    case 3:
    }
    return 270;
  }

  private static List<ResultProtos.Result> getAllEyecandyResults(GogglesProtos.GogglesResponse paramGogglesResponse)
  {
    return ((ExtendedGogglesProtos.ExtendedGogglesResponse)paramGogglesResponse.getExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse)).getEyeCandyResultsList();
  }

  private static List<ResultProtos.Result> getAllPuggleResults(GogglesProtos.GogglesResponse paramGogglesResponse)
  {
    return ((ExtendedGogglesProtos.ExtendedGogglesResponse)paramGogglesResponse.getExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse)).getPuggleResultsList();
  }

  public static QueryResponse interpretPullResponse(GogglesProtos.GogglesResponse paramGogglesResponse, long paramLong)
  {
    return new QueryResponse(adaptToResultItems(paramGogglesResponse.getResultsList(), QueryType.IMAGE, GogglesProtos.GogglesRequest.Source.LIVE), Collections.emptyList(), Collections.emptyList(), paramGogglesResponse.getSuggestedRestricts(), QueryType.IMAGE, "", 0, "", null, paramLong);
  }

  public static QueryResponse interpretReplayResponse(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse, QueryType paramQueryType, GogglesProtos.GogglesRequest.Source paramSource, String paramString, Viewport paramViewport, long paramLong)
  {
    GogglesProtos.Image localImage = paramGogglesReplayResponse.getQueryImage();
    int i = paramViewport.deviceRotationToCameraRotation(extractImageRotation(localImage));
    GogglesProtos.GogglesResponse localGogglesResponse = paramGogglesReplayResponse.getGogglesResponse();
    return new QueryResponse(adaptToResultItems(localGogglesResponse.getResultsList(), paramQueryType, paramSource), adaptToResultItems(getAllEyecandyResults(localGogglesResponse), paramQueryType, paramSource), adaptToResultItems(getAllPuggleResults(localGogglesResponse), paramQueryType, GogglesProtos.GogglesRequest.Source.PUGGLE), localGogglesResponse.getSuggestedRestricts(), paramQueryType, localImage.getImageUrl(), i, paramString, paramGogglesReplayResponse.getUserContributionMetadata(), paramLong);
  }

  public static SingleShotQueryResponse interpretResponse(GogglesProtos.GogglesResponse paramGogglesResponse, QueryType paramQueryType, GogglesProtos.GogglesRequest.Source paramSource, String paramString1, long paramLong, SingleShotQueryResponse.RequestMetrics paramRequestMetrics, String paramString2)
  {
    return new SingleShotQueryResponse(adaptToResultItems(paramGogglesResponse.getResultsList(), paramQueryType, paramSource), adaptToResultItems(getAllEyecandyResults(paramGogglesResponse), paramQueryType, paramSource), adaptToResultItems(getAllPuggleResults(paramGogglesResponse), paramQueryType, GogglesProtos.GogglesRequest.Source.PUGGLE), paramGogglesResponse.getSuggestedRestricts(), paramQueryType, "", 0, paramString1, null, paramLong, paramRequestMetrics, paramString2);
  }

  public static enum QueryType
  {
    private static final QueryType[] queryTypes = arrayOfQueryType2;
    public final int id;

    static
    {
      FELIX_TEXT_IMAGE = new QueryType("FELIX_TEXT_IMAGE", 4, 4);
      QueryType[] arrayOfQueryType1 = new QueryType[5];
      arrayOfQueryType1[0] = IMAGE;
      arrayOfQueryType1[1] = LOCAL_BARCODE;
      arrayOfQueryType1[2] = REPLAY;
      arrayOfQueryType1[3] = SHARE;
      arrayOfQueryType1[4] = FELIX_TEXT_IMAGE;
      $VALUES = arrayOfQueryType1;
      QueryType[] arrayOfQueryType2 = new QueryType[5];
      arrayOfQueryType2[0] = IMAGE;
      arrayOfQueryType2[1] = LOCAL_BARCODE;
      arrayOfQueryType2[2] = REPLAY;
      arrayOfQueryType2[3] = SHARE;
      arrayOfQueryType2[4] = FELIX_TEXT_IMAGE;
    }

    private QueryType(int paramInt)
    {
      this.id = paramInt;
    }

    public static final QueryType safeValueOf(String paramString)
    {
      if (TextUtils.isEmpty(paramString))
        return IMAGE;
      try
      {
        QueryType localQueryType = valueOf(paramString);
        return localQueryType;
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
      }
      return IMAGE;
    }

    public static final QueryType valueOf(int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= queryTypes.length))
      {
        QueryResponseFactory.logger.e("QueryType id out of range.", new Object[0]);
        return IMAGE;
      }
      return queryTypes[paramInt];
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryResponseFactory
 * JD-Core Version:    0.6.2
 */