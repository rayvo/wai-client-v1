package com.google.android.apps.unveil.history;

import android.text.TextUtils;
import com.google.android.apps.unveil.env.Clock;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.network.AbstractConnector;
import com.google.android.apps.unveil.network.DefaultHttpRequestFactory;
import com.google.android.apps.unveil.network.fetch.FetchResponse;
import com.google.android.apps.unveil.network.fetch.FetchResponse.Handler;
import java.net.URL;
import org.apache.http.client.methods.HttpUriRequest;

public class XsrfTokenManager
{
  static final String GOGGLES_TOKEN_COOKIE_NAME = "gogglestok";
  static final String GOGGLES_TOKEN_HEADER_NAME = "X-Goggles-Token";
  static final long TOKEN_EXPIRATION_TIME_MS = 300000L;
  static final String TOKEN_REFRESH_PATH = "/goggles/a/token";
  private static final UnveilLogger logger = new UnveilLogger();
  private final Clock clock;
  private final AbstractConnector connector;
  long expiresAt;
  String gogglesToken;
  private final String refreshUrl;
  private final DefaultHttpRequestFactory requestFactory;

  public XsrfTokenManager(DefaultHttpRequestFactory paramDefaultHttpRequestFactory, Clock paramClock, AbstractConnector paramAbstractConnector, URL paramURL)
  {
    this.requestFactory = paramDefaultHttpRequestFactory;
    this.clock = paramClock;
    this.connector = paramAbstractConnector;
    this.refreshUrl = (paramURL + "/goggles/a/token");
  }

  private void execute(HttpUriRequest paramHttpUriRequest, FetchResponse.Handler paramHandler)
  {
    paramHttpUriRequest.addHeader("X-Goggles-Token", this.gogglesToken);
    this.connector.sendRequest(paramHttpUriRequest, paramHandler);
  }

  private void refreshTokenAndExecute(final HttpUriRequest paramHttpUriRequest, final FetchResponse.Handler paramHandler)
  {
    this.connector.sendRequest(this.requestFactory.newGetRequest(this.refreshUrl), new FetchResponse.Handler()
    {
      public void onResponseReceived(FetchResponse paramAnonymousFetchResponse)
      {
        if (FetchResponse.isValid(paramAnonymousFetchResponse))
        {
          XsrfTokenManager.this.handleGetResponse(paramAnonymousFetchResponse);
          XsrfTokenManager.this.execute(paramHttpUriRequest, paramHandler);
          return;
        }
        paramHandler.onResponseReceived(null);
      }
    });
  }

  public void executeSideEffectRequest(HttpUriRequest paramHttpUriRequest, FetchResponse.Handler paramHandler)
  {
    if (hasValidToken())
    {
      execute(paramHttpUriRequest, paramHandler);
      return;
    }
    refreshTokenAndExecute(paramHttpUriRequest, paramHandler);
  }

  public void handleGetResponse(FetchResponse paramFetchResponse)
  {
    String str = paramFetchResponse.getCookie("gogglestok");
    if (str != null)
    {
      this.gogglesToken = str;
      this.expiresAt = (300000L + this.clock.now());
    }
  }

  boolean hasValidToken()
  {
    return (!TextUtils.isEmpty(this.gogglesToken)) && (this.clock.now() < this.expiresAt);
  }

  public void invalidateToken()
  {
    this.gogglesToken = null;
    this.expiresAt = this.clock.now();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.history.XsrfTokenManager
 * JD-Core Version:    0.6.2
 */