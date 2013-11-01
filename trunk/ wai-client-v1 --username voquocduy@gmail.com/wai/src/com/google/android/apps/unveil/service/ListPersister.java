package com.google.android.apps.unveil.service;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ListPersister<Entry>
{
  public static Serializer<Long> LONG_SERIALIZER = new Serializer()
  {
    private static final int RADIX = 10;

    public Long deserialize(String paramAnonymousString)
    {
      return Long.valueOf(Long.parseLong(paramAnonymousString, 10));
    }

    public String serialize(Long paramAnonymousLong)
    {
      return Long.toString(paramAnonymousLong.longValue(), 10);
    }
  };
  public static Serializer<String> STRING_SERIALIZER;
  private static final UnveilLogger logger = new UnveilLogger();
  private final Serializer<Entry> serializer;
  private final String storageKey;
  private final SharedPreferences storageMedium;

  static
  {
    STRING_SERIALIZER = new Serializer()
    {
      public String deserialize(String paramAnonymousString)
      {
        return paramAnonymousString;
      }

      public String serialize(String paramAnonymousString)
      {
        return paramAnonymousString;
      }
    };
  }

  public ListPersister(SharedPreferences paramSharedPreferences, String paramString, Serializer<Entry> paramSerializer)
  {
    this.storageMedium = paramSharedPreferences;
    this.storageKey = paramString;
    this.serializer = paramSerializer;
  }

  private List<Entry> deserialize(String paramString)
  {
    Object localObject;
    if (TextUtils.isEmpty(paramString))
      localObject = Collections.emptyList();
    while (true)
    {
      return localObject;
      String[] arrayOfString = paramString.split(",");
      localObject = new ArrayList(arrayOfString.length);
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str = arrayOfString[j];
        ((List)localObject).add(this.serializer.deserialize(str));
      }
    }
  }

  private String serialize(List<Entry> paramList)
  {
    return TextUtils.join(",", toStrings(paramList));
  }

  private String[] toStrings(List<Entry> paramList)
  {
    String[] arrayOfString = new String[paramList.size()];
    for (int i = 0; i < paramList.size(); i++)
      arrayOfString[i] = this.serializer.serialize(paramList.get(i));
    return arrayOfString;
  }

  public List<Entry> load()
  {
    return deserialize(this.storageMedium.getString(this.storageKey, ""));
  }

  public void persist(List<Entry> paramList)
  {
    this.storageMedium.edit().putString(this.storageKey, serialize(paramList)).commit();
  }

  public void persistNew(Entry paramEntry)
  {
    List localList = load();
    ArrayList localArrayList = new ArrayList(1 + localList.size());
    localArrayList.addAll(localList);
    localArrayList.add(paramEntry);
    persist(localArrayList);
  }

  public static abstract interface Serializer<Entry>
  {
    public abstract Entry deserialize(String paramString);

    public abstract String serialize(Entry paramEntry);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.ListPersister
 * JD-Core Version:    0.6.2
 */