package com.example.httpproject;

import java.io.BufferedReader;
import java.io.UnsupportedEncodingException;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

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
import android.widget.Toast;

public class MyFragment3 extends Fragment {
	private static final String TAG = "MainActivity";
	Activity activity = null;
	EditText etID, etPw, etResult;
	ProgressDialog pDialog = null;
	@Override
	public void onAttach(Activity activity) {
		this.activity = activity;
		super.onAttach(activity);
	}
	class LoginThread extends Thread
	{
		public void run()
		{
			String url = "http://mynode.sungback.cloulu.com/login";
			String id = etID.getText().toString().trim();
			String pw = etPw.getText().toString().trim();
		
			Log.v(TAG, "URL: " +url);
			
			HttpClient client = null;
			HttpPost request = null;
			HttpResponse response = null;
			
			BufferedReader br = null;
			StringBuilder sb = null;
			Message msg = handler.obtainMessage();
			int code = 0;
			
			List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
			params.add(new BasicNameValuePair("id", id)); //객체생성
			params.add(new BasicNameValuePair("pw", pw));
			
			UrlEncodedFormEntity entity = null;
			
			try {
				entity = new UrlEncodedFormEntity(params);
				client = NetManager.getHttpClient();
				request = NetManager.getPost(url);
				request.setEntity(entity); //post 인경우
				response = client.execute(request);
				code = response.getStatusLine().getStatusCode();
				switch(code)
				{
				case 200:
					br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
					sb = new StringBuilder();
				
					String temp = "";
					while((temp = br.readLine()) != null)
					{
						sb.append(temp).append("\n");
					}
					Log.v(TAG, sb.toString());
					MemberResult obj = MyLoginParser.parse(sb.toString());
					if("success".equals(obj.getResult()))
					{
						msg.what = 999; //정상적
						msg.obj = obj;
					}
					else
					{
						msg.what = 555;
						msg.obj = "Failed";
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
			case 555:
				Toast.makeText(activity, msg.obj.toString(), 
						Toast.LENGTH_SHORT).show();
				etResult.setText("error: " +msg.obj);
				break;
			}
			super.handleMessage(msg);
		}
		
	};
	
	View.OnClickListener bHandler = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) 
		{
			pDialog = ProgressDialog.show(activity, "", "로그인중...");
			new LoginThread().start();
		}
	};
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = null;
		v = inflater.inflate(R.layout.frame3, null);
		etID = (EditText)v.findViewById(R.id.editText1);
		etPw = (EditText)v.findViewById(R.id.editText2);
		etResult = (EditText)v.findViewById(R.id.editText3);
		v.findViewById(R.id.button1).setOnClickListener(bHandler);
		
		return v;
	}
	
}
