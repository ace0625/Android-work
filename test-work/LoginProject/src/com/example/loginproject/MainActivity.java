package com.example.loginproject;


import java.util.HashMap;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.skp.openplatform.android.sdk.api.APIRequest;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.CONTENT_TYPE;
import com.skp.openplatform.android.sdk.common.PlanetXSDKConstants.HttpMethod;
import com.skp.openplatform.android.sdk.common.PlanetXSDKException;
import com.skp.openplatform.android.sdk.common.RequestBundle;
import com.skp.openplatform.android.sdk.common.ResponseMessage;
import com.skp.openplatform.android.sdk.oauth.OAuthInfoManager;
import com.skp.openplatform.android.sdk.oauth.OAuthListener;
import com.skp.openplatform.android.sdk.oauth.PlanetXOAuthException;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	EditText et;
	Button btnLogIn;
	OneIdProfile profile = null;
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.btnLogIn :
				logIn();
				break;
			case R.id.btnLogout :
				logOut();
				break;
			}
			
		}
	};
void logOut(){
		
		OAuthInfoManager.logout(this, new OAuthListener() {
			@Override
			public void onError(String error) {
				Log.v(TAG, "" + error);
				Toast.makeText(getApplicationContext(), "" + error,
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onComplete(String arg0) {
				Log.v(TAG, arg0);
				Toast.makeText(getApplicationContext(), "LogOut 성공",
						Toast.LENGTH_LONG).show();
				clearProfile();
				setLogOut();
			}
		});				
	}
	String token = "";
	private void startOAuth() {
		
		Log.v(TAG, "start oauth");
		try {
			
			OAuthInfoManager.login(MainActivity.this, new OAuthListener() {
				@Override
				public void onError(String error) {
					Log.v(TAG, "" + error);
					Toast.makeText(getApplicationContext(), "" + error,
							Toast.LENGTH_LONG).show();
				}

				@Override
				public void onComplete(String arg0) {
					Log.v(TAG, arg0);
					token = OAuthInfoManager.authorInfo.getAccessToken();
					Log.v(TAG, "token: " +token);
					
					new JobThread().start();
				}
			});

		} catch (PlanetXOAuthException e) {
			e.printStackTrace();
			Log.e("auth", "" + e);
			Toast.makeText(getApplicationContext(), "" + e, Toast.LENGTH_LONG)
					.show();
		}
		
	}
	Handler handler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch(msg.what){
			case 0 :
				profile = (OneIdProfile)msg.obj;
				setProfile();
				break;
			default :
				Toast.makeText(MainActivity.this, "오류 : " + msg.obj,
						Toast.LENGTH_SHORT).show();
				break;
			}
		}
		
	};
	class JobThread extends Thread {
		public void run() {
			APIRequest.setAppKey("2af0c219-62ea-39c6-aba9-b2c629ac4fd8");
			APIRequest api = new APIRequest();


			String url = "https://apis.skplanetx.com/users/me/profile";
			HashMap<String, Object> param = new HashMap<String, Object>();
			param.put("version", "1");
//			param.put("access_token", token); //생략 가능함
			
			RequestBundle requestBundle = new RequestBundle();
			requestBundle.setUrl(url);
			requestBundle.setParameters(param);
			requestBundle.setHttpMethod(HttpMethod.GET);
			requestBundle.setResponseType(CONTENT_TYPE.JSON);
//			requestBundle.setAppID("2af0c219-62ea-39c6-aba9-b2c629ac4fd8");

			
			ResponseMessage result = new ResponseMessage();
			Message msg = handler.obtainMessage();
			try {
				result = api.request(requestBundle);
				profile = MyJSONParser.parse(result.toString());
				Log.v(TAG, " 아이디 : " + profile.getUserId());
				Log.v(TAG, " 서비스번호 : " + profile.getServiceNo());
				Log.v(TAG, " 이메일 : " + profile.getEmail());
				Log.v(TAG, " 이름 : " + profile.getUserName());
				
				saveProfile();
				msg.what = 0;
				msg.obj = profile;
						
			} catch (PlanetXSDKException e) {
				msg.what = 1;
				msg.obj = e.toString();
				Log.v(TAG, "error : " + e);
			}
		
			handler.sendMessage(msg);
		}
	}
	
	void logIn(){
		APIRequest.setAppKey("2af0c219-62ea-39c6-aba9-b2c629ac4fd8");
//		APIRequest.setAppKey("20e0098a-451b-3a54-af4f-dba319e1d211");
		
		OAuthInfoManager.clientId = "2224f9c8-b483-333d-8d06-d434b8542267";
		OAuthInfoManager.clientSecret = "2d053d49-2f12-34a0-9f99-82d36ba834f6";
		OAuthInfoManager.scope = "user";
//		OAuthInfoManager.scope = "user,nate,cyworld";

		startOAuth();
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setUI();
		getLogInData();
	}
	void setUI(){
		et = (EditText)findViewById(R.id.etResult);
		btnLogIn = (Button)findViewById(R.id.btnLogIn);
		
		findViewById(R.id.btnLogIn).setOnClickListener(bHandler);
		findViewById(R.id.btnLogout).setOnClickListener(bHandler);
	}
	void setProfile(){
		et.setText(String.format("%s(%s) email : %s " ,
				profile.getUserId(), profile.getUserName(), profile.getEmail()));
		btnLogIn.setVisibility(View.INVISIBLE);
	}
	void setLogOut(){
		et.setText("로그인 한후 활용해주세요");
		btnLogIn.setVisibility(View.VISIBLE);
	}
	void saveProfile(){
		SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.putString("userId", profile.getUserId());
		editor.putString("email", profile.getEmail());
		editor.putString("userName", profile.getUserName());
		editor.commit();
	}
	void getLogInData(){
		SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
			
		String userId = sp.getString("userId", "");
		if( !userId.equals( "" )){
			profile = new OneIdProfile();
			profile.setUserId(userId);
			profile.setEmail(sp.getString("email",""));
			profile.setUserName(sp.getString("userName", ""));
			setProfile();
		}else{
			setLogOut();
			}
		}
	
	void clearProfile(){
		SharedPreferences sp = getSharedPreferences("Login", MODE_PRIVATE);
		SharedPreferences.Editor editor = sp.edit();
		editor.clear();
		editor.commit();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
