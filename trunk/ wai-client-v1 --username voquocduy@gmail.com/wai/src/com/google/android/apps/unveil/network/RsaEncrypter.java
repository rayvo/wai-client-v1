package com.google.android.apps.unveil.network;

import com.google.android.apps.unveil.env.UnveilLogger;
import java.math.BigInteger;
import java.security.GeneralSecurityException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

public class RsaEncrypter
{
  private static final String CRYPT_ALGORITHM = "RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING";
  public static final int DEFAULT_PRELOAD_DELAY_SECONDS = 5;
  private static final String DIGEST_ALGORITHM = "SHA-1";
  private static final String KEY_ALG = "RSA";
  private static final int KEY_HASH_LENGTH = 4;
  private static final byte VERSION;
  private static final UnveilLogger logger = new UnveilLogger();
  private final byte[] hashValue;
  private final RSAPublicKey publicKey;

  public RsaEncrypter(byte[] paramArrayOfByte)
    throws GeneralSecurityException
  {
    X509EncodedKeySpec localX509EncodedKeySpec = new X509EncodedKeySpec(paramArrayOfByte);
    this.publicKey = ((RSAPublicKey)KeyFactory.getInstance("RSA").generatePublic(localX509EncodedKeySpec));
    this.hashValue = new byte[4];
    byte[][] arrayOfByte = new byte[2][];
    arrayOfByte[0] = this.publicKey.getModulus().toByteArray();
    arrayOfByte[1] = this.publicKey.getPublicExponent().toByteArray();
    System.arraycopy(hashValues(arrayOfByte), 0, this.hashValue, 0, 4);
  }

  private static byte[] fromInt(int paramInt)
  {
    byte[] arrayOfByte = new byte[4];
    arrayOfByte[0] = ((byte)(paramInt >> 24));
    arrayOfByte[1] = ((byte)(paramInt >> 16));
    arrayOfByte[2] = ((byte)(paramInt >> 8));
    arrayOfByte[3] = ((byte)paramInt);
    return arrayOfByte;
  }

  private static byte[] hashValues(byte[][] paramArrayOfByte)
    throws GeneralSecurityException
  {
    MessageDigest localMessageDigest = MessageDigest.getInstance("SHA-1");
    int i = paramArrayOfByte.length;
    for (int j = 0; j < i; j++)
    {
      byte[] arrayOfByte = stripLeadingZeros(paramArrayOfByte[j]);
      localMessageDigest.update(fromInt(arrayOfByte.length));
      localMessageDigest.update(arrayOfByte);
    }
    return localMessageDigest.digest();
  }

  public static void prefetchEncryptor(int paramInt)
  {
    Runnable local1 = new Runnable()
    {
      public void run()
      {
        RsaEncrypter.logger.v("Preloading RSAEncrypter", new Object[0]);
        try
        {
          Cipher.getInstance("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING");
          return;
        }
        catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
        {
          localNoSuchAlgorithmException.printStackTrace();
          return;
        }
        catch (NoSuchPaddingException localNoSuchPaddingException)
        {
          localNoSuchPaddingException.printStackTrace();
        }
      }
    };
    logger.v("Scheduling preloading of RSAEncrypter", new Object[0]);
    new ScheduledThreadPoolExecutor(1).schedule(local1, paramInt, TimeUnit.SECONDS);
  }

  private static byte[] stripLeadingZeros(byte[] paramArrayOfByte)
  {
    for (int i = 0; (i < paramArrayOfByte.length) && (paramArrayOfByte[i] == 0); i++);
    if (i == 0)
      return paramArrayOfByte;
    byte[] arrayOfByte = new byte[paramArrayOfByte.length - i];
    System.arraycopy(paramArrayOfByte, i, arrayOfByte, 0, arrayOfByte.length);
    return arrayOfByte;
  }

  public byte[] encrypt(byte[] paramArrayOfByte)
    throws GeneralSecurityException
  {
    Cipher localCipher = Cipher.getInstance("RSA/ECB/OAEPWITHSHA1ANDMGF1PADDING");
    localCipher.init(1, this.publicKey);
    byte[] arrayOfByte1 = localCipher.doFinal(paramArrayOfByte);
    byte[] arrayOfByte2 = new byte[4 + (1 + arrayOfByte1.length)];
    int i = 0 + 1;
    arrayOfByte2[0] = 0;
    System.arraycopy(this.hashValue, 0, arrayOfByte2, i, 4);
    System.arraycopy(arrayOfByte1, 0, arrayOfByte2, i + 4, arrayOfByte1.length);
    return arrayOfByte2;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.RsaEncrypter
 * JD-Core Version:    0.6.2
 */