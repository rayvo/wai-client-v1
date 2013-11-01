package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class ContinuousFirstRunActivity extends StateRestorationActivity
{
  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.continuous_first_run);
    ((UnveilApplication)getApplication());
    findViewById(R.id.ok).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Settings.putBoolean(paramAnonymousView.getContext(), R.string.show_continuous_first_run_key, false);
        Intent localIntent = UnveilApplication.makeContinuousActivityIntent(ContinuousFirstRunActivity.this);
        ContinuousFirstRunActivity.this.startActivity(localIntent);
        ContinuousFirstRunActivity.this.finish();
      }
    });
    TextView localTextView = (TextView)findViewById(R.id.data_retention_paragraph);
    localTextView.setText(Html.fromHtml(getString(R.string.data_retention)));
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ContinuousFirstRunActivity.this.startActivity(new Intent(paramAnonymousView.getContext(), SHFirstRunActivity.class));
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ContinuousFirstRunActivity
 * JD-Core Version:    0.6.2
 */