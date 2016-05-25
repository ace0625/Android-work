package com.kimjw.fragmenttest3;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

public class ViewActivity extends FragmentActivity {
	private static final String TAG = "MainActivity";
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	
	    if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
	    	finish();
	    	return;
	    }
	    setContentView(R.layout.view);
	    ViewFragment fragment = (ViewFragment)getSupportFragmentManager().findFragmentById(R.id.viewFragment);
	    Intent intent = getIntent();
	    int index = intent.getIntExtra("index", 0);
	    fragment.setChage(index);
	    Log.v(TAG, "viewActivity index :" + index);
	    
	}

}
