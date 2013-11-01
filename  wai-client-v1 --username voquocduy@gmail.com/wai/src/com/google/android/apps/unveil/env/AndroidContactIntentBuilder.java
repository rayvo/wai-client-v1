package com.google.android.apps.unveil.env;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.ContactsContract.RawContacts;
import android.text.TextUtils;
import java.util.List;

public class AndroidContactIntentBuilder
{
  static final int TYPE_FAX = 5;
  static final int TYPE_HOME = 2;
  static final int TYPE_MAIN = 3;
  static final int TYPE_MOBILE = 4;
  static final int TYPE_OTHER = 0;
  static final int TYPE_WORK = 1;
  private static final int[] mapOfSdk5AddressType = { 3, 2, 1, 3, 3, 3 };
  private static final int[] mapOfSdk5EmailType;
  private static final int[] mapOfSdk5PhoneType = { 7, 3, 1, 12, 2, 13 };

  static
  {
    mapOfSdk5EmailType = new int[] { 3, 2, 1, 3, 3, 3 };
  }

  static int getType(String paramString)
  {
    if ("OTHER".equals(paramString));
    do
    {
      return 0;
      if ("WORK".equals(paramString))
        return 1;
      if ("HOME".equals(paramString))
        return 2;
      if ("MAIN".equals(paramString))
        return 3;
      if ("MOBILE".equals(paramString))
        return 4;
    }
    while (!"FAX".equals(paramString));
    return 5;
  }

  public Intent build(Context paramContext, Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.INSERT", ContactsContract.RawContacts.CONTENT_URI);
    String str1 = paramUri.getQueryParameter("name");
    if (!TextUtils.isEmpty(str1))
      localIntent.putExtra("name", str1);
    List localList1 = paramUri.getQueryParameters("pt");
    List localList2 = paramUri.getQueryParameters("pn");
    int i = localList1.size();
    int j = localList2.size();
    int k;
    List localList3;
    List localList4;
    int m;
    int n;
    if (i > j)
    {
      k = j;
      if (k > 0)
      {
        localIntent.putExtra("phone", (String)localList2.get(0));
        localIntent.putExtra("phone_type", mapOfSdk5PhoneType[getType((String)localList1.get(0))]);
      }
      if (k > 1)
      {
        localIntent.putExtra("secondary_phone", (String)localList2.get(1));
        localIntent.putExtra("secondary_phone_type", mapOfSdk5PhoneType[getType((String)localList1.get(1))]);
      }
      if (k > 2)
      {
        localIntent.putExtra("tertiary_phone", (String)localList2.get(2));
        localIntent.putExtra("tertiary_phone_type", mapOfSdk5PhoneType[getType((String)localList1.get(2))]);
      }
      localList3 = paramUri.getQueryParameters("et");
      localList4 = paramUri.getQueryParameters("em");
      m = localList3.size();
      n = localList4.size();
      if (m <= n)
        break label503;
    }
    label503: for (int i1 = n; ; i1 = m)
    {
      if (i1 > 0)
      {
        localIntent.putExtra("email", (String)localList4.get(0));
        localIntent.putExtra("email_type", mapOfSdk5EmailType[getType((String)localList3.get(0))]);
      }
      if (i1 > 1)
      {
        localIntent.putExtra("secondary_email", (String)localList4.get(1));
        localIntent.putExtra("secondary_email_type", mapOfSdk5EmailType[getType((String)localList3.get(1))]);
      }
      if (i1 > 2)
      {
        localIntent.putExtra("tertiary_email", (String)localList4.get(2));
        localIntent.putExtra("tertiary_email_type", mapOfSdk5EmailType[getType((String)localList3.get(2))]);
      }
      String str2 = paramUri.getQueryParameter("mailing_address");
      String str3 = paramUri.getQueryParameter("at");
      if (!TextUtils.isEmpty(str2))
      {
        localIntent.putExtra("postal", str2);
        localIntent.putExtra("postal_type", mapOfSdk5AddressType[getType(str3)]);
      }
      String str4 = paramUri.getQueryParameter("wb");
      if (!TextUtils.isEmpty(str4))
        localIntent.putExtra("notes", str4);
      return localIntent;
      k = i;
      break;
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.AndroidContactIntentBuilder
 * JD-Core Version:    0.6.2
 */