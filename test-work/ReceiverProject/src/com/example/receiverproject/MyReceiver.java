package com.example.receiverproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MainActivity";
	int cnt = 0;
	@Override
	public void onReceive(Context context, Intent intent) {
		cnt++;
		Log.v(TAG, "수신됨cnt: " +cnt);
		
		String action = intent.getAction();
		if("com.example.receiveproject.action.MyAction".equals(action))
		{
			Log.v(TAG, "myAction111111");
		}
		else if(action.equals("com.example.receiveproject.action.TAction"))
		{
			Log.v(TAG, "TAction111111111111111");
		}
	}

}
