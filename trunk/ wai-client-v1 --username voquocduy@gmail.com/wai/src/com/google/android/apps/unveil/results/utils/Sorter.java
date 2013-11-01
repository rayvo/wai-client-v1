package com.google.android.apps.unveil.results.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.res.Resources;
import android.text.TextUtils;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.puggle.PuggleResultItems.PriceComparator;
import com.google.android.apps.unveil.results.PuggleResultItem;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Sorter
{
  private final Context context;
  private String currentSortType;
  private SortedResultsCallback onSortedResultsListener;
  private final String[] sortTypes;

  public Sorter(Context paramContext)
  {
    this(paramContext, paramContext.getResources().getString(R.string.sort_relevance));
  }

  public Sorter(Context paramContext, String paramString)
  {
    this.context = paramContext;
    String[] arrayOfString = new String[3];
    arrayOfString[0] = paramContext.getResources().getString(R.string.sort_relevance);
    arrayOfString[1] = paramContext.getResources().getString(R.string.sort_price_ascending);
    arrayOfString[2] = paramContext.getResources().getString(R.string.sort_price_descending);
    this.sortTypes = arrayOfString;
    if (paramString != null)
    {
      this.currentSortType = paramString;
      return;
    }
    this.currentSortType = paramContext.getResources().getString(R.string.sort_relevance);
  }

  private void clearRelatedItems(List<PuggleResultItem> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      ((PuggleResultItem)localIterator.next()).setRelatedItems(Collections.emptyList());
  }

  private List<PuggleResultItem> collapseDuplicates(List<PuggleResultItem> paramList)
  {
    Map localMap = groupResultsByImage(paramList);
    linkRelatedResultsToResults(localMap);
    LinkedList localLinkedList = new LinkedList(paramList);
    Iterator localIterator = localLinkedList.iterator();
    while (localIterator.hasNext())
    {
      PuggleResultItem localPuggleResultItem = (PuggleResultItem)localIterator.next();
      String str = localPuggleResultItem.getClusterId();
      if ((str != null) && (((List)localMap.get(str)).get(0) != localPuggleResultItem))
        localIterator.remove();
    }
    return localLinkedList;
  }

  private Map<String, List<PuggleResultItem>> groupResultsByImage(List<PuggleResultItem> paramList)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      PuggleResultItem localPuggleResultItem = (PuggleResultItem)localIterator.next();
      String str = localPuggleResultItem.getClusterId();
      if (!localHashMap.containsKey(str))
        localHashMap.put(str, new LinkedList());
      ((List)localHashMap.get(str)).add(localPuggleResultItem);
    }
    return localHashMap;
  }

  private void linkRelatedResultsToResults(Map<String, List<PuggleResultItem>> paramMap)
  {
    Iterator localIterator = paramMap.values().iterator();
    while (localIterator.hasNext())
    {
      List localList = (List)localIterator.next();
      if (localList.size() > 1)
        ((PuggleResultItem)localList.get(0)).setRelatedItems(localList.subList(1, localList.size()));
    }
  }

  public static Sorter newSorter(Context paramContext, String paramString)
  {
    if (TextUtils.isEmpty(paramString))
      return new Sorter(paramContext);
    return new Sorter(paramContext, paramString);
  }

  private List<PuggleResultItem> sortByPrice(List<PuggleResultItem> paramList, boolean paramBoolean)
  {
    ArrayList localArrayList = new ArrayList(paramList);
    Collections.sort(localArrayList, new PuggleResultItems.PriceComparator(paramBoolean));
    return localArrayList;
  }

  public String getDisplayString()
  {
    return this.currentSortType;
  }

  public int getSortTypeIndex(String paramString)
  {
    for (int i = 0; i < this.sortTypes.length; i++)
      if (this.sortTypes[i] == paramString)
        return i;
    return -1;
  }

  public void setOnSortedResults(SortedResultsCallback paramSortedResultsCallback)
  {
    this.onSortedResultsListener = paramSortedResultsCallback;
  }

  public void show(final List<PuggleResultItem> paramList)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setTitle(R.string.sort_title_label);
    localBuilder.setSingleChoiceItems(this.sortTypes, getSortTypeIndex(this.currentSortType), new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Sorter.access$002(Sorter.this, Sorter.this.sortTypes[paramAnonymousInt]);
        Sorter.this.onSortedResultsListener.onSortedResults(Sorter.this.getDisplayString(), Sorter.this.sort(paramList));
        paramAnonymousDialogInterface.dismiss();
      }
    });
    localBuilder.show();
  }

  public List<PuggleResultItem> sort(List<PuggleResultItem> paramList)
  {
    Object localObject;
    if (this.currentSortType.equals(this.context.getResources().getString(R.string.sort_relevance)))
      localObject = new ArrayList(paramList);
    while (true)
    {
      clearRelatedItems((List)localObject);
      return collapseDuplicates((List)localObject);
      if (this.currentSortType.equals(this.context.getResources().getString(R.string.sort_price_ascending)))
      {
        localObject = sortByPrice(paramList, true);
      }
      else
      {
        if (!this.currentSortType.equals(this.context.getResources().getString(R.string.sort_price_descending)))
          break;
        localObject = sortByPrice(paramList, false);
      }
    }
    throw new AssertionError("Current sort type has not been set.");
  }

  public static abstract interface SortedResultsCallback
  {
    public abstract void onSortedResults(String paramString, List<PuggleResultItem> paramList);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.results.utils.Sorter
 * JD-Core Version:    0.6.2
 */