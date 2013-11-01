package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.ClipboardManager;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.AndroidContactIntentBuilder;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.net.URL;

public final class ActionClickListener
  implements View.OnClickListener
{
  private static final String ADD_CONTACT_COMMAND = "/goggles/cmd_add_contact";
  private static final String COPY_COMMAND = "/goggles/cmd_copy";
  private final ActionContext actionLocation;
  private final int actionPosition;
  private final String actionUrl;
  private final ResultItem resultItem;

  private ActionClickListener(String paramString, ResultItem paramResultItem, int paramInt, ActionContext paramActionContext)
  {
    this.actionUrl = paramString;
    this.resultItem = paramResultItem;
    this.actionPosition = paramInt;
    this.actionLocation = paramActionContext;
  }

  public static void applyTo(View paramView, String paramString, ResultItem paramResultItem, int paramInt, ActionContext paramActionContext)
  {
    if (TextUtils.isEmpty(paramString))
      throw new AssertionError("Cannot use ActionClickListener without an actionUrl");
    paramView.setOnClickListener(new ActionClickListener(paramString, paramResultItem, paramInt, paramActionContext));
  }

  private void viewUrl(Uri paramUri, Context paramContext)
  {
    paramContext.startActivity(new Intent().setAction("android.intent.action.VIEW").setData(paramUri));
  }

  public void onClick(View paramView)
  {
    ClickTracker.logActionClick(paramView, this.actionLocation.clickTarget, this.resultItem, this.actionPosition);
    UnveilApplication localUnveilApplication = (UnveilApplication)paramView.getContext().getApplicationContext();
    Uri localUri = Uri.parse(this.actionUrl);
    if (("http".equalsIgnoreCase(localUri.getScheme())) && (localUnveilApplication.getFrontendUrl().getAuthority().equalsIgnoreCase(localUri.getAuthority())))
    {
      String str = localUri.getPath();
      if ("/goggles/cmd_add_contact".equals(str))
      {
        AndroidContactIntentBuilder localAndroidContactIntentBuilder = new AndroidContactIntentBuilder();
        paramView.getContext().startActivity(localAndroidContactIntentBuilder.build(paramView.getContext().getApplicationContext(), localUri));
        return;
      }
      if ("/goggles/cmd_copy".equals(str))
      {
        ((ClipboardManager)paramView.getContext().getSystemService("clipboard")).setText(localUri.getQueryParameter("string"));
        Toast.makeText(paramView.getContext(), R.string.text_copied, 0).show();
        return;
      }
      viewUrl(localUri, paramView.getContext());
      return;
    }
    viewUrl(localUri, paramView.getContext());
  }

  public static enum ActionContext
  {
    public final NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET clickTarget;

    static
    {
      ActionContext[] arrayOfActionContext = new ActionContext[2];
      arrayOfActionContext[0] = DISAMBIGUATION_SCREEN;
      arrayOfActionContext[1] = EXPANDED_RESULT_SCREEN;
    }

    private ActionContext(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET paramCLICK_TARGET)
    {
      this.clickTarget = paramCLICK_TARGET;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.ActionClickListener
 * JD-Core Version:    0.6.2
 */