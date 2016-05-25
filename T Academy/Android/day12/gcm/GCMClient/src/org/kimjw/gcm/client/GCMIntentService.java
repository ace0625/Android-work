/*
 * Copyright 2012 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kimjw.gcm.client;

import static org.kimjw.gcm.client.CommonUtilities.SENDER_ID;
import static org.kimjw.gcm.client.CommonUtilities.displayMessage;



import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.PowerManager;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;
import com.google.android.gcm.GCMRegistrar;

/**
 * {@link IntentService} responsible for handling GCM messages.
 */
public class GCMIntentService extends GCMBaseIntentService {


    private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }

    @Override
    protected void onRegistered(Context context, String registrationId) {
        Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, getString(R.string.gcm_registered));
        ServerUtilities.register(context, registrationId);
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        if (GCMRegistrar.isRegisteredOnServer(context)) {
            ServerUtilities.unregister(context, registrationId);
        } else {
            Log.i(TAG, "Ignoring unregister callback");
        }
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String code = intent.getStringExtra("code");
        String msg = intent.getStringExtra("msg");
        
        Log.v(TAG, "code : " + code);
        Log.v(TAG, "msg : " + msg);
        
        String message = getString(R.string.gcm_message);
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message, code, msg);
       
		PowerManager.WakeLock sCpuWakeLock; 
		PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
	
		sCpuWakeLock = pm.newWakeLock( PowerManager.FULL_WAKE_LOCK |
				PowerManager.ACQUIRE_CAUSES_WAKEUP |
				PowerManager.ON_AFTER_RELEASE, TAG);
		
		sCpuWakeLock.acquire(); 

    }

    @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
    }

    @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }
    private static void generateNotification(Context context, String message, String code, String msg) {
        int icon = R.drawable.ic_stat_gcm;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        
        Intent notificationIntent = new Intent(context, MainActivity.class);
        notificationIntent.putExtra("msg", msg);
        String title = context.getString(R.string.app_name);
        if(code.equals("2")){
        	message = "지정된 웹페이지로 이동하세요";
        	notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(msg));
        	title="회사정보 웹페이지로 이동";
        }else if(code.equals("3")){
        	message = "특정 지도를 확인하세요";
        	String url = "geo:%f,%f?z=17";
        	String[] srr = msg.split(",");
    		String mapUrl = String.format(url, Double.parseDouble(srr[0]), Double.parseDouble(srr[1]));
    		Uri uri = Uri.parse(mapUrl);
    		notificationIntent = new Intent(Intent.ACTION_VIEW, uri);
    		title = "구글지도 확인";
        }
        Notification notification = new Notification(icon, message, when);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0, notification);
    }

}
