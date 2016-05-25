package org.kimjw.gcm.client;

import static org.kimjw.gcm.client.CommonUtilities.DISPLAY_MESSAGE_ACTION;
import static org.kimjw.gcm.client.CommonUtilities.EXTRA_MESSAGE;
import static org.kimjw.gcm.client.CommonUtilities.SENDER_ID;

import org.kimjw.gcm.client.ServerUtilities;


import com.google.android.gcm.GCMRegistrar;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText mDisplay = null;
	AsyncTask<Void, Void, Void> mRegisterTask;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.button1 :
				doSCMRegister();
				break;
			case R.id.button2 :
				doSCMUnregister();
				break;
			}
			
		}
	};
	private void doSCMRegister(){
		final String regId = GCMRegistrar.getRegistrationId(this);
        Log.v(TAG, "regId : " + regId);
        if (regId.equals("")) {

            GCMRegistrar.register(this, SENDER_ID);
            Log.v(TAG, "사용자 등록함 ");
        } else {

            if (GCMRegistrar.isRegisteredOnServer(this)) {

                mDisplay.append(getString(R.string.already_registered) + "\n");
                Log.v(TAG, "이미존재하는 ID ");
            } else {
                final Context context = this;
                Log.v(TAG, "regId : ");
                mRegisterTask = new AsyncTask<Void, Void, Void>() {

                    @Override
                    protected Void doInBackground(Void... params) {
                        boolean registered =
                                ServerUtilities.register(context, regId);
                        // At this point all attempts to register with the app
                        // server failed, so we need to unregister the device
                        // from GCM - the app will try to register again when
                        // it is restarted. Note that GCM will send an
                        // unregistered callback upon completion, but
                        // GCMIntentService.onUnregistered() will ignore it.
                        if (!registered) {
                            GCMRegistrar.unregister(context);
                        }
                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mRegisterTask = null;
                    }

                };
                mRegisterTask.execute(null, null, null);
            }
        }
	}
	private void doSCMUnregister(){
		GCMRegistrar.unregister(this);
	}
	private final BroadcastReceiver mHandleMessageReceiver =
            new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String newMessage = intent.getExtras().getString(EXTRA_MESSAGE);
            mDisplay.append(newMessage + "\n");
            Log.v(TAG, "수신된 메세지 : action : " + intent.getAction());
        }
    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        mDisplay = (EditText)findViewById(R.id.editText1);
        findViewById(R.id.button1).setOnClickListener(bHandler);
        findViewById(R.id.button2).setOnClickListener(bHandler);
        
        
        // 실제 장비가 지원하는지 확인 여부
        GCMRegistrar.checkDevice(this);
        // manifest 파일에 설정정보가 제대로 되어 있는지 확인
         
        GCMRegistrar.checkManifest(this);
        
        
        registerReceiver(mHandleMessageReceiver,
                new IntentFilter(DISPLAY_MESSAGE_ACTION));
    }    
}
