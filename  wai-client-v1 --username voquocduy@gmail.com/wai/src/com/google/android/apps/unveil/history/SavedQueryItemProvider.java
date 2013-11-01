package com.google.android.apps.unveil.history;

public class SavedQueryItemProvider extends ItemProvider
{
  private final SavedQueryProvider.SavedQueryCursor savedQueryCursor;

  public SavedQueryItemProvider(String paramString, SavedQueryProvider.SavedQueryCursor paramSavedQueryCursor)
  {
    super(paramString);
    this.savedQueryCursor = paramSavedQueryCursor;
  }

  protected void finalize()
    throws Throwable
  {
    this.savedQueryCursor.close();
  }

  public ItemModel get(int paramInt)
  {
    this.savedQueryCursor.moveTo(paramInt);
    return this.savedQueryCursor.next();
  }

  public int getCount()
  {
    return this.savedQueryCursor.getCount();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.SavedQueryItemProvider
 * JD-Core Version:    0.6.2
 */