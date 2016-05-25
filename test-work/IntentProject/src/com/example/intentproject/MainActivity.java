package com.example.intentproject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			Intent intent = null;
			
			switch(v.getId())
			{
			case R.id.button1:
				intent = new Intent();
				//intent.setAction("android.intent.action.DIAL");
				intent.setAction(intent.ACTION_CALL);
				intent.setData(Uri.parse("tel:010-2565-7958"));
				break;
			case R.id.button2:
				intent = new Intent();
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("http://m.naver.com"));
				break;
			case R.id.button3:
				intent = new Intent();
				ComponentName cName = new ComponentName("com.example.anrproject", "com.example.anrproject.MainActivity");
				
				intent.setComponent(cName);
				break;
			}
			startActivity(intent);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
