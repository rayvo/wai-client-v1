package com.google.android.apps.unveil.ui.rotating;

import com.google.android.apps.unveil.env.UnveilLogger;
import java.util.ArrayList;

public class RotatingDialogController
{
  private static final UnveilLogger logger = new UnveilLogger();
  private final ArrayList<RotatingDialog> queue = new ArrayList();
  private RotatingDialog visibleDialog;

  private void close(RotatingDialog paramRotatingDialog, boolean paramBoolean)
  {
    if (paramRotatingDialog == this.visibleDialog)
      if (paramBoolean)
      {
        this.visibleDialog.cancel();
        this.visibleDialog = null;
        showNext();
      }
    while (true)
    {
      return;
      this.visibleDialog.dismiss();
      break;
      for (int i = 0; i < this.queue.size(); i++)
        if (this.queue.get(i) == paramRotatingDialog)
        {
          this.queue.remove(i);
          return;
        }
    }
  }

  private void dequeueAndShow()
  {
    this.visibleDialog = ((RotatingDialog)this.queue.remove(0));
    this.visibleDialog.show(this);
  }

  private void showNext()
  {
    if ((this.visibleDialog == null) && (!this.queue.isEmpty()))
      dequeueAndShow();
  }

  public void cancel(RotatingDialog paramRotatingDialog)
  {
    close(paramRotatingDialog, true);
  }

  public void dismiss(RotatingDialog paramRotatingDialog)
  {
    close(paramRotatingDialog, false);
  }

  public boolean handleBackButton()
  {
    if (this.visibleDialog != null)
    {
      if (this.visibleDialog.getCancelable())
        cancel(this.visibleDialog);
      return true;
    }
    return false;
  }

  public void show(RotatingDialog paramRotatingDialog)
  {
    if ((!this.queue.contains(paramRotatingDialog)) && (this.visibleDialog != paramRotatingDialog))
    {
      this.queue.add(paramRotatingDialog);
      showNext();
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingDialogController
 * JD-Core Version:    0.6.2
 */