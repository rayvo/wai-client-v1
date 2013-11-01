package com.google.android.apps.unveil.env;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Build;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ResourceUtils
{
  private static final String ABI_ARMV7 = "armeabi-v7a";
  private static final String ABI_ARMV7_EXTENSION = "-v7a";
  public static final String ASSETS_PREFIX = "assets:";
  public static final String LOCAL_FILESYSTEM_PREFIX = "sdcard:";
  private static final UnveilLogger LOGGER = new UnveilLogger();
  private static final String MARKET_EXTENSION = "-new";

  public static void closeStream(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException localIOException)
    {
      LOGGER.e(localIOException, "Failed to close stream", new Object[0]);
    }
  }

  public static boolean fromAssets(String paramString)
  {
    return paramString.startsWith("assets:");
  }

  private static void getDirectoryContentsFromAssets(AssetManager paramAssetManager, ArrayList<String> paramArrayList, String paramString, FilenameFilter paramFilenameFilter, boolean paramBoolean)
    throws IOException
  {
    String[] arrayOfString = paramAssetManager.list(paramString);
    int i = arrayOfString.length;
    int j = 0;
    if (j < i)
    {
      String str1 = arrayOfString[j];
      String str2 = paramString + "/" + str1;
      if (new File(str2).isDirectory())
        if (paramBoolean)
          getDirectoryContentsFromAssets(paramAssetManager, paramArrayList, str2, paramFilenameFilter, true);
      while (true)
      {
        j++;
        break;
        if ((paramFilenameFilter == null) || (paramFilenameFilter.accept(new File(paramString), str1)))
          paramArrayList.add(str2);
      }
    }
  }

  private static void getDirectoryContentsFromDisk(List<String> paramList, File paramFile, FilenameFilter paramFilenameFilter, boolean paramBoolean)
  {
    File[] arrayOfFile = paramFile.listFiles();
    int i = arrayOfFile.length;
    int j = 0;
    if (j < i)
    {
      File localFile = arrayOfFile[j];
      if (localFile.isDirectory())
        if (paramBoolean)
          getDirectoryContentsFromDisk(paramList, localFile, paramFilenameFilter, true);
      while (true)
      {
        j++;
        break;
        if ((paramFilenameFilter == null) || (paramFilenameFilter.accept(paramFile, localFile.getName())))
          paramList.add(localFile.getAbsolutePath());
      }
    }
  }

  public static InputStream getInputStreamForFile(Resources paramResources, boolean paramBoolean, String paramString)
    throws IOException
  {
    if (paramBoolean);
    UnveilLogger localUnveilLogger;
    Object[] arrayOfObject;
    try
    {
      return paramResources.getAssets().open(paramString);
      FileInputStream localFileInputStream = new FileInputStream(paramString);
      return localFileInputStream;
    }
    catch (IOException localIOException)
    {
      localUnveilLogger = LOGGER;
      arrayOfObject = new Object[2];
      if (!paramBoolean);
    }
    for (String str = " (from assets)"; ; str = "")
    {
      arrayOfObject[0] = str;
      arrayOfObject[1] = paramString;
      localUnveilLogger.w("Exception reading file%s: %s", arrayOfObject);
      throw localIOException;
    }
  }

  public static String getPathFromPrefixedString(String paramString)
  {
    String[] arrayOfString = paramString.split(":");
    if (arrayOfString.length < 2)
      throw new IllegalArgumentException("Can not extract path from: " + paramString);
    return arrayOfString[1];
  }

  public static String[] listFiles(Resources paramResources, boolean paramBoolean, String paramString, FilenameFilter paramFilenameFilter)
    throws IOException
  {
    return listFiles(paramResources, paramBoolean, paramString, paramFilenameFilter, true);
  }

  public static String[] listFiles(Resources paramResources, boolean paramBoolean1, String paramString, FilenameFilter paramFilenameFilter, boolean paramBoolean2)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    if (paramBoolean1)
      getDirectoryContentsFromAssets(paramResources.getAssets(), localArrayList, paramString, paramFilenameFilter, paramBoolean2);
    while (true)
    {
      return (String[])localArrayList.toArray(new String[1]);
      getDirectoryContentsFromDisk(localArrayList, new File(paramString), paramFilenameFilter, paramBoolean2);
    }
  }

  public static void loadNativeLibrary(String paramString)
  {
    String str;
    if (useMarketLibraries())
      str = paramString + "-new";
    while (true)
    {
      if (Build.CPU_ABI.equals("armeabi-v7a"))
        str = str + "-v7a";
      try
      {
        System.loadLibrary(str);
        return;
        str = paramString;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError)
      {
        LOGGER.e("Error loading native library '%s'.", new Object[] { str });
      }
    }
  }

  public static String readTxtFileFromResource(Resources paramResources, int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(paramResources.openRawResource(paramInt)));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          break;
        localStringBuilder.append(str);
        localStringBuilder.append('\n');
      }
    }
    catch (IOException localIOException)
    {
      LOGGER.e("Error reading input ", new Object[0]);
    }
    return localStringBuilder.toString();
  }

  private static boolean useMarketLibraries()
  {
    return true;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.ResourceUtils
 * JD-Core Version:    0.6.2
 */