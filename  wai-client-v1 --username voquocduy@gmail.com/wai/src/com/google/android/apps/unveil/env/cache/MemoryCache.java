package com.google.android.apps.unveil.env.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class MemoryCache<Key, Value> extends AbstractCache<Key, Value>
{
  private final LinkedHashMap<Key, Value> memoryMap;
  private final float reclaimRatio;
  private final int sizeLimit;

  public MemoryCache(int paramInt, float paramFloat)
  {
    this.sizeLimit = paramInt;
    this.reclaimRatio = paramFloat;
    this.memoryMap = new LinkedHashMap(paramInt, 1.0F, true);
  }

  public Value get(Key paramKey)
  {
    try
    {
      Object localObject2 = this.memoryMap.get(paramKey);
      return localObject2;
    }
    finally
    {
      localObject1 = finally;
      throw localObject1;
    }
  }

  public void put(Key paramKey, Value paramValue)
  {
    try
    {
      int i = this.memoryMap.size();
      if (i > this.sizeLimit)
      {
        int j = i - this.sizeLimit + (int)(this.sizeLimit * this.reclaimRatio);
        Iterator localIterator = this.memoryMap.keySet().iterator();
        while ((localIterator.hasNext()) && (j > 0))
        {
          localIterator.next();
          localIterator.remove();
          j--;
        }
      }
      this.memoryMap.put(paramKey, paramValue);
      return;
    }
    finally
    {
    }
  }

  int size()
  {
    try
    {
      int i = this.memoryMap.size();
      return i;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.cache.MemoryCache
 * JD-Core Version:    0.6.2
 */