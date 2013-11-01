package com.google.android.apps.unveil.protocol;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.ExtendedGogglesProtos.ExtendedGogglesResponse;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse;

public class IdExtractor
{
  private static final UnveilLogger logger = new UnveilLogger();

  public static String extractMomentId(GogglesProtos.GogglesResponse paramGogglesResponse)
  {
    if (paramGogglesResponse.hasExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse))
      return ((ExtendedGogglesProtos.ExtendedGogglesResponse)paramGogglesResponse.getExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse)).getMomentId();
    logger.e("Failed to extract a moment ID because the extended goggles response is missing.", new Object[0]);
    return null;
  }

  public static String extractMomentId(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse)
  {
    return extractMomentId(paramGogglesReplayResponse.getGogglesResponse());
  }

  public static String extractTrackingId(GogglesProtos.GogglesResponse paramGogglesResponse)
  {
    if (paramGogglesResponse.hasExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse))
      return ((ExtendedGogglesProtos.ExtendedGogglesResponse)paramGogglesResponse.getExtension(ExtendedGogglesProtos.ExtendedGogglesResponse.extendedGogglesResponse)).getTrackingId();
    logger.e("Failed to extract a tracking ID because the extended goggles response is missing.", new Object[0]);
    return null;
  }

  public static String extractTrackingId(GogglesReplayProtos.GogglesReplayResponse paramGogglesReplayResponse)
  {
    return extractTrackingId(paramGogglesReplayResponse.getGogglesResponse());
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.IdExtractor
 * JD-Core Version:    0.6.2
 */