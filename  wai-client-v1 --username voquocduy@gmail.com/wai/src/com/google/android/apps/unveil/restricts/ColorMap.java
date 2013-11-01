package com.google.android.apps.unveil.restricts;

import android.content.Context;
import com.google.android.apps.unveil.R.string;
import com.google.goggles.RestrictsProtos.ColorEnum.Color;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public enum ColorMap
{
  public final int colorValue;
  private final List<RestrictsProtos.ColorEnum.Color> colors;
  private final int resId;

  static
  {
    RestrictsProtos.ColorEnum.Color[] arrayOfColor1 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor1[0] = RestrictsProtos.ColorEnum.Color.WHITE;
    WHITE = new ColorMap("WHITE", 0, -1, Arrays.asList(arrayOfColor1), R.string.restricts_color_white);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor2 = new RestrictsProtos.ColorEnum.Color[2];
    arrayOfColor2[0] = RestrictsProtos.ColorEnum.Color.GRAY;
    arrayOfColor2[1] = RestrictsProtos.ColorEnum.Color.GREY;
    GRAY = new ColorMap("GRAY", 1, -7829368, Arrays.asList(arrayOfColor2), R.string.restricts_color_gray);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor3 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor3[0] = RestrictsProtos.ColorEnum.Color.BLACK;
    BLACK = new ColorMap("BLACK", 2, -16777216, Arrays.asList(arrayOfColor3), R.string.restricts_color_white);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor4 = new RestrictsProtos.ColorEnum.Color[3];
    arrayOfColor4[0] = RestrictsProtos.ColorEnum.Color.RED;
    arrayOfColor4[1] = RestrictsProtos.ColorEnum.Color.CRIMSON;
    arrayOfColor4[2] = RestrictsProtos.ColorEnum.Color.ROSE;
    RED = new ColorMap("RED", 3, -65536, Arrays.asList(arrayOfColor4), R.string.restricts_color_red);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor5 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor5[0] = RestrictsProtos.ColorEnum.Color.MAROON;
    MAROON = new ColorMap("MAROON", 4, -7864320, Arrays.asList(arrayOfColor5), R.string.restricts_color_maroon);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor6 = new RestrictsProtos.ColorEnum.Color[2];
    arrayOfColor6[0] = RestrictsProtos.ColorEnum.Color.FUCHSIA;
    arrayOfColor6[1] = RestrictsProtos.ColorEnum.Color.MAGENTA;
    FUCHSIA = new ColorMap("FUCHSIA", 5, -65434, Arrays.asList(arrayOfColor6), R.string.restricts_color_fuchsia);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor7 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor7[0] = RestrictsProtos.ColorEnum.Color.PINK;
    PINK = new ColorMap("PINK", 6, -17477, Arrays.asList(arrayOfColor7), R.string.restricts_color_pink);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor8 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor8[0] = RestrictsProtos.ColorEnum.Color.ORANGE;
    ORANGE = new ColorMap("ORANGE", 7, -26368, Arrays.asList(arrayOfColor8), R.string.restricts_color_orange);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor9 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor9[0] = RestrictsProtos.ColorEnum.Color.CHARTREUSE;
    CHARTREUSE = new ColorMap("CHARTREUSE", 8, -13312, Arrays.asList(arrayOfColor9), R.string.restricts_color_chartreuse);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor10 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor10[0] = RestrictsProtos.ColorEnum.Color.YELLOW;
    YELLOW = new ColorMap("YELLOW", 9, -256, Arrays.asList(arrayOfColor10), R.string.restricts_color_yellow);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor11 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor11[0] = RestrictsProtos.ColorEnum.Color.LIME;
    LIME = new ColorMap("LIME", 10, -3342439, Arrays.asList(arrayOfColor11), R.string.restricts_color_lime);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor12 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor12[0] = RestrictsProtos.ColorEnum.Color.GREEN;
    GREEN = new ColorMap("GREEN", 11, -16737997, Arrays.asList(arrayOfColor12), R.string.restricts_color_green);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor13 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor13[0] = RestrictsProtos.ColorEnum.Color.TEAL;
    TEAL = new ColorMap("TEAL", 12, -16742264, Arrays.asList(arrayOfColor13), R.string.restricts_color_teal);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor14 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor14[0] = RestrictsProtos.ColorEnum.Color.AQUAMARINE;
    AQUAMARINE = new ColorMap("AQUAMARINE", 13, -8388652, Arrays.asList(arrayOfColor14), R.string.restricts_color_aquamarine);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor15 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor15[0] = RestrictsProtos.ColorEnum.Color.AZURE;
    AZURE = new ColorMap("AZURE", 14, -16744449, Arrays.asList(arrayOfColor15), R.string.restricts_color_azure);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor16 = new RestrictsProtos.ColorEnum.Color[2];
    arrayOfColor16[0] = RestrictsProtos.ColorEnum.Color.CYAN;
    arrayOfColor16[1] = RestrictsProtos.ColorEnum.Color.AQUA;
    CYAN = new ColorMap("CYAN", 15, -16711681, Arrays.asList(arrayOfColor16), R.string.restricts_color_cyan);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor17 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor17[0] = RestrictsProtos.ColorEnum.Color.BLUE;
    BLUE = new ColorMap("BLUE", 16, -13395457, Arrays.asList(arrayOfColor17), R.string.restricts_color_blue);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor18 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor18[0] = RestrictsProtos.ColorEnum.Color.NAVY;
    NAVY = new ColorMap("NAVY", 17, -16777080, Arrays.asList(arrayOfColor18), R.string.restricts_color_navy);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor19 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor19[0] = RestrictsProtos.ColorEnum.Color.LAVENDER;
    LAVENDER = new ColorMap("LAVENDER", 18, -3355393, Arrays.asList(arrayOfColor19), R.string.restricts_color_lavender);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor20 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor20[0] = RestrictsProtos.ColorEnum.Color.PURPLE;
    PURPLE = new ColorMap("PURPLE", 19, -6723841, Arrays.asList(arrayOfColor20), R.string.restricts_color_purple);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor21 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor21[0] = RestrictsProtos.ColorEnum.Color.INDIGO;
    INDIGO = new ColorMap("INDIGO", 20, -12320632, Arrays.asList(arrayOfColor21), R.string.restricts_color_indigo);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor22 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor22[0] = RestrictsProtos.ColorEnum.Color.IVORY;
    IVORY = new ColorMap("IVORY", 21, -18, Arrays.asList(arrayOfColor22), R.string.restricts_color_ivory);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor23 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor23[0] = RestrictsProtos.ColorEnum.Color.BEIGE;
    BEIGE = new ColorMap("BEIGE", 22, -657956, Arrays.asList(arrayOfColor23), R.string.restricts_color_beige);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor24 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor24[0] = RestrictsProtos.ColorEnum.Color.KHAKI;
    KHAKI = new ColorMap("KHAKI", 23, -4343957, Arrays.asList(arrayOfColor24), R.string.restricts_color_khaki);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor25 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor25[0] = RestrictsProtos.ColorEnum.Color.OLIVE;
    OLIVE = new ColorMap("OLIVE", 24, -7829504, Arrays.asList(arrayOfColor25), R.string.restricts_color_olive);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor26 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor26[0] = RestrictsProtos.ColorEnum.Color.SIENNA;
    SIENNA = new ColorMap("SIENNA", 25, -7852777, Arrays.asList(arrayOfColor26), R.string.restricts_color_sienna);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor27 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor27[0] = RestrictsProtos.ColorEnum.Color.BROWN;
    BROWN = new ColorMap("BROWN", 26, -11193600, Arrays.asList(arrayOfColor27), R.string.restricts_color_brown);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor28 = new RestrictsProtos.ColorEnum.Color[1];
    arrayOfColor28[0] = RestrictsProtos.ColorEnum.Color.SILVER;
    SILVER = new ColorMap("SILVER", 27, -4144960, Arrays.asList(arrayOfColor28), R.string.restricts_color_silver);
    RestrictsProtos.ColorEnum.Color[] arrayOfColor29 = new RestrictsProtos.ColorEnum.Color[2];
    arrayOfColor29[0] = RestrictsProtos.ColorEnum.Color.GOLD;
    arrayOfColor29[1] = RestrictsProtos.ColorEnum.Color.GOLDEN;
    GOLD = new ColorMap("GOLD", 28, -8960, Arrays.asList(arrayOfColor29), R.string.restricts_color_gold);
    ColorMap[] arrayOfColorMap = new ColorMap[29];
    arrayOfColorMap[0] = WHITE;
    arrayOfColorMap[1] = GRAY;
    arrayOfColorMap[2] = BLACK;
    arrayOfColorMap[3] = RED;
    arrayOfColorMap[4] = MAROON;
    arrayOfColorMap[5] = FUCHSIA;
    arrayOfColorMap[6] = PINK;
    arrayOfColorMap[7] = ORANGE;
    arrayOfColorMap[8] = CHARTREUSE;
    arrayOfColorMap[9] = YELLOW;
    arrayOfColorMap[10] = LIME;
    arrayOfColorMap[11] = GREEN;
    arrayOfColorMap[12] = TEAL;
    arrayOfColorMap[13] = AQUAMARINE;
    arrayOfColorMap[14] = AZURE;
    arrayOfColorMap[15] = CYAN;
    arrayOfColorMap[16] = BLUE;
    arrayOfColorMap[17] = NAVY;
    arrayOfColorMap[18] = LAVENDER;
    arrayOfColorMap[19] = PURPLE;
    arrayOfColorMap[20] = INDIGO;
    arrayOfColorMap[21] = IVORY;
    arrayOfColorMap[22] = BEIGE;
    arrayOfColorMap[23] = KHAKI;
    arrayOfColorMap[24] = OLIVE;
    arrayOfColorMap[25] = SIENNA;
    arrayOfColorMap[26] = BROWN;
    arrayOfColorMap[27] = SILVER;
    arrayOfColorMap[28] = GOLD;
  }

  private ColorMap(int paramInt1, List<RestrictsProtos.ColorEnum.Color> paramList, int paramInt2)
  {
    this.colorValue = paramInt1;
    this.colors = paramList;
    this.resId = paramInt2;
  }

  public static ColorMap getColorMap(RestrictsProtos.ColorEnum.Color paramColor)
  {
    for (ColorMap localColorMap : values())
    {
      Iterator localIterator = localColorMap.colors.iterator();
      while (localIterator.hasNext())
        if (((RestrictsProtos.ColorEnum.Color)localIterator.next()).equals(paramColor))
          return localColorMap;
    }
    throw new IllegalArgumentException("no map for color: " + paramColor.toString());
  }

  public String getName(Context paramContext)
  {
    return paramContext.getString(this.resId);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.restricts.ColorMap
 * JD-Core Version:    0.6.2
 */