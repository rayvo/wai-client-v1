package com.google.protobuf;

import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

class SmallSortedMap<K extends Comparable<K>, V> extends AbstractMap<K, V>
{
  private List<SmallSortedMap<K, V>.Entry> entryList;
  private boolean isImmutable;
  private volatile SmallSortedMap<K, V>.EntrySet lazyEntrySet;
  private final int maxArraySize;
  private Map<K, V> overflowEntries;

  private SmallSortedMap(int paramInt)
  {
    this.maxArraySize = paramInt;
    this.entryList = Collections.emptyList();
    this.overflowEntries = Collections.emptyMap();
  }

  private int binarySearchInArray(K paramK)
  {
    int i = -1 + this.entryList.size();
    int j = 0;
    int k;
    if (i >= 0)
    {
      int n = paramK.compareTo(((Entry)this.entryList.get(i)).getKey());
      if (n > 0)
      {
        k = -(i + 2);
        return k;
      }
      j = 0;
      if (n == 0)
        return i;
    }
    while (true)
    {
      if (j > i)
        break label128;
      k = (j + i) / 2;
      int m = paramK.compareTo(((Entry)this.entryList.get(k)).getKey());
      if (m < 0)
      {
        i = k - 1;
      }
      else
      {
        if (m <= 0)
          break;
        j = k + 1;
      }
    }
    label128: return -(j + 1);
  }

  private void checkMutable()
  {
    if (this.isImmutable)
      throw new UnsupportedOperationException();
  }

  private void ensureEntryArrayMutable()
  {
    checkMutable();
    if ((this.entryList.isEmpty()) && (!(this.entryList instanceof ArrayList)))
      this.entryList = new ArrayList(this.maxArraySize);
  }

  private SortedMap<K, V> getOverflowEntriesMutable()
  {
    checkMutable();
    if ((this.overflowEntries.isEmpty()) && (!(this.overflowEntries instanceof TreeMap)))
      this.overflowEntries = new TreeMap();
    return (SortedMap)this.overflowEntries;
  }

  static <FieldDescriptorType extends FieldSet.FieldDescriptorLite<FieldDescriptorType>> SmallSortedMap<FieldDescriptorType, Object> newFieldMap(int paramInt)
  {
    // Byte code:
    //   0: new 107	com/google/protobuf/SmallSortedMap$1
    //   3: dup
    //   4: iload_0
    //   5: invokespecial 108	com/google/protobuf/SmallSortedMap$1:<init>	(I)V
    //   8: areturn
  }

  static <K extends Comparable<K>, V> SmallSortedMap<K, V> newInstanceForTest(int paramInt)
  {
    return new SmallSortedMap(paramInt);
  }

  private V removeArrayEntryAt(int paramInt)
  {
    checkMutable();
    Object localObject = ((Entry)this.entryList.remove(paramInt)).getValue();
    if (!this.overflowEntries.isEmpty())
    {
      Iterator localIterator = getOverflowEntriesMutable().entrySet().iterator();
      this.entryList.add(new Entry((Map.Entry)localIterator.next()));
      localIterator.remove();
    }
    return localObject;
  }

  public void clear()
  {
    checkMutable();
    if (!this.entryList.isEmpty())
      this.entryList.clear();
    if (!this.overflowEntries.isEmpty())
      this.overflowEntries.clear();
  }

  public boolean containsKey(Object paramObject)
  {
    Comparable localComparable = (Comparable)paramObject;
    return (binarySearchInArray(localComparable) >= 0) || (this.overflowEntries.containsKey(localComparable));
  }

  public Set<Map.Entry<K, V>> entrySet()
  {
    if (this.lazyEntrySet == null)
      this.lazyEntrySet = new EntrySet(null);
    return this.lazyEntrySet;
  }

  public V get(Object paramObject)
  {
    Comparable localComparable = (Comparable)paramObject;
    int i = binarySearchInArray(localComparable);
    if (i >= 0)
      return ((Entry)this.entryList.get(i)).getValue();
    return this.overflowEntries.get(localComparable);
  }

  public Map.Entry<K, V> getArrayEntryAt(int paramInt)
  {
    return (Map.Entry)this.entryList.get(paramInt);
  }

  public int getNumArrayEntries()
  {
    return this.entryList.size();
  }

  public int getNumOverflowEntries()
  {
    return this.overflowEntries.size();
  }

  public Iterable<Map.Entry<K, V>> getOverflowEntries()
  {
    if (this.overflowEntries.isEmpty())
      return EmptySet.iterable();
    return this.overflowEntries.entrySet();
  }

  public boolean isImmutable()
  {
    return this.isImmutable;
  }

  public void makeImmutable()
  {
    if (!this.isImmutable)
      if (!this.overflowEntries.isEmpty())
        break label34;
    label34: for (Map localMap = Collections.emptyMap(); ; localMap = Collections.unmodifiableMap(this.overflowEntries))
    {
      this.overflowEntries = localMap;
      this.isImmutable = true;
      return;
    }
  }

  public V put(K paramK, V paramV)
  {
    checkMutable();
    int i = binarySearchInArray(paramK);
    if (i >= 0)
      return ((Entry)this.entryList.get(i)).setValue(paramV);
    ensureEntryArrayMutable();
    int j = -(i + 1);
    if (j >= this.maxArraySize)
      return getOverflowEntriesMutable().put(paramK, paramV);
    if (this.entryList.size() == this.maxArraySize)
    {
      Entry localEntry = (Entry)this.entryList.remove(-1 + this.maxArraySize);
      getOverflowEntriesMutable().put(localEntry.getKey(), localEntry.getValue());
    }
    this.entryList.add(j, new Entry(paramK, paramV));
    return null;
  }

