package com.example.oauthloginproject;



import org.json.JSONObject;

import android.util.Log;

public class MyJSONParser {

	private static final String TAG = "MainActivity";
	public static OneIdProfile parse(String str) {
		OneIdProfile oneIdProfile = new OneIdProfile();
		JSONObject json = null;
		JSONObject jprofile = null;

		
		try {
			json = new JSONObject(str);
			jprofile = json.getJSONObject("profile");
			
			oneIdProfile.setServiceNo(jprofile.getString("serviceNo"));
			oneIdProfile.setUserId(jprofile.getString("userId"));
			oneIdProfile.setUserName(jprofile.getString("userName"));
			oneIdProfile.setEmail(jprofile.getString("email"));

			
		} catch(Exception e) {
			Log.v(TAG, "파서 오류 : " + e );
		}
		
		return oneIdProfile;
	}
	
}
