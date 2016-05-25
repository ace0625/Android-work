package com.example.httpproject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class MyFragment2 extends Fragment {
	private static final String TAG = "MainActivity";
	Activity activity = null;
	EditText etID, etPassWord, etName, etEmail, etResult;
	ProgressDialog pDialog = null;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}
	class MemberThread extends Thread
	{
		public void run()
		{
			String url = "http://mynode.sungback.cloulu.com/join"; //회원가입 URL
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", etID.getText().toString());
			params.put("pw", etPassWord.getText().toString());
			params.put("name", etName.getText().toString());
			params.put("email", etEmail.getText().toString());
			Message msg = handler.obtainMessage();
			try {
				String data = ServerUtilities.post1(url, params);
				if(data.indexOf("id") > -1)
				{
					msg.what = 999;
					msg.obj = "Id insert success";
				}
				else
				{
					msg.what = 666;
					msg.obj = "id insert failed";
				}
//				if(data.indexOf("pw") > -1)
//				{
//					msg.what = 999;
//					msg.obj = "pw insert success";
//				}
//				else
//				{
//					msg.what = 666;
//					msg.obj = "pw insert failed";
//				}
//				if(data.indexOf("name") > -1)
//				{
//					msg.what = 999;
//					msg.obj = "name insert success";
//				}
//				else
//				{
//					msg.what = 666;
//					msg.obj = "name insert failed";
//				}
//				if(data.indexOf("email") > -1)
//				{
//					msg.what = 999;
//					msg.obj = "email insert success";
//				}
//				else
//				{
//					msg.what = 666;
//					msg.obj = "email insert failed";
//				}
//			
			} catch (IOException e) {
				Log.v(TAG,"insert error : " +e);
				msg.what = 777;
				msg.obj = e;
			}
			handler.sendMessage(msg);
		}
	}
	
	Handler handler = new Handler()
	{

		@Override
		public void handleMessage(Message msg) 
		{
			if(pDialog != null)
			{
				pDialog.cancel();
			}
			switch(msg.what)
			{
			case 999:
			case 777: 
			case 666:
				etResult.setText(msg.obj.toString());
				break;
			case 888:
				etResult.setText("code: " +msg.arg1);
				break;
			
			}
			super.handleMessage(msg);
		}
		
	};
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			pDialog = ProgressDialog.show(activity, "", "조회중...");
			new MemberThread().start();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.frame2, null);
		
		v.findViewById(R.id.button1).setOnClickListener(bHandler);
		etID = (EditText)v.findViewById(R.id.editText1);
		etPassWord = (EditText)v.findViewById(R.id.editText2);
		etName = (EditText)v.findViewById(R.id.editText3);
		etEmail = (EditText)v.findViewById(R.id.editText4);
		etResult = (EditText)v.findViewById(R.id.editText5);
		
		 
		return v;
	}
	
}
