package com.actionbarsherlock.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import com.actionbarsherlock.ActionBarSherlock;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.MenuInflater;

public abstract class SherlockMapActivity extends MapActivity
  implements ActionBarSherlock.OnCreatePanelMenuListener, ActionBarSherlock.OnPreparePanelListener, ActionBarSherlock.OnMenuItemSelectedListener, ActionBarSherlock.OnActionModeStartedListener, ActionBarSherlock.OnActionModeFinishedListener
{
  private ActionBarSherlock mSherlock;

  public void addContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getSherlock().addContentView(paramView, paramLayoutParams);
  }

  public void closeOptionsMenu()
  {
    if (!getSherlock().dispatchCloseOptionsMenu())
      super.closeOptionsMenu();
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (getSherlock().dispatchKeyEvent(paramKeyEvent))
      return true;
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  protected final ActionBarSherlock getSherlock()
  {
    if (this.mSherlock == null)
      this.mSherlock = ActionBarSherlock.wrap(this, 1);
    return this.mSherlock;
  }

  public ActionBar getSupportActionBar()
  {
    return getSherlock().getActionBar();
  }

  public MenuInflater getSupportMenuInflater()
  {
    return getSherlock().getMenuInflater();
  }

  public void invalidateOptionsMenu()
  {
    getSherlock().dispatchInvalidateOptionsMenu();
  }

  public void onActionModeFinished(ActionMode paramActionMode)
  {
  }

  public void onActionModeStarted(ActionMode paramActionMode)
  {
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    getSherlock().dispatchConfigurationChanged(paramConfiguration);
  }

  public final boolean onCreateOptionsMenu(android.view.Menu paramMenu)
  {
    return getSherlock().dispatchCreateOptionsMenu(paramMenu);
  }

  public boolean onCreateOptionsMenu(com.actionbarsherlock.view.Menu paramMenu)
  {
    return true;
  }

  public boolean onCreatePanelMenu(int paramInt, com.actionbarsherlock.view.Menu paramMenu)
  {
    if (paramInt == 0)
      return onCreateOptionsMenu(paramMenu);
    return false;
  }

  public boolean onMenuItemSelected(int paramInt, com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    if (paramInt == 0)
      return onOptionsItemSelected(paramMenuItem);
    return false;
  }

  public final boolean onMenuOpened(int paramInt, android.view.Menu paramMenu)
  {
    if (getSherlock().dispatchMenuOpened(paramInt, paramMenu))
      return true;
    return super.onMenuOpened(paramInt, paramMenu);
  }

  public final boolean onOptionsItemSelected(android.view.MenuItem paramMenuItem)
  {
    return getSherlock().dispatchOptionsItemSelected(paramMenuItem);
  }

  public boolean onOptionsItemSelected(com.actionbarsherlock.view.MenuItem paramMenuItem)
  {
    return false;
  }

  public void onPanelClosed(int paramInt, android.view.Menu paramMenu)
  {
    getSherlock().dispatchPanelClosed(paramInt, paramMenu);
    super.onPanelClosed(paramInt, paramMenu);
  }

  protected void onPause()
  {
    getSherlock().dispatchPause();
    super.onPause();
  }

  protected void onPostCreate(Bundle paramBundle)
  {
    getSherlock().dispatchPostCreate(paramBundle);
    super.onPostCreate(paramBundle);
  }

  protected void onPostResume()
  {
    super.onPostResume();
    getSherlock().dispatchPostResume();
  }

  public final boolean onPrepareOptionsMenu(android.view.Menu paramMenu)
  {
    return getSherlock().dispatchPrepareOptionsMenu(paramMenu);
  }

  public boolean onPrepareOptionsMenu(com.actionbarsherlock.view.Menu paramMenu)
  {
    return true;
  }

  public boolean onPreparePanel(int paramInt, View paramView, com.actionbarsherlock.view.Menu paramMenu)
  {
    if (paramInt == 0)
      return onPrepareOptionsMenu(paramMenu);
    return false;
  }

  protected void onStop()
  {
    getSherlock().dispatchStop();
    super.onStop();
  }

  protected void onTitleChanged(CharSequence paramCharSequence, int paramInt)
  {
    getSherlock().dispatchTitleChanged(paramCharSequence, paramInt);
    super.onTitleChanged(paramCharSequence, paramInt);
  }

  public void openOptionsMenu()
  {
    if (!getSherlock().dispatchOpenOptionsMenu())
      super.openOptionsMenu();
  }

  public void requestWindowFeature(long paramLong)
  {
    getSherlock().requestFeature((int)paramLong);
  }

  public void setContentView(int paramInt)
  {
    getSherlock().setContentView(paramInt);
  }

  public void setContentView(View paramView)
  {
    getSherlock().setContentView(paramView);
  }

  public void setContentView(View paramView, ViewGroup.LayoutParams paramLayoutParams)
  {
    getSherlock().setContentView(paramView, paramLayoutParams);
  }

  public void setSupportProgress(int paramInt)
  {
    getSherlock().setProgress(paramInt);
  }

  public void setSupportProgressBarIndeterminate(boolean paramBoolean)
  {
    getSherlock().setProgressBarIndeterminate(paramBoolean);
  }

  public void setSupportProgressBarIndeterminateVisibility(boolean paramBoolean)
  {
    getSherlock().setProgressBarIndeterminateVisibility(paramBoolean);
  }

  public void setSupportProgressBarVisibility(boolean paramBoolean)
  {
    getSherlock().setProgressBarVisibility(paramBoolean);
  }

  public void setSupportSecondaryProgress(int paramInt)
  {
    getSherlock().setSecondaryProgress(paramInt);
  }

  public ActionMode startActionMode(ActionMode.Callback paramCallback)
  {
    return getSherlock().startActionMode(paramCallback);
  }

  public void supportInvalidateOptionsMenu()
  {
    invalidateOptionsMenu();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.actionbarsherlock.app.SherlockMapActivity
 * JD-Core Version:    0.6.2
 */