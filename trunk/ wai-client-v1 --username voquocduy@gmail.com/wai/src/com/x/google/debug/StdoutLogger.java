package com.x.google.debug;

import java.io.PrintStream;

public class StdoutLogger extends SimpleLogger
{
  public void flush()
  {
    System.out.flush();
  }

  public void log(String paramString)
  {
    System.out.println(paramString);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.x.google.debug.StdoutLogger
 * JD-Core Version:    0.6.2
 */