package com.example.fragmentproject1;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.widget.FrameLayout;

public class MainActivity extends FragmentActivity { //FragmentActivity 상속
	android.support.v4.app.FragmentManager fm = null;
	MyFragment fragment1 = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

//		fm = getFragmentManager(); //일반 엑비티비
		fm = getSupportFragmentManager();
		
//		fragment1 = (MyFragment)fm.findFragmentById(R.id.fragment1); //xml을통해서 집어넣는 방법
		fragment1 = new MyFragment();
		FragmentTransaction ft = fm.beginTransaction();
		ft.add(R.id.mainFrame, fragment1);
		ft.commit();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
