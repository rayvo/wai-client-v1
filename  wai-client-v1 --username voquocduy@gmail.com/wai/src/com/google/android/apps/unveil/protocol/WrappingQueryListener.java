package com.google.android.apps.unveil.protocol;

public class WrappingQueryListener extends QueryListener
{
  private final QueryListener inner;
  private final QueryListener outer;

  public WrappingQueryListener(QueryListener paramQueryListener1, QueryListener paramQueryListener2)
  {
    this.outer = paramQueryListener1;
    this.inner = paramQueryListener2;
  }

  public void onAuthenticationError()
  {
    this.outer.onAuthenticationError();
    this.inner.onAuthenticationError();
  }

  public void onHeavyProcessingEnd()
  {
    this.outer.onHeavyProcessingEnd();
    this.inner.onHeavyProcessingEnd();
  }

  public void onHeavyProcessingStart()
  {
    this.outer.onHeavyProcessingStart();
    this.inner.onHeavyProcessingStart();
  }

  public void onNetworkError(int paramInt)
  {
    this.outer.onNetworkError(paramInt);
    this.inner.onNetworkError(paramInt);
  }

  public void onQueryResponse(QueryResponse paramQueryResponse)
  {
    this.outer.onQueryResponse(paramQueryResponse);
    this.inner.onQueryResponse(paramQueryResponse);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.protocol.WrappingQueryListener
 * JD-Core Version:    0.6.2
 */