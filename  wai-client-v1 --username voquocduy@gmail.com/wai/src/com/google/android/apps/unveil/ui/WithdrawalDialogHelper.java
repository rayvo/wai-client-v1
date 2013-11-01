package com.google.android.apps.unveil.ui;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.google.android.apps.unveil.BaseApplication;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.TagListener;

public class WithdrawalDialogHelper
{
  private static final UnveilLogger logger = new UnveilLogger();

  private static DialogInterface.OnClickListener createWithdrawalDialogListener(final String paramString, BaseApplication paramBaseApplication, final WithdrawalDialogListener paramWithdrawalDialogListener)
  {
    return new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        this.val$application.getSearchHistoryProvider().withdrawUserSubmission(paramString, new SearchHistoryProvider.TagListener()
        {
          public void onError()
          {
            WithdrawalDialogHelper.logger.e("Error withdrawing user submission.", new Object[0]);
            Toast.makeText(WithdrawalDialogHelper.1.this.val$application, WithdrawalDialogHelper.1.this.val$application.getString(R.string.withdrawal_failed), 1).show();
          }

          public void onResult()
          {
            WithdrawalDialogHelper.logger.i("Moment withdrawn", new Object[0]);
            WithdrawalDialogHelper.1.this.val$listener.withdrawalSuccessful();
          }
        });
      }
    };
  }

  public static void showWithdrawalDialog(String paramString, Context paramContext, BaseApplication paramBaseApplication, WithdrawalDialogListener paramWithdrawalDialogListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(R.string.withdraw_result);
    localBuilder.setIcon(17301543);
    localBuilder.setMessage(R.string.confirm_withdrawal);
    localBuilder.setCancelable(true);
    localBuilder.setPositiveButton(R.string.ok, createWithdrawalDialogListener(paramString, paramBaseApplication, paramWithdrawalDialogListener));
    localBuilder.setNegativeButton(R.string.cancel, null);
    localBuilder.show();
  }

  public static abstract interface WithdrawalDialogListener
  {
    public abstract void withdrawalSuccessful();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.WithdrawalDialogHelper
 * JD-Core Version:    0.6.2
 */