  public V remove(Object paramObject)
  {
    checkMutable();
    Comparable localComparable = (Comparable)paramObject;
    int i = binarySearchInArray(localComparable);
    if (i >= 0)
      return removeArrayEntryAt(i);
    if (this.overflowEntries.isEmpty())
      return null;
    return this.overflowEntries.remove(localComparable);
  }

  public int size()
  {
    return this.entryList.size() + this.overflowEntries.size();
  }

  private static class EmptySet
  {
    private static final Iterable<Object> ITERABLE = new Iterable()
    {
      public Iterator<Object> iterator()
      {
        return SmallSortedMap.EmptySet.ITERATOR;
      }
    };
    private static final Iterator<Object> ITERATOR = new Iterator()
    {
      public boolean hasNext()
      {
        return false;
      }

      public Object next()
      {
        throw new NoSuchElementException();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };

    static <T> Iterable<T> iterable()
    {
      return ITERABLE;
    }
  }

  private class Entry
    implements Map.Entry<K, V>, Comparable<SmallSortedMap<K, V>.Entry>
  {
    private final K key;
    private V value;

    Entry(V arg2)
    {
      Object localObject1;
      this.key = localObject1;
      Object localObject2;
      this.value = localObject2;
    }

    Entry()
    {
      this((Comparable)localObject.getKey(), localObject.getValue());
    }

    private boolean equals(Object paramObject1, Object paramObject2)
    {
      if (paramObject1 == null)
        return paramObject2 == null;
      return paramObject1.equals(paramObject2);
    }

    public int compareTo(SmallSortedMap<K, V>.Entry paramSmallSortedMap)
    {
      return getKey().compareTo(paramSmallSortedMap.getKey());
    }

    public boolean equals(Object paramObject)
    {
      if (paramObject == this);
      Map.Entry localEntry;
      do
      {
        return true;
        if (!(paramObject instanceof Map.Entry))
          return false;
        localEntry = (Map.Entry)paramObject;
      }
      while ((equals(this.key, localEntry.getKey())) && (equals(this.value, localEntry.getValue())));
      return false;
    }

    public K getKey()
    {
      return this.key;
    }

    public V getValue()
    {
      return this.value;
    }

    public int hashCode()
    {
      int i;
      int j;
      if (this.key == null)
      {
        i = 0;
        Object localObject = this.value;
        j = 0;
        if (localObject != null)
          break label35;
      }
      while (true)
      {
        return i ^ j;
        i = this.key.hashCode();
        break;
        label35: j = this.value.hashCode();
      }
    }

    public V setValue(V paramV)
    {
      SmallSortedMap.this.checkMutable();
      Object localObject = this.value;
      this.value = paramV;
      return localObject;
    }

    public String toString()
    {
      return this.key + "=" + this.value;
    }
  }

  private class EntryIterator
    implements Iterator<Map.Entry<K, V>>
  {
    private Iterator<Map.Entry<K, V>> lazyOverflowIterator;
    private boolean nextCalledBeforeRemove;
    private int pos = -1;

    private EntryIterator()
    {
    }

    private Iterator<Map.Entry<K, V>> getOverflowIterator()
    {
      if (this.lazyOverflowIterator == null)
        this.lazyOverflowIterator = SmallSortedMap.this.overflowEntries.entrySet().iterator();
      return this.lazyOverflowIterator;
    }

    public boolean hasNext()
    {
      return (1 + this.pos < SmallSortedMap.this.entryList.size()) || (getOverflowIterator().hasNext());
    }

    public Map.Entry<K, V> next()
    {
      this.nextCalledBeforeRemove = true;
      int i = 1 + this.pos;
      this.pos = i;
      if (i < SmallSortedMap.this.entryList.size())
        return (Map.Entry)SmallSortedMap.this.entryList.get(this.pos);
      return (Map.Entry)getOverflowIterator().next();
    }

    public void remove()
    {
      if (!this.nextCalledBeforeRemove)
        throw new IllegalStateException("remove() was called before next()");
      this.nextCalledBeforeRemove = false;
      SmallSortedMap.this.checkMutable();
      if (this.pos < SmallSortedMap.this.entryList.size())
      {
        SmallSortedMap localSmallSortedMap = SmallSortedMap.this;
        int i = this.pos;
        this.pos = (i - 1);
        localSmallSortedMap.removeArrayEntryAt(i);
        return;
      }
      getOverflowIterator().remove();
    }
  }

  private class EntrySet extends AbstractSet<Map.Entry<K, V>>
  {
    private EntrySet()
    {
    }

    public boolean add(Map.Entry<K, V> paramEntry)
    {
      if (!contains(paramEntry))
      {
        SmallSortedMap.this.put((Comparable)paramEntry.getKey(), paramEntry.getValue());
        return true;
      }
      return false;
    }

    public void clear()
    {
      SmallSortedMap.this.clear();
    }

    public boolean contains(Object paramObject)
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      Object localObject1 = SmallSortedMap.this.get(localEntry.getKey());
      Object localObject2 = localEntry.getValue();
      return (localObject1 == localObject2) || ((localObject1 != null) && (localObject1.equals(localObject2)));
    }

    public Iterator<Map.Entry<K, V>> iterator()
    {
      return new SmallSortedMap.EntryIterator(SmallSortedMap.this, null);
    }

    public boolean remove(Object paramObject)
    {
      Map.Entry localEntry = (Map.Entry)paramObject;
      if (contains(localEntry))
      {
        SmallSortedMap.this.remove(localEntry.getKey());
        return true;
      }
      return false;
    }

    public int size()
    {
      return SmallSortedMap.this.size();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.SmallSortedMap
 * JD-Core Version:    0.6.2
 */