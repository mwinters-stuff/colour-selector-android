
package nz.org.winters.android.common.colourselector;

import nz.org.winters.android.common.colourselector.GridColorValueView.OnGSVColorChangedListener;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GridSelectorView extends LinearLayout implements OnGSVColorChangedListener, OnColorChangedListener
{
  private GridColorValueView     gridColorValueView;
  private TextView textColourName;
  private ColorKeeper mColorKeeper;
  private OnColorChangedListener mColorChangedListener;

  public GridSelectorView(Context context, ColorKeeper colorKeeper)
  {
    super(context);
    mColorKeeper = colorKeeper;
    init();
  }

  public GridSelectorView(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    init();
  }

  private void init()
  {
    buildUI();
    
    
  }

  private void buildUI()
  {
    LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    View gridView = inflater.inflate(R.layout.color_grid, null);
    this.addView(gridView, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    
    gridColorValueView = (GridColorValueView) gridView.findViewById(R.id.color_grid_value);
    gridColorValueView.setColorKeeper(mColorKeeper);
    
    gridColorValueView.setOnColorChangedListener(this);
    gridColorValueView.setOnGSVColorChangedListener(this);
    
    textColourName = (TextView) gridView.findViewById(R.id.colour_name);
    
  }

  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
  {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public void tabShow()
  {
    gridColorValueView.tabShow();

    
  }
  
  
  @Override
  public void colorChanged(String name)
  {
    textColourName.setText(name);
  }

  public void setOnColorChangedListener(OnColorChangedListener listener)
  {
    mColorChangedListener = listener;
    
  }

  @Override
  public void colorChanged(int color)
  {
    if(mColorChangedListener != null)
    {
      mColorChangedListener.colorChanged(color);
    }
    
  }

}
