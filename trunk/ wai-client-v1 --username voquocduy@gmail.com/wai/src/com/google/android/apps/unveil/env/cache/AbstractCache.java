package com.google.android.apps.unveil.env.cache;

public abstract class AbstractCache<Key, Value>
{
  public abstract Value get(Key paramKey);

  public abstract void put(Key paramKey, Value paramValue);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.cache.AbstractCache
 * JD-Core Version:    0.6.2
 */