package com.example.layouttouch;

import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AnimationUtils;

import android.widget.Toast;
import android.widget.ViewFlipper;

public class MainActivity extends Activity implements OnTouchListener {
	
	
	ViewFlipper flipper;
	float xDown;
	float xUp;
	int count = 0;
	View.OnTouchListener tHandler = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				xDown = event.getX();
				break;
			case MotionEvent.ACTION_UP:
				xUp = event.getX();
				if(xDown>xUp)
				{
					flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.animator.leftin));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.animator.leftout));
					count++;
					if(count <3)
					{
						flipper.showNext();
					}
					else
					{
						Toast.makeText(MainActivity.this, "last page", Toast.LENGTH_SHORT).show();
						count--;
					}
				}
				else if(xDown<xUp)
				{
					flipper.setInAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.animator.rightin));
					flipper.setOutAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.animator.rightout));
					count--;
					if(count>-1)
					{
						flipper.showPrevious();
					}
					else
					{
						Toast.makeText(MainActivity.this, "First page", Toast.LENGTH_SHORT).show();
						count++;
					}
				}
				break;
				
//				fl.addView(fl, R.drawable.colorswatch3);
				
			}
			return true;
		}

	
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
//		findViewById(R.id.frame).setOnTouchListener(tHandler);
		flipper = (ViewFlipper)findViewById(R.id.flipper);
		flipper.setOnTouchListener(tHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		return false;
	}

}
