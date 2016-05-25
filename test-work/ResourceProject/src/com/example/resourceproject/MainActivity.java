package com.example.resourceproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		et = (EditText)findViewById(R.id.editText1);
		et.setText("김현찬");
		et.setText(R.string.strName);
		
//		Resources res = getResources();
		String name = getResources().getString(R.string.strName);
		
		String date = getResources().getString(R.string.strDate, "2013", "10", "4");
		et.setText(name);
		et.setText(date);
		
//		et.setTextColor(Color.RED);
		et.setTextColor(getResources().getColor(R.color.myRedColor));
//		et.setTextSize(30);
		
		float size = getResources().getDimension(R.dimen.myTitleSize);
		Log.v(TAG, "Size: " +size);
		et.setTextSize(size);
		
		String[] arg = getResources().getStringArray(R.array.myArr);
		Log.v(TAG, "=========");
		for(String s:arg)
		{
			Log.v(TAG, s);
			
		}
		Log.v(TAG, "=========");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
