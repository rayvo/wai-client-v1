package com.google.android.apps.unveil.ui.puggle;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.env.puggle.ParcelableQuery;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.protocol.QueryResponse;
import com.google.android.apps.unveil.restricts.QueryRestricts;
import com.google.android.apps.unveil.restricts.RestrictType;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.AnnotationResultProtos.AnnotationResult;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.ProductInformationProtos.ProductInformation;
import com.google.goggles.RestrictsProtos.Category;
import com.google.goggles.RestrictsProtos.Category.Domain;
import java.util.Iterator;
import java.util.List;

public class QuerySummaryView extends FrameLayout
{
  private static final UnveilLogger logger = new UnveilLogger();
  private CategorySelectedListener categorySelectedListener;
  private RestrictsProtos.Category selectedPrettyNameCategory;

  public QuerySummaryView(Context paramContext)
  {
    super(paramContext);
    LayoutInflater.from(paramContext).inflate(R.layout.query_summary, this);
    setupNewSearchButton();
  }

  public QuerySummaryView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    LayoutInflater.from(paramContext).inflate(R.layout.query_summary, this);
    setupNewSearchButton();
  }

  private RestrictsProtos.Category extractPrettyName(ParcelableQuery paramParcelableQuery, QueryResponse paramQueryResponse)
  {
    List localList = new QueryRestricts(paramParcelableQuery.getRestricts()).getValues(RestrictType.PRETTY_NAME_CATEGORY);
    if (!localList.isEmpty())
      return (RestrictsProtos.Category)localList.get(0);
    if ((paramQueryResponse.hasPuggleResults()) && (((ResultItem)paramQueryResponse.getPuggleResults().get(0)).getAnnotationResult().hasProductInfo()))
    {
      Iterator localIterator = ((ResultItem)paramQueryResponse.getPuggleResults().get(0)).getAnnotationResult().getProductInfo().getCategoriesList().iterator();
      while (localIterator.hasNext())
      {
        RestrictsProtos.Category localCategory = (RestrictsProtos.Category)localIterator.next();
        if (localCategory.getDomain() == RestrictsProtos.Category.Domain.PRETTY_NAME)
          return localCategory;
      }
    }
    return null;
  }

  private void setQueryImage(Picture paramPicture)
  {
    paramPicture.populateWithBitmap((ImageView)findViewById(R.id.query_image));
  }

  public void setCategoryClickListener(CategorySelectedListener paramCategorySelectedListener)
  {
    this.categorySelectedListener = paramCategorySelectedListener;
  }

  protected void setPrettyNameSpinner(RestrictsProtos.Category paramCategory, final List<RestrictsProtos.Category> paramList)
  {
    this.selectedPrettyNameCategory = paramCategory;
    Spinner localSpinner = (Spinner)findViewById(R.id.product_category_selector);
    localSpinner.setAdapter(new CategorySpinnerAdapter(paramList));
    RestrictsProtos.Category localCategory = this.selectedPrettyNameCategory;
    int i = 0;
    if (localCategory == null)
      localSpinner.setPromptId(R.string.category_selector_prompt);
    while (true)
    {
      localSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
      {
        public void onItemSelected(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          RestrictsProtos.Category localCategory = (RestrictsProtos.Category)paramList.get(paramAnonymousInt);
          if ((QuerySummaryView.this.selectedPrettyNameCategory != null) && (!TextUtils.equals(QuerySummaryView.this.selectedPrettyNameCategory.getName(), localCategory.getName())))
          {
            QuerySummaryView.access$002(QuerySummaryView.this, localCategory);
            QuerySummaryView.this.categorySelectedListener.onCategorySelected(QuerySummaryView.this.selectedPrettyNameCategory);
          }
        }

        public void onNothingSelected(AdapterView<?> paramAnonymousAdapterView)
        {
        }
      });
      return;
      do
        i++;
      while ((i < paramList.size()) && (!TextUtils.equals(this.selectedPrettyNameCategory.getName(), ((RestrictsProtos.Category)paramList.get(i)).getName())));
      if (i < paramList.size())
        localSpinner.setSelection(i);
      else
        localSpinner.setPromptId(R.string.category_selector_prompt);
    }
  }

  public void setQuery(ParcelableQuery paramParcelableQuery, QueryResponse paramQueryResponse)
  {
    setPrettyNameSpinner(extractPrettyName(paramParcelableQuery, paramQueryResponse), new QueryRestricts(paramQueryResponse.getSuggestedRestricts()).getValues(RestrictType.PRETTY_NAME_CATEGORY));
    setQueryImage(paramParcelableQuery.getCroppedPicture());
  }

  public void setupNewSearchButton()
  {
    if (findViewById(R.id.new_search) == null)
      return;
    findViewById(R.id.new_search).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        ((UnveilApplication)paramAnonymousView.getContext().getApplicationContext()).getClickTracker().logClick(NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_CLICK_NEW_SEARCH);
        paramAnonymousView.getContext().startActivity(UnveilApplication.makeCaptureActivityIntent(paramAnonymousView.getContext()));
      }
    });
  }

  public static abstract interface CategorySelectedListener
  {
    public abstract boolean onCategorySelected(RestrictsProtos.Category paramCategory);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.puggle.QuerySummaryView
 * JD-Core Version:    0.6.2
 */