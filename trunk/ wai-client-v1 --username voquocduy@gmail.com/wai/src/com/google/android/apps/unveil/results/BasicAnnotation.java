package com.google.android.apps.unveil.results;

import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import java.util.Vector;

public class BasicAnnotation
{
  private static final UnveilLogger logger = new UnveilLogger();
  private int numComfirmations;
  private ResultItem result;

  public BasicAnnotation(ResultItem paramResultItem)
  {
    this.result = paramResultItem;
  }

  protected Vector<String> getDebugText()
  {
    Vector localVector = new Vector();
    localVector.add("Type: " + this.result.getType());
    localVector.add("# confirms: " + this.numComfirmations);
    localVector.add("ResultId: " + getResultId());
    return localVector;
  }

  public ResultItem getResult()
  {
    return this.result;
  }

  public String getResultId()
  {
    return this.result.getAnnotationResult().getResultId();
  }

  public String getTitle()
  {
    return this.result.getTitle();
  }

  public void updateResultItem(ResultItem paramResultItem)
  {
    this.result = paramResultItem;
    this.numComfirmations = (1 + this.numComfirmations);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.BasicAnnotation
 * JD-Core Version:    0.6.2
 */