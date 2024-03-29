/*
 * Copyright (C) 2011 Devmil (Michael Lamers) 
 * Mail: develmil@googlemail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nz.org.winters.android.common.colourselector;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HexSelectorView extends LinearLayout {

	private EditText edit;
	private TextView txtError;
	private Button btnSave;
  private ColorKeeper mColorKeeper;
  private OnColorChangedListener mColorChangedListener;
	
	public HexSelectorView(Context context, ColorKeeper colorKeeper)
  {
    super(context);
    mColorKeeper = colorKeeper;
		init();
	}
	
	public HexSelectorView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	
	private void init()
	{
		LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View content = inflater.inflate(R.layout.color_hex, null);
		addView(content, new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	
		txtError = (TextView)content.findViewById(R.id.color_hex_txtError);
		
		edit = (EditText)content.findViewById(R.id.color_hex_edit);
		btnSave = (Button)content.findViewById(R.id.color_hex_btnSave);
		btnSave.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				try
				{
					String hex = edit.getText().toString();
//					String prefix = "";
					if(hex.startsWith("0x"))
					{
						hex = hex.substring(2);
//						prefix = "0x";
					}
					if(hex.startsWith("#"))
					{
						hex = hex.substring(1);
//						prefix = "#";
					}
					if(hex.length() == 6)
					{
						hex = "FF" + hex;
					}
					if(hex.length() != 8)
						throw new Exception();
					mColorKeeper.set((int)Long.parseLong(hex, 16));

				  if(mColorChangedListener != null)
			    {
			      mColorChangedListener.colorChanged(mColorKeeper.get());
			    }
			    

					txtError.setVisibility(GONE);
				}
				catch(Exception e)
				{
					txtError.setVisibility(VISIBLE);
				}
			}
		});
	}
	
	public void setColor()
	{
		edit.setText(padLeft(Integer.toHexString(mColorKeeper.get()).toUpperCase(), '0', 8));
		txtError.setVisibility(GONE);
	}
	
	private String padLeft(String string, char padChar, int size)
	{
		if(string.length() >= size)
			return string;
		StringBuilder result = new StringBuilder();
		for(int i=string.length(); i<size; i++)
			result.append(padChar);
		result.append(string);
		return result.toString();
	}

  public void tabShow()
  {
    setColor();
    
  }
  
  public void setOnColorChangedListener(OnColorChangedListener listener)
  {
    mColorChangedListener = listener;
    
  }

	

}
