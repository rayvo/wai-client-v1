package com.google.android.apps.unveil;

import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockMapActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.google.android.apps.unveil.auth.AuthState;
import com.google.android.apps.unveil.auth.AuthToken.AuthTokenType;
import com.google.android.apps.unveil.env.AbstractProvider;
import com.google.android.apps.unveil.env.AbstractProvider.ItemsChangedListener;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.feedback.FeedbackUtils;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.QueryListener;
import com.google.android.apps.unveil.history.SearchHistoryProvider.QueryToken;
import com.google.android.apps.unveil.history.SearchHistoryQuery.AllMapsItemsQuery;
import com.google.android.apps.unveil.ui.history.MapOverlayManager;
import com.google.android.apps.unveil.ui.history.UnveilMapView;
import java.util.List;

public class MapHistoryActivity extends SherlockMapActivity
  implements AbstractProvider.ItemsChangedListener
{
  private static final int BATCH_SIZE = 100;
  private static final int DIALOG_DOWNLOAD_ERROR = 1;
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private boolean isShowingActivity;
  private UnveilMapView mapView;
  private MapOverlayManager overlayManager;
  ProgressDialog progressDialog;
  private SearchHistoryProvider searchHistoryProvider;

  private Dialog newDownloadErrorDialog()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this);
    localBuilder.setMessage(R.string.map_network_error);
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(getString(R.string.try_again), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        MapHistoryActivity.this.queryForSearchHistoryItems();
      }
    });
    localBuilder.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        MapHistoryActivity.this.finish();
      }
    });
    return localBuilder.create();
  }

  private void queryForSearchHistoryItems()
  {
    SearchHistoryProvider.QueryToken localQueryToken = SearchHistoryProvider.makeQuery(new SearchHistoryQuery.AllMapsItemsQuery(), new SearchHistoryProvider.QueryListener()
    {
      public void onError()
      {
        MapHistoryActivity.logger.e("Error from search history provider.", new Object[0]);
        MapHistoryActivity.this.showDialog(1);
      }

      public void onResults(SearchHistoryProvider.QueryToken paramAnonymousQueryToken, int paramAnonymousInt)
      {
        List localList = paramAnonymousQueryToken.getResults();
        int i = localList.size();
        for (int j = paramAnonymousInt; j < i; j++)
        {
          SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)localList.get(j);
          if (localSearchHistoryItem == null)
            throw new AssertionError("null search history item on onResults");
          if (localSearchHistoryItem.getGeoPoint() != null)
            MapHistoryActivity.this.overlayManager.addItem(localSearchHistoryItem);
        }
        MapHistoryActivity.this.overlayManager.update();
        if (i > 0)
          MapHistoryActivity.this.overlayManager.setMapBoundsToMinimalBoundingRegion();
        MapHistoryActivity.this.progressDialog.dismiss();
      }
    });
    localQueryToken.setBatchSize(100);
    this.searchHistoryProvider.query(localQueryToken);
  }

  private void reload()
  {
    Intent localIntent = getIntent();
    overridePendingTransition(0, 0);
    localIntent.addFlags(65536);
    finish();
    overridePendingTransition(0, 0);
    startActivity(localIntent);
  }

  protected boolean isRouteDisplayed()
  {
    return false;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.application = ((UnveilApplication)getApplication());
    StateRestorationActivity.restoreState(paramBundle, this.application, this);
    setContentView(R.layout.map);
    this.mapView = ((UnveilMapView)findViewById(R.id.mapview));
    this.mapView.setBuiltInZoomControls(true);
    this.overlayManager = new MapOverlayManager(this, this.mapView);
    this.searchHistoryProvider = ((UnveilApplication)getApplication()).getSearchHistoryProvider();
    this.searchHistoryProvider.addListener(this);
    this.progressDialog = ProgressDialog.show(this, "", getString(R.string.map_please_wait), true, true);
    this.progressDialog.setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        MapHistoryActivity.this.finish();
      }
    });
    queryForSearchHistoryItems();
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    getSupportActionBar().setHomeButtonEnabled(true);
  }

  protected Dialog onCreateDialog(int paramInt)
  {
    if (!this.isShowingActivity)
      return null;
    switch (paramInt)
    {
    default:
      return super.onCreateDialog(paramInt);
    case 1:
    }
    return newDownloadErrorDialog();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    super.onCreateOptionsMenu(paramMenu);
    getSherlock().getMenuInflater().inflate(R.menu.map, paramMenu);
    paramMenu.findItem(R.id.send_feedback).setVisible(false);
    return true;
  }

  protected void onDestroy()
  {
    this.overlayManager.stop();
    super.onDestroy();
  }

  public void onItemsChanged(AbstractProvider paramAbstractProvider)
  {
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (paramMenuItem.getItemId() == R.id.help)
    {
      startActivity(new Intent(this, AboutActivity.class));
      return true;
    }
    if (paramMenuItem.getItemId() == 16908332)
    {
      onBackPressed();
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

  protected void onPause()
  {
    this.isShowingActivity = false;
    this.overlayManager.stop();
    super.onPause();
  }

  protected void onResume()
  {
    super.onResume();
    if (!this.application.userWantsHistory())
    {
      finish();
      return;
    }
    if (this.overlayManager.isCacheInvalid())
    {
      reload();
      return;
    }
    this.overlayManager.start();
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    StateRestorationActivity.saveState(paramBundle, this.application);
  }

  public boolean onSearchRequested()
  {
    if (this.application.getAuthState().isAuthenticated(AuthToken.AuthTokenType.SID))
      return super.onSearchRequested();
    return false;
  }

  protected void onStart()
  {
    super.onStart();
    this.isShowingActivity = true;
  }

  protected void onStop()
  {
    this.overlayManager.stop();
    super.onStop();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.MapHistoryActivity
 * JD-Core Version:    0.6.2
 */