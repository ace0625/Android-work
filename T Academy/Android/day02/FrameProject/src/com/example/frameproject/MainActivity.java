package com.example.frameproject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	LinearLayout linear1, linear2, linear3;
	
	View.OnTouchListener tHandler = new OnTouchListener() 
	{
		
		public boolean onTouch(View v, MotionEvent event) 
		{
			Log.v(TAG,"터치상태" + event.getAction());
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				Log.v(TAG, "터치눌림상태좌표갯수" + event.getPointerCount() 
						+ ", x : " + event.getX());
				doAction(v);
				break;
			case MotionEvent.ACTION_UP:
				break;
			case MotionEvent.ACTION_MOVE:
				break;
			}
			return true;
		}
	}; 
	public void doAction(View v)
	{
		hideView();
		switch(v.getId())
		{
		case R.id.textView1:
			linear1.setVisibility(View.VISIBLE);
			break;
		case R.id.textView2:
			linear2.setVisibility(View.VISIBLE);
			break;
		case R.id.textView3:
			linear3.setVisibility(View.VISIBLE);
			break;
		}
	}
	
	void hideView()
	{
		linear1.setVisibility(View.INVISIBLE);
		linear2.setVisibility(View.INVISIBLE);
		linear3.setVisibility(View.INVISIBLE);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.textView1).setOnTouchListener(tHandler);
		findViewById(R.id.textView2).setOnTouchListener(tHandler);
		findViewById(R.id.textView3).setOnTouchListener(tHandler);
		
		linear1 = (LinearLayout)findViewById(R.id.linear1);
		linear2 = (LinearLayout)findViewById(R.id.linear2);
		linear3 = (LinearLayout)findViewById(R.id.linear3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
