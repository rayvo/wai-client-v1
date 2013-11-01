package com.google.android.apps.unveil.network;

import com.google.goggles.ContainerProtos.ContainerResponse;
import com.google.goggles.GogglesProtos.GogglesResponse;
import com.google.goggles.GogglesReplayProtos.GogglesReplayResponse;
import com.google.goggles.NativeClientLoggingProtos.NativeClientLogEventResponse;
import com.google.goggles.TracingCookieProtos.TracingCookieResponse;
import com.google.goggles.TracingProtos.TraceResponse;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageLite;
import com.google.protobuf.GeneratedMessageLite.Builder;
import java.io.IOException;
import java.io.InputStream;

public abstract class ProtoBuilder
{
  public static <T extends GeneratedMessageLite> T build(Class<T> paramClass, InputStream paramInputStream, ExtensionRegistryLite paramExtensionRegistryLite)
    throws IOException
  {
    return (GeneratedMessageLite)((GeneratedMessageLite.Builder)builderForClass(paramClass).mergeFrom(paramInputStream, paramExtensionRegistryLite)).build();
  }

  private static GeneratedMessageLite.Builder<?, ?> builderForClass(Class<? extends GeneratedMessageLite> paramClass)
  {
    if (paramClass == ContainerProtos.ContainerResponse.class)
      return ContainerProtos.ContainerResponse.newBuilder();
    if (paramClass == GogglesProtos.GogglesResponse.class)
      return GogglesProtos.GogglesResponse.newBuilder();
    if (paramClass == TracingCookieProtos.TracingCookieResponse.class)
      return TracingCookieProtos.TracingCookieResponse.newBuilder();
    if (paramClass == GogglesReplayProtos.GogglesReplayResponse.class)
      return GogglesReplayProtos.GogglesReplayResponse.newBuilder();
    if (paramClass == TracingProtos.TraceResponse.class)
      return TracingProtos.TraceResponse.newBuilder();
    if (paramClass == NativeClientLoggingProtos.NativeClientLogEventResponse.class)
      return NativeClientLoggingProtos.NativeClientLogEventResponse.newBuilder();
    throw new UnsupportedOperationException(paramClass + " not yet supported.");
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.network.ProtoBuilder
 * JD-Core Version:    0.6.2
 */