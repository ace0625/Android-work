package com.example.audioproject;

import java.io.File;

import android.app.Activity;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	MediaRecorder recorder;
	MediaPlayer player;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				mRecord();
				break;
			case R.id.button2:
				mRStop();
				break;
			case R.id.button3:
				Rplay();
				break;
			}
			
		}
	};
	void Rplay()
	{
		
		if(player == null)
			{
				player = new MediaPlayer();
			}
			File sdCard = Environment.getExternalStorageDirectory();
			File f = new File(sdCard, "recorded.mp4");
//			File f1 = new File(f, file);
			try {
				Log.v(TAG, f.getAbsolutePath());
				player.setDataSource(f.getAbsolutePath()); 
				player.prepare();
				player.start();
			} catch (Exception e) {
				Log.v(TAG, "sound error: " + e);
			}	
	}
		

	void mRStop()
	{	
		try {
			recorder.stop();
		} catch (IllegalStateException e) {
			Log.v(TAG, "stop error: " +e);
		}
		
	}
	void mRecord()
	{
		if(recorder == null)
		{
			recorder = new MediaRecorder();
		}
		else
		{
			recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
			recorder.setOutputFile("/sdcard/recorded.mp4");
			try {
				recorder.prepare();
				recorder.start();
			} catch (Exception e) {
				Log.v(TAG, "Recording error: " + e);
			}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		findViewById(R.id.button3).setOnClickListener(bHandler);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
