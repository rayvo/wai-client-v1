package com.google.android.apps.unveil.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.google.android.apps.unveil.R.id;

public class SearchHistoryListFooterView extends LinearLayout
  implements View.OnClickListener
{
  private OnClickListener listener;
  private View loading;
  private View networkError;
  private View retry;

  public SearchHistoryListFooterView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public void bind(int paramInt)
  {
    if (paramInt == 0)
    {
      this.loading.setVisibility(0);
      this.networkError.setVisibility(8);
      return;
    }
    this.loading.setVisibility(8);
    this.networkError.setVisibility(0);
  }

  public void onClick(View paramView)
  {
    if (this.listener != null)
      this.listener.onClick();
  }

  protected void onFinishInflate()
  {
    super.onFinishInflate();
    this.loading = findViewById(R.id.loading);
    this.networkError = findViewById(R.id.network_error);
    this.retry = findViewById(R.id.retry_button);
    this.retry.setOnClickListener(this);
  }

  public void setOnClickListener(OnClickListener paramOnClickListener)
  {
    this.listener = paramOnClickListener;
  }

  public static abstract interface OnClickListener
  {
    public abstract void onClick();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.SearchHistoryListFooterView
 * JD-Core Version:    0.6.2
 */