package com.example.framefragmentproject;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class MainActivity extends FragmentActivity {
	
	FragmentManager fm = null;
	MyFragment1 frag1;
	MyFragment2 frag2;
	MyFragment3 frag3;
	
	View.OnTouchListener tHandler = new View.OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch(event.getAction())
			{
			case MotionEvent.ACTION_DOWN:
				FragmentTransaction ft = fm.beginTransaction();
				switch(v.getId())
				{
				case R.id.tab1:
					ft.replace(R.id.mainFrame, frag1);
					break;
				case R.id.tab2:
					ft.replace(R.id.mainFrame, frag2);
					break;
				case R.id.tab3:
					ft.replace(R.id.mainFrame, frag3);
					break;
				}
				ft.commit();
				break;
			}
			return true;
		}
	};
	MenuFragment menu;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				FragmentTransaction ft = fm.beginTransaction();
				ft.add(R.id.mainFrame, menu);
				ft.addToBackStack(null);
				ft.commit();// Do not forget
				break;
			case R.id.button2:
				MyDialog dialog = new MyDialog();
				dialog.show(fm, "aaa"); //앞에는 fragmentmanager 뒤에는 구분자
//				FragmentTransaction ft1 = fm.beginTransaction();
//				ft1.show(dialog);
//				ft1.commit();
				break;
			}	
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		fm = getSupportFragmentManager();
		
		findViewById(R.id.tab1).setOnTouchListener(tHandler);
		findViewById(R.id.tab2).setOnTouchListener(tHandler);
		findViewById(R.id.tab3).setOnTouchListener(tHandler);
		
		menu = new MenuFragment();
		
		frag1 = new MyFragment1();
		frag2 = new MyFragment2();
		frag3 = new MyFragment3();
		
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.mainFrame, frag1);
		ft.commit();// Do not forget
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
