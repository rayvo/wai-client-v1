package com.google.protobuf;

import java.util.List;

public abstract interface LazyStringList extends List<String>
{
  public abstract void add(ByteString paramByteString);

  public abstract ByteString getByteString(int paramInt);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.LazyStringList
 * JD-Core Version:    0.6.2
 */