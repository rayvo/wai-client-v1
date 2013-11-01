package com.google.android.apps.unveil.env.puggle;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.PictureFactory;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.PuggleQueryBuilder;
import com.google.goggles.RestrictsProtos.Restricts;
import com.google.goggles.RestrictsProtos.Restricts.Builder;
import com.google.protobuf.InvalidProtocolBufferException;

public class ParcelableQuery
  implements Parcelable
{
  public static final Parcelable.Creator<ParcelableQuery> CREATOR = new Parcelable.Creator()
  {
    public ParcelableQuery createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ParcelableQuery(paramAnonymousParcel, null);
    }

    public ParcelableQuery[] newArray(int paramAnonymousInt)
    {
      return new ParcelableQuery[paramAnonymousInt];
    }
  };
  private static final UnveilLogger logger = new UnveilLogger();
  private final Picture picture;
  private final RestrictsProtos.Restricts restricts;
  private final String text;

  private ParcelableQuery(Parcel paramParcel)
  {
    byte[] arrayOfByte1 = new byte[paramParcel.readInt()];
    paramParcel.readByteArray(arrayOfByte1);
    this.picture = PictureFactory.createJpeg(arrayOfByte1, paramParcel.readInt());
    this.text = paramParcel.readString();
    byte[] arrayOfByte2 = new byte[paramParcel.readInt()];
    paramParcel.readByteArray(arrayOfByte2);
    try
    {
      RestrictsProtos.Restricts localRestricts2 = RestrictsProtos.Restricts.parseFrom(arrayOfByte2);
      localRestricts1 = localRestricts2;
      if (localRestricts1 == null)
      {
        this.restricts = RestrictsProtos.Restricts.newBuilder().build();
        return;
      }
    }
    catch (InvalidProtocolBufferException localInvalidProtocolBufferException)
    {
      RestrictsProtos.Restricts localRestricts1;
      while (true)
      {
        logger.e("Failed to parse: %s", new Object[] { arrayOfByte2 });
        localRestricts1 = null;
      }
      this.restricts = localRestricts1;
    }
  }

  public ParcelableQuery(String paramString, Picture paramPicture, RestrictsProtos.Restricts paramRestricts)
  {
    this.text = paramString;
    this.picture = paramPicture;
    if (paramRestricts == null)
    {
      this.restricts = RestrictsProtos.Restricts.newBuilder().build();
      return;
    }
    this.restricts = paramRestricts;
  }

  public int describeContents()
  {
    return 0;
  }

  public Picture getCroppedPicture()
  {
    return PictureFactory.createJpeg(this.picture).getCroppedPicture();
  }

  public Picture getPicture()
  {
    return this.picture;
  }

  public RestrictsProtos.Restricts getRestricts()
  {
    return this.restricts;
  }

  public String getText()
  {
    return this.text;
  }

  public PuggleQueryBuilder toPuggleQueryBuilder()
  {
    return new PuggleQueryBuilder().addText(this.text);
  }

  public String toString()
  {
    return this.text;
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeInt(this.picture.getJpegData().length);
    paramParcel.writeByteArray(this.picture.getJpegData());
    paramParcel.writeInt(this.picture.getOrientation());
    paramParcel.writeString(this.text);
    byte[] arrayOfByte = this.restricts.toByteArray();
    paramParcel.writeInt(arrayOfByte.length);
    paramParcel.writeByteArray(arrayOfByte);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.puggle.ParcelableQuery
 * JD-Core Version:    0.6.2
 */