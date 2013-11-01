package com.google.android.apps.unveil;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.provider.MediaStore.Images.Media;
import android.provider.SearchRecentSuggestions;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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
import com.google.android.apps.unveil.history.MergingItemProvider;
import com.google.android.apps.unveil.history.SavedQueryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryQuery.AllItemsQuery;
import com.google.android.apps.unveil.history.SearchHistoryQuery.QuerySpec;
import com.google.android.apps.unveil.history.SearchHistoryQuery.StringQuery;
import com.google.android.apps.unveil.history.SearchHistoryUpsell;
import com.google.android.apps.unveil.history.SearchListener;
import com.google.android.apps.unveil.history.SfCItemProvider;
import com.google.android.apps.unveil.history.SuggestionProvider;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.service.PictureRequestService;
import com.google.android.apps.unveil.ui.CachingPagerAdapter;
import com.google.android.apps.unveil.ui.DispatchingOnPageChangeListener;
import com.google.android.apps.unveil.ui.ViewPagerHeader;
import com.google.android.apps.unveil.ui.ViewPagerHeader.ClickListener;
import com.google.android.apps.unveil.ui.ViewPagerHeader.IndicatorStyle;
import com.google.android.apps.unveil.ui.history.HistoryPagerAdapter;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SearchHistoryActivity extends AuthenticatedActivity
  implements SearchListener
{
  public static final String HIGHLIGHT_SFC_LOCAL_IDS_KEY = "highlight_moments";
  private static final int REQUEST_CODE_LOAD_IMAGE = 1;
  private static final String USER_ACCOUNT_BUNDLE_KEY = "user-account";
  private static final UnveilLogger logger = new UnveilLogger();
  private AccessibilityUtils accessibilityUtils;
  private UnveilApplication application;
  private SearchHistoryProvider historyProvider;
  private final AbstractProvider.ItemsChangedListener itemsChangedListener = new AbstractProvider.ItemsChangedListener()
  {
    public void onItemsChanged(AbstractProvider paramAnonymousAbstractProvider)
    {
      SearchHistoryActivity.this.runOnUiThread(new Runnable()
      {
        public void run()
        {
          SearchHistoryActivity.this.refreshViews();
        }
      });
    }
  };
  private String userAccount = "";

  private boolean continuousSupported()
  {
    return (Build.VERSION.SDK_INT >= 9) && (singleShotSupported());
  }

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

  private ViewPager getColumns()
  {
    return (ViewPager)((ViewGroup)findViewById(R.id.columns_holder)).getChildAt(0);
  }

  private List<String> getMomentIdsToHighlight(Intent paramIntent)
  {
    if (paramIntent.hasExtra("highlight_moments"))
      return (List)paramIntent.getSerializableExtra("highlight_moments");
    return Collections.emptyList();
  }

  private void handleActionAllItems()
  {
    startService(PictureRequestService.makeOnSeenIntent(this, getMomentIdsToHighlight(getIntent())));
    setColumns(makeAllItemsColumns());
  }

  private void handleActionSearch(Intent paramIntent)
  {
    String str = paramIntent.getStringExtra("query");
    setColumns(makeStringQueryColumn(new SearchHistoryQuery.StringQuery(str)));
    new SearchRecentSuggestions(this, SuggestionProvider.getAuthority(this), 1).saveRecentQuery(str, null);
  }

  private void handleSfCItems(Intent paramIntent)
  {
    if (paramIntent.hasExtra("log_notification_count"))
    {
      int i = paramIntent.getExtras().getInt("log_notification_count");
      this.application.getClickTracker().logNotificationClick(i);
    }
    List localList = (List)paramIntent.getSerializableExtra("highlight_moments");
    logger.d("get unseen ids in extras: %s", new Object[] { localList });
    SfCItemProvider.deleteAllExceptFor(this, localList);
    startService(PictureRequestService.makeOnSeenIntent(this, getMomentIdsToHighlight(paramIntent)));
    setColumns(makeSfCItemsColumn(localList));
  }

  private void initializeButtons()
  {
    int i = 8;
    View localView1 = findViewById(R.id.single_shot);
    localView1.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(SearchHistoryActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HOME_SCREEN_SNAPSHOT);
        SearchHistoryActivity.this.startActivity(UnveilApplication.makeCaptureActivityIntent(SearchHistoryActivity.this));
      }
    });
    View localView2 = findViewById(R.id.continuous);
    localView2.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(SearchHistoryActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HOME_SCREEN_CONTINUOUS);
        SearchHistoryActivity.this.startActivity(UnveilApplication.makeContinuousActivityIntent(SearchHistoryActivity.this));
      }
    });
    findViewById(R.id.load_image).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ClickTracker.logClick(SearchHistoryActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HOME_SCREEN_LOAD_IMAGE);
        SearchHistoryActivity.this.loadFromGallery();
      }
    });
    if (singleShotSupported());
    for (int j = 0; ; j = i)
    {
      localView1.setVisibility(j);
      if (continuousSupported())
        i = 0;
      localView2.setVisibility(i);
      ImageButton localImageButton = (ImageButton)findViewById(R.id.map_mode);
      localImageButton.setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ClickTracker.logClick(SearchHistoryActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HISTORY_MAP_MODE_CLICK);
          Intent localIntent = new Intent(SearchHistoryActivity.this, MapHistoryActivity.class);
          SearchHistoryActivity.this.startActivity(localIntent);
        }
      });
      localImageButton.setFocusable(false);
      ((ImageButton)findViewById(R.id.search)).setOnClickListener(new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          ClickTracker.logClick(SearchHistoryActivity.this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HISTORY_SEARCH_BUTTON_CLICK);
          SearchHistoryActivity.this.onSearchRequested();
        }
      });
      return;
    }
  }

  private void invalidateHistoryHeader()
  {
    if (this.application.isSearchHistoryEnabled());
    for (int i = 0; ; i = 8)
    {
      ((ImageButton)findViewById(R.id.search)).setVisibility(i);
      ((ImageButton)findViewById(R.id.map_mode)).setVisibility(i);
      return;
    }
  }

  private void loadFromGallery()
  {
    Intent localIntent = new Intent("android.intent.action.PICK", MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    localIntent.setType("image/*");
    startActivityForResult(localIntent, 1);
  }

  private CachingPagerAdapter<ItemProvider, ? extends HistoryPagerAdapter> makeAllItemsColumns()
  {
    ItemProvider localItemProvider1 = ItemProvider.of("", this.application.getSavedQueryProvider());
    if (this.application.isSearchHistoryEnabled());
    for (ItemProvider localItemProvider2 = ItemProvider.of("", this.historyProvider, new SearchHistoryQuery.AllItemsQuery(), this); ; localItemProvider2 = ItemProvider.of("", Collections.singletonList(new SearchHistoryUpsell(this))))
    {
      LinkedList localLinkedList = new LinkedList();
      localLinkedList.add(MergingItemProvider.merge(getString(R.string.all), new ItemProvider[] { localItemProvider1, localItemProvider2 }));
      return CachingPagerAdapter.wrap(new HistoryPagerAdapter(this, this.application, localLinkedList));
    }
  }

  private <ItemModel, AdapterType extends HistoryPagerAdapter> View makePagerHeader(CachingPagerAdapter<ItemModel, ? extends HistoryPagerAdapter> paramCachingPagerAdapter, ViewPager paramViewPager)
  {
    ViewPagerHeader localViewPagerHeader = new ViewPagerHeader(this, (HistoryPagerAdapter)paramCachingPagerAdapter.getInner(), ViewPagerHeader.IndicatorStyle.ARROW, new HeaderClickListener(null));
    paramViewPager.setOnPageChangeListener(DispatchingOnPageChangeListener.wrap(new ViewPager.OnPageChangeListener[] { localViewPagerHeader }));
    return localViewPagerHeader;
  }

  private CachingPagerAdapter<ItemProvider, ? extends HistoryPagerAdapter> makeSfCItemsColumn(List<String> paramList)
  {
    boolean bool = this.application.isSearchHistoryEnabled();
    Object localObject = paramList;
    if (bool)
    {
      localObject = SfCItemProvider.getLocalSfCResultsOnly(this, paramList);
      UnveilLogger localUnveilLogger = logger;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(((List)localObject).size());
      arrayOfObject[1] = Integer.valueOf(paramList.size());
      localUnveilLogger.d("Search history is on, show %d search by camera results out of %d", arrayOfObject);
    }
    ItemProvider localItemProvider1 = ItemProvider.of("", (List)localObject, this);
    if (bool);
    for (ItemProvider localItemProvider2 = ItemProvider.of("", this.historyProvider, new SearchHistoryQuery.AllItemsQuery(), this); ; localItemProvider2 = ItemProvider.of("", Collections.singletonList(new SearchHistoryUpsell(this))))
    {
      LinkedList localLinkedList = new LinkedList();
      localLinkedList.add(MergingItemProvider.merge(getString(R.string.all), new ItemProvider[] { localItemProvider1, localItemProvider2 }));
      return CachingPagerAdapter.wrap(new HistoryPagerAdapter(this, this.application, localLinkedList));
    }
  }

  private CachingPagerAdapter<ItemProvider, ? extends HistoryPagerAdapter> makeStringQueryColumn(SearchHistoryQuery.StringQuery paramStringQuery)
  {
    return CachingPagerAdapter.wrap(new HistoryPagerAdapter(this, this.application, Collections.singletonList(ItemProvider.of("", this.historyProvider, paramStringQuery, this))));
  }

  private void onAuthChanged()
  {
    invalidateOptionsMenu();
    invalidateHistoryHeader();
    refreshViews();
    if (!this.userAccount.equals(this.application.getAuthState().getAccount()))
    {
      this.userAccount = this.application.getAuthState().getAccount();
      if (this.userAccount == null)
        this.userAccount = "";
    }
  }

  private void refreshViews()
  {
    Intent localIntent = getIntent();
    if ("android.intent.action.SEARCH".equals(localIntent.getAction()))
    {
      handleActionSearch(localIntent);
      return;
    }
    if ("com.google.android.apps.unveil.load_sfc_results".equals(localIntent.getAction()))
    {
      handleSfCItems(localIntent);
      return;
    }
    handleActionAllItems();
  }

  private <ItemModel, AdapterType extends HistoryPagerAdapter> void setColumns(CachingPagerAdapter<ItemModel, ? extends HistoryPagerAdapter> paramCachingPagerAdapter)
  {
    ViewPager localViewPager = new ViewPager(this);
    localViewPager.setAdapter(paramCachingPagerAdapter);
    FrameLayout localFrameLayout1 = (FrameLayout)findViewById(R.id.columns_holder);
    localFrameLayout1.removeAllViews();
    localFrameLayout1.addView(localViewPager);
    View localView = makePagerHeader(paramCachingPagerAdapter, localViewPager);
    FrameLayout localFrameLayout2 = (FrameLayout)findViewById(R.id.header_holder);
    localFrameLayout2.removeAllViews();
    localFrameLayout2.addView(localView);
  }

  private boolean singleShotSupported()
  {
    PackageManager localPackageManager = getPackageManager();
    return (localPackageManager.hasSystemFeature("android.hardware.camera")) || (localPackageManager.hasSystemFeature("android.hardware.camera.front"));
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getKeyCode() == 4))
      this.application.getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HARDWARE_BACK_BUTTON);
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if ((paramInt1 == 1024) && (paramInt2 == -1))
    {
      handleActionAllItems();
      return;
    }
    if ((paramInt1 == 1) && (paramInt2 == -1))
    {
      logger.v("Got a saved image URI", new Object[0]);
      Uri localUri = paramIntent.getData();
      logger.v("URI: %s", new Object[] { localUri });
      Intent localIntent = new Intent();
      localIntent.setAction("android.intent.action.SEND");
      localIntent.setClass(this, ShareActivity.class);
      localIntent.putExtra("android.intent.extra.STREAM", localUri);
      startActivity(localIntent);
      return;
    }
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
  }

  public void onAuthCanceled()
  {
    logger.w("onAuthCanceled()", new Object[0]);
    this.application.setUserWantsHistory(false);
    onAuthChanged();
  }

  public void onAuthFailure()
  {
    logger.w("onAuthFailure()", new Object[0]);
    onAuthChanged();
  }

  public void onAuthSuccess()
  {
    logger.i("onAuthSuccess()", new Object[0]);
    onAuthChanged();
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
    if ((this.application.isFirstRun()) || (this.application.isUpgrade()))
    {
      startActivity(new Intent(this, WelcomeActivity.class));
      finish();
      return;
    }
    this.historyProvider = this.application.getSearchHistoryProvider();
    StateRestorationActivity.restoreState(paramBundle, this.application, this);
    this.accessibilityUtils = new AccessibilityUtils(this, R.string.continuous_tts_settings_key);
    UnveilApplication.configureWindowFormat(getWindow());
    setContentView(R.layout.history);
    initializeButtons();
    this.historyProvider.addListener(this.itemsChangedListener);
    this.application.getSavedQueryProvider().addListener(this.itemsChangedListener);
    if (paramBundle != null)
      this.userAccount = paramBundle.getString("user-account");
    getSupportActionBar().setHomeButtonEnabled(false);
    getSupportActionBar().setDisplayHomeAsUpEnabled(false);
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getSupportMenuInflater().inflate(R.menu.history, paramMenu);
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
    if (paramMenuItem.getItemId() == R.id.submitted_results)
    {
      ClickTracker.logClick(this, NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.HISTORY_SWIPE_TO_SUBMITTED_RESULTS);
      startActivity(new Intent(this, SubmittedResultsActivity.class));
      return true;
    }
    if (paramMenuItem.getItemId() == R.id.tips)
    {
      startActivity(new Intent(this, TipsActivity.class));
      return true;
    }
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    paramMenu.findItem(R.id.submitted_results).setVisible(this.application.isSearchHistoryEnabled());
    return true;
  }

  protected void onResume()
  {
    super.onResume();
    if ((this.application.userWantsHistory()) && (!this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID)))
    {
      fetchAuthToken();
      return;
    }
    onAuthSuccess();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
    paramBundle.putString("user-account", this.userAccount);
  }

  public void onSearchCompleted(SearchHistoryQuery.QuerySpec paramQuerySpec, int paramInt)
  {
    if ((paramQuerySpec instanceof SearchHistoryQuery.StringQuery))
    {
      ((ViewPagerHeader)((ViewGroup)findViewById(R.id.header_holder)).getChildAt(0)).updateTitles();
      findViewById(R.id.header_holder).setVisibility(0);
    }
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

  private class HeaderClickListener
    implements ViewPagerHeader.ClickListener
  {
    private HeaderClickListener()
    {
    }

    public void onTitleClicked(int paramInt)
    {
      SearchHistoryActivity.this.getColumns().setCurrentItem(paramInt);
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.SearchHistoryActivity
 * JD-Core Version:    0.6.2
 */