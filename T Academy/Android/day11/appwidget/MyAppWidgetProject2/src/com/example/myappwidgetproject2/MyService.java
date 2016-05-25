package com.example.myappwidgetproject2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

public class MyService extends Service {
	private static final String TAG = "MainActivity";
	boolean onAir = false;
	JobThread trd = null;
	
	int cnt = 0;
	class JobThread extends Thread{
		public void run(){
			Intent intent = null;
			while( onAir ){
				cnt++;
				Log.v(TAG, "작업중... cnt : " + cnt);
				intent = new Intent("aaa.bbb.actionCount");
				intent.putExtra("cnt", cnt);
				sendBroadcast(intent);
				SystemClock.sleep(500);
			}
		}
	}
	
	@Override
	public void onCreate() {
		onAir = true;
		trd = new JobThread();
		trd.start();
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		onAir = false;
		try{
			trd.join();
		}catch(InterruptedException e){}
		super.onDestroy();
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return super.onStartCommand(intent, flags, startId);
	}

	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

}
