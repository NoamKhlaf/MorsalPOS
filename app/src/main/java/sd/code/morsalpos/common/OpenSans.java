package sd.code.morsalpos.common;

import android.content.Context;
import android.graphics.Typeface;

public class OpenSans
{
  private static OpenSans instance;
  private static Typeface typeface;
  
  public static OpenSans getInstance(Context paramContext)
  {
    try
    {
      if (instance == null)
      {
        instance = new OpenSans();
        typeface = Typeface.createFromAsset(paramContext.getResources().getAssets(), "DroidKufi-Regular.ttf");
      }
      OpenSans localOpenSans = instance;
      return localOpenSans;
    }
    finally {}
  }
  
  public Typeface getTypeFace()
  {
    return typeface;
  }
}



/* Location:           C:\Users\master\Desktop\dex2jar-0.0.9.15\classes_dex2jar.jar

 * Qualified Name:     model.OpenSans

 * JD-Core Version:    0.7.0.1

 */