package com.google.android.apps.unveil.env;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.FloatMath;
import com.google.goggles.BoundingBoxProtos.BoundingBox;
import com.google.goggles.BoundingBoxProtos.BoundingBox.Builder;

public class GeometryUtils
{
  private static final UnveilLogger logger = new UnveilLogger();

  public static float distance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return FloatMath.sqrt(squareDistance(paramFloat1, paramFloat2, paramFloat3, paramFloat4));
  }

  public static float distance(Point paramPoint1, Point paramPoint2)
  {
    return distance(paramPoint1.x, paramPoint1.y, paramPoint2.x, paramPoint2.y);
  }

  public static float distance(PointF paramPointF1, PointF paramPointF2)
  {
    return distance(paramPointF1.x, paramPointF1.y, paramPointF2.x, paramPointF2.y);
  }

  public static Rect fitBox(Size paramSize1, Size paramSize2)
  {
    if (paramSize2.aspectRatio() > paramSize1.aspectRatio())
    {
      int k = paramSize2.height * paramSize1.width / paramSize2.width;
      int m = (paramSize1.height - k) / 2;
      return new Rect(0, m, paramSize1.width, k + m);
    }
    int i = paramSize2.width * paramSize1.height / paramSize2.height;
    int j = (paramSize1.width - i) / 2;
    return new Rect(j, 0, j + i, paramSize1.height);
  }

  public static boolean intersect(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6)
  {
    return squareDistance(paramFloat1, paramFloat2, paramFloat4, paramFloat5) < (paramFloat3 + paramFloat6) * (paramFloat3 + paramFloat6);
  }

  public static boolean intersect(Point paramPoint1, float paramFloat1, Point paramPoint2, float paramFloat2)
  {
    return intersect(paramPoint1.x, paramPoint1.y, paramFloat1, paramPoint2.x, paramPoint2.y, paramFloat2);
  }

  public static boolean intersect(PointF paramPointF1, float paramFloat1, PointF paramPointF2, float paramFloat2)
  {
    return intersect(paramPointF1.x, paramPointF1.y, paramFloat1, paramPointF2.x, paramPointF2.y, paramFloat2);
  }

  public static void recenter(RectF paramRectF, float paramFloat1, float paramFloat2)
  {
    float f1 = paramFloat1 - paramRectF.centerX();
    float f2 = paramFloat2 - paramRectF.centerY();
    paramRectF.left = (f1 + paramRectF.left);
    paramRectF.right = (f1 + paramRectF.right);
    paramRectF.top = (f2 + paramRectF.top);
    paramRectF.bottom = (f2 + paramRectF.bottom);
  }

  public static Rect round(RectF paramRectF)
  {
    Rect localRect = new Rect();
    paramRectF.roundOut(localRect);
    return localRect;
  }

  public static Rect scale(Rect paramRect, int paramInt1, int paramInt2)
  {
    int i = paramRect.centerX();
    int j = paramRect.centerY();
    int k = i - paramInt2 / 2;
    int m = i + paramInt2 / 2;
    return new Rect(k, j - paramInt1 / 2, m, j + paramInt1 / 2);
  }

  public static void scale(RectF paramRectF, float paramFloat)
  {
    paramRectF.right = (paramRectF.left + paramFloat * paramRectF.width());
    paramRectF.bottom = (paramRectF.top + paramFloat * paramRectF.height());
  }

  public static void scaleRect(Rect paramRect, Size paramSize1, Size paramSize2)
  {
    float f1 = paramSize2.width / paramSize1.width;
    float f2 = paramSize2.width / paramSize1.width;
    paramRect.left = ((int)(f1 * paramRect.left));
    paramRect.top = ((int)(f2 * paramRect.top));
    paramRect.right = ((int)(f1 * paramRect.right));
    paramRect.bottom = ((int)(f2 * paramRect.bottom));
  }

  public static float squareDistance(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    return (paramFloat3 - paramFloat1) * (paramFloat3 - paramFloat1) + (paramFloat4 - paramFloat2) * (paramFloat4 - paramFloat2);
  }

  public static BoundingBoxProtos.BoundingBox toBoundingBox(Rect paramRect)
  {
    return BoundingBoxProtos.BoundingBox.newBuilder().setX(paramRect.left).setY(paramRect.top).setWidth(paramRect.width()).setHeight(paramRect.height()).build();
  }

  public static void translate(RectF paramRectF, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    float f = Math.min(paramInt1 / paramInt3, paramInt2 / paramInt4);
    int i = Math.max(0, (int)(paramInt1 - f * paramInt3) / 2);
    int j = Math.max(0, (int)(paramInt2 - f * paramInt4) / 2);
    int k = i + (int)(f * paramRectF.centerX());
    int m = j + (int)(f * paramRectF.centerY());
    scale(paramRectF, f);
    recenter(paramRectF, k, m);
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.GeometryUtils
 * JD-Core Version:    0.6.2
 */