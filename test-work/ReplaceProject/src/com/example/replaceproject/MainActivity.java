package com.example.replaceproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	int cnt = 0;
	TextView tv;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				cnt++;
				tv.setText("cnt : " +cnt);
				Log.v(TAG, "cnt : " + cnt);
				break;
			case R.id.button2:
				cnt = 0;
				tv.setText("초기화");
				Log.v(TAG, "초기화");
				break;
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		View v = findViewById(R.id.button2);
		if(v != null)
		{
			v.setOnClickListener(bHandler);
		}
		
		tv = (TextView)findViewById(R.id.textView1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
