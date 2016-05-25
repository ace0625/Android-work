package com.example.threadproject;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static String TAG = "MainActivity";
	EditText et1, et2;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				doStart1();
				break;
			case R.id.button2:
				Log.v(TAG, "button2");
				doStop1();
				break;
			}
		}
	};
	JobThread trd = null;
	JobThread1 trd1 = null;
	
	void doStop1()
	{
		if(trd != null)
		{
			if(trd.isAlive())
			{
				onAir = false;
				trd = null;
				
			}
		}
		
	}
	void doStart1()
	{
		if(trd == null)
		{
			trd = new JobThread();
			trd1 = new JobThread1();
			onAir = true;
			trd.start();
			trd1.start();
		}
	}
	Handler handler = new Handler()
	{
		@Override
		public void handleMessage(Message msg) 
		{
			switch(msg.what)
			{
				case 100:
					et1.setText(msg.arg1 + "");
					break;
				case 200:
					et2.setText(msg.arg1 + "");
					break;
			}
			
			//et1.setText(cnt + "");
		}
	
	};
	boolean onAir = false;
	class JobThread extends Thread
	{
		int cnt = 0;
		public void run()
		{
			Message msg = null;
			while(onAir)
			{
				cnt++;
				msg = handler.obtainMessage();
				msg.arg1 = cnt;
				msg.what = 100;
				handler.sendMessage(msg);
				//handler.sendEmptyMessage(100);
				Log.v(TAG, "cnt : " + cnt);
			//	et1.setText(cnt + "");
				SystemClock.sleep(500);
			}
		}
	}
	
	Handler handler1 = new Handler();
	
	class JobThread1 extends Thread
	{
		int cnt = 0;
		public void run()
		{
		//	Message msg = null;
			while(onAir)
			{
				cnt++;
//				msg = handler.obtainMessage();
//				msg.arg1 = cnt;
//				msg.what = 200;
//				handler.sendMessage(msg);
				//handler.sendEmptyMessage(100);
				Log.v(TAG, "cnt : " + cnt);
				handler1.post(new Runnable() {
					
					@Override
					public void run() {
						et2.setText(cnt + "");
					}
				});
			//	et1.setText(cnt + "");
				SystemClock.sleep(700);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		
		et1 = (EditText)findViewById(R.id.editText1);
		et2 = (EditText)findViewById(R.id.editText2);				
	}
	

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		doStop1();
		super.onDestroy();
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
