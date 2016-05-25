package com.example.gcmproject;

import java.io.IOException;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private static final String SENDER_ID = "1019598113195";
	EditText et;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId())
			{
			case R.id.button1:
				doRegister();
				break;
			case R.id.button2:
				doUnRegister();
				break;
				
				
			}
			
		}
	};
	void doUnRegister()
	{
		String regId = getRegistrationID();
		if(regId.isEmpty()) //회원가입!
		{
			Log.v(TAG, "이미 해제됨");
		}
		else
		{
			new AsyncTask<Void, Void, String>() {

				@Override
				protected String doInBackground(Void... params) {
					GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
					String msg = "등록해제 성공";
					try {
						gcm.unregister(); //구글 서버 해제
						savegetRegistrationID("");
//						doServerDelete(); //서드 파트 서버 해제
//						Log.v(TAG, "regId : " +regId);
//						doServerInsert(regId); //Third party에 등록 중요!
						Log.v(TAG,"해제 성공");
					} catch (IOException e) {
						Log.v(TAG, "해제오류: "+e);
						msg = "오류: " +e;
					}
					
					return msg;
				}

				@Override
				protected void onPostExecute(String result) {
					et.setText(result);
					super.onPostExecute(result);
				}
				
			}.execute();	
		}	
	}
	void doRegister()
	{
		String regId = getRegistrationID();
		if(regId.equals(""))
		{
			new AsyncTask<Void, Void, String>() {

				@Override
				protected String doInBackground(Void... params) {
					GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(MainActivity.this);
					String regId = "";
					try {
						regId = gcm.register(SENDER_ID);
						savegetRegistrationID(regId);
						Log.v(TAG, "regId : " +regId);
//						doServerInsert(regId); //Third party에 등록 중요!
						
					} catch (IOException e) {
						Log.v(TAG, "등록오류: "+e);
					}
					
					return regId;
				}

				@Override
				protected void onPostExecute(String result) {
					et.setText(result);
					super.onPostExecute(result);
				}
				
			}.execute();
			
			
		}
		else
		{
			Log.v(TAG, "이미 등록됨 regId: " +regId);
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findViewById(R.id.button1).setOnClickListener(bHandler);
		findViewById(R.id.button2).setOnClickListener(bHandler);
		et= (EditText)findViewById(R.id.editText1);
		
		if(!checkPlayServices())
		{
			Toast.makeText(this, "지원 불가능", Toast.LENGTH_SHORT).show();
			finish();
		}
	}
	
	void savegetRegistrationID(String regId)
	{
		SharedPreferences sp = getSharedPreferences("gcm", 0);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("regId", regId);
		editor.commit();
	}
	String getRegistrationID()
	{
		SharedPreferences sp = getSharedPreferences("gcm", 0);
		String regId = sp.getString("regId", "");
		
		return regId;
	}
	boolean checkPlayServices()
	{
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if(resultCode != ConnectionResult.SUCCESS)
		{
			if(GooglePlayServicesUtil.isUserRecoverableError(resultCode)){
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, 9000).show();
				return true;
			}else{
				return false;
			}
		}
		else
		{
			Log.v(TAG, "사용가능한 장비");
			return true;
		}
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
