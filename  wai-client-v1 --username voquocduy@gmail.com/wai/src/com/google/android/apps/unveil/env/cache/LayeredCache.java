package com.google.android.apps.unveil.env.cache;

public class LayeredCache<Key, Value> extends AbstractCache<Key, Value>
{
  private final AbstractCache<Key, Value> bottomLayer;
  private final AbstractCache<Key, Value> topLayer;

  private LayeredCache(AbstractCache<Key, Value> paramAbstractCache1, AbstractCache<Key, Value> paramAbstractCache2)
  {
    this.topLayer = paramAbstractCache1;
    this.bottomLayer = paramAbstractCache2;
  }

  public static <Key, Value> AbstractCache<Key, Value> layer(AbstractCache<Key, Value> paramAbstractCache1, AbstractCache<Key, Value> paramAbstractCache2)
  {
    return new LayeredCache(paramAbstractCache1, paramAbstractCache2);
  }

  public Value get(Key paramKey)
  {
    Object localObject1 = this.topLayer.get(paramKey);
    if (localObject1 == null)
    {
      Object localObject2 = this.bottomLayer.get(paramKey);
      if (localObject2 != null)
        this.topLayer.put(paramKey, localObject2);
      return localObject2;
    }
    return localObject1;
  }

  public void put(Key paramKey, Value paramValue)
  {
    this.topLayer.put(paramKey, paramValue);
    this.bottomLayer.put(paramKey, paramValue);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.cache.LayeredCache
 * JD-Core Version:    0.6.2
 */