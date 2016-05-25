package com.example.animation2;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class MainActivity extends Activity {
	ImageView iv;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		public void onClick(View v) 
		{
			Animation ani = null;
			switch(v.getId())
			{
			case R.id.button1:			
				ani = new TranslateAnimation(0, 200, 0, 0);
				break;
			case R.id.button2:
				ani = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 1, 
						Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);
				break;
			case R.id.button3:
				ani = new TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 1, 
						Animation.RELATIVE_TO_PARENT, 0, Animation.RELATIVE_TO_PARENT, 0);				
				break;
			case R.id.button4:
				ani = new RotateAnimation(0, -180);
				break;
			case R.id.button5:
				ani = new RotateAnimation(0, -180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				break;
			case R.id.button6:
				ani = new RotateAnimation(0, 90, Animation.RELATIVE_TO_PARENT, 0.5f, Animation.RELATIVE_TO_PARENT, 1.0f);
				break;
			case R.id.button7:
				ani = new ScaleAnimation(0, 1, 0, 1);
				break;
			case R.id.button8:
				ani = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				break;
			case R.id.button9:
				ani = new ScaleAnimation(1, 0, 1, 0, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
				break;
			case R.id.button10:
				ani = new AlphaAnimation(0, 1);
				break;
			case R.id.button11:
				ani = new AlphaAnimation(1, 0);
				break;
			}
					
			ani.setDuration(1000);
			iv.startAnimation(ani);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
		findViewById(R.id.button5).setOnClickListener(bHandler);
		findViewById(R.id.button6).setOnClickListener(bHandler);
		findViewById(R.id.button7).setOnClickListener(bHandler);
		findViewById(R.id.button8).setOnClickListener(bHandler);
		findViewById(R.id.button9).setOnClickListener(bHandler);
		findViewById(R.id.button10).setOnClickListener(bHandler);
		findViewById(R.id.button11).setOnClickListener(bHandler);
		
		iv = (ImageView)findViewById(R.id.imageView1);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
