package com.google.android.apps.unveil.barcode;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

import android.graphics.Rect;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.apps.unveil.env.GeometryUtils;
import com.google.android.apps.unveil.env.Size;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.Viewport;
import com.google.goggles.ClientAnnotationProtos;
import com.google.goggles.ClientAnnotationProtos.ClientBarcode.BarcodeFormat;
import com.google.goggles.ClientAnnotationProtos.ClientBarcode.BarcodeType;

public class Barcode
  implements Parcelable, Serializable
{
  public static final Parcelable.Creator<Barcode> CREATOR = new Parcelable.Creator()
  {
    public Barcode createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Barcode(paramAnonymousParcel);
    }

    public Barcode[] newArray(int paramAnonymousInt)
    {
      return new Barcode[paramAnonymousInt];
    }
  };
  private static final UnveilLogger logger = new UnveilLogger();
  public static final long serialVersionUID = -957171312096573499L;
  private Rect boundingBox;
  private String format;
  private String type;
  private String urlString;
  private String value;

  public Barcode(Parcel paramParcel)
  {
    this.type = paramParcel.readString();
    this.format = paramParcel.readString();
    this.value = paramParcel.readString();
    this.boundingBox = ((Rect)paramParcel.readParcelable(Rect.class.getClassLoader()));
  }

  public Barcode(ParsedResultType paramParsedResultType, BarcodeFormat paramBarcodeFormat, String paramString, Rect paramRect)
  {
    this.type = paramParsedResultType.toString();
    this.format = paramBarcodeFormat.getName();
    this.value = paramString;
    this.boundingBox = paramRect;
  }

  private BarcodeFormat getProtoBarcodeFormat()
  {
    if (this.format.equals("QR_CODE"))
      return BarcodeFormat.valueOf(BarcodeFormat.QR_CODE_VALUE);
    if (this.format.equals("DATAMATRIX"))
      return BarcodeFormat.valueOf(BarcodeFormat.DATAMATRIX_VALUE);
    if (this.format.equals("UPC_E"))
      return BarcodeFormat.valueOf(BarcodeFormat.UPC_E_VALUE);
    if (this.format.equals("UPC_A"))
      return BarcodeFormat.valueOf(BarcodeFormat.UPC_A_VALUE);
    if (this.format.equals("EAN_8"))
      return BarcodeFormat.valueOf(BarcodeFormat.EAN_8_VALUE);
    if (this.format.equals("EAN_13"))
      return BarcodeFormat.valueOf(BarcodeFormat.EAN_13_VALUE);
    if (this.format.equals("CODE_128"))
      return BarcodeFormat.valueOf(BarcodeFormat.CODE_128_VALUE);
    if (this.format.equals("CODE_39"))
      return BarcodeFormat.valueOf(BarcodeFormat.CODE_39_VALUE);
    if (this.format.equals("ITF"))
      return BarcodeFormat.valueOf(BarcodeFormat.ITF_VALUE);
    if (this.format.equals("PDF417"))
      return BarcodeFormat.valueOf(BarcodeFormat.PDF417_VALUE);
    return BarcodeFormat.valueOf(BarcodeFormat.OTHER_FORMAT_VALUE);
  }

  private BarcodeType getProtoBarcodeType()
  {
    if (this.type.equals(ParsedResultType.ADDRESSBOOK.toString()))
      return BarcodeType.ADDRESS_BOOK_VALUE;
    if (this.type.equals(ParsedResultType.EMAIL_ADDRESS.toString()))
      return BarcodeType.EMAIL_ADDRESS_VALUE;
    if (this.type.equals(ParsedResultType.PRODUCT.toString()))
      return BarcodeType.PRODUCT_VALUE;
    if (this.type.equals(ParsedResultType.URI.toString()))
      return BarcodeType.URI_VALUE;
    if (this.type.equals(ParsedResultType.TEXT.toString()))
      return BarcodeType.TEXT_VALUE;
    if (this.type.equals(ParsedResultType.TEL.toString()))
      return BarcodeType.TEL_VALUE;
    if (this.type.equals(ParsedResultType.SMS.toString()))
      return BarcodeType.SMS_VALUE;
    if (this.type.equals(ParsedResultType.ISBN.toString()))
      return BarcodeType.ISBN_VALUE;
    return BarcodeType.OTHER_TYPE_VALUE);
  }

  public static Barcode parseFrom(byte[] paramArrayOfByte)
  {
    try
    {
      Barcode localBarcode = (Barcode)new ObjectInputStream(new ByteArrayInputStream(paramArrayOfByte)).readObject();
      return localBarcode;
    }
    catch (IOException localIOException)
    {
      logger.e(localIOException, "Could not deserialize a Barcode.", new Object[0]);
      return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      logger.e(localClassNotFoundException, "Could not deserialize a Barcode.", new Object[0]);
    }
    return null;
  }

  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    this.type = ((String)paramObjectInputStream.readObject());
    this.format = ((String)paramObjectInputStream.readObject());
    this.value = ((String)paramObjectInputStream.readObject());
    this.boundingBox = readRect(paramObjectInputStream);
  }

  private static final Rect readRect(ObjectInputStream paramObjectInputStream)
    throws IOException
  {
    return new Rect(paramObjectInputStream.readInt(), paramObjectInputStream.readInt(), paramObjectInputStream.readInt(), paramObjectInputStream.readInt());
  }

  private ClientAnnotationProtos.ClientAnnotation.Builder toClientAnnotationInner(Rect paramRect)
  {
    ClientAnnotationProtos.ClientAnnotation.Builder localBuilder = ClientAnnotationProtos.ClientAnnotation.newBuilder();
    localBuilder.setBoundingBox(GeometryUtils.toBoundingBox(paramRect));
    ClientAnnotationProtos.ClientBarcode.Builder localBuilder1 = ClientAnnotationProtos.ClientBarcode.newBuilder();
    localBuilder1.setType(getProtoBarcodeType());
    localBuilder1.setFormat(getProtoBarcodeFormat());
    localBuilder1.setValue(this.value);
    return localBuilder.setBarcode(localBuilder1);
  }

  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.writeObject(this.type);
    paramObjectOutputStream.writeObject(this.format);
    paramObjectOutputStream.writeObject(this.value);
    paramObjectOutputStream.writeInt(this.boundingBox.left);
    paramObjectOutputStream.writeInt(this.boundingBox.top);
    paramObjectOutputStream.writeInt(this.boundingBox.right);
    paramObjectOutputStream.writeInt(this.boundingBox.bottom);
  }

  public int describeContents()
  {
    return 0;
  }

  public boolean equals(Object paramObject)
  {
    if (this == paramObject);
    Barcode localBarcode;
    do
    {
      do
      {
        return true;
        if (paramObject == null)
          return false;
        if (getClass() != paramObject.getClass())
          return false;
        localBarcode = (Barcode)paramObject;
        if (this.value != null)
          break;
      }
      while (localBarcode.value == null);
      return false;
    }
    while (this.value.equals(localBarcode.value));
    return false;
  }

  public Rect getBoundingBox()
  {
    return this.boundingBox;
  }

  public String getFormat()
  {
    return this.format;
  }

  public String getType()
  {
    return this.type;
  }

  public String getUrl()
  {
    if (this.urlString == null);
    try
    {
      URL localURL = new URL(this.value);
      String str = localURL.getProtocol();
      if (("http".equals(str)) || ("https".equals(str)));
      for (this.urlString = localURL.toString(); ; this.urlString = "")
        return this.urlString;
    }
    catch (MalformedURLException localMalformedURLException)
    {
      while (true)
        this.urlString = "";
    }
  }

  public String getValue()
  {
    return this.value;
  }

  public int hashCode()
  {
    return this.value.hashCode();
  }

  public byte[] toByteArray()
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      new ObjectOutputStream(localByteArrayOutputStream).writeObject(this);
      byte[] arrayOfByte = localByteArrayOutputStream.toByteArray();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      logger.e(localIOException, "Could not serialize %s", new Object[] { this });
    }
    return null;
  }

  public ClientAnnotationProtos.ClientAnnotation.Builder toClientAnnotation()
  {
    return toClientAnnotationInner(this.boundingBox);
  }

  public ClientAnnotationProtos.ClientAnnotation.Builder toRotatedClientAnnotation(Size paramSize, int paramInt, Viewport paramViewport)
  {
    Rect localRect = new Rect(this.boundingBox);
    if ((paramSize != null) && (paramViewport != null))
      localRect = paramViewport.rotateAndScaleBarcodeBox(this.boundingBox, paramInt, paramSize);
    return toClientAnnotationInner(localRect);
  }

  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Barcode [type=").append(this.type).append(", format=").append(this.format).append(", value=").append(this.value).append(", boundingBox=").append(this.boundingBox).append("]");
    return localStringBuilder.toString();
  }

  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.type);
    paramParcel.writeString(this.format);
    paramParcel.writeString(this.value);
    paramParcel.writeParcelable(this.boundingBox, 0);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.barcode.Barcode
 * JD-Core Version:    0.6.2
 */