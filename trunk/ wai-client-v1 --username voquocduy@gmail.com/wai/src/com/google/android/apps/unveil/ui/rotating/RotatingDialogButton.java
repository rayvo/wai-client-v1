package com.google.android.apps.unveil.ui.rotating;

import android.view.View;
import android.view.View.OnClickListener;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingDialogButton
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final RotatingDialog.OnClickListener clickListener;
  private final CharSequence text;
  private final int which;

  public RotatingDialogButton(CharSequence paramCharSequence, RotatingDialog.OnClickListener paramOnClickListener, int paramInt)
  {
    this.text = paramCharSequence;
    this.clickListener = paramOnClickListener;
    this.which = paramInt;
  }

  public void bind(RotatingButton paramRotatingButton, final RotatingDialog paramRotatingDialog)
  {
    paramRotatingButton.setText(getText());
    paramRotatingButton.setVisibility(0);
    paramRotatingButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RotatingDialogButton.this.clickListener.onClick(paramRotatingDialog, RotatingDialogButton.this.which);
        paramRotatingDialog.getController().dismiss(paramRotatingDialog);
      }
    });
  }

  public CharSequence getText()
  {
    return this.text;
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingDialogButton
 * JD-Core Version:    0.6.2
 */