package com.google.goggles;

import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import com.google.protobuf.Internal;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.MessageLiteOrBuilder;
import com.google.protobuf.UninitializedMessageException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectStreamException;

public final class MusicInformationProtos
{
  public static void registerAllExtensions(ExtensionRegistryLite paramExtensionRegistryLite)
  {
  }

  public static final class MusicInformation extends GeneratedMessageLite
    implements MusicInformationProtos.MusicInformationOrBuilder
  {
    public static final int ALBUM_FIELD_NUMBER = 3;
    public static final int ARTIST_FIELD_NUMBER = 1;
    public static final int ARTWORK_URL_FIELD_NUMBER = 4;
    public static final int GOOGLE_MUSIC_ALBUM_ID_FIELD_NUMBER = 6;
    public static final int GOOGLE_MUSIC_TRACK_ID_FIELD_NUMBER = 5;
    public static final int RECORD_LABEL_FIELD_NUMBER = 7;
    public static final int TRACK_FIELD_NUMBER = 2;
    private static final MusicInformation defaultInstance = new MusicInformation(true);
    private static final long serialVersionUID;
    private Object album_;
    private Object artist_;
    private Object artworkUrl_;
    private int bitField0_;
    private Object googleMusicAlbumId_;
    private Object googleMusicTrackId_;
    private byte memoizedIsInitialized = -1;
    private int memoizedSerializedSize = -1;
    private Object recordLabel_;
    private Object track_;

    static
    {
      defaultInstance.initFields();
    }

    private MusicInformation(Builder paramBuilder)
    {
      super();
    }

    private MusicInformation(boolean paramBoolean)
    {
    }

    private ByteString getAlbumBytes()
    {
      Object localObject = this.album_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.album_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getArtistBytes()
    {
      Object localObject = this.artist_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.artist_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getArtworkUrlBytes()
    {
      Object localObject = this.artworkUrl_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.artworkUrl_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    public static MusicInformation getDefaultInstance()
    {
      return defaultInstance;
    }

    private ByteString getGoogleMusicAlbumIdBytes()
    {
      Object localObject = this.googleMusicAlbumId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.googleMusicAlbumId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getGoogleMusicTrackIdBytes()
    {
      Object localObject = this.googleMusicTrackId_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.googleMusicTrackId_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getRecordLabelBytes()
    {
      Object localObject = this.recordLabel_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.recordLabel_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private ByteString getTrackBytes()
    {
      Object localObject = this.track_;
      if ((localObject instanceof String))
      {
        ByteString localByteString = ByteString.copyFromUtf8((String)localObject);
        this.track_ = localByteString;
        return localByteString;
      }
      return (ByteString)localObject;
    }

    private void initFields()
    {
      this.artist_ = "";
      this.track_ = "";
      this.album_ = "";
      this.artworkUrl_ = "";
      this.googleMusicTrackId_ = "";
      this.googleMusicAlbumId_ = "";
      this.recordLabel_ = "";
    }

    public static Builder newBuilder()
    {
      return Builder.access$100();
    }

    public static Builder newBuilder(MusicInformation paramMusicInformation)
    {
      return newBuilder().mergeFrom(paramMusicInformation);
    }

    public static MusicInformation parseDelimitedFrom(InputStream paramInputStream)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream))
        return localBuilder.buildParsed();
      return null;
    }

    public static MusicInformation parseDelimitedFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      Builder localBuilder = newBuilder();
      if (localBuilder.mergeDelimitedFrom(paramInputStream, paramExtensionRegistryLite))
        return localBuilder.buildParsed();
      return null;
    }

    public static MusicInformation parseFrom(ByteString paramByteString)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString)).buildParsed();
    }

