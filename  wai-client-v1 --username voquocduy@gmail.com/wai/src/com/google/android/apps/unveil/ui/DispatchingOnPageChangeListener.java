package com.google.android.apps.unveil.ui;

import android.support.v4.view.ViewPager.OnPageChangeListener;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class DispatchingOnPageChangeListener
  implements ViewPager.OnPageChangeListener
{
  private final List<ViewPager.OnPageChangeListener> listeners;

  private DispatchingOnPageChangeListener(List<ViewPager.OnPageChangeListener> paramList)
  {
    this.listeners = Collections.unmodifiableList(paramList);
  }

  public static ViewPager.OnPageChangeListener wrap(ViewPager.OnPageChangeListener[] paramArrayOfOnPageChangeListener)
  {
    return new DispatchingOnPageChangeListener(Arrays.asList(paramArrayOfOnPageChangeListener));
  }

  public void onPageScrollStateChanged(int paramInt)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((ViewPager.OnPageChangeListener)localIterator.next()).onPageScrollStateChanged(paramInt);
  }

  public void onPageScrolled(int paramInt1, float paramFloat, int paramInt2)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((ViewPager.OnPageChangeListener)localIterator.next()).onPageScrolled(paramInt1, paramFloat, paramInt2);
  }

  public void onPageSelected(int paramInt)
  {
    Iterator localIterator = this.listeners.iterator();
    while (localIterator.hasNext())
      ((ViewPager.OnPageChangeListener)localIterator.next()).onPageSelected(paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.DispatchingOnPageChangeListener
 * JD-Core Version:    0.6.2
 */