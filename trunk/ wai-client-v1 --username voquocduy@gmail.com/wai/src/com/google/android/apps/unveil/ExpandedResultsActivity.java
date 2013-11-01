package com.google.android.apps.unveil;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.View;
import android.webkit.WebView;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.android.apps.unveil.results.ResultModel;
import com.google.android.apps.unveil.ui.CachingPagerAdapter;
import com.google.android.apps.unveil.ui.ExpandedResultPagerAdapter;
import com.google.android.apps.unveil.ui.ExpandedResultView.MomentInteractionListener;
import com.google.android.apps.unveil.ui.WithdrawalDialogHelper;
import com.google.android.apps.unveil.ui.WithdrawalDialogHelper.WithdrawalDialogListener;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.List;

public class ExpandedResultsActivity extends StateRestorationActivity
  implements ExpandedResultView.MomentInteractionListener
{
  public static final String EXTRA_OPEN_TO = "open_to";
  public static final String EXTRA_RESULTS = "results";
  private static final int REQUEST_CODE_REPORT_ABUSE = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private ViewPager expandedResultView;
  private List<ResultModel> results;

  public void flagForAbuse(String paramString)
  {
    Intent localIntent = new Intent(this, ReportAbuseActivity.class);
    localIntent.putExtra("abuse_url", paramString);
    startActivityForResult(localIntent, 1);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    switch (paramInt1)
    {
    default:
    case 1:
    }
    do
      return;
    while (paramInt2 != -1);
    if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
      setResult(1);
    finish();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(R.layout.expanded_results_activity);
    this.application = ((UnveilContext)getApplicationContext());
    Intent localIntent = getIntent();
    this.results = ((List)localIntent.getSerializableExtra("results"));
    ResultModel localResultModel = (ResultModel)localIntent.getSerializableExtra("open_to");
    this.expandedResultView = ((ViewPager)findViewById(R.id.view_pager));
    this.expandedResultView.setAdapter(CachingPagerAdapter.wrap(new ExpandedResultPagerAdapter(this, this.results, this)));
    this.expandedResultView.setCurrentItem(this.results.indexOf(localResultModel));
    this.expandedResultView.setOnPageChangeListener(new ClickTrackingPageChangeListener(null));
  }

  public void withdrawSubmission(String paramString)
  {
    WithdrawalDialogHelper.showWithdrawalDialog(paramString, this, (UnveilApplication)this.application, new WithdrawalDialogHelper.WithdrawalDialogListener()
    {
      public void withdrawalSuccessful()
      {
        ExpandedResultsActivity.this.setResult(1);
        ExpandedResultsActivity.this.finish();
      }
    });
  }

  private final class ClickTrackingPageChangeListener extends ViewPager.SimpleOnPageChangeListener
  {
    private ClickTrackingPageChangeListener()
    {
    }

    public void onPageScrollStateChanged(int paramInt)
    {
      View localView = ExpandedResultsActivity.this.getCurrentFocus();
      if (localView == null);
      while ((!localView.isInTouchMode()) || (!(localView instanceof WebView)))
        return;
      localView.setFocusable(false);
      localView.clearFocus();
    }

    public void onPageSelected(int paramInt)
    {
      ResultModel localResultModel = (ResultModel)ExpandedResultsActivity.this.results.get(paramInt);
      if (!(localResultModel instanceof ResultItem))
        return;
      ResultItem localResultItem = (ResultItem)localResultModel;
      localResultItem.getAnnotationResult().getResultId();
      ClickTracker.logResultClick(ExpandedResultsActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RESULT_SHOW_EXPANDED_RESULT, localResultItem, paramInt);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ExpandedResultsActivity
 * JD-Core Version:    0.6.2
 */