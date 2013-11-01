package com.google.android.apps.unveil.env;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.util.Collection;
import java.util.Vector;

public class PictureTracker
{
  private static PictureTracker instance;
  private static final UnveilLogger logger = new UnveilLogger();
  volatile boolean exitWhenFinished = false;
  Thread reaper;
  ReferenceQueue<Picture> referenceQueue = new ReferenceQueue();
  final Collection<PhantomReference<Picture>> trackers = new Vector();

  public static PictureTracker getInstance()
  {
    if (instance == null)
      instance = new PictureTracker();
    return instance;
  }

  private String getPictureInfo(Picture paramPicture)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramPicture.getClass().getSimpleName());
    localStringBuilder.append(", ");
    if (paramPicture.isRecycled())
    {
      localStringBuilder.append(" already recycled");
      return localStringBuilder.toString();
    }
    Size localSize = paramPicture.getSize();
    localStringBuilder.append(localSize.width);
    localStringBuilder.append("x");
    localStringBuilder.append(localSize.height);
    localStringBuilder.append(", ");
    localStringBuilder.append(paramPicture.getByteSize());
    localStringBuilder.append(" bytes");
    return localStringBuilder.toString();
  }

  public void exitWhenFinished()
  {
    try
    {
      this.exitWhenFinished = true;
      if (this.reaper != null);
      synchronized (this.reaper)
      {
        this.reaper.interrupt();
        return;
      }
    }
    finally
    {
    }
  }

  public void track(Picture paramPicture1, Picture paramPicture2)
  {
    if ((paramPicture1 == null) || (paramPicture2 == null));
    while (true)
    {
      return;
      try
      {
        if (this.exitWhenFinished)
          throw new IllegalStateException("No picture can be added once exitWhenFinished() is called");
      }
      finally
      {
      }
      if (this.reaper == null)
      {
        this.reaper = new Reaper();
        this.reaper.start();
      }
      this.trackers.add(new Tracker(paramPicture1, this.referenceQueue, paramPicture2));
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = getPictureInfo(paramPicture2);
      arrayOfObject[1] = Integer.valueOf(this.trackers.size());
      localUnveilLogger.d("Tracked: %s, total %d", arrayOfObject);
    }
  }

  private final class Reaper extends Thread
  {
    public Reaper()
    {
      super();
      setPriority(10);
      setDaemon(true);
    }

    public void run()
    {
      PictureTracker.logger.d("Reaper started", new Object[0]);
      while ((!PictureTracker.this.exitWhenFinished) || (PictureTracker.this.trackers.size() > 0))
        try
        {
          PictureTracker.Tracker localTracker = (PictureTracker.Tracker)PictureTracker.this.referenceQueue.remove();
          if (localTracker != null)
          {
            localTracker.recycle();
            localTracker.clear();
            PictureTracker.this.trackers.remove(localTracker);
          }
        }
        catch (Exception localException)
        {
        }
    }
  }

  private final class Tracker extends PhantomReference<Picture>
  {
    private final Picture picture;

    public Tracker(ReferenceQueue<? super Picture> paramPicture, Picture arg3)
    {
      super(localReferenceQueue);
      Object localObject;
      this.picture = localObject;
    }

    public void recycle()
    {
      this.picture.recycle();
      UnveilLogger localUnveilLogger = PictureTracker.logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = PictureTracker.this.getPictureInfo(this.picture);
      arrayOfObject[1] = Integer.valueOf(-1 + PictureTracker.this.trackers.size());
      localUnveilLogger.d("Recycled %s, total %d", arrayOfObject);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.PictureTracker
 * JD-Core Version:    0.6.2
 */