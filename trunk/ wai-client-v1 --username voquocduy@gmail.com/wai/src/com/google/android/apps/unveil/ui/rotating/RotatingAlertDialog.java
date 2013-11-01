package com.google.android.apps.unveil.ui.rotating;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.Layout.Alignment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.UnveilLogger;

public class RotatingAlertDialog
  implements RotatingDialog
{
  public static final int NEGATIVE_BUTTON_ID = 2;
  public static final int NEUTRAL_BUTTON_ID = 3;
  public static final int POSITIVE_BUTTON_ID = 1;
  private static final UnveilLogger logger = new UnveilLogger("DialogInstance");
  private final Activity activity;
  private RotatingButton button1View;
  private RotatingButton button2View;
  private RotatingButton button3View;
  private RotatingDialog.OnCancelListener cancelListener;
  private boolean cancelable = true;
  private RotatingLinearLayout centerView;
  private final ViewGroup container;
  private RotatingDialogController controller;
  private RotatingLayout dialogView;
  private RotatingDialog.OnDismissListener dismissListener;
  private RotatingTextView messageView;
  private RotatingDialogButton negativeButton;
  private RotatingDialogButton neutralButton;
  private RotatingDialogButton positiveButton;
  private final FrameLayout shade;
  private RotatingTextView titleView;

  private RotatingAlertDialog(Activity paramActivity, ViewGroup paramViewGroup)
  {
    this.activity = paramActivity;
    this.container = paramViewGroup;
    this.shade = new FrameLayout(paramViewGroup.getContext());
    this.shade.setBackgroundColor(Color.argb(160, 0, 0, 0));
    this.shade.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
  }

  private void adjustForSingleButton(View paramView)
  {
    View localView1 = this.dialogView.findViewById(R.id.rotating_dialog_leftSpacer);
    View localView2 = this.dialogView.findViewById(R.id.rotating_dialog_rightSpacer);
    localView1.setVisibility(0);
    localView2.setVisibility(0);
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)paramView.getLayoutParams();
    localLayoutParams.weight = 0.5F;
    localLayoutParams.gravity = 1;
    paramView.setLayoutParams(localLayoutParams);
  }

  private void bindButtons()
  {
    ((RotatingLayout)this.dialogView.findViewById(R.id.rotating_dialog_root)).initializeChildren();
    RotatingButton[] arrayOfRotatingButton = new RotatingButton[3];
    arrayOfRotatingButton[0] = this.button1View;
    arrayOfRotatingButton[1] = this.button2View;
    arrayOfRotatingButton[2] = this.button3View;
    RotatingDialogButton localRotatingDialogButton1 = this.neutralButton;
    int i = 0;
    if (localRotatingDialogButton1 != null)
    {
      RotatingDialogButton localRotatingDialogButton4 = this.neutralButton;
      int m = 0 + 1;
      localRotatingDialogButton4.bind(arrayOfRotatingButton[0], this);
      i = m;
    }
    if (this.negativeButton != null)
    {
      RotatingDialogButton localRotatingDialogButton3 = this.negativeButton;
      int k = i + 1;
      localRotatingDialogButton3.bind(arrayOfRotatingButton[i], this);
      i = k;
    }
    if (this.positiveButton != null)
    {
      RotatingDialogButton localRotatingDialogButton2 = this.positiveButton;
      int j = i + 1;
      localRotatingDialogButton2.bind(arrayOfRotatingButton[i], this);
      i = j;
    }
    if (i == 1)
      adjustForSingleButton(arrayOfRotatingButton[0]);
    this.dialogView.requestLayout();
  }

  private void hideTitle()
  {
    this.titleView.setVisibility(8);
    this.dialogView.findViewById(R.id.rotating_dialog_divider).setVisibility(8);
  }

  private void inflate(boolean paramBoolean)
  {
    this.dialogView = ((RotatingLayout)LayoutInflater.from(this.activity).inflate(R.layout.rotating_dialog, this.container).findViewById(R.id.rotating_dialog_root));
    this.dialogView.setVisibility(4);
    this.titleView = ((RotatingTextView)this.dialogView.findViewById(R.id.rotating_dialog_title));
    this.titleView.setTextAlignment(Layout.Alignment.ALIGN_NORMAL);
    this.dialogView.findViewById(R.id.rotating_dialog_divider).setVisibility(4);
    this.centerView = ((RotatingLinearLayout)this.dialogView.findViewById(R.id.rotating_dialog_center));
    this.messageView = ((RotatingTextView)this.dialogView.findViewById(R.id.rotating_dialog_message));
    this.centerView.setVisibility(8);
    RotatingLinearLayout localRotatingLinearLayout;
    if (paramBoolean)
    {
      localRotatingLinearLayout = (RotatingLinearLayout)this.dialogView.findViewById(R.id.vertical_button_layout);
      localRotatingLinearLayout.setVisibility(0);
      this.dialogView.findViewById(R.id.horizontal_button_layout).setVisibility(8);
    }
    while (true)
    {
      this.button1View = ((RotatingButton)localRotatingLinearLayout.findViewById(R.id.rotating_dialog_b1));
      this.button2View = ((RotatingButton)localRotatingLinearLayout.findViewById(R.id.rotating_dialog_b2));
      this.button3View = ((RotatingButton)localRotatingLinearLayout.findViewById(R.id.rotating_dialog_b3));
      this.button2View.setVisibility(8);
      this.button3View.setVisibility(8);
      this.container.removeView(this.dialogView);
      return;
      localRotatingLinearLayout = (RotatingLinearLayout)this.dialogView.findViewById(R.id.horizontal_button_layout);
    }
  }

  private void setCancelable(boolean paramBoolean)
  {
    this.cancelable = paramBoolean;
  }

  private void setIcon(int paramInt)
  {
    if (this.titleView.getVisibility() == 0)
    {
      this.titleView.setLeftDrawable(this.activity.getResources().getDrawable(paramInt));
      return;
    }
    this.messageView.setLeftDrawable(this.activity.getResources().getDrawable(paramInt));
  }

  private void setMessage(int paramInt)
  {
    setMessage(this.activity.getText(paramInt).toString());
  }

  private void setMessage(String paramString)
  {
    this.messageView.setText(paramString);
    this.centerView.setVisibility(0);
    if (this.titleView.getVisibility() == 0)
      this.dialogView.findViewById(R.id.rotating_dialog_divider).setVisibility(0);
  }

  private void setNegativeButton(RotatingDialogButton paramRotatingDialogButton)
  {
    this.negativeButton = paramRotatingDialogButton;
  }

  private void setNeutralButton(RotatingDialogButton paramRotatingDialogButton)
  {
    this.neutralButton = paramRotatingDialogButton;
  }

  private void setOnCancelListener(RotatingDialog.OnCancelListener paramOnCancelListener)
  {
    this.cancelListener = paramOnCancelListener;
  }

  private void setOnDismissListener(RotatingDialog.OnDismissListener paramOnDismissListener)
  {
    this.dismissListener = paramOnDismissListener;
  }

  private void setPositiveButton(RotatingDialogButton paramRotatingDialogButton)
  {
    this.positiveButton = paramRotatingDialogButton;
  }

  private void setTitle(CharSequence paramCharSequence)
  {
    this.titleView.setText(paramCharSequence);
  }

  public void cancel()
  {
    if (this.cancelListener != null)
      this.cancelListener.onCancel(this);
    this.container.removeView(this.dialogView);
    this.container.removeView(this.shade);
  }

  public void dismiss()
  {
    if (this.dismissListener != null)
      this.dismissListener.onDismiss(this);
    this.container.removeView(this.dialogView);
    this.container.removeView(this.shade);
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
    this.container.addView(this.shade);
    this.container.addView(this.dialogView);
    ((RotatingLayout)this.dialogView.findViewById(R.id.rotating_dialog_root)).initializeChildren();
    this.dialogView.setVisibility(0);
    this.controller = paramRotatingDialogController;
  }

  public static class Builder
  {
    private static final int UNSPECIFIED_ID = -1;
    private static final UnveilLogger logger = new UnveilLogger(Builder.class);
    private final Activity activity;
    private final RotatingAlertDialog dialog;
    private int iconId = -1;
    private int messageId = -1;
    private CharSequence titleText;
    private boolean vertical = false;

    public Builder(Activity paramActivity, ViewGroup paramViewGroup)
    {
      this.activity = paramActivity;
      this.dialog = new RotatingAlertDialog(paramActivity, paramViewGroup, null);
    }

    public RotatingAlertDialog create()
    {
      this.dialog.inflate(this.vertical);
      if (this.titleText != null)
        this.dialog.setTitle(this.titleText);
      while (true)
      {
        if (this.messageId != -1)
          this.dialog.setMessage(this.messageId);
        if (this.iconId != -1)
          this.dialog.setIcon(this.iconId);
        this.dialog.bindButtons();
        return this.dialog;
        this.dialog.hideTitle();
      }
    }

    public void setCancelable(boolean paramBoolean)
    {
      this.dialog.setCancelable(paramBoolean);
    }

    public void setIcon(int paramInt)
    {
      this.iconId = paramInt;
    }

    public void setMessage(int paramInt)
    {
      this.messageId = paramInt;
    }

    public void setNegativeButton(int paramInt, RotatingDialog.OnClickListener paramOnClickListener)
    {
      this.dialog.setNegativeButton(new RotatingDialogButton(this.activity.getText(paramInt), paramOnClickListener, 2));
    }

    public void setNeutralButton(int paramInt, RotatingDialog.OnClickListener paramOnClickListener)
    {
      this.dialog.setNeutralButton(new RotatingDialogButton(this.activity.getText(paramInt), paramOnClickListener, 3));
    }

    public void setOnCancelListener(RotatingDialog.OnCancelListener paramOnCancelListener)
    {
      this.dialog.setOnCancelListener(paramOnCancelListener);
    }

    public void setOnDismissListener(RotatingDialog.OnDismissListener paramOnDismissListener)
    {
      this.dialog.setOnDismissListener(paramOnDismissListener);
    }

    public void setPositiveButton(int paramInt, RotatingDialog.OnClickListener paramOnClickListener)
    {
      this.dialog.setPositiveButton(new RotatingDialogButton(this.activity.getText(paramInt), paramOnClickListener, 1));
    }

    public void setTitle(int paramInt)
    {
      setTitle(this.activity.getText(paramInt));
    }

    public void setTitle(CharSequence paramCharSequence)
    {
      this.titleText = paramCharSequence;
    }

    public void setVertical(boolean paramBoolean)
    {
      this.vertical = paramBoolean;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.rotating.RotatingAlertDialog
 * JD-Core Version:    0.6.2
 */