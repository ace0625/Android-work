package com.example.test1;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		
		@Override
		public void onClick(View v) {
			Intent intent;
			switch(v.getId())
			{
			case R.id.button1:
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
				startActivity(intent);
				break;
			case R.id.button2:
				intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-2565-7958"));
				startActivity(intent);
				break;
			case R.id.button3:
				intent = new Intent(getApplicationContext(), back.class);
				startActivity(intent);
				break;
			}
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
