package com.google.android.apps.unveil.protocol.nonstop;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.ContinuousConnector.PushLogger;
import com.google.android.apps.unveil.nonstop.TimestampedFrame;
import com.google.android.apps.unveil.protocol.TraceTracker;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import com.google.goggles.TracingProtos.TraceRequest;
import com.google.goggles.TracingProtos.TraceRequest.Builder;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ContinuousTracer
{
  public static final int DEFAULT_MIN_TRACE_ACTIONS = 300;
  private static final int NO_FRAMES_PROCESSED = -1;
  public static final int ON_PAUSE_MIN_TRACE_ACTIONS = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  private int lastProcessedFrameNum = -1;
  private TracingProtos.TraceRequest.Builder pendingBuilder;
  private final List<PerFrameTracer> perFrameTracers = new LinkedList();
  private final ContinuousConnector.PushLogger pushLogger = new ContinuousConnector.PushLogger()
  {
    public void onReceivePushResponse(int paramAnonymousInt)
    {
      ContinuousTracer.this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_PUSH, paramAnonymousInt);
    }

    public void onSendPush(int paramAnonymousInt)
    {
      ContinuousTracer.this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_PUSH, paramAnonymousInt);
    }
  };
  private final TraceTracker traceTracker;

  public ContinuousTracer(TraceTracker paramTraceTracker)
  {
    this.traceTracker = paramTraceTracker;
  }

  public void addPerFrameTracer(PerFrameTracer paramPerFrameTracer)
  {
    this.perFrameTracers.add(paramPerFrameTracer);
  }

  public ContinuousConnector.PushLogger getPushLogger()
  {
    return this.pushLogger;
  }

  public TracingProtos.TraceRequest getTraceRequest(int paramInt)
  {
    try
    {
      if (this.pendingBuilder == null)
        this.pendingBuilder = TracingProtos.TraceRequest.newBuilder();
      this.traceTracker.populateRequestContinuous(this.pendingBuilder);
      Object localObject2;
      if (this.pendingBuilder.getTraceActionCount() > paramInt)
      {
        localObject2 = this.pendingBuilder.build();
        this.pendingBuilder = null;
      }
      while (true)
      {
        return localObject2;
        TracingProtos.TraceRequest localTraceRequest = TracingProtos.TraceRequest.getDefaultInstance();
        localObject2 = localTraceRequest;
      }
    }
    finally
    {
    }
  }

  public void onBeginEncoding(int paramInt)
  {
    this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_IMAGE_ENCODE, paramInt);
  }

  public void onClientAnnotationPush(int paramInt)
  {
    this.traceTracker.beginTraceAction(paramInt);
    this.traceTracker.addPoint(paramInt, TracingProtos.PointVariable.Type.CLIENT_ANNOTATION_SENT);
  }

  public void onEndEncoding(int paramInt)
  {
    this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_IMAGE_ENCODE, paramInt);
  }

  public void onMakingRequest(Integer paramInteger, TimestampedFrame paramTimestampedFrame)
  {
    if (paramInteger != null)
    {
      this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_REQUEST_TO_REQUEST, paramInteger.intValue());
      this.traceTracker.tryToEndTraceAction(paramInteger.intValue());
    }
    this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_REQUEST_TO_REQUEST, paramTimestampedFrame.getFrameNum());
  }

  public void onProcessFrame(int paramInt)
  {
    if (this.lastProcessedFrameNum != -1)
      this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_IMAGE_TO_IMAGE, this.lastProcessedFrameNum);
    this.traceTracker.beginTraceAction(paramInt);
    this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_IMAGE_TO_IMAGE, paramInt);
    this.lastProcessedFrameNum = paramInt;
    Iterator localIterator = this.perFrameTracers.iterator();
    while (localIterator.hasNext())
      ((PerFrameTracer)localIterator.next()).onProcessFrame(paramInt, this.traceTracker);
  }

  public void onQueryRespnse(List<Integer> paramList, int paramInt)
  {
    this.traceTracker.tryToEndTraceAction(paramInt);
    this.traceTracker.tryToEndAllExcept(paramList);
  }

  public void onResult(int paramInt, long paramLong)
  {
    this.traceTracker.endIntervalDelayed(TracingProtos.SpanVariable.Type.CONTINUOUS_PUSH_TO_PULL, paramInt, paramLong);
    this.traceTracker.addRawInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_RENDER_RESPONSE, paramLong, System.currentTimeMillis(), paramInt);
    this.traceTracker.endInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_END_TO_END, paramInt);
    this.traceTracker.tryToEndTraceAction(paramInt);
  }

  public void onSending(int paramInt)
  {
    this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_PUSH_TO_PULL, paramInt);
  }

  public void onWillRequest(int paramInt)
  {
    this.traceTracker.beginInterval(TracingProtos.SpanVariable.Type.CONTINUOUS_END_TO_END, paramInt);
  }

  public void reset()
  {
    this.traceTracker.clearAll();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.ContinuousTracer
 * JD-Core Version:    0.6.2
 */