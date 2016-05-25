package com.example.lifeproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class SubActivity extends Activity {

	private static final String TAG = "MainActivity";
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				finish();
				break;
				
			}
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sub);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		
		Log.v(TAG, "Sub onCreate savedInstanceState : " +savedInstanceState);
	}
	

	@Override
	protected void onDestroy() {
		Log.v(TAG,"Sub onDestroy");
		super.onDestroy();
	}


	@Override
	protected void onPause() {
		Log.v(TAG,"Sub onPause");
		super.onPause();
	}


	@Override
	protected void onRestart() {
		Log.v(TAG,"Sub onRestart");
		super.onRestart();
	}


	@Override
	protected void onResume() {
		Log.v(TAG,"Sub onResume");
		super.onResume();
	}


	@Override
	protected void onStart() {
		Log.v(TAG,"Sub onStart");
		super.onStart();
	}


	@Override
	protected void onStop() {
		Log.v(TAG,"Sub onStop");
		super.onStop();
	}


}
