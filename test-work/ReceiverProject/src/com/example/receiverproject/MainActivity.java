package com.example.receiverproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				Intent intent1 = new Intent("com.example.receiveproject.action.MyAction"); //권장
				sendBroadcast(intent1);
//				Log.v(TAG,"방송됨");
				break;
			case R.id.button2:
				Intent intent2 = new Intent("com.example.receiveproject.action.TAction"); //권장
				sendBroadcast(intent2);
				break;
			}
		}
	};
	
	EditText et=null;
	BroadcastReceiver receiver = new BroadcastReceiver() 
	{
		@Override
		public void onReceive(Context context, Intent intent) 
		{
			String action = intent.getAction();
			if("com.example.receiveproject.action.MyAction".equals(action))
			{
				Log.v(TAG, "myAction");
				et.setText("MyAction");
			}
			else if(action.equals("com.example.receiveproject.action.TAction"))
			{
				Log.v(TAG, "TAction");
				et.setText("TAction");
			}
			
		}
	};
	
	@Override
	protected void onPause() {
		unregisterReceiver(receiver);
		super.onPause();
	}

	@Override
	protected void onResume() {
		IntentFilter filter = new IntentFilter();
		filter.addAction("com.example.receiveproject.action.MyAction");
		filter.addAction("com.example.receiveproject.action.TAction");
		
		registerReceiver(receiver, filter);
		super.onResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		
		et = (EditText)findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
