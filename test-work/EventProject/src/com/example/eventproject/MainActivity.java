package com.example.eventproject;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivity";
	int num = 100;
	
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.btn1:
				Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("010-2565-7958"));
				startActivity(intent);
				Log.v(TAG, "확인1");
				break;
			case R.id.btn2:
				Intent intent2 = new Intent(Intent.ACTION_VIEW,Uri.parse("http://www.naver.com"));
				startActivity(intent2);
				Log.v(TAG, "확인2");
			
				break;
			case R.id.btn3:
				Log.v(TAG, "확인3");
				break;
			case R.id.btn4:
				Log.v(TAG,"확인4");
				break;
			
			}
			Log.v(TAG, "확인0");
//			Log.v(TAG, "this: " + this);
//			Log.v(TAG, "MainActivity.this" + MainActivity.this);
		}
		
	};
	public void doAction(View v)
	{
		Log.v(TAG, "문자열: " + ((Button) v).getText());
	}
	
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Button btn1 = (Button)findViewById(R.id.btn1);
		//btn1.setOnClickListener(bHandler);
	
		findViewById(R.id.btn1).setOnClickListener(bHandler);
		findViewById(R.id.btn2).setOnClickListener(bHandler);
		findViewById(R.id.btn3).setOnClickListener(bHandler);
		findViewById(R.id.btn4).setOnClickListener(bHandler);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
