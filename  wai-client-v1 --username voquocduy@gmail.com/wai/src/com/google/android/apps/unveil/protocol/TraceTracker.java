package com.google.android.apps.unveil.protocol;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.InfoProvider;
import com.google.android.apps.unveil.env.Stopwatch;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.NetworkInfoProvider;
import com.google.goggles.NetworkInfoProtos.NetworkInfo;
import com.google.goggles.TracingProtos.PointVariable;
import com.google.goggles.TracingProtos.PointVariable.Builder;
import com.google.goggles.TracingProtos.PointVariable.Type;
import com.google.goggles.TracingProtos.ProcessorStatus;
import com.google.goggles.TracingProtos.SpanVariable;
import com.google.goggles.TracingProtos.SpanVariable.Builder;
import com.google.goggles.TracingProtos.SpanVariable.Type;
import com.google.goggles.TracingProtos.TraceAction;
import com.google.goggles.TracingProtos.TraceAction.Builder;
import com.google.goggles.TracingProtos.TraceAction.Type;
import com.google.goggles.TracingProtos.TraceRequest.Builder;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class TraceTracker
{
  static final int MAX_EVICT_NOTICES = 4;
  private static final UnveilLogger logger = new UnveilLogger("TraceTracker", "");
  private final TracingCookieFetcher cookieFetcher;
  private final Map<Integer, ActionData> currentActions;
  private final NetworkInfoProvider networkInfoProvider;
  private int traceActionNumber;

  public TraceTracker(NetworkInfoProvider paramNetworkInfoProvider, TracingCookieFetcher paramTracingCookieFetcher)
  {
    this.networkInfoProvider = paramNetworkInfoProvider;
    this.cookieFetcher = paramTracingCookieFetcher;
    this.currentActions = Collections.synchronizedMap(new HashMap());
  }

  private void addAction(int paramInt, ActionData paramActionData)
  {
    synchronized (this.currentActions)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramActionData.tracingCookie;
      localUnveilLogger.v("Adding action %d with tracing cookie %s", arrayOfObject);
      this.currentActions.put(Integer.valueOf(paramInt), paramActionData);
      return;
    }
  }

  private void addFinishedTraceActions(TracingProtos.TraceRequest.Builder paramBuilder)
  {
    synchronized (this.currentActions)
    {
      Iterator localIterator = this.currentActions.values().iterator();
      while (localIterator.hasNext())
      {
        ActionData localActionData = (ActionData)localIterator.next();
        if (localActionData.isEnded())
          localActionData.populate(paramBuilder);
      }
    }
  }

  private void clearFinishedTraceActions()
  {
    LinkedList localLinkedList = new LinkedList();
    synchronized (this.currentActions)
    {
      Iterator localIterator1 = this.currentActions.keySet().iterator();
      while (localIterator1.hasNext())
      {
        Integer localInteger2 = (Integer)localIterator1.next();
        if (((ActionData)this.currentActions.get(localInteger2)).isEnded())
          localLinkedList.add(localInteger2);
      }
    }
    Iterator localIterator2 = localLinkedList.iterator();
    while (localIterator2.hasNext())
    {
      Integer localInteger1 = (Integer)localIterator2.next();
      this.currentActions.remove(localInteger1);
    }
  }

  private ActionData getActionData(int paramInt)
  {
    synchronized (this.currentActions)
    {
      ActionData localActionData = (ActionData)this.currentActions.get(Integer.valueOf(paramInt));
      return localActionData;
    }
  }

  private void incrementCurrentActionNumber()
  {
    try
    {
      logger.v("Incrementing current action number.", new Object[0]);
      this.traceActionNumber = (1 + this.traceActionNumber);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addDurationInterval(TracingProtos.SpanVariable.Type paramType, int paramInt1, int paramInt2)
  {
    try
    {
      ActionData localActionData = getActionData(paramInt2);
      if (localActionData == null)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt2);
        arrayOfObject[1] = paramType;
        localUnveilLogger.w("TraceAction %d has already been completed, cannot add duration interval %s", arrayOfObject);
      }
      while (true)
      {
        return;
        localActionData.addDurationInterval(paramType, paramInt1);
      }
    }
    finally
    {
    }
  }

  public void addPoint(int paramInt, TracingProtos.PointVariable.Type paramType)
  {
    try
    {
      ActionData localActionData = getActionData(paramInt);
      if (localActionData == null)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramType;
        arrayOfObject[1] = Integer.valueOf(paramInt);
        localUnveilLogger.d("Tried to add point of type %s to action %d but that action is not currently open.", arrayOfObject);
      }
      while (true)
      {
        return;
        localActionData.addPoint(paramType);
      }
    }
    finally
    {
    }
  }

  public void addPoint(TracingProtos.PointVariable.Type paramType)
  {
    try
    {
      addPoint(getCurrentActionNumber(), paramType);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void addProcessorStatus(int paramInt, TracingProtos.ProcessorStatus paramProcessorStatus)
  {
    if (paramProcessorStatus == null)
      return;
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.d("Tried to add processor status to action %d but that action is not currently open.", arrayOfObject);
      return;
    }
    localActionData.addProcessorStatus(paramProcessorStatus);
  }

  public void addRawInterval(TracingProtos.SpanVariable.Type paramType, long paramLong1, long paramLong2, int paramInt)
  {
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramType;
      localUnveilLogger.w("TraceAction %d has already been completed, cannot add duration interval %s", arrayOfObject);
      return;
    }
    localActionData.addRawInterval(paramType, paramLong1, paramLong2);
  }

  public int beginInterval(TracingProtos.SpanVariable.Type paramType)
  {
    try
    {
      int i = getCurrentActionNumber();
      beginInterval(paramType, i);
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void beginInterval(TracingProtos.SpanVariable.Type paramType, int paramInt)
  {
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      logger.e("[%s]: null TraceAction in beginInterval!", new Object[] { paramType });
      return;
    }
    localActionData.startInterval(paramType);
  }

  public void beginTraceAction(int paramInt)
  {
    try
    {
      boolean bool = this.currentActions.containsKey(Integer.valueOf(paramInt));
      if (bool);
      while (true)
      {
        return;
        addAction(paramInt, new ActionData(paramInt, this.cookieFetcher.getFreshCookie(), this.networkInfoProvider.getNetworkInfo(), null));
      }
    }
    finally
    {
    }
  }

  public void clearAll()
  {
    synchronized (this.currentActions)
    {
      this.currentActions.clear();
      return;
    }
  }

  public void deleteTraceAction(int paramInt)
  {
    try
    {
      synchronized (this.currentActions)
      {
        if (!this.currentActions.containsKey(Integer.valueOf(paramInt)))
        {
          logger.w("key was not in actions list!", new Object[0]);
          return;
        }
        this.currentActions.remove(Integer.valueOf(paramInt));
      }
    }
    finally
    {
    }
  }

  public void endInterval(TracingProtos.SpanVariable.Type paramType)
  {
    try
    {
      endInterval(paramType, getCurrentActionNumber());
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void endInterval(TracingProtos.SpanVariable.Type paramType, int paramInt)
  {
    try
    {
      ActionData localActionData = getActionData(paramInt);
      if (localActionData == null)
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = Integer.valueOf(paramInt);
        arrayOfObject[1] = paramType;
        localUnveilLogger.v("TraceAction %d has already been completed, cannot end interval %s", arrayOfObject);
      }
      while (true)
      {
        return;
        localActionData.endIntervalNow(paramType);
      }
    }
    finally
    {
    }
  }

  public void endIntervalDelayed(TracingProtos.SpanVariable.Type paramType, int paramInt, long paramLong)
  {
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      arrayOfObject[1] = paramType;
      localUnveilLogger.v("TraceAction %d has already been completed, cannot end interval %s", arrayOfObject);
      return;
    }
    localActionData.endIntervalAtTime(paramType, paramLong);
  }

  int getCurrentActionNumber()
  {
    try
    {
      int i = this.traceActionNumber;
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String getLatestActionString()
  {
    try
    {
      if (!this.currentActions.containsKey(Integer.valueOf(this.traceActionNumber)));
      String str;
      for (Object localObject2 = ""; ; localObject2 = str)
      {
        return localObject2;
        str = ((ActionData)this.currentActions.get(Integer.valueOf(this.traceActionNumber))).toString();
      }
    }
    finally
    {
    }
  }

  protected String getTracingCookieForAction(int paramInt)
  {
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      logger.w("Null action in getTracingCookieForAction.", new Object[0]);
      return null;
    }
    return localActionData.tracingCookie;
  }

  public String getTracingCookieForCurrentAction()
  {
    return getTracingCookieForAction(getCurrentActionNumber());
  }

  public boolean hasPendingActions()
  {
    synchronized (this.currentActions)
    {
      Iterator localIterator = this.currentActions.values().iterator();
      while (localIterator.hasNext())
        if (((ActionData)localIterator.next()).isEnded())
          return true;
      return false;
    }
  }

  public void populateRequest(TracingProtos.TraceRequest.Builder paramBuilder)
  {
    synchronized (this.currentActions)
    {
      tryToEndAllExcept(Collections.singletonList(Integer.valueOf(getCurrentActionNumber())));
      addFinishedTraceActions(paramBuilder);
      clearFinishedTraceActions();
      return;
    }
  }

  public void populateRequestContinuous(TracingProtos.TraceRequest.Builder paramBuilder)
  {
    synchronized (this.currentActions)
    {
      addFinishedTraceActions(paramBuilder);
      clearFinishedTraceActions();
      return;
    }
  }

  public void setTrackingId(String paramString, int paramInt)
  {
    ActionData localActionData = getActionData(paramInt);
    if (localActionData == null)
    {
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = Integer.valueOf(paramInt);
      localUnveilLogger.w("Null action in setTracingId for number %d.", arrayOfObject);
      return;
    }
    localActionData.setTrackingId(paramString);
  }

  void startNewTraceAction()
  {
    try
    {
      if (getActionData(getCurrentActionNumber()) != null)
        tryToEndTraceAction(getCurrentActionNumber());
      incrementCurrentActionNumber();
      beginTraceAction(getCurrentActionNumber());
      return;
    }
    finally
    {
    }
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TraceTracker [");
    localStringBuilder.append("traceActionNumber=").append(this.traceActionNumber).append(",");
    localStringBuilder.append("currentActions(").append(this.currentActions.keySet().size()).append("),");
    return localStringBuilder.toString();
  }

  public void tryToEndAllExcept(List<Integer> paramList)
  {
    while (true)
    {
      ActionData localActionData;
      try
      {
        Iterator localIterator = this.currentActions.values().iterator();
        if (!localIterator.hasNext())
          break;
        localActionData = (ActionData)localIterator.next();
        if (paramList.contains(Integer.valueOf(localActionData.actionNumber)))
          continue;
        if ((!localActionData.isEnded()) && (localActionData.shouldEnd()))
        {
          localActionData.end();
          continue;
        }
      }
      finally
      {
      }
      ActionData.access$708(localActionData);
    }
  }

  public void tryToEndTraceAction(int paramInt)
  {
    try
    {
      ActionData localActionData = getActionData(paramInt);
      if (localActionData != null)
      {
        if ((localActionData.isEnded()) || (!localActionData.shouldEnd()))
          break label33;
        localActionData.end();
      }
      while (true)
      {
        return;
        label33: ActionData.access$704(localActionData);
      }
    }
    finally
    {
    }
  }

  static class ActionData
  {
    private final int actionNumber;
    private final List<TracingProtos.SpanVariable> closedIntervals;
    private int evictNotices;
    private final NetworkInfoProtos.NetworkInfo networkInfo;
    private final Map<TracingProtos.SpanVariable.Type, Queue<TracingProtos.SpanVariable.Builder>> openIntervals;
    private final List<TracingProtos.PointVariable.Builder> points;
    private final LinkedList<TracingProtos.ProcessorStatus> processorStatuses;
    private final long startTimeMs;
    private final Stopwatch stopwatch;
    private final String tracingCookie;
    private String trackingId;

    private ActionData(int paramInt, String paramString, NetworkInfoProtos.NetworkInfo paramNetworkInfo)
    {
      this.networkInfo = paramNetworkInfo;
      if (paramString == null)
        TraceTracker.logger.v("No tracing cookie for this trace action.", new Object[0]);
      this.tracingCookie = paramString;
      this.openIntervals = new HashMap();
      this.points = new LinkedList();
      this.closedIntervals = new LinkedList();
      this.processorStatuses = new LinkedList();
      this.actionNumber = paramInt;
      this.stopwatch = new Stopwatch("[" + paramInt + ": \"" + paramString + "\"]");
      this.startTimeMs = System.currentTimeMillis();
      UnveilLogger localUnveilLogger = TraceTracker.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(this.startTimeMs);
      arrayOfObject[1] = this.stopwatch.toString();
      localUnveilLogger.v("Start action: %d + %s", arrayOfObject);
      this.stopwatch.start();
    }

    private void addPoint(TracingProtos.PointVariable.Type paramType)
    {
      TracingProtos.PointVariable.Builder localBuilder = TracingProtos.PointVariable.newBuilder();
      localBuilder.setTimestampMs((int)this.stopwatch.getElapsedMilliseconds());
      localBuilder.setType(paramType);
      UnveilLogger localUnveilLogger = TraceTracker.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramType;
      arrayOfObject[1] = Long.valueOf(this.stopwatch.getElapsedMilliseconds());
      localUnveilLogger.v("Added point (%s) at %d", arrayOfObject);
      this.points.add(localBuilder);
    }

    private void closeInterval(TracingProtos.SpanVariable.Builder paramBuilder, int paramInt)
    {
      paramBuilder.setDurationMs(paramInt);
      UnveilLogger localUnveilLogger = TraceTracker.logger;
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = paramBuilder.getType();
      arrayOfObject[1] = Long.valueOf(this.startTimeMs);
      arrayOfObject[2] = this.stopwatch.toString();
      arrayOfObject[3] = Integer.valueOf(paramBuilder.getDurationMs());
      localUnveilLogger.v("End interval(%s): %d + %s, took %d ms", arrayOfObject);
      this.closedIntervals.add(paramBuilder.build());
    }

    private void end()
    {
      try
      {
        if (!this.stopwatch.isRunning())
          TraceTracker.logger.e("Ending an action more than once.", new Object[0]);
        TraceTracker.logger.v("End action: %s", new Object[] { this });
        this.stopwatch.stop();
        return;
      }
      finally
      {
      }
    }

    private void endIntervalNow(TracingProtos.SpanVariable.Type paramType)
    {
      if (!this.stopwatch.isRunning())
        TraceTracker.logger.e("Ending an interval on an ended action.", new Object[0]);
      TracingProtos.SpanVariable.Builder localBuilder = popInterval(paramType);
      if (localBuilder == null)
        return;
      closeInterval(localBuilder, (int)this.stopwatch.getElapsedMilliseconds() - localBuilder.getStartMs());
    }

    private boolean hasOpenIntervals()
    {
      try
      {
        boolean bool1 = this.openIntervals.isEmpty();
        if (!bool1)
        {
          bool2 = true;
          return bool2;
        }
        boolean bool2 = false;
      }
      finally
      {
      }
    }

    private boolean isEnded()
    {
      try
      {
        boolean bool1 = this.stopwatch.isRunning();
        if (!bool1)
        {
          bool2 = true;
          return bool2;
        }
        boolean bool2 = false;
      }
      finally
      {
      }
    }

    private TracingProtos.SpanVariable.Builder popInterval(TracingProtos.SpanVariable.Type paramType)
    {
      TracingProtos.SpanVariable.Builder localBuilder;
      if (!this.openIntervals.containsKey(paramType))
      {
        TraceTracker.logger.e("Tried to end a %s interval that has not been started.", new Object[] { paramType });
        localBuilder = null;
      }
      Queue localQueue;
      do
      {
        return localBuilder;
        localQueue = (Queue)this.openIntervals.get(paramType);
        localBuilder = (TracingProtos.SpanVariable.Builder)localQueue.poll();
        if (localBuilder == null)
        {
          TraceTracker.logger.e("Tried to end a %s interval that has not been started.", new Object[] { paramType });
          return null;
        }
      }
      while (!localQueue.isEmpty());
      this.openIntervals.remove(paramType);
      return localBuilder;
    }

    private void setTrackingId(String paramString)
    {
      try
      {
        this.trackingId = paramString;
        return;
      }
      finally
      {
        localObject = finally;
        throw localObject;
      }
    }

    private boolean shouldEnd()
    {
      boolean bool1 = true;
      try
      {
        boolean bool2 = hasOpenIntervals();
        if (!bool2);
        while (true)
        {
          return bool1;
          UnveilLogger localUnveilLogger = TraceTracker.logger;
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(this.evictNotices);
          arrayOfObject[1] = Integer.valueOf(this.actionNumber);
          localUnveilLogger.d("Eviction notice number %d for ActionData %d", arrayOfObject);
          int i = this.evictNotices;
          if (i < 4)
            bool1 = false;
        }
      }
      finally
      {
      }
    }

    private void startInterval(TracingProtos.SpanVariable.Type paramType)
    {
      if (!this.stopwatch.isRunning())
        TraceTracker.logger.e("Starting an interval on an ended action.", new Object[0]);
      TracingProtos.SpanVariable.Builder localBuilder = TracingProtos.SpanVariable.newBuilder();
      localBuilder.setType(paramType);
      localBuilder.setStartMs((int)this.stopwatch.getElapsedMilliseconds());
      if (!this.openIntervals.containsKey(paramType))
        this.openIntervals.put(paramType, new LinkedList());
      ((Queue)this.openIntervals.get(paramType)).add(localBuilder);
      UnveilLogger localUnveilLogger = TraceTracker.logger;
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = paramType;
      arrayOfObject[1] = Long.valueOf(this.startTimeMs);
      arrayOfObject[2] = this.stopwatch.toString();
      localUnveilLogger.v("Start interval(%s): %d + %s", arrayOfObject);
    }

    public void addDurationInterval(TracingProtos.SpanVariable.Type paramType, int paramInt)
    {
      TracingProtos.SpanVariable.Builder localBuilder = TracingProtos.SpanVariable.newBuilder();
      localBuilder.setType(paramType).setDurationMs(paramInt).setStartMs(0);
      this.closedIntervals.add(localBuilder.build());
    }

    public void addProcessorStatus(TracingProtos.ProcessorStatus paramProcessorStatus)
    {
      this.processorStatuses.add(paramProcessorStatus);
    }

    public void addRawInterval(TracingProtos.SpanVariable.Type paramType, long paramLong1, long paramLong2)
    {
      TracingProtos.SpanVariable.Builder localBuilder = TracingProtos.SpanVariable.newBuilder();
      localBuilder.setType(paramType).setStartMs((int)(paramLong1 - this.startTimeMs)).setDurationMs((int)(paramLong2 - paramLong1));
      this.closedIntervals.add(localBuilder.build());
    }

    public void endIntervalAtTime(TracingProtos.SpanVariable.Type paramType, long paramLong)
    {
      if (!this.stopwatch.isRunning())
        TraceTracker.logger.e("Ending an interval on an ended action.", new Object[0]);
      TracingProtos.SpanVariable.Builder localBuilder = popInterval(paramType);
      if (localBuilder == null)
        return;
      closeInterval(localBuilder, (int)(paramLong - this.startTimeMs));
    }

    void fillTraceAction(TracingProtos.TraceAction.Type paramType, TracingProtos.TraceAction.Builder paramBuilder)
    {
      try
      {
        paramBuilder.setType(paramType);
        Iterator localIterator1 = this.closedIntervals.iterator();
        while (localIterator1.hasNext())
          paramBuilder.addSpanVariables((TracingProtos.SpanVariable)localIterator1.next());
      }
      finally
      {
      }
      Iterator localIterator2 = this.points.iterator();
      while (localIterator2.hasNext())
        paramBuilder.addPointVariables((TracingProtos.PointVariable.Builder)localIterator2.next());
      paramBuilder.setActionStartMs(this.startTimeMs);
      paramBuilder.setDurationMs((int)this.stopwatch.getElapsedMilliseconds());
      if (this.tracingCookie != null)
        paramBuilder.setTracingCookie(this.tracingCookie);
      if (!TextUtils.isEmpty(this.trackingId))
        paramBuilder.setTrackingId(this.trackingId);
    }

    public NetworkInfoProtos.NetworkInfo getNetworkInfo()
    {
      return this.networkInfo;
    }

    void populate(TracingProtos.TraceRequest.Builder paramBuilder)
    {
      TracingProtos.TraceAction.Builder localBuilder = TracingProtos.TraceAction.newBuilder();
      fillTraceAction(TracingProtos.TraceAction.Type.CONTINUOUS_GOGGLES, localBuilder);
      paramBuilder.setNetworkInfo(getNetworkInfo());
      paramBuilder.setDeviceInfo(InfoProvider.getDeviceInfo());
      paramBuilder.addTraceAction(localBuilder);
      if (!this.processorStatuses.isEmpty())
        paramBuilder.addAllProcessorStatus(this.processorStatuses);
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ActionData [");
      localStringBuilder.append("actionNumber=");
      localStringBuilder.append(this.actionNumber);
      localStringBuilder.append(", ");
      if (this.tracingCookie != null)
      {
        localStringBuilder.append("tracingCookie=");
        localStringBuilder.append(this.tracingCookie);
        localStringBuilder.append(", ");
      }
      if (this.openIntervals != null)
      {
        localStringBuilder.append(this.openIntervals.size());
        localStringBuilder.append(" openIntervals: ");
        localStringBuilder.append(this.openIntervals.keySet());
        localStringBuilder.append(", ");
      }
      if (this.closedIntervals != null)
      {
        localStringBuilder.append(this.closedIntervals.size());
        localStringBuilder.append(" closedIntervals: ");
        Iterator localIterator = this.closedIntervals.iterator();
        while (localIterator.hasNext())
        {
          TracingProtos.SpanVariable localSpanVariable = (TracingProtos.SpanVariable)localIterator.next();
          localStringBuilder.append(localSpanVariable.getType().name() + ": " + localSpanVariable.getDurationMs() + "ms; ");
        }
        localStringBuilder.append(", ");
      }
      if (this.points != null)
      {
        localStringBuilder.append(this.points.size());
        localStringBuilder.append(" points");
        localStringBuilder.append(", ");
      }
      localStringBuilder.append("startTimeMs=");
      localStringBuilder.append(this.startTimeMs);
      localStringBuilder.append(", trackingId=");
      localStringBuilder.append(this.trackingId);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.TraceTracker
 * JD-Core Version:    0.6.2
 */