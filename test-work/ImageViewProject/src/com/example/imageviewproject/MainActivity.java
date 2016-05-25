package com.example.imageviewproject;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

	private static final String TAG = "MainActivitiy";
	View.OnClickListener bHandler = new View.OnClickListener() 
	{
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent(Intent.ACTION_VIEW);
			File sdPath = Environment.getExternalStorageDirectory();
			Log.v(TAG, "sdPath: " +sdPath);
			File sdFile = new File(sdPath, "imagetest/test.png");
			
			Uri data = Uri.fromFile(sdFile);
			intent.setDataAndType(data, "image/png");
			startActivity(intent);
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
