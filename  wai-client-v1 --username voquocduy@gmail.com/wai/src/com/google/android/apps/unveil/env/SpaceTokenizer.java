package com.google.android.apps.unveil.env;

import android.widget.MultiAutoCompleteTextView.Tokenizer;

public final class SpaceTokenizer
  implements MultiAutoCompleteTextView.Tokenizer
{
  private static final char TOKEN = ' ';

  public int findTokenEnd(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramInt;
    int j = paramCharSequence.length();
    while (i < j)
    {
      if (paramCharSequence.charAt(i) == ' ')
        return i;
      i++;
    }
    return j;
  }

  public int findTokenStart(CharSequence paramCharSequence, int paramInt)
  {
    for (int i = paramInt; (i > 0) && (paramCharSequence.charAt(i - 1) != ' '); i--);
    while ((i < paramInt) && (paramCharSequence.charAt(i) == ' '))
      i++;
    return i;
  }

  public CharSequence terminateToken(CharSequence paramCharSequence)
  {
    for (int i = paramCharSequence.length(); (i > 0) && (paramCharSequence.charAt(i - 1) == ' '); i--);
    return paramCharSequence.subSequence(0, i) + " ";
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.SpaceTokenizer
 * JD-Core Version:    0.6.2
 */