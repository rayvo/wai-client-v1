package com.google.android.apps.unveil.ui.rotating;

public abstract interface RotatingDialog
{
  public abstract void cancel();

  public abstract void dismiss();

  public abstract boolean getCancelable();

  public abstract RotatingDialogController getController();

  public abstract void show(RotatingDialogController paramRotatingDialogController);

  public static abstract interface OnCancelListener
  {
    public abstract void onCancel(RotatingDialog paramRotatingDialog);
  }

  public static abstract interface OnClickListener
  {
    public abstract void onClick(RotatingDialog paramRotatingDialog, int paramInt);
  }

  public static abstract interface OnDismissListener
  {
    public abstract void onDismiss(RotatingDialog paramRotatingDialog);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingDialog
 * JD-Core Version:    0.6.2
 */