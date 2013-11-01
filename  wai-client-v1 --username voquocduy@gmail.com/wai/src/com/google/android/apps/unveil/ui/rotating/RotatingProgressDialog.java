package com.google.android.apps.unveil.ui.rotating;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.apps.unveil.R.drawable;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingProgressDialog
  implements RotatingDialog
{
  private static final UnveilLogger logger = new UnveilLogger(RotatingProgressDialog.class);
  private final Activity activity;
  private boolean cancelable = true;
  private final ViewGroup container;
  private RotatingDialogController controller;
  private RotatingLayout dialogView;
  private RotatingLinearLayout iconView;
  private RotatingTextView messageView;
  private RotatingDialog.OnCancelListener onCancelListener;
  private RotatingDialog.OnDismissListener onDismissListener;

  private RotatingProgressDialog(Activity paramActivity, ViewGroup paramViewGroup)
  {
    this.activity = paramActivity;
    this.container = paramViewGroup;
  }

  private void inflate()
  {
    this.dialogView = ((RotatingLayout)LayoutInflater.from(this.activity).inflate(R.layout.progress_dialog, this.container).findViewById(R.id.rotating_progress_root));
    this.dialogView.setVisibility(4);
    this.iconView = ((RotatingLinearLayout)this.dialogView.findViewById(R.id.rotating_progress_icon));
    this.iconView.setBackgroundResource(R.drawable.rotating_spinner);
    this.messageView = ((RotatingTextView)this.dialogView.findViewById(R.id.rotating_progress_message));
    this.container.removeView(this.dialogView);
  }

  private void setCancelable(boolean paramBoolean)
  {
    this.cancelable = paramBoolean;
  }

  private void setMessage(int paramInt)
  {
    setMessage(this.activity.getText(paramInt).toString());
  }

  private void setMessage(String paramString)
  {
    this.messageView.setText(paramString);
  }

  private void setOnCancelListener(RotatingDialog.OnCancelListener paramOnCancelListener)
  {
    this.onCancelListener = paramOnCancelListener;
  }

  private void setOnDismissListener(RotatingDialog.OnDismissListener paramOnDismissListener)
  {
    this.onDismissListener = paramOnDismissListener;
  }

  public void cancel()
  {
    if (this.onCancelListener != null)
      this.onCancelListener.onCancel(this);
    this.container.removeView(this.dialogView);
  }

  public void dismiss()
  {
    if (this.onDismissListener != null)
      this.onDismissListener.onDismiss(this);
    this.container.removeView(this.dialogView);
  }

  public boolean getCancelable()
  {
    return this.cancelable;
  }

  public RotatingDialogController getController()
  {
    return this.controller;
  }

  public void show(RotatingDialogController paramRotatingDialogController)
  {
    this.controller = paramRotatingDialogController;
    this.container.addView(this.dialogView);
    this.dialogView.initializeChildren();
    this.dialogView.setVisibility(0);
    this.iconView.startAnimation();
  }

  public static class Builder
  {
    private static final UnveilLogger logger = new UnveilLogger(Builder.class);
    private boolean cancelable = true;
    private final RotatingProgressDialog dialog;
    private String message;
    private int messageId = -1;
    private RotatingDialog.OnCancelListener onCancelListener;
    private RotatingDialog.OnDismissListener onDismissListener;

    public Builder(Activity paramActivity, ViewGroup paramViewGroup)
    {
      this.dialog = new RotatingProgressDialog(paramActivity, paramViewGroup, null);
    }

    public RotatingProgressDialog create()
    {
      this.dialog.inflate();
      if (this.messageId != -1)
        this.dialog.setMessage(this.messageId);
      while (true)
      {
        if (this.onCancelListener != null)
          this.dialog.setOnCancelListener(this.onCancelListener);
        if (this.onDismissListener != null)
          this.dialog.setOnDismissListener(this.onDismissListener);
        this.dialog.setCancelable(this.cancelable);
        return this.dialog;
        if (this.message != null)
          this.dialog.setMessage(this.message);
      }
    }

    public void setCancelable(boolean paramBoolean)
    {
      this.cancelable = paramBoolean;
    }

    public void setMessage(int paramInt)
    {
      this.messageId = paramInt;
      this.message = null;
    }

    public void setMessage(String paramString)
    {
      this.message = paramString;
      this.messageId = -1;
    }

    public void setOnCancelListener(RotatingDialog.OnCancelListener paramOnCancelListener)
    {
      this.onCancelListener = paramOnCancelListener;
    }

    public void setOnDismissListener(RotatingDialog.OnDismissListener paramOnDismissListener)
    {
      this.onDismissListener = paramOnDismissListener;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingProgressDialog
 * JD-Core Version:    0.6.2
 */