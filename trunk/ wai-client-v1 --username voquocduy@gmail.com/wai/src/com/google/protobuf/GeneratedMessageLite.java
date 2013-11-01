package com.google.protobuf;

import java.io.IOException;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

public abstract class GeneratedMessageLite extends AbstractMessageLite
  implements Serializable
{
  private static final long serialVersionUID = 1L;

  protected GeneratedMessageLite()
  {
  }

  protected GeneratedMessageLite(Builder paramBuilder)
  {
  }

  public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newRepeatedGeneratedExtension(ContainingType paramContainingType, MessageLite paramMessageLite, Internal.EnumLiteMap<?> paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, boolean paramBoolean)
  {
    List localList = Collections.emptyList();
    GeneratedExtension localGeneratedExtension = new GeneratedExtension(paramContainingType, localList, paramMessageLite, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, true, paramBoolean, null), null);
    return localGeneratedExtension;
  }

  public static <ContainingType extends MessageLite, Type> GeneratedExtension<ContainingType, Type> newSingularGeneratedExtension(ContainingType paramContainingType, Type paramType, MessageLite paramMessageLite, Internal.EnumLiteMap<?> paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType)
  {
    GeneratedExtension localGeneratedExtension = new GeneratedExtension(paramContainingType, paramType, paramMessageLite, new ExtensionDescriptor(paramEnumLiteMap, paramInt, paramFieldType, false, false, null), null);
    return localGeneratedExtension;
  }

  protected Object writeReplace()
    throws ObjectStreamException
  {
    return new SerializedForm(this);
  }

  public static abstract class Builder<MessageType extends GeneratedMessageLite, BuilderType extends Builder> extends AbstractMessageLite.Builder<BuilderType>
  {
    public BuilderType clear()
    {
      return this;
    }

    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public abstract MessageType getDefaultInstanceForType();

    public abstract BuilderType mergeFrom(MessageType paramMessageType);

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      return paramCodedInputStream.skipField(paramInt);
    }
  }

  public static abstract class ExtendableBuilder<MessageType extends GeneratedMessageLite.ExtendableMessage<MessageType>, BuilderType extends ExtendableBuilder<MessageType, BuilderType>> extends GeneratedMessageLite.Builder<MessageType, BuilderType>
    implements GeneratedMessageLite.ExtendableMessageOrBuilder<MessageType>
  {
    private FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions = FieldSet.emptySet();
    private boolean extensionsIsMutable;

    private FieldSet<GeneratedMessageLite.ExtensionDescriptor> buildExtensions()
    {
      this.extensions.makeImmutable();
      this.extensionsIsMutable = false;
      return this.extensions;
    }

    private void ensureExtensionsIsMutable()
    {
      if (!this.extensionsIsMutable)
      {
        this.extensions = this.extensions.clone();
        this.extensionsIsMutable = true;
      }
    }

    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }

    public final <Type> BuilderType addExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.addRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension), paramType);
      return this;
    }

    public BuilderType clear()
    {
      this.extensions.clear();
      this.extensionsIsMutable = false;
      return (ExtendableBuilder)super.clear();
    }

    public final <Type> BuilderType clearExtension(GeneratedMessageLite.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.clearField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
      return this;
    }

    public BuilderType clone()
    {
      throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Object localObject = this.extensions.getField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
      if (localObject == null)
        localObject = GeneratedMessageLite.GeneratedExtension.access$200(paramGeneratedExtension);
      return localObject;
    }

    public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension), paramInt);
    }

    public final <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedFieldCount(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
    }

    public final <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
    }

    protected final void mergeExtensionFields(MessageType paramMessageType)
    {
      ensureExtensionsIsMutable();
      this.extensions.mergeFrom(GeneratedMessageLite.ExtendableMessage.access$300(paramMessageType));
    }

    protected boolean parseUnknownField(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite, int paramInt)
      throws IOException
    {
      int i = WireFormat.getTagWireType(paramInt);
      int j = WireFormat.getTagFieldNumber(paramInt);
      GeneratedMessageLite.GeneratedExtension localGeneratedExtension = paramExtensionRegistryLite.findLiteExtensionByNumber(getDefaultInstanceForType(), j);
      int k = 0;
      int m;
      if (localGeneratedExtension == null)
        m = 1;
      while (m != 0)
      {
        return paramCodedInputStream.skipField(paramInt);
        if (i == FieldSet.getWireFormatForFieldType(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType(), false))
        {
          k = 0;
          m = 0;
        }
        else if ((GeneratedMessageLite.ExtensionDescriptor.access$500(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension))) && (GeneratedMessageLite.ExtensionDescriptor.access$600(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension)).isPackable()) && (i == FieldSet.getWireFormatForFieldType(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType(), true)))
        {
          k = 1;
          m = 0;
        }
        else
        {
          m = 1;
          k = 0;
        }
      }
      if (k != 0)
      {
        int i1 = paramCodedInputStream.pushLimit(paramCodedInputStream.readRawVarint32());
        if (GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType() == WireFormat.FieldType.ENUM)
          while (paramCodedInputStream.getBytesUntilLimit() > 0)
          {
            int i2 = paramCodedInputStream.readEnum();
            Internal.EnumLite localEnumLite = GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getEnumType().findValueByNumber(i2);
            if (localEnumLite == null)
              return true;
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension), localEnumLite);
          }
        while (paramCodedInputStream.getBytesUntilLimit() > 0)
        {
          Object localObject2 = FieldSet.readPrimitiveField(paramCodedInputStream, GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType());
          ensureExtensionsIsMutable();
          this.extensions.addRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension), localObject2);
        }
        paramCodedInputStream.popLimit(i1);
      }
      while (true)
      {
        return true;
        Object localObject1;
        switch (GeneratedMessageLite.1.$SwitchMap$com$google$protobuf$WireFormat$JavaType[GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteJavaType().ordinal()])
        {
        default:
          localObject1 = FieldSet.readPrimitiveField(paramCodedInputStream, GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType());
        case 1:
        case 2:
        }
        while (true)
          if (GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).isRepeated())
          {
            ensureExtensionsIsMutable();
            this.extensions.addRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension), localObject1);
            break;
            boolean bool = GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).isRepeated();
            MessageLite.Builder localBuilder = null;
            if (!bool)
            {
              MessageLite localMessageLite = (MessageLite)this.extensions.getField(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension));
              localBuilder = null;
              if (localMessageLite != null)
                localBuilder = localMessageLite.toBuilder();
            }
            if (localBuilder == null)
              localBuilder = GeneratedMessageLite.GeneratedExtension.access$700(localGeneratedExtension).newBuilderForType();
            if (GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getLiteType() == WireFormat.FieldType.GROUP)
              paramCodedInputStream.readGroup(localGeneratedExtension.getNumber(), localBuilder, paramExtensionRegistryLite);
            while (true)
            {
              localObject1 = localBuilder.build();
              break;
              paramCodedInputStream.readMessage(localBuilder, paramExtensionRegistryLite);
            }
            int n = paramCodedInputStream.readEnum();
            localObject1 = GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension).getEnumType().findValueByNumber(n);
            if (localObject1 == null)
              return true;
          }
        ensureExtensionsIsMutable();
        this.extensions.setField(GeneratedMessageLite.GeneratedExtension.access$100(localGeneratedExtension), localObject1);
      }
    }

    public final <Type> BuilderType setExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.setRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension), paramInt, paramType);
      return this;
    }

    public final <Type> BuilderType setExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension, Type paramType)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      ensureExtensionsIsMutable();
      this.extensions.setField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension), paramType);
      return this;
    }
  }

  public static abstract class ExtendableMessage<MessageType extends ExtendableMessage<MessageType>> extends GeneratedMessageLite
    implements GeneratedMessageLite.ExtendableMessageOrBuilder<MessageType>
  {
    private final FieldSet<GeneratedMessageLite.ExtensionDescriptor> extensions;

    protected ExtendableMessage()
    {
      this.extensions = FieldSet.newFieldSet();
    }

    protected ExtendableMessage(GeneratedMessageLite.ExtendableBuilder<MessageType, ?> paramExtendableBuilder)
    {
      this.extensions = paramExtendableBuilder.buildExtensions();
    }

    private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension<MessageType, ?> paramGeneratedExtension)
    {
      if (paramGeneratedExtension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
        throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
    }

    protected boolean extensionsAreInitialized()
    {
      return this.extensions.isInitialized();
    }

    protected int extensionsSerializedSize()
    {
      return this.extensions.getSerializedSize();
    }

    protected int extensionsSerializedSizeAsMessageSet()
    {
      return this.extensions.getMessageSetSerializedSize();
    }

    public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      Object localObject = this.extensions.getField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
      if (localObject == null)
        localObject = GeneratedMessageLite.GeneratedExtension.access$200(paramGeneratedExtension);
      return localObject;
    }

    public final <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension), paramInt);
    }

    public final <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.getRepeatedFieldCount(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
    }

    public final <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension)
    {
      verifyExtensionContainingType(paramGeneratedExtension);
      return this.extensions.hasField(GeneratedMessageLite.GeneratedExtension.access$100(paramGeneratedExtension));
    }

    protected ExtendableMessage<MessageType>.ExtensionWriter newExtensionWriter()
    {
      return new ExtensionWriter(false, null);
    }

    protected ExtendableMessage<MessageType>.ExtensionWriter newMessageSetExtensionWriter()
    {
      return new ExtensionWriter(true, null);
    }

    protected class ExtensionWriter
    {
      private final Iterator<Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object>> iter = GeneratedMessageLite.ExtendableMessage.this.extensions.iterator();
      private final boolean messageSetWireFormat;
      private Map.Entry<GeneratedMessageLite.ExtensionDescriptor, Object> next;

      private ExtensionWriter(boolean arg2)
      {
        if (this.iter.hasNext())
          this.next = ((Map.Entry)this.iter.next());
        boolean bool;
        this.messageSetWireFormat = bool;
      }

      public void writeUntil(int paramInt, CodedOutputStream paramCodedOutputStream)
        throws IOException
      {
        while ((this.next != null) && (((GeneratedMessageLite.ExtensionDescriptor)this.next.getKey()).getNumber() < paramInt))
        {
          GeneratedMessageLite.ExtensionDescriptor localExtensionDescriptor = (GeneratedMessageLite.ExtensionDescriptor)this.next.getKey();
          if ((this.messageSetWireFormat) && (localExtensionDescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE) && (!localExtensionDescriptor.isRepeated()))
            paramCodedOutputStream.writeMessageSetExtension(localExtensionDescriptor.getNumber(), (MessageLite)this.next.getValue());
          while (true)
          {
            if (!this.iter.hasNext())
              break label131;
            this.next = ((Map.Entry)this.iter.next());
            break;
            FieldSet.writeField(localExtensionDescriptor, this.next.getValue(), paramCodedOutputStream);
          }
          label131: this.next = null;
        }
      }
    }
  }

  public static abstract interface ExtendableMessageOrBuilder<MessageType extends GeneratedMessageLite.ExtendableMessage> extends MessageLiteOrBuilder
  {
    public abstract <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension);

    public abstract <Type> Type getExtension(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension, int paramInt);

    public abstract <Type> int getExtensionCount(GeneratedMessageLite.GeneratedExtension<MessageType, List<Type>> paramGeneratedExtension);

    public abstract <Type> boolean hasExtension(GeneratedMessageLite.GeneratedExtension<MessageType, Type> paramGeneratedExtension);
  }

  private static final class ExtensionDescriptor
    implements FieldSet.FieldDescriptorLite<ExtensionDescriptor>
  {
    private final Internal.EnumLiteMap<?> enumTypeMap;
    private final boolean isPacked;
    private final boolean isRepeated;
    private final int number;
    private final WireFormat.FieldType type;

    private ExtensionDescriptor(Internal.EnumLiteMap<?> paramEnumLiteMap, int paramInt, WireFormat.FieldType paramFieldType, boolean paramBoolean1, boolean paramBoolean2)
    {
      this.enumTypeMap = paramEnumLiteMap;
      this.number = paramInt;
      this.type = paramFieldType;
      this.isRepeated = paramBoolean1;
      this.isPacked = paramBoolean2;
    }

    public int compareTo(ExtensionDescriptor paramExtensionDescriptor)
    {
      return this.number - paramExtensionDescriptor.number;
    }

    public Internal.EnumLiteMap<?> getEnumType()
    {
      return this.enumTypeMap;
    }

    public WireFormat.JavaType getLiteJavaType()
    {
      return this.type.getJavaType();
    }

    public WireFormat.FieldType getLiteType()
    {
      return this.type;
    }

    public int getNumber()
    {
      return this.number;
    }

    public MessageLite.Builder internalMergeFrom(MessageLite.Builder paramBuilder, MessageLite paramMessageLite)
    {
      return ((GeneratedMessageLite.Builder)paramBuilder).mergeFrom((GeneratedMessageLite)paramMessageLite);
    }

    public boolean isPacked()
    {
      return this.isPacked;
    }

    public boolean isRepeated()
    {
      return this.isRepeated;
    }
  }

  public static final class GeneratedExtension<ContainingType extends MessageLite, Type>
  {
    private final ContainingType containingTypeDefaultInstance;
    private final Type defaultValue;
    private final GeneratedMessageLite.ExtensionDescriptor descriptor;
    private final MessageLite messageDefaultInstance;

    private GeneratedExtension(ContainingType paramContainingType, Type paramType, MessageLite paramMessageLite, GeneratedMessageLite.ExtensionDescriptor paramExtensionDescriptor)
    {
      if (paramContainingType == null)
        throw new IllegalArgumentException("Null containingTypeDefaultInstance");
      if ((paramExtensionDescriptor.getLiteType() == WireFormat.FieldType.MESSAGE) && (paramMessageLite == null))
        throw new IllegalArgumentException("Null messageDefaultInstance");
      this.containingTypeDefaultInstance = paramContainingType;
      this.defaultValue = paramType;
      this.messageDefaultInstance = paramMessageLite;
      this.descriptor = paramExtensionDescriptor;
    }

    public ContainingType getContainingTypeDefaultInstance()
    {
      return this.containingTypeDefaultInstance;
    }

    public MessageLite getMessageDefaultInstance()
    {
      return this.messageDefaultInstance;
    }

    public int getNumber()
    {
      return this.descriptor.getNumber();
    }
  }

  static final class SerializedForm
    implements Serializable
  {
    private static final long serialVersionUID;
    private byte[] asBytes;
    private String messageClassName;

    SerializedForm(MessageLite paramMessageLite)
    {
      this.messageClassName = paramMessageLite.getClass().getName();
      this.asBytes = paramMessageLite.toByteArray();
    }

    protected Object readResolve()
      throws ObjectStreamException
    {
      try
      {
        MessageLite.Builder localBuilder = (MessageLite.Builder)Class.forName(this.messageClassName).getMethod("newBuilder", new Class[0]).invoke(null, new Object[0]);
        localBuilder.mergeFrom(this.asBytes);
        MessageLite localMessageLite = localBuilder.buildPartial();
        return localMessageLite;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        throw new RuntimeException("Unable to find proto buffer class", localClassNotFoundException);
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        throw new RuntimeException("Unable to find newBuilder method", localNoSuchMethodException);
      }
      catch (IllegalAccessException localIllegalAccessException)
      {
        throw new RuntimeException("Unable to call newBuilder method", localIllegalAccessException);
      }
      catch (InvocationTargetException localInvocationTargetException)
      {
        throw new RuntimeException("Error calling newBuilder", localInvocationTargetException.getCause());
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw new RuntimeException("Unable to understand proto buffer", localInvalidProtocolBufferException);
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.GeneratedMessageLite
 * JD-Core Version:    0.6.2
 */