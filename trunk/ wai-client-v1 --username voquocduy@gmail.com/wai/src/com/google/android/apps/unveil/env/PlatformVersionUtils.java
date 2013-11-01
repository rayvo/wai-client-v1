package com.google.android.apps.unveil.env;

import android.hardware.Camera;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PlatformVersionUtils
{
  private static Method Camera_setPreviewTexture;
  public static final String FOCUS_MODE_CONTINUOUS_PICTURE = "continuous-picture";
  public static final int GL_TEXTURE_EXTERNAL_OES = 36197;
  public static final int ICE_CREAM_SANDWICH = 14;
  private static Class TextureViewClass;
  private static Method TextureView_getSurfaceTexture;
  private static boolean isInitialized;

  // ERROR //
  private static void initialize()
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 44	com/google/android/apps/unveil/env/PlatformVersionUtils:isInitialized	Z
    //   6: istore_1
    //   7: iload_1
    //   8: ifeq +7 -> 15
    //   11: ldc 2
    //   13: monitorexit
    //   14: return
    //   15: iconst_1
    //   16: anewarray 46	java/lang/Class
    //   19: astore 6
    //   21: aload 6
    //   23: iconst_0
    //   24: ldc 48
    //   26: invokestatic 52	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   29: aastore
    //   30: ldc 54
    //   32: ldc 56
    //   34: aload 6
    //   36: invokevirtual 60	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   39: putstatic 62	com/google/android/apps/unveil/env/PlatformVersionUtils:Camera_setPreviewTexture	Ljava/lang/reflect/Method;
    //   42: ldc 64
    //   44: invokestatic 52	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   47: ldc 66
    //   49: iconst_0
    //   50: anewarray 46	java/lang/Class
    //   53: invokevirtual 60	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   56: putstatic 28	com/google/android/apps/unveil/env/PlatformVersionUtils:TextureView_getSurfaceTexture	Ljava/lang/reflect/Method;
    //   59: iconst_1
    //   60: putstatic 44	com/google/android/apps/unveil/env/PlatformVersionUtils:isInitialized	Z
    //   63: goto -52 -> 11
    //   66: astore_0
    //   67: ldc 2
    //   69: monitorexit
    //   70: aload_0
    //   71: athrow
    //   72: astore 5
    //   74: aload 5
    //   76: invokevirtual 69	java/lang/NoSuchMethodException:printStackTrace	()V
    //   79: goto -20 -> 59
    //   82: astore 4
    //   84: aload 4
    //   86: invokevirtual 70	java/lang/SecurityException:printStackTrace	()V
    //   89: goto -30 -> 59
    //   92: astore_3
    //   93: aload_3
    //   94: invokevirtual 71	java/lang/IllegalArgumentException:printStackTrace	()V
    //   97: goto -38 -> 59
    //   100: astore_2
    //   101: aload_2
    //   102: invokevirtual 72	java/lang/ClassNotFoundException:printStackTrace	()V
    //   105: goto -46 -> 59
    //
    // Exception table:
    //   from	to	target	type
    //   3	7	66	finally
    //   15	59	66	finally
    //   59	63	66	finally
    //   74	79	66	finally
    //   84	89	66	finally
    //   93	97	66	finally
    //   101	105	66	finally
    //   15	59	72	java/lang/NoSuchMethodException
    //   15	59	82	java/lang/SecurityException
    //   15	59	92	java/lang/IllegalArgumentException
    //   15	59	100	java/lang/ClassNotFoundException
  }

  private static Object invoke(Method paramMethod, Object paramObject, Object[] paramArrayOfObject)
  {
    if (paramMethod == null)
      return null;
    try
    {
      Object localObject = paramMethod.invoke(paramObject, paramArrayOfObject);
      return localObject;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      localIllegalArgumentException.printStackTrace();
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      localInvocationTargetException.printStackTrace();
    }
    return null;
  }

  public static void setPreviewTexture(Camera paramCamera, TextureView paramTextureView)
  {
    initialize();
    Method localMethod = Camera_setPreviewTexture;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramTextureView.getSurfaceTexture();
    invoke(localMethod, paramCamera, arrayOfObject);
  }

  public static class TextureView
  {
    private Object surfaceTexture;
    private final View textureView;

    public TextureView(View paramView)
    {
      this.textureView = paramView;
      this.surfaceTexture = null;
    }

    public TextureView(Object paramObject)
    {
      this.textureView = null;
      this.surfaceTexture = paramObject;
    }

    public Object getSurfaceTexture()
    {
      if (this.surfaceTexture == null)
        this.surfaceTexture = PlatformVersionUtils.invoke(PlatformVersionUtils.TextureView_getSurfaceTexture, this.textureView, new Object[0]);
      return this.surfaceTexture;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.PlatformVersionUtils
 * JD-Core Version:    0.6.2
 */