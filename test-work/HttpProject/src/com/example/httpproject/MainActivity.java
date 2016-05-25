package com.example.httpproject;

import android.os.Bundle;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			FragmentTransaction ft = manager.beginTransaction();
			switch(v.getId())
			{
			case R.id.button1:
				ft.replace(R.id.mainFrame, fragment1);
				break;
			case R.id.button2:
				ft.replace(R.id.mainFrame, fragment2);
				break;
			case R.id.button3:
				ft.replace(R.id.mainFrame, fragment3);
				break;
			case R.id.button4:
				ft.replace(R.id.mainFrame, fragment4);
				break;
			}
			ft.commit();
		}
	};
	FragmentManager manager = null;
	MyFragment1 fragment1;
	MyFragment2 fragment2;
	MyFragment3 fragment3;
	MyFragment4 fragment4;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
		findViewById(R.id.button4).setOnClickListener(bHandler);
		
		manager = getFragmentManager();
		
		fragment1 = new MyFragment1();
		fragment2 = new MyFragment2();
		fragment3 = new MyFragment3();
		fragment4 = new MyFragment4();
		FragmentTransaction ft = manager.beginTransaction();
		ft.replace(R.id.mainFrame, fragment1);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
