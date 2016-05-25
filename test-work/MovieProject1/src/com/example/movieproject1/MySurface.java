package com.example.movieproject1;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

public abstract class MySurface extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = "MainActivity";
	Context context;
	MediaPlayer player;
	Button btn1, btn2;
	SurfaceHolder holder = null;
	SurfaceView mPreview;
	
	void init(Context context)
	{
		this.context = context;
		holder = getHolder();
		holder.addCallback(this);
		
	}
View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				startPlay();
				break;
			case R.id.button2:
				stop();
				break;
			}
			
		}
	};
	void startPlay()
	{
		if(player.isPlaying() == false)
		{
			player.start();
			btn1.setText("일시정지");
		}
		else
		{
			player.pause();
			btn1.setText("재생");
			
		}
	}
	void stop()
	{
		player.stop();
		try {
			player.prepare();
		} catch (Exception e) {
			Log.v(TAG, "stop error: " +e);
		}
	}
	
	
	public MySurface(Context context)
	{
		super(context);
		init(context);
	}
	public MySurface(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		init(context);
	}
	public MySurface(Context context, AttributeSet attrs, int defStyle)
	{
		super(context, attrs, defStyle);
		init(context);
	}
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.v(TAG, "changed");
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.v(TAG, "Created");
		
		if(player == null)
		{
			player = new MediaPlayer(); 
		}
		else
		{
			player.reset(); 
		}
		try {
			player.setDataSource(context, Uri.parse("http://kimjw.yanghee.kim.cloulu.com/hoot.mp4"));
			player.setDisplay(holder);
			player.prepare();
			player.setOnCompletionListener(complete);
			player.setOnVideoSizeChangedListener(sizeChange);
		} catch (Exception e) {
			Log.v(TAG, "video error: " + e);
		}
	
	}

	MediaPlayer.OnCompletionListener complete = new MediaPlayer.OnCompletionListener() {
		
		@Override
		public void onCompletion(MediaPlayer player) {
			 btn1.setText("재생");
			
		}
	};
	MediaPlayer.OnVideoSizeChangedListener sizeChange = new MediaPlayer.OnVideoSizeChangedListener() {
		
		@Override
		public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
			
			
		}
	};
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
			Log.v(TAG,"destroyed");
			if(player != null)
			{
				player.release();
			}
		
	}

}
