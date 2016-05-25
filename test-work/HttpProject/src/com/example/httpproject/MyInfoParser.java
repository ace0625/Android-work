package com.example.httpproject;

import org.json.JSONObject;

import android.util.Log;

public class MyInfoParser {
	private static final String TAG = "MainActivity";

	public static Object parse(String data)
	{
		Object obj = null;
		JSONObject json = null;
		try {
			json = new JSONObject(data);
			InfoVO vo = new InfoVO();
			vo.setId(json.getString("id"));
			vo.setEmail(json.getString("email"));
			vo.setName(json.getString("name"));
			obj = vo;
		} catch (Exception e) {
			Log.v(TAG, "json parse error: " +e);
			try {
				obj = json.getString("result");
			} catch (Exception e2) {
				Log.v(TAG, "json parse error 2 : " +e2);
			}
			
		}
		return obj;
	}
}
