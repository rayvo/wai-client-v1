package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.AbstractProvider;
import com.google.android.apps.unveil.env.AbstractProvider.ItemsChangedListener;
import com.google.android.apps.unveil.env.AccessibilityUtils;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.feedback.FeedbackUtils;
import com.google.android.apps.unveil.history.ItemProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryQuery.QuerySpec;
import com.google.android.apps.unveil.history.SearchHistoryQuery.SearchableItemsQuery;
import com.google.android.apps.unveil.history.SearchListener;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.ui.CachingPagerAdapter;
import com.google.android.apps.unveil.ui.history.HistoryPagerAdapter;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.LinkedList;
import java.util.List;

public class SubmittedResultsActivity extends StateRestorationActivity
  implements SearchListener
{
  private static final String USER_ACCOUNT_BUNDLE_KEY = "user-account";
  private static final UnveilLogger logger = new UnveilLogger();
  private AccessibilityUtils accessibilityUtils;
  private UnveilApplication application;
  private SearchHistoryProvider historyProvider;
  private final AbstractProvider.ItemsChangedListener itemsChangedListener = new AbstractProvider.ItemsChangedListener()
  {
    public void onItemsChanged(AbstractProvider paramAnonymousAbstractProvider)
    {
      SubmittedResultsActivity.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          SubmittedResultsActivity.this.initializeViews();
        }
      });
    }
  };
  private String userAccount = "";

  private String generateTextForAccessibility(SearchHistoryQuery.QuerySpec paramQuerySpec, int paramInt)
  {
    Resources localResources = getResources();
    if (paramQuerySpec.isQueryForAllItems())
    {
      if (paramInt == 1)
        return localResources.getQuantityString(R.plurals.history_items_description, paramInt);
      int k = R.plurals.history_items_description;
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(paramInt);
      return localResources.getQuantityString(k, paramInt, arrayOfObject3);
    }
    if (paramInt == 1)
    {
      int j = R.plurals.history_items_description_with_query;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = paramQuerySpec.toPresentationString();
      return localResources.getQuantityString(j, paramInt, arrayOfObject2);
    }
    int i = R.plurals.history_items_description_with_query;
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = Integer.valueOf(paramInt);
    arrayOfObject1[1] = paramQuerySpec.toPresentationString();
    return localResources.getQuantityString(i, paramInt, arrayOfObject1);
  }

  private void handleActionAllItems()
  {
    setColumns(makeAllItemsColumns());
  }

  private void initializeViews()
  {
    handleActionAllItems();
  }

  private CachingPagerAdapter<ItemProvider, ? extends HistoryPagerAdapter> makeAllItemsColumns()
  {
    ItemProvider.of("", this.application.getSavedQueryProvider());
    LinkedList localLinkedList = new LinkedList();
    localLinkedList.add(ItemProvider.of(getString(R.string.sent_to_goggles), this.historyProvider, new SearchHistoryQuery.SearchableItemsQuery(), this));
    return CachingPagerAdapter.wrap(new HistoryPagerAdapter(this, this.application, localLinkedList));
  }

  private <ItemModel, AdapterType extends HistoryPagerAdapter> void setColumns(CachingPagerAdapter<ItemModel, ? extends HistoryPagerAdapter> paramCachingPagerAdapter)
  {
    ViewPager localViewPager = new ViewPager(this);
    localViewPager.setAdapter(paramCachingPagerAdapter);
    FrameLayout localFrameLayout = (FrameLayout)findViewById(R.id.columns_holder);
    localFrameLayout.removeAllViews();
    localFrameLayout.addView(localViewPager);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
      this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HARDWARE_BACK_BUTTON);
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void onBackPressed()
  {
    ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.RETURN_TO_HOME_SCREEN_BACK_BUTTON);
    super.onBackPressed();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    this.historyProvider = this.application.getSearchHistoryProvider();
    this.accessibilityUtils = new AccessibilityUtils(this, R.string.continuous_tts_settings_key);
    UnveilApplication.configureWindowFormat(getWindow());
    setContentView(R.layout.submitted_results);
    initializeViews();
    this.historyProvider.addListener(this.itemsChangedListener);
    if (paramBundle != null)
      this.userAccount = paramBundle.getString("user-account");
    getSupportActionBar().setHomeButtonEnabled(true);
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getSupportMenuInflater().inflate(R.menu.submitted_results, paramMenu);
    paramMenu.findItem(R.id.send_feedback).setVisible(false);
    return true;
  }

  protected void onNewIntent(Intent paramIntent)
  {
    logger.i("onNewIntent", new Object[0]);
    startActivity(paramIntent);
    finish();
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.help)
    {
      startActivity(new Intent(this, AboutActivity.class));
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.settings)
    {
      startActivity(new Intent(this, PreferencesActivity.class));
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.send_feedback)
    {
      FeedbackUtils.onFeedbackOptionsItemSelected(this, null);
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onResume()
  {
    super.onResume();
    invalidateOptionsMenu();
    handleActionAllItems();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    paramBundle.putString("user-account", this.userAccount);
  }

  public void onSearchCompleted(SearchHistoryQuery.QuerySpec paramQuerySpec, int paramInt)
  {
    if (paramInt == 0)
      return;
    String str = generateTextForAccessibility(paramQuerySpec, paramInt);
    this.accessibilityUtils.readTextForAccessibility(str);
  }

  public boolean onSearchRequested()
  {
    if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
      return super.onSearchRequested();
    return false;
  }

  public void onSearchResultsNoResults(int paramInt)
  {
    this.accessibilityUtils.readTextForAccessibility(getString(paramInt));
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.SubmittedResultsActivity
 * JD-Core Version:    0.6.2
 */