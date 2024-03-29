package com.google.userfeedback.android.api;

public class ProductSpecificBinaryDataHolder
{
  private byte[] mData;
  private String mMimeType;
  private String mName;

  ProductSpecificBinaryDataHolder(String paramString1, String paramString2, byte[] paramArrayOfByte)
  {
    this.mName = paramString1;
    this.mMimeType = paramString2;
    this.mData = paramArrayOfByte;
  }

  public byte[] getData()
  {
    return this.mData;
  }

  public String getMimeType()
  {
    return this.mMimeType;
  }

  public String getName()
  {
    return this.mName;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.userfeedback.android.api.ProductSpecificBinaryDataHolder
 * JD-Core Version:    0.6.2
 */