package com.google.android.apps.unveil.network;

import com.google.android.apps.unveil.UnveilContext;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.Provider;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

public class DefaultHttpRequestFactory
{
  private static HeaderProvider ACCEPT_ENCODING_HEADER = new StaticHeaderProvider("Accept-Encoding", "gzip");
  public static final String COOKIE_HEADER = "Cookie";
  public static final String USER_AGENT_HEADER = "User-Agent";
  private final List<HeaderProvider> headerProviders;

  DefaultHttpRequestFactory(List<? extends HeaderProvider> paramList)
  {
    this.headerProviders = new ArrayList(paramList);
    this.headerProviders.add(ACCEPT_ENCODING_HEADER);
  }

  private void addHeaders(HttpRequest paramHttpRequest)
  {
    Iterator localIterator = this.headerProviders.iterator();
    while (localIterator.hasNext())
      ((HeaderProvider)localIterator.next()).attachHeader(paramHttpRequest);
  }

  public static DefaultHttpRequestFactory newAnonymousRequestFactory(UnveilContext paramUnveilContext)
  {
    return newAnonymousRequestFactory(paramUnveilContext, Collections.emptyList());
  }

  public static DefaultHttpRequestFactory newAnonymousRequestFactory(UnveilContext paramUnveilContext, List<HeaderProvider> paramList)
  {
    return new DefaultHttpRequestFactory(paramList).addHeaderProvider(newDynamicProvider("User-Agent", paramUnveilContext.getFullUserAgent()));
  }

  public static DefaultHttpRequestFactory newAnonymousRequestFactory(Provider<String> paramProvider, String paramString)
  {
    HeaderProvider[] arrayOfHeaderProvider = new HeaderProvider[2];
    arrayOfHeaderProvider[0] = newDynamicProvider("User-Agent", paramProvider);
    arrayOfHeaderProvider[1] = new StaticHeaderProvider("X-Goggles-InstallationId", paramString);
    return new DefaultHttpRequestFactory(Arrays.asList(arrayOfHeaderProvider));
  }

  public static DefaultHttpRequestFactory newAnonymousRequestFactory(String paramString)
  {
    return new DefaultHttpRequestFactory(Collections.singletonList(new StaticHeaderProvider("User-Agent", paramString)));
  }

  public static DefaultHttpRequestFactory newAuthenticatedRequestFactory(UnveilContext paramUnveilContext)
  {
    HeaderProvider[] arrayOfHeaderProvider = new HeaderProvider[3];
    arrayOfHeaderProvider[0] = newDynamicProvider("User-Agent", paramUnveilContext.getFullUserAgent());
    arrayOfHeaderProvider[1] = newAuthenticationProvider(paramUnveilContext);
    arrayOfHeaderProvider[2] = newInstallationIdProvider(paramUnveilContext);
    return new DefaultHttpRequestFactory(Arrays.asList(arrayOfHeaderProvider));
  }

  public static HeaderProvider newAuthenticationProvider(UnveilContext paramUnveilContext)
  {
    return new ApplicationAuthenticationProvider(paramUnveilContext, null);
  }

  public static final HeaderProvider newDynamicProvider(String paramString, final Provider<String> paramProvider)
  {
    return new HeaderProvider()
    {
      public void attachHeader(HttpRequest paramAnonymousHttpRequest)
      {
        paramAnonymousHttpRequest.addHeader(this.val$name, (String)paramProvider.get());
      }
    };
  }

  public static HeaderProvider newInstallationIdProvider(UnveilContext paramUnveilContext)
  {
    return new InstallationIdProvider(paramUnveilContext, null);
  }

  public static HeaderProvider newStaticAuthenticationProvider(String paramString)
  {
    return new StaticHeaderProvider("Cookie", toSidCookieValue(paramString));
  }

  public static HttpEntity newStringEntity(String paramString)
  {
    try
    {
      StringEntity localStringEntity = new StringEntity(paramString);
      return localStringEntity;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException(localUnsupportedEncodingException);
    }
  }

  private static String toSidCookieValue(String paramString)
  {
    return String.format("SID=%s", new Object[] { paramString });
  }

  public DefaultHttpRequestFactory addHeaderProvider(HeaderProvider paramHeaderProvider)
  {
    this.headerProviders.add(paramHeaderProvider);
    return this;
  }

  public HttpGet newGetRequest(String paramString)
  {
    HttpGet localHttpGet = new HttpGet(paramString);
    addHeaders(localHttpGet);
    return localHttpGet;
  }

  public HttpPost newPostRequest(String paramString, HttpEntity paramHttpEntity, ContentType paramContentType)
  {
    HttpPost localHttpPost = new HttpPost(paramString);
    localHttpPost.setEntity(paramHttpEntity);
    localHttpPost.setHeader("Content-Type", paramContentType.typeString);
    addHeaders(localHttpPost);
    return localHttpPost;
  }

  private static final class ApplicationAuthenticationProvider
    implements DefaultHttpRequestFactory.HeaderProvider
  {
    private final UnveilContext application;

    private ApplicationAuthenticationProvider(UnveilContext paramUnveilContext)
    {
      this.application = paramUnveilContext;
    }

    public void attachHeader(HttpRequest paramHttpRequest)
    {
      if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
        paramHttpRequest.addHeader("Cookie", DefaultHttpRequestFactory.toSidCookieValue(this.application.getAuthState().getAuthToken(AuthToken.AuthTokenType.SID)));
    }
  }

  public static enum ContentType
  {
    public static final String HEADER_NAME = "Content-Type";
    public final String typeString;

    static
    {
      ContentType[] arrayOfContentType = new ContentType[2];
      arrayOfContentType[0] = PROTOBUF;
      arrayOfContentType[1] = URL_ENCODED;
    }

    private ContentType(String paramString)
    {
      this.typeString = paramString;
    }
  }

  public static abstract interface HeaderProvider
  {
    public abstract void attachHeader(HttpRequest paramHttpRequest);
  }

  private static final class InstallationIdProvider
    implements DefaultHttpRequestFactory.HeaderProvider
  {
    public static final String INSTALLATION_ID_HEADER = "X-Goggles-InstallationId";
    private final UnveilContext application;

    private InstallationIdProvider(UnveilContext paramUnveilContext)
    {
      this.application = paramUnveilContext;
    }

    public void attachHeader(HttpRequest paramHttpRequest)
    {
      paramHttpRequest.addHeader("X-Goggles-InstallationId", this.application.getInstallationId());
    }
  }

  public static final class StaticHeaderProvider
    implements DefaultHttpRequestFactory.HeaderProvider
  {
    private final String name;
    private final String value;

    public StaticHeaderProvider(String paramString1, String paramString2)
    {
      this.name = paramString1;
      this.value = paramString2;
    }

    public void attachHeader(HttpRequest paramHttpRequest)
    {
      paramHttpRequest.addHeader(this.name, this.value);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.DefaultHttpRequestFactory
 * JD-Core Version:    0.6.2
 */