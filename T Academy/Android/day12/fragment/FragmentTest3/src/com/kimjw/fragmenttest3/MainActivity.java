package com.kimjw.fragmenttest3;


import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;

public class MainActivity extends FragmentActivity implements TitlesFragment.ListItemSelectionListener {
	private static final String TAG = "MainActivity";
	ViewFragment vFragment = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	}

	@Override
	public void onListItemSelected(int index) {
		
		vFragment = (ViewFragment)getSupportFragmentManager().findFragmentById(R.id.viewFragment);
		if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
			Intent intent = new Intent(this, ViewActivity.class);
			intent.putExtra("index", index);
			startActivity(intent);
		}else{
			vFragment.setChage(index);
		}
		
	}

}
