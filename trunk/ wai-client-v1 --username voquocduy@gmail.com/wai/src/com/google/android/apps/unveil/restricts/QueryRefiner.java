package com.google.android.apps.unveil.restricts;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.R.string;
import com.google.android.apps.unveil.UnveilApplication;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.protocol.ClickTracker;
import com.google.android.apps.unveil.results.ResultItem;
import com.google.goggles.NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET;
import com.google.goggles.RestrictsProtos.Restricts;
import java.util.List;

public class QueryRefiner
{
  private static final boolean FLAG_COLORS_FROM_BACKEND = true;
  private static final UnveilLogger logger = new UnveilLogger();
  private SuggestionsListener clickListener;
  private final Context context;
  private final QueryRestricts selectedRestricts;
  private final QueryRestricts suggestedRestricts;
  private AlertDialog suggestionsDialog;

  public QueryRefiner(Context paramContext, RestrictsProtos.Restricts paramRestricts1, RestrictsProtos.Restricts paramRestricts2, List<ResultItem> paramList)
  {
    this.context = paramContext;
    this.selectedRestricts = new QueryRestricts(paramRestricts2);
    this.suggestedRestricts = new QueryRestricts(paramRestricts1);
    if (paramList != null)
      this.suggestedRestricts.addAllGendersInResults(paramList);
    this.suggestedRestricts.removeRestrictsWithTooFewOptions();
  }

  public void close()
  {
    this.suggestionsDialog.dismiss();
  }

  public boolean isEmpty()
  {
    return (this.suggestedRestricts.isEmpty()) && (this.selectedRestricts.isEmpty());
  }

  public void logRefinementClick(RestrictType paramRestrictType, int paramInt)
  {
    NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET localCLICK_TARGET;
    if (paramRestrictType == RestrictType.PRODUCT_CATEGORY)
      localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_REFINE_BY_CATEGORY;
    while (true)
    {
      ((UnveilApplication)this.context.getApplicationContext()).getClickTracker().logClick(localCLICK_TARGET);
      do
      {
        return;
        if (paramRestrictType == RestrictType.BRAND)
        {
          localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_REFINE_BY_BRAND;
          break;
        }
      }
      while (paramRestrictType != RestrictType.GENDER);
      localCLICK_TARGET = NativeClientLoggingProtos.NativeClientClick.CLICK_TARGET.PUGGLE_REFINE_BY_GENDER;
    }
  }

  public void setOnClickListener(SuggestionsListener paramSuggestionsListener)
  {
    this.clickListener = paramSuggestionsListener;
  }

  public void show()
  {
    if (isEmpty())
    {
      logger.e("Should never call show() when there are no suggestions.", new Object[0]);
      return;
    }
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    final RefinementAdapter localRefinementAdapter = new RefinementAdapter(this.suggestedRestricts, this.selectedRestricts, this.clickListener);
    localBuilder.setAdapter(localRefinementAdapter, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface.dismiss();
        if (localRefinementAdapter.isSelectedSuggestion(paramAnonymousInt))
        {
          QueryRefiner.this.selectedRestricts.remove(localRefinementAdapter.getItem(paramAnonymousInt));
          QueryRefiner.this.clickListener.onRestrictClick(QueryRefiner.this.selectedRestricts.buildRestricts());
          return;
        }
        RestrictType localRestrictType = localRefinementAdapter.getItem(paramAnonymousInt);
        if (localRestrictType == RestrictType.COLOR)
        {
          QueryRefiner.this.showColorDialog();
          return;
        }
        QueryRefiner.this.showTextDialog(localRestrictType);
      }
    });
    localBuilder.setTitle(R.string.filter_title_label);
    this.suggestionsDialog = localBuilder.show();
  }

  public void showColorDialog()
  {
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(this.context).inflate(R.layout.color_dialog, null);
    final GridView localGridView = (GridView)localLinearLayout.findViewById(R.id.color_grid);
    localGridView.setAdapter(new ColorRestrictsAdapter(this.context, this.suggestedRestricts.getValues(RestrictType.COLOR)));
    localGridView.setOnItemClickListener(new AdapterView.OnItemClickListener()
    {
      public void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
      {
        QueryRefiner.this.selectedRestricts.setRestrict(RestrictType.COLOR, localGridView.getAdapter().getItem(paramAnonymousInt));
        QueryRefiner.this.clickListener.onRestrictClick(QueryRefiner.this.selectedRestricts.buildRestricts());
      }
    });
    this.suggestionsDialog = new AlertDialog.Builder(this.context).setView(localLinearLayout).setTitle(RestrictType.COLOR.getName(this.context)).show();
  }

  public void showTextDialog(final RestrictType paramRestrictType)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(this.context);
    localBuilder.setTitle(paramRestrictType.getName(this.context));
    final TextRestrictsAdapter localTextRestrictsAdapter = new TextRestrictsAdapter(paramRestrictType, this.suggestedRestricts.getValues(paramRestrictType));
    this.suggestionsDialog = localBuilder.setAdapter(localTextRestrictsAdapter, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        QueryRefiner.this.logRefinementClick(paramRestrictType, paramAnonymousInt);
        QueryRefiner.this.selectedRestricts.setRestrict(paramRestrictType, localTextRestrictsAdapter.getItem(paramAnonymousInt));
        QueryRefiner.this.clickListener.onRestrictClick(QueryRefiner.this.selectedRestricts.buildRestricts());
      }
    }).show();
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.QueryRefiner
 * JD-Core Version:    0.6.2
 */