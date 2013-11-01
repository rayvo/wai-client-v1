package com.google.android.apps.unveil.protocol.nonstop;

import com.google.android.apps.unveil.protocol.ContinuousQueryBuilder;
import java.util.List;

public abstract interface RequestPreprocessor
{
  public abstract void addDebugText(List<String> paramList);

  public abstract void onPause();

  public abstract void onReset();

  public abstract void onStart();

  public abstract void preprocess(ContinuousQueryBuilder paramContinuousQueryBuilder);
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.nonstop.RequestPreprocessor
 * JD-Core Version:    0.6.2
 */