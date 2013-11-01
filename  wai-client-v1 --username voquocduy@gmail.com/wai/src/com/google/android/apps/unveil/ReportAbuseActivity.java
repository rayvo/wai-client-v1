package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.AbuseListener;

public class ReportAbuseActivity extends StateRestorationActivity
{
  public static final String EXTRA_ABUSE_URL = "abuse_url";
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;

  public void flagForAbuse(String paramString1, String paramString2, String paramString3)
  {
    this.application.getSearchHistoryProvider().reportAbuse(paramString1, paramString2, paramString3, this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID), new SearchHistoryProvider.AbuseListener()
    {
      public void onError()
      {
        ReportAbuseActivity.logger.e("Failure when reporting abuse.", new Object[0]);
        Toast.makeText(ReportAbuseActivity.this, ReportAbuseActivity.this.getString(R.string.flagging_failed), 1).show();
      }

      public void onResult()
      {
        ReportAbuseActivity.logger.i("Result flagged for abuse.", new Object[0]);
        Toast.makeText(ReportAbuseActivity.this, ReportAbuseActivity.this.getString(R.string.flagging_succeeded), 1).show();
        ReportAbuseActivity.this.setResult(-1);
        ReportAbuseActivity.this.finish();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.report_abuse);
    this.application = ((UnveilContext)getApplicationContext());
    final String str = getIntent().getExtras().getString("abuse_url");
    ((Button)findViewById(R.id.submit_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        RadioGroup localRadioGroup = (RadioGroup)ReportAbuseActivity.this.findViewById(R.id.reason);
        String str1 = (String)ReportAbuseActivity.this.findViewById(localRadioGroup.getCheckedRadioButtonId()).getTag();
        String str2 = ((EditText)ReportAbuseActivity.this.findViewById(R.id.comments)).getText().toString();
        ReportAbuseActivity.this.flagForAbuse(str, str1, str2);
      }
    });
    ((Button)findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ReportAbuseActivity.this.setResult(0);
        ReportAbuseActivity.this.finish();
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ReportAbuseActivity
 * JD-Core Version:    0.6.2
 */