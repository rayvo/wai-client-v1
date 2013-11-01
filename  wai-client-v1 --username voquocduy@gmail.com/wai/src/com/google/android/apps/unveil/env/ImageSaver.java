package com.google.android.apps.unveil.env;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore.Images.Media;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImageSaver
{
  public static final String DESCRIPTION = "Goggles-generated Image";
  private static final String FOLDER_NAME = "Goggles";
  private static final String JPEG_EXTENSION = ".jpg";
  public static final String MIME_TYPE = "image/jpeg";
  private static final String SLASH = "/";
  private final ContentResolver contentResolver;
  private boolean gogglesDirectoryExists;
  private final UnveilLogger logger = new UnveilLogger();

  public ImageSaver(ContentResolver paramContentResolver)
  {
    this.contentResolver = paramContentResolver;
  }

  private void createDirectoryIfNotPresent()
  {
    try
    {
      boolean bool = this.gogglesDirectoryExists;
      if (bool);
      while (true)
      {
        return;
        File localFile = new File(Environment.getExternalStorageDirectory().toString() + "/" + "Goggles");
        if (!localFile.exists())
          localFile.mkdir();
        this.gogglesDirectoryExists = true;
      }
    }
    finally
    {
    }
  }

  private String generateFilename()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(Environment.getExternalStorageDirectory().toString());
    localStringBuilder.append("/");
    localStringBuilder.append("Goggles");
    localStringBuilder.append("/");
    localStringBuilder.append("IMG_");
    localStringBuilder.append(System.currentTimeMillis());
    localStringBuilder.append(".jpg");
    return localStringBuilder.toString();
  }

  public static Uri saveImageToMediaStore(ContentResolver paramContentResolver, byte[] paramArrayOfByte)
  {
    String str = MediaStore.Images.Media.insertImage(paramContentResolver, BitmapFactory.decodeByteArray(paramArrayOfByte, 0, paramArrayOfByte.length), "", "Goggles-generated Image");
    if (str == null)
      return null;
    return Uri.parse(str);
  }

  private File saveToSdCard(byte[] paramArrayOfByte)
  {
    createDirectoryIfNotPresent();
    File localFile = new File(generateFilename());
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile);
      localFileOutputStream.write(paramArrayOfByte);
      localFileOutputStream.close();
      return localFile;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      this.logger.e(localFileNotFoundException, "Could not save image to SD card; the SD card is probably mounted.", new Object[0]);
      return null;
    }
    catch (IOException localIOException)
    {
      this.logger.e(localIOException, "Could not save image to SD card; reason unknown.", new Object[0]);
    }
    return null;
  }

  public Uri saveToGallery(byte[] paramArrayOfByte)
  {
    File localFile = saveToSdCard(paramArrayOfByte);
    if (localFile == null)
      return null;
    ContentValues localContentValues = new ContentValues();
    Uri localUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
    localContentValues.put("date_added", Long.valueOf(System.currentTimeMillis() / 1000L));
    localContentValues.put("datetaken", Long.valueOf(System.currentTimeMillis()));
    try
    {
      String str2 = localFile.getCanonicalPath();
      str1 = str2;
      localContentValues.put("title", localFile.getName());
      localContentValues.put("_display_name", localFile.getName());
      localContentValues.put("mime_type", "image/jpeg");
      localContentValues.put("_data", str1);
      localContentValues.put("_size", Long.valueOf(localFile.length()));
      localContentValues.put("description", "Goggles-generated Image");
      return this.contentResolver.insert(localUri, localContentValues);
    }
    catch (IOException localIOException)
    {
      while (true)
      {
        localIOException.printStackTrace();
        String str1 = null;
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.ImageSaver
 * JD-Core Version:    0.6.2
 */