    public static MusicInformation parseFrom(ByteString paramByteString, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramByteString, paramExtensionRegistryLite)).buildParsed();
    }

    public static MusicInformation parseFrom(CodedInputStream paramCodedInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramCodedInputStream)).buildParsed();
    }

    public static MusicInformation parseFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return newBuilder().mergeFrom(paramCodedInputStream, paramExtensionRegistryLite).buildParsed();
    }

    public static MusicInformation parseFrom(InputStream paramInputStream)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream)).buildParsed();
    }

    public static MusicInformation parseFrom(InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
      throws IOException
    {
      return ((Builder)newBuilder().mergeFrom(paramInputStream, paramExtensionRegistryLite)).buildParsed();
    }

    public static MusicInformation parseFrom(byte[] paramArrayOfByte)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte)).buildParsed();
    }

    public static MusicInformation parseFrom(byte[] paramArrayOfByte, ExtensionRegistryLite paramExtensionRegistryLite)
      throws InvalidProtocolBufferException
    {
      return ((Builder)newBuilder().mergeFrom(paramArrayOfByte, paramExtensionRegistryLite)).buildParsed();
    }

    public String getAlbum()
    {
      Object localObject = this.album_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.album_ = str;
      return str;
    }

    public String getArtist()
    {
      Object localObject = this.artist_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.artist_ = str;
      return str;
    }

    public String getArtworkUrl()
    {
      Object localObject = this.artworkUrl_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.artworkUrl_ = str;
      return str;
    }

    public MusicInformation getDefaultInstanceForType()
    {
      return defaultInstance;
    }

    public String getGoogleMusicAlbumId()
    {
      Object localObject = this.googleMusicAlbumId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.googleMusicAlbumId_ = str;
      return str;
    }

    public String getGoogleMusicTrackId()
    {
      Object localObject = this.googleMusicTrackId_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.googleMusicTrackId_ = str;
      return str;
    }

    public String getRecordLabel()
    {
      Object localObject = this.recordLabel_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.recordLabel_ = str;
      return str;
    }

    public int getSerializedSize()
    {
      int i = this.memoizedSerializedSize;
      if (i != -1)
        return i;
      int j = 0x1 & this.bitField0_;
      int k = 0;
      if (j == 1)
        k = 0 + CodedOutputStream.computeBytesSize(1, getArtistBytes());
      if ((0x2 & this.bitField0_) == 2)
        k += CodedOutputStream.computeBytesSize(2, getTrackBytes());
      if ((0x4 & this.bitField0_) == 4)
        k += CodedOutputStream.computeBytesSize(3, getAlbumBytes());
      if ((0x8 & this.bitField0_) == 8)
        k += CodedOutputStream.computeBytesSize(4, getArtworkUrlBytes());
      if ((0x10 & this.bitField0_) == 16)
        k += CodedOutputStream.computeBytesSize(5, getGoogleMusicTrackIdBytes());
      if ((0x20 & this.bitField0_) == 32)
        k += CodedOutputStream.computeBytesSize(6, getGoogleMusicAlbumIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        k += CodedOutputStream.computeBytesSize(7, getRecordLabelBytes());
      this.memoizedSerializedSize = k;
      return k;
    }

    public String getTrack()
    {
      Object localObject = this.track_;
      if ((localObject instanceof String))
        return (String)localObject;
      ByteString localByteString = (ByteString)localObject;
      String str = localByteString.toStringUtf8();
      if (Internal.isValidUtf8(localByteString))
        this.track_ = str;
      return str;
    }

    public boolean hasAlbum()
    {
      return (0x4 & this.bitField0_) == 4;
    }

    public boolean hasArtist()
    {
      return (0x1 & this.bitField0_) == 1;
    }

    public boolean hasArtworkUrl()
    {
      return (0x8 & this.bitField0_) == 8;
    }

    public boolean hasGoogleMusicAlbumId()
    {
      return (0x20 & this.bitField0_) == 32;
    }

    public boolean hasGoogleMusicTrackId()
    {
      return (0x10 & this.bitField0_) == 16;
    }

    public boolean hasRecordLabel()
    {
      return (0x40 & this.bitField0_) == 64;
    }

    public boolean hasTrack()
    {
      return (0x2 & this.bitField0_) == 2;
    }

    public final boolean isInitialized()
    {
      int i = this.memoizedIsInitialized;
      if (i != -1)
        return i == 1;
      this.memoizedIsInitialized = 1;
      return true;
    }

    public Builder newBuilderForType()
    {
      return newBuilder();
    }

    public Builder toBuilder()
    {
      return newBuilder(this);
    }

    protected Object writeReplace()
      throws ObjectStreamException
    {
      return super.writeReplace();
    }

    public void writeTo(CodedOutputStream paramCodedOutputStream)
      throws IOException
    {
      getSerializedSize();
      if ((0x1 & this.bitField0_) == 1)
        paramCodedOutputStream.writeBytes(1, getArtistBytes());
      if ((0x2 & this.bitField0_) == 2)
        paramCodedOutputStream.writeBytes(2, getTrackBytes());
      if ((0x4 & this.bitField0_) == 4)
        paramCodedOutputStream.writeBytes(3, getAlbumBytes());
      if ((0x8 & this.bitField0_) == 8)
        paramCodedOutputStream.writeBytes(4, getArtworkUrlBytes());
      if ((0x10 & this.bitField0_) == 16)
        paramCodedOutputStream.writeBytes(5, getGoogleMusicTrackIdBytes());
      if ((0x20 & this.bitField0_) == 32)
        paramCodedOutputStream.writeBytes(6, getGoogleMusicAlbumIdBytes());
      if ((0x40 & this.bitField0_) == 64)
        paramCodedOutputStream.writeBytes(7, getRecordLabelBytes());
    }

    public static final class Builder extends GeneratedMessageLite.Builder<MusicInformationProtos.MusicInformation, Builder>
      implements MusicInformationProtos.MusicInformationOrBuilder
    {
      private Object album_ = "";
      private Object artist_ = "";
      private Object artworkUrl_ = "";
      private int bitField0_;
      private Object googleMusicAlbumId_ = "";
      private Object googleMusicTrackId_ = "";
      private Object recordLabel_ = "";
      private Object track_ = "";

      private Builder()
      {
        maybeForceBuilderInitialization();
      }

      private MusicInformationProtos.MusicInformation buildParsed()
        throws InvalidProtocolBufferException
      {
        MusicInformationProtos.MusicInformation localMusicInformation = buildPartial();
        if (!localMusicInformation.isInitialized())
          throw newUninitializedMessageException(localMusicInformation).asInvalidProtocolBufferException();
        return localMusicInformation;
      }

      private static Builder create()
      {
        return new Builder();
      }

      private void maybeForceBuilderInitialization()
      {
      }

      public MusicInformationProtos.MusicInformation build()
      {
        MusicInformationProtos.MusicInformation localMusicInformation = buildPartial();
        if (!localMusicInformation.isInitialized())
          throw newUninitializedMessageException(localMusicInformation);
        return localMusicInformation;
      }

      public MusicInformationProtos.MusicInformation buildPartial()
      {
        int i = 1;
        MusicInformationProtos.MusicInformation localMusicInformation = new MusicInformationProtos.MusicInformation(this, null);
        int j = this.bitField0_;
        if ((j & 0x1) == i);
        while (true)
        {
          MusicInformationProtos.MusicInformation.access$302(localMusicInformation, this.artist_);
          if ((j & 0x2) == 2)
            i |= 2;
          MusicInformationProtos.MusicInformation.access$402(localMusicInformation, this.track_);
          if ((j & 0x4) == 4)
            i |= 4;
          MusicInformationProtos.MusicInformation.access$502(localMusicInformation, this.album_);
          if ((j & 0x8) == 8)
            i |= 8;
          MusicInformationProtos.MusicInformation.access$602(localMusicInformation, this.artworkUrl_);
          if ((j & 0x10) == 16)
            i |= 16;
          MusicInformationProtos.MusicInformation.access$702(localMusicInformation, this.googleMusicTrackId_);
          if ((j & 0x20) == 32)
            i |= 32;
          MusicInformationProtos.MusicInformation.access$802(localMusicInformation, this.googleMusicAlbumId_);
          if ((j & 0x40) == 64)
            i |= 64;
          MusicInformationProtos.MusicInformation.access$902(localMusicInformation, this.recordLabel_);
          MusicInformationProtos.MusicInformation.access$1002(localMusicInformation, i);
          return localMusicInformation;
          i = 0;
        }
      }

      public Builder clear()
      {
        super.clear();
        this.artist_ = "";
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.track_ = "";
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.album_ = "";
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.artworkUrl_ = "";
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.googleMusicTrackId_ = "";
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.googleMusicAlbumId_ = "";
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.recordLabel_ = "";
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        return this;
      }

      public Builder clearAlbum()
      {
        this.bitField0_ = (0xFFFFFFFB & this.bitField0_);
        this.album_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getAlbum();
        return this;
      }

      public Builder clearArtist()
      {
        this.bitField0_ = (0xFFFFFFFE & this.bitField0_);
        this.artist_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getArtist();
        return this;
      }

      public Builder clearArtworkUrl()
      {
        this.bitField0_ = (0xFFFFFFF7 & this.bitField0_);
        this.artworkUrl_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getArtworkUrl();
        return this;
      }

      public Builder clearGoogleMusicAlbumId()
      {
        this.bitField0_ = (0xFFFFFFDF & this.bitField0_);
        this.googleMusicAlbumId_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getGoogleMusicAlbumId();
        return this;
      }

      public Builder clearGoogleMusicTrackId()
      {
        this.bitField0_ = (0xFFFFFFEF & this.bitField0_);
        this.googleMusicTrackId_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getGoogleMusicTrackId();
        return this;
      }

      public Builder clearRecordLabel()
      {
        this.bitField0_ = (0xFFFFFFBF & this.bitField0_);
        this.recordLabel_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getRecordLabel();
        return this;
      }

      public Builder clearTrack()
      {
        this.bitField0_ = (0xFFFFFFFD & this.bitField0_);
        this.track_ = MusicInformationProtos.MusicInformation.getDefaultInstance().getTrack();
        return this;
      }

      public Builder clone()
      {
        return create().mergeFrom(buildPartial());
      }

      public String getAlbum()
      {
        Object localObject = this.album_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.album_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getArtist()
      {
        Object localObject = this.artist_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.artist_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getArtworkUrl()
      {
        Object localObject = this.artworkUrl_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.artworkUrl_ = str;
          return str;
        }
        return (String)localObject;
      }

      public MusicInformationProtos.MusicInformation getDefaultInstanceForType()
      {
        return MusicInformationProtos.MusicInformation.getDefaultInstance();
      }

      public String getGoogleMusicAlbumId()
      {
        Object localObject = this.googleMusicAlbumId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.googleMusicAlbumId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getGoogleMusicTrackId()
      {
        Object localObject = this.googleMusicTrackId_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.googleMusicTrackId_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getRecordLabel()
      {
        Object localObject = this.recordLabel_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.recordLabel_ = str;
          return str;
        }
        return (String)localObject;
      }

      public String getTrack()
      {
        Object localObject = this.track_;
        if (!(localObject instanceof String))
        {
          String str = ((ByteString)localObject).toStringUtf8();
          this.track_ = str;
          return str;
        }
        return (String)localObject;
      }

      public boolean hasAlbum()
      {
        return (0x4 & this.bitField0_) == 4;
      }

      public boolean hasArtist()
      {
        return (0x1 & this.bitField0_) == 1;
      }

      public boolean hasArtworkUrl()
      {
        return (0x8 & this.bitField0_) == 8;
      }

      public boolean hasGoogleMusicAlbumId()
      {
        return (0x20 & this.bitField0_) == 32;
      }

      public boolean hasGoogleMusicTrackId()
      {
        return (0x10 & this.bitField0_) == 16;
      }

      public boolean hasRecordLabel()
      {
        return (0x40 & this.bitField0_) == 64;
      }

      public boolean hasTrack()
      {
        return (0x2 & this.bitField0_) == 2;
      }

      public final boolean isInitialized()
      {
        return true;
      }

      public Builder mergeFrom(MusicInformationProtos.MusicInformation paramMusicInformation)
      {
        if (paramMusicInformation == MusicInformationProtos.MusicInformation.getDefaultInstance());
        do
        {
          return this;
          if (paramMusicInformation.hasArtist())
            setArtist(paramMusicInformation.getArtist());
          if (paramMusicInformation.hasTrack())
            setTrack(paramMusicInformation.getTrack());
          if (paramMusicInformation.hasAlbum())
            setAlbum(paramMusicInformation.getAlbum());
          if (paramMusicInformation.hasArtworkUrl())
            setArtworkUrl(paramMusicInformation.getArtworkUrl());
          if (paramMusicInformation.hasGoogleMusicTrackId())
            setGoogleMusicTrackId(paramMusicInformation.getGoogleMusicTrackId());
          if (paramMusicInformation.hasGoogleMusicAlbumId())
            setGoogleMusicAlbumId(paramMusicInformation.getGoogleMusicAlbumId());
        }
        while (!paramMusicInformation.hasRecordLabel());
        setRecordLabel(paramMusicInformation.getRecordLabel());
        return this;
      }

      public Builder mergeFrom(CodedInputStream paramCodedInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
        throws IOException
      {
        while (true)
        {
          int i = paramCodedInputStream.readTag();
          switch (i)
          {
          default:
            if (parseUnknownField(paramCodedInputStream, paramExtensionRegistryLite, i))
              continue;
          case 0:
            return this;
          case 10:
            this.bitField0_ = (0x1 | this.bitField0_);
            this.artist_ = paramCodedInputStream.readBytes();
            break;
          case 18:
            this.bitField0_ = (0x2 | this.bitField0_);
            this.track_ = paramCodedInputStream.readBytes();
            break;
          case 26:
            this.bitField0_ = (0x4 | this.bitField0_);
            this.album_ = paramCodedInputStream.readBytes();
            break;
          case 34:
            this.bitField0_ = (0x8 | this.bitField0_);
            this.artworkUrl_ = paramCodedInputStream.readBytes();
            break;
          case 42:
            this.bitField0_ = (0x10 | this.bitField0_);
            this.googleMusicTrackId_ = paramCodedInputStream.readBytes();
            break;
          case 50:
            this.bitField0_ = (0x20 | this.bitField0_);
            this.googleMusicAlbumId_ = paramCodedInputStream.readBytes();
            break;
          case 58:
          }
          this.bitField0_ = (0x40 | this.bitField0_);
          this.recordLabel_ = paramCodedInputStream.readBytes();
        }
      }

      public Builder setAlbum(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x4 | this.bitField0_);
        this.album_ = paramString;
        return this;
      }

      void setAlbum(ByteString paramByteString)
      {
        this.bitField0_ = (0x4 | this.bitField0_);
        this.album_ = paramByteString;
      }

      public Builder setArtist(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x1 | this.bitField0_);
        this.artist_ = paramString;
        return this;
      }

      void setArtist(ByteString paramByteString)
      {
        this.bitField0_ = (0x1 | this.bitField0_);
        this.artist_ = paramByteString;
      }

      public Builder setArtworkUrl(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x8 | this.bitField0_);
        this.artworkUrl_ = paramString;
        return this;
      }

      void setArtworkUrl(ByteString paramByteString)
      {
        this.bitField0_ = (0x8 | this.bitField0_);
        this.artworkUrl_ = paramByteString;
      }

      public Builder setGoogleMusicAlbumId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x20 | this.bitField0_);
        this.googleMusicAlbumId_ = paramString;
        return this;
      }

      void setGoogleMusicAlbumId(ByteString paramByteString)
      {
        this.bitField0_ = (0x20 | this.bitField0_);
        this.googleMusicAlbumId_ = paramByteString;
      }

      public Builder setGoogleMusicTrackId(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x10 | this.bitField0_);
        this.googleMusicTrackId_ = paramString;
        return this;
      }

      void setGoogleMusicTrackId(ByteString paramByteString)
      {
        this.bitField0_ = (0x10 | this.bitField0_);
        this.googleMusicTrackId_ = paramByteString;
      }

      public Builder setRecordLabel(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x40 | this.bitField0_);
        this.recordLabel_ = paramString;
        return this;
      }

      void setRecordLabel(ByteString paramByteString)
      {
        this.bitField0_ = (0x40 | this.bitField0_);
        this.recordLabel_ = paramByteString;
      }

      public Builder setTrack(String paramString)
      {
        if (paramString == null)
          throw new NullPointerException();
        this.bitField0_ = (0x2 | this.bitField0_);
        this.track_ = paramString;
        return this;
      }

      void setTrack(ByteString paramByteString)
      {
        this.bitField0_ = (0x2 | this.bitField0_);
        this.track_ = paramByteString;
      }
    }
  }

  public static abstract interface MusicInformationOrBuilder extends MessageLiteOrBuilder
  {
    public abstract String getAlbum();

    public abstract String getArtist();

    public abstract String getArtworkUrl();

    public abstract String getGoogleMusicAlbumId();

    public abstract String getGoogleMusicTrackId();

    public abstract String getRecordLabel();

    public abstract String getTrack();

    public abstract boolean hasAlbum();

    public abstract boolean hasArtist();

    public abstract boolean hasArtworkUrl();

    public abstract boolean hasGoogleMusicAlbumId();

    public abstract boolean hasGoogleMusicTrackId();

    public abstract boolean hasRecordLabel();

    public abstract boolean hasTrack();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.goggles.MusicInformationProtos
 * JD-Core Version:    0.6.2
 */