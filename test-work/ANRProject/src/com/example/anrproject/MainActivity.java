package com.example.anrproject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et1;
	ProgressDialog pDialog = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			
			switch (v.getId())
			{
			case R.id.button1:
				pDialog = ProgressDialog.show(MainActivity.this, "1", "작업중....");
				 new JobThread().start();
				break;
			case R.id.button2:
				
				break;
			}
			
		} 
	};
	
	
	class JobThread extends Thread
	{
		public void run()
		{
			doForLongJob();
		}
	}
	Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) {
			if(pDialog != null)
			{
				pDialog.cancel();
			}
			et1.setText("cnt : " + cnt);
		}
		
	};
	
	int cnt = 0;
	void doForLongJob()
	{
		for(int i=0; i<10; i++)
		{
			Log.v(TAG, "cnt: " + i);
			SystemClock.sleep(1000);
			
		}
		cnt++;
		handler.sendEmptyMessage(100);
//		et1.setText("cnt : " + cnt);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1 = (EditText)findViewById(R.id.editText1);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
