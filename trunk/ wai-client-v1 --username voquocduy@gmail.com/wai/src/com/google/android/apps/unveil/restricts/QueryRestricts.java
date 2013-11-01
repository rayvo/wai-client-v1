package com.google.android.apps.unveil.restricts;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Category.Domain;
import com.google.goggles.RestrictsProtos.ColorEnum.Color;
import com.google.goggles.RestrictsProtos.Restricts;
import com.google.goggles.RestrictsProtos.Restricts.Builder;
import com.google.goggles.RestrictsProtos.Restricts.Gender;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class QueryRestricts
{
  private static boolean EXCLUDE_PRETTY_NAME = true;
  private static final UnveilLogger logger = new UnveilLogger();
  private final List<RestrictType> activeRestrictTypes;
  private final List<RestrictsProtos.Category> prettyNameCategories;
  private final List<RestrictsProtos.Category> productCategories;
  private final RestrictsProtos.Restricts.Builder restricts;

  public QueryRestricts()
  {
    this(null);
  }

  public QueryRestricts(RestrictsProtos.Restricts paramRestricts)
  {
    if (paramRestricts != null);
    for (this.restricts = paramRestricts.toBuilder(); ; this.restricts = RestrictsProtos.Restricts.newBuilder())
    {
      this.activeRestrictTypes = new ArrayList();
      this.prettyNameCategories = new ArrayList();
      this.productCategories = new ArrayList();
      populateCategoryLists();
      populateActiveRestrictTypes();
      return;
    }
  }

  public static String getDisplayableString(Object paramObject, Context paramContext)
  {
    if ((paramObject instanceof String))
      return (String)paramObject;
    if ((paramObject instanceof RestrictsProtos.Category))
    {
      String str = ((RestrictsProtos.Category)paramObject).getDisplayName();
      if (TextUtils.isEmpty(str))
        str = ((RestrictsProtos.Category)paramObject).getName();
      return str;
    }
    if ((paramObject instanceof RestrictsProtos.Restricts.Gender))
      return GenderMap.valueOf(paramObject.toString()).getName(paramContext);
    if ((paramObject instanceof RestrictsProtos.ColorEnum.Color))
      return ColorMap.getColorMap((RestrictsProtos.ColorEnum.Color)paramObject).getName(paramContext);
    return "";
  }

  public static String getSummaryString(RestrictsProtos.Restricts paramRestricts, Context paramContext)
  {
    QueryRestricts localQueryRestricts = new QueryRestricts(paramRestricts);
    if (localQueryRestricts.getActiveRestrictTypes().size() == 0)
      return "";
    String str = paramContext.getString(R.string.restricts_label) + " ";
    Iterator localIterator1 = localQueryRestricts.getActiveRestrictTypes().iterator();
    while (localIterator1.hasNext())
    {
      Iterator localIterator2 = localQueryRestricts.getValues((RestrictType)localIterator1.next()).iterator();
      while (localIterator2.hasNext())
      {
        Object localObject = localIterator2.next();
        str = str + getDisplayableString(localObject, paramContext) + ", ";
      }
    }
    return str.substring(0, -2 + str.length());
  }

  private void populateActiveRestrictTypes()
  {
    this.activeRestrictTypes.clear();
    RestrictType[] arrayOfRestrictType = RestrictType.values();
    int i = arrayOfRestrictType.length;
    int j = 0;
    if (j < i)
    {
      RestrictType localRestrictType = arrayOfRestrictType[j];
      if ((EXCLUDE_PRETTY_NAME) && (localRestrictType == RestrictType.PRETTY_NAME_CATEGORY));
      while (true)
      {
        j++;
        break;
        if (getValues(localRestrictType).size() > 0)
          this.activeRestrictTypes.add(localRestrictType);
      }
    }
  }

  private void populateCategoryLists()
  {
    Iterator localIterator = this.restricts.getCategoriesList().iterator();
    while (localIterator.hasNext())
    {
      RestrictsProtos.Category localCategory = (RestrictsProtos.Category)localIterator.next();
      if (localCategory.getDomain() == RestrictsProtos.Category.Domain.PRODUCT)
        this.productCategories.add(localCategory);
      else if (localCategory.getDomain() == RestrictsProtos.Category.Domain.PRETTY_NAME)
        this.prettyNameCategories.add(localCategory);
    }
    Collections.sort(this.productCategories, new Comparator()
    {
      public int compare(RestrictsProtos.Category paramAnonymousCategory1, RestrictsProtos.Category paramAnonymousCategory2)
      {
        return paramAnonymousCategory1.getDisplayName().compareTo(paramAnonymousCategory2.getDisplayName());
      }
    });
  }

  public void addAllColors()
  {
    this.restricts.clearColors();
    for (RestrictsProtos.ColorEnum.Color localColor : RestrictsProtos.ColorEnum.Color.values())
      this.restricts.addColors(localColor);
    populateActiveRestrictTypes();
  }

  public void addAllGendersInResults(List<? extends ResultItem> paramList)
  {
    HashSet localHashSet = new HashSet(this.restricts.getGendersList());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
      localHashSet.add(((ResultItem)localIterator.next()).getAnnotationResult().getProductInfo().getGender());
    this.restricts.addAllGenders(localHashSet);
    populateActiveRestrictTypes();
  }

  public RestrictsProtos.Restricts buildRestricts()
  {
    this.restricts.clearCategories();
    this.restricts.addAllCategories(this.productCategories);
    this.restricts.addAllCategories(this.prettyNameCategories);
    return this.restricts.build();
  }

  public List<RestrictType> getActiveRestrictTypes()
  {
    return Collections.unmodifiableList(this.activeRestrictTypes);
  }

  public List<? extends Object> getValues(RestrictType paramRestrictType)
  {
    switch (2.$SwitchMap$com$google$android$apps$unveil$restricts$RestrictType[paramRestrictType.ordinal()])
    {
    default:
      return Collections.emptyList();
    case 1:
      return this.restricts.getBrandsList();
    case 2:
      return this.productCategories;
    case 3:
      return this.prettyNameCategories;
    case 4:
      return this.restricts.getColorsList();
    case 5:
    }
    return this.restricts.getGendersList();
  }

  public boolean isEmpty()
  {
    int i = 0;
    RestrictType[] arrayOfRestrictType = RestrictType.values();
    int j = arrayOfRestrictType.length;
    int k = 0;
    if (k < j)
    {
      RestrictType localRestrictType = arrayOfRestrictType[k];
      if ((EXCLUDE_PRETTY_NAME) && (localRestrictType == RestrictType.PRETTY_NAME_CATEGORY));
      while (true)
      {
        k++;
        break;
        i += getValues(localRestrictType).size();
      }
    }
    return i == 0;
  }

  public void remove(RestrictType paramRestrictType)
  {
    switch (2.$SwitchMap$com$google$android$apps$unveil$restricts$RestrictType[paramRestrictType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      populateActiveRestrictTypes();
      return;
      this.restricts.clearBrands();
      continue;
      this.productCategories.clear();
      continue;
      this.prettyNameCategories.clear();
      continue;
      this.restricts.clearColors();
      continue;
      this.restricts.clearGenders();
    }
  }

  public void removeRestrictsWithTooFewOptions()
  {
    if (this.restricts.getBrandsCount() == 1)
      this.restricts.clearBrands();
    if (this.productCategories.size() == 1)
      this.productCategories.clear();
    if (this.prettyNameCategories.size() == 1)
      this.prettyNameCategories.clear();
    if (this.restricts.getColorsCount() == 1)
      this.restricts.clearColors();
    if (this.restricts.getGendersCount() == 1)
      this.restricts.clearGenders();
    populateActiveRestrictTypes();
  }

  public void setRestrict(RestrictType paramRestrictType, Object paramObject)
  {
    switch (2.$SwitchMap$com$google$android$apps$unveil$restricts$RestrictType[paramRestrictType.ordinal()])
    {
    default:
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
    }
    while (true)
    {
      populateActiveRestrictTypes();
      return;
      if ((paramObject instanceof String))
      {
        this.restricts.clearBrands();
        this.restricts.addBrands((String)paramObject);
        continue;
        if (((paramObject instanceof RestrictsProtos.Category)) && (((RestrictsProtos.Category)paramObject).getDomain() == RestrictsProtos.Category.Domain.PRODUCT))
        {
          this.productCategories.clear();
          this.productCategories.add((RestrictsProtos.Category)paramObject);
          continue;
          if (((paramObject instanceof RestrictsProtos.Category)) && (((RestrictsProtos.Category)paramObject).getDomain() == RestrictsProtos.Category.Domain.PRETTY_NAME))
          {
            this.prettyNameCategories.clear();
            this.prettyNameCategories.add((RestrictsProtos.Category)paramObject);
            continue;
            if ((paramObject instanceof RestrictsProtos.ColorEnum.Color))
            {
              this.restricts.clearColors();
              this.restricts.addColors((RestrictsProtos.ColorEnum.Color)paramObject);
              continue;
              if ((paramObject instanceof RestrictsProtos.Restricts.Gender))
              {
                this.restricts.clearGenders();
                this.restricts.addGenders((RestrictsProtos.Restricts.Gender)paramObject);
              }
            }
          }
        }
      }
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.QueryRestricts
 * JD-Core Version:    0.6.2
 */