package com.google.android.apps.unveil.history;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MergingItemProvider extends ItemProvider.AsyncItemProvider
{
  private final ArrayList<? extends ItemProvider.AsyncItemProvider> asyncProviders;
  private final ArrayList<? extends ItemProvider> providers;

  private MergingItemProvider(String paramString, List<? extends ItemProvider> paramList, List<ItemProvider.AsyncItemProvider> paramList1)
  {
    super(paramString);
    if (!paramList.containsAll(paramList1))
      throw new AssertionError();
    this.providers = new ArrayList(paramList);
    this.asyncProviders = new ArrayList(paramList1);
  }

  public static ItemProvider.AsyncItemProvider merge(String paramString, ItemProvider[] paramArrayOfItemProvider)
  {
    if (paramArrayOfItemProvider.length < 1)
      throw new IllegalArgumentException("Must merge at least one provider");
    ArrayList localArrayList = new ArrayList(paramArrayOfItemProvider.length);
    int i = paramArrayOfItemProvider.length;
    for (int j = 0; j < i; j++)
    {
      ItemProvider localItemProvider = paramArrayOfItemProvider[j];
      if ((localItemProvider instanceof ItemProvider.AsyncItemProvider))
        localArrayList.add((ItemProvider.AsyncItemProvider)localItemProvider);
    }
    MergingItemProvider localMergingItemProvider = new MergingItemProvider(paramString, Arrays.asList(paramArrayOfItemProvider), localArrayList);
    Iterator localIterator = localArrayList.iterator();
    while (localIterator.hasNext())
      ((ItemProvider.AsyncItemProvider)localIterator.next()).addCallback(new DispatchingCallback(localMergingItemProvider));
    return localMergingItemProvider;
  }

  public ItemModel get(int paramInt)
  {
    int i = 0;
    Iterator localIterator = this.providers.iterator();
    while (localIterator.hasNext())
    {
      ItemProvider localItemProvider = (ItemProvider)localIterator.next();
      int j = localItemProvider.getCount();
      if (i + j > paramInt)
        return localItemProvider.get(paramInt - i);
      i += j;
    }
    throw new AssertionError();
  }

  public int getCount()
  {
    int i = 0;
    Iterator localIterator = this.providers.iterator();
    while (localIterator.hasNext())
      i += ((ItemProvider)localIterator.next()).getCount();
    return i;
  }

  public boolean hasMore()
  {
    Iterator localIterator = this.asyncProviders.iterator();
    while (localIterator.hasNext())
      if (((ItemProvider.AsyncItemProvider)localIterator.next()).hasMore())
        return true;
    return false;
  }

  public void loadMore()
  {
    Iterator localIterator = this.asyncProviders.iterator();
    while (localIterator.hasNext())
      ((ItemProvider.AsyncItemProvider)localIterator.next()).loadMore();
  }

  public void startLoading()
  {
    Iterator localIterator = this.asyncProviders.iterator();
    while (localIterator.hasNext())
      ((ItemProvider.AsyncItemProvider)localIterator.next()).startLoading();
  }

  private static final class DispatchingCallback
    implements ItemProvider.AsyncItemProvider.Callback
  {
    private final MergingItemProvider provider;

    public DispatchingCallback(MergingItemProvider paramMergingItemProvider)
    {
      this.provider = paramMergingItemProvider;
    }

    public void onError()
    {
      this.provider.onError();
    }

    public void onQuerying()
    {
      this.provider.onQuerying();
    }

    public void onSuccess()
    {
      this.provider.onSuccess();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.MergingItemProvider
 * JD-Core Version:    0.6.2
 */