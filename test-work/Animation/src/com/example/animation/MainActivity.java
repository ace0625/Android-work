package com.example.animation;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	AnimationDrawable Ani;
	ImageView img;
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		public void onClick(View v) 
		{
			switch(v.getId())
			{
			case R.id.button1:
				Ani.start();
				break;
			case R.id.button2:
				Ani.stop();
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
		
		img = (ImageView)findViewById(R.id.imageView1);
		Ani = (AnimationDrawable)img.getBackground();
		
		img.post(new Runnable()
		{

			@Override
			public void run() 
			{
				Ani.start();
			}
			
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
