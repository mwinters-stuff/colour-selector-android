package nz.org.winters.android.common.colourselector;

import android.graphics.Color;

public class ColorKeeper
{
 // private final static ColorKeeper sInstance = new ColorKeeper();
  
  private int mColor;
  
  public ColorKeeper()
  {
    mColor = Color.BLACK;
  }
  
  
  int get()
  {
    return mColor;
  }
  
  void set(int color)
  {
    mColor = color;
  }

}
