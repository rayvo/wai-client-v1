package com.google.protobuf;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class FieldSet<FieldDescriptorType extends FieldDescriptorLite<FieldDescriptorType>>
{
  private static final FieldSet DEFAULT_INSTANCE = new FieldSet(true);
  private final SmallSortedMap<FieldDescriptorType, Object> fields;
  private boolean isImmutable;

  private FieldSet()
  {
    this.fields = SmallSortedMap.newFieldMap(16);
  }

  private FieldSet(boolean paramBoolean)
  {
    this.fields = SmallSortedMap.newFieldMap(0);
    makeImmutable();
  }

  private static int computeElementSize(WireFormat.FieldType paramFieldType, int paramInt, Object paramObject)
  {
    int i = CodedOutputStream.computeTagSize(paramInt);
    if (paramFieldType == WireFormat.FieldType.GROUP)
      i *= 2;
    return i + computeElementSizeNoTag(paramFieldType, paramObject);
  }

  private static int computeElementSizeNoTag(WireFormat.FieldType paramFieldType, Object paramObject)
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    default:
      throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    case 1:
      return CodedOutputStream.computeDoubleSizeNoTag(((Double)paramObject).doubleValue());
    case 2:
      return CodedOutputStream.computeFloatSizeNoTag(((Float)paramObject).floatValue());
    case 3:
      return CodedOutputStream.computeInt64SizeNoTag(((Long)paramObject).longValue());
    case 4:
      return CodedOutputStream.computeUInt64SizeNoTag(((Long)paramObject).longValue());
    case 5:
      return CodedOutputStream.computeInt32SizeNoTag(((Integer)paramObject).intValue());
    case 6:
      return CodedOutputStream.computeFixed64SizeNoTag(((Long)paramObject).longValue());
    case 7:
      return CodedOutputStream.computeFixed32SizeNoTag(((Integer)paramObject).intValue());
    case 8:
      return CodedOutputStream.computeBoolSizeNoTag(((Boolean)paramObject).booleanValue());
    case 9:
      return CodedOutputStream.computeStringSizeNoTag((String)paramObject);
    case 16:
      return CodedOutputStream.computeGroupSizeNoTag((MessageLite)paramObject);
    case 17:
      return CodedOutputStream.computeMessageSizeNoTag((MessageLite)paramObject);
    case 10:
      return CodedOutputStream.computeBytesSizeNoTag((ByteString)paramObject);
    case 11:
      return CodedOutputStream.computeUInt32SizeNoTag(((Integer)paramObject).intValue());
    case 12:
      return CodedOutputStream.computeSFixed32SizeNoTag(((Integer)paramObject).intValue());
    case 13:
      return CodedOutputStream.computeSFixed64SizeNoTag(((Long)paramObject).longValue());
    case 14:
      return CodedOutputStream.computeSInt32SizeNoTag(((Integer)paramObject).intValue());
    case 15:
      return CodedOutputStream.computeSInt64SizeNoTag(((Long)paramObject).longValue());
    case 18:
    }
    return CodedOutputStream.computeEnumSizeNoTag(((Internal.EnumLite)paramObject).getNumber());
  }

  public static int computeFieldSize(FieldDescriptorLite<?> paramFieldDescriptorLite, Object paramObject)
  {
    WireFormat.FieldType localFieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated())
    {
      int j;
      if (paramFieldDescriptorLite.isPacked())
      {
        int k = 0;
        Iterator localIterator2 = ((List)paramObject).iterator();
        while (localIterator2.hasNext())
          k += computeElementSizeNoTag(localFieldType, localIterator2.next());
        j = k + CodedOutputStream.computeTagSize(i) + CodedOutputStream.computeRawVarint32Size(k);
      }
      while (true)
      {
        return j;
        j = 0;
        Iterator localIterator1 = ((List)paramObject).iterator();
        while (localIterator1.hasNext())
          j += computeElementSize(localFieldType, i, localIterator1.next());
      }
    }
    return computeElementSize(localFieldType, i, paramObject);
  }

  public static <T extends FieldDescriptorLite<T>> FieldSet<T> emptySet()
  {
    return DEFAULT_INSTANCE;
  }

  private int getMessageSetSerializedSize(Map.Entry<FieldDescriptorType, Object> paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if ((localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptorLite.isRepeated()) && (!localFieldDescriptorLite.isPacked()))
      return CodedOutputStream.computeMessageSetExtensionSize(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)paramEntry.getValue());
    return computeFieldSize(localFieldDescriptorLite, paramEntry.getValue());
  }

  static int getWireFormatForFieldType(WireFormat.FieldType paramFieldType, boolean paramBoolean)
  {
    if (paramBoolean)
      return 2;
    return paramFieldType.getWireType();
  }

  private boolean isInitialized(Map.Entry<FieldDescriptorType, Object> paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if (localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
    {
      if (localFieldDescriptorLite.isRepeated())
      {
        Iterator localIterator = ((List)paramEntry.getValue()).iterator();
        do
          if (!localIterator.hasNext())
            break;
        while (((MessageLite)localIterator.next()).isInitialized());
        return false;
      }
      if (!((MessageLite)paramEntry.getValue()).isInitialized())
        return false;
    }
    return true;
  }

  private void mergeFromField(Map.Entry<FieldDescriptorType, Object> paramEntry)
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    Object localObject1 = paramEntry.getValue();
    if (localFieldDescriptorLite.isRepeated())
    {
      Object localObject3 = this.fields.get(localFieldDescriptorLite);
      if (localObject3 == null)
      {
        this.fields.put(localFieldDescriptorLite, new ArrayList((List)localObject1));
        return;
      }
      ((List)localObject3).addAll((List)localObject1);
      return;
    }
    if (localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE)
    {
      Object localObject2 = this.fields.get(localFieldDescriptorLite);
      if (localObject2 == null)
      {
        this.fields.put(localFieldDescriptorLite, localObject1);
        return;
      }
      this.fields.put(localFieldDescriptorLite, localFieldDescriptorLite.internalMergeFrom(((MessageLite)localObject2).toBuilder(), (MessageLite)localObject1).build());
      return;
    }
    this.fields.put(localFieldDescriptorLite, localObject1);
  }

  public static <T extends FieldDescriptorLite<T>> FieldSet<T> newFieldSet()
  {
    return new FieldSet();
  }

  public static Object readPrimitiveField(CodedInputStream paramCodedInputStream, WireFormat.FieldType paramFieldType)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    default:
      throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    case 1:
      return Double.valueOf(paramCodedInputStream.readDouble());
    case 2:
      return Float.valueOf(paramCodedInputStream.readFloat());
    case 3:
      return Long.valueOf(paramCodedInputStream.readInt64());
    case 4:
      return Long.valueOf(paramCodedInputStream.readUInt64());
    case 5:
      return Integer.valueOf(paramCodedInputStream.readInt32());
    case 6:
      return Long.valueOf(paramCodedInputStream.readFixed64());
    case 7:
      return Integer.valueOf(paramCodedInputStream.readFixed32());
    case 8:
      return Boolean.valueOf(paramCodedInputStream.readBool());
    case 9:
      return paramCodedInputStream.readString();
    case 10:
      return paramCodedInputStream.readBytes();
    case 11:
      return Integer.valueOf(paramCodedInputStream.readUInt32());
    case 12:
      return Integer.valueOf(paramCodedInputStream.readSFixed32());
    case 13:
      return Long.valueOf(paramCodedInputStream.readSFixed64());
    case 14:
      return Integer.valueOf(paramCodedInputStream.readSInt32());
    case 15:
      return Long.valueOf(paramCodedInputStream.readSInt64());
    case 16:
      throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
    case 17:
      throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
    case 18:
    }
    throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
  }

  private static void verifyType(WireFormat.FieldType paramFieldType, Object paramObject)
  {
    if (paramObject == null)
      throw new NullPointerException();
    int i = 1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[paramFieldType.getJavaType().ordinal()];
    boolean bool = false;
    switch (i)
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    case 6:
    case 7:
    case 8:
    case 9:
    }
    while (!bool)
    {
      throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      bool = paramObject instanceof Integer;
      continue;
      bool = paramObject instanceof Long;
      continue;
      bool = paramObject instanceof Float;
      continue;
      bool = paramObject instanceof Double;
      continue;
      bool = paramObject instanceof Boolean;
      continue;
      bool = paramObject instanceof String;
      continue;
      bool = paramObject instanceof ByteString;
      continue;
      bool = paramObject instanceof Internal.EnumLite;
      continue;
      bool = paramObject instanceof MessageLite;
    }
  }

  private static void writeElement(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, int paramInt, Object paramObject)
    throws IOException
  {
    if (paramFieldType == WireFormat.FieldType.GROUP)
    {
      paramCodedOutputStream.writeGroup(paramInt, (MessageLite)paramObject);
      return;
    }
    paramCodedOutputStream.writeTag(paramInt, getWireFormatForFieldType(paramFieldType, false));
    writeElementNoTag(paramCodedOutputStream, paramFieldType, paramObject);
  }

  private static void writeElementNoTag(CodedOutputStream paramCodedOutputStream, WireFormat.FieldType paramFieldType, Object paramObject)
    throws IOException
  {
    switch (1.$SwitchMap$com$google$protobuf$WireFormat$FieldType[paramFieldType.ordinal()])
    {
    default:
      return;
    case 1:
      paramCodedOutputStream.writeDoubleNoTag(((Double)paramObject).doubleValue());
      return;
    case 2:
      paramCodedOutputStream.writeFloatNoTag(((Float)paramObject).floatValue());
      return;
    case 3:
      paramCodedOutputStream.writeInt64NoTag(((Long)paramObject).longValue());
      return;
    case 4:
      paramCodedOutputStream.writeUInt64NoTag(((Long)paramObject).longValue());
      return;
    case 5:
      paramCodedOutputStream.writeInt32NoTag(((Integer)paramObject).intValue());
      return;
    case 6:
      paramCodedOutputStream.writeFixed64NoTag(((Long)paramObject).longValue());
      return;
    case 7:
      paramCodedOutputStream.writeFixed32NoTag(((Integer)paramObject).intValue());
      return;
    case 8:
      paramCodedOutputStream.writeBoolNoTag(((Boolean)paramObject).booleanValue());
      return;
    case 9:
      paramCodedOutputStream.writeStringNoTag((String)paramObject);
      return;
    case 16:
      paramCodedOutputStream.writeGroupNoTag((MessageLite)paramObject);
      return;
    case 17:
      paramCodedOutputStream.writeMessageNoTag((MessageLite)paramObject);
      return;
    case 10:
      paramCodedOutputStream.writeBytesNoTag((ByteString)paramObject);
      return;
    case 11:
      paramCodedOutputStream.writeUInt32NoTag(((Integer)paramObject).intValue());
      return;
    case 12:
      paramCodedOutputStream.writeSFixed32NoTag(((Integer)paramObject).intValue());
      return;
    case 13:
      paramCodedOutputStream.writeSFixed64NoTag(((Long)paramObject).longValue());
      return;
    case 14:
      paramCodedOutputStream.writeSInt32NoTag(((Integer)paramObject).intValue());
      return;
    case 15:
      paramCodedOutputStream.writeSInt64NoTag(((Long)paramObject).longValue());
      return;
    case 18:
    }
    paramCodedOutputStream.writeEnumNoTag(((Internal.EnumLite)paramObject).getNumber());
  }

  public static void writeField(FieldDescriptorLite<?> paramFieldDescriptorLite, Object paramObject, CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    WireFormat.FieldType localFieldType = paramFieldDescriptorLite.getLiteType();
    int i = paramFieldDescriptorLite.getNumber();
    if (paramFieldDescriptorLite.isRepeated())
    {
      List localList = (List)paramObject;
      if (paramFieldDescriptorLite.isPacked())
      {
        paramCodedOutputStream.writeTag(i, 2);
        int j = 0;
        Iterator localIterator2 = localList.iterator();
        while (localIterator2.hasNext())
          j += computeElementSizeNoTag(localFieldType, localIterator2.next());
        paramCodedOutputStream.writeRawVarint32(j);
        Iterator localIterator3 = localList.iterator();
        while (localIterator3.hasNext())
          writeElementNoTag(paramCodedOutputStream, localFieldType, localIterator3.next());
      }
      Iterator localIterator1 = localList.iterator();
      while (localIterator1.hasNext())
        writeElement(paramCodedOutputStream, localFieldType, i, localIterator1.next());
    }
    writeElement(paramCodedOutputStream, localFieldType, i, paramObject);
  }

  private void writeMessageSetTo(Map.Entry<FieldDescriptorType, Object> paramEntry, CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    FieldDescriptorLite localFieldDescriptorLite = (FieldDescriptorLite)paramEntry.getKey();
    if ((localFieldDescriptorLite.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localFieldDescriptorLite.isRepeated()) && (!localFieldDescriptorLite.isPacked()))
    {
      paramCodedOutputStream.writeMessageSetExtension(((FieldDescriptorLite)paramEntry.getKey()).getNumber(), (MessageLite)paramEntry.getValue());
      return;
    }
    writeField(localFieldDescriptorLite, paramEntry.getValue(), paramCodedOutputStream);
  }

  public void addRepeatedField(FieldDescriptorType paramFieldDescriptorType, Object paramObject)
  {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("addRepeatedField() can only be called on repeated fields.");
    verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    Object localObject1 = this.fields.get(paramFieldDescriptorType);
    Object localObject2;
    if (localObject1 == null)
    {
      localObject2 = new ArrayList();
      this.fields.put(paramFieldDescriptorType, localObject2);
    }
    while (true)
    {
      ((List)localObject2).add(paramObject);
      return;
      localObject2 = (List)localObject1;
    }
  }

  public void clear()
  {
    this.fields.clear();
  }

  public void clearField(FieldDescriptorType paramFieldDescriptorType)
  {
    this.fields.remove(paramFieldDescriptorType);
  }

  public FieldSet<FieldDescriptorType> clone()
  {
    FieldSet localFieldSet = newFieldSet();
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
    {
      Map.Entry localEntry2 = this.fields.getArrayEntryAt(i);
      localFieldSet.setField((FieldDescriptorLite)localEntry2.getKey(), localEntry2.getValue());
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator.next();
      localFieldSet.setField((FieldDescriptorLite)localEntry1.getKey(), localEntry1.getValue());
    }
    return localFieldSet;
  }

  public Map<FieldDescriptorType, Object> getAllFields()
  {
    if (this.fields.isImmutable())
      return this.fields;
    return Collections.unmodifiableMap(this.fields);
  }

  public Object getField(FieldDescriptorType paramFieldDescriptorType)
  {
    return this.fields.get(paramFieldDescriptorType);
  }

  public int getMessageSetSerializedSize()
  {
    int i = 0;
    for (int j = 0; j < this.fields.getNumArrayEntries(); j++)
      i += getMessageSetSerializedSize(this.fields.getArrayEntryAt(j));
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
      i += getMessageSetSerializedSize((Map.Entry)localIterator.next());
    return i;
  }

  public Object getRepeatedField(FieldDescriptorType paramFieldDescriptorType, int paramInt)
  {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorType);
    if (localObject == null)
      throw new IndexOutOfBoundsException();
    return ((List)localObject).get(paramInt);
  }

  public int getRepeatedFieldCount(FieldDescriptorType paramFieldDescriptorType)
  {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorType);
    if (localObject == null)
      return 0;
    return ((List)localObject).size();
  }

  public int getSerializedSize()
  {
    int i = 0;
    for (int j = 0; j < this.fields.getNumArrayEntries(); j++)
    {
      Map.Entry localEntry2 = this.fields.getArrayEntryAt(j);
      i += computeFieldSize((FieldDescriptorLite)localEntry2.getKey(), localEntry2.getValue());
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator.next();
      i += computeFieldSize((FieldDescriptorLite)localEntry1.getKey(), localEntry1.getValue());
    }
    return i;
  }

  public boolean hasField(FieldDescriptorType paramFieldDescriptorType)
  {
    if (paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("hasField() can only be called on non-repeated fields.");
    return this.fields.get(paramFieldDescriptorType) != null;
  }

  public boolean isImmutable()
  {
    return this.isImmutable;
  }

  public boolean isInitialized()
  {
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
      if (!isInitialized(this.fields.getArrayEntryAt(i)))
        return false;
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
      if (!isInitialized((Map.Entry)localIterator.next()))
        return false;
    return true;
  }

  public Iterator<Map.Entry<FieldDescriptorType, Object>> iterator()
  {
    return this.fields.entrySet().iterator();
  }

  public void makeImmutable()
  {
    if (this.isImmutable)
      return;
    this.fields.makeImmutable();
    this.isImmutable = true;
  }

  public void mergeFrom(FieldSet<FieldDescriptorType> paramFieldSet)
  {
    for (int i = 0; i < paramFieldSet.fields.getNumArrayEntries(); i++)
      mergeFromField(paramFieldSet.fields.getArrayEntryAt(i));
    Iterator localIterator = paramFieldSet.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
      mergeFromField((Map.Entry)localIterator.next());
  }

  public void setField(FieldDescriptorType paramFieldDescriptorType, Object paramObject)
  {
    if (paramFieldDescriptorType.isRepeated())
    {
      if (!(paramObject instanceof List))
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll((List)paramObject);
      Iterator localIterator = localArrayList.iterator();
      while (localIterator.hasNext())
      {
        Object localObject = localIterator.next();
        verifyType(paramFieldDescriptorType.getLiteType(), localObject);
      }
      paramObject = localArrayList;
    }
    while (true)
    {
      this.fields.put(paramFieldDescriptorType, paramObject);
      return;
      verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    }
  }

  public void setRepeatedField(FieldDescriptorType paramFieldDescriptorType, int paramInt, Object paramObject)
  {
    if (!paramFieldDescriptorType.isRepeated())
      throw new IllegalArgumentException("getRepeatedField() can only be called on repeated fields.");
    Object localObject = this.fields.get(paramFieldDescriptorType);
    if (localObject == null)
      throw new IndexOutOfBoundsException();
    verifyType(paramFieldDescriptorType.getLiteType(), paramObject);
    ((List)localObject).set(paramInt, paramObject);
  }

  public void writeMessageSetTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
      writeMessageSetTo(this.fields.getArrayEntryAt(i), paramCodedOutputStream);
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
      writeMessageSetTo((Map.Entry)localIterator.next(), paramCodedOutputStream);
  }

  public void writeTo(CodedOutputStream paramCodedOutputStream)
    throws IOException
  {
    for (int i = 0; i < this.fields.getNumArrayEntries(); i++)
    {
      Map.Entry localEntry2 = this.fields.getArrayEntryAt(i);
      writeField((FieldDescriptorLite)localEntry2.getKey(), localEntry2.getValue(), paramCodedOutputStream);
    }
    Iterator localIterator = this.fields.getOverflowEntries().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry1 = (Map.Entry)localIterator.next();
      writeField((FieldDescriptorLite)localEntry1.getKey(), localEntry1.getValue(), paramCodedOutputStream);
    }
  }

  public static abstract interface FieldDescriptorLite<T extends FieldDescriptorLite<T>> extends Comparable<T>
  {
    public abstract Internal.EnumLiteMap<?> getEnumType();

    public abstract WireFormat.JavaType getLiteJavaType();

    public abstract WireFormat.FieldType getLiteType();

    public abstract int getNumber();

    public abstract MessageLite.Builder internalMergeFrom(MessageLite.Builder paramBuilder, MessageLite paramMessageLite);

    public abstract boolean isPacked();

    public abstract boolean isRepeated();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.FieldSet
 * JD-Core Version:    0.6.2
 */