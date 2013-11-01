package com.google.android.apps.unveil.env;

import java.lang.ref.WeakReference;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class AbstractProvider
{
  private final List<WeakReference<ItemsChangedListener>> listenerReferences = new LinkedList();

  public final void addListener(ItemsChangedListener paramItemsChangedListener)
  {
    try
    {
      this.listenerReferences.add(new WeakReference(paramItemsChangedListener));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public final void notifyListenersOfChange()
  {
    while (true)
    {
      ItemsChangedListener localItemsChangedListener;
      try
      {
        Iterator localIterator = this.listenerReferences.iterator();
        if (!localIterator.hasNext())
          break;
        localItemsChangedListener = (ItemsChangedListener)((WeakReference)localIterator.next()).get();
        if (localItemsChangedListener == null)
        {
          localIterator.remove();
          continue;
        }
      }
      finally
      {
      }
      localItemsChangedListener.onItemsChanged(this);
    }
  }

  public static abstract interface ItemsChangedListener
  {
    public abstract void onItemsChanged(AbstractProvider paramAbstractProvider);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.AbstractProvider
 * JD-Core Version:    0.6.2
 */