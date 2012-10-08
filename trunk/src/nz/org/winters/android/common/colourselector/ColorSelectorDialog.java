
package nz.org.winters.android.common.colourselector;

import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorSelectorDialog extends Dialog  {
	private ColorSelectorView content;
	private OnColorChangedListener listener;
	private int initColor;
	private int color;
	private Button btnOld;
	private Button btnNew;
	
//	public final static String INTENT_COLOR_CHANGED = "de.devmil.common.ui.color.COLOR_CHANGED"; 
	
	public ColorSelectorDialog(Context context, String title, OnColorChangedListener listener, int initColor) {
		super(context);
		this.listener = listener;
		this.initColor = initColor;
		this.setTitle(title);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		LinearLayout mainContent = new LinearLayout(getContext());
		mainContent.setOrientation(LinearLayout.VERTICAL);
		
		LinearLayout buttonsLL = new LinearLayout(getContext());
		buttonsLL.setBackgroundResource(R.drawable.transparentbackrepeat);
		btnOld = new Button(getContext());
		btnOld.setText(getContext().getResources().getString(R.string.color_old_color));
		LinearLayout.LayoutParams paramsOld = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		paramsOld.weight = 1;
		buttonsLL.addView(btnOld, paramsOld);
		
		btnOld.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				dismiss();
			}
		});
		
		btnNew = new Button(getContext());
		btnNew.setText(getContext().getResources().getString(R.string.color_new_color));
		LinearLayout.LayoutParams paramsNew = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		paramsNew.weight = 1;
		buttonsLL.addView(btnNew, paramsNew);
		
		btnNew.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if(listener != null)
					listener.colorChanged(color);
				dismiss();
			}
		});
		
		content = new ColorSelectorView(getContext());
		content.setOnColorChangedListener(new OnColorChangedListener() {
			
			public void colorChanged(int color) {
				colorChangedInternal();
			}
		});
		
		LinearLayout.LayoutParams paramsContent = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
		paramsContent.weight = 1;
		LinearLayout.LayoutParams paramsButtons = new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT);
		paramsButtons.weight = 0;
		
		mainContent.addView(content, paramsContent);
		mainContent.addView(buttonsLL, paramsButtons);
		
		setContentView(mainContent);

		btnOld.setBackgroundColor(initColor);
		btnOld.setTextColor(~initColor | 0xFF000000);
		content.setColor(initColor);
		//content.setColor(initColor);
		colorChangedInternal();
		
//		BroadcastReceiver recever = new BroadcastReceiver(){
//
//      @Override
//      public void onReceive(Context context, Intent intent)
//      {
//        if(intent.getAction().equals(INTENT_COLOR_CHANGED))
//        {
//          colorChangedInternal();
//        }
//        
//      }
//		  
//		};
//	  IntentFilter filter = new IntentFilter();
//    filter.addAction(INTENT_COLOR_CHANGED);
//    getContext().registerReceiver(recever, filter);
		
		
	}
	
	
	private void colorChangedInternal()
	{
	  int color = content.getColor();
		btnNew.setBackgroundColor(color);
		btnNew.setTextColor(~color | 0xFF000000); //without alpha
		this.color = color;
	}
	
	public void setColor(int color)
	{
		content.setColor(color);
	}
	


}
