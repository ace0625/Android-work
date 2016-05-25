package com.example.serviceproject;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;
import android.view.textservice.SentenceSuggestionsInfo;

public class MyService extends Service {
	private static final String TAG = "MainActivity";
	
	int cnt = 0;
	
	boolean onAir  = false;
	class JobThread extends Thread
	{
		Intent intent = null;
		public void run()
		{
			while(onAir)
			{
				cnt++;
				if(cnt % 3 ==0)
				{
					intent = new Intent("aaa.bbb.Action");
					intent.putExtra("cnt", cnt);
					sendBroadcast(intent);
				}
				Log.v(TAG, "작업중.....cnt : " +cnt);
				SystemClock.sleep(500);
			}
		}
	}
	
	@Override
	public void onCreate() {
		onAir = true;
		new JobThread().start();
		cnt++;
		Log.v(TAG,"Create cnt :" +cnt);
		super.onCreate();
	}


	@Override
	public void onDestroy() {
		cnt++;
		Log.v(TAG,"Destroy cnt: " +cnt);
		super.onDestroy();
	}


	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		cnt++;
		Log.v(TAG,"onStartCommand cnt : " +cnt);
		return super.onStartCommand(intent, flags, startId);
	}


	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
