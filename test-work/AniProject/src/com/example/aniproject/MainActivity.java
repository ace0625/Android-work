package com.example.aniproject;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	AnimationDrawable ani;
	TextView tv = null;
	Button btn = null;
	boolean flag = false;
	
	Animation scale1;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			v.startAnimation(scale1);
			switch(v.getId())
			{
			case R.id.button1:
//				ani.start();
//				btn.startAnimation(scale1);
				break;
			case R.id.button2:
				ani.stop();
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
	
		tv = (TextView)findViewById(R.id.textView2);
		ani = (AnimationDrawable)tv.getBackground();
		
		btn = (Button)findViewById(R.id.button3);
		scale1 = AnimationUtils.loadAnimation(this, R.anim.ani1);
	}

	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) { //화면이 받아지자마자 실행!!
		if(hasFocus)
		{
			ani.start();
		}
		else
		{
			ani.stop();
		}
		super.onWindowFocusChanged(hasFocus);
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
