package com.example.videorecord;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	MediaPlayer player = null;
	MediaRecorder recorder = null;
	SurfaceView surface;
	SurfaceHolder holder;
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				record();
				break;
			case R.id.button2:
				stop();
				break;
			}
			
		}
	};
	void stop()
	{
		if(recorder == null)
		{
			return;
		}
		else
		{
			try {
				recorder.stop();
				recorder.reset();
				recorder.release();
				recorder = null;
				ContentValues values = new ContentValues();
				values.put(MediaStore.MediaColumns.TITLE, "RecordedVideo");
				values.put(MediaStore.Audio.Media.ALBUM, "Vidoe Album");
				values.put(MediaStore.Audio.Media.ARTIST, "KIM");
				values.put(MediaStore.Audio.Media.DISPLAY_NAME, "Recorded Viedeo");
				values.put(MediaStore.Audio.Media.IS_RINGTONE, 1);
				values.put(MediaStore.Audio.Media.IS_MUSIC, 1);
				values.put(MediaStore.MediaColumns.DATE_ADDED, System.currentTimeMillis()/1000);
				values.put(MediaStore.MediaColumns.MIME_TYPE, "video/mp4");
				values.put(MediaStore.Audio.Media.DATA,"/sdcard/video_recorded.mp4");
				
				Uri videoUri = getContentResolver().insert(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, values);
				if(videoUri == null)
				{
					Log.v(TAG,"insert failed");
					return;
				}
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, videoUri));
			} catch (Exception e) {
				Log.v(TAG,"stop error: " +e);
			}
			
		}
	}
	void record()
	{
		if(recorder == null)
		{
			recorder = new MediaRecorder();
		}
	
			recorder.setAudioSource((MediaRecorder.AudioSource.MIC));
			recorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
			recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
			recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
			recorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);
			
			recorder.setPreviewDisplay((holder.getSurface()));
			recorder.setOutputFile("/sdcard/video_recorded.mp4");
			try {
				recorder.prepare();
				recorder.start();
			} catch (Exception e) {
				Log.v(TAG, "Recording error: " + e);
				recorder.release();
				recorder = null;
			}
			
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		surface = (SurfaceView)findViewById(R.id.surfaceView1);
		holder = surface.getHolder();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
