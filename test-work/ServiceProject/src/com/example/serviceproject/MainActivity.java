package com.example.serviceproject;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
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
				Intent intent = new Intent(MainActivity.this, MyService.class);
				startService(intent);
				break;
			case R.id.button2:
				Intent intent1 = new Intent(MainActivity.this, MyService.class);
				stopService(intent1);
				break;
			case R.id.button3:
				doReceiverStart();
				break;
			case R.id.button4:
				doReceiverStop();
				break;
			}
		}
	};
	BroadcastReceiver receiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) 
		{
			et.setText("받은 cnt : " + intent.getIntExtra("cnt", 0));
		}
	};
	
	void doReceiverStart()
	{
		IntentFilter filter = new IntentFilter();
		filter.addAction("aaa.bbb.Action");
		registerReceiver(receiver, filter);
	}
	void doReceiverStop()
	{
		unregisterReceiver(receiver);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
		
		et = (EditText)findViewById(R.id.editText1);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
