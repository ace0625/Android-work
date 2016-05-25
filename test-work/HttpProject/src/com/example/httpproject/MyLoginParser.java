package com.example.httpproject;

import org.json.JSONObject;

import android.util.Log;

public class MyLoginParser {
	private static final String TAG = "MainActivity";
	
	public static MemberResult parse(String data)
	{
		MemberResult result = new MemberResult();
		JSONObject json = null;
		try {
			json = new JSONObject(data);
			result.setResult(json.getString("result"));
		} catch (Exception e) {
			Log.v(TAG, "longin parse error : " +e);
		}
		return result;
	}
}
