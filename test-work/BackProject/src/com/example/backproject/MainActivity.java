package com.example.backproject;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	TextView tv;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				cnt++;
				tv.setText("cnt : " + cnt);
				break;
			}
		}
	};
	
	
	int cnt = 0;
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		switch(newConfig.orientation)
		{
		case Configuration.ORIENTATION_LANDSCAPE:
			tv.setText("가로모드");
			break;
		case Configuration.ORIENTATION_PORTRAIT:
			tv.setText("세로모드");
			break;
		}
		super.onConfigurationChanged(newConfig);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tv = (TextView)findViewById(R.id.textView1);
		findViewById(R.id.button1).setOnClickListener(bHandler);
	}
	
	boolean flag = true;
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		switch(keyCode)
		{
		case KeyEvent.KEYCODE_BACK:
			if(flag)
			{
				Toast.makeText(this, "한번 더 눌리면 취소됨", Toast.LENGTH_SHORT).show();
				flag = false;
			}
			else
			{
				finish();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}




	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		return super.onKeyUp(keyCode, event);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
