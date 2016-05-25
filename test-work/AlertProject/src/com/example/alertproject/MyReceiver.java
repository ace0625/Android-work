package com.example.alertproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {
	private static final String TAG = "MainActivity";
	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		String data = intent.getStringExtra("data");
		boolean flag = intent.getBooleanExtra(LocationManager.KEY_PROXIMITY_ENTERING, true);
		
		Toast.makeText(context, "Action: " +action + "data: " +data + "flag: " +flag, Toast.LENGTH_SHORT).show();
		Log.v(TAG, "action : " +action);
		Log.v(TAG, "data : " +data);
		Log.v(TAG, "flag : " +(flag? "들어옴":"나감"));
		
	}

}
