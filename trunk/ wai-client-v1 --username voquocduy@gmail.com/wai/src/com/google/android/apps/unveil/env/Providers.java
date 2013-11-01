package com.google.android.apps.unveil.env;

public class Providers
{
  public static <T> Provider<T> staticProvider(T paramT)
  {
    return new Provider()
    {
      public T get()
      {
        return this.val$value;
      }
    };
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Providers
 * JD-Core Version:    0.6.2
 */