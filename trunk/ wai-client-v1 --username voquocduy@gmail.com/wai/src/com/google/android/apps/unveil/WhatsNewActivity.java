package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class WhatsNewActivity extends StateRestorationActivity
{
  private void finishAndStartSfCPromotion()
  {
    startActivity(new Intent(this, SfCFirstRunActivity.class));
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.whats_new);
    TextView localTextView = (TextView)findViewById(R.id.whats_new_continuous_sh);
    localTextView.setText(Html.fromHtml(getString(R.string.whats_new_continuous_sh)));
    localTextView.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        Intent localIntent = new Intent(WhatsNewActivity.this, SHFirstRunActivity.class);
        WhatsNewActivity.this.startActivity(localIntent);
      }
    });
    ((Button)findViewById(R.id.lets_go)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        WhatsNewActivity.this.finishAndStartSfCPromotion();
      }
    });
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.WhatsNewActivity
 * JD-Core Version:    0.6.2
 */