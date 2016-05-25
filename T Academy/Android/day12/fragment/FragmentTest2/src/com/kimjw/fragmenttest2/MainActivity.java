package com.kimjw.fragmenttest2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.View;

public class MainActivity extends FragmentActivity {

	FragmentManager fm = null;
	FragmentTransaction tf = null;
	MyFragment1 f1 = null;
	MyFragment2 f2 = null;
	MyFragment3 f3 = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			tf = fm.beginTransaction();
			
			switch(v.getId()){
			case R.id.textView1 :
				
				tf.replace(R.id.mainView, f1);
				break;
			case R.id.textView2 :
				tf.replace(R.id.mainView, f2);
				break;
			case R.id.textView3 :
				tf.replace(R.id.mainView, f3);
				break;
			}
			tf.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
			tf.commit();
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		fm = getSupportFragmentManager();
		tf = fm.beginTransaction();
		f1 = new MyFragment1();
		f2 = new MyFragment2();
		f3 = new MyFragment3();
		
		tf.replace(R.id.mainView, f1).commit();
		
		findViewById(R.id.textView1).setOnClickListener(bHandler);
		findViewById(R.id.textView2).setOnClickListener(bHandler);
		findViewById(R.id.textView3).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
