package com.google.android.apps.unveil.network;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.LatLngProtos.LatLng;
import com.google.protobuf.ByteString;
import java.security.GeneralSecurityException;

public class LatLngEncrypter
{
  private RsaEncrypter encrypter;
  private final UnveilLogger logger = new UnveilLogger();

  public LatLngEncrypter(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      return;
    try
    {
      this.encrypter = new RsaEncrypter(paramArrayOfByte);
      return;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      this.logger.e(localGeneralSecurityException, "Failed to create encrypter.", new Object[0]);
    }
  }

  public ByteString encryptLatLng(LatLngProtos.LatLng paramLatLng)
  {
    if (paramLatLng == null);
    while (this.encrypter == null)
      return null;
    try
    {
      ByteString localByteString = ByteString.copyFrom(this.encrypter.encrypt(paramLatLng.toByteArray()));
      return localByteString;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
    }
    return null;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.LatLngEncrypter
 * JD-Core Version:    0.6.2
 */