package com.google.android.apps.unveil.ui.result.swipe;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.google.android.apps.unveil.R.id;
import com.google.android.apps.unveil.R.layout;
import com.google.android.apps.unveil.env.Picture;

public class SwipeableCell extends LinearLayout
{
  final ImageView imageView;
  final TextView nameView;
  Picture picture;

  public SwipeableCell(Context paramContext, int paramInt)
  {
    super(paramContext);
    LinearLayout localLinearLayout = (LinearLayout)LayoutInflater.from(paramContext).inflate(R.layout.swipeable_cell, null);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    addView(localLinearLayout);
    this.imageView = ((ImageView)findViewById(R.id.image));
    this.nameView = ((TextView)findViewById(R.id.name));
    this.imageView.setLayoutParams(new LinearLayout.LayoutParams(paramInt, paramInt));
    this.nameView.setLayoutParams(new LinearLayout.LayoutParams(paramInt, -2));
  }

  public void setImage(Picture paramPicture)
  {
    this.picture = paramPicture;
    paramPicture.populateWithBitmap(this.imageView);
  }

  public void setName(String paramString)
  {
    this.nameView.setText(paramString);
    this.nameView.setVisibility(0);
  }

  public void setNameVisibility(int paramInt)
  {
    this.nameView.setVisibility(paramInt);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.ui.result.swipe.SwipeableCell
 * JD-Core Version:    0.6.2
 */