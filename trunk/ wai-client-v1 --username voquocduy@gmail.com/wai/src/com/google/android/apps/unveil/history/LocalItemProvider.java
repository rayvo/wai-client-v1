package com.google.android.apps.unveil.history;

import java.util.Collections;
import java.util.List;

class LocalItemProvider extends ItemProvider
{
  private final List<ItemModel> items;

  public LocalItemProvider(String paramString, List<? extends ItemModel> paramList)
  {
    super(paramString);
    this.items = Collections.unmodifiableList(paramList);
  }

  public ItemModel get(int paramInt)
  {
    return (ItemModel)this.items.get(paramInt);
  }

  public int getCount()
  {
    return this.items.size();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.LocalItemProvider
 * JD-Core Version:    0.6.2
 */