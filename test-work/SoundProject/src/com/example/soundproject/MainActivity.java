package com.example.soundproject;

import java.io.File;
import java.net.URL;

import android.app.Activity;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	MediaPlayer player;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
//				startMusic("music.mp3");
				startMusic1("http://www.winapi.co.kr/data/saemaul1.mp3");
				break;
			case R.id.button2:
				pauseMusic();
				break;
			case R.id.button3:
				reStartMusic();
				break;
			}
			
		}
	};
	void startMusic1(String url)
	{
		if(player == null)
		{
			player = new MediaPlayer();
		}
		try {
			player.setDataSource(this, Uri.parse(url));
			player.setOnPreparedListener(pListener);
			player.prepareAsync();
//			player.prepare();
//			player.start();
		} catch (Exception e) {
			Log.v(TAG, "sound error: " + e);
		}	
	}
	
	MediaPlayer.OnPreparedListener pListener = new MediaPlayer.OnPreparedListener() {
		
		@Override
		public void onPrepared(MediaPlayer mp) {
			mp.start();
			
		}
	};
	void reStartMusic()
	{
		if(player != null)
		{
			player.seekTo(duration);
			try {
				player.start();
			} catch (IllegalStateException e) {
				Log.v(TAG, "restart error: " +e);
			}
		}
	}
	int duration = 0;
	void pauseMusic()
	{
		if(player != null)
		{
			if(player.isPlaying())
			{
				duration = player.getCurrentPosition();
				try {
					player.pause();
				} catch (IllegalStateException e) {
					Log.v(TAG, "Pause error: " +e);
				}
				
			}
		}
	}
	
	void startMusic(String file)
	{
		if(player == null)
		{
			player = new MediaPlayer();
		}
		File sdCard = Environment.getExternalStorageDirectory();
		File f = new File(sdCard, "Music");
		File f1 = new File(f, file);
		try {
			Log.v(TAG, f1.getAbsolutePath());
			player.setDataSource(f1.getAbsolutePath()); //String path는 데이터 소스 연결
			player.prepare();
			player.start();
		} catch (Exception e) {
			Log.v(TAG, "sound error: " + e);
		}	
	}
	
	@Override
	protected void onPause() 
	{
		killPlayer();
		super.onPause();
	}
	void killPlayer()
	{
		if(player != null)
		{
			if(player.isPlaying())
			{
				try {
					player.start();
				} catch (IllegalStateException e) {
					Log.v(TAG, "killplyer error: " +e);
				}
				player.release();
				player = null;
				System.gc();
				
				Bitmap bitmap = null;
				bitmap.recycle();
				bitmap = null;
				System.gc();
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
