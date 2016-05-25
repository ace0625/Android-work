package com.example.movieproject;

import java.io.File;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivitiy";
	VideoView video = null;
	MediaController mc = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		video = (VideoView)findViewById(R.id.videoView1);
		mc = new MediaController(this);
		video.setMediaController(mc);
		
		
		File sdFile = Environment.getExternalStorageDirectory();
		File f1 = new File(sdFile, "hoot.mp4");
//		File f2 = new File(f1, "hoot.mp4");
		
		
		String path = f1.getAbsolutePath();
		Log.v(TAG, "path");
		video.setVideoPath(path);
		video.requestFocus();
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) { //바로실행
		if(hasFocus)
		{
			video.start();
		}
		super.onWindowFocusChanged(hasFocus);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
