package com.google.protobuf;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.RandomAccess;

public class UnmodifiableLazyStringList extends AbstractList<String>
  implements LazyStringList, RandomAccess
{
  private final LazyStringList list;

  public UnmodifiableLazyStringList(LazyStringList paramLazyStringList)
  {
    this.list = paramLazyStringList;
  }

  public void add(ByteString paramByteString)
  {
    throw new UnsupportedOperationException();
  }

  public String get(int paramInt)
  {
    return (String)this.list.get(paramInt);
  }

  public ByteString getByteString(int paramInt)
  {
    return this.list.getByteString(paramInt);
  }

  public Iterator<String> iterator()
  {
    return new Iterator()
    {
      Iterator<String> iter = UnmodifiableLazyStringList.this.list.iterator();

      public boolean hasNext()
      {
        return this.iter.hasNext();
      }

      public String next()
      {
        return (String)this.iter.next();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    };
  }

  public ListIterator<String> listIterator(final int paramInt)
  {
    return new ListIterator()
    {
      ListIterator<String> iter = UnmodifiableLazyStringList.this.list.listIterator(paramInt);

      public void add(String paramAnonymousString)
      {
        throw new UnsupportedOperationException();
      }

      public boolean hasNext()
      {
        return this.iter.hasNext();
      }

      public boolean hasPrevious()
      {
        return this.iter.hasPrevious();
      }

      public String next()
      {
        return (String)this.iter.next();
      }

      public int nextIndex()
      {
        return this.iter.nextIndex();
      }

      public String previous()
      {
        return (String)this.iter.previous();
      }

      public int previousIndex()
      {
        return this.iter.previousIndex();
      }

      public void remove()
      {
        throw new UnsupportedOperationException();
      }

      public void set(String paramAnonymousString)
      {
        throw new UnsupportedOperationException();
      }
    };
  }

  public int size()
  {
    return this.list.size();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.UnmodifiableLazyStringList
 * JD-Core Version:    0.6.2
 */