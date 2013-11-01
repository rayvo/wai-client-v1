package com.google.android.apps.unveil.ui;

import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.google.android.apps.unveil.ui.utils.PagerAdapters;
import java.util.HashMap;
import java.util.Map;

public class CachingPagerAdapter<PageModel, InnerType extends AbstractListPagerAdapter<PageModel>> extends PagerAdapter
{
  private final Map<PageModel, View> cachedViews = new HashMap();
  private final InnerType decorated;

  private CachingPagerAdapter(InnerType paramInnerType)
  {
    this.decorated = paramInnerType;
  }

  private View getPageFor(int paramInt)
  {
    Object localObject = this.decorated.getItemAt(paramInt);
    if ((this.cachedViews.containsKey(localObject)) && (((View)this.cachedViews.get(localObject)).getParent() == null))
      return (View)this.cachedViews.get(localObject);
    View localView = this.decorated.createPage(localObject);
    this.cachedViews.put(localObject, localView);
    return localView;
  }

  public static <PageModel, InnerType extends AbstractListPagerAdapter<PageModel>> CachingPagerAdapter<PageModel, ? extends InnerType> wrap(InnerType paramInnerType)
  {
    return new CachingPagerAdapter(paramInnerType);
  }

  public void destroyItem(View paramView, int paramInt, Object paramObject)
  {
    this.decorated.destroyItem(paramView, paramInt, paramObject);
  }

  public void finishUpdate(View paramView)
  {
    this.decorated.finishUpdate(paramView);
  }

  public int getCount()
  {
    return this.decorated.getCount();
  }

  public InnerType getInner()
  {
    return this.decorated;
  }

  public Object instantiateItem(View paramView, int paramInt)
  {
    View localView = getPageFor(paramInt);
    PagerAdapters.attach((ViewPager)paramView, localView);
    return localView;
  }

  public boolean isViewFromObject(View paramView, Object paramObject)
  {
    return this.decorated.isViewFromObject(paramView, paramObject);
  }

  public void restoreState(Parcelable paramParcelable, ClassLoader paramClassLoader)
  {
    this.decorated.restoreState(paramParcelable, paramClassLoader);
  }

  public Parcelable saveState()
  {
    return this.decorated.saveState();
  }

  public void startUpdate(View paramView)
  {
    this.decorated.startUpdate(paramView);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.CachingPagerAdapter
 * JD-Core Version:    0.6.2
 */