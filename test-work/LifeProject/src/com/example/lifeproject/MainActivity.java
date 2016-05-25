package com.example.lifeproject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				Intent intent = new Intent(MainActivity.this, SubActivity.class);
				startActivity(intent);
				break;
			case R.id.button2:
				doTask();
				break;
			}
		}
	};
	
	int x=0, y=0;
	void doTask()
	{
		x++;
		y++;
		et.setText(String.format("x : %d , y : %d ", x, y));
		Log.v(TAG, String.format("x : %d , y : %d ", x, y));
	}
	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		Log.v(TAG, "onSavedInstanceState");
		outState.putInt("mx", x);
		super.onSaveInstanceState(outState);
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		
		if(savedInstanceState != null)
		{
			x = savedInstanceState.getInt("mx");
		}
		SharedPreferences sp = getSharedPreferences("Tcam", 0);
		
		y = sp.getInt("my", 100);
		
		boolean flag = sp.getBoolean("first", true);
		if(flag)
		{
			doFirst();
		}
		
		et = (EditText)findViewById(R.id.editText1);
		Log.v(TAG, "Main onCreate savedInstanceState : " +savedInstanceState);
	}
	void doFirst()
	{
		//어플에서 한번만 해야할일 실행하는 소스 작성
		SharedPreferences sp = getSharedPreferences("Tcam", 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putBoolean("first", false);
		editor.commit();
	}
	

	@Override
	protected void onDestroy() {
		Log.v(TAG,"Main onDestroy");
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		Log.v(TAG,"Main onPause");
		SharedPreferences sp = getSharedPreferences("Tcam", 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putInt("my", y);
		editor.putString("data", "김현찬");
		editor.putBoolean("init", true);
		editor.commit();
		
		super.onPause();
	}


	@Override
	protected void onRestart() {
		Log.v(TAG,"Main onRestart");
		super.onRestart();
	}


	@Override
	protected void onResume() {
		Log.v(TAG,"Main onResume");
		super.onResume();
	}


	@Override
	protected void onStart() {
		Log.v(TAG,"Main onStart");
		super.onStart();
	}


	@Override
	protected void onStop() {
		Log.v(TAG,"Main onStop");
		super.onStop();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
