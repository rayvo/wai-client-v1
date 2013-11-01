package com.google.android.apps.unveil.ui;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.google.android.apps.unveil.ui.utils.PagerAdapters;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListPagerAdapter<PageModel> extends PagerAdapter
{
  private final ArrayList<PageModel> models;

  protected AbstractListPagerAdapter(List<? extends PageModel> paramList)
  {
    this.models = new ArrayList(paramList);
  }

  protected abstract View createPage(PageModel paramPageModel);

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    ((ViewPager)paramView).removeView((View)paramObject);
  }

  public void finishUpdate(View paramView)
  {
  }

  public int getCount()
  {
    return this.models.size();
  }

  protected PageModel getItemAt(int paramInt)
  {
    return this.models.get(paramInt);
  }

  public Object instantiateItem(View paramView, int paramInt)
  {
    View localView = createPage(getItemAt(paramInt));
    PagerAdapters.attach((ViewPager)paramView, localView);
    return localView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return paramView == (View)paramObject;
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
  }

  public Parcelable saveState()
  {
    return null;
  }

  public void startUpdate(View paramView)
  {
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.AbstractListPagerAdapter
 * JD-Core Version:    0.6.2
 */