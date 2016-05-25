package com.example.gcmproject;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
	private static final String TAG = "MainActivity";
	@Override
	public void onReceive(Context context, Intent intent) {
//		ComponentName cName = new ComponentName(context, GCMIntentService.class);
		ComponentName cName = new ComponentName(context.getPackageName(), GCMIntentService.class.getName());
		
		doNoti(intent, context);
//		startWakefulService(context, intent.setComponent(cName));
//		setResultCode(Activity.RESULT_OK);

	}
	
	private void doNoti(Intent intent, Context context)
	{
		PowerManager.WakeLock sCpuWakeLock;  //깨워주기 
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	
		sCpuWakeLock = pm.newWakeLock( PowerManager.FULL_WAKE_LOCK |
				PowerManager.ACQUIRE_CAUSES_WAKEUP |
				PowerManager.ON_AFTER_RELEASE, TAG);
		
		sCpuWakeLock.acquire(); 

		int code  = Integer.parseInt(intent.getStringExtra("code"));
		String msg = intent.getStringExtra("msg");
		NotificationManager manager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification noti = null;
		Intent result = null;
		PendingIntent pIntent = null;
		switch(code)
		{
		case 1:
			Log.v(TAG,"case 1");
			result = new Intent(context, MainActivity.class);
			result.putExtra("msg", msg);
			pIntent = PendingIntent.getActivity(context, 0, result, PendingIntent.FLAG_CANCEL_CURRENT);
			
//			noti = new Notification(R.drawable.ic_launcher, "문자수신됨", System.currentTimeMillis()); //실제 상용
//			noti.setLatestEventInfo(context, "title", msg, pIntent);
			
			noti = new Notification.Builder(context)
			.setContentText(msg)
			.setContentTitle("일반메시지")
			.setSmallIcon(R.drawable.ic_launcher)
			.setContentIntent(pIntent)
			.setAutoCancel(true)
			.setTicker("메세지 수신완료")
			.setWhen(System.currentTimeMillis())
			.build();
			break;
			
			
		}
		manager.notify(1, noti);
	}

}
