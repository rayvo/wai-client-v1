package com.google.android.apps.unveil.service;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.media.ImageLoader.Image;
import com.google.android.apps.unveil.env.media.ImageLoader.Policy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PathFilterPolicy
  implements ImageLoader.Policy<ImageLoader.Image>
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final Pattern acceptablePathsPattern;

  public PathFilterPolicy(Pattern paramPattern)
  {
    this.acceptablePathsPattern = paramPattern;
  }

  public List<ImageLoader.Image> apply(List<ImageLoader.Image> paramList)
  {
    ArrayList localArrayList = new ArrayList(paramList.size());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      ImageLoader.Image localImage = (ImageLoader.Image)localIterator.next();
      if (this.acceptablePathsPattern.matcher(localImage.path).find())
      {
        localArrayList.add(localImage);
      }
      else
      {
        UnveilLogger localUnveilLogger = logger;
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = localImage.path;
        localUnveilLogger.d("Ignoring image because its path does not match: %s", arrayOfObject);
      }
    }
    return localArrayList;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.service.PathFilterPolicy
 * JD-Core Version:    0.6.2
 */