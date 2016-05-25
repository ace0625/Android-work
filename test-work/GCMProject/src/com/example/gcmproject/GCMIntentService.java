package com.example.gcmproject;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class GCMIntentService extends IntentService {
	private static final String TAG = "MainActivity";
	public GCMIntentService(String name) {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle bundle = intent.getExtras(); //불러오기
		Log.v(TAG, bundle.toString());
		
		GCMBroadcastReceiver.completeWakefulIntent(intent); //처리 완료
		
	}

}
