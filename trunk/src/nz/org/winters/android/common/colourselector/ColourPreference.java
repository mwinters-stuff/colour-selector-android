package nz.org.winters.android.common.colourselector;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.preference.DialogPreference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ColourPreference extends DialogPreference implements OnColorChangedListener
{

  private TextView  mSummaryView;
  ColorSelectorView mSelectorView;

  public ColourPreference(Context context, AttributeSet attrs)
  {
    super(context, attrs);
    initialize();
  }

  public ColourPreference(Context context, AttributeSet attrs, int defStyle)
  {
    super(context, attrs, defStyle);
    initialize();
  }

  private void initialize()
  {
    setPersistent(true);
//    setPositiveButtonText(R.string.color_new_color);
//    setNegativeButtonText(R.string.color_old_color);
  }

  private String getSummaryColour(int colour)
  {
    if (BigColour.indexOf(colour) < 0)
    {
      return "RGB #" + Integer.toHexString(colour);
    } else
    {
      return BigColour.values()[BigColour.indexOf(colour)].readableName();
    }
  }

  @Override
  public CharSequence getSummary()
  {
    if (mSummaryView != null && getColour() != Color.BLACK)
    {
      mSummaryView.setTextColor(getColour());
    }
    return getSummaryColour(getColour());
  }

  @Override
  protected void onBindView(View view)
  {
    mSummaryView = (TextView) view.findViewById(android.R.id.summary);
    setSummary(getSummaryColour(getColour()));

    super.onBindView(view);

  }

  @Override
  protected void onBindDialogView(View view)
  {
    super.onBindDialogView(view);
  }

  @Override
  protected void onDialogClosed(boolean positiveResult)
  {
    if (positiveResult)
    {
      int color = mSelectorView.getColor();
      persistInt(color);
      setSummary(getSummaryColour(color));
    }
    super.onDialogClosed(positiveResult);
  }

  public int getColour()
  {
    return getPersistedInt(Color.WHITE);
  }

  @Override
  protected View onCreateDialogView()
  {
    mSelectorView = new ColorSelectorView(getContext());
    mSelectorView.setColor(getColour());
    mSelectorView.setOnColorChangedListener(this);
    return mSelectorView;
  }

  @Override
  public void colorChanged(int color)
  {
    // TODO Auto-generated method stub

  }
  

}
