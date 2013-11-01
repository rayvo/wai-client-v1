package com.google.android.apps.unveil.env;

import android.util.FloatMath;
import java.util.Iterator;
import java.util.LinkedList;

public class Statistic
{
  private static final int MAX_NUMBERS = 10;
  private final LinkedList<Float> numbers = new LinkedList();

  private static float blend(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    return paramFloat1 * (1.0F - paramFloat3) + paramFloat3 * paramFloat2;
  }

  public void addNumber(float paramFloat)
  {
    try
    {
      this.numbers.add(Float.valueOf(paramFloat));
      while (this.numbers.size() > 10)
        this.numbers.remove(0);
    }
    finally
    {
    }
  }

  public float getMovingAverage()
  {
    try
    {
      int i = this.numbers.size();
      float f1;
      if (i == 0)
        f1 = 0.0F;
      while (true)
      {
        return f1;
        f1 = ((Float)this.numbers.get(0)).floatValue();
        for (int j = 1; j < this.numbers.size(); j++)
        {
          float f2 = blend(f1, ((Float)this.numbers.get(j)).floatValue(), 0.3F);
          f1 = f2;
        }
      }
    }
    finally
    {
    }
  }

  public float getStandardDeviation()
  {
    try
    {
      float f = FloatMath.sqrt(getVariance());
      return f;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public float getVariance()
  {
    try
    {
      int i = this.numbers.size();
      if (i == 0);
      float f3;
      int j;
      for (float f4 = 0.0F; ; f4 = f3 / j)
      {
        return f4;
        float f1 = 0.0F;
        Iterator localIterator1 = this.numbers.iterator();
        while (localIterator1.hasNext())
          f1 += ((Float)localIterator1.next()).floatValue();
        float f2 = f1 / this.numbers.size();
        f3 = 0.0F;
        Iterator localIterator2 = this.numbers.iterator();
        while (localIterator2.hasNext())
        {
          Float localFloat = (Float)localIterator2.next();
          f3 += (localFloat.floatValue() - f2) * (localFloat.floatValue() - f2);
        }
        j = this.numbers.size();
      }
    }
    finally
    {
    }
  }

  public void reset()
  {
    try
    {
      this.numbers.clear();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public String toString()
  {
    Object localObject1 = "";
    try
    {
      Iterator localIterator = this.numbers.iterator();
      while (localIterator.hasNext())
      {
        Float localFloat = (Float)localIterator.next();
        String str = (String)localObject1 + "" + localFloat + " ";
        localObject1 = str;
      }
      return localObject1;
    }
    finally
    {
    }
  }
}

/* Location:           C:\apktool\SmaliToJavaTUTKit\jd-gui-0.3.5.windows\classes-dex2jar.jar
 * Qualified Name:     com.google.android.apps.unveil.env.Statistic
 * JD-Core Version:    0.6.2
 */