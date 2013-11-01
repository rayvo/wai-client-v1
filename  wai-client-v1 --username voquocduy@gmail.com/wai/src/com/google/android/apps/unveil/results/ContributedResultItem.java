package com.google.android.apps.unveil.results;

import android.graphics.Rect;
import android.text.TextUtils;
import android.view.accessibility.AccessibilityEvent;
import com.google.android.apps.unveil.protocol.QueryResponseFactory.QueryType;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata;
import java.util.List;

public class ContributedResultItem extends ResultModel
{
  private final String momentId;
  private final String queryImageUrl;
  private final QueryResponseFactory.QueryType queryType;
  private final GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata userContribution;

  public ContributedResultItem(GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata paramUserContributionMetadata, QueryResponseFactory.QueryType paramQueryType, String paramString1, String paramString2)
  {
    this.userContribution = paramUserContributionMetadata;
    this.queryType = paramQueryType;
    this.momentId = paramString1;
    this.queryImageUrl = paramString2;
  }

  public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent paramAccessibilityEvent)
  {
    paramAccessibilityEvent.getText().add(this.userContribution.getTitle());
    return true;
  }

  public Rect getBoundingBox()
  {
    BoundingBoxProtos.BoundingBox localBoundingBox = this.userContribution.getRegion();
    return new Rect(localBoundingBox.getX(), localBoundingBox.getY(), localBoundingBox.getX() + localBoundingBox.getWidth(), localBoundingBox.getY() + localBoundingBox.getHeight());
  }

  public String getImageUrl()
  {
    return this.queryImageUrl;
  }

  public String getMomentId()
  {
    return this.momentId;
  }

  public QueryResponseFactory.QueryType getQueryType()
  {
    return this.queryType;
  }

  public String getThumbnailUrl()
  {
    return this.queryImageUrl;
  }

  public String getTitle()
  {
    return this.userContribution.getTitle();
  }

  public GogglesReplayProtos.GogglesReplayResponse.UserContributionMetadata getUserContribution()
  {
    return this.userContribution;
  }

  public String getWebSearchUrl()
  {
    return this.userContribution.getWebsearchUrl();
  }

  public boolean hasImageUrl()
  {
    return !TextUtils.isEmpty(getImageUrl());
  }

  public boolean isUserGenerated()
  {
    return true;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.ContributedResultItem
 * JD-Core Version:    0.6.2
 */