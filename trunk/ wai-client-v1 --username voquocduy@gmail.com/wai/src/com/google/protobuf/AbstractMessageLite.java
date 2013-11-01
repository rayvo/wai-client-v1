package com.google.protobuf;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.Iterator;

public abstract class AbstractMessageLite
  implements MessageLite
{
  public byte[] toByteArray()
  {
    try
    {
      byte[] arrayOfByte = new byte[getSerializedSize()];
      CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(arrayOfByte);
      writeTo(localCodedOutputStream);
      localCodedOutputStream.checkNoSpaceLeft();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", localIOException);
    }
  }

  public ByteString toByteString()
  {
    try
    {
      ByteString.CodedBuilder localCodedBuilder = ByteString.newCodedBuilder(getSerializedSize());
      writeTo(localCodedBuilder.getCodedOutput());
      ByteString localByteString = localCodedBuilder.build();
      return localByteString;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException("Serializing to a ByteString threw an IOException (should never happen).", localIOException);
    }
  }

  public void writeDelimitedTo(OutputStream paramOutputStream)
    throws IOException
  {
    int i = getSerializedSize();
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream, CodedOutputStream.computePreferredBufferSize(i + CodedOutputStream.computeRawVarint32Size(i)));
    localCodedOutputStream.writeRawVarint32(i);
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    CodedOutputStream localCodedOutputStream = CodedOutputStream.newInstance(paramOutputStream, CodedOutputStream.computePreferredBufferSize(getSerializedSize()));
    writeTo(localCodedOutputStream);
    localCodedOutputStream.flush();
  }

  public static abstract class Builder<BuilderType extends Builder>
    implements MessageLite.Builder
  {
    protected static <T> void addAll(Iterable<T> paramIterable, Collection<? super T> paramCollection)
    {
      Iterator localIterator1 = paramIterable.iterator();
      while (localIterator1.hasNext())
        if (localIterator1.next() == null)
          throw new NullPointerException();
      if ((paramIterable instanceof Collection))
        paramCollection.addAll((Collection)paramIterable);
      while (true)
      {
        return;
        Iterator localIterator2 = paramIterable.iterator();
        while (localIterator2.hasNext())
          paramCollection.add(localIterator2.next());
      }
    }

    protected static UninitializedMessageException newUninitializedMessageException(MessageLite paramMessageLite)
    {
      return new UninitializedMessageException(paramMessageLite);
    }

    public abstract BuilderType clone();

    public boolean mergeDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      return mergeDelimitedFrom(paramInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public boolean mergeDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      int i = paramInputStream.read();
      if (i == -1)
        return false;
      mergeFrom(new LimitedInputStream(paramInputStream, CodedInputStream.readRawVarint32(i, paramInputStream)), paramExtensionRegistryLite);
      return true;
    }

    public BuilderType mergeFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = paramByteString.newCodedInput();
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", localIOException);
      }
    }

    public BuilderType mergeFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = paramByteString.newCodedInput();
        mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a ByteString threw an IOException (should never happen).", localIOException);
      }
    }

    public BuilderType mergeFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return mergeFrom(paramCodedInputStream, ExtensionRegistryLite.getEmptyRegistry());
    }

    public abstract BuilderType mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException;

    public BuilderType mergeFrom(InputStream paramInputStream)
      throws IOException
    {
      CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(localCodedInputStream);
      localCodedInputStream.checkLastTagWas(0);
      return this;
    }

    public BuilderType mergeFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramInputStream);
      mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
      localCodedInputStream.checkLastTagWas(0);
      return this;
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length);
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(localCodedInputStream);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, int paramInt1, int paramInt2, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      try
      {
        CodedInputStream localCodedInputStream = CodedInputStream.newInstance(paramArrayOfByte, paramInt1, paramInt2);
        mergeFrom(localCodedInputStream, paramExtensionRegistryLite);
        localCodedInputStream.checkLastTagWas(0);
        return this;
      }
      catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
      {
        throw localInvalidProtocolBufferException;
      }
      catch (IOException localIOException)
      {
        throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", localIOException);
      }
    }

    public BuilderType mergeFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return mergeFrom(paramArrayOfByte, 0, paramArrayOfByte.length, paramExtensionRegistryLite);
    }

    static final class LimitedInputStream extends FilterInputStream
    {
      private int limit;

      LimitedInputStream(InputStream paramInputStream, int paramInt)
      {
        super();
        this.limit = paramInt;
      }

      public int available()
        throws IOException
      {
        return Math.min(super.available(), this.limit);
      }

      public int read()
        throws IOException
      {
        int i;
        if (this.limit <= 0)
          i = -1;
        do
        {
          return i;
          i = super.read();
        }
        while (i < 0);
        this.limit = (-1 + this.limit);
        return i;
      }

      public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
        throws IOException
      {
        int i;
        if (this.limit <= 0)
          i = -1;
        do
        {
          return i;
          i = super.read(paramArrayOfByte, paramInt1, Math.min(paramInt2, this.limit));
        }
        while (i < 0);
        this.limit -= i;
        return i;
      }

      public long skip(long paramLong)
        throws IOException
      {
        long l = super.skip(Math.min(paramLong, this.limit));
        if (l >= 0L)
          this.limit = ((int)(this.limit - l));
        return l;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.protobuf.AbstractMessageLite
 * JD-Core Version:    0.6.2
 */