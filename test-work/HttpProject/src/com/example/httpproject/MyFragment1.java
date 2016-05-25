package com.example.httpproject;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

public class MyFragment1 extends Fragment {
	private static final String TAG = "MainActivity";
	Activity activity = null;
	EditText etID, etResult;
	ProgressDialog pDialog = null;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}
	class GetThread extends Thread
	{
		public void run()
		{
			String url = "http://mynode.sungback.cloulu.com/user/";
			String id = etID.getText().toString().trim();
			url += id; //trim 좌우 공백 배제
			
//			try {
//				url += URLEncoder.encode(id, "UTF-8"); //한글
//			} catch (Exception e) {
//				// TODO: handle exception
//			}
			Log.v(TAG, "URL: " +url);
			HttpURLConnection connection = null;
			BufferedReader br = null;
			StringBuilder sb = null;
			Message msg = handler.obtainMessage();
			int code = 0;
			try {
				connection = (HttpURLConnection)new URL(url).openConnection();
				connection.setRequestMethod("GET");
				connection.setConnectTimeout(10000);
				code = connection.getResponseCode();
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
					sb = new StringBuilder();
					String temp = "";
					while((temp = br.readLine()) != null)
					{
						sb.append(temp).append("\n");
					}
					Log.v(TAG, sb.toString());
					Object obj = MyInfoParser.parse(sb.toString());
					msg.what = 999; //정상적
					InfoVO vo = null;
						try {
							vo = (InfoVO)obj;
							msg.obj = vo.toString();
						} catch (ClassCastException e) {
							Log.v(TAG,"casting error: " +e);
							msg.obj = obj;
						}
					break;
				default:
					msg.what = 888;
					msg.arg1 = code;
					break;
				}
			} catch (Exception e) {
				msg.what = 777;
				msg.obj = e;
			}finally{
				if(br!=null)
				{
					try {
						br.close();
					} catch (Exception e2) {
						
					}
					if(connection != null)
					{
						connection.disconnect();
					}
				}
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
			new GetThread().start();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.frame1, null);
		etID = (EditText)v.findViewById(R.id.editText1);
		etResult = (EditText)v.findViewById(R.id.editText2);
		v.findViewById(R.id.button1).setOnClickListener(bHandler);
		
		return v;
	}
	
}
