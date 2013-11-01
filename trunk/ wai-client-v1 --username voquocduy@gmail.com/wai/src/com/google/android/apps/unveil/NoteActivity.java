package com.google.android.apps.unveil;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.google.android.apps.unveil.env.Picture;
import com.google.android.apps.unveil.env.UnveilLogger;
import com.google.android.apps.unveil.history.SearchHistoryItem;
import com.google.android.apps.unveil.history.SearchHistoryProvider;
import com.google.android.apps.unveil.history.SearchHistoryProvider.UpdateListener;
import com.google.android.apps.unveil.protocol.QueryPipeline;

public class NoteActivity extends StateRestorationActivity
{
  private static final UnveilLogger logger = new UnveilLogger();
  private UnveilContext application;
  private View contentView;
  private SearchHistoryProvider searchHistoryProvider;

  private void dismissProgressDialog(final ProgressDialog paramProgressDialog)
  {
    new Handler().post(new Runnable()
    {
      public void run()
      {
        paramProgressDialog.dismiss();
      }
    });
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.contentView = ((LayoutInflater)getSystemService("layout_inflater")).inflate(R.layout.note, null);
    setContentView(this.contentView);
    this.application = ((UnveilApplication)getApplication());
    this.searchHistoryProvider = this.application.getSearchHistoryProvider();
    final SearchHistoryItem localSearchHistoryItem = (SearchHistoryItem)getIntent().getSerializableExtra("search_history_item");
    Picture localPicture = this.application.getQueryPipeline().getPicture();
    if (localPicture != null)
      localPicture.populateWithBitmap((ImageView)findViewById(R.id.thumbnail));
    final EditText localEditText = (EditText)findViewById(R.id.note_text);
    localEditText.setText(localSearchHistoryItem.getNote());
    InputFilter[] arrayOfInputFilter = new InputFilter[1];
    arrayOfInputFilter[0] = new MaxUTF8BytesFilter(4096);
    localEditText.setFilters(arrayOfInputFilter);
    ((Button)findViewById(R.id.save_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        final ProgressDialog localProgressDialog = ProgressDialog.show(NoteActivity.this, "", NoteActivity.this.getString(R.string.saving), true, false);
        NoteActivity.this.searchHistoryProvider.updateNote(localSearchHistoryItem.getMomentId(), localEditText.getText().toString(), new SearchHistoryProvider.UpdateListener()
        {
          public void onError()
          {
            NoteActivity.this.dismissProgressDialog(localProgressDialog);
            Toast.makeText(NoteActivity.this, NoteActivity.this.getText(R.string.add_note_problem), 1).show();
          }

          public void onResult(SearchHistoryItem paramAnonymous2SearchHistoryItem)
          {
            NoteActivity.this.dismissProgressDialog(localProgressDialog);
            Toast.makeText(NoteActivity.this, NoteActivity.this.getText(R.string.note_updated), 1).show();
            Intent localIntent = new Intent();
            localIntent.putExtra("search_history_item", paramAnonymous2SearchHistoryItem);
            NoteActivity.this.setResult(-1, localIntent);
            NoteActivity.this.finish();
          }
        });
      }
    });
    ((Button)findViewById(R.id.cancel_button)).setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        NoteActivity.this.finish();
      }
    });
  }

  static class MaxUTF8BytesFilter
    implements InputFilter
  {
    private final int mMaxBytes;

    MaxUTF8BytesFilter(int paramInt)
    {
      this.mMaxBytes = paramInt;
    }

    public CharSequence filter(CharSequence paramCharSequence, int paramInt1, int paramInt2, Spanned paramSpanned, int paramInt3, int paramInt4)
    {
      if (paramCharSequence.length() + paramSpanned.length() < this.mMaxBytes / 4);
      while (paramCharSequence.subSequence(paramInt1, paramInt2).toString().getBytes().length + (paramSpanned.toString().getBytes().length - paramSpanned.subSequence(paramInt3, paramInt4).toString().getBytes().length) <= this.mMaxBytes)
        return null;
      return "";
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.NoteActivity
 * JD-Core Version:    0.6.2
 */