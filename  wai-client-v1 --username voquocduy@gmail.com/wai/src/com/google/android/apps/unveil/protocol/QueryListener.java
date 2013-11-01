package com.google.android.apps.unveil.protocol;

public abstract class QueryListener
{
  public abstract void onAuthenticationError();

  public void onHeavyProcessingEnd()
  {
  }

  public void onHeavyProcessingStart()
  {
  }

  public abstract void onNetworkError(int paramInt);

  public abstract void onQueryResponse(QueryResponse paramQueryResponse);

  public void onSending(int paramInt)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.QueryListener
 * JD-Core Version:    0.6.2
 */