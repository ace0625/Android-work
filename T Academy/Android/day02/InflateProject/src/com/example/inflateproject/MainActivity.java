package com.example.inflateproject;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.view.Menu;
import android.view.TextureView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//Method 1
//		LinearLayout view = new LinearLayout(this);
//		view.setOrientation(LinearLayout.VERTICAL);
//		view.setBackgroundColor(Color.GRAY);
//		
//		TextView tv = new TextView(this);
//		tv.setText("T Academy");
//		tv.setTextColor(0xff00ff00);
//		tv.setTextSize(30);
//		
//		view.addView(tv);
//		setContentView(view);
		
		//Method 2
		View view = View.inflate(this, R.layout.activity_main, null);
		
		setContentView(view);
		//Method 3
		//setